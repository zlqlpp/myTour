package com.cpst.emsadrdb.dao.clfw;


import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import com.cpst.core.orm.hibernate.HibernateDao;
import com.cpst.emsadrdb.entity.clfw.CLFWBean;
import com.cpst.emsadrdb.entity.clfw.DistrictBean;
import com.cpst.emsadrdb.service.clfw.ClfwCommon;

@Repository
@Transactional
public class ClfwOptionDao extends HibernateDao<DistrictBean, String> {

	@Transactional(readOnly = true)
	public List<DistrictBean> getProvinces(String DISTRICT_CODE) {
		String sql="select a.DISTRICT_CODE,a.PROVINCE_NAME from CP_BASE_ORG_DISTRICT a where a.DEGREE='1' ";
		if(DISTRICT_CODE!=null && !DISTRICT_CODE.equals("null")  && DISTRICT_CODE.length()>0){
			sql = sql + " and a.DISTRICT_CODE like '" + DISTRICT_CODE.substring(0,2) + "%'";
		}
		sql = sql + " order by a.DISTRICT_CODE";
		List<?> list = getSession().createSQLQuery(sql).list();
		Iterator<?> it = list.iterator();
		List<DistrictBean> beans=new ArrayList<DistrictBean>();
		while (it.hasNext()) {
			Object[] obj = (Object[]) it.next();
			DistrictBean bean = new DistrictBean();
			if (obj[0] != null) bean.setDISTRICT_CODE(obj[0].toString());
			else  bean.setDISTRICT_CODE("");
			if (obj[1] != null) bean.setPROVINCE_NAME(obj[1].toString());
			else  bean.setPROVINCE_NAME("");	
			beans.add(bean);
		}
		return beans;
	}
	
	@Transactional(readOnly = true)
	public List<DistrictBean> getCitys(String PROVINCE_NAME,String DISTRICT_CODE) {
		String sql="";
		if(ClfwCommon.isMunicipalities(PROVINCE_NAME)){
			sql="select a.DISTRICT_CODE,a.COUNTY_NAME from CP_BASE_ORG_DISTRICT a where a.DEGREE='3' and a.PROVINCE_NAME = '" + PROVINCE_NAME + "'  ";
		}else{
			sql="select a.DISTRICT_CODE,a.CITY_NAME from CP_BASE_ORG_DISTRICT a where a.DEGREE='2' and a.PROVINCE_NAME = '" + PROVINCE_NAME + "'  ";
			if(DISTRICT_CODE!=null && !DISTRICT_CODE.equals("null")  && DISTRICT_CODE.length()>0){
				sql = sql + " and a.DISTRICT_CODE like '" + DISTRICT_CODE.substring(0,4) + "%'";
			}
		}
		sql = sql + " order by a.DISTRICT_CODE";
		List<?> list = getSession().createSQLQuery(sql).list();
		Iterator<?> it = list.iterator();
		List<DistrictBean> beans=new ArrayList<DistrictBean>();
		while (it.hasNext()) {
			Object[] obj = (Object[]) it.next();
			DistrictBean bean = new DistrictBean();
			if (obj[0] != null) bean.setDISTRICT_CODE(obj[0].toString());
			else  bean.setDISTRICT_CODE("");
			if (obj[1] != null) bean.setCITY_NAME(obj[1].toString());
			else  bean.setCITY_NAME("");	
			beans.add(bean);
		}
		return beans;
	}
	
	@Transactional(readOnly = true)
	public List<DistrictBean> getCountys(String CITY_NAME,String DISTRICT_CODE) {
		String sql="";
		if(DISTRICT_CODE!=null && !DISTRICT_CODE.equals("null")){
		if(DISTRICT_CODE.substring(4, 6).equals("00")){
			sql="select a.DISTRICT_CODE,a.COUNTY_NAME from CP_BASE_ORG_DISTRICT a where a.DEGREE='3' and a.CITY_NAME = '" + CITY_NAME + "' ";
		}else{
			sql="select a.DISTRICT_CODE,a.COUNTY_NAME from CP_BASE_ORG_DISTRICT a where a.DEGREE='3' and a.CITY_NAME = '" + CITY_NAME + "' ";
			if(DISTRICT_CODE!=null && !DISTRICT_CODE.equals("null")  && DISTRICT_CODE.length()>0){
				sql = sql + " and a.DISTRICT_CODE = '" + DISTRICT_CODE + "'";
			}
		}
		}else{
			sql="select a.DISTRICT_CODE,a.COUNTY_NAME from CP_BASE_ORG_DISTRICT a where a.DEGREE='3' and a.CITY_NAME = '" + CITY_NAME + "' ";
		}
		sql = sql + " order by a.DISTRICT_CODE";
		List<?> list = getSession().createSQLQuery(sql).list();
		Iterator<?> it = list.iterator();
		List<DistrictBean> beans=new ArrayList<DistrictBean>();
		while (it.hasNext()) {
			Object[] obj = (Object[]) it.next();
			DistrictBean bean = new DistrictBean();
			if (obj[0] != null) bean.setDISTRICT_CODE(obj[0].toString());
			else  bean.setDISTRICT_CODE("");
			if (obj[1] != null) bean.setCOUNTY_NAME(obj[1].toString());
			else  bean.setCOUNTY_NAME("");	
			beans.add(bean);
		}
		return beans;
	}
	
	@Transactional(readOnly = true)
	public List<DistrictBean> getCountysqd(String CITY_NAME) {
		String sql="";
		if(ClfwCommon.isMunicipalitiesID(CITY_NAME.substring(0,2))){
			sql="select a.DISTRICT_CODE,a.COUNTY_NAME from CP_BASE_ORG_DISTRICT a where a.DEGREE='3' and a.DISTRICT_CODE like '" + CITY_NAME.substring(0,2) + "%' ";
		}else{
			sql="select a.DISTRICT_CODE,a.COUNTY_NAME from CP_BASE_ORG_DISTRICT a where a.DEGREE='3' and a.DISTRICT_CODE like '" + CITY_NAME.substring(0,4) + "%' ";
		}
		sql = sql + " order by a.DISTRICT_CODE";
		List<?> list = getSession().createSQLQuery(sql).list();
		Iterator<?> it = list.iterator();
		List<DistrictBean> beans=new ArrayList<DistrictBean>();
		while (it.hasNext()) {
			Object[] obj = (Object[]) it.next();
			DistrictBean bean = new DistrictBean();
			if (obj[0] != null) bean.setDISTRICT_CODE(obj[0].toString());
			else  bean.setDISTRICT_CODE("");
			if (obj[1] != null) bean.setCOUNTY_NAME(obj[1].toString());
			else  bean.setCOUNTY_NAME("");	
			beans.add(bean);
		}
		return beans;
	}
	
	@Transactional(readOnly = true)
	public List<CLFWBean> getClfwpcs(String CITY_NAME,String CLFWPC_SX) {
		String sql="select  a.CLFWPC_SEQID,a.CLFWPC_MC||decode(a.CLFWPC_SX,1,decode(substr(a.CLFWPC_MC,0,1),'Z','(直达)','(省中心)'),2,'(揽收)',3,'(投递)','(未配置)') from CLFW_CLFWPC a where 1=1 ";
		
		if (CITY_NAME!=null && CITY_NAME!="null" && CITY_NAME.length() > 0) {
			sql= sql + " and a.CLFWPC_DISTCD = '" + CITY_NAME + "'";
		}
		
		if (!CLFWPC_SX.equals("null") && CLFWPC_SX.length() > 0) {
			if (CLFWPC_SX.equals("1")) {
				sql = sql + " and a.CLFWPC_SX = 1 ";
			} else if (CLFWPC_SX.equals("2")) {
				sql = sql + " and a.CLFWPC_SX = 2 ";
			} else if (CLFWPC_SX.equals("3")) {
				sql = sql + " and a.CLFWPC_SX = 3 ";
			} else if (CLFWPC_SX.equals("23")) {
				sql = sql + " and a.CLFWPC_SX in (2,3) ";
			}
		}
		
		sql = sql + " order by a.CLFWPC_MC";
		List<?> list = getSession().createSQLQuery(sql).list();
		Iterator<?> it = list.iterator();
		List<CLFWBean> beans=new ArrayList<CLFWBean>();
		while (it.hasNext()) {
			Object[] obj = (Object[]) it.next();
			CLFWBean bean = new CLFWBean();
			if (obj[0] != null) bean.setCLFWPC_SEQID(obj[0].toString());
			else  bean.setCLFWPC_SEQID("");
			if (obj[1] != null) bean.setCLFWPC_MC(obj[1].toString());
			else  bean.setCLFWPC_MC("");	
			beans.add(bean);
		}
		return beans;
	}
	
	@Transactional(readOnly = true)
	public List<CLFWBean> getClfwpcssheng(String CITY_NAME,String CLFWPC_SX) {
		String sql="select  b.CLFWPCSHENG_SEQID,'['||a.CLFWPC_MC||decode(a.CLFWPC_SX,1,decode(substr(a.CLFWPC_MC,0,1),'Z','(直达)','(省中心)'),2,'(揽收)',3,'(投递)','(未配置)')||']'||b.CLFWPCSHENG_SHENGM " +
				" from CLFW_CLFWPC a,CLFW_CLFWPC_SHENG b where a.CLFWPC_SEQID = b.CLFWPC_SEQID ";
		
		if (CITY_NAME!=null && CITY_NAME.length() > 0) {
			sql= sql + " and b.CLFWPCSHENG_DISTCD = '" + CITY_NAME + "'";
		}
		
		if (!CLFWPC_SX.equals("null") && CLFWPC_SX.length() > 0) {
			if (CLFWPC_SX.equals("1")) {
				sql = sql + " and a.CLFWPC_SX = 1 ";
			} else if (CLFWPC_SX.equals("2")) {
				sql = sql + " and a.CLFWPC_SX = 2 ";
			} else if (CLFWPC_SX.equals("3")) {
				sql = sql + " and a.CLFWPC_SX = 3 ";
			} else if (CLFWPC_SX.equals("23")) {
				sql = sql + " and a.CLFWPC_SX in (2,3) ";
			}
		}
		
		sql = sql + " order by a.CLFWPC_SX,a.CLFWPC_MC,b.CLFWPCSHENG_SHENGM";
		List<?> list = getSession().createSQLQuery(sql).list();
		Iterator<?> it = list.iterator();
		List<CLFWBean> beans=new ArrayList<CLFWBean>();
		while (it.hasNext()) {
			Object[] obj = (Object[]) it.next();
			CLFWBean bean = new CLFWBean();
			if (obj[0] != null) bean.setCLFWPCSHENG_SEQID(obj[0].toString());
			else  bean.setCLFWPCSHENG_SEQID("");
			if (obj[1] != null) bean.setCLFWPCSHENG_SHENAGM(obj[1].toString());
			else  bean.setCLFWPCSHENG_SHENAGM("");	
			beans.add(bean);
		}
		return beans;
	}
	
	@Transactional(readOnly = true)
	public List<CLFWBean> getClfwpcsshi(String CITY_NAME,String CLFWPC_SX) {
		String sql="select  b.CLFWPCSHI_SEQID,'['||a.CLFWPC_MC||decode(a.CLFWPC_SX,1,decode(substr(a.CLFWPC_MC,0,1),'Z','(直达)','(省中心)'),2,'(揽收)',3,'(投递)','(未配置)')||']'||b.CLFWPCSHI_SHIGM||'--'||decode(c.CLFWPCSHENG_SHENGM,null,'未配置衔接频次',c.CLFWPCSHENG_SHENGM) " +
				" from CLFW_CLFWPC a,CLFW_CLFWPC_SHI b," +
				"(select " +
				" cb.CLFWPCSHENG_SEQID CLFWPCSHENG_SEQID," +
				" '['||ca.CLFWPC_MC||decode(ca.CLFWPC_SX,1,decode(substr(ca.CLFWPC_MC,0,1),'Z','(直达)','(省中心)'),2,'(揽收)',3,'(投递)','(未配置)')||']'||cb.CLFWPCSHENG_SHENGM CLFWPCSHENG_SHENGM"+
				" from clfw_clfwpc ca,CLFW_CLFWPC_SHENG cb where ca.clfwpc_seqid = cb.clfwpc_seqid" +
				") c" +
				" where a.CLFWPC_SEQID = b.CLFWPC_SEQID and b.CLFWPCSHENG_SEQID = c.CLFWPCSHENG_SEQID(+) ";
		
		if(!CITY_NAME.equals("null") && CITY_NAME.length()>0){
			if(ClfwCommon.isMunicipalitiesID(CITY_NAME.substring(0,2))){
				sql= sql + " and b.CLFWPCSHI_DISTCD like '" + CITY_NAME.substring(0, 2) + "%'";
			}else{
				sql= sql + " and b.CLFWPCSHI_DISTCD like '" + CITY_NAME.substring(0, 4) + "%'";
			}
		}
		
		if (!CLFWPC_SX.equals("null") && CLFWPC_SX.length() > 0) {
			if (CLFWPC_SX.equals("1")) {
				sql = sql + " and a.CLFWPC_SX = 1 ";
			} else if (CLFWPC_SX.equals("2")) {
				sql = sql + " and a.CLFWPC_SX = 2 ";
			} else if (CLFWPC_SX.equals("3")) {
				sql = sql + " and a.CLFWPC_SX = 3 ";
			} else if (CLFWPC_SX.equals("23")) {
				sql = sql + " and a.CLFWPC_SX in (2,3) ";
			}
		}
		
		sql = sql + " order by a.CLFWPC_SX,a.CLFWPC_MC,b.CLFWPCSHI_SHIGM";
		List<?> list = getSession().createSQLQuery(sql).list();
		Iterator<?> it = list.iterator();
		List<CLFWBean> beans=new ArrayList<CLFWBean>();
		while (it.hasNext()) {
			Object[] obj = (Object[]) it.next();
			CLFWBean bean = new CLFWBean();
			if (obj[0] != null) bean.setCLFWPCSHI_SEQID(obj[0].toString());
			else  bean.setCLFWPCSHI_SEQID("");
			if (obj[1] != null) bean.setCLFWPCSHI_SHIAGM(obj[1].toString());
			else  bean.setCLFWPCSHI_SHIAGM("");	
			beans.add(bean);
		}
		return beans;
	}
	
	@Transactional(readOnly = true)
	public List<CLFWBean> getWhclfwtdbs(String CITY_NAME,String CLFWPC_NAMES,String RulUsDepartmentOffice) {
		String sql=" select a.dm_name||'['||a.office_code||']',a.dm_pk_code from CP_WH_DEPARTMENT a ," +
				" (select * from clfw_clfwtdb  where CLFWPCSHI_SEQID = '" + CLFWPC_NAMES + "') b" +
				" where a.dm_pk_code = b.dm_pk_code(+) and b.dm_pk_code is null " +
				" and a.city_code = '" + CITY_NAME + "'";
		if(RulUsDepartmentOffice!=null && !RulUsDepartmentOffice.equals("null")  && RulUsDepartmentOffice.length()>0){
			sql = sql + " and a.DM_PK_CODE = '" + RulUsDepartmentOffice + "'";
		}
		sql = sql + " order by a.dm_name";
		List<?> list = getSession().createSQLQuery(sql).list();
		Iterator<?> it = list.iterator();
		List<CLFWBean> beans=new ArrayList<CLFWBean>();
		while (it.hasNext()) {
			Object[] obj = (Object[]) it.next();
			CLFWBean bean = new CLFWBean();
			if (obj[0] != null) bean.setDM_NAME(obj[0].toString());
			else  bean.setDM_NAME("");
			if (obj[1] != null) bean.setDM_PK_CODE(obj[1].toString());
			else  bean.setDM_PK_CODE("");
			beans.add(bean);
		}
		return beans;
	}
	
	@Transactional(readOnly = true)
	public List<CLFWBean> getDMs(String CITY_NAME,String RulUsDepartmentOffice) {
		String sql="select a.DM_PK_CODE,a.DM_NAME||'['||a.office_code||']' from CP_WH_DEPARTMENT a where a.city_code = '" + CITY_NAME + "'";
		if(RulUsDepartmentOffice!=null && !RulUsDepartmentOffice.equals("null")  && RulUsDepartmentOffice.length()>0){
			sql = sql + " and a.DM_PK_CODE = '" + RulUsDepartmentOffice + "'";
		}
		sql = sql + " order by a.dm_name";
		List<?> list = getSession().createSQLQuery(sql).list();
		Iterator<?> it = list.iterator();
		List<CLFWBean> beans=new ArrayList<CLFWBean>();
		while (it.hasNext()) {
			Object[] obj = (Object[]) it.next();
			CLFWBean bean = new CLFWBean();
			if (obj[0] != null) bean.setDM_PK_CODE(obj[0].toString());
			else  bean.setDM_PK_CODE("");
			if (obj[1] != null) bean.setDM_NAME(obj[1].toString());
			else  bean.setDM_NAME("");	
			beans.add(bean);
		}
		return beans;
	}
	
	@Transactional(readOnly = true)
	public List<CLFWBean> getPGs(String DM_PK_CODE) {
		String sql="select a.PG_PK_CODE,a.PG_NAME from CP_WH_POSTSEG a where a.DM_PK_CODE='" + DM_PK_CODE + "'";
		sql = sql + " order by a.PG_PK_CODE";
		List<?> list = getSession().createSQLQuery(sql).list();
		Iterator<?> it = list.iterator();
		List<CLFWBean> beans=new ArrayList<CLFWBean>();
		while (it.hasNext()) {
			Object[] obj = (Object[]) it.next();
			CLFWBean bean = new CLFWBean();
			if (obj[0] != null) bean.setPG_PK_CODE(obj[0].toString());
			else  bean.setPG_PK_CODE("");
			if (obj[1] != null) bean.setPG_NAME(obj[1].toString());
			else  bean.setPG_NAME("");	
			beans.add(bean);
		}
		return beans;
	}
	
	@Transactional(readOnly = true)
	public List<CLFWBean> getClfwylxxbz(String CITY_NAME) {
		String sql="select CLFWYLXXBZ_SEQID,CLFWYLXXBZ_YLMC||'['||CLFWYLXXBZ_SJKCSJ||'-'||CLFWYLXXBZ_SJDDSJ||']' CLFWYLXXBZ_YLMC" +
				" from CLFW_YLXXBZ a ";
		
		if (CITY_NAME!=null && CITY_NAME.length() > 0) {
			sql= sql + " and a.CLFWYLXXBZ_SFJID = '" + CITY_NAME + "'";
		}
		
		
		sql = sql + " order by a.CLFWYLXXBZ_YLMC";
		List<?> list = getSession().createSQLQuery(sql).list();
		Iterator<?> it = list.iterator();
		List<CLFWBean> beans=new ArrayList<CLFWBean>();
		while (it.hasNext()) {
			Object[] obj = (Object[]) it.next();
			CLFWBean bean = new CLFWBean();
			if (obj[0] != null) bean.setCLFWYLXXBZ_SEQID(obj[0].toString());
			else  bean.setCLFWYLXXBZ_SEQID("");
			if (obj[1] != null) bean.setCLFWYLXXBZ_YLMC(obj[1].toString());
			else  bean.setCLFWYLXXBZ_YLMC("");
			beans.add(bean);
		}
		return beans;
	}
}
