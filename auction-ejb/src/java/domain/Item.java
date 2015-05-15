/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.util.*;
import javax.persistence.*;

/**
 * Description:
 * @author   Wang 
 * @version  1.0
 */

@Entity
@Table(name="item")
public class Item
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="item_id")
	private Integer id;
	
	@Column(name="item_remark", length=255, nullable=false)
	private String itemRemark;
	
	@Column(name="item_name", length=255, nullable=false)
	private String itemName;
	
	@Column(name="item_desc" , length=255, nullable=false)
	private String itemDesc;
        
	@Column(name="addtime", nullable=false)
	@Temporal(TemporalType.DATE)
	private Date addtime;
        
	@Column(name="endtime", nullable=false)
	@Temporal(TemporalType.DATE)
	private Date endtime;
	
	@Column(name="init_price", nullable=false)
	private double initPrice;
	
	@Column(name="max_price", nullable=false)
	private double maxPrice;
	
	@ManyToOne(fetch=FetchType.EAGER, targetEntity=AuctionUser.class, cascade=CascadeType.ALL)
	/* use @JoinColumn to indicate foreign column */
	@JoinColumn(name="owner_id", nullable=false)
	private AuctionUser owner;
	
	@ManyToOne(fetch=FetchType.EAGER, targetEntity=Kind.class, cascade=CascadeType.ALL)
	@JoinColumn(name="kind_id", nullable=false)
	private Kind kind;

        @ManyToOne(fetch=FetchType.EAGER, targetEntity=AuctionUser.class, cascade=CascadeType.ALL)
	/* 使用@JoinColumn来配置外键列的信息 */
	@JoinColumn(name="winer_id", nullable=true)
	private AuctionUser winer;
	//该物品所处的状态
	@ManyToOne(fetch=FetchType.EAGER
		,targetEntity=State.class , cascade=CascadeType.ALL)
	/* 使用@JoinColumn来配置外键列的信息 */
	@JoinColumn(name="state_id", nullable=false)
	private State itemState;
	/*该物品对应的全部竞价记录
	  设置了mappedBy属性表明Item实体不控制关联关系，
	  因此不能增加@JoinTable和@JoinColumn修饰*/
	@OneToMany(cascade=CascadeType.ALL , mappedBy="bidItem"
		, targetEntity=Bid.class)
	private Set<Bid> bids = new HashSet<Bid>();

	//无参数的构造器
	public Item()
	{
	}
	//初始化全部基本属性的构造器
	public Item(Integer id , String itemRemark , String itemName , 
		String itemDesc , Date addtime , Date endtime , 
		double initPrice , double maxPrice , AuctionUser owner)
	{
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

	//id属性的setter和getter方法
	public void setId(Integer id)
	{
		this.id = id;
	}
	public Integer getId()
	{
		return this.id;
	}

	//itemRemark属性的setter和getter方法
	public void setItemRemark(String itemRemark)
	{
		this.itemRemark = itemRemark;
	}
	public String getItemRemark()
	{
		return this.itemRemark;
	}

	//itemName属性的setter和getter方法
	public void setItemName(String itemName)
	{
		this.itemName = itemName;
	}
	public String getItemName()
	{
		return this.itemName;
	}

	//itemDesc属性的setter和getter方法
	public void setItemDesc(String itemDesc)
	{
		this.itemDesc = itemDesc;
	}
	public String getItemDesc()
	{
		return this.itemDesc;
	}

	//addtime属性的setter和getter方法
	public void setAddtime(Date addtime)
	{
		this.addtime = addtime;
	}
	public Date getAddtime()
	{
		return this.addtime;
	}

	//endtime属性的setter和getter方法
	public void setEndtime(Date endtime)
	{
		this.endtime = endtime;
	}
	public Date getEndtime()
	{
		return this.endtime;
	}

	//initPrice属性的setter和getter方法
	public void setInitPrice(double initPrice)
	{
		this.initPrice = initPrice;
	}
	public double getInitPrice()
	{
		return this.initPrice;
	}

	//maxPrice属性的setter和getter方法
	public void setMaxPrice(double maxPrice)
	{
		this.maxPrice = maxPrice;
	}
	public double getMaxPrice()
	{
		return this.maxPrice;
	}

	//owner属性的setter和getter方法
	public void setOwner(AuctionUser owner)
	{
		this.owner = owner;
	}
	public AuctionUser getOwner()
	{
		return this.owner;
	}

	//kind属性的setter和getter方法
	public void setKind(Kind kind)
	{
		this.kind = kind;
	}
	public Kind getKind()
	{
		return this.kind;
	}

	//winer属性的setter和getter方法
	public void setWiner(AuctionUser winer)
	{
		this.winer = winer;
	}
	public AuctionUser getWiner()
	{
		return this.winer;
	}

	//itemState属性的setter和getter方法
	public void setItemState(State itemState)
	{
		this.itemState = itemState;
	}
	public State getItemState()
	{
		return this.itemState;
	}

	//bids属性的setter和getter方法
	public void setBids(Set<Bid> bids)
	{
		this.bids = bids;
	}
	public Set<Bid> getBids()
	{
		return this.bids;
	}
}