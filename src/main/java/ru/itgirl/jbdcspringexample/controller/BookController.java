package ru.itgirl.jbdcspringexample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.itgirl.jbdcspringexample.model.Books;
import ru.itgirl.jbdcspringexample.repository.BookRepository;

import java.util.List;

@RestController
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping("/book/all")
    List<Books> getAllBooks(){
        return bookRepository.findAllBooks();
    }
    @GetMapping("/book/{id}")
    String getBookById(@PathVariable Long id){
        Books book = bookRepository.getBookById(id);
        if(book.getId() == null) {
            return "Книги с таким id не существует";
        }
        return book.toString();
    }
}
