package com.cpst.emsadrdb.dao.clfw;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.cpst.core.orm.hibernate.HibernateDao;
import com.cpst.emsadrdb.entity.clfw.CLFWBean;

@Repository
@Transactional
public class ClfwConfigDao extends HibernateDao<CLFWBean, String> {

	@Transactional(readOnly = true)
	public long getQueryCount(String sql) {
		List<?> listCount = getSession().createSQLQuery(sql).list();
		long totalCount = ((BigDecimal) listCount.get(0)).longValue();
		return totalCount;
	}
	
	@Transactional(readOnly = true)
	public boolean addclfwpc(CLFWBean clfwBean) {
		boolean saveFlag = false;
		Session session = getSession();
		String sql = "INSERT INTO CLFW_CLFWPC "
				+ "(CLFWPC_SEQID,CLFWPC_MC,CLFWPC_SM,CLFWPC_SX,OPE_INSERTTIME,OPE_REMARK,OPE_OPEID,OPE_OPENAME) "
				+ "values(SEQ_CLFW_ALL.nextval,'" + clfwBean.getCLFWPC_MC() + "','" + clfwBean.getCLFWPC_SM() + "','"
				+ clfwBean.getCLFWPC_SX() + "',"
				+ "to_char(sysdate,'yyyymmddhh24miss'),'" + clfwBean.getOPE_REMARK() + "','" + clfwBean.getOPE_OPEID() + "','" + clfwBean.getOPE_OPENAME() + "'" + ")";
		session.createSQLQuery(sql).executeUpdate();

		saveFlag = true;
		return saveFlag;
	}

	@Transactional(readOnly = true)
	public boolean upclfwpc(CLFWBean clfwBean) {
		boolean saveFlag = false;

		Session session = getSession();

		String sql = "UPDATE CLFW_CLFWPC set" + 
				"  CLFWPC_MC = '" + clfwBean.getCLFWPC_MC() + "', " + 
				"  CLFWPC_SM = '" + clfwBean.getCLFWPC_SM() + "', " + 
				"  CLFWPC_SX = '" + clfwBean.getCLFWPC_SX() + "', " + 
				"  OPE_INSERTTIME = to_char(sysdate,'yyyymmddhh24miss'), " + 
				"  OPE_REMARK = '" + clfwBean.getOPE_REMARK() + "', " + 
				"  OPE_OPEID = '" + clfwBean.getOPE_OPEID() + "', " + 
				"  OPE_OPENAME = '" + clfwBean.getOPE_OPENAME() + "'" + 
				"  WHERE CLFWPC_SEQID = " + clfwBean.getCLFWPC_SEQID();

		session.createSQLQuery(sql).executeUpdate();

		saveFlag = true;
		return saveFlag;
	}

	@Transactional(readOnly = true)
	public boolean delclfwpc(CLFWBean clfwBean) {
		boolean saveFlag = false;

		Session session = getSession();
		
		String sql = "";
		
		sql = "select CLFWPC_SEQID,CLFWPC_SX from CLFW_CLFWPC WHERE CLFWPC_SEQID = '" + clfwBean.getCLFWPC_SEQID() + "'";
		
		String tCLFWPC_SX=""; 
		
		List<?> list = getSession().createSQLQuery(sql).list();
		Iterator<?> it = list.iterator();
		if(it.hasNext()){
			Object[] obj = (Object[]) it.next();
			tCLFWPC_SX = obj[1].toString();
		}
		
		if(tCLFWPC_SX.length()>0){
			if(tCLFWPC_SX.equals("1")){
				
				sql = "update CLFW_CLFWPC_SHI set" +
						" CLFWPCSHENG_SEQID = null " +
						" WHERE CLFWPCSHENG_SEQID in " +
						" ( " +
							" select CLFWPCSHENG_SEQID from CLFW_CLFWPC_SHENG WHERE CLFWPC_SEQID = '" + clfwBean.getCLFWPC_SEQID() + "'" +
						" )";

				session.createSQLQuery(sql).executeUpdate();
				
				sql = "delete from CLFW_CLFWPC_SHENG WHERE CLFWPC_SEQID = '" + clfwBean.getCLFWPC_SEQID() + "'";

				session.createSQLQuery(sql).executeUpdate();
				
			}else if(tCLFWPC_SX.equals("2") || tCLFWPC_SX.equals("3")){
				
				sql = "delete from CLFW_CLFWTDD "+
				" WHERE CLFWTDB_SEQID in " +
				" ( " +
					" select CLFWTDB_SEQID from CLFW_CLFWTDB a,CLFW_CLFWPC_SHI b WHERE a.CLFWPCSHI_SEQID = b.CLFWPCSHI_SEQID and" +
					" b.CLFWPC_SEQID = '" + clfwBean.getCLFWPC_SEQID() + "'" +
				" )";
				
				session.createSQLQuery(sql).executeUpdate();
				
				sql = "delete from CLFW_CLFWTDB "+
						" WHERE CLFWPCSHI_SEQID in " +
						" ( " +
							" select CLFWPCSHI_SEQID from CLFW_CLFWPC_SHI WHERE CLFWPC_SEQID = '" + clfwBean.getCLFWPC_SEQID() + "'" +
						" )";
				
				session.createSQLQuery(sql).executeUpdate();
				
				sql = "delete from clfw_resorg_pz "+
				" WHERE CLFWPCSHI_SEQID in " +
				" ( " +
					" select CLFWPCSHI_SEQID from CLFW_CLFWPC_SHI WHERE CLFWPC_SEQID = '" + clfwBean.getCLFWPC_SEQID() + "'" +
				" )";
		
				session.createSQLQuery(sql).executeUpdate();
				
				sql = "delete from CLFW_CLFWPC_SHI WHERE CLFWPC_SEQID = '" + clfwBean.getCLFWPC_SEQID() + "'";

				session.createSQLQuery(sql).executeUpdate();
			}
			
			sql = "delete from CLFW_CLFWPC WHERE CLFWPC_SEQID = '" + clfwBean.getCLFWPC_SEQID() + "'";

			session.createSQLQuery(sql).executeUpdate();
		}
		
		saveFlag = true;
		return saveFlag;
	}
	
	@Transactional(readOnly = true)
	public boolean addclfwpcsheng(CLFWBean clfwBean) {
		boolean saveFlag = false;

		Session session = getSession();

		String sql = "INSERT INTO clfw_clfwpc_sheng "
			+ "(CLFWPCSHENG_SEQID,CLFWPC_SEQID,CLFWPCSHENG_DISTCD,CLFWPCSHENG_SHENGM,CLFWPCSHENG_SM,OPE_INSERTTIME,OPE_OPEID,OPE_OPENAME,CLFWPCSHENG_JGDM) "
			+ " values(SEQ_CLFW_ALL.nextval,'" + clfwBean.getCLFWPC_SEQID() + "','" + clfwBean.getCLFWPCSHENG_DISTCD() + "','" + clfwBean.getCLFWPCSHENG_SHENGM() + "',"
			+ "'" + clfwBean.getCLFWPCSHENG_SM() + "',to_char(sysdate,'yyyymmddhh24miss'),'" + clfwBean.getOPE_OPEID() + "','" + clfwBean.getOPE_OPENAME() + "',"
			+ "'" + clfwBean.getCLFWPCSHENG_JGDM() + "'" + ")";

		session.createSQLQuery(sql).executeUpdate();
		
		sql = "UPDATE clfw_clfwpc_sheng set" + 
		"  CLFWPCSHENG_JGDM = '" + clfwBean.getCLFWPCSHENG_JGDM() + "' " +
		"  WHERE CLFWPCSHENG_SHENGM = '" + clfwBean.getCLFWPCSHENG_SHENGM() + "' and CLFWPCSHENG_DISTCD = '" + clfwBean.getCLFWPCSHENG_DISTCD() + "'";

		session.createSQLQuery(sql).executeUpdate();
		
		saveFlag = true;
		return saveFlag;
	}
	
	@Transactional(readOnly = true)
	public boolean upclfwpcsheng(CLFWBean clfwBean) {
		boolean saveFlag = false;
		
		Session session = getSession();

		String sql = "UPDATE clfw_clfwpc_sheng set" + 
		"  CLFWPC_SEQID = '" + clfwBean.getCLFWPC_SEQID() + "', " + 
		"  CLFWPCSHENG_DISTCD = '" + clfwBean.getCLFWPCSHENG_DISTCD() + "', " + 
		"  CLFWPCSHENG_SHENGM = '" + clfwBean.getCLFWPCSHENG_SHENGM() + "', " + 
		"  CLFWPCSHENG_JGDM = '" + clfwBean.getCLFWPCSHENG_JGDM() + "', " + 
		"  CLFWPCSHENG_SM = '" + clfwBean.getCLFWPCSHENG_SM() + "', " + 
		"  OPE_INSERTTIME = to_char(sysdate,'yyyymmddhh24miss'), " + 
		"  OPE_OPEID = '" + clfwBean.getOPE_OPEID() + "', " + 
		"  OPE_OPENAME = '" + clfwBean.getOPE_OPENAME() + "'" + 
		"  WHERE CLFWPCSHENG_SEQID = '" + clfwBean.getCLFWPCSHENG_SEQID() + "'";

		session.createSQLQuery(sql).executeUpdate();
		
		sql = "UPDATE clfw_clfwpc_sheng set" + 
		"  CLFWPCSHENG_JGDM = '" + clfwBean.getCLFWPCSHENG_JGDM() + "' " +
		"  WHERE CLFWPCSHENG_SHENGM = '" + clfwBean.getCLFWPCSHENG_SHENGM() + "' and CLFWPCSHENG_DISTCD = '" + clfwBean.getCLFWPCSHENG_DISTCD() + "'";

		session.createSQLQuery(sql).executeUpdate();

		saveFlag = true;
		return saveFlag;
	}
	
	@Transactional(readOnly = true)
	public boolean delclfwpcsheng(CLFWBean clfwBean) {
		boolean saveFlag = false;

		Session session = getSession();
		
		String sql = "";
		
		
		sql = "update CLFW_CLFWPC_SHI set" +
		" CLFWPCSHENG_SEQID = null " +
		" WHERE CLFWPCSHENG_SEQID = '" + clfwBean.getCLFWPCSHENG_SEQID() + "'";

		session.createSQLQuery(sql).executeUpdate();
	
		sql = "delete from clfw_clfwpc_sheng " +
				" WHERE CLFWPCSHENG_SEQID = '" + clfwBean.getCLFWPCSHENG_SEQID() + "'";

		session.createSQLQuery(sql).executeUpdate();

		saveFlag = true;
		return saveFlag;
	}
	
	@Transactional(readOnly = true)
	public boolean addclfwpcshi(CLFWBean clfwBean) {
		boolean saveFlag = false;

		Session session = getSession();

		
		String sql = "INSERT INTO CLFW_CLFWPC_SHI "
			+ "(CLFWPCSHI_SEQID,CLFWPCSHENG_SEQID,CLFWPC_SEQID,CLFWPCSHI_DISTCD,CLFWPCSHI_SHIGM,CLFWPCSHI_SM,OPE_INSERTTIME,OPE_OPEID,OPE_OPENAME,CLFWPCSHI_JZSJ,CLFWPCSHI_JZYH) "
			+ " values(SEQ_CLFW_ALL.nextval,'" + clfwBean.getCLFWPCSHENG_SEQID() + "','" + clfwBean.getCLFWPC_SEQID() + "','" + clfwBean.getCLFWPCSHI_DISTCD() + "','" + clfwBean.getCLFWPCSHI_SHIGM() + "',"
			+ "'" + clfwBean.getCLFWPCSHI_SM() + "',to_char(sysdate,'yyyymmddhh24miss'),'" + clfwBean.getOPE_OPEID() + "','" + clfwBean.getOPE_OPENAME() + "',"
			+ "'" + clfwBean.getCLFWPCSHI_JZSJ() + "','" + clfwBean.getCLFWPCSHI_JZYH() +  "'" + ")";

		session.createSQLQuery(sql).executeUpdate();
		
		
		saveFlag = true;
		return saveFlag;
	}
	
	@Transactional(readOnly = true)
	public boolean upclfwpcshi(CLFWBean clfwBean) {
		boolean saveFlag = false;
		
		Session session = getSession();
		
		
		String sql = "UPDATE CLFW_CLFWPC_SHI set" + 
		"  CLFWPCSHENG_SEQID = '" + clfwBean.getCLFWPCSHENG_SEQID() + "', " + 
		"  CLFWPC_SEQID = '" + clfwBean.getCLFWPC_SEQID() + "', " + 
		"  CLFWPCSHI_DISTCD = '" + clfwBean.getCLFWPCSHI_DISTCD() + "', " + 
		"  CLFWPCSHI_SHIGM = '" + clfwBean.getCLFWPCSHI_SHIGM() + "', " + 
		"  CLFWPCSHI_JZSJ = '" + clfwBean.getCLFWPCSHI_JZSJ() + "', " + 
		"  CLFWPCSHI_JZYH = '" + clfwBean.getCLFWPCSHI_JZYH() + "', " + 
		"  CLFWPCSHI_SM = '" + clfwBean.getCLFWPCSHI_SM() + "', " + 
		"  OPE_INSERTTIME = to_char(sysdate,'yyyymmddhh24miss'), " + 
		"  OPE_OPEID = '" + clfwBean.getOPE_OPEID() + "', " + 
		"  OPE_OPENAME = '" + clfwBean.getOPE_OPENAME() + "'" + 
		"  WHERE CLFWPCSHI_SEQID = '" + clfwBean.getCLFWPCSHI_SEQID() + "'";

		session.createSQLQuery(sql).executeUpdate();
		

		saveFlag = true;
		return saveFlag;
	}
	
	@Transactional(readOnly = true)
	public boolean delclfwpcshi(CLFWBean clfwBean) {
		boolean saveFlag = false;

		Session session = getSession();
		
		String sql = "";
		
		sql = "delete from CLFW_CLFWTDD "+
		" WHERE CLFWTDB_SEQID in " +
		" ( " +
			" select CLFWTDB_SEQID from CLFW_CLFWTDB WHERE CLFWPCSHI_SEQID = '" + clfwBean.getCLFWPCSHI_SEQID() + "'" +
		" )";
		
		session.createSQLQuery(sql).executeUpdate();
		
		sql = "delete from CLFW_CLFWTDB "+
				" WHERE CLFWPCSHI_SEQID = '" + clfwBean.getCLFWPCSHI_SEQID() + "'";
		
		session.createSQLQuery(sql).executeUpdate();
		
		sql = "delete from clfw_resorg_pz "+
		" WHERE CLFWPCSHI_SEQID = '" + clfwBean.getCLFWPCSHI_SEQID() + "'";

		session.createSQLQuery(sql).executeUpdate();
	
		sql = "delete from CLFW_CLFWPC_SHI " +
				" WHERE CLFWPCSHI_SEQID = '" + clfwBean.getCLFWPCSHI_SEQID() + "'";

		session.createSQLQuery(sql).executeUpdate();

		saveFlag = true;
		return saveFlag;
	}
	
	@Transactional(readOnly = true)
	public boolean delclfwtdbcq(CLFWBean clfwBean){
		boolean saveFlag=false;
		
		Session session=getSession();
		
		String sql="delete from CLFW_CLFWTDBCQ "
			+ "  WHERE CLFWTDBCQ_SEQID = '" + clfwBean.getCLFWTDBCQ_SEQID() + "'";
		
		session.createSQLQuery(sql).executeUpdate();
		
		saveFlag=true;
		return saveFlag;
	}

	@Transactional(readOnly = true)
	public boolean addclfwtdbcq(CLFWBean clfwBean) {
		boolean saveFlag = false;

		Session session = getSession();

		String sql = "INSERT INTO CLFW_CLFWTDBCQ "
			+ "(CLFWTDBCQ_SEQID,DM_PK_CODE,CLFWTDB_DISTCD,CLFWTDB_FW,OPE_INSERTTIME,OPE_OPEID,OPE_OPENAME) "
			+ " select SEQ_CLFW_ALL.nextval,DM_PK_CODE,'" + clfwBean.getCLFWTDB_DISTCD() + "','" + clfwBean.getCLFWTDB_FW() + "',"
			+ "to_char(sysdate,'yyyymmddhh24miss'),'" + clfwBean.getOPE_OPEID() + "','" + clfwBean.getOPE_OPENAME() + "'" + 
			" from CP_WH_DEPARTMENT where DM_PK_CODE = '" + clfwBean.getDM_PK_CODE() + "'"  +
					" and DM_PK_CODE not in ( " +
						"select DM_PK_CODE from CLFW_CLFWTDBCQ where " +
						" DM_PK_CODE = '" + clfwBean.getDM_PK_CODE() + "' " +
						" and CLFWTDB_DISTCD = '" + clfwBean.getCLFWTDB_DISTCD() + "'" +
					")";

		session.createSQLQuery(sql).executeUpdate();
		

		saveFlag = true;
		return saveFlag;
	}
	
	@Transactional(readOnly = true)
	public boolean upclfwtdbcq(CLFWBean clfwBean) {
		boolean saveFlag = false;
		
		Session session = getSession();
		
		String sql = "UPDATE CLFW_CLFWTDBCQ set" + 
		"  CLFWTDB_DISTCD = '" + clfwBean.getCLFWTDB_DISTCD() + "', " + 
		"  CLFWTDB_FW = '" + clfwBean.getCLFWTDB_FW() + "', " + 
		"  OPE_INSERTTIME = to_char(sysdate,'yyyymmddhh24miss'), " + 
		"  OPE_REMARK = '" + clfwBean.getOPE_REMARK() + "', " + 
		"  OPE_OPEID = '" + clfwBean.getOPE_OPEID() + "', " + 
		"  OPE_OPENAME = '" + clfwBean.getOPE_OPENAME() + "'" + 
		"  WHERE CLFWTDBCQ_SEQID = '" + clfwBean.getCLFWTDBCQ_SEQID() + "'";

		session.createSQLQuery(sql).executeUpdate();

		saveFlag = true;
		return saveFlag;
	}
	
	@Transactional(readOnly = true)
	public boolean addclfwtdb(CLFWBean clfwBean){
		boolean saveFlag=false;
		Session session=getSession();
		
		
		String sql=" INSERT INTO clfw_clfwtdb "
			+ " (CLFWTDB_SEQID,DM_PK_CODE,CLFWPCSHI_SEQID,CLFWTDB_CLSX,CLFWTDB_DISTCD,OPE_INSERTTIME,OPE_OPEID,OPE_OPENAME,CLFWTDB_SHSX) "
			+ " select SEQ_CLFW_ALL.nextval,DM_PK_CODE,'" + clfwBean.getCLFWPCSHI_SEQID() +"','" + clfwBean.getCLFWTDB_CLSX() + "','" + clfwBean.getCLFWTDB_DISTCD() +"',"
			+ " to_char(sysdate,'yyyymmddhh24miss'),'" + clfwBean.getOPE_OPEID() + "','" + clfwBean.getOPE_OPENAME() + "','" + clfwBean.getCLFWTDB_SHSX() + "' "
			+ " from CP_WH_DEPARTMENT where DM_PK_CODE in " + clfwBean.getDM_PK_CODE();
		session.createSQLQuery(sql).executeUpdate();
		
				
		saveFlag=true;
		return saveFlag;
	}
	
	@Transactional(readOnly = true)
	public boolean upclfwtdb(CLFWBean clfwBean){
		boolean saveFlag=false;
		
		Session session=getSession();
		
		String sql="UPDATE clfw_clfwtdb set"
		+ "  CLFWTDB_SJYXSC = '" + clfwBean.getCLFWTDB_SJYXSC() + "', "
		+ "  CLFWTDB_LSLZB = '" + clfwBean.getCLFWTDB_LSLZB() + "', "
		+ "  CLFWTDB_SM = '" + clfwBean.getCLFWTDB_SM() + "', "
		+ "  OPE_INSERTTIME = to_char(sysdate,'yyyymmddhh24miss'), "
		+ "  OPE_REMARK = '" + clfwBean.getOPE_REMARK() + "', "
		+ "  OPE_OPEID = '" + clfwBean.getOPE_OPEID() + "', "
		+ "  OPE_OPENAME = '" + clfwBean.getOPE_OPENAME() + "'"
		+ "  WHERE CLFWTDB_SEQID = '" + clfwBean.getCLFWTDB_SEQID() + "'";
		
		session.createSQLQuery(sql).executeUpdate();
		
		saveFlag=true;
		return saveFlag;
	}
	
	@Transactional(readOnly = true)
	public boolean bjclfwtdb(CLFWBean clfwBean){
		boolean saveFlag=false;
		
		Session session=getSession();
		
		String sql=" ";
		
		if(clfwBean.getCLFWTDB_CLSX().equals("1")){

			sql="UPDATE clfw_clfwtdb set"
			+ "  CLFWTDB_SHSX = '1', " 
			+ "  CLFWTDB_CLSX = '" + clfwBean.getCLFWTDB_CLSX() + "', "
			+ "  CLFWTDB_SHCZ = null, "
			+ "  OPE_INSERTTIME = to_char(sysdate,'yyyymmddhh24miss'), "
			+ "  OPE_OPEID = '" + clfwBean.getOPE_OPEID() + "', "
			+ "  OPE_OPENAME = '" + clfwBean.getOPE_OPENAME() + "'"
			+ "  WHERE CLFWTDB_SEQID in (" + clfwBean.getCLFWTDB_SEQID() + ")";
			
			session.createSQLQuery(sql).executeUpdate();
			
			sql="delete from clfw_clfwtdd "
				+ "  WHERE CLFWTDB_SEQID in (" + clfwBean.getCLFWTDB_SEQID() + ")";
			
			session.createSQLQuery(sql).executeUpdate();
			
		}else if(clfwBean.getCLFWTDB_CLSX().equals("0")){

			sql="UPDATE clfw_clfwtdb set"
			+ "  CLFWTDB_SHSX = '1', "
			+ "  CLFWTDB_CLSX = '" + clfwBean.getCLFWTDB_CLSX() + "', "
			+ "  OPE_INSERTTIME = to_char(sysdate,'yyyymmddhh24miss'), "
			+ "  OPE_OPEID = '" + clfwBean.getOPE_OPEID() + "', "
			+ "  OPE_OPENAME = '" + clfwBean.getOPE_OPENAME() + "'"
			+ "  WHERE CLFWTDB_CLSX <> 1 and CLFWTDB_SEQID in (" + clfwBean.getCLFWTDB_SEQID() + ")";
			
			session.createSQLQuery(sql).executeUpdate();
			
			sql="UPDATE clfw_clfwtdb set"
				+ "  CLFWTDB_SHSX = '0', "
				+ "  CLFWTDB_SHCZ = '1', "
				+ "  OPE_INSERTTIME = to_char(sysdate,'yyyymmddhh24miss'), "
				+ "  OPE_OPEID = '" + clfwBean.getOPE_OPEID() + "', "
				+ "  OPE_OPENAME = '" + clfwBean.getOPE_OPENAME() + "'"
				+ "  WHERE CLFWTDB_CLSX = 1 and CLFWTDB_SEQID in (" + clfwBean.getCLFWTDB_SEQID() + ")";
				
			session.createSQLQuery(sql).executeUpdate();
			
			sql="delete from clfw_clfwtdd "
					+ "  WHERE CLFWTDB_SEQID in (" + clfwBean.getCLFWTDB_SEQID() + ")";
			
			session.createSQLQuery(sql).executeUpdate();
			
		}else if(clfwBean.getCLFWTDB_CLSX().equals("2")){

			sql="UPDATE clfw_clfwtdb set"
			+ "  CLFWTDB_SHSX = '1', "
			+ "  CLFWTDB_CLSX = '" + clfwBean.getCLFWTDB_CLSX() + "', "
			+ "  OPE_INSERTTIME = to_char(sysdate,'yyyymmddhh24miss'), "
			+ "  OPE_OPEID = '" + clfwBean.getOPE_OPEID() + "', "
			+ "  OPE_OPENAME = '" + clfwBean.getOPE_OPENAME() + "'"
			+ "  WHERE CLFWTDB_CLSX <> 1 and CLFWTDB_SEQID in (" + clfwBean.getCLFWTDB_SEQID() + ")";
			
			session.createSQLQuery(sql).executeUpdate();
			
			sql="UPDATE clfw_clfwtdb set"
				+ "  CLFWTDB_SHSX = '0', "
				+ "  CLFWTDB_SHCZ = '2', "
				+ "  OPE_INSERTTIME = to_char(sysdate,'yyyymmddhh24miss'), "
				+ "  OPE_OPEID = '" + clfwBean.getOPE_OPEID() + "', "
				+ "  OPE_OPENAME = '" + clfwBean.getOPE_OPENAME() + "'"
				+ "  WHERE CLFWTDB_CLSX = 1 and CLFWTDB_SEQID in (" + clfwBean.getCLFWTDB_SEQID() + ")";
				
				session.createSQLQuery(sql).executeUpdate();
			
		}
		
		saveFlag=true;
		return saveFlag;
	}
	
	@Transactional(readOnly = true)
	public boolean shclfwtdb(CLFWBean clfwBean){
		boolean saveFlag=false;
		
		Session session=getSession();
		
		String sql=" ";
		
		if(clfwBean.getCLFWTDB_SHSX().equals("1")){

			sql="UPDATE clfw_clfwtdb set"
			+ "  CLFWTDB_SHSX = '1', " 
			+ "  CLFWTDB_SHCZ = null, "
			+ "  CLFWTDB_CLSX = '0', "
			+ "  OPE_INSERTTIME = to_char(sysdate,'yyyymmddhh24miss'), "
			+ "  OPE_OPEID = '" + clfwBean.getOPE_OPEID() + "', "
			+ "  OPE_OPENAME = '" + clfwBean.getOPE_OPENAME() + "'"
			+ "  WHERE CLFWTDB_SHCZ = '1' and CLFWTDB_SEQID in (" + clfwBean.getCLFWTDB_SEQID() + ")";
			
			session.createSQLQuery(sql).executeUpdate();
			
			sql="UPDATE clfw_clfwtdb set"
				+ "  CLFWTDB_SHSX = '1', " 
				+ "  CLFWTDB_SHCZ = null, "
				+ "  CLFWTDB_CLSX = '2', "
				+ "  OPE_INSERTTIME = to_char(sysdate,'yyyymmddhh24miss'), "
				+ "  OPE_OPEID = '" + clfwBean.getOPE_OPEID() + "', "
				+ "  OPE_OPENAME = '" + clfwBean.getOPE_OPENAME() + "'"
				+ "  WHERE CLFWTDB_SHCZ = '2' and CLFWTDB_SEQID in (" + clfwBean.getCLFWTDB_SEQID() + ")";
			
			session.createSQLQuery(sql).executeUpdate();
			
			sql="delete from clfw_clfwtdb "
				+ "  WHERE CLFWTDB_SHCZ = '0' and CLFWTDB_SEQID in (" + clfwBean.getCLFWTDB_SEQID() + ")";
			
			session.createSQLQuery(sql).executeUpdate();
			
			sql="delete from clfw_clfwtdd "
				+ "  WHERE CLFWTDB_SEQID in (" + clfwBean.getCLFWTDB_SEQID() + ")";
			
			session.createSQLQuery(sql).executeUpdate();
			
		}else if(clfwBean.getCLFWTDB_SHSX().equals("2")){

				sql="UPDATE clfw_clfwtdb set"
				+ "  CLFWTDB_SHSX = '2', " 
				+ "  OPE_INSERTTIME = to_char(sysdate,'yyyymmddhh24miss'), "
				+ "  OPE_OPEID = '" + clfwBean.getOPE_OPEID() + "', "
				+ "  OPE_OPENAME = '" + clfwBean.getOPE_OPENAME() + "'"
				+ "  WHERE CLFWTDB_SEQID in (" + clfwBean.getCLFWTDB_SEQID() + ")";
				
				session.createSQLQuery(sql).executeUpdate();
				
			
		}
		
		saveFlag=true;
		return saveFlag;
	}
	
	@Transactional(readOnly = true)
	public boolean delclfwtdb(CLFWBean clfwBean){
		boolean saveFlag=false;
		
		Session session=getSession();
		
		String sql="delete from clfw_clfwtdd "
			+ "  WHERE CLFWTDB_SEQID in (" +
					" select CLFWTDB_SEQID from clfw_clfwtdb "
			+ "  WHERE  CLFWTDB_CLSX <> 1 and CLFWTDB_SEQID in (" + clfwBean.getCLFWTDB_SEQID() + ")" +
					" ) ";
		
		session.createSQLQuery(sql).executeUpdate();
		
		sql="delete from clfw_clfwtdb "
			+ "  WHERE  CLFWTDB_CLSX <> 1 and CLFWTDB_SEQID in (" + clfwBean.getCLFWTDB_SEQID() + ")";
		
		session.createSQLQuery(sql).executeUpdate();
		
		sql="UPDATE clfw_clfwtdb set"
			+ "  CLFWTDB_SHSX = '0', "
			+ "  CLFWTDB_SHCZ = '0', "
			+ "  OPE_INSERTTIME = to_char(sysdate,'yyyymmddhh24miss'), "
			+ "  OPE_OPEID = '" + clfwBean.getOPE_OPEID() + "', "
			+ "  OPE_OPENAME = '" + clfwBean.getOPE_OPENAME() + "'"
			+ "  WHERE CLFWTDB_CLSX = 1 and CLFWTDB_SEQID in (" + clfwBean.getCLFWTDB_SEQID() + ")";
			
			session.createSQLQuery(sql).executeUpdate();
		
		saveFlag=true;
		return saveFlag;
	}
	
	@Transactional(readOnly = true)
	public boolean bjclfwtdd(CLFWBean clfwBean) {
		boolean saveFlag = false;
		
		Session session = getSession();

		String sql = "INSERT INTO CLFW_CLFWTDD "
			+ "(CLFWTDD_SEQID,PG_PK_CODE,CLFWTDB_SEQID,CLFWTDD_CLSX,OPE_INSERTTIME,OPE_REMARK,OPE_OPEID,OPE_OPENAME) "
			+ " select SEQ_CLFW_ALL.nextval,b.PG_PK_CODE,'" + clfwBean.getCLFWTDB_SEQID() + "','" + clfwBean.getCLFWTDD_CLSX() + "',"
			+ "to_char(sysdate,'yyyymmddhh24miss'),'" + clfwBean.getOPE_REMARK() + "','" + clfwBean.getOPE_OPEID() + "','" + clfwBean.getOPE_OPENAME() + "'" + 
			" from clfw_clfwtdb a, cp_wh_postseg b, clfw_clfwtdd c " +
			" where a.dm_pk_code = b.dm_pk_code and b.pg_pk_code = c.pg_pk_code(+) and c.pg_pk_code is null " +
			" and a.CLFWTDB_SEQID = '" + clfwBean.getCLFWTDB_SEQID() + "' and b.PG_PK_CODE in (" + clfwBean.getPG_PK_CODE() + ")";

		session.createSQLQuery(sql).executeUpdate();
		
		sql = "UPDATE CLFW_CLFWTDD set" + 
		"  CLFWTDD_CLSX = '" + clfwBean.getCLFWTDD_CLSX() + "', " + 
		"  OPE_INSERTTIME = to_char(sysdate,'yyyymmddhh24miss'), " + 
		"  OPE_REMARK = '" + clfwBean.getOPE_REMARK() + "', " + 
		"  OPE_OPEID = '" + clfwBean.getOPE_OPEID() + "', " + 
		"  OPE_OPENAME = '" + clfwBean.getOPE_OPENAME() + "'" + 
		"  WHERE CLFWTDB_SEQID = '" + clfwBean.getCLFWTDB_SEQID() + "' and PG_PK_CODE in (" + clfwBean.getPG_PK_CODE() + ")";

		session.createSQLQuery(sql).executeUpdate();

		saveFlag = true;
		return saveFlag;
	}
	
	
	@Transactional(readOnly = true)
	public boolean addclfwkffw(CLFWBean clfwBean) {
		boolean saveFlag = false;

		Session session = getSession();

		String sql = "INSERT INTO CLFW_KFFW "
			+ "(CLFWKFFW_SEQID,CLFWKFFW_DISTCD,CLFWKFFW_YTEFW,CLFWKFFW_SWSFW,OPE_INSERTTIME,OPE_OPEID,OPE_OPENAME) "
			+ " values(SEQ_CLFW_ALL.nextval,'" + clfwBean.getCLFWKFFW_DISTCD() + "','" + clfwBean.getCLFWKFFW_YTEFW() + "','" + clfwBean.getCLFWKFFW_SWSFW() + "',"
			+ "to_char(sysdate,'yyyymmddhh24miss'),'" + clfwBean.getOPE_OPEID() + "','" + clfwBean.getOPE_OPENAME() + "'" + ")";

		session.createSQLQuery(sql).executeUpdate();
		
		saveFlag = true;
		return saveFlag;
	}
	
	@Transactional(readOnly = true)
	public boolean upclfwkffw(CLFWBean clfwBean) {
		boolean saveFlag = false;
		
		Session session = getSession();

		String sql = "UPDATE CLFW_KFFW set" + 
		"  CLFWKFFW_DISTCD = '" + clfwBean.getCLFWKFFW_DISTCD() + "', " + 
		"  CLFWKFFW_YTEFW = '" + clfwBean.getCLFWKFFW_YTEFW() + "', " + 
		"  CLFWKFFW_SWSFW = '" + clfwBean.getCLFWKFFW_SWSFW() + "', " + 
		"  OPE_INSERTTIME = to_char(sysdate,'yyyymmddhh24miss'), " + 
		"  OPE_OPEID = '" + clfwBean.getOPE_OPEID() + "', " + 
		"  OPE_OPENAME = '" + clfwBean.getOPE_OPENAME() + "'" + 
		"  WHERE CLFWKFFW_SEQID = '" + clfwBean.getCLFWKFFW_SEQID() + "'";

		session.createSQLQuery(sql).executeUpdate();

		saveFlag = true;
		return saveFlag;
	}
	
	@Transactional(readOnly = true)
	public boolean delclfwkffw(CLFWBean clfwBean) {
		boolean saveFlag = false;

		Session session = getSession();
		
		String sql = "";
	
		sql = "delete from CLFW_KFFW " +
				" WHERE CLFWKFFW_SEQID = '" + clfwBean.getCLFWKFFW_SEQID() + "'";

		session.createSQLQuery(sql).executeUpdate();

		saveFlag = true;
		return saveFlag;
	}
	
	@Transactional(readOnly = true)
	public boolean addclfwhkwhcg(CLFWBean clfwBean) {
		boolean saveFlag = false;

		Session session = getSession();

		String sql = "INSERT INTO CLFW_HBXXGL "
			+ "(CLFW_HBXXGL_SEQID,CLFW_HBXXGL_LDLSH,CLFW_HBXXGL_SFJID,CLFW_HBXXGL_ZDJID,CLFW_HBXXGL_CSRQ,CLFW_HBXXGL_HBH,CLFW_HBXXGL_ZBS,CLFW_HBXXGL_ZL,SFJOPE_INSERTTIME,SFJOPE_OPEID,SFJOPE_OPENAME,CLFW_HBXXGL_QYRQ,CLFW_HBXXGL_CLSX) "
			+ " values(SEQ_CLFW_ALL.nextval,'" + clfwBean.getCLFW_HBXXGL_LDLSH() + "','" + clfwBean.getCLFW_HBXXGL_SFJID() + "','" + clfwBean.getCLFW_HBXXGL_ZDJID() + "','" + clfwBean.getCLFW_HBXXGL_CSRQ() + "',"
			+ "'" + clfwBean.getCLFW_HBXXGL_HBH() + "','" + clfwBean.getCLFW_HBXXGL_ZBS() + "','" + clfwBean.getCLFW_HBXXGL_ZL()  + "',"
			+ "to_char(sysdate,'yyyymmddhh24miss'),'" + clfwBean.getOPE_OPEID() + "','" + clfwBean.getOPE_OPENAME() + "',to_char(sysdate,'yyyymmddhh24miss')" + ",'0')";

		session.createSQLQuery(sql).executeUpdate();
		
		saveFlag = true;
		return saveFlag;
	}
	
	@Transactional(readOnly = true)
	public boolean addclfwhkwhjg(CLFWBean clfwBean) {
		boolean saveFlag = false;

		Session session = getSession();

		String sql = "INSERT INTO CLFW_HBXXGL "
			+ "(CLFW_HBXXGL_SEQID,CLFW_HBXXGL_SFJID,CLFW_HBXXGL_ZDJID,CLFW_HBXXGL_CSRQ,CLFW_HBXXGL_HBH,CLFW_HBXXGL_LDZBS,CLFW_HBXXGL_SSZBS,ZDJOPE_INSERTTIME,ZDJOPE_OPEID,ZDJOPE_OPENAME,CLFW_HBXXGL_JZRQ,CLFW_HBXXGL_CLSX) "
			+ " values(SEQ_CLFW_ALL.nextval,'" + clfwBean.getCLFW_HBXXGL_SFJID() + "','" + clfwBean.getCLFW_HBXXGL_ZDJID() + "','" + clfwBean.getCLFW_HBXXGL_CSRQ() + "',"
			+ "'" + clfwBean.getCLFW_HBXXGL_HBH() + "','" + clfwBean.getCLFW_HBXXGL_LDZBS() + "','" + clfwBean.getCLFW_HBXXGL_SSZBS() + "',"
			+ "to_char(sysdate,'yyyymmddhh24miss'),'" + clfwBean.getOPE_OPEID() + "','" + clfwBean.getOPE_OPENAME() + "',to_char(sysdate,'yyyymmddhh24miss')" + ",'1')";

		session.createSQLQuery(sql).executeUpdate();
		
		saveFlag = true;
		return saveFlag;
	}
	
	@Transactional(readOnly = true)
	public boolean upclfwhkwhcg(CLFWBean clfwBean) {
		boolean saveFlag = false;
		
		Session session = getSession();
		
		String sql = " ";
		
		sql = "UPDATE CLFW_HBXXGL set" + 
		"  CLFW_HBXXGL_HBH = '" + clfwBean.getCLFW_HBXXGL_HBH() + "', " + 
		"  CLFW_HBXXGL_LDLSH = '" + clfwBean.getCLFW_HBXXGL_LDLSH() + "', " + 
		"  CLFW_HBXXGL_ZBS = '" + clfwBean.getCLFW_HBXXGL_ZBS() + "', " + 
		"  CLFW_HBXXGL_ZL = '" + clfwBean.getCLFW_HBXXGL_ZL() + "', " + 
		"  CLFW_HBXXGL_QYRQ = to_char(sysdate,'yyyymmddhh24miss'), " + 
		"  SFJOPE_INSERTTIME = to_char(sysdate,'yyyymmddhh24miss'), " + 
		"  SFJOPE_OPEID = '" + clfwBean.getOPE_OPEID() + "', " + 
		"  SFJOPE_OPENAME = '" + clfwBean.getOPE_OPENAME() + "'" + 
		"  WHERE CLFW_HBXXGL_JZRQ is null and CLFW_HBXXGL_SEQID = '" + clfwBean.getCLFW_HBXXGL_SEQID() + "'";
		
		session.createSQLQuery(sql).executeUpdate();

		saveFlag = true;
		return saveFlag;
	}
	
	@Transactional(readOnly = true)
	public boolean upclfwhkwhjg(CLFWBean clfwBean) {
		boolean saveFlag = false;
		
		Session session = getSession();
		
		String sql = " ";
		
		sql = "UPDATE CLFW_HBXXGL set" + 
		"  CLFW_HBXXGL_HBH = '" + clfwBean.getCLFW_HBXXGL_HBH() + "', " + 
		"  CLFW_HBXXGL_LDZBS = '" + clfwBean.getCLFW_HBXXGL_LDZBS() + "', " + 
		"  CLFW_HBXXGL_SSZBS = '" + clfwBean.getCLFW_HBXXGL_SSZBS() + "', " + 
		"  CLFW_HBXXGL_JZRQ = to_char(sysdate,'yyyymmddhh24miss'), " + 
		"  ZDJOPE_INSERTTIME = to_char(sysdate,'yyyymmddhh24miss'), " + 
		"  ZDJOPE_OPEID = '" + clfwBean.getOPE_OPEID() + "', " + 
		"  ZDJOPE_OPENAME = '" + clfwBean.getOPE_OPENAME() + "'" + 
		"  WHERE CLFW_HBXXGL_SEQID = '" + clfwBean.getCLFW_HBXXGL_SEQID() + "'";
		
		session.createSQLQuery(sql).executeUpdate();

		saveFlag = true;
		return saveFlag;
	}
	
	@Transactional(readOnly = true)
	public boolean delclfwhkwh(CLFWBean clfwBean) {
		boolean saveFlag = false;

		Session session = getSession();
		
		String sql = "";
	
		sql = "delete from CLFW_HBXXGL " +
				" WHERE  CLFW_HBXXGL_SEQID = '" + clfwBean.getCLFW_HBXXGL_SEQID() + "'";

		session.createSQLQuery(sql).executeUpdate();

		saveFlag = true;
		return saveFlag;
	}
	
	
	@Transactional(readOnly = true)
	public boolean bjclfwwhkwhcg(CLFWBean clfwBean) {
		boolean saveFlag = false;

		Session session = getSession();

		String sql = "INSERT INTO CLFW_HBXXGL "
			+ "(CLFW_HBXXGL_SEQID,CLFW_HBXXGL_SFJID,CLFW_HBXXGL_ZDJID,CLFW_HBXXGL_CSRQ,CLFW_HBXXGL_HBH,SFJOPE_INSERTTIME,SFJOPE_OPEID,SFJOPE_OPENAME,CLFW_HBXXGL_QYRQ,CLFW_HBXXGL_CLSX) "
			+ " select SEQ_CLFW_ALL.nextval,CLFW_HBXXGL_SFJID,CLFW_HBXXGL_ZDJID,'" + clfwBean.getCLFW_HBXXGL_CSRQ() + "',"
			+ " CLFW_HBXXGL_HBH,"
			+ "to_char(sysdate,'yyyymmddhh24miss'),'" + clfwBean.getOPE_OPEID() + "','" + clfwBean.getOPE_OPENAME() + "',to_char(sysdate,'yyyymmddhh24miss'),CLFW_HBXXGL_CLSX " +
					" from CLFW_HBXXGL where CLFW_HBXXGL_SEQID  in (" + clfwBean.getCLFW_HBXXGL_SEQID() + ")";

		session.createSQLQuery(sql).executeUpdate();
		
		saveFlag = true;
		return saveFlag;
	}
	
	@Transactional(readOnly = true)
	public boolean bjclfwwhkwhjg(CLFWBean clfwBean) {
		boolean saveFlag = false;

		Session session = getSession();

		String sql = "INSERT INTO CLFW_HBXXGL "
			+ "(CLFW_HBXXGL_SEQID,CLFW_HBXXGL_SFJID,CLFW_HBXXGL_ZDJID,CLFW_HBXXGL_CSRQ,CLFW_HBXXGL_HBH,ZDJOPE_INSERTTIME,ZDJOPE_OPEID,ZDJOPE_OPENAME,CLFW_HBXXGL_JZRQ,CLFW_HBXXGL_CLSX) "
			+ " select SEQ_CLFW_ALL.nextval,CLFW_HBXXGL_SFJID,CLFW_HBXXGL_ZDJID,'" + clfwBean.getCLFW_HBXXGL_CSRQ() + "',"
			+ " CLFW_HBXXGL_HBH,"
			+ "to_char(sysdate,'yyyymmddhh24miss'),'" + clfwBean.getOPE_OPEID() + "','" + clfwBean.getOPE_OPENAME() + "',to_char(sysdate,'yyyymmddhh24miss'),CLFW_HBXXGL_CLSX " +
					" from CLFW_HBXXGL where CLFW_HBXXGL_SEQID  in (" + clfwBean.getCLFW_HBXXGL_SEQID() + ")";

		session.createSQLQuery(sql).executeUpdate();
		
		saveFlag = true;
		return saveFlag;
	}
	
	@Transactional(readOnly = true)
	public boolean upclfwylxxbt(CLFWBean clfwBean) {
		boolean saveFlag = false;
		
		Session session = getSession();
		
		String sql = " ";

		
		if(clfwBean.getCLFWYLXXB_XSBZ().equals("1") || clfwBean.getCLFWYLXXB_XSBZ().equals("3") ){
			
			sql = "UPDATE CLFW_YLXXB set" + 
			" CLFWYLXXB_QYRQ = to_char(sysdate,'yyyymmdd')," +
			"  CLFWYLXXB_LSH = '" + clfwBean.getCLFWYLXXB_LSH() + "', " + 
			"  CLFWYLXXB_CPH = '" + clfwBean.getCLFWYLXXB_CPH() + "', " + 
			"  CLFWYLXXB_SJKCSJ = '" + clfwBean.getCLFWYLXXB_SJKCSJ() + "', " + 
			"  CLFWYLXXB_SFJGHSM = '" + clfwBean.getCLFWYLXXB_SFJGHSM() + "', " + 
			"  SFJOPE_INSERTTIME = to_char(sysdate,'yyyymmddhh24miss'), " + 
			"  SFJOPE_OPEID = '" + clfwBean.getOPE_OPEID() + "', " + 
			"  SFJOPE_OPENAME = '" + clfwBean.getOPE_OPENAME() + "'" + 
			" where " +
			" CLFWYLXXB_SEQID = '" + clfwBean.getCLFWYLXXB_SEQID() + "'";

			session.createSQLQuery(sql).executeUpdate();
			
		}else if(clfwBean.getCLFWYLXXB_XSBZ().equals("2") || clfwBean.getCLFWYLXXB_XSBZ().equals("4") ){
			
			sql = "UPDATE CLFW_YLXXB set" + 
			"  CLFWYLXXB_SJDDSJ = '" + clfwBean.getCLFWYLXXB_SJDDSJ() + "', " + 
			" CLFWYLXXB_JZRQ = to_char(sysdate,'yyyymmdd')," +
			"  ZDJOPE_INSERTTIME = to_char(sysdate,'yyyymmddhh24miss'), " + 
			"  ZDJOPE_OPEID = '" + clfwBean.getOPE_OPEID() + "', " + 
			"  ZDJOPE_OPENAME = '" + clfwBean.getOPE_OPENAME() + "'" + 
			" where " +
			" CLFWYLXXB_SEQID = '" + clfwBean.getCLFWYLXXB_SEQID() + "'";

			session.createSQLQuery(sql).executeUpdate();
		}
		

		saveFlag = true;
		return saveFlag;
	}
	
	@Transactional(readOnly = true)
	public boolean bjclfwylxxclwt(CLFWBean clfwBean) {
		boolean saveFlag = false;
		
		Session session = getSession();
		
		String sql = " ";

		
		if(clfwBean.getCLFWYLXXB_XSBZ().equals("1")){
			
			sql = "UPDATE CLFW_YLXXB set" + 
			"  CLFWYLXXB_SJKCSJ = null, " + 
			"  SFJOPE_INSERTTIME = to_char(sysdate,'yyyymmddhh24miss'), " + 
			"  SFJOPE_OPEID = '" + clfwBean.getOPE_OPEID() + "', " + 
			"  SFJOPE_OPENAME = '" + clfwBean.getOPE_OPENAME() + "'" + 
			" where " +
			" CLFWYLXXB_SEQID in (" + clfwBean.getCLFWYLXXB_SEQID() + ")";

			session.createSQLQuery(sql).executeUpdate();
			
		}else if(clfwBean.getCLFWYLXXB_XSBZ().equals("2")){
			
			sql = "UPDATE CLFW_YLXXB set" + 
			" CLFWYLXXB_SJDDSJ = null, " + 
			"  ZDJOPE_INSERTTIME = to_char(sysdate,'yyyymmddhh24miss'), " + 
			"  ZDJOPE_OPEID = '" + clfwBean.getOPE_OPEID() + "', " + 
			"  ZDJOPE_OPENAME = '" + clfwBean.getOPE_OPENAME() + "'" + 
			" where " +
			" CLFWYLXXB_SEQID in (" + clfwBean.getCLFWYLXXB_SEQID() + ")";

			session.createSQLQuery(sql).executeUpdate();
			
		}else if(clfwBean.getCLFWYLXXB_XSBZ().equals("3")){
			
			sql = "UPDATE CLFW_YLXXB set" + 
			" 	CLFWYLXXB_SFJGH = '" + clfwBean.getCLFWYLXXB_SFJGH() + "', " + 
			"  SFJOPE_INSERTTIME = to_char(sysdate,'yyyymmddhh24miss'), " + 
			"  SFJOPE_OPEID = '" + clfwBean.getOPE_OPEID() + "', " + 
			"  SFJOPE_OPENAME = '" + clfwBean.getOPE_OPENAME() + "'" + 
			" where " +
			" CLFWYLXXB_SEQID in (" + clfwBean.getCLFWYLXXB_SEQID() + ")";

			session.createSQLQuery(sql).executeUpdate();
			
		}else if(clfwBean.getCLFWYLXXB_XSBZ().equals("4")){
			
			sql = "UPDATE CLFW_YLXXB set" + 
			" 	CLFWYLXXB_ZDJGH = '" + clfwBean.getCLFWYLXXB_ZDJGH() + "', " + 
			"  ZDJOPE_INSERTTIME = to_char(sysdate,'yyyymmddhh24miss'), " + 
			"  ZDJOPE_OPEID = '" + clfwBean.getOPE_OPEID() + "', " + 
			"  ZDJOPE_OPENAME = '" + clfwBean.getOPE_OPENAME() + "'" + 
			" where " +
			" CLFWYLXXB_SEQID in (" + clfwBean.getCLFWYLXXB_SEQID() + ")";

			session.createSQLQuery(sql).executeUpdate();
		}
		

		saveFlag = true;
		return saveFlag;
	}
	
	
	@Transactional(readOnly = true)
	public boolean addclfwresorgpz(CLFWBean clfwBean) {
		boolean saveFlag = false;

		Session session = getSession();

		String sql = "INSERT INTO clfw_resorg_pz "
			+ "(CLFWRESORGPZ_SEQID,ORG_CODE,CLFWPCSHI_SEQID,CLFWRESORGPZ_DISTCD,OPE_INSERTTIME,OPE_OPEID,OPE_OPENAME,CLFWRESORGPZ_SHSX,CLFWRESORGPZ_CLSX) "
				+ " select SEQ_CLFW_ALL.nextval,ORG_CODE," +
				" '" + clfwBean.getCLFWPCSHI_SEQID() + "'," +
				" decode(CITY_CODE,null,prov_code||'0000',CITY_CODE),"
				+ " to_char(sysdate,'yyyymmddhh24miss')," +
				" '" + clfwBean.getOPE_OPEID() + "'," +
				" '" + clfwBean.getOPE_OPENAME() + "','1','1'" + " " +
				" from clfw_resorg_code  " +
				" where ORG_CODE  in (" + clfwBean.getCLFWRESORGPZ_SEQID() + ")" +
						" and ORG_CODE not in (" +
						" select ORG_CODE from  clfw_resorg_pz where ORG_CODE  in (" + clfwBean.getCLFWRESORGPZ_SEQID() + ")" +
								" and CLFWPCSHI_SEQID = '" + clfwBean.getCLFWPCSHI_SEQID() + "'" +
						"  )";

		session.createSQLQuery(sql).executeUpdate();
		
		saveFlag = true;
		return saveFlag;
	}
	
	@Transactional(readOnly = true)
	public boolean delclfwresorgpz(CLFWBean clfwBean) {
		boolean saveFlag = false;

		Session session = getSession();
		
		String sql = "";
	
		sql = "delete from clfw_resorg_pz " +
				" WHERE CLFWRESORGPZ_SEQID in (" + clfwBean.getCLFWRESORGPZ_SEQID() + ")";

		session.createSQLQuery(sql).executeUpdate();

		saveFlag = true;
		return saveFlag;
	}
	
	@Transactional(readOnly = true)
	public boolean addclfwtabnjjs(CLFWBean clfwBean) {
		boolean saveFlag = false;

		Session session = getSession();

		String sql = "INSERT INTO CLFW_TDBNJJS "
			+ "(CLFWTDBNJJS_SEQID,DM_PK_CODE,CLFWTDBNJJS_SFNJ,OPE_INSERTTIME,OPE_OPEID,OPE_OPENAME) "
				+ " select SEQ_CLFW_ALL.nextval,DM_PK_CODE," +
				" '" + clfwBean.getCLFWTDBNJJS_SFNJ() + "'," +
				" to_char(sysdate,'yyyymmddhh24miss')," +
				" '" + clfwBean.getOPE_OPEID() + "'," +
				" '" + clfwBean.getOPE_OPENAME() + "' " +
				" from CP_WH_DEPARTMENT  " +
				" where DM_PK_CODE  in (" + clfwBean.getCLFWTDBNJJS_SEQID() + ")" +
						" and DM_PK_CODE not in (" +
						" select DM_PK_CODE from  CLFW_TDBNJJS " +
						" ) ";

		session.createSQLQuery(sql).executeUpdate();
		
		saveFlag = true;
		return saveFlag;
	}
	
	@Transactional(readOnly = true)
	public boolean delclfwtabnjjs(CLFWBean clfwBean) {
		boolean saveFlag = false;

		Session session = getSession();
		
		String sql = "";
	
		sql = "delete from CLFW_TDBNJJS " +
				" WHERE CLFWTDBNJJS_SEQID in (" + clfwBean.getCLFWTDBNJJS_SEQID() + ")";

		session.createSQLQuery(sql).executeUpdate();

		saveFlag = true;
		return saveFlag;
	}
	
	@Transactional(readOnly = true)
	public boolean upclfwhkldwh(CLFWBean clfwBean) {
		boolean saveFlag = false;
		
		Session session = getSession();
		
		String sql = " ";

		
		if(clfwBean.getCLFW_HBXXGL_XSBZ().equals("0") ){
			
			sql = "UPDATE CLFW_HBXXLDBW set" + 
			"  CLFW_HBXXLDBW_AJTHZBS = '" + clfwBean.getCLFW_HBXXLDBW_AJTHZBS() + "', " + 
			"  CLFW_HBXXLDBW_LXZBS = '" + clfwBean.getCLFW_HBXXLDBW_LXZBS() + "', " + 
			"  OPE_INSERTTIME = to_char(sysdate,'yyyymmddhh24miss'), " + 
			"  OPE_OPEID = '" + clfwBean.getOPE_OPEID() + "', " + 
			"  OPE_OPENAME = '" + clfwBean.getOPE_OPENAME() + "'" + 
			" where " +
			" CLFW_HBXXLDBW_SEQID = '" + clfwBean.getCLFW_HBXXLDBW_SEQID() + "'";

			session.createSQLQuery(sql).executeUpdate();
			
		}else if(clfwBean.getCLFW_HBXXGL_XSBZ().equals("1") ){
			
			sql = "UPDATE CLFW_HBXXLDBW set" + 
			"  CLFW_HBXXLDBW_SSZBS = '" + clfwBean.getCLFW_HBXXLDBW_SSZBS() + "', " + 
			"  ZDJOPE_INSERTTIME = to_char(sysdate,'yyyymmddhh24miss'), " + 
			"  ZDJOPE_OPEID = '" + clfwBean.getOPE_OPEID() + "', " + 
			"  ZDJOPE_OPENAME = '" + clfwBean.getOPE_OPENAME() + "'" + 
			" where " +
			" CLFW_HBXXLDBW_SEQID = '" + clfwBean.getCLFW_HBXXLDBW_SEQID() + "'";

			session.createSQLQuery(sql).executeUpdate();
		}
		

		saveFlag = true;
		return saveFlag;
	}
	
	@Transactional(readOnly = true)
	public boolean bjupclfwhkldwh(CLFWBean clfwBean) {
		boolean saveFlag = false;
		
		Session session = getSession();
		
		String sql = " ";

		
		if(clfwBean.getCLFW_HBXXGL_XSBZ().equals("1")){
			
			sql = "UPDATE CLFW_HBXXLDBW set" + 
			"  CLFW_HBXXLDBW_SFJGH = '1', " + 
			"  OPE_INSERTTIME = to_char(sysdate,'yyyymmddhh24miss'), " + 
			"  OPE_OPEID = '" + clfwBean.getOPE_OPEID() + "', " + 
			"  OPE_OPENAME = '" + clfwBean.getOPE_OPENAME() + "'" + 
			" where " +
			" CLFW_HBXXLDBW_SEQID in (" + clfwBean.getCLFW_HBXXLDBW_SEQID() + ")";

			session.createSQLQuery(sql).executeUpdate();
			
		}else if(clfwBean.getCLFW_HBXXGL_XSBZ().equals("2")){
			
			sql = "UPDATE CLFW_HBXXLDBW set" + 
			"  CLFW_HBXXLDBW_ZDJGH = '1', " + 
			"  ZDJOPE_INSERTTIME = to_char(sysdate,'yyyymmddhh24miss'), " + 
			"  ZDJOPE_OPEID = '" + clfwBean.getOPE_OPEID() + "', " + 
			"  ZDJOPE_OPENAME = '" + clfwBean.getOPE_OPENAME() + "'" + 
			" where " +
			" CLFW_HBXXLDBW_SEQID in (" + clfwBean.getCLFW_HBXXLDBW_SEQID() + ")";

			session.createSQLQuery(sql).executeUpdate();
			
		}else if(clfwBean.getCLFW_HBXXGL_XSBZ().equals("3")){
			
			sql = "UPDATE CLFW_HBXXLDBW set" + 
			"  CLFW_HBXXLDBW_SFJGH = '0', " + 
			"  OPE_INSERTTIME = to_char(sysdate,'yyyymmddhh24miss'), " + 
			"  OPE_OPEID = '" + clfwBean.getOPE_OPEID() + "', " + 
			"  OPE_OPENAME = '" + clfwBean.getOPE_OPENAME() + "'" + 
			" where " +
			" CLFW_HBXXLDBW_SEQID in (" + clfwBean.getCLFW_HBXXLDBW_SEQID() + ")";

			session.createSQLQuery(sql).executeUpdate();
			
		}else if(clfwBean.getCLFW_HBXXGL_XSBZ().equals("4")){
			
			sql = "UPDATE CLFW_HBXXLDBW set" + 
			"  CLFW_HBXXLDBW_ZDJGH = '0', " + 
			"  ZDJOPE_INSERTTIME = to_char(sysdate,'yyyymmddhh24miss'), " + 
			"  ZDJOPE_OPEID = '" + clfwBean.getOPE_OPEID() + "', " + 
			"  ZDJOPE_OPENAME = '" + clfwBean.getOPE_OPENAME() + "'" + 
			" where " +
			" CLFW_HBXXLDBW_SEQID in (" + clfwBean.getCLFW_HBXXLDBW_SEQID() + ")";

			session.createSQLQuery(sql).executeUpdate();
		}
		

		saveFlag = true;
		return saveFlag;
	}
	
}
