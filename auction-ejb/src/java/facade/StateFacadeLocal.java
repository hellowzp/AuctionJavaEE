package facade;

import entity.State;
import java.util.List;
import javax.ejb.*;

@Local
public interface StateFacadeLocal extends Facade {

    List<State> findAll();
}
