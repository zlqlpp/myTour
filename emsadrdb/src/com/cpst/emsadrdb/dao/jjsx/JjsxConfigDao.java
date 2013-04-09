package com.cpst.emsadrdb.dao.jjsx;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.cpst.core.orm.hibernate.HibernateDao;
import com.cpst.emsadrdb.entity.jjsx.JJSXBean;
import com.cpst.emsadrdb.service.jjsx.JjsxCommon;

@Repository
@Transactional
public class JjsxConfigDao extends HibernateDao<JJSXBean, String> {

	@Transactional(readOnly = true)
	public long getQueryCount(String sql) {
		List<?> listCount = getSession().createSQLQuery(sql).list();
		long totalCount = ((BigDecimal) listCount.get(0)).longValue();
		return totalCount;
	}
	
	@Transactional(readOnly = true)
	public boolean addjjsxzbpc(JJSXBean itemBean) {
		boolean saveFlag = false;
		
		Session session = getSession();
		
		String sql = "INSERT INTO CJJSX_ZB_PC " +
				"(SEQID,PCMC,PCSX,CQUSXXUS," +
						"HXJZSJ,HXJZJG,FHXJZSJ,FHXJZJG," +
						"OPEINTIME,OPEUPTIME,OPEFLAG,OPEID,OPENAME) " +
				"values(SEQ_CJJSX_ALL.nextval,'" + itemBean.getPCMC() + "','" + itemBean.getPCSX() + "','" + itemBean.getCQUSXXUS() + "'," +
				"'"  + itemBean.getHXJZSJ() + "','"  + itemBean.getHXJZJG() + "','"  + itemBean.getFHXJZSJ() + "','"  + itemBean.getFHXJZJG() + "'," +
				"to_char(sysdate,'yyyymmddhh24miss'),to_char(sysdate,'yyyymmddhh24miss'),'IN','" + itemBean.getOPEID() + "','" + itemBean.getOPENAME() + "')";
		
		
		session.createSQLQuery(sql).executeUpdate();

		saveFlag = true;
		return saveFlag;
	}
	
	@Transactional(readOnly = true)
	public boolean upjjsxzbpc(JJSXBean itemBean) {
		boolean saveFlag = false;
		
		Session session = getSession();
		
		String sql = "update CJJSX_ZB_PC set " +
				" PCMC = " + "'" + itemBean.getPCMC() + "'," +
				" HXJZSJ = " + "'" + itemBean.getHXJZSJ() + "'," +
				" HXJZJG = " + "'" + itemBean.getHXJZJG() + "'," +
				" FHXJZSJ = " + "'" + itemBean.getFHXJZSJ() + "'," +
				" FHXJZJG = " + "'" + itemBean.getFHXJZJG() + "'," +
				" README = " + "'" + itemBean.getREADME() + "'," +
				" OPEUPTIME = to_char(sysdate,'yyyymmddhh24miss')," +
				" OPEFLAG = 'UP'," +
				" OPEID = " + "'" + itemBean.getOPEID() + "'," +
				" OPENAME = " + "'" + itemBean.getOPENAME() + "'" +
						" where SEQID = "  + "'" + itemBean.getSEQID() + "'";
		
		
		session.createSQLQuery(sql).executeUpdate();
	

		saveFlag = true;
		return saveFlag;
	}
	
	@Transactional(readOnly = true)
	public boolean deljjsxzbpc(JJSXBean itemBean) {
		boolean saveFlag = false;
		
		Session session = getSession();
		
		String sql = " insert into CJJSX_SJ_ORGPC_DEL" +
				" (ORG_CODE,PCJZSJ,PCSJJG,SJSPCSEQID,README,REMARK,OPEUPTIME,OPEID,OPEREMARK,OPENAME,ADUOPFLAG,ADUUSFLAG,ADUTIME,ADUID,ADUREMARK,ADUNAME,USEFLAG,SEQID,HXFLAG,OPEINTIME,OPEFLAG,OPEDETIME) " +
				" select ORG_CODE,PCJZSJ,PCSJJG,SJSPCSEQID,README,REMARK,OPEUPTIME,OPEID,OPEREMARK,OPENAME,ADUOPFLAG,ADUUSFLAG,ADUTIME,ADUID,ADUREMARK,ADUNAME,USEFLAG,SEQID,HXFLAG,OPEINTIME,OPEFLAG,to_char(sysdate,'yyyymmddhh24miss') " +
				" from CJJSX_SJ_ORGPC where SJSPCSEQID in ( select SEQID from CJJSX_SJ_SPC  where ZBPCSEQID = '" + itemBean.getSEQID() + "')";

		session.createSQLQuery(sql).executeUpdate();
		
		sql = "delete from CJJSX_SJ_ORGPC  where SJSPCSEQID in ( select SEQID from CJJSX_SJ_SPC  where ZBPCSEQID = '" + itemBean.getSEQID() + "')";

		session.createSQLQuery(sql).executeUpdate();
		
		sql = " insert into CJJSX_SJ_SPC_DEL " +
				" (SEQID,PCMC,README,REMARK,OPEUPTIME,OPEID,OPEREMARK,OPENAME,ADUOPFLAG,ADUUSFLAG,ADUTIME,ADUID,ADUREMARK,ADUNAME,USEFLAG,HXJZSJ,HXJZJG,FHXJZSJ,FHXJZJG,ZBPCSEQID,DISTCD,OPEINTIME,OPEFLAG,OPEDETIME) " +
				" select SEQID,PCMC,README,REMARK,OPEUPTIME,OPEID,OPEREMARK,OPENAME,ADUOPFLAG,ADUUSFLAG,ADUTIME,ADUID,ADUREMARK,ADUNAME,USEFLAG,HXJZSJ,HXJZJG,FHXJZSJ,FHXJZJG,ZBPCSEQID,DISTCD,OPEINTIME,OPEFLAG,to_char(sysdate,'yyyymmddhh24miss') " +
				" from CJJSX_SJ_SPC  where ZBPCSEQID = "  + "'" + itemBean.getSEQID() + "'";

		session.createSQLQuery(sql).executeUpdate();
		
		sql = "delete from CJJSX_SJ_SPC  where ZBPCSEQID = "  + "'" + itemBean.getSEQID() + "'";

		session.createSQLQuery(sql).executeUpdate();
		
		sql = " insert into CJJSX_ZB_PC_DEL " +
		" (SEQID ,PCMC,README,PCSX,REMARK,OPEUPTIME,OPEID,OPEREMARK,OPENAME,ADUOPFLAG,ADUUSFLAG,ADUTIME,ADUID,ADUREMARK,ADUNAME,USEFLAG,CQUSXXUS,HXJZSJ,HXJZJG,FHXJZSJ,FHXJZJG,OPEINTIME,OPEFLAG,OPEDETIME) " +
		" select SEQID ,PCMC,README,PCSX,REMARK,OPEUPTIME,OPEID,OPEREMARK,OPENAME,ADUOPFLAG,ADUUSFLAG,ADUTIME,ADUID,ADUREMARK,ADUNAME,USEFLAG,CQUSXXUS,HXJZSJ,HXJZJG,FHXJZSJ,FHXJZJG,OPEINTIME,OPEFLAG,to_char(sysdate,'yyyymmddhh24miss') " +
		" from CJJSX_ZB_PC a where SEQID = "  + "'" + itemBean.getSEQID() + "'";

		session.createSQLQuery(sql).executeUpdate();
		
		sql = "delete from CJJSX_ZB_PC  where SEQID = "  + "'" + itemBean.getSEQID() + "'";

		session.createSQLQuery(sql).executeUpdate();
	

		saveFlag = true;
		return saveFlag;
	}
	
	@Transactional(readOnly = true)
	public boolean addjjsxsjspc(JJSXBean itemBean) {
		boolean saveFlag = false;
		
		Session session = getSession();
		
		itemBean.setDISTNAME(itemBean.getDISTNAME().replaceAll("市", ""));
		
		String sql = "INSERT INTO CJJSX_SJ_SPC " +
				"(ZBPCSEQID,SEQID,PCMC,DISTCD," +
						"OPEINTIME,OPEUPTIME,OPEFLAG,OPEID,OPENAME) " +
				" select SEQID,SEQ_CJJSX_ALL.nextval,'" + itemBean.getDISTNAME() + "','" + itemBean.getDISTCD() + "'," +
						"to_char(sysdate,'yyyymmddhh24miss'),to_char(sysdate,'yyyymmddhh24miss'),'IN','" + itemBean.getOPEID() + "','" + itemBean.getOPENAME() + "'" +
								" from CJJSX_ZB_PC " +
								" where SEQID  in (" + itemBean.getSEQIDS() + ")" +
								" and SEQID not in (" +
								" select ZBPCSEQID from  CJJSX_SJ_SPC where DISTCD = '" + itemBean.getDISTCD() + "'" +
								" ) ";
		
		
		session.createSQLQuery(sql).executeUpdate();
		

		saveFlag = true;
		return saveFlag;
	}
	
	@Transactional(readOnly = true)
	public boolean upjjsxsjspc(JJSXBean itemBean) {
		boolean saveFlag = false;
		
		Session session = getSession();
		
		String sql = "update CJJSX_SJ_SPC set " +
			" PCMC = " + "'" + itemBean.getPCMC() + "'," +
			" README = " + "'" + itemBean.getREADME() + "'," +
			" OPEUPTIME = to_char(sysdate,'yyyymmddhh24miss')," +
			" OPEFLAG = 'UP'," +
			" OPEID = " + "'" + itemBean.getOPEID() + "'," +
			" OPENAME = " + "'" + itemBean.getOPENAME() + "'" +
				" where SEQID  = "  + "'" + itemBean.getSEQID() + "'";
		
		session.createSQLQuery(sql).executeUpdate();
		

		saveFlag = true;
		return saveFlag;
	}
	
	@Transactional(readOnly = true)
	public boolean deljjsxsjspc(JJSXBean itemBean) {
		boolean saveFlag = false;
		
		Session session = getSession();
		
		String sql = " insert into CJJSX_SJ_ORGPC_DEL" +
		" (ORG_CODE,PCJZSJ,PCSJJG,SJSPCSEQID,README,REMARK,OPEUPTIME,OPEID,OPEREMARK,OPENAME,ADUOPFLAG,ADUUSFLAG,ADUTIME,ADUID,ADUREMARK,ADUNAME,USEFLAG,SEQID,HXFLAG,OPEINTIME,OPEFLAG,OPEDETIME) " +
		" select ORG_CODE,PCJZSJ,PCSJJG,SJSPCSEQID,README,REMARK,OPEUPTIME,OPEID,OPEREMARK,OPENAME,ADUOPFLAG,ADUUSFLAG,ADUTIME,ADUID,ADUREMARK,ADUNAME,USEFLAG,SEQID,HXFLAG,OPEINTIME,OPEFLAG,to_char(sysdate,'yyyymmddhh24miss') " +
		" from CJJSX_SJ_ORGPC a where SJSPCSEQID in ( select SEQID from CJJSX_SJ_SPC  where SEQID = '" + itemBean.getSEQID() + "'  )";

		session.createSQLQuery(sql).executeUpdate();
		
		sql = "delete from CJJSX_SJ_ORGPC  where SJSPCSEQID in ( select SEQID from CJJSX_SJ_SPC  where SEQID = '" + itemBean.getSEQID() + "'  )";

		session.createSQLQuery(sql).executeUpdate();
		
		sql = " insert into CJJSX_SJ_SPC_DEL " +
		" (SEQID,PCMC,README,REMARK,OPEUPTIME,OPEID,OPEREMARK,OPENAME,ADUOPFLAG,ADUUSFLAG,ADUTIME,ADUID,ADUREMARK,ADUNAME,USEFLAG,HXJZSJ,HXJZJG,FHXJZSJ,FHXJZJG,ZBPCSEQID,DISTCD,OPEINTIME,OPEFLAG,OPEDETIME) " +
		" select SEQID,PCMC,README,REMARK,OPEUPTIME,OPEID,OPEREMARK,OPENAME,ADUOPFLAG,ADUUSFLAG,ADUTIME,ADUID,ADUREMARK,ADUNAME,USEFLAG,HXJZSJ,HXJZJG,FHXJZSJ,FHXJZJG,ZBPCSEQID,DISTCD,OPEINTIME,OPEFLAG,to_char(sysdate,'yyyymmddhh24miss') " +
		" from CJJSX_SJ_SPC a where SEQID = '" + itemBean.getSEQID() + "'";

		session.createSQLQuery(sql).executeUpdate();
		
		sql = "delete from CJJSX_SJ_SPC where SEQID = '" + itemBean.getSEQID() + "'";
		
		session.createSQLQuery(sql).executeUpdate();
		

		saveFlag = true;
		return saveFlag;
	}
	
	@Transactional(readOnly = true)
	public boolean addjjsxqxzyzjpc(JJSXBean itemBean) {
		boolean saveFlag = false;
		
		Session session = getSession();		

		String sql = "INSERT INTO CJJSX_SJ_ORGPC " +
				"(SEQID,ORG_CODE,SJSPCSEQID,HXFLAG," +
						"OPEINTIME,OPEUPTIME,OPEFLAG,OPEID,OPENAME) " +
				" select SEQ_CJJSX_ALL.nextval,ORG_CODE,'" + itemBean.getSJSPCSEQID() + "','" + itemBean.getHXFLAG() + "'," +
						"to_char(sysdate,'yyyymmddhh24miss'),to_char(sysdate,'yyyymmddhh24miss'),'IN','" + itemBean.getOPEID() + "','" + itemBean.getOPENAME() + "'" +
								" from RES_ORG " +
								" where ORG_CODE  in ('" + itemBean.getSEQIDS().replaceAll(",", "','") + "')" +
								" and ORG_CODE not in (" +
								" select ORG_CODE from  CJJSX_SJ_ORGPC" +
								" where SJSPCSEQID =  '" + itemBean.getSJSPCSEQID() + "'" +
								" ) ";
		
		
		session.createSQLQuery(sql).executeUpdate();

		saveFlag = true;
		return saveFlag;
	}
	
	@Transactional(readOnly = true)
	public boolean upjjsxqxzyzjpc(JJSXBean itemBean) {
		boolean saveFlag = false;
		
		Session session = getSession();
		
		String sql = "update CJJSX_SJ_ORGPC set " +
		" OPEUPTIME = to_char(sysdate,'yyyymmddhh24miss')," +
		" OPEFLAG = 'UP'," +
		" OPEID = " + "'" + itemBean.getOPEID() + "'," +
		" OPENAME = " + "'" + itemBean.getOPENAME() + "'," +
		" HXFLAG = " + "'" + itemBean.getHXFLAG() + "'," +
		" README = " + "'" + itemBean.getREADME() + "'" +
			" where SEQID  = "  + "'" + itemBean.getSEQID() + "'";
		
		session.createSQLQuery(sql).executeUpdate();
		

		saveFlag = true;
		return saveFlag;
	}
	
	@Transactional(readOnly = true)
	public boolean deljjsxqxzyzjpc(JJSXBean itemBean) {
		boolean saveFlag = false;
		
		Session session = getSession();		

		String sql = " insert into CJJSX_SJ_ORGPC_DEL" +
		" (ORG_CODE,PCJZSJ,PCSJJG,SJSPCSEQID,README,REMARK,OPEUPTIME,OPEID,OPEREMARK,OPENAME,ADUOPFLAG,ADUUSFLAG,ADUTIME,ADUID,ADUREMARK,ADUNAME,USEFLAG,SEQID,HXFLAG,OPEINTIME,OPEFLAG,OPEDETIME) " +
		" select ORG_CODE,PCJZSJ,PCSJJG,SJSPCSEQID,README,REMARK,OPEUPTIME,OPEID,OPEREMARK,OPENAME,ADUOPFLAG,ADUUSFLAG,ADUTIME,ADUID,ADUREMARK,ADUNAME,USEFLAG,SEQID,HXFLAG,OPEINTIME,OPEFLAG,to_char(sysdate,'yyyymmddhh24miss') " +
		" from CJJSX_SJ_ORGPC a where SEQID  in (" + itemBean.getSEQIDS() + ")" ;

		session.createSQLQuery(sql).executeUpdate();
		
		sql = "delete from CJJSX_SJ_ORGPC where SEQID  in (" + itemBean.getSEQIDS() + ")" ;

		session.createSQLQuery(sql).executeUpdate();

		saveFlag = true;
		return saveFlag;
	}
	
}
