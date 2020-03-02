package com.skillbox.sw.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DialogListApi extends ResponseApi {

    @JsonProperty("data")
    private List<DialogApi> dialogList;
    private long total;
    private int offset;
    private int perPage;

    public DialogListApi(String error, List<DialogApi> dialogList, int total, int offset,
                       int perPage) {
        super(error);
        this.dialogList = dialogList;
        this.total = total;
        this.offset = offset;
        this.perPage = perPage;
    }
}