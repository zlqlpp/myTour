package com.cpst.emsadrdb.web.clfw;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import com.cpst.core.orm.Page;
import com.cpst.core.web.struts2.BaseActionSupport;
import com.cpst.emsadrdb.dao.clfw.ClfwQueryDao;
import com.cpst.emsadrdb.service.clfw.ClfwCommon;
import com.cpst.emsadrdb.service.clfw.ClfwExportall;

@ParentPackage("jsoncrud")
@Results( { @Result(type = "json", name = "json") })
public class B09r02clfwqueryAction extends BaseActionSupport {

	private static final long serialVersionUID = -6071823460042875876L;

	private String q;// ajax参数
	private int pageNo;
	@SuppressWarnings("unchecked")
	private Page page = new Page(10);

	public String getQ() {
		return q;
	}

	public void setQ(String q) {
		this.q = q;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	@Autowired
	private ClfwQueryDao clfwQueryDao;

	public String SEL_CLFWPC_MC;
	public String RAD_CLFWPC_SX;
	public String PROVINCE_ID;
	public String CITY_ID;
	public String COUNTY_ID;
	public String SEL_DM_NAME;
	public String CLFWPC_NAMES;
	public String SEL_CLFWTDB_CLSX;
	public String CLFWTDB_SEQID;
	public String SEL_PG_NAME;
	public String SEL_CLFWTDD_CLSX;
	public String RAD_CLFWPC_QY;
	public String SEL_CLFWPCSHENG_SHENGM;
	public String SEL_CLFWPCSHI_SHIGM;
	public String SEL_CLFWYLXXBZ_YLMC;
	public String RAD_CLFWYLXXB_CHSX;
	public String SEL_CLFWYLXXB_CSRQ;
	public String SEL_ORG_CODE;
	public String ITEM_DATESS;
	public String ITEM_DATESE;
	public String CLFWYLXXBZ_YLMC;
	public String CLFW_HBXXGL_CLSX;
	public String CLFW_ORDERBY;
	public int FLAGNUM;
	
	public String LINEKEY;
	public String EXDATA;
	public String EXPORTALLPATH;
	public String LINEMAIN;
	public String LINENAME;
	public String CLFW_POSTALROUTEATTR;//邮路类型
	
	
	
	
	

	public String getCLFW_POSTALROUTEATTR() {
		return CLFW_POSTALROUTEATTR;
	}

	public void setCLFW_POSTALROUTEATTR(String cLFW_POSTALROUTEATTR) {
		CLFW_POSTALROUTEATTR = ClfwCommon.sql_inj(cLFW_POSTALROUTEATTR);
	}

	public String getCLFW_ORDERBY() {
		return CLFW_ORDERBY;
	}

	public void setCLFW_ORDERBY(String clfw_orderby) {
		CLFW_ORDERBY = ClfwCommon.sql_inj(clfw_orderby);
	}

	public String getLINEMAIN() {
		return LINEMAIN;
	}

	public void setLINEMAIN(String linemain) {
		LINEMAIN = ClfwCommon.sql_inj(linemain);
	}

	public String getLINENAME() {
		return LINENAME;
	}

	public void setLINENAME(String linename) {
		LINENAME = ClfwCommon.sql_inj(linename);
	}

	public String getEXPORTALLPATH() {
		return EXPORTALLPATH;
	}

	public void setEXPORTALLPATH(String exportallpath) {
		EXPORTALLPATH = ClfwCommon.sql_inj(exportallpath);
	}

	public String getLINEKEY() {
		return LINEKEY;
	}

	public void setLINEKEY(String linekey) {
		LINEKEY = ClfwCommon.sql_inj(linekey);
	}

	public String getEXDATA() {
		return EXDATA;
	}

	public void setEXDATA(String exdata) {
		EXDATA = ClfwCommon.sql_inj(exdata);
	}

	public int getFLAGNUM() {
		return FLAGNUM;
	}

	public void setFLAGNUM(int flagnum) {
		FLAGNUM = flagnum;
	}

	public String getCLFW_HBXXGL_CLSX() {
		return CLFW_HBXXGL_CLSX;
	}

	public void setCLFW_HBXXGL_CLSX(String clfw_hbxxgl_clsx) {
		CLFW_HBXXGL_CLSX = ClfwCommon.sql_inj(clfw_hbxxgl_clsx);
	}

	public String getCLFWYLXXBZ_YLMC() {
		return CLFWYLXXBZ_YLMC;
	}

	public void setCLFWYLXXBZ_YLMC(String clfwylxxbz_ylmc) {
		CLFWYLXXBZ_YLMC = ClfwCommon.sql_inj(clfwylxxbz_ylmc);
	}

	public String getITEM_DATESS() {
		return ITEM_DATESS;
	}

	public void setITEM_DATESS(String item_datess) {
		ITEM_DATESS = ClfwCommon.sql_inj(item_datess);
	}

	public String getITEM_DATESE() {
		return ITEM_DATESE;
	}

	public void setITEM_DATESE(String item_datese) {
		ITEM_DATESE = ClfwCommon.sql_inj(item_datese);
	}

	public String getSEL_ORG_CODE() {
		return SEL_ORG_CODE;
	}

	public void setSEL_ORG_CODE(String sel_org_code) {
		SEL_ORG_CODE = ClfwCommon.sql_inj(sel_org_code);
	}

	

	public String getSEL_CLFWYLXXB_CSRQ() {
		return SEL_CLFWYLXXB_CSRQ;
	}

	public void setSEL_CLFWYLXXB_CSRQ(String sel_clfwylxxb_csrq) {
		SEL_CLFWYLXXB_CSRQ = ClfwCommon.sql_inj(sel_clfwylxxb_csrq);
	}

	public String getSEL_CLFWYLXXBZ_YLMC() {
		return SEL_CLFWYLXXBZ_YLMC;
	}

	public void setSEL_CLFWYLXXBZ_YLMC(String sel_clfwylxxbz_ylmc) {
		SEL_CLFWYLXXBZ_YLMC = ClfwCommon.sql_inj(sel_clfwylxxbz_ylmc);
	}

	public String getRAD_CLFWYLXXB_CHSX() {
		return RAD_CLFWYLXXB_CHSX;
	}

	public void setRAD_CLFWYLXXB_CHSX(String rad_clfwylxxb_chsx) {
		RAD_CLFWYLXXB_CHSX = ClfwCommon.sql_inj(rad_clfwylxxb_chsx);
	}

	public String getSEL_CLFWPCSHI_SHIGM() {
		return SEL_CLFWPCSHI_SHIGM;
	}

	public void setSEL_CLFWPCSHI_SHIGM(String sel_clfwpcshi_shigm) {
		SEL_CLFWPCSHI_SHIGM = ClfwCommon.sql_inj(sel_clfwpcshi_shigm);
	}

	public String getSEL_CLFWPCSHENG_SHENGM() {
		return SEL_CLFWPCSHENG_SHENGM;
	}

	public void setSEL_CLFWPCSHENG_SHENGM(String sel_clfwpcsheng_shengm) {
		SEL_CLFWPCSHENG_SHENGM = ClfwCommon.sql_inj(sel_clfwpcsheng_shengm);
	}

	public String getRAD_CLFWPC_QY() {
		return RAD_CLFWPC_QY;
	}

	public void setRAD_CLFWPC_QY(String rad_clfwpc_qy) {
		RAD_CLFWPC_QY = ClfwCommon.sql_inj(rad_clfwpc_qy);
	}

	public String getCLFWPC_NAMES() {
		return CLFWPC_NAMES;
	}

	public void setCLFWPC_NAMES(String clfwpc_names) {
		CLFWPC_NAMES = ClfwCommon.sql_inj(clfwpc_names);
	}

	public String getSEL_CLFWTDB_CLSX() {
		return SEL_CLFWTDB_CLSX;
	}

	public void setSEL_CLFWTDB_CLSX(String sel_clfwtdb_clsx) {
		SEL_CLFWTDB_CLSX = ClfwCommon.sql_inj(sel_clfwtdb_clsx);
	}

	public String getSEL_CLFWPC_MC() {
		return SEL_CLFWPC_MC;
	}

	public void setSEL_CLFWPC_MC(String sel_clfwpc_mc) {
		SEL_CLFWPC_MC = ClfwCommon.sql_inj(sel_clfwpc_mc);
	}

	public String getRAD_CLFWPC_SX() {
		return RAD_CLFWPC_SX;
	}

	public void setRAD_CLFWPC_SX(String rad_clfwpc_sx) {
		RAD_CLFWPC_SX = rad_clfwpc_sx;
	}
	

	public String getPROVINCE_ID() {
		return PROVINCE_ID;
	}

	public void setPROVINCE_ID(String province_id) {
		PROVINCE_ID = ClfwCommon.sql_inj(province_id);
	}

	public String getCITY_ID() {
		return CITY_ID;
	}

	public void setCITY_ID(String city_id) {
		CITY_ID = ClfwCommon.sql_inj(city_id);
	}

	public String getCOUNTY_ID() {
		return COUNTY_ID;
	}

	public void setCOUNTY_ID(String county_id) {
		COUNTY_ID = ClfwCommon.sql_inj(county_id);
	}

	public String getSEL_DM_NAME() {
		return SEL_DM_NAME;
	}

	public void setSEL_DM_NAME(String sel_dm_name) {
		SEL_DM_NAME = ClfwCommon.sql_inj(sel_dm_name);
	}
	public String getCLFWTDB_SEQID() {
		return CLFWTDB_SEQID;
	}

	public void setCLFWTDB_SEQID(String clfwtdb_seqid) {
		CLFWTDB_SEQID = ClfwCommon.sql_inj(clfwtdb_seqid);
	}

	public String getSEL_PG_NAME() {
		return SEL_PG_NAME;
	}

	public void setSEL_PG_NAME(String sel_pg_name) {
		SEL_PG_NAME = ClfwCommon.sql_inj(sel_pg_name);
	}

	public String getSEL_CLFWTDD_CLSX() {
		return SEL_CLFWTDD_CLSX;
	}

	public void setSEL_CLFWTDD_CLSX(String sel_clfwtdd_clsx) {
		SEL_CLFWTDD_CLSX = ClfwCommon.sql_inj(sel_clfwtdd_clsx);
	}
	
	public Boolean userflag(){
		if(getSessionUser()!=null && getSessionUser().getUsLoginId() != null && getSessionUser().getUsLoginId().length() > 0){
			return true;
		}
		else{
			//saveMessage = "用户信息丢失,请重新登陆!";
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	public String queryclfwpc() throws Exception {
		
		if(!userflag()){return "json";}

		String sql = "select a.CLFWPC_SEQID,a.CLFWPC_MC,a.CLFWPC_SM,a.CLFWPC_SX,decode(a.CLFWPC_SX,1,decode(substr(a.CLFWPC_MC,0,1),'Z','直达','省中心'),2,'揽收',3,'投递','未配置') CLFWPC_SXSTR ";
		String sqlCount = "select count(*) ";
		String sqlfrom = " from CLFW_CLFWPC a ";

		String sqlwhere = " where 1=1 ";
		String sqlorder = " order by a.CLFWPC_SX,a.CLFWPC_MC";

		if (!SEL_CLFWPC_MC.equals("null") && SEL_CLFWPC_MC.length() > 0) {
			sqlwhere = sqlwhere + " and a.CLFWPC_MC like '%" + SEL_CLFWPC_MC + "%'";
		}

		if (!RAD_CLFWPC_SX.equals("null") && RAD_CLFWPC_SX.length() > 0) {
			if (RAD_CLFWPC_SX.equals("1")) {
				sqlwhere = sqlwhere + " and a.CLFWPC_SX = 1 ";
			} else if (RAD_CLFWPC_SX.equals("2")) {
				sqlwhere = sqlwhere + " and a.CLFWPC_SX = 2 ";
			} else if (RAD_CLFWPC_SX.equals("3")) {
				sqlwhere = sqlwhere + " and a.CLFWPC_SX = 3 ";
			}
		}

		sql = sql + sqlfrom + sqlwhere + sqlorder;
		sqlCount = sqlCount + sqlfrom + sqlwhere;

		page.setPageSize(99999);
		if (pageNo != 0) {
			page.setPageNo(pageNo);
		}
		page = clfwQueryDao.getBeanQueryClfwpc(sql, sqlCount, page);
		return "json";
	}
	
	@SuppressWarnings("unchecked")
	public String queryclfwpcsheng() throws Exception {
		
		if(!userflag()){return "json";}

		String sql = "select b.CLFWPCSHENG_SEQID,a.CLFWPC_MC,b.CLFWPCSHENG_SM,b.CLFWPCSHENG_SHENGM,b.CLFWPCSHENG_JGDM ";
		String sqlCount = "select count(*) ";
		String sqlfrom = " from clfw_clfwpc a,CLFW_CLFWPC_SHENG b ";

		String sqlwhere = " where 1=1 and a.clfwpc_seqid = b.clfwpc_seqid";
		String sqlorder = " order by a.CLFWPC_MC,b.CLFWPCSHENG_SHENGM";

		if (!SEL_CLFWPC_MC.equals("null") && SEL_CLFWPC_MC.length() > 0) {
			sqlwhere = sqlwhere + " and a.CLFWPC_MC like '%" + SEL_CLFWPC_MC + "%'";
		}
		
		if (!SEL_CLFWPCSHENG_SHENGM.equals("null") && SEL_CLFWPCSHENG_SHENGM.length() > 0) {
			sqlwhere = sqlwhere + " and b.CLFWPCSHENG_SHENGM like '%" + SEL_CLFWPCSHENG_SHENGM + "%'";
		}

		if(!PROVINCE_ID.equals("null") && PROVINCE_ID.length()>0){
			sqlwhere = sqlwhere + " and b.CLFWPCSHENG_DISTCD = '" + PROVINCE_ID + "'";
		}


		sql = sql + sqlfrom + sqlwhere + sqlorder;
		sqlCount = sqlCount + sqlfrom + sqlwhere;

		page.setPageSize(99999);
		if (pageNo != 0) {
			page.setPageNo(pageNo);
		}
		page = clfwQueryDao.getBeanQueryClfwpcsheng(sql, sqlCount, page);
		return "json";
	}
	
	@SuppressWarnings("unchecked")
	public String queryclfwpcshi() throws Exception {
		
		if(!userflag()){return "json";}

		String sql = "select c.CLFWPCSHI_SEQID,a.CLFWPC_MC,a.CLFWPC_SM,decode(a.CLFWPC_SX,1,decode(substr(a.CLFWPC_MC,0,1),'Z','直达','省中心'),2,'揽收',3,'投递','未配置') CLFWPC_SXSTR," +
				" decode(b.CLFWPCSHENG_SHENGM,null,'未配置衔接频次',b.CLFWPCSHENG_SHENGM),c.CLFWPCSHI_SHIGM,c.CLFWPCSHI_SM," +
				" decode(d.city_name,null,d.province_name,d.city_name)||d.county_name CLFWPCSHI_DISTCDSTR," +
				" c.CLFWPCSHI_JZSJ,decode(c.CLFWPCSHI_JZYH,'1','赶发邮航','0','正常邮路','正常邮路') CLFWPCSHI_JZYH ";
		String sqlCount = "select count(*) ";
		String sqlfrom = " from clfw_clfwpc a," +
				"(select " +
				" bb.CLFWPCSHENG_SEQID CLFWPCSHENG_SEQID," +
				" '['||ba.CLFWPC_MC||decode(ba.CLFWPC_SX,1,decode(substr(ba.CLFWPC_MC,0,1),'Z','(直达)','(省中心)'),2,'(揽收)',3,'(投递)','(未配置)')||']'||bb.CLFWPCSHENG_SHENGM CLFWPCSHENG_SHENGM" +
				" from clfw_clfwpc ba,CLFW_CLFWPC_SHENG bb where ba.clfwpc_seqid = bb.clfwpc_seqid";
				sqlfrom = sqlfrom + " ) b," +
				" CLFW_CLFWPC_SHI c,cp_base_org_district d ";

		String sqlwhere = " where a.clfwpc_seqid = c.clfwpc_seqid and c.CLFWPCSHENG_SEQID = b.CLFWPCSHENG_SEQID(+) and c.CLFWPCSHI_DISTCD = d.district_code ";
		String sqlorder = " order by a.CLFWPC_SX,a.CLFWPC_MC,b.CLFWPCSHENG_SHENGM,c.CLFWPCSHI_SHIGM";
		
		
		if (!SEL_CLFWPCSHENG_SHENGM.equals("null") && SEL_CLFWPCSHENG_SHENGM.length() > 0) {
			sqlwhere = sqlwhere + " and b.CLFWPCSHENG_SHENGM like '%" + SEL_CLFWPCSHENG_SHENGM + "%'";
		}
		
		if (!SEL_CLFWPCSHI_SHIGM.equals("null") && SEL_CLFWPCSHI_SHIGM.length() > 0) {
			sqlwhere = sqlwhere + " and c.CLFWPCSHI_SHIGM like '%" + SEL_CLFWPCSHI_SHIGM + "%'";
		}

		if (!SEL_CLFWPC_MC.equals("null") && SEL_CLFWPC_MC.length() > 0) {
			sqlwhere = sqlwhere + " and a.CLFWPC_MC like '%" + SEL_CLFWPC_MC + "%'";
		}

		if (!RAD_CLFWPC_SX.equals("null") && RAD_CLFWPC_SX.length() > 0) {
			if (RAD_CLFWPC_SX.equals("2")) {
				sqlwhere = sqlwhere + " and a.CLFWPC_SX = 2 ";
			} else if (RAD_CLFWPC_SX.equals("3")) {
				sqlwhere = sqlwhere + " and a.CLFWPC_SX = 3 ";
			}
		}
		
		if(!CITY_ID.equals("null") && CITY_ID.length()>0){
			if(ClfwCommon.isMunicipalitiesID(CITY_ID.substring(0,2))){
				sqlwhere = sqlwhere + " and c.CLFWPCSHI_DISTCD like '" + CITY_ID.substring(0, 2) + "%'";
			}else{
				sqlwhere = sqlwhere + " and c.CLFWPCSHI_DISTCD like '" + CITY_ID.substring(0, 4) + "%'";
			}
		}


		sql = sql + sqlfrom + sqlwhere + sqlorder;
		sqlCount = sqlCount + sqlfrom + sqlwhere;

		page.setPageSize(99999);
		if (pageNo != 0) {
			page.setPageNo(pageNo);
		}
		page = clfwQueryDao.getBeanQueryClfwpcshi(sql, sqlCount, page);
		return "json";
	}
	
	@SuppressWarnings("unchecked")
	public String queryclfwtdbcq() throws Exception {
		
		if(!userflag()){return "json";}
		
		String sql=" ";
		String sqlCount=" ";
		String sqlfrom="  ";
		String sqlwhere=" ";
		String sqlorder=" order by c.district_code,a.dm_pk_code ";

		if (!RAD_CLFWPC_QY.equals("null") && RAD_CLFWPC_QY.length() > 0) {
			if (RAD_CLFWPC_QY.equals("1")) {
				sql="select b.CLFWTDBCQ_SEQID,a.dm_pk_code,a.dm_name,a.office_code,decode(c.city_name,null,c.province_name,c.city_name)||c.county_name CLFWTDB_DISTCDSTR,b.CLFWTDB_FW,b.CLFWTDBCQ_SEQID NULLFLAG ";
				sqlCount="select count(*) ";
				sqlfrom=" from CP_WH_DEPARTMENT a,CLFW_CLFWTDBCQ b,cp_base_org_district c ";
				sqlwhere=" where b.CLFWTDB_DISTCD = c.district_code and a.DM_PK_CODE = b.DM_PK_CODE  ";
			} 
			else if (RAD_CLFWPC_QY.equals("0")) {
				sql="select '' CLFWTDBCQ_SEQID,a.dm_pk_code,a.dm_name,a.office_code,decode(c.city_name,null,c.province_name,c.city_name)||c.county_name CLFWTDB_DISTCDSTR,'' CLFWTDB_FW,'' NULLFLAG ";
				sqlCount="select count(*) ";
				sqlfrom=" from CP_WH_DEPARTMENT a,cp_base_org_district c ";
				sqlwhere=" where a.city_code = c.district_code  ";
			}
		}
		
		if(!COUNTY_ID.equals("null") && COUNTY_ID.length()>0){
			sqlwhere = sqlwhere + " and c.district_code = '" + COUNTY_ID + "'";
		}else if(!CITY_ID.equals("null") && CITY_ID.length()>0){
			if(CITY_ID.substring(4, 6).equals("00")){
				sqlwhere = sqlwhere + " and c.district_code like '" + CITY_ID.substring(0, 4) + "%'";
			}else{
				sqlwhere = sqlwhere + " and c.district_code like '" + CITY_ID + "%'";
			}
		}else if(!PROVINCE_ID.equals("null") && PROVINCE_ID.length()>0){
			sqlwhere = sqlwhere + " and c.district_code like '" + PROVINCE_ID.substring(0, 2) + "%'";
		}
		
		if(!SEL_DM_NAME.equals("null") && SEL_DM_NAME.length()>0){
			sqlwhere = sqlwhere + " and  a.DM_NAME like '%" + SEL_DM_NAME + "%' ";
		}

		sql=sql+sqlfrom+sqlwhere+sqlorder;
		sqlCount=sqlCount+sqlfrom+sqlwhere;
		
		page.setPageSize(10);
		if(pageNo!=0){
			page.setPageNo(pageNo);
		}
		page=clfwQueryDao.getBeanQueryClfwtdbcq(sql, sqlCount, page);
		return "json";
	}
	
	@SuppressWarnings("unchecked")
	public String queryclfwtdbsh() throws Exception {
		
		if(!userflag()){return "json";}
		
		String sql="select a.dm_pk_code,a.dm_name,a.office_code,decode(b.city_name,null,b.province_name,b.city_name) CLFWTDB_DISTCDSTR," +
				"c.CLFWTDB_SEQID,'['||f.CLFWPC_MC||decode(f.CLFWPC_SX,1,decode(substr(f.CLFWPC_MC,0,1),'Z','(直达)','(省中心)'),2,'(揽收)',3,'(投递)','(未配置)')||']'||d.CLFWPCSHI_SHIGM CLFWPCSHI_SHIGM ," +
				"decode(e.CLFWPCSHENG_SHENGM,null,'未配置衔接频次',e.CLFWPCSHENG_SHENGM) CLFWPCSHENG_SHENGM," +
				"decode(c.clfwtdb_clsx,0,'X-全否',1,'V-全是',2,'C-部分','?-无配置') CLFWTDB_CLSXSTR,c.CLFWTDB_SJYXSC,c.CLFWTDB_LSLZB," +
				"decode(c.CLFWTDB_SHSX,0,'?-待审核',1,'V-已通过',2,'X-已拒绝','N-无状态') CLFWTDB_SHSXSTR, " +
				"decode(c.CLFWTDB_SHCZ,0,'【删除数据】',1,'【全是】->【全否】',2,'【全是】->【部分】',' ') CLFWTDB_SHCZSTR " ;
		String sqlCount="select count(*) ";
		String sqlfrom=" from " +
				" CP_WH_DEPARTMENT a,cp_base_org_district b,CLFW_CLFWTDB c,CLFW_CLFWPC_SHI d," +
				"(select " +
				" eb.CLFWPCSHENG_SEQID CLFWPCSHENG_SEQID," +
				" '['||ea.CLFWPC_MC||decode(ea.CLFWPC_SX,1,decode(substr(ea.CLFWPC_MC,0,1),'Z','(直达)','(省中心)'),2,'(揽收)',3,'(投递)','(未配置)')||']'||eb.CLFWPCSHENG_SHENGM CLFWPCSHENG_SHENGM"+
				" from clfw_clfwpc ea,CLFW_CLFWPC_SHENG eb where ea.clfwpc_seqid = eb.clfwpc_seqid";
				sqlfrom=sqlfrom + ") e," +
				" CLFW_CLFWPC f ";

		String sqlwhere=" where 1=1 " +
				" and c.CLFWTDB_SHSX = '0' " +
				" and a.dm_pk_code = c.dm_pk_code " +
				" and d.CLFWPCSHI_SEQID = c.CLFWPCSHI_SEQID " +
				" and d.CLFWPC_SEQID = f.CLFWPC_SEQID " +
				" and d.CLFWPCSHENG_SEQID = e.CLFWPCSHENG_SEQID(+) " +
				" and a.city_code = b.district_code ";
		String sqlorder=" order by CLFWPCSHI_SHIGM,CLFWPCSHENG_SHENGM,a.dm_name ";
		
		if (!SEL_CLFWPCSHENG_SHENGM.equals("null") && SEL_CLFWPCSHENG_SHENGM.length() > 0) {
			sqlwhere = sqlwhere + " and e.CLFWPCSHENG_SHENGM like '%" + SEL_CLFWPCSHENG_SHENGM + "%'";
		}
		
		if (!SEL_CLFWPCSHI_SHIGM.equals("null") && SEL_CLFWPCSHI_SHIGM.length() > 0) {
			sqlwhere = sqlwhere + " and d.CLFWPCSHI_SHIGM like '%" + SEL_CLFWPCSHI_SHIGM + "%'";
		}

		if (!RAD_CLFWPC_SX.equals("null") && RAD_CLFWPC_SX.length() > 0) {
			if (RAD_CLFWPC_SX.equals("2")) {
				sqlwhere = sqlwhere + " and f.CLFWPC_SX = 2 ";
			} else if (RAD_CLFWPC_SX.equals("3")) {
				sqlwhere = sqlwhere + " and f.CLFWPC_SX = 3 ";
			}
		}
		
		if(!CITY_ID.equals("null") && CITY_ID.length()>0){
			if(CITY_ID.substring(4, 6).equals("00")){
				sqlwhere = sqlwhere + " and a.city_code like '" + CITY_ID.substring(0, 4) + "%'";
			}else{
				sqlwhere = sqlwhere + " and a.city_code like '" + CITY_ID + "%'";
			}
		}else if(!PROVINCE_ID.equals("null") && PROVINCE_ID.length()>0){
			sqlwhere = sqlwhere + " and a.city_code like '" + PROVINCE_ID.substring(0, 2) + "%'";
		}
		
		if(!SEL_DM_NAME.equals("null") && SEL_DM_NAME.length()>0){
			sqlwhere = sqlwhere + " and  a.DM_NAME like '%" + SEL_DM_NAME + "%' ";
		}
		
		if(!SEL_CLFWTDB_CLSX.equals("null") && SEL_CLFWTDB_CLSX.length()>0){
			if(SEL_CLFWTDB_CLSX.equals("2")){
				sqlwhere = sqlwhere + " and  c.CLFWTDB_CLSX = 2 ";
			}else if(SEL_CLFWTDB_CLSX.equals("1")){
				sqlwhere = sqlwhere + " and c.CLFWTDB_CLSX = 1 ";
			}else if(SEL_CLFWTDB_CLSX.equals("0")){
				sqlwhere = sqlwhere + " and c.CLFWTDB_CLSX = 0 ";
			}
		}

		sql=sql+sqlfrom+sqlwhere+sqlorder;
		sqlCount=sqlCount+sqlfrom+sqlwhere;
		
		page.setPageSize(20);
		if(pageNo!=0){
			page.setPageNo(pageNo);
		}
		page=clfwQueryDao.getBeanQueryClfwtdbsh(sql, sqlCount, page);
		return "json";
	}
	
	@SuppressWarnings("unchecked")
	public String queryclfwtdb() throws Exception {
		
		if(!userflag()){return "json";}
		
		String sql="select a.dm_pk_code,a.dm_name,a.office_code,decode(b.city_name,null,b.province_name,b.city_name) CLFWTDB_DISTCDSTR," +
				"c.CLFWTDB_SEQID,'['||f.CLFWPC_MC||decode(f.CLFWPC_SX,1,decode(substr(f.CLFWPC_MC,0,1),'Z','(直达)','(省中心)'),2,'(揽收)',3,'(投递)','(未配置)')||']'||d.CLFWPCSHI_SHIGM CLFWPCSHI_SHIGM ," +
				"decode(e.CLFWPCSHENG_SHENGM,null,'未配置衔接频次',e.CLFWPCSHENG_SHENGM) CLFWPCSHENG_SHENGM," +
				"decode(c.clfwtdb_clsx,0,'X-全否',1,'V-全是',2,'C-部分','?-无配置') CLFWTDB_CLSXSTR,c.CLFWTDB_SJYXSC,c.CLFWTDB_LSLZB," +
				"decode(c.CLFWTDB_SHSX,0,'?-待审核',1,'V-已通过',2,'X-已拒绝','N-无状态') CLFWTDB_SHSXSTR, " +
				"decode(c.CLFWTDB_SHCZ,0,'【删除数据】',1,'【全是】->【全否】',2,'【全是】->【部分】',' ') CLFWTDB_SHCZSTR " ;
		String sqlCount="select count(*) ";
		String sqlfrom=" from " +
				" CP_WH_DEPARTMENT a,cp_base_org_district b,CLFW_CLFWTDB c,CLFW_CLFWPC_SHI d," +
				"(select " +
				" eb.CLFWPCSHENG_SEQID CLFWPCSHENG_SEQID," +
				" '['||ea.CLFWPC_MC||decode(ea.CLFWPC_SX,1,decode(substr(ea.CLFWPC_MC,0,1),'Z','(直达)','(省中心)'),2,'(揽收)',3,'(投递)','(未配置)')||']'||eb.CLFWPCSHENG_SHENGM CLFWPCSHENG_SHENGM"+
				" from clfw_clfwpc ea,CLFW_CLFWPC_SHENG eb where ea.clfwpc_seqid = eb.clfwpc_seqid";
				sqlfrom=sqlfrom + ") e," +
				" CLFW_CLFWPC f ";

		String sqlwhere=" where 1=1 " +
				" and a.dm_pk_code = c.dm_pk_code " +
				" and d.CLFWPCSHI_SEQID = c.CLFWPCSHI_SEQID " +
				" and d.CLFWPC_SEQID = f.CLFWPC_SEQID " +
				" and d.CLFWPCSHENG_SEQID = e.CLFWPCSHENG_SEQID(+) " +
				" and a.city_code = b.district_code ";
		String sqlorder=" order by CLFWPCSHI_SHIGM,CLFWPCSHENG_SHENGM,a.dm_name ";
		
		if (!SEL_CLFWPCSHENG_SHENGM.equals("null") && SEL_CLFWPCSHENG_SHENGM.length() > 0) {
			sqlwhere = sqlwhere + " and e.CLFWPCSHENG_SHENGM like '%" + SEL_CLFWPCSHENG_SHENGM + "%'";
		}
		
		if (!SEL_CLFWPCSHI_SHIGM.equals("null") && SEL_CLFWPCSHI_SHIGM.length() > 0) {
			sqlwhere = sqlwhere + " and d.CLFWPCSHI_SHIGM like '%" + SEL_CLFWPCSHI_SHIGM + "%'";
		}

		if (!RAD_CLFWPC_SX.equals("null") && RAD_CLFWPC_SX.length() > 0) {
			if (RAD_CLFWPC_SX.equals("2")) {
				sqlwhere = sqlwhere + " and f.CLFWPC_SX = 2 ";
			} else if (RAD_CLFWPC_SX.equals("3")) {
				sqlwhere = sqlwhere + " and f.CLFWPC_SX = 3 ";
			}
		}
		
		if(!CITY_ID.equals("null") && CITY_ID.length()>0){
			if(CITY_ID.substring(4, 6).equals("00")){
				sqlwhere = sqlwhere + " and a.city_code like '" + CITY_ID.substring(0, 4) + "%'";
			}else{
				sqlwhere = sqlwhere + " and a.city_code like '" + CITY_ID + "%'";
			}
		}else if(!PROVINCE_ID.equals("null") && PROVINCE_ID.length()>0){
			sqlwhere = sqlwhere + " and a.city_code like '" + PROVINCE_ID.substring(0, 2) + "%'";
		}
		
		if(!SEL_DM_NAME.equals("null") && SEL_DM_NAME.length()>0){
			sqlwhere = sqlwhere + " and  a.DM_NAME like '%" + SEL_DM_NAME + "%' ";
		}
		
		if(!SEL_CLFWTDB_CLSX.equals("null") && SEL_CLFWTDB_CLSX.length()>0){
			if(SEL_CLFWTDB_CLSX.equals("2")){
				sqlwhere = sqlwhere + " and  c.CLFWTDB_CLSX = 2 ";
			}else if(SEL_CLFWTDB_CLSX.equals("1")){
				sqlwhere = sqlwhere + " and c.CLFWTDB_CLSX = 1 ";
			}else if(SEL_CLFWTDB_CLSX.equals("0")){
				sqlwhere = sqlwhere + " and c.CLFWTDB_CLSX = 0 ";
			}
		}

		sql=sql+sqlfrom+sqlwhere+sqlorder;
		sqlCount=sqlCount+sqlfrom+sqlwhere;
		
		page.setPageSize(20);
		if(pageNo!=0){
			page.setPageNo(pageNo);
		}
		page=clfwQueryDao.getBeanQueryClfwtdb(sql, sqlCount, page);
		return "json";
	}
	
	@SuppressWarnings("unchecked")
	public String queryclfwtdd() throws Exception {
		
		if(!userflag()){return "json";}
		
		String sql="select b.pg_pk_code,c.clfwtdd_seqid,decode(c.clfwtdd_clsx, 0, 'X-全否', 1, 'V-全是', 'C-无配置') clfwtdb_clsxstr,b.pg_name,c.OPE_REMARK ";
		String sqlCount="select count(*) ";
		String sqlfrom=" from clfw_clfwtdb a,cp_wh_postseg b,clfw_clfwtdd c ";

		String sqlwhere=" where 1=1 " +
				" and a.dm_pk_code = b.dm_pk_code " +
				" and b.pg_pk_code = c.pg_pk_code(+) ";
		
		if(!CLFWTDB_SEQID.equals("null") && CLFWTDB_SEQID.length()>0){
			sqlwhere = sqlwhere + " and  a.CLFWTDB_SEQID = '" + CLFWTDB_SEQID + "' ";
		}
		
		if(!SEL_PG_NAME.equals("null") && SEL_PG_NAME.length()>0){
			sqlwhere = sqlwhere + " and  b.PG_NAME like '%" + SEL_PG_NAME + "%' ";
		}
		
		if(!SEL_CLFWTDD_CLSX.equals("null") && SEL_CLFWTDD_CLSX.length()>0){
			if(SEL_CLFWTDD_CLSX.equals("0")){
				sqlwhere = sqlwhere + " and  c.CLFWTDD_CLSX = 0 ";
			}else if(SEL_CLFWTDD_CLSX.equals("1")){
				sqlwhere = sqlwhere + " and c.CLFWTDD_CLSX = 1 ";
			}else if(SEL_CLFWTDD_CLSX.equals("2")){
				sqlwhere = sqlwhere + " and c.CLFWTDD_CLSX is null ";
			}
		}
		
		
		String sqlorder=" order by a.dm_pk_code,b.pg_pk_code ";

		sql=sql+sqlfrom+sqlwhere+sqlorder;
		sqlCount=sqlCount+sqlfrom+sqlwhere;
		
		page.setPageSize(50);
		if(pageNo!=0){
			page.setPageNo(pageNo);
		}
		page=clfwQueryDao.getBeanQueryClfwtdd(sql, sqlCount, page);
		return "json";
	}
	
	@SuppressWarnings("unchecked")
	public String queryclfwsjcx() throws Exception {
		
		if(!userflag()){return "json";}
		
		String sql="select b.province_name||b.city_name||b.county_name pccname,c.dm_name,d.strt1_name||d.strt2_name||d.strt3_name||d.strt4_name||d.strt5_name strtallname,a.prefix || a.start_num || '-' || a.end_num || a.suffix pnume, decode(a.flag,5,'单',6,'双','不分') flagstr,a.rl_pg_st_id,e.PG_NAME ";
		String sqlCount="select count(*) ";
		String sqlfrom=" from cp_wh_rl_pg_st_" + PROVINCE_ID.substring(0, 2) + "  a," +
				" cp_base_org_district b," +
				" cp_wh_department c," +
				" cp_mk_adr_street_" + PROVINCE_ID.substring(0, 2) + "  d ," +
				" CP_WH_POSTSEG e";
		String sqlwhere=" where a.org_id||a.rsdnbldg_id is null " +
				"and a.dist_cd = b.district_code " +
				"and a.strt_id = d.strt_id ";
		String sqlorder=" order by pccname,strtallname ";
		
		if(!COUNTY_ID.equals("null") && COUNTY_ID.length()>0){
			sqlwhere = sqlwhere + " and a.dist_cd = '" + COUNTY_ID + "'";
		}else if(!CITY_ID.equals("null") && CITY_ID.length()>0){
			if(CITY_ID.substring(4, 6).equals("00")){
				sqlwhere = sqlwhere + " and a.dist_cd like '" + CITY_ID.substring(0, 4) + "%'";
			}else{
				sqlwhere = sqlwhere + " and a.dist_cd like '" + CITY_ID + "%'";
			}
		}else if(!PROVINCE_ID.equals("null") && PROVINCE_ID.length()>0){
			sqlwhere = sqlwhere + " and a.dist_cd like '" + PROVINCE_ID.substring(0, 2) + "%'";
		}
		
		if(!SEL_DM_NAME.equals("null") && SEL_DM_NAME.length()>0){
			sqlwhere = sqlwhere + " and  a.dm_pk_code = c.dm_pk_code and c.DM_NAME like '%" + SEL_DM_NAME + "%' ";
		}else{
			sqlwhere = sqlwhere + " and a.dm_pk_code = c.dm_pk_code(+) ";
		}
		
		if(!SEL_PG_NAME.equals("null") && SEL_PG_NAME.length()>0){
			sqlwhere = sqlwhere + "  and a.PG_PK_CODE = e.PG_PK_CODE and  e.PG_NAME like '%" + SEL_PG_NAME + "%' ";
		}else{
			sqlwhere = sqlwhere + " and a.PG_PK_CODE = e.PG_PK_CODE(+) ";
		}

		sql=sql+sqlfrom+sqlwhere+sqlorder;
		sqlCount=sqlCount+sqlfrom+sqlwhere;
		
		page.setPageSize(20);
		if(pageNo!=0){
			page.setPageNo(pageNo);
		}
		page=clfwQueryDao.getBeanQueryClfwsjcx(sql, sqlCount, page);
		return "json";
	}
	
	@SuppressWarnings("unchecked")
	public String queryclfwtdbtj() throws Exception {
		
		if(!userflag()){return "json";}
		
		String sql="select a.city_code,decode(b.city_name,null,b.province_name,b.city_name)||b.county_name CLFWTDB_DISTCDSTR,a.clfwtdb_clsx,a.dm_name,a.clfwtdb_fw,a.CLFWPCSHENG_SHENGM,a.CLFWPCSHI_SHIGM ";
		
		String sqlCount="select count(*) ";
		
		String sqlt=" select t.CLFWPCSHENG_SHENGM,t.CLFWPCSHI_SHIGM,t.dm_pk_code,t.city_code,t.clfwtdb_clsx,max(t.dm_name) dm_name,max(t.clfwtdb_fw) clfwtdb_fw from clfw_clfwtdbcq_view t where 1=1 ";
				
		if (!SEL_CLFWPCSHENG_SHENGM.equals("null") && SEL_CLFWPCSHENG_SHENGM.length() > 0) {
			sqlt = sqlt + " and t.CLFWPCSHENG_SHENGM like '%" + SEL_CLFWPCSHENG_SHENGM + "%'";
		}
		
		if (!SEL_CLFWPCSHI_SHIGM.equals("null") && SEL_CLFWPCSHI_SHIGM.length() > 0) {
			sqlt = sqlt + " and t.CLFWPCSHI_SHIGM like '%" + SEL_CLFWPCSHI_SHIGM + "%'";
		}

		if (!RAD_CLFWPC_SX.equals("null") && RAD_CLFWPC_SX.length() > 0) {
			if (RAD_CLFWPC_SX.equals("2")) {
				sqlt = sqlt + " and t.CLFWPC_SX = 2 ";
			} else if (RAD_CLFWPC_SX.equals("3")) {
				sqlt = sqlt + " and t.CLFWPC_SX = 3 ";
			}
		}
		
		if(!COUNTY_ID.equals("null") && COUNTY_ID.length()>0){
					sqlt = sqlt + " and t.city_code = '" + COUNTY_ID + "'";
				}else if(!CITY_ID.equals("null") && CITY_ID.length()>0){
					if(CITY_ID.substring(4, 6).equals("00")){
						sqlt = sqlt + " and t.city_code like '" + CITY_ID.substring(0, 4) + "%'";
					}else{
						sqlt = sqlt + " and t.city_code like '" + CITY_ID + "%'";
					}
				}else if(!PROVINCE_ID.equals("null") && PROVINCE_ID.length()>0){
					sqlt = sqlt + " and t.city_code like '" + PROVINCE_ID.substring(0, 2) + "%'";
				}
		
				sqlt= sqlt + "group by t.dm_pk_code,t.city_code,t.clfwtdb_clsx,t.CLFWPCSHENG_SHENGM,t.CLFWPCSHI_SHIGM";
		
		String sqlfrom=" from " +
				" (" + sqlt + ")a ,CP_BASE_ORG_DISTRICT b ";

		String sqlwhere=" where a.city_code = b.district_code ";
		String sqlorder=" order by a.CLFWPCSHENG_SHENGM,a.CLFWPCSHI_SHIGM,b.district_code,a.clfwtdb_clsx ";
		
		

		sql=sql+sqlfrom+sqlwhere+sqlorder;
		sqlCount=sqlCount+sqlfrom+sqlwhere;
		
		page.setPageSize(99999);
		if(pageNo!=0){
			page.setPageNo(pageNo);
		}
		page=clfwQueryDao.getBeanQueryClfwtdbtj(sql, sqlCount, page);
		
		return "json";
	}
	
	@SuppressWarnings("unchecked")
	public String queryclfwkffwpz() throws Exception {
		
		if(!userflag()){return "json";}
		
		String sql="select a.CLFWKFFW_SEQID,decode(b.city_name,null,b.province_name,b.city_name)||b.county_name CLFWKFFW_DISTCDSTR,a.CLFWKFFW_YTEFW,a.CLFWKFFW_SWSFW";
		String sqlCount="select count(*) ";
		String sqlfrom=" from CLFW_KFFW a,cp_base_org_district b ";
		String sqlwhere=" where a.CLFWKFFW_DISTCD = b.district_code";
		String sqlorder=" order by b.district_code ";
		
		if(!COUNTY_ID.equals("null") && COUNTY_ID.length()>0){
			sqlwhere = sqlwhere + " and a.CLFWKFFW_DISTCD = '" + COUNTY_ID + "'";
		}else if(!CITY_ID.equals("null") && CITY_ID.length()>0){
			if(CITY_ID.substring(4, 6).equals("00")){
				sqlwhere = sqlwhere + " and a.CLFWKFFW_DISTCD like '" + CITY_ID.substring(0, 4) + "%'";
			}else{
				sqlwhere = sqlwhere + " and a.CLFWKFFW_DISTCD like '" + CITY_ID + "%'";
			}
		}else if(!PROVINCE_ID.equals("null") && PROVINCE_ID.length()>0){
			sqlwhere = sqlwhere + " and a.CLFWKFFW_DISTCD like '" + PROVINCE_ID.substring(0, 2) + "%'";
		}

		sql=sql+sqlfrom+sqlwhere+sqlorder;
		sqlCount=sqlCount+sqlfrom+sqlwhere;
		
		page.setPageSize(99999);
		if(pageNo!=0){
			page.setPageNo(pageNo);
		}
		page=clfwQueryDao.getBeanQueryClfwkffwpz(sql, sqlCount, page);
		return "json";
	}
	
	@SuppressWarnings("unchecked")
	public String queryclfwhkwh() throws Exception {
		
		if(!userflag()){return "json";}

		String sql = "select CLFW_HBXXGL_SEQID,CLFW_HBXXGL_XSBZ," +
				" decode(f.city_name,null,f.province_name,f.city_name)||f.county_name CLFW_HBXXGL_SFJIDSTR," +
				" decode(g.city_name,null,g.province_name,g.city_name)||g.county_name CLFW_HBXXGL_ZDJIDSTR," +
				" CLFW_HBXXGL_CSRQ,CLFW_HBXXGL_HBH,CLFW_HBXXGL_ZBS,CLFW_HBXXGL_ZL,CLFW_HBXXGL_LDZBS,CLFW_HBXXGL_SSZBS,CLFW_HBXXGL_QYRQ,CLFW_HBXXGL_JZRQ,CLFW_HBXXGL_LDLSH ";
		String sqlCount = "select count(*) ";
		
		String sqlfrom = " from ( ";
		
		sqlfrom += " select d.CLFW_HBXXGL_SEQID,d.CLFW_HBXXGL_CLSX CLFW_HBXXGL_XSBZ,CLFW_HBXXGL_SFJID,CLFW_HBXXGL_ZDJID,CLFW_HBXXGL_CSRQ,CLFW_HBXXGL_HBH,CLFW_HBXXGL_ZBS,CLFW_HBXXGL_ZL,CLFW_HBXXGL_LDZBS,CLFW_HBXXGL_SSZBS,CLFW_HBXXGL_QYRQ,CLFW_HBXXGL_JZRQ,CLFW_HBXXGL_LDLSH " +
				" from  CLFW_HBXXGL d " +
				" where 1=1 and CLFW_HBXXGL_CLSX = '0' ";
		
		if(!ITEM_DATESS.equals("null") && ITEM_DATESS.length()>0){
			sqlfrom = sqlfrom + " and d.CLFW_HBXXGL_CSRQ  >= '" + ITEM_DATESS + "' ";
		}
		
		if(!ITEM_DATESE.equals("null") && ITEM_DATESE.length()>0){
			sqlfrom = sqlfrom + " and d.CLFW_HBXXGL_CSRQ  <= '" + ITEM_DATESE + "' ";
		}

		if(!COUNTY_ID.equals("null") && COUNTY_ID.length()>0){
			sqlfrom = sqlfrom + " and d.CLFW_HBXXGL_SFJID = '" + COUNTY_ID + "'";
				}else if(!CITY_ID.equals("null") && CITY_ID.length()>0){
					if(CITY_ID.substring(4, 6).equals("00")){
						sqlfrom = sqlfrom + " and d.CLFW_HBXXGL_SFJID like '" + CITY_ID.substring(0, 4) + "%'";
					}else{
						sqlfrom = sqlfrom + " and d.CLFW_HBXXGL_SFJID like '" + CITY_ID + "%'";
					}
				}else if(!PROVINCE_ID.equals("null") && PROVINCE_ID.length()>0){
					sqlfrom = sqlfrom + " and d.CLFW_HBXXGL_SFJID like '" + PROVINCE_ID.substring(0, 2) + "%'";
				}
		
		sqlfrom += " union ";
		
		sqlfrom += " select d.CLFW_HBXXGL_SEQID,d.CLFW_HBXXGL_CLSX CLFW_HBXXGL_XSBZ,CLFW_HBXXGL_SFJID,CLFW_HBXXGL_ZDJID,CLFW_HBXXGL_CSRQ,CLFW_HBXXGL_HBH,CLFW_HBXXGL_ZBS,CLFW_HBXXGL_ZL,CLFW_HBXXGL_LDZBS,CLFW_HBXXGL_SSZBS,CLFW_HBXXGL_QYRQ,CLFW_HBXXGL_JZRQ,CLFW_HBXXGL_LDLSH " +
		" from  CLFW_HBXXGL d " +
		" where 1=1  and CLFW_HBXXGL_CLSX = '1'  ";

		if(!ITEM_DATESS.equals("null") && ITEM_DATESS.length()>0){
			sqlfrom = sqlfrom + " and d.CLFW_HBXXGL_CSRQ  >= '" + ITEM_DATESS + "' ";
		}
		
		if(!ITEM_DATESE.equals("null") && ITEM_DATESE.length()>0){
			sqlfrom = sqlfrom + " and d.CLFW_HBXXGL_CSRQ  <= '" + ITEM_DATESE + "' ";
		}
		
		if(!COUNTY_ID.equals("null") && COUNTY_ID.length()>0){
			sqlfrom = sqlfrom + " and d.CLFW_HBXXGL_ZDJID = '" + COUNTY_ID + "'";
				}else if(!CITY_ID.equals("null") && CITY_ID.length()>0){
					if(CITY_ID.substring(4, 6).equals("00")){
						sqlfrom = sqlfrom + " and d.CLFW_HBXXGL_ZDJID like '" + CITY_ID.substring(0, 4) + "%'";
					}else{
						sqlfrom = sqlfrom + " and d.CLFW_HBXXGL_ZDJID like '" + CITY_ID + "%'";
					}
				}else if(!PROVINCE_ID.equals("null") && PROVINCE_ID.length()>0){
					sqlfrom = sqlfrom + " and d.CLFW_HBXXGL_ZDJID like '" + PROVINCE_ID.substring(0, 2) + "%'";
				}
		
		sqlfrom += " ) e,CP_BASE_ORG_DISTRICT f,CP_BASE_ORG_DISTRICT g ";

		String sqlwhere = " where 1=1 and e.CLFW_HBXXGL_SFJID = f.DISTRICT_CODE and e.CLFW_HBXXGL_ZDJID = g.DISTRICT_CODE ";
		String sqlorder = " order by CLFW_HBXXGL_CSRQ,CLFW_HBXXGL_SFJID,CLFW_HBXXGL_HBH,CLFW_HBXXGL_ZDJID ";


		sql = sql + sqlfrom + sqlwhere + sqlorder;
		sqlCount = sqlCount + sqlfrom + sqlwhere;

		page.setPageSize(99999);
		if (pageNo != 0) {
			page.setPageNo(pageNo);
		}
		page = clfwQueryDao.getBeanQueryClfwhkwh(sql, sqlCount, page);
		return "json";
	}
	
	@SuppressWarnings("unchecked")
	public String queryclfwylxxbt() throws Exception {
		
		if(!userflag()){return "json";}

		String sql = "select CLFWYLXXB_SEQID,CLFWYLXXB_FLAG,CLFWYLXXBZ_SEQID,CLFWYLXXBZ_YLMC,CLFWYLXXB_XSBZ,CLFWYLXXB_LSH,CLFWYLXXB_CPH," +
				" decode(f.city_name,null,f.province_name,f.city_name)||f.county_name CLFWYLXXB_SFJID_STR,CLFWYLXXB_SJKCSJ," +
				" decode(g.city_name,null,g.province_name,g.city_name)||g.county_name CLFWYLXXB_ZDJID_STR,CLFWYLXXB_SJDDSJ," +
				" CLFWYLXXB_QYRQ,CLFWYLXXB_JZRQ,CLFWYLXXB_CSRQ,CLFWYLXXB_SFJGHSM ";
		String sqlCount = "select count(*) ";
		
		String sqlfrom = " from ( ";
		
		sqlfrom += " select d.CLFWYLXXB_SEQID,'1' CLFWYLXXB_FLAG,c.CLFWYLXXBZ_SEQID,c.CLFWYLXXBZ_YLMC,'1' CLFWYLXXB_XSBZ,d.CLFWYLXXB_LSH,d.CLFWYLXXB_CPH,c.CLFWYLXXBZ_SFJID,d.CLFWYLXXB_SJKCSJ,c.CLFWYLXXBZ_ZDJID,d.CLFWYLXXB_SJDDSJ,d.CLFWYLXXB_QYRQ,d.CLFWYLXXB_JZRQ,d.CLFWYLXXB_CSRQ,d.CLFWYLXXB_SFJGHSM " +
				" from  clfw_ylxxbz c,clfw_ylxxb d " +
				" where " +
				//加入clfwylxxbz_sybz判定是否历史记录
				" c.clfwylxxbz_sybz = 1 and " +
				" c.CLFWYLXXBZ_SEQID = d.CLFWYLXXBZ_SEQID and c.CLFWYLXXBZ_SFJID = '" + CITY_ID + "' and CLFWYLXXB_CSRQ = '" + SEL_CLFWYLXXB_CSRQ + "'" +
				" and decode('" + getSessionUser().getUsLoginId() + "','sjtb_njjsad','1','0') = CLFWYLXXB_SFJNJSX ";
		
		sqlfrom += " union ";
		
		sqlfrom += " select  d.CLFWYLXXB_SEQID,'3' CLFWYLXXB_FLAG,c.CLFWYLXXBZ_SEQID,c.CLFWYLXXBZ_YLMC,'3' CLFWYLXXB_XSBZ,d.CLFWYLXXB_LSH,d.CLFWYLXXB_CPH,c.CLFWYLXXBZ_SFJID,d.CLFWYLXXB_SJKCSJ,c.CLFWYLXXBZ_ZDJID,d.CLFWYLXXB_SJDDSJ,d.CLFWYLXXB_QYRQ,d.CLFWYLXXB_JZRQ,d.CLFWYLXXB_CSRQ,d.CLFWYLXXB_SFJGHSM " +
		" from  clfw_ylxxbz c,clfw_ylxxb d " +
		" where " +
		//加入clfwylxxbz_sybz判定是否历史记录
		" c.clfwylxxbz_sybz = 1 and " +
		" c.CLFWYLXXBZ_SEQID = d.CLFWYLXXBZ_SEQID and c.CLFWYLXXBZ_SFJID = '" + CITY_ID + "' and d.CLFWYLXXB_SJKCSJ is  null and CLFWYLXXB_CSRQ < '" + SEL_CLFWYLXXB_CSRQ + "'" +
				" and decode('" + getSessionUser().getUsLoginId() + "','sjtb_njjsad','1','0') = CLFWYLXXB_SFJNJSX ";

		sqlfrom += " union ";
		
		sqlfrom += " select d.CLFWYLXXB_SEQID,'2' CLFWYLXXB_FLAG,c.CLFWYLXXBZ_SEQID,c.CLFWYLXXBZ_YLMC,'2' CLFWYLXXB_XSBZ,d.CLFWYLXXB_LSH,d.CLFWYLXXB_CPH,c.CLFWYLXXBZ_SFJID,d.CLFWYLXXB_SJKCSJ,c.CLFWYLXXBZ_ZDJID,d.CLFWYLXXB_SJDDSJ,d.CLFWYLXXB_QYRQ,d.CLFWYLXXB_JZRQ,d.CLFWYLXXB_CSRQ,d.CLFWYLXXB_SFJGHSM " +
		" from  clfw_ylxxbz c,clfw_ylxxb d " +
		" where " +
		//加入clfwylxxbz_sybz判定是否历史记录
		" c.clfwylxxbz_sybz = 1 and " +
		" c.CLFWYLXXBZ_SEQID = d.CLFWYLXXBZ_SEQID and c.CLFWYLXXBZ_ZDJID = '" + CITY_ID + "' and d.CLFWYLXXB_SJKCSJ is not null  and  CLFWYLXXB_CSRQ = '" + SEL_CLFWYLXXB_CSRQ + "'" +
				" and decode('" + getSessionUser().getUsLoginId() + "','sjtb_njjsad','1','0') = CLFWYLXXB_ZDJNJSX ";
		
		
		sqlfrom += " union ";
		
		sqlfrom += " select d.CLFWYLXXB_SEQID,'4' CLFWYLXXB_FLAG,c.CLFWYLXXBZ_SEQID,c.CLFWYLXXBZ_YLMC,'4' CLFWYLXXB_XSBZ,d.CLFWYLXXB_LSH,d.CLFWYLXXB_CPH,c.CLFWYLXXBZ_SFJID,d.CLFWYLXXB_SJKCSJ,c.CLFWYLXXBZ_ZDJID,d.CLFWYLXXB_SJDDSJ,d.CLFWYLXXB_QYRQ,d.CLFWYLXXB_JZRQ,d.CLFWYLXXB_CSRQ,d.CLFWYLXXB_SFJGHSM " +
		" from  clfw_ylxxbz c,clfw_ylxxb d " +
		" where " +
		//加入clfwylxxbz_sybz判定是否历史记录
		" c.clfwylxxbz_sybz = 1 and " +
		" c.CLFWYLXXBZ_SEQID = d.CLFWYLXXBZ_SEQID and c.CLFWYLXXBZ_ZDJID = '" + CITY_ID + "' and d.CLFWYLXXB_SJKCSJ is not null and d.CLFWYLXXB_SJDDSJ is  null and  CLFWYLXXB_CSRQ < '" + SEL_CLFWYLXXB_CSRQ + "'" +
				" and decode('" + getSessionUser().getUsLoginId() + "','sjtb_njjsad','1','0') = CLFWYLXXB_ZDJNJSX ";
		
		sqlfrom += " ) e,CP_BASE_ORG_DISTRICT f,CP_BASE_ORG_DISTRICT g  ";

		String sqlwhere = " where 1=1 and e.CLFWYLXXBZ_SFJID = f.DISTRICT_CODE and e.CLFWYLXXBZ_ZDJID = g.DISTRICT_CODE ";
		
		String sqlorder = " order by CLFWYLXXB_CSRQ,CLFWYLXXBZ_SFJID,CLFWYLXXBZ_SEQID ";


		sql = sql + sqlfrom + sqlwhere + sqlorder;
		sqlCount = sqlCount + sqlfrom + sqlwhere;

		page.setPageSize(99999);
		if (pageNo != 0) {
			page.setPageNo(pageNo);
		}
		page = clfwQueryDao.getBeanQueryClfwylxxbt(sql, sqlCount, page);
		return "json";
	}
	
	
	@SuppressWarnings("unchecked")
	public String queryclfwresorgpz() throws Exception {
		
		if(!userflag()){return "json";}
		
		String sql=" ";
		String sqlCount=" ";
		String sqlfrom=" ";
		String sqlwhere=" ";
		String sqlorder=" ";
		
		if(RAD_CLFWPC_QY.equals("1")){
			sql="select c.ORG_CODE,a.ORG_CNAME,d.CLFWPCSHI_DISTCD,decode(b.city_name,null,b.province_name,b.city_name)||b.county_name CLFWRESORGPZ_DISTCDSTR," +
			"c.CLFWRESORGPZ_SEQID,'['||f.CLFWPC_MC||decode(f.CLFWPC_SX,1,decode(substr(f.CLFWPC_MC,0,1),'Z','(直达)','(省中心)'),2,'(揽收)',3,'(投递)','(未配置)')||']'||d.CLFWPCSHI_SHIGM CLFWPCSHI_SHIGM ," +
			"decode(e.CLFWPCSHENG_SHENGM,null,'未配置衔接频次',e.CLFWPCSHENG_SHENGM) CLFWPCSHENG_SHENGM," +
			"decode(c.CLFWRESORGPZ_CLSX,0,'X-全否',1,'V-全是',2,'C-部分','?-无配置') CLFWRESORGPZ_CLSXSTR,c.CLFWRESORGPZ_SJYXSC,c.CLFWRESORGPZ_LSLZB," +
			"decode(c.CLFWRESORGPZ_SHSX,0,'?-待审核',1,'V-已通过',2,'X-已拒绝','N-无状态') CLFWRESORGPZ_SHSXSTR, " +
			"decode(c.CLFWRESORGPZ_SHCZ,0,'【删除数据】',1,'【全是】->【全否】',2,'【全是】->【部分】',' ') CLFWRESORGPZ_SHCZSTR " ;
			sqlCount="select count(*) ";
			sqlfrom=" from " +
					" clfw_resorg_code a,cp_base_org_district b,clfw_resorg_pz c,CLFW_CLFWPC_SHI d," +
					"(select " +
					" eb.CLFWPCSHENG_SEQID CLFWPCSHENG_SEQID," +
					" '['||ea.CLFWPC_MC||decode(ea.CLFWPC_SX,1,decode(substr(ea.CLFWPC_MC,0,1),'Z','(直达)','(省中心)'),2,'(揽收)',3,'(投递)','(未配置)')||']'||eb.CLFWPCSHENG_SHENGM CLFWPCSHENG_SHENGM"+
					" from clfw_clfwpc ea,CLFW_CLFWPC_SHENG eb where ea.clfwpc_seqid = eb.clfwpc_seqid";
					sqlfrom=sqlfrom + ") e," +
					" CLFW_CLFWPC f ";
		
			sqlwhere=" where 1=1 " +
					" and a.ORG_CODE = c.ORG_CODE " +
					" and d.CLFWPCSHI_SEQID = c.CLFWPCSHI_SEQID " +
					" and d.CLFWPC_SEQID = f.CLFWPC_SEQID " +
					" and d.CLFWPCSHENG_SEQID = e.CLFWPCSHENG_SEQID(+) ";
			sqlorder=" order by CLFWPCSHI_SHIGM,CLFWPCSHENG_SHENGM,a.ORG_CNAME ";
			
			if (!SEL_CLFWPCSHENG_SHENGM.equals("null") && SEL_CLFWPCSHENG_SHENGM.length() > 0) {
				sqlwhere = sqlwhere + " and e.CLFWPCSHENG_SHENGM like '%" + SEL_CLFWPCSHENG_SHENGM + "%'";
			}
			
			if (!SEL_CLFWPCSHI_SHIGM.equals("null") && SEL_CLFWPCSHI_SHIGM.length() > 0) {
				sqlwhere = sqlwhere + " and d.CLFWPCSHI_SHIGM like '%" + SEL_CLFWPCSHI_SHIGM + "%'";
			}
		
			if (!RAD_CLFWPC_SX.equals("null") && RAD_CLFWPC_SX.length() > 0) {
				if (RAD_CLFWPC_SX.equals("2")) {
					sqlwhere = sqlwhere + " and f.CLFWPC_SX = 2 ";
				} else if (RAD_CLFWPC_SX.equals("3")) {
					sqlwhere = sqlwhere + " and f.CLFWPC_SX = 3 ";
				}
			}
			
			
			if(!SEL_CLFWTDB_CLSX.equals("null") && SEL_CLFWTDB_CLSX.length()>0){
				if(SEL_CLFWTDB_CLSX.equals("2")){
					sqlwhere = sqlwhere + " and  c.CLFWRESORGPZ_CLSX = 2 ";
				}else if(SEL_CLFWTDB_CLSX.equals("1")){
					sqlwhere = sqlwhere + " and c.CLFWRESORGPZ_CLSX = 1 ";
				}else if(SEL_CLFWTDB_CLSX.equals("0")){
					sqlwhere = sqlwhere + " and c.CLFWRESORGPZ_CLSX = 0 ";
				}
			}
			
			if(!SEL_ORG_CODE.equals("null") && SEL_ORG_CODE.length()>0){
				sqlwhere = sqlwhere + " and  c.ORG_CODE in (" + SEL_ORG_CODE + ") ";
			}
			
			if(!CITY_ID.equals("null") && CITY_ID.length()>0){
				if(CITY_ID.substring(4, 6).equals("00")){
					sqlwhere = sqlwhere + " and d.CLFWPCSHI_DISTCD like '" + CITY_ID.substring(0, 4) + "%'";
				}else{
					sqlwhere = sqlwhere + " and d.CLFWPCSHI_DISTCD like '" + CITY_ID + "%'";
				}
				sqlwhere = sqlwhere + " and d.CLFWPCSHI_DISTCD = b.district_code";
			}else if(!PROVINCE_ID.equals("null") && PROVINCE_ID.length()>0){
				sqlwhere = sqlwhere + " and d.CLFWPCSHI_DISTCD like '" + PROVINCE_ID.substring(0, 2) + "%'" +
						"  and d.CLFWPCSHI_DISTCD = b.district_code ";
			}
			
			
		}else if(RAD_CLFWPC_QY.equals("0")){
			sql="select a.ORG_CODE,a.ORG_CNAME,a.CITY_CODE,decode(b.city_name,null,b.province_name,b.city_name)||b.county_name CLFWRESORGPZ_DISTCDSTR," +
			"a.ORG_CODE CLFWRESORGPZ_SEQID,'--' CLFWPCSHI_SHIGM," +
			"'--' CLFWPCSHENG_SHENGM," +
			"'--' CLFWRESORGPZ_CLSXSTR,'--' CLFWRESORGPZ_SJYXSC,'--' CLFWRESORGPZ_LSLZB," +
			"'--' CLFWRESORGPZ_SHSXSTR, " +
			"'--' CLFWRESORGPZ_SHCZSTR " ;
			sqlCount="select count(*) ";
			sqlfrom=" from " +
					" clfw_resorg_code a,cp_base_org_district b";
		
			sqlwhere=" where 1=1 ";

			if(!SEL_ORG_CODE.equals("null") && SEL_ORG_CODE.length()>0){
				sqlwhere = sqlwhere + " and  a.ORG_CODE in (" + SEL_ORG_CODE + ") ";
			}
			
			if(!CITY_ID.equals("null") && CITY_ID.length()>0){
				if(CITY_ID.substring(4, 6).equals("00")){
					sqlwhere = sqlwhere + " and a.city_code like '" + CITY_ID.substring(0, 4) + "%'";
				}else{
					sqlwhere = sqlwhere + " and a.city_code like '" + CITY_ID + "%'";
				}
				sqlwhere = sqlwhere + " and a.CITY_CODE = b.district_code";
			}else if(!PROVINCE_ID.equals("null") && PROVINCE_ID.length()>0){
				sqlwhere = sqlwhere + " and a.prov_code like '" + PROVINCE_ID.substring(0, 2) + "%'" +
						"  and a.prov_code||'0000' = b.district_code ";
			}
			
			
			sqlorder=" order by a.ORG_CNAME ";

		}

		if(!SEL_DM_NAME.equals("null") && SEL_DM_NAME.length()>0){
			sqlwhere = sqlwhere + " and  a.ORG_CNAME like '%" + SEL_DM_NAME + "%' ";
		}


		sql=sql+sqlfrom+sqlwhere+sqlorder;
		sqlCount=sqlCount+sqlfrom+sqlwhere;
		
		page.setPageSize(20);
		if(pageNo!=0){
			page.setPageNo(pageNo);
		}
		page=clfwQueryDao.getBeanQueryClfwresorgpz(sql, sqlCount, page);
		return "json";
	}
	
	@SuppressWarnings("unchecked")
	public String queryclfwjgdm() throws Exception {
		
		if(!userflag()){return "json";}
		
		String sql=" ";
		String sqlCount=" ";
		String sqlfrom=" ";
		String sqlwhere=" ";
		String sqlorder=" ";
		
		if(RAD_CLFWPC_QY.equals("0")){
			
			sql="select c.ORG_CODE,a.ORG_CNAME," +
					" decode(b.city_name,null,b.province_name,b.city_name)||b.county_name CLFWRESORGPZ_DISTCDSTR,d.CLFWPCSHI_DISTCD, " +
			"'['||f.CLFWPC_MC||decode(f.CLFWPC_SX,1,decode(substr(f.CLFWPC_MC,0,1),'Z','(直达)','(省中心)'),2,'(揽收)',3,'(投递)','(未配置)')||']'||d.CLFWPCSHI_SHIGM CLFWPCSHI_SHIGM,d.CLFWPCSHI_SEQID ," +
			"decode(e.CLFWPCSHENG_SHENGM,null,'未配置衔接频次',e.CLFWPCSHENG_SHENGM) CLFWPCSHENG_SHENGM,e.CLFWPCSHENG_SEQID ";
			sqlCount="select count(*) ";
			sqlfrom=" from " +
					" clfw_resorg_code a,cp_base_org_district b,clfw_resorg_pz c,CLFW_CLFWPC_SHI d," +
					"(select " +
					" eb.CLFWPCSHENG_SEQID CLFWPCSHENG_SEQID," +
					" '['||ea.CLFWPC_MC||decode(ea.CLFWPC_SX,1,decode(substr(ea.CLFWPC_MC,0,1),'Z','(直达)','(省中心)'),2,'(揽收)',3,'(投递)','(未配置)')||']'||eb.CLFWPCSHENG_SHENGM CLFWPCSHENG_SHENGM"+
					" from clfw_clfwpc ea,CLFW_CLFWPC_SHENG eb where ea.clfwpc_seqid = eb.clfwpc_seqid";
					sqlfrom=sqlfrom + ") e," +
					" CLFW_CLFWPC f ";
		
			sqlwhere=" where 1=1 " +
					" and a.ORG_CODE = c.ORG_CODE " +
					" and d.CLFWPCSHI_SEQID = c.CLFWPCSHI_SEQID " +
					" and d.CLFWPC_SEQID = f.CLFWPC_SEQID " +
					" and d.CLFWPCSHENG_SEQID = e.CLFWPCSHENG_SEQID(+)" +
					" and d.CLFWPCSHI_DISTCD = b.district_code ";

			sqlorder=" order by CLFWPCSHI_SHIGM,CLFWPCSHENG_SHENGM ";
			
			if (!SEL_CLFWPCSHENG_SHENGM.equals("null") && SEL_CLFWPCSHENG_SHENGM.length() > 0) {
				sqlwhere = sqlwhere + " and e.CLFWPCSHENG_SHENGM like '%" + SEL_CLFWPCSHENG_SHENGM + "%'";
			}
			
			if (!SEL_CLFWPCSHI_SHIGM.equals("null") && SEL_CLFWPCSHI_SHIGM.length() > 0) {
				sqlwhere = sqlwhere + " and d.CLFWPCSHI_SHIGM like '%" + SEL_CLFWPCSHI_SHIGM + "%'";
			}
		
			if (!RAD_CLFWPC_SX.equals("null") && RAD_CLFWPC_SX.length() > 0) {
				if (RAD_CLFWPC_SX.equals("2")) {
					sqlwhere = sqlwhere + " and f.CLFWPC_SX = 2 ";
				} else if (RAD_CLFWPC_SX.equals("3")) {
					sqlwhere = sqlwhere + " and f.CLFWPC_SX = 3 ";
				}
			}
			
			if(!CITY_ID.equals("null") && CITY_ID.length()>0){
				if(CITY_ID.substring(4, 6).equals("00")){
					sqlwhere = sqlwhere + " and d.CLFWPCSHI_DISTCD like '" + CITY_ID.substring(0, 4) + "%'";
				}else{
					sqlwhere = sqlwhere + " and d.CLFWPCSHI_DISTCD like '" + CITY_ID + "%'";
				}
			}else if(!PROVINCE_ID.equals("null") && PROVINCE_ID.length()>0){
				sqlwhere = sqlwhere + " and d.CLFWPCSHI_DISTCD like '" + PROVINCE_ID.substring(0, 2) + "%'";
			}
			
			
		}else if(RAD_CLFWPC_QY.equals("1")){
			
			sql="select a.office_code ORG_CODE,a.DM_NAME, " +
			" decode(b.city_name,null,b.province_name,b.city_name)||b.county_name CLFWRESORGPZ_DISTCDSTR,d.CLFWPCSHI_DISTCD, " +
			"'['||f.CLFWPC_MC||decode(f.CLFWPC_SX,1,decode(substr(f.CLFWPC_MC,0,1),'Z','(直达)','(省中心)'),2,'(揽收)',3,'(投递)','(未配置)')||']'||d.CLFWPCSHI_SHIGM CLFWPCSHI_SHIGM,d.CLFWPCSHI_SEQID ," +
			"decode(e.CLFWPCSHENG_SHENGM,null,'未配置衔接频次',e.CLFWPCSHENG_SHENGM) CLFWPCSHENG_SHENGM,e.CLFWPCSHENG_SEQID ";
			sqlCount="select count(*) ";
			sqlfrom=" from " +
					" CP_WH_DEPARTMENT a,cp_base_org_district b,CLFW_CLFWTDB c,CLFW_CLFWPC_SHI d," +
					"(select " +
					" eb.CLFWPCSHENG_SEQID CLFWPCSHENG_SEQID," +
					" '['||ea.CLFWPC_MC||decode(ea.CLFWPC_SX,1,decode(substr(ea.CLFWPC_MC,0,1),'Z','(直达)','(省中心)'),2,'(揽收)',3,'(投递)','(未配置)')||']'||eb.CLFWPCSHENG_SHENGM CLFWPCSHENG_SHENGM"+
					" from clfw_clfwpc ea,CLFW_CLFWPC_SHENG eb where ea.clfwpc_seqid = eb.clfwpc_seqid";
					sqlfrom=sqlfrom + ") e," +
					" CLFW_CLFWPC f ";
		
			sqlwhere=" where 1=1 " +
					" and a.dm_pk_code = c.dm_pk_code " +
					" and d.CLFWPCSHI_SEQID = c.CLFWPCSHI_SEQID " +
					" and d.CLFWPC_SEQID = f.CLFWPC_SEQID " +
					" and d.CLFWPCSHENG_SEQID = e.CLFWPCSHENG_SEQID(+) " +
					" and d.CLFWPCSHI_DISTCD = b.district_code ";
			
			sqlorder=" order by CLFWPCSHI_SHIGM,CLFWPCSHENG_SHENGM ";
			
			if (!SEL_CLFWPCSHENG_SHENGM.equals("null") && SEL_CLFWPCSHENG_SHENGM.length() > 0) {
				sqlwhere = sqlwhere + " and e.CLFWPCSHENG_SHENGM like '%" + SEL_CLFWPCSHENG_SHENGM + "%'";
			}
			
			if (!SEL_CLFWPCSHI_SHIGM.equals("null") && SEL_CLFWPCSHI_SHIGM.length() > 0) {
				sqlwhere = sqlwhere + " and d.CLFWPCSHI_SHIGM like '%" + SEL_CLFWPCSHI_SHIGM + "%'";
			}
		
			if (!RAD_CLFWPC_SX.equals("null") && RAD_CLFWPC_SX.length() > 0) {
				if (RAD_CLFWPC_SX.equals("2")) {
					sqlwhere = sqlwhere + " and f.CLFWPC_SX = 2 ";
				} else if (RAD_CLFWPC_SX.equals("3")) {
					sqlwhere = sqlwhere + " and f.CLFWPC_SX = 3 ";
				}
			}
			
			if(!CITY_ID.equals("null") && CITY_ID.length()>0){
				if(CITY_ID.substring(4, 6).equals("00")){
					sqlwhere = sqlwhere + " and d.CLFWPCSHI_DISTCD like '" + CITY_ID.substring(0, 4) + "%'";
				}else{
					sqlwhere = sqlwhere + " and d.CLFWPCSHI_DISTCD like '" + CITY_ID + "%'";
				}
			}else if(!PROVINCE_ID.equals("null") && PROVINCE_ID.length()>0){
				sqlwhere = sqlwhere + " and d.CLFWPCSHI_DISTCD like '" + PROVINCE_ID.substring(0, 2) + "%'";
			}
				
			
		}
		
		sql=sql+sqlfrom+sqlwhere+sqlorder;
		sqlCount=sqlCount+sqlfrom+sqlwhere;
		
		page.setPageSize(99999);
		if(pageNo!=0){
			page.setPageNo(pageNo);
		}
		page=clfwQueryDao.getBeanQueryClfwjgdm(sql, sqlCount, page);
		return "json";
	}
	
	@SuppressWarnings("unchecked")
	public String querytjclfwylxxbt() throws Exception {
		
		if(!userflag()){return "json";}
		
		String sql="select b.CLFWYLXXB_CSRQ,a.CLFWYLXXBZ_YLMC,b.CLFWYLXXB_LSH,b.CLFWYLXXB_CPH," +
				" decode(c.city_name,null,c.province_name,c.city_name)||c.county_name||decode(a.CLFWYLXXB_SFJNJSX,'1','(南航)','') CLFWYLXXB_SFJID_STR,a.CLFWYLXXBZ_SJKCSJ,b.CLFWYLXXB_SJKCSJ," +
				" decode(d.city_name,null,d.province_name,d.city_name)||d.county_name||decode(a.CLFWYLXXB_ZDJNJSX,'1','(南航)','') CLFWYLXXB_ZDJID_STR,a.CLFWYLXXBZ_SJDDSJ,b.CLFWYLXXB_SJDDSJ," +
				" a.CLFWYLXXBZ_SEQID,b.CLFWYLXXB_SEQID," +
				" decode(b.CLFWYLXXB_SFJGH,'1','正常考核','2','只参与发班不参与准点考核','3','不参与发班准点考核','错误数据') CLFWYLXXB_SFJGH," +
				" b.CLFWYLXXB_SFJGHSM," +
				" decode(b.CLFWYLXXB_SFJGHSM,'1','正常发班','2','邮航落地晚点连带汽运晚点','3','本日非发班日期','错误数据') CLFWYLXXB_SFJGHSMSTR," +
				" decode(e.city_name,null,e.province_name,e.city_name)||e.county_name CLFWYLXXB_AYJID_STR ";
		
		String sqlCount="select count(*) ";
		
		String sqlfrom=" from clfw_ylxxbz a,clfw_ylxxb b,CP_BASE_ORG_DISTRICT c,CP_BASE_ORG_DISTRICT d,CP_BASE_ORG_DISTRICT e";

		String sqlwhere=" where 1=1 and a.CLFWYLXXBZ_SEQID = b.CLFWYLXXBZ_SEQID " +
				" and a.CLFWYLXXBZ_SFJID = c.DISTRICT_CODE and a.CLFWYLXXBZ_ZDJID = d.DISTRICT_CODE and a.CLFWYLXXBZ_AYJID = e.DISTRICT_CODE ";
		
		if(!ITEM_DATESS.equals("null") && ITEM_DATESS.length()>0){
			sqlwhere = sqlwhere + " and b.CLFWYLXXB_CSRQ  >= '" + ITEM_DATESS + "' ";
		}
		
		if(!ITEM_DATESE.equals("null") && ITEM_DATESE.length()>0){
			sqlwhere = sqlwhere + " and b.CLFWYLXXB_CSRQ  <= '" + ITEM_DATESE + "' ";
		}
		
		if(!CLFWYLXXBZ_YLMC.equals("null") && CLFWYLXXBZ_YLMC.length()>0){
			sqlwhere = sqlwhere + " and a.CLFWYLXXBZ_YLMC  like '%" + CLFWYLXXBZ_YLMC + "%' ";
		}
		
		if (!RAD_CLFWPC_SX.equals("null") && RAD_CLFWPC_SX.length() > 0) {
			if (RAD_CLFWPC_SX.equals("1")) {
				
				if(!COUNTY_ID.equals("null") && COUNTY_ID.length()>0){
					sqlwhere = sqlwhere + " and ( a.CLFWYLXXBZ_AYJID = '" + COUNTY_ID + "')";
						}else if(!CITY_ID.equals("null") && CITY_ID.length()>0){
							if(CITY_ID.substring(4, 6).equals("00")){
								sqlwhere = sqlwhere + " and (  a.CLFWYLXXBZ_AYJID  like '" + CITY_ID.substring(0, 4) + "%' )";
							}else{
								sqlwhere = sqlwhere + " and ( a.CLFWYLXXBZ_AYJID  like '" + CITY_ID + "%')";
							}
						}else if(!PROVINCE_ID.equals("null") && PROVINCE_ID.length()>0){
							sqlwhere = sqlwhere + " and ( a.CLFWYLXXBZ_AYJID  like '" + PROVINCE_ID.substring(0, 2) + "%')";
						}
				
			} else if (RAD_CLFWPC_SX.equals("2")) {
				
				if(!COUNTY_ID.equals("null") && COUNTY_ID.length()>0){
					sqlwhere = sqlwhere + " and ( a.CLFWYLXXBZ_SFJID = '" + COUNTY_ID + "')";
						}else if(!CITY_ID.equals("null") && CITY_ID.length()>0){
							if(CITY_ID.substring(4, 6).equals("00")){
								sqlwhere = sqlwhere + " and (  a.CLFWYLXXBZ_SFJID  like '" + CITY_ID.substring(0, 4) + "%' )";
							}else{
								sqlwhere = sqlwhere + " and ( a.CLFWYLXXBZ_SFJID  like '" + CITY_ID + "%')";
							}
						}else if(!PROVINCE_ID.equals("null") && PROVINCE_ID.length()>0){
							sqlwhere = sqlwhere + " and ( a.CLFWYLXXBZ_SFJID  like '" + PROVINCE_ID.substring(0, 2) + "%')";
						}
				
			} else if (RAD_CLFWPC_SX.equals("3")) {
				
				if(!COUNTY_ID.equals("null") && COUNTY_ID.length()>0){
					sqlwhere = sqlwhere + " and ( a.CLFWYLXXBZ_ZDJID = '" + COUNTY_ID + "')";
						}else if(!CITY_ID.equals("null") && CITY_ID.length()>0){
							if(CITY_ID.substring(4, 6).equals("00")){
								sqlwhere = sqlwhere + " and (  a.CLFWYLXXBZ_ZDJID  like '" + CITY_ID.substring(0, 4) + "%' )";
							}else{
								sqlwhere = sqlwhere + " and ( a.CLFWYLXXBZ_ZDJID  like '" + CITY_ID + "%')";
							}
						}else if(!PROVINCE_ID.equals("null") && PROVINCE_ID.length()>0){
							sqlwhere = sqlwhere + " and ( a.CLFWYLXXBZ_ZDJID  like '" + PROVINCE_ID.substring(0, 2) + "%')";
						}
				
			}
		}else{
			
			if(!COUNTY_ID.equals("null") && COUNTY_ID.length()>0){
				sqlwhere = sqlwhere + " and ( a.CLFWYLXXBZ_SFJID = '" + COUNTY_ID + "' or a.CLFWYLXXBZ_ZDJID = '" + COUNTY_ID + "' or a.CLFWYLXXBZ_AYJID = '" + COUNTY_ID + "')";
					}else if(!CITY_ID.equals("null") && CITY_ID.length()>0){
						if(CITY_ID.substring(4, 6).equals("00")){
							sqlwhere = sqlwhere + " and ( a.CLFWYLXXBZ_SFJID like '" + CITY_ID.substring(0, 4) + "%'" +
									"  or a.CLFWYLXXBZ_ZDJID  like '" + CITY_ID.substring(0, 4) + "%'" +
											" or a.CLFWYLXXBZ_AYJID  like '" + CITY_ID.substring(0, 4) + "%' )";
						}else{
							sqlwhere = sqlwhere + " and ( a.CLFWYLXXBZ_SFJID like '" + CITY_ID + "%'" +
							"  or a.CLFWYLXXBZ_ZDJID  like '" + CITY_ID + "%'" +
									"  or a.CLFWYLXXBZ_AYJID  like '" + CITY_ID + "%')";
						}
					}else if(!PROVINCE_ID.equals("null") && PROVINCE_ID.length()>0){
						sqlwhere = sqlwhere + " and ( a.CLFWYLXXBZ_SFJID like '" + PROVINCE_ID.substring(0, 2) + "%'" +
						"  or a.CLFWYLXXBZ_ZDJID  like '" + PROVINCE_ID.substring(0, 2) + "%' " +
								" or a.CLFWYLXXBZ_AYJID  like '" + PROVINCE_ID.substring(0, 2) + "%')";
					}
			
		}
		
		if (!CLFW_HBXXGL_CLSX.equals("null") && CLFW_HBXXGL_CLSX.length() > 0) {
			if (CLFW_HBXXGL_CLSX.equals("1")) {
				sqlwhere += " and  a.clfwylxxbz_sybz = '1' " ;
			} else if (CLFW_HBXXGL_CLSX.equals("0")) {
				sqlwhere += " and  a.clfwylxxbz_sybz = '0' " ;
			}
		}
		
		String sqlorder=" order by b.CLFWYLXXB_CSRQ,a.CLFWYLXXBZ_AYJID,a.CLFWYLXXBZ_SFJID,a.CLFWYLXXBZ_ZDJID,a.CLFWYLXXBZ_SEQID ";
		
		if (!CLFW_ORDERBY.equals("null") && CLFW_ORDERBY.length() > 0) {
			if (CLFW_ORDERBY.equals("1")) {
				sqlorder=" order by b.CLFWYLXXB_CSRQ,a.CLFWYLXXBZ_AYJID,a.CLFWYLXXBZ_SFJID,a.CLFWYLXXBZ_ZDJID,a.CLFWYLXXBZ_SEQID ";
			} else if (CLFW_ORDERBY.equals("2")) {
				sqlorder=" order by b.CLFWYLXXB_CSRQ,a.CLFWYLXXBZ_SFJID,a.CLFWYLXXBZ_ZDJID,a.CLFWYLXXBZ_AYJID,a.CLFWYLXXBZ_SEQID ";
			} else if (CLFW_ORDERBY.equals("3")) {
				sqlorder=" order by b.CLFWYLXXB_CSRQ,a.CLFWYLXXBZ_ZDJID,a.CLFWYLXXBZ_SFJID,a.CLFWYLXXBZ_AYJID,a.CLFWYLXXBZ_SEQID ";
			}
		}
		
		

		sql=sql+sqlfrom+sqlwhere+sqlorder;
		sqlCount=sqlCount+sqlfrom+sqlwhere;
		
		page.setPageSize(FLAGNUM);
		
		if(pageNo!=0){
			page.setPageNo(pageNo);
		}
		page=clfwQueryDao.getBeanQueryClfwtjclfwylxxbt(sql, sqlCount, page);
		
		return "json";
	}
	
	@SuppressWarnings("unchecked")
	public String querytjclfwhbwhal() throws Exception {
		
		if(!userflag()){return "json";}
		
		String sql = "select CLFW_HBXXGL_SFJIDSTR,OPE_INSERTTIME,CLFW_HBXXGL_CSRQ,CLFW_HBXXGL_ZBS,CLFW_HBXXGL_ZL,CLFW_HBXXGL_LDZBS,CLFW_HBXXGL_SSZBS,CLFW_HBXXGL_XSBZ ";
		String sqlCount = "select count(*) ";
		
		String sqlfrom = " from ( ";
		
		sqlfrom +="select max(decode(b.city_name,null,b.province_name,b.city_name)||b.county_name) CLFW_HBXXGL_SFJIDSTR," +
		" count(*) OPE_INSERTTIME," +
		" sum(case when(c.CLFW_HBXXGL_SFJID is null) then 0 else 1 end) CLFW_HBXXGL_CSRQ," +
		" sum(a.CLFW_HBXXGL_ZBS) CLFW_HBXXGL_ZBS," +
		" sum(a.CLFW_HBXXGL_ZL) CLFW_HBXXGL_ZL," +
		" sum(case when(c.CLFW_HBXXGL_SFJID is not null and c.CLFW_HBXXGL_LDZBS = a.CLFW_HBXXGL_ZBS) then 1 else 0 end) CLFW_HBXXGL_LDZBS," +
		" sum(case when(c.CLFW_HBXXGL_SFJID is not null and c.CLFW_HBXXGL_SSZBS = a.CLFW_HBXXGL_ZBS) then 1 else 0 end) CLFW_HBXXGL_SSZBS," +
		" '0' CLFW_HBXXGL_XSBZ," +
		"  a.CLFW_HBXXGL_SFJID CLFW_HBXXGL_SFJID  " +
		" from CLFW_HBXXGL a ,CP_BASE_ORG_DISTRICT b," +
		" (" +
		" select CLFW_HBXXGL_SFJID||CLFW_HBXXGL_ZDJID||CLFW_HBXXGL_CSRQ||CLFW_HBXXGL_HBH CLFW_HBXXGL_SFJID," +
		" CLFW_HBXXGL_LDZBS,CLFW_HBXXGL_SSZBS " +
		" from CLFW_HBXXGL where CLFW_HBXXGL_CLSX = '1'" +
		" ) c ";
		
		sqlfrom +=" where 1=1 and a.CLFW_HBXXGL_SFJID =b.DISTRICT_CODE" +
				" and a.CLFW_HBXXGL_CLSX = '0'" +
				" and a.CLFW_HBXXGL_SFJID||a.CLFW_HBXXGL_ZDJID||a.CLFW_HBXXGL_CSRQ||a.CLFW_HBXXGL_HBH = c.CLFW_HBXXGL_SFJID(+) ";
		
		if(!ITEM_DATESS.equals("null") && ITEM_DATESS.length()>0){
			sqlfrom += " and a.CLFW_HBXXGL_CSRQ  >= '" + ITEM_DATESS + "' ";
		}
		
		if(!ITEM_DATESE.equals("null") && ITEM_DATESE.length()>0){
			sqlfrom +=" and a.CLFW_HBXXGL_CSRQ  <= '" + ITEM_DATESE + "' ";
		}
		
		if(!COUNTY_ID.equals("null") && COUNTY_ID.length()>0){
			sqlfrom +=" and a.CLFW_HBXXGL_SFJID = '" + COUNTY_ID + "'";
				}else if(!CITY_ID.equals("null") && CITY_ID.length()>0){
					if(CITY_ID.substring(4, 6).equals("00")){
						sqlfrom +=" and a.CLFW_HBXXGL_SFJID like '" + CITY_ID.substring(0, 4) + "%'";
					}else{
						sqlfrom +=" and a.CLFW_HBXXGL_SFJID like '" + CITY_ID + "%'";
					}
				}else if(!PROVINCE_ID.equals("null") && PROVINCE_ID.length()>0){
					sqlfrom +=" and a.CLFW_HBXXGL_SFJID like '" + PROVINCE_ID.substring(0, 2) + "%'" ;
				}
		
		sqlfrom +=" group by a.CLFW_HBXXGL_SFJID ";
		
		sqlfrom += " union ";
		
		sqlfrom +="select max(decode(b.city_name,null,b.province_name,b.city_name)||b.county_name) CLFW_HBXXGL_SFJIDSTR," +
		" count(*) OPE_INSERTTIME," +
		" sum(case when(c.CLFW_HBXXGL_SFJID is null) then 0 else 1 end) CLFW_HBXXGL_CSRQ," +
		" sum(a.CLFW_HBXXGL_LDZBS) CLFW_HBXXGL_ZBS," +
		" sum(a.CLFW_HBXXGL_SSZBS) CLFW_HBXXGL_ZL," +
		" sum(case when(c.CLFW_HBXXGL_SFJID is not null and a.CLFW_HBXXGL_LDZBS = c.CLFW_HBXXGL_ZBS) then 1 else 0 end) CLFW_HBXXGL_LDZBS," +
		" sum(case when(c.CLFW_HBXXGL_SFJID is not null and a.CLFW_HBXXGL_SSZBS = c.CLFW_HBXXGL_ZBS) then 1 else 0 end) CLFW_HBXXGL_SSZBS," +
		" '1' CLFW_HBXXGL_XSBZ," +
		" a.CLFW_HBXXGL_ZDJID CLFW_HBXXGL_SFJID  " +
		" from CLFW_HBXXGL a ,CP_BASE_ORG_DISTRICT b," +
		" (" +
		" select CLFW_HBXXGL_SFJID||CLFW_HBXXGL_ZDJID||CLFW_HBXXGL_CSRQ||CLFW_HBXXGL_HBH CLFW_HBXXGL_SFJID," +
		" CLFW_HBXXGL_ZBS,CLFW_HBXXGL_ZL " +
		" from CLFW_HBXXGL where CLFW_HBXXGL_CLSX = '0'" +
		" ) c ";
		
		sqlfrom +=" where 1=1 and a.CLFW_HBXXGL_ZDJID =b.DISTRICT_CODE" +
				" and a.CLFW_HBXXGL_CLSX = '1'" +
				" and a.CLFW_HBXXGL_SFJID||a.CLFW_HBXXGL_ZDJID||a.CLFW_HBXXGL_CSRQ||a.CLFW_HBXXGL_HBH = c.CLFW_HBXXGL_SFJID(+) ";
		
		if(!ITEM_DATESS.equals("null") && ITEM_DATESS.length()>0){
			sqlfrom += " and a.CLFW_HBXXGL_CSRQ  >= '" + ITEM_DATESS + "' ";
		}
		
		if(!ITEM_DATESE.equals("null") && ITEM_DATESE.length()>0){
			sqlfrom +=" and a.CLFW_HBXXGL_CSRQ  <= '" + ITEM_DATESE + "' ";
		}
		
		if(!COUNTY_ID.equals("null") && COUNTY_ID.length()>0){
			sqlfrom +=" and a.CLFW_HBXXGL_ZDJID = '" + COUNTY_ID + "'";
				}else if(!CITY_ID.equals("null") && CITY_ID.length()>0){
					if(CITY_ID.substring(4, 6).equals("00")){
						sqlfrom +=" and a.CLFW_HBXXGL_ZDJID like '" + CITY_ID.substring(0, 4) + "%'";
					}else{
						sqlfrom +=" and a.CLFW_HBXXGL_ZDJID like '" + CITY_ID + "%'";
					}
				}else if(!PROVINCE_ID.equals("null") && PROVINCE_ID.length()>0){
					sqlfrom +=" and a.CLFW_HBXXGL_ZDJID like '" + PROVINCE_ID.substring(0, 2) + "%'" ;
				}
		
		sqlfrom +=" group by a.CLFW_HBXXGL_ZDJID ";
		
		sqlfrom += " ) e ";
		
		String sqlwhere = " where 1=1 ";
		String sqlorder = " order by CLFW_HBXXGL_SFJID ";
		
		sql=sql+sqlfrom+sqlwhere+sqlorder;
		sqlCount=sqlCount+sqlfrom+sqlwhere;
		
		page.setPageSize(99999);
		
		if(pageNo!=0){
			page.setPageNo(pageNo);
		}
		page=clfwQueryDao.getBeanQueryClfwhbwhal(sql, sqlCount, page);
		
		return "json";
	}

	@SuppressWarnings("unchecked")
	public String querytjclfwylxxbtalc() throws Exception {
		//System.out.println(CLFW_POSTALROUTEATTR);//邮路类型
		//System.out.println(CLFW_HBXXGL_CLSX);
		//System.out.println(CLFW_HBXXGL_CLSX);
		if(!userflag()){return "json";}
		
		String sql = "select (decode(c.city_name,null,c.province_name,c.city_name)||c.county_name) CLFWYLXXB_SFJID_STR," +
				" b.CLFWYLXXBZ_AYJID,a.clfwylxxb_sjkcsj,b.clfwylxxbz_sjkcsj,a.CLFWYLXXB_SJDDSJ ,b.CLFWYLXXBZ_SJDDSJ,a.CLFWYLXXB_SFJGHSM,a.CLFWYLXXB_SFJGH";
		
		String sqlCount = "select count(*) ";
		
		String sqlfrom = " from clfw_ylxxb a ,clfw_ylxxbz b,CP_BASE_ORG_DISTRICT c ";
		
		//add by zlq, begin
		String sqlPOSTALROUTEATTR = "";
		if(!CLFW_POSTALROUTEATTR.equals("null") && CLFW_POSTALROUTEATTR.length()>0){
			sqlPOSTALROUTEATTR="and a.CLFWYLXXB_POSTALROUTEATTR='"+CLFW_POSTALROUTEATTR +"' ";
			if(CLFW_POSTALROUTEATTR=="1"||"1".equals(CLFW_POSTALROUTEATTR)){
				sqlPOSTALROUTEATTR="and (a.CLFWYLXXB_POSTALROUTEATTR='"+CLFW_POSTALROUTEATTR +"' or a.CLFWYLXXB_POSTALROUTEATTR='0')";
			}
		}
		String sqlwhere = " where 1=1 "+sqlPOSTALROUTEATTR+" and a.clfwylxxbz_seqid = b.clfwylxxbz_seqid " +
				" and  b.CLFWYLXXBZ_AYJID = c.DISTRICT_CODE";
		//add by zlq, end
		if(!ITEM_DATESS.equals("null") && ITEM_DATESS.length()>0){
			sqlwhere += " and a.CLFWYLXXB_CSRQ  >= '" + ITEM_DATESS + "' ";
		}
		
		if(!ITEM_DATESE.equals("null") && ITEM_DATESE.length()>0){
			sqlwhere +=" and a.CLFWYLXXB_CSRQ  <= '" + ITEM_DATESE + "' ";
		}
		
		if(!COUNTY_ID.equals("null") && COUNTY_ID.length()>0){
			sqlwhere +=" and b.CLFWYLXXBZ_AYJID = '" + COUNTY_ID + "'";
				}else if(!CITY_ID.equals("null") && CITY_ID.length()>0){
					if(CITY_ID.substring(4, 6).equals("00")){
						sqlwhere +=" and  b.CLFWYLXXBZ_AYJID like '" + CITY_ID.substring(0, 4) + "%'";
					}else{
						sqlwhere +=" and  b.CLFWYLXXBZ_AYJID like '" + CITY_ID + "%'";
					}
				}else if(!PROVINCE_ID.equals("null") && PROVINCE_ID.length()>0){
					sqlwhere +=" and  b.CLFWYLXXBZ_AYJID like '" + PROVINCE_ID.substring(0, 2) + "%'" ;
				}
		
	
		
		if (!CLFW_HBXXGL_CLSX.equals("null") && CLFW_HBXXGL_CLSX.length() > 0) {
			if (CLFW_HBXXGL_CLSX.equals("1")) {
				sqlwhere += " and  b.clfwylxxbz_sybz = '1' " ;
			} else if (CLFW_HBXXGL_CLSX.equals("0")) {
				sqlwhere += " and  b.clfwylxxbz_sybz = '0' " ;
			}
		}
		
		String sqlorder = " ORDER BY   b.CLFWYLXXBZ_AYJID ";
		
		sql=sql+sqlfrom+sqlwhere+sqlorder;
		sqlCount=sqlCount+sqlfrom+sqlwhere;
		
		page.setPageSize(99999);
		
		if(pageNo!=0){
			page.setPageNo(pageNo);
		}
		page=clfwQueryDao.getBeanQueryClfwtjclfwylxxbtalc(sql, sqlCount, page);
		
		return "json";
	}
	
	@SuppressWarnings("unchecked")
	public String querytjclfwhbwhalmx() throws Exception {
		
		if(!userflag()){return "json";}
		
		String sql=" ";
		
		String sqlCount=" select count(*) ";
		
		String sqlfrom=" ";

		String sqlwhere=" ";

		String sqlorder=" ";
		
		if(!CLFW_HBXXGL_CLSX.equals("null") && CLFW_HBXXGL_CLSX.length()>0){
			if(CLFW_HBXXGL_CLSX.equals("0")){
				
				sql=" select a.CLFW_HBXXGL_SEQID, " +
				" decode(b.city_name, null, b.province_name, b.city_name) || b.county_name CLFW_HBXXGL_SFJIDSTR, " +
				" decode(c.city_name, null, c.province_name, c.city_name) || c.county_name CLFW_HBXXGL_ZDJIDSTR, " +
				" a.CLFW_HBXXGL_CSRQ,a.CLFW_HBXXGL_HBH,a.CLFW_HBXXGL_ZBS,a.CLFW_HBXXGL_ZL, " +
				" d.CLFW_HBXXGL_LDZBS,d.CLFW_HBXXGL_SSZBS, " +
				" decode(d.CLFW_HBXXGL_SFJID,null,'3-有出港信息无进港信息', " +
				" decode(d.CLFW_HBXXGL_SSZBS,a.CLFW_HBXXGL_ZBS,'1-出港实收总包数符合','2-出港实收总包数不符') " +
				" ) CLFW_HBXXGL_THJID," +
				" a.CLFW_HBXXGL_LDLSH,a.CLFW_HBXXGL_CLSX ";
				
				sqlfrom=" from CLFW_HBXXGL a,CP_BASE_ORG_DISTRICT b,CP_BASE_ORG_DISTRICT c, " +
				" (" +
				" select CLFW_HBXXGL_SFJID||CLFW_HBXXGL_ZDJID||CLFW_HBXXGL_CSRQ||CLFW_HBXXGL_HBH CLFW_HBXXGL_SFJID," +
				" CLFW_HBXXGL_ZBS,CLFW_HBXXGL_ZL,CLFW_HBXXGL_LDZBS,CLFW_HBXXGL_SSZBS,CLFW_HBXXGL_LDLSH " +
				" from CLFW_HBXXGL where CLFW_HBXXGL_CLSX = '1'" +
				" ) d ";
				
				
				sqlwhere = " where 1=1 and a.CLFW_HBXXGL_SFJID = b.DISTRICT_CODE and a.CLFW_HBXXGL_ZDJID = c.DISTRICT_CODE  " +
				" and a.CLFW_HBXXGL_CLSX = '0'" +
				" and a.CLFW_HBXXGL_SFJID||a.CLFW_HBXXGL_ZDJID||a.CLFW_HBXXGL_CSRQ||a.CLFW_HBXXGL_HBH = d.CLFW_HBXXGL_SFJID(+) ";
		
				if(!ITEM_DATESS.equals("null") && ITEM_DATESS.length()>0){
					sqlwhere += " and a.CLFW_HBXXGL_CSRQ  >= '" + ITEM_DATESS + "' ";
				}
				
				if(!ITEM_DATESE.equals("null") && ITEM_DATESE.length()>0){
					sqlwhere +=" and a.CLFW_HBXXGL_CSRQ  <= '" + ITEM_DATESE + "' ";
				}
				
				if(!COUNTY_ID.equals("null") && COUNTY_ID.length()>0){
					sqlwhere +=" and a.CLFW_HBXXGL_SFJID = '" + COUNTY_ID + "'";
						}else if(!CITY_ID.equals("null") && CITY_ID.length()>0){
							if(CITY_ID.substring(4, 6).equals("00")){
								sqlwhere +=" and a.CLFW_HBXXGL_SFJID like '" + CITY_ID.substring(0, 4) + "%'";
							}else{
								sqlwhere +=" and a.CLFW_HBXXGL_SFJID like '" + CITY_ID + "%'";
							}
						}else if(!PROVINCE_ID.equals("null") && PROVINCE_ID.length()>0){
							sqlwhere +=" and a.CLFW_HBXXGL_SFJID like '" + PROVINCE_ID.substring(0, 2) + "%'" ;
						}
				
				sqlorder=" order by  CLFW_HBXXGL_THJID,a.CLFW_HBXXGL_SFJID,a.CLFW_HBXXGL_ZDJID";
				
			}else if(CLFW_HBXXGL_CLSX.equals("1")){
				
				sql=" select a.CLFW_HBXXGL_SEQID, " +
				" decode(b.city_name, null, b.province_name, b.city_name) || b.county_name CLFW_HBXXGL_SFJIDSTR, " +
				" decode(c.city_name, null, c.province_name, c.city_name) || c.county_name CLFW_HBXXGL_ZDJIDSTR, " +
				" a.CLFW_HBXXGL_CSRQ,a.CLFW_HBXXGL_HBH,d.CLFW_HBXXGL_ZBS,d.CLFW_HBXXGL_ZL, " +
				" a.CLFW_HBXXGL_LDZBS,a.CLFW_HBXXGL_SSZBS, " +
				" decode(d.CLFW_HBXXGL_SFJID,null,'3-有进港信息无出港信息', " +
				" decode(a.CLFW_HBXXGL_SSZBS,d.CLFW_HBXXGL_ZBS,'1-进港实收总包数符合','2-进港实收总包数不符') " +
				" ) CLFW_HBXXGL_THJID," +
				" d.CLFW_HBXXGL_LDLSH,a.CLFW_HBXXGL_CLSX ";
				
				sqlfrom=" from CLFW_HBXXGL a,CP_BASE_ORG_DISTRICT b,CP_BASE_ORG_DISTRICT c, " +
				" (" +
				" select CLFW_HBXXGL_SFJID||CLFW_HBXXGL_ZDJID||CLFW_HBXXGL_CSRQ||CLFW_HBXXGL_HBH CLFW_HBXXGL_SFJID," +
				" CLFW_HBXXGL_ZBS,CLFW_HBXXGL_ZL,CLFW_HBXXGL_LDZBS,CLFW_HBXXGL_SSZBS,CLFW_HBXXGL_LDLSH " +
				" from CLFW_HBXXGL where CLFW_HBXXGL_CLSX = '0'" +
				" ) d ";
				
				
				sqlwhere = " where 1=1 and a.CLFW_HBXXGL_SFJID = b.DISTRICT_CODE and a.CLFW_HBXXGL_ZDJID = c.DISTRICT_CODE  " +
				" and a.CLFW_HBXXGL_CLSX = '1'" +
				" and a.CLFW_HBXXGL_SFJID||a.CLFW_HBXXGL_ZDJID||a.CLFW_HBXXGL_CSRQ||a.CLFW_HBXXGL_HBH = d.CLFW_HBXXGL_SFJID(+) ";
		
				if(!ITEM_DATESS.equals("null") && ITEM_DATESS.length()>0){
					sqlwhere += " and a.CLFW_HBXXGL_CSRQ  >= '" + ITEM_DATESS + "' ";
				}
				
				if(!ITEM_DATESE.equals("null") && ITEM_DATESE.length()>0){
					sqlwhere +=" and a.CLFW_HBXXGL_CSRQ  <= '" + ITEM_DATESE + "' ";
				}
				
				if(!COUNTY_ID.equals("null") && COUNTY_ID.length()>0){
					sqlwhere +=" and a.CLFW_HBXXGL_ZDJID = '" + COUNTY_ID + "'";
						}else if(!CITY_ID.equals("null") && CITY_ID.length()>0){
							if(CITY_ID.substring(4, 6).equals("00")){
								sqlwhere +=" and a.CLFW_HBXXGL_ZDJID like '" + CITY_ID.substring(0, 4) + "%'";
							}else{
								sqlwhere +=" and a.CLFW_HBXXGL_ZDJID like '" + CITY_ID + "%'";
							}
						}else if(!PROVINCE_ID.equals("null") && PROVINCE_ID.length()>0){
							sqlwhere +=" and a.CLFW_HBXXGL_ZDJID like '" + PROVINCE_ID.substring(0, 2) + "%'" ;
						}
				
				sqlorder=" order by CLFW_HBXXGL_THJID,a.CLFW_HBXXGL_ZDJID,a.CLFW_HBXXGL_SFJID";
			}
		}
		

		sql=sql+sqlfrom+sqlwhere+sqlorder;
		sqlCount=sqlCount+sqlfrom+sqlwhere;
		
		page.setPageSize(50);
		
		if(pageNo!=0){
			page.setPageNo(pageNo);
		}
		page=clfwQueryDao.getBeanQueryClfwtjclfwhbwhalmx(sql, sqlCount, page);
		
		return "json";
	}
	
	
	@SuppressWarnings("unchecked")
	public String queryclfwtabnjjs() throws Exception {
		
		if(!userflag()){return "json";}
		
		String sql=" ";
		String sqlCount=" ";
		String sqlfrom=" ";
		String sqlwhere=" ";
		String sqlorder=" ";
		
		if(RAD_CLFWPC_QY.equals("1")){
			
			sql="select a.CLFWTDBNJJS_SEQID,c.CITY_CODE," +
					" decode(b.city_name,null,b.province_name,b.city_name)||b.county_name CLFWTDB_DISTCDSTR," +
					" c.DM_NAME,c.OFFICE_CODE,decode(a.CLFWTDBNJJS_SFNJ,'1','参与直封集散','不参与直封集散') CLFWTDBNJJS_SFNJ" ;
			sqlCount="select count(*) ";
			sqlfrom=" from " +
					" CLFW_TDBNJJS a,cp_base_org_district b,CP_WH_DEPARTMENT c ";
		
			sqlwhere=" where 1=1 " +
					" and a.DM_PK_CODE = c.DM_PK_CODE " +
					" and c.CITY_CODE = b.district_code ";
			
			sqlorder=" order by c.DM_NAME ";
			
			if(!CITY_ID.equals("null") && CITY_ID.length()>0){
				if(CITY_ID.substring(4, 6).equals("00")){
					sqlwhere = sqlwhere + " and c.CITY_CODE like '" + CITY_ID.substring(0, 4) + "%'";
				}else{
					sqlwhere = sqlwhere + " and c.CITY_CODE like '" + CITY_ID + "%'";
				}
			}else if(!PROVINCE_ID.equals("null") && PROVINCE_ID.length()>0){
				sqlwhere = sqlwhere + " and c.CITY_CODE like '" + PROVINCE_ID.substring(0, 2) + "%'";
			}

		}else if(RAD_CLFWPC_QY.equals("0")){


			sql="select c.DM_PK_CODE CLFWTDBNJJS_SEQID,c.CITY_CODE," +
			" decode(b.city_name,null,b.province_name,b.city_name)||b.county_name CLFWTDB_DISTCDSTR," +
			" c.DM_NAME,c.OFFICE_CODE,'' CLFWTDBNJJS_SFNJ" ;
			
			sqlCount="select count(*) ";
			
			sqlfrom=" from " +
					" cp_base_org_district b,CP_WH_DEPARTMENT c ";
		
			sqlwhere=" where 1=1 " +
					" and c.CITY_CODE = b.district_code ";
			
			sqlorder=" order by c.DM_NAME ";
			
			if(!CITY_ID.equals("null") && CITY_ID.length()>0){
				if(CITY_ID.substring(4, 6).equals("00")){
					sqlwhere = sqlwhere + " and c.CITY_CODE like '" + CITY_ID.substring(0, 4) + "%'";
				}else{
					sqlwhere = sqlwhere + " and c.CITY_CODE like '" + CITY_ID + "%'";
				}
			}else if(!PROVINCE_ID.equals("null") && PROVINCE_ID.length()>0){
				sqlwhere = sqlwhere + " and c.CITY_CODE like '" + PROVINCE_ID.substring(0, 2) + "%'";
			}

		}

		sql=sql+sqlfrom+sqlwhere+sqlorder;
		sqlCount=sqlCount+sqlfrom+sqlwhere;
		
		page.setPageSize(20);
		if(pageNo!=0){
			page.setPageNo(pageNo);
		}
		page=clfwQueryDao.getBeanQueryClfwtabnjjs(sql, sqlCount, page);
		return "json";
	}
	
	
	@SuppressWarnings("unchecked")
	public String queryexportall() throws Exception {
		
		if(!userflag()){return "json";}
		
		String sql="";
		String sqlfrom="";
		String sqlwhere="";
		String sqlorder="";
		String sqlCount="select count(*) ";
		
		String[] LINEKEYs = LINEKEY.split(",");
		
		String[] EXDATAs = EXDATA.split(",");
		
		if(LINEKEYs[0].equals("exptjclfwhbwhalmx")){
			
			sql=" select decode(CLFW_HBXXGL_CLSX,'0','出港信息','1','进港信息','错误信息') CLFW_HBXXGL_CLSX,CLFW_HBXXGL_SFJIDSTR,CLFW_HBXXGL_LDLSH,CLFW_HBXXGL_CSRQ,CLFW_HBXXGL_HBH,CLFW_HBXXGL_ZDJIDSTR, " +
					" CLFW_HBXXGL_ZBS,CLFW_HBXXGL_ZL,CLFW_HBXXGL_LDZBS,CLFW_HBXXGL_SSZBS,CLFW_HBXXGL_THJID from ";
			
			sql+= "( select '0' CLFW_HBXXGL_CLSX, " +
			" decode(b.city_name, null, b.province_name, b.city_name) || b.county_name CLFW_HBXXGL_SFJIDSTR, " +
			" decode(c.city_name, null, c.province_name, c.city_name) || c.county_name CLFW_HBXXGL_ZDJIDSTR, " +
			" a.CLFW_HBXXGL_CSRQ," +
			" a.CLFW_HBXXGL_HBH," +
			" a.CLFW_HBXXGL_ZBS," +
			" a.CLFW_HBXXGL_ZL, " +
			" d.CLFW_HBXXGL_LDZBS," +
			" d.CLFW_HBXXGL_SSZBS, " +
			" decode(d.CLFW_HBXXGL_SFJID,null,'3-有出港信息无进港信息', " +
			" decode(d.CLFW_HBXXGL_SSZBS,a.CLFW_HBXXGL_ZBS,'1-出港实收总包数符合','2-出港实收总包数不符') " +
			" ) CLFW_HBXXGL_THJID," +
			" a.CLFW_HBXXGL_LDLSH," +
			" a.CLFW_HBXXGL_SFJID CLFW_HBXXGL_SFJID,a.CLFW_HBXXGL_ZDJID CLFW_HBXXGL_ZDJID";
			
			sql+= " from CLFW_HBXXGL a,CP_BASE_ORG_DISTRICT b,CP_BASE_ORG_DISTRICT c, " +
			" (" +
			" select CLFW_HBXXGL_SFJID||CLFW_HBXXGL_ZDJID||CLFW_HBXXGL_CSRQ||CLFW_HBXXGL_HBH CLFW_HBXXGL_SFJID," +
			" CLFW_HBXXGL_ZBS,CLFW_HBXXGL_ZL,CLFW_HBXXGL_LDZBS,CLFW_HBXXGL_SSZBS,CLFW_HBXXGL_LDLSH " +
			" from CLFW_HBXXGL where CLFW_HBXXGL_CLSX = '1'" +
			" ) d ";
			
			
			sql += " where 1=1 and a.CLFW_HBXXGL_SFJID = b.DISTRICT_CODE and a.CLFW_HBXXGL_ZDJID = c.DISTRICT_CODE  " +
			" and a.CLFW_HBXXGL_CLSX = '0'" +
			" and a.CLFW_HBXXGL_SFJID||a.CLFW_HBXXGL_ZDJID||a.CLFW_HBXXGL_CSRQ||a.CLFW_HBXXGL_HBH = d.CLFW_HBXXGL_SFJID(+) ";
	
			if(!EXDATAs[3].equals("null") && EXDATAs[3].trim().length()>0){
				sql += " and a.CLFW_HBXXGL_CSRQ  >= '" + EXDATAs[3].trim() + "' ";
			}
			
			if(!EXDATAs[4].equals("null") && EXDATAs[4].trim().length()>0){
				sql +=" and a.CLFW_HBXXGL_CSRQ  <= '" + EXDATAs[4].trim() + "' ";
			}
			
			if(!EXDATAs[2].equals("null") && EXDATAs[2].trim().length()>0){
				sql +=" and a.CLFW_HBXXGL_SFJID = '" + EXDATAs[2].trim() + "'";
					}else if(!EXDATAs[1].equals("null") && EXDATAs[1].trim().length()>0){
						if(EXDATAs[1].trim().substring(4, 6).equals("00")){
							sql +=" and a.CLFW_HBXXGL_SFJID like '" + EXDATAs[1].trim().substring(0, 4) + "%'";
						}else{
							sql +=" and a.CLFW_HBXXGL_SFJID like '" + EXDATAs[1].trim() + "%'";
						}
					}else if(!EXDATAs[0].equals("null") && EXDATAs[0].trim().length()>0){
						sql +=" and a.CLFW_HBXXGL_SFJID like '" + EXDATAs[0].trim().substring(0, 2) + "%'" ;
					}
			
			sql += " union ";
					
			sql += " select '1' CLFW_HBXXGL_CLSX, " +
			" decode(b.city_name, null, b.province_name, b.city_name) || b.county_name CLFW_HBXXGL_SFJIDSTR, " +
			" decode(c.city_name, null, c.province_name, c.city_name) || c.county_name CLFW_HBXXGL_ZDJIDSTR, " +
			" a.CLFW_HBXXGL_CSRQ," +
			" a.CLFW_HBXXGL_HBH," +
			" d.CLFW_HBXXGL_ZBS," +
			" d.CLFW_HBXXGL_ZL, " +
			" a.CLFW_HBXXGL_LDZBS,a.CLFW_HBXXGL_SSZBS, " +
			" decode(d.CLFW_HBXXGL_SFJID,null,'3-有进港信息无出港信息', " +
			" decode(a.CLFW_HBXXGL_SSZBS,d.CLFW_HBXXGL_ZBS,'1-进港实收总包数符合','2-进港实收总包数不符') " +
			" ) CLFW_HBXXGL_THJID," +
			" d.CLFW_HBXXGL_LDLSH," +
			" a.CLFW_HBXXGL_ZDJID CLFW_HBXXGL_SFJID,a.CLFW_HBXXGL_SFJID CLFW_HBXXGL_ZDJID";
			
			sql += " from CLFW_HBXXGL a,CP_BASE_ORG_DISTRICT b,CP_BASE_ORG_DISTRICT c, " +
			" (" +
			" select CLFW_HBXXGL_SFJID||CLFW_HBXXGL_ZDJID||CLFW_HBXXGL_CSRQ||CLFW_HBXXGL_HBH CLFW_HBXXGL_SFJID," +
			" CLFW_HBXXGL_ZBS,CLFW_HBXXGL_ZL,CLFW_HBXXGL_LDZBS,CLFW_HBXXGL_SSZBS,CLFW_HBXXGL_LDLSH " +
			" from CLFW_HBXXGL where CLFW_HBXXGL_CLSX = '0'" +
			" ) d ";
			
			
			sql += " where 1=1 and a.CLFW_HBXXGL_SFJID = b.DISTRICT_CODE and a.CLFW_HBXXGL_ZDJID = c.DISTRICT_CODE  " +
			" and a.CLFW_HBXXGL_CLSX = '1'" +
			" and a.CLFW_HBXXGL_SFJID||a.CLFW_HBXXGL_ZDJID||a.CLFW_HBXXGL_CSRQ||a.CLFW_HBXXGL_HBH = d.CLFW_HBXXGL_SFJID(+) ";
	
			if(!EXDATAs[3].equals("null") && EXDATAs[3].trim().length()>0){
				sql += " and a.CLFW_HBXXGL_CSRQ  >= '" + EXDATAs[3].trim() + "' ";
			}
			
			if(!EXDATAs[4].equals("null") && EXDATAs[4].trim().length()>0){
				sql +=" and a.CLFW_HBXXGL_CSRQ  <= '" + EXDATAs[4].trim() + "' ";
			}
			
			if(!EXDATAs[2].equals("null") && EXDATAs[2].trim().length()>0){
				sql +=" and a.CLFW_HBXXGL_ZDJID = '" + EXDATAs[2].trim() + "'";
					}else if(!EXDATAs[1].equals("null") && EXDATAs[1].trim().length()>0){
						if(EXDATAs[1].trim().substring(4, 6).equals("00")){
							sql +=" and a.CLFW_HBXXGL_ZDJID like '" + EXDATAs[1].trim().substring(0, 4) + "%'";
						}else{
							sql +=" and a.CLFW_HBXXGL_ZDJID like '" + EXDATAs[1].trim() + "%'";
						}
					}else if(!EXDATAs[0].equals("null") && EXDATAs[0].trim().length()>0){
						sql +=" and a.CLFW_HBXXGL_ZDJID like '" + EXDATAs[0].trim().substring(0, 2) + "%'" ;
					}
			
			sql += " ) order by CLFW_HBXXGL_CLSX,CLFW_HBXXGL_SFJID,CLFW_HBXXGL_THJID,CLFW_HBXXGL_ZDJID";
		}else if(LINEKEYs[0].equals("expclfwhkldwhmx")){
			
			sql=" select decode(CLFW_HBXXGL_XSBZ,'0','出港信息','1','进港信息','错误信息') CLFW_HBXXGL_XSBZ," +
					" CLFW_HBXXYL_SFJIDSTR,CLFW_HBXXLDBW_FYRQSJ,CLFW_HBXXLDBW_HBCC,CLFW_HBXXYL_ZDJIDSTR, " +
					" CLFW_HBXXLDBW_ZBS,CLFW_HBXXLDBW_AJTHZBS,CLFW_HBXXLDBW_LXZBS,CLFW_HBXXLDBW_SSZBS,CLFW_HBXXGL_CLSXSTR from ";
			
			sql+= "( " +
			" select CLFW_HBXXLDBW_SEQID,'0' CLFW_HBXXGL_XSBZ," +
			" decode(f.city_name,null,f.province_name,f.city_name)||f.county_name CLFW_HBXXYL_SFJIDSTR," +
			" decode(g.city_name,null,g.province_name,g.city_name)||g.county_name CLFW_HBXXYL_ZDJIDSTR," +
			" CLFW_HBXXLDBW_FYRQSJ,a.CLFW_HBXXYL_YLDM,CLFW_HBXXLDBW_LDLSH,CLFW_HBXXLDBW_HBCC,CLFW_HBXXLDBW_ZBS," +
			" CLFW_HBXXLDBW_AJTHZBS,CLFW_HBXXLDBW_LXZBS,CLFW_HBXXLDBW_SSZBS," +
			" decode(d.CLFW_HBXXLDBW_SSZBS,null,'3-没有实收总包数', " +
			" decode(d.CLFW_HBXXLDBW_SSZBS,(d.CLFW_HBXXLDBW_ZBS-nvl(d.CLFW_HBXXLDBW_AJTHZBS,0)-nvl(d.CLFW_HBXXLDBW_LXZBS,0)),'1-出港实收总包数符合','2-出港实收总包数不符') " +
			" ) CLFW_HBXXGL_CLSXSTR,d.CLFW_HBXXLDBW_LDHM," +
			" a.CLFW_HBXXYL_SFJID CLFW_HBXXYL_SFJID,a.CLFW_HBXXYL_ZDJID CLFW_HBXXYL_ZDJID " ;
			
			sql+= " from  CLFW_HBXXYL a,CLFW_HBXXLDBW_VIEW d,CP_BASE_ORG_DISTRICT f,CP_BASE_ORG_DISTRICT g ";
			
			
			sql += " where 1=1 and d.clfw_hbxxldbw_lddz = 'S' and a.CLFW_HBXXYL_YLDM = d.CLFW_HBXXYL_YLDM " +
			" and a.CLFW_HBXXYL_SFJID = f.DISTRICT_CODE and a.CLFW_HBXXYL_ZDJID = g.DISTRICT_CODE ";
	
			if(!EXDATAs[3].equals("null") && EXDATAs[3].trim().length()>0){
				sql += " and substr(d.CLFW_HBXXLDBW_FYRQSJ,0,8)   >= '" + EXDATAs[3].trim() + "' ";
			}
			
			if(!EXDATAs[4].equals("null") && EXDATAs[4].trim().length()>0){
				sql +=" and substr(d.CLFW_HBXXLDBW_FYRQSJ,0,8)   <= '" + EXDATAs[4].trim() + "' ";
			}
			
			if(!EXDATAs[2].equals("null") && EXDATAs[2].trim().length()>0){
				sql +=" and a.CLFW_HBXXYL_SFJID = '" + EXDATAs[2].trim() + "'";
					}else if(!EXDATAs[1].equals("null") && EXDATAs[1].trim().length()>0){
						if(EXDATAs[1].trim().substring(4, 6).equals("00")){
							sql +=" and a.CLFW_HBXXYL_SFJID like '" + EXDATAs[1].trim().substring(0, 4) + "%'";
						}else{
							sql +=" and a.CLFW_HBXXYL_SFJID like '" + EXDATAs[1].trim() + "%'";
						}
					}else if(!EXDATAs[0].equals("null") && EXDATAs[0].trim().length()>0){
						sql +=" and a.CLFW_HBXXYL_SFJID like '" + EXDATAs[0].trim().substring(0, 2) + "%'" ;
					}
			
			sql += " union ";
					
			sql += " select CLFW_HBXXLDBW_SEQID,'1' CLFW_HBXXGL_XSBZ," +
			" decode(f.city_name,null,f.province_name,f.city_name)||f.county_name CLFW_HBXXYL_SFJIDSTR," +
			" decode(g.city_name,null,g.province_name,g.city_name)||g.county_name CLFW_HBXXYL_ZDJIDSTR," +
			" CLFW_HBXXLDBW_FYRQSJ,a.CLFW_HBXXYL_YLDM,CLFW_HBXXLDBW_LDLSH,CLFW_HBXXLDBW_HBCC,CLFW_HBXXLDBW_ZBS," +
			" CLFW_HBXXLDBW_AJTHZBS,CLFW_HBXXLDBW_LXZBS,CLFW_HBXXLDBW_SSZBS," +
			" decode(d.CLFW_HBXXLDBW_SSZBS,null,'3-没有实收总包数', " +
			" decode(d.CLFW_HBXXLDBW_SSZBS,(d.CLFW_HBXXLDBW_ZBS-d.CLFW_HBXXLDBW_AJTHZBS-d.CLFW_HBXXLDBW_LXZBS),'1-进港实收总包数符合','2-进港实收总包数不符') " +
			" ) CLFW_HBXXGL_CLSXSTR,d.CLFW_HBXXLDBW_LDHM," +
			" a.CLFW_HBXXYL_ZDJID CLFW_HBXXYL_SFJID,a.CLFW_HBXXYL_SFJID CLFW_HBXXYL_ZDJID " ;
			
			sql += " from  CLFW_HBXXYL a,CLFW_HBXXLDBW_VIEW d,CP_BASE_ORG_DISTRICT f,CP_BASE_ORG_DISTRICT g ";
			
			
			sql += " where 1=1 and d.clfw_hbxxldbw_lddz = 'S' and a.CLFW_HBXXYL_YLDM = d.CLFW_HBXXYL_YLDM " +
			" and a.CLFW_HBXXYL_SFJID = f.DISTRICT_CODE and a.CLFW_HBXXYL_ZDJID = g.DISTRICT_CODE ";
	
			if(!EXDATAs[3].equals("null") && EXDATAs[3].trim().length()>0){
				sql += " and substr(d.CLFW_HBXXLDBW_FYRQSJ,0,8)  >= '" + EXDATAs[3].trim() + "' ";
			}
			
			if(!EXDATAs[4].equals("null") && EXDATAs[4].trim().length()>0){
				sql +=" and substr(d.CLFW_HBXXLDBW_FYRQSJ,0,8)  <= '" + EXDATAs[4].trim() + "' ";
			}
			
			if(!EXDATAs[2].equals("null") && EXDATAs[2].trim().length()>0){
				sql +=" and a.CLFW_HBXXYL_ZDJID = '" + EXDATAs[2].trim() + "'";
					}else if(!EXDATAs[1].equals("null") && EXDATAs[1].trim().length()>0){
						if(EXDATAs[1].trim().substring(4, 6).equals("00")){
							sql +=" and a.CLFW_HBXXYL_ZDJID like '" + EXDATAs[1].trim().substring(0, 4) + "%'";
						}else{
							sql +=" and a.CLFW_HBXXYL_ZDJID like '" + EXDATAs[1].trim() + "%'";
						}
					}else if(!EXDATAs[0].equals("null") && EXDATAs[0].trim().length()>0){
						sql +=" and a.CLFW_HBXXYL_ZDJID like '" + EXDATAs[0].trim().substring(0, 2) + "%'" ;
					}
			
			sql += " ) order by CLFW_HBXXGL_XSBZ, substr(CLFW_HBXXLDBW_FYRQSJ,0,8),CLFW_HBXXYL_SFJID,CLFW_HBXXYL_ZDJID,CLFW_HBXXLDBW_HBCC";
			
		}else if(LINEKEYs[0].equals("exptjclfwylxxbt")){
			
			sql=" select b.CLFWYLXXB_CSRQ, " +
					" decode(e.city_name,null,e.province_name,e.city_name)||e.county_name CLFWYLXXB_AYJID_STR, " +
					" a.CLFWYLXXBZ_YLMC, " +
					" b.CLFWYLXXB_LSH, " +
					" b.CLFWYLXXB_CPH, " +
					" decode(c.city_name,null,c.province_name,c.city_name)||c.county_name||decode(a.CLFWYLXXB_SFJNJSX,'1','(南航)','') CLFWYLXXB_SFJID_STR, " +
					" a.CLFWYLXXBZ_SJKCSJ,b.CLFWYLXXB_SJKCSJ, " +
					" decode(b.CLFWYLXXB_SFJGHSM,'1','正常发班','2','邮航落地晚点连带汽运晚点','3','本日非发班日期','错误数据') CLFWYLXXB_SFJGHSMSTR, " +
					" decode(b.CLFWYLXXB_SFJGH,'1','正常考核','2','只参与发班不参与准点考核','3','不参与发班准点考核','错误数据') CLFWYLXXB_SFJGH, " +
					" decode(d.city_name,null,d.province_name,d.city_name)||d.county_name||decode(a.CLFWYLXXB_ZDJNJSX,'1','(南航)','') CLFWYLXXB_ZDJID_STR, " +
					" a.CLFWYLXXBZ_SJDDSJ,b.CLFWYLXXB_SJDDSJ ";
			
			sql+= " from clfw_ylxxbz a,clfw_ylxxb b,CP_BASE_ORG_DISTRICT c,CP_BASE_ORG_DISTRICT d,CP_BASE_ORG_DISTRICT e ";
			
			sql+= " where 1=1 and a.CLFWYLXXBZ_SEQID = b.CLFWYLXXBZ_SEQID " +
			" and a.CLFWYLXXBZ_SFJID = c.DISTRICT_CODE and a.CLFWYLXXBZ_ZDJID = d.DISTRICT_CODE and a.CLFWYLXXBZ_AYJID = e.DISTRICT_CODE ";
	
			if(!EXDATAs[3].equals("null") && EXDATAs[3].trim().length()>0){
				sql += " and b.CLFWYLXXB_CSRQ  >= '" + EXDATAs[3].trim() + "' ";
			}
			
			if(!EXDATAs[4].equals("null") && EXDATAs[4].trim().length()>0){
				sql +=" and b.CLFWYLXXB_CSRQ  <= '" + EXDATAs[4].trim() + "' ";
			}
			
			if(!EXDATAs[2].equals("null") && EXDATAs[2].trim().length()>0){
				sql +=" and a.CLFWYLXXBZ_AYJID = '" + EXDATAs[2].trim() + "'";
					}else if(!EXDATAs[1].equals("null") && EXDATAs[1].trim().length()>0){
						if(EXDATAs[1].trim().substring(4, 6).equals("00")){
							sql +=" and a.CLFWYLXXBZ_AYJID like '" + EXDATAs[1].trim().substring(0, 4) + "%'";
						}else{
							sql +=" and a.CLFWYLXXBZ_AYJID like '" + EXDATAs[1].trim() + "%'";
						}
					}else if(!EXDATAs[0].equals("null") && EXDATAs[0].trim().length()>0){
						sql +=" and a.CLFWYLXXBZ_AYJID like '" + EXDATAs[0].trim().substring(0, 2) + "%'" ;
					}
			
			if(!EXDATAs[5].equals("null") && EXDATAs[5].trim().length()>0){
				if (EXDATAs[5].trim().equals("1")) {
					sqlwhere += " and  a.clfwylxxbz_sybz = '1' " ;
				} else if (EXDATAs[5].trim().equals("0")) {
					sqlwhere += " and  a.clfwylxxbz_sybz = '0' " ;
				}
			}
			
			sql += " order by b.CLFWYLXXB_CSRQ,a.CLFWYLXXBZ_AYJID,a.CLFWYLXXBZ_SFJID,a.CLFWYLXXBZ_ZDJID,a.CLFWYLXXBZ_SEQID ";
		}
		
		sql=sql+sqlfrom+sqlwhere+sqlorder;
		
		sqlCount=sqlCount+sqlfrom+sqlwhere;
		
		List<?> list = clfwQueryDao.getqueryexportall(sql);
		
		ClfwExportall clfwexportall = new ClfwExportall();
		
		EXPORTALLPATH = clfwexportall.exportall("clfw",LINEMAIN,LINENAME,list);
		
		return "json";
	}
	
	
	@SuppressWarnings("unchecked")
	public String queryclfwhkldwh() throws Exception {
		
		if(!userflag()){return "json";}

		String sql = "select CLFW_HBXXLDBW_SEQID,CLFW_HBXXGL_XSBZ," +
				" decode(f.city_name,null,f.province_name,f.city_name)||f.county_name CLFW_HBXXYL_SFJIDSTR," +
				" decode(g.city_name,null,g.province_name,g.city_name)||g.county_name CLFW_HBXXYL_ZDJIDSTR," +
				" CLFW_HBXXLDBW_FYRQSJ,CLFW_HBXXYL_YLDM,CLFW_HBXXLDBW_LDLSH,CLFW_HBXXLDBW_HBCC,CLFW_HBXXLDBW_ZBS," +
				" CLFW_HBXXLDBW_AJTHZBS,CLFW_HBXXLDBW_LXZBS,CLFW_HBXXLDBW_SSZBS,CLFW_HBXXLDBW_LDHM," +
				"OPE_INSERTTIME,ZDJOPE_INSERTTIME,CLFW_HBXXLDBW_SFJGH,CLFW_HBXXLDBW_ZDJGH";
		String sqlCount = "select count(*) ";
		
		String sqlfrom = " from ( ";
		
		sqlfrom += " select a.CLFW_HBXXYL_SFJID,a.CLFW_HBXXYL_ZDJID,a.CLFW_HBXXYL_YLDM," +
				" d.CLFW_HBXXLDBW_SEQID,'0' CLFW_HBXXGL_XSBZ," +
				" d.CLFW_HBXXLDBW_FYRQSJ,d.CLFW_HBXXLDBW_LDLSH,d.CLFW_HBXXLDBW_HBCC,d.CLFW_HBXXLDBW_ZBS," +
				" d.CLFW_HBXXLDBW_AJTHZBS,d.CLFW_HBXXLDBW_LXZBS,d.CLFW_HBXXLDBW_SSZBS,d.CLFW_HBXXLDBW_LDHM," +
				" d.OPE_INSERTTIME,d.ZDJOPE_INSERTTIME,d.CLFW_HBXXLDBW_SFJGH,d.CLFW_HBXXLDBW_ZDJGH " +
				" from  CLFW_HBXXYL a,CLFW_HBXXLDBW_VIEW d " +
				" where 1=1 and d.clfw_hbxxldbw_lddz = 'S' and  a.CLFW_HBXXYL_YLDM = d.CLFW_HBXXYL_YLDM ";
		
		if(!ITEM_DATESS.equals("null") && ITEM_DATESS.length()>0){
			sqlfrom = sqlfrom + " and substr(d.CLFW_HBXXLDBW_FYRQSJ,0,8)  >= '" + ITEM_DATESS + "'";
		}
		
		if(!ITEM_DATESE.equals("null") && ITEM_DATESE.length()>0){
			sqlfrom = sqlfrom + " and substr(d.CLFW_HBXXLDBW_FYRQSJ,0,8)  <= '" + ITEM_DATESE + "' ";
		}

		if(!COUNTY_ID.equals("null") && COUNTY_ID.length()>0){
			sqlfrom = sqlfrom + " and a.CLFW_HBXXYL_SFJID = '" + COUNTY_ID + "'";
				}else if(!CITY_ID.equals("null") && CITY_ID.length()>0){
					if(CITY_ID.substring(4, 6).equals("00")){
						sqlfrom = sqlfrom + " and a.CLFW_HBXXYL_SFJID like '" + CITY_ID.substring(0, 4) + "%'";
					}else{
						sqlfrom = sqlfrom + " and a.CLFW_HBXXYL_SFJID like '" + CITY_ID + "%'";
					}
				}else if(!PROVINCE_ID.equals("null") && PROVINCE_ID.length()>0){
					sqlfrom = sqlfrom + " and a.CLFW_HBXXYL_SFJID like '" + PROVINCE_ID.substring(0, 2) + "%'";
				}
		
		sqlfrom += " union ";
		
		sqlfrom += " select a.CLFW_HBXXYL_SFJID,a.CLFW_HBXXYL_ZDJID,a.CLFW_HBXXYL_YLDM," +
		" d.CLFW_HBXXLDBW_SEQID,'1' CLFW_HBXXGL_XSBZ," +
		" d.CLFW_HBXXLDBW_FYRQSJ,d.CLFW_HBXXLDBW_LDLSH,d.CLFW_HBXXLDBW_HBCC,d.CLFW_HBXXLDBW_ZBS," +
		" d.CLFW_HBXXLDBW_AJTHZBS,d.CLFW_HBXXLDBW_LXZBS,d.CLFW_HBXXLDBW_SSZBS,d.CLFW_HBXXLDBW_LDHM," +
		" d.OPE_INSERTTIME,d.ZDJOPE_INSERTTIME,d.CLFW_HBXXLDBW_SFJGH,d.CLFW_HBXXLDBW_ZDJGH " +
		" from  CLFW_HBXXYL a,CLFW_HBXXLDBW_VIEW d " +
		" where 1=1 and d.clfw_hbxxldbw_lddz = 'S' and a.CLFW_HBXXYL_YLDM = d.CLFW_HBXXYL_YLDM ";

		if(!ITEM_DATESS.equals("null") && ITEM_DATESS.length()>0){
			sqlfrom = sqlfrom + " and substr(d.CLFW_HBXXLDBW_FYRQSJ,0,8)  >= '" + ITEM_DATESS + "'";
		}
		
		if(!ITEM_DATESE.equals("null") && ITEM_DATESE.length()>0){
			sqlfrom = sqlfrom + " and substr(d.CLFW_HBXXLDBW_FYRQSJ,0,8)  <= '" + ITEM_DATESE + "' ";
		}
		
		if(!COUNTY_ID.equals("null") && COUNTY_ID.length()>0){
			sqlfrom = sqlfrom + " and a.CLFW_HBXXYL_ZDJID = '" + COUNTY_ID + "'";
				}else if(!CITY_ID.equals("null") && CITY_ID.length()>0){
					if(CITY_ID.substring(4, 6).equals("00")){
						sqlfrom = sqlfrom + " and a.CLFW_HBXXYL_ZDJID like '" + CITY_ID.substring(0, 4) + "%'";
					}else{
						sqlfrom = sqlfrom + " and a.CLFW_HBXXYL_ZDJID like '" + CITY_ID + "%'";
					}
				}else if(!PROVINCE_ID.equals("null") && PROVINCE_ID.length()>0){
					sqlfrom = sqlfrom + " and a.CLFW_HBXXYL_ZDJID like '" + PROVINCE_ID.substring(0, 2) + "%'";
				}
		
		sqlfrom += " ) e,CP_BASE_ORG_DISTRICT f,CP_BASE_ORG_DISTRICT g ";

		String sqlwhere = " where 1=1 and e.CLFW_HBXXYL_SFJID = f.DISTRICT_CODE and e.CLFW_HBXXYL_ZDJID = g.DISTRICT_CODE ";
		String sqlorder = " order by substr(e.CLFW_HBXXLDBW_FYRQSJ,0,8),CLFW_HBXXYL_SFJID,CLFW_HBXXYL_ZDJID ,CLFW_HBXXLDBW_HBCC";


		sql = sql + sqlfrom + sqlwhere + sqlorder;
		sqlCount = sqlCount + sqlfrom + sqlwhere;

		page.setPageSize(99999);
		if (pageNo != 0) {
			page.setPageNo(pageNo);
		}
		page = clfwQueryDao.getBeanQueryClfwhkldwh(sql, sqlCount, page);
		return "json";
	}
	
	@SuppressWarnings("unchecked")
	public String queryclfwhkldwhmx() throws Exception {
		
		if(!userflag()){return "json";}

		String sql=" ";
		
		String sqlCount=" select count(*) ";
		
		String sqlfrom=" ";

		String sqlwhere=" ";

		String sqlorder=" ";
		
		if(!CLFW_HBXXGL_CLSX.equals("null") && CLFW_HBXXGL_CLSX.length()>0){
			
			if(CLFW_HBXXGL_CLSX.equals("0")){
				
				sql=" select CLFW_HBXXLDBW_SEQID,'0' CLFW_HBXXGL_XSBZ," +
				" decode(f.city_name,null,f.province_name,f.city_name)||f.county_name CLFW_HBXXYL_SFJIDSTR," +
				" decode(g.city_name,null,g.province_name,g.city_name)||g.county_name CLFW_HBXXYL_ZDJIDSTR," +
				" CLFW_HBXXLDBW_FYRQSJ,a.CLFW_HBXXYL_YLDM,CLFW_HBXXLDBW_LDLSH,CLFW_HBXXLDBW_HBCC,CLFW_HBXXLDBW_ZBS," +
				" CLFW_HBXXLDBW_AJTHZBS,CLFW_HBXXLDBW_LXZBS,CLFW_HBXXLDBW_SSZBS," +
				" decode(d.CLFW_HBXXLDBW_SSZBS,null,'3-没有实收总包数', " +
				" decode(d.CLFW_HBXXLDBW_SSZBS,(d.CLFW_HBXXLDBW_ZBS-nvl(d.CLFW_HBXXLDBW_AJTHZBS,0)-nvl(d.CLFW_HBXXLDBW_LXZBS,0)),'1-出港实收总包数符合','2-出港实收总包数不符') " +
				" ) CLFW_HBXXGL_CLSXSTR,d.CLFW_HBXXLDBW_LDHM ";
				
				sqlfrom=" from  CLFW_HBXXYL a,CLFW_HBXXLDBW_VIEW d,CP_BASE_ORG_DISTRICT f,CP_BASE_ORG_DISTRICT g ";
				
				
				sqlwhere = " where 1=1 and d.clfw_hbxxldbw_lddz = 'S' and a.CLFW_HBXXYL_YLDM = d.CLFW_HBXXYL_YLDM " +
						" and a.CLFW_HBXXYL_SFJID = f.DISTRICT_CODE and a.CLFW_HBXXYL_ZDJID = g.DISTRICT_CODE ";
		
				if(!ITEM_DATESS.equals("null") && ITEM_DATESS.length()>0){
					sqlwhere = sqlwhere + " and substr(d.CLFW_HBXXLDBW_FYRQSJ,0,8)  >= '" + ITEM_DATESS + "'";
				}
				
				if(!ITEM_DATESE.equals("null") && ITEM_DATESE.length()>0){
					sqlwhere = sqlwhere + " and substr(d.CLFW_HBXXLDBW_FYRQSJ,0,8)  <= '" + ITEM_DATESE + "' ";
				}
		
				if(!COUNTY_ID.equals("null") && COUNTY_ID.length()>0){
					sqlwhere = sqlwhere + " and a.CLFW_HBXXYL_SFJID = '" + COUNTY_ID + "'";
						}else if(!CITY_ID.equals("null") && CITY_ID.length()>0){
							if(CITY_ID.substring(4, 6).equals("00")){
								sqlwhere = sqlwhere + " and a.CLFW_HBXXYL_SFJID like '" + CITY_ID.substring(0, 4) + "%'";
							}else{
								sqlwhere = sqlwhere + " and a.CLFW_HBXXYL_SFJID like '" + CITY_ID + "%'";
							}
						}else if(!PROVINCE_ID.equals("null") && PROVINCE_ID.length()>0){
							sqlwhere = sqlwhere + " and a.CLFW_HBXXYL_SFJID like '" + PROVINCE_ID.substring(0, 2) + "%'";
						}
				
				sqlorder=" order by  substr(d.CLFW_HBXXLDBW_FYRQSJ,0,8),CLFW_HBXXYL_SFJID,CLFW_HBXXYL_ZDJID,CLFW_HBXXLDBW_HBCC";
			}else if(CLFW_HBXXGL_CLSX.equals("1")){
				
				sql=" select CLFW_HBXXLDBW_SEQID,'1' CLFW_HBXXGL_XSBZ," +
				" decode(f.city_name,null,f.province_name,f.city_name)||f.county_name CLFW_HBXXYL_SFJIDSTR," +
				" decode(g.city_name,null,g.province_name,g.city_name)||g.county_name CLFW_HBXXYL_ZDJIDSTR," +
				" CLFW_HBXXLDBW_FYRQSJ,a.CLFW_HBXXYL_YLDM,CLFW_HBXXLDBW_LDLSH,CLFW_HBXXLDBW_HBCC,CLFW_HBXXLDBW_ZBS," +
				" CLFW_HBXXLDBW_AJTHZBS,CLFW_HBXXLDBW_LXZBS,CLFW_HBXXLDBW_SSZBS," +
				" decode(d.CLFW_HBXXLDBW_SSZBS,null,'3-没有实收总包数', " +
				" decode(d.CLFW_HBXXLDBW_SSZBS,(d.CLFW_HBXXLDBW_ZBS-nvl(d.CLFW_HBXXLDBW_AJTHZBS,0)-nvl(d.CLFW_HBXXLDBW_LXZBS,0)),'1-进港实收总包数符合','2-进港实收总包数不符') " +
				" ) CLFW_HBXXGL_CLSXSTR,d.CLFW_HBXXLDBW_LDHM ";
				
				sqlfrom=" from  CLFW_HBXXYL a,CLFW_HBXXLDBW_VIEW d,CP_BASE_ORG_DISTRICT f,CP_BASE_ORG_DISTRICT g ";
				
				
				sqlwhere = " where 1=1 and d.clfw_hbxxldbw_lddz = 'S' and a.CLFW_HBXXYL_YLDM = d.CLFW_HBXXYL_YLDM " +
						" and a.CLFW_HBXXYL_SFJID = f.DISTRICT_CODE and a.CLFW_HBXXYL_ZDJID = g.DISTRICT_CODE ";
		
				if(!ITEM_DATESS.equals("null") && ITEM_DATESS.length()>0){
					sqlwhere = sqlwhere + " and substr(d.CLFW_HBXXLDBW_FYRQSJ,0,8)  >= '" + ITEM_DATESS + "'";
				}
				
				if(!ITEM_DATESE.equals("null") && ITEM_DATESE.length()>0){
					sqlwhere = sqlwhere + " and substr(d.CLFW_HBXXLDBW_FYRQSJ,0,8)  <= '" + ITEM_DATESE + "' ";
				}
		
				if(!COUNTY_ID.equals("null") && COUNTY_ID.length()>0){
					sqlwhere = sqlwhere + " and a.CLFW_HBXXYL_ZDJID = '" + COUNTY_ID + "'";
						}else if(!CITY_ID.equals("null") && CITY_ID.length()>0){
							if(CITY_ID.substring(4, 6).equals("00")){
								sqlwhere = sqlwhere + " and a.CLFW_HBXXYL_ZDJID like '" + CITY_ID.substring(0, 4) + "%'";
							}else{
								sqlwhere = sqlwhere + " and a.CLFW_HBXXYL_ZDJID like '" + CITY_ID + "%'";
							}
						}else if(!PROVINCE_ID.equals("null") && PROVINCE_ID.length()>0){
							sqlwhere = sqlwhere + " and a.CLFW_HBXXYL_ZDJID like '" + PROVINCE_ID.substring(0, 2) + "%'";
						}
				
				sqlorder=" order by  substr(d.CLFW_HBXXLDBW_FYRQSJ,0,8),CLFW_HBXXYL_ZDJID,CLFW_HBXXYL_SFJID,CLFW_HBXXLDBW_HBCC";
			}
		
		}

		sql = sql + sqlfrom + sqlwhere + sqlorder;
		sqlCount = sqlCount + sqlfrom + sqlwhere;

		page.setPageSize(50);
		if (pageNo != 0) {
			page.setPageNo(pageNo);
		}
		page = clfwQueryDao.getBeanQueryClfwhkldwhmx(sql, sqlCount, page);
		return "json";
	}
	
	@SuppressWarnings("unchecked")
	public String queryclfwhkldwhtj() throws Exception {
		
		if(!userflag()){return "json";}
		
		String sql = "select CLFW_HBXXYL_SFJIDSTR,OPE_INSERTTIME,CLFW_HBXXLDBW_ZBS,CLFW_HBXXLDBW_AJTHZBS,CLFW_HBXXLDBW_LXZBS,CLFW_HBXXLDBW_SSZBS,CLFW_HBXXGL_XSBZ,CLFW_HBXXLDBW_SSZBSXU,CLFW_HBXXLDBW_SFJGH ";
		String sqlCount = "select count(*) ";
		
		String sqlfrom = " from ( ";
		
		sqlfrom +="select max(decode(f.city_name,null,f.province_name,f.city_name)||f.county_name) CLFW_HBXXYL_SFJIDSTR," +
		" count(*) OPE_INSERTTIME," +
		" sum(d.CLFW_HBXXLDBW_ZBS) CLFW_HBXXLDBW_ZBS," +
		" sum(d.CLFW_HBXXLDBW_AJTHZBS) CLFW_HBXXLDBW_AJTHZBS," +
		" sum(d.CLFW_HBXXLDBW_LXZBS) CLFW_HBXXLDBW_LXZBS," +
		" sum(d.CLFW_HBXXLDBW_SSZBS) CLFW_HBXXLDBW_SSZBS," +
		" '0' CLFW_HBXXGL_XSBZ," +
		" a.CLFW_HBXXYL_SFJID CLFW_HBXXYL_SFJID, " +
		" sum(d.CLFW_HBXXLDBW_SSZBSXU) CLFW_HBXXLDBW_SSZBSXU , " +
		" sum(decode(d.CLFW_HBXXLDBW_SFJGH,'1',1,0)) CLFW_HBXXLDBW_SFJGH " +
		" from  CLFW_HBXXYL a,CLFW_HBXXLDBW_VIEW d,CP_BASE_ORG_DISTRICT f ";
		
		
		sqlfrom += " where 1=1 and d.clfw_hbxxldbw_lddz = 'S' and  a.CLFW_HBXXYL_YLDM = d.CLFW_HBXXYL_YLDM " +
				" and a.CLFW_HBXXYL_SFJID = f.DISTRICT_CODE ";

		if(!ITEM_DATESS.equals("null") && ITEM_DATESS.length()>0){
			sqlfrom = sqlfrom + " and substr(d.CLFW_HBXXLDBW_FYRQSJ,0,8)  >= '" + ITEM_DATESS + "'";
		}
		
		if(!ITEM_DATESE.equals("null") && ITEM_DATESE.length()>0){
			sqlfrom = sqlfrom + " and substr(d.CLFW_HBXXLDBW_FYRQSJ,0,8)  <= '" + ITEM_DATESE + "' ";
		}

		if(!COUNTY_ID.equals("null") && COUNTY_ID.length()>0){
			sqlfrom = sqlfrom + " and a.CLFW_HBXXYL_SFJID = '" + COUNTY_ID + "'";
				}else if(!CITY_ID.equals("null") && CITY_ID.length()>0){
					if(CITY_ID.substring(4, 6).equals("00")){
						sqlfrom = sqlfrom + " and a.CLFW_HBXXYL_SFJID like '" + CITY_ID.substring(0, 4) + "%'";
					}else{
						sqlfrom = sqlfrom + " and a.CLFW_HBXXYL_SFJID like '" + CITY_ID + "%'";
					}
				}else if(!PROVINCE_ID.equals("null") && PROVINCE_ID.length()>0){
					sqlfrom = sqlfrom + " and a.CLFW_HBXXYL_SFJID like '" + PROVINCE_ID.substring(0, 2) + "%'";
				}
		
		sqlfrom +=" group by a.CLFW_HBXXYL_SFJID ";

		sqlfrom += " union ";
		
		sqlfrom +="select max(decode(f.city_name,null,f.province_name,f.city_name)||f.county_name) CLFW_HBXXYL_SFJIDSTR," +
		" count(*) OPE_INSERTTIME," +
		" sum(d.CLFW_HBXXLDBW_ZBS) CLFW_HBXXLDBW_ZBS," +
		" sum(d.CLFW_HBXXLDBW_AJTHZBS) CLFW_HBXXLDBW_AJTHZBS," +
		" sum(d.CLFW_HBXXLDBW_LXZBS) CLFW_HBXXLDBW_LXZBS," +
		" sum(d.CLFW_HBXXLDBW_SSZBS) CLFW_HBXXLDBW_SSZBS," +
		" '1' CLFW_HBXXGL_XSBZ," +
		" a.CLFW_HBXXYL_ZDJID CLFW_HBXXYL_SFJID, " +
		" sum(d.CLFW_HBXXLDBW_SSZBSXU)  CLFW_HBXXLDBW_SSZBSXU, " +
		" sum(decode(d.CLFW_HBXXLDBW_ZDJGH,'1',1,0)) CLFW_HBXXLDBW_SFJGH " +
		" from  CLFW_HBXXYL a,CLFW_HBXXLDBW_VIEW d,CP_BASE_ORG_DISTRICT f ";
		
		
		sqlfrom += " where 1=1 and a.CLFW_HBXXYL_YLDM = d.CLFW_HBXXYL_YLDM " +
				" and a.CLFW_HBXXYL_ZDJID = f.DISTRICT_CODE ";

		if(!ITEM_DATESS.equals("null") && ITEM_DATESS.length()>0){
			sqlfrom = sqlfrom + " and substr(d.CLFW_HBXXLDBW_FYRQSJ,0,8)  >= '" + ITEM_DATESS + "'";
		}
		
		if(!ITEM_DATESE.equals("null") && ITEM_DATESE.length()>0){
			sqlfrom = sqlfrom + " and substr(d.CLFW_HBXXLDBW_FYRQSJ,0,8)  <= '" + ITEM_DATESE + "' ";
		}

		if(!COUNTY_ID.equals("null") && COUNTY_ID.length()>0){
			sqlfrom = sqlfrom + " and a.CLFW_HBXXYL_ZDJID = '" + COUNTY_ID + "'";
				}else if(!CITY_ID.equals("null") && CITY_ID.length()>0){
					if(CITY_ID.substring(4, 6).equals("00")){
						sqlfrom = sqlfrom + " and a.CLFW_HBXXYL_ZDJID like '" + CITY_ID.substring(0, 4) + "%'";
					}else{
						sqlfrom = sqlfrom + " and a.CLFW_HBXXYL_ZDJID like '" + CITY_ID + "%'";
					}
				}else if(!PROVINCE_ID.equals("null") && PROVINCE_ID.length()>0){
					sqlfrom = sqlfrom + " and a.CLFW_HBXXYL_ZDJID like '" + PROVINCE_ID.substring(0, 2) + "%'";
				}
		
		sqlfrom +=" group by a.CLFW_HBXXYL_ZDJID ";
		
		sqlfrom += " ) e ";
		
		String sqlwhere = " where 1=1 ";

		String sqlorder =" order by CLFW_HBXXYL_SFJID ";
		
		sql=sql+sqlfrom+sqlwhere+sqlorder;
		sqlCount=sqlCount+sqlfrom+sqlwhere;
		
		page.setPageSize(99999);
		
		if(pageNo!=0){
			page.setPageNo(pageNo);
		}
		page=clfwQueryDao.getBeanQueryClfwhkldwhtj(sql, sqlCount, page);
		
		return "json";
	}
}
