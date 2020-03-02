package com.skillbox.sw.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class PostFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Integer id;

    private String name;

    @Column(length = 2048)
    private String path;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;
}
