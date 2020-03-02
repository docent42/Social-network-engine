package com.skillbox.sw.domain;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private NotificationType notificationType;

    @Column(columnDefinition = "TIMESTAMP")
    private LocalDate sentTime;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;

    private boolean enable;

    private Integer entityId;

    private String contact;
}
