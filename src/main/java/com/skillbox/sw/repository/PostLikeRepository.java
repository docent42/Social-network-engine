package com.skillbox.sw.repository;

import com.skillbox.sw.domain.PostLike;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostLikeRepository extends JpaRepository<PostLike, Integer> {
	List<PostLike> findAllByPostId(int postId);

	List<PostLike> findAllByPostIdAndPersonId(int postId, int userId);
}
