package com.cpst.emsadrdb.dao.wh;

import java.util.Iterator;
import java.util.List;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cpst.core.orm.hibernate.HibernateDao;
import com.cpst.emsadrdb.entity.wh.Department;
import com.cpst.emsadrdb.entity.wh.District;
import com.cpst.emsadrdb.entity.wh.Postseg;

@Repository
@Transactional
public class PostsegDao extends HibernateDao<Postseg, Long> {
	@Autowired
	private DistrictDao districtDao;
	@Autowired
	private DepartmentDao departmentDao;
	
	@Autowired
	private Cache postsegCacheBean;
	
	@Transactional(readOnly = true)
	public List<Postseg> getAll(){
		String hql="from Postseg";
		return find(hql);
	}

	@Transactional(readOnly = true)
	public List<Postseg> getPostsegsByDepartmentId(Long departmentId) {
		String hql = "from Postseg where dmPkCode=? order by pgPkCode";
		return find(hql, departmentId);
	}
	@Transactional(readOnly = true)
	public List<Postseg> getPostsegsByDistrictId(String districtId) {
		String hql = "from Postseg where district.dtPkCode=? order by pgPkCode";
		return find(hql, districtId);
	}

	public int savePostseg(Postseg postseg, String dtPkId, Long dmPkId) {
		int code = 1;// 成功
		District d = districtDao.get(dtPkId);
		if (d != null) {
			d.getPostsegs().add(postseg);
			if (dmPkId != null && !dmPkId.toString().equals("")) {
				Department dm = departmentDao.get(dmPkId);
				if (dm == null) {
					code = 0;// 失败
					return code;
				} else {
					postseg.setDmPkCode(dmPkId);
				}
			}
			postseg.setDistrict(d);
			save(postseg);
			putPostsegInCache(postseg);
		} else {
			code = 0;// 失败
		}
		return code;
	}

	public void deletePostseg(Postseg postseg) {
		delete(postseg);
	}

	public void changDisrict(String districtId, String postsegIds) {
		String hql = "update Postseg set district.dtPkCode=? where pgPkCode in(?)";
		batchExecute(hql, districtId, postsegIds);
	}

	public void changDepartment(Long departmentId, String postsegIds) {
		String hql = "update Postseg set dmPkCode=? where pgPkCode in("+postsegIds+")";
		batchExecute(hql, departmentId);
	}
	
	@Transactional(readOnly = true)
	public void initCache() {
		synchronized (this) {
			Iterator<Postseg> it = getAll().iterator();
			while (it.hasNext()) {
				Postseg postseg = it.next();
				putPostsegInCache(postseg);
			}
		}
	}
	@Transactional(readOnly = true)
	public void putPostsegInCache(Postseg postseg) {
		Element element = new Element(postseg.getPgPkCode(), postseg);
		if(postsegCacheBean.get(postseg.getPgPkCode())!=null){
			postsegCacheBean.remove(postseg.getPgPkCode());
		}
		postsegCacheBean.put(element);	
	}
	@Transactional(readOnly = true)
	public void removePostsegFromCache(Postseg postseg) {
		if(postsegCacheBean.get(postseg.getPgPkCode())!=null){
			postsegCacheBean.remove(postseg.getPgPkCode());
		}
	}

}
