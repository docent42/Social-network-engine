package com.skillbox.sw.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Integer id;

    @Column(length = 45)
    private String tag;

    @ManyToMany(mappedBy = "tags")
    private List<Post> posts;
}