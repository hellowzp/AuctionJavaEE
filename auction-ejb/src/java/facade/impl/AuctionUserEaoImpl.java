package facade.impl;

import facade.AuctionUserEao;
import domain.AuctionUser;
import java.util.*;

import javax.ejb.*;


@Stateless(name="auctionUserEao")
public class AuctionUserEaoImpl
	extends CrazyItEao implements AuctionUserEao  
{
	/**
	 * 根据用户名，密码查找用户
	 * @param username 查询所需的用户名
	 * @param pass 查询所需的密码
	 * @return 指定用户名、密码对应的用户
	 */
	public AuctionUser findUserByNameAndPass(String username , String pass)
	{
		List<AuctionUser> userList = getResultList(AuctionUser.class 
			, "where o.username = ?1 and o.userpass=?2"
			, null
			, username , pass);
		if (userList != null && userList.size() > 0)
		{
			return userList.get(0);
		}
		return null;
	}
}