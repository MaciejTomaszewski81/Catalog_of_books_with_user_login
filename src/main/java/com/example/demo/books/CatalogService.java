package com.example.demo.books;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CatalogService {

    private final BookCatalogRepository bookCatalogRepository;
    private final AuthorRepository authorRepository;

    public CatalogService(BookCatalogRepository bookCatalogRepository, AuthorRepository authorRepository) {
        this.bookCatalogRepository = bookCatalogRepository;
        this.authorRepository = authorRepository;
    }

    public List<Book> findAllBook() {
        return bookCatalogRepository.findAll();
    }

    public Book addBook(Book book) {
        return bookCatalogRepository.save(book);
    }

    public Author addAuthor(Author author) {
        return authorRepository.save(author);
    }

    public void upgradeBook(Book book) {
        bookCatalogRepository.save(book);
    }

    public Optional<Book> findById(Long id) {
        return bookCatalogRepository.findById(id);
    }

    public List<Book> findAllForFilters(BookFilters bookFilters) {
        return bookCatalogRepository.findByTitleContainingIgnoreCase(
                bookFilters.getTitle());
    }

    public List<Author> findAllAuthors() {
        return authorRepository.findAll();
    }

    public void deleteBookById(Long id) {
        bookCatalogRepository.deleteById(id);
    }

    public void deleteAuthorById(Long id) {
        authorRepository.deleteById(id);
    }

    public void removeAuthorFromBook(Long bookId, Long authorId) {
        Optional<Author> author = authorRepository.findById(authorId);
        Optional<Book> book = bookCatalogRepository.findById(bookId);

        if (author.isPresent() && book.isPresent()) {
            Book editBook = book.get();
            editBook.getAuthors().remove(author.get());
            bookCatalogRepository.save(editBook);
        } else {
            System.err.println("Nie udało się usunąć autora");
        }
    }

    public void adAuthorToBook(Long bookId, Long authorId) {
        Optional<Author> author = authorRepository.findById(authorId);
        Optional<Book> book = bookCatalogRepository.findById(bookId);

        if (author.isPresent() && book.isPresent()) {
           Book addAuthorToBook = book.get();
            addAuthorToBook.addAuthor(author.get());
            bookCatalogRepository.save(addAuthorToBook);
        } else {
            System.err.println("Nie udało się dodać autora");
        }
    }
}