package com.skillbox.sw.mapper;

import com.skillbox.sw.api.request.RequestPostApi;
import com.skillbox.sw.api.response.ResponsePostApi;
import com.skillbox.sw.domain.Post;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(
    uses = {
        DateMapper.class,
        PersonMapper.class,
        CommentMapper.class
    }
)
public interface PostMapper {

  ResponsePostApi postToResponsePostApi(Post post);

  List<ResponsePostApi> postToResponsePostApi(List<Post> posts);

  Post requestPostApiToPost(RequestPostApi requestPostApi);
}