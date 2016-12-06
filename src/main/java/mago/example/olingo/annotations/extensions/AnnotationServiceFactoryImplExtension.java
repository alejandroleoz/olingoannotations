package mago.example.olingo.annotations.extensions;

import org.apache.olingo.odata2.annotation.processor.api.AnnotationServiceFactory;
import org.apache.olingo.odata2.annotation.processor.core.ListsProcessor;
import org.apache.olingo.odata2.annotation.processor.core.datasource.AnnotationInMemoryDs;
import org.apache.olingo.odata2.annotation.processor.core.datasource.AnnotationValueAccess;
import org.apache.olingo.odata2.annotation.processor.core.datasource.DataSource;
import org.apache.olingo.odata2.annotation.processor.core.edm.AnnotationEdmProvider;
import org.apache.olingo.odata2.api.ODataService;
import org.apache.olingo.odata2.api.exception.ODataException;
import org.apache.olingo.odata2.api.rt.RuntimeDelegate;

import java.util.Collection;

/**
 * Created by aleoz on 12/6/16.
 */
public class AnnotationServiceFactoryImplExtension {

    public AnnotationServiceFactoryImplExtension() {
    }

    public ODataService createAnnotationService(String modelPackage) throws ODataException {
        AnnotationEdmProvider edmProvider = new AnnotationEdmProvider(modelPackage);
//        AnnotationInMemoryDs dataSource = new AnnotationInMemoryDs(modelPackage);
        DataSource dataSource = new CustomDataSource();
        AnnotationValueAccess valueAccess = new AnnotationValueAccess();
        return RuntimeDelegate.createODataSingleProcessorService(edmProvider, new ListsProcessor(dataSource, valueAccess));
    }

    public ODataService createAnnotationService(Collection<Class<?>> annotatedClasses) throws ODataException {
        AnnotationEdmProvider edmProvider = new AnnotationEdmProvider(annotatedClasses);
//        AnnotationInMemoryDs dataSource = new AnnotationInMemoryDs(annotatedClasses);
        DataSource dataSource = new CustomDataSource();
        AnnotationValueAccess valueAccess = new AnnotationValueAccess();
        return RuntimeDelegate.createODataSingleProcessorService(edmProvider, new ListsProcessor(dataSource, valueAccess));
    }
}
