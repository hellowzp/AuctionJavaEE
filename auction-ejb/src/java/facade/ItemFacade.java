package facade;

import entity.Item;
import java.util.*;

import javax.ejb.*;

@Stateless(name = "itemFacade")
public class ItemFacade extends AbstractFacade implements ItemFacadeLocal {

    @Override
    public List<Item> findItemByKind(Integer kindId) {
        return getResultList(Item.class, "where o.kind.id = ?1 and o.itemState.id = 1", null, kindId);
    }

    @Override
    public List<Item> findItemByOwner(Integer userId) {
        return getResultList(Item.class, "where o.owner.id = ?1 and o.itemState.id = 1", null, userId);
    }

    @Override
    public List<Item> findItemByWiner(Integer userId) {
        return getResultList(Item.class, "where o.winer.id = ?1 and o.itemState.id = 2", null, userId);
    }

    @Override
    public List<Item> findItemByState(Integer stateId) {
        return getResultList(Item.class, "where o.itemState.id = ?1", null, stateId);
    }
}
