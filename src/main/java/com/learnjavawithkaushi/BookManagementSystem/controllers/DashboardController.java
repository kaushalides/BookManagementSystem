        package com.learnjavawithkaushi.BookManagementSystem.controllers;

        import com.learnjavawithkaushi.BookManagementSystem.services.BookService;
        import com.learnjavawithkaushi.BookManagementSystem.services.PublisherService;
        import org.springframework.stereotype.Controller;
        import org.springframework.ui.Model;
        import org.springframework.web.bind.annotation.GetMapping;

        import java.util.ArrayList;
        import java.util.Map;

        @Controller
        public class DashboardController {

            private final BookService bookService;
            private final PublisherService publisherService;

            public DashboardController(BookService bookService, PublisherService publisherService) {
                this.bookService = bookService;
                this.publisherService = publisherService;

            }

            @GetMapping("/")
            public String showDashboard(Model model) {
                model.addAttribute("totalBooks", bookService.count());
                model.addAttribute("totalPublishers", publisherService.count());

                Map<String, Long> genreCounts = bookService.countBooksByGenre();
                Map<String, Long> publisherCounts = bookService.countBooksByPublisher();

                // Add labels and data separately
                model.addAttribute("genreLabels", new ArrayList<>(genreCounts.keySet()));
                model.addAttribute("genreData", new ArrayList<>(genreCounts.values()));

                model.addAttribute("publisherLabels", new ArrayList<>(publisherCounts.keySet()));
                model.addAttribute("publisherData", new ArrayList<>(publisherCounts.values()));

                model.addAttribute("totalGenres", genreCounts.size());


                return "dashboard";
            }
        }