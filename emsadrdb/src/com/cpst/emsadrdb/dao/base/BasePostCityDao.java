package com.cpst.emsadrdb.dao.base;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cpst.core.orm.hibernate.HibernateDao;
import com.cpst.emsadrdb.entity.base.BasePostCity;
import com.cpst.emsadrdb.entity.base.BasePostCityId;
@Repository
@Transactional
public class BasePostCityDao extends HibernateDao<BasePostCity,BasePostCityId>{
	
	@Transactional(readOnly = true)
	public List<BasePostCity> getBasePostCityByCityId(String cityId){
		String hql="from BasePostCity where id.cityCode=? order by id.postCode";
		return find(hql, cityId);
	}

}
