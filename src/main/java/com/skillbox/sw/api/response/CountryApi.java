package com.skillbox.sw.api.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CountryApi extends AbstractResponse {

    private int id;
    private String title;
}
