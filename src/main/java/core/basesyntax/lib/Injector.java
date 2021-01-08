package core.basesyntax.lib;

import core.basesyntax.ApplicationStarter;
import core.basesyntax.exceptions.DaoAnnotationException;
import core.basesyntax.factory.Factory;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Set;
import org.reflections.Reflections;

public class Injector {
    public static Object getInstance(Class<?> clazz) throws IllegalAccessException,
            InvocationTargetException, InstantiationException, NoSuchMethodException {
        Object instance = clazz.getDeclaredConstructor().newInstance();
        for (Field field : clazz.getDeclaredFields()) {
            if (field.getAnnotation(Inject.class) != null) {
                field.setAccessible(true);
                Class<?> fieldType = field.getType();
                for (Class<?> foundClass : reflectionsScan(Dao.class)) {
                    if (Arrays.stream(foundClass.getInterfaces()).anyMatch(c -> c == fieldType)) {
                        Object daoByType = Factory.getDaoByType(foundClass);
                        if (daoByType == null) {
                            throw new DaoAnnotationException("Proper annotation Dao does not exist."
                                    + " Field: " + field.getName());
                        }
                        field.set(instance, daoByType);
                    }
                }
            }
        }
        return instance;
    }

    private static Set<Class<?>> reflectionsScan(Class<? extends Annotation> annotation) {
        Reflections reflections = new Reflections(ApplicationStarter.class.getPackage().getName());
        Set<Class<?>> classes = reflections.getTypesAnnotatedWith(annotation);
        if (classes.size() == 0) {
            throw new DaoAnnotationException("Proper annotation Dao does not exist.");
        }
        return classes;
    }
}
