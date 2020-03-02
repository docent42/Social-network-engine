package com.skillbox.sw.service;

import com.skillbox.sw.api.request.MessageSendRequestBodyApi;
import com.skillbox.sw.api.request.PostCommentApi;
import com.skillbox.sw.api.request.RequestPostApi;
import com.skillbox.sw.api.response.AbstractResponse;
import com.skillbox.sw.api.response.CommentApi;
import com.skillbox.sw.api.response.ResponseApi;
import com.skillbox.sw.api.response.ResponsePostApi;
import com.skillbox.sw.domain.Post;
import com.skillbox.sw.domain.PostComment;
import com.skillbox.sw.mapper.CommentMapper;
import com.skillbox.sw.mapper.DateMapper;
import com.skillbox.sw.mapper.PostMapper;
import com.skillbox.sw.repository.MessageRepository;
import com.skillbox.sw.repository.PostCommentRepository;
import com.skillbox.sw.repository.PostRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class PostService {

    private PostRepository postRepository;
    private PostCommentRepository commentRepository;

    private PostMapper postMapper;
    private DateMapper dateMapper;
    private CommentMapper commentMapper;


    // поиск пубоикации ГОТОВ
    public List<ResponsePostApi> searchPublication(String text, long dateFrom, long dateTo, int offset, int itemPerPage) {

        int limit = itemPerPage < 1 ? 10 : itemPerPage;
        final PageRequest pageRequest = PageRequest.of(offset / itemPerPage, limit);

        LocalDate dateF = dateMapper.asLocalDate(dateFrom);
        LocalDate dateT = dateMapper.asLocalDate(dateTo);

        Page<Post> allPosts = postRepository.findAllByPostTextContainingAndTimeBetween(text, dateF, dateT, pageRequest);
        if (allPosts.isEmpty()) {
            throw new EntityNotFoundException("Not Found");
        }

        List<ResponsePostApi> postList = new ArrayList<>();
        for (Post post : allPosts) {
            postList.add(postMapper.postToResponsePostApi(post));
        }

        return postList;
    }

    // получение публикации по id ГОТОВ | Постманом ПРОВЕРЕННО
    public AbstractResponse receivingPublication(int postId) {

        Post post = postRepository.findById(postId).orElseThrow(() -> new EntityNotFoundException("Post with this ID not found."));

        return postMapper.postToResponsePostApi(post);
    }

    // Редактирование публикации по ID ГОТОВ | Постманом ПРОВЕРЕННО
    public AbstractResponse editingPublication(int postId, long publishDate, RequestPostApi requestPostApi) {

        Post postBefore = postRepository.findById(postId).orElseThrow(() -> new EntityNotFoundException("Not found"));
        Post postAfter = postMapper.requestPostApiToPost(requestPostApi);

        postAfter.setId(postBefore.getId());
        postAfter.setAuthor(postBefore.getAuthor());
        postAfter.setTime(dateMapper.asLocalDate(publishDate));

        postRepository.save(postAfter);

        return postMapper.postToResponsePostApi(postAfter);
    }

    // Удаление текущего пользователя по ID ГОТОВО | Постманом ПРОВЕРЕННО
    public AbstractResponse deletePublication(int postId) {

        Post post = postRepository.findById(postId).orElseThrow( () -> new EntityNotFoundException("Not found"));

        if (post.isBlocked()) {
            throw new EntityNotFoundException("Post is already blocked");
        }

        post.setDeleted(true);
        postRepository.save(post);

        return new ResponseApi("Post deleted", postMapper.postToResponsePostApi(post));
    }

    // Восстановление публикации по ID ГОТОВО | Постманом ПРОВЕРЕННО
    public AbstractResponse recoveryPublication(int postId) {

        Post post = postRepository.findById(postId).orElseThrow( () -> new EntityNotFoundException("Not found"));

        if (!post.isBlocked()) {
            throw new EntityNotFoundException("Post is already unlocked.");
        }

        post.setDeleted(false);
        postRepository.save(post);

        return new ResponseApi("Post recover", postMapper.postToResponsePostApi(post));
    }

    // Получение комментариев на публикации ГОТОВ | Постманом ПРОВЕРЕННО
    public List<CommentApi> gettingCommentsPublication(int postId, int offset, int itemPerPage) {

        List<PostComment> listComment = commentRepository.findAllByPostId(postId);

        if (listComment.isEmpty()) {
            throw new EntityNotFoundException("No comments were found for the publication");
        }

        List<CommentApi> commentApiList = new ArrayList<>();
        Pageable pageable = PageRequest.of(offset / itemPerPage, itemPerPage);

        commentApiList.addAll(commentMapper.commentToCommentApi(commentRepository.findAllByPostId(postId, pageable).toList()));

        return commentApiList;
    }

    // Создание комментария к публикации ГОТОВ | Постманом ПРОВЕРЕННО
    public AbstractResponse createCommentsPublication (int postId, PostCommentApi postCommentApi) {

        Post post = postRepository.findById(postId).orElseThrow( () -> new EntityNotFoundException("Not found"));

        PostComment postComment = commentMapper.postCommentApiToPostComment(postCommentApi);
        postComment.setPost(post);
        postComment.setAuthor(post.getAuthor());

        commentRepository.save(postComment);

        return commentMapper.postCommentToCommentApi(postComment);
    }

    // Редактирование комментария к публикации ГОТОВО | Постманом ПРОВЕРЕННО
    public AbstractResponse editingCommentPublication(int postId, int commentId, PostCommentApi postCommentApi) {
        Post post = postRepository.findById(postId).orElseThrow( () -> new EntityNotFoundException("Not found"));
        PostComment postCommentBefore = commentRepository.findById(commentId).orElseThrow(() -> new EntityNotFoundException("Not Found"));
        PostComment postCommentAfter = commentMapper.postCommentApiToPostComment(postCommentApi);

        postCommentAfter.setPost(post);
        postCommentAfter.setId(postCommentBefore.getId());
        postCommentAfter.setAuthor(postCommentBefore.getAuthor());

        commentRepository.save(postCommentAfter);

        return commentMapper.postCommentToCommentApi(postCommentAfter);
    }

    // Удаление комментария к публикации ГОТОВО | Постманом ПРОВЕРЕННО
    public AbstractResponse deleteCommentPublication (int postId, int commentId) {
        Post post = postRepository.findById(postId).orElseThrow( () -> new EntityNotFoundException("Not Found"));
        PostComment comment = commentRepository.findById(commentId).orElseThrow( () -> new EntityNotFoundException("Not Found"));

        if (post.isBlocked()) {
            throw new EntityNotFoundException("Post is already blocked");
        }
        if (comment.isBlocked()) {
            throw new EntityNotFoundException("comment is already blocked");
        }

        comment.setDeleted(true);
        commentRepository.save(comment);

        return new ResponseApi("Comment deleted", commentMapper.postCommentToCommentApi(comment));
    }

    // Восстановление комментария ГОТОВО | Постманом ПРОВЕРЕННО
    public AbstractResponse recoverComment( int postId, int commentId) {
        Post post = postRepository.findById(postId).orElseThrow( () -> new EntityNotFoundException("Not Found"));
        PostComment comment = commentRepository.findById(commentId).orElseThrow( () -> new EntityNotFoundException("Not Found"));

        if (!post.isBlocked()) {
            throw new EntityNotFoundException("Post is already unlocked");
        }
        if (!comment.isBlocked()) {
            throw new EntityNotFoundException("comment is already unlocked");
        }

        comment.setDeleted(false);
        commentRepository.save(comment);

        return new ResponseApi("Comment recover", commentMapper.postCommentToCommentApi(comment));
    }

    // Подать жалобу на публикацию | не реалезованно на фронте
    public ResponseEntity reportPublication(int postId) {


        return new ResponseEntity(HttpStatus.OK);
    }

    // Подать жалобу на комментарий к публикации | не реалезованно на фронте
    public ResponseEntity reportComment(int postId, int commentId) {

        return new ResponseEntity(HttpStatus.OK);

    }

}
