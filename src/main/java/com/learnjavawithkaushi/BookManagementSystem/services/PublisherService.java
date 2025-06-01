package com.learnjavawithkaushi.BookManagementSystem.services;

import com.learnjavawithkaushi.BookManagementSystem.models.Publisher;
import com.learnjavawithkaushi.BookManagementSystem.repositories.PublisherRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublisherService {
    private final PublisherRepository publisherRepository;

    public PublisherService(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }

    public List<Publisher> findAll() {
        return publisherRepository.findAll();
    }

    public Publisher save(Publisher publisher) {
        return publisherRepository.save(publisher);
    }

    public Publisher findById(Long id) {
        return publisherRepository.findById(id).orElse(null);
    }

    public void deleteById(Long id) {
        publisherRepository.deleteById(id);
    }

    public long count() {
        return publisherRepository.count();
    }
}