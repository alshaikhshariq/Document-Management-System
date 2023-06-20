package com.example.dms.service;

import com.example.dms.constants.AppConstants;
import com.example.dms.model.Comment;
import com.example.dms.model.Document;
import com.example.dms.model.Post;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ThirdPartyApiService {


    /**
     * Create posts call
     *
     * @return
     */
    public Post createPost() {

        RestTemplate restTemplate = new RestTemplate();
        // Make the POST request to create the post
        ResponseEntity<Post> response = restTemplate.postForEntity(
                AppConstants.BASE_API + AppConstants.POSTS,
                new Post(), // Pass the necessary data
                Post.class
        );

        if (response.getStatusCode() == HttpStatus.CREATED) {
            return response.getBody();
        } else {
            // Handle the error case
            throw new RuntimeException("Failed to create post");
        }
    }

    /**
     * @param postId
     * @return
     */
    public Post getPost(Long postId) {
        RestTemplate restTemplate = new RestTemplate();

        // Make the GET request to retrieve the post
        ResponseEntity<Post> response = restTemplate.getForEntity(
                AppConstants.BASE_API + AppConstants.POSTS + "/{postId}",
                Post.class,
                postId);

        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        } else {
            // Handle the error case
            throw new RuntimeException("Failed to retrieve post");
        }
    }

    /**
     * @param comment
     * @return
     */
    public Comment createComment(Comment comment) {
        RestTemplate restTemplate = new RestTemplate();

        // Make the POST request to create the comment
        ResponseEntity<Comment> response = restTemplate.postForEntity(
                AppConstants.BASE_API + AppConstants.COMMENTS,
                comment,
                Comment.class
        );

        if (response.getStatusCode() == HttpStatus.CREATED) {
            return response.getBody();
        } else {
            // Handle the error case
            throw new RuntimeException("Failed to create comment");
        }
    }
}
