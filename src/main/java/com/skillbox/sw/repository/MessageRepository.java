package com.skillbox.sw.repository;

import com.skillbox.sw.domain.Dialog;
import com.skillbox.sw.domain.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MessageRepository extends JpaRepository<Message,Integer> {

    Optional<Message> findById(int id);

    Page<Message> findAllByDialogAndMessageTextContaining(Dialog dialog, String query, Pageable pageable);

}
