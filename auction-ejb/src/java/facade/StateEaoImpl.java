package facade;

import entity.State;
import java.util.*;

import javax.ejb.*;

@Stateless(name = "stateEao")
public class StateEaoImpl extends AbstractFacade implements StateEao {

    @Override
    public List<State> findAll() {
        return getResultList(State.class, "", null);
    }
}
