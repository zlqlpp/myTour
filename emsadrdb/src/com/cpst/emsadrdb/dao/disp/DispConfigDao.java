package com.cpst.emsadrdb.dao.disp;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cpst.core.orm.hibernate.HibernateDao;
import com.cpst.emsadrdb.entity.disp.DispBean;
import com.cpst.emsadrdb.entity.disp.DistrictBean;
import com.cpst.emsadrdb.entity.disp.TransBean;
import com.cpst.emsadrdb.entity.pmn.User;


@Repository
@Transactional
public class DispConfigDao extends HibernateDao<DistrictBean, String> {
	
	@Autowired
	private DispQueryDao dispQueryDao;
	
	@Transactional(readOnly = true)
	public  long getQueryCount(String sql){
		List<?> listCount=getSession().createSQLQuery(sql).list();
		long totalCount =((BigDecimal)listCount.get(0)).longValue();
		return totalCount;
	}
	
	@Transactional(readOnly = true)
	public boolean addDisp(DispBean dispBean,User user){
		boolean saveFlag=false;
		Session session=getSession();
		String tID=null; 
				
		String sql="INSERT INTO CP_BASE_DISP_OFFICE "
			+ "(PROVINCE_CODE,PROVINCE_NAME,DISP_OFFICE_CODE,DISP_OFFICE_NAME,DISP_OFFICE_ABBR,DEGREE,DATA_DATE,DATA_FLAG,DATA_USER) "
			+ "values('" + dispBean.getPROVINCE_CODE() +"',"
			+ "'" + dispBean.getPROVINCE_NAME() + "','" + dispBean.getDISP_OFFICE_CODE() + "','" + dispBean.getDISP_OFFICE_NAME() + "','" + dispBean.getDISP_OFFICE_ABBR() + "','"  + dispBean.getDEGREE()  + "',"
			+ "sysdate,2,'" + user.getUsLoginId() + "'"
			+ ")";
		session.createSQLQuery(sql).executeUpdate();
		
		session.createSQLQuery("commit").executeUpdate();
		
		SQLQuery query = (SQLQuery) session.createSQLQuery("{Call CP_BASE_DISP.CP_BASE_DISP_INSERT(?,?)}");
		query.setString(0,dispBean.getDISP_OFFICE_CODE());
		query.setString(1,dispBean.getWHDIS());
		
//		System.out.println(dispBean.getDISP_OFFICE_CODE());
//		System.out.println(dispBean.getWHDIS());

		query.executeUpdate();
				
		saveFlag=true;
		return saveFlag;
	}
	
	@Transactional(readOnly = true)
	public boolean upDisp(DispBean dispBean,User user){
		boolean saveFlag=false;
		Session session=getSession();
				
		String sql="UPDATE CP_BASE_DISP_OFFICE set"
		+ "  DISP_OFFICE_ABBR = '" + dispBean.getDISP_OFFICE_ABBR() + "', "
		+ "  DATA_FLAG = 2, "
		+ "  DATA_USER = '" + user.getUsLoginId() + "', "
		+ "  DATA_DATE = sysdate "
		+ "  WHERE DISP_OFFICE_CODE = '" + dispBean.getDISP_OFFICE_CODE() + "'";
		session.createSQLQuery(sql).executeUpdate();
		
		session.createSQLQuery("commit").executeUpdate();
		
		SQLQuery query = (SQLQuery) session.createSQLQuery("{Call CP_BASE_DISP.CP_BASE_DISP_UPDATE(?,?)}");
		query.setString(0,dispBean.getDISP_OFFICE_CODE());
		query.setString(1,dispBean.getWHDIS());
		
		//System.out.println(dispBean.getWHDIS());
		
		query.executeUpdate();
		
		saveFlag=true;
		return saveFlag;
	}
	
	@Transactional(readOnly = true)
	public boolean delDisp(DispBean dispBean,User user){
		boolean saveFlag=false;
		Session session=getSession();
		
		String sql="delete from CP_BASE_DISP_OFFICE where DISP_OFFICE_CODE = '" + dispBean.getDISP_OFFICE_CODE() + "'";
		
		session.createSQLQuery(sql).executeUpdate();
		
		sql="delete from CP_BASE_TRANS_OFFICE where DISP_OFFICE_CODE = '" + dispBean.getDISP_OFFICE_CODE() + "'";
		
		session.createSQLQuery(sql).executeUpdate();
				
		sql="delete from CP_BASE_TRANS_OFFICE_ALIAS where DISP_OFFICE_CODE = '" + dispBean.getDISP_OFFICE_CODE() + "'";
		
		session.createSQLQuery(sql).executeUpdate();
		
		sql="delete from CP_WH_DISP_OFFICE_DIS where  DISP_OFFICE_CODE = '" + dispBean.getDISP_OFFICE_CODE() + "'";
		
		session.createSQLQuery(sql).executeUpdate();
		
		sql="delete from CP_WH_TRANS_OFFICE_DIS where DISP_OFFICE_CODE = '" + dispBean.getDISP_OFFICE_CODE() + "'";
		
		session.createSQLQuery(sql).executeUpdate();
		
		saveFlag=true;
		return saveFlag;
	}
	
	@Transactional(readOnly = true)
	public boolean zhDisp(DispBean dispBean,User user){
		boolean saveFlag=false;
		Session session=getSession();
		
		SQLQuery query = (SQLQuery) session.createSQLQuery("{Call CP_BASE_DISP.CP_BASE_DISP_ZH(?,?)}");
		query.setString(0,dispBean.getDISP_OFFICE_CODE());
		query.setString(1,dispBean.getDISP_OFFICE_CODET());
		query.executeUpdate();
		
		saveFlag=true;
		return saveFlag;
	}
	
	@Transactional(readOnly = true)
	public boolean addTrans(TransBean transBean,User user){
		boolean saveFlag=false;
		Session session=getSession();
		String tID=null; 
				
		String sql="INSERT INTO CP_BASE_TRANS_OFFICE "
			+ "(TRANS_CODE,TRANS_NAME,PROVINCE_CODE,PROVINCE_NAME,DISP_OFFICE_CODE,DEGREE,FLAG,DATA_DATE,DATA_FLAG,DATA_USER) "
			+ "values('" + transBean.getTRANS_CODE() + "','" + transBean.getTRANS_NAME() +"','" + transBean.getPROVINCE_CODE() +"',"
			+ "'" + transBean.getPROVINCE_NAME() + "','" + transBean.getDISP_OFFICE_CODE() + "','" + transBean.getDEGREE() + "','" + transBean.getFLAG() + "',"
			+ "sysdate,2,'" + user.getUsLoginId() + "'"
			+ ")";
		session.createSQLQuery(sql).executeUpdate();
		
		sql="INSERT INTO CP_BASE_TRANS_OFFICE_ALIAS "
			+ "(TRANS_CODE,TRANS_NAME,PROVINCE_CODE,PROVINCE_NAME,DISP_OFFICE_CODE,DEGREE,FLAG,DATA_DATE,DATA_FLAG,DATA_USER) "
			+ "values('" + transBean.getTRANS_CODE() + "','" + transBean.getTRANS_NAME() +"','" + transBean.getPROVINCE_CODE() +"',"
			+ "'" + transBean.getPROVINCE_NAME() + "','" + transBean.getDISP_OFFICE_CODE() + "','" + transBean.getDEGREE() + "','" + transBean.getFLAG() + "',"
			+ "sysdate,2,'" + user.getUsLoginId() + "'"
			+ ")";
		session.createSQLQuery(sql).executeUpdate();
		
		session.createSQLQuery("commit").executeUpdate();
		
		SQLQuery query = (SQLQuery) session.createSQLQuery("{Call CP_BASE_DISP.CP_BASE_TRANS_INSERT(?,?)}");

		query.setString(0,transBean.getTRANS_CODE());
		query.setString(1,transBean.getWHDIS());

		query.executeUpdate();
				
		saveFlag=true;
		return saveFlag;
	}
	
	/*@Transactional(readOnly = true)
	public boolean upTrans(TransBean transBean,User user){
		boolean saveFlag=false;
		Session session=getSession();
				
		String sql="UPDATE CP_BASE_TRANS_OFFICE set"
		+ "  PROVINCE_CODE = '" + transBean.getPROVINCE_CODE() + "', "
		+ "  PROVINCE_NAME = '" + transBean.getPROVINCE_NAME() + "', "
		+ "  DISP_OFFICE_CODE = '" + transBean.getDISP_OFFICE_CODE() + "', "
		+ "  DEGREE = '" + transBean.getDEGREE() + "', "
		+ "  FLAG = '" + transBean.getFLAG() + "', "
		+ "  DATA_FLAG = 2, "
		+ "  DATA_USER = '" + user.getUsLoginId() + "', "
		+ "  DATA_DATE = sysdate "
		+ "  WHERE TRANS_CODE = '" + transBean.getTRANS_CODE() + "'";
		session.createSQLQuery(sql).executeUpdate();
		
		sql="UPDATE CP_BASE_TRANS_OFFICE_ALIAS set"
			+ "  PROVINCE_CODE = '" + transBean.getPROVINCE_CODE() + "', "
			+ "  PROVINCE_NAME = '" + transBean.getPROVINCE_NAME() + "', "
			+ "  DISP_OFFICE_CODE = '" + transBean.getDISP_OFFICE_CODE() + "', "
			+ "  DEGREE = '" + transBean.getDEGREE() + "', "
			+ "  FLAG = '" + transBean.getFLAG() + "', "
			+ "  DATA_FLAG = 2, "
			+ "  DATA_USER = '" + user.getUsLoginId() + "', "
			+ "  DATA_DATE = sysdate "
			+ "  WHERE TRANS_CODE = '" + transBean.getTRANS_CODE() + "'";
			session.createSQLQuery(sql).executeUpdate();
		
		saveFlag=true;
		return saveFlag;
	}*/
	
	@Transactional(readOnly = true)
	public boolean dtTrans(TransBean transBean,User user){
		boolean saveFlag=false;
		Session session=getSession();
		
		String sql="delete from CP_WH_TRANS_OFFICE_DIS where TRANS_CODE = '" + transBean.getTRANS_CODE() + "'";
		
		session.createSQLQuery(sql).executeUpdate();
				
		if(!transBean.getDT_PK_CODE().equals("null") && transBean.getDT_PK_CODE().length()>0){
			sql="INSERT INTO CP_WH_TRANS_OFFICE_DIS "
				+ "(ID,TRANS_CODE,DT_PK_CODE,PROVINCE_CODE,DISP_OFFICE_CODE,CITY_CODE,DEGREE,DATA_DATE,DATA_FLAG,DATA_USER) "
				+ "values(SQ_CP_WH_TRANS_OFFICE_DIS.nextval,'" + transBean.getTRANS_CODE() + "','" + transBean.getDT_PK_CODE() + "',(select max(PROVINCE_CODE) from CP_BASE_TRANS_OFFICE_ALIAS where TRANS_CODE = " +  transBean.getTRANS_CODE() + "),(select max(DISP_OFFICE_CODE) from CP_BASE_TRANS_OFFICE_ALIAS where TRANS_CODE = " +  transBean.getTRANS_CODE() + "),(select max(CITY_CODE) from CP_BASE_TRANS_OFFICE_ALIAS where TRANS_CODE = " +  transBean.getTRANS_CODE() + "),(select max(DEGREE) from CP_BASE_TRANS_OFFICE_ALIAS where TRANS_CODE = " +  transBean.getTRANS_CODE() + "),sysdate,2,'" + user.getUsLoginId() + "'"
				+ ")";
			
			session.createSQLQuery(sql).executeUpdate();
			
			sql="UPDATE CP_WH_RL_PG_ST_"  + transBean.getTRANS_CODE().substring(0,2) +  " set"
			+ "  DT_PK_CODE = '" + transBean.getDT_PK_CODE() + "', "
			//+ "  DM_PK_CODE = '', "
			//+ "  PG_PK_CODE = '', "
			+ "  OPERATOR = '" + user.getUsLoginId() + "', "
			+ "  MOD_DATE = sysdate "
			+ "  WHERE DIST_CD = '" + transBean.getTRANS_CODE() + "'"
			+ "  and (DT_PK_CODE <> '" + transBean.getDT_PK_CODE() + "'  or DT_PK_CODE is null) ";
		
			session.createSQLQuery(sql).executeUpdate();
			
			/*
			sql="UPDATE CP_WH_DEPARTMENT set"
			+ "  DT_PK_CODE = '" + transBean.getDT_PK_CODE() + "'"
			+ "  WHERE DM_PK_CODE in (select DM_PK_CODE from CP_WH_RL_PG_ST_"  + transBean.getTRANS_CODE().substring(0,2) + " where DIST_CD = '" + transBean.getTRANS_CODE() + "')";
		
			session.createSQLQuery(sql).executeUpdate();
			
			
			sql="UPDATE CP_PMN_USER set"
				+ "  US_DISTRICT_OFFICE = '" + transBean.getDT_PK_CODE() + "'"
				+ "  WHERE US_DEPARTMENT_OFFICE in (select DM_PK_CODE from CP_WH_RL_PG_ST_"  + transBean.getTRANS_CODE().substring(0,2) + " where DIST_CD = '" + transBean.getTRANS_CODE() + "')";
			
				session.createSQLQuery(sql).executeUpdate();
			*/
		}else{
			sql="UPDATE CP_WH_RL_PG_ST_"  + transBean.getTRANS_CODE().substring(0,2) +  " set "
			+ "  DT_PK_CODE = '', "
			//+ "  DM_PK_CODE = '', "
			//+ "  PG_PK_CODE = '', "
			+ "  OPERATOR = '" + user.getUsLoginId() + "', "
			+ "  MOD_DATE = sysdate "
			+ "  WHERE DIST_CD = '" + transBean.getTRANS_CODE() + "'";
		
			session.createSQLQuery(sql).executeUpdate();
		}
		
		
		
		
		saveFlag=true;
		return saveFlag;
	}
	
	@Transactional(readOnly = true)
	public boolean delTrans(TransBean transBean,User user){
		boolean saveFlag=false;
		Session session=getSession();
		
		String sql="delete from CP_BASE_TRANS_OFFICE where TRANS_CODE = '" + transBean.getTRANS_CODE() + "'";
		
		session.createSQLQuery(sql).executeUpdate();
		
		sql="UPDATE CP_WH_RL_PG_ST_"  + transBean.getTRANS_CODE().substring(0,2) +  " set"
		+ "  DT_PK_CODE = '', "
		//+ "  DM_PK_CODE = '', "
		//+ "  PG_PK_CODE = '', "
		+ "  OPERATOR = '" + user.getUsLoginId() + "', "
		+ "  MOD_DATE = sysdate "
		+ "  WHERE DIST_CD = '" + transBean.getTRANS_CODE() + "'";
	
		session.createSQLQuery(sql).executeUpdate();
		
		sql="delete from CP_BASE_TRANS_OFFICE_ALIAS where TRANS_CODE = '" + transBean.getTRANS_CODE() + "'";
		
		session.createSQLQuery(sql).executeUpdate();
		
		sql="delete from CP_WH_TRANS_OFFICE_DIS where TRANS_CODE = '" + transBean.getTRANS_CODE() + "'";
		
		session.createSQLQuery(sql).executeUpdate();
		
		saveFlag=true;
		return saveFlag;
	}
	
	@Transactional(readOnly = true)
	public boolean zhtrans(TransBean transBean,User user){
		boolean saveFlag=false;
		Session session=getSession();
		
		String sql="delete from CP_WH_TRANS_OFFICE_DIS where  TRANS_CODE = '" + transBean.getTRANS_CODE() + "'";
		
		session.createSQLQuery(sql).executeUpdate();
				
		sql="UPDATE CP_BASE_TRANS_OFFICE set"
		+ "  DISP_OFFICE_CODE = '" + transBean.getDISP_OFFICE_CODE() + "', "
		+ "  FLAG = '" + transBean.getFLAG() + "', "
		+ "  DATA_FLAG = 2, "
		+ "  DATA_USER = '" + user.getUsLoginId() + "', "
		+ "  DATA_DATE = sysdate "
		+ "  WHERE TRANS_CODE = '" + transBean.getTRANS_CODE() + "'";
		session.createSQLQuery(sql).executeUpdate();
		
		sql="UPDATE CP_BASE_TRANS_OFFICE_ALIAS set"
			+ "  DISP_OFFICE_CODE = '" + transBean.getDISP_OFFICE_CODE() + "', "
			+ "  FLAG = '" + transBean.getFLAG() + "', "
			+ "  DATA_FLAG = 2, "
			+ "  DATA_USER = '" + user.getUsLoginId() + "', "
			+ "  DATA_DATE = sysdate "
			+ "  WHERE TRANS_CODE = '" + transBean.getTRANS_CODE() + "'";
		session.createSQLQuery(sql).executeUpdate();
		
		session.createSQLQuery("commit").executeUpdate();
		
		SQLQuery query = (SQLQuery) session.createSQLQuery("{Call CP_BASE_DISP.CP_BASE_TRANS_ZH(?)}");
		query.setString(0,transBean.getTRANS_CODE());
		
//		System.out.println(dispBean.getDISP_OFFICE_CODE());
//		System.out.println(dispBean.getWHDIS());

		query.executeUpdate();
		
		saveFlag=true;
		return saveFlag;
	}
	
	@Transactional(readOnly = true)
	public boolean updistrict(DistrictBean districtBean,User user){
		boolean saveFlag=false;
		Session session=getSession();
		
		String sql="UPDATE CP_BASE_ORG_DISTRICT set"
		+ "  CITY_FLAG = '" + districtBean.getCITY_FLAG() + "' "
		+ "  WHERE DISTRICT_CODE = '" + districtBean.getDISTRICT_CODE() + "'";
		session.createSQLQuery(sql).executeUpdate();
		
		if(districtBean.getCITY_FLAG().equals("1")){
			sql="delete from cp_base_trans_office where TRANS_CODE = '" + districtBean.getDISTRICT_CODE() + "'";
			
			session.createSQLQuery(sql).executeUpdate();
		}else if(districtBean.getCITY_FLAG().equals("0")){
			sql="select count(*) from cp_base_trans_office where TRANS_CODE = '" + districtBean.getDISTRICT_CODE() + "'";
			
			if(dispQueryDao.getQueryCount(sql)<1){
				sql="INSERT INTO cp_base_trans_office "
					+ "(TRANS_CODE,TRANS_NAME,PROVINCE_CODE,PROVINCE_NAME,DISP_OFFICE_CODE,DEGREE,FLAG,DATA_DATE,DATA_FLAG,DATA_USER) "
					+ " select TRANS_CODE,TRANS_NAME,PROVINCE_CODE,PROVINCE_NAME,DISP_OFFICE_CODE,DEGREE,FLAG,DATA_DATE,DATA_FLAG,DATA_USER from cp_base_trans_office_alias where TRANS_CODE = '" + districtBean.getDISTRICT_CODE() + "'";
				
				session.createSQLQuery(sql).executeUpdate();
			}
	
		}

		saveFlag=true;
		return saveFlag;
	}
}
