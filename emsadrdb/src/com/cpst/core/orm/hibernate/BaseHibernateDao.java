package com.cpst.core.orm.hibernate;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.hibernate.metadata.ClassMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.cpst.core.utils.ReflectionUtils;
/**
 * hibernate基类
 */
@SuppressWarnings("unchecked")
public class BaseHibernateDao<T, PK extends Serializable> {
	
	protected Logger logger = LoggerFactory.getLogger(getClass());

	protected SessionFactory sessionFactory;

	protected Class<T> entityClass;

	/**
	 * 确保初始化entityClass，并取得值为Class<T>
	 */
	public BaseHibernateDao() {
		this.entityClass = ReflectionUtils.getSuperClassGenricType(getClass());
	}

	/**
	 * 另一个构造函数，sessionFactory需要自行定义
	 */
	public BaseHibernateDao(final SessionFactory sessionFactory, final Class<T> entityClass) {
		this.sessionFactory = sessionFactory;
		this.entityClass = entityClass;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	/**
	 * 通过spring注解注入sessionFactory
	 */
	@Autowired
	public void setSessionFactory(final SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	/**
	 * 取得当前Session.
	 */
	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	/**
	 * 保存新增或修改的对象.
	 */
	public void save(final T entity) {
		getSession().saveOrUpdate(entity);
	}

	/**
	 * 删除对象.
	 * 
	 */
	public void delete(final T entity) {
		getSession().delete(entity);
	}

	/**
	 * 按id删除对象.
	 */
	public void delete(final PK id) {
		delete(get(id));
	}

	/**
	 * 按id获取对象.
	 */
	public T get(final PK id) {
		return (T) getSession().get(entityClass, id);
		//return (T) getSession().load(entityClass, id);
	}
	
	/**
	 * 既然用到hibernate，就用用对象查询
	 * @param criterions
	 * @return criteria
	 */
	public Criteria createCriteria(final Criterion... criterions) {
		Criteria criteria = getSession().createCriteria(entityClass);
		for (Criterion c : criterions) {
			criteria.add(c);
		}
		return criteria;
	}
	/**
	 * 返回查询列表
	 */
	public List<T> find(final Criterion... criterions) {
		return createCriteria(criterions).list();
	}
	
	/**
	 * 按Criteria查询唯一对象.
	 * 
	 * @param criterions 数量可变的Criterion.
	 */
	public T findUnique(final Criterion... criterions) {
		return (T) createCriteria(criterions).uniqueResult();
	}
	
	/**
	 * 按属性查找对象列表,匹配方式为相等
	 */
	public List<T> findBy(final String propertyName, final Object value) {
		Criterion criterion = Restrictions.eq(propertyName, value);
		return find(criterion);
	}
	
	
	
	/**
	 * 按id列表获取对象.
	 */
	public List<T> findByIds(List<PK> ids) {
		return find(Restrictions.in(getIdName(), ids));
	}
	
	/**
	 * 取得对象的主键名.
	 */
	public String getIdName() {
		ClassMetadata meta = getSessionFactory().getClassMetadata(entityClass);
		return meta.getIdentifierPropertyName();
	}
	
	/**
	 * 最为顺手的HQL，按参数顺序绑定
	 */
	public Query createQuery(final String queryString, final Object... values) {
		Query query = getSession().createQuery(queryString);
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				query.setParameter(i, values[i]);
			}
		}
		return query;
	}
	
	/**
	 * 按HQL查询对象列表.
	 * 按顺序绑定.
	 */
	public <X> List<X> find(final String hql, final Object... values) {
		return createQuery(hql, values).list();
	}
	
	/**
	 * 按HQL查询唯一对象.
	 * 按顺序绑定.
	 */
	public <X> X findUnique(final String hql, final Object... values) {
		return (X) createQuery(hql, values).uniqueResult();
	}
	
	/**
	 * HQL按名称绑定.
	 */
	public Query createQuery(final String queryString, final Map<String, Object> values) {
		Query query = getSession().createQuery(queryString);
		if (values != null) {
			query.setProperties(values);
		}
		return query;
	}
	
	/**
	 * 按HQL查询对象列表.
	 * 按名称绑定.
	 */
	public <X> List<X> find(final String hql, final Map<String, Object> values) {
		return createQuery(hql, values).list();
	}
	
	/**
	 * 按HQL查询唯一对象.
	 * 按名称绑定.
	 */
	public <X> X findUnique(final String hql, final Map<String, Object> values) {
		return (X) createQuery(hql, values).uniqueResult();
	}
	
	/**
	 * 执行HQL进行批量修改/删除操作.
	 */
	public int batchExecute(final String hql, final Object... values) {
		return createQuery(hql, values).executeUpdate();
	}

	/**
	 * 执行HQL进行批量修改/删除操作.
	 * @return 更新记录数.
	 */
	public int batchExecute(final String hql, final Map<String, Object> values) {
		return createQuery(hql, values).executeUpdate();
	}
	
	/**
	 * 执行SQL进行批量修改/删除操作.
	 */
	public int batchSqlExecute(final String sql) {
		return getSession().createSQLQuery(sql).executeUpdate();
	}

}
