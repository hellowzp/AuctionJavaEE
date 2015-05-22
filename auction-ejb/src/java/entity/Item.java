package entity;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;

@Entity
@Table(name = "item")
public class Item implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Integer id;

    @Column(name = "item_remark", length = 255, nullable = false)
    private String itemRemark;

    @Column(name = "item_name", length = 255, nullable = false)
    private String itemName;

    @Column(name = "item_desc", length = 255, nullable = false)
    private String itemDesc;

    @Column(name = "addtime", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date addtime;

    @Column(name = "endtime", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date endtime;

    @Column(name = "init_price", nullable = false)
    private double initPrice;

    @Column(name = "max_price", nullable = false)
    private double maxPrice;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = User.class, cascade = CascadeType.ALL)

    @JoinColumn(name = "owner_id", nullable = false)
    private User owner;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Kind.class, cascade = CascadeType.ALL)

    @JoinColumn(name = "kind_id", nullable = false)
    private Kind kind;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = User.class, cascade = CascadeType.ALL)

    @JoinColumn(name = "winer_id", nullable = true)
    private User winer;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = State.class, cascade = CascadeType.ALL)

    @JoinColumn(name = "state_id", nullable = false)
    private State itemState;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bidItem", targetEntity = Bid.class)
    private Set<Bid> bids = new HashSet<>();

    public Item() {
    }

    public Item(Integer id, String itemRemark, String itemName,
            String itemDesc, Date addtime, Date endtime,
            double initPrice, double maxPrice, User owner) {
        this.id = id;
        this.itemRemark = itemRemark;
        this.itemName = itemName;
        this.itemDesc = itemDesc;
        this.addtime = addtime;
        this.endtime = endtime;
        this.initPrice = initPrice;
        this.maxPrice = maxPrice;
        this.owner = owner;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return this.id;
    }

    public void setItemRemark(String itemRemark) {
        this.itemRemark = itemRemark;
    }

    public String getItemRemark() {
        return this.itemRemark;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemName() {
        return this.itemName;
    }

    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc;
    }

    public String getItemDesc() {
        return this.itemDesc;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    public Date getAddtime() {
        return this.addtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    public Date getEndtime() {
        return this.endtime;
    }

    public void setInitPrice(double initPrice) {
        this.initPrice = initPrice;
    }

    public double getInitPrice() {
        return this.initPrice;
    }

    public void setMaxPrice(double maxPrice) {
        this.maxPrice = maxPrice;
    }

    public double getMaxPrice() {
        return this.maxPrice;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public User getOwner() {
        return this.owner;
    }

    public void setKind(Kind kind) {
        this.kind = kind;
    }

    public Kind getKind() {
        return this.kind;
    }

    public void setWiner(User winer) {
        this.winer = winer;
    }

    public User getWiner() {
        return this.winer;
    }

    public void setItemState(State itemState) {
        this.itemState = itemState;
    }

    public State getItemState() {
        return this.itemState;
    }

    public void setBids(Set<Bid> bids) {
        this.bids = bids;
    }

    public Set<Bid> getBids() {
        return this.bids;
    }
}
