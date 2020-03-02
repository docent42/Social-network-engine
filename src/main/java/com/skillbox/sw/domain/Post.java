package com.skillbox.sw.domain;

import java.time.LocalDate;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Integer id;

    @CreationTimestamp
    @Column(columnDefinition = "TIMESTAMP")
    private LocalDate time;//  - дата и время публикации

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Person author;

    private String title;

    @Column(length = 4096)
    private String postText; // - HTML-текст поста

    private boolean isBlocked; // - отметка о том, что пост заблокирован

    boolean isDeleted;// - отметка о том что пост удален

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
        name = "post2tag",
        joinColumns = {@JoinColumn(name = "post_id")},
        inverseJoinColumns = {@JoinColumn(name = "tag_id")}
    )
    private List<Tag> tags;

    @OneToMany(mappedBy = "entityId", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Notification> entity;

    @OneToMany(mappedBy = "post")
    private List<PostComment> comments;
}
