package com.skillbox.sw.repository;

import com.skillbox.sw.domain.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedRepository extends CrudRepository<Post,Integer> {
    @Query(nativeQuery = true,
        value = "SELECT * FROM Post post WHERE post.author_id IN (SELECT p.id FROM person p" +
                " JOIN friendship f ON p.id = f.dst_person_id JOIN friendship_status fs " +
                "ON f.status_id = fs.id WHERE f.src_person_id = :id AND fs.code = 'FRIEND')" +
                " AND post.title like concat('%', :name, '%') ORDER BY post.time DESC ")
    Page<Post> getAllPosts(@Param("id")Integer id,
                           @Param("name")String name,
                           Pageable pageable);
}
