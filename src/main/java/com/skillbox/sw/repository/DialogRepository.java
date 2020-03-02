package com.skillbox.sw.repository;

import com.skillbox.sw.domain.Dialog;
import com.skillbox.sw.domain.Person;
import com.skillbox.sw.domain.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DialogRepository extends JpaRepository<Dialog, Integer> {

    Optional<Dialog> findById(int id);

    @Query(nativeQuery = true,
            value = "SELECT * FROM dialog d where (select count(*) from message m2 " +
                    "where m2.dialog_id = d.id and locate(:query, m2.message_text) > 0 ) > 0 " +
                    "and owner_id = :id")
    Page<Dialog> getAllDialogs(@Param("id")Integer id,
                               @Param("query")String query,
                               Pageable pageable);
}