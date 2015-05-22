package entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "kind")
public class Kind implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "kind_id")
    private Integer id;

    @Column(name = "kind_name", length = 50, nullable = false)
    private String kindName;

    @Column(name = "kind_desc", length = 255, nullable = false)
    private String kindDesc;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "kind", targetEntity = Item.class)
    private Set<Item> items = new HashSet<>();

    public Kind() {
    }

    public Kind(Integer id, String kindName, String kindDesc) {
        this.id = id;
        this.kindName = kindName;
        this.kindDesc = kindDesc;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return this.id;
    }

    public void setKindName(String kindName) {
        this.kindName = kindName;
    }

    public String getKindName() {
        return this.kindName;
    }

    public void setKindDesc(String kindDesc) {
        this.kindDesc = kindDesc;
    }

    public String getKindDesc() {
        return this.kindDesc;
    }

    public void setItems(Set<Item> items) {
        this.items = items;
    }

    public Set<Item> getItems() {
        return this.items;
    }

}
