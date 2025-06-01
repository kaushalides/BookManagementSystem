package com.learnjavawithkaushi.BookManagementSystem.controllers;

import com.learnjavawithkaushi.BookManagementSystem.models.Publisher;
import com.learnjavawithkaushi.BookManagementSystem.services.PublisherService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/publishers")
public class PublisherController {

    private final PublisherService publisherService;

    public PublisherController(PublisherService publisherService) {
        this.publisherService = publisherService;
    }

    @GetMapping
    public String listPublishers(Model model) {
        model.addAttribute("publishers", publisherService.findAll());
        return "publishers/list";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("publisher", new Publisher());
        return "publishers/form";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Publisher publisher = publisherService.findById(id);
        if (publisher == null) {
            return "redirect:/publishers?notfound";
        }
        model.addAttribute("publisher", publisher);
        return "publishers/form";
    }

    @PostMapping("/save")
    public String savePublisher(@Valid @ModelAttribute("publisher") Publisher publisher,
                                BindingResult result) {
        if (result.hasErrors()) {
            return "publishers/form";
        }
        publisherService.save(publisher);
        return "redirect:/publishers?success";
    }

    @GetMapping("/delete/{id}")
    public String deletePublisher(@PathVariable Long id) {
        publisherService.deleteById(id);
        return "redirect:/publishers?deleted";
    }
}
