package mago.example.olingo.annotations.extensions;

import mago.example.olingo.annotations.db.AbstractRepository;
import mago.example.olingo.annotations.db.AuthorRepository;
import mago.example.olingo.annotations.db.BooksRepository;
import mago.example.olingo.annotations.db.MySQLConnection;
import mago.example.olingo.annotations.model.Author;
import mago.example.olingo.annotations.model.Book;
import org.apache.olingo.odata2.annotation.processor.core.datasource.DataSource;
import org.apache.olingo.odata2.api.edm.EdmEntitySet;
import org.apache.olingo.odata2.api.edm.EdmException;
import org.apache.olingo.odata2.api.edm.EdmFunctionImport;
import org.apache.olingo.odata2.api.exception.ODataApplicationException;
import org.apache.olingo.odata2.api.exception.ODataNotFoundException;
import org.apache.olingo.odata2.api.exception.ODataNotImplementedException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class CustomDataSource implements DataSource {

    // repo per entity name
    private Map<String, AbstractRepository> repositories;

    // Class implementation per entity name
    private Map<String, Class> implementations;

    public CustomDataSource() {
        this.repositories = new HashMap<String, AbstractRepository>();
        this.repositories.put("Book", new BooksRepository(MySQLConnection.getInstance()));
        this.repositories.put("Author", new AuthorRepository(MySQLConnection.getInstance()));

        this.implementations = new HashMap<String, Class>();
        this.implementations.put("Book", mago.example.olingo.annotations.model.Book.class);
        this.implementations.put("Author", mago.example.olingo.annotations.model.Author.class);
    }

    public List<?> readData(EdmEntitySet edmEntitySet) throws ODataNotImplementedException, ODataNotFoundException, EdmException, ODataApplicationException {
        return this.repositories.get(edmEntitySet.getEntityType().getName()).getAll();
    }

    public Object readData(EdmEntitySet edmEntitySet, Map<String, Object> map) throws ODataNotImplementedException, ODataNotFoundException, EdmException, ODataApplicationException {
        Long id = (Long) map.get("Id");
        String entityName = edmEntitySet.getEntityType().getName();
        Object result = this.repositories.get(entityName).get(id);
        return result;
    }

    public Object readData(EdmFunctionImport edmFunctionImport, Map<String, Object> map, Map<String, Object> map1) throws ODataNotImplementedException, ODataNotFoundException, EdmException, ODataApplicationException {
        return null;
    }

    public Object readRelatedData(EdmEntitySet edmEntitySet, Object o, EdmEntitySet edmEntitySet1, Map<String, Object> map) throws ODataNotImplementedException, ODataNotFoundException, EdmException, ODataApplicationException {

        // todo: reflection!!!!
        if(edmEntitySet.getEntityType().getName().equals("Book")){
            return ((Book)o).getAuthor();
        }else {
            return null;
        }
    }

    public BinaryData readBinaryData(EdmEntitySet edmEntitySet, Object o) throws ODataNotImplementedException, ODataNotFoundException, EdmException, ODataApplicationException {
        return null;
    }

    public Object newDataObject(EdmEntitySet edmEntitySet) throws ODataNotImplementedException, EdmException, ODataApplicationException {
        Object newInstance = null;
        String entityName = edmEntitySet.getEntityType().getName();
        try {
            newInstance = this.implementations.get(entityName).newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
            throw new ODataNotImplementedException();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new ODataNotImplementedException();
        }

        // persistence is done in createData()

        return newInstance;
    }

    public void writeBinaryData(EdmEntitySet edmEntitySet, Object o, BinaryData binaryData) throws ODataNotImplementedException, ODataNotFoundException, EdmException, ODataApplicationException {

    }

    public void deleteData(EdmEntitySet edmEntitySet, Map<String, Object> map) throws ODataNotImplementedException, ODataNotFoundException, EdmException, ODataApplicationException {
        String entityName = edmEntitySet.getEntityType().getName();
        this.repositories.get(entityName).delete((Long)map.get("Id"));
    }

    public void createData(EdmEntitySet edmEntitySet, Object o) throws ODataNotImplementedException, EdmException, ODataApplicationException {
        // persist in DB
        String entityName = edmEntitySet.getEntityType().getName();
        this.repositories.get(entityName).save(o);
    }

    public void deleteRelation(EdmEntitySet edmEntitySet, Object o, EdmEntitySet edmEntitySet1, Map<String, Object> map) throws ODataNotImplementedException, ODataNotFoundException, EdmException, ODataApplicationException {
    }

    public void writeRelation(EdmEntitySet edmEntitySet, Object o, EdmEntitySet edmEntitySet1, Map<String, Object> map) throws ODataNotImplementedException, ODataNotFoundException, EdmException, ODataApplicationException {
        Object associatedEntity = this.readData(edmEntitySet1, map);
        String associatedEntityName = edmEntitySet1.getEntityType().getName();

        // todo use reflection!
        if(edmEntitySet.getEntityType().getName().equals("Book")){
            ((Book)o).setAuthor((Author) associatedEntity);
            String entityName = edmEntitySet.getEntityType().getName();
            this.repositories.get(entityName).save(o);
        }

    }
}
