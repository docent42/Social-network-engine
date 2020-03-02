package com.skillbox.sw.service;

import com.skillbox.sw.api.request.MessageSendRequestBodyApi;
import com.skillbox.sw.api.request.RequestDialogUsersApi;
import com.skillbox.sw.api.request.RequestInviteLink;
import com.skillbox.sw.api.response.*;
import com.skillbox.sw.config.jwt.JwtProvider;
import com.skillbox.sw.domain.Dialog;
import com.skillbox.sw.domain.Message;
import com.skillbox.sw.domain.Person;
import com.skillbox.sw.domain.enums.ReadStatus;
import com.skillbox.sw.mapper.DateMapper;
import com.skillbox.sw.mapper.DialogMapper;
import com.skillbox.sw.mapper.MessageMapper;
import com.skillbox.sw.repository.DialogRepository;
import com.skillbox.sw.repository.MessageRepository;
import com.skillbox.sw.repository.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = false)
@AllArgsConstructor
public class DialogService {

    private DialogRepository dialogRepository;
    private PersonRepository personRepository;
    private MessageRepository messageRepository;
    private PersonService personService;
    private MessageMapper messageMapper;
    private DialogMapper dialogMapper;
    private DateMapper dateMapper;
    private final int NO_UNREAD_MESSAGES = 0;
    private final long ONLINE_ACTIVITY_PERIOD = 15;

    @Transactional(readOnly = true)
    public AbstractResponse getUsersDialogList(String token, String query, int offset, int itemPerPage) {

        Person currentUser = personService.getCurrentPersonByToken(token);

        Page<DialogApi> page = dialogRepository.getAllDialogs(currentUser.getId(),
                                                                    query,
                                                                    PageRequest.of(offset,itemPerPage))
                                                     .map(dialog -> dialogMapper.dialogToDialogApi(dialog));

        return new DialogListApi("dialog list recieved",page.toList(),
                                (int) page.getTotalElements(),offset,itemPerPage);
    }

    public AbstractResponse createDialog(RequestDialogUsersApi userList, String token) {

        Dialog dialog = new Dialog();
        dialog.setRecipients(personRepository.findByIdIn(userList.getUserIds()));

        Person dialogOwner = personService.getCurrentPersonByToken(token);
        dialog.setOwner(dialogOwner);
        dialog.setUnreadCount(NO_UNREAD_MESSAGES);
        int newDialogId = dialogRepository.saveAndFlush(dialog).getId();

        sendMessage(newDialogId,new MessageSendRequestBodyApi("Аллоха всем !!!"),token);

        return new ResponseApi<>("dialog added", new IdApi(newDialogId));
    }

    public AbstractResponse sendMessage(int targetDialogId, MessageSendRequestBodyApi sentMessage,String token) {

        Person messageAuthor = personService.getCurrentPersonByToken(token);

        Dialog targetDialog = getDialogById(targetDialogId);

        Message messageToSave = Message.builder()
                                        .time(LocalDate.now())
                                        .dialog(targetDialog)
                                        .author(messageAuthor)
                                        .messageText(sentMessage.getMessageText())
                                        .readStatus(ReadStatus.SENT)
                                        .build();
        messageToSave = messageRepository.saveAndFlush(messageToSave);

        return new ResponseApi<>("Message added to Dialog",
                                 messageMapper.messageToMessageApi(messageToSave));
    }

    @Transactional(readOnly = true)
    public AbstractResponse getUnreadMessagesCountTotal(String token) {

        List<Dialog> currentUserDialogs = personService.getCurrentPersonByToken(token)
                                                       .getDialogList();
        int totalCount = currentUserDialogs.stream()
                .filter( dialog -> !dialog.isDeleted())
                .mapToInt(Dialog::getUnreadCount)
                .sum();

        return new ResponseApi<>("Count complete successfully", new UnreadCount(totalCount));
    }

    public AbstractResponse deleteDialog(int dialogId) {

        Dialog dialogToDelete = getDialogById(dialogId);

        dialogToDelete.setDeleted(true);

        dialogRepository.saveAndFlush(dialogToDelete);

        return new ResponseApi<>("Dialog deleted", new IdApi(dialogId));
    }

    public AbstractResponse deleteUsersFromDialog(int dialogId, List<String> userIdStr) {

        List<Integer> userIdsToDelete = userIdStr.stream()
                .map(Integer::parseInt).collect(Collectors.toList());

        Dialog dialogToEdit = getDialogById(dialogId);

        List<Integer> userIdsToUpdate = personToId(dialogToEdit.getRecipients());

        userIdsToUpdate.removeAll(userIdsToDelete);

        dialogToEdit.setRecipients(personRepository.findByIdIn(userIdsToUpdate));
        dialogRepository.saveAndFlush(dialogToEdit);

        return new ResponseApi<>("Users deleted from Dialog " + dialogId,
                                 new ResponseDialogUsersApi(userIdsToUpdate));
    }

    public AbstractResponse addUserToDialog(int dialogId,RequestDialogUsersApi userList) {

        List<Person> usersToAdd = personRepository.findByIdIn(userList.getUserIds());

        Dialog dialogToEdit = getDialogById(dialogId);

        dialogToEdit.getRecipients().addAll(usersToAdd);
        dialogRepository.saveAndFlush(dialogToEdit);

        return new ResponseApi<>("Users added to Dialog " + dialogId,
                                 new ResponseDialogUsersApi(personToId(dialogToEdit.getRecipients())));
    }

    @Transactional(readOnly = true)
    public AbstractResponse getInviteLinkToDialog(int dialogId) {

        Dialog dialogToInvite = getDialogById(dialogId);

        return new ResponseApi<>("Link sent...", new InviteLinkApi(dialogToInvite.getInviteCode()));
    }

    @Transactional(readOnly = true)
    public AbstractResponse getDialogMessages(int dialogId, String query,int offset, int itemPerPage) {

        Dialog targetDialog = getDialogById(dialogId);

        Page<MessageApi> page = messageRepository.findAllByDialogAndMessageTextContaining(targetDialog,
                query,
                PageRequest.of(offset,itemPerPage))
                .map(message -> messageMapper.messageToMessageApi(message));

        return new MessageListApi("message list recieved",
                                  page.toList(), (int) page.getTotalElements(),offset,itemPerPage);
    }

    public AbstractResponse joinToDialogByLink(String token,int dialogId, RequestInviteLink link) {

        Dialog dialogToJoin = getDialogById(dialogId);
        Person currentUser = personService.getCurrentPersonByToken(token);

        if (dialogToJoin.getInviteCode().equals(link.getLink())) {

            dialogToJoin.getRecipients().add(currentUser);
            dialogRepository.saveAndFlush(dialogToJoin);
        }
        return new ResponseApi<>("User added to Dialog " + dialogId + " by inviteLink",
                new ResponseDialogUsersApi(personToId(dialogToJoin.getRecipients())));
    }

    public AbstractResponse deleteMessage(int messageId) {

       Message messageToDelete = getMessageById(messageId);
       messageToDelete.setDeleted(true);
       messageRepository.saveAndFlush(messageToDelete);

        return new ResponseApi<>("Message deleted",new MessageIdApi(messageToDelete.getId()));
    }

    public AbstractResponse editMessage(int messageId, MessageSendRequestBodyApi newMessage) {

        Message messageToEit = getMessageById(messageId);
        messageToEit.setMessageText(newMessage.getMessageText());
        messageToEit.setReadStatus(ReadStatus.SENT);
        messageRepository.saveAndFlush(messageToEit);

        return new ResponseApi<>("Message with id - " + messageId + " updated",
                                 messageMapper.messageToMessageApi(messageToEit));
    }

    public AbstractResponse recoverMessage(int messageId) {

        Message messageToRecover = getMessageById(messageId);
        messageToRecover.setDeleted(false);
        messageToRecover.setReadStatus(ReadStatus.SENT);
        messageRepository.saveAndFlush(messageToRecover);

        return new ResponseApi<>("Message with id - " + messageId + " recovered",
                                 messageMapper.messageToMessageApi(messageToRecover));
    }

    public AbstractResponse changeStatusFromSentToRead(int messageId) {

        Message readMessage = getMessageById(messageId);
        readMessage.setReadStatus(ReadStatus.READ);
        messageRepository.saveAndFlush(readMessage);

        return new ResponseApi<>("Message status id - " + messageId + " changed",
                                 new ReportApi("ok"));
    }

    @Transactional(readOnly = true)
    public AbstractResponse getLastActivity(int dialogId, int userId) {

        Person targetPerson = getDialogById(dialogId).getRecipients()
                .stream()
                .filter(user -> user.getId() == userId)
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("No such user in dialog " + dialogId));
    UserApi targetUser = new UserApi();
    long minutes = targetPerson.getLastOnlineTime().until(LocalDateTime.now(), ChronoUnit.MINUTES);

    targetUser.setUserActivity(dateMapper.asEpochMillis(targetPerson.getLastOnlineTime()));
    targetUser.setOnline(minutes <= ONLINE_ACTIVITY_PERIOD);

        return new ResponseApi<>("last activity recieved",targetUser);
    }

    @Transactional(readOnly = true)
    public Dialog getDialogById(int dialogId) {
        return dialogRepository.findById(dialogId)
                .orElseThrow(() -> new EntityNotFoundException("Dialog with Id - " + dialogId + " not found"));
    }

    @Transactional(readOnly = true)
    public Message getMessageById(int messageId) {
        return messageRepository.findById(messageId)
                .orElseThrow(() -> new EntityNotFoundException("Message with Id - " + messageId + " not found"));
    }

    public List<Integer> personToId(List<Person> personList) {
        return  personList.stream()
                .map(Person::getId)
                .collect(Collectors.toList());
    }
}
