package com.learnjavawithkaushi.BookManagementSystem.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Publisher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required")
    @Size(max = 100, message = "Name must be less than 100 characters")
    private String name;

    @NotBlank(message = "Address is required")
    @Size(max = 200, message = "Address must be less than 200 characters")
    private String address;

    @Size(max = 150, message = "Contact Info must be less than 150 characters")
    private String contactInfo;

    @OneToMany(mappedBy = "publisher")
    private List<Book> books = new ArrayList<>();
}
