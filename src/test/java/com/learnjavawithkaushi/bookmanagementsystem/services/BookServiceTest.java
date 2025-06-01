package com.learnjavawithkaushi.bookmanagementsystem.services;

import com.learnjavawithkaushi.BookManagementSystem.models.Book;
import com.learnjavawithkaushi.BookManagementSystem.repositories.BookRepository;
import com.learnjavawithkaushi.BookManagementSystem.services.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BookServiceTest {

    private BookRepository bookRepository;
    private BookService bookService;

    @BeforeEach
    void setup() {
        bookRepository = Mockito.mock(BookRepository.class);
        bookService = new BookService(bookRepository);
    }

    @Test
    void testCreateBook() {
        Book book = new Book();
        book.setTitle("Test Book");

        when(bookRepository.save(book)).thenReturn(book);

        Book result = bookService.save(book);
        assertEquals("Test Book", result.getTitle());
        verify(bookRepository, times(1)).save(book);
    }

    @Test
    void testFindBookById() {
        Book book = new Book();
        book.setId(1L);
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));

        Optional<Book> result = Optional.ofNullable(bookService.findById(1L));
        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getId());
    }
}