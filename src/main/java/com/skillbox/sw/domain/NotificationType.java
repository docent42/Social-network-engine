package com.skillbox.sw.domain;

import com.skillbox.sw.domain.enums.NotificationName;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class NotificationType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer code;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "enum('POST','POST_COMMENT', 'COMMENT_COMMENT', 'FRIEND_REQUEST', 'MESSAGE')")
    private NotificationName name;
}
