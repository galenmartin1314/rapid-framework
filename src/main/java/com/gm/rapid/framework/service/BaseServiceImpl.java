package com.gm.rapid.framework.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.gm.rapid.framework.orm.dao.BaseDAO;

public abstract class BaseServiceImpl<T> implements BaseService<T> {
	protected abstract BaseDAO<T> getDAO(String who);
	public boolean save(T obj) {
		return getDAO("default").save(obj);
	}


	/**
	 * 删除对象
	 */
	public boolean delete(T obj) {
		return getDAO("default").delete(obj);
	}

	/**
	 * 根据Id删除对象
	 */
	public boolean deleteById(Serializable id) {
		return getDAO("default").deleteById(id);
	}

	/**
	 * 修改对象
	 */
	public boolean update(T obj) {
		return getDAO("default").update(obj);
	}

	/**
	 * 查询对象
	 */
	public T find(final String hql) {
		return getDAO("default").find(hql);
	}

	/**
	 * 查询对象(防止依赖注入)
	 */
	public T findOfMap(final String hql, Map<Serializable, Serializable> map) {
		return getDAO("default").findOfMap(hql, map);
	}

	/**
	 * 主要用于防止sql依赖注入
	 */
	public List<T> findListOfMap(final String hql,
			final Map<Serializable, Serializable> map) {
		return getDAO("default").findListOfMap(hql, map);
	}

	/**
	 * 根据id查询对象
	 */
	public T findById(Serializable id) {

		return getDAO("default").findById(id);

	}

	/**
	 * 根据id查询缓存对象
	 */
	public T findByIdLoad(Serializable id) {
		return getDAO("default").findByIdLoad(id);
	}

	/**
	 * 查询集合
	 */
	public List<T> findList(String hql) {
		return getDAO("default").findList(hql);
	}

	/**
	 * 根据页数和条数查询集合
	 */
	public List<T> findList(String hql, int page, int count) {
		return getDAO("default").findList(hql,page,count);

	}

	/**
	 * 根据hql查找集合的条数
	 */
	public int findCountByHql(String hql) {
		return getDAO("default").findCountByHql(hql);
	}
	
	/**
	 * 根据hql查询最大值
	 */
	public Object maxByHql(String hql) {
		// TODO Auto-generated method stub
		return getDAO("default").maxByHql(hql);
	}

	/**
	 * 根据hql查询最小值
	 */
	public Object minByHql(String hql) {
		// TODO Auto-generated method stub
		return getDAO("default").minByHql(hql);
	}
	

	// -------------------sql语句-----------------------//

	/**
	 * sql語句操作數據庫
	 */
	public boolean executeSql(String sql) {
		return getDAO("default").executeSql(sql);
	}

	/**
	 * sql語句查询集合
	 */
	public List findListBySql(final String sql, Class className) {
		return getDAO("default").findListBySql(sql,className);
	}
}
