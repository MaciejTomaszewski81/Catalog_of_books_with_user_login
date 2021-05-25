package com.example.demo.books;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class BookController {

    private final CatalogService catalogService;

    public BookController(CatalogService bookCatalogService) {
        this.catalogService = bookCatalogService;
    }

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/books")
    public String books(BookFilters bookFilters, Model model) {
        List<Book> bookList;
        if (!(bookFilters.getTitle() == null && bookFilters.getName() == null)) {
            bookList = catalogService.findAllForFilters(bookFilters);
        } else {
            bookList = catalogService.findAllBook();
        }
        model.addAttribute("listAllBooks", bookList);
        model.addAttribute("filters", bookFilters);
        return "books";
    }

    @GetMapping("/{id}/book-details")
    public String bookDetail(@PathVariable("id") Long id, Model model) {
        Optional<Book> book = catalogService.findById(id);
        if (book.isPresent()) {
           Book books = book.get();

            model.addAttribute("bookDetails", books);
            return "bookdetails";
        } else
            return "redirect:/";
    }

    @GetMapping("/add-book")
    public String addBook(Model model) {
        model.addAttribute("author", catalogService.findAllAuthors());
        model.addAttribute("addBook", new Book());
        return "addbook";
    }

    @PostMapping("/add-book")
    public String addBook(Book book) {
        catalogService.addBook(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit-book")
    public String editBook(@PathVariable("id") Long id, Model model) {
        Optional<Book> byId = catalogService.findById(id);
        if (byId.isPresent()) {
            Book book = byId.get();
            List<Author> authors = catalogService.findAllAuthors();
            authors.removeAll(book.getAuthors());
            List<Author> bookAuthors = book.getAuthors();
            model.addAttribute("authorEdit", catalogService.findAllBook());
            model.addAttribute("author", authors);
            model.addAttribute("updateBook", book);
            model.addAttribute("model", "updateBook");
            model.addAttribute("bookauthors",bookAuthors);

            return "/editbook";
        } else {
            return "redirect:/";
        }
    }

    @GetMapping("/{id}/edit-book/delete")
    public String deleteBook(@PathVariable Long id) {
        catalogService.deleteBookById(id);
        return "redirect:/books";
    }

    @GetMapping("{id}/addauthor/delete")
    public String deleteAuthor(@PathVariable Long id) {
        catalogService.deleteAuthorById(id);
        return "redirect:/";
    }

    @GetMapping("/{bookId}/author-remove/{authorId}")
    public String removeAuthorFromBook(@PathVariable Long bookId, @PathVariable Long authorId) {
        catalogService.removeAuthorFromBook(bookId, authorId);
        return "redirect:/books";
    }

    @GetMapping("/{bookId}/author-add/{authorId}")
    public String addAuthorToBook(@PathVariable Long bookId, @PathVariable Long authorId) {
        catalogService.adAuthorToBook(bookId, authorId);
        return "redirect:/books";
    }

    @PostMapping("/{id}/edit-book")
    public String editBook(Book book) {
        catalogService.upgradeBook(book);
        return "redirect:/books";
    }

    @GetMapping("/add-author")
    public String addAuthor(Model model) {
        model.addAttribute("deleteAuthor", catalogService.findAllAuthors());
        model.addAttribute("author", new Author());
        return "addauthor";
    }

    @PostMapping("/add-author")
    public String addAuthor(Author author) {
        catalogService.addAuthor(author);
        return "redirect:/";
    }
}