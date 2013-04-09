package com.cpst.emsadrdb.dao.jjsx;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.cpst.core.orm.Page;
import com.cpst.core.orm.PageUtils;
import com.cpst.core.orm.hibernate.HibernateDao;
import com.cpst.emsadrdb.entity.jjsx.JJSXBean;
import com.cpst.emsadrdb.service.jjsx.JjsxCommon;

@Repository
@Transactional
public class JjsxQueryDao extends HibernateDao<JJSXBean, String> {

	@Transactional(readOnly = true)
	public long getQueryCount(String sql) {
		List<?> listCount = getSession().createSQLQuery(sql).list();
		long totalCount = ((BigDecimal) listCount.get(0)).longValue();
		return totalCount;
	}

	@Transactional(readOnly = true)
	public JJSXBean getBeanqueryjjsxzbpc(String SEQID) {
		String sql = " select " +
		" a.SEQID,a.PCMC," +
		" a.CQUSXXUS,decode(a.CQUSXXUS,'1','城区','0','辖县','-') CQUSXXUSSTR," +
		" a.HXJZSJ,a.HXJZJG,a.FHXJZSJ,a.FHXJZJG," +
		" a.README," +
		" a.PCSX,decode(a.pcsx,'0','揽收','1','投递','-') PCSXSTR "
				+ " from cjjsx_zb_pc a where  a.SEQID = '" + SEQID + "'";
		List<?> list = getSession().createSQLQuery(sql).list();
		Iterator<?> it = list.iterator();
		JJSXBean bean = new JJSXBean();
		if (it.hasNext()) {
			Object[] obj = (Object[]) it.next();
			bean.setSEQID(JjsxCommon.commonsql(obj[0]));
			bean.setPCMC(JjsxCommon.commonsql(obj[1]));
			bean.setCQUSXXUS(JjsxCommon.commonsql(obj[2]));
			bean.setCQUSXXUSSTR(JjsxCommon.commonsql(obj[3]));
			bean.setHXJZSJ(JjsxCommon.commonsql(obj[4]));
			bean.setHXJZJG(JjsxCommon.commonsql(obj[5]));
			bean.setFHXJZSJ(JjsxCommon.commonsql(obj[6]));
			bean.setFHXJZJG(JjsxCommon.commonsql(obj[7]));
			bean.setREADME(JjsxCommon.commonsql(obj[8]));
		}
		return bean;
	}
	
	@Transactional(readOnly = true)
	public Page<JJSXBean> getBeansqueryjjsxzbpc(String sql, String sqlCount,
			Page<JJSXBean> page) {
		if (page.isAutoCount()) {
			long totalCount = 9999;
			page.setTotalCount(totalCount);
		}
		Query query = getSession().createSQLQuery(sql);
		query.setFirstResult(page.getFirst() - 1);
		query.setMaxResults(page.getPageSize());
		List<?> list = query.list();
		Iterator<?> it = list.iterator();
		List<JJSXBean> beans = new ArrayList<JJSXBean>();
		while (it.hasNext()) {
			Object[] obj = (Object[]) it.next();
			JJSXBean bean = new JJSXBean();
				bean.setSEQID(JjsxCommon.commonsql(obj[0]));
				bean.setPCMC(JjsxCommon.commonsql(obj[1]));
				bean.setCQUSXXUS(JjsxCommon.commonsql(obj[2]));
				bean.setCQUSXXUSSTR(JjsxCommon.commonsql(obj[3]));
				bean.setHXJZSJ(JjsxCommon.commonsql(obj[4]));
				bean.setHXJZJG(JjsxCommon.commonsql(obj[5]));
				bean.setFHXJZSJ(JjsxCommon.commonsql(obj[6]));
				bean.setFHXJZJG(JjsxCommon.commonsql(obj[7]));
				bean.setREADME(JjsxCommon.commonsql(obj[8]));
				bean.setPCSX(JjsxCommon.commonsql(obj[9]));
				bean.setPCSXSTR(JjsxCommon.commonsql(obj[10]));
			beans.add(bean);
		}
		page.setResult(beans);
		page.setAjaxUrl(PageUtils.getPageUrl(page, "AJAx"));
		return page;
	}
	
	@Transactional(readOnly = true)
	public JJSXBean getBeanqueryjjsxsjspc(String SEQID) {
		String sql = "select " +
				" b.SEQID,b.PCMC," +
				" a.CQUSXXUS,decode(a.CQUSXXUS,'1','城区','0','辖县','-') CQUSXXUSSTR," +
				" a.HXJZSJ,a.HXJZJG,a.FHXJZSJ,a.FHXJZJG," +
				" b.README," +
				" b.pcmc||a.PCMC||decode(a.pcsx,'0','[揽收]','1','[投递]','[-]') ALLPCMC,city_name||county_name DISTNAME " 
				+ " from cjjsx_zb_pc a,CJJSX_SJ_SPC b,cp_base_org_district c where   1=1 and a.seqid = b.zbpcseqid and b.distcd = c.district_code  and b.SEQID = '" + SEQID + "'";
		List<?> list = getSession().createSQLQuery(sql).list();
		Iterator<?> it = list.iterator();
		JJSXBean bean = new JJSXBean();
		if (it.hasNext()) {
			Object[] obj = (Object[]) it.next();
			bean.setSEQID(JjsxCommon.commonsql(obj[0]));
			bean.setPCMC(JjsxCommon.commonsql(obj[1]));
			bean.setCQUSXXUS(JjsxCommon.commonsql(obj[2]));
			bean.setCQUSXXUSSTR(JjsxCommon.commonsql(obj[3]));
			bean.setHXJZSJ(JjsxCommon.commonsql(obj[4]));
			bean.setHXJZJG(JjsxCommon.commonsql(obj[5]));
			bean.setFHXJZSJ(JjsxCommon.commonsql(obj[6]));
			bean.setFHXJZJG(JjsxCommon.commonsql(obj[7]));
			bean.setREADME(JjsxCommon.commonsql(obj[8]));
			bean.setALLPCMC(JjsxCommon.commonsql(obj[9]));
			bean.setDISTNAME(JjsxCommon.commonsql(obj[10]));
		}
		return bean;
	}
	
	@Transactional(readOnly = true)
	public Page<JJSXBean> getBeansqueryjjsxsjspc(String sql, String sqlCount,
			Page<JJSXBean> page) {
		if (page.isAutoCount()) {
			long totalCount = 9999;
			page.setTotalCount(totalCount);
		}
		Query query = getSession().createSQLQuery(sql);
		query.setFirstResult(page.getFirst() - 1);
		query.setMaxResults(page.getPageSize());
		List<?> list = query.list();
		Iterator<?> it = list.iterator();
		List<JJSXBean> beans = new ArrayList<JJSXBean>();
		while (it.hasNext()) {
			Object[] obj = (Object[]) it.next();
			JJSXBean bean = new JJSXBean();
			bean.setSEQID(JjsxCommon.commonsql(obj[0]));
			bean.setPCMC(JjsxCommon.commonsql(obj[1]));
			bean.setCQUSXXUS(JjsxCommon.commonsql(obj[2]));
			bean.setCQUSXXUSSTR(JjsxCommon.commonsql(obj[3]));
			bean.setHXJZSJ(JjsxCommon.commonsql(obj[4]));
			bean.setHXJZJG(JjsxCommon.commonsql(obj[5]));
			bean.setFHXJZSJ(JjsxCommon.commonsql(obj[6]));
			bean.setFHXJZJG(JjsxCommon.commonsql(obj[7]));
			bean.setREADME(JjsxCommon.commonsql(obj[8]));
			bean.setALLPCMC(JjsxCommon.commonsql(obj[9]));
			bean.setDISTNAME(JjsxCommon.commonsql(obj[10]));
			beans.add(bean);
		}
		page.setResult(beans);
		page.setAjaxUrl(PageUtils.getPageUrl(page, "AJAx"));
		return page;
	}
	
	@Transactional(readOnly = true)
	public JJSXBean getBeanqueryjjsxqxzyzjpc(String SEQID) {
		String sql = " select "+
					 " e.SEQID SEQID, "+
					 " nvl(d.city_name,d.province_name)||d.county_name DISTNAME, "+
					 " b.pcmc || a.pcmc || "+
					 " decode(a.pcsx, '0', '[揽收]', '1', '[投递]', '[-]') || "+
					 " decode(a.CQUSXXUS,'1','[城区]','0','[辖县]','-') ||" +
					 " decode(e.hxflag, '1', '[核心]', '0', '[非核心]', '[-]')  ALLPCMC, "+
					 "  c.ORG_CNAME ORG_CNAME, "+
					 "  c.ORG_CODE ORG_CODE, "+
					 "  decode(e.hxflag,'1',a.hxjzsj,'0',a.fhxjzsj,'-') PCJZSJ, "+
					 "  decode(e.hxflag,'1',a.hxjzjg,'0',a.fhxjzjg,'-')  PCSJJG, "+
					 "  e.README README,e.hxflag "+
					 "  from cjjsx_zb_pc a, "+
					 "  cjjsx_sj_spc b," +
					 " res_org c,cp_base_org_district_yt d ,CJJSX_SJ_ORGPC e " +
                      "  where nvl(c.county_code,c.city_code) = d.district_code   and c.org_code = e.org_code  " +
                      " and b.seqid = e.sjspcseqid and a.seqid = b.zbpcseqid and e.SEQID = '" + SEQID + "'";
		List<?> list = getSession().createSQLQuery(sql).list();
		Iterator<?> it = list.iterator();
		JJSXBean bean = new JJSXBean();
		if (it.hasNext()) {
			Object[] obj = (Object[]) it.next();
			bean.setSEQID(JjsxCommon.commonsql(obj[0]));
			bean.setDISTNAME(JjsxCommon.commonsql(obj[1]));
			bean.setALLPCMC(JjsxCommon.commonsql(obj[2]));
			bean.setORG_CNAME(JjsxCommon.commonsql(obj[3]));
			bean.setORG_CODE(JjsxCommon.commonsql(obj[4]));
			bean.setPCJZSJ(JjsxCommon.commonsql(obj[5]));
			bean.setPCSJJG(JjsxCommon.commonsql(obj[6]));
			bean.setREADME(JjsxCommon.commonsql(obj[7]));
			bean.setHXFLAG(JjsxCommon.commonsql(obj[8]));
		}
		return bean;
	}
	
	@Transactional(readOnly = true)
	public Page<JJSXBean> getBeansqueryjjsxqxzyzjpc(String sql, String sqlCount,
			Page<JJSXBean> page) {
		if (page.isAutoCount()) {
			List<?> listCount=getSession().createSQLQuery(sqlCount).list();
			long totalCount =((BigDecimal)listCount.get(0)).longValue();
			page.setTotalCount(totalCount);
		}
		Query query = getSession().createSQLQuery(sql);
		query.setFirstResult(page.getFirst() - 1);
		query.setMaxResults(page.getPageSize());
		List<?> list = query.list();
		Iterator<?> it = list.iterator();
		List<JJSXBean> beans = new ArrayList<JJSXBean>();
		while (it.hasNext()) {
			Object[] obj = (Object[]) it.next();
			JJSXBean bean = new JJSXBean();
			bean.setSEQID(JjsxCommon.commonsql(obj[0]));
			bean.setDISTNAME(JjsxCommon.commonsql(obj[1]));
			bean.setALLPCMC(JjsxCommon.commonsql(obj[2]));
			bean.setORG_CNAME(JjsxCommon.commonsql(obj[3]));
			bean.setORG_CODE(JjsxCommon.commonsql(obj[4]));
			bean.setPCJZSJ(JjsxCommon.commonsql(obj[5]));
			bean.setPCSJJG(JjsxCommon.commonsql(obj[6]));
			bean.setREADME(JjsxCommon.commonsql(obj[7]));
			bean.setHXFLAG(JjsxCommon.commonsql(obj[8]));
			beans.add(bean);
		}
		page.setResult(beans);
		page.setAjaxUrl(PageUtils.getPageUrl(page, "AJAx"));
		return page;
	}
	
	@Transactional(readOnly = true)
	public Page<JJSXBean> getBeansqueryjjsxqxzypctj(String sql, String sqlCount,
			Page<JJSXBean> page) {
		if (page.isAutoCount()) {
			List<?> listCount=getSession().createSQLQuery(sqlCount).list();
			long totalCount =((BigDecimal)listCount.get(0)).longValue();
			page.setTotalCount(totalCount);
		}
		Query query = getSession().createSQLQuery(sql);
		query.setFirstResult(page.getFirst() - 1);
		query.setMaxResults(page.getPageSize());
		List<?> list = query.list();
		Iterator<?> it = list.iterator();
		List<JJSXBean> beans = new ArrayList<JJSXBean>();
		while (it.hasNext()) {
			Object[] obj = (Object[]) it.next();
			JJSXBean bean = new JJSXBean();
			    bean.setDISTCD(JjsxCommon.commonsql(obj[0]));
			    bean.setDISTNAME(JjsxCommon.commonsql(obj[1]));
				bean.setALLPCMC(JjsxCommon.commonsql(obj[2]));
				bean.setALLCOUNT(JjsxCommon.commonsql(obj[3]));
				bean.setHXCOUNT(JjsxCommon.commonsql(obj[4]));
				bean.setHXFCOUNT(JjsxCommon.commonsql(obj[5]));
			beans.add(bean);
		}
		page.setResult(beans);
		page.setAjaxUrl(PageUtils.getPageUrl(page, "AJAx"));
		return page;
	}
	
	@Transactional(readOnly = true)
	public  List<?> getqueryexportall(String sql){
		Query query=getSession().createSQLQuery(sql);
		List<?> list = query.list();
		return list;
	}
	
}
