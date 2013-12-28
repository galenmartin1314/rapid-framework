package com.gm.rapid.framework.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface BaseService<T> {
	/**
	 * 保存对象
	 * @param obj
	 * @return
	 */
	public boolean save(T obj);

	/**
	 * 删除对象
	 * @param obj
	 * @return
	 */
	public boolean delete(T obj);

	/**
	 * 删除对象
	 * @param id
	 * @return
	 */
	public boolean deleteById(Serializable id);

	/**
	 * 修改对象
	 * @param obj
	 * @return
	 */
	public boolean update(T obj) ;

	/**
	 * 查询对象
	 * @param hql
	 * @return
	 */
	public T find(final String hql);
	
	/**
	 * 查询对象(防止依赖注入)
	 * @param hql
	 * @param map
	 * @return
	 */
	public T findOfMap(final String hql,Map<Serializable,Serializable> map);
	
	/**
	 * 主要用于防止sql依赖注入
	 * @param hql
	 * @param map
	 * @return
	 */
	public List<T> findListOfMap(final String hql,final Map<Serializable, Serializable> map);

	/**
	 * 根据id查询对象
	 * @param id
	 * @return
	 */
	public T findById(Serializable id) ;
	
	/**
	 * 根据id查询缓存对象
	 * @param id
	 * @return
	 */
	public T findByIdLoad(Serializable id);

	/**
	 * 查询集合
	 * @param hql
	 * @return
	 */
	public List<T> findList(String hql);

	/**
	 * 根据页数和条数查询集合
	 * @param hql
	 * @param page
	 * @param count
	 * @return
	 */
	public List<T> findList(String hql, int page, int count);

	/**
	 * 根据hql查找集合的条数
	 * @param hql
	 * @return
	 */
	public int findCountByHql(String hql) ;
	
	/**
	 * 根据Hql查询最大值
	 * @param hql
	 * @return
	 */
	public Object maxByHql(String hql);
	
	/**
	 * 根据Hql查询最小值
	 * @param hql
	 * @return
	 */
	public Object minByHql(String hql);

	//-------------------sql语句-----------------------//
	/**
	 * sql語句操作數據庫
	 * @param sql
	 * @return
	 */
	public boolean executeSql(String sql) ;
	
	/**
	 * sql語句查询集合
	 * @param sql
	 * @param className
	 * @return
	 */
	public List findListBySql(final String sql,Class className);
}
