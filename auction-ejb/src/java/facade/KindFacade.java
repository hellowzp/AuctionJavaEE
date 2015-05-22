package facade;

import entity.Kind;
import java.util.*;

import javax.ejb.*;

@Stateless(name = "kindFacade")
public class KindFacade extends AbstractFacade implements KindFacadeLocal {

    @Override
    public List<Kind> findAll() {
        return getResultList(Kind.class, "", null);
    }
}
