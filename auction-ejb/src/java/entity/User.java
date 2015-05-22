package entity;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;

@Entity
@Table(name = "auction_user")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer id;

    @Column(name = "username", length = 50)
    private String username;

    @Column(name = "userpass", length = 50)
    private String userpass;

    @Column(name = "email", length = 100)
    private String email;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner", targetEntity = Item.class)
    private Set<Item> itemsByOwner = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "winer", targetEntity = Item.class)
    private Set<Item> itemsByWiner = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bidUser", targetEntity = Bid.class)
    private Set<Bid> bids = new HashSet<>();

    public User() {
    }

    public User(Integer id, String username,
            String userpass, String email) {
        this.id = id;
        this.username = username;
        this.userpass = userpass;
        this.email = email;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return this.id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUserpass(String userpass) {
        this.userpass = userpass;
    }

    public String getUserpass() {
        return this.userpass;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return this.email;
    }

    public void setItemsByOwner(Set<Item> itemsByOwner) {
        this.itemsByOwner = itemsByOwner;
    }

    public Set<Item> getItemsByOwner() {
        return this.itemsByOwner;
    }

    public void setItemsByWiner(Set<Item> itemsByWiner) {
        this.itemsByWiner = itemsByWiner;
    }

    public Set<Item> getItemsByWiner() {
        return this.itemsByWiner;
    }

    public void setBids(Set<Bid> bids) {
        this.bids = bids;
    }

    public Set<Bid> getBids() {
        return this.bids;
    }
}
