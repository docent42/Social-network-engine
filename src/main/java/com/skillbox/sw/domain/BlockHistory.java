package com.skillbox.sw.domain;

import com.skillbox.sw.domain.enums.Action;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
public class BlockHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "TIMESTAMP")
    private LocalDate time;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;
    @ManyToOne
    @JoinColumn(name = "comment_id")
    private PostComment comment;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "enum('BLOCK','UNBLOCK')")
    private Action action;
}
