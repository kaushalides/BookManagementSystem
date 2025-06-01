package com.learnjavawithkaushi.BookManagementSystem.controllers;

import com.learnjavawithkaushi.BookManagementSystem.models.Book;
import com.learnjavawithkaushi.BookManagementSystem.models.Genre;
import com.learnjavawithkaushi.BookManagementSystem.models.Publisher;
import com.learnjavawithkaushi.BookManagementSystem.services.BookService;
import com.learnjavawithkaushi.BookManagementSystem.services.PublisherService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/books")
public class BookController {

    private static final int DEFAULT_PAGE_SIZE = 10;

    private final BookService bookService;
    private final PublisherService publisherService;

    public BookController(BookService bookService, PublisherService publisherService) {
        this.bookService = bookService;
        this.publisherService = publisherService;
    }

    // List books with pagination and sorting
    @GetMapping
    public String listBooks(
            Model model,
            @RequestParam("page") Optional<Integer> page,
            @RequestParam("size") Optional<Integer> size,
            @RequestParam("search") Optional<String> search,
            @RequestParam("sort") Optional<String> sort,
            @RequestParam("direction") Optional<String> direction) {

        int currentPage = page.orElse(1);
        int pageSize = size.orElse(DEFAULT_PAGE_SIZE);
        String searchTerm = search.orElse("");
        String sortField = sort.orElse("title");
        Sort.Direction sortDirection = direction.map(Sort.Direction::fromString)
                .orElse(Sort.Direction.ASC);

        Pageable pageable = PageRequest.of(currentPage - 1, pageSize, Sort.by(sortDirection, sortField));

        Page<Book> bookPage = searchTerm.isEmpty() ?
                bookService.findAll(pageable) :
                bookService.searchBooks(searchTerm, pageable);

        model.addAttribute("books", bookPage);
        model.addAttribute("searchTerm", searchTerm);
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDirection", sortDirection);
        model.addAttribute("reverseSortDirection", sortDirection == Sort.Direction.ASC ? "desc" : "asc");

        int totalPages = bookPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        return "books/list";
    }

    // Show form for creating a new book
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        Book book = new Book();
        book.setPublisher(new Publisher());
        book.getGenres().add(new Genre());
        model.addAttribute("book", book);
        model.addAttribute("publishers", publisherService.findAll());
        model.addAttribute("formAction", "/books/save");
        return "books/form";
    }

    // Show form for editing an existing book
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Book book = bookService.findById(id);
        if (book == null) {
            return "redirect:/books";
        }
        if (book.getPublisher() == null) {
            book.setPublisher(new Publisher());
        }
        if (book.getGenres().isEmpty()) {
            book.getGenres().add(new Genre());
        }
        model.addAttribute("book", book);
        model.addAttribute("publishers", publisherService.findAll());
        model.addAttribute("formAction", "/books/save");
        return "books/form";
    }

    // Save new or edited book
    @PostMapping("/save")
    public String saveBook(@Valid @ModelAttribute("book") Book book, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("publishers", publisherService.findAll());
            return "books/form";
        }
        book.getGenres().removeIf(g -> g.getName() == null || g.getName().trim().isEmpty());
        bookService.save(book);
        return "redirect:/books?success";
    }


    // Delete book by id
    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            bookService.deleteById(id);
            redirectAttributes.addFlashAttribute("successMessage", "Book deleted successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error deleting book: " + e.getMessage());
        }
        return "redirect:/books";
    }
}
