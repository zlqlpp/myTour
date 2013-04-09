package com.cpst.emsadrdb.dao.jjsx;


import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import com.cpst.core.orm.hibernate.HibernateDao;
import com.cpst.emsadrdb.entity.jjsx.DistrictBean;
import com.cpst.emsadrdb.entity.jjsx.JJSXBean;
import com.cpst.emsadrdb.service.jjsx.JjsxCommon;

@Repository
@Transactional
public class JjsxOptionDao extends HibernateDao<DistrictBean, String> {

	@Transactional(readOnly = true)
	public List<DistrictBean> getProvincesyt(String DISTRICT_CODE) {
		String sql="select a.DISTRICT_CODE,a.PROVINCE_NAME from cp_base_org_district_yt a where a.DEGREE='1' ";
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
			bean.setDISTRICT_CODE(JjsxCommon.commonsql(obj[0]));
			bean.setPROVINCE_NAME(JjsxCommon.commonsql(obj[1]));
			beans.add(bean);
		}
		return beans;
	}
	
	@Transactional(readOnly = true)
	public List<DistrictBean> getCitysyt(String PROVINCE_NAME,String DISTRICT_CODE) {
		String sql="";
		if(JjsxCommon.isMunicipalities(PROVINCE_NAME)){
			sql="select a.DISTRICT_CODE,a.CITY_NAME from cp_base_org_district_yt a where a.us_cityflag = '1' and  a.DEGREE='1' and a.PROVINCE_NAME = '" + PROVINCE_NAME + "'  ";
		}else{
			sql="select a.DISTRICT_CODE,nvl(a.county_name,a.CITY_NAME) from cp_base_org_district_yt a where a.us_cityflag = '1' and  a.DEGREE>'1' and a.PROVINCE_NAME = '" + PROVINCE_NAME + "'  ";
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
			bean.setDISTRICT_CODE(JjsxCommon.commonsql(obj[0]));
			bean.setCITY_NAME(JjsxCommon.commonsql(obj[1]));
			beans.add(bean);
		}
		return beans;
	}
	
	@Transactional(readOnly = true)
	public List<DistrictBean> getCountysyt(String CITY_NAME,String CITY_CODE) {
		
		String sql="";
		
		sql="	select DISTRICT_CODE,nvl(COUNTY_NAME,CITY_NAME) from cp_base_org_district_yt  " +
				"	where " +
				" ( YT_COUNTYFLAG = '1' and nvl(us_cityflag,'0') = '0' and DEGREE='3' " +
				"  and nvl(CITY_NAME,PROVINCE_NAME) = '" + CITY_NAME + "' ) " +
				"  or " +
				"  ( YT_COUNTYFLAG = '1' and us_cityflag = '1' and DEGREE='3' " +
				"	and DISTRICT_CODE = '" + CITY_CODE + "' )" +
				"  or " +
				" ( DEGREE='2' and VA_COUNTYFLAG = '1' and DISTRICT_CODE = '" + CITY_CODE + "' ) ";
		
		sql = sql + " order by DISTRICT_CODE";
		List<?> list = getSession().createSQLQuery(sql).list();
		Iterator<?> it = list.iterator();
		List<DistrictBean> beans=new ArrayList<DistrictBean>();
		while (it.hasNext()) {
			Object[] obj = (Object[]) it.next();
			DistrictBean bean = new DistrictBean();
			bean.setDISTRICT_CODE(JjsxCommon.commonsql(obj[0]));
			bean.setCOUNTY_NAME(JjsxCommon.commonsql(obj[1]));
			beans.add(bean);
		}
		return beans;
		
	}
	
	
	@Transactional(readOnly = true)
	public List<JJSXBean> getSpcs(String CITY_CODE,String COUNTY_CODE,String PCFLAG) {
		String sql="select " +
				" b.seqid," +
				" b.pcmc||a.pcmc||decode(a.pcsx,'0','[揽收]','1','[投递]','[-]')||decode(a.CQUSXXUS,'1','[城区]','0','[辖县]','-') pcmc" +
				"  from " +
				" cjjsx_zb_pc a , cjjsx_sj_spc b," +
				" (select decode(degree||city_flag||US_CITYFLAG,'30','0','1') cqusxxus from cp_base_org_district_yt where district_code = '" + COUNTY_CODE + "') c" +
				" where a.seqid = b.zbpcseqid and b.distcd = '" + CITY_CODE + "'" +
				" and a.cqusxxus = c.cqusxxus ";
				if(PCFLAG.length()>0){
					if(PCFLAG.equals("T")){
						sql= sql + " and a.pcsx = '1' ";
					}else if(PCFLAG.equals("L")){
						sql= sql + " and a.pcsx = '0' ";
					}
				}
				sql= sql + "order by pcmc";
		List<?> list = getSession().createSQLQuery(sql).list();
		Iterator<?> it = list.iterator();
		List<JJSXBean> beans=new ArrayList<JJSXBean>();
		while (it.hasNext()) {
			Object[] obj = (Object[]) it.next();
			JJSXBean bean = new JJSXBean();
			bean.setSEQID(JjsxCommon.commonsql(obj[0]));
			bean.setALLPCMC(JjsxCommon.commonsql(obj[1]));
			beans.add(bean);
		}
		return beans;
	}
	
}
