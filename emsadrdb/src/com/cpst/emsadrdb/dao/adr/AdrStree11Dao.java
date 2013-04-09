package com.cpst.emsadrdb.dao.adr;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cpst.core.orm.Page;
import com.cpst.core.orm.PageUtils;
import com.cpst.core.orm.hibernate.HibernateDao;
import com.cpst.emsadrdb.entity.adr.AdrStree11;
import com.cpst.emsadrdb.entity.adr.AdrStree11Id;
import com.cpst.emsadrdb.entity.adr.AdrStreeBean;

/**
 * 全都用SQL查询
 * @author PengYulei
 *
 */
@Repository
@Transactional
@SuppressWarnings("unchecked")
public class AdrStree11Dao extends HibernateDao<AdrStree11,AdrStree11Id>{
	
	@Transactional(readOnly = true)
	public  Page<AdrStreeBean> getAdrStreeBeanByDistrictIdAndProvinceId(Page<AdrStreeBean> page,String districtId,String provinceCode,String adrName,String statFlag){
		String sql="select *";
		sql+=" from CP_MK_ADR_STREET_"+provinceCode;
		sql+=" where 1=1";
		if(adrName!=null && !adrName.trim().equals("")){
			sql+=" and (strt1_name|| strt2_name|| strt2_name|| strt4_name|| strt5_name) like '%"+adrName+"%'";
		}
		sql+=" and strt_id in(";
		sql+=" select strt_id from CP_BASE_POST_STREET_"+provinceCode+" where post_code in(";
		sql+=" select pd_pk_code from CP_WH_RL_DT_PD where dt_pk_code='"+districtId+"'))";
		if(statFlag!=null && !statFlag.equals("")){
			sql+=" and stat_flag='"+statFlag+"'";
		}
		sql+=" order by strt1_name,strt2_name,strt3_name";
		String sqlCount ="select count(*) "+sql.substring(8, sql.length());
		page=getPage(page,sql,sqlCount);
		return page;
	}
	@Transactional(readOnly = true)
	public  Page<AdrStreeBean> getAdrStreeBeanByDistrictIdAndProvinceId(Page<AdrStreeBean> page,String districtId,String provinceCode,String adrName,Long departmentId,Long postsegId){
		String sql="select DISTINCT cp.* ";
		sql+=" from CP_MK_ADR_STREET_"+provinceCode+" cp";
		sql+=" LEFT JOIN CP_WH_RL_PG_ST_"+provinceCode+" ch";
		sql+=" ON cp.strt_id=ch.strt_id ";
		sql+=" where 1=1";
		if(adrName!=null && !adrName.trim().equals("")){
			sql+=" and (cp.strt1_name|| cp.strt2_name|| cp.strt2_name|| cp.strt4_name|| cp.strt5_name) like '%"+adrName+"%'";
		}
		sql+=" AND ch.dt_pk_code='"+districtId+"'";
		if(departmentId!=null){
			sql+=" and ch.dm_pk_code="+departmentId;
		}
		if(postsegId!=null){
			sql+=" and ch.pg_pk_code="+postsegId;
		}
		sql+=" order by cp.strt1_name,cp.strt2_name,cp.strt3_name";
		String sqlCount ="select count(*) from ("+sql+")";
		page=getPage(page,sql,sqlCount);
		return page;
	}
	@Transactional(readOnly = true)
	private Page getPage(Page page,String sql,String sqlcount){
		if (page.isAutoCount()) {
			List listCount=getSession().createSQLQuery(sqlcount).list();
			long totalCount =((BigDecimal)listCount.get(0)).longValue();
			page.setTotalCount(totalCount);
		}
		Query query=getSession().createSQLQuery(sql);
		query.setFirstResult(page.getFirst() - 1);
		query.setMaxResults(page.getPageSize());
		List list = query.list();
		List<AdrStreeBean> beans=AdrStree11DaoUtils.fillAdrStreeBean(list);
		page.setResult(beans);
		page.setUrl(PageUtils.getPageUrl(page, "URL"));
		return page;
	}
}
