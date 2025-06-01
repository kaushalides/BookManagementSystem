package com.learnjavawithkaushi.BookManagementSystem.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Embeddable
@NoArgsConstructor
public class Genre {

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    // Add setters for binding
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // equals and hashCode for proper behavior in collections
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Genre)) return false;
        Genre genre = (Genre) o;
        return Objects.equals(name, genre.name) &&
                Objects.equals(description, genre.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description);
    }

    @Override
    public String toString() {
        return "Genre{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
