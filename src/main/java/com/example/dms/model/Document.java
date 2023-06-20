package com.example.dms.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "document")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Lob
    @Column(nullable = false)
    private byte[] file;
    // Other fields

    @OneToOne(mappedBy = "document")
    private Post post;
}
