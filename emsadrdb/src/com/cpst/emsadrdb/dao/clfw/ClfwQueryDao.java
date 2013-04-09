package com.cpst.emsadrdb.dao.clfw;

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
import com.cpst.emsadrdb.entity.clfw.CLFWBean;
import com.cpst.emsadrdb.service.clfw.ClfwCommon;

@Repository
@Transactional
public class ClfwQueryDao extends HibernateDao<CLFWBean, String> {

	@Transactional(readOnly = true)
	public long getQueryCount(String sql) {
		List<?> listCount = getSession().createSQLQuery(sql).list();
		long totalCount = ((BigDecimal) listCount.get(0)).longValue();
		return totalCount;
	}

	@Transactional(readOnly = true)
	public CLFWBean getClfwpc(String SEQID) {
		String sql = "select a.CLFWPC_SEQID,a.CLFWPC_MC,a.CLFWPC_SM,a.CLFWPC_SX,decode(a.CLFWPC_SX,0,'进口',1,'出口','未配置') CLFWPC_SXSTR "
				+ " from CLFW_CLFWPC a where  a.CLFWPC_SEQID = '" + SEQID + "'";
		List<?> list = getSession().createSQLQuery(sql).list();
		Iterator<?> it = list.iterator();
		CLFWBean bean = new CLFWBean();
		if (it.hasNext()) {
			Object[] obj = (Object[]) it.next();
				bean.setCLFWPC_SEQID(ClfwCommon.configcommon(obj[0]));
				bean.setCLFWPC_MC(ClfwCommon.configcommon(obj[1]));
				bean.setCLFWPC_SM(ClfwCommon.configcommon(obj[2]));
				bean.setCLFWPC_SX(ClfwCommon.configcommon(obj[3]));
				bean.setCLFWPC_SXSTR(ClfwCommon.configcommon(obj[4]));
		}
		return bean;
	}

	@Transactional(readOnly = true)
	public Page<CLFWBean> getBeanQueryClfwpc(String sql, String sqlCount,
			Page<CLFWBean> page) {
		if (page.isAutoCount()) {
			long totalCount = 99999;
			page.setTotalCount(totalCount);
		}
		Query query = getSession().createSQLQuery(sql);
		query.setFirstResult(page.getFirst() - 1);
		query.setMaxResults(page.getPageSize());
		List<?> list = query.list();
		Iterator<?> it = list.iterator();
		List<CLFWBean> beans = new ArrayList<CLFWBean>();
		while (it.hasNext()) {
			Object[] obj = (Object[]) it.next();
			CLFWBean bean = new CLFWBean();
				bean.setCLFWPC_SEQID(ClfwCommon.configcommon(obj[0]));
				bean.setCLFWPC_MC(ClfwCommon.configcommon(obj[1]));
				bean.setCLFWPC_SM(ClfwCommon.configcommon(obj[2]));
				bean.setCLFWPC_SX(ClfwCommon.configcommon(obj[3]));
				bean.setCLFWPC_SXSTR(ClfwCommon.configcommon(obj[4]));
			beans.add(bean);
		}
		page.setResult(beans);
		page.setAjaxUrl(PageUtils.getPageUrl(page, "AJAx"));
		return page;
	}
	
	@Transactional(readOnly = true)
	public CLFWBean getClfwpcsheng(String SEQID) {
		String sql = "select b.CLFWPCSHENG_SEQID,b.CLFWPC_SEQID,b.CLFWPCSHENG_DISTCD,b.CLFWPCSHENG_SHENGM,b.CLFWPCSHENG_SM,b.CLFWPCSHENG_JGDM "
				+ " from CLFW_CLFWPC_SHENG b where b.CLFWPCSHENG_SEQID = '" + SEQID + "'";
		List<?> list = getSession().createSQLQuery(sql).list();
		Iterator<?> it = list.iterator();
		CLFWBean bean = new CLFWBean();
		if (it.hasNext()) {
			Object[] obj = (Object[]) it.next();
				bean.setCLFWPCSHENG_SEQID(ClfwCommon.configcommon(obj[0]));
				bean.setCLFWPC_SEQID(ClfwCommon.configcommon(obj[1]));
				bean.setCLFWPCSHENG_DISTCD(ClfwCommon.configcommon(obj[2]));
				bean.setCLFWPCSHENG_SHENGM(ClfwCommon.configcommon(obj[3]));
				bean.setCLFWPCSHENG_SM(ClfwCommon.configcommon(obj[4]));
				bean.setCLFWPCSHENG_JGDM(ClfwCommon.configcommon(obj[5]));
		}
		return bean;
	}
	
	@Transactional(readOnly = true)
	public Page<CLFWBean> getBeanQueryClfwpcsheng(String sql, String sqlCount,
			Page<CLFWBean> page) {
		if (page.isAutoCount()) {
			long totalCount = 99999;
			page.setTotalCount(totalCount);
		}
		Query query = getSession().createSQLQuery(sql);
		query.setFirstResult(page.getFirst() - 1);
		query.setMaxResults(page.getPageSize());
		List<?> list = query.list();
		Iterator<?> it = list.iterator();
		List<CLFWBean> beans = new ArrayList<CLFWBean>();
		while (it.hasNext()) {
			Object[] obj = (Object[]) it.next();
			CLFWBean bean = new CLFWBean();
				bean.setCLFWPCSHENG_SEQID(ClfwCommon.configcommon(obj[0]));
				bean.setCLFWPC_MC(ClfwCommon.configcommon(obj[1]));
				bean.setCLFWPCSHENG_SM(ClfwCommon.configcommon(obj[2]));
				bean.setCLFWPCSHENG_SHENGM(ClfwCommon.configcommon(obj[3]));
				bean.setCLFWPCSHENG_JGDM(ClfwCommon.configcommon(obj[4]));
			beans.add(bean);
		}
		page.setResult(beans);
		page.setAjaxUrl(PageUtils.getPageUrl(page, "AJAx"));
		return page;
	}
	
	@Transactional(readOnly = true)
	public CLFWBean getClfwpcshi(String SEQID) {
		String sql = "select b.CLFWPCSHI_SEQID,b.CLFWPCSHENG_SEQID,b.CLFWPC_SEQID,b.CLFWPCSHI_DISTCD,b.CLFWPCSHI_SHIGM," +
				" b.CLFWPCSHI_SM,a.CLFWPC_SX,c.CLFWPCSHENG_DISTCD ,b.CLFWPCSHI_JZSJ,b.CLFWPCSHI_JZYH "
				+ " from CLFW_CLFWPC a,CLFW_CLFWPC_SHI b,CLFW_CLFWPC_SHENG c" +
						"  where a.CLFWPC_SEQID = b.CLFWPC_SEQID and b.CLFWPCSHENG_SEQID = c.CLFWPCSHENG_SEQID(+)" +
						" and  b.CLFWPCSHI_SEQID = '" + SEQID + "'";
		List<?> list = getSession().createSQLQuery(sql).list();
		Iterator<?> it = list.iterator();
		CLFWBean bean = new CLFWBean();
		if (it.hasNext()) {
			Object[] obj = (Object[]) it.next();
				bean.setCLFWPCSHI_SEQID(ClfwCommon.configcommon(obj[0]));
				bean.setCLFWPCSHENG_SEQID(ClfwCommon.configcommon(obj[1]));
				bean.setCLFWPC_SEQID(ClfwCommon.configcommon(obj[2]));
				bean.setCLFWPCSHI_DISTCD(ClfwCommon.configcommon(obj[3]));
				bean.setCLFWPCSHI_SHIGM(ClfwCommon.configcommon(obj[4]));
				bean.setCLFWPCSHI_SM(ClfwCommon.configcommon(obj[5]));
				bean.setCLFWPC_SX(ClfwCommon.configcommon(obj[6]));
				bean.setCLFWPCSHENG_DISTCD(ClfwCommon.configcommon(obj[7]));
				bean.setCLFWPCSHI_JZSJ(ClfwCommon.configcommon(obj[8]));
				bean.setCLFWPCSHI_JZYH(ClfwCommon.configcommon(obj[9]));
		}
		return bean;
	}
	
	@Transactional(readOnly = true)
	public Page<CLFWBean> getBeanQueryClfwpcshi(String sql, String sqlCount,
			Page<CLFWBean> page) {
		if (page.isAutoCount()) {
			long totalCount = 99999;
			page.setTotalCount(totalCount);
		}
		Query query = getSession().createSQLQuery(sql);
		query.setFirstResult(page.getFirst() - 1);
		query.setMaxResults(page.getPageSize());
		List<?> list = query.list();
		Iterator<?> it = list.iterator();
		List<CLFWBean> beans = new ArrayList<CLFWBean>();
		while (it.hasNext()) {
			Object[] obj = (Object[]) it.next();
			CLFWBean bean = new CLFWBean();
				bean.setCLFWPCSHI_SEQID(ClfwCommon.configcommon(obj[0]));
				bean.setCLFWPC_MC(ClfwCommon.configcommon(obj[1]));
				bean.setCLFWPC_SM(ClfwCommon.configcommon(obj[2]));
				bean.setCLFWPC_SXSTR(ClfwCommon.configcommon(obj[3]));
				bean.setCLFWPCSHENG_SHENGM(ClfwCommon.configcommon(obj[4]));
				bean.setCLFWPCSHI_SHIGM(ClfwCommon.configcommon(obj[5]));
				bean.setCLFWPCSHI_SM(ClfwCommon.configcommon(obj[6]));
				bean.setCLFWPCSHI_DISTCDSTR(ClfwCommon.configcommon(obj[7]));
				bean.setCLFWPCSHI_JZSJ(ClfwCommon.configcommon(obj[8]));
				bean.setCLFWPCSHI_JZYH(ClfwCommon.configcommon(obj[9]));
			beans.add(bean);
		}
		page.setResult(beans);
		page.setAjaxUrl(PageUtils.getPageUrl(page, "AJAx"));
		return page;
	}
	
	@Transactional(readOnly = true)
	public CLFWBean getCPWHDEPARTMENT(String SEQID) {
		String sql = "select a.dm_pk_code,a.CITY_CODE " +
				" from CP_WH_DEPARTMENT a "
				+ " where a.dm_pk_code = '" + SEQID + "'";
		List<?> list = getSession().createSQLQuery(sql).list();
		Iterator<?> it = list.iterator();
		CLFWBean bean = new CLFWBean();
		if (it.hasNext()) {
			Object[] obj = (Object[]) it.next();
				bean.setDM_PK_CODE(ClfwCommon.configcommon(obj[0]));
				bean.setCITY_CODE(ClfwCommon.configcommon(obj[1]));
				bean.setCLFWTDB_DISTCD(ClfwCommon.configcommon(obj[1]));
		}
		return bean;
	}
	
	@Transactional(readOnly = true)
	public CLFWBean getClfwtdbcq(String SEQID) {
		String sql = "select a.CLFWTDBCQ_SEQID,a.CLFWTDB_DISTCD,a.CLFWTDB_FW,b.CITY_CODE " +
				" from CLFW_CLFWTDBCQ a,CP_WH_DEPARTMENT b "
				+ " where a.dm_pk_code = b.dm_pk_code and a.CLFWTDBCQ_SEQID = '" + SEQID + "'";
		List<?> list = getSession().createSQLQuery(sql).list();
		Iterator<?> it = list.iterator();
		CLFWBean bean = new CLFWBean();
		if (it.hasNext()) {
			Object[] obj = (Object[]) it.next();
				bean.setCLFWTDBCQ_SEQID(ClfwCommon.configcommon(obj[0]));
				bean.setCLFWTDB_DISTCD(ClfwCommon.configcommon(obj[1]));
				bean.setCLFWTDB_FW(ClfwCommon.configcommon(obj[2]));
				bean.setCITY_CODE(ClfwCommon.configcommon(obj[3]));
		}
		return bean;
	}

	@Transactional(readOnly = true)
	public Page<CLFWBean> getBeanQueryClfwtdbcq(String sql, String sqlCount,
			Page<CLFWBean> page) {
		if (page.isAutoCount()) {
			List<?> listCount = getSession().createSQLQuery(sqlCount).list();
			long totalCount = ((BigDecimal) listCount.get(0)).longValue();
			page.setTotalCount(totalCount);
		}
		Query query = getSession().createSQLQuery(sql);
		query.setFirstResult(page.getFirst() - 1);
		query.setMaxResults(page.getPageSize());
		List<?> list = query.list();
		Iterator<?> it = list.iterator();
		List<CLFWBean> beans = new ArrayList<CLFWBean>();
		while (it.hasNext()) {
			Object[] obj = (Object[]) it.next();
			CLFWBean bean = new CLFWBean();
				bean.setCLFWTDBCQ_SEQID(ClfwCommon.configcommon(obj[0]));
				bean.setDM_PK_CODE(ClfwCommon.configcommon(obj[1]));
				bean.setDM_NAME(ClfwCommon.configcommon(obj[2]));
				bean.setOFFICE_CODE(ClfwCommon.configcommon(obj[3]));
				bean.setCLFWTDB_DISTCDSTR(ClfwCommon.configcommon(obj[4]));
				bean.setCLFWTDB_FW(ClfwCommon.configcommon(obj[5]));
				bean.setNULLFLAG(ClfwCommon.configcommon(obj[6]));
			beans.add(bean);
		}
		page.setResult(beans);
		page.setAjaxUrl(PageUtils.getPageUrl(page, "AJAx"));
		return page;
	}
	
	@Transactional(readOnly = true)
	public CLFWBean getClfwtdb(String SEQID) {
		String sql = "select a.CLFWTDB_SEQID,a.CLFWTDB_SJYXSC,a.CLFWTDB_LSLZB,a.CLFWTDB_SM " +
				" from CLFW_CLFWtdb a "
				+ " where  a.CLFWTDB_SEQID = '" + SEQID + "'";
		List<?> list = getSession().createSQLQuery(sql).list();
		Iterator<?> it = list.iterator();
		CLFWBean bean = new CLFWBean();
		if (it.hasNext()) {
			Object[] obj = (Object[]) it.next();
				bean.setCLFWTDB_SEQID(ClfwCommon.configcommon(obj[0]));
				bean.setCLFWTDB_SJYXSC(ClfwCommon.configcommon(obj[1]));
				bean.setCLFWTDB_LSLZB(ClfwCommon.configcommon(obj[2]));
				bean.setCLFWTDB_SM(ClfwCommon.configcommon(obj[3]));
		}
		return bean;
	}
	
	@Transactional(readOnly = true)
	public Page<CLFWBean> getBeanQueryClfwtdbsh(String sql, String sqlCount,
			Page<CLFWBean> page) {
		if (page.isAutoCount()) {
			List<?> listCount = getSession().createSQLQuery(sqlCount).list();
			long totalCount = ((BigDecimal) listCount.get(0)).longValue();
			page.setTotalCount(totalCount);
		}
		Query query = getSession().createSQLQuery(sql);
		query.setFirstResult(page.getFirst() - 1);
		query.setMaxResults(page.getPageSize());
		List<?> list = query.list();
		Iterator<?> it = list.iterator();
		List<CLFWBean> beans = new ArrayList<CLFWBean>();
		while (it.hasNext()) {
			Object[] obj = (Object[]) it.next();
			CLFWBean bean = new CLFWBean();
				bean.setDM_PK_CODE(ClfwCommon.configcommon(obj[0]));
				bean.setDM_NAME(ClfwCommon.configcommon(obj[1]));
				bean.setOFFICE_CODE(ClfwCommon.configcommon(obj[2]));
				bean.setCLFWTDB_DISTCDSTR(ClfwCommon.configcommon(obj[3]));
				bean.setCLFWTDB_SEQID(ClfwCommon.configcommon(obj[4]));
				bean.setCLFWPCSHI_SHIGM(ClfwCommon.configcommon(obj[5]));
				bean.setCLFWPCSHENG_SHENGM(ClfwCommon.configcommon(obj[6]));
				bean.setCLFWTDB_CLSXSTR(ClfwCommon.configcommon(obj[7]));
				bean.setCLFWTDB_SJYXSC(ClfwCommon.configcommon(obj[8]));
				bean.setCLFWTDB_LSLZB(ClfwCommon.configcommon(obj[9]));
				bean.setCLFWTDB_SHSXSTR(ClfwCommon.configcommon(obj[10]));
				bean.setCLFWTDB_SHCZSTR(ClfwCommon.configcommon(obj[11]));
			beans.add(bean);
		}
		page.setResult(beans);
		page.setAjaxUrl(PageUtils.getPageUrl(page, "AJAx"));
		return page;
	}
	
	@Transactional(readOnly = true)
	public Page<CLFWBean> getBeanQueryClfwtdb(String sql, String sqlCount,
			Page<CLFWBean> page) {
		if (page.isAutoCount()) {
			List<?> listCount = getSession().createSQLQuery(sqlCount).list();
			long totalCount = ((BigDecimal) listCount.get(0)).longValue();
			page.setTotalCount(totalCount);
		}
		Query query = getSession().createSQLQuery(sql);
		query.setFirstResult(page.getFirst() - 1);
		query.setMaxResults(page.getPageSize());
		List<?> list = query.list();
		Iterator<?> it = list.iterator();
		List<CLFWBean> beans = new ArrayList<CLFWBean>();
		while (it.hasNext()) {
			Object[] obj = (Object[]) it.next();
			CLFWBean bean = new CLFWBean();
				bean.setDM_PK_CODE(ClfwCommon.configcommon(obj[0]));
				bean.setDM_NAME(ClfwCommon.configcommon(obj[1]));
				bean.setOFFICE_CODE(ClfwCommon.configcommon(obj[2]));
				bean.setCLFWTDB_DISTCDSTR(ClfwCommon.configcommon(obj[3]));
				bean.setCLFWTDB_SEQID(ClfwCommon.configcommon(obj[4]));
				bean.setCLFWPCSHI_SHIGM(ClfwCommon.configcommon(obj[5]));
				bean.setCLFWPCSHENG_SHENGM(ClfwCommon.configcommon(obj[6]));
				bean.setCLFWTDB_CLSXSTR(ClfwCommon.configcommon(obj[7]));
				bean.setCLFWTDB_SJYXSC(ClfwCommon.configcommon(obj[8]));
				bean.setCLFWTDB_LSLZB(ClfwCommon.configcommon(obj[9]));
				bean.setCLFWTDB_SHSXSTR(ClfwCommon.configcommon(obj[10]));
				bean.setCLFWTDB_SHCZSTR(ClfwCommon.configcommon(obj[11]));
			beans.add(bean);
		}
		page.setResult(beans);
		page.setAjaxUrl(PageUtils.getPageUrl(page, "AJAx"));
		return page;
	}

	@Transactional(readOnly = true)
	public Page<CLFWBean> getBeanQueryClfwtdd(String sql, String sqlCount,
			Page<CLFWBean> page) {
		if (page.isAutoCount()) {
			List<?> listCount = getSession().createSQLQuery(sqlCount).list();
			long totalCount = ((BigDecimal) listCount.get(0)).longValue();
			page.setTotalCount(totalCount);
		}
		Query query = getSession().createSQLQuery(sql);
		query.setFirstResult(page.getFirst() - 1);
		query.setMaxResults(page.getPageSize());
		List<?> list = query.list();
		Iterator<?> it = list.iterator();
		List<CLFWBean> beans = new ArrayList<CLFWBean>();
		while (it.hasNext()) {
			Object[] obj = (Object[]) it.next();
			CLFWBean bean = new CLFWBean();
				bean.setPG_PK_CODE(ClfwCommon.configcommon(obj[0]));
				bean.setCLFWTDD_SEQID(ClfwCommon.configcommon(obj[1]));
				bean.setCLFWTDD_CLSXSTR(ClfwCommon.configcommon(obj[2]));
				bean.setPG_NAME(ClfwCommon.configcommon(obj[3]));
				bean.setOPE_REMARK(ClfwCommon.configcommon(obj[4]));
			beans.add(bean);
		}
		page.setResult(beans);
		page.setAjaxUrl(PageUtils.getPageUrl(page, "AJAx"));
		return page;
	}

	@Transactional(readOnly = true)
	public Page<CLFWBean> getBeanQueryClfwsjcx(String sql, String sqlCount,
			Page<CLFWBean> page) {
		if (page.isAutoCount()) {
			List<?> listCount = getSession().createSQLQuery(sqlCount).list();
			long totalCount = ((BigDecimal) listCount.get(0)).longValue();
			page.setTotalCount(totalCount);
		}
		Query query = getSession().createSQLQuery(sql);
		query.setFirstResult(page.getFirst() - 1);
		query.setMaxResults(page.getPageSize());
		List<?> list = query.list();
		Iterator<?> it = list.iterator();
		List<CLFWBean> beans = new ArrayList<CLFWBean>();
		while (it.hasNext()) {
			Object[] obj = (Object[]) it.next();
			CLFWBean bean = new CLFWBean();
				bean.setPCCNAME(ClfwCommon.configcommon(obj[0]));
				bean.setDM_NAME(ClfwCommon.configcommon(obj[1]));
				bean.setSTRTALLNAME(ClfwCommon.configcommon(obj[2]));
				bean.setPNUME(ClfwCommon.configcommon(obj[3]));
				bean.setFLAGSTR(ClfwCommon.configcommon(obj[4]));
				bean.setRL_PG_ST_ID(ClfwCommon.configcommon(obj[5]));
				bean.setPG_NAME(ClfwCommon.configcommon(obj[6]));
			beans.add(bean);
		}
		page.setResult(beans);
		page.setAjaxUrl(PageUtils.getPageUrl(page, "AJAx"));
		return page;
	}
	
	@Transactional(readOnly = true)
	public Page<CLFWBean> getBeanQueryClfwtdbtj(String sql, String sqlCount,
			Page<CLFWBean> page) {
		if (page.isAutoCount()) {
			//List<?> listCount = getSession().createSQLQuery(sqlCount).list();
			//long totalCount = ((BigDecimal) listCount.get(0)).longValue();
			long totalCount = 99999;
			page.setTotalCount(totalCount);
		}
		Query query = getSession().createSQLQuery(sql);
		query.setFirstResult(page.getFirst() - 1);
		query.setMaxResults(page.getPageSize());
		List<?> list = query.list();
		Iterator<?> it = list.iterator();
		List<CLFWBean> beans = new ArrayList<CLFWBean>();
		while (it.hasNext()) {
			Object[] obj = (Object[]) it.next();
			
				CLFWBean bean = new CLFWBean();
				bean.setCITY_CODE(ClfwCommon.configcommon(obj[0]));
				bean.setCLFWTDB_DISTCDSTR(ClfwCommon.configcommon(obj[1]));
				bean.setCLFWTDB_CLSX(ClfwCommon.configcommon(obj[2]));
				bean.setDM_NAME(ClfwCommon.configcommon(obj[3]));
				bean.setCLFWTDB_FW(ClfwCommon.configcommon(obj[4]));
				bean.setCLFWPCSHENG_SHENGM(ClfwCommon.configcommon(obj[5]));
				bean.setCLFWPCSHI_SHIGM(ClfwCommon.configcommon(obj[6]));
				
				String ttCLFWTDB_FW = "";
				boolean ttFlag = true;
				
				ttCLFWTDB_FW =  bean.getDM_NAME() + "<font color='red'>[" + bean.getCLFWTDB_FW() + "]</font>";
				
				for(int i=0;i<beans.size();i++){
					
					if(beans.get(i).getCITY_CODE().equals(bean.getCITY_CODE())	
						&& beans.get(i).getCLFWPCSHENG_SHENGM().equals(bean.getCLFWPCSHENG_SHENGM())
						&& beans.get(i).getCLFWPCSHI_SHIGM().equals(bean.getCLFWPCSHI_SHIGM())
						){
						
						if(bean.getCLFWTDB_CLSX().equals("1")){
							if(beans.get(i).getYTDBN()>0){
								if(beans.get(i).getYTDBN() % 3 == 0){
									ttCLFWTDB_FW = beans.get(i).getYTDB() + "<br>" +
										"&nbsp;" + ttCLFWTDB_FW;
								}else{
									ttCLFWTDB_FW = beans.get(i).getYTDB() +
									"&nbsp;" + ttCLFWTDB_FW;
								}
							}
							beans.get(i).setYTDB(ttCLFWTDB_FW);
							beans.get(i).setYTDBN(beans.get(i).getYTDBN() + 1);
						}else if(bean.getCLFWTDB_CLSX().equals("0")){
							if(beans.get(i).getXTDBN()>0){
								//if(beans.get(i).getXTDBN() % 3 == 0){
									ttCLFWTDB_FW = beans.get(i).getXTDB() + "<br>" +
										"&nbsp;" + ttCLFWTDB_FW;
								//}else{
								//	ttCLFWTDB_FW = beans.get(i).getXTDB() +
								//	"&nbsp;" + ttCLFWTDB_FW;
								//}
							}
							beans.get(i).setXTDB(ttCLFWTDB_FW);
							beans.get(i).setYTDBN(beans.get(i).getXTDBN() + 1);
						}else if(bean.getCLFWTDB_CLSX().equals("2")){
							if(beans.get(i).getCTDBN()>0){
								//if(beans.get(i).getCTDBN() % 3 == 0){
									ttCLFWTDB_FW = beans.get(i).getCTDB() + "<br>" +
										"&nbsp;" + ttCLFWTDB_FW;
								//}else{
								//	ttCLFWTDB_FW = beans.get(i).getCTDB() +
								//	"&nbsp;" + ttCLFWTDB_FW;
								//}
							}
							beans.get(i).setCTDB(ttCLFWTDB_FW);
							beans.get(i).setYTDBN(beans.get(i).getCTDBN() + 1);
						}
						

						beans.get(i).setCLFWTDB_FW(ttCLFWTDB_FW);
						ttFlag = false;
					}
				}
				
				if(ttFlag){
					bean.setYTDB("");
					bean.setXTDB("");
					bean.setCTDB("");
					if(bean.getCLFWTDB_CLSX().equals("1")){
						bean.setYTDB(ttCLFWTDB_FW);
						bean.setYTDBN(1);
					}else if(bean.getCLFWTDB_CLSX().equals("0")){
						bean.setXTDB(ttCLFWTDB_FW);
						bean.setXTDBN(1);
					}else if(bean.getCLFWTDB_CLSX().equals("2")){
						bean.setCTDB(ttCLFWTDB_FW);
						bean.setCTDBN(1);
					}
					beans.add(bean);
				}
			
		}
		page.setResult(beans);
		page.setAjaxUrl(PageUtils.getPageUrl(page, "AJAx"));
		return page;
	}
	
	@Transactional(readOnly = true)
	public Page<CLFWBean> getBeanQueryClfwkffwpz(String sql, String sqlCount,
			Page<CLFWBean> page) {
		if (page.isAutoCount()) {
			long totalCount = 99999;
			page.setTotalCount(totalCount);
		}
		Query query = getSession().createSQLQuery(sql);
		query.setFirstResult(page.getFirst() - 1);
		query.setMaxResults(page.getPageSize());
		List<?> list = query.list();
		Iterator<?> it = list.iterator();
		List<CLFWBean> beans = new ArrayList<CLFWBean>();
		while (it.hasNext()) {
			Object[] obj = (Object[]) it.next();
			CLFWBean bean = new CLFWBean();
				bean.setCLFWKFFW_SEQID(ClfwCommon.configcommon(obj[0]));
				bean.setCLFWKFFW_DISTCDSTR(ClfwCommon.configcommon(obj[1]));
				bean.setCLFWKFFW_YTEFW(ClfwCommon.configcommon(obj[2]));
				bean.setCLFWKFFW_SWSFW(ClfwCommon.configcommon(obj[3]));
			beans.add(bean);
		}
		page.setResult(beans);
		page.setAjaxUrl(PageUtils.getPageUrl(page, "AJAx"));
		return page;
	}
	
	@Transactional(readOnly = true)
	public CLFWBean getClfwkffw(String SEQID) {
		String sql = "select a.CLFWKFFW_SEQID,a.CLFWKFFW_DISTCD,a.CLFWKFFW_YTEFW,a.CLFWKFFW_SWSFW "
				+ " from CLFW_KFFW a where a.CLFWKFFW_SEQID = '" + SEQID + "'";
		List<?> list = getSession().createSQLQuery(sql).list();
		Iterator<?> it = list.iterator();
		CLFWBean bean = new CLFWBean();
		if (it.hasNext()) {
			Object[] obj = (Object[]) it.next();
				bean.setCLFWKFFW_SEQID(ClfwCommon.configcommon(obj[0]));
				bean.setCLFWKFFW_DISTCD(ClfwCommon.configcommon(obj[1]));
				bean.setCLFWKFFW_YTEFW(ClfwCommon.configcommon(obj[2]));
				bean.setCLFWKFFW_SWSFW(ClfwCommon.configcommon(obj[3]));
		}
		return bean;
	}
	
	@Transactional(readOnly = true)
	public CLFWBean getClfwylxxb(String SEQID) {
		String sql = "select a.CLFWYLXXB_SEQID,a.CLFWYLXXB_SFJID,a.CLFWYLXXB_ZDJID,a.CLFWYLXXB_SFJSM," +
				" a.CLFWYLXXB_ZDJSM,a.CLFWYLXXBZ_SEQID,a.CLFWYLXXB_LSH,a.CLFWYLXXB_CPH," +
		 		" a.CLFWYLXXB_SJKCSJ,a.CLFWYLXXB_SJDDSJ "
				+ " from CLFW_YLXXB a where a.CLFWYLXXB_SEQID = '" + SEQID + "'";
		List<?> list = getSession().createSQLQuery(sql).list();
		Iterator<?> it = list.iterator();
		CLFWBean bean = new CLFWBean();
		if (it.hasNext()) {
			Object[] obj = (Object[]) it.next();
				bean.setCLFWYLXXB_SEQID(ClfwCommon.configcommon(obj[0]));
				bean.setCLFWYLXXB_SFJID(ClfwCommon.configcommon(obj[1]));
				bean.setCLFWYLXXB_ZDJID(ClfwCommon.configcommon(obj[2]));
				bean.setCLFWYLXXB_SFJSM(ClfwCommon.configcommon(obj[3]));
				bean.setCLFWYLXXB_ZDJSM(ClfwCommon.configcommon(obj[4]));
				bean.setCLFWYLXXBZ_SEQID(ClfwCommon.configcommon(obj[5]));
				bean.setCLFWYLXXB_LSH(ClfwCommon.configcommon(obj[6]));
				bean.setCLFWYLXXB_CPH(ClfwCommon.configcommon(obj[7]));
				bean.setCLFWYLXXB_SJKCSJ(ClfwCommon.configcommon(obj[8]));
				bean.setCLFWYLXXB_SJDDSJ(ClfwCommon.configcommon(obj[9]));

		}
		return bean;
	}
	
	@Transactional(readOnly = true)
	public Page<CLFWBean> getBeanQueryClfwhkwh(String sql, String sqlCount,
			Page<CLFWBean> page) {
		if (page.isAutoCount()) {
			long totalCount = 99999;
			page.setTotalCount(totalCount);
		}
		Query query = getSession().createSQLQuery(sql);
		query.setFirstResult(page.getFirst() - 1);
		query.setMaxResults(page.getPageSize());
		List<?> list = query.list();
		Iterator<?> it = list.iterator();
		List<CLFWBean> beans = new ArrayList<CLFWBean>();
		while (it.hasNext()) {
			Object[] obj = (Object[]) it.next();
			CLFWBean bean = new CLFWBean();
				bean.setCLFW_HBXXGL_SEQID(ClfwCommon.configcommon(obj[0]));
				bean.setCLFW_HBXXGL_XSBZ(ClfwCommon.configcommon(obj[1]));
				bean.setCLFW_HBXXGL_SFJIDSTR(ClfwCommon.configcommon(obj[2]));
				bean.setCLFW_HBXXGL_ZDJIDSTR(ClfwCommon.configcommon(obj[3]));
				bean.setCLFW_HBXXGL_CSRQ(ClfwCommon.configcommon(obj[4]));
				bean.setCLFW_HBXXGL_HBH(ClfwCommon.configcommon(obj[5]));
				bean.setCLFW_HBXXGL_ZBS(ClfwCommon.configcommon(obj[6]));
				bean.setCLFW_HBXXGL_ZL(ClfwCommon.configcommon(obj[7]));
				bean.setCLFW_HBXXGL_LDZBS(ClfwCommon.configcommon(obj[8]));
				bean.setCLFW_HBXXGL_SSZBS(ClfwCommon.configcommon(obj[9]));
				bean.setCLFW_HBXXGL_QYRQ(ClfwCommon.configcommon(obj[10]));
				bean.setCLFW_HBXXGL_JZRQ(ClfwCommon.configcommon(obj[11]));
				bean.setCLFW_HBXXGL_LDLSH(ClfwCommon.configcommon(obj[12]));
			beans.add(bean);
		}
		page.setResult(beans);
		page.setAjaxUrl(PageUtils.getPageUrl(page, "AJAx"));
		return page;
	}
	
	@Transactional(readOnly = true)
	public CLFWBean getClfwhkwh(String SEQID) {
		String sql = "select a.CLFW_HBXXGL_SEQID,a.CLFW_HBXXGL_SFJID,a.CLFW_HBXXGL_ZDJID,a.CLFW_HBXXGL_CSRQ," +
				" a.CLFW_HBXXGL_HBH,a.CLFW_HBXXGL_ZBS,a.CLFW_HBXXGL_ZL,a.CLFW_HBXXGL_LDZBS," +
		 		" a.CLFW_HBXXGL_SSZBS,CLFW_HBXXGL_LDLSH "
				+ " from CLFW_HBXXGL a where a.CLFW_HBXXGL_SEQID = '" + SEQID + "'";
		List<?> list = getSession().createSQLQuery(sql).list();
		Iterator<?> it = list.iterator();
		CLFWBean bean = new CLFWBean();
		if (it.hasNext()) {
			Object[] obj = (Object[]) it.next();
				bean.setCLFW_HBXXGL_SEQID(ClfwCommon.configcommon(obj[0]));
				bean.setCLFW_HBXXGL_SFJID(ClfwCommon.configcommon(obj[1]));
				bean.setCLFW_HBXXGL_ZDJID(ClfwCommon.configcommon(obj[2]));
				bean.setCLFW_HBXXGL_CSRQ(ClfwCommon.configcommon(obj[3]));
				bean.setCLFW_HBXXGL_HBH(ClfwCommon.configcommon(obj[4]));
				bean.setCLFW_HBXXGL_ZBS(ClfwCommon.configcommon(obj[5]));
				bean.setCLFW_HBXXGL_ZL(ClfwCommon.configcommon(obj[6]));
				bean.setCLFW_HBXXGL_LDZBS(ClfwCommon.configcommon(obj[7]));
				bean.setCLFW_HBXXGL_SSZBS(ClfwCommon.configcommon(obj[8]));
				bean.setCLFW_HBXXGL_LDLSH(ClfwCommon.configcommon(obj[9]));

		}
		return bean;
	}
	
	@Transactional(readOnly = true)
	public Page<CLFWBean> getBeanQueryClfwylxxbt(String sql, String sqlCount,
			Page<CLFWBean> page) {
		if (page.isAutoCount()) {
			long totalCount = 99999;
			page.setTotalCount(totalCount);
		}
		Query query = getSession().createSQLQuery(sql);
		query.setFirstResult(page.getFirst() - 1);
		query.setMaxResults(page.getPageSize());
		List<?> list = query.list();
		Iterator<?> it = list.iterator();
		List<CLFWBean> beans = new ArrayList<CLFWBean>();
		while (it.hasNext()) {
			Object[] obj = (Object[]) it.next();
			CLFWBean bean = new CLFWBean();
				bean.setCLFWYLXXB_SEQID(ClfwCommon.configcommon(obj[0]));
				bean.setCLFWYLXXB_FLAG(ClfwCommon.configcommon(obj[1]));
				bean.setCLFWYLXXBZ_SEQID(ClfwCommon.configcommon(obj[2]));
				bean.setCLFWYLXXBZ_YLMC(ClfwCommon.configcommon(obj[3]));
				bean.setCLFWYLXXB_XSBZ(ClfwCommon.configcommon(obj[4]));
				bean.setCLFWYLXXB_LSH(ClfwCommon.configcommon(obj[5]));
				bean.setCLFWYLXXB_CPH(ClfwCommon.configcommon(obj[6]));
				bean.setCLFWYLXXB_SFJID_STR(ClfwCommon.configcommon(obj[7]));
				bean.setCLFWYLXXB_SJKCSJ(ClfwCommon.configcommon(obj[8]));
				bean.setCLFWYLXXB_ZDJID_STR(ClfwCommon.configcommon(obj[9]));
				bean.setCLFWYLXXB_SJDDSJ(ClfwCommon.configcommon(obj[10]));
				bean.setCLFWYLXXB_QYRQ(ClfwCommon.configcommon(obj[11]));
				bean.setCLFWYLXXB_JZRQ(ClfwCommon.configcommon(obj[12]));
				bean.setCLFWYLXXB_CSRQ(ClfwCommon.configcommon(obj[13]));
				bean.setCLFWYLXXB_SFJGHSM(ClfwCommon.configcommon(obj[14]));
			beans.add(bean);
		}
		page.setResult(beans);
		page.setAjaxUrl(PageUtils.getPageUrl(page, "AJAx"));
		return page;
	}
	
	@Transactional(readOnly = true)
	public Page<CLFWBean> getBeanQueryClfwresorgpz(String sql, String sqlCount,
			Page<CLFWBean> page) {
		if (page.isAutoCount()) {
			List<?> listCount = getSession().createSQLQuery(sqlCount).list();
			long totalCount = ((BigDecimal) listCount.get(0)).longValue();
			page.setTotalCount(totalCount);
		}
		Query query = getSession().createSQLQuery(sql);
		query.setFirstResult(page.getFirst() - 1);
		query.setMaxResults(page.getPageSize());
		List<?> list = query.list();
		Iterator<?> it = list.iterator();
		List<CLFWBean> beans = new ArrayList<CLFWBean>();
		while (it.hasNext()) {
			Object[] obj = (Object[]) it.next();
			CLFWBean bean = new CLFWBean();
				bean.setORG_CODE(ClfwCommon.configcommon(obj[0]));
				bean.setORG_CNAME(ClfwCommon.configcommon(obj[1]));
				bean.setCITY_CODE(ClfwCommon.configcommon(obj[2]));
				bean.setCLFWRESORGPZ_DISTCDSTR(ClfwCommon.configcommon(obj[3]));
				bean.setCLFWRESORGPZ_SEQID(ClfwCommon.configcommon(obj[4]));
				bean.setCLFWPCSHI_SHIGM(ClfwCommon.configcommon(obj[5]));
				bean.setCLFWPCSHENG_SHENGM(ClfwCommon.configcommon(obj[6]));
				bean.setCLFWRESORGPZ_CLSXSTR(ClfwCommon.configcommon(obj[7]));
				bean.setCLFWRESORGPZ_SJYXSC(ClfwCommon.configcommon(obj[8]));
				bean.setCLFWRESORGPZ_LSLZB(ClfwCommon.configcommon(obj[9]));
				bean.setCLFWRESORGPZ_SHSXSTR(ClfwCommon.configcommon(obj[10]));
				bean.setCLFWRESORGPZ_SHCZSTR(ClfwCommon.configcommon(obj[11]));
			beans.add(bean);
		}
		page.setResult(beans);
		page.setAjaxUrl(PageUtils.getPageUrl(page, "AJAx"));
		return page;
	}
	
	@Transactional(readOnly = true)
	public Page<CLFWBean> getBeanQueryClfwjgdm(String sql, String sqlCount,
			Page<CLFWBean> page) {
		if (page.isAutoCount()) {
			//List<?> listCount = getSession().createSQLQuery(sqlCount).list();
			//long totalCount = ((BigDecimal) listCount.get(0)).longValue();
			long totalCount = 99999;
			page.setTotalCount(totalCount);
		}
		Query query = getSession().createSQLQuery(sql);
		query.setFirstResult(page.getFirst() - 1);
		query.setMaxResults(page.getPageSize());
		List<?> list = query.list();
		Iterator<?> it = list.iterator();
		List<CLFWBean> beans = new ArrayList<CLFWBean>();
		while (it.hasNext()) {
			Object[] obj = (Object[]) it.next();
			
				CLFWBean bean = new CLFWBean();
				bean.setORG_CODE(ClfwCommon.configcommon(obj[0]));
				bean.setORG_CNAME(ClfwCommon.configcommon(obj[1]));
				bean.setCLFWRESORGPZ_DISTCDSTR(ClfwCommon.configcommon(obj[2]));
				bean.setCLFWPCSHI_DISTCD(ClfwCommon.configcommon(obj[3]));
				bean.setCLFWPCSHI_SHIGM(ClfwCommon.configcommon(obj[4]));
				bean.setCLFWPCSHI_SEQID(ClfwCommon.configcommon(obj[5]));
				bean.setCLFWPCSHENG_SHENGM(ClfwCommon.configcommon(obj[6]));
				bean.setCLFWPCSHENG_SEQID(ClfwCommon.configcommon(obj[7]));
				
				String ttCLFWTDB_FW = "";
				boolean ttFlag = true;
				
				ttCLFWTDB_FW =  bean.getORG_CODE() + "<font color='red'>[" + bean.getORG_CNAME() + "]</font>";
				
				for(int i=0;i<beans.size();i++){
					
					if(beans.get(i).getCLFWPCSHI_DISTCD().equals(bean.getCLFWPCSHI_DISTCD())	
						&& beans.get(i).getCLFWPCSHI_SEQID().equals(bean.getCLFWPCSHI_SEQID())
						&& beans.get(i).getCLFWPCSHENG_SEQID().equals(bean.getCLFWPCSHENG_SEQID())
						){
						
						if(beans.get(i).getYTDBN()>0){
							if(beans.get(i).getYTDBN() % 3 == 0){
								ttCLFWTDB_FW = beans.get(i).getYTDB() + "<br>" +
									"&nbsp;" + ttCLFWTDB_FW;
							}else{
								ttCLFWTDB_FW = beans.get(i).getYTDB() +
								"&nbsp;" + ttCLFWTDB_FW;
							}
						}
						
						beans.get(i).setYTDB(ttCLFWTDB_FW);
						beans.get(i).setYTDBN(beans.get(i).getYTDBN() + 1);
						

						beans.get(i).setCLFWTDB_FW(ttCLFWTDB_FW);
						ttFlag = false;
					}
				}
				
				if(ttFlag){
					bean.setYTDB("");
					bean.setYTDB(ttCLFWTDB_FW);
					bean.setYTDBN(1);
					beans.add(bean);
				}
			
		}
		page.setResult(beans);
		page.setAjaxUrl(PageUtils.getPageUrl(page, "AJAx"));
		return page;
	}
	
	@Transactional(readOnly = true)
	public Page<CLFWBean> getBeanQueryClfwtjclfwylxxbt(String sql, String sqlCount,
			Page<CLFWBean> page) {
		if (page.isAutoCount()) {
			List<?> listCount = getSession().createSQLQuery(sqlCount).list();
			long totalCount = ((BigDecimal) listCount.get(0)).longValue();
			page.setTotalCount(totalCount);
		}
		Query query = getSession().createSQLQuery(sql);
		query.setFirstResult(page.getFirst() - 1);
		query.setMaxResults(page.getPageSize());
		List<?> list = query.list();
		Iterator<?> it = list.iterator();
		List<CLFWBean> beans = new ArrayList<CLFWBean>();
		
		while (it.hasNext()) {
			Object[] obj = (Object[]) it.next();
			CLFWBean bean = new CLFWBean();
			
				bean.setCLFWYLXXB_CSRQ(ClfwCommon.configcommon(obj[0]));
				bean.setCLFWYLXXBZ_YLMC(ClfwCommon.configcommon(obj[1]));
				bean.setCLFWYLXXB_LSH(ClfwCommon.configcommon(obj[2]));
				bean.setCLFWYLXXB_CPH(ClfwCommon.configcommon(obj[3]));
				bean.setCLFWYLXXB_SFJID_STR(ClfwCommon.configcommon(obj[4]));
				bean.setCLFWYLXXBZ_SJKCSJ(ClfwCommon.configcommon(obj[5]));
				
				
				int ishijianss = ClfwCommon.shijianss(ClfwCommon.configcommon(obj[6]),ClfwCommon.configcommon(obj[5]));
				
				bean.setCLFWYLXXB_SJKCSJ(ClfwCommon.configcommon(obj[6]));
				
				if(ClfwCommon.configcommon(obj[6]).length() > 1){
					if( ishijianss == 2){
						bean.setCLFWYLXXB_SJKCSJ("<font color='blue'>" + ClfwCommon.configcommon(obj[6]) + "</font>");
					}else if(ishijianss == 0){
						bean.setCLFWYLXXB_SJKCSJ("<font color='red'>" + ClfwCommon.configcommon(obj[6]) + "</font>");
					}
				}
				
				
				bean.setCLFWYLXXB_ZDJID_STR(ClfwCommon.configcommon(obj[7]));
				bean.setCLFWYLXXBZ_SJDDSJ(ClfwCommon.configcommon(obj[8]));
				
				
				ishijianss = ClfwCommon.shijianss(ClfwCommon.configcommon(obj[9]),ClfwCommon.configcommon(obj[8]));
				
				bean.setCLFWYLXXB_SJDDSJ(ClfwCommon.configcommon(obj[9]));
				
				if(ClfwCommon.configcommon(obj[9]).length() > 1){
					if(ishijianss == 2){
						bean.setCLFWYLXXB_SJDDSJ("<font color='blue'>" + ClfwCommon.configcommon(obj[9]) + "</font>");
					}else if(ishijianss == 0){
						bean.setCLFWYLXXB_SJDDSJ("<font color='red'>" + ClfwCommon.configcommon(obj[9]) + "</font>");
					}
				}
				
				bean.setCLFWYLXXBZ_SEQID(ClfwCommon.configcommon(obj[10]));
				bean.setCLFWYLXXB_SEQID(ClfwCommon.configcommon(obj[11]));
				
				bean.setCLFWYLXXB_SFJGH(ClfwCommon.configcommon(obj[12]));
				bean.setCLFWYLXXB_SFJGHSM(ClfwCommon.configcommon(obj[13]));
				bean.setCLFWYLXXB_SFJGHSMSTR(ClfwCommon.configcommon(obj[14]));
				bean.setCLFWYLXXB_AYJID_STR(ClfwCommon.configcommon(obj[15]));
				
			beans.add(bean);
		}
		page.setResult(beans);
		page.setAjaxUrl(PageUtils.getPageUrl(page, "AJAx"));
		return page;
	}
	
	@Transactional(readOnly = true)
	public Page<CLFWBean> getBeanQueryClfwtjclfwylxxbtalc(String sql, String sqlCount,
			Page<CLFWBean> page) {
		if (page.isAutoCount()) {
			long totalCount = 99999;
			page.setTotalCount(totalCount);
		}
		Query query = getSession().createSQLQuery(sql);
		query.setFirstResult(page.getFirst() - 1);
		query.setMaxResults(page.getPageSize());
		List<?> list = query.list();
		Iterator<?> it = list.iterator();
		List<CLFWBean> beans = new ArrayList<CLFWBean>();
		while (it.hasNext()) {
			Object[] obj = (Object[]) it.next();
			CLFWBean bean = new CLFWBean();
				bean.setCLFWYLXXB_SFJID_STR(ClfwCommon.configcommon(obj[0]));
				bean.setCLFWYLXXB_SFJID(ClfwCommon.configcommon(obj[1]));
				bean.setCLFWYLXXB_SJKCSJ(ClfwCommon.configcommon(obj[2]));
				bean.setCLFWYLXXBZ_SJKCSJ(ClfwCommon.configcommon(obj[3]));
				bean.setCLFWYLXXB_SJDDSJ(ClfwCommon.configcommon(obj[4]));
				bean.setCLFWYLXXBZ_SJDDSJ(ClfwCommon.configcommon(obj[5]));
				bean.setCLFWYLXXB_SFJGHSM(ClfwCommon.configcommon(obj[6]));
				bean.setCLFWYLXXB_SFJGH(ClfwCommon.configcommon(obj[7]));
				
				boolean ttFlag = true;
				
				for(int i=0;i<beans.size();i++){
					
					if(beans.get(i).getCLFWYLXXB_SFJID().equals(bean.getCLFWYLXXB_SFJID())
						){
						
						if(bean.getCLFWYLXXB_SFJGH().equals("1") || bean.getCLFWYLXXB_SFJGH().equals("2")){
							
							beans.get(i).setOPE_INSERTTIMEINT(1 + beans.get(i).getOPE_INSERTTIMEINT());
							
							if(bean.getCLFWYLXXB_SJDDSJ().length() > 1){
							
								beans.get(i).setCLFWYLXXB_CSRQINT(1 + beans.get(i).getCLFWYLXXB_CSRQINT());
								
								if(bean.getCLFWYLXXB_SFJGH().equals("1")){
									
									beans.get(i).setCLFWYLXXB_CSRQINTKH(1 + beans.get(i).getCLFWYLXXB_CSRQINTKH());
	
									beans.get(i).setCLFWYLXXB_JZRQINT(beans.get(i).getCLFWYLXXB_JZRQINT() + ClfwCommon.shijianssf(bean.getCLFWYLXXB_SJDDSJ(), bean.getCLFWYLXXBZ_SJDDSJ(), bean.getCLFWYLXXB_SJKCSJ(), bean.getCLFWYLXXBZ_SJKCSJ()));
	
								}
							}
						}
						
						ttFlag = false;
					}
				}
				
				if(ttFlag){

					if(bean.getCLFWYLXXB_SFJGH().equals("1") || bean.getCLFWYLXXB_SFJGH().equals("2")){
						
						bean.setOPE_INSERTTIMEINT(1);
						
						if(bean.getCLFWYLXXB_SJDDSJ().length() > 1){

							bean.setCLFWYLXXB_CSRQINT(1);
							
							if(bean.getCLFWYLXXB_SFJGH().equals("1")){
								
								bean.setCLFWYLXXB_CSRQINTKH(1);
								
								bean.setCLFWYLXXB_JZRQINT(ClfwCommon.shijianssf(bean.getCLFWYLXXB_SJDDSJ(), bean.getCLFWYLXXBZ_SJDDSJ(), bean.getCLFWYLXXB_SJKCSJ(), bean.getCLFWYLXXBZ_SJKCSJ()));
	
							}
							
						}
					}else{
						
						bean.setOPE_INSERTTIMEINT(0);
						
						bean.setCLFWYLXXB_CSRQINT(0);
						
						bean.setCLFWYLXXB_JZRQINT(0);
					}
					
					beans.add(bean);
				}
		}
		page.setResult(beans);
		page.setAjaxUrl(PageUtils.getPageUrl(page, "AJAx"));
		return page;
	}
	
	@Transactional(readOnly = true)
	public Page<CLFWBean> getBeanQueryClfwhbwhal(String sql, String sqlCount,
			Page<CLFWBean> page) {
		if (page.isAutoCount()) {
			long totalCount = 99999;
			page.setTotalCount(totalCount);
		}
		Query query = getSession().createSQLQuery(sql);
		query.setFirstResult(page.getFirst() - 1);
		query.setMaxResults(page.getPageSize());
		List<?> list = query.list();
		Iterator<?> it = list.iterator();
		List<CLFWBean> beans = new ArrayList<CLFWBean>();
		while (it.hasNext()) {
			Object[] obj = (Object[]) it.next();
			CLFWBean bean = new CLFWBean();
				bean.setCLFW_HBXXGL_SFJIDSTR(ClfwCommon.configcommon(obj[0]));
				bean.setOPE_INSERTTIME(ClfwCommon.configcommon(obj[1]));
				bean.setCLFW_HBXXGL_CSRQ(ClfwCommon.configcommon(obj[2]));
				bean.setCLFW_HBXXGL_ZBS(ClfwCommon.configcommon(obj[3]));
				bean.setCLFW_HBXXGL_ZL(ClfwCommon.configcommon(obj[4]));
				bean.setCLFW_HBXXGL_LDZBS(ClfwCommon.configcommon(obj[5]));
				bean.setCLFW_HBXXGL_SSZBS(ClfwCommon.configcommon(obj[6]));
				bean.setCLFW_HBXXGL_XSBZ(ClfwCommon.configcommon(obj[7]));
			beans.add(bean);
		}
		page.setResult(beans);
		page.setAjaxUrl(PageUtils.getPageUrl(page, "AJAx"));
		return page;
	}
	
	@Transactional(readOnly = true)
	public Page<CLFWBean> getBeanQueryClfwtjclfwhbwhalmx(String sql, String sqlCount,
			Page<CLFWBean> page) {
		if (page.isAutoCount()) {
			List<?> listCount = getSession().createSQLQuery(sqlCount).list();
			long totalCount = ((BigDecimal) listCount.get(0)).longValue();
			page.setTotalCount(totalCount);
		}
		Query query = getSession().createSQLQuery(sql);
		query.setFirstResult(page.getFirst() - 1);
		query.setMaxResults(page.getPageSize());
		List<?> list = query.list();
		Iterator<?> it = list.iterator();
		List<CLFWBean> beans = new ArrayList<CLFWBean>();
		
		while (it.hasNext()) {
			Object[] obj = (Object[]) it.next();
			CLFWBean bean = new CLFWBean();
			
				bean.setCLFW_HBXXGL_SEQID(ClfwCommon.configcommon(obj[0]));
				bean.setCLFW_HBXXGL_SFJIDSTR(ClfwCommon.configcommon(obj[1]));
				bean.setCLFW_HBXXGL_ZDJIDSTR(ClfwCommon.configcommon(obj[2]));
				bean.setCLFW_HBXXGL_CSRQ(ClfwCommon.configcommon(obj[3]));
				bean.setCLFW_HBXXGL_HBH(ClfwCommon.configcommon(obj[4]));
				bean.setCLFW_HBXXGL_ZBS(ClfwCommon.configcommon(obj[5]));
				bean.setCLFW_HBXXGL_ZL(ClfwCommon.configcommon(obj[6]));
				bean.setCLFW_HBXXGL_LDZBS(ClfwCommon.configcommon(obj[7]));
				bean.setCLFW_HBXXGL_SSZBS(ClfwCommon.configcommon(obj[8]));
				bean.setCLFW_HBXXGL_CLSXSTR(ClfwCommon.configcommon(obj[9]));
				bean.setCLFW_HBXXGL_LDLSH(ClfwCommon.configcommon(obj[10]));
				bean.setCLFW_HBXXGL_CLSX(ClfwCommon.configcommon(obj[11]));
				
			beans.add(bean);
		}
		page.setResult(beans);
		page.setAjaxUrl(PageUtils.getPageUrl(page, "AJAx"));
		return page;
	}
	
	@Transactional(readOnly = true)
	public Page<CLFWBean> getBeanQueryClfwtabnjjs(String sql, String sqlCount,
			Page<CLFWBean> page) {
		if (page.isAutoCount()) {
			List<?> listCount = getSession().createSQLQuery(sqlCount).list();
			long totalCount = ((BigDecimal) listCount.get(0)).longValue();
			page.setTotalCount(totalCount);
		}
		Query query = getSession().createSQLQuery(sql);
		query.setFirstResult(page.getFirst() - 1);
		query.setMaxResults(page.getPageSize());
		List<?> list = query.list();
		Iterator<?> it = list.iterator();
		List<CLFWBean> beans = new ArrayList<CLFWBean>();
		while (it.hasNext()) {
			Object[] obj = (Object[]) it.next();
			CLFWBean bean = new CLFWBean();
				bean.setCLFWTDBNJJS_SEQID(ClfwCommon.configcommon(obj[0]));
				bean.setCITY_CODE(ClfwCommon.configcommon(obj[1]));
				bean.setCLFWTDB_DISTCDSTR(ClfwCommon.configcommon(obj[2]));
				bean.setDM_NAME(ClfwCommon.configcommon(obj[3]));
				bean.setOFFICE_CODE(ClfwCommon.configcommon(obj[4]));
				bean.setCLFWTDBNJJS_SFNJ(ClfwCommon.configcommon(obj[5]));
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
	
	@Transactional(readOnly = true)
	public Page<CLFWBean> getBeanQueryClfwhkldwh(String sql, String sqlCount,
			Page<CLFWBean> page) {
		if (page.isAutoCount()) {
			long totalCount = 99999;
			page.setTotalCount(totalCount);
		}
		Query query = getSession().createSQLQuery(sql);
		query.setFirstResult(page.getFirst() - 1);
		query.setMaxResults(page.getPageSize());
		List<?> list = query.list();
		Iterator<?> it = list.iterator();
		List<CLFWBean> beans = new ArrayList<CLFWBean>();
		while (it.hasNext()) {
			Object[] obj = (Object[]) it.next();
			CLFWBean bean = new CLFWBean();
				bean.setCLFW_HBXXLDBW_SEQID(ClfwCommon.configcommon(obj[0]));
				bean.setCLFW_HBXXGL_XSBZ(ClfwCommon.configcommon(obj[1]));
				bean.setCLFW_HBXXYL_SFJIDSTR(ClfwCommon.configcommon(obj[2]));
				bean.setCLFW_HBXXYL_ZDJIDSTR(ClfwCommon.configcommon(obj[3]));
				bean.setCLFW_HBXXLDBW_FYRQSJ(ClfwCommon.configcommon(obj[4]));
				bean.setCLFW_HBXXYL_YLDM(ClfwCommon.configcommon(obj[5]));
				bean.setCLFW_HBXXLDBW_LDLSH(ClfwCommon.configcommon(obj[6]));
				bean.setCLFW_HBXXLDBW_HBCC(ClfwCommon.configcommon(obj[7]));
				bean.setCLFW_HBXXLDBW_ZBS(ClfwCommon.configcommon(obj[8]));
				bean.setCLFW_HBXXLDBW_AJTHZBS(ClfwCommon.configcommon(obj[9]));
				bean.setCLFW_HBXXLDBW_LXZBS(ClfwCommon.configcommon(obj[10]));
				bean.setCLFW_HBXXLDBW_SSZBS(ClfwCommon.configcommon(obj[11]));
				bean.setCLFW_HBXXLDBW_LDHM(ClfwCommon.configcommon(obj[12]));
				bean.setOPE_INSERTTIME(ClfwCommon.configcommon(obj[13]));
				bean.setZDJOPE_INSERTTIME(ClfwCommon.configcommon(obj[14]));
				bean.setCLFW_HBXXLDBW_SFJGH(ClfwCommon.configcommon(obj[15]));
				bean.setCLFW_HBXXLDBW_ZDJGH(ClfwCommon.configcommon(obj[16]));
			beans.add(bean);
		}
		page.setResult(beans);
		page.setAjaxUrl(PageUtils.getPageUrl(page, "AJAx"));
		return page;
	}
	
	@Transactional(readOnly = true)
	public Page<CLFWBean> getBeanQueryClfwhkldwhmx(String sql, String sqlCount,
			Page<CLFWBean> page) {
		if (page.isAutoCount()) {
			List<?> listCount = getSession().createSQLQuery(sqlCount).list();
			long totalCount = ((BigDecimal) listCount.get(0)).longValue();
			page.setTotalCount(totalCount);
		}
		Query query = getSession().createSQLQuery(sql);
		query.setFirstResult(page.getFirst() - 1);
		query.setMaxResults(page.getPageSize());
		List<?> list = query.list();
		Iterator<?> it = list.iterator();
		List<CLFWBean> beans = new ArrayList<CLFWBean>();
		while (it.hasNext()) {
			Object[] obj = (Object[]) it.next();
			CLFWBean bean = new CLFWBean();
				bean.setCLFW_HBXXLDBW_SEQID(ClfwCommon.configcommon(obj[0]));
				bean.setCLFW_HBXXGL_XSBZ(ClfwCommon.configcommon(obj[1]));
				bean.setCLFW_HBXXYL_SFJIDSTR(ClfwCommon.configcommon(obj[2]));
				bean.setCLFW_HBXXYL_ZDJIDSTR(ClfwCommon.configcommon(obj[3]));
				bean.setCLFW_HBXXLDBW_FYRQSJ(ClfwCommon.configcommon(obj[4]));
				bean.setCLFW_HBXXYL_YLDM(ClfwCommon.configcommon(obj[5]));
				bean.setCLFW_HBXXLDBW_LDLSH(ClfwCommon.configcommon(obj[6]));
				bean.setCLFW_HBXXLDBW_HBCC(ClfwCommon.configcommon(obj[7]));
				bean.setCLFW_HBXXLDBW_ZBS(ClfwCommon.configcommon(obj[8]));
				bean.setCLFW_HBXXLDBW_AJTHZBS(ClfwCommon.configcommon(obj[9]));
				bean.setCLFW_HBXXLDBW_LXZBS(ClfwCommon.configcommon(obj[10]));
				bean.setCLFW_HBXXLDBW_SSZBS(ClfwCommon.configcommon(obj[11]));
				bean.setCLFW_HBXXGL_CLSXSTR(ClfwCommon.configcommon(obj[12]));
				bean.setCLFW_HBXXLDBW_LDHM(ClfwCommon.configcommon(obj[13]));
			beans.add(bean);
		}
		page.setResult(beans);
		page.setAjaxUrl(PageUtils.getPageUrl(page, "AJAx"));
		return page;
	}
	
	@Transactional(readOnly = true)
	public Page<CLFWBean> getBeanQueryClfwhkldwhtj(String sql, String sqlCount,
			Page<CLFWBean> page) {
		if (page.isAutoCount()) {
			long totalCount = 99999;
			page.setTotalCount(totalCount);
		}
		Query query = getSession().createSQLQuery(sql);
		query.setFirstResult(page.getFirst() - 1);
		query.setMaxResults(page.getPageSize());
		List<?> list = query.list();
		Iterator<?> it = list.iterator();
		List<CLFWBean> beans = new ArrayList<CLFWBean>();
		while (it.hasNext()) {
			Object[] obj = (Object[]) it.next();
			CLFWBean bean = new CLFWBean();
				bean.setCLFW_HBXXYL_SFJIDSTR(ClfwCommon.configcommon(obj[0]));
				bean.setOPE_INSERTTIME(ClfwCommon.configcommon(obj[1]));
				bean.setCLFW_HBXXLDBW_ZBS(ClfwCommon.configcommon(obj[2]));
				bean.setCLFW_HBXXLDBW_AJTHZBS(ClfwCommon.configcommon(obj[3]));
				bean.setCLFW_HBXXLDBW_LXZBS(ClfwCommon.configcommon(obj[4]));
				bean.setCLFW_HBXXLDBW_SSZBS(ClfwCommon.configcommon(obj[5]));
				bean.setCLFW_HBXXGL_XSBZ(ClfwCommon.configcommon(obj[6]));
				bean.setCLFW_HBXXLDBW_SSZBSXU(ClfwCommon.configcommon(obj[7]));
				bean.setCLFW_HBXXLDBW_SFJGH(ClfwCommon.configcommon(obj[8]));
			beans.add(bean);
		}
		page.setResult(beans);
		page.setAjaxUrl(PageUtils.getPageUrl(page, "AJAx"));
		return page;
	}
}
