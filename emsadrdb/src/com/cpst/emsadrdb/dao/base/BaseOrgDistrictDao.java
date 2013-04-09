package com.cpst.emsadrdb.dao.base;

import java.util.Iterator;
import java.util.List;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cpst.core.orm.hibernate.HibernateDao;
import com.cpst.emsadrdb.entity.base.BaseOrgDistrict;
import com.cpst.emsadrdb.service.base.BaseOrgDistrictManager;
@Repository
@Transactional
public class BaseOrgDistrictDao extends HibernateDao<BaseOrgDistrict,String>{
	@Autowired
	private Cache baseOrgDistrictCacheBean;
	@Autowired
	private BaseOrgDistrictManager baseOrgDistrictManager;
	@Transactional(readOnly = true)
	public List<BaseOrgDistrict> getProvinces() {
		String hql = "from BaseOrgDistrict o where o.degree='1' order by o.districtCode";
		return find(hql);
	}
	
	@Transactional(readOnly = true)
	public BaseOrgDistrict getProvinceByProvinceName(String provinceName) {
		String hql = "from BaseOrgDistrict o where o.degree='1' and o.provinceName=?";
		List<BaseOrgDistrict> provinces = find(hql,provinceName);
		return provinces.get(0);
	}
	
	@Transactional(readOnly = true)
	public List<BaseOrgDistrict> getCitysByProvince(String degree,String provinceName) {
		String hql = "from BaseOrgDistrict o where o.degree=? and o.provinceName=? order by o.districtCode";
		return find(hql,degree,provinceName);
	}
	
	@Transactional(readOnly = true)
	public List<BaseOrgDistrict> getAllBaseOrgDistricts() {
		String hql="from BaseOrgDistrict";
		return find(hql);
	}
	public void initCache() {
		synchronized (this) {
			Iterator<BaseOrgDistrict> it = getAllBaseOrgDistricts().iterator();
			while (it.hasNext()) {
				BaseOrgDistrict orgDistrict = it.next();
				if(orgDistrict.getDegree().equals("1")&&baseOrgDistrictManager.isMunicipalities(orgDistrict.getProvinceName())){
					orgDistrict.setCityName(orgDistrict.getProvinceName());
				}
				putBaseOrgDistrictInCache(orgDistrict);
			}
		}
	}
	public void putBaseOrgDistrictInCache(BaseOrgDistrict orgDistrict) {
		Element element = new Element(orgDistrict.getDistrictCode(), orgDistrict);
		if(baseOrgDistrictCacheBean.get(orgDistrict.getDistrictCode())!=null){
			baseOrgDistrictCacheBean.remove(orgDistrict.getDistrictCode());
		}
		baseOrgDistrictCacheBean.put(element);	
	}

}
