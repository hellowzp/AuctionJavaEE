package entity;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;

@Entity
@Table(name = "bid")
public class Bid implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bid_id")
    private Integer id;

    @Column(name = "bid_price", nullable = false)
    private double bidPrice;

    @Column(name = "bid_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date bidDate;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Item.class, cascade = CascadeType.ALL)

    @JoinColumn(name = "item_id", nullable = false)
    private Item bidItem;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = User.class, cascade = CascadeType.ALL)

    @JoinColumn(name = "user_id", nullable = false)
    private User bidUser;

    public Bid() {
    }

    public Bid(Integer id, double bidPrice, Date bidDate) {
        this.id = id;
        this.bidPrice = bidPrice;
        this.bidDate = bidDate;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return this.id;
    }

    public void setBidPrice(double bidPrice) {
        this.bidPrice = bidPrice;
    }

    public double getBidPrice() {
        return this.bidPrice;
    }

    public void setBidDate(Date bidDate) {
        this.bidDate = bidDate;
    }

    public Date getBidDate() {
        return this.bidDate;
    }

    public void setBidItem(Item bidItem) {
        this.bidItem = bidItem;
    }

    public Item getBidItem() {
        return this.bidItem;
    }

    public void setBidUser(User bidUser) {
        this.bidUser = bidUser;
    }

    public User getBidUser() {
        return this.bidUser;
    }

    public int hashCode() {
        return bidUser.getUsername().hashCode()
                + bidItem.hashCode() * 13 + (int) bidPrice * 19;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && obj.getClass() == Bid.class) {
            Bid bid = (Bid) obj;
            if (bid.getBidUser().getUsername().equals(bidUser.getUsername())
                    && bid.getBidItem().equals(this.getBidItem())
                    && bid.getBidPrice() == this.getBidPrice()) {
                return true;
            }
        }
        return false;
    }
}
