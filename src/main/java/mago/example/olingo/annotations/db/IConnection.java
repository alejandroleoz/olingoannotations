package mago.example.olingo.annotations.db;

import javax.persistence.EntityManager;

/**
 * Created by aleoz on 11/22/16.
 */
public interface IConnection {

    void createConnection();

    void closeConnection();

    EntityManager getEntityManager();
}
