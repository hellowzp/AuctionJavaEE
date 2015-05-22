package business;

import facade.StateFacadeLocal;
import facade.ItemFacadeLocal;
import facade.UserFacadeLocal;
import facade.BidFacadeLocal;
import facade.KindFacadeLocal;
import entity.Bid;
import entity.User;
import entity.Item;
import entity.Kind;
import entity.State;
import org.apache.log4j.Logger;

import java.util.*;
import javax.ejb.*;
import javax.annotation.*;
import javax.jms.*;

import exception.AuctionException;

@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
@Stateless(name = "auctionManager")
public class AuctionManagerBean implements AuctionManagerLocal {

    static Logger log = Logger.getLogger(AuctionManagerBean.class.getName());

    @EJB(beanName = "userFacade")
    private UserFacadeLocal userFacade;
    
    @EJB(beanName = "bidFacade")
    private BidFacadeLocal bidFacade;
    
    @EJB(beanName = "itemFacade")
    private ItemFacadeLocal itemFacade;
    
    @EJB(beanName = "kindFacade")
    private KindFacadeLocal kindFacade;
    
    @EJB(beanName = "stateFacade")
    private StateFacadeLocal stateFacade;

    @Resource(mappedName = "auction_mdb_queue")
    private Destination dest;
    
    @Resource(mappedName = "jms/AuctionConnectionFactory")
    private ConnectionFactory connFactory;

    @Override
    public List<Item> getItemByWiner(Integer winerId) throws AuctionException {
        try {
            return itemFacade.findItemByWiner(winerId);
        } catch (Exception e) {
            log.debug(e.getMessage());
            throw new AuctionException("查询用户所赢取的物品出现异常,请重试");
        }
    }

    @Override
    public List<Item> getFailItems() throws AuctionException {
        try {
            return itemFacade.findItemByState(3);
        } catch (Exception e) {
            log.debug(e.getMessage());
            throw new AuctionException("查询流拍物品出现异常,请重试");
        }
    }

    @Override
    public int validateLogin(String username, String pass) throws AuctionException {
        try {
            User u = userFacade.findUserByNameAndPwd(username, pass);
            if (u != null) {
                return u.getId();
            }
            return -1;
        } catch (Exception e) {
            log.debug(e.getMessage());
            throw new AuctionException("AuctionManager validate users exception");
        }
    }

    @Override
    public List<Bid> getBidByUser(Integer userId) throws AuctionException {
        try {
            return bidFacade.findByUser(userId);
        } catch (Exception e) {
            log.debug(e.getMessage());
            throw new AuctionException("浏览用户的全部竞价出现异常,请重试");
        }
    }

    @Override
    public List<Item> getItemsByOwner(Integer userId) throws AuctionException {
        try {
            return itemFacade.findItemByOwner(userId);
        } catch (Exception e) {
            log.debug(e.getMessage());
            throw new AuctionException("查询用户所有的物品出现异常,请重新");
        }
    }

    @Override
    public List<Kind> getAllKind() throws AuctionException {
        try {
            return kindFacade.findAll();
        } catch (Exception e) {
            log.debug(e.getMessage());
            throw new AuctionException("查询全部种类出现异常,请重试");
        }
    }

    @Override
    public int addItem(String name, String desc, String remark,
            double initPrice, int avail, int kindId, Integer userId)
            throws AuctionException {
        try {
            Kind k = kindFacade.get(Kind.class, kindId);
            User owner = userFacade.get(User.class, userId);

            Item item = new Item();
            item.setItemName(name);
            item.setItemDesc(desc);
            item.setItemRemark(remark);
            item.setAddtime(new Date());
            Calendar c = Calendar.getInstance();
            c.add(Calendar.DATE, avail);
            item.setEndtime(c.getTime());
            item.setInitPrice(initPrice);
            item.setMaxPrice(initPrice);
            item.setItemState(stateFacade.get(State.class, 1));
            item.setKind(k);
            item.setOwner(owner);

            itemFacade.save(item);
            return item.getId();
        } catch (Exception e) {
            log.debug(e.getMessage());
            throw new AuctionException("添加物品出现异常,请重试");
        }
    }

    @Override
    public int addKind(String name, String desc) throws AuctionException {
        try {
            Kind k = new Kind();
            k.setKindName(name);
            k.setKindDesc(desc);
            kindFacade.save(k);
            return k.getId();
        } catch (Exception e) {
            log.debug(e.getMessage());
            throw new AuctionException("添加种类出现异常,请重试");
        }
    }

    @Override
    public List<Item> getItemsByKind(int kindId) throws AuctionException {
        try {
            return itemFacade.findItemByKind(kindId);
        } catch (Exception e) {
            log.debug(e.getMessage());
            throw new AuctionException("根据种类获取物品出现异常,请重试");
        }
    }

    @Override
    public String getKind(int kindId) throws AuctionException {
        try {
            Kind k = kindFacade.get(Kind.class, kindId);
            if (k != null) {
                return k.getKindName();
            }
            return null;
        } catch (Exception ex) {
            log.debug(ex.getMessage());
            throw new AuctionException("根据种类id获取种类名称出现异常,请重试");
        }
    }

    @Override
    public Item getItem(int itemId)
            throws AuctionException {
        try {
            return itemFacade.get(Item.class, itemId);
        } catch (Exception ex) {
            log.debug(ex.getMessage());
            throw new AuctionException("根据物品id获取物品详细信息出现异常,请重试");
        }
    }

    @Override
    public int addBid(int itemId, double bidPrice, Integer userId)
            throws AuctionException {
        try {
            User au = userFacade.get(User.class, userId);
            Item item = itemFacade.get(Item.class, itemId);
            if (bidPrice > item.getMaxPrice()) {
                item.setMaxPrice(bidPrice);
                itemFacade.save(item);
            }

            Bid bid = new Bid();
            bid.setBidItem(item);
            bid.setBidUser(au);
            bid.setBidDate(new Date());
            bid.setBidPrice(bidPrice);

            bidFacade.save(bid);

            try(Connection conn = connFactory.createConnection();
                Session session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE)) {
                               
                MessageProducer sender = session.createProducer(dest);
                
                sender.setDeliveryMode(DeliveryMode.PERSISTENT);
                sender.setTimeToLive(20000);
                
                MapMessage msg = session.createMapMessage();
                
                msg.setString("mailTo", au.getEmail());
                msg.setString("bidUser", au.getUsername());
                msg.setString("itemName", item.getItemName());
                
                sender.send(msg);                
            }

            return bid.getId();
        } catch (Exception ex) {
            ex.printStackTrace();
            log.debug(ex.getMessage());
            throw new AuctionException("处理用户竞价出现异常,请重试");
        }
    }

    @Override
    public void updateWiner() throws AuctionException {
        try {
            List<Item> itemList = itemFacade.findItemByState(1);
            for( Item item : itemList) {
                if (!item.getEndtime().after(new Date())) {

                    User au = bidFacade.findUserByItemAndPrice(
                            item.getId(), item.getMaxPrice());

                    if (au != null) {

                        item.setWiner(au);

                        item.setItemState(stateFacade.get(State.class, 2));
                        itemFacade.save(item);
                    } else {

                        item.setItemState(stateFacade.get(State.class, 3));
                        itemFacade.save(item);
                    }
                }
            }
        } catch (Exception ex) {
            log.debug(ex.getMessage());
            throw new AuctionException("检查物品是否超过竞拍时间出现异常,请重试");
        }
    }
}
