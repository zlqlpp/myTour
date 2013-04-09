package com.cpst.emsadrdb.dao.wh;

import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.List;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cpst.core.orm.hibernate.HibernateDao;
import com.cpst.emsadrdb.entity.wh.District;
@Repository
@Transactional
public class DistrictDao extends HibernateDao<District,String>{
	@Autowired
	private Cache districtCacheBean;
	@Transactional(readOnly = true)
	public List<District> getAll(){
		String hql="from District";
		return find(hql);
	}
	@Transactional(readOnly = true)
	public List<District> getDistrictsAndShareDistrictsByCity(String cityId){
		String hql="from District d left join fetch d.shareDistricts where d.dtCityId=? order by d.dtPkCode";
		return find(hql,cityId);
	}
	@Transactional(readOnly = true)
	public List<District> getDistrictsByCityAndDepartment(String cityId,String haveDepartment){
		String hql="from District d where d.dtCityId=? and d.dtHaveDepartment=? order by d.dtPkCode";
		return find(hql,cityId,haveDepartment);
	}
	@Transactional(readOnly = true)
	public List<District> getDistrictsByCity(String cityId){
		String hql="from District d where d.dtCityId=? order by d.dtPkCode";
		return find(hql,cityId);
	}
	
	public void saveDistrict(District district){
		String dtProvinceCode=district.getDtCityId().substring(0, 2);
		String dtCityCode=district.getDtCityId().substring(2, 4);
		if(district.getDtPkCode()==null){//新增
			String hql ="select max(d.dtSno) from District d where d.dtProvinceCode=? and d.dtCityCode=?";
			List<Short> dtSnos=find(hql, dtProvinceCode,dtCityCode);
			Short dtSno=dtSnos.get(0);
			if(dtSno==null){
				dtSno=1;
			}else{
				dtSno=((Integer)(dtSno+1)).shortValue();
			}
			DecimalFormat df = new DecimalFormat("000");
			String fsno=df.format(dtSno);
			district.setDtPkCode(dtProvinceCode+dtCityCode+fsno);
			district.setDtSno(dtSno.shortValue());
		}else{
			String s="delete from ShareDistrict where district.dtPkCode=? ";
			batchExecute(s, district.getDtPkCode());
			district.setDtSno((Short.parseShort(district.getDtPkCode().substring(4, 7))));
		}
		district.setDtProvinceCode(dtProvinceCode);
		district.setDtCityCode(dtCityCode);
		save(district);
		putDistrictInCache(district);
	}
	
	public void deleteDistrict(District district){
		delete(district);
	}
	@Transactional(readOnly = true)
	public void initCache() {
		synchronized (this) {
			Iterator<District> it = getAll().iterator();
			while (it.hasNext()) {
				District district = it.next();
				putDistrictInCache(district);
			}
		}
	}
	@Transactional(readOnly = true)
	public void putDistrictInCache(District district) {
		Element element = new Element(district.getDtPkCode(), district);
		if(districtCacheBean.get(district.getDtPkCode())!=null){
			districtCacheBean.remove(district.getDtPkCode());
		}
		districtCacheBean.put(element);	
	}
	@Transactional(readOnly = true)
	public void removeDistrictFromCache(District district) {
		if(districtCacheBean.get(district.getDtPkCode())!=null){
			districtCacheBean.remove(district.getDtPkCode());
		}
	}

}
