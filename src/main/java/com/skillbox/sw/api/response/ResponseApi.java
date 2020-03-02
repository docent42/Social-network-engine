package com.skillbox.sw.api.response;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Use ResponseApi.Message to send object with a single `message` field
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseApi<T extends AbstractResponse> extends AbstractResponse {

    private String error;
    private long timestamp;
    private T data;

    public ResponseApi(String error, T data) {
        this.error = error;
        this.timestamp = LocalDateTime.now().toEpochSecond(OffsetDateTime.now().getOffset()) * 1000;
        this.data = data;
    }

    public ResponseApi(String error) {
        this.error = error;
        this.timestamp = LocalDateTime.now().toEpochSecond(OffsetDateTime.now().getOffset()) * 1000;
    }


    @Data
    @AllArgsConstructor
    public static class Message extends AbstractResponse {
        private String message;
    }
}
