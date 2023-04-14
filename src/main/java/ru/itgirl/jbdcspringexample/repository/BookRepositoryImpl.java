package ru.itgirl.jbdcspringexample.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.itgirl.jbdcspringexample.model.Books;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BookRepositoryImpl implements BookRepository {

    @Autowired
    private DataSource dataSource;

    public BookRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Books> findAllBooks() {
        List<Books> result = new ArrayList<>();
        String SQL_findAllBooks = "Select * from books";

        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SQL_findAllBooks)) {
            while (resultSet.next()) {
                Books books = convertRowToBook(resultSet);
                result.add(books);
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
        return result;
    }

    @Override
    public Books getBookById(Long id) {
        String query = "Select id, name from books where id = ?";
        Books book = null;
        try (Connection connection = dataSource.getConnection()) {
             PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if(resultSet.next()) {
                    book = new Books(resultSet.getLong("id"),
                                        resultSet.getString("name"));
                } else {
                    book = new Books(null, null);
                }
            }

        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
        return book;
    }


    private Books convertRowToBook(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong("id");
        String name = resultSet.getString("name");
        return new Books(id, name);
    }
}
