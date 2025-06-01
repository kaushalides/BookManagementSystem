package com.learnjavawithkaushi.BookManagementSystem.repositories;

import com.learnjavawithkaushi.BookManagementSystem.models.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublisherRepository extends JpaRepository<Publisher, Long> {
}