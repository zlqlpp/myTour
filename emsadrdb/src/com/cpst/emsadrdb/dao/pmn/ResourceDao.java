package com.cpst.emsadrdb.dao.pmn;

import java.util.Iterator;
import java.util.List;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cpst.core.orm.hibernate.HibernateDao;
import com.cpst.emsadrdb.entity.pmn.Resource;
@Repository
@Transactional
public class ResourceDao extends HibernateDao<Resource,Long>{
	@Autowired
	private Cache resourceCacheBean;
	@Transactional(readOnly = true)
	public List<Resource> getAllOrderByPropertyName(String propertyName){
		String hql="from Resource order by "+propertyName;
		return find(hql);
	}
	
	public void saveResource(Resource resource){
		save(resource);
	}
	
	public int deleteResourcesByrsPkIds(String rsPkIds){
		String hql="delete from Resource where rsPkId in("+rsPkIds+")";
		return batchExecute(hql);
	}
	@Transactional(readOnly = true)
	public List<Resource> getAllResourcesAndRoles() {
		String hql = "from Resource r left join fetch r.roles rs";
		return find(hql);
	}
	@Transactional(readOnly = true)
	public void initCache() {
		synchronized (this) {
			Iterator<Resource> it = getAllResourcesAndRoles().iterator();
			while (it.hasNext()) {
				Resource resource = it.next();
				putResourceInCache(resource);
			}
		}
	}
	@Transactional(readOnly = true)
	public void putResourceInCache(Resource resource) {
		Element element = new Element(resource.getRsPkId(), resource);
		if(resourceCacheBean.get(resource.getRsPkId())!=null){
			resourceCacheBean.remove(resource.getRsPkId());
		}
		resourceCacheBean.put(element);	
	}
	@Transactional(readOnly = true)
	public void removeResourceFromCache(Resource resource) {
		if(resourceCacheBean.get(resource.getRsPkId())!=null){
			resourceCacheBean.remove(resource.getRsPkId());
		}
	}

}
