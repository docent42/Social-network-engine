package com.skillbox.sw.api.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PostDeleteApi extends AbstractResponse {

    private int id;
}
