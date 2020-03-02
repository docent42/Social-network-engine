package com.skillbox.sw.repository;

import com.skillbox.sw.domain.Post;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Integer> {
  List<Post> findAllByAuthorId(int id);

  Page<Post> findAllByAuthorId(int id, Pageable pageable);

  Page<Post> findAllByPostTextContainingAndTimeBetween(String text, LocalDate dateFrom, LocalDate dateTo, Pageable pageable);
}