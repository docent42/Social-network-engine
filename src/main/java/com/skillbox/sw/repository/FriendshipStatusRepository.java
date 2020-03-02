package com.skillbox.sw.repository;

import com.skillbox.sw.domain.FriendshipStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FriendshipStatusRepository extends JpaRepository<FriendshipStatus, Integer> {
}
