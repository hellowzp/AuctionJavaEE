package jsf;

import domain.Item;
import java.util.*;
import javax.ejb.*;
import javax.faces.context.FacesContext;

import service.AuctionManager;
import exception.AuctionException;

/**
 * Description:
 * <br/>网站: <a href="http://www.crazyit.org">疯狂Java联盟</a> 
 * <br/>Copyright (C), 2001-2010, Yeeku.H.Lee
 * <br/>This program is protected by copyright laws.
 * <br/>Program Name:
 * <br/>Date:
 * @author  Yeeku.H.Lee kongyeeku@163.com
 * @version  1.0
 */
public class ViewSuAction
{
	private List<Item> items;
	//依赖注入业务逻辑组件（Session Bean）
	@EJB(beanName="auctionManager")
	private AuctionManager am;
	public void setItems(List<Item> items)
	{
		this.items = items;
	}
	public List<Item> getItems()
	{
		//在JSF中访问Session范围的数据
		Map<String , Object> session = FacesContext
			.getCurrentInstance()
			.getExternalContext()
			.getSessionMap();
		Integer userId = (Integer)session.get("userId");
		return am.getItemByWiner(userId);
	}
}