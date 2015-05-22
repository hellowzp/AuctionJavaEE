package facade;

import entity.Bid;
import entity.User;
import java.util.*;

import javax.ejb.*;

@Stateless(name = "bidFacade")
public class BidFacade extends AbstractFacade implements BidFacadeLocal {

    @Override
    public List<Bid> findByUser(Integer userId) {
        return getResultList(Bid.class, "where o.bidUser.id = ?1", null, userId);
    }

    @Override
    public User findUserByItemAndPrice(Integer itemId, Double price) {
        List<Bid> bidList = getResultList(Bid.class, "where o.bidItem.id = ?1 and o.bidPrice = ?2", null, itemId, price);
        
        if (bidList != null && bidList.size() >= 1) {
            return bidList.get(0).getBidUser();
        }
        return null;
    }
}
