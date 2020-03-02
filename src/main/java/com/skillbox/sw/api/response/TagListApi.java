package com.skillbox.sw.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class TagListApi extends ResponseApi<TagApi> {

    @JsonProperty("data")
    private List<TagApi> tagList;
    private int total;
    private int offset;
    private int perPage;

    public TagListApi(List<TagApi> tagList, int total, int offset, int perPage) {
        super("string");
        this.tagList = tagList;
        this.total = total;
        this.offset = offset;
        this.perPage = perPage;
    }
}
