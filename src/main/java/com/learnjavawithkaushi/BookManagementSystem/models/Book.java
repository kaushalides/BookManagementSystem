package com.learnjavawithkaushi.BookManagementSystem.models;

import jakarta.persistence.*;
import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "ISBN is required")
    private String isbn;

    private Integer publicationYear;

    @ManyToOne
    private Publisher publisher;

    @ElementCollection
    @CollectionTable(
            name = "book_genres",
            joinColumns = @JoinColumn(name = "book_id")
    )
    @AttributeOverrides({
            @AttributeOverride(name = "name", column = @Column(name = "name")),
            @AttributeOverride(name = "description", column = @Column(name = "description"))
    })
    private List<Genre> genres = new ArrayList<>();
}
