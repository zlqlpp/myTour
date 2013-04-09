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
@Repository
@Transactional
public class DepartmentDao extends HibernateDao<Department,Long>{
	@Autowired
	private DistrictDao districtDao;
	@Autowired
	private Cache departmentCacheBean;
	
	@Transactional(readOnly = true)
	public List<Department> getAll(){
		String hql="from Department";
		return find(hql);
	}
	public int saveDepartment(Department department,String dtPkId){
		int code=1;//成功
		District d=districtDao.get(dtPkId);
		if(d!=null){
			d.getDepartments().add(department);
			department.setDistrict(d);
			save(department);
			putDepartmentInCache(department);
		}else{
			code=0;//失败
		}
		return code;
	}
	
	public void deleteDepartment(Department department){
		delete(department);
	}
	@Transactional(readOnly = true)
	public List<Department> getDepartments(String districtId){
		String hql="from Department d where d.district.dtPkCode=?";
		return find(hql, districtId);
	}
	@Transactional(readOnly = true)
	public void initCache() {
		synchronized (this) {
			Iterator<Department> it = getAll().iterator();
			while (it.hasNext()) {
				Department department = it.next();
				putDepartmentInCache(department);
			}
		}
	}
	@Transactional(readOnly = true)
	public void putDepartmentInCache(Department department) {
		Element element = new Element(department.getDmPkCode(), department);
		if(departmentCacheBean.get(department.getDmPkCode())!=null){
			departmentCacheBean.remove(department.getDmPkCode());
		}
		departmentCacheBean.put(element);	
	}
	@Transactional(readOnly = true)
	public void removeDepartmentFromCache(Department department) {
		if(departmentCacheBean.get(department.getDmPkCode())!=null){
			departmentCacheBean.remove(department.getDmPkCode());
		}
	}

}
