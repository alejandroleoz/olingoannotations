package mago.example.olingo.annotations.extensions;

import org.apache.olingo.odata2.api.ODataService;
import org.apache.olingo.odata2.api.exception.ODataException;

import java.util.Collection;

/**
 * Created by aleoz on 12/6/16.
 */
public class AnnotationServiceFactoryExtension {

    private static AnnotationServiceFactoryImplExtension impl;

    static {
        Class clazz = null;
        try {
            clazz = Class.forName("mago.example.olingo.annotations.extensions.AnnotationServiceFactoryImplExtension");
            Object object = clazz.newInstance();
            impl = (AnnotationServiceFactoryImplExtension) object;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public AnnotationServiceFactoryExtension() {
    }

    public static ODataService createAnnotationService(String modelPackage) throws ODataException {
        return impl.createAnnotationService(modelPackage);
    }

    public static ODataService createAnnotationService(Collection<Class<?>> annotatedClasses) throws ODataException {
        return impl.createAnnotationService(annotatedClasses);
    }
}
