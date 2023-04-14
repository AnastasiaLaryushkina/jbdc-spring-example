package ru.itgirl.jbdcspringexample.repository;

import ru.itgirl.jbdcspringexample.model.Books;

import java.util.List;

public interface BookRepository {
    List<Books> findAllBooks();
    Books getBookById(Long id);

}
