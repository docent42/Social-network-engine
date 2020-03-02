package com.skillbox.sw.api.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ListResponseApi extends ResponseApi {

    private long total;
    private long offset;
    private long perPage;

    public ListResponseApi(String error, long timestamp, AbstractResponse data, long total, long offset, long perPage) {
        super(error, timestamp, data);
        this.total = total;
        this.offset = offset;
        this.perPage = perPage;
    }
}
