package facade;

import entity.State;
import java.util.*;

import javax.ejb.*;

@Stateless(name = "stateFacade")
public class StateFacade extends AbstractFacade implements StateFacadeLocal {

    @Override
    public List<State> findAll() {
        return getResultList(State.class, "", null);
    }
}
