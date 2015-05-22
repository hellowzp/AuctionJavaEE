package facade;

import entity.Kind;
import java.util.*;

import javax.ejb.*;

@Stateless(name = "kindEao")
public class KindEaoImpl extends AbstractFacade implements KindEao {

    @Override
    public List<Kind> findAll() {
        return getResultList(Kind.class, "", null);
    }
}
