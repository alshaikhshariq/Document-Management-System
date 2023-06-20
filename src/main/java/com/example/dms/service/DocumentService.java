package com.example.dms.service;

import com.example.dms.model.Comment;
import com.example.dms.model.Document;
import com.example.dms.model.Post;

import java.util.List;

public interface DocumentService {

    Document createDocument(Document document);

    Document findById(Long documentId);

    List<Document> findAll();

    void delete(Long documentId);

    List<Post> getPostsByDocument(Document document);

    Comment createComment(Document document, Comment comment);
}
