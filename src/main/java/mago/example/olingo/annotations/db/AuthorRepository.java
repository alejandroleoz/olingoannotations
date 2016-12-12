package mago.example.olingo.annotations.db;

import mago.example.olingo.annotations.model.Author;

import java.util.List;

/**
 * Created by aleoz on 12/6/16.
 */
public class AuthorRepository extends AbstractRepository<Author> {

    public AuthorRepository(IConnection connection) {
        super(connection, Author.class);
    }

    public List<Author> getAll() {
        String strQuery = "SELECT author FROM Author author";
        return this.connection.getEntityManager().createQuery(strQuery).getResultList();
    }
}
