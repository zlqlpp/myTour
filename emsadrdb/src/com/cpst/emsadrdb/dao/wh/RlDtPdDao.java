package com.cpst.emsadrdb.dao.wh;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cpst.core.orm.hibernate.HibernateDao;
import com.cpst.emsadrdb.entity.wh.RlDtPd;
import com.cpst.emsadrdb.entity.wh.RlDtPdId;

@Repository
@Transactional
public class RlDtPdDao extends HibernateDao<RlDtPd, RlDtPdId>{
	@Transactional(readOnly = true)
	public List<RlDtPd> getRlDtPdsByDtPkCode(String dtPkCode){
		String hql="from RlDtPd where id.dtPkCode=?";
		return find(hql, dtPkCode);
	}
	
	public void saveBatch(String dtPkCode,String ptPkIds){
		String deleteSql="delete from CP_WH_RL_DT_PD where DT_PK_CODE="+dtPkCode;
		String sql="INSERT INTO CP_WH_RL_DT_PD ";
		sql+="SELECT ";
		sql+=dtPkCode+",post_code from CP_BASE_POST_CITY where post_code in("+ptPkIds+")";
		Session session=getSession();
		session.createSQLQuery(deleteSql).executeUpdate();
		session.createSQLQuery(sql).executeUpdate();
	}

}
