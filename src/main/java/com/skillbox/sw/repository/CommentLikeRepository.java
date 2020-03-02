package com.skillbox.sw.repository;

import com.skillbox.sw.domain.CommentLike;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentLikeRepository extends JpaRepository<CommentLike, Integer> {
	List<CommentLike> findAllByCommentId(int commentId);
	List<CommentLike> findAllByCommentIdAndPersonId(int commentId, int userId);
}
