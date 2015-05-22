package facade;

import entity.Item;
import java.util.List;
import javax.ejb.*;

@Local
public interface ItemEao extends Facade {

    List<Item> findItemByKind(Integer kindId);

    List<Item> findItemByOwner(Integer userId);

    List<Item> findItemByWiner(Integer userId);

    List<Item> findItemByState(Integer stateId);
}
