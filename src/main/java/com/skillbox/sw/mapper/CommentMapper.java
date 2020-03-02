package com.skillbox.sw.mapper;

import com.skillbox.sw.api.request.PostCommentApi;
import com.skillbox.sw.api.response.CommentApi;
import com.skillbox.sw.domain.PostComment;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(
    uses = {
        DateMapper.class,
        PersonMapper.class
    }
)

public interface CommentMapper extends BaseMapper {

  @Mapping(target = "parentId", source = "parent.id")
  @Mapping(target = "postId", source = "post.id")
  @Mapping(target = "authorId", source = "author.id")
  CommentApi postCommentToCommentApi(PostComment postComment);

  @InheritInverseConfiguration
  PostComment postCommentApiToPostComment(PostCommentApi postCommentApi);

  List<CommentApi> commentToCommentApi(List<PostComment> posts);
}