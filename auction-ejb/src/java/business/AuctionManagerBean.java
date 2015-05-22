package business;

import facade.StateEao;
import facade.ItemEao;
import facade.UserEao;
import facade.BidEao;
import facade.KindEao;
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

    @EJB(beanName = "userEao")
    private UserEao userEao;
    
    @EJB(beanName = "bidEao")
    private BidEao bidEao;
    
    @EJB(beanName = "itemEao")
    private ItemEao itemEao;
    
    @EJB(beanName = "kindEao")
    private KindEao kindEao;
    
    @EJB(beanName = "stateEao")
    private StateEao stateEao;

    @Resource(mappedName = "jms/AuctionQueue")
    private Destination dest;
    
    @Resource(mappedName = "jms/AuctionConnectionFactory")
    private ConnectionFactory connFactory;

    @Override
    public List<Item> getItemByWiner(Integer winerId) throws AuctionException {
        try {
            return itemEao.findItemByWiner(winerId);
        } catch (Exception e) {
            log.debug(e.getMessage());
            throw new AuctionException("查询用户所赢取的物品出现异常,请重试");
        }
    }

    @Override
    public List<Item> getFailItems() throws AuctionException {
        try {
            return itemEao.findItemByState(3);
        } catch (Exception e) {
            log.debug(e.getMessage());
            throw new AuctionException("查询流拍物品出现异常,请重试");
        }
    }

    @Override
    public int validLogin(String username, String pass) throws AuctionException {
        try {
            User u = userEao.findUserByNameAndPass(username, pass);
            if (u != null) {
                return u.getId();
            }
            return -1;
        } catch (Exception e) {
            log.debug(e.getMessage());
            throw new AuctionException("处理用户登录出现异常,请重试");
        }
    }

    @Override
    public List<Bid> getBidByUser(Integer userId) throws AuctionException {
        try {
            return bidEao.findByUser(userId);
        } catch (Exception e) {
            log.debug(e.getMessage());
            throw new AuctionException("浏览用户的全部竞价出现异常,请重试");
        }
    }

    @Override
    public List<Item> getItemsByOwner(Integer userId) throws AuctionException {
        try {
            return itemEao.findItemByOwner(userId);
        } catch (Exception e) {
            log.debug(e.getMessage());
            throw new AuctionException("查询用户所有的物品出现异常,请重新");
        }
    }

    @Override
    public List<Kind> getAllKind() throws AuctionException {
        try {
            return kindEao.findAll();
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
            Kind k = kindEao.get(Kind.class, kindId);
            User owner = userEao.get(User.class, userId);

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
            item.setItemState(stateEao.get(State.class, 1));
            item.setKind(k);
            item.setOwner(owner);

            itemEao.save(item);
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
            kindEao.save(k);
            return k.getId();
        } catch (Exception e) {
            log.debug(e.getMessage());
            throw new AuctionException("添加种类出现异常,请重试");
        }
    }

    @Override
    public List<Item> getItemsByKind(int kindId) throws AuctionException {
        try {
            return itemEao.findItemByKind(kindId);
        } catch (Exception e) {
            log.debug(e.getMessage());
            throw new AuctionException("根据种类获取物品出现异常,请重试");
        }
    }

    @Override
    public String getKind(int kindId) throws AuctionException {
        try {
            Kind k = kindEao.get(Kind.class, kindId);
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
            return itemEao.get(Item.class, itemId);
        } catch (Exception ex) {
            log.debug(ex.getMessage());
            throw new AuctionException("根据物品id获取物品详细信息出现异常,请重试");
        }
    }

    @Override
    public int addBid(int itemId, double bidPrice, Integer userId)
            throws AuctionException {
        try {
            User au = userEao.get(User.class, userId);
            Item item = itemEao.get(Item.class, itemId);
            if (bidPrice > item.getMaxPrice()) {
                item.setMaxPrice(bidPrice);
                itemEao.save(item);
            }

            Bid bid = new Bid();
            bid.setBidItem(item);
            bid.setBidUser(au);
            bid.setBidDate(new Date());
            bid.setBidPrice(bidPrice);

            bidEao.save(bid);

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
            List<Item> itemList = itemEao.findItemByState(1);
            for( Item item : itemList) {
                if (!item.getEndtime().after(new Date())) {

                    User au = bidEao.findUserByItemAndPrice(
                            item.getId(), item.getMaxPrice());

                    if (au != null) {

                        item.setWiner(au);

                        item.setItemState(stateEao.get(State.class, 2));
                        itemEao.save(item);
                    } else {

                        item.setItemState(stateEao.get(State.class, 3));
                        itemEao.save(item);
                    }
                }
            }
        } catch (Exception ex) {
            log.debug(ex.getMessage());
            throw new AuctionException("检查物品是否超过竞拍时间出现异常,请重试");
        }
    }
}
