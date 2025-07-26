package com.Books.BooksManagement.B;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class BookController {

    private BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        super();
        this.bookRepository = bookRepository;
    }

    @RequestMapping("/")
    public String welcomePage() {

        return "welcome";
    }

    @RequestMapping(value = "list-books")
    public String showAllBooks(ModelMap model) {
        List<Book> books = bookRepository.findAll();
        if (books == null) {
            books = new ArrayList<>();
        }
        model.addAttribute("books", books);
        return "listBooks";
    }

    @RequestMapping(value = "add-book", method = RequestMethod.GET)
    public String addBooks() {
        return "book";
    }

    @RequestMapping(value = "add-book", method = RequestMethod.POST)
    public String returnBook(@RequestParam String title, @RequestParam String author,
                             @RequestParam String description, @RequestParam String category,
                             ModelMap model) {
        Book b = new Book(title, author, description, category);
        bookRepository.save(b);
        return "redirect:list-books";
    }

    @RequestMapping("delete-book")
    public String deleteBook(@RequestParam int id) {
        bookRepository.deleteById(id);
        return "redirect:list-books";
    }

    @RequestMapping(value = "search-book", method = RequestMethod.POST)
    public String getBookByName(@RequestParam String title, ModelMap model) {
        List<Book> book = bookRepository.findByTitleContainingIgnoreCase(title);
        model.addAttribute("books", book);
        return "searchBook";
    }

    @RequestMapping(value = "edit-book", method = RequestMethod.GET)
    public String updateBook(@RequestParam int id, ModelMap model) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid book ID"));
        model.addAttribute("book", book);
        return "editBook";
    }

    @RequestMapping(value = "edit-book", method = RequestMethod.POST)
    public String updateBook(@RequestParam int id,
                             @RequestParam String title,
                             @RequestParam String author,
                             @RequestParam String description,
                             @RequestParam String category) {

        Book book = new Book(title, author, description, category);
        book.setId(id); // 🔥 Set the existing ID
        bookRepository.save(book); // This will update the existing book

        return "redirect:list-books";
    }
}
