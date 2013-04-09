package com.cpst.emsadrdb.dao.disp;


import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import com.cpst.core.orm.hibernate.HibernateDao;
import com.cpst.emsadrdb.entity.disp.DispBean;
import com.cpst.emsadrdb.entity.disp.DistrictBean;
import com.cpst.emsadrdb.entity.disp.WhdistrictBean;
import com.cpst.emsadrdb.service.disp.DispCommon;


@Repository
@Transactional
public class DispOptionDao extends HibernateDao<DistrictBean, String> {

	@Transactional(readOnly = true)
	public List<DistrictBean> getProvinces(String DISTRICT_CODE) {
		String sql="select a.DISTRICT_CODE,a.PROVINCE_NAME from CP_BASE_ORG_DISTRICT a where a.DEGREE='1' ";
		if(DISTRICT_CODE!=null && !DISTRICT_CODE.equals("null")  && DISTRICT_CODE.length()>0){
			sql = sql + " and a.DISTRICT_CODE  like '" + DISTRICT_CODE.substring(0,2) + "%'";
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
		if(DispCommon.isMunicipalities(PROVINCE_NAME)){
			sql="select a.DISTRICT_CODE,a.COUNTY_NAME from CP_BASE_ORG_DISTRICT a where a.DEGREE='3' and a.PROVINCE_NAME = '" + PROVINCE_NAME + "'  ";
		}else{
			sql="select a.DISTRICT_CODE,a.CITY_NAME from CP_BASE_ORG_DISTRICT a where a.DEGREE='2' and a.PROVINCE_NAME = '" + PROVINCE_NAME + "'  ";
			if(DISTRICT_CODE!=null && !DISTRICT_CODE.equals("null")  && DISTRICT_CODE.length()>0){
				sql = sql + " and a.DISTRICT_CODE  like '" + DISTRICT_CODE.substring(0,4) + "%'";
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
	public List<DistrictBean> getCitysname(String PROVINCE_NAME,String DISTRICT_CODE) {
		String sql="";
		if(DispCommon.isMunicipalities(PROVINCE_NAME)){
			sql="select a.DISTRICT_CODE,a.COUNTY_NAME from CP_BASE_ORG_DISTRICT a where a.CITY_FLAG='0' and a.DEGREE='3' and a.PROVINCE_NAME = '" + PROVINCE_NAME + "'  ";
		}else{
			sql="select a.DISTRICT_CODE,a.CITY_NAME from CP_BASE_ORG_DISTRICT a where a.DEGREE='2' and a.PROVINCE_NAME = '" + PROVINCE_NAME + "'  ";
			if(DISTRICT_CODE!=null && !DISTRICT_CODE.equals("null")  && DISTRICT_CODE.length()>0){
				sql = sql + " and a.DISTRICT_CODE  like '" + DISTRICT_CODE.substring(0,4) + "%'";
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
			sql="select a.DISTRICT_CODE,a.COUNTY_NAME from CP_BASE_ORG_DISTRICT a where a.CITY_FLAG='0' and  a.DEGREE='3' and a.CITY_NAME = '" + CITY_NAME + "' ";
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
	public List<DistrictBean> getCountysname(String CITY_NAME,String DISTRICT_CODE) {
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
			sql="select a.DISTRICT_CODE,a.COUNTY_NAME from CP_BASE_ORG_DISTRICT a where a.CITY_FLAG='0' and a.DEGREE='3' and a.CITY_NAME = '" + CITY_NAME + "' ";
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
	public List<DispBean> getDispp(String PROVINCE_CODE) {
		String sql="select distinct a.PROVINCE_CODE,a.PROVINCE_NAME from CP_BASE_DISP_OFFICE a where 1=1 ";
		if(PROVINCE_CODE!=null && !PROVINCE_CODE.equals("null")  && PROVINCE_CODE.length()>0){
			sql = sql + " and a.PROVINCE_CODE = '" + PROVINCE_CODE + "'";
		}
		sql = sql + " order by a.PROVINCE_CODE";
		List<?> list = getSession().createSQLQuery(sql).list();
		Iterator<?> it = list.iterator();
		List<DispBean> beans=new ArrayList<DispBean>();
		while (it.hasNext()) {
			Object[] obj = (Object[]) it.next();
			DispBean bean = new DispBean();
			if (obj[0] != null) bean.setPROVINCE_CODE(obj[0].toString());
			else  bean.setPROVINCE_CODE("");
			if (obj[1] != null) bean.setPROVINCE_NAME(obj[1].toString());
			else  bean.setPROVINCE_NAME("");	
			beans.add(bean);
		}
		return beans;
	}
	
	@Transactional(readOnly = true)
	public List<DispBean> getDisppd(String PROVINCE_NAME,String DISP_OFFICE_CODE) {
		String sql="select distinct a.DISP_OFFICE_CODE,a.DISP_OFFICE_NAME from CP_BASE_DISP_OFFICE a where 1=1  and a.PROVINCE_NAME = '" + PROVINCE_NAME + "'  ";
		if(DISP_OFFICE_CODE!=null && !DISP_OFFICE_CODE.equals("null")  && DISP_OFFICE_CODE.length()>0){
			sql = sql + " and a.DISP_OFFICE_CODE = '" + DISP_OFFICE_CODE + "'";
		}
		sql = sql + " order by a.DISP_OFFICE_CODE";
		List<?> list = getSession().createSQLQuery(sql).list();
		Iterator<?> it = list.iterator();
		List<DispBean> beans=new ArrayList<DispBean>();
		while (it.hasNext()) {
			Object[] obj = (Object[]) it.next();
			DispBean bean = new DispBean();
			if (obj[0] != null) bean.setDISP_OFFICE_CODE(obj[0].toString());
			else  bean.setDISP_OFFICE_CODE("");
			if (obj[1] != null) bean.setDISP_OFFICE_NAME(obj[1].toString());
			else  bean.setDISP_OFFICE_NAME("");
			beans.add(bean);
		}
		return beans;
	}
	
	@Transactional(readOnly = true)
	public List<DispBean> getDispd(String DISP_PROVINCE_NAME) {
		String sql="select distinct a.DISP_OFFICE_CODE,a.DISP_OFFICE_NAME from CP_BASE_DISP_OFFICE a where 1=1 ";
		sql = sql + " and a.PROVINCE_NAME = '" + DISP_PROVINCE_NAME + "'";
		sql = sql + " order by a.DISP_OFFICE_CODE";
		List<?> list = getSession().createSQLQuery(sql).list();
		Iterator<?> it = list.iterator();
		List<DispBean> beans=new ArrayList<DispBean>();
		while (it.hasNext()) {
			Object[] obj = (Object[]) it.next();
			DispBean bean = new DispBean();
			if (obj[0] != null) bean.setDISP_OFFICE_CODE(obj[0].toString());
			else  bean.setDISP_OFFICE_CODE("");
			if (obj[1] != null) bean.setDISP_OFFICE_ABBR(obj[1].toString());
			else  bean.setDISP_OFFICE_ABBR("");	
			beans.add(bean);
		}
		return beans;
	}
	
	@Transactional(readOnly = true)
	public List<DispBean> getDispdwh(String DISP_OFFICE_CODE) {
		String sql="select a.DT_PK_CODE,b.DT_NAME||'('||b.DT_ALIAS_NAME||')' from CP_WH_DISP_OFFICE_DIS a ,CP_WH_DISTRICT b where 1=1 and a.DT_PK_CODE=b.DT_PK_CODE";
		sql = sql + " and a.DISP_OFFICE_CODE = '" + DISP_OFFICE_CODE + "'";
		sql = sql + " order by a.DISP_OFFICE_CODE";
		List<?> list = getSession().createSQLQuery(sql).list();
		Iterator<?> it = list.iterator();
		List<DispBean> beans=new ArrayList<DispBean>();
		while (it.hasNext()) {
			Object[] obj = (Object[]) it.next();
			DispBean bean = new DispBean();
			if (obj[0] != null) bean.setDT_PK_CODE(obj[0].toString());
			else  bean.setDT_PK_CODE("");
			if (obj[1] != null) bean.setDT_NAME(obj[1].toString());
			else  bean.setDT_NAME("");	
			beans.add(bean);
		}
		return beans;
	}
	
	@Transactional(readOnly = true)
	public List<WhdistrictBean> getWhdistrictdispl(String PROVINCE_CODE) {
		String sql="select distinct a.DT_PK_CODE,a.DT_NAME||'('||a.DT_ALIAS_NAME||')' from CP_WH_DISTRICT a where 1=1 ";
		sql = sql + " and a.DT_PK_CODE not in (select DT_PK_CODE from CP_WH_DISP_OFFICE_DIS) and a.DT_PROVINCE_CODE = '" + PROVINCE_CODE + "'";
		sql = sql + " order by a.DT_PK_CODE";
		List<?> list = getSession().createSQLQuery(sql).list();
		Iterator<?> it = list.iterator();
		List<WhdistrictBean> beans=new ArrayList<WhdistrictBean>();
		while (it.hasNext()) {
			Object[] obj = (Object[]) it.next();
			WhdistrictBean bean = new WhdistrictBean();
			if (obj[0] != null) bean.setDT_PK_CODE(obj[0].toString());
			else  bean.setDT_PK_CODE("");
			if (obj[1] != null) bean.setDT_NAME(obj[1].toString());
			else  bean.setDT_NAME("");	
			beans.add(bean);
		}
		return beans;
	}
	
	@Transactional(readOnly = true)
	public List<WhdistrictBean> getWhdistrictdispr(String CITY_CODE) {
		String sql="select distinct a.DT_PK_CODE,a.DT_NAME||'('||a.DT_ALIAS_NAME||')' from CP_WH_DISTRICT a,CP_WH_DISP_OFFICE_DIS b where 1=1 ";
		sql = sql + " and a.DT_PK_CODE = b.DT_PK_CODE and b.DISP_OFFICE_CODE = '" + CITY_CODE + "'";
		sql = sql + " order by a.DT_PK_CODE";
		List<?> list = getSession().createSQLQuery(sql).list();
		Iterator<?> it = list.iterator();
		List<WhdistrictBean> beans=new ArrayList<WhdistrictBean>();
		while (it.hasNext()) {
			Object[] obj = (Object[]) it.next();
			WhdistrictBean bean = new WhdistrictBean();
			if (obj[0] != null) bean.setDT_PK_CODE(obj[0].toString());
			else  bean.setDT_PK_CODE("");
			if (obj[1] != null) bean.setDT_NAME(obj[1].toString());
			else  bean.setDT_NAME("");	
			beans.add(bean);
		}
		return beans;
	}
	
	@Transactional(readOnly = true)
	public List<WhdistrictBean> getWhdistricttransl(String PROVINCE_CODE) {
		String sql="select distinct a.DT_PK_CODE,a.DT_NAME||'('||a.DT_ALIAS_NAME||')' from CP_WH_DISTRICT a where 1=1 ";
		sql = sql + " and a.DT_PK_CODE not in (select DT_PK_CODE from CP_WH_TRANS_OFFICE_DIS) and a.DT_PROVINCE_CODE = '" + PROVINCE_CODE + "'";
		sql = sql + " order by a.DT_PK_CODE";
		List<?> list = getSession().createSQLQuery(sql).list();
		Iterator<?> it = list.iterator();
		List<WhdistrictBean> beans=new ArrayList<WhdistrictBean>();
		while (it.hasNext()) {
			Object[] obj = (Object[]) it.next();
			WhdistrictBean bean = new WhdistrictBean();
			if (obj[0] != null) bean.setDT_PK_CODE(obj[0].toString());
			else  bean.setDT_PK_CODE("");
			if (obj[1] != null) bean.setDT_NAME(obj[1].toString());
			else  bean.setDT_NAME("");	
			beans.add(bean);
		}
		return beans;
	}
	
	@Transactional(readOnly = true)
	public List<WhdistrictBean> getWhdistricttransr(String CITY_CODE) {
		String sql="select distinct a.DT_PK_CODE,a.DT_NAME||'('||a.DT_ALIAS_NAME||')' from CP_WH_DISTRICT a,CP_WH_TRANS_OFFICE_DIS b where 1=1 ";
		sql = sql + " and a.DT_PK_CODE = b.DT_PK_CODE and b.TRANS_CODE = '" + CITY_CODE + "'";
		sql = sql + " order by a.DT_PK_CODE";
		List<?> list = getSession().createSQLQuery(sql).list();
		Iterator<?> it = list.iterator();
		List<WhdistrictBean> beans=new ArrayList<WhdistrictBean>();
		while (it.hasNext()) {
			Object[] obj = (Object[]) it.next();
			WhdistrictBean bean = new WhdistrictBean();
			if (obj[0] != null) bean.setDT_PK_CODE(obj[0].toString());
			else  bean.setDT_PK_CODE("");
			if (obj[1] != null) bean.setDT_NAME(obj[1].toString());
			else  bean.setDT_NAME("");	
			beans.add(bean);
		}
		return beans;
	}
}
