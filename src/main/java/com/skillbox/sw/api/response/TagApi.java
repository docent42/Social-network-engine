package com.skillbox.sw.api.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TagApi extends AbstractResponse {

    private int id;
    private String tag;
}
