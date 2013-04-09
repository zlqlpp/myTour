package com.cpst.emsadrdb.dao.address;

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
import com.cpst.emsadrdb.entity.address.BldgrsdnsBean;
import com.cpst.emsadrdb.entity.address.BoBean;
import com.cpst.emsadrdb.entity.address.CpwhrlpgstBean;
import com.cpst.emsadrdb.entity.address.ExgcltitemopeBean;
import com.cpst.emsadrdb.entity.address.LogBean;
import com.cpst.emsadrdb.entity.address.OrganizationBean;
import com.cpst.emsadrdb.entity.address.QhzuiBean;
import com.cpst.emsadrdb.entity.address.ShdataBean;
import com.cpst.emsadrdb.entity.address.StreetBean;
import com.cpst.emsadrdb.entity.address.DistrictBean;
import com.cpst.emsadrdb.entity.address.TBSRPROCESSBean;
import com.cpst.emsadrdb.service.export.Exporttjpqb;
import com.cpst.emsadrdb.entity.address.PYDKHGZCXEXPORTBean;

@Repository
@Transactional
public class AddrQueryDao extends HibernateDao<DistrictBean,String>{
	
	@Transactional(readOnly = true)
	public  long getQueryCount(String sql){
		List<?> listCount=getSession().createSQLQuery(sql).list();
		long totalCount =((BigDecimal)listCount.get(0)).longValue();
		return totalCount;
	}
	
	@Transactional(readOnly = true)
	public  Page<StreetBean> getBeanQueryStreets(String sql,String sqlCount,Page<StreetBean> page){
		if (page.isAutoCount()) {
			List<?> listCount=getSession().createSQLQuery(sqlCount).list();
			long totalCount =((BigDecimal)listCount.get(0)).longValue();
			page.setTotalCount(totalCount);
		}
		Query query=getSession().createSQLQuery(sql);
		query.setFirstResult(page.getFirst() - 1);
		query.setMaxResults(page.getPageSize());
		List<?> list = query.list();
		Iterator<?> it = list.iterator();
		List<StreetBean> beans=new ArrayList<StreetBean>();
		while (it.hasNext()) {
			Object[] obj = (Object[]) it.next();
			StreetBean bean = new StreetBean();
			if (obj[0] != null) bean.setSTRT_ID(obj[0].toString());
			else  bean.setSTRT_ID("");
			if (obj[1] != null) bean.setDIST_CD(obj[1].toString());
			else  bean.setDIST_CD("");	
			if (obj[2] != null) bean.setTOTAL_DISTRICT_NAME(obj[2].toString());
			else  bean.setTOTAL_DISTRICT_NAME("");	
			if (obj[3] != null) bean.setTOTAL_STREET_NAME(obj[3].toString());
			else  bean.setTOTAL_STREET_NAME("");
			if (obj[4] != null) bean.setDATA_FLAG(obj[4].toString());
			else  bean.setDATA_FLAG("");
			if (obj[4] != null) bean.setDATA_FLAG_NAME(obj[4].toString());
			else  bean.setDATA_FLAG_NAME("");
			if (obj[5] != null) bean.setPOST_CD(obj[5].toString());
			else  bean.setPOST_CD("");
			beans.add(bean);
		}
		page.setResult(beans);
		page.setAjaxUrl(PageUtils.getPageUrl(page, "AJAx"));
		return page;
	}
	
	@Transactional(readOnly = true)
	public  Page<StreetBean> getBeanQueryStreetsqfhdb(String sql,String sqlCount,Page<StreetBean> page){
		if (page.isAutoCount()) {
			List<?> listCount=getSession().createSQLQuery(sqlCount).list();
			long totalCount =((BigDecimal)listCount.get(0)).longValue();
			page.setTotalCount(totalCount);
		}
		Query query=getSession().createSQLQuery(sql);
		query.setFirstResult(page.getFirst() - 1);
		query.setMaxResults(page.getPageSize());
		List<?> list = query.list();
		Iterator<?> it = list.iterator();
		List<StreetBean> beans=new ArrayList<StreetBean>();
		while (it.hasNext()) {
			Object[] obj = (Object[]) it.next();
			StreetBean bean = new StreetBean();
			if (obj[0] != null) bean.setSTRT_ID(obj[0].toString());
			else  bean.setSTRT_ID("");
			if (obj[1] != null) bean.setDIST_CD(obj[1].toString());
			else  bean.setDIST_CD("");	
			if (obj[2] != null) bean.setTOTAL_DISTRICT_NAME(obj[2].toString());
			else  bean.setTOTAL_DISTRICT_NAME("");	
			if (obj[3] != null) bean.setTOTAL_STREET_NAME(obj[3].toString());
			else  bean.setTOTAL_STREET_NAME("");
			if (obj[4] != null) bean.setDATA_FLAG(obj[4].toString());
			else  bean.setDATA_FLAG("");
			if (obj[4] != null) bean.setDATA_FLAG_NAME(obj[4].toString());
			else  bean.setDATA_FLAG_NAME("");
			if (obj[5] != null) bean.setPOST_CD(obj[5].toString());
			else  bean.setPOST_CD("");
			if (obj[6] != null) bean.setSTRT1_NAME(obj[6].toString());
			else  bean.setSTRT1_NAME("");
			if (obj[7] != null) bean.setSTRT2_NAME(obj[7].toString());
			else  bean.setSTRT2_NAME("");
			if (obj[8] != null) bean.setSTRT3_NAME(obj[8].toString());
			else  bean.setSTRT3_NAME("");
			if (obj[9] != null) bean.setSTRT4_NAME(obj[9].toString());
			else  bean.setSTRT4_NAME("");
			if (obj[10] != null) bean.setSTRT5_NAME(obj[10].toString());
			else  bean.setSTRT5_NAME("");
			beans.add(bean);
		}
		page.setResult(beans);
		page.setAjaxUrl(PageUtils.getPageUrl(page, "AJAx"));
		return page;
	}
	
	@Transactional(readOnly = true)
	public  Page<StreetBean> getBeanQueryStreetsdium(String sql,String sqlCount,Page<StreetBean> page){
		if (page.isAutoCount()) {
			List<?> listCount=getSession().createSQLQuery(sqlCount).list();
			long totalCount =((BigDecimal)listCount.get(0)).longValue();
			page.setTotalCount(totalCount);
		}
		Query query=getSession().createSQLQuery(sql);
		query.setFirstResult(page.getFirst() - 1);
		query.setMaxResults(page.getPageSize());
		List<?> list = query.list();
		Iterator<?> it = list.iterator();
		List<StreetBean> beans=new ArrayList<StreetBean>();
		while (it.hasNext()) {
			Object[] obj = (Object[]) it.next();
			StreetBean bean = new StreetBean();
			if (obj[0] != null) bean.setSTRT_ID(obj[0].toString());
			else  bean.setSTRT_ID("");
			if (obj[1] != null) bean.setDIST_CD(obj[1].toString());
			else  bean.setDIST_CD("");	
			if (obj[2] != null) bean.setTOTAL_DISTRICT_NAME(obj[2].toString());
			else  bean.setTOTAL_DISTRICT_NAME("");	
			if (obj[3] != null) bean.setTOTAL_STREET_NAME(obj[3].toString());
			else  bean.setTOTAL_STREET_NAME("");
			if (obj[4] != null) bean.setTOTAL_DIUMENPAI(obj[4].toString());
			else  bean.setTOTAL_DIUMENPAI("");
			beans.add(bean);
		}
		page.setResult(beans);
		page.setAjaxUrl(PageUtils.getPageUrl(page, "AJAx"));
		return page;
	}
	
	@Transactional(readOnly = true)
	public  Page<StreetBean> getBeanQueryTwstr(String sql,String sqlCount,Page<StreetBean> page){
		if (page.isAutoCount()) {
			List<?> listCount=getSession().createSQLQuery(sqlCount).list();
			long totalCount =((BigDecimal)listCount.get(0)).longValue();
			page.setTotalCount(totalCount);
		}
		Query query=getSession().createSQLQuery(sql);
		query.setFirstResult(page.getFirst() - 1);
		query.setMaxResults(page.getPageSize());
		List<?> list = query.list();
		Iterator<?> it = list.iterator();
		List<StreetBean> beans=new ArrayList<StreetBean>();
		while (it.hasNext()) {
			Object[] obj = (Object[]) it.next();
			StreetBean bean = new StreetBean();
			if (obj[0] != null) bean.setSTRT_ID(obj[0].toString());
			else  bean.setSTRT_ID("");
			if (obj[1] != null) bean.setDIST_CD(obj[1].toString());
			else  bean.setDIST_CD("");	
			if (obj[2] != null) bean.setTOTAL_DISTRICT_NAME(obj[2].toString());
			else  bean.setTOTAL_DISTRICT_NAME("");	
			if (obj[3] != null) bean.setTOTAL_STREET_NAME(obj[3].toString());
			else  bean.setTOTAL_STREET_NAME("");	
			beans.add(bean);
		}
		page.setResult(beans);
		page.setAjaxUrl(PageUtils.getPageUrl(page, "AJAx"));
		return page;
	}
	
	@Transactional(readOnly = true)
	public  Page<LogBean> getBeanQueryLogs(String sql,String sqlCount,Page<LogBean> page){
		if (page.isAutoCount()) {
			List<?> listCount=getSession().createSQLQuery(sqlCount).list();
			long totalCount =((BigDecimal)listCount.get(0)).longValue();
			page.setTotalCount(totalCount);
		}
		Query query=getSession().createSQLQuery(sql);
		query.setFirstResult(page.getFirst() - 1);
		query.setMaxResults(page.getPageSize());
		List<?> list = query.list();
		Iterator<?> it = list.iterator();
		List<LogBean> beans=new ArrayList<LogBean>();
		while (it.hasNext()) {
			Object[] obj = (Object[]) it.next();
			LogBean bean = new LogBean();
			if (obj[0] != null) bean.setSTRT_ID(obj[0].toString());
			else  bean.setSTRT_ID("");
			if (obj[1] != null) bean.setDIST_CD(obj[1].toString());
			else  bean.setDIST_CD("");	
			if (obj[2] != null) bean.setDIST_NAME(obj[2].toString());
			else  bean.setDIST_NAME("");	
			if (obj[3] != null) bean.setSTRT_NAME(obj[3].toString());
			else  bean.setSTRT_NAME("");	
			if (obj[4] != null) bean.setRSDNBLDG_NAME(obj[4].toString());
			else  bean.setRSDNBLDG_NAME("");
			if (obj[5] != null) bean.setORG_NAME(obj[5].toString());
			else  bean.setORG_NAME("");	
			if (obj[6] != null) bean.setDATA_FLAG_NAME(obj[6].toString());
			else  bean.setDATA_FLAG_NAME("");	
			if (obj[7] != null) bean.setDATA_DATE(obj[7].toString());
			else  bean.setDATA_DATE("");	
			if (obj[8] != null) bean.setDATA_USER_NAME(obj[8].toString());
			else  bean.setDATA_USER_NAME("");	
			beans.add(bean);
		}
		page.setResult(beans);
		page.setAjaxUrl(PageUtils.getPageUrl(page, "AJAx"));
		return page;
	}
	
	@Transactional(readOnly = true)
	public  Page<LogBean> getBeanQueryLogsbyid(String sql,String sqlCount,Page<LogBean> page){
		if (page.isAutoCount()) {
			List<?> listCount=getSession().createSQLQuery(sqlCount).list();
			long totalCount =((BigDecimal)listCount.get(0)).longValue();
			page.setTotalCount(totalCount);
		}
		Query query=getSession().createSQLQuery(sql);
		query.setFirstResult(page.getFirst() - 1);
		query.setMaxResults(page.getPageSize());
		List<?> list = query.list();
		Iterator<?> it = list.iterator();
		List<LogBean> beans=new ArrayList<LogBean>();
		while (it.hasNext()) {
			Object[] obj = (Object[]) it.next();
			LogBean bean = new LogBean();
			if (obj[0] != null) bean.setSTRT_ID(obj[0].toString());
			else  bean.setSTRT_ID("");
			if (obj[1] != null) bean.setDIST_CD(obj[1].toString());
			else  bean.setDIST_CD("");	
			if (obj[2] != null) bean.setDIST_NAME(obj[2].toString());
			else  bean.setDIST_NAME("");	
			if (obj[3] != null) bean.setSTRT_NAME(obj[3].toString());
			else  bean.setSTRT_NAME("");	
			if (obj[4] != null) bean.setRSDNBLDG_NAME(obj[4].toString());
			else  bean.setRSDNBLDG_NAME("");
			if (obj[5] != null) bean.setORG_NAME(obj[5].toString());
			else  bean.setORG_NAME("");	
			if (obj[6] != null) bean.setDATA_FLAG_NAME(obj[6].toString());
			else  bean.setDATA_FLAG_NAME("");	
			if (obj[7] != null) bean.setDATA_DATE(obj[7].toString());
			else  bean.setDATA_DATE("");	
			if (obj[8] != null) bean.setDATA_USER_NAME(obj[8].toString());
			else  bean.setDATA_USER_NAME("");	
			beans.add(bean);
		}
		page.setResult(beans);
		page.setAjaxUrl(PageUtils.getPageUrl(page, "AJAx"));
		return page;
	}
	
	@Transactional(readOnly = true)
	public StreetBean getStreet(String DIST_CD,String STRT_ID) {
		String sql="select a.STRT_ID,a.DIST_CD,a.STRT1_NAME,a.STRT2_NAME,a.STRT3_NAME,a.STRT4_NAME,a.STRT5_NAME,a.STRT_ABBR,a.DATA_FLAG,to_char(a.DATA_DATE,'yyyymmdd hh24:mm:ss')," +
			" CASE WHEN (length(a.DATA_USER) > 0 )  THEN (select MAX(c.US_NAME) from CP_PMN_USER c  where c.US_LOGIN_ID = a.DATA_USER) END AS DATA_USER_NAME ," +
			" b.PROVINCE_NAME||b.CITY_NAME||b.COUNTY_NAME,a.STRT1_ABBR_NAME,a.STRT2_ABBR_NAME,a.STRT3_ABBR_NAME,a.STRT4_ABBR_NAME,a.STRT5_ABBR_NAME,a.POST_CD,a.NOTE,a.ADULTNOTE " +
			" from CP_MK_ADR_STREET_" + DIST_CD.substring(0,2) + " a , CP_BASE_ORG_DISTRICT b where b.district_code=a.dist_cd and a.STRT_ID =" + STRT_ID;
		List<?> list = getSession().createSQLQuery(sql).list();
		Iterator<?> it = list.iterator();
		StreetBean bean = new StreetBean();
		if(it.hasNext()){
			Object[] obj = (Object[]) it.next();
			if (obj[0] != null) bean.setSTRT_ID(obj[0].toString());
			else  bean.setSTRT_ID("");
			if (obj[1] != null) bean.setDIST_CD(obj[1].toString());
			else  bean.setDIST_CD("");	
			if (obj[2] != null) bean.setSTRT1_NAME(obj[2].toString());
			else  bean.setSTRT1_NAME("");	
			if (obj[3] != null) bean.setSTRT2_NAME(obj[3].toString());
			else  bean.setSTRT2_NAME("");	
			if (obj[4] != null) bean.setSTRT3_NAME(obj[4].toString());
			else  bean.setSTRT3_NAME("");	
			if (obj[5] != null) bean.setSTRT4_NAME(obj[5].toString());
			else  bean.setSTRT4_NAME("");	
			if (obj[6] != null) bean.setSTRT5_NAME(obj[6].toString());
			else  bean.setSTRT5_NAME("");	
			if (obj[7] != null) bean.setSTRT_ABBR(obj[7].toString());
			else  bean.setSTRT_ABBR("");
			if (obj[8] != null) bean.setDATA_FLAG_NAME(obj[8].toString());
			else  bean.setDATA_FLAG_NAME("");
			if (obj[9] != null) bean.setDATA_DATE(obj[9].toString());
			else  bean.setDATA_DATE("");
			if (obj[10] != null) bean.setDATA_USER_NAME(obj[10].toString());
			else  bean.setDATA_USER_NAME("");
			if (obj[11] != null) bean.setTOTAL_DISTRICT_NAME(obj[11].toString());
			else  bean.setTOTAL_DISTRICT_NAME("");
			if (obj[12] != null) bean.setSTRT1_ABBR_NAME(obj[12].toString());
			else  bean.setSTRT1_ABBR_NAME("");	
			if (obj[13] != null) bean.setSTRT2_ABBR_NAME(obj[13].toString());
			else  bean.setSTRT2_ABBR_NAME("");	
			if (obj[14] != null) bean.setSTRT3_ABBR_NAME(obj[14].toString());
			else  bean.setSTRT3_ABBR_NAME("");	
			if (obj[15] != null) bean.setSTRT4_ABBR_NAME(obj[15].toString());
			else  bean.setSTRT4_ABBR_NAME("");	
			if (obj[16] != null) bean.setSTRT5_ABBR_NAME(obj[16].toString());
			else  bean.setSTRT5_ABBR_NAME("");
			if (obj[17] != null) bean.setPOST_CD(obj[17].toString());
			else  bean.setPOST_CD("");
			if (obj[18] != null) bean.setNOTE(obj[18].toString());
			else  bean.setNOTE("");
			if (obj[19] != null) bean.setADULTNOTE(obj[19].toString());
			else  bean.setADULTNOTE("");
		}
		return bean;
	}
	
	@Transactional(readOnly = true)
	public  Page<BoBean> getBeanQueryBos(String sql,String sqlCount,Page<BoBean> page){
		if (page.isAutoCount()) {
			List<?> listCount=getSession().createSQLQuery(sqlCount).list();
			long totalCount =((BigDecimal)listCount.get(0)).longValue();
			page.setTotalCount(totalCount);
		}
		Query query=getSession().createSQLQuery(sql);
		query.setFirstResult(page.getFirst() - 1);
		query.setMaxResults(page.getPageSize());
		List<?> list = query.list();
		Iterator<?> it = list.iterator();
		List<BoBean> beans=new ArrayList<BoBean>();
		while (it.hasNext()) {
			Object[] obj = (Object[]) it.next();
			BoBean bean = new BoBean();
			if (obj[0] != null) bean.setBO_FLAG(obj[0].toString());
			else  bean.setBO_FLAG("");
			if (obj[1] != null) bean.setRSDNBLDG_ID(obj[1].toString());
			else  bean.setRSDNBLDG_ID("");	
			if (obj[2] != null) bean.setORG_ID(obj[2].toString());
			else  bean.setORG_ID("");
			if (obj[3] != null) bean.setDIST_CD(obj[3].toString());
			else  bean.setDIST_CD("");
			if (obj[4] != null) bean.setSTRT_ID(obj[4].toString());
			else  bean.setSTRT_ID("");	
			if (obj[5] != null) bean.setRSDNBLDG_NAME(obj[5].toString());
			else  bean.setRSDNBLDG_NAME("");
			if (obj[6] != null) bean.setORG_NAME(obj[6].toString());
			else  bean.setORG_NAME("");
			if (obj[7] != null) bean.setTOTAL_BLDG_NAME(obj[7].toString());
			else  bean.setTOTAL_BLDG_NAME("");
			if (obj[8] != null) bean.setDATA_FLAG(obj[8].toString());
			else  bean.setDATA_FLAG("");
			if (obj[8] != null) bean.setDATA_FLAG_NAME(obj[8].toString());
			else  bean.setDATA_FLAG_NAME("");
			if (obj[9] != null) bean.setRSDNBLDG_FLAG_NAME(obj[9].toString());
			else  bean.setRSDNBLDG_FLAG_NAME("");
			beans.add(bean);
		}
		page.setResult(beans);
		page.setAjaxUrl(PageUtils.getPageUrl(page, "AJAx"));
		return page;
	}
	
	@Transactional(readOnly = true)
	public  Page<BldgrsdnsBean> getBeanQueryBldgrsdns(String sql,String sqlCount,Page<BldgrsdnsBean> page){
		if (page.isAutoCount()) {
			List<?> listCount=getSession().createSQLQuery(sqlCount).list();
			long totalCount =((BigDecimal)listCount.get(0)).longValue();
			page.setTotalCount(totalCount);
		}
		Query query=getSession().createSQLQuery(sql);
		query.setFirstResult(page.getFirst() - 1);
		query.setMaxResults(page.getPageSize());
		List<?> list = query.list();
		Iterator<?> it = list.iterator();
		List<BldgrsdnsBean> beans=new ArrayList<BldgrsdnsBean>();
		while (it.hasNext()) {
			Object[] obj = (Object[]) it.next();
			BldgrsdnsBean bean = new BldgrsdnsBean();
			if (obj[0] != null) bean.setRSDNBLDG_ID(obj[0].toString());
			else  bean.setRSDNBLDG_ID("");	
			if (obj[1] != null) bean.setDIST_CD(obj[1].toString());
			else  bean.setDIST_CD("");	
			if (obj[2] != null) bean.setSTRT_ID(obj[2].toString());
			else  bean.setSTRT_ID("");
			if (obj[3] != null) bean.setDORPLT_ID(obj[3].toString());
			else  bean.setDORPLT_ID("");
			if (obj[4] != null) bean.setRSDNBLDG_NAME(obj[4].toString());
			else  bean.setRSDNBLDG_NAME("");	
			if (obj[5] != null) bean.setRSDNBLDG_ABBR(obj[5].toString());
			else  bean.setRSDNBLDG_ABBR("");
			if (obj[6] != null) bean.setTOTAL_BLDG_NAME(obj[6].toString());
			else  bean.setTOTAL_BLDG_NAME("");
			if (obj[7] != null) bean.setDATA_FLAG_NAME(obj[7].toString());
			else  bean.setDATA_FLAG_NAME("");
			if (obj[7] != null) bean.setDATA_FLAG(obj[7].toString());
			else  bean.setDATA_FLAG("");
			if (obj[8] != null) bean.setRSDNBLDG_FLAG_NAME(obj[8].toString());
			else  bean.setRSDNBLDG_FLAG_NAME("");
			beans.add(bean);
		}
		page.setResult(beans);
		page.setAjaxUrl(PageUtils.getPageUrl(page, "AJAx"));
		return page;
	}
	
	@Transactional(readOnly = true)
	public  Page<BldgrsdnsBean> getBeanQueryBldgrsdnsluan(String sql,String sqlCount,Page<BldgrsdnsBean> page){
		if (page.isAutoCount()) {
			List<?> listCount=getSession().createSQLQuery(sqlCount).list();
			long totalCount =((BigDecimal)listCount.get(0)).longValue();
			page.setTotalCount(totalCount);
		}
		Query query=getSession().createSQLQuery(sql);
		query.setFirstResult(page.getFirst() - 1);
		query.setMaxResults(page.getPageSize());
		List<?> list = query.list();
		Iterator<?> it = list.iterator();
		List<BldgrsdnsBean> beans=new ArrayList<BldgrsdnsBean>();
		while (it.hasNext()) {
			Object[] obj = (Object[]) it.next();
			BldgrsdnsBean bean = new BldgrsdnsBean();
			if (obj[0] != null) bean.setRSDNBLDG_ID(obj[0].toString());
			else  bean.setRSDNBLDG_ID("");	
			if (obj[1] != null) bean.setDIST_CD(obj[1].toString());
			else  bean.setDIST_CD("");	
			if (obj[2] != null) bean.setSTRT_ID(obj[2].toString());
			else  bean.setSTRT_ID("");
			if (obj[3] != null) bean.setTOTAL_DISTRICT_NAME(obj[3].toString());
			else  bean.setTOTAL_DISTRICT_NAME("");	
			if (obj[4] != null) bean.setTOTAL_STREET_NAME(obj[4].toString());
			else  bean.setTOTAL_STREET_NAME("");
			if (obj[5] != null) bean.setRSDNBLDG_NAME(obj[5].toString());
			else  bean.setRSDNBLDG_NAME("");	
			if (obj[6] != null) bean.setRSDNBLDG_ABBR(obj[6].toString());
			else  bean.setRSDNBLDG_ABBR("");
			beans.add(bean);
		}
		page.setResult(beans);
		page.setAjaxUrl(PageUtils.getPageUrl(page, "AJAx"));
		return page;
	}
	
	@Transactional(readOnly = true)
	public BldgrsdnsBean getBldgrsdns(String DIST_CD,String RSDNBLDG_ID) {
		String sql="select a.RSDNBLDG_ID,a.STRT_ID,a.DIST_CD,a.RSDNBLDG_NAME,a.RSDNBLDG_ABBR, a.PREFIX || ' @@ ' || a.START_NUM || ' @@ ' || a.END_NUM || ' @@ ' ||  a.SUFFIX || ' @@ ' ||  a.NUM_FLAG AS TOTAL_BLDG_NAME ,  CASE WHEN (length(a.STRT_ID) > 0 )  THEN (select MAX(c.STRT1_NAME||c.STRT2_NAME||c.STRT3_NAME||c.STRT4_NAME||c.STRT5_NAME) from CP_MK_ADR_STREET_" + DIST_CD.substring(0,2) + "  c  where c.STRT_ID = a.STRT_ID) END AS TOTAL_STREET_NAME  ,  "
		+ " decode(b.DT_PK_CODE,null,a.DT_PK_CODE,b.DT_PK_CODE), "
		+ " decode(b.DM_PK_CODE,null,a.DM_PK_CODE,b.DM_PK_CODE), "
		+ " decode(b.PG_PK_CODE,null,a.PG_PK_CODE,b.PG_PK_CODE), "
		+ " START_NUM,PREFIX,END_NUM,SUFFIX,NUM_FLAG,DATA_USER,POST_CD,  "
		+ " (select MAX(c.DT_NAME) from CP_WH_DISTRICT c  where c.DT_PK_CODE = decode(b.DT_PK_CODE,null,a.DT_PK_CODE,b.DT_PK_CODE)), "
		+ " (select MAX(c.DM_NAME) from CP_WH_DEPARTMENT c  where c.DM_PK_CODE = decode(b.DM_PK_CODE,null,a.DM_PK_CODE,b.DM_PK_CODE)), "
		+ " (select MAX(c.PG_NAME) from CP_WH_POSTSEG c  where c.PG_PK_CODE = decode(b.PG_PK_CODE,null,a.PG_PK_CODE,b.PG_PK_CODE)), "
		+ "  decode(a.RSDNBLDG_FLAG,'0','小区','1','建筑物','其他') as RSDNBLDG_FLAG_NAME,a.NOTE   "
		+ "  from CP_MK_ADR_BLDGRSDNS_" + DIST_CD.substring(0,2) + "  a , "
		+ "  ( "
		+ " select   "
		/*
		+ " decode(b.DT_PK_CODE,null,(select max(DT_PK_CODE) from cp_wh_rl_pg_st_" + DIST_CD.substring(0,2) + "  c where c.start_num <= nvl(a.start_num,0) and c.end_num >= nvl(a.end_num,0) and nvl(c.prefix,'null') = nvl(a.prefix,'null') and nvl(c.suffix,'null') = nvl(a.suffix,'null') and c.RSDNBLDG_ID is null and c.ORG_ID is null and c.strt_id = a.strt_id),b.DT_PK_CODE) DT_PK_CODE, "
		+ " decode(b.DM_PK_CODE,null,(select max(DM_PK_CODE) from cp_wh_rl_pg_st_" + DIST_CD.substring(0,2) + "  c where c.start_num <= nvl(a.start_num,0) and c.end_num >= nvl(a.end_num,0) and nvl(c.prefix,'null') = nvl(a.prefix,'null') and nvl(c.suffix,'null') = nvl(a.suffix,'null') and c.RSDNBLDG_ID is null and c.ORG_ID is null and c.strt_id = a.strt_id),b.DM_PK_CODE) DM_PK_CODE, "
		+ " decode(b.PG_PK_CODE,null,(select max(PG_PK_CODE) from cp_wh_rl_pg_st_" + DIST_CD.substring(0,2) + "  c where c.start_num <= nvl(a.start_num,0) and c.end_num >= nvl(a.end_num,0) and nvl(c.prefix,'null') = nvl(a.prefix,'null') and nvl(c.suffix,'null') = nvl(a.suffix,'null') and c.RSDNBLDG_ID is null and c.ORG_ID is null and c.strt_id = a.strt_id),b.PG_PK_CODE) PG_PK_CODE, "
		*/
		+ " decode(b.DT_PK_CODE,null,(select max(DT_PK_CODE) from cp_wh_rl_pg_st_" + DIST_CD.substring(0,2) + "  c where c.start_num <= nvl(a.start_num,0) and c.end_num >= nvl(a.end_num,0) and ( (flag = 4)  or (flag = 5 and mod(a.start_num,2)=1) or (flag = 6 and mod(a.start_num,2)=0)) and nvl(c.prefix,'null') = nvl(a.prefix,'null') and nvl(c.suffix,'null') = nvl(a.suffix,'null') and c.RSDNBLDG_ID is null and c.ORG_ID is null and c.strt_id = a.strt_id),b.DT_PK_CODE) DT_PK_CODE, "
		+ " decode(b.DM_PK_CODE,null,(select max(DM_PK_CODE) from cp_wh_rl_pg_st_" + DIST_CD.substring(0,2) + "  c where c.start_num <= nvl(a.start_num,0) and c.end_num >= nvl(a.end_num,0) and ( (flag = 4)  or (flag = 5 and mod(a.start_num,2)=1) or (flag = 6 and mod(a.start_num,2)=0)) and nvl(c.prefix,'null') = nvl(a.prefix,'null') and nvl(c.suffix,'null') = nvl(a.suffix,'null') and c.RSDNBLDG_ID is null and c.ORG_ID is null and c.strt_id = a.strt_id),b.DM_PK_CODE) DM_PK_CODE, "
		+ " decode(b.PG_PK_CODE,null,(select max(PG_PK_CODE) from cp_wh_rl_pg_st_" + DIST_CD.substring(0,2) + "  c where c.start_num <= nvl(a.start_num,0) and c.end_num >= nvl(a.end_num,0) and ( (flag = 4)  or (flag = 5 and mod(a.start_num,2)=1) or (flag = 6 and mod(a.start_num,2)=0)) and nvl(c.prefix,'null') = nvl(a.prefix,'null') and nvl(c.suffix,'null') = nvl(a.suffix,'null') and c.RSDNBLDG_ID is null and c.ORG_ID is null and c.strt_id = a.strt_id),b.PG_PK_CODE) PG_PK_CODE, "
		+ " a.RSDNBLDG_ID "
		+ " from CP_MK_ADR_BLDGRSDNS_" + DIST_CD.substring(0,2) + "  a,cp_wh_rl_pg_st_" + DIST_CD.substring(0,2) + "   b "
		+ " where a.RSDNBLDG_ID =" + RSDNBLDG_ID
		+ " and a.RSDNBLDG_ID = b.RSDNBLDG_ID(+) "
		+ " ) b "
		+ " where  "
		+ " a.RSDNBLDG_ID =" + RSDNBLDG_ID
		+ " and a.RSDNBLDG_ID = b.RSDNBLDG_ID ";
		List<?> list = getSession().createSQLQuery(sql).list();
		Iterator<?> it = list.iterator();
		BldgrsdnsBean bean = new BldgrsdnsBean();
		if(it.hasNext()){
			Object[] obj = (Object[]) it.next();
			if (obj[0] != null) bean.setRSDNBLDG_ID(obj[0].toString());
			else  bean.setRSDNBLDG_ID("");
			if (obj[1] != null) bean.setSTRT_ID(obj[1].toString());
			else  bean.setSTRT_ID("");
			if (obj[2] != null) bean.setDIST_CD(obj[2].toString());
			else  bean.setDIST_CD("");	
			if (obj[3] != null) bean.setRSDNBLDG_NAME(obj[3].toString());
			else  bean.setRSDNBLDG_NAME("");	
			if (obj[4] != null) bean.setRSDNBLDG_ABBR(obj[4].toString());
			else  bean.setRSDNBLDG_ABBR("");
			if (obj[5] != null) bean.setTOTAL_BLDG_NAME(obj[5].toString());
			else  bean.setTOTAL_BLDG_NAME("");
			if (obj[6] != null) bean.setTOTAL_STREET_NAME(obj[6].toString());
			else  bean.setTOTAL_STREET_NAME("");
			if (obj[7] != null) bean.setDT_PK_CODE(obj[7].toString());
			else  bean.setDT_PK_CODE("");
			if (obj[8] != null) bean.setDM_PK_CODE(obj[8].toString());
			else  bean.setDM_PK_CODE("");
			if (obj[9] != null) bean.setPG_PK_CODE(obj[9].toString());
			else  bean.setPG_PK_CODE("");
			if (obj[10] != null) bean.setSTART_NUM(obj[10].toString());
			else  bean.setSTART_NUM("");
			if (obj[11] != null) bean.setPREFIX(obj[11].toString());
			else  bean.setPREFIX("");
			if (obj[12] != null) bean.setEND_NUM(obj[12].toString());
			else  bean.setEND_NUM("");
			if (obj[13] != null) bean.setSUFFIX(obj[13].toString());
			else  bean.setSUFFIX("");
			if (obj[14] != null) bean.setNUM_FLAG(obj[14].toString());
			else  bean.setNUM_FLAG("");
			if (obj[15] != null) bean.setDATA_USER(obj[15].toString());
			else  bean.setDATA_USER("");
			if (obj[16] != null) bean.setPOST_CD(obj[16].toString());
			else  bean.setPOST_CD("");
			if (obj[17] != null) bean.setDT_PK_NAME(obj[17].toString());
			else  bean.setDT_PK_NAME("");
			if (obj[18] != null) bean.setDM_PK_NAME(obj[18].toString());
			else  bean.setDM_PK_NAME("");
			if (obj[19] != null) bean.setPG_PK_NAME(obj[19].toString());
			else  bean.setPG_PK_NAME("");
			if (obj[20] != null) bean.setRSDNBLDG_FLAG_NAME(obj[20].toString());
			else  bean.setRSDNBLDG_FLAG_NAME("");
			if (obj[21] != null) bean.setNOTE(obj[21].toString());
			else  bean.setNOTE("");
		}
		return bean;
	}
	
	@Transactional(readOnly = true)
	public BldgrsdnsBean getBldgrsdnsdiusBean(String DIST_CD,String RSDNBLDG_ID) {
		String sql="select a.RSDNBLDG_ID,a.STRT_ID,a.DIST_CD,a.RSDNBLDG_NAME,a.RSDNBLDG_ABBR, a.PREFIX || ' @@ ' || a.START_NUM || ' @@ ' || a.END_NUM || ' @@ ' ||  a.SUFFIX || ' @@ ' ||  a.NUM_FLAG AS TOTAL_BLDG_NAME ,  CASE WHEN (length(a.STRT_ID) > 0 )  THEN (select MAX(c.STRT1_NAME||c.STRT2_NAME||c.STRT3_NAME||c.STRT4_NAME||c.STRT5_NAME) from CP_MK_ADR_STREET_" + DIST_CD.substring(0,2) + "  c  where c.STRT_ID = a.STRT_ID) END AS TOTAL_STREET_NAME  ,  "
		+ " START_NUM,PREFIX,END_NUM,SUFFIX,NUM_FLAG,DATA_USER,POST_CD,  "
		+ "  decode(a.RSDNBLDG_FLAG,'0','小区','1','建筑物','其他') as RSDNBLDG_FLAG_NAME,a.NOTE   "
		+ "  from TB_MINUS_BLDGRSDNS_" + DIST_CD.substring(0,2) + "  a "
		+ " where  "
		+ " a.RSDNBLDG_ID =" + RSDNBLDG_ID;
		List<?> list = getSession().createSQLQuery(sql).list();
		Iterator<?> it = list.iterator();
		BldgrsdnsBean bean = new BldgrsdnsBean();
		if(it.hasNext()){
			Object[] obj = (Object[]) it.next();
			if (obj[0] != null) bean.setRSDNBLDG_ID(obj[0].toString());
			else  bean.setRSDNBLDG_ID("");
			if (obj[1] != null) bean.setSTRT_ID(obj[1].toString());
			else  bean.setSTRT_ID("");
			if (obj[2] != null) bean.setDIST_CD(obj[2].toString());
			else  bean.setDIST_CD("");	
			if (obj[3] != null) bean.setRSDNBLDG_NAME(obj[3].toString());
			else  bean.setRSDNBLDG_NAME("");	
			if (obj[4] != null) bean.setRSDNBLDG_ABBR(obj[4].toString());
			else  bean.setRSDNBLDG_ABBR("");
			if (obj[5] != null) bean.setTOTAL_BLDG_NAME(obj[5].toString());
			else  bean.setTOTAL_BLDG_NAME("");
			if (obj[6] != null) bean.setTOTAL_STREET_NAME(obj[6].toString());
			else  bean.setTOTAL_STREET_NAME("");
			if (obj[7] != null) bean.setSTART_NUM(obj[7].toString());
			else  bean.setSTART_NUM("");
			if (obj[8] != null) bean.setPREFIX(obj[8].toString());
			else  bean.setPREFIX("");
			if (obj[9] != null) bean.setEND_NUM(obj[9].toString());
			else  bean.setEND_NUM("");
			if (obj[10] != null) bean.setSUFFIX(obj[10].toString());
			else  bean.setSUFFIX("");
			if (obj[11] != null) bean.setNUM_FLAG(obj[11].toString());
			else  bean.setNUM_FLAG("");
			if (obj[12] != null) bean.setDATA_USER(obj[12].toString());
			else  bean.setDATA_USER("");
			if (obj[13] != null) bean.setPOST_CD(obj[13].toString());
			else  bean.setPOST_CD("");
			if (obj[14] != null) bean.setRSDNBLDG_FLAG_NAME(obj[14].toString());
			else  bean.setRSDNBLDG_FLAG_NAME("");
			if (obj[15] != null) bean.setNOTE(obj[15].toString());
			else  bean.setNOTE("");
		}
		return bean;
	}
	
	@Transactional(readOnly = true)
	public BldgrsdnsBean getBldgrsdnssh(String DIST_CD,String RSDNBLDG_ID) {
		String sql="select a.RSDNBLDG_ID,a.STRT_ID,a.DIST_CD,a.RSDNBLDG_NAME,a.RSDNBLDG_ABBR," +
				" a.PREFIX || ' @@ ' || a.START_NUM || ' @@ ' || a.END_NUM || ' @@ ' ||  a.SUFFIX || ' @@ ' ||  a.NUM_FLAG AS TOTAL_BLDG_NAME , " +
				" CASE WHEN (length(a.STRT_ID) > 0 )  THEN (select MAX(c.STRT1_NAME||c.STRT2_NAME||c.STRT3_NAME||c.STRT4_NAME||c.STRT5_NAME) from CP_MK_ADR_STREET_" + DIST_CD.substring(0,2) + " c  where c.STRT_ID = a.STRT_ID) END AS TOTAL_STREET_NAME  , " +
				" DT_PK_CODE,DM_PK_CODE,PG_PK_CODE,START_NUM,PREFIX,END_NUM,SUFFIX,NUM_FLAG,DATA_USER,POST_CD, " +
				" CASE WHEN (length(a.DT_PK_CODE) > 0 )  THEN (select MAX(c.DT_NAME) from CP_WH_DISTRICT c  where c.DT_PK_CODE = a.DT_PK_CODE) END AS DT_PK_NAME  , " +
				" CASE WHEN (length(a.DM_PK_CODE) > 0 )  THEN (select '-' || MAX(c.DM_NAME) from CP_WH_DEPARTMENT c  where c.DM_PK_CODE = a.DM_PK_CODE) END AS DM_PK_NAME  , " +
				" CASE WHEN (length(a.PG_PK_CODE) > 0 )  THEN (select '-' || MAX(c.PG_NAME) from CP_WH_POSTSEG c  where c.PG_PK_CODE = a.PG_PK_CODE) END AS PG_PK_NAME  ," +
				" decode(a.RSDNBLDG_FLAG,'0','小区','1','建筑物','其他') as RSDNBLDG_FLAG_NAME,a.NOTE " +
				" from CP_MK_ADR_BLDGRSDNS_" + DIST_CD.substring(0,2) + " a where a.RSDNBLDG_ID =" + RSDNBLDG_ID;
		List<?> list = getSession().createSQLQuery(sql).list();
		Iterator<?> it = list.iterator();
		BldgrsdnsBean bean = new BldgrsdnsBean();
		if(it.hasNext()){
			Object[] obj = (Object[]) it.next();
			if (obj[0] != null) bean.setRSDNBLDG_ID(obj[0].toString());
			else  bean.setRSDNBLDG_ID("");
			if (obj[1] != null) bean.setSTRT_ID(obj[1].toString());
			else  bean.setSTRT_ID("");
			if (obj[2] != null) bean.setDIST_CD(obj[2].toString());
			else  bean.setDIST_CD("");	
			if (obj[3] != null) bean.setRSDNBLDG_NAME(obj[3].toString());
			else  bean.setRSDNBLDG_NAME("");	
			if (obj[4] != null) bean.setRSDNBLDG_ABBR(obj[4].toString());
			else  bean.setRSDNBLDG_ABBR("");
			if (obj[5] != null) bean.setTOTAL_BLDG_NAME(obj[5].toString());
			else  bean.setTOTAL_BLDG_NAME("");
			if (obj[6] != null) bean.setTOTAL_STREET_NAME(obj[6].toString());
			else  bean.setTOTAL_STREET_NAME("");
			if (obj[7] != null) bean.setDT_PK_CODE(obj[7].toString());
			else  bean.setDT_PK_CODE("");
			if (obj[8] != null) bean.setDM_PK_CODE(obj[8].toString());
			else  bean.setDM_PK_CODE("");
			if (obj[9] != null) bean.setPG_PK_CODE(obj[9].toString());
			else  bean.setPG_PK_CODE("");
			if (obj[10] != null) bean.setSTART_NUM(obj[10].toString());
			else  bean.setSTART_NUM("");
			if (obj[11] != null) bean.setPREFIX(obj[11].toString());
			else  bean.setPREFIX("");
			if (obj[12] != null) bean.setEND_NUM(obj[12].toString());
			else  bean.setEND_NUM("");
			if (obj[13] != null) bean.setSUFFIX(obj[13].toString());
			else  bean.setSUFFIX("");
			if (obj[14] != null) bean.setNUM_FLAG(obj[14].toString());
			else  bean.setNUM_FLAG("");
			if (obj[15] != null) bean.setDATA_USER(obj[15].toString());
			else  bean.setDATA_USER("");
			if (obj[16] != null) bean.setPOST_CD(obj[16].toString());
			else  bean.setPOST_CD("");
			if (obj[17] != null) bean.setDT_PK_NAME(obj[17].toString());
			else  bean.setDT_PK_NAME("");
			if (obj[18] != null) bean.setDM_PK_NAME(obj[18].toString());
			else  bean.setDM_PK_NAME("");
			if (obj[19] != null) bean.setPG_PK_NAME(obj[19].toString());
			else  bean.setPG_PK_NAME("");
			if (obj[20] != null) bean.setRSDNBLDG_FLAG_NAME(obj[20].toString());
			else  bean.setRSDNBLDG_FLAG_NAME("");
			if (obj[21] != null) bean.setNOTE(obj[21].toString());
			else  bean.setNOTE("");
		}
		return bean;
	}
	
	@Transactional(readOnly = true)
	public  Page<OrganizationBean> getBeanQueryOrganizations(String sql,String sqlCount,Page<OrganizationBean> page){
		if (page.isAutoCount()) {
			List<?> listCount=getSession().createSQLQuery(sqlCount).list();
			long totalCount =((BigDecimal)listCount.get(0)).longValue();
			page.setTotalCount(totalCount);
		}
		Query query=getSession().createSQLQuery(sql);
		query.setFirstResult(page.getFirst() - 1);
		query.setMaxResults(page.getPageSize());
		List<?> list = query.list();
		Iterator<?> it = list.iterator();
		List<OrganizationBean> beans=new ArrayList<OrganizationBean>();
		while (it.hasNext()) {
			Object[] obj = (Object[]) it.next();
			OrganizationBean bean = new OrganizationBean();
			if (obj[0] != null) bean.setORG_ID(obj[0].toString());
			else  bean.setORG_ID("");	
			if (obj[1] != null) bean.setDIST_CD(obj[1].toString());
			else  bean.setDIST_CD("");	
			if (obj[2] != null) bean.setSTRT_ID(obj[2].toString());
			else  bean.setSTRT_ID("");
			if (obj[3] != null) bean.setORG_NAME(obj[3].toString());
			else  bean.setORG_NAME("");	
			if (obj[4] != null) bean.setORG_ABBR_NAME(obj[4].toString());
			else  bean.setORG_ABBR_NAME("");
			if (obj[5] != null) bean.setORG_ABBR(obj[5].toString());
			else  bean.setORG_ABBR("");
			if (obj[6] != null) bean.setTOTAL_BLDG_NAME(obj[6].toString());
			else  bean.setTOTAL_BLDG_NAME("");
			if (obj[7] != null) bean.setDATA_FLAG_NAME(obj[7].toString());
			else  bean.setDATA_FLAG_NAME("");
			if (obj[7] != null) bean.setDATA_FLAG(obj[7].toString());
			else  bean.setDATA_FLAG("");
/*			if (obj[8] != null) bean.setRSDNBLDG_NAME(obj[8].toString());
			else  bean.setRSDNBLDG_NAME("");*/
			beans.add(bean);
		}
		page.setResult(beans);
		page.setAjaxUrl(PageUtils.getPageUrl(page, "AJAx"));
		return page;
	}
	
	@Transactional(readOnly = true)
	public  Page<OrganizationBean> getBeanQueryOrganluan(String sql,String sqlCount,Page<OrganizationBean> page){
		if (page.isAutoCount()) {
			List<?> listCount=getSession().createSQLQuery(sqlCount).list();
			long totalCount =((BigDecimal)listCount.get(0)).longValue();
			page.setTotalCount(totalCount);
		}
		Query query=getSession().createSQLQuery(sql);
		query.setFirstResult(page.getFirst() - 1);
		query.setMaxResults(page.getPageSize());
		List<?> list = query.list();
		Iterator<?> it = list.iterator();
		List<OrganizationBean> beans=new ArrayList<OrganizationBean>();
		while (it.hasNext()) {
			Object[] obj = (Object[]) it.next();
			OrganizationBean bean = new OrganizationBean();
			if (obj[0] != null) bean.setORG_ID(obj[0].toString());
			else  bean.setORG_ID("");	
			if (obj[1] != null) bean.setDIST_CD(obj[1].toString());
			else  bean.setDIST_CD("");	
			if (obj[2] != null) bean.setSTRT_ID(obj[2].toString());
			else  bean.setSTRT_ID("");
			if (obj[3] != null) bean.setTOTAL_DISTRICT_NAME(obj[3].toString());
			else  bean.setTOTAL_DISTRICT_NAME("");	
			if (obj[4] != null) bean.setTOTAL_STREET_NAME(obj[4].toString());
			else  bean.setTOTAL_STREET_NAME("");
			if (obj[5] != null) bean.setORG_NAME(obj[5].toString());
			else  bean.setORG_NAME("");	
			if (obj[6] != null) bean.setORG_ABBR(obj[6].toString());
			else  bean.setORG_ABBR("");
			beans.add(bean);
		}
		page.setResult(beans);
		page.setAjaxUrl(PageUtils.getPageUrl(page, "AJAx"));
		return page;
	}
	
	public OrganizationBean getOrganizationBean(String DIST_CD,String ORG_ID) {
		String sql ="select a.ORG_ID,a.STRT_ID,a.ORG_ABBR,a.DIST_CD,a.ORG_NAME,a.ORG_ABBR_NAME, a.PREFIX || ' @@ ' || a.START_NUM || ' @@ ' || a.END_NUM || ' @@ ' ||  a.SUFFIX || ' @@ ' ||  a.NUM_FLAG AS TOTAL_BLDG_NAME ,  CASE WHEN (length(a.STRT_ID) > 0 )  THEN (select MAX(c.STRT1_NAME||c.STRT2_NAME||c.STRT3_NAME||c.STRT4_NAME||c.STRT5_NAME) from CP_MK_ADR_STREET_" + DIST_CD.substring(0,2) + "  c  where c.STRT_ID = a.STRT_ID) END AS TOTAL_STREET_NAME  , " 
			+ " decode(b.DT_PK_CODE,null,a.DT_PK_CODE,b.DT_PK_CODE), "
			+ " decode(b.DM_PK_CODE,null,a.DM_PK_CODE,b.DM_PK_CODE), "
			+ " decode(b.PG_PK_CODE,null,a.PG_PK_CODE,b.PG_PK_CODE), "
			+ " START_NUM,PREFIX,END_NUM,SUFFIX,NUM_FLAG,DATA_USER,POST_CD,  "
			+ " (select MAX(c.DT_NAME) from CP_WH_DISTRICT c  where c.DT_PK_CODE = decode(b.DT_PK_CODE,null,a.DT_PK_CODE,b.DT_PK_CODE)), "
			+ " (select MAX(c.DM_NAME) from CP_WH_DEPARTMENT c  where c.DM_PK_CODE = decode(b.DM_PK_CODE,null,a.DM_PK_CODE,b.DM_PK_CODE)), "
			+ " (select MAX(c.PG_NAME) from CP_WH_POSTSEG c  where c.PG_PK_CODE = decode(b.PG_PK_CODE,null,a.PG_PK_CODE,b.PG_PK_CODE)), "
			+ "  a.NOTE "
			+ " from "
			+ " CP_MK_ORG_ORGANIZATION_" + DIST_CD.substring(0,2) + "  a , "
			+ " ( "
			+ " select   "
			+ " decode(b.DT_PK_CODE,null,(select max(DT_PK_CODE) from cp_wh_rl_pg_st_" + DIST_CD.substring(0,2) + "  c where c.start_num <= nvl(a.start_num,0) and c.end_num >= nvl(a.end_num,0) and ( (flag = 4)  or (flag = 5 and mod(a.start_num,2)=1) or (flag = 6 and mod(a.start_num,2)=0)) and nvl(c.prefix,'null') = nvl(a.prefix,'null') and nvl(c.suffix,'null') = nvl(a.suffix,'null') and c.RSDNBLDG_ID is null and c.org_id is null and c.strt_id = a.strt_id),b.DT_PK_CODE) DT_PK_CODE, "
			+ " decode(b.DM_PK_CODE,null,(select max(DM_PK_CODE) from cp_wh_rl_pg_st_" + DIST_CD.substring(0,2) + "  c where c.start_num <= nvl(a.start_num,0) and c.end_num >= nvl(a.end_num,0) and ( (flag = 4)  or (flag = 5 and mod(a.start_num,2)=1) or (flag = 6 and mod(a.start_num,2)=0)) and  nvl(c.prefix,'null') = nvl(a.prefix,'null') and nvl(c.suffix,'null') = nvl(a.suffix,'null') and c.RSDNBLDG_ID is null and c.org_id is null and c.strt_id = a.strt_id),b.DM_PK_CODE) DM_PK_CODE, "
			+ " decode(b.PG_PK_CODE,null,(select max(PG_PK_CODE) from cp_wh_rl_pg_st_" + DIST_CD.substring(0,2) + "  c where c.start_num <= nvl(a.start_num,0) and c.end_num >= nvl(a.end_num,0) and ( (flag = 4)  or (flag = 5 and mod(a.start_num,2)=1) or (flag = 6 and mod(a.start_num,2)=0)) and nvl(c.prefix,'null') = nvl(a.prefix,'null') and nvl(c.suffix,'null') = nvl(a.suffix,'null') and c.RSDNBLDG_ID is null and c.org_id is null and c.strt_id = a.strt_id),b.PG_PK_CODE) PG_PK_CODE, "
			+ " a.ORG_ID "
			+ " from CP_MK_ORG_ORGANIZATION_" + DIST_CD.substring(0,2) + "  a,cp_wh_rl_pg_st_" + DIST_CD.substring(0,2) + " b "
			+ " where a.ORG_ID =" + ORG_ID
			+ " and a.org_id = b.org_id(+) "
			+ " ) b "
			+ " where  "
			+ " a.org_id =" + ORG_ID
			+ " and a.org_id = b.org_id ";
		List<?> list = getSession().createSQLQuery(sql).list();
		Iterator<?> it = list.iterator();
		OrganizationBean bean = new OrganizationBean();
		if(it.hasNext()){
			Object[] obj = (Object[]) it.next();
			if (obj[0] != null) bean.setORG_ID(obj[0].toString());
			else  bean.setORG_ID("");
			if (obj[1] != null) bean.setSTRT_ID(obj[1].toString());
			else  bean.setSTRT_ID("");
			if (obj[2] != null) bean.setORG_ABBR(obj[2].toString());
			else  bean.setORG_ABBR("");
			if (obj[3] != null) bean.setDIST_CD(obj[3].toString());
			else  bean.setDIST_CD("");	
			if (obj[4] != null) bean.setORG_NAME(obj[4].toString());
			else  bean.setORG_NAME("");	
			if (obj[5] != null) bean.setORG_ABBR_NAME(obj[5].toString());
			else  bean.setORG_ABBR_NAME("");
			if (obj[6] != null) bean.setTOTAL_BLDG_NAME(obj[6].toString());
			else  bean.setTOTAL_BLDG_NAME("");
			if (obj[7] != null) bean.setTOTAL_STREET_NAME(obj[7].toString());
			else  bean.setTOTAL_STREET_NAME("");
			if (obj[8] != null) bean.setDT_PK_CODE(obj[8].toString());
			else  bean.setDT_PK_CODE("");
			if (obj[9] != null) bean.setDM_PK_CODE(obj[9].toString());
			else  bean.setDM_PK_CODE("");
			if (obj[10] != null) bean.setPG_PK_CODE(obj[10].toString());
			else  bean.setPG_PK_CODE("");
			if (obj[11] != null) bean.setSTART_NUM(obj[11].toString());
			else  bean.setSTART_NUM("");
			if (obj[12] != null) bean.setPREFIX(obj[12].toString());
			else  bean.setPREFIX("");
			if (obj[13] != null) bean.setEND_NUM(obj[13].toString());
			else  bean.setEND_NUM("");
			if (obj[14] != null) bean.setSUFFIX(obj[14].toString());
			else  bean.setSUFFIX("");
			if (obj[15] != null) bean.setNUM_FLAG(obj[15].toString());
			else  bean.setNUM_FLAG("");
			if (obj[16] != null) bean.setDATA_USER(obj[16].toString());
			else  bean.setDATA_USER("");
			if (obj[17] != null) bean.setPOST_CD(obj[17].toString());
			else  bean.setPOST_CD("");
			if (obj[18] != null) bean.setDT_PK_NAME(obj[18].toString());
			else  bean.setDT_PK_NAME("");
			if (obj[19] != null) bean.setDM_PK_NAME(obj[19].toString());
			else  bean.setDM_PK_NAME("");
			if (obj[20] != null) bean.setPG_PK_NAME(obj[20].toString());
			else  bean.setPG_PK_NAME("");
			if (obj[21] != null) bean.setNOTE(obj[21].toString());
			else  bean.setNOTE("");
		}
		return bean;
	}
	
	public OrganizationBean getOrgandiusBean(String DIST_CD,String ORG_ID) {
		String sql="select b.ORG_ID,b.DIST_CD,a.PROVINCE_NAME||a.CITY_NAME||a.COUNTY_NAME TOTAL_DISTRICT_NAME,b.STRT_ID,c.strt1_name||c.strt2_name||c.strt3_name||c.strt4_name||c.strt5_name,b.ORG_NAME,b.ORG_ABBR,b.post_cd,b.ORG_ABBR_NAME,b.NOTE,b.PREFIX,b.START_NUM,b.END_NUM,b.SUFFIX" +
					" from CP_BASE_ORG_DISTRICT a,TB_MINUS_ORGANIZATION_" + DIST_CD.substring(0,2) + " b,CP_MK_ADR_STREET_" + DIST_CD.substring(0,2) + " c " +
					" where a.district_code=b.dist_cd and b.STRT_ID = c.STRT_ID(+)  " +
					" and b.org_id = " + ORG_ID;
		List<?> list = getSession().createSQLQuery(sql).list();
		Iterator<?> it = list.iterator();
		OrganizationBean bean = new OrganizationBean();
		if(it.hasNext()){
			Object[] obj = (Object[]) it.next();
			if (obj[0] != null) bean.setORG_ID(obj[0].toString());
			else  bean.setORG_ID("");
			if (obj[1] != null) bean.setDIST_CD(obj[1].toString());
			else  bean.setDIST_CD("");	
			if (obj[2] != null) bean.setTOTAL_BLDG_NAME(obj[2].toString());
			else  bean.setTOTAL_BLDG_NAME("");
			if (obj[3] != null) bean.setSTRT_ID(obj[3].toString());
			else  bean.setSTRT_ID("");
			if (obj[4] != null) bean.setTOTAL_STREET_NAME(obj[4].toString());
			else  bean.setTOTAL_STREET_NAME("");
			if (obj[5] != null) bean.setORG_NAME(obj[5].toString());
			else  bean.setORG_NAME("");	
			if (obj[6] != null) bean.setORG_ABBR(obj[6].toString());
			else  bean.setORG_ABBR("");
			if (obj[7] != null) bean.setPOST_CD(obj[7].toString());
			else  bean.setPOST_CD("");
			if (obj[8] != null) bean.setORG_ABBR_NAME(obj[8].toString());
			else  bean.setORG_ABBR_NAME("");
			if (obj[9] != null) bean.setNOTE(obj[9].toString());
			else  bean.setNOTE("");
			if (obj[10] != null) bean.setPREFIX(obj[10].toString());
			else  bean.setPREFIX("");
			if (obj[11] != null) bean.setSTART_NUM(obj[11].toString());
			else  bean.setSTART_NUM("");
			if (obj[12] != null) bean.setEND_NUM(obj[12].toString());
			else  bean.setEND_NUM("");
			if (obj[13] != null) bean.setSUFFIX(obj[13].toString());
			else  bean.setSUFFIX("");
		}
		return bean;
	}
	
	public OrganizationBean getOrganizationshBean(String DIST_CD,String ORG_ID) {
		String sql="select a.ORG_ID,a.STRT_ID,a.ORG_ABBR,a.DIST_CD,a.ORG_NAME,a.ORG_ABBR_NAME," +
		" a.PREFIX || ' @@ ' || a.START_NUM || ' @@ ' || a.END_NUM || ' @@ ' ||  a.SUFFIX || ' @@ ' ||  a.NUM_FLAG AS TOTAL_BLDG_NAME , " +
		" CASE WHEN (length(a.STRT_ID) > 0 )  THEN (select MAX(c.STRT1_NAME||c.STRT2_NAME||c.STRT3_NAME||c.STRT4_NAME||c.STRT5_NAME) from CP_MK_ADR_STREET_" + DIST_CD.substring(0,2) + " c  where c.STRT_ID = a.STRT_ID) END AS TOTAL_STREET_NAME  , " +
		" DT_PK_CODE,DM_PK_CODE,PG_PK_CODE,START_NUM,PREFIX,END_NUM,SUFFIX,NUM_FLAG,DATA_USER,POST_CD, " +
		" CASE WHEN (length(a.DT_PK_CODE) > 0 )  THEN (select MAX(c.DT_NAME) from CP_WH_DISTRICT c  where c.DT_PK_CODE = a.DT_PK_CODE) END AS DT_PK_NAME  , " +
		" CASE WHEN (length(a.DM_PK_CODE) > 0 )  THEN (select '-' || MAX(c.DM_NAME) from CP_WH_DEPARTMENT c  where c.DM_PK_CODE = a.DM_PK_CODE) END AS DM_PK_NAME  , " +
		" CASE WHEN (length(a.PG_PK_CODE) > 0 )  THEN (select '-' || MAX(c.PG_NAME) from CP_WH_POSTSEG c  where c.PG_PK_CODE = a.PG_PK_CODE) END AS PG_PK_NAME,a.NOTE  " +
		//" CASE WHEN (length(a.RSDNBLDG_ID) > 0 )  THEN (select MAX(b.RSDNBLDG_NAME) from CP_MK_ADR_BLDGRSDNS_" + DIST_CD.substring(0,2) + " b  where b.RSDNBLDG_ID = a.RSDNBLDG_ID) END AS RSDNBLDG_NAME " +
		" from CP_MK_ORG_ORGANIZATION_" + DIST_CD.substring(0,2) + " a where a.ORG_ID =" + ORG_ID;
		List<?> list = getSession().createSQLQuery(sql).list();
		Iterator<?> it = list.iterator();
		OrganizationBean bean = new OrganizationBean();
		if(it.hasNext()){
			Object[] obj = (Object[]) it.next();
			if (obj[0] != null) bean.setORG_ID(obj[0].toString());
			else  bean.setORG_ID("");
			if (obj[1] != null) bean.setSTRT_ID(obj[1].toString());
			else  bean.setSTRT_ID("");
			if (obj[2] != null) bean.setORG_ABBR(obj[2].toString());
			else  bean.setORG_ABBR("");
			if (obj[3] != null) bean.setDIST_CD(obj[3].toString());
			else  bean.setDIST_CD("");	
			if (obj[4] != null) bean.setORG_NAME(obj[4].toString());
			else  bean.setORG_NAME("");	
			if (obj[5] != null) bean.setORG_ABBR_NAME(obj[5].toString());
			else  bean.setORG_ABBR_NAME("");
			if (obj[6] != null) bean.setTOTAL_BLDG_NAME(obj[6].toString());
			else  bean.setTOTAL_BLDG_NAME("");
			if (obj[7] != null) bean.setTOTAL_STREET_NAME(obj[7].toString());
			else  bean.setTOTAL_STREET_NAME("");
			if (obj[8] != null) bean.setDT_PK_CODE(obj[8].toString());
			else  bean.setDT_PK_CODE("");
			if (obj[9] != null) bean.setDM_PK_CODE(obj[9].toString());
			else  bean.setDM_PK_CODE("");
			if (obj[10] != null) bean.setPG_PK_CODE(obj[10].toString());
			else  bean.setPG_PK_CODE("");
			if (obj[11] != null) bean.setSTART_NUM(obj[11].toString());
			else  bean.setSTART_NUM("");
			if (obj[12] != null) bean.setPREFIX(obj[12].toString());
			else  bean.setPREFIX("");
			if (obj[13] != null) bean.setEND_NUM(obj[13].toString());
			else  bean.setEND_NUM("");
			if (obj[14] != null) bean.setSUFFIX(obj[14].toString());
			else  bean.setSUFFIX("");
			if (obj[15] != null) bean.setNUM_FLAG(obj[15].toString());
			else  bean.setNUM_FLAG("");
			if (obj[16] != null) bean.setDATA_USER(obj[16].toString());
			else  bean.setDATA_USER("");
			if (obj[17] != null) bean.setPOST_CD(obj[17].toString());
			else  bean.setPOST_CD("");
			if (obj[18] != null) bean.setDT_PK_NAME(obj[18].toString());
			else  bean.setDT_PK_NAME("");
			if (obj[19] != null) bean.setDM_PK_NAME(obj[19].toString());
			else  bean.setDM_PK_NAME("");
			if (obj[20] != null) bean.setPG_PK_NAME(obj[20].toString());
			else  bean.setPG_PK_NAME("");
			if (obj[21] != null) bean.setNOTE(obj[21].toString());
			else  bean.setNOTE("");
		}
		return bean;
	}
	
	@Transactional(readOnly = true)
	public  String getALL_DATA_NUMBER(String sql){
		String ALL_DATA_NUMBER="0";
		Query query=getSession().createSQLQuery(sql);
		List<?> list = query.list();
		Iterator<?> it = list.iterator();
		while (it.hasNext()) {
			ALL_DATA_NUMBER = it.next().toString();
		}
		return ALL_DATA_NUMBER;
	}
	
	@Transactional(readOnly = true)
	public  Page<ShdataBean> getBeanQueryShdata(String sql,String sqlCount,Page<ShdataBean> page){
		if (page.isAutoCount()) {
			List<?> listCount=getSession().createSQLQuery(sqlCount).list();
			long totalCount =((BigDecimal)listCount.get(0)).longValue();
			page.setTotalCount(totalCount);
		}
		Query query=getSession().createSQLQuery(sql);
		query.setFirstResult(page.getFirst() - 1);
		query.setMaxResults(page.getPageSize());
		List<?> list = query.list();
		Iterator<?> it = list.iterator();
		List<ShdataBean> beans=new ArrayList<ShdataBean>();
		while (it.hasNext()) {
			Object[] obj = (Object[]) it.next();
			ShdataBean bean = new ShdataBean();
			if (obj[0] != null) bean.setDIST_CD(obj[0].toString());
			else  bean.setDIST_CD("");
			if (obj[1] != null) bean.setTYPE_ID(obj[1].toString());
			else  bean.setTYPE_ID("");	
			if (obj[2] != null) bean.setTOTAL_VALUE(obj[2].toString());
			else  bean.setTOTAL_VALUE("");	
			if (obj[3] != null) bean.setTOTAL_NAME(obj[3].toString());
			else  bean.setTOTAL_NAME("");
			if (obj[4] != null) bean.setTYPE_DATA_NAME(obj[4].toString());
			else  bean.setTYPE_DATA_NAME("");
			if (obj[5] != null) bean.setTYPE_FLAG_NAME(obj[5].toString());
			else  bean.setTYPE_FLAG_NAME("");
			if (obj[6] != null) bean.setNOTE(obj[6].toString());
			else  bean.setNOTE("");
			beans.add(bean);
		}
		page.setResult(beans);
		page.setAjaxUrl(PageUtils.getPageUrl(page, "AJAx"));
		return page;
	}
	
	@Transactional(readOnly = true)
	public  Page<OrganizationBean> getBeanQueryorgandius(String sql,String sqlCount,Page<OrganizationBean> page){
		if (page.isAutoCount()) {
			List<?> listCount=getSession().createSQLQuery(sqlCount).list();
			long totalCount =((BigDecimal)listCount.get(0)).longValue();
			page.setTotalCount(totalCount);
		}
		Query query=getSession().createSQLQuery(sql);
		query.setFirstResult(page.getFirst() - 1);
		query.setMaxResults(page.getPageSize());
		List<?> list = query.list();
		Iterator<?> it = list.iterator();
		List<OrganizationBean> beans=new ArrayList<OrganizationBean>();
		while (it.hasNext()) {
			Object[] obj = (Object[]) it.next();
			OrganizationBean bean = new OrganizationBean();
			if (obj[0] != null) bean.setORG_ID(obj[0].toString());
			else  bean.setORG_ID("");	
			if (obj[1] != null) bean.setDIST_CD(obj[1].toString());
			else  bean.setDIST_CD("");	
			if (obj[2] != null) bean.setTOTAL_DISTRICT_NAME(obj[2].toString());
			else  bean.setTOTAL_DISTRICT_NAME("");
			if (obj[3] != null) bean.setSTRT_ID(obj[3].toString());
			else  bean.setSTRT_ID("");	
			if (obj[4] != null) bean.setTOTAL_STREET_NAME(obj[4].toString());
			else  bean.setTOTAL_STREET_NAME("");
			if (obj[5] != null) bean.setORG_NAME(obj[5].toString());
			else  bean.setORG_NAME("");	
			if (obj[6] != null) bean.setORG_ABBR(obj[6].toString());
			else  bean.setORG_ABBR("");
			
			beans.add(bean);
		}
		page.setResult(beans);
		page.setAjaxUrl(PageUtils.getPageUrl(page, "AJAx"));
		return page;
	}
	
	@Transactional(readOnly = true)
	public  Page<BldgrsdnsBean> getBeanQuerybldgrsdnsdius(String sql,String sqlCount,Page<BldgrsdnsBean> page){
		if (page.isAutoCount()) {
			List<?> listCount=getSession().createSQLQuery(sqlCount).list();
			long totalCount =((BigDecimal)listCount.get(0)).longValue();
			page.setTotalCount(totalCount);
		}
		Query query=getSession().createSQLQuery(sql);
		query.setFirstResult(page.getFirst() - 1);
		query.setMaxResults(page.getPageSize());
		List<?> list = query.list();
		Iterator<?> it = list.iterator();
		List<BldgrsdnsBean> beans=new ArrayList<BldgrsdnsBean>();
		while (it.hasNext()) {
			Object[] obj = (Object[]) it.next();
			BldgrsdnsBean bean = new BldgrsdnsBean();
			if (obj[0] != null) bean.setRSDNBLDG_ID(obj[0].toString());
			else  bean.setRSDNBLDG_ID("");	
			if (obj[1] != null) bean.setDIST_CD(obj[1].toString());
			else  bean.setDIST_CD("");	
			if (obj[2] != null) bean.setTOTAL_DISTRICT_NAME(obj[2].toString());
			else  bean.setTOTAL_DISTRICT_NAME("");
			if (obj[3] != null) bean.setSTRT_ID(obj[3].toString());
			else  bean.setSTRT_ID("");
			if (obj[4] != null) bean.setTOTAL_STREET_NAME(obj[4].toString());
			else  bean.setTOTAL_STREET_NAME("");
			if (obj[5] != null) bean.setRSDNBLDG_NAME(obj[5].toString());
			else  bean.setRSDNBLDG_NAME("");	
			if (obj[6] != null) bean.setRSDNBLDG_ABBR(obj[6].toString());
			else  bean.setRSDNBLDG_ABBR("");
			
			beans.add(bean);
		}
		page.setResult(beans);
		page.setAjaxUrl(PageUtils.getPageUrl(page, "AJAx"));
		return page;
	}
	
	@Transactional(readOnly = true)
	public  Page<QhzuiBean> getBeanQueryQueryqhzuis(String sql,String sqlCount,Page<QhzuiBean> page){
		if (page.isAutoCount()) {
			List<?> listCount=getSession().createSQLQuery(sqlCount).list();
			long totalCount =((BigDecimal)listCount.get(0)).longValue();
			page.setTotalCount(totalCount);
		}
		Query query=getSession().createSQLQuery(sql);
		query.setFirstResult(page.getFirst() - 1);
		query.setMaxResults(page.getPageSize());
		List<?> list = query.list();
		Iterator<?> it = list.iterator();
		List<QhzuiBean> beans=new ArrayList<QhzuiBean>();
		while (it.hasNext()) {
			Object[] obj = (Object[]) it.next();
			QhzuiBean bean = new QhzuiBean();
			if (obj[0] != null) bean.setDIST_CD(obj[0].toString());
			else  bean.setDIST_CD("");	
			if (obj[1] != null) bean.setQIANZUI_ID(obj[1].toString());
			else  bean.setQIANZUI_ID("");	
			if (obj[2] != null) bean.setTOTAL_DISTRICT_NAME(obj[2].toString());
			else  bean.setTOTAL_DISTRICT_NAME("");	
			if (obj[3] != null) bean.setFIX(obj[3].toString());
			else  bean.setFIX("");	
			if (obj[4] != null) bean.setFIX_ABBR(obj[4].toString());
			else  bean.setFIX_ABBR("");	
			if (obj[5] != null) bean.setFIX_XZ(obj[5].toString());
			else  bean.setFIX_XZ("");	
			if (obj[6] != null) bean.setFIX_SMP(obj[6].toString());
			else  bean.setFIX_SMP("");	
			if (obj[7] != null) bean.setFIX_FLAG(obj[7].toString());
			else  bean.setFIX_FLAG("");	
			if (obj[7] != null) bean.setFIX_FLAGNAME(obj[7].toString());
			else  bean.setFIX_FLAGNAME("");	
			beans.add(bean);
		}
		page.setResult(beans);
		page.setAjaxUrl(PageUtils.getPageUrl(page, "AJAx"));
		return page;
	}
	
	@Transactional(readOnly = true)
	public QhzuiBean getQhzuis(String DIST_CD,String QIANZUI_ID) {
		String sql="select a.DIST_CD,a.QIANZUI_ID,a.FIX,a.FIX_ABBR,a.FIX_XZ,a.FIX_FLAG,a.FIX_SMP " +
				" from CP_WH_QHZUI a " +
				" where a.QIANZUI_ID =" + QIANZUI_ID;
		List<?> list = getSession().createSQLQuery(sql).list();
		Iterator<?> it = list.iterator();
		QhzuiBean bean = new QhzuiBean();
		if(it.hasNext()){
			Object[] obj = (Object[]) it.next();
			if (obj[0] != null) bean.setDIST_CD(obj[0].toString());
			else  bean.setDIST_CD("");
			if (obj[1] != null) bean.setQIANZUI_ID(obj[1].toString());
			else  bean.setQIANZUI_ID("");
			if (obj[2] != null) bean.setFIX(obj[2].toString());
			else  bean.setFIX("");
			if (obj[3] != null) bean.setFIX_ABBR(obj[3].toString());
			else  bean.setFIX_ABBR("");
			if (obj[4] != null) bean.setFIX_XZ(obj[4].toString());
			else  bean.setFIX_XZ("");
			if (obj[5] != null) bean.setFIX_FLAG(obj[5].toString());
			else  bean.setFIX_FLAG("");
			if (obj[6] != null) bean.setFIX_SMP(obj[6].toString());
			else  bean.setFIX_SMP("");
		}
		return bean;
	}
	
	@Transactional(readOnly = true)
	public  Page<ExgcltitemopeBean> getBeanQueryrgpqpd(String sql,String sqlCount,Page<ExgcltitemopeBean> page){
		if (page.isAutoCount()) {
			List<?> listCount=getSession().createSQLQuery(sqlCount).list();
			long totalCount =((BigDecimal)listCount.get(0)).longValue();
			page.setTotalCount(totalCount);
		}
		Query query=getSession().createSQLQuery(sql);
		query.setFirstResult(page.getFirst() - 1);
		query.setMaxResults(page.getPageSize());
		List<?> list = query.list();
		Iterator<?> it = list.iterator();
		List<ExgcltitemopeBean> beans=new ArrayList<ExgcltitemopeBean>();
		while (it.hasNext()) {
			Object[] obj = (Object[]) it.next();
			ExgcltitemopeBean bean = new ExgcltitemopeBean();
			if (obj[0] != null) bean.setSEQID(obj[0].toString());
			else  bean.setSEQID("");
			if (obj[1] != null) bean.setITEMNO(obj[1].toString());
			else  bean.setITEMNO("");
			if (obj[2] != null) bean.setCOLLECT_DATE(obj[2].toString());
			else  bean.setCOLLECT_DATE("");
			if (obj[3] != null) bean.setCOLLECT_OFFICE(obj[3].toString());
			else  bean.setCOLLECT_OFFICE("");
			if (obj[4] != null) bean.setDIST_CD(obj[4].toString());
			else  bean.setDIST_CD("");
			if (obj[5] != null) bean.setREC_POST(obj[5].toString());
			else  bean.setREC_POST("");
			if (obj[6] != null) bean.setREC_ALLADDR(obj[6].toString());
			else  bean.setREC_ALLADDR("");
			if (obj[7] != null) bean.setTOTAL_DISTRICT_NAME(obj[7].toString());
			else  bean.setTOTAL_DISTRICT_NAME("");
			if (obj[8] != null) bean.setDT_PK_CODE(obj[8].toString());
			else  bean.setDT_PK_CODE("");
			if (obj[9] != null) bean.setDM_PK_CODE(obj[9].toString());
			else  bean.setDM_PK_CODE("");
			if (obj[10] != null) bean.setPG_PK_CODE(obj[10].toString());
			else  bean.setPG_PK_CODE("");
			if (obj[11] != null) bean.setDT_NAME(obj[11].toString());
			else  bean.setDT_NAME("");
			if (obj[12] != null) bean.setDM_NAME(obj[12].toString());
			else  bean.setDM_NAME("");
			if (obj[13] != null) bean.setPG_NAME(obj[13].toString());
			else  bean.setPG_NAME("");
			if (obj[14] != null) bean.setPK_REMARK(obj[14].toString());
			else  bean.setPK_REMARK("");
			if (obj[15] != null) bean.setPOSTDIST(obj[15].toString());
			else  bean.setPOSTDIST("");
			if (obj[16] != null) bean.setPOSTSEG(obj[16].toString());
			else  bean.setPOSTSEG("");
			if (obj[17] != null) bean.setPOSTDIST_NAME(obj[17].toString());
			else  bean.setPOSTDIST_NAME("");
			if (obj[18] != null) bean.setPOSTSEG_NAME(obj[18].toString());
			else  bean.setPOSTSEG_NAME("");
			if (obj[19] != null) bean.setPK_ALLADDR(obj[19].toString());
			else  bean.setPK_ALLADDR("");
			if (obj[20] != null) bean.setREC_STREET(obj[20].toString());
			else  bean.setREC_STREET("");
			if (obj[21] != null) bean.setREC_ORG(obj[21].toString());
			else  bean.setREC_ORG("");
			if (obj[22] != null) bean.setADDR_FLAG(obj[22].toString());
			else  bean.setADDR_FLAG("");
			if (obj[23] != null) bean.setORG_NAME(obj[23].toString());
			else  bean.setORG_NAME("");
			if (obj[24] != null) bean.setPK_TIME(obj[24].toString());
			else  bean.setPK_TIME("");
			if (obj[25] != null) bean.setINSERTTIME(obj[25].toString());
			else  bean.setINSERTTIME("");
			beans.add(bean);
		}
		page.setResult(beans);
		page.setAjaxUrl(PageUtils.getPageUrl(page, "AJAx"));
		return page;
	}
	
	@Transactional(readOnly = true)
	public ExgcltitemopeBean getRgming(String SEQID) {
		String sql="select a.SEQID,a.ITEMNO,decode(a.ADDR_FLAG,1,a.REC_ALLADDR,0,a.REC_STREET,a.REC_ALLADDR) REC_ALLADDR,a.REC_ORG,a.DT_PK_CODE,a.DM_PK_CODE,a.PG_PK_CODE  "
		+ "  from exg_clt_item_ope a "
		+ " where  "
		+ " a.SEQID =" + SEQID;
		List<?> list = getSession().createSQLQuery(sql).list();
		Iterator<?> it = list.iterator();
		ExgcltitemopeBean bean = new ExgcltitemopeBean();
		if(it.hasNext()){
			Object[] obj = (Object[]) it.next();
			if (obj[0] != null) bean.setSEQID(obj[0].toString());
			else  bean.setSEQID("");
			if (obj[1] != null) bean.setITEMNO(obj[1].toString());
			else  bean.setITEMNO("");
			if (obj[2] != null) bean.setREC_ALLADDR(obj[2].toString());
			else  bean.setREC_ALLADDR("");	
			if (obj[3] != null) bean.setREC_ORG(obj[3].toString());
			else  bean.setREC_ORG("");
			if (obj[4] != null) bean.setDT_PK_CODE(obj[4].toString());
			else  bean.setDT_PK_CODE("");
			if (obj[5] != null) bean.setDM_PK_CODE(obj[5].toString());
			else  bean.setDM_PK_CODE("");	
			if (obj[6] != null) bean.setPG_PK_CODE(obj[6].toString());
			else  bean.setPG_PK_CODE("");
		}
		return bean;
	}
	
	@Transactional(readOnly = true)
	public  Page<ExgcltitemopeBean> getBeanQuerycksr(String sql,String sqlCount,Page<ExgcltitemopeBean> page){
		if (page.isAutoCount()) {
			List<?> listCount=getSession().createSQLQuery(sqlCount).list();
			long totalCount =((BigDecimal)listCount.get(0)).longValue();
			page.setTotalCount(totalCount);
		}
		Query query=getSession().createSQLQuery(sql);
		query.setFirstResult(page.getFirst() - 1);
		query.setMaxResults(page.getPageSize());
		List<?> list = query.list();
		Iterator<?> it = list.iterator();
		List<ExgcltitemopeBean> beans=new ArrayList<ExgcltitemopeBean>();
		while (it.hasNext()) {
			Object[] obj = (Object[]) it.next();
			ExgcltitemopeBean bean = new ExgcltitemopeBean();
			if (obj[0] != null) bean.setSEQID(obj[0].toString());
			else  bean.setSEQID("");
			if (obj[1] != null) bean.setITEMNO(obj[1].toString());
			else  bean.setITEMNO("");
			if (obj[2] != null) bean.setCOLLECT_DATE(obj[2].toString());
			else  bean.setCOLLECT_DATE("");
			if (obj[3] != null) bean.setCOLLECT_OFFICE(obj[3].toString());
			else  bean.setCOLLECT_OFFICE("");
			if (obj[4] != null) bean.setDIST_CD(obj[4].toString());
			else  bean.setDIST_CD("");
			if (obj[5] != null) bean.setREC_POST(obj[5].toString());
			else  bean.setREC_POST("");
			if (obj[6] != null) bean.setREC_ALLADDR(obj[6].toString());
			else  bean.setREC_ALLADDR("");
			if (obj[7] != null) bean.setTOTAL_DISTRICT_NAME(obj[7].toString());
			else  bean.setTOTAL_DISTRICT_NAME("");
			if (obj[8] != null) bean.setPK_ALLADDR(obj[8].toString());
			else  bean.setPK_ALLADDR("");
			if (obj[9] != null) bean.setREC_STREET(obj[9].toString());
			else  bean.setREC_STREET("");
			if (obj[10] != null) bean.setREC_ORG(obj[10].toString());
			else  bean.setREC_ORG("");
			if (obj[11] != null) bean.setADDR_FLAG(obj[11].toString());
			else  bean.setADDR_FLAG("");
			if (obj[12] != null) bean.setORG_NAME(obj[12].toString());
			else  bean.setORG_NAME("");
			if (obj[13] != null) bean.setPOSTDIST_NAME(obj[13].toString());
			else  bean.setPOSTDIST_NAME("");
			beans.add(bean);
		}
		page.setResult(beans);
		page.setAjaxUrl(PageUtils.getPageUrl(page, "AJAx"));
		return page;
	}
	@Transactional(readOnly = true)
	public  String exportcksr(String sql){
		Query query=getSession().createSQLQuery(sql);
		//Query query=getSession().createSQLQuery("select 1 from dual");
		List<?> list = query.list();
		
		Exporttjpqb exporttjpqb = new Exporttjpqb();
		return exporttjpqb.exportall(list);
	}
	@Transactional(readOnly = true)
	public  Page<CpwhrlpgstBean> getBeanQueryZshf(String sql,String sqlCount,Page<CpwhrlpgstBean> page){
		if (page.isAutoCount()) {
			List<?> listCount=getSession().createSQLQuery(sqlCount).list();
			long totalCount =((BigDecimal)listCount.get(0)).longValue();
			page.setTotalCount(totalCount);
		}
		Query query=getSession().createSQLQuery(sql);
		query.setFirstResult(page.getFirst() - 1);
		query.setMaxResults(page.getPageSize());
		List<?> list = query.list();
		Iterator<?> it = list.iterator();
		List<CpwhrlpgstBean> beans=new ArrayList<CpwhrlpgstBean>();
		while (it.hasNext()) {
			Object[] obj = (Object[]) it.next();
			CpwhrlpgstBean bean = new CpwhrlpgstBean();
			if (obj[0] != null) bean.setRL_PG_ST_ID(obj[0].toString());
			else  bean.setRL_PG_ST_ID("");	
			if (obj[1] != null) bean.setDIST_CD(obj[1].toString());
			else  bean.setDIST_CD("");	
			if (obj[2] != null) bean.setTOTAL_DISTRICT_NAME(obj[2].toString());
			else  bean.setTOTAL_DISTRICT_NAME("");
			if (obj[3] != null) bean.setSTRT_ID(obj[3].toString());
			else  bean.setSTRT_ID("");
			if (obj[4] != null) bean.setTOTAL_STREET_NAME(obj[4].toString());
			else  bean.setTOTAL_STREET_NAME("");
			if (obj[5] != null) bean.setPOST_CODE(obj[5].toString());
			else  bean.setPOST_CODE("");	
			if (obj[6] != null) bean.setDT_PK_CODE(obj[6].toString());
			else  bean.setDT_PK_CODE("");
			if (obj[7] != null) bean.setDT_NAME(obj[7].toString());
			else  bean.setDT_NAME("");
			if (obj[8] != null) bean.setDM_PK_CODE(obj[8].toString());
			else  bean.setDM_PK_CODE("");
			if (obj[9] != null) bean.setDM_NAME(obj[9].toString());
			else  bean.setDM_NAME("");
			if (obj[10] != null) bean.setPG_PK_CODE(obj[10].toString());
			else  bean.setPG_PK_CODE("");
			if (obj[11] != null) bean.setPG_NAME(obj[11].toString());
			else  bean.setPG_NAME("");
			if (obj[12] != null) bean.setPREFIX(obj[12].toString());
			else  bean.setPREFIX("");
			if (obj[13] != null) bean.setSTART_NUM(obj[13].toString());
			else  bean.setSTART_NUM("");
			if (obj[14] != null) bean.setEND_NUM(obj[14].toString());
			else  bean.setEND_NUM("");
			if (obj[15] != null) bean.setALL_NUM(obj[15].toString());
			else  bean.setALL_NUM("");
			if (obj[16] != null) bean.setSUFFIX(obj[16].toString());
			else  bean.setSUFFIX("");
			if (obj[17] != null) bean.setRSDNBLDG_ID(obj[17].toString());
			else  bean.setRSDNBLDG_ID("");
			if (obj[18] != null) bean.setRSDNBLDG_NAME(obj[18].toString());
			else  bean.setRSDNBLDG_NAME("");
			if (obj[19] != null) bean.setORG_ID(obj[19].toString());
			else  bean.setORG_ID("");
			if (obj[20] != null) bean.setORG_NAME(obj[20].toString());
			else  bean.setORG_NAME("");
			if (obj[21] != null) bean.setFLAG(obj[21].toString());
			else  bean.setFLAG("");
			if (obj[22] != null) bean.setDATA_SOURCE(obj[22].toString());
			else  bean.setDATA_SOURCE("");
			if (obj[23] != null) bean.setVERIFY_FLAG(obj[23].toString());
			else  bean.setVERIFY_FLAG("");
			
			if (obj[24] != null) bean.setMOD_DATE(obj[24].toString());
			else  bean.setMOD_DATE("");
			if (obj[25] != null) bean.setOPERATOR(obj[25].toString());
			else  bean.setOPERATOR("");
			beans.add(bean);
		}
		page.setResult(beans);
		page.setAjaxUrl(PageUtils.getPageUrl(page, "AJAx"));
		return page;
	}
	
	
	@Transactional(readOnly = true)
	public  Page<PYDKHGZCXEXPORTBean> getBeanQuerygonggao(String sql,String sqlCount,Page<PYDKHGZCXEXPORTBean> page){
		if (page.isAutoCount()) {
			List<?> listCount=getSession().createSQLQuery(sqlCount).list();
			long totalCount =((BigDecimal)listCount.get(0)).longValue();
			page.setTotalCount(totalCount);
		}
		Query query=getSession().createSQLQuery(sql);
		query.setFirstResult(page.getFirst() - 1);
		query.setMaxResults(page.getPageSize());
		List<?> list = query.list();
		Iterator<?> it = list.iterator();
		List<PYDKHGZCXEXPORTBean> beans=new ArrayList<PYDKHGZCXEXPORTBean>();
		while (it.hasNext()) {
			Object[] obj = (Object[]) it.next();
			PYDKHGZCXEXPORTBean bean = new PYDKHGZCXEXPORTBean();
			if (obj[0] != null) bean.setGZCX_EXZT(obj[0].toString());
			else  bean.setGZCX_EXZT("");
			if (obj[1] != null) bean.setGZCX_EXCX(obj[1].toString());
			else  bean.setGZCX_EXCX("");
			if (obj[2] != null) bean.setGZCX_EXPS(obj[2].toString());
			else  bean.setGZCX_EXPS("");
			if (obj[3] != null) bean.setGZCX_FLAG(obj[3].toString());
			else  bean.setGZCX_FLAG("");
			if (obj[4] != null) bean.setGZCX_EXRQ(obj[4].toString());
			else  bean.setGZCX_EXRQ("");
			if (obj[5] != null) bean.setGZCX_ID(obj[5].toString());
			else  bean.setGZCX_ID("");
			beans.add(bean);
		}
		page.setResult(beans);
		
		page.setAjaxUrl(PageUtils.getPageUrl(page, "AJAx"));
		
		return page;
	}
	
	@Transactional(readOnly = true)
	public  Page<TBSRPROCESSBean> getBeanQueryfkqr(String sql,String sqlCount,Page<TBSRPROCESSBean> page){
		if (page.isAutoCount()) {
			List<?> listCount=getSession().createSQLQuery(sqlCount).list();
			long totalCount =((BigDecimal)listCount.get(0)).longValue();
			page.setTotalCount(totalCount);
		}
		Query query=getSession().createSQLQuery(sql);
		query.setFirstResult(page.getFirst() - 1);
		query.setMaxResults(page.getPageSize());
		List<?> list = query.list();
		Iterator<?> it = list.iterator();
		List<TBSRPROCESSBean> beans=new ArrayList<TBSRPROCESSBean>();
		while (it.hasNext()) {
			Object[] obj = (Object[]) it.next();
			TBSRPROCESSBean bean = new TBSRPROCESSBean();
			if (obj[0] != null) bean.setSEQID(obj[0].toString());
			else  bean.setSEQID("");
			if (obj[1] != null) bean.setDIST_CD(obj[1].toString());
			else  bean.setDIST_CD("");
			if (obj[2] != null) bean.setDIST_CD_STR(obj[2].toString());
			else  bean.setDIST_CD_STR("");
			if (obj[3] != null) bean.setDT_PK_CODE(obj[3].toString());
			else  bean.setDT_PK_CODE("");
			if (obj[4] != null) bean.setDT_PK_CODE_STR(obj[4].toString());
			else  bean.setDT_PK_CODE_STR("");
			if (obj[5] != null) bean.setDM_PK_CODE(obj[5].toString());
			else  bean.setDM_PK_CODE("");
			if (obj[6] != null) bean.setDM_PK_CODE_STR(obj[6].toString());
			else  bean.setDM_PK_CODE_STR("");
			if (obj[7] != null) bean.setPG_PK_CODE(obj[7].toString());
			else  bean.setPG_PK_CODE("");
			if (obj[8] != null) bean.setPG_PK_CODE_STR(obj[8].toString());
			else  bean.setPG_PK_CODE_STR("");
			if (obj[9] != null) bean.setCOLLECT_DATE(obj[9].toString());
			else  bean.setCOLLECT_DATE("");
			if (obj[10] != null) bean.setIS_PROCESS(obj[10].toString());
			else  bean.setIS_PROCESS("");
			if (obj[11] != null) bean.setALLADDR(obj[11].toString());
			else  bean.setALLADDR("");
			if (obj[12] != null) bean.setT_ALLADDR(obj[12].toString());
			else  bean.setT_ALLADDR("");
			if (obj[13] != null) bean.setPG_ALLADDR(obj[13].toString());
			else  bean.setPG_ALLADDR("");
			if (obj[14] != null) bean.setPG_FLAG(obj[14].toString());
			else  bean.setPG_FLAG("");
			if (obj[15] != null) bean.setSOURCE(obj[15].toString());
			else  bean.setSOURCE("");
			if (obj[16] != null) bean.setSINTIME(obj[16].toString());
			else  bean.setSINTIME("");
			if (obj[17] != null) bean.setSUSEFLAG(obj[17].toString());
			else  bean.setSUSEFLAG("");
			if (obj[18] != null) bean.setSUSEFLAG_STR(obj[18].toString());
			else  bean.setSUSEFLAG_STR("");
			beans.add(bean);
		}
		page.setResult(beans);
		
		page.setAjaxUrl(PageUtils.getPageUrl(page, "AJAx"));
		
		return page;
	}
}
