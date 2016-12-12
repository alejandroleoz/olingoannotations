package mago.example.olingo.annotations.db;

import mago.example.olingo.annotations.model.Book;

import java.util.List;

/**
 * Created by aleoz on 12/6/16.
 */
public class BooksRepository extends AbstractRepository<Book> {

    public BooksRepository(IConnection connection) {
        super(connection, Book.class);
    }

    public List<Book> getAll() {
        String strQuery = "SELECT book FROM Book book";
        return this.connection.getEntityManager().createQuery(strQuery).getResultList();
    }
}
