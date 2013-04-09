package com.cpst.emsadrdb.dao.wh;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cpst.core.orm.hibernate.HibernateDao;
import com.cpst.emsadrdb.entity.wh.ShareDistrict;

@Repository
@Transactional
public class ShareDistrictDao extends HibernateDao<ShareDistrict, Long>{
	
	public List<ShareDistrict> getShareDistrictsByCityId(String cityId){
		String hql="from ShareDistrict s where s.sdCityId=?";
		return find(hql, cityId);
	}

}
