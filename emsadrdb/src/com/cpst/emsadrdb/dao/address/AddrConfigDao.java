package com.cpst.emsadrdb.dao.address;

import java.util.Iterator;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cpst.core.orm.hibernate.HibernateDao;
import com.cpst.emsadrdb.entity.address.BldgrsdnsBean;
import com.cpst.emsadrdb.entity.address.CpwhrlpgstBean;
import com.cpst.emsadrdb.entity.address.DistrictBean;
import com.cpst.emsadrdb.entity.address.ExgcltitemopeBean;
import com.cpst.emsadrdb.entity.address.LogBean;
import com.cpst.emsadrdb.entity.address.OpexxsbbocBean;
import com.cpst.emsadrdb.entity.address.OrganizationBean;
import com.cpst.emsadrdb.entity.address.QhzuiBean;
import com.cpst.emsadrdb.entity.address.StreetBean;
import com.cpst.emsadrdb.entity.address.TBSRPROCESSBean;
import com.cpst.emsadrdb.entity.pmn.User;
import com.cpst.emsadrdb.service.address.AddrCommon;

@Repository
@Transactional
public class AddrConfigDao extends HibernateDao<DistrictBean, String> {
	
	@Transactional(readOnly = true)
	public boolean addLog(LogBean logBean){
		boolean saveFlag=false;
		Session session=getSession();
		
		String sql="INSERT INTO CP_MK_ADR_LOG "
			+ "(LOG_ID,DIST_CD,STRT_ID,STRT_NAME,RSDNBLDG_ID,RSDNBLDG_NAME,ORG_ID,ORG_NAME,DATA_DATE,DATA_FLAG,DATA_USER) "
			+ "values('" +logBean.getLOG_ID() + "','" + logBean.getDIST_CD() + "','" + logBean.getSTRT_ID() + "','" + logBean.getSTRT_NAME() + "',"
			+ "'" + logBean.getRSDNBLDG_ID() + "','" + logBean.getRSDNBLDG_NAME() + "',"
			+ "'" + logBean.getORG_ID() + "','" + logBean.getORG_NAME() + "'," + logBean.getDATA_DATE() + ",'" + logBean.getDATA_FLAG() + "',"
			+ "'" + logBean.getDATA_USER() + "'"
			+ ")";
		session.createSQLQuery(sql).executeUpdate();
		
		saveFlag=true;
		return saveFlag;
	}
	
	@Transactional(readOnly = true)
	public boolean hbstreet(StreetBean streetBean,User user){
		boolean saveFlag=false;
		Session session=getSession();
		
		
		String sql="UPDATE CP_MK_ADR_STREET_" + streetBean.getDIST_CD().substring(0,2) + " set"
		+ "  DATA_USER = '" + user.getUsLoginId() + "', "
		+ "  DATA_DATE = sysdate "
		+ "  WHERE STRT_ID = " + streetBean.getSTRT_IDG();
		
		session.createSQLQuery(sql).executeUpdate();
		
//		
//		System.out.println(streetBean.getDIST_CD());
//		System.out.println(streetBean.getSTRT_IDY());
//		System.out.println(streetBean.getSTRT_IDG());
//		
		
		SQLQuery query = (SQLQuery) session.createSQLQuery("{Call CP_MK_ADR_CZ.CP_MK_ADR_HB_STREET(?,?,?)}");
		query.setString(0,streetBean.getDIST_CD());
		query.setString(1,streetBean.getSTRT_IDY());
		query.setString(2,streetBean.getSTRT_IDG());
		query.executeUpdate();
		
		saveFlag=true;
		return saveFlag;
	}
	
	@Transactional(readOnly = true)
	public boolean shStreet(StreetBean streetBean,User user){
		boolean saveFlag=false;
		Session session=getSession();
		
		String sql="UPDATE CP_MK_ADR_STREET_" + streetBean.getDIST_CD().substring(0,2) + " set"
		+ "  ADULTNOTE = '" + streetBean.getADULTNOTE() + "', "
		+ "  DATA_DATE = sysdate "
		+ "  WHERE STRT_ID = " + streetBean.getSTRT_ID();
		
		session.createSQLQuery(sql).executeUpdate();
/*		System.out.println(streetBean.getDATA_FLAG());
		System.out.println(streetBean.getDIST_CD());
		System.out.println(streetBean.getSTRT_ID());
		System.out.println(user.getUsLoginId());*/
		session.createSQLQuery("commit").executeUpdate();
		
		SQLQuery query = (SQLQuery) session.createSQLQuery("{Call CP_MK_ADR_AUDIT.CP_MK_ADR_AUDIT_STREET(?,?,?,?)}");
		query.setString(0,streetBean.getDATA_FLAG());
		query.setString(1,streetBean.getDIST_CD());
		query.setString(2,streetBean.getSTRT_ID());
		query.setString(3,user.getUsLoginId());
		query.executeUpdate();
		
		saveFlag=true;
		return saveFlag;
	}
	
	@Transactional(readOnly = true)
	public boolean addStreet(StreetBean streetBean,User user){
		boolean saveFlag=false;
		Session session=getSession();
		String tID=null; 
		String tLOGID=null; 
		
		String sql=" select '" + streetBean.getDIST_CD().substring(0,4) + "'||trim(to_char(SQ_CP_MK_ADR_STREET.nextval,'0000000000')),'" + streetBean.getDIST_CD().substring(0,4) + "'||trim(to_char(SQ_CP_MK_ADR_LOG.nextval,'0000000000')) from dual";
		
		List<?> list = getSession().createSQLQuery(sql).list();
		Iterator<?> it = list.iterator();
		if(it.hasNext()){
			Object[] obj = (Object[]) it.next();
			tID = obj[0].toString();
			tLOGID = obj[1].toString();
		}
		
		streetBean.setSTRT_ID(tID);
		streetBean.setDATA_FLAG("6");
		
		sql="INSERT INTO CP_MK_ADR_STREET_" + streetBean.getDIST_CD().substring(0,2) + " "
			+ "(STRT_ID,DIST_CD,STRT1_NAME,STRT2_NAME,STRT3_NAME,STRT4_NAME,STRT5_NAME,STRT1_ABBR_NAME,STRT2_ABBR_NAME,STRT3_ABBR_NAME,STRT4_ABBR_NAME,STRT5_ABBR_NAME,STRT_ABBR,MIN_BGN_NBR,STAT_CD,SEG_NUM,DATA_DATE,DATA_FLAG,LOG_ID,DATA_USER,POST_CD,NOTE) "
			+ "values('" + streetBean.getSTRT_ID() + "','" + streetBean.getDIST_CD() +"','" + streetBean.getSTRT1_NAME() +"',"
			+ "'" + streetBean.getSTRT2_NAME() + "','" + streetBean.getSTRT3_NAME() + "','" + streetBean.getSTRT4_NAME() + "','" + streetBean.getSTRT5_NAME() + "',"
			+ "'" + streetBean.getSTRT1_ABBR_NAME() + "','" + streetBean.getSTRT2_ABBR_NAME() + "','" + streetBean.getSTRT3_ABBR_NAME() + "',"
			+ "'" + streetBean.getSTRT4_ABBR_NAME() + "','" + streetBean.getSTRT5_ABBR_NAME() + "','" + streetBean.getSTRT_ABBR() + "',"
			+ "1,1," + streetBean.getSEG_NUM() + ",sysdate," + streetBean.getDATA_FLAG() + "," + tLOGID + ",'" + user.getUsLoginId() + "','" + streetBean.getPOST_CD() +  "','" + streetBean.getNOTE() + "'"
			+ ")";
		session.createSQLQuery(sql).executeUpdate();
		
		LogBean logBean=new LogBean();
		logBean.setLOG_ID(tLOGID);
		logBean.setDIST_CD(streetBean.getDIST_CD());
		logBean.setSTRT_ID(tID);
		logBean.setSTRT_NAME(streetBean.getSTRT1_NAME() + streetBean.getSTRT2_NAME() + streetBean.getSTRT3_NAME() + streetBean.getSTRT4_NAME() + streetBean.getSTRT5_NAME());
		logBean.setDATA_USER(user.getUsLoginId());
		logBean.setDATA_FLAG(streetBean.getDATA_FLAG());
		logBean.setDATA_DATE("sysdate");
		
		addLog(logBean);
		
		session.createSQLQuery("commit").executeUpdate();

		if(AddrCommon.getUsWebRule(user)){
			streetBean.setDATA_FLAG("2");
			shStreet(streetBean,user);
		}
				
		saveFlag=true;
		return saveFlag;
	}
	
	@Transactional(readOnly = true)
	public boolean upStreet(StreetBean streetBean,User user){
		boolean saveFlag=false;
		Session session=getSession();
		
		String tLOGID=null; 
		
		String sql=" select sysdate,'" + streetBean.getDIST_CD().substring(0,4) + "'||trim(to_char(SQ_CP_MK_ADR_LOG.nextval,'0000000000')) from dual";
		
		List<?> list = getSession().createSQLQuery(sql).list();
		Iterator<?> it = list.iterator();
		if(it.hasNext()){
			Object[] obj = (Object[]) it.next();
			tLOGID = obj[1].toString();
		}
		
		streetBean.setDATA_FLAG("7");
		
		sql="UPDATE CP_MK_ADR_STREET_" + streetBean.getDIST_CD().substring(0,2) + " set"
		+ "  DIST_CD = '" + streetBean.getDIST_CD() + "',"
		+ "  STRT1_NAME = '" + streetBean.getSTRT1_NAME() + "', "
		+ "  STRT2_NAME = '" + streetBean.getSTRT2_NAME() + "', "
		+ "  STRT3_NAME = '" + streetBean.getSTRT3_NAME() + "', "
		+ "  STRT4_NAME = '" + streetBean.getSTRT4_NAME() + "', "
		+ "  STRT5_NAME = '" + streetBean.getSTRT5_NAME() + "', "
		+ "  STRT1_ABBR_NAME = '" + streetBean.getSTRT1_ABBR_NAME() + "', "
		+ "  STRT2_ABBR_NAME = '" + streetBean.getSTRT2_ABBR_NAME() + "', "
		+ "  STRT3_ABBR_NAME = '" + streetBean.getSTRT3_ABBR_NAME() + "', "
		+ "  STRT4_ABBR_NAME = '" + streetBean.getSTRT4_ABBR_NAME() + "', "
		+ "  STRT5_ABBR_NAME = '" + streetBean.getSTRT5_ABBR_NAME() + "', "
		+ "  STRT_ABBR = '" + streetBean.getSTRT_ABBR() + "', "
		+ "  SEG_NUM = " + streetBean.getSEG_NUM() + ", "
		+ "  DATA_FLAG = " + streetBean.getDATA_FLAG() + ", "
		+ "  POST_CD = '" + streetBean.getPOST_CD() + "', "
		+ "  DATA_USER = '" + user.getUsLoginId() + "', "
		+ "  LOG_ID = " + tLOGID + ", "
		+ "  NOTE = '" + streetBean.getNOTE() + "', "
		+ "  DATA_DATE = sysdate "
		+ "  WHERE STRT_ID = " + streetBean.getSTRT_ID();
		session.createSQLQuery(sql).executeUpdate();
		
		LogBean logBean=new LogBean();
		logBean.setLOG_ID(tLOGID);
		logBean.setDIST_CD(streetBean.getDIST_CD());
		logBean.setSTRT_ID(streetBean.getSTRT_ID());
		logBean.setSTRT_NAME(streetBean.getSTRT1_NAME() + streetBean.getSTRT2_NAME() + streetBean.getSTRT3_NAME() + streetBean.getSTRT4_NAME() + streetBean.getSTRT5_NAME());
		logBean.setDATA_USER(user.getUsLoginId());
		logBean.setDATA_FLAG(streetBean.getDATA_FLAG());
		logBean.setDATA_DATE("sysdate");
		
		addLog(logBean);
		
		session.createSQLQuery("commit").executeUpdate();

		if(AddrCommon.getUsWebRule(user)){
			streetBean.setDATA_FLAG("2");
			shStreet(streetBean,user);
		}
		
		saveFlag=true;
		return saveFlag;
	}
	
	@Transactional(readOnly = true)
	public boolean luanStreet(StreetBean streetBean,User user){
		boolean saveFlag=false;
		Session session=getSession();
		
		String tLOGID=null; 
		
		String sql=" select sysdate,'" + streetBean.getDIST_CD().substring(0,4) + "'||trim(to_char(SQ_CP_MK_ADR_LOG.nextval,'0000000000')) from dual";
		
		List<?> list = getSession().createSQLQuery(sql).list();
		Iterator<?> it = list.iterator();
		if(it.hasNext()){
			Object[] obj = (Object[]) it.next();
			tLOGID = obj[1].toString();
		}
		
		sql="UPDATE CP_MK_ADR_STREET_" + streetBean.getDIST_CD().substring(0,2) + " set"
		+ "  STRT1_NAME = '" + streetBean.getSTRT1_NAME() + "', "
		+ "  STRT2_NAME = '" + streetBean.getSTRT2_NAME() + "', "
		+ "  STRT3_NAME = '" + streetBean.getSTRT3_NAME() + "', "
		+ "  STRT4_NAME = '" + streetBean.getSTRT4_NAME() + "', "
		+ "  STRT5_NAME = '" + streetBean.getSTRT5_NAME() + "', "
		+ "  STRT1_ABBR_NAME = '" + streetBean.getSTRT1_ABBR_NAME() + "', "
		+ "  STRT2_ABBR_NAME = '" + streetBean.getSTRT2_ABBR_NAME() + "', "
		+ "  STRT3_ABBR_NAME = '" + streetBean.getSTRT3_ABBR_NAME() + "', "
		+ "  STRT4_ABBR_NAME = '" + streetBean.getSTRT4_ABBR_NAME() + "', "
		+ "  STRT5_ABBR_NAME = '" + streetBean.getSTRT5_ABBR_NAME() + "', "
		+ "  STRT_ABBR = '" + streetBean.getSTRT_ABBR() + "', "
		+ "  SEG_NUM = " + streetBean.getSEG_NUM() + ", "
		+ "  DATA_USER = '" + user.getUsLoginId() + "', "
		+ "  LOG_ID = " + tLOGID + ", "
		+ "  NOTE = '" + streetBean.getNOTE() + "', "
		+ "  DATA_DATE = sysdate "
		+ "  WHERE STRT_ID = " + streetBean.getSTRT_ID();
		session.createSQLQuery(sql).executeUpdate();
		
		LogBean logBean=new LogBean();
		logBean.setLOG_ID(tLOGID);
		logBean.setDIST_CD(streetBean.getDIST_CD());
		logBean.setSTRT_ID(streetBean.getSTRT_ID());
		logBean.setSTRT_NAME(streetBean.getSTRT1_NAME() + streetBean.getSTRT2_NAME() + streetBean.getSTRT3_NAME() + streetBean.getSTRT4_NAME() + streetBean.getSTRT5_NAME());
		logBean.setDATA_USER(user.getUsLoginId());
		logBean.setDATA_FLAG(streetBean.getDATA_FLAG());
		logBean.setDATA_DATE("sysdate");
		
		addLog(logBean);
		
		session.createSQLQuery("commit").executeUpdate();
		
		saveFlag=true;
		return saveFlag;
	}
	
	@Transactional(readOnly = true)
	public boolean qfhdbstreet(StreetBean streetBean,User user){
		boolean saveFlag=false;
		Session session=getSession();
		
		String tLOGID=null; 
		
		String sql=" select sysdate,'" + streetBean.getDIST_CD().substring(0,4) + "'||trim(to_char(SQ_CP_MK_ADR_LOG.nextval,'0000000000')) from dual";
		
		List<?> list = getSession().createSQLQuery(sql).list();
		Iterator<?> it = list.iterator();
		if(it.hasNext()){
			Object[] obj = (Object[]) it.next();
			tLOGID = obj[1].toString();
		}
		
		sql="UPDATE CP_MK_ADR_STREET_" + streetBean.getDIST_CD().substring(0,2) + " set"
		+ "  STRT1_NAME = '" + streetBean.getSTRT1_NAME() + "', "
		+ "  STRT2_NAME = '" + streetBean.getSTRT2_NAME() + "', "
		+ "  STRT3_NAME = '" + streetBean.getSTRT3_NAME() + "', "
		+ "  STRT4_NAME = '" + streetBean.getSTRT4_NAME() + "', "
		+ "  STRT5_NAME = '" + streetBean.getSTRT5_NAME() + "', "
		+ "  STRT1_ABBR_NAME = '" + streetBean.getSTRT1_ABBR_NAME() + "', "
		+ "  STRT2_ABBR_NAME = '" + streetBean.getSTRT2_ABBR_NAME() + "', "
		+ "  STRT3_ABBR_NAME = '" + streetBean.getSTRT3_ABBR_NAME() + "', "
		+ "  STRT4_ABBR_NAME = '" + streetBean.getSTRT4_ABBR_NAME() + "', "
		+ "  STRT5_ABBR_NAME = '" + streetBean.getSTRT5_ABBR_NAME() + "', "
		+ "  STRT_ABBR = '" + streetBean.getSTRT_ABBR() + "', "
		+ "  SEG_NUM = " + streetBean.getSEG_NUM() + ", "
		+ "  DATA_USER = '" + user.getUsLoginId() + "', "
		+ "  LOG_ID = " + tLOGID + ", "
		+ "  NOTE = '" + streetBean.getNOTE() + "', "
		+ "  DATA_DATE = sysdate "
		+ "  WHERE STRT_ID = " + streetBean.getSTRT_ID();
		session.createSQLQuery(sql).executeUpdate();
		
		LogBean logBean=new LogBean();
		logBean.setLOG_ID(tLOGID);
		logBean.setDIST_CD(streetBean.getDIST_CD());
		logBean.setSTRT_ID(streetBean.getSTRT_ID());
		logBean.setSTRT_NAME(streetBean.getSTRT1_NAME() + streetBean.getSTRT2_NAME() + streetBean.getSTRT3_NAME() + streetBean.getSTRT4_NAME() + streetBean.getSTRT5_NAME());
		logBean.setDATA_USER(user.getUsLoginId());
		logBean.setDATA_FLAG(streetBean.getDATA_FLAG());
		logBean.setDATA_DATE("sysdate");
		
		addLog(logBean);
		
		session.createSQLQuery("commit").executeUpdate();
		
		saveFlag=true;
		return saveFlag;
	}
	
	@Transactional(readOnly = true)
	public boolean delStreet(StreetBean streetBean,User user){
		boolean saveFlag=false;
		Session session=getSession();
				
		String tLOGID=null; 

		String sql=" select count(*)  from  CP_MK_ADR_STREET_" + streetBean.getDIST_CD().substring(0,2) + " " +
		"  WHERE (ADULT_DATA_FLAG = 6 or DATA_FLAG = 6) " + 
		"  AND STRT_ID = " + streetBean.getSTRT_ID();
		
		List<?> list = getSession().createSQLQuery(sql).list();
		if(Integer.valueOf(list.get(0).toString())>0){
			sql="DELETE FROM CP_MK_ADR_STREET_" + streetBean.getDIST_CD().substring(0,2) + " " +
			"  WHERE STRT_ID = " + streetBean.getSTRT_ID();
	
			
			session.createSQLQuery(sql).executeUpdate();
			
			sql="DELETE FROM CP_MK_ADR_BLDGRSDNS_" + streetBean.getDIST_CD().substring(0,2) + " " +
			"  WHERE STRT_ID = " + streetBean.getSTRT_ID();
			
			session.createSQLQuery(sql).executeUpdate();
			
			sql="DELETE FROM CP_MK_ORG_ORGANIZATION_" + streetBean.getDIST_CD().substring(0,2) + " " +
			"  WHERE STRT_ID = " + streetBean.getSTRT_ID();
			
			session.createSQLQuery(sql).executeUpdate();
			
			sql="DELETE FROM CP_WH_RL_PG_ST_" + streetBean.getDIST_CD().substring(0,2) + " " +
			"  WHERE STRT_ID = " + streetBean.getSTRT_ID();
			
			session.createSQLQuery(sql).executeUpdate();
		}
		else{
			
			sql=" select sysdate,'" + streetBean.getDIST_CD().substring(0,4) + "'||trim(to_char(SQ_CP_MK_ADR_LOG.nextval,'0000000000')) from dual";
			
			list = getSession().createSQLQuery(sql).list();
			Iterator<?> it = list.iterator();
			if(it.hasNext()){
				Object[] obj = (Object[]) it.next();
				tLOGID = obj[1].toString();
			}
			
			
			streetBean.setDATA_FLAG("8");
			
			sql="UPDATE CP_MK_ADR_STREET_" + streetBean.getDIST_CD().substring(0,2) + " set" +
			"  DATA_FLAG = " + streetBean.getDATA_FLAG() + ", " + 
			"  DATA_USER = '" + user.getUsLoginId() + "', " +
			"  LOG_ID = " + tLOGID + ", " +
			"  NOTE = '" + streetBean.getNOTE() + "', " +
			"  DATA_DATE = sysdate " + 
			"  WHERE STRT_ID = " + streetBean.getSTRT_ID();
	
			
			session.createSQLQuery(sql).executeUpdate();
			
			LogBean logBean=new LogBean();
			logBean.setLOG_ID(tLOGID);
			logBean.setDIST_CD(streetBean.getDIST_CD());
			logBean.setSTRT_ID(streetBean.getSTRT_ID());
			logBean.setSTRT_NAME(streetBean.getTOTAL_STREET_NAME());
			logBean.setDATA_USER(user.getUsLoginId());
			logBean.setDATA_FLAG(streetBean.getDATA_FLAG());
			logBean.setDATA_DATE("sysdate");
			
			addLog(logBean);
			
			session.createSQLQuery("commit").executeUpdate();
	
			if(AddrCommon.getUsWebRule(user)){
				streetBean.setDATA_FLAG("2");
				shStreet(streetBean,user);
			}
		}
		
		saveFlag=true;
		return saveFlag;
	}
	
	@Transactional(readOnly = true)
	public boolean streetBean(StreetBean streetBean,User user){
		boolean saveFlag=false;
		Session session=getSession();
		
		
		String sql="UPDATE CP_MK_ADR_STREET_" + streetBean.getDIST_CD().substring(0,2) + " set " +
		"  IS_IGNORED = 1 , " + 
		"  DATA_USER = '" + user.getUsLoginId() + "', " +
		"  DATA_DATE = sysdate " + 
		"  WHERE STRT_ID in (" + streetBean.getSTRT_ID() + ")";
		
		session.createSQLQuery(sql).executeUpdate();

		saveFlag=true;
		return saveFlag;
	}
	
	@Transactional(readOnly = true)
	public boolean delluanstreet(StreetBean streetBean,User user){
		boolean saveFlag=false;
		Session session=getSession();
				
		String tLOGID=null; 

		String sql=" ";
		
		sql="DELETE FROM CP_MK_ADR_BLDGRSDNS_" + streetBean.getDIST_CD().substring(0,2) + " " +
		"  WHERE STRT_ID = " + streetBean.getSTRT_ID();

		session.createSQLQuery(sql).executeUpdate();

		sql="DELETE FROM CP_MK_ORG_ORGANIZATION_" + streetBean.getDIST_CD().substring(0,2) + " " +
		"  WHERE STRT_ID = " + streetBean.getSTRT_ID();
		
		session.createSQLQuery(sql).executeUpdate();
		
		sql="DELETE FROM CP_MK_ADR_BLDG_" + streetBean.getDIST_CD().substring(0,2) + " " +
		"  WHERE STRT_ID = " + streetBean.getSTRT_ID();

		session.createSQLQuery(sql).executeUpdate();
		
		sql="DELETE FROM CP_WH_RL_PG_ST_" + streetBean.getDIST_CD().substring(0,2) + " " +
		"  WHERE STRT_ID = " + streetBean.getSTRT_ID();

		session.createSQLQuery(sql).executeUpdate();
		
		sql="DELETE FROM CP_MK_ADR_STREET_" + streetBean.getDIST_CD().substring(0,2) + " " +
		"  WHERE STRT_ID = " + streetBean.getSTRT_ID();

		session.createSQLQuery(sql).executeUpdate();
		
		sql=" select sysdate,'" + streetBean.getDIST_CD().substring(0,4) + "'||trim(to_char(SQ_CP_MK_ADR_LOG.nextval,'0000000000')) from dual";
		
		List list = getSession().createSQLQuery(sql).list();
		Iterator<?> it = list.iterator();
		if(it.hasNext()){
			Object[] obj = (Object[]) it.next();
			tLOGID = obj[1].toString();
		}
		
		LogBean logBean=new LogBean();
		logBean.setLOG_ID(tLOGID);
		logBean.setDIST_CD(streetBean.getDIST_CD());
		logBean.setSTRT_ID(streetBean.getSTRT_ID());
		logBean.setSTRT_NAME(streetBean.getTOTAL_STREET_NAME());
		logBean.setDATA_USER(user.getUsLoginId());
		logBean.setDATA_FLAG(streetBean.getDATA_FLAG());
		logBean.setDATA_DATE("sysdate");
		
		addLog(logBean);
		
		
		saveFlag=true;
		return saveFlag;
	}
	
	@Transactional(readOnly = true)
	public boolean shBldgrsdns(BldgrsdnsBean bldgrsdnsBean,User user){
		boolean saveFlag=false;
		Session session=getSession();
		/*
		System.out.println(bldgrsdnsBean.getDATA_FLAG());
		System.out.println(bldgrsdnsBean.getDIST_CD());
		System.out.println(bldgrsdnsBean.getRSDNBLDG_ID());
		System.out.println(bldgrsdnsBean.getTOTAL_STREET_NAME());
		*/
		
		SQLQuery query = (SQLQuery) session.createSQLQuery("{Call CP_MK_ADR_AUDIT.CP_MK_ADR_AUDIT_BLDGRSDNS(?,?,?,?,?)}");
		query.setString(0,bldgrsdnsBean.getDATA_FLAG());
		query.setString(1,bldgrsdnsBean.getDIST_CD());
		query.setString(2,bldgrsdnsBean.getRSDNBLDG_ID());
		query.setString(3,user.getUsLoginId());
		query.setString(4,bldgrsdnsBean.getTOTAL_STREET_NAME());
		query.executeUpdate();
		
		saveFlag=true;
		return saveFlag;
	}
		
	@Transactional(readOnly = true)
	public boolean addBldgrsdnsdius(BldgrsdnsBean bldgrsdnsBean,User user){
		boolean saveFlag=false;
		Session session=getSession();
		
		String tID=null; 
		String tLOGID=null; 
		
		String sql=" select '" + bldgrsdnsBean.getDIST_CD().substring(0,4) + "'||trim(to_char(SQ_CP_MK_ADR_BLDGRSDNS.nextval,'0000000000')),'" + bldgrsdnsBean.getDIST_CD().substring(0,4) + "'||trim(to_char(SQ_CP_MK_ADR_LOG.nextval,'0000000000')) from dual";
		
		List<?> list = getSession().createSQLQuery(sql).list();
		Iterator<?> it = list.iterator();
		if(it.hasNext()){
			Object[] obj = (Object[]) it.next();
			tID = obj[0].toString();
			tLOGID = obj[1].toString();
		}
		
		bldgrsdnsBean.setRSDNBLDG_ID(tID);
		bldgrsdnsBean.setDATA_FLAG("6");
		
		sql="INSERT INTO CP_MK_ADR_BLDGRSDNS_" + bldgrsdnsBean.getDIST_CD().substring(0,2) + " "
			+ "(RSDNBLDG_ID,DIST_CD,STRT_ID,RSDNBLDG_FLAG,RSDNBLDG_NAME,RSDNBLDG_ABBR,STAT_CD,DATA_DATE,DATA_FLAG,LOG_ID,DATA_USER,POST_CD,NUM_FLAG,PREFIX,START_NUM,END_NUM,SUFFIX,DT_PK_CODE,DM_PK_CODE,PG_PK_CODE,NOTE) "
			+ "values(" + bldgrsdnsBean.getRSDNBLDG_ID() + ",'" +bldgrsdnsBean.getDIST_CD()+"','" + bldgrsdnsBean.getSTRT_ID() + "','0','" + bldgrsdnsBean.getRSDNBLDG_NAME() +"','"+ bldgrsdnsBean.getRSDNBLDG_ABBR() +"',"
			+ "1,sysdate," + bldgrsdnsBean.getDATA_FLAG() + "," + tLOGID + ",'" + user.getUsLoginId()
			+ "','" + bldgrsdnsBean.getPOST_CD() + "','" + bldgrsdnsBean.getNUM_FLAG() + "','" + bldgrsdnsBean.getPREFIX() + "','" + bldgrsdnsBean.getSTART_NUM() + "','" + bldgrsdnsBean.getEND_NUM() + "','"
			+ bldgrsdnsBean.getSUFFIX() + "','" + bldgrsdnsBean.getDT_PK_CODE() + "','" + bldgrsdnsBean.getDM_PK_CODE() + "','" + bldgrsdnsBean.getPG_PK_CODE() + "','" + bldgrsdnsBean.getNOTE() + "'" 
			+ ")";
		session.createSQLQuery(sql).executeUpdate();
		
		LogBean logBean=new LogBean();
		logBean.setLOG_ID(tLOGID);
		logBean.setDIST_CD(bldgrsdnsBean.getDIST_CD());
		logBean.setSTRT_ID(bldgrsdnsBean.getSTRT_ID());
		logBean.setSTRT_NAME(bldgrsdnsBean.getTOTAL_STREET_NAME());
		logBean.setRSDNBLDG_ID(tID);
		logBean.setRSDNBLDG_NAME(bldgrsdnsBean.getRSDNBLDG_NAME());
		logBean.setDATA_USER(user.getUsLoginId());
		logBean.setDATA_FLAG(bldgrsdnsBean.getDATA_FLAG());
		logBean.setDATA_DATE("sysdate");
		
		addLog(logBean);
		
		session.createSQLQuery("commit").executeUpdate();
		
		sql=" select count(*)  from  CP_MK_ADR_STREET_" + bldgrsdnsBean.getDIST_CD().substring(0,2) + " " +
		"  WHERE (ADULT_DATA_FLAG = 6 or DATA_FLAG = 6) " + 
		"  AND STRT_ID = " + bldgrsdnsBean.getSTRT_ID();
		
		list = getSession().createSQLQuery(sql).list();
		
		if(AddrCommon.getUsWebRule(user) || Integer.valueOf(list.get(0).toString())<1){
			bldgrsdnsBean.setDATA_FLAG("2");
			shBldgrsdns(bldgrsdnsBean,user);
		}
		
		sql="DELETE FROM TB_MINUS_BLDGRSDNS_" + bldgrsdnsBean.getDIST_CD().substring(0,2) + " " +
		"  WHERE  RSDNBLDG_ID = " + bldgrsdnsBean.getRSDNBLDG_IDTMP();

		
		session.createSQLQuery(sql).executeUpdate();
		
		saveFlag=true;
		return saveFlag;
	}
	
	@Transactional(readOnly = true)
	public boolean addBldgrsdns(BldgrsdnsBean bldgrsdnsBean,User user){
		boolean saveFlag=false;
		Session session=getSession();
		
		String tID=null; 
		String tLOGID=null; 
		
		String sql=" select '" + bldgrsdnsBean.getDIST_CD().substring(0,4) + "'||trim(to_char(SQ_CP_MK_ADR_BLDGRSDNS.nextval,'0000000000')),'" + bldgrsdnsBean.getDIST_CD().substring(0,4) + "'||trim(to_char(SQ_CP_MK_ADR_LOG.nextval,'0000000000')) from dual";
		
		List<?> list = getSession().createSQLQuery(sql).list();
		Iterator<?> it = list.iterator();
		if(it.hasNext()){
			Object[] obj = (Object[]) it.next();
			tID = obj[0].toString();
			tLOGID = obj[1].toString();
		}
		
		bldgrsdnsBean.setRSDNBLDG_ID(tID);
		bldgrsdnsBean.setDATA_FLAG("6");
		
		sql="INSERT INTO CP_MK_ADR_BLDGRSDNS_" + bldgrsdnsBean.getDIST_CD().substring(0,2) + " "
			+ "(RSDNBLDG_ID,DIST_CD,STRT_ID,RSDNBLDG_FLAG,RSDNBLDG_NAME,RSDNBLDG_ABBR,STAT_CD,DATA_DATE,DATA_FLAG,LOG_ID,DATA_USER,POST_CD,NUM_FLAG,PREFIX,START_NUM,END_NUM,SUFFIX,DT_PK_CODE,DM_PK_CODE,PG_PK_CODE,NOTE) "
			+ "values(" + bldgrsdnsBean.getRSDNBLDG_ID() + ",'" +bldgrsdnsBean.getDIST_CD()+"','" + bldgrsdnsBean.getSTRT_ID() + "','0','" + bldgrsdnsBean.getRSDNBLDG_NAME() +"','"+ bldgrsdnsBean.getRSDNBLDG_ABBR() +"',"
			+ "1,sysdate," + bldgrsdnsBean.getDATA_FLAG() + "," + tLOGID + ",'" + user.getUsLoginId()
			+ "','" + bldgrsdnsBean.getPOST_CD() + "','" + bldgrsdnsBean.getNUM_FLAG() + "','" + bldgrsdnsBean.getPREFIX() + "','" + bldgrsdnsBean.getSTART_NUM() + "','" + bldgrsdnsBean.getEND_NUM() + "','"
			+ bldgrsdnsBean.getSUFFIX() + "','" + bldgrsdnsBean.getDT_PK_CODE() + "','" + bldgrsdnsBean.getDM_PK_CODE() + "','" + bldgrsdnsBean.getPG_PK_CODE() + "','" + bldgrsdnsBean.getNOTE() + "'" 
			+ ")";
		session.createSQLQuery(sql).executeUpdate();
		
		LogBean logBean=new LogBean();
		logBean.setLOG_ID(tLOGID);
		logBean.setDIST_CD(bldgrsdnsBean.getDIST_CD());
		logBean.setSTRT_ID(bldgrsdnsBean.getSTRT_ID());
		logBean.setSTRT_NAME(bldgrsdnsBean.getTOTAL_STREET_NAME());
		logBean.setRSDNBLDG_ID(tID);
		logBean.setRSDNBLDG_NAME(bldgrsdnsBean.getRSDNBLDG_NAME());
		logBean.setDATA_USER(user.getUsLoginId());
		logBean.setDATA_FLAG(bldgrsdnsBean.getDATA_FLAG());
		logBean.setDATA_DATE("sysdate");
		
		addLog(logBean);
		
		session.createSQLQuery("commit").executeUpdate();
		
		sql=" select count(*)  from  CP_MK_ADR_STREET_" + bldgrsdnsBean.getDIST_CD().substring(0,2) + " " +
		"  WHERE (ADULT_DATA_FLAG = 6 or DATA_FLAG = 6) " + 
		"  AND STRT_ID = " + bldgrsdnsBean.getSTRT_ID();
		
		list = getSession().createSQLQuery(sql).list();
		
		if(AddrCommon.getUsWebRule(user) || Integer.valueOf(list.get(0).toString())<1){
			bldgrsdnsBean.setDATA_FLAG("2");
			shBldgrsdns(bldgrsdnsBean,user);
		}
		
		saveFlag=true;
		return saveFlag;
	}
	
	@Transactional(readOnly = true)
	public boolean upBldgrsdns(BldgrsdnsBean bldgrsdnsBean,User user){
		boolean saveFlag=false;
		Session session=getSession();
		
		String tLOGID=null; 
		
		String sql=" select sysdate,'" + bldgrsdnsBean.getDIST_CD().substring(0,4) + "'||trim(to_char(SQ_CP_MK_ADR_LOG.nextval,'0000000000')) from dual";
		
		List<?> list = getSession().createSQLQuery(sql).list();
		Iterator<?> it = list.iterator();
		if(it.hasNext()){
			Object[] obj = (Object[]) it.next();
			tLOGID = obj[1].toString();
		}
		
		bldgrsdnsBean.setDATA_FLAG("7");
				
		sql="UPDATE CP_MK_ADR_BLDGRSDNS_" + bldgrsdnsBean.getDIST_CD().substring(0,2) + " set "
		+ "  DIST_CD = " + bldgrsdnsBean.getDIST_CD() + ","
		+ "  STRT_ID = " + bldgrsdnsBean.getSTRT_ID() + ","
		+ "  DORPLT_ID = " + bldgrsdnsBean.getDORPLT_ID() + ","
		+ "  RSDNBLDG_NAME = '" + bldgrsdnsBean.getRSDNBLDG_NAME() + "', "
		+ "  RSDNBLDG_ABBR = '" + bldgrsdnsBean.getRSDNBLDG_ABBR() + "', "
		+ "  DATA_FLAG = " + bldgrsdnsBean.getDATA_FLAG() + ", "
		+ "  DATA_USER = '" + user.getUsLoginId() + "', "
		+ "  POST_CD = '" + bldgrsdnsBean.getPOST_CD() + "', "
		+ "  NUM_FLAG = '" + bldgrsdnsBean.getNUM_FLAG() + "', "
		+ "  PREFIX = '" + bldgrsdnsBean.getPREFIX() + "', "
		+ "  START_NUM = '" + bldgrsdnsBean.getSTART_NUM() + "', "
		+ "  END_NUM = '" + bldgrsdnsBean.getEND_NUM() + "', "
		+ "  SUFFIX = '" + bldgrsdnsBean.getSUFFIX() + "', "
		+ "  DT_PK_CODE = '" + bldgrsdnsBean.getDT_PK_CODE() + "', "
		+ "  DM_PK_CODE = '" + bldgrsdnsBean.getDM_PK_CODE() + "', "
		+ "  PG_PK_CODE = '" + bldgrsdnsBean.getPG_PK_CODE() + "', "
		+ "  NOTE = '" + bldgrsdnsBean.getNOTE() + "', "
		+ "  LOG_ID = " + tLOGID + ", "
		+ "  DATA_DATE = sysdate "
		+ "  WHERE RSDNBLDG_ID = " + bldgrsdnsBean.getRSDNBLDG_ID();
		session.createSQLQuery(sql).executeUpdate();
		
		LogBean logBean=new LogBean();
		logBean.setLOG_ID(tLOGID);
		logBean.setDIST_CD(bldgrsdnsBean.getDIST_CD());
		logBean.setSTRT_ID(bldgrsdnsBean.getSTRT_ID());
		logBean.setSTRT_NAME(bldgrsdnsBean.getTOTAL_STREET_NAME());
		logBean.setRSDNBLDG_ID(bldgrsdnsBean.getRSDNBLDG_ID());
		logBean.setRSDNBLDG_NAME(bldgrsdnsBean.getRSDNBLDG_NAME());
		logBean.setDATA_USER(user.getUsLoginId());
		logBean.setDATA_FLAG(bldgrsdnsBean.getDATA_FLAG());
		logBean.setDATA_DATE("sysdate");
		
		addLog(logBean);
		
		session.createSQLQuery("commit").executeUpdate();

		if(AddrCommon.getUsWebRule(user)){
			bldgrsdnsBean.setDATA_FLAG("2");
			shBldgrsdns(bldgrsdnsBean,user);
		}
		
		saveFlag=true;
		return saveFlag;
	}
	
	@Transactional(readOnly = true)
	public boolean luanbldgrsdns(BldgrsdnsBean bldgrsdnsBean,User user){
		boolean saveFlag=false;
		Session session=getSession();
		
		String tLOGID=null; 
		
		String sql=" select sysdate,'" + bldgrsdnsBean.getDIST_CD().substring(0,4) + "'||trim(to_char(SQ_CP_MK_ADR_LOG.nextval,'0000000000')) from dual";
		
		List<?> list = getSession().createSQLQuery(sql).list();
		Iterator<?> it = list.iterator();
		if(it.hasNext()){
			Object[] obj = (Object[]) it.next();
			tLOGID = obj[1].toString();
		}
						
		sql="UPDATE CP_MK_ADR_BLDGRSDNS_" + bldgrsdnsBean.getDIST_CD().substring(0,2) + " set "
		+ "  RSDNBLDG_NAME = '" + bldgrsdnsBean.getRSDNBLDG_NAME() + "', "
		+ "  RSDNBLDG_ABBR = '" + bldgrsdnsBean.getRSDNBLDG_ABBR() + "', "
		+ "  DATA_USER = '" + user.getUsLoginId() + "', "
		+ "  NOTE = '" + bldgrsdnsBean.getNOTE() + "', "
		+ "  LOG_ID = " + tLOGID + ", "
		+ "  DATA_DATE = sysdate "
		+ "  WHERE RSDNBLDG_ID = " + bldgrsdnsBean.getRSDNBLDG_ID();
		session.createSQLQuery(sql).executeUpdate();
		
		LogBean logBean=new LogBean();
		logBean.setLOG_ID(tLOGID);
		logBean.setDIST_CD(bldgrsdnsBean.getDIST_CD());
		logBean.setSTRT_ID(bldgrsdnsBean.getSTRT_ID());
		logBean.setSTRT_NAME(bldgrsdnsBean.getTOTAL_STREET_NAME());
		logBean.setRSDNBLDG_ID(bldgrsdnsBean.getRSDNBLDG_ID());
		logBean.setRSDNBLDG_NAME(bldgrsdnsBean.getRSDNBLDG_NAME());
		logBean.setDATA_USER(user.getUsLoginId());
		logBean.setDATA_FLAG(bldgrsdnsBean.getDATA_FLAG());
		logBean.setDATA_DATE("sysdate");
		
		addLog(logBean);
		
		session.createSQLQuery("commit").executeUpdate();
		
		saveFlag=true;
		return saveFlag;
	}
	
	@Transactional(readOnly = true)
	public boolean delBldgrsdns(BldgrsdnsBean bldgrsdnsBean,User user){
		boolean saveFlag=false;
		Session session=getSession();
		
		String tLOGID=null; 
		
		String sql=" select sysdate,'" + bldgrsdnsBean.getDIST_CD().substring(0,4) + "'||trim(to_char(SQ_CP_MK_ADR_LOG.nextval,'0000000000')) from dual";
		
		List<?> list = getSession().createSQLQuery(sql).list();
		Iterator<?> it = list.iterator();
		if(it.hasNext()){
			Object[] obj = (Object[]) it.next();
			tLOGID = obj[1].toString();
		}
		
		
		bldgrsdnsBean.setDATA_FLAG("8");
		
		sql="DELETE FROM CP_MK_ADR_BLDGRSDNS_" + bldgrsdnsBean.getDIST_CD().substring(0,2) + " " +
		"  WHERE (ADULT_DATA_FLAG = 6 or DATA_FLAG = 6) " + 
		"  AND RSDNBLDG_ID = " + bldgrsdnsBean.getRSDNBLDG_ID();

		
		session.createSQLQuery(sql).executeUpdate();
		
		sql="UPDATE CP_MK_ADR_BLDGRSDNS_" + bldgrsdnsBean.getDIST_CD().substring(0,2) + " set " +
		"  DATA_FLAG = " + bldgrsdnsBean.getDATA_FLAG() + ", " + 
		"  DATA_USER = '" + user.getUsLoginId() + "', " +
		"  NOTE = '" + bldgrsdnsBean.getNOTE() + "', " +
		"  LOG_ID = " + tLOGID + ", " +
		"  DATA_DATE = sysdate " + 
		"  WHERE RSDNBLDG_ID = " + bldgrsdnsBean.getRSDNBLDG_ID();
		session.createSQLQuery(sql).executeUpdate();

		
		session.createSQLQuery(sql).executeUpdate();
		
		LogBean logBean=new LogBean();
		logBean.setLOG_ID(tLOGID);
		logBean.setDIST_CD(bldgrsdnsBean.getDIST_CD());
		logBean.setSTRT_ID(bldgrsdnsBean.getSTRT_ID());
		logBean.setSTRT_NAME(bldgrsdnsBean.getTOTAL_STREET_NAME());
		logBean.setRSDNBLDG_ID(bldgrsdnsBean.getRSDNBLDG_ID());
		logBean.setRSDNBLDG_NAME(bldgrsdnsBean.getRSDNBLDG_NAME());
		logBean.setDATA_USER(user.getUsLoginId());
		logBean.setDATA_FLAG(bldgrsdnsBean.getDATA_FLAG());
		logBean.setDATA_DATE("sysdate");
		
		addLog(logBean);
		
		session.createSQLQuery("commit").executeUpdate();

		if(AddrCommon.getUsWebRule(user)){
			bldgrsdnsBean.setDATA_FLAG("2");
			shBldgrsdns(bldgrsdnsBean,user);
		}
				
		saveFlag=true;
		return saveFlag;
	}
	
	@Transactional(readOnly = true)
	public boolean delBldgrsdnsluan(BldgrsdnsBean bldgrsdnsBean,User user){
		boolean saveFlag=false;
		Session session=getSession();
		
		
		String sql="DELETE FROM CP_MK_ADR_BLDGRSDNS_" + bldgrsdnsBean.getDIST_CD().substring(0,2) + " " +
		"  WHERE  RSDNBLDG_ID = " + bldgrsdnsBean.getRSDNBLDG_ID();

		
		session.createSQLQuery(sql).executeUpdate();
		
		sql="DELETE FROM CP_WH_RL_PG_ST_" + bldgrsdnsBean.getDIST_CD().substring(0,2) + " " +
		"  WHERE  RSDNBLDG_ID = " + bldgrsdnsBean.getRSDNBLDG_ID();

		
		session.createSQLQuery(sql).executeUpdate();

		saveFlag=true;
		return saveFlag;
	}
	
	@Transactional(readOnly = true)
	public boolean fnbldgrsdnsluan(BldgrsdnsBean bldgrsdnsBean,User user){
		boolean saveFlag=false;
		Session session=getSession();
		
		
		String sql="UPDATE CP_MK_ADR_BLDGRSDNS_" + bldgrsdnsBean.getDIST_CD().substring(0,2) + " set " +
		"  IS_IGNORED = 1 , " + 
		"  DATA_USER = '" + user.getUsLoginId() + "', " +
		"  DATA_DATE = sysdate " + 
		"  WHERE RSDNBLDG_ID in (" + bldgrsdnsBean.getRSDNBLDG_ID() + ")";
		
		session.createSQLQuery(sql).executeUpdate();

		saveFlag=true;
		return saveFlag;
	}
	
	@Transactional(readOnly = true)
	public boolean delBldgrsdnsdius(BldgrsdnsBean bldgrsdnsBean,User user){
		boolean saveFlag=false;
		Session session=getSession();
		
		
		
		String sql="DELETE FROM TB_MINUS_BLDGRSDNS_" + bldgrsdnsBean.getDIST_CD().substring(0,2) + " " +
		"  WHERE RSDNBLDG_ID = " + bldgrsdnsBean.getRSDNBLDG_ID();

		
		session.createSQLQuery(sql).executeUpdate();

		saveFlag=true;
		return saveFlag;
	}
	
	
	@Transactional(readOnly = true)
	public boolean delbldgdiustssq(BldgrsdnsBean bldgrsdnsBean,User user){
		boolean saveFlag=false;
		Session session=getSession();
		
		
		
		String sql="DELETE FROM TB_MINUS_BLDGRSDNS_" + bldgrsdnsBean.getDIST_CD().substring(0,2) + " " +
		"  WHERE RSDNBLDG_ID in (" + bldgrsdnsBean.getRSDNBLDG_ID() + ")";

		
		session.createSQLQuery(sql).executeUpdate();

		saveFlag=true;
		return saveFlag;
	}
	
	@Transactional(readOnly = true)
	public boolean shOrganization(OrganizationBean organizationBean,User user){
		boolean saveFlag=false;
		Session session=getSession();
		/*
		System.out.println(organizationBean.getDATA_FLAG());
		System.out.println(organizationBean.getDIST_CD());
		System.out.println(organizationBean.getORG_ID());
		System.out.println(user.getUsLoginId());
		System.out.println(organizationBean.getTOTAL_STREET_NAME());
		*/
		SQLQuery query = (SQLQuery) session.createSQLQuery("{Call CP_MK_ADR_AUDIT.CP_MK_ADR_AUDIT_ORGANIZATION(?,?,?,?,?)}");
		query.setString(0,organizationBean.getDATA_FLAG());
		query.setString(1,organizationBean.getDIST_CD());
		query.setString(2,organizationBean.getORG_ID());
		query.setString(3,user.getUsLoginId());
		query.setString(4,organizationBean.getTOTAL_STREET_NAME());
		query.executeUpdate();
		
		saveFlag=true;
		return saveFlag;
	}
	
	@Transactional(readOnly = true)
	public boolean addOrganization(OrganizationBean organizationBean,User user){
		boolean saveFlag=false;
		Session session=getSession();
		
		String tID=null; 
		String tLOGID=null; 
		String tRSDNBLDG_ID="";
		
		
		
		String sql=" select '" + organizationBean.getDIST_CD().substring(0,4) + "'||trim(to_char(SQ_CP_MK_ADR_BLDGRSDNS.nextval,'0000000000')),'" + organizationBean.getDIST_CD().substring(0,4) + "'||trim(to_char(SQ_CP_MK_ADR_LOG.nextval,'0000000000')) from dual";
		
		List<?> list = getSession().createSQLQuery(sql).list();
		Iterator<?> it = list.iterator();
		if(it.hasNext()){
			Object[] obj = (Object[]) it.next();
			tID = obj[0].toString();
			tLOGID = obj[1].toString();
		}
		
		organizationBean.setORG_ID(tID);
		organizationBean.setDATA_FLAG("6");
		
		/*if(organizationBean.getRSDNBLDG_NAME().length()>0){
			sql="select RSDNBLDG_ID from CP_MK_ADR_BLDGRSDNS_" + organizationBean.getDIST_CD().substring(0,2) + " where DIST_CD = " +  organizationBean.getDIST_CD() + " and  STRT_ID = " +  organizationBean.getSTRT_ID()  + " and  RSDNBLDG_NAME = '" + organizationBean.getRSDNBLDG_NAME()  + "'";
			list = getSession().createSQLQuery(sql).list();
			if(list.size()>0){
				it = list.iterator();
				if(it.hasNext()){
					tRSDNBLDG_ID = it.next().toString();
				}
			}else{
				sql=" select '" + organizationBean.getDIST_CD().substring(0,4) + "'||trim(to_char(SQ_CP_MK_ADR_BLDGRSDNS.nextval,'0000000000')) from dual";
				list = getSession().createSQLQuery(sql).list();
				it = list.iterator();
				if(it.hasNext()){
					tRSDNBLDG_ID = it.next().toString();
				}
				
				sql="INSERT INTO CP_MK_ADR_BLDGRSDNS_" + organizationBean.getDIST_CD().substring(0,2) + " "
				+ "(RSDNBLDG_ID,DIST_CD,STRT_ID,RSDNBLDG_FLAG,RSDNBLDG_NAME,RSDNBLDG_ABBR,STAT_CD,DATA_DATE,DATA_FLAG,LOG_ID,DATA_USER,POST_CD,NUM_FLAG,PREFIX,START_NUM,END_NUM,SUFFIX,DT_PK_CODE,DM_PK_CODE,PG_PK_CODE) "
				+ "values(" + tRSDNBLDG_ID + ",'" +organizationBean.getDIST_CD()+"','" + organizationBean.getSTRT_ID() + "','1','" + organizationBean.getRSDNBLDG_NAME() +"','',"
				+ "1,sysdate," + organizationBean.getDATA_FLAG() + "," + tLOGID + ",'" + user.getUsLoginId()
				+ "','','','','','','','','',''" 
				+ ")";
				session.createSQLQuery(sql).executeUpdate();
			}
		}*/
		
		sql="INSERT INTO CP_MK_ORG_ORGANIZATION_" + organizationBean.getDIST_CD().substring(0,2) + " "
		+ "(ORG_ID,DIST_CD,STRT_ID,ORG_NAME,ORG_ABBR_NAME,ORG_ABBR,STAT_CD,DATA_DATE,DATA_FLAG,LOG_ID,DATA_USER,POST_CD,NUM_FLAG,PREFIX,START_NUM,END_NUM,SUFFIX,DT_PK_CODE,DM_PK_CODE,PG_PK_CODE,RSDNBLDG_ID,NOTE) "
		+ "values(" + organizationBean.getORG_ID() + ",'" + organizationBean.getDIST_CD()+"','" + organizationBean.getSTRT_ID()  + "','" + organizationBean.getORG_NAME() +"','"+ organizationBean.getORG_ABBR_NAME() + "','"+ organizationBean.getORG_ABBR() +"',"
		+ "1,sysdate," + organizationBean.getDATA_FLAG() + "," + tLOGID + ",'" + user.getUsLoginId()
		+ "','" + organizationBean.getPOST_CD() + "','" + organizationBean.getNUM_FLAG() + "','" + organizationBean.getPREFIX() + "','" + organizationBean.getSTART_NUM() + "','" + organizationBean.getEND_NUM() + "','"
		+ organizationBean.getSUFFIX() + "','" + organizationBean.getDT_PK_CODE() + "','" + organizationBean.getDM_PK_CODE() + "','" + organizationBean.getPG_PK_CODE() + "','" + tRSDNBLDG_ID + "','" + organizationBean.getNOTE()  + "'"
		+ ")";
		session.createSQLQuery(sql).executeUpdate();
		
		LogBean logBean=new LogBean();
		logBean.setLOG_ID(tLOGID);
		logBean.setDIST_CD(organizationBean.getDIST_CD());
		logBean.setSTRT_ID(organizationBean.getSTRT_ID());
		logBean.setSTRT_NAME(organizationBean.getTOTAL_STREET_NAME());
		logBean.setRSDNBLDG_ID(tRSDNBLDG_ID);
		logBean.setRSDNBLDG_NAME(organizationBean.getRSDNBLDG_NAME());
		logBean.setORG_ID(tID);
		logBean.setORG_NAME(organizationBean.getORG_NAME());
		logBean.setDATA_USER(user.getUsLoginId());
		logBean.setDATA_FLAG(organizationBean.getDATA_FLAG());
		logBean.setDATA_DATE("sysdate");
		
		addLog(logBean);
		
		session.createSQLQuery("commit").executeUpdate();

		sql=" select count(*)  from  CP_MK_ADR_STREET_" + organizationBean.getDIST_CD().substring(0,2) + " " +
		"  WHERE (ADULT_DATA_FLAG = 6 or DATA_FLAG = 6) " + 
		"  AND STRT_ID = " + organizationBean.getSTRT_ID();
		
		list = getSession().createSQLQuery(sql).list();
		
		if(AddrCommon.getUsWebRule(user) || Integer.valueOf(list.get(0).toString())<1){
			organizationBean.setDATA_FLAG("2");
			shOrganization(organizationBean,user);
		}
		
		saveFlag=true;
		return saveFlag;
	}
	
	@Transactional(readOnly = true)
	public boolean addOrgandius(OrganizationBean organizationBean,User user){
		boolean saveFlag=false;
		Session session=getSession();
		
		String tID=null; 
		String tLOGID=null; 
		String tRSDNBLDG_ID="";
		
		String sql=" select '" + organizationBean.getDIST_CD().substring(0,4) + "'||trim(to_char(SQ_CP_MK_ADR_BLDGRSDNS.nextval,'0000000000')),'" + organizationBean.getDIST_CD().substring(0,4) + "'||trim(to_char(SQ_CP_MK_ADR_LOG.nextval,'0000000000')) from dual";
		
		List<?> list = getSession().createSQLQuery(sql).list();
		Iterator<?> it = list.iterator();
		if(it.hasNext()){
			Object[] obj = (Object[]) it.next();
			tID = obj[0].toString();
			tLOGID = obj[1].toString();
		}
		
		organizationBean.setORG_ID(tID);
		organizationBean.setDATA_FLAG("6");
		
		sql="INSERT INTO CP_MK_ORG_ORGANIZATION_" + organizationBean.getDIST_CD().substring(0,2) + " "
		+ "(ORG_ID,DIST_CD,STRT_ID,ORG_NAME,ORG_ABBR_NAME,ORG_ABBR,STAT_CD,DATA_DATE,DATA_FLAG,LOG_ID,DATA_USER,POST_CD,NUM_FLAG,PREFIX,START_NUM,END_NUM,SUFFIX,DT_PK_CODE,DM_PK_CODE,PG_PK_CODE,RSDNBLDG_ID,NOTE) "
		+ "values(" + organizationBean.getORG_ID() + ",'" + organizationBean.getDIST_CD()+"','" + organizationBean.getSTRT_ID()  + "','" + organizationBean.getORG_NAME() +"','"+ organizationBean.getORG_ABBR_NAME() + "','"+ organizationBean.getORG_ABBR() +"',"
		+ "1,sysdate," + organizationBean.getDATA_FLAG() + "," + tLOGID + ",'" + user.getUsLoginId()
		+ "','" + organizationBean.getPOST_CD() + "','" + organizationBean.getNUM_FLAG() + "','" + organizationBean.getPREFIX() + "','" + organizationBean.getSTART_NUM() + "','" + organizationBean.getEND_NUM() + "','"
		+ organizationBean.getSUFFIX() + "','" + organizationBean.getDT_PK_CODE() + "','" + organizationBean.getDM_PK_CODE() + "','" + organizationBean.getPG_PK_CODE() + "','" + tRSDNBLDG_ID + "','" + organizationBean.getNOTE()  + "'"
		+ ")";
		session.createSQLQuery(sql).executeUpdate();
		
		LogBean logBean=new LogBean();
		logBean.setLOG_ID(tLOGID);
		logBean.setDIST_CD(organizationBean.getDIST_CD());
		logBean.setSTRT_ID(organizationBean.getSTRT_ID());
		logBean.setSTRT_NAME(organizationBean.getTOTAL_STREET_NAME());
		logBean.setRSDNBLDG_ID(tRSDNBLDG_ID);
		logBean.setRSDNBLDG_NAME(organizationBean.getRSDNBLDG_NAME());
		logBean.setORG_ID(tID);
		logBean.setORG_NAME(organizationBean.getORG_NAME());
		logBean.setDATA_USER(user.getUsLoginId());
		logBean.setDATA_FLAG(organizationBean.getDATA_FLAG());
		logBean.setDATA_DATE("sysdate");
		
		addLog(logBean);
		
		session.createSQLQuery("commit").executeUpdate();

		sql=" select count(*)  from  CP_MK_ADR_STREET_" + organizationBean.getDIST_CD().substring(0,2) + " " +
		"  WHERE (ADULT_DATA_FLAG = 6 or DATA_FLAG = 6) " + 
		"  AND STRT_ID = " + organizationBean.getSTRT_ID();
		
		list = getSession().createSQLQuery(sql).list();
		
		if(AddrCommon.getUsWebRule(user) || Integer.valueOf(list.get(0).toString())<1){
			organizationBean.setDATA_FLAG("2");
			shOrganization(organizationBean,user);
		}
		
		sql="DELETE FROM TB_MINUS_ORGANIZATION_" + organizationBean.getDIST_CD().substring(0,2) + " " +
		"  WHERE  ORG_ID = " + organizationBean.getORG_IDTMP();

		
		session.createSQLQuery(sql).executeUpdate();
		
		saveFlag=true;
		return saveFlag;
	}
	
	@Transactional(readOnly = true)
	public boolean upOrganization(OrganizationBean organizationBean,User user){
		boolean saveFlag=false;
		Session session=getSession();
		
		String tLOGID=null; 
		
		String tRSDNBLDG_ID="";
		
		String sql=" select sysdate,'" + organizationBean.getDIST_CD().substring(0,4) + "'||trim(to_char(SQ_CP_MK_ADR_LOG.nextval,'0000000000')) from dual";
		
		List<?> list = getSession().createSQLQuery(sql).list();
		Iterator<?> it = list.iterator();
		if(it.hasNext()){
			Object[] obj = (Object[]) it.next();
			tLOGID = obj[1].toString();
		}
		
		organizationBean.setDATA_FLAG("7");
		
		/*if(organizationBean.getRSDNBLDG_NAME().length()>0){
			sql="select RSDNBLDG_ID from CP_MK_ADR_BLDGRSDNS_" + organizationBean.getDIST_CD().substring(0,2) + " where DIST_CD = " +  organizationBean.getDIST_CD() + " and  STRT_ID = " +  organizationBean.getSTRT_ID()  + " and  RSDNBLDG_NAME = '" + organizationBean.getRSDNBLDG_NAME()  + "'";
			list = getSession().createSQLQuery(sql).list();
			if(list.size()>0){
				it = list.iterator();
				if(it.hasNext()){
					tRSDNBLDG_ID = it.next().toString();
				}
			}else{
				sql=" select '" + organizationBean.getDIST_CD().substring(0,4) + "'||trim(to_char(SQ_CP_MK_ADR_BLDGRSDNS.nextval,'0000000000')) from dual";
				list = getSession().createSQLQuery(sql).list();
				it = list.iterator();
				if(it.hasNext()){
					tRSDNBLDG_ID = it.next().toString();
				}
				
				sql="INSERT INTO CP_MK_ADR_BLDGRSDNS_" + organizationBean.getDIST_CD().substring(0,2) + " "
				+ "(RSDNBLDG_ID,DIST_CD,STRT_ID,RSDNBLDG_FLAG,RSDNBLDG_NAME,RSDNBLDG_ABBR,STAT_CD,DATA_DATE,DATA_FLAG,LOG_ID,DATA_USER,POST_CD,NUM_FLAG,PREFIX,START_NUM,END_NUM,SUFFIX,DT_PK_CODE,DM_PK_CODE,PG_PK_CODE) "
				+ "values(" + tRSDNBLDG_ID + ",'" +organizationBean.getDIST_CD()+"','" + organizationBean.getSTRT_ID() + "','1','" + organizationBean.getRSDNBLDG_NAME() +"','',"
				+ "1,sysdate," + organizationBean.getDATA_FLAG() + "," + tLOGID + ",'" + user.getUsLoginId()
				+ "','','','','','','','','',''" 
				+ ")";
				session.createSQLQuery(sql).executeUpdate();
			}
		}*/
				
		sql="UPDATE CP_MK_ORG_ORGANIZATION_" + organizationBean.getDIST_CD().substring(0,2) + " set"
		+ "  DIST_CD = " + organizationBean.getDIST_CD() + ","
		+ "  STRT_ID = " + organizationBean.getSTRT_ID() + ","
		+ "  DORPLT_ID = " + organizationBean.getDORPLT_ID() + ","
		+ "  ORG_NAME = '" + organizationBean.getORG_NAME() + "', "
		+ "  ORG_ABBR_NAME = '" + organizationBean.getORG_ABBR_NAME() + "', "
		+ "  ORG_ABBR = '" + organizationBean.getORG_ABBR() + "', "
		+ "  DATA_FLAG = " + organizationBean.getDATA_FLAG() + ", "
		+ "  DATA_USER = '" + user.getUsLoginId() + "', "
		+ "  POST_CD = '" + organizationBean.getPOST_CD() + "', "
		+ "  NUM_FLAG = '" + organizationBean.getNUM_FLAG() + "', "
		+ "  PREFIX = '" + organizationBean.getPREFIX() + "', "
		+ "  START_NUM = '" + organizationBean.getSTART_NUM() + "', "
		+ "  END_NUM = '" + organizationBean.getEND_NUM() + "', "
		+ "  SUFFIX = '" + organizationBean.getSUFFIX() + "', "
		+ "  DT_PK_CODE = '" + organizationBean.getDT_PK_CODE() + "', "
		+ "  DM_PK_CODE = '" + organizationBean.getDM_PK_CODE() + "', "
		+ "  PG_PK_CODE = '" + organizationBean.getPG_PK_CODE() + "', "
		+ "  NOTE = '" + organizationBean.getNOTE() + "', "
		+ "  LOG_ID = " + tLOGID + ", "
		+ "  RSDNBLDG_ID = '" + tRSDNBLDG_ID + "', "
		+ "  DATA_DATE = sysdate "
		+ "  WHERE ORG_ID = " + organizationBean.getORG_ID();
		session.createSQLQuery(sql).executeUpdate();
		
		LogBean logBean=new LogBean();
		logBean.setLOG_ID(tLOGID);
		logBean.setDIST_CD(organizationBean.getDIST_CD());
		logBean.setSTRT_ID(organizationBean.getSTRT_ID());
		logBean.setSTRT_NAME(organizationBean.getTOTAL_STREET_NAME());
		logBean.setRSDNBLDG_ID(tRSDNBLDG_ID);
		logBean.setRSDNBLDG_NAME(organizationBean.getRSDNBLDG_NAME());
		logBean.setORG_ID(organizationBean.getORG_ID());
		logBean.setORG_NAME(organizationBean.getORG_NAME());
		logBean.setDATA_USER(user.getUsLoginId());
		logBean.setDATA_FLAG(organizationBean.getDATA_FLAG());
		logBean.setDATA_DATE("sysdate");
		
		addLog(logBean);
		
		session.createSQLQuery("commit").executeUpdate();

		if(AddrCommon.getUsWebRule(user)){
			organizationBean.setDATA_FLAG("2");
			shOrganization(organizationBean,user);
		}
		
		saveFlag=true;
		return saveFlag;
	}
	
	@Transactional(readOnly = true)
	public boolean luanorganization(OrganizationBean organizationBean,User user){
		boolean saveFlag=false;
		Session session=getSession();
		
		String tLOGID=null; 
		
		String tRSDNBLDG_ID="";
		
		String sql=" select sysdate,'" + organizationBean.getDIST_CD().substring(0,4) + "'||trim(to_char(SQ_CP_MK_ADR_LOG.nextval,'0000000000')) from dual";
		
		List<?> list = getSession().createSQLQuery(sql).list();
		Iterator<?> it = list.iterator();
		if(it.hasNext()){
			Object[] obj = (Object[]) it.next();
			tLOGID = obj[1].toString();
		}
	
		
				
		sql="UPDATE CP_MK_ORG_ORGANIZATION_" + organizationBean.getDIST_CD().substring(0,2) + " set"
	
		+ "  ORG_NAME = '" + organizationBean.getORG_NAME() + "', "
		+ "  ORG_ABBR_NAME = '" + organizationBean.getORG_ABBR_NAME() + "', "
		+ "  ORG_ABBR = '" + organizationBean.getORG_ABBR() + "', "
		+ "  DATA_USER = '" + user.getUsLoginId() + "', "
		+ "  NOTE = '" + organizationBean.getNOTE() + "', "
		+ "  LOG_ID = " + tLOGID + ", "
		+ "  DATA_DATE = sysdate "
		+ "  WHERE ORG_ID = " + organizationBean.getORG_ID();
		session.createSQLQuery(sql).executeUpdate();
		
		LogBean logBean=new LogBean();
		logBean.setLOG_ID(tLOGID);
		logBean.setDIST_CD(organizationBean.getDIST_CD());
		logBean.setSTRT_ID(organizationBean.getSTRT_ID());
		logBean.setSTRT_NAME(organizationBean.getTOTAL_STREET_NAME());
		logBean.setRSDNBLDG_ID(tRSDNBLDG_ID);
		logBean.setRSDNBLDG_NAME(organizationBean.getRSDNBLDG_NAME());
		logBean.setORG_ID(organizationBean.getORG_ID());
		logBean.setORG_NAME(organizationBean.getORG_NAME());
		logBean.setDATA_USER(user.getUsLoginId());
		logBean.setDATA_FLAG(organizationBean.getDATA_FLAG());
		logBean.setDATA_DATE("sysdate");
		
		addLog(logBean);
		
		session.createSQLQuery("commit").executeUpdate();
		
		saveFlag=true;
		return saveFlag;
	}
	
	@Transactional(readOnly = true)
	public boolean delOrganization(OrganizationBean organizationBean,User user){
		boolean saveFlag=false;
		Session session=getSession();
		
		String tLOGID=null; 
		
		String sql=" select sysdate,'" + organizationBean.getDIST_CD().substring(0,4) + "'||trim(to_char(SQ_CP_MK_ADR_LOG.nextval,'0000000000')) from dual";
		
		List<?> list = getSession().createSQLQuery(sql).list();
		Iterator<?> it = list.iterator();
		if(it.hasNext()){
			Object[] obj = (Object[]) it.next();
			tLOGID = obj[1].toString();
		}
		
		
		organizationBean.setDATA_FLAG("8");
		
		sql="DELETE FROM CP_MK_ORG_ORGANIZATION_" + organizationBean.getDIST_CD().substring(0,2) + " " +
		"  WHERE (ADULT_DATA_FLAG = 6 or DATA_FLAG = 6) " + 
		"  AND ORG_ID = " + organizationBean.getORG_ID();

		
		session.createSQLQuery(sql).executeUpdate();
		
		sql="UPDATE CP_MK_ORG_ORGANIZATION_" + organizationBean.getDIST_CD().substring(0,2) + " set " +
		"  DATA_FLAG = " + organizationBean.getDATA_FLAG() + ", " + 
		"  DATA_USER = '" + user.getUsLoginId() + "', " +
		"  LOG_ID = " + tLOGID + ", " +
		"  NOTE = '" + organizationBean.getNOTE() + "', " +
		"  DATA_DATE = sysdate " + 
		"  WHERE ORG_ID = " + organizationBean.getORG_ID();
		session.createSQLQuery(sql).executeUpdate();

		
		session.createSQLQuery(sql).executeUpdate();
		
		LogBean logBean=new LogBean();
		logBean.setLOG_ID(tLOGID);
		logBean.setDIST_CD(organizationBean.getDIST_CD());
		logBean.setSTRT_ID(organizationBean.getSTRT_ID());
		logBean.setSTRT_NAME(organizationBean.getTOTAL_STREET_NAME());
		logBean.setORG_ID(organizationBean.getORG_ID());
		logBean.setORG_NAME(organizationBean.getORG_NAME());
		logBean.setDATA_USER(user.getUsLoginId());
		logBean.setDATA_FLAG(organizationBean.getDATA_FLAG());
		logBean.setDATA_DATE("sysdate");
		
		addLog(logBean);
		
		session.createSQLQuery("commit").executeUpdate();

		if(AddrCommon.getUsWebRule(user)){
			organizationBean.setDATA_FLAG("2");
			shOrganization(organizationBean,user);
		}
		
		
		saveFlag=true;
		return saveFlag;
	}
	
	@Transactional(readOnly = true)
	public boolean delOrganizationluan(OrganizationBean organizationBean,User user){
		boolean saveFlag=false;
		Session session=getSession();
		
		
		String sql="DELETE FROM CP_MK_ORG_ORGANIZATION_" + organizationBean.getDIST_CD().substring(0,2) + " " +
		"  WHERE  ORG_ID = " + organizationBean.getORG_ID();

		
		session.createSQLQuery(sql).executeUpdate();
		
		sql="DELETE FROM CP_WH_RL_PG_ST_" + organizationBean.getDIST_CD().substring(0,2) + " " +
		"  WHERE  ORG_ID = " + organizationBean.getORG_ID();

		
		session.createSQLQuery(sql).executeUpdate();

		saveFlag=true;
		return saveFlag;
	}
	
	@Transactional(readOnly = true)
	public boolean fnorganizationluan(OrganizationBean organizationBean,User user){
		boolean saveFlag=false;
		Session session=getSession();
		
		
		String sql="UPDATE CP_MK_ORG_ORGANIZATION_" + organizationBean.getDIST_CD().substring(0,2) + " set " +
		"  IS_IGNORED = 1 , " + 
		"  DATA_USER = '" + user.getUsLoginId() + "', " +
		"  DATA_DATE = sysdate " + 
		"  WHERE ORG_ID in (" + organizationBean.getORG_ID() + ")";
		
		session.createSQLQuery(sql).executeUpdate();

		saveFlag=true;
		return saveFlag;
	}
	
	@Transactional(readOnly = true)
	public boolean delOrganizationdius(OrganizationBean organizationBean,User user){
		boolean saveFlag=false;
		Session session=getSession();
		
		
		
		String sql="DELETE FROM TB_MINUS_ORGANIZATION_" + organizationBean.getDIST_CD().substring(0,2) + " " +
		"  WHERE ORG_ID = " + organizationBean.getORG_ID();

		
		session.createSQLQuery(sql).executeUpdate();

		saveFlag=true;
		return saveFlag;
	}
	
	@Transactional(readOnly = true)
	public boolean delorgdiustssq(OrganizationBean organizationBean,User user){
		boolean saveFlag=false;
		Session session=getSession();
		
		
		
		String sql="DELETE FROM TB_MINUS_ORGANIZATION_" + organizationBean.getDIST_CD().substring(0,2) + " " +
		"  WHERE ORG_ID in (" + organizationBean.getORG_ID() + ")";

		
		session.createSQLQuery(sql).executeUpdate();

		saveFlag=true;
		return saveFlag;
	}
	
	@Transactional(readOnly = true)
	public boolean addQhzuis(QhzuiBean qhzuiBean,User user){
		boolean saveFlag=false;
		Session session=getSession();
		
		
		String sql="INSERT INTO CP_WH_QHZUI "
		+ "(QIANZUI_ID,DIST_CD,FIX,FIX_ABBR,FIX_XZ,FIX_FLAG,FIX_SMP) "
		+ "values(SQ_QIANZUI_ID.nextval,'" + qhzuiBean.getDIST_CD()+"','" + qhzuiBean.getFIX()  + "','" + qhzuiBean.getFIX_ABBR() +"','"+ qhzuiBean.getFIX_XZ() + "','"+ qhzuiBean.getFIX_FLAG() +"',"
		+ " '" + qhzuiBean.getFIX_SMP() + "'"
		+ ")";

		
		session.createSQLQuery(sql).executeUpdate();

		saveFlag=true;
		return saveFlag;
	}
	
	@Transactional(readOnly = true)
	public boolean upQhzuis(QhzuiBean qhzuiBean,User user){
		boolean saveFlag=false;
		Session session=getSession();
		
		
		String sql="UPDATE CP_WH_QHZUI set"
		+ "  FIX = '" + qhzuiBean.getFIX() + "', "
		+ "  FIX_ABBR = '" + qhzuiBean.getFIX_ABBR() + "', "
		+ "  FIX_XZ = '" + qhzuiBean.getFIX_XZ() + "', "
		+ "  FIX_FLAG = '" + qhzuiBean.getFIX_FLAG() + "', "
		+ "  FIX_SMP = '" + qhzuiBean.getFIX_SMP() + "' "
		+ "  WHERE QIANZUI_ID = " + qhzuiBean.getQIANZUI_ID();

		
		session.createSQLQuery(sql).executeUpdate();

		saveFlag=true;
		return saveFlag;
	}
	
	@Transactional(readOnly = true)
	public boolean delQhzuis(QhzuiBean qhzuiBean,User user){
		boolean saveFlag=false;
		Session session=getSession();
		
		
		String sql="DELETE FROM CP_WH_QHZUI " +
		"  WHERE  QIANZUI_ID = " + qhzuiBean.getQIANZUI_ID();

		
		session.createSQLQuery(sql).executeUpdate();

		saveFlag=true;
		return saveFlag;
	}
	
	@Transactional(readOnly = true)
	public boolean uprgpqpd(ExgcltitemopeBean exgcltitemopeBean,User user){
		boolean saveFlag=false;
		Session session=getSession();
		
		/*
		String sql="UPDATE exg_clt_item_ope set"
		+ "  PK_OPEID = '" + user.getUsLoginId() + "', "
		+ "  PK_OPENAME = '" + user.getUsName() + "', "
		+ "  DT_PK_CODE = '" + exgcltitemopeBean.getDT_PK_CODE() + "', "
		+ "  DM_PK_CODE = '" + exgcltitemopeBean.getDM_PK_CODE() + "', "
		+ "  PG_PK_CODE = '" + exgcltitemopeBean.getPG_PK_CODE() + "', "
		+ "  PK_REMARK = '" + exgcltitemopeBean.getPK_REMARK() + "', "
		+ "  PK_FLAG_R = 'R', "
		+ "  PK_TIME = sysdate " 
		+ "  WHERE SEQID = " + exgcltitemopeBean.getSEQID();
		*/
		String bsql = " exg_clt_item_ope_rg ";
		if(exgcltitemopeBean.getISWHQUERY().equals("2")){
			bsql = " exg_clt_item_ope_rg_his ";
		}
		String sql="UPDATE " + bsql + " set"
		//临时测试exg_clt_item_ope_rg
		//String sql="UPDATE exg_clt_item_ope set"
			+ "  PK_OPEID = '" + user.getUsLoginId() + "', "
			+ "  PK_OPENAME = '" + user.getUsName() + "', "
			+ "  DT_PK_CODE = '" + exgcltitemopeBean.getDT_PK_CODE() + "', "
			+ "  DM_PK_CODE = '" + exgcltitemopeBean.getDM_PK_CODE() + "', "
			+ "  PG_PK_CODE = '" + exgcltitemopeBean.getPG_PK_CODE() + "', "
			+ "  PK_REMARK = '" + exgcltitemopeBean.getPK_REMARK() + "', "
			+ "  PK_FLAG_R = 'R', "
			+ "  PK_TIME = decode(PK_TIME,null,sysdate,PK_TIME) " 
			+ "  WHERE SEQID in (" + exgcltitemopeBean.getSEQID() + ")";
		
		session.createSQLQuery(sql).executeUpdate();

		saveFlag=true;
		return saveFlag;
	}
	
	@Transactional(readOnly = true)
	public boolean uprgpqpdn(ExgcltitemopeBean exgcltitemopeBean,User user){
		boolean saveFlag=false;
		Session session=getSession();

		String bsql = " exg_clt_item_ope ";
		String sql="UPDATE " + bsql + " set"
			+ "  PK_OPEID = '" + user.getUsLoginId() + "', "
			+ "  PK_OPENAME = '" + user.getUsName() + "', "
			+ "  DT_PK_CODE = '" + exgcltitemopeBean.getDT_PK_CODE() + "', "
			+ "  DM_PK_CODE = '" + exgcltitemopeBean.getDM_PK_CODE() + "', "
			+ "  PG_PK_CODE = '" + exgcltitemopeBean.getPG_PK_CODE() + "', "
			+ "  PK_REMARK = '" + exgcltitemopeBean.getPK_REMARK() + "', "
			+ "  PK_FLAG_R = 'R', "
			+ "  PK_TIME = decode(PK_TIME,null,sysdate,PK_TIME) " 
			+ "  WHERE SEQID in (" + exgcltitemopeBean.getSEQID() + ")";
		
		session.createSQLQuery(sql).executeUpdate();
		
		if(exgcltitemopeBean.getDT_PK_CODE()!=null && exgcltitemopeBean.getDT_PK_CODE().length()>0){
			String segf="0";
			if(exgcltitemopeBean.getPG_PK_CODE()!=null && exgcltitemopeBean.getPG_PK_CODE().length()>0){
				segf = "1";
			}
			
			String tOFFICE_CODE=null; 
			String tTIME=null; 
			
			sql=" select OFFICE_CODE,sysdate from CP_WH_DISTRICT where DT_PK_CODE = '" +  exgcltitemopeBean.getDT_PK_CODE() + "'";
			
			List<?> list = getSession().createSQLQuery(sql).list();
			
			if(list.size()>0){
					Iterator<?> it = list.iterator();
					if(it.hasNext()){
						Object[] obj = (Object[]) it.next();
						tOFFICE_CODE = obj[0].toString();
						tTIME = obj[1].toString();
					}
		
					sql="insert into EXG_ESB_MATCH(SEQID,ACTION,ITEMNO,COLLECT_DATE,COLLECT_TIME,COLLECT_OFFICE," +
					"INSERT_DATE,INSERT_TIME," +
					"BL_USER,BL_OFFICE_CODE,IS_DISTRI,SOURCE,REC_DIST_TYPE,REC_SEG_TYPE,SEND_DIST_TYPE,SEND_SEG_TYPE,MATCH_DATE,MATCH_TIME," +
					"PRODUCT,REC_DIST_FLAG," +
					"REC_SEG_FLAG," +
					"SEND_DIST_FLAG,SEND_SEG_FLAG," +
					"REC_POSTDIST," +
					"REC_POSTSEG," +
					"SEND_POSTDIST,SEND_POSTSEG,REC_OFFDIST,SEND_OFFDIST) "
					+ " select SEQ_EXG_CLT_ITEM_ORG.nextval," +
							"'U',ITEMNO,substr(COLLECT_DATE,1,8),substr(COLLECT_DATE,9,6),COLLECT_OFFICE," +
							"substr(POSTTIME,1,8),substr(POSTTIME,9,6)," +
							"1,100,IS_DISTRI,SOURCE,2,2,NULL,NULL,to_char(sysdate,'yyyymmdd'),to_char(sysdate,'hh24miss')," +
							"PRODUCT,1," +
							"'" +  segf + "'," +
							"NULL,NULL," +
							"'" +  exgcltitemopeBean.getDT_PK_CODE() + "'," +
							"'" +  exgcltitemopeBean.getPG_PK_CODE() + "'," +
							"NULL,NULL," +
							"'" +  tOFFICE_CODE + "'," +
							"NULL from EXG_CLT_ITEM_OPE"
					+ "  WHERE SEQID in (" + exgcltitemopeBean.getSEQID() + ")";
					
					session.createSQLQuery(sql).executeUpdate();
					
					sql=" delete from  EXG_ESB_MATCH_RG  WHERE SEQID  in (" + exgcltitemopeBean.getSEQID() + ")";
		
					session.createSQLQuery(sql).executeUpdate();
					
					sql="insert into EXG_ESB_MATCH_RG(SEQID,ACTION,ITEMNO,COLLECT_DATE,COLLECT_TIME,COLLECT_OFFICE," +
					"INSERT_DATE,INSERT_TIME," +
					"BL_USER,BL_OFFICE_CODE,IS_DISTRI,SOURCE,REC_DIST_TYPE,REC_SEG_TYPE,SEND_DIST_TYPE,SEND_SEG_TYPE,MATCH_DATE,MATCH_TIME," +
					"PRODUCT,REC_DIST_FLAG," +
					"REC_SEG_FLAG," +
					"SEND_DIST_FLAG,SEND_SEG_FLAG," +
					"REC_POSTDIST," +
					"REC_POSTSEG," +
					"SEND_POSTDIST,SEND_POSTSEG,REC_OFFDIST,SEND_OFFDIST,INSERTDATE) "
					+ " select SEQID," +
					"'U',ITEMNO,substr(COLLECT_DATE,1,8),substr(COLLECT_DATE,9,6),COLLECT_OFFICE," +
					"substr(POSTTIME,1,8),substr(POSTTIME,9,6)," +
					"1,100,IS_DISTRI,SOURCE,2,2,NULL,NULL,to_char(sysdate,'yyyymmdd'),to_char(sysdate,'hh24miss')," +
					"PRODUCT,1," +
					"'" +  segf + "'," +
					"NULL,NULL," +
					"'" +  exgcltitemopeBean.getDT_PK_CODE() + "'," +
					"'" +  exgcltitemopeBean.getPG_PK_CODE() + "'," +
					"NULL,NULL," +
					"'" +  tOFFICE_CODE + "'," +
					"NULL,to_char(sysdate,'yyyymmddhh24miss') from EXG_CLT_ITEM_OPE"
					+ "  WHERE SEQID in (" + exgcltitemopeBean.getSEQID() + ")";
		
					session.createSQLQuery(sql).executeUpdate();
					/*
					String[] tTOTAL_ALL_VALUE = exgcltitemopeBean.getSEQID().split(",");
					for(int i=0;i<tTOTAL_ALL_VALUE.length;i++){
		
						String tSEQID=null; 
						
						sql=" select sysdate,SEQ_EXG_CLT_ITEM_ORG.nextval from dual";
						
						List<?> list = getSession().createSQLQuery(sql).list();
						Iterator<?> it = list.iterator();
						if(it.hasNext()){
							Object[] obj = (Object[]) it.next();
							tSEQID = obj[1].toString();
						}
						
						sql="insert into EXG_ESB_MATCH(SEQID,ACTION,ITEMNO,COLLECT_DATE,COLLECT_TIME,COLLECT_OFFICE," +
								"INSERT_DATE,INSERT_TIME," +
								"BL_USER,BL_OFFICE_CODE,IS_DISTRI,SOURCE,REC_DIST_TYPE,REC_SEG_TYPE,SEND_DIST_TYPE,SEND_SEG_TYPE,MATCH_DATE,MATCH_TIME," +
								"PRODUCT,REC_DIST_FLAG," +
								"REC_SEG_FLAG," +
								"SEND_DIST_FLAG,SEND_SEG_FLAG," +
								"REC_POSTDIST," +
								"REC_POSTSEG," +
								"SEND_POSTDIST,SEND_POSTSEG,REC_OFFDIST,SEND_OFFDIST) "
							+ " select " + tSEQID + "," +
									"'U',ITEMNO,substr(COLLECT_DATE,1,8),substr(COLLECT_DATE,9,6),COLLECT_OFFICE," +
									"to_char(sysdate,'yyyymmdd'),to_char(sysdate,'hh24miss')," +
									"1,100,IS_DISTRI,SOURCE,2,2,NULL,NULL,substr(POSTTIME,1,8),substr(POSTTIME,9,6)," +
									"PRODUCT,1," +
									"'" +  segf + "'," +
									"NULL,NULL," +
									"'" +  exgcltitemopeBean.getDT_PK_CODE() + "'," +
									"'" +  exgcltitemopeBean.getPG_PK_CODE() + "'," +
									"NULL,NULL,NULL,NULL from EXG_CLT_ITEM_OPE"
							+ "  WHERE SEQID = " + tTOTAL_ALL_VALUE[i];
					
						session.createSQLQuery(sql).executeUpdate();
						
						sql=" delete from  EXG_ESB_MATCH_RG  WHERE SEQID = " + tTOTAL_ALL_VALUE[i];
			
						session.createSQLQuery(sql).executeUpdate();
						
						sql="insert into EXG_ESB_MATCH_RG(SEQID,ACTION,ITEMNO,COLLECT_DATE,COLLECT_TIME,COLLECT_OFFICE," +
						"INSERT_DATE,INSERT_TIME," +
						"BL_USER,BL_OFFICE_CODE,IS_DISTRI,SOURCE,REC_DIST_TYPE,REC_SEG_TYPE,SEND_DIST_TYPE,SEND_SEG_TYPE,MATCH_DATE,MATCH_TIME," +
						"PRODUCT,REC_DIST_FLAG," +
						"REC_SEG_FLAG," +
						"SEND_DIST_FLAG,SEND_SEG_FLAG," +
						"REC_POSTDIST," +
						"REC_POSTSEG," +
						"SEND_POSTDIST,SEND_POSTSEG,REC_OFFDIST,SEND_OFFDIST,INSERTDATE) "
						+ " select " + tTOTAL_ALL_VALUE[i] + "," +
						"ACTION,ITEMNO,COLLECT_DATE,COLLECT_TIME,COLLECT_OFFICE," +
						"INSERT_DATE,INSERT_TIME," +
						"BL_USER,BL_OFFICE_CODE,IS_DISTRI,SOURCE,REC_DIST_TYPE,REC_SEG_TYPE,SEND_DIST_TYPE,SEND_SEG_TYPE,MATCH_DATE,MATCH_TIME," +
						"PRODUCT,REC_DIST_FLAG," +
						"REC_SEG_FLAG," +
						"SEND_DIST_FLAG,SEND_SEG_FLAG," +
						"REC_POSTDIST," +
						"REC_POSTSEG," +
						"SEND_POSTDIST,SEND_POSTSEG,REC_OFFDIST,SEND_OFFDIST,to_char(sysdate,'yyyymmddhh24miss') from EXG_ESB_MATCH "
							+ "  WHERE SEQID = " + tSEQID;
			
						session.createSQLQuery(sql).executeUpdate();
					}	
					*/
			}
		}

		saveFlag=true;
		return saveFlag;
	}
	
	@Transactional(readOnly = true)
	public boolean tssq(ExgcltitemopeBean exgcltitemopeBean,User user){
		boolean saveFlag=false;
		Session session=getSession();
		
		/*
		String sql="UPDATE exg_clt_item_ope set"
			+ "  PK_OPEID = '" + user.getUsLoginId() + "', "
			+ "  PK_OPENAME = '" + user.getUsName() + "', "
			+ "  PK_REMARK = '调整行政区-" + exgcltitemopeBean.getDIST_CD() + "', "
			+ "  POSTDISTTYPE = 4 , "
			+ "  PK_FLAG_R = 'R', "
			+ "  PK_TIME = sysdate " 
			+ "  WHERE SEQID in (" + exgcltitemopeBean.getSEQID() + ")";
		
		session.createSQLQuery(sql).executeUpdate();
		*/
		
		//临时测试exg_clt_item_ope_rg
		String sql="UPDATE exg_clt_item_ope_rg set"
		//String sql="UPDATE exg_clt_item_ope set"
			+ "  PK_OPEID = '" + user.getUsLoginId() + "', "
			+ "  PK_OPENAME = '" + user.getUsName() + "', "
			+ "  PK_REMARK = '调整行政区-" + exgcltitemopeBean.getDIST_CD() + "', "
			+ "  POSTDISTTYPE = 4 , "
			+ "  PK_FLAG_R = 'R', "
			+ "  PK_TIME = decode(PK_TIME,null,sysdate,PK_TIME) " 
			+ "  WHERE SEQID in (" + exgcltitemopeBean.getSEQID() + ")";
		
		session.createSQLQuery(sql).executeUpdate();

		/*
		String[] tTOTAL_ALL_VALUE = exgcltitemopeBean.getSEQID().split(",");
		for(int i=0;i<tTOTAL_ALL_VALUE.length;i++){
			sql="insert into EXG_CLT_ITEM_ORG(SEQID,DIST_CD,ACTION,ITEMNO,COLLECT_DATE,COLLECT_OFFICE,INOROUT,IS_DISTRI,ADDR_FLAG,COLLECT_POSTCODE,DEST_PLACE,ACTUAL_WEIGHT,VOLUME_WEIGHT,BILLING_WEIGHT,TRANSTYPE,SEND_NAME,SEND_COMP,SEND_TELNO1,SEND_TELNO2,SEND_ALLADDR,SEND_PROV,SEND_CITY,SEND_COUNTY,SEND_STREET,SEND_ORG,SEND_ADDR,SEND_POST,REC_POST,REC_ALLADDR,REC_PROV,REC_CITY,REC_COUNTY,REC_STREET,REC_ORG,REC_TELNO1,REC_TELNO2,REC_NAME,REC_COMP,REC_ADDR,PRODUCT,BUSSINESS,LENGTH,WIDTH,HEIGHT,COLLECTNAME,COLLECTID,WORKERNAME,WORKERID,REMARKCODE,REMARKDESC,MATCHID,POSTDIST,POSTSEG,SOURCE,INSERTTIME,INSERT_YEAR,INSERT_MONTH,INSERT_DAY,CLIENT_CODE,ENTRYTIME,BL_USER,BL_OFFICE_CODE)" +
			" select SEQ_EXG_CLT_ITEM_ORG.nextval,'"+ exgcltitemopeBean.getDIST_CD() + "',"+
			" ACTION,ITEMNO,COLLECT_DATE,COLLECT_OFFICE,INOROUT,IS_DISTRI,ADDR_FLAG,COLLECT_POSTCODE,DEST_PLACE,ACTUAL_WEIGHT,VOLUME_WEIGHT,BILLING_WEIGHT,TRANSTYPE,SEND_NAME,SEND_COMP,SEND_TELNO1,SEND_TELNO2,SEND_ALLADDR,SEND_PROV,SEND_CITY,SEND_COUNTY,SEND_STREET,SEND_ORG,SEND_ADDR,SEND_POST,REC_POST,REC_ALLADDR,REC_PROV,REC_CITY,REC_COUNTY,REC_STREET,REC_ORG,REC_TELNO1,REC_TELNO2,REC_NAME,REC_COMP,REC_ADDR,PRODUCT,BUSSINESS,LENGTH,WIDTH,HEIGHT,COLLECTNAME,COLLECTID,WORKERNAME,WORKERID,REMARKCODE,REMARKDESC,MATCHID,POSTDIST,POSTSEG,SOURCE || '_ER',to_char(sysdate,'yyyymmddhh24miss'),to_char(sysdate,'yyyy'),to_char(sysdate,'mm'),to_char(sysdate,'dd'),CLIENT_CODE,ENTRYTIME,BL_USER,BL_OFFICE_CODE from exg_clt_item_org a "
			+ "  WHERE a.SEQID = " + tTOTAL_ALL_VALUE[i];
			
			session.createSQLQuery(sql).executeUpdate();
		}
		*/
		
		
		/*
		sql="insert into EXG_CLT_ITEM_ORG_rg(SEQID,DIST_CD,ACTION,ITEMNO,COLLECT_DATE,COLLECT_OFFICE,INOROUT,IS_DISTRI,ADDR_FLAG,COLLECT_POSTCODE,DEST_PLACE,ACTUAL_WEIGHT,VOLUME_WEIGHT,BILLING_WEIGHT,TRANSTYPE,SEND_NAME,SEND_COMP,SEND_TELNO1,SEND_TELNO2,SEND_ALLADDR,SEND_PROV,SEND_CITY,SEND_COUNTY,SEND_STREET,SEND_ORG,SEND_ADDR,SEND_POST,REC_POST,REC_ALLADDR,REC_PROV,REC_CITY,REC_COUNTY,REC_STREET,REC_ORG,REC_TELNO1,REC_TELNO2,REC_NAME,REC_COMP,REC_ADDR,PRODUCT,BUSSINESS,LENGTH,WIDTH,HEIGHT,COLLECTNAME,COLLECTID,WORKERNAME,WORKERID,REMARKCODE,REMARKDESC,MATCHID,POSTDIST,POSTSEG,SOURCE,INSERTTIME,INSERT_YEAR,INSERT_MONTH,INSERT_DAY,CLIENT_CODE,ENTRYTIME,BL_USER,BL_OFFICE_CODE)" +
		" select SEQ_EXG_CLT_ITEM_ORG_RG.nextval,'"+ exgcltitemopeBean.getDIST_CD() + "',"+
		" ACTION,ITEMNO,COLLECT_DATE,COLLECT_OFFICE,INOROUT,IS_DISTRI,ADDR_FLAG,COLLECT_POSTCODE,DEST_PLACE,ACTUAL_WEIGHT,VOLUME_WEIGHT,BILLING_WEIGHT,TRANSTYPE,SEND_NAME,SEND_COMP,SEND_TELNO1,SEND_TELNO2,SEND_ALLADDR,SEND_PROV,SEND_CITY,SEND_COUNTY,SEND_STREET,SEND_ORG,SEND_ADDR,SEND_POST,REC_POST,REC_ALLADDR,REC_PROV,REC_CITY,REC_COUNTY,REC_STREET,REC_ORG,REC_TELNO1,REC_TELNO2,REC_NAME,REC_COMP,REC_ADDR,PRODUCT,BUSSINESS,LENGTH,WIDTH,HEIGHT,COLLECTNAME,COLLECTID,WORKERNAME,WORKERID,REMARKCODE,REMARKDESC,MATCHID,POSTDIST,POSTSEG,SOURCE || '_ER',to_char(sysdate,'yyyymmddhh24miss'),to_char(sysdate,'yyyy'),to_char(sysdate,'mm'),to_char(sysdate,'dd'),CLIENT_CODE,ENTRYTIME,BL_USER,BL_OFFICE_CODE from exg_clt_item_org a "
		+ "  WHERE a.SEQID in (" + exgcltitemopeBean.getSEQID() + ")";

		session.createSQLQuery(sql).executeUpdate();
		*/
		
		saveFlag=true;
		return saveFlag;
	}
	
	@Transactional(readOnly = true)
	public boolean uprgming(OpexxsbbocBean opexxsbbocBean,User user){
		boolean saveFlag=false;
		
		Session session=getSession();
		
		
		
		String sql="";
		
		sql="UPDATE exg_clt_item_ope_rg set"
		//临时测试exg_clt_item_ope_rg
	    //sql="UPDATE exg_clt_item_ope set"
		+ "  PK_OPEID = '" + user.getUsLoginId() + "', "
		+ "  PK_OPENAME = '" + user.getUsName() + "', "
		+ "  DT_PK_CODE = decode('" + opexxsbbocBean.getDT_PK_CODE() + "','null',null,'" + opexxsbbocBean.getDT_PK_CODE() + "'), "
		+ "  DM_PK_CODE = decode('" + opexxsbbocBean.getDM_PK_CODE() + "','null',null,'" + opexxsbbocBean.getDM_PK_CODE() + "'), "
		+ "  PG_PK_CODE = decode('" + opexxsbbocBean.getPG_PK_CODE() + "','null',null,'" + opexxsbbocBean.getPG_PK_CODE() + "'), "
		+ "  PK_ALLADDR = '" + opexxsbbocBean.getSTRT1_NAME()+
			opexxsbbocBean.getSTRT2_NAME()+
			opexxsbbocBean.getSTRT3_NAME()+
			opexxsbbocBean.getSTRT4_NAME()+
			opexxsbbocBean.getSTRT5_NAME()+ "', "
		+ "  PK_FLAG_R = 'R', "
		+ "  PK_TIME = decode(PK_TIME,null,sysdate,PK_TIME) " 
		+ "  WHERE SEQID = " + opexxsbbocBean.getSEQID();

		session.createSQLQuery(sql).executeUpdate();
		
		sql=" select sysdate,SQ_OPE_XX_SBBOC.nextval from dual";
		
		String tOPEXXID = "";
		List<?> list = getSession().createSQLQuery(sql).list();
		Iterator<?> it = list.iterator();
		if(it.hasNext()){
			Object[] obj = (Object[]) it.next();
			tOPEXXID = obj[1].toString();
		}
		
		sql="INSERT INTO OPE_XX_SBBOC "
			+ "(OPEXXID,SEQID,ITEMNO,REC_ALLADDR,REC_ORG,DIST_CD," +
					"STRT1_NAME,STRT2_NAME,STRT3_NAME,STRT4_NAME,STRT5_NAME," +
					"STRT1_ABBR_NAME,STRT2_ABBR_NAME,STRT3_ABBR_NAME,STRT4_ABBR_NAME,STRT5_ABBR_NAME," +
					"STRT_ABBR,SEG_NUM,POST_CD,RSDNBLDG_NAME,RSDNBLDG_ABBR," +
					"ORG_NAME,ORG_ABBR_NAME,ORG_ABBR," +
					"DT_PK_CODE,DM_PK_CODE,PG_PK_CODE," +
					"START_NUM,PREFIX,END_NUM,SUFFIX,NUM_FLAG,NOTE," +
					"DATA_USER,DATA_DATE,DATA_FLAG) "
			+ "values('" + tOPEXXID + "','" + opexxsbbocBean.getSEQID()+"','" + opexxsbbocBean.getITEMNO()  + "','" + opexxsbbocBean.getREC_ALLADDR() +"','"+ opexxsbbocBean.getREC_ORG() + "','"+ opexxsbbocBean.getDIST_CD() +"',"
			+ " '" + opexxsbbocBean.getSTRT1_NAME() + "','" + opexxsbbocBean.getSTRT2_NAME() + "','" + opexxsbbocBean.getSTRT3_NAME() + "','" + opexxsbbocBean.getSTRT4_NAME() + "','" + opexxsbbocBean.getSTRT5_NAME() + "',"
			+ " '" + opexxsbbocBean.getSTRT1_ABBR_NAME() + "','" + opexxsbbocBean.getSTRT2_ABBR_NAME() + "','" + opexxsbbocBean.getSTRT3_ABBR_NAME() + "','" + opexxsbbocBean.getSTRT4_ABBR_NAME() + "','" + opexxsbbocBean.getSTRT5_ABBR_NAME() + "',"
			+ " '" + opexxsbbocBean.getSTRT_ABBR() + "','" + opexxsbbocBean.getSEG_NUM() + "','" + opexxsbbocBean.getPOST_CD() + "','" + opexxsbbocBean.getRSDNBLDG_NAME() + "','" + opexxsbbocBean.getRSDNBLDG_ABBR() + "',"
			+ " '" + opexxsbbocBean.getORG_NAME() + "','" + opexxsbbocBean.getORG_ABBR_NAME() + "','" + opexxsbbocBean.getORG_ABBR() + "',"
			+ " decode('" + opexxsbbocBean.getDT_PK_CODE() + "','null',null,'" + opexxsbbocBean.getDT_PK_CODE() + "'),decode('" + opexxsbbocBean.getDM_PK_CODE() + "','null',null,'" + opexxsbbocBean.getDM_PK_CODE() + "'),decode('" + opexxsbbocBean.getPG_PK_CODE() + "','null',null,'" + opexxsbbocBean.getPG_PK_CODE() + "'),"
			+ " '" + opexxsbbocBean.getSTART_NUM() + "','" + opexxsbbocBean.getPREFIX() + "','" + opexxsbbocBean.getEND_NUM()+ "','" + opexxsbbocBean.getSUFFIX()+ "','" + opexxsbbocBean.getNUM_FLAG()+ "','" + opexxsbbocBean.getNOTE() + "',"
			+ " '" +user.getUsLoginId() + "',sysdate,'1'"
			+ ")";

		session.createSQLQuery(sql).executeUpdate();
		
		session.createSQLQuery("commit").executeUpdate();
		
		SQLQuery query = (SQLQuery) session.createSQLQuery("{Call CP_MK_ADR_CZ_OPE.CP_MK_ADR_OPE_ALL(?)}");
		query.setString(0,tOPEXXID);
		query.executeUpdate();
		
		saveFlag=true;
		return saveFlag;
	}
	
	@Transactional(readOnly = true)
	public boolean addBldgrsdnsdiusorg(BldgrsdnsBean bldgrsdnsBean,User user){
		boolean saveFlag=false;
		Session session=getSession();
		
		String tID=null; 
		String tLOGID=null; 
		
		String sql=" select '" + bldgrsdnsBean.getDIST_CD().substring(0,4) + "'||trim(to_char(SQ_CP_MK_ADR_BLDGRSDNS.nextval,'0000000000')),'" + bldgrsdnsBean.getDIST_CD().substring(0,4) + "'||trim(to_char(SQ_CP_MK_ADR_LOG.nextval,'0000000000')) from dual";
		
		List<?> list = getSession().createSQLQuery(sql).list();
		Iterator<?> it = list.iterator();
		if(it.hasNext()){
			Object[] obj = (Object[]) it.next();
			tID = obj[0].toString();
			tLOGID = obj[1].toString();
		}
		
		bldgrsdnsBean.setRSDNBLDG_ID(tID);
		bldgrsdnsBean.setDATA_FLAG("6");
		
		sql="INSERT INTO CP_MK_ADR_BLDGRSDNS_" + bldgrsdnsBean.getDIST_CD().substring(0,2) + " "
			+ "(RSDNBLDG_ID,DIST_CD,STRT_ID,RSDNBLDG_FLAG,RSDNBLDG_NAME,RSDNBLDG_ABBR,STAT_CD,DATA_DATE,DATA_FLAG,LOG_ID,DATA_USER,POST_CD,NUM_FLAG,PREFIX,START_NUM,END_NUM,SUFFIX,DT_PK_CODE,DM_PK_CODE,PG_PK_CODE,NOTE) "
			+ "values(" + bldgrsdnsBean.getRSDNBLDG_ID() + ",'" +bldgrsdnsBean.getDIST_CD()+"','" + bldgrsdnsBean.getSTRT_ID() + "','0','" + bldgrsdnsBean.getRSDNBLDG_NAME() +"','"+ bldgrsdnsBean.getRSDNBLDG_ABBR() +"',"
			+ "1,sysdate," + bldgrsdnsBean.getDATA_FLAG() + "," + tLOGID + ",'" + user.getUsLoginId()
			+ "','" + bldgrsdnsBean.getPOST_CD() + "','" + bldgrsdnsBean.getNUM_FLAG() + "','" + bldgrsdnsBean.getPREFIX() + "','" + bldgrsdnsBean.getSTART_NUM() + "','" + bldgrsdnsBean.getEND_NUM() + "','"
			+ bldgrsdnsBean.getSUFFIX() + "','" + bldgrsdnsBean.getDT_PK_CODE() + "','" + bldgrsdnsBean.getDM_PK_CODE() + "','" + bldgrsdnsBean.getPG_PK_CODE() + "','" + bldgrsdnsBean.getNOTE() + "'" 
			+ ")";
		session.createSQLQuery(sql).executeUpdate();
		
		LogBean logBean=new LogBean();
		logBean.setLOG_ID(tLOGID);
		logBean.setDIST_CD(bldgrsdnsBean.getDIST_CD());
		logBean.setSTRT_ID(bldgrsdnsBean.getSTRT_ID());
		logBean.setSTRT_NAME(bldgrsdnsBean.getTOTAL_STREET_NAME());
		logBean.setRSDNBLDG_ID(tID);
		logBean.setRSDNBLDG_NAME(bldgrsdnsBean.getRSDNBLDG_NAME());
		logBean.setDATA_USER(user.getUsLoginId());
		logBean.setDATA_FLAG(bldgrsdnsBean.getDATA_FLAG());
		logBean.setDATA_DATE("sysdate");
		
		addLog(logBean);
		
		session.createSQLQuery("commit").executeUpdate();
		
		sql=" select count(*)  from  CP_MK_ADR_STREET_" + bldgrsdnsBean.getDIST_CD().substring(0,2) + " " +
		"  WHERE (ADULT_DATA_FLAG = 6 or DATA_FLAG = 6) " + 
		"  AND STRT_ID = " + bldgrsdnsBean.getSTRT_ID();
		
		list = getSession().createSQLQuery(sql).list();
		
		if(AddrCommon.getUsWebRule(user) || Integer.valueOf(list.get(0).toString())<1){
			bldgrsdnsBean.setDATA_FLAG("2");
			shBldgrsdns(bldgrsdnsBean,user);
		}
		
		sql="DELETE FROM TB_MINUS_ORGANIZATION_" + bldgrsdnsBean.getDIST_CD().substring(0,2) + " " +
		"  WHERE  org_id = " + bldgrsdnsBean.getRSDNBLDG_IDTMP();

		
		session.createSQLQuery(sql).executeUpdate();
		
		saveFlag=true;
		return saveFlag;
	}
	
	@Transactional(readOnly = true)
	public boolean delzshf(CpwhrlpgstBean cpwhrlpgstBean,User user){
		boolean saveFlag=false;
		Session session=getSession();
		
		
		String sql="delete from CP_WH_RL_PG_ST_" + cpwhrlpgstBean.getDIST_CD().substring(0,2) + 
		"  WHERE RSDNBLDG_ID||ORG_ID is not null " +
		" and RL_PG_ST_ID in (" + cpwhrlpgstBean.getRL_PG_ST_ID() + ")";
		
		session.createSQLQuery(sql).executeUpdate();

		saveFlag=true;
		return saveFlag;
	}
	
	@Transactional(readOnly = true)
	public boolean delzsyq(CpwhrlpgstBean cpwhrlpgstBean,User user){
		boolean saveFlag=false;
		Session session=getSession();
		
		String sql="UPDATE CP_WH_RL_PG_ST_" + cpwhrlpgstBean.getDIST_CD().substring(0,2) + 
		" set DT_PK_CODE = null," +
		" DM_PK_CODE = null," +
		" PG_PK_CODE = null, " +
		"  OPERATOR = '" + user.getUsLoginId() + "', " +
		"  MOD_DATE = sysdate " + 
		" WHERE RL_PG_ST_ID in (" + cpwhrlpgstBean.getRL_PG_ST_ID() + ")";
		
		session.createSQLQuery(sql).executeUpdate();

		saveFlag=true;
		return saveFlag;
	}
	
	@Transactional(readOnly = true)
	public boolean delzsyb(CpwhrlpgstBean cpwhrlpgstBean,User user){
		boolean saveFlag=false;
		Session session=getSession();
		
		String sql="UPDATE CP_WH_RL_PG_ST_" + cpwhrlpgstBean.getDIST_CD().substring(0,2) + 
		" set DM_PK_CODE = null," +
		" PG_PK_CODE = null, " +
		"  OPERATOR = '" + user.getUsLoginId() + "', " +
		"  MOD_DATE = sysdate " + 
		" WHERE RL_PG_ST_ID in (" + cpwhrlpgstBean.getRL_PG_ST_ID() + ")";
		
		session.createSQLQuery(sql).executeUpdate();

		saveFlag=true;
		return saveFlag;
	}
	
	@Transactional(readOnly = true)
	public boolean delzsyd(CpwhrlpgstBean cpwhrlpgstBean,User user){
		boolean saveFlag=false;
		Session session=getSession();
		
		String sql="UPDATE CP_WH_RL_PG_ST_" + cpwhrlpgstBean.getDIST_CD().substring(0,2) + 
		" set PG_PK_CODE = null, " +
		"  OPERATOR = '" + user.getUsLoginId() + "', " +
		"  MOD_DATE = sysdate " + 
		" WHERE RL_PG_ST_ID in (" + cpwhrlpgstBean.getRL_PG_ST_ID() + ")";
		
		session.createSQLQuery(sql).executeUpdate();

		saveFlag=true;
		return saveFlag;
	}
	
	@Transactional(readOnly = true)
	public boolean upzsqbd(CpwhrlpgstBean cpwhrlpgstBean,User user){
		boolean saveFlag=false;
		Session session=getSession();
		
		String sql="UPDATE CP_WH_RL_PG_ST_" + cpwhrlpgstBean.getDIST_CD().substring(0,2) + 
		" set DT_PK_CODE = '" + cpwhrlpgstBean.getDT_PK_CODE() + "'," +
		" DM_PK_CODE = '" + cpwhrlpgstBean.getDM_PK_CODE() + "'," +
		" PG_PK_CODE = '" + cpwhrlpgstBean.getPG_PK_CODE() + "'," +
		"  OPERATOR = '" + user.getUsLoginId() + "', " +
		"  MOD_DATE = sysdate " + 
		" WHERE RL_PG_ST_ID in (" + cpwhrlpgstBean.getRL_PG_ST_ID() + ")";
		
		session.createSQLQuery(sql).executeUpdate();

		saveFlag=true;
		return saveFlag;
	}
	
	@Transactional(readOnly = true)
	public boolean upfkqr(TBSRPROCESSBean tbsrprocessBean,User user){
		boolean saveFlag=false;
		Session session=getSession();
		String sql="UPDATE TB_SR_PROCESS set"
			+ "  PK_OPEID = '" + user.getUsLoginId() + "', "
			+ "  PK_OPENAME = '" + user.getUsName() + "', "
			+ "  SUSEFLAG = '1', "
			+ "  PK_TIME = sysdate " 
			+ "  WHERE SUSEFLAG = 0 and SEQID in (" + tbsrprocessBean.getSEQID() + ")";
		
		session.createSQLQuery(sql).executeUpdate();

		saveFlag=true;
		return saveFlag;
	}
	
	@Transactional(readOnly = true)
	public boolean delfkqr(TBSRPROCESSBean tbsrprocessBean,User user){
		boolean saveFlag=false;
		Session session=getSession();
		String sql="delete from TB_SR_PROCESS "
			+ "  WHERE SUSEFLAG = 0 and SEQID in (" + tbsrprocessBean.getSEQID() + ")";
		
		session.createSQLQuery(sql).executeUpdate();

		saveFlag=true;
		return saveFlag;
	}
}
