package com.learnjavawithkaushi.bookmanagementsystem.services;

import com.learnjavawithkaushi.BookManagementSystem.models.Publisher;
import com.learnjavawithkaushi.BookManagementSystem.repositories.PublisherRepository;
import com.learnjavawithkaushi.BookManagementSystem.services.PublisherService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PublisherServiceTest {

    @Mock
    private PublisherRepository publisherRepository;

    @InjectMocks
    private PublisherService publisherService;

    private Publisher samplePublisher;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        samplePublisher = new Publisher();
        samplePublisher.setId(1L);
        samplePublisher.setName("Test Publisher");
        samplePublisher.setAddress("123 Main St");
        samplePublisher.setContactInfo("test@publisher.com");
    }

    @Test
    void testFindAll() {
        List<Publisher> publishers = Arrays.asList(samplePublisher);
        when(publisherRepository.findAll()).thenReturn(publishers);

        List<Publisher> result = publisherService.findAll();
        assertEquals(1, result.size());
        assertEquals("Test Publisher", result.get(0).getName());
    }

    @Test
    void testSave() {
        when(publisherRepository.save(samplePublisher)).thenReturn(samplePublisher);

        Publisher saved = publisherService.save(samplePublisher);
        assertNotNull(saved);
        assertEquals("Test Publisher", saved.getName());
    }

    @Test
    void testFindById_WhenExists() {
        when(publisherRepository.findById(1L)).thenReturn(Optional.of(samplePublisher));

        Publisher found = publisherService.findById(1L);
        assertNotNull(found);
        assertEquals(1L, found.getId());
    }

    @Test
    void testFindById_WhenNotExists() {
        when(publisherRepository.findById(2L)).thenReturn(Optional.empty());

        Publisher found = publisherService.findById(2L);
        assertNull(found);
    }

    @Test
    void testDeleteById() {
        doNothing().when(publisherRepository).deleteById(1L);

        publisherService.deleteById(1L);
        verify(publisherRepository, times(1)).deleteById(1L);
    }

    @Test
    void testCount() {
        when(publisherRepository.count()).thenReturn(5L);

        long count = publisherService.count();
        assertEquals(5L, count);
    }

}