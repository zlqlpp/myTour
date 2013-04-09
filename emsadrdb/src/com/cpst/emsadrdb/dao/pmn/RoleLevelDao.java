package com.cpst.emsadrdb.dao.pmn;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cpst.core.orm.hibernate.HibernateDao;
import com.cpst.emsadrdb.entity.pmn.RoleLevel;
@Repository
@Transactional
public class RoleLevelDao extends HibernateDao<RoleLevel,Short>{
	@Transactional(readOnly = true)
	public List<RoleLevel> getAllOrderByPropertyName(String propertyName){
		String hql="from RoleLevel order by "+propertyName;
		return find(hql);
	}
	
	@Transactional(readOnly = true)
	public List<RoleLevel> getRoleLevelByRulLevel(Short rulLevel,String sign){
		String hql="from RoleLevel r where r.rulLevel "+sign+" ?";
		return find(hql,rulLevel);
	}

}
