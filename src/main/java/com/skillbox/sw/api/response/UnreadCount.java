package com.skillbox.sw.api.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UnreadCount extends AbstractResponse{

    private int count;
}
