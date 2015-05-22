package facade;

import java.util.*;
import javax.persistence.*;

public abstract class AbstractFacade implements Facade {

    @PersistenceContext(unitName = "auction-ejbPU", type = PersistenceContextType.TRANSACTION)
    private EntityManager em;

    @Override
    public <T> T get(Class<T> entityClass, Object primaryKey) {
        T obj = em.find(entityClass, primaryKey);
        return obj;
    }
    
    @Override
    public void save(Object entity) {
        em.persist(entity);
    }

    
    @Override
    public void update(Object entity) {
        em.merge(entity);
    }

    
    @Override
    public void delete(Class entityClass, Object primaryKey) {
        em.remove(em.getReference(entityClass, primaryKey));
    }

    @Override
    public <T> List<T> getResultList(Class<T> entityClass, String whereJpql, LinkedHashMap<String, String> orderBy, Object... args) {
        
        String entityName = entityClass.getSimpleName();
        
        Query query = em.createQuery("select o from " + entityName
                + " as o " + whereJpql + buildOrderby(orderBy));
        
        for (int i = 0; i < args.length; i++) {
            query.setParameter(i + 1, args[i]);
        }
        
        return (List<T>) query.getResultList();
    }

    @Override
    public <T> List<T> getResultList(Class<T> entityClass, String whereJpql, int firstResult, int maxResult, LinkedHashMap<String, String> orderBy, Object... args) {
        
        String entityName = entityClass.getSimpleName();
        
        Query query = em.createQuery("select o from " + entityName
                + " as o " + whereJpql + buildOrderby(orderBy));
        
        for (int i = 0; i < args.length; i++) {
            query.setParameter(i + 1, args[i]);
        }
        
        query.setMaxResults(maxResult).setFirstResult(firstResult);
        
        return (List<T>) query.getResultList();
    }

    
    private static String buildOrderby(LinkedHashMap<String, String> orderby) {
        StringBuilder out = new StringBuilder();
        if (orderby != null && orderby.size() > 0) {
            
            out.append(" order by ");
			
            
            for (String key : orderby.keySet()) {
                out.append("o.").append(key).append(" ").append(orderby.get(key)).append(",");
            }
            out.deleteCharAt(out.length() - 1);
        }
        return out.toString();
    }
}
