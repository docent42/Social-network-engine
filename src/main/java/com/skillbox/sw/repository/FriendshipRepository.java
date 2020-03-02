package com.skillbox.sw.repository;

import com.skillbox.sw.domain.Friendship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface FriendshipRepository extends JpaRepository<Friendship, Integer>, JpaSpecificationExecutor<Friendship> {
}
