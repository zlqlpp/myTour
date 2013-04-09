package com.cpst.emsadrdb.dao.address;


import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import com.cpst.core.orm.hibernate.HibernateDao;
import com.cpst.emsadrdb.entity.address.DistrictBean;
import com.cpst.emsadrdb.entity.address.StreetBean;
import com.cpst.emsadrdb.service.address.AddrCommon;
import com.cpst.emsadrdb.entity.address.CpBean;
import com.cpst.emsadrdb.entity.address.DmBean;
import com.cpst.emsadrdb.entity.address.PgBean;
import com.cpst.emsadrdb.entity.address.QhzuiBean;
import com.cpst.emsadrdb.entity.address.ResorgBean;
import com.cpst.emsadrdb.entity.disp.CpwhrlpgstBean;

@Repository
@Transactional
public class AddrOptionDao extends HibernateDao<DistrictBean, String> {

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
	public List<DistrictBean> getProvincesno(String DISTRICT_CODE) {
		String sql="select a.DISTRICT_CODE,a.PROVINCE_NAME from CP_BASE_ORG_DISTRICT a where a.DEGREE='1' ";
		if(DISTRICT_CODE!=null && !DISTRICT_CODE.equals("null")  && DISTRICT_CODE.length()>0){
			sql = sql + " and a.DISTRICT_CODE not like '" + DISTRICT_CODE.substring(0,2) + "%'";
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
		if(AddrCommon.isMunicipalities(PROVINCE_NAME)){
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
	public List<DistrictBean> getCitysno(String PROVINCE_NAME,String DISTRICT_CODE) {
		String sql="";
		if(AddrCommon.isMunicipalities(PROVINCE_NAME)){
			sql="select a.DISTRICT_CODE,a.COUNTY_NAME from CP_BASE_ORG_DISTRICT a where a.DEGREE='3' and a.PROVINCE_NAME = '" + PROVINCE_NAME + "'  ";
		}else{
			sql="select a.DISTRICT_CODE,a.CITY_NAME from CP_BASE_ORG_DISTRICT a where a.DEGREE='2' and a.PROVINCE_NAME = '" + PROVINCE_NAME + "'  ";
			if(DISTRICT_CODE!=null && !DISTRICT_CODE.equals("null")  && DISTRICT_CODE.length()>0){
				sql = sql + " and a.DISTRICT_CODE not like '" + DISTRICT_CODE.substring(0,4) + "%'";
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
	public List<ResorgBean> getResorgs(String COUNTY_ID,String DISTRICT_CODE) {
		String sql="";
		
		sql="select a.org_code,a.org_sname from res_org a  where a.county_code = '" + COUNTY_ID + "'  ";
		if(DISTRICT_CODE!=null && !DISTRICT_CODE.equals("null")  && DISTRICT_CODE.length()>0){
			sql = sql + " and a.COUNTY_ID = '" + DISTRICT_CODE + "'";
		}
		sql = sql + " order by a.org_sname";
		List<?> list = getSession().createSQLQuery(sql).list();
		Iterator<?> it = list.iterator();
		List<ResorgBean> beans=new ArrayList<ResorgBean>();
		while (it.hasNext()) {
			Object[] obj = (Object[]) it.next();
			ResorgBean bean = new ResorgBean();
			if (obj[0] != null) bean.setORG_CODE(obj[0].toString());
			else  bean.setORG_CODE("");
			if (obj[1] != null) bean.setORG_CNAME(obj[1].toString());
			else  bean.setORG_CNAME("");	
			beans.add(bean);
		}
		return beans;
	}
	
	@Transactional(readOnly = true)
	public List<StreetBean> getStreets(String COUNTY_ID,String STREET_NAME) {
		String sql=" select a.STRT_ID," +
				" a.STRT1_NAME||decode(a.STRT2_NAME,null,'','-'||a.STRT2_NAME)||decode(a.STRT3_NAME,null,'','-'||a.STRT3_NAME)||decode(a.STRT4_NAME,null,'','-'||a.STRT4_NAME)||decode(a.STRT5_NAME,null,'','-'||a.STRT5_NAME) || " +
				" decode(a.STRT1_ABBR_NAME||a.STRT2_ABBR_NAME||a.STRT3_ABBR_NAME||a.STRT4_ABBR_NAME||a.STRT5_ABBR_NAME,null,'','[') || " +
				" a.STRT1_ABBR_NAME||decode(a.STRT2_ABBR_NAME,null,'','-'||a.STRT2_ABBR_NAME)||decode(a.STRT3_ABBR_NAME,null,'','-'||a.STRT3_ABBR_NAME)||decode(a.STRT4_ABBR_NAME,null,'','-'||a.STRT4_ABBR_NAME)||decode(a.STRT5_ABBR_NAME,null,'','-'||a.STRT5_ABBR_NAME) || " +
				" decode(a.STRT1_ABBR_NAME||a.STRT2_ABBR_NAME||a.STRT3_ABBR_NAME||a.STRT4_ABBR_NAME||a.STRT5_ABBR_NAME,null,'',']') " +
				" from CP_MK_ADR_STREET_" + COUNTY_ID.substring(0, 2) + " a " +
				" where a.DIST_CD=" + COUNTY_ID + " " +
				" and (a.STRT1_NAME||a.STRT2_NAME||a.STRT3_NAME||a.STRT4_NAME||a.STRT5_NAME like '%" + STREET_NAME + "%' " +
				" or a.STRT1_ABBR_NAME||a.STRT2_ABBR_NAME||a.STRT3_ABBR_NAME||a.STRT4_ABBR_NAME||a.STRT5_ABBR_NAME like '%" + STREET_NAME + "%') " + "  " +
				" order by a.STRT1_NAME||a.STRT2_NAME||a.STRT3_NAME||a.STRT4_NAME||a.STRT5_NAME";
		List<?> list = getSession().createSQLQuery(sql).list();
		Iterator<?> it = list.iterator();
		List<StreetBean> beans=new ArrayList<StreetBean>();
		while (it.hasNext()) {
			Object[] obj = (Object[]) it.next();
			StreetBean bean = new StreetBean();
			if (obj[0] != null) bean.setSTRT_ID(obj[0].toString());
			else  bean.setSTRT_ID("");
			if (obj[1] != null) bean.setTOTAL_STREET_NAME(obj[1].toString());
			else  bean.setTOTAL_STREET_NAME("");	
			beans.add(bean);
		}
		return beans;
	}
		
	@Transactional(readOnly = true)
	public List<CpBean> getDTs(String CITY_CODE,String RulUsPkId,String US_PK_ID) {
		
		/**/
		RulUsPkId=null; 
		
		String tsql=" select sysdate,decode(US_DISTRICT_OFFICE,null,'null',US_DISTRICT_OFFICE) from CP_PMN_USER where US_PK_ID = " + US_PK_ID;
		
		List<?> tlist = getSession().createSQLQuery(tsql).list();
		Iterator<?> tit = tlist.iterator();
		if(tit.hasNext()){
			Object[] obj = (Object[]) tit.next();
			if(!obj[1].toString().equals("null")){
				RulUsPkId = obj[1].toString();
			}
		}
		/**/
		
		
		
		String sql="select a.DT_PK_CODE,a.DT_NAME||'('||a.DT_ALIAS_NAME||')' from CP_WH_DISTRICT a where a.DT_PROVINCE_CODE='" + CITY_CODE.substring(0, 2) + "'";
		if(RulUsPkId!=null && !RulUsPkId.equals("null")  && RulUsPkId.length()>0){
			sql = sql + " and a.DT_PK_CODE = '" + RulUsPkId + "'";
		}
		sql = sql + " order by a.DT_PK_CODE";
		List<?> list = getSession().createSQLQuery(sql).list();
		Iterator<?> it = list.iterator();
		List<CpBean> beans=new ArrayList<CpBean>();
		while (it.hasNext()) {
			Object[] obj = (Object[]) it.next();
			CpBean bean = new CpBean();
			if (obj[0] != null) bean.setDT_PK_CODE(obj[0].toString());
			else  bean.setDT_PK_CODE("");
			if (obj[1] != null) bean.setDT_NAME(obj[1].toString());
			else  bean.setDT_NAME("");	
			beans.add(bean);
		}
		return beans;
	}
	
	@Transactional(readOnly = true)
	public List<CpBean> getDTsFromDisp(String CITY_CODE,String RulUsPkId,String US_PK_ID) {
		
		/**/
		RulUsPkId=null; 
		
		String tsql=" select sysdate,decode(US_DISTRICT_OFFICE,null,'null',US_DISTRICT_OFFICE) from CP_PMN_USER where US_PK_ID = " + US_PK_ID;
		
		List<?> tlist = getSession().createSQLQuery(tsql).list();
		Iterator<?> tit = tlist.iterator();
		if(tit.hasNext()){
			Object[] obj = (Object[]) tit.next();
			if(!obj[1].toString().equals("null")){
				RulUsPkId = obj[1].toString();
			}
		}
		/**/
		
		
		String sql=" select c.DT_PK_CODE,c.DT_NAME||'('||c.DT_ALIAS_NAME||')' from CP_WH_DISTRICT c , " +
		" (select distinct a.dt_pk_code from cp_wh_disp_office_dis a where a.disp_office_code like '" + CITY_CODE.substring(0, 4) + "%' "+
		" union " +
		" select distinct b.dt_pk_code from cp_wh_trans_office_dis b where b.trans_code like  '" + CITY_CODE.substring(0, 4) + "%' ) d " +
		" where c.dt_pk_code = d.dt_pk_code ";
		if(RulUsPkId!=null && !RulUsPkId.equals("null")  && RulUsPkId.length()>0){
			sql = sql + " and c.DT_PK_CODE = '" + RulUsPkId + "'";
		}
		sql = sql + " order by c.DT_PK_CODE";
		List<?> list = getSession().createSQLQuery(sql).list();
		Iterator<?> it = list.iterator();
		List<CpBean> beans=new ArrayList<CpBean>();
		while (it.hasNext()) {
			Object[] obj = (Object[]) it.next();
			CpBean bean = new CpBean();
			if (obj[0] != null) bean.setDT_PK_CODE(obj[0].toString());
			else  bean.setDT_PK_CODE("");
			if (obj[1] != null) bean.setDT_NAME(obj[1].toString());
			else  bean.setDT_NAME("");	
			beans.add(bean);
		}
		return beans;
	}
	
	@Transactional(readOnly = true)
	public List<CpBean> getDTsFromWh(String CITY_CODE) {
		String sql=" select a.DT_PK_CODE,max(a.DT_NAME||'('||a.DT_ALIAS_NAME||')') from CP_WH_DISTRICT a , CP_WH_RL_PG_ST_" + CITY_CODE.substring(0, 2) + " b " +
		" where a.dt_pk_code = b.dt_pk_code " +
		" and b.DIST_CD like '" + CITY_CODE + "%'" + 
		" group by a.DT_PK_CODE " +
		" order by a.DT_PK_CODE ";
		List<?> list = getSession().createSQLQuery(sql).list();
		Iterator<?> it = list.iterator();
		List<CpBean> beans=new ArrayList<CpBean>();
		while (it.hasNext()) {
			Object[] obj = (Object[]) it.next();
			CpBean bean = new CpBean();
			if (obj[0] != null) bean.setDT_PK_CODE(obj[0].toString());
			else  bean.setDT_PK_CODE("");
			if (obj[1] != null) bean.setDT_NAME(obj[1].toString());
			else  bean.setDT_NAME("");	
			beans.add(bean);
		}
		return beans;
	}
	
	@Transactional(readOnly = true)
	public List<DmBean> getDMs(String DT_PK_CODE,String RulUsDepartmentOffice,String CITY_CODE) {
		String sql="select a.DM_PK_CODE,a.DM_NAME from CP_WH_DEPARTMENT a where a.DT_PK_CODE='" + DT_PK_CODE + "'";
		
		if(CITY_CODE!=null && !CITY_CODE.equals("null")  && CITY_CODE.length()>0){
			sql = sql + " and a.CITY_CODE = '" + CITY_CODE + "'";
		}
		
		if(RulUsDepartmentOffice!=null && !RulUsDepartmentOffice.equals("null")  && RulUsDepartmentOffice.length()>0){
			sql = sql + " and a.DM_PK_CODE = '" + RulUsDepartmentOffice + "'";
		}
		
		sql = sql + " order by a.DM_PK_CODE";
		List<?> list = getSession().createSQLQuery(sql).list();
		Iterator<?> it = list.iterator();
		List<DmBean> beans=new ArrayList<DmBean>();
		while (it.hasNext()) {
			Object[] obj = (Object[]) it.next();
			DmBean bean = new DmBean();
			if (obj[0] != null) bean.setDM_PK_CODE(obj[0].toString());
			else  bean.setDM_PK_CODE("");
			if (obj[1] != null) bean.setDM_NAME(obj[1].toString());
			else  bean.setDM_NAME("");	
			beans.add(bean);
		}
		return beans;
	}
	
	@Transactional(readOnly = true)
	public List<PgBean> getPGs(String DM_PK_CODE,String RulUsPostsegOffice) {
		String sql="select a.PG_PK_CODE,a.PG_NAME from CP_WH_POSTSEG a where a.DM_PK_CODE='" + DM_PK_CODE + "'";
		if(RulUsPostsegOffice!=null && !RulUsPostsegOffice.equals("null")  && RulUsPostsegOffice.length()>0){
			sql = sql + " and a.PG_PK_CODE = '" + RulUsPostsegOffice + "'";
		}
		sql = sql + " order by a.PG_PK_CODE";
		List<?> list = getSession().createSQLQuery(sql).list();
		Iterator<?> it = list.iterator();
		List<PgBean> beans=new ArrayList<PgBean>();
		while (it.hasNext()) {
			Object[] obj = (Object[]) it.next();
			PgBean bean = new PgBean();
			if (obj[0] != null) bean.setPG_PK_CODE(obj[0].toString());
			else  bean.setPG_PK_CODE("");
			if (obj[1] != null) bean.setPG_NAME(obj[1].toString());
			else  bean.setPG_NAME("");	
			beans.add(bean);
		}
		return beans;
	}
	
	@Transactional(readOnly = true)
	public List<CpwhrlpgstBean> getdtdmpgs(CpwhrlpgstBean cpwhrlpgstBean) {
		String sql=" select max(DT_PK_CODE), max(DM_PK_CODE), max(PG_PK_CODE) from CP_WH_RL_PG_ST_" + cpwhrlpgstBean.getDIST_CD().substring(0, 2)  +
				" where RSDNBLDG_ID is null and ORG_ID is null and strt_id = '" + cpwhrlpgstBean.getSTRT_ID() + "' and dist_cd = '" + cpwhrlpgstBean.getDIST_CD()+ "'" +
				" and start_num <= " + cpwhrlpgstBean.getSTART_NUM() + " and end_num >= " + cpwhrlpgstBean.getSTART_NUM();
		if(!cpwhrlpgstBean.getPREFIX().equals("null") && cpwhrlpgstBean.getPREFIX().length()>0){
			sql = sql + " and PREFIX = '" + cpwhrlpgstBean.getPREFIX() + "'";
		}
		if(!cpwhrlpgstBean.getSUFFIX().equals("null") && cpwhrlpgstBean.getSUFFIX().length()>0){
			sql = sql + " and SUFFIX = '" + cpwhrlpgstBean.getSUFFIX() + "'";
		}
		sql = sql + "and ( (flag = 4) " +
				" or (flag = 5 and mod(" + cpwhrlpgstBean.getSTART_NUM() + ",2)=1) " +
				" or (flag = 6 and mod(" + cpwhrlpgstBean.getSTART_NUM() + ",2)=0) )";
		List<?> list = getSession().createSQLQuery(sql).list();
		Iterator<?> it = list.iterator();
		List<CpwhrlpgstBean> beans=new ArrayList<CpwhrlpgstBean>();
		while (it.hasNext()) {
			Object[] obj = (Object[]) it.next();
			CpwhrlpgstBean bean = new CpwhrlpgstBean();
			if (obj[0] != null) bean.setDT_PK_CODE(obj[0].toString());
			else  bean.setDT_PK_CODE("");
			if (obj[1] != null) bean.setDM_PK_CODE(obj[1].toString());
			else  bean.setDM_PK_CODE("");
			if (obj[2] != null) bean.setPG_PK_CODE(obj[2].toString());
			else  bean.setPG_PK_CODE("");
			beans.add(bean);
		}
		return beans;
	}
	
	
	@Transactional(readOnly = true)
	public List<CpwhrlpgstBean> getdtdmpgsno(CpwhrlpgstBean cpwhrlpgstBean) {
		
		String tDT_PK_CODE=""; 
		String tDM_PK_CODE=""; 
		String tPG_PK_CODE=""; 
		
		String sql="select DT_PK_CODE,DM_PK_CODE,PG_PK_CODE from cp_wh_rl_pg_st_" + cpwhrlpgstBean.getDIST_CD().substring(0, 2)  +
				" where RSDNBLDG_ID is null and ORG_ID is null and strt_id = '" + cpwhrlpgstBean.getSTRT_ID() + "' and dist_cd = '" + cpwhrlpgstBean.getDIST_CD()+ "'" +
				" group by DT_PK_CODE,DM_PK_CODE,PG_PK_CODE ";
		
		//System.out.println("getdtdmpgsno-1:" + sql);
		
		List<?> list = getSession().createSQLQuery(sql).list();
		Iterator<?> it = list.iterator();
		if(list.size()==1){
			if(it.hasNext()){
				Object[] obj = (Object[]) it.next();
				if (obj[0] != null) tDT_PK_CODE = obj[0].toString();
				else  tDT_PK_CODE = "";
				
				if (obj[1] != null) tDM_PK_CODE = obj[1].toString();
				else  tDM_PK_CODE = "";

				if (obj[1] != null) tPG_PK_CODE = obj[1].toString();
				else  tPG_PK_CODE = "";
			}
		}
		
		if(tPG_PK_CODE.length()<1){
			sql=" select DT_PK_CODE,DM_PK_CODE from cp_wh_rl_pg_st_" + cpwhrlpgstBean.getDIST_CD().substring(0, 2)  +
			" where RSDNBLDG_ID is null and ORG_ID is null and strt_id = '" + cpwhrlpgstBean.getSTRT_ID() + "' and dist_cd = '" + cpwhrlpgstBean.getDIST_CD()+ "'" +
			" group by DT_PK_CODE,DM_PK_CODE ";
			
			//System.out.println("getdtdmpgsno-2:" + sql);
			
			list = getSession().createSQLQuery(sql).list();
			it = list.iterator();
			if(list.size()==1){
				if(it.hasNext()){
					Object[] obj = (Object[]) it.next();
					
					if (obj[0] != null) tDT_PK_CODE = obj[0].toString();
					else  tDT_PK_CODE = "";
					
					if (obj[1] != null) tDM_PK_CODE = obj[1].toString();
					else  tDM_PK_CODE = "";
				}
			}
		}
		
		if(tDM_PK_CODE.length()<1){
			sql=" select DT_PK_CODE,sysdate from cp_wh_rl_pg_st_" + cpwhrlpgstBean.getDIST_CD().substring(0, 2)  +
			" where RSDNBLDG_ID is null and ORG_ID is null and strt_id = '" + cpwhrlpgstBean.getSTRT_ID() + "' and dist_cd = '" + cpwhrlpgstBean.getDIST_CD()+ "'" +
			" group by DT_PK_CODE ";

			//System.out.println("getdtdmpgsno-3:" + sql);
			
			list = getSession().createSQLQuery(sql).list();
			it = list.iterator();
			if(list.size()==1){
				if(it.hasNext()){
					
					Object[] obj = (Object[]) it.next();

					if (obj[0] != null) tDT_PK_CODE = obj[0].toString();
					
					else  tDT_PK_CODE = "";
					
				}
			}
		}
		
		
		CpwhrlpgstBean bean = new CpwhrlpgstBean();
		
		List<CpwhrlpgstBean> beans=new ArrayList<CpwhrlpgstBean>();
		
		if (tDT_PK_CODE != null) bean.setDT_PK_CODE(tDT_PK_CODE);
		else  bean.setDT_PK_CODE("");
		if (tDM_PK_CODE != null) bean.setDM_PK_CODE(tDM_PK_CODE);
		else  bean.setDM_PK_CODE("");
		if (tPG_PK_CODE != null) bean.setPG_PK_CODE(tPG_PK_CODE);
		else  bean.setPG_PK_CODE("");
		
		beans.add(bean);

		return beans;
	}
	
	
	@Transactional(readOnly = true)
	public List<QhzuiBean> getqzuis(String DIST_CD) {
		String sql="select a.DIST_CD,a.FIX,a.FIX_ABBR,a.FIX_XZ,a.FIX_FLAG,a.FIX_SMP from cp_wh_qhzui a where a.FIX_FLAG ='0' and  a.DIST_CD='" + DIST_CD + "'";
		sql = sql + " order by a.FIX";
		List<?> list = getSession().createSQLQuery(sql).list();
		Iterator<?> it = list.iterator();
		List<QhzuiBean> beans=new ArrayList<QhzuiBean>();
		while (it.hasNext()) {
			Object[] obj = (Object[]) it.next();
			QhzuiBean bean = new QhzuiBean();
			if (obj[0] != null) bean.setDIST_CD(obj[0].toString());
			else  bean.setDIST_CD("");
			if (obj[1] != null) bean.setFIX(obj[1].toString());
			else  bean.setFIX("");
			if (obj[2] != null) bean.setFIX_ABBR(obj[2].toString());
			else  bean.setFIX_ABBR("");
			if (obj[3] != null) bean.setFIX_XZ(obj[3].toString());
			else  bean.setFIX_XZ("");
			if (obj[4] != null) bean.setFIX_FLAG(obj[4].toString());
			else  bean.setFIX_FLAG("");
			if (obj[5] != null) bean.setFIX_SMP(obj[5].toString());
			else  bean.setFIX_SMP("");
			beans.add(bean);
		}
		return beans;
	}
	
	@Transactional(readOnly = true)
	public List<QhzuiBean> gethzuis(String DIST_CD) {
		String sql="select a.DIST_CD,a.FIX,a.FIX_ABBR,a.FIX_XZ,a.FIX_FLAG,a.FIX_SMP from cp_wh_qhzui a where a.FIX_FLAG ='1' and a.DIST_CD='" + DIST_CD + "'";
		sql = sql + " order by a.FIX";
		List<?> list = getSession().createSQLQuery(sql).list();
		Iterator<?> it = list.iterator();
		List<QhzuiBean> beans=new ArrayList<QhzuiBean>();
		while (it.hasNext()) {
			Object[] obj = (Object[]) it.next();
			QhzuiBean bean = new QhzuiBean();
			if (obj[0] != null) bean.setDIST_CD(obj[0].toString());
			else  bean.setDIST_CD("");
			if (obj[1] != null) bean.setFIX(obj[1].toString());
			else  bean.setFIX("");
			if (obj[2] != null) bean.setFIX_ABBR(obj[2].toString());
			else  bean.setFIX_ABBR("");
			if (obj[3] != null) bean.setFIX_XZ(obj[3].toString());
			else  bean.setFIX_XZ("");
			if (obj[4] != null) bean.setFIX_FLAG(obj[4].toString());
			else  bean.setFIX_FLAG("");
			if (obj[5] != null) bean.setFIX_SMP(obj[5].toString());
			else  bean.setFIX_SMP("");
			beans.add(bean);
		}
		return beans;
	}
}
