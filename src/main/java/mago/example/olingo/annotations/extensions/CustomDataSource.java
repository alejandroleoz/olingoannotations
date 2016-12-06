package mago.example.olingo.annotations.extensions;

import mago.example.olingo.annotations.model.Author;
import mago.example.olingo.annotations.model.Book;
import org.apache.olingo.odata2.annotation.processor.core.datasource.DataSource;
import org.apache.olingo.odata2.api.edm.EdmEntitySet;
import org.apache.olingo.odata2.api.edm.EdmException;
import org.apache.olingo.odata2.api.edm.EdmFunctionImport;
import org.apache.olingo.odata2.api.exception.ODataApplicationException;
import org.apache.olingo.odata2.api.exception.ODataNotFoundException;
import org.apache.olingo.odata2.api.exception.ODataNotImplementedException;

import java.util.List;
import java.util.Map;


public class CustomDataSource implements DataSource {
    public List<?> readData(EdmEntitySet edmEntitySet) throws ODataNotImplementedException, ODataNotFoundException, EdmException, ODataApplicationException {
        return null;
    }

    public Object readData(EdmEntitySet edmEntitySet, Map<String, Object> map) throws ODataNotImplementedException, ODataNotFoundException, EdmException, ODataApplicationException {
//        return null;
        Book book = new Book(123L, "El principito", "aaaaa");
        book.setAuthor(new Author(88L, "Pipi Romagnoli"));
        return book;
    }

    public Object readData(EdmFunctionImport edmFunctionImport, Map<String, Object> map, Map<String, Object> map1) throws ODataNotImplementedException, ODataNotFoundException, EdmException, ODataApplicationException {
        return null;
    }

    public Object readRelatedData(EdmEntitySet edmEntitySet, Object o, EdmEntitySet edmEntitySet1, Map<String, Object> map) throws ODataNotImplementedException, ODataNotFoundException, EdmException, ODataApplicationException {
        return null;
    }

    public BinaryData readBinaryData(EdmEntitySet edmEntitySet, Object o) throws ODataNotImplementedException, ODataNotFoundException, EdmException, ODataApplicationException {
        return null;
    }

    public Object newDataObject(EdmEntitySet edmEntitySet) throws ODataNotImplementedException, EdmException, ODataApplicationException {
        return new Book(444L, "a", "b");
    }

    public void writeBinaryData(EdmEntitySet edmEntitySet, Object o, BinaryData binaryData) throws ODataNotImplementedException, ODataNotFoundException, EdmException, ODataApplicationException {

    }

    public void deleteData(EdmEntitySet edmEntitySet, Map<String, Object> map) throws ODataNotImplementedException, ODataNotFoundException, EdmException, ODataApplicationException {

    }

    public void createData(EdmEntitySet edmEntitySet, Object o) throws ODataNotImplementedException, EdmException, ODataApplicationException {

    }

    public void deleteRelation(EdmEntitySet edmEntitySet, Object o, EdmEntitySet edmEntitySet1, Map<String, Object> map) throws ODataNotImplementedException, ODataNotFoundException, EdmException, ODataApplicationException {

    }

    public void writeRelation(EdmEntitySet edmEntitySet, Object o, EdmEntitySet edmEntitySet1, Map<String, Object> map) throws ODataNotImplementedException, ODataNotFoundException, EdmException, ODataApplicationException {

    }
}
