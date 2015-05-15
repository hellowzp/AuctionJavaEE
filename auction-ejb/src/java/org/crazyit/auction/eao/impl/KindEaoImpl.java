package org.crazyit.auction.eao.impl;

import java.util.*;

import javax.ejb.*;

import org.crazyit.auction.model.*;
import org.crazyit.auction.eao.*;

/**
 * Description:
 * <br/>��վ: <a href="http://www.crazyit.org">���Java����</a> 
 * <br/>Copyright (C), 2001-2010, Yeeku.H.Lee
 * <br/>This program is protected by copyright laws.
 * <br/>Program Name:
 * <br/>Date:
 * @author  Yeeku.H.Lee kongyeeku@163.com
 * @version  1.0
 */
@Stateless(name="kindEao")
public class KindEaoImpl
	extends CrazyItEao implements KindEao  
{

	/**
	 * ��ѯȫ������
	 * @return ���ȫ������
	 */
	public List<Kind> findAll()
	{
		return getResultList(Kind.class 
			, "" , null);
	}
}