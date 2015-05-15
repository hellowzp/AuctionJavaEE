package jsf;

import domain.Item;
import java.util.*;
import javax.ejb.*;

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
public class ViewFailAction
{
	private List<Item> failItems;
	//依赖注入业务逻辑组件（Session Bean）
	@EJB(beanName="auctionManager")
	private AuctionManager am;

	//failItems属性的setter和getter方法
	public void setFailItems(List<Item> failItems)
	{
		this.failItems = failItems;
	}
	public List<Item> getFailItems()
	{
		//调用业务逻辑方法初始化failItems属性
		return am.getFailItems();
	}
}