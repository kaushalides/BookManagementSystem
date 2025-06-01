package com.learnjavawithkaushi.BookManagementSystem.repositories;

import com.learnjavawithkaushi.BookManagementSystem.models.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
    Page<Book> findByTitleContainingIgnoreCaseOrIsbnContainingIgnoreCase(
            String title, String isbn, Pageable pageable);
}