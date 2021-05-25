package com.example.demo.books;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookCatalogRepository extends JpaRepository<Book, Long> {

    List<Book> findByTitleContainingIgnoreCase(String title);

//     Część do przemyślenia i ew. rozwoju

//      @Query("SELECT b FROM Book b INNER JOIN Book_Authors ba ON books_id = b.id INNER JOIN " +
//     "Author a ON a.id = authors_id")
//     List<Book> findByFilters(String title, String name);
//    @Query("SELECT b.title, a.name FROM Book b JOIN b.authors p")
//    @Query(" SELECT DISTINCT b FROM Book b JOIN b.authors a WHERE a.name = :name")
//    List<Book> findAllBooksByAuthorName(@Param("name") String authorName);
//    @Query("SELECT b, a FROM Book b, Author a WHERE b.id = a.id")
//    @Query(value = "SELECT LOWER(Book.title), LOWER(Author.name) FROM Book  INNER JOIN Author ON Book.id=Author.id ")
}