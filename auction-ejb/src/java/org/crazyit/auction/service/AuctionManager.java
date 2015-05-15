package org.crazyit.auction.service;

import java.util.List;
import javax.ejb.*;

import org.crazyit.auction.exception.AuctionException;
import org.crazyit.auction.model.*;


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
@Local
public interface AuctionManager  
{
	/**
	 * 根据赢取者查询物品
	 * @param winerId 赢取者的ID
	 * @return 赢取者获得的全部物品
	 */
	List<Item> getItemByWiner(Integer winerId) 
		throws AuctionException;

	/**
	 * 查询流拍的全部物品
	 * @return 全部流拍物品
	 */
	List<Item> getFailItems()throws AuctionException;

	/**
	 * 根据用户名，密码验证登录是否成功
	 * @param username 登录的用户名
 	 * @param pass 登录的密码
	 * @return 登录成功返回用户ID，否则返回-1
	 */
	int validLogin(String username , String pass)
		throws AuctionException;

	/**
	 * 查询用户的全部出价
	 * @param userId 竞价用户的ID
	 * @return 用户的全部出价
	 */
	List<Bid> getBidByUser(Integer userId)
		throws AuctionException;

	/**
	 * 根据用户查找目前仍在拍卖中的全部物品
	 * @param userId 所属者的ID
	 * @return 属于当前用户的、处于拍卖中的全部物品。
	 */
	List<Item> getItemsByOwner(Integer userId)
		throws AuctionException;

	/**
	 * 查询全部种类
	 * @return 系统中全部全部种类
	 */
	List<Kind> getAllKind() throws AuctionException; 

	/**
	* 添加物品
	* @param name 物品名称
	* @param desc 物品描述
	* @param remark 物品备注
	* @param avail 有效天数
	* @param kind 物品种类
	* @param userId 添加者的ID
	* @return 新增物品的主键
	*/ 
	int addItem(String name , String desc , String remark , 
		double initPrice , int avail , int kind , Integer userId) 
		throws AuctionException;

	/**
	 * 添加种类
	 * @param name 种类名称
	 * @param desc 种类描述
	 * @return 新增种类的主键
	 */ 
	int addKind(String name , String desc) throws AuctionException;

	/**
	 * 根据产品分类，获取处于拍卖中的全部物品
	 * @param kindId 种类id;
	 * @return 该类的全部产品
	 */
	List<Item> getItemsByKind(int kindId) throws AuctionException;

	/**
	 * 根据种类id获取种类名
	 * @param kindId 种类id;
	 * @return 该种类的名称
	 */
	String getKind(int kindId) throws AuctionException;

	/**
	 * 根据物品id，获取物品
	 * @param itemId 物品id;
	 * @return 指定id对应的物品
	 */
	Item getItem(int itemId) throws AuctionException;

	/**
	 * 增加新的竞价，并对竞价用户发邮件通知
	 * @param itemId 物品id;
	 * @param bidPrice 竞价价格
	 * @param userId 竞价用户的ID
	 * @return 返回新增竞价记录的ID
	 */
	int addBid(int itemId , double bidPrice ,Integer userId)
		throws AuctionException;

	/**
	 * 根据时间来修改物品的赢取者
	 */
	void updateWiner()
		throws AuctionException;
}