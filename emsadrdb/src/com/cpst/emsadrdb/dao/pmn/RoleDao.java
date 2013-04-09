package com.cpst.emsadrdb.dao.pmn;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cpst.core.orm.hibernate.HibernateDao;
import com.cpst.emsadrdb.entity.pmn.Role;

@Repository
@Transactional
public class RoleDao extends HibernateDao<Role,Long>{
	@Transactional(readOnly = true)
	public List<Role> getAllOrderByPropertyName(String propertyName){
		String hql="from Role order by "+propertyName;
		return find(hql);
	}
	
	@Transactional(readOnly = true)
	public List<Role> getRolesByRulLevel(Short rulLevel,String sign){
		String hql="from Role r where r.rulLevel "+sign+" ? ";
		return find(hql,rulLevel);
	}
	
	@Transactional(readOnly = true)
	public List<Role> getRolesIds(String ids){
		String hql="from Role r where r.rePkId in ("+ids+")";
		return find(hql);
	}
	/*@Transactional(readOnly = true)
	public List<Role> getRolesByUsPkId(Long usPkId){
		String sql="select re_pk_id from CP_PMN_RL_USER_ROLE where us_pk_id="+usPkId;
		List<Long> ids=getSession().createSQLQuery(sql).list();
		String rePkIds="";
		if(ids!=null && !ids.isEmpty()){
			rePkIds+=","+ids.get(0);
		}
		if(rePkIds.length()>0){
			rePkIds=rePkIds.substring(1);
			String hql="from Role r where r.rePkId in ("+rePkIds+")";
			return find(hql);
		}else{
			return new ArrayList<Role>();
		}
	}*/
	/*@Transactional(readOnly = true)
	public List<Role> getRolesByRsPkId(Long rsPkId){
		String sql="select re_pk_id from CP_PMN_RL_ROLE_RES where rs_pk_id="+rsPkId;
		List<Long> ids=getSession().createSQLQuery(sql).list();
		String rePkIds="";
		if(ids!=null && !ids.isEmpty()){
			rePkIds+=","+ids.get(0);
		}
		if(rePkIds.length()>0){
			rePkIds=rePkIds.substring(1);
			String hql="from Role r where r.rePkId in ("+rePkIds+")";
			return find(hql);
		}else{
			return new ArrayList<Role>();
		}
	}*/
	
	
	public void saveRole(Role role){
		save(role);
	}
	public void deleteRoleById(Long rePkId){
		delete(rePkId);
	}

}
