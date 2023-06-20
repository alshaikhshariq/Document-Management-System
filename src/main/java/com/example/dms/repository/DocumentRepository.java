package com.example.dms.repository;

import com.example.dms.model.Document;
import com.example.dms.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DocumentRepository extends JpaRepository<Document, Long> {

    Optional<Document> findByPost(Post post);

}
