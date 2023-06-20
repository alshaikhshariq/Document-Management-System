package com.example.dms.controller;

import com.example.dms.model.Comment;
import com.example.dms.model.Document;
import com.example.dms.model.Post;
import com.example.dms.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import static com.example.dms.constants.AppConstants.MIME_TYPE_PDF;


@RestController
@RequestMapping("/documents")
public class DocumentController {

    @Autowired
    private DocumentService documentService;

    /**
     * Create Document
     *
     * @param file
     * @param name
     * @return
     */
    @PostMapping
    public ResponseEntity<Document> createDocument(@RequestParam("file") MultipartFile file,
                                                   @RequestParam("name") String name) {
        // Validate file format (PDF)
        if (!"application/pdf".equals(file.getContentType())) {
            return ResponseEntity.badRequest().build();
        }

        try {
            byte[] fileBytes = file.getBytes();
            Document document = new Document();
            document.setName(file.getOriginalFilename());
            document.setFile(fileBytes);
            documentService.createDocument(document);
            return ResponseEntity.ok(document);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Get All Documents
     *
     * @return
     */
    @GetMapping
    public ResponseEntity<List<Document>> getAllDocuments() {
        List<Document> documents = documentService.findAll();
        return ResponseEntity.ok(documents);
    }

    /**
     * Download document
     *
     * @param documentId
     * @return
     * @throws Exception
     */
    @GetMapping("/{documentId}")
    public ResponseEntity<Resource> downloadDocument(@PathVariable Long documentId) {
        try {
            Document document = documentService.findById(documentId);
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(MIME_TYPE_PDF))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + document.getName() + "\"")
                    .body(new ByteArrayResource(document.getFile()));
        } catch (Exception e) {
            throw new RuntimeException("Error downloading file");
        }
    }

    /**
     * Remove Document
     *
     * @param documentId
     * @return
     */
    @DeleteMapping("/{documentId}")
    public ResponseEntity<Void> removeDocument(@PathVariable Long documentId) {
        documentService.delete(documentId);
        return ResponseEntity.noContent().build();
    }

    /**
     * Get All posts by document
     *
     * @param documentId
     * @return
     */
    @GetMapping("/{documentId}/posts")
    public ResponseEntity<List<Post>> getPostsByDocument(@PathVariable Long documentId) {
        Document document = documentService.findById(documentId);

        if (document != null) {
            List<Post> posts = documentService.getPostsByDocument(document);
            return ResponseEntity.ok(posts);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    /**
     * Create comments against the document
     *
     * @param documentId
     * @param comment
     * @return
     */
    @PostMapping("/{documentId}/comments")
    public ResponseEntity<Comment> createComment(@PathVariable Long documentId, @RequestBody Comment comment) {
        Document document = documentService.findById(documentId);

        if (document != null) {
            Comment createdComment = documentService.createComment(document, comment);
            return ResponseEntity.ok(createdComment);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
