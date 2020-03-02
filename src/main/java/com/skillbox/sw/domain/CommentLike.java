package com.skillbox.sw.domain;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import lombok.NonNull;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Data
public class CommentLike {

  @Id
  private Integer id;

  @Column(columnDefinition = "TIMESTAMP", updatable = false)
  @CreationTimestamp
  LocalDate time;

  @ManyToOne
  @JoinColumn(name = "comment_id")
  @NonNull
  private PostComment comment;

  @ManyToOne
  @JoinColumn(name = "person_id")
  @NonNull
  private Person person;
}
