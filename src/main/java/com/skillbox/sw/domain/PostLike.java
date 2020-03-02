package com.skillbox.sw.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDate;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class PostLike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Integer id;

    @Column(columnDefinition = "TIMESTAMP", updatable = false)
    @CreationTimestamp
    private LocalDate time;

    @ManyToOne
    @JoinColumn(name = "post_id")
    @NonNull
    private Post post;

    @ManyToOne
    @JoinColumn(name = "person_id")
    @NonNull
    private Person person;
}
