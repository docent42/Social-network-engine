package com.skillbox.sw.domain;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
public class PostComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Integer id;

    @Column(columnDefinition = "TIMESTAMP")
    private LocalDate time;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private PostComment parent;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Person author;

    @Column(length = 2048)
    private String commentText;

    private boolean isBlocked;

    boolean isDeleted; // - отметка о том что комментарий удален (ESV)

    @OneToMany(mappedBy = "entityId", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Notification> entity;
}
