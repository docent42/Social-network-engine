package com.skillbox.sw.repository;


import com.skillbox.sw.domain.PostComment;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostCommentRepository extends JpaRepository<PostComment, Integer> {

  List<PostComment> findAllByPostId(int id);

  Page<PostComment> findAllByPostId(int id, Pageable pageable);

}
