-- schema.sql
DROP TABLE IF EXISTS book_genres;
DROP TABLE IF EXISTS book;
DROP TABLE IF EXISTS publisher;

CREATE TABLE publisher (
                           id BIGINT PRIMARY KEY AUTO_INCREMENT,
                           name VARCHAR(255),
                           address VARCHAR(255),
                           contact_info VARCHAR(255)
);

CREATE TABLE book (
                      id BIGINT PRIMARY KEY AUTO_INCREMENT,
                      title VARCHAR(255),
                      isbn VARCHAR(255),
                      publication_year INT,
                      publisher_id BIGINT,
                      FOREIGN KEY (publisher_id) REFERENCES publisher(id)
);

CREATE TABLE book_genres (
                             book_id BIGINT,
                             name VARCHAR(255),
                             description VARCHAR(255),
                             PRIMARY KEY (book_id, name),
                             FOREIGN KEY (book_id) REFERENCES book(id)
);
INSERT INTO publisher (name, address, contact_info) VALUES
                                                        ('Penguin Random House', '1745 Broadway, New York, NY 10019', 'info@penguinrandomhouse.com'),
                                                        ('HarperCollins', '195 Broadway, New York, NY 10007', 'contact@harpercollins.com'),
                                                        ('Simon & Schuster', '1230 Avenue of the Americas, New York, NY 10020', 'info@simonandschuster.com');

-- Insert sample books
INSERT INTO book (title, isbn, publication_year, publisher_id) VALUES
                                                                   ('The Great Gatsby', '9780743273565', 1925, 1),
                                                                   ('To Kill a Mockingbird', '9780061120084', 1960, 2),
                                                                   ('1984', '9780451524935', 1949, 1),
                                                                   ('The Catcher in the Rye', '9780316769488', 1951, 3),
                                                                   ('Pride and Prejudice', '9781503290563', 1813, 2);

-- Insert sample genres
INSERT INTO book_genres (book_id, name, description) VALUES
                                                         (1, 'Fiction', 'Literary fiction'),
                                                         (1, 'Classic', 'Classic literature'),
                                                         (2, 'Fiction', 'Literary fiction'),
                                                         (2, 'Classic', 'Classic literature'),
                                                         (3, 'Dystopian', 'Dystopian fiction'),
                                                         (3, 'Science Fiction', 'Speculative fiction'),
                                                         (4, 'Coming-of-age', 'Coming-of-age story'),
                                                         (5, 'Romance', 'Romantic novel'),
                                                         (5, 'Classic', 'Classic literature');