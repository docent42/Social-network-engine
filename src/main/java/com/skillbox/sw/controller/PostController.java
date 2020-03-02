package com.skillbox.sw.controller;

import com.skillbox.sw.api.request.PostCommentApi;
import com.skillbox.sw.api.request.RequestPostApi;
import com.skillbox.sw.api.response.*;
import org.springframework.http.HttpStatus;
import com.skillbox.sw.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/post")
public class PostController {

    private PostService service;

    public PostController(PostService service) {
        this.service = service;
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK) // поиск пубоикации
    public AbstractResponse searchPublication(@RequestParam String text,
                                              @RequestParam(value = "date_from", required = false) Long dateFrom,
                                              @RequestParam(value = "date_to", required = false) Long dateTo,
                                              @RequestParam(value = "offset", required = false) Integer offset,
                                              @RequestParam(value = "itemPerPage", required = false, defaultValue = "20") Integer itemPerPage) {

        List<ResponsePostApi> responsePostApiList = service.searchPublication(text, dateFrom, dateTo, offset, itemPerPage);
        int total = responsePostApiList.size();

        return new PostListApi("Search completed successfully.", responsePostApiList, total, offset, itemPerPage);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK) // получение публикации по id | Постманом ПРОВЕРЕННО
    public AbstractResponse receivingPublication(@PathVariable int id) {

        return new ResponseApi("Post found successfully.", service.receivingPublication(id));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)  // Редактирование публикации по ID | Постманом ПРОВЕРЕННО
    public AbstractResponse editingPublication(@PathVariable int id,
                                               @RequestParam(value = "publish_date", required = false) Long  publishDate,
                                               @RequestBody RequestPostApi requestPostApi) {

        return new ResponseApi("Successful editing of the publication.", service.editingPublication(id, publishDate, requestPostApi));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)  // Удаление текущего пользователя по ID | Постманом ПРОВЕРЕННО
    public AbstractResponse deletePublication(@PathVariable int id) {

        return new ResponseApi("Your post has been successfully deleted.",
                service.deletePublication(id));
    }

    @PutMapping("/{id}/recover")
    @ResponseStatus(HttpStatus.OK)  // Восстановление публикации по ID | Постманом ПРОВЕРЕННО
    public AbstractResponse recoveryPublication(@PathVariable int id) {

        return new ResponseApi("Post restored.", service.recoveryPublication(id));
    }

    @GetMapping("/{id}/comments")
    @ResponseStatus(HttpStatus.OK)  // Получение комментариев на публикации | Постманом ПРОВЕРЕННО
    public AbstractResponse gettingCommentsPublication(@PathVariable int id,
                                                       @RequestParam(value = "offset", required = false) Integer offset,
                                                       @RequestParam(value = "itemPerPage", required = false, defaultValue = "20") int itemPerPage) {

        List<CommentApi> commentApiList = service.gettingCommentsPublication(id, offset, itemPerPage);
        int total = commentApiList.size();

        return new CommentListApi(commentApiList, total, offset, itemPerPage);
    }

    @PostMapping("/{id}/comments")
    @ResponseStatus(HttpStatus.OK)  // Создание комментария к публикации | Постманом ПРОВЕРЕННО
    public AbstractResponse createCommentsPublication(@PathVariable int id, @RequestBody PostCommentApi postCommentApi) {

        return new ResponseApi("A comment to your post has been created.", service.createCommentsPublication(id, postCommentApi));
    }

    @PutMapping("/{id}/comments/{commentId}")
    @ResponseStatus(HttpStatus.OK)  // Редактирование комментария к публикации | Постманом ПРОВЕРЕННО
    public AbstractResponse editingCommentPublication(@PathVariable int id, @PathVariable int commentId, @RequestBody PostCommentApi postCommentApi) {

        return new ResponseApi("Comment edited.", service.editingCommentPublication(id, commentId, postCommentApi));
    }

    @DeleteMapping("/{id}/comments/{commentId}")
    @ResponseStatus(HttpStatus.OK)  // Удаление комментария к публикации | Постманом ПРОВЕРЕННО
    public AbstractResponse deleteCommentPublication(@PathVariable int id, @PathVariable int commentId) {

       return new ResponseApi("Comment has been deleted.", service.deleteCommentPublication(id, commentId));
    }

    @PutMapping("/{id}/comments/{commentId}/recover")
    @ResponseStatus(HttpStatus.OK)  // Восстановление комментария | Постманом ПРОВЕРЕННО
    public AbstractResponse recoverComment(@PathVariable int id, @PathVariable int commentId) {

        return new ResponseApi(" ", service.recoverComment(id, commentId));
    }

    @PostMapping("/{id}/report")
    @ResponseStatus(HttpStatus.OK)  // Подать жалобу на публикацию | не реалезованно на фронте
    public ResponseEntity reportPublication(@PathVariable int id) {

//        return new ResponseApi("The successful establishment of the complaint to the publication.", service.reportPublication(id));
        return service.reportPublication(id);
    }

    @PostMapping("/{id}/comments/{commentId}/report")
    @ResponseStatus(HttpStatus.OK)  // Подать жалобу на комментарий к публикации | не реалезованно на фронте
    public ResponseEntity reportComment(@PathVariable int id, @PathVariable int commentId) {

//        return new ResponseApi("The successful establishment of complaints review.", service.reportComment(id, commentId));
        return service.reportComment(id, commentId);
    }

}
