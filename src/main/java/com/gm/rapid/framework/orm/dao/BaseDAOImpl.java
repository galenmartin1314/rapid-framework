package com.gm.rapid.framework.orm.dao;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.SessionFactoryUtils;

public abstract class BaseDAOImpl<T> implements BaseDAO<T> {
	@Autowired
	protected SessionFactory sessionFactory;
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	private Class<T> clazz;
	
	public BaseDAOImpl(){
		clazz = (Class<T>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
	}

	/**
	 * 保存对象
	 */
	public boolean save(T obj) {
		boolean flag = false;
		try{
			sessionFactory.getCurrentSession().save(obj);
			sessionFactory.getCurrentSession().flush();
			flag = true;
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return flag;
	}


	/**
	 * 删除对象
	 */
	public boolean delete(T obj) {
		boolean flag = false;
		try{
			sessionFactory.getCurrentSession().delete(obj);
			sessionFactory.getCurrentSession().flush();
			flag = true;
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return flag;
	}

	/**
	 * 根据Id删除对象
	 */
	public boolean deleteById(Serializable id) {
		boolean flag = false;
		try{
			sessionFactory.getCurrentSession().delete(sessionFactory.getCurrentSession().get(clazz, id));
			sessionFactory.getCurrentSession().flush();
			flag = true;
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return flag;
	}

	/**
	 * 修改对象
	 */
	public boolean update(T obj) {
		boolean flag = false;
		try{
			sessionFactory.getCurrentSession().update(obj);
			sessionFactory.getCurrentSession().flush();
			flag = true;
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return flag;
	}

	/**
	 * 查询对象
	 */
	public T find(final String hql) {
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setFirstResult(0);
		query.setMaxResults(1);
		return (T) query.uniqueResult();
	}

	/**
	 * 查询对象(防止依赖注入)
	 */
	public T findOfMap(final String hql, Map<Serializable, Serializable> map) {
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		for (Serializable key : map.keySet()) {
			query.setParameter((String) key, map.get(key));
		}
		query.setFirstResult(0);
		query.setMaxResults(1);
		return (T) query.uniqueResult();
	}

	/**
	 * 主要用于防止sql依赖注入
	 */
	public List<T> findListOfMap(final String hql,
			final Map<Serializable, Serializable> map) {
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		for (Serializable key : map.keySet()) {
			query.setParameter((String) key, map.get(key));
		}
		return query.list();
	}

	/**
	 * 根据id查询对象
	 */
	public T findById(Serializable id) {

		if (null == id || "".equals(id)) {
			return null;

		} else {
			return (T) sessionFactory.getCurrentSession().get(clazz, id);
		}

	}

	/**
	 * 根据id查询缓存对象
	 */
	public T findByIdLoad(Serializable id) {

		if (null == id || "".equals(id)) {
			return null;

		} else {
			return (T) sessionFactory.getCurrentSession().load(clazz, id);
		}

	}

	/**
	 * 查询集合
	 */
	public List<T> findList(String hql) {
		if (null == hql || "".equals(hql)) {
			return null;
		}else{
			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			return query.list();
		}
	}

	/**
	 * 根据页数和条数查询集合
	 */
	public List<T> findList(String hql, int page, int count) {
		if (null == hql || "".equals(hql)) {
			return null;
		}else{
			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			int index = 0;
			int tempPageCount = 0;
			// 如果page为负数,将page设置为1
			if (page < 1) {
				tempPageCount = 1;
			} else {
				tempPageCount = page;
			}
			// 如果为第一页,索引为0
			if (tempPageCount == 1) {
				// 但前页数索引
				index = count;
			} else {
				index = tempPageCount * count;
			}
			// 设置取出的第一条索引
			query.setFirstResult(index - count);
			// 设置取出得最大的索引
			query.setMaxResults(count);
			return query.list();
		}

	}

	/**
	 * 根据hql查找集合的条数
	 */
	public int findCountByHql(String hql) {
		final String sql="select count(*) "+hql;
		return Integer.parseInt(sessionFactory.getCurrentSession().createQuery(sql).list().get(0).toString());
	}
	
	/**
	 * 根据hql查询最大值
	 */
	public Object maxByHql(String hql) {
		// TODO Auto-generated method stub
		final String sql=hql;
		return sessionFactory.getCurrentSession().createQuery(sql).list().get(0);
	}

	/**
	 * 根据hql查询最小值
	 */
	public Object minByHql(String hql) {
		// TODO Auto-generated method stub
		final String sql=hql;
		return sessionFactory.getCurrentSession().createQuery(sql).list().get(0);
	}
	

	// -------------------sql语句-----------------------//

	/**
	 * sql語句操作數據庫
	 */
	public boolean executeSql(String sql) {
		boolean flag = false;
		try{
			sessionFactory.getCurrentSession().createSQLQuery(sql).executeUpdate();
			flag = true;
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return flag;
	}

	/**
	 * sql語句查询集合
	 */
	public List findListBySql(final String sql, Class className) {
		Object obj = null;
		List list = new ArrayList();
		try {
			Connection conn = SessionFactoryUtils.getDataSource(sessionFactory).getConnection();
			Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE); 
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				obj = Class.forName(className.toString().substring(6,className.toString().length())).newInstance();
				for(Field field:obj.getClass().getDeclaredFields()){
					field.setAccessible(true);
					field.set(obj, rs.getObject(field.getName().toString()));
				}
				list.add(obj);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return list;
	}
}
