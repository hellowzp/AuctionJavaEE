package org.crazyit.auction.eao.impl;

import java.util.*;
import javax.persistence.*;

import org.crazyit.auction.eao.Eao;
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
public abstract class CrazyItEao
	implements Eao 
{
	@PersistenceContext 
	private EntityManager em;
	/**
	 * ����ʵ��
	 * @param <T> ��̬����ʵ����
	 * @param entityClass ʵ����
         * @param primaryKey ����
	 * @return ����ָ���������ص�ʵ��
	 */
        @Override
	public <T> T get(Class <T> entityClass, Object primaryKey)
	{
		T obj = em.find(entityClass, primaryKey);
		return obj;
	}
	/**
	 * ����ʵ��
	 * @param entity ��Ҫ�����ʵ��
	 */
        @Override
	public void save(Object entity)
	{
		em.persist(entity);
	}
	/**
	 * ����ʵ��
	 * @param entity ��Ҫ�����ʵ��
	 */
        @Override
	public void update(Object entity)
	{
		em.merge(entity);
	}
	/**
	 * ɾ��ʵ��
	 * @param entityClass ��Ҫɾ��ʵ����
	 * @param primaryKey ��Ҫɾ����ʵ������
	 */
        @Override
	public void delete(Class entityClass, Object primaryKey)
	{
		em.remove(em.getReference(entityClass, primaryKey));
	}
	
        @Override
	public <T> List<T> getResultList(Class<T> entityClass 
		, String whereJpql 
		, LinkedHashMap<String, String> orderBy
		, Object... args)
	{
		//��ȡʵ�����ʵ������Ĭ������£�ʵ������������ͬ
		String entityName = entityClass.getSimpleName();
		//������ѯ
		Query query = em.createQuery("select o from "+ entityName
			+ " as o " + whereJpql + buildOrderby(orderBy));
		//Ϊ��ѯ�ַ����в�������ֵ
		for (int i = 0 ; i < args.length ; i++)
		{
			query.setParameter(i + 1 , args[i]);
		}
		//���ؽ����
		return (List<T>)query.getResultList();
	}
        
        @Override
	public <T> List<T> getResultList(Class<T> entityClass 
		, String whereJpql 
		, int firstResult 
		, int maxResult 
		, LinkedHashMap<String, String> orderBy
		, Object... args)
	{
		//��ȡʵ�����ʵ������Ĭ������£�ʵ������������ͬ
		String entityName = entityClass.getSimpleName();
		//������ѯ
		Query query = em.createQuery("select o from "+ entityName
			+ " as o " + whereJpql + buildOrderby(orderBy));
		//Ϊ��ѯ�ַ����в�������ֵ
		for (int i = 0 ; i < args.length ; i++)
		{
			query.setParameter(i + 1 , args[i]);
		}
		//�Բ�ѯ��������з�ҳ
		query.setMaxResults(maxResult).setFirstResult(firstResult);
		//���ؽ����
		return (List<T>)query.getResultList();
	}
	
	/**
	 * ���������Ӿ�
	 * @param orderby LinkedHashMap����ÿ��key-value��ָ��һ����������
	 */ 
	private static String buildOrderby(LinkedHashMap<String , String>
		 orderby)
	{
		StringBuilder out = new StringBuilder();
		if(orderby != null && orderby.size() > 0)
		{
			//���order by �Ӿ�
			out.append(" order by ");
			//����LinkedHashMap�е�ÿ��key-value�ԣ�
			//ÿ��key-value������һ����������
			for(String key : orderby.keySet())
			{
			out.append("o.").append(key).append(" ").append(orderby.get(key)).append(",");
			}
			out.deleteCharAt(out.length()-1);
		}
		return out.toString();
	}
}
