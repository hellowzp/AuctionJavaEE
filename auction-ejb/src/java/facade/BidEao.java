package facade;

import entity.Bid;
import entity.User;
import java.util.List;
import javax.ejb.*;

@Local
public interface BidEao extends Facade {

    List<Bid> findByUser(Integer userId);

    User findUserByItemAndPrice(Integer itemId, Double price);
}
