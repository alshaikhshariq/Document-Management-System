package com.example.dms.service;

import com.example.dms.model.Comment;
import com.example.dms.model.Document;
import com.example.dms.model.Post;
import com.example.dms.repository.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class DocumentServiceImpl implements DocumentService {


    @Autowired
    DocumentRepository documentRepository;

    @Autowired
    ThirdPartyApiService thirdPartyApiService;


    @Override
    public Document createDocument(Document document) {
        document.setPost(thirdPartyApiService.createPost(document));
        return documentRepository.save(document);
    }

    @Override
    public Document findById(Long documentId) {
        return documentRepository.findById(documentId)
                .orElseThrow(() -> new RuntimeException(String.valueOf(documentId)));
    }

    @Override
    public List<Document> findAll() {
        return documentRepository.findAll();
    }

    @Override
    public void delete(Long documentId) {
        documentRepository.delete(findById(documentId));
    }

    @Override
    public List<Post> getPostsByDocument(Document document) {
        // Retrieve the associated post from the document
        Post post = document.getPost();

        if (post != null) {
            // Fetch the post from the third-party API
            return Collections.singletonList(thirdPartyApiService.getPost(post.getId()));
        } else {
            return Collections.emptyList();
        }
    }

    @Override
    public Comment createComment(Document document, Comment comment) {
        // Associate the comment with the document
        comment.setDocument(document);

        // Create the comment using the third-party API
        return thirdPartyApiService.createComment(comment);
    }
}
