package com.learnjavawithkaushi.BookManagementSystem.services;

import com.learnjavawithkaushi.BookManagementSystem.models.Book;
import com.learnjavawithkaushi.BookManagementSystem.models.Genre;
import com.learnjavawithkaushi.BookManagementSystem.repositories.BookRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Map<String, Long> countBooksByGenre() {
        List<Book> books = bookRepository.findAll();
        Map<String, Long> genreCount = new HashMap<>();

        for (Book book : books) {
            for (Genre genre : book.getGenres()) {
                genreCount.put(genre.getName(),
                        genreCount.getOrDefault(genre.getName(), 0L) + 1);
            }
        }

        return genreCount;
    }

    public Map<String, Long> countBooksByPublisher() {
        List<Book> books = bookRepository.findAll();
        Map<String, Long> publisherCount = new HashMap<>();

        for (Book book : books) {
            if (book.getPublisher() != null) {
                String name = book.getPublisher().getName();
                publisherCount.put(name, publisherCount.getOrDefault(name, 0L) + 1);
            }
        }

        return publisherCount;
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public Page<Book> findAll(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }

    public Page<Book> searchBooks(String searchTerm, Pageable pageable) {
        return bookRepository.findByTitleContainingIgnoreCaseOrIsbnContainingIgnoreCase(
                searchTerm, searchTerm, pageable);
    }

    public Book save(Book book) {
        return bookRepository.save(book);
    }

    public Book findById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }

    public long count() {
        return bookRepository.count();
    }
}
