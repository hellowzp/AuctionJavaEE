package business;

import entity.Bid;
import entity.Item;
import entity.Kind;
import java.util.List;
import javax.ejb.*;

import exception.AuctionException;

@Local
public interface AuctionManagerLocal {

    List<Item> getItemByWiner(Integer winerId)
            throws AuctionException;

    List<Item> getFailItems() throws AuctionException;

    int validLogin(String username, String pass)
            throws AuctionException;

    List<Bid> getBidByUser(Integer userId)
            throws AuctionException;

    List<Item> getItemsByOwner(Integer userId)
            throws AuctionException;

    List<Kind> getAllKind() throws AuctionException;

    int addItem(String name, String desc, String remark,
            double initPrice, int avail, int kind, Integer userId)
            throws AuctionException;

    int addKind(String name, String desc) throws AuctionException;

    List<Item> getItemsByKind(int kindId) throws AuctionException;

    String getKind(int kindId) throws AuctionException;

    Item getItem(int itemId) throws AuctionException;

    int addBid(int itemId, double bidPrice, Integer userId)
            throws AuctionException;

    void updateWiner()
            throws AuctionException;
}
