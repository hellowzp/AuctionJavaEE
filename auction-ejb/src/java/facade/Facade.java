package facade;

import java.io.Serializable;
import java.util.*;

public interface Facade extends Serializable {
    
    <T> T get(Class<T> entityClass, Object pk);

    void save(Object entity);

    void update(Object entity);

    void delete(Class<?> entityClass, Object pk);

    <T> List<T> getResultList(Class<T> entityClass, String whereJpql, LinkedHashMap<String, String> orderBy, Object... args);

    <T> List<T> getResultList(Class<T> entityClass, String whereJpql, int firstResult, int maxResult, LinkedHashMap<String, String> orderBy, Object... args);
}
