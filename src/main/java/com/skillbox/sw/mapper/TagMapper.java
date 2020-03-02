package com.skillbox.sw.mapper;

import com.skillbox.sw.api.request.RequestTagApi;
import com.skillbox.sw.api.response.TagApi;
import com.skillbox.sw.domain.Tag;
import org.mapstruct.Mapper;

@Mapper
public interface TagMapper {
    Tag requestTagApiToTag(RequestTagApi requestTagApi);

    TagApi tagToTagApi(Tag tag);
}
