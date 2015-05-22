package jsf;

import java.util.*;
import javax.ejb.*;
import javax.faces.context.FacesContext;

import business.AuctionManagerLocal;
import exception.AuctionException;


public class AddBidAction
{
	
	private int itemId;
	private double bidPrice;
	private double maxPrice;
	private String vercode;
	private String tipInfo;
	
	@EJB(beanName="auctionManager")
	private AuctionManagerLocal am;
	
	public String bidPro() throws Exception
	{
		
		Map<String , Object> session = FacesContext
			.getCurrentInstance()
			.getExternalContext()
			.getSessionMap();
			
		Integer userId = (Integer)session.get("userId");
		String ver2 = (String)session.get("rand");
		session.put("rand" , null);
		
		if (vercode.equalsIgnoreCase(ver2))
		{
			if(bidPrice <= getMaxPrice())
			{
				setTipInfo("您输入的竞价必须高于当前最高价！");
				return "input";
			}
			am.addBid(getItemId() , bidPrice ,userId);  
			setTipInfo("竞价成功！");
			return "success";
		}
		else
		{
			setTipInfo("验证码不匹配,请重新输入");
			return "input";
		}
	}
	
	public void setItemId(int itemId)
	{
		this.itemId = itemId;
	}
	public int getItemId()
	{
		Map<String , String> request = FacesContext
			.getCurrentInstance()
			.getExternalContext()
			.getRequestParameterMap();
		return Integer.parseInt(request.get("itemId"));
	}
	
	public void setBidPrice(double bidPrice)
	{
		this.bidPrice = bidPrice;
	}
	public double getBidPrice()
	{
		return this.bidPrice;
	}
	
	public void setMaxPrice(double maxPrice)
	{
		this.maxPrice = maxPrice;
	}
	public double getMaxPrice()
	{
		Map<String , String> request = FacesContext
			.getCurrentInstance()
			.getExternalContext()
			.getRequestParameterMap();
		return Double.parseDouble(request.get("maxPrice"));
	}
	
	public void setVercode(String vercode)
	{
		this.vercode = vercode;
	}
	public String getVercode()
	{
		 return this.vercode;
	}
	
	public void setTipInfo(String tipInfo)
	{
		this.tipInfo = tipInfo;
	}
	public String getTipInfo()
	{
		 return this.tipInfo;
	}
}