package com.example.demo.books;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface AuthorRepository extends JpaRepository<Author, Long> {

    @Transactional
    void deleteByBooksId(Long id);
}