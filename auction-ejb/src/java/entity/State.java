package entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.*;

/**
 *
 * @author Administrator
 */
@Entity
@Table(name = "state")
public class State implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "state_id")
    private Integer id;

    @Column(name = "state_name", length = 10, nullable = false)
    private String stateName;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "itemState", targetEntity = Item.class)
    private Set<Item> items = new HashSet<>();

    public State() {
    }

    public State(Integer id, String stateName) {
        this.id = id;
        this.stateName = stateName;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return this.id;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getStateName() {
        return this.stateName;
    }

    public void setItems(Set<Item> items) {
        this.items = items;
    }

    public Set<Item> getItems() {
        return this.items;
    }
}
