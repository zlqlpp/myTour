package com.cpst.emsadrdb.dao.disp;

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
import com.cpst.emsadrdb.entity.disp.DispBean;
import com.cpst.emsadrdb.entity.disp.DistrictBean;
import com.cpst.emsadrdb.entity.disp.ItemnoBean;
import com.cpst.emsadrdb.entity.disp.STATECIOPEDTBean;
import com.cpst.emsadrdb.entity.disp.TJcityqbBean;
import com.cpst.emsadrdb.entity.disp.TJprovinceqbBean;
import com.cpst.emsadrdb.entity.disp.TJrbBean;
import com.cpst.emsadrdb.entity.disp.TransBean;
import com.cpst.emsadrdb.entity.disp.WhdistrictBean;
import com.cpst.emsadrdb.service.export.Exporttjpqb;


@Repository
@Transactional
public class DispQueryDao extends HibernateDao<DistrictBean,String>{
	
	@Transactional(readOnly = true)
	public  long getQueryCount(String sql){
		List<?> listCount=getSession().createSQLQuery(sql).list();
		long totalCount =((BigDecimal)listCount.get(0)).longValue();
		return totalCount;
	}
	
	@Transactional(readOnly = true)
	public  List<?> getqueryexportmethod(String sql){
		Query query=getSession().createSQLQuery(sql);
		List<?> list = query.list();
		return list;
	}
	
	@Transactional(readOnly = true)
	public  Page<DispBean> getBeanQueryDisps(String sql,String sqlCount,Page<DispBean> page){
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
		List<DispBean> beans=new ArrayList<DispBean>();
		while (it.hasNext()) {
			Object[] obj = (Object[]) it.next();
			DispBean bean = new DispBean();
			if (obj[0] != null) bean.setDISP_OFFICE_CODE(obj[0].toString());
			else  bean.setDATA_FLAG("");
			if (obj[1] != null) bean.setDISP_OFFICE_NAME(obj[1].toString());
			else  bean.setDISP_OFFICE_NAME("");
			if (obj[2] != null) bean.setPROVINCE_CODE(obj[2].toString());
			else  bean.setPROVINCE_CODE("");	
			if (obj[3] != null) bean.setPROVINCE_NAME(obj[3].toString());
			else  bean.setPROVINCE_NAME("");
			if (obj[4] != null) bean.setDEGREE(obj[4].toString());
			else  bean.setDEGREE("");
			if (obj[5] != null) bean.setTOTAL_TRANS_NAME(obj[5].toString());
			else  bean.setTOTAL_TRANS_NAME("");
			if (obj[6] != null) bean.setTOTAL_WH(obj[6].toString());
			else  bean.setTOTAL_WH("");
			if (obj[7] != null) bean.setDISP_OFFICE_ABBR(obj[7].toString());
			else  bean.setDISP_OFFICE_ABBR("");
			if (obj[8] != null) bean.setTOTAL_TRANS_CHSNAME(obj[8].toString());
			else  bean.setTOTAL_TRANS_CHSNAME("");
			if (obj[9] != null) bean.setTOTAL_TRANS_CITY_CHSNAME(obj[9].toString());
			else  bean.setTOTAL_TRANS_CITY_CHSNAME("");
			beans.add(bean);
		}
		page.setResult(beans);
		page.setAjaxUrl(PageUtils.getPageUrl(page, "AJAx"));
		return page;
	}
	
	@Transactional(readOnly = true)
	public DispBean getBeanQueryDisp(String DISP_OFFICE_CODE) {
		String sql="select a.DISP_OFFICE_CODE,a.DISP_OFFICE_NAME,a.PROVINCE_CODE,a.PROVINCE_NAME,a.DEGREE ," +
		" a.DISP_OFFICE_ABBR " +
		" from CP_BASE_DISP_OFFICE a where a.DISP_OFFICE_CODE = " + DISP_OFFICE_CODE;
		List<?> list = getSession().createSQLQuery(sql).list();
		Iterator<?> it = list.iterator();
		DispBean bean = new DispBean();
		if(it.hasNext()){
			Object[] obj = (Object[]) it.next();
			if (obj[0] != null) bean.setDISP_OFFICE_CODE(obj[0].toString());
			else  bean.setDATA_FLAG("");
			if (obj[1] != null) bean.setDISP_OFFICE_NAME(obj[1].toString());
			else  bean.setDISP_OFFICE_NAME("");
			if (obj[2] != null) bean.setPROVINCE_CODE(obj[2].toString());
			else  bean.setPROVINCE_CODE("");	
			if (obj[3] != null) bean.setPROVINCE_NAME(obj[3].toString());
			else  bean.setPROVINCE_NAME("");
			if (obj[4] != null) bean.setDEGREE(obj[4].toString());
			else  bean.setDEGREE("");
			if (obj[5] != null) bean.setDISP_OFFICE_ABBR(obj[5].toString());
			else  bean.setDISP_OFFICE_ABBR("");
		}
		return bean;
	}
	
	@Transactional(readOnly = true)
	public  Page<WhdistrictBean> getBeanQueryTddispp(String sql,String sqlCount,Page<WhdistrictBean> page){
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
		List<WhdistrictBean> beans=new ArrayList<WhdistrictBean>();
		while (it.hasNext()) {
			Object[] obj = (Object[]) it.next();
			WhdistrictBean bean = new WhdistrictBean();
			if (obj[0] != null) bean.setDT_NAME(obj[0].toString());
			else  bean.setDT_NAME("");
			if (obj[1] != null) bean.setDT_ALIAS_NAME(obj[1].toString());
			else  bean.setDT_ALIAS_NAME("");
			beans.add(bean);
		}
		page.setResult(beans);
		page.setAjaxUrl(PageUtils.getPageUrl(page, "AJAx"));
		return page;
	}
	
	@Transactional(readOnly = true)
	public  Page<TransBean> getBeanQueryTrans(String sql,String sqlCount,Page<TransBean> page){
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
		List<TransBean> beans=new ArrayList<TransBean>();
		while (it.hasNext()) {
			Object[] obj = (Object[]) it.next();
			TransBean bean = new TransBean();
			if (obj[0] != null) bean.setTRANS_CODE(obj[0].toString());
			else  bean.setTRANS_CODE("");
			if (obj[1] != null) bean.setTRANS_NAME(obj[1].toString());
			else  bean.setTRANS_NAME("");	
			if (obj[2] != null) bean.setPROVINCE_CODE(obj[2].toString());
			else  bean.setPROVINCE_CODE("");	
			if (obj[3] != null) bean.setPROVINCE_NAME(obj[3].toString());
			else  bean.setPROVINCE_NAME("");
			if (obj[4] != null) bean.setDISP_OFFICE_CODE(obj[4].toString());
			else  bean.setDATA_FLAG("");
			if (obj[5] != null) bean.setDEGREE(obj[5].toString());
			else  bean.setDEGREE("");
			if (obj[6] != null) bean.setFLAG(obj[6].toString());
			else  bean.setFLAG("");
			if (obj[6] != null) bean.setFLAG_NAME(obj[6].toString());
			else  bean.setFLAG_NAME("");
			beans.add(bean);
		}
		page.setResult(beans);
		page.setAjaxUrl(PageUtils.getPageUrl(page, "AJAx"));
		return page;
	}
	
	@Transactional(readOnly = true)
	public  Page<TransBean> getBeanQueryWhtrans(String sql,String sqlCount,Page<TransBean> page){
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
		List<TransBean> beans=new ArrayList<TransBean>();
		while (it.hasNext()) {
			Object[] obj = (Object[]) it.next();
			TransBean bean = new TransBean();
			if (obj[0] != null) bean.setTRANS_CODE(obj[0].toString());
			else  bean.setTRANS_CODE("");
			if (obj[1] != null) bean.setTRANS_NAME(obj[1].toString());
			else  bean.setTRANS_NAME("");	
			if (obj[2] != null) bean.setPROVINCE_CODE(obj[2].toString());
			else  bean.setPROVINCE_CODE("");	
			if (obj[3] != null) bean.setPROVINCE_NAME(obj[3].toString());
			else  bean.setPROVINCE_NAME("");
			if (obj[4] != null) bean.setDISP_OFFICE_CODE(obj[4].toString());
			else  bean.setDATA_FLAG("");
			if (obj[5] != null) bean.setDEGREE(obj[5].toString());
			else  bean.setDEGREE("");
			if (obj[6] != null) bean.setFLAG(obj[6].toString());
			else  bean.setFLAG("");
			if (obj[6] != null) bean.setFLAG_NAME(obj[6].toString());
			else  bean.setFLAG_NAME("");
			if (obj[7] != null) bean.setDISP_OFFICE_NAME(obj[7].toString());
			else  bean.setDISP_OFFICE_NAME("");
			if (obj[8] != null) bean.setDT_NAME(obj[8].toString());
			else  bean.setDT_NAME("");
			beans.add(bean);
		}
		page.setResult(beans);
		page.setAjaxUrl(PageUtils.getPageUrl(page, "AJAx"));
		return page;
	}
	
	@Transactional(readOnly = true)
	public TransBean getBeanQueryTrans(String TRANS_CODE) {
		String sql="select a.TRANS_CODE,a.TRANS_NAME,a.PROVINCE_CODE,a.PROVINCE_NAME,a.DISP_OFFICE_CODE,b.DISP_OFFICE_NAME,a.DEGREE ," +
			" CASE WHEN (length(a.trans_code) > 0 )  THEN (select MAX(c.PROVINCE_NAME||c.CITY_NAME||c.COUNTY_NAME) from CP_BASE_ORG_DISTRICT c  where c.DISTRICT_CODE = a.trans_code) END AS TOTAL_DISTRICT_NAME "+
			" from CP_BASE_TRANS_OFFICE a,CP_BASE_DISP_OFFICE b " +
			" where a.TRANS_CODE = " + TRANS_CODE;
		List<?> list = getSession().createSQLQuery(sql).list();
		Iterator<?> it = list.iterator();
		TransBean bean = new TransBean();
		if(it.hasNext()){
			Object[] obj = (Object[]) it.next();
			if (obj[0] != null) bean.setTRANS_CODE(obj[0].toString());
			else  bean.setTRANS_CODE("");
			if (obj[1] != null) bean.setTRANS_NAME(obj[1].toString());
			else  bean.setTRANS_NAME("");	
			if (obj[2] != null) bean.setPROVINCE_CODE(obj[2].toString());
			else  bean.setPROVINCE_CODE("");	
			if (obj[3] != null) bean.setPROVINCE_NAME(obj[3].toString());
			else  bean.setPROVINCE_NAME("");
			if (obj[4] != null) bean.setDISP_OFFICE_CODE(obj[4].toString());
			else  bean.setDATA_FLAG("");
			if (obj[5] != null) bean.setDISP_OFFICE_NAME(obj[5].toString());
			else  bean.setDISP_OFFICE_NAME("");
			if (obj[6] != null) bean.setDEGREE(obj[6].toString());
			else  bean.setDEGREE("");
			if (obj[7] != null) bean.setTOTAL_DISTRICT_NAME(obj[7].toString());
			else  bean.setTOTAL_DISTRICT_NAME("");
		}
		return bean;
	}
	
	@Transactional(readOnly = true)
	public  Page<DistrictBean> getBeanQueryDistrict(String sql,String sqlCount,Page<DistrictBean> page){
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
		List<DistrictBean> beans=new ArrayList<DistrictBean>();
		while (it.hasNext()) {
			Object[] obj = (Object[]) it.next();
			DistrictBean bean = new DistrictBean();
			if (obj[0] != null) bean.setDISTRICT_CODE(obj[0].toString());
			else  bean.setDISTRICT_CODE("");
			if (obj[1] != null) bean.setPROVINCE_NAME(obj[1].toString());
			else  bean.setPROVINCE_NAME("");	
			if (obj[2] != null) bean.setCITY_NAME(obj[2].toString());
			else  bean.setCITY_NAME("");	
			if (obj[3] != null) bean.setCOUNTY_NAME(obj[3].toString());
			else  bean.setCOUNTY_NAME("");
			if (obj[4] != null) bean.setCITY_FLAG(obj[4].toString());
			else  bean.setCITY_FLAG("");
			if (obj[4] != null) bean.setCITY_FLAGNAME(obj[4].toString());
			else  bean.setCITY_FLAGNAME("");
			beans.add(bean);
		}
		page.setResult(beans);
		page.setAjaxUrl(PageUtils.getPageUrl(page, "AJAx"));
		return page;
	}
	
	@Transactional(readOnly = true)
	public  Page<TJprovinceqbBean> getBeanQuerytjpqb(String sql,String sqlCount,Page<TJprovinceqbBean> page){
		if (page.isAutoCount()) {
			//List<?> listCount=getSession().createSQLQuery(sqlCount).list();
			//long totalCount =((BigDecimal)listCount.get(0)).longValue();
			long totalCount = 32;
			page.setTotalCount(totalCount);
		}
		Query query=getSession().createSQLQuery(sql);
		query.setFirstResult(page.getFirst() - 1);
		query.setMaxResults(page.getPageSize());
		List<?> list = query.list();
		Iterator<?> it = list.iterator();
		List<TJprovinceqbBean> beans=new ArrayList<TJprovinceqbBean>();
		while (it.hasNext()) {
			Object[] obj = (Object[]) it.next();
			TJprovinceqbBean bean = new TJprovinceqbBean();
			if (obj[0] != null) bean.setDISTRICT_CODE(obj[0].toString());
			else  bean.setDISTRICT_CODE("");
			if (obj[1] != null) bean.setPROVINCE_NAME(obj[1].toString());
			else  bean.setPROVINCE_NAME("");	
			if (obj[2] != null) bean.setSJ_CODE(obj[2].toString());
			else  bean.setSJ_CODE("");	
			if (obj[3] != null) bean.setDT_CODE(obj[3].toString());
			else  bean.setDT_CODE("");
			if (obj[4] != null) bean.setDT_SJ_CODE(obj[4].toString());
			else  bean.setDT_SJ_CODE("");
			if (obj[5] != null) bean.setDT_SJNO_CODE(obj[5].toString());
			else  bean.setDT_SJNO_CODE("");
			if (obj[6] != null) bean.setPG_CODE(obj[6].toString());
			else  bean.setPG_CODE("");
			if (obj[7] != null) bean.setPG_SJ_CODE(obj[7].toString());
			else  bean.setPG_SJ_CODE("");
			if (obj[8] != null) bean.setPG_SJNO_CODE(obj[8].toString());
			else  bean.setPG_SJNO_CODE("");
			if (obj[9] != null) bean.setDM_SJ_CODE(obj[9].toString());
			else  bean.setDM_SJ_CODE("");
			beans.add(bean);
		}
		page.setResult(beans);
		page.setAjaxUrl(PageUtils.getPageUrl(page, "AJAx"));
		return page;
	}
	
	@Transactional(readOnly = true)
	public  Page<TJcityqbBean> getBeanQuerytjcqb(String sql,String sqlCount,Page<TJcityqbBean> page){
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
		List<TJcityqbBean> beans=new ArrayList<TJcityqbBean>();
		while (it.hasNext()) {
			Object[] obj = (Object[]) it.next();
			TJcityqbBean bean = new TJcityqbBean();
			if (obj[0] != null) bean.setDISTRICT_CODE(obj[0].toString());
			else  bean.setDISTRICT_CODE("");
			if (obj[1] != null) bean.setPROVINCE_NAME(obj[1].toString());
			else  bean.setPROVINCE_NAME("");	
			if (obj[2] != null) bean.setCITY_NAME(obj[2].toString());
			else  bean.setCITY_NAME("");
			if (obj[3] != null) bean.setSJ_CODE(obj[3].toString());
			else  bean.setSJ_CODE("");	
			if (obj[4] != null) bean.setDT_CODE(obj[4].toString());
			else  bean.setDT_CODE("");
			if (obj[5] != null) bean.setDT_SJ_CODE(obj[5].toString());
			else  bean.setDT_SJ_CODE("");
			if (obj[6] != null) bean.setDT_SJNO_CODE(obj[6].toString());
			else  bean.setDT_SJNO_CODE("");
			if (obj[7] != null) bean.setPG_CODE(obj[7].toString());
			else  bean.setPG_CODE("");
			if (obj[8] != null) bean.setPG_SJ_CODE(obj[8].toString());
			else  bean.setPG_SJ_CODE("");
			if (obj[9] != null) bean.setPG_SJNO_CODE(obj[9].toString());
			else  bean.setPG_SJNO_CODE("");
			beans.add(bean);
		}
		page.setResult(beans);
		page.setAjaxUrl(PageUtils.getPageUrl(page, "AJAx"));
		return page;
	}
	
	@Transactional(readOnly = true)
	public  String getBeanQueryexporttjpqb(String sql){
		Query query=getSession().createSQLQuery(sql);
		//Query query=getSession().createSQLQuery("select 1 from dual");
		List<?> list = query.list();
		
		Exporttjpqb exporttjpqb = new Exporttjpqb();
		return exporttjpqb.exportall(list);
	}
	
	@Transactional(readOnly = true)
	public  Page<TJcityqbBean> getBeanQuerytjdtqb(String sql,String sqlCount,Page<TJcityqbBean> page){
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
		List<TJcityqbBean> beans=new ArrayList<TJcityqbBean>();
		while (it.hasNext()) {
			Object[] obj = (Object[]) it.next();
			TJcityqbBean bean = new TJcityqbBean();
			if (obj[0] != null) bean.setDISTRICT_CODE(obj[0].toString());
			else  bean.setDISTRICT_CODE("");
			if (obj[1] != null) bean.setPROVINCE_NAME(obj[1].toString());
			else  bean.setPROVINCE_NAME("");	
			if (obj[2] != null) bean.setCITY_NAME(obj[2].toString());
			else  bean.setCITY_NAME("");
			if (obj[3] != null) bean.setDT_CODE(obj[3].toString());
			else  bean.setDT_CODE("");
			if (obj[4] != null) bean.setDT_SJ_CODE(obj[4].toString());
			else  bean.setDT_SJ_CODE("");
			if (obj[5] != null) bean.setDM_SJ_CODE(obj[5].toString());
			else  bean.setDM_SJ_CODE("");
			if (obj[6] != null) bean.setPG_SJ_CODE(obj[6].toString());
			else  bean.setPG_SJ_CODE("");
			beans.add(bean);
		}
		page.setResult(beans);
		page.setAjaxUrl(PageUtils.getPageUrl(page, "AJAx"));
		return page;
	}
	
	@Transactional(readOnly = true)
	public  Page<TJcityqbBean> getBeanQuerytjtdqwhtj(String sql,String sqlCount,Page<TJcityqbBean> page){
		if (page.isAutoCount()) {
			//List<?> listCount=getSession().createSQLQuery(sqlCount).list();
			//long totalCount =((BigDecimal)listCount.get(0)).longValue();
			page.setTotalCount(0);
		}
		Query query=getSession().createSQLQuery(sql);
		query.setFirstResult(page.getFirst() - 1);
		query.setMaxResults(page.getPageSize());
		List<?> list = query.list();
		Iterator<?> it = list.iterator();
		List<TJcityqbBean> beans=new ArrayList<TJcityqbBean>();
		while (it.hasNext()) {
			Object[] obj = (Object[]) it.next();
			TJcityqbBean bean = new TJcityqbBean();
			if (obj[0] != null) bean.setPROVINCE_NAME(obj[0].toString());
			else  bean.setPROVINCE_NAME("");	
			if (obj[1] != null) bean.setCITY_NAME(obj[1].toString());
			else  bean.setCITY_NAME("");
			if (obj[2] != null) bean.setDT_CODE(obj[2].toString());
			else  bean.setDT_CODE("");
			if (obj[3] != null) bean.setDT_SJ_CODE(obj[3].toString());
			else  bean.setDT_SJ_CODE("");
			if (obj[4] != null) bean.setDT_SJNO_CODE(obj[4].toString());
			else  bean.setDT_SJNO_CODE("");
			if (obj[5] != null) bean.setDM_SJ_CODE(obj[5].toString());
			else  bean.setDM_SJ_CODE("");
			if (obj[6] != null) bean.setDM_SJNO_CODE(obj[6].toString());
			else  bean.setDM_SJNO_CODE("");
			beans.add(bean);
		}
		page.setResult(beans);
		page.setAjaxUrl(PageUtils.getPageUrl(page, "AJAx"));
		return page;
	}
	
	@Transactional(readOnly = true)
	public  Page<TJcityqbBean> getBeanQuerytjtdbwhtj(String sql,String sqlCount,Page<TJcityqbBean> page){
		if (page.isAutoCount()) {
			//List<?> listCount=getSession().createSQLQuery(sqlCount).list();
			//long totalCount =((BigDecimal)listCount.get(0)).longValue();
			page.setTotalCount(0);
		}
		Query query=getSession().createSQLQuery(sql);
		query.setFirstResult(page.getFirst() - 1);
		query.setMaxResults(page.getPageSize());
		List<?> list = query.list();
		Iterator<?> it = list.iterator();
		List<TJcityqbBean> beans=new ArrayList<TJcityqbBean>();
		while (it.hasNext()) {
			Object[] obj = (Object[]) it.next();
			TJcityqbBean bean = new TJcityqbBean();
			if (obj[0] != null) bean.setPROVINCE_NAME(obj[0].toString());
			else  bean.setPROVINCE_NAME("");	
			if (obj[1] != null) bean.setCITY_NAME(obj[1].toString());
			else  bean.setCITY_NAME("");
			if (obj[2] != null) bean.setDM_CODE(obj[2].toString());
			else  bean.setDM_CODE("");
			if (obj[3] != null) bean.setDM_SJ_CODE(obj[3].toString());
			else  bean.setDM_SJ_CODE("");
			if (obj[4] != null) bean.setDM_SJNO_CODE(obj[4].toString());
			else  bean.setDM_SJNO_CODE("");
			beans.add(bean);
		}
		page.setResult(beans);
		page.setAjaxUrl(PageUtils.getPageUrl(page, "AJAx"));
		return page;
	}
	
	@Transactional(readOnly = true)
	public  Page<WhdistrictBean> getBeanQuerytjdtpkisp(String sql,String sqlCount,Page<WhdistrictBean> page){
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
		List<WhdistrictBean> beans=new ArrayList<WhdistrictBean>();
		while (it.hasNext()) {
			Object[] obj = (Object[]) it.next();
			WhdistrictBean bean = new WhdistrictBean();
			if (obj[0] != null) bean.setDT_NAME(obj[0].toString());
			else  bean.setDT_NAME("");
			if (obj[1] != null) bean.setDT_ALIAS_NAME(obj[1].toString());
			else  bean.setDT_ALIAS_NAME("");
			if (obj[2] != null) bean.setDM_NAME(obj[2].toString());
			else  bean.setDM_NAME("");
			if (obj[3] != null) bean.setPG_NAME(obj[3].toString());
			else  bean.setPG_NAME("");
			beans.add(bean);
		}
		page.setResult(beans);
		page.setAjaxUrl(PageUtils.getPageUrl(page, "AJAx"));
		return page;
	}
	
	@Transactional(readOnly = true)
	public  Page<WhdistrictBean> getBeanQuerytjtdqqb(String sql,String sqlCount,Page<WhdistrictBean> page){
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
		List<WhdistrictBean> beans=new ArrayList<WhdistrictBean>();
		while (it.hasNext()) {
			Object[] obj = (Object[]) it.next();
			WhdistrictBean bean = new WhdistrictBean();
			if (obj[0] != null) bean.setDISTRICT_CODE(obj[0].toString());
			else  bean.setDISTRICT_CODE("");
			if (obj[1] != null) bean.setCITY_NAME(obj[1].toString());
			else  bean.setCITY_NAME("");
			if (obj[2] != null) bean.setDT_PK_CODE(obj[2].toString());
			else  bean.setDT_PK_CODE("");
			if (obj[3] != null) bean.setDT_NAME(obj[3].toString());
			else  bean.setDT_NAME("");
			if (obj[4] != null) bean.setDT_ALIAS_NAME(obj[4].toString());
			else  bean.setDT_ALIAS_NAME("");
			if (obj[5] != null) bean.setCOUNTY_NAME_SN(obj[5].toString());
			else  bean.setCOUNTY_NAME_SN("");
			if (obj[6] != null) bean.setCOUNTY_NAME_FN(obj[6].toString());
			else  bean.setCOUNTY_NAME_FN("");
			if (obj[7] != null) bean.setCOUNTY_NAME_SNYJ(obj[7].toString());
			else  bean.setCOUNTY_NAME_SNYJ("");
			if (obj[8] != null) bean.setCOUNTY_NAME_FNYJ(obj[8].toString());
			else  bean.setCOUNTY_NAME_FNYJ("");
			beans.add(bean);
		}
		page.setResult(beans);
		page.setAjaxUrl(PageUtils.getPageUrl(page, "AJAx"));
		return page;
	}
	
	
	@Transactional(readOnly = true)
	public  Page<TJrbBean> getBeanQuerytjprb(String sql,String sqlCount,Page<TJrbBean> page){
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
		List<TJrbBean> beans=new ArrayList<TJrbBean>();
		while (it.hasNext()) {
			Object[] obj = (Object[]) it.next();
			TJrbBean bean = new TJrbBean();
			if (obj[0] != null) bean.setDIST_CD(obj[0].toString());
			else  bean.setDIST_CD("");
			if (obj[1] != null) bean.setPROVINCE_NAME(obj[1].toString());
			else  bean.setPROVINCE_NAME("");
			if (obj[2] != null) bean.setCITY_NAME(obj[2].toString());
			else  bean.setCITY_NAME("");	
			if (obj[3] != null) bean.setITEM_DATE(obj[3].toString());
			else  bean.setITEM_DATE("");	
			if (obj[4] != null) bean.setTOTAL_COUNT(obj[4].toString());
			else  bean.setTOTAL_COUNT("");
			if (obj[5] != null) bean.setDISTRI_COUNT(obj[5].toString());
			else  bean.setDISTRI_COUNT("");
			if (obj[6] != null) bean.setADDR_COUNT(obj[6].toString());
			else  bean.setADDR_COUNT("");
			if (obj[7] != null) bean.setTDQ_COUNT(obj[7].toString());
			else  bean.setTDQ_COUNT("");
			if (obj[8] != null) bean.setADDRTDQ_COUNT(obj[8].toString());
			else  bean.setADDRTDQ_COUNT("");
			if (obj[9] != null) bean.setNOADDRTDQ_COUNT(obj[9].toString());
			else  bean.setNOADDRTDQ_COUNT("");
			if (obj[10] != null) bean.setNUM(obj[10].toString());
			else  bean.setNUM("");
			if (obj[11] != null) bean.setSRDIPQ_COUNT(obj[11].toString());
			else  bean.setSRDIPQ_COUNT("");
			beans.add(bean);
		}
		page.setResult(beans);
		page.setAjaxUrl(PageUtils.getPageUrl(page, "AJAx"));
		return page;
	}
	
	
	@Transactional(readOnly = true)
	public  Page<TJrbBean> getBeanQuerytjcrb(String sql,String sqlCount,Page<TJrbBean> page){
		if (page.isAutoCount()) {
			//List<?> listCount=getSession().createSQLQuery(sqlCount).list();
			//long totalCount =((BigDecimal)listCount.get(0)).longValue();
			long totalCount = 32;
			page.setTotalCount(totalCount);
		}
		Query query=getSession().createSQLQuery(sql);
		query.setFirstResult(page.getFirst() - 1);
		query.setMaxResults(page.getPageSize());
		List<?> list = query.list();
		Iterator<?> it = list.iterator();
		List<TJrbBean> beans=new ArrayList<TJrbBean>();
		while (it.hasNext()) {
			Object[] obj = (Object[]) it.next();
			TJrbBean bean = new TJrbBean();
			if (obj[0] != null) bean.setDIST_CD(obj[0].toString());
			else  bean.setDIST_CD("");
			if (obj[1] != null) bean.setPROVINCE_NAME(obj[1].toString());
			else  bean.setPROVINCE_NAME("");
			if (obj[2] != null) bean.setCITY_NAME(obj[2].toString());
			else  bean.setCITY_NAME("");	
			if (obj[3] != null) bean.setITEM_DATE(obj[3].toString());
			else  bean.setITEM_DATE("");	
			if (obj[4] != null) bean.setTOTAL_COUNT(obj[4].toString());
			else  bean.setTOTAL_COUNT("");
			if (obj[5] != null) bean.setDISTRI_COUNT(obj[5].toString());
			else  bean.setDISTRI_COUNT("");
			if (obj[6] != null) bean.setADDR_COUNT(obj[6].toString());
			else  bean.setADDR_COUNT("");
			if (obj[7] != null) bean.setTDQ_COUNT(obj[7].toString());
			else  bean.setTDQ_COUNT("");
			if (obj[8] != null) bean.setADDRTDQ_COUNT(obj[8].toString());
			else  bean.setADDRTDQ_COUNT("");
			if (obj[9] != null) bean.setNOADDRTDQ_COUNT(obj[9].toString());
			else  bean.setNOADDRTDQ_COUNT("");
			if (obj[10] != null) bean.setNUM(obj[10].toString());
			else  bean.setNUM("");
			if (obj[11] != null) bean.setSRDIPQ_COUNT(obj[11].toString());
			else  bean.setSRDIPQ_COUNT("");
			beans.add(bean);
		}
		page.setResult(beans);
		page.setAjaxUrl(PageUtils.getPageUrl(page, "AJAx"));
		return page;
	}
	
	@Transactional(readOnly = true)
	public  Page<TJrbBean> getBeanQuerytjpwhrb(String sql,String sqlCount,Page<TJrbBean> page){
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
		List<TJrbBean> beans=new ArrayList<TJrbBean>();
		while (it.hasNext()) {
			Object[] obj = (Object[]) it.next();
			TJrbBean bean = new TJrbBean();
			if (obj[0] != null) bean.setDIST_CD(obj[0].toString());
			else  bean.setDIST_CD("");
			if (obj[1] != null) bean.setPROVINCE_NAME(obj[1].toString());
			else  bean.setPROVINCE_NAME("");
			if (obj[2] != null) bean.setCITY_NAME(obj[2].toString());
			else  bean.setCITY_NAME("");	
			if (obj[3] != null) bean.setITEM_DATE(obj[3].toString());
			else  bean.setITEM_DATE("");	
			if (obj[4] != null) bean.setSR_COUNT(obj[4].toString());
			else  bean.setSR_COUNT("");
			if (obj[5] != null) bean.setADDR_SR_COUNT(obj[5].toString());
			else  bean.setADDR_SR_COUNT("");
			if (obj[6] != null) bean.setDRWH_COUNT(obj[6].toString());
			else  bean.setDRWH_COUNT("");
			if (obj[7] != null) bean.setCRWH_COUNT(obj[7].toString());
			else  bean.setCRWH_COUNT("");
			if (obj[8] != null) bean.setZRWH_COUNT(obj[8].toString());
			else  bean.setZRWH_COUNT("");
			if (obj[9] != null) bean.setNUM(obj[9].toString());
			else  bean.setNUM("");
			if (obj[10] != null) bean.setQBWH_COUNT(obj[10].toString());
			else  bean.setQBWH_COUNT("");
			
			beans.add(bean);
		}
		page.setResult(beans);
		page.setAjaxUrl(PageUtils.getPageUrl(page, "AJAx"));
		return page;
	}
	
	@Transactional(readOnly = true)
	public  Page<TJrbBean> getBeanQuerytjpwhrbopdn(String sql,String sqlCount,Page<TJrbBean> page){
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
		List<TJrbBean> beans=new ArrayList<TJrbBean>();
		while (it.hasNext()) {
			Object[] obj = (Object[]) it.next();
			TJrbBean bean = new TJrbBean();
			if (obj[0] != null) bean.setDIST_CD(obj[0].toString());
			else  bean.setDIST_CD("");
			if (obj[1] != null) bean.setPROVINCE_NAME(obj[1].toString());
			else  bean.setPROVINCE_NAME("");
			if (obj[2] != null) bean.setCITY_NAME(obj[2].toString());
			else  bean.setCITY_NAME("");	
			if (obj[3] != null) bean.setITEM_DATE(obj[3].toString());
			else  bean.setITEM_DATE("");	
			if (obj[4] != null) bean.setSR_COUNT(obj[4].toString());
			else  bean.setSR_COUNT("");
			if (obj[5] != null) bean.setADDR_SR_COUNT(obj[5].toString());
			else  bean.setADDR_SR_COUNT("");
			if (obj[6] != null) bean.setDRWH_COUNT(obj[6].toString());
			else  bean.setDRWH_COUNT("");
			if (obj[7] != null) bean.setCRWH_COUNT(obj[7].toString());
			else  bean.setCRWH_COUNT("");
			if (obj[8] != null) bean.setZRWH_COUNT(obj[8].toString());
			else  bean.setZRWH_COUNT("");
			if (obj[9] != null) bean.setNUM(obj[9].toString());
			else  bean.setNUM("");
			if (obj[10] != null) bean.setQBWH_COUNT(obj[10].toString());
			else  bean.setQBWH_COUNT("");
			if (obj[11] != null)  bean.setREMEAKTJ(obj[11].toString());
			else  bean.setREMEAKTJ("");
			beans.add(bean);
		}
		page.setResult(beans);
		page.setAjaxUrl(PageUtils.getPageUrl(page, "AJAx"));
		return page;
	}
	
	@Transactional(readOnly = true)
	public  Page<STATECIOPEDTBean> getBeanQueryTjdt(String sql,String sqlCount,Page<STATECIOPEDTBean> page){
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
		List<STATECIOPEDTBean> beans=new ArrayList<STATECIOPEDTBean>();
		while (it.hasNext()) {
			Object[] obj = (Object[]) it.next();
			STATECIOPEDTBean bean = new STATECIOPEDTBean();
			if (obj[0] != null) bean.setDIST_CD(obj[0].toString());
			else  bean.setDIST_CD("");
			if (obj[1] != null) bean.setPROVINCE_NAME(obj[1].toString());
			else  bean.setPROVINCE_NAME("");
			if (obj[2] != null) bean.setDT_PK_CODE(obj[2].toString());
			else  bean.setDT_PK_CODE("");
			if (obj[3] != null) bean.setDT_NAME(obj[3].toString());
			else  bean.setDT_NAME("");
			if (obj[4] != null) bean.setCCOUNT(obj[4].toString());
			else  bean.setCCOUNT("");
			if (obj[5] != null) bean.setNSRCOUNT(obj[5].toString());
			else  bean.setNSRCOUNT("");
			if (obj[6] != null) bean.setSRCOUNT(obj[6].toString());
			else  bean.setSRCOUNT("");
			if (obj[7] != null) bean.setNJXZ(obj[7].toString());
			else  bean.setNJXZ("");
			if (obj[8] != null) bean.setJDSX(obj[8].toString());
			else  bean.setJDSX("");
			beans.add(bean);
		}
		page.setResult(beans);
		page.setAjaxUrl(PageUtils.getPageUrl(page, "AJAx"));
		return page;
	}
	
	@Transactional(readOnly = true)
	public  Page<ItemnoBean> getBeanQueryitemnoxqcx(String sql,String sqlCount,Page<ItemnoBean> page){
		if (page.isAutoCount()) {
			//List<?> listCount=getSession().createSQLQuery(sqlCount).list();
			//long totalCount =((BigDecimal)listCount.get(0)).longValue();
			long totalCount = 99999;
			page.setTotalCount(totalCount);
		}
		Query query=getSession().createSQLQuery(sql);
		query.setFirstResult(page.getFirst() - 1);
		query.setMaxResults(page.getPageSize());
		List<?> list = query.list();
		Iterator<?> it = list.iterator();
		List<ItemnoBean> beans=new ArrayList<ItemnoBean>();
		while (it.hasNext()) {
			Object[] obj = (Object[]) it.next();
			ItemnoBean bean = new ItemnoBean();
			if (obj[0] != null) bean.setITEMNO(obj[0].toString());
			else  bean.setITEMNO("");
			if (obj[1] != null) bean.setREC_PROVCITY(obj[1].toString());
			else  bean.setREC_PROVCITY("");
			if (obj[2] != null) bean.setCOLLECT_DATE(obj[2].toString());
			else  bean.setCOLLECT_DATE("");
			if (obj[3] != null) bean.setCOLLECT_OFFICE(obj[3].toString());
			else  bean.setCOLLECT_OFFICE("");
			if (obj[4] != null) bean.setINSERTTIME(obj[4].toString());
			else  bean.setINSERTTIME("");
			if (obj[5] != null) bean.setREC_ALLADDR(obj[5].toString());
			else  bean.setREC_ALLADDR("");
			if (obj[6] != null) bean.setIS_DISTRI(obj[6].toString());
			else  bean.setIS_DISTRI("");
			if (obj[7] != null) bean.setPOSTDIST(obj[7].toString());
			else  bean.setPOSTDIST("");
			if (obj[8] != null) bean.setMATCH_DATETIME(obj[8].toString());
			else  bean.setMATCH_DATETIME("");
			if (obj[9] != null) bean.setSEND_FLAG(obj[9].toString());
			else  bean.setSEND_FLAG("");
			if (obj[10] != null) bean.setDT_NAME(obj[10].toString());
			else  bean.setDT_NAME("");
			if (obj[11] != null) bean.setDT_ALIAS_NAME(obj[11].toString());
			else  bean.setDT_ALIAS_NAME("");
			beans.add(bean);
		}
		page.setResult(beans);
		page.setAjaxUrl(PageUtils.getPageUrl(page, "AJAx"));
		return page;
	}
}
