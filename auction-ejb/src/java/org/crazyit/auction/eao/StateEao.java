package org.crazyit.auction.eao;

import java.util.List;
import javax.ejb.*;

import org.crazyit.auction.model.*;
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
@Local
public interface StateEao
	extends Eao
{
	/**
	 * ��ѯȫ��״̬
	 * @return ���ȫ��״̬
	 */ 
	List<State> findAll();
}
