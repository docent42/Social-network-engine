package com.skillbox.sw.controller;


import com.skillbox.sw.api.request.MessageSendRequestBodyApi;
import com.skillbox.sw.api.request.RequestDialogUsersApi;
import com.skillbox.sw.api.request.RequestInviteLink;
import com.skillbox.sw.api.response.AbstractResponse;
import com.skillbox.sw.api.response.InviteLinkApi;
import com.skillbox.sw.api.response.ReportApi;
import com.skillbox.sw.api.response.ResponseApi;
import com.skillbox.sw.service.DialogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.skillbox.sw.config.SecurityConstants.HEADER;

@RestController
@RequestMapping("/dialogs")
public class DialogController {


    private DialogService dialogService;

    @Autowired
    public DialogController(DialogService dialogService) {
        this.dialogService = dialogService;
    }


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public AbstractResponse getUsersDialogList(@RequestHeader(value = HEADER) String token,
                                               @RequestParam(value = "query", defaultValue = "") String query,
                                               @RequestParam(value = "offset", defaultValue = "0") int offset,
                                               @RequestParam(value = "itemPerPage", defaultValue = "20") int itemPerPage) {

        return dialogService.getUsersDialogList(token, query,offset, itemPerPage);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public AbstractResponse createDialog(@RequestHeader(value = HEADER) String token,
                                         @RequestBody RequestDialogUsersApi userIdList) {

        return dialogService.createDialog(userIdList,token);
    }

    @PostMapping("/{dialogId}/messages")
    @ResponseStatus(HttpStatus.OK)
    public AbstractResponse sendMessage(@RequestHeader(value = HEADER) String token,
                                        @PathVariable int dialogId,
                                        @RequestBody MessageSendRequestBodyApi sentMessage) {

        return dialogService.sendMessage(dialogId, sentMessage, token);
    }

    @GetMapping("/unreaded")
    @ResponseStatus(HttpStatus.OK)
    public AbstractResponse getUnreadMessagesCountTotal(@RequestHeader (value = HEADER) String token) {

        return dialogService.getUnreadMessagesCountTotal(token);
    }

    @DeleteMapping("/{dialogId}")
    @ResponseStatus(HttpStatus.OK)
    public AbstractResponse deleteDialog(@PathVariable int dialogId) {

        return dialogService.deleteDialog(dialogId);
    }

    @DeleteMapping("/{dialogId}/users")
    @ResponseStatus(HttpStatus.OK)
    public AbstractResponse deleteUsersFromDialog(@PathVariable int dialogId,
                                                  @RequestParam("users_ids") List<String> userIds) {

        return dialogService.deleteUsersFromDialog(dialogId, userIds);
    }

    @PutMapping("/{dialogId}/users")
    @ResponseStatus(HttpStatus.OK)
    public AbstractResponse addUserToDialog(@PathVariable int dialogId,
                                            @RequestBody RequestDialogUsersApi userIdList) {

        return dialogService.addUserToDialog(dialogId, userIdList);
    }

    @GetMapping("/{dialogId}/users/invite")
    @ResponseStatus(HttpStatus.OK)
    public AbstractResponse getInviteLinkToDialog(@PathVariable int dialogId) {
        return dialogService.getInviteLinkToDialog(dialogId);
    }

    @PutMapping("/{dialogId}/users/join")
    @ResponseStatus(HttpStatus.OK)
    public AbstractResponse joinInDialogByLink(@RequestHeader(value = HEADER) String token,
                                               @PathVariable int dialogId,
                                               @RequestBody RequestInviteLink link) {

        return dialogService.joinToDialogByLink(token,dialogId,link);
    }

    @GetMapping("/{dialogId}/messages")
    @ResponseStatus(HttpStatus.OK)
    public AbstractResponse getDialogMessages(@PathVariable int dialogId,
                                              @RequestParam(value = "query", defaultValue = "") String query,
                                              @RequestParam(value = "offset", defaultValue = "0") int offset,
                                              @RequestParam(value = "itemPerPage", defaultValue = "20") int itemPerPage) {

        return dialogService.getDialogMessages(dialogId, query, offset, itemPerPage);
    }

    @DeleteMapping("/{dialogId}/messages/{messageId}")
    @ResponseStatus(HttpStatus.OK)
    public AbstractResponse deleteMessage(@PathVariable int dialogId,
                                          @PathVariable int messageId) {

        return dialogService.deleteMessage(messageId);
    }

    @PutMapping("/{dialogId}/messages/{messageId}")
    @ResponseStatus(HttpStatus.OK)
    public AbstractResponse editMessage(@PathVariable int dialogId,
                                        @PathVariable int messageId,
                                        @RequestBody MessageSendRequestBodyApi newMessage) {

        return dialogService.editMessage(messageId,newMessage);
    }

    @PutMapping("/{dialogId}/messages/{messageId}/recover")
    @ResponseStatus(HttpStatus.OK)
    public AbstractResponse recoverMessage(@PathVariable int dialogId,
                                           @PathVariable int messageId) {

        return dialogService.recoverMessage(messageId);
    }

    @PutMapping("/{dialogId}/messages/{messageId}/read")
    @ResponseStatus(HttpStatus.OK)
    public AbstractResponse changeStatusFromSentToRead(@PathVariable int dialogId,
                                                       @PathVariable int messageId) {

        return dialogService.changeStatusFromSentToRead(messageId);
    }

    @GetMapping("/{dialogId}/activity/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public AbstractResponse getLastActivity(@PathVariable int dialogId,
                                            @PathVariable int userId) {

        return dialogService.getLastActivity(dialogId, userId);
    }

    @PostMapping("/{dialogId}/activity/{userId}")
    public AbstractResponse changeTypingStatus(@PathVariable int dialogId,
                                               @PathVariable int userId) {

        return new ResponseApi<>("typing...", new ReportApi("ok"));
    }
}
