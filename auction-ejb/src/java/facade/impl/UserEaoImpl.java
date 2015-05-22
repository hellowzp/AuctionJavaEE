package facade.impl;

import facade.AuctionUserEao;
import domain.User;
import java.util.*;

import javax.ejb.*;


@Stateless(name="auctionUserEao")
public class UserEaoImpl
	extends CrazyItEao implements AuctionUserEao  
{
	/**
	 * 根据用户名，密码查找用户
	 * @param username 查询所需的用户名
	 * @param pass 查询所需的密码
	 * @return 指定用户名、密码对应的用户
	 */
	public User findUserByNameAndPass(String username , String pass)
	{
		List<User> userList = getResultList(User.class 
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