package com.cpst.emsadrdb.web.disp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.cpst.core.orm.Page;
import com.cpst.core.web.struts2.BaseActionSupport;
import com.cpst.emsadrdb.dao.disp.DispQueryDao;
import com.cpst.emsadrdb.service.disp.DispCommon;
import com.cpst.emsadrdb.service.export.Exportall;
import com.cpst.emsadrdb.service.export.Exportmathod;


@ParentPackage("jsoncrud")
@Results( {@Result(type="json", name = "json")})
public class B07r02dispqueryAction  extends BaseActionSupport{

	private static final long serialVersionUID = -6071823460042875876L;
	
	private String saveMessage;
	
	private String q;//ajax参数
	private int pageNo;
	@SuppressWarnings("unchecked")
	private Page page=new Page(10);
	
	@Autowired
	private DispQueryDao dispQueryDao;
	
	private String PROVINCE_NAME;
	private String PROVINCE_CODE;


	private String DISP_OFFICE_NAME;
	private String TRANS_NAME;
	private String DISP_OFFICE_CODE;
	private String COUNTY_NAME;
	private String CITY_NAME;
	private String DISTRICT_CODE;
	
	private String EXPORTALLPATH;
	
	
	private String LINENAME;
	private String LINELIST;
	
	private String ITEM_DATES;
	private String ITEM_DATESS;
	private String ITEM_DATESE;
	
	private String IS_TOUDIQU;
	private String IS_CUSPEO;
	private String IS_DISTRI;
	
	private String pagecount;
	private String EXDATA;
	private String EXREPORT;
	private String LINEMAIN;
	
	private String ITEMNO;
	
	
	
	public String getITEMNO() {
		return ITEMNO;
	}


	public void setITEMNO(String itemno) {
		ITEMNO = DispCommon.sql_inj(itemno);
	}


	public String getSaveMessage() {
		return saveMessage;
	}


	public void setSaveMessage(String saveMessage) {
		this.saveMessage = saveMessage;
	}


	public String getLINEMAIN() {
		return LINEMAIN;
	}


	public void setLINEMAIN(String linemain) {
		LINEMAIN = DispCommon.sql_inj(linemain);
	}


	public String getLINEKEY() {
		return LINEKEY;
	}


	public void setLINEKEY(String linekey) {
		LINEKEY = DispCommon.sql_inj(linekey);
	}


	private String LINEKEY;
	
	
	
	
	public String getEXDATA() {
		return EXDATA;
	}


	public void setEXDATA(String exdata) {
		EXDATA = DispCommon.sql_inj(exdata);
	}


	public String getEXREPORT() {
		return EXREPORT;
	}


	public void setEXREPORT(String exreport) {
		EXREPORT = DispCommon.sql_inj(exreport);
	}


	public String getPagecount() {
		return pagecount;
	}


	public void setPagecount(String pagecount) {
		this.pagecount = DispCommon.sql_inj(pagecount);
	}


	public String getIS_DISTRI() {
		return IS_DISTRI;
	}


	public void setIS_DISTRI(String is_distri) {
		IS_DISTRI = DispCommon.sql_inj(is_distri);
	}


	public String getIS_CUSPEO() {
		return IS_CUSPEO;
	}


	public void setIS_CUSPEO(String is_cuspeo) {
		IS_CUSPEO = DispCommon.sql_inj(is_cuspeo);
	}


	public String getIS_TOUDIQU() {
		return IS_TOUDIQU;
	}


	public void setIS_TOUDIQU(String is_toudiqu) {
		IS_TOUDIQU = DispCommon.sql_inj(is_toudiqu);
	}


	public String getITEM_DATESS() {
		return ITEM_DATESS;
	}


	public void setITEM_DATESS(String item_datess) {
		ITEM_DATESS = DispCommon.sql_inj(item_datess);
	}


	public String getITEM_DATESE() {
		return ITEM_DATESE;
	}


	public void setITEM_DATESE(String item_datese) {
		ITEM_DATESE = DispCommon.sql_inj(item_datese);
	}


	public String getITEM_DATES() {
		return ITEM_DATES;
	}


	public void setITEM_DATES(String item_dates) {
		ITEM_DATES = DispCommon.sql_inj(item_dates);
	}


	public String getLINENAME() {
		return LINENAME;
	}


	public void setLINENAME(String linename) {
		LINENAME = DispCommon.sql_inj(linename);
	}

	public String getLINELIST() {
		return LINELIST;
	}


	public void setLINELIST(String linelist) {
		LINELIST = DispCommon.sql_inj(linelist);
	}


	public String getEXPORTALLPATH() {
		return EXPORTALLPATH;
	}


	public void setEXPORTALLPATH(String exportallpath) {
		EXPORTALLPATH = DispCommon.sql_inj(exportallpath);
	}


	public String getDISTRICT_CODE() {
		return DISTRICT_CODE;
	}


	public void setDISTRICT_CODE(String district_code) {
		DISTRICT_CODE = DispCommon.sql_inj(district_code);
	}


	public String getPROVINCE_CODE() {
		return PROVINCE_CODE;
	}


	public void setPROVINCE_CODE(String province_code) {
		PROVINCE_CODE = DispCommon.sql_inj(province_code);
	}


	public String getCOUNTY_NAME() {
		return COUNTY_NAME;
	}


	public void setCOUNTY_NAME(String county_name) {
		COUNTY_NAME = DispCommon.sql_inj(county_name);
	}


	public String getCITY_NAME() {
		return CITY_NAME;
	}


	public void setCITY_NAME(String city_name) {
		CITY_NAME = DispCommon.sql_inj(city_name);
	}


	public String getDISP_OFFICE_CODE() {
		return DISP_OFFICE_CODE;
	}


	public void setDISP_OFFICE_CODE(String disp_office_code) {
		DISP_OFFICE_CODE = DispCommon.sql_inj(disp_office_code);
	}


	public String getPROVINCE_NAME() {
		return PROVINCE_NAME;
	}


	public void setPROVINCE_NAME(String province_name) {
		PROVINCE_NAME = DispCommon.sql_inj(province_name);
	}


	public String getDISP_OFFICE_NAME() {
		return DISP_OFFICE_NAME;
	}


	public void setDISP_OFFICE_NAME(String disp_office_name) {
		DISP_OFFICE_NAME = DispCommon.sql_inj(disp_office_name);
	}


	public String getTRANS_NAME() {
		return TRANS_NAME;
	}


	public void setTRANS_NAME(String trans_name) {
		TRANS_NAME = DispCommon.sql_inj(trans_name);
	}


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


	@SuppressWarnings("unchecked")
	public Page getPage() {
		return page;
	}


	@SuppressWarnings("unchecked")
	public void setPage(Page page) {
		this.page = page;
	}
	
	
	
	@SuppressWarnings("unchecked")
	public String querydisps() throws Exception {
		
		
		
		if(userflag()==null){return "json";}
		
		
		String sql="select a.DISP_OFFICE_CODE,a.DISP_OFFICE_NAME,a.PROVINCE_CODE,a.PROVINCE_NAME,a.DEGREE ," +
		//" CASE WHEN (length(a.DISP_OFFICE_CODE) > 0 )  THEN (select wmsys.wm_concat(c.TRANS_NAME||'('||c.PROVINCE_NAME||')'||'|'||c.TRANS_CODE||'|'||c.FLAG||'|'||decode(c.degree,2,(select decode(max(f.dt_pk_code),null,'null',max(f.dt_pk_code)) from (select e.city_code,e.dt_pk_code from cp_wh_trans_office_dis e  group by e.city_code,e.dt_pk_code) f  where f.city_code = c.trans_code group by f.city_code having count(*) = 1),decode(d.DT_PK_CODE,null,'null',d.DT_PK_CODE))) from CP_BASE_TRANS_OFFICE c,cp_wh_trans_office_dis d  where c.TRANS_CODE = d.TRANS_CODE(+) and c.DISP_OFFICE_CODE = a.DISP_OFFICE_CODE  group by c.DISP_OFFICE_CODE) END AS TOTAL_TRANS_NAME , " +
		//" CASE WHEN (length(a.DISP_OFFICE_CODE) > 0 )  THEN (select wmsys.wm_concat(c.TRANS_NAME||'|'||c.TRANS_CODE||'|'||c.FLAG||'|'||decode(c.degree,2,(select decode(max(f.dt_pk_code),null,'null',max(f.dt_pk_code)) from (select e.city_code,e.dt_pk_code from cp_wh_trans_office_dis e  group by e.city_code,e.dt_pk_code) f  where f.city_code = c.trans_code group by f.city_code having count(*) = 1),decode(d.DT_PK_CODE,null,'null',d.DT_PK_CODE))) from CP_BASE_TRANS_OFFICE c,cp_wh_trans_office_dis d  where c.TRANS_CODE = d.TRANS_CODE(+) and c.DISP_OFFICE_CODE = a.DISP_OFFICE_CODE  group by c.DISP_OFFICE_CODE) END AS TOTAL_TRANS_NAME , " +
		//" CASE WHEN (length(a.DISP_OFFICE_CODE) > 0 )  THEN (select wmsys.wm_concat(c.TRANS_NAME||'|'||c.TRANS_CODE||'|'||c.FLAG||'|'||decode(c.degree,2,(select decode(count(*),1,to_char(max(f.dt_pk_code)),'null') from (select e.city_code,e.dt_pk_code from cp_wh_trans_office_dis e  group by e.city_code,e.dt_pk_code) f  where f.city_code = c.trans_code group by f.city_code),decode(d.DT_PK_CODE,null,'null',d.DT_PK_CODE))) from CP_BASE_TRANS_OFFICE c,cp_wh_trans_office_dis d  where c.TRANS_CODE = d.TRANS_CODE(+) and c.DISP_OFFICE_CODE = a.DISP_OFFICE_CODE  group by c.DISP_OFFICE_CODE) END AS TOTAL_TRANS_NAME , " +
		//" CASE WHEN (length(a.DISP_OFFICE_CODE) > 0 )  THEN (select wmsys.wm_concat(c.TRANS_NAME||'|'||c.TRANS_CODE||'|'||c.FLAG||'|'||decode(c.degree,2,(select to_char(max(f.dt_pk_code)) from (select e.city_code,e.dt_pk_code from cp_wh_trans_office_dis e  group by e.city_code,e.dt_pk_code) f  where f.city_code = c.trans_code group by f.city_code having count(*) = 1 ),decode(d.DT_PK_CODE,null,'null',d.DT_PK_CODE))) from CP_BASE_TRANS_OFFICE c,cp_wh_trans_office_dis d  where c.TRANS_CODE = d.TRANS_CODE(+) and c.DISP_OFFICE_CODE = a.DISP_OFFICE_CODE  group by c.DISP_OFFICE_CODE) END AS TOTAL_TRANS_NAME , " +
		" CASE WHEN (length(a.DISP_OFFICE_CODE) > 0 )  THEN (select wmsys.wm_concat(c.TRANS_NAME||'|'||c.TRANS_CODE||'|'||c.FLAG||'|'||decode(c.degree,2,(select to_char(max(f.dt_pk_code)) from (select e.city_code,e.dt_pk_code from cp_wh_trans_office_dis e  group by e.city_code,e.dt_pk_code) f  where f.city_code = c.trans_code group by f.city_code having count(*) = 1 ),decode(d.DT_PK_CODE,null,'',d.DT_PK_CODE))) from CP_BASE_TRANS_OFFICE c,cp_wh_trans_office_dis d  where c.TRANS_CODE = d.TRANS_CODE(+) and c.DISP_OFFICE_CODE = a.DISP_OFFICE_CODE  group by c.DISP_OFFICE_CODE) END AS TOTAL_TRANS_NAME , " +
		" CASE WHEN (length(a.DISP_OFFICE_CODE) > 0 )  THEN (select wmsys.wm_concat(d.DT_NAME||'('||d.DT_ALIAS_NAME||')'||'|'||c.DT_PK_CODE) from cp_wh_disp_office_dis c,CP_WH_DISTRICT d  where c.DT_PK_CODE = d.DT_PK_CODE and c.DISP_OFFICE_CODE = a.DISP_OFFICE_CODE  group by c.DISP_OFFICE_CODE) END AS TOTAL_WH , " +
		" a.DISP_OFFICE_ABBR ,"+
		" CASE WHEN (length(a.DISP_OFFICE_CODE) > 0 )  THEN (select wmsys.wm_concat(c.TRANS_NAME||'('||d.city_name||')'||'|'||c.TRANS_CODE) from CP_BASE_TRANS_OFFICE c,CP_BASE_ORG_DISTRICT d  where c.TRANS_CODE = d.DISTRICT_CODE and c.DISP_OFFICE_CODE = a.DISP_OFFICE_CODE and c.FLAG > 1 and c.degree = 3  group by c.DISP_OFFICE_CODE) END AS TOTAL_TRANS_CHSNAME," +
		//" CASE WHEN (length(a.DISP_OFFICE_CODE) > 0 )  THEN (select wmsys.wm_concat(d.city_name||'|'||d.DISTRICT_CODE) from (select distinct substr(TRANS_CODE,0,4)||'00' TRANS_CODE,DISP_OFFICE_CODE,degree,flag from CP_BASE_TRANS_OFFICE) c,CP_BASE_ORG_DISTRICT d  where c.TRANS_CODE = d.DISTRICT_CODE and c.DISP_OFFICE_CODE = a.DISP_OFFICE_CODE and c.flag > 1 and c.degree = 3  group by c.DISP_OFFICE_CODE) END AS TOTAL_TRANS_CITY_CHSNAME ";
		" CASE WHEN (length(a.DISP_OFFICE_CODE) > 0 )  THEN (select wmsys.wm_concat(d.city_name||'|'||d.DISTRICT_CODE) from (select distinct substr(TRANS_CODE,0,4)||'00' TRANS_CODE,DISP_OFFICE_CODE,flag from CP_BASE_TRANS_OFFICE) c,CP_BASE_ORG_DISTRICT d  where c.TRANS_CODE = d.DISTRICT_CODE and c.DISP_OFFICE_CODE = a.DISP_OFFICE_CODE and c.flag > 1  group by c.DISP_OFFICE_CODE) END AS TOTAL_TRANS_CITY_CHSNAME ";
		String sqlCount="select count(*) ";
		String sqlfrom=" from CP_BASE_DISP_OFFICE a ";
		String sqlwhere=" where 1=1 ";
		String sqlorder=" ) order by DISP_OFFICE_CODE";
				
		if(!PROVINCE_NAME.equals("null") && PROVINCE_NAME.length()>0){
			sqlwhere = sqlwhere + " and a.PROVINCE_NAME  = '" + PROVINCE_NAME + "' ";
		}
		
		if(!DISP_OFFICE_NAME.equals("null") && DISP_OFFICE_NAME.length()>0){
			sqlwhere = sqlwhere + " and (a.DISP_OFFICE_NAME like '%" + DISP_OFFICE_NAME + "%'" + "or" + " a.DISP_OFFICE_ABBR like '%" + DISP_OFFICE_NAME + "%'" + ") ";
		}
		
		if(!TRANS_NAME.equals("null") && TRANS_NAME.length()>0){
			sqlwhere = sqlwhere + " and a.DISP_OFFICE_CODE in (select DISP_OFFICE_CODE from CP_BASE_TRANS_OFFICE where TRANS_NAME like '%" + TRANS_NAME + "%' ) ";
		}

		String sqltochar = "select " + 
		"DISP_OFFICE_CODE,DISP_OFFICE_NAME,PROVINCE_CODE,PROVINCE_NAME,DEGREE, " + 
		"to_char(substr(TOTAL_TRANS_NAME,1,4000)),to_char(substr(TOTAL_WH,1,4000)),DISP_OFFICE_ABBR,to_char(substr(TOTAL_TRANS_CHSNAME,1,4000)),to_char(substr(TOTAL_TRANS_CITY_CHSNAME,1,4000)) " + 
		"from ( ";
		sql=sqltochar + sql+sqlfrom+sqlwhere+sqlorder;
		sqlCount=sqlCount+sqlfrom+sqlwhere;
		
		page.setPageSize(99999);
		if(pageNo!=0){
			page.setPageNo(pageNo);
		}
		page=dispQueryDao.getBeanQueryDisps(sql, sqlCount, page);
		return "json";
	}
	
	@SuppressWarnings("unchecked")
	public String querytddisp() throws Exception {
		
		if(userflag()==null){return "json";}
		
		String sql="select b.DT_NAME,b.DT_ALIAS_NAME ";
		String sqlCount="select count(*) ";
		String sqlfrom=" from CP_WH_DISP_OFFICE_DIS a ,CP_WH_DISTRICT b";
		String sqlwhere=" where a.DT_PK_CODE=b.DT_PK_CODE ";
		String sqlorder=" order by b.DT_NAME,b.DT_ALIAS_NAME";
				
		if(!DISP_OFFICE_CODE.equals("null") && DISP_OFFICE_CODE.length()>0){
			sqlwhere = sqlwhere + " and a.DISP_OFFICE_CODE  = '" + DISP_OFFICE_CODE + "' ";
		}
		
		sql=sql+sqlfrom+sqlwhere+sqlorder;
		sqlCount=sqlCount+sqlfrom+sqlwhere;
		
		page.setPageSize(99999);
		if(pageNo!=0){
			page.setPageNo(pageNo);
		}
		page=dispQueryDao.getBeanQueryTddispp(sql, sqlCount, page);
		return "json";
	}

	@SuppressWarnings("unchecked")
	public String querytrans() throws Exception {
		
		if(userflag()==null){return "json";}
		
		String sql="select a.TRANS_CODE,a.TRANS_NAME,a.PROVINCE_CODE,a.PROVINCE_NAME,a.DISP_OFFICE_CODE,a.DEGREE,a.FLAG ";
		String sqlCount="select count(*) ";
		String sqlfrom=" from CP_BASE_TRANS_OFFICE a ";
		String sqlwhere=" where 1=1";
		String sqlorder=" order by a.FLAG,a.PROVINCE_NAME,a.TRANS_NAME";
				
		
		
		if(!DISP_OFFICE_CODE.equals("null") && DISP_OFFICE_CODE.length()>0){
			sqlwhere = sqlwhere + " and a.DISP_OFFICE_CODE = " + DISP_OFFICE_CODE ;
		}
		
		if(!TRANS_NAME.equals("null") && TRANS_NAME.length()>0){
			sqlwhere = sqlwhere + " and a.TRANS_NAME like '%" + TRANS_NAME + "%' ";
		}

		sql=sql+sqlfrom+sqlwhere+sqlorder;
		sqlCount=sqlCount+sqlfrom+sqlwhere;
		if(pageNo!=0){
			page.setPageNo(pageNo);
		}
		page=dispQueryDao.getBeanQueryTrans(sql, sqlCount, page);
		return "json";
	}
	
	
	@SuppressWarnings("unchecked")
	public String querywhtrans() throws Exception {
		
		if(userflag()==null){return "json";}
		
		/*
		String sql="select a.TRANS_CODE,a.TRANS_NAME,a.PROVINCE_CODE,a.PROVINCE_NAME||a.CITY_NAME,a.DISP_OFFICE_CODE,a.DEGREE,a.FLAG,b.DISP_OFFICE_NAME, "+
		" CASE WHEN (length(a.TRANS_CODE) > 0 )  THEN (select max(e.DT_NAME||'('||e.DT_ALIAS_NAME||')') from CP_WH_TRANS_OFFICE_DIS d,CP_WH_DISTRICT e  where   d.DT_PK_CODE = e.DT_PK_CODE and d.DT_PK_CODE is not null and d.TRANS_CODE = a.TRANS_CODE ) END AS TOTAL_TRANS_NAME";
		String sqlCount="select count(*) ";
		String sqlfrom=" from CP_BASE_TRANS_OFFICE_ALIAS a,CP_BASE_DISP_OFFICE b ";
		String sqlwhere=" where 1=1 and a.degree=3 and a.DISP_OFFICE_CODE = b.DISP_OFFICE_CODE ";
		String sqlorder=" order by a.FLAG,a.PROVINCE_NAME||a.CITY_NAME,a.TRANS_NAME";
		*/
		
		String sql="select a.TRANS_CODE,a.TRANS_NAME,a.PROVINCE_CODE,a.PROVINCE_NAME||a.CITY_NAME,a.DISP_OFFICE_CODE,a.DEGREE,a.FLAG,b.DISP_OFFICE_NAME, "+
		" CASE WHEN (length(a.TRANS_CODE) > 0 )  THEN (select max(e.DT_NAME||'('||e.DT_ALIAS_NAME||')') from CP_WH_TRANS_OFFICE_DIS d,CP_WH_DISTRICT e  where   d.DT_PK_CODE = e.DT_PK_CODE and d.DT_PK_CODE is not null and d.TRANS_CODE = a.TRANS_CODE ) END AS TOTAL_TRANS_NAME";
		String sqlCount="select count(*) ";
		String sqlfrom=" from CP_BASE_TRANS_OFFICE_ALIAS a,CP_BASE_DISP_OFFICE b ";
		String sqlwhere=" where 1=1 and (a.degree=3 or a.TRANS_CODE in (select substr(DISTRICT_CODE,0,4)||'00' from CP_BASE_ORG_DISTRICT c where substr(DISTRICT_CODE,3,4)<>'0000' group by substr(DISTRICT_CODE,0,4) having count(*) =1 )) and a.DISP_OFFICE_CODE = b.DISP_OFFICE_CODE ";
		String sqlorder=" order by a.FLAG,a.PROVINCE_NAME||a.CITY_NAME,a.TRANS_NAME";
		

		if(!DISP_OFFICE_CODE.equals("null") && DISP_OFFICE_CODE.length()>0){
			sqlwhere = sqlwhere + " and a.DISP_OFFICE_CODE = " + DISP_OFFICE_CODE ;
		}
		
		if(!TRANS_NAME.equals("null") && TRANS_NAME.length()>0){
			sqlwhere = sqlwhere + " and a.TRANS_NAME like '%" + TRANS_NAME + "%' ";
		}

		sql=sql+sqlfrom+sqlwhere+sqlorder;
		sqlCount=sqlCount+sqlfrom+sqlwhere;
		
		page.setPageSize(20);
		
		if(pageNo!=0){
			page.setPageNo(pageNo);
		}
		page=dispQueryDao.getBeanQueryWhtrans(sql, sqlCount, page);
		return "json";
	}
	
	
	@SuppressWarnings("unchecked")
	public String querydistrict() throws Exception {
		
		if(userflag()==null){return "json";}
		
		String sql="select a.DISTRICT_CODE,a.PROVINCE_NAME,a.CITY_NAME,a.COUNTY_NAME,a.CITY_FLAG ";
		String sqlCount="select count(*) ";
		String sqlfrom=" from CP_BASE_ORG_DISTRICT a ";
		String sqlwhere=" where DEGREE=3 ";
		String sqlorder=" order by a.CITY_FLAG desc,a.DISTRICT_CODE";
				
		if(!PROVINCE_NAME.equals("null") && PROVINCE_NAME.length()>0){
			sqlwhere = sqlwhere + " and a.PROVINCE_NAME = '" + PROVINCE_NAME + "'";
		}
		
		if(!CITY_NAME.equals("null") && CITY_NAME.length()>0){
			sqlwhere = sqlwhere + " and a.CITY_NAME = '" + CITY_NAME + "'";
		}
		
		if(!COUNTY_NAME.equals("null") && COUNTY_NAME.length()>0){
			sqlwhere = sqlwhere + " and a.COUNTY_NAME like '%" + COUNTY_NAME + "%' ";
		}

		sql=sql+sqlfrom+sqlwhere+sqlorder;
		sqlCount=sqlCount+sqlfrom+sqlwhere;
		
		page.setPageSize(30);
		if(pageNo!=0){
			page.setPageNo(pageNo);
		}
		page=dispQueryDao.getBeanQueryDistrict(sql, sqlCount, page);
		return "json";
	}
	
	@SuppressWarnings("unchecked")
	public String querytjpqb() throws Exception {
		
		if(userflag()==null){return "json";}
		
		String sql="select a.DISTRICT_CODE,a.PROVINCE_NAME,a.SJ_CODE,a.DT_CODE,a.DT_SJ_CODE,a.DT_SJNO_CODE,a.PG_CODE,a.PG_SJ_CODE,a.PG_SJNO_CODE,a.DM_SJ_CODE ";
		String sqlCount="select count(*) ";
		String sqlfrom=" from (" +
				" select a.DISTRICT_CODE,a.PROVINCE_NAME,a.SJ_CODE,a.DT_CODE,a.DT_SJ_CODE,a.DT_SJNO_CODE,a.PG_CODE,a.PG_SJ_CODE,a.PG_SJNO_CODE,a.DM_SJ_CODE from tj_province_quanbushuju a " +
				" union all" +
				" select '','全国',sum(a.SJ_CODE),sum(a.DT_CODE),sum(a.DT_SJ_CODE),sum(a.DT_SJNO_CODE),sum(a.PG_CODE),sum(a.PG_SJ_CODE),sum(a.PG_SJNO_CODE),sum(a.DM_SJ_CODE) from tj_province_quanbushuju a ) a ";
		String sqlwhere=" where 1=1 ";
		String sqlorder=" order by a.DISTRICT_CODE ";
			

		sql=sql+sqlfrom+sqlwhere+sqlorder;
		sqlCount=sqlCount+sqlfrom+sqlwhere;
		
		page.setPageSize(100);
		if(pageNo!=0){
			page.setPageNo(pageNo);
		}
		page=dispQueryDao.getBeanQuerytjpqb(sql, sqlCount, page);
		return "json";
	}
	
	public String queryexporttjpqb() throws Exception {
		
		if(userflag()==null){return "json";}
		
		String sql="select a.DISTRICT_CODE,a.PROVINCE_NAME,a.SJ_CODE,a.DT_CODE,a.DT_SJ_CODE,a.DT_SJNO_CODE,a.DM_SJ_CODE,a.PG_CODE,a.PG_SJ_CODE,a.PG_SJNO_CODE ";
		String sqlfrom=" from tj_province_quanbushuju a ";
		String sqlwhere=" where 1=1 ";
		String sqlorder=" order by a.DISTRICT_CODE ";
			

		sql=sql+sqlfrom+sqlwhere+sqlorder;
				
		EXPORTALLPATH = dispQueryDao.getBeanQueryexporttjpqb(sql);
		
		if(pageNo!=0){
			page.setPageNo(pageNo);
		}
		
		return "json";
	}
	
	@SuppressWarnings("unchecked")
	public String querytjcqb() throws Exception {
		
		if(userflag()==null){return "json";}
		
		String sql="";
		String sqlCount="";
		String sqlfrom="";
		String sqlwhere="";
		String sqlorder="";
		
		if(PROVINCE_CODE==null || PROVINCE_CODE.equals("null") || PROVINCE_CODE.length()<1){
			sql="select a.district_code,a.province_name,a.city_name,sj_code,dt_code,dt_sj_code,dt_sjno_code,pg_code,pg_sj_code,pg_sjno_code  ";
			sqlCount="select count(*) ";
			sqlfrom=" from tj_city_quanbushuju a ";
			sqlwhere=" where 1=1 ";
			sqlorder=" order by a.DISTRICT_CODE ";
			if(!IS_TOUDIQU.equals("null") && IS_TOUDIQU.length()>0){
				if(IS_TOUDIQU.equals("1")){
					sqlwhere = sqlwhere + " and dt_code = 1 ";
				}else if(IS_TOUDIQU.equals("2")){
					sqlwhere = sqlwhere + " and dt_code > 1 ";
				}else if(IS_TOUDIQU.equals("3")){
					sqlwhere = sqlwhere + " and dt_code > 2 ";
				}
			}
		}
		else{
			
		
			sql="select a.district_code,a.province_name,a.city_name, " +
					" (select count(*) from cp_wh_rl_pg_st_" + PROVINCE_CODE.substring(0, 2) + " where RSDNBLDG_ID is null and ORG_ID is null ) sj_code, " +
					" (select count(*) from( " +
					" select distinct dt_pk_code from cp_wh_disp_office_dis where dt_pk_code is not null and  substr(disp_office_code,0,2) = '" + PROVINCE_CODE.substring(0, 2) + "' " +
					" union " +
					" select distinct dt_pk_code from cp_wh_trans_office_dis where dt_pk_code is not null and  substr(trans_code,0,2) = '" + PROVINCE_CODE.substring(0, 2) + "')) dt_code, " +
					" (select count(*) from (select distinct dt_pk_code from cp_wh_rl_pg_st_" + PROVINCE_CODE.substring(0, 2) + " where dt_pk_code is not null and RSDNBLDG_ID is null and ORG_ID is null)) dt_sj_code, " +
					" (select count(*) from cp_wh_rl_pg_st_" + PROVINCE_CODE.substring(0, 2) + " where dt_pk_code is null and RSDNBLDG_ID is null and ORG_ID is null) dt_sjno_code, " +
					" (select count(*) from cp_wh_postseg a,cp_wh_department b where a.dm_pk_code = b.dm_pk_code and substr(b.city_code,0,2) = '" + PROVINCE_CODE.substring(0, 2) + "') pg_code, " +
					" (select count(*) from (select distinct pg_pk_code from cp_wh_rl_pg_st_" + PROVINCE_CODE.substring(0, 2) + " where pg_pk_code is not null and RSDNBLDG_ID is null and ORG_ID is null)) pg_sj_code, " +
					" (select count(*) from cp_wh_rl_pg_st_" + PROVINCE_CODE.substring(0, 2) + " where pg_pk_code is null and RSDNBLDG_ID is null and ORG_ID is null) pg_sjno_code ";
			sqlCount="select count(*) ";
			sqlfrom=" from cp_base_org_district a ";
			sqlwhere=" where substr(a.district_code,0,2)='" + PROVINCE_CODE.substring(0, 2) + "' and a.degree = '1' ";
			sqlorder=" order by a.DISTRICT_CODE ";
			 
			if(!DispCommon.isMunicipalitiesID(PROVINCE_CODE.substring(0, 2))){
				sql="select a.DISTRICT_CODE,a.PROVINCE_NAME,a.city_name,a.SJ_CODE,a.DT_CODE,a.DT_SJ_CODE,a.DT_SJNO_CODE,a.PG_CODE,a.PG_SJ_CODE,a.PG_SJNO_CODE ";
				sqlCount="select count(*) ";
				sqlfrom=" from (" +
				" select a.district_code,a.province_name,a.city_name, " +
				" (select count(*) from cp_wh_rl_pg_st_" + PROVINCE_CODE.substring(0, 2) + " where substr(dist_cd,0,4) = substr(a.district_code,0,4) and RSDNBLDG_ID is null and ORG_ID is null ) sj_code, " +
				" (select count(*) from( " +
				" select distinct dist_cd,dt_pk_code from( " +
				" select distinct substr(disp_office_code,0,4)||'00' dist_cd,dt_pk_code from cp_wh_disp_office_dis where dt_pk_code is not null and substr(disp_office_code,0,2) = '" + PROVINCE_CODE.substring(0, 2) + "' " +
				" union " +
				" select distinct substr(trans_code,0,4)||'00' dist_cd,dt_pk_code from cp_wh_trans_office_dis where dt_pk_code is not null and substr(trans_code,0,2) = '" + PROVINCE_CODE.substring(0, 2) + "'))" +
				" where substr(dist_cd,0,4) = substr(a.district_code,0,4)) dt_code, " +
				" (select count(*) from (select distinct substr(dist_cd,0,4) dist_cd,dt_pk_code from cp_wh_rl_pg_st_" + PROVINCE_CODE.substring(0, 2) + " where dt_pk_code is not null and RSDNBLDG_ID is null and ORG_ID is null) where substr(dist_cd,0,4) = substr(a.district_code,0,4)) dt_sj_code, " +
				" (select count(*) from cp_wh_rl_pg_st_" + PROVINCE_CODE.substring(0, 2) + " where substr(dist_cd,0,4) = substr(a.district_code,0,4) and dt_pk_code is null and RSDNBLDG_ID is null and ORG_ID is null) dt_sjno_code, " +
				" (select count(*) from cp_wh_postseg a,cp_wh_department b where a.dm_pk_code = b.dm_pk_code and substr(b.city_code,0,4) = substr(a.district_code,0,4)) pg_code, " +
				" (select count(*) from (select distinct substr(dist_cd,0,4) dist_cd,pg_pk_code from cp_wh_rl_pg_st_" + PROVINCE_CODE.substring(0, 2) + " where pg_pk_code is not null and RSDNBLDG_ID is null and ORG_ID is null) where  substr(dist_cd,0,4) = substr(a.district_code,0,4)) pg_sj_code, " +
				" (select count(*) from cp_wh_rl_pg_st_" + PROVINCE_CODE.substring(0, 2) + " where  substr(dist_cd,0,4) = substr(a.district_code,0,4) and pg_pk_code is null and RSDNBLDG_ID is null and ORG_ID is null) pg_sjno_code " + 
				" from cp_base_org_district a where substr(a.district_code,0,2)='" + PROVINCE_CODE.substring(0, 2) + "' and a.degree = '2' " +
				" union all " +
				" select a.district_code,a.province_name,'省合计', " +
				" (select count(*) from cp_wh_rl_pg_st_" + PROVINCE_CODE.substring(0, 2) + ") sj_code, " +
				" ( " +
				" select count(*) from( " +
				" select distinct dt_pk_code from cp_wh_disp_office_dis where dt_pk_code is not null and  substr(disp_office_code,0,2) = '" + PROVINCE_CODE.substring(0, 2) + "' " +
				" union " +
				" select distinct dt_pk_code from cp_wh_trans_office_dis where dt_pk_code is not null and  substr(trans_code,0,2) = '" + PROVINCE_CODE.substring(0, 2) + "') " +
				" ) dt_code, " +
				" (select count(*) from (select distinct dt_pk_code from cp_wh_rl_pg_st_" + PROVINCE_CODE.substring(0, 2) + " where dt_pk_code is not null and RSDNBLDG_ID is null and ORG_ID is null)) dt_sj_code, " +
				" (select count(*) from cp_wh_rl_pg_st_" + PROVINCE_CODE.substring(0, 2) + " where dt_pk_code is null and RSDNBLDG_ID is null and ORG_ID is null) dt_sjno_code, " +
				" (select count(*) from cp_wh_postseg a,cp_wh_department b where a.dm_pk_code = b.dm_pk_code and substr(b.city_code,0,2) = '" + PROVINCE_CODE.substring(0, 2) + "') pg_code, " +
				" (select count(*) from (select distinct pg_pk_code from cp_wh_rl_pg_st_" + PROVINCE_CODE.substring(0, 2) + " where pg_pk_code is not null and RSDNBLDG_ID is null and ORG_ID is null)) pg_sj_code, " +
				" (select count(*) from cp_wh_rl_pg_st_" + PROVINCE_CODE.substring(0, 2) + " where pg_pk_code is null and RSDNBLDG_ID is null and ORG_ID is null) pg_sjno_code " +
				" from cp_base_org_district a " +
				" where substr(a.district_code,0,2)='" + PROVINCE_CODE.substring(0, 2) + "' and a.degree = '1' ) a";
				sqlwhere=" where 1=1 ";
				if(!IS_TOUDIQU.equals("null") && IS_TOUDIQU.length()>0){
					if(IS_TOUDIQU.equals("1")){
						sqlwhere = sqlwhere + " and dt_code = 1 ";
					}else if(IS_TOUDIQU.equals("2")){
						sqlwhere = sqlwhere + " and dt_code > 1 ";
					}else if(IS_TOUDIQU.equals("3")){
						sqlwhere = sqlwhere + " and dt_code > 2 ";
					}
				}
				sqlorder=" order by a.DISTRICT_CODE desc";
			}
		
		}

		sql=sql+sqlfrom+sqlwhere+sqlorder;
		sqlCount=sqlCount+sqlfrom+sqlwhere;
		
		page.setPageSize(500);
		if(pageNo!=0){
			page.setPageNo(pageNo);
		}
		page=dispQueryDao.getBeanQuerytjcqb(sql, sqlCount, page);
		return "json";
	}
	
	@SuppressWarnings("unchecked")
	public String querytjtddisp() throws Exception {
		
		if(userflag()==null){return "json";}
		
		String sql="";
		String sqlCount="";
		String sqlfrom="";
		String sqlwhere="";
		String sqlorder="";
		sql="select b.DT_NAME,b.DT_ALIAS_NAME||'['||decode(b.nj_flag,'1','集散','非集散')||']'  ";
		sqlCount="select count(*) ";
		sqlfrom=" from (select distinct dt_pk_code from cp_wh_disp_office_dis where dt_pk_code is not null and substr(disp_office_code,0,2) = '" + DISTRICT_CODE.substring(0, 2) + "' " +
			" union " +
			" select distinct dt_pk_code from cp_wh_trans_office_dis where dt_pk_code is not null and  substr(trans_code,0,2) = '" + DISTRICT_CODE.substring(0, 2) + "' ) a,CP_WH_DISTRICT b ";
		sqlwhere=" where a.DT_PK_CODE=b.DT_PK_CODE ";
		sqlorder=" order by b.nj_flag,b.DT_NAME,b.DT_ALIAS_NAME ";
		 
		if(!DispCommon.isMunicipalitiesID(DISTRICT_CODE.substring(0, 2))){
			sql="select b.DT_NAME,b.DT_ALIAS_NAME||'['||decode(b.nj_flag,'1','集散','非集散')||']'  ";
			sqlCount="select count(*) ";
			sqlfrom=" from (select distinct dist_cd,dt_pk_code from" +
				"(select distinct substr(disp_office_code,0,4)||'00' dist_cd,dt_pk_code from cp_wh_disp_office_dis where dt_pk_code is not null and substr(disp_office_code,0,2) = '" + DISTRICT_CODE.substring(0, 2) + "' " +
				" union " +
				" select distinct substr(trans_code,0,4)||'00' dist_cd,dt_pk_code from cp_wh_trans_office_dis where dt_pk_code is not null and  substr(trans_code,0,2) = '" + DISTRICT_CODE.substring(0, 2) + "' ) where dist_cd = '" + DISTRICT_CODE + "') a,CP_WH_DISTRICT b ";
			sqlwhere=" where a.DT_PK_CODE=b.DT_PK_CODE ";
			sqlorder=" order by  b.nj_flag,b.DT_NAME,b.DT_ALIAS_NAME ";
		}
		
		sql=sql+sqlfrom+sqlwhere+sqlorder;
		sqlCount=sqlCount+sqlfrom+sqlwhere;
		
		page.setPageSize(99999);
		if(pageNo!=0){
			page.setPageNo(pageNo);
		}
		page=dispQueryDao.getBeanQueryTddispp(sql, sqlCount, page);
		return "json";
	}
	
	@SuppressWarnings("unchecked")
	public String querytjdtqb() throws Exception {
		
		if(userflag()==null){return "json";}
		
		String sql="";
		String sqlCount="";
		String sqlfrom="";
		String sqlwhere="";
		String sqlorder="";
		sql=" select a.district_code,a.province_name,a.city_name, " +
				" (select count(*) from( " +
				" select distinct dt_pk_code from cp_wh_disp_office_dis where dt_pk_code is not null and  substr(disp_office_code,0,2) = '" + PROVINCE_CODE.substring(0, 2) + "' " +
				" union " +
				" select distinct dt_pk_code from cp_wh_trans_office_dis where dt_pk_code is not null and  substr(trans_code,0,2) = '" + PROVINCE_CODE.substring(0, 2) + "')) dt_code, " +
				" (select count(*) from (select distinct dt_pk_code from cp_wh_rl_pg_st_" + PROVINCE_CODE.substring(0, 2) + " where dt_pk_code is not null and RSDNBLDG_ID is null and ORG_ID is null)) dt_sj_code, " +
				" (select count(*) from (select distinct dm_pk_code from cp_wh_rl_pg_st_" + PROVINCE_CODE.substring(0, 2) + " where dt_pk_code is not null and dm_pk_code is not null and RSDNBLDG_ID is null and ORG_ID is null)) dm_sj_code, " +				
				" (select count(*) from (select distinct pg_pk_code from cp_wh_rl_pg_st_" + PROVINCE_CODE.substring(0, 2) + " where dt_pk_code is not null and dm_pk_code is not null and pg_pk_code is not null and RSDNBLDG_ID is null and ORG_ID is null)) pg_sj_code " ;
		sqlCount="select count(*) ";
		sqlfrom=" from cp_base_org_district a ";
		sqlwhere=" where substr(a.district_code,0,2)='" + PROVINCE_CODE.substring(0, 2) + "' and a.degree = '1' ";
		sqlorder=" order by a.DISTRICT_CODE ";
		 
		if(!DispCommon.isMunicipalitiesID(PROVINCE_CODE.substring(0, 2))){
			sql="select a.DISTRICT_CODE,a.PROVINCE_NAME,a.city_name,a.DT_CODE,a.DT_SJ_CODE,a.DM_SJ_CODE,a.PG_SJ_CODE ";
			sqlCount="select count(*) ";
			sqlfrom=" from (" +
			" select a.district_code,a.province_name,a.city_name, " +
			" (select count(*) from( " +
			" select distinct dist_cd,dt_pk_code from( " +
			" select distinct substr(disp_office_code,0,4)||'00' dist_cd,dt_pk_code from cp_wh_disp_office_dis where dt_pk_code is not null and  substr(disp_office_code,0,2) = '" + PROVINCE_CODE.substring(0, 2) + "' " +
			" union " +
			" select distinct substr(trans_code,0,4)||'00' dist_cd,dt_pk_code from cp_wh_trans_office_dis where dt_pk_code is not null and  substr(trans_code,0,2) = '" + PROVINCE_CODE.substring(0, 2) + "'))" +
			" where substr(dist_cd,0,4) = substr(a.district_code,0,4)) dt_code, " +
			" (select count(*) from (select distinct substr(dist_cd,0,4) dist_cd,dt_pk_code from cp_wh_rl_pg_st_" + PROVINCE_CODE.substring(0, 2) + " where dt_pk_code is not null and RSDNBLDG_ID is null and ORG_ID is null) where substr(dist_cd,0,4) = substr(a.district_code,0,4)) dt_sj_code, " +
			" (select count(*) from (select distinct substr(dist_cd,0,4) dist_cd,dm_pk_code from cp_wh_rl_pg_st_" + PROVINCE_CODE.substring(0, 2) + " where dt_pk_code is not null and dm_pk_code is not null and RSDNBLDG_ID is null and ORG_ID is null) where substr(dist_cd,0,4) = substr(a.district_code,0,4)) dm_sj_code, " +
			" (select count(*) from (select distinct substr(dist_cd,0,4) dist_cd,pg_pk_code from cp_wh_rl_pg_st_" + PROVINCE_CODE.substring(0, 2) + " where dt_pk_code is not null and dm_pk_code is not null and pg_pk_code is not null and RSDNBLDG_ID is null and ORG_ID is null) where  substr(dist_cd,0,4) = substr(a.district_code,0,4)) pg_sj_code " + 
			" from cp_base_org_district a where substr(a.district_code,0,2)='" + PROVINCE_CODE.substring(0, 2) + "' and a.degree = '2' " +
			" union all " +
			" select a.district_code,a.province_name,'省合计', " +
			" ( " +
			" select count(*) from( " +
			" select distinct dt_pk_code from cp_wh_disp_office_dis where dt_pk_code is not null and substr(disp_office_code,0,2) = '" + PROVINCE_CODE.substring(0, 2) + "' " +
			" union " +
			" select distinct dt_pk_code from cp_wh_trans_office_dis where dt_pk_code is not null and  substr(trans_code,0,2) = '" + PROVINCE_CODE.substring(0, 2) + "') " +
			" ) dt_code, " +
			" (select count(*) from (select distinct dt_pk_code from cp_wh_rl_pg_st_" + PROVINCE_CODE.substring(0, 2) + " where dt_pk_code is not null and RSDNBLDG_ID is null and ORG_ID is null)) dt_sj_code, " +
			" (select count(*) from (select distinct dm_pk_code from cp_wh_rl_pg_st_" + PROVINCE_CODE.substring(0, 2) + " where dt_pk_code is not null and dm_pk_code is not null and RSDNBLDG_ID is null and ORG_ID is null)) dm_sj_code, " +
			" (select count(*) from (select distinct pg_pk_code from cp_wh_rl_pg_st_" + PROVINCE_CODE.substring(0, 2) + " where dt_pk_code is not null and dm_pk_code is not null and pg_pk_code is not null and RSDNBLDG_ID is null and ORG_ID is null)) pg_sj_code " +
			" from cp_base_org_district a " +
			" where substr(a.district_code,0,2)='" + PROVINCE_CODE.substring(0, 2) + "' and a.degree = '1' ) a";
			sqlwhere=" where 1=1 ";
			sqlorder=" order by a.DISTRICT_CODE desc";
		}
		
			
		sql=sql+sqlfrom+sqlwhere+sqlorder;
		sqlCount=sqlCount+sqlfrom+sqlwhere;
		
		page.setPageSize(100);
		if(pageNo!=0){
			page.setPageNo(pageNo);
		}
		page=dispQueryDao.getBeanQuerytjdtqb(sql, sqlCount, page);
		return "json";
	}
	
	@SuppressWarnings("unchecked")
	public String querytjtdqwhtj() throws Exception {
		
		if(userflag()==null){return "json";}
		
		String sql="";
		String sqlCount="";
		String sqlfrom="";
		String sqlwhere="";
		String sqlorder="";
		
		sql=" select a.province_name," +
				" '' CITY_NAME," +
				" count(*) DT_CODE," +
				" sum(case when(b.nj_flag = 1) then 1 else 0 end) DT_SJ_CODE," +
				" sum(case when(b.nj_flag = 0) then 1 else 0 end) DT_SJNO_CODE," +
				" sum(case when(b.nj_flag = 1 and b.OFFICE_CODE is not null) then 1 else 0 end) DM_SJ_CODE," +
				" sum(case when(b.nj_flag = 1 and b.OFFICE_CODE is null) then 1 else 0 end) DM_SJNO_CODE ";
		sqlCount="select count(*) ";
		sqlfrom=" from cp_base_org_district a,cp_wh_district b ";
		sqlwhere=" where a.degree = '1' and substr(a.district_code,0,2) = substr(b.dt_province_code,0,2) ";
		sqlorder=" group by a.district_code,a.province_name order by a.district_code ";
		 
		if(!PROVINCE_CODE.equals("null") && PROVINCE_CODE.length()>0){
			sql=" select a.province_name," +
			" a.city_name," +
			" count(*) DT_CODE," +
			" sum(case when(b.nj_flag = 1) then 1 else 0 end) DT_SJ_CODE," +
			" sum(case when(b.nj_flag = 0) then 1 else 0 end) DT_SJNO_CODE," +
			" sum(case when(b.nj_flag = 1 and b.OFFICE_CODE is not null) then 1 else 0 end) DM_SJ_CODE," +
			" sum(case when(b.nj_flag = 1 and b.OFFICE_CODE is null) then 1 else 0 end) DM_SJNO_CODE ";
			sqlCount="select count(*) ";
			sqlfrom=" from cp_base_org_district a,cp_wh_district b,cp_wh_disp_office_dis c ";
			sqlwhere=" where a.district_code = c.disp_office_code and b.dt_pk_code = c.dt_pk_code ";
			sqlorder=" group by a.district_code,a.province_name,a.city_name order by a.district_code ";
			sqlwhere = sqlwhere + " and substr(a.district_code,0,2)  = '" + PROVINCE_CODE.substring(0,2) + "' ";
		}
			
		sql=sql+sqlfrom+sqlwhere+sqlorder;
		sqlCount=sqlCount+sqlfrom+sqlwhere;
		
		page.setPageSize(99999);
		
		if(pageNo!=0){
			page.setPageNo(pageNo);
		}
		page=dispQueryDao.getBeanQuerytjtdqwhtj(sql, sqlCount, page);
		return "json";
	}
	
	@SuppressWarnings("unchecked")
	public String querytjtdbwhtj() throws Exception {
		
		if(userflag()==null){return "json";}
		
		String sql="";
		String sqlCount="";
		String sqlfrom="";
		String sqlwhere="";
		String sqlorder="";
		sql=" select a.province_name," +
				" '' CITY_NAME," +
				" count(*) DM_CODE," +
				" sum(case when(b.OFFICE_CODE is not null) then 1 else 0 end) DM_SJ_CODE," +
				" sum(case when(b.OFFICE_CODE is null) then 1 else 0 end) DM_SJNO_CODE ";
		sqlCount="select count(*) ";
		sqlfrom=" from cp_base_org_district a,CP_WH_DEPARTMENT b ";
		sqlwhere=" where a.degree = '1' and substr(a.district_code,0,2) = substr(b.CITY_CODE,0,2) ";
		sqlorder=" group by a.district_code,a.province_name order by a.district_code ";
		 
		if(!PROVINCE_CODE.equals("null") && PROVINCE_CODE.length()>0){
			sql=" select a.province_name," +
			" a.city_name," +
			" count(*) DM_CODE," +
			" sum(case when(b.OFFICE_CODE is not null) then 1 else 0 end) DM_SJ_CODE," +
			" sum(case when(b.OFFICE_CODE is null) then 1 else 0 end) DM_SJNO_CODE ";
			sqlCount="select count(*) ";
			sqlfrom=" from cp_base_org_district a,CP_WH_DEPARTMENT b ";
			sqlwhere=" where a.district_code = b.CITY_CODE ";
			sqlorder=" group by a.district_code,a.province_name,a.city_name order by a.district_code ";
			sqlwhere = sqlwhere + " and substr(a.district_code,0,2)  = '" + PROVINCE_CODE.substring(0,2) + "' ";
		}
			
		sql=sql+sqlfrom+sqlwhere+sqlorder;
		sqlCount=sqlCount+sqlfrom+sqlwhere;
		
		page.setPageSize(99999);
		
		if(pageNo!=0){
			page.setPageNo(pageNo);
		}
		page=dispQueryDao.getBeanQuerytjtdbwhtj(sql, sqlCount, page);
		return "json";
	}
	
	
	@SuppressWarnings("unchecked")
	public String querytjdtpkisp() throws Exception {
		
		if(userflag()==null){return "json";}
		
		String sql="";
		String sqlCount="";
		String sqlfrom="";
		String sqlwhere="";
		String sqlorder="";
		sql="select d.dt_name,d.dt_alias_name,c.dm_name,b.pg_name  ";
		sqlCount="select count(*) ";
		sqlfrom=" from  " +
		" ( select distinct pg_pk_code from cp_wh_rl_pg_st_" + DISTRICT_CODE.substring(0, 2)  + " where dt_pk_code is not null and dm_pk_code is not null and pg_pk_code is not null and RSDNBLDG_ID is null and ORG_ID is null ) a,  " + 
		" cp_wh_postseg b,  " + 
		" cp_wh_department c,  " + 
		" cp_wh_district d " ;
		sqlwhere=" where a.pg_pk_code = b.pg_pk_code and b.dm_pk_code = c.dm_pk_code and c.dt_pk_code = d.dt_pk_code ";
		sqlorder=" order by d.dt_name,d.dt_alias_name,c.dm_name,b.pg_name ";
		 
		if(!DispCommon.isMunicipalitiesID(DISTRICT_CODE.substring(0, 2))){
			sql="select d.dt_name,d.dt_alias_name,c.dm_name,b.pg_name  ";
			sqlCount="select count(*) ";
			sqlfrom=" from  " +
			" ( select distinct pg_pk_code from cp_wh_rl_pg_st_" + DISTRICT_CODE.substring(0, 2) + "  where substr(dist_cd,0,4)||'00' =  '" + DISTRICT_CODE + "' and dt_pk_code is not null and dm_pk_code is not null and pg_pk_code is not null and RSDNBLDG_ID is null and ORG_ID is null) a,  " + 
			" cp_wh_postseg b,  " + 
			" cp_wh_department c,  " + 
			" cp_wh_district d " ;
			sqlwhere=" where a.pg_pk_code = b.pg_pk_code and b.dm_pk_code = c.dm_pk_code and c.dt_pk_code = d.dt_pk_code ";
			sqlorder=" order by d.dt_name,d.dt_alias_name,c.dm_name,b.pg_name ";
		}
		
		sql=sql+sqlfrom+sqlwhere+sqlorder;
		sqlCount=sqlCount+sqlfrom+sqlwhere;
		
		page.setPageSize(99999);
		if(pageNo!=0){
			page.setPageNo(pageNo);
		}
		page=dispQueryDao.getBeanQuerytjdtpkisp(sql, sqlCount, page);
		return "json";
	}
	
	
	@SuppressWarnings("unchecked")
	public String querytjtdqqb() throws Exception {
		
		if(userflag()==null){return "json";}
		
		String sql="";
		String sqlCount="";
		String sqlfrom="";
		String sqlwhere="";
		String sqlorder="";
		sql="select a.district_code,a.city_name,a.dt_pk_code,a.dt_name,a.dt_alias_name,to_char(substr(b.county_name_sn,1,4000)),to_char(substr(c.county_name_fn,1,4000)),to_char(substr(d.county_name_snyj,1,4000)),to_char(substr(e.county_name_fnyj,1,4000))  ";
		sqlCount="select count(*) ";
		sqlfrom=" from  " +
		" (select  d.district_code,d.city_name,c.dt_pk_code,c.dt_name,c.dt_alias_name from ( select  max(substr(a.dist_cd,0,2)||'0000') dist_cd,a.dt_pk_code from cp_wh_rl_pg_st_" + PROVINCE_CODE.substring(0, 2) + "   a group by a.dt_pk_code) b,cp_wh_district c,cp_base_org_district d where b.dt_pk_code = c.dt_pk_code and b.dist_cd = d.district_code) a, " +
		" (select b.dt_pk_code,wmsys.wm_concat(b.county_name) county_name_sn from ( select b.dt_pk_code,decode(c.county_name,null,c.city_name,c.county_name) county_name from ( select  a.dist_cd,a.dt_pk_code from cp_wh_rl_pg_st_" + PROVINCE_CODE.substring(0, 2) + "   a group by a.dist_cd,a.dt_pk_code) b,cp_base_org_district c where b.dist_cd = c.district_code and c.city_flag = 1) b group by b.dt_pk_code) b, " +
		" (select b.dt_pk_code,wmsys.wm_concat(b.county_name) county_name_fn from ( select b.dt_pk_code,decode(c.county_name,null,c.city_name,c.county_name) county_name from ( select  a.dist_cd,a.dt_pk_code from cp_wh_rl_pg_st_" + PROVINCE_CODE.substring(0, 2) + "   a group by a.dist_cd,a.dt_pk_code) b,cp_base_org_district c where b.dist_cd = c.district_code and c.city_flag = 0) b group by b.dt_pk_code) c, " +
		" (select b.dt_pk_code,wmsys.wm_concat(b.county_name) county_name_snyj from ( select b.dt_pk_code,decode(c.county_name,null,c.city_name,c.county_name) county_name from ( select b.dist_cd,b.dt_pk_code from ( select  a.dist_cd,a.dt_pk_code from cp_wh_rl_pg_st_" + PROVINCE_CODE.substring(0, 2) + "   a group by a.dist_cd,a.dt_pk_code) b,( select  b.dist_cd from(select  a.dist_cd,a.dt_pk_code from cp_wh_rl_pg_st_" + PROVINCE_CODE.substring(0, 2) + "   a group by a.dist_cd,a.dt_pk_code) b group by b.dist_cd having count(*) > 1) c where c.dist_cd = b.dist_cd) b,cp_base_org_district c where b.dist_cd = c.district_code and c.city_flag = 1) b group by b.dt_pk_code) d, " +
		" (select b.dt_pk_code,wmsys.wm_concat(b.county_name) county_name_fnyj from ( select b.dt_pk_code,decode(c.county_name,null,c.city_name,c.county_name) county_name from ( select b.dist_cd,b.dt_pk_code from ( select  a.dist_cd,a.dt_pk_code from cp_wh_rl_pg_st_" + PROVINCE_CODE.substring(0, 2) + "   a group by a.dist_cd,a.dt_pk_code) b,( select  b.dist_cd from(select  a.dist_cd,a.dt_pk_code from cp_wh_rl_pg_st_" + PROVINCE_CODE.substring(0, 2) + "   a group by a.dist_cd,a.dt_pk_code) b group by b.dist_cd having count(*) > 1) c where c.dist_cd = b.dist_cd) b,cp_base_org_district c where b.dist_cd = c.district_code and c.city_flag = 0) b group by b.dt_pk_code) e ";
		sqlwhere=" where a.dt_pk_code = b.dt_pk_code(+) and a.dt_pk_code = c.dt_pk_code(+) and a.dt_pk_code = d.dt_pk_code(+) and a.dt_pk_code = e.dt_pk_code(+) ";
		sqlorder=" order by a.dt_pk_code ";
		 
		if(!DispCommon.isMunicipalitiesID(PROVINCE_CODE.substring(0, 2))){
			sql="select a.district_code,a.city_name,a.dt_pk_code,a.dt_name,a.dt_alias_name,to_char(substr(b.county_name_sn,1,4000)),to_char(substr(c.county_name_fn,1,4000)),to_char(substr(d.county_name_snyj,1,4000)),to_char(substr(e.county_name_fnyj,1,4000))  ";
			sqlCount="select count(*) ";
			sqlfrom=" from  " +
			" (select d.district_code||c.dt_pk_code dist_dtpk,d.district_code,d.city_name,c.dt_pk_code,c.dt_name,c.dt_alias_name from ( select  substr(a.dist_cd,0,4)||'00' dist_cd,a.dt_pk_code from cp_wh_rl_pg_st_" + PROVINCE_CODE.substring(0, 2) + "  a group by substr(a.dist_cd,0,4)||'00',a.dt_pk_code) b,cp_wh_district c,cp_base_org_district d where b.dt_pk_code = c.dt_pk_code and b.dist_cd = d.district_code) a, " +
			" (select b.dist_cd||b.dt_pk_code dist_dtpk,b.dist_cd,b.dt_pk_code,wmsys.wm_concat(b.county_name) county_name_sn from(select substr(c.district_code,0,4)||'00' dist_cd,b.dt_pk_code,decode(c.county_name,null,c.city_name,c.county_name) county_name from(select  a.dist_cd,a.dt_pk_code from cp_wh_rl_pg_st_" + PROVINCE_CODE.substring(0, 2) + "  a group by a.dist_cd,a.dt_pk_code) b,cp_base_org_district c where b.dist_cd = c.district_code and c.city_flag = 1) b group by b.dist_cd,b.dt_pk_code) b, " +
			" (select b.dist_cd||b.dt_pk_code dist_dtpk,b.dist_cd,b.dt_pk_code,wmsys.wm_concat(b.county_name) county_name_fn from(select substr(c.district_code,0,4)||'00' dist_cd,b.dt_pk_code,decode(c.county_name,null,c.city_name,c.county_name) county_name from(select  a.dist_cd,a.dt_pk_code from cp_wh_rl_pg_st_" + PROVINCE_CODE.substring(0, 2) + "  a group by a.dist_cd,a.dt_pk_code) b,cp_base_org_district c where b.dist_cd = c.district_code and c.city_flag = 0) b group by b.dist_cd,b.dt_pk_code) c, " +
			" (select b.dist_cd||b.dt_pk_code dist_dtpk,b.dist_cd,b.dt_pk_code,wmsys.wm_concat(b.county_name) county_name_snyj from(select substr(c.district_code,0,4)||'00' dist_cd,b.dt_pk_code,decode(c.county_name,null,c.city_name,c.county_name) county_name from(select b.dist_cd,b.dt_pk_code from(select  a.dist_cd,a.dt_pk_code from cp_wh_rl_pg_st_" + PROVINCE_CODE.substring(0, 2) + "  a group by a.dist_cd,a.dt_pk_code) b,(select  b.dist_cd from( select  a.dist_cd,a.dt_pk_code from cp_wh_rl_pg_st_" + PROVINCE_CODE.substring(0, 2) + "  a group by a.dist_cd,a.dt_pk_code) b group by b.dist_cd having count(*) > 1) c where c.dist_cd = b.dist_cd) b,cp_base_org_district c where b.dist_cd = c.district_code and c.city_flag = 1) b group by b.dist_cd,b.dt_pk_code) d, " +
			" (select b.dist_cd||b.dt_pk_code dist_dtpk,b.dist_cd,b.dt_pk_code,wmsys.wm_concat(b.county_name) county_name_fnyj from(select substr(c.district_code,0,4)||'00' dist_cd,b.dt_pk_code,decode(c.county_name,null,c.city_name,c.county_name) county_name from(select b.dist_cd,b.dt_pk_code from(select  a.dist_cd,a.dt_pk_code from cp_wh_rl_pg_st_" + PROVINCE_CODE.substring(0, 2) + "  a group by a.dist_cd,a.dt_pk_code) b,(select  b.dist_cd from( select  a.dist_cd,a.dt_pk_code from cp_wh_rl_pg_st_" + PROVINCE_CODE.substring(0, 2) + "  a group by a.dist_cd,a.dt_pk_code) b group by b.dist_cd having count(*) > 1) c where c.dist_cd = b.dist_cd) b,cp_base_org_district c where b.dist_cd = c.district_code and c.city_flag = 0) b group by b.dist_cd,b.dt_pk_code) e ";
			sqlwhere=" where a.dist_dtpk = b.dist_dtpk(+) and a.dist_dtpk = c.dist_dtpk(+) and a.dist_dtpk = d.dist_dtpk(+) and a.dist_dtpk = e.dist_dtpk(+) ";
			sqlorder=" order by a.district_code,a.dt_pk_code ";
		}
		
		sql=sql+sqlfrom+sqlwhere+sqlorder;
		sqlCount=sqlCount+sqlfrom+sqlwhere;
		
		page.setPageSize(99999);
		if(pageNo!=0){
			page.setPageNo(pageNo);
		}
		page=dispQueryDao.getBeanQuerytjtdqqb(sql, sqlCount, page);
		return "json";
	}
	
	public String queryexportall() throws Exception {
		
		if(userflag()==null){return "json";}
		
		Exportall el = new Exportall();
				
		EXPORTALLPATH = el.exportall(LINENAME,LINELIST);
		
		return "json";
	}
	
	@SuppressWarnings("unchecked")
	public String querytjprb() throws Exception {
		
		if(userflag()==null){return "json";}
		
		String sql="select a.DIST_CD,decode(a.PROVINCE_NAME,null,a.DIST_CD,a.PROVINCE_NAME),decode(a.CITY_NAME,null,a.DIST_CD,a.CITY_NAME),a.ITEM_DATE,a.TOTAL_COUNT,a.DISTRI_COUNT,a.ADDR_COUNT,a.TDQ_COUNT,a.ADDRTDQ_COUNT,a.NOADDRTDQ_COUNT,decode(a.NUM,null,0,a.num),a.SRDIPQ_COUNT ";
		String sqlCount="select count(*) ";
		String sqlfrom=" from (";
		
		
		if(IS_TOUDIQU.equals("1")){
			sqlfrom = sqlfrom + " select substr(a.DIST_CD,0,2) DIST_CD, ";
		}else if(IS_TOUDIQU.equals("2")){
			sqlfrom = sqlfrom + " select substr(a.DIST_CD,0,4) DIST_CD, ";
		}else if(IS_TOUDIQU.equals("3")){
			sqlfrom = sqlfrom + " select substr(a.DIST_CD,0,4) DIST_CD, ";
		}else if(IS_TOUDIQU.equals("4")){
			sqlfrom = sqlfrom + " select substr(a.DIST_CD,0,4) DIST_CD, ";
		}
		
		sqlfrom = sqlfrom + " max(a.PROVINCE_NAME) PROVINCE_NAME,max(a.CITY_NAME) CITY_NAME,'' ITEM_DATE,sum(a.TOTAL_COUNT) TOTAL_COUNT,sum(a.DISTRI_COUNT) DISTRI_COUNT,sum(a.ADDR_COUNT) ADDR_COUNT,sum(a.TDQ_COUNT) TDQ_COUNT,sum(a.ADDRTDQ_COUNT) ADDRTDQ_COUNT,sum(a.NOADDRTDQ_COUNT) NOADDRTDQ_COUNT , max(b.num) num , sum(a.SRDIPQ_COUNT) SRDIPQ_COUNT  from STAT_ECI_OPE a  ";
		
		if(IS_TOUDIQU.equals("1")){
			sqlfrom = sqlfrom + "  ,(select dt_province_code dist_cd,count(*) num from cp_wh_district where nj_flag = 1 group by dt_province_code) b where substr(a.DIST_CD,0,2)=b.dist_cd(+) ";
		}else if(IS_TOUDIQU.equals("2")){
			sqlfrom = sqlfrom + "  ,sh_duotoudiqu_shi b where a.dist_cd = b.dist_cd ";
		}else if(IS_TOUDIQU.equals("3")){
			sqlfrom = sqlfrom + "  ,sh_duotoudiqu_pzf b  where a.dist_cd = b.dist_cd ";
		}else if(IS_TOUDIQU.equals("4")){
			sqlfrom = sqlfrom + "  ,sh_duotoudiqu_sy b  where a.dist_cd = b.dist_cd ";
		}

		if(!ITEM_DATESS.equals("null") && ITEM_DATESS.length()>0){
			sqlfrom = sqlfrom + " and a.item_date  >= '" + ITEM_DATESS + "' ";
		}
		
		if(!ITEM_DATESE.equals("null") && ITEM_DATESE.length()>0){
			sqlfrom = sqlfrom + " and a.item_date  <= '" + ITEM_DATESE + "' ";
		}
		
		if(!IS_CUSPEO.equals("null") && IS_CUSPEO.length()>0){
			if(IS_CUSPEO.equals("1")){
				sqlfrom = sqlfrom + " and a.source  = '1' ";
			}else if(IS_CUSPEO.equals("2")){
				sqlfrom = sqlfrom + " and a.source  = '2' ";
			}
		}
		
		if(!IS_DISTRI.equals("null") && IS_DISTRI.length()>0){
			if(IS_DISTRI.equals("1")){
				sqlfrom = sqlfrom + " and IS_DISTRI  = '1' ";
			}else if(IS_DISTRI.equals("2")){
				sqlfrom = sqlfrom + " and IS_DISTRI  = '0' ";
			}
		}
				
		if(IS_TOUDIQU.equals("1")){
			sqlfrom = sqlfrom + " group by substr(a.DIST_CD,0,2) ";
		}else if(IS_TOUDIQU.equals("2")){
			sqlfrom = sqlfrom + " group by substr(a.DIST_CD,0,4) ";
		}else if(IS_TOUDIQU.equals("3")){
			sqlfrom = sqlfrom + " group by substr(a.DIST_CD,0,4) ";
		}else if(IS_TOUDIQU.equals("4")){
			sqlfrom = sqlfrom + " group by substr(a.DIST_CD,0,4) ";
		}
		
		sqlfrom = sqlfrom + "  union all";
		
		sqlfrom = sqlfrom + " select  '' DIST_CD,'全国' PROVINCE_NAME,'合计' CITY_NAME,'' ITEM_DATE,sum(a.TOTAL_COUNT) TOTAL_COUNT,sum(a.DISTRI_COUNT) DISTRI_COUNT,sum(a.ADDR_COUNT) ADDR_COUNT,sum(a.TDQ_COUNT) TDQ_COUNT,sum(a.ADDRTDQ_COUNT) ADDRTDQ_COUNT,sum(a.NOADDRTDQ_COUNT) NOADDRTDQ_COUNT,0 , sum(a.SRDIPQ_COUNT) SRDIPQ_COUNT from STAT_ECI_OPE a  ";
		

		if(IS_TOUDIQU.equals("1")){
			sqlfrom = sqlfrom + "   where 1=1";
		}else if(IS_TOUDIQU.equals("2")){
			sqlfrom = sqlfrom + " ,sh_duotoudiqu_shi b  where a.dist_cd = b.dist_cd ";
		}else if(IS_TOUDIQU.equals("3")){
			sqlfrom = sqlfrom + " ,sh_duotoudiqu_pzf b  where a.dist_cd = b.dist_cd ";
		}else if(IS_TOUDIQU.equals("4")){
			sqlfrom = sqlfrom + " ,sh_duotoudiqu_sy b  where a.dist_cd = b.dist_cd ";
		}
		
		if(!ITEM_DATESS.equals("null") && ITEM_DATESS.length()>0){
			sqlfrom = sqlfrom + " and a.item_date  >= '" + ITEM_DATESS + "' ";
		}
		
		if(!ITEM_DATESE.equals("null") && ITEM_DATESE.length()>0){
			sqlfrom = sqlfrom + " and a.item_date  <= '" + ITEM_DATESE + "' ";
		}
		
		if(!IS_CUSPEO.equals("null") && IS_CUSPEO.length()>0){
			if(IS_CUSPEO.equals("1")){
				sqlfrom = sqlfrom + " and a.source  = '1' ";
			}else if(IS_CUSPEO.equals("2")){
				sqlfrom = sqlfrom + " and a.source  = '2' ";
			}
		}
		
		if(!IS_DISTRI.equals("null") && IS_DISTRI.length()>0){
			if(IS_DISTRI.equals("1")){
				sqlfrom = sqlfrom + " and IS_DISTRI  = '1' ";
			}else if(IS_DISTRI.equals("2")){
				sqlfrom = sqlfrom + " and IS_DISTRI  = '0' ";
			}
		}
		
		sqlfrom = sqlfrom + " ) a ";
		String sqlwhere=" where 1=1 ";
		String sqlorder=" order by a.DIST_CD,a.PROVINCE_NAME ";
			

		sql=sql+sqlfrom+sqlwhere+sqlorder;
		sqlCount=sqlCount+sqlfrom+sqlwhere;
		
		page.setPageSize(1000);
		if(pageNo!=0){
			page.setPageNo(pageNo);
		}
		page=dispQueryDao.getBeanQuerytjprb(sql, sqlCount, page);
		return "json";
	}
	
	@SuppressWarnings("unchecked")
	public String querytjcrb() throws Exception {
		
		if(userflag()==null){return "json";}
		
		String sql="select a.DIST_CD,decode(a.PROVINCE_NAME,null,a.DIST_CD,a.PROVINCE_NAME),decode(a.CITY_NAME,null,a.DIST_CD,a.CITY_NAME),a.ITEM_DATE,a.TOTAL_COUNT,a.DISTRI_COUNT,a.ADDR_COUNT,a.TDQ_COUNT,a.ADDRTDQ_COUNT,a.NOADDRTDQ_COUNT,decode(a.NUM,null,0,a.num),a.SRDIPQ_COUNT ";
		String sqlCount="select count(*) ";
		String sqlfrom=" from (";
		
		//sqlfrom = sqlfrom + " select max(a.DIST_CD) DIST_CD,max(a.PROVINCE_NAME) PROVINCE_NAME,max(a.CITY_NAME) CITY_NAME,'' ITEM_DATE,sum(a.TOTAL_COUNT) TOTAL_COUNT,sum(a.DISTRI_COUNT) DISTRI_COUNT,sum(a.ADDR_COUNT) ADDR_COUNT,sum(a.TDQ_COUNT) TDQ_COUNT,sum(a.ADDRTDQ_COUNT) ADDRTDQ_COUNT,sum(a.NOADDRTDQ_COUNT) NOADDRTDQ_COUNT, max(b.num) num from STAT_ECI_OPE a ,sh_duotoudiqu_shi b where 1=1 and a.DIST_CD = b.DIST_CD(+) ";
		
		//sqlfrom = sqlfrom + " select max(a.DIST_CD) DIST_CD,max(a.PROVINCE_NAME) PROVINCE_NAME,max(decode(a.CITY_NAME,null,a.PROVINCE_NAME,a.CITY_NAME)) CITY_NAME,'' ITEM_DATE,sum(a.TOTAL_COUNT) TOTAL_COUNT,sum(a.DISTRI_COUNT) DISTRI_COUNT,sum(a.ADDR_COUNT) ADDR_COUNT,sum(a.TDQ_COUNT) TDQ_COUNT,sum(a.ADDRTDQ_COUNT) ADDRTDQ_COUNT,sum(a.NOADDRTDQ_COUNT) NOADDRTDQ_COUNT, max(b.num) num from STAT_ECI_OPE a ,CP_BASE_ORG_DISTRICT c where 1=1 and a.DIST_CD = c.DISTRICT_CODE ";
		
		sqlfrom = sqlfrom + " select max(a.DIST_CD) DIST_CD,max(a.PROVINCE_NAME) PROVINCE_NAME,max(decode(a.CITY_NAME,null,a.PROVINCE_NAME,a.CITY_NAME)) CITY_NAME,'' ITEM_DATE,sum(a.TOTAL_COUNT) TOTAL_COUNT,sum(a.DISTRI_COUNT) DISTRI_COUNT,sum(a.ADDR_COUNT) ADDR_COUNT,sum(a.TDQ_COUNT) TDQ_COUNT,sum(a.ADDRTDQ_COUNT) ADDRTDQ_COUNT,sum(a.NOADDRTDQ_COUNT) NOADDRTDQ_COUNT, max(b.num) num , sum(a.SRDIPQ_COUNT) SRDIPQ_COUNT from STAT_ECI_OPE a ,sh_duotoudiqu_shi b where 1=1 and a.DIST_CD = b.DIST_CD(+) ";

		if(!PROVINCE_CODE.equals("null") && PROVINCE_CODE.length()>0){
			sqlfrom = sqlfrom + " and substr(a.DIST_CD,0,2)  = '" + PROVINCE_CODE.substring(0,2) + "' ";
		}
		
		if(!ITEM_DATESS.equals("null") && ITEM_DATESS.length()>0){
			sqlfrom = sqlfrom + " and a.item_date  >= '" + ITEM_DATESS + "' ";
		}
		
		if(!ITEM_DATESE.equals("null") && ITEM_DATESE.length()>0){
			sqlfrom = sqlfrom + " and a.item_date  <= '" + ITEM_DATESE + "' ";
		}
		
		if(!IS_CUSPEO.equals("null") && IS_CUSPEO.length()>0){
			if(IS_CUSPEO.equals("1")){
				sqlfrom = sqlfrom + " and a.source  = '1' ";
			}else if(IS_CUSPEO.equals("2")){
				sqlfrom = sqlfrom + " and a.source  = '2' ";
			}
		}
		
		if(!IS_DISTRI.equals("null") && IS_DISTRI.length()>0){
			if(IS_DISTRI.equals("1")){
				sqlfrom = sqlfrom + " and IS_DISTRI  = '1' ";
			}else if(IS_DISTRI.equals("2")){
				sqlfrom = sqlfrom + " and IS_DISTRI  = '0' ";
			}
		}
		
		sqlfrom = sqlfrom + " group by a.DIST_CD";
		
		if(!DispCommon.isMunicipalitiesID(PROVINCE_CODE.substring(0, 2))){
			
		
		sqlfrom = sqlfrom + "  union all";
		
		sqlfrom = sqlfrom + " select  '' DIST_CD,'省合计' PROVINCE_NAME,'省合计' CITY_NAME,'' ITEM_DATE,sum(a.TOTAL_COUNT) TOTAL_COUNT,sum(a.DISTRI_COUNT) DISTRI_COUNT,sum(a.ADDR_COUNT) ADDR_COUNT,sum(a.TDQ_COUNT) TDQ_COUNT,sum(a.ADDRTDQ_COUNT) ADDRTDQ_COUNT,sum(a.NOADDRTDQ_COUNT) NOADDRTDQ_COUNT,max((select count(*) from cp_wh_district where dt_province_code = '" + PROVINCE_CODE.substring(0,2) + "')) num , sum(a.SRDIPQ_COUNT) SRDIPQ_COUNT from STAT_ECI_OPE a  where 1=1 ";
		
		if(!PROVINCE_CODE.equals("null") && PROVINCE_CODE.length()>0){
			sqlfrom = sqlfrom + " and substr(a.DIST_CD,0,2)  = '" + PROVINCE_CODE.substring(0,2) + "' ";
		}
		
		if(!ITEM_DATESS.equals("null") && ITEM_DATESS.length()>0){
			sqlfrom = sqlfrom + " and a.item_date  >= '" + ITEM_DATESS + "' ";
		}
		
		if(!ITEM_DATESE.equals("null") && ITEM_DATESE.length()>0){
			sqlfrom = sqlfrom + " and a.item_date  <= '" + ITEM_DATESE + "' ";
		}
		
		if(!IS_CUSPEO.equals("null") && IS_CUSPEO.length()>0){
			if(IS_CUSPEO.equals("1")){
				sqlfrom = sqlfrom + " and a.source  = '1' ";
			}else if(IS_CUSPEO.equals("2")){
				sqlfrom = sqlfrom + " and a.source  = '2' ";
			}
		}
		
		if(!IS_DISTRI.equals("null") && IS_DISTRI.length()>0){
			if(IS_DISTRI.equals("1")){
				sqlfrom = sqlfrom + " and IS_DISTRI  = '1' ";
			}else if(IS_DISTRI.equals("2")){
				sqlfrom = sqlfrom + " and IS_DISTRI  = '0' ";
			}
		}
		
		}
		
		sqlfrom = sqlfrom + " ) a ";
		String sqlwhere=" where 1=1 ";
		String sqlorder=" order by a.DIST_CD,a.CITY_NAME ";
			

		sql=sql+sqlfrom+sqlwhere+sqlorder;
		sqlCount=sqlCount+sqlfrom+sqlwhere;
		
		page.setPageSize(100);
		if(pageNo!=0){
			page.setPageNo(pageNo);
		}
		page=dispQueryDao.getBeanQuerytjcrb(sql, sqlCount, page);
		return "json";
	}
	
	@SuppressWarnings("unchecked")
	public String querytjpwhrb() throws Exception {
		
		if(userflag()==null){return "json";}
		
		String sql="select a.DIST_CD,decode(a.PROVINCE_NAME,null,a.DIST_CD,a.PROVINCE_NAME),decode(a.CITY_NAME,null,a.DIST_CD,a.CITY_NAME),a.ITEM_DATE,a.SR_COUNT,a.ADDR_SR_COUNT,a.DRWH_COUNT,a.CRWH_COUNT,a.ZRWH_COUNT,decode(a.NUM,null,0,a.num),a.QBWH_COUNT ";
		String sqlCount="select count(*) ";
		String sqlfrom=" from (";
		
		
		if(IS_TOUDIQU.equals("1")){
			sqlfrom = sqlfrom + " select substr(a.DIST_CD,0,2) DIST_CD, ";
		}else if(IS_TOUDIQU.equals("2")){
			sqlfrom = sqlfrom + " select substr(a.DIST_CD,0,4) DIST_CD, ";
		}else if(IS_TOUDIQU.equals("3")){
			sqlfrom = sqlfrom + " select substr(a.DIST_CD,0,4) DIST_CD, ";
		}else if(IS_TOUDIQU.equals("4")){
			sqlfrom = sqlfrom + " select substr(a.DIST_CD,0,4) DIST_CD, ";
		}
		
		sqlfrom = sqlfrom + " max(a.PROVINCE_NAME) PROVINCE_NAME,max(a.CITY_NAME) CITY_NAME,'' ITEM_DATE,sum(a.SR_COUNT) SR_COUNT,sum(a.ADDR_SR_COUNT) ADDR_SR_COUNT,sum(a.DRWH_COUNT) DRWH_COUNT,sum(a.CRWH_COUNT) CRWH_COUNT,sum(a.ZRWH_COUNT) ZRWH_COUNT, max(b.num) num,sum(a.QBWH_COUNT) QBWH_COUNT  from STAT_ECI_OPE_wh a  ";
		
		if(IS_TOUDIQU.equals("1")){
			sqlfrom = sqlfrom + "  ,(select dt_province_code dist_cd,count(*) num from cp_wh_district group by dt_province_code) b where substr(a.DIST_CD,0,2)=b.dist_cd(+) ";
		}else if(IS_TOUDIQU.equals("2")){
			sqlfrom = sqlfrom + "  ,sh_duotoudiqu_shi b where a.dist_cd = b.dist_cd ";
		}else if(IS_TOUDIQU.equals("3")){
			sqlfrom = sqlfrom + "  ,sh_duotoudiqu_pzf b  where a.dist_cd = b.dist_cd ";
		}else if(IS_TOUDIQU.equals("4")){
			sqlfrom = sqlfrom + "  ,sh_duotoudiqu_sy b  where a.dist_cd = b.dist_cd ";
		}
		
		if(!ITEM_DATESS.equals("null") && ITEM_DATESS.length()>0){
			sqlfrom = sqlfrom + " and a.item_date  >= '" + ITEM_DATESS + "' ";
		}
		
		if(!ITEM_DATESE.equals("null") && ITEM_DATESE.length()>0){
			sqlfrom = sqlfrom + " and a.item_date  <= '" + ITEM_DATESE + "' ";
		}
		
		if(!IS_CUSPEO.equals("null") && IS_CUSPEO.length()>0){
			if(IS_CUSPEO.equals("1")){
				sqlfrom = sqlfrom + " and a.source  = '1' ";
			}else if(IS_CUSPEO.equals("2")){
				sqlfrom = sqlfrom + " and a.source  = '2' ";
			}
		}
		
		if(!IS_DISTRI.equals("null") && IS_DISTRI.length()>0){
			if(IS_DISTRI.equals("1")){
				sqlfrom = sqlfrom + " and IS_DISTRI  = '1' ";
			}else if(IS_DISTRI.equals("2")){
				sqlfrom = sqlfrom + " and IS_DISTRI  = '0' ";
			}
		}
		
		if(getSessionUser().getUsCityOffice() != null && getSessionUser().getUsCityOffice().length() > 0){
			sqlfrom = sqlfrom + " and substr(a.DIST_CD,0,4) = '" + getSessionUser().getUsCityOffice().substring(0,4) + "'";
		}else if(getSessionUser().getUsProvinceOffice() != null && getSessionUser().getUsProvinceOffice().length() > 0){
			sqlfrom = sqlfrom + " and substr(a.DIST_CD,0,2) = '" + getSessionUser().getUsProvinceOffice().substring(0,2) + "'";
		}
				
		if(IS_TOUDIQU.equals("1")){
			sqlfrom = sqlfrom + " group by substr(a.DIST_CD,0,2) ";
		}else if(IS_TOUDIQU.equals("2")){
			sqlfrom = sqlfrom + " group by substr(a.DIST_CD,0,4) ";
		}else if(IS_TOUDIQU.equals("3")){
			sqlfrom = sqlfrom + " group by substr(a.DIST_CD,0,4) ";
		}else if(IS_TOUDIQU.equals("4")){
			sqlfrom = sqlfrom + " group by substr(a.DIST_CD,0,4) ";
		}
		
		sqlfrom = sqlfrom + "  union all";
		
		sqlfrom = sqlfrom + " select  '' DIST_CD,'全部' PROVINCE_NAME,'合计' CITY_NAME,'' ITEM_DATE,sum(a.SR_COUNT) SR_COUNT,sum(a.ADDR_SR_COUNT) ADDR_SR_COUNT,sum(a.DRWH_COUNT) DRWH_COUNT,sum(a.CRWH_COUNT) CRWH_COUNT,sum(a.ZRWH_COUNT) ZRWH_COUNT,0,sum(a.QBWH_COUNT) QBWH_COUNT  from STAT_ECI_OPE_wh a  ";
		
		if(IS_TOUDIQU.equals("1")){
			sqlfrom = sqlfrom + "   where 1=1";
		}else if(IS_TOUDIQU.equals("2")){
			sqlfrom = sqlfrom + " ,sh_duotoudiqu_shi b  where a.dist_cd = b.dist_cd ";
		}else if(IS_TOUDIQU.equals("3")){
			sqlfrom = sqlfrom + " ,sh_duotoudiqu_pzf b  where a.dist_cd = b.dist_cd ";
		}else if(IS_TOUDIQU.equals("4")){
			sqlfrom = sqlfrom + " ,sh_duotoudiqu_sy b  where a.dist_cd = b.dist_cd ";
		}
		
		if(!ITEM_DATESS.equals("null") && ITEM_DATESS.length()>0){
			sqlfrom = sqlfrom + " and a.item_date  >= '" + ITEM_DATESS + "' ";
		}
		
		if(!ITEM_DATESE.equals("null") && ITEM_DATESE.length()>0){
			sqlfrom = sqlfrom + " and a.item_date  <= '" + ITEM_DATESE + "' ";
		}
		
		if(!IS_CUSPEO.equals("null") && IS_CUSPEO.length()>0){
			if(IS_CUSPEO.equals("1")){
				sqlfrom = sqlfrom + " and a.source  = '1' ";
			}else if(IS_CUSPEO.equals("2")){
				sqlfrom = sqlfrom + " and a.source  = '2' ";
			}
		}
		
		if(!IS_DISTRI.equals("null") && IS_DISTRI.length()>0){
			if(IS_DISTRI.equals("1")){
				sqlfrom = sqlfrom + " and IS_DISTRI  = '1' ";
			}else if(IS_DISTRI.equals("2")){
				sqlfrom = sqlfrom + " and IS_DISTRI  = '0' ";
			}
		}
		
		if(getSessionUser().getUsCityOffice() != null && getSessionUser().getUsCityOffice().length() > 0){
			sqlfrom = sqlfrom + " and substr(a.DIST_CD,0,4) = '" + getSessionUser().getUsCityOffice().substring(0,4) + "'";
		}else if(getSessionUser().getUsProvinceOffice() != null && getSessionUser().getUsProvinceOffice().length() > 0){
			sqlfrom = sqlfrom + " and substr(a.DIST_CD,0,2) = '" + getSessionUser().getUsProvinceOffice().substring(0,2) + "'";
		}
		
		sqlfrom = sqlfrom + " ) a ";
		String sqlwhere=" where 1=1 ";
		String sqlorder=" order by a.DIST_CD,a.PROVINCE_NAME ";
			

		sql=sql+sqlfrom+sqlwhere+sqlorder;
		sqlCount=sqlCount+sqlfrom+sqlwhere;
		
		page.setPageSize(1000);
		if(pageNo!=0){
			page.setPageNo(pageNo);
		}
		page=dispQueryDao.getBeanQuerytjpwhrb(sql, sqlCount, page);
		return "json";
	}
	
	@SuppressWarnings("unchecked")
	public String querytjpwhrbdn() throws Exception {
		
		if(userflag()==null){return "json";}
		
		String sql="select a.DIST_CD,decode(a.PROVINCE_NAME,null,a.DIST_CD,a.PROVINCE_NAME),decode(a.CITY_NAME,null,a.DIST_CD,a.CITY_NAME),a.ITEM_DATE,a.SR_COUNT,a.ADDR_SR_COUNT,a.DRWH_COUNT,a.CRWH_COUNT,a.ZRWH_COUNT,decode(a.NUM,null,0,a.num),a.QBWH_COUNT ";
		String sqlCount="select count(*) ";
		String sqlfrom=" from (";
		
		
		if(IS_TOUDIQU.equals("1")){
			sqlfrom = sqlfrom + " select substr(a.DIST_CD,0,2) DIST_CD, ";
		}else if(IS_TOUDIQU.equals("2")){
			sqlfrom = sqlfrom + " select substr(a.DIST_CD,0,4) DIST_CD, ";
		}else if(IS_TOUDIQU.equals("3")){
			sqlfrom = sqlfrom + " select substr(a.DIST_CD,0,4) DIST_CD, ";
		}else if(IS_TOUDIQU.equals("4")){
			sqlfrom = sqlfrom + " select substr(a.DIST_CD,0,4) DIST_CD, ";
		}
		
		sqlfrom = sqlfrom + " max(a.PROVINCE_NAME) PROVINCE_NAME,max(a.CITY_NAME) CITY_NAME,'' ITEM_DATE,sum(a.SR_COUNT) SR_COUNT,sum(a.ADDR_SR_COUNT) ADDR_SR_COUNT,sum(a.DRWH_COUNT) DRWH_COUNT,sum(a.CRWH_COUNT) CRWH_COUNT,sum(a.ZRWH_COUNT) ZRWH_COUNT, max(b.num) num,sum(a.QBWH_COUNT) QBWH_COUNT  from STAT_ECI_OPE_dn_wh a  ";
		
		if(IS_TOUDIQU.equals("1")){
			sqlfrom = sqlfrom + "  ,(select dt_province_code dist_cd,count(*) num from cp_wh_district group by dt_province_code) b where substr(a.DIST_CD,0,2)=b.dist_cd(+) ";
		}else if(IS_TOUDIQU.equals("2")){
			sqlfrom = sqlfrom + "  ,sh_duotoudiqu_shi b where a.dist_cd = b.dist_cd ";
		}else if(IS_TOUDIQU.equals("3")){
			sqlfrom = sqlfrom + "  ,sh_duotoudiqu_pzf b  where a.dist_cd = b.dist_cd ";
		}else if(IS_TOUDIQU.equals("4")){
			sqlfrom = sqlfrom + "  ,sh_duotoudiqu_sy b  where a.dist_cd = b.dist_cd ";
		}
		
		if(!ITEM_DATESS.equals("null") && ITEM_DATESS.length()>0){
			sqlfrom = sqlfrom + " and a.item_date  >= '" + ITEM_DATESS + "' ";
		}
		
		if(!ITEM_DATESE.equals("null") && ITEM_DATESE.length()>0){
			sqlfrom = sqlfrom + " and a.item_date  <= '" + ITEM_DATESE + "' ";
		}
		
		if(!IS_CUSPEO.equals("null") && IS_CUSPEO.length()>0){
			if(IS_CUSPEO.equals("1")){
				sqlfrom = sqlfrom + " and a.source  = '1' ";
			}else if(IS_CUSPEO.equals("2")){
				sqlfrom = sqlfrom + " and a.source  = '2' ";
			}
		}
		
		if(!IS_DISTRI.equals("null") && IS_DISTRI.length()>0){
			if(IS_DISTRI.equals("1")){
				sqlfrom = sqlfrom + " and IS_DISTRI  = '1' ";
			}else if(IS_DISTRI.equals("2")){
				sqlfrom = sqlfrom + " and IS_DISTRI  = '0' ";
			}
		}
		
		if(getSessionUser().getUsCityOffice() != null && getSessionUser().getUsCityOffice().length() > 0){
			sqlfrom = sqlfrom + " and substr(a.DIST_CD,0,4) = '" + getSessionUser().getUsCityOffice().substring(0,4) + "'";
		}else if(getSessionUser().getUsProvinceOffice() != null && getSessionUser().getUsProvinceOffice().length() > 0){
			sqlfrom = sqlfrom + " and substr(a.DIST_CD,0,2) = '" + getSessionUser().getUsProvinceOffice().substring(0,2) + "'";
		}
				
		if(IS_TOUDIQU.equals("1")){
			sqlfrom = sqlfrom + " group by substr(a.DIST_CD,0,2) ";
		}else if(IS_TOUDIQU.equals("2")){
			sqlfrom = sqlfrom + " group by substr(a.DIST_CD,0,4) ";
		}else if(IS_TOUDIQU.equals("3")){
			sqlfrom = sqlfrom + " group by substr(a.DIST_CD,0,4) ";
		}else if(IS_TOUDIQU.equals("4")){
			sqlfrom = sqlfrom + " group by substr(a.DIST_CD,0,4) ";
		}
		
		sqlfrom = sqlfrom + "  union all";
		
		sqlfrom = sqlfrom + " select  '' DIST_CD,'全部' PROVINCE_NAME,'合计' CITY_NAME,'' ITEM_DATE,sum(a.SR_COUNT) SR_COUNT,sum(a.ADDR_SR_COUNT) ADDR_SR_COUNT,sum(a.DRWH_COUNT) DRWH_COUNT,sum(a.CRWH_COUNT) CRWH_COUNT,sum(a.ZRWH_COUNT) ZRWH_COUNT,0,sum(a.QBWH_COUNT) QBWH_COUNT  from STAT_ECI_OPE_dn_wh a  ";
		
		if(IS_TOUDIQU.equals("1")){
			sqlfrom = sqlfrom + "   where 1=1";
		}else if(IS_TOUDIQU.equals("2")){
			sqlfrom = sqlfrom + " ,sh_duotoudiqu_shi b  where a.dist_cd = b.dist_cd ";
		}else if(IS_TOUDIQU.equals("3")){
			sqlfrom = sqlfrom + " ,sh_duotoudiqu_pzf b  where a.dist_cd = b.dist_cd ";
		}else if(IS_TOUDIQU.equals("4")){
			sqlfrom = sqlfrom + " ,sh_duotoudiqu_sy b  where a.dist_cd = b.dist_cd ";
		}
		
		if(!ITEM_DATESS.equals("null") && ITEM_DATESS.length()>0){
			sqlfrom = sqlfrom + " and a.item_date  >= '" + ITEM_DATESS + "' ";
		}
		
		if(!ITEM_DATESE.equals("null") && ITEM_DATESE.length()>0){
			sqlfrom = sqlfrom + " and a.item_date  <= '" + ITEM_DATESE + "' ";
		}
		
		if(!IS_CUSPEO.equals("null") && IS_CUSPEO.length()>0){
			if(IS_CUSPEO.equals("1")){
				sqlfrom = sqlfrom + " and a.source  = '1' ";
			}else if(IS_CUSPEO.equals("2")){
				sqlfrom = sqlfrom + " and a.source  = '2' ";
			}
		}
		
		if(!IS_DISTRI.equals("null") && IS_DISTRI.length()>0){
			if(IS_DISTRI.equals("1")){
				sqlfrom = sqlfrom + " and IS_DISTRI  = '1' ";
			}else if(IS_DISTRI.equals("2")){
				sqlfrom = sqlfrom + " and IS_DISTRI  = '0' ";
			}
		}
		
		if(getSessionUser().getUsCityOffice() != null && getSessionUser().getUsCityOffice().length() > 0){
			sqlfrom = sqlfrom + " and substr(a.DIST_CD,0,4) = '" + getSessionUser().getUsCityOffice().substring(0,4) + "'";
		}else if(getSessionUser().getUsProvinceOffice() != null && getSessionUser().getUsProvinceOffice().length() > 0){
			sqlfrom = sqlfrom + " and substr(a.DIST_CD,0,2) = '" + getSessionUser().getUsProvinceOffice().substring(0,2) + "'";
		}
		
		sqlfrom = sqlfrom + " ) a ";
		String sqlwhere=" where 1=1 ";
		String sqlorder=" order by a.DIST_CD,a.PROVINCE_NAME ";
			

		sql=sql+sqlfrom+sqlwhere+sqlorder;
		sqlCount=sqlCount+sqlfrom+sqlwhere;
		
		page.setPageSize(1000);
		if(pageNo!=0){
			page.setPageNo(pageNo);
		}
		page=dispQueryDao.getBeanQuerytjpwhrb(sql, sqlCount, page);
		return "json";
	}
	
	@SuppressWarnings("unchecked")
	public String querytjpwhrbopdn() throws Exception {
		
		if(userflag()==null){return "json";}
		
		String sql="select a.DIST_CD,decode(a.PROVINCE_NAME,null,a.DIST_CD,a.PROVINCE_NAME),decode(a.CITY_NAME,null,a.DIST_CD,a.CITY_NAME),a.ITEM_DATE,a.SR_COUNT,a.ADDR_SR_COUNT,a.DRWH_COUNT,a.CRWH_COUNT,a.ZRWH_COUNT,decode(a.NUM,null,0,a.num),a.QBWH_COUNT,REMEAKTJ ";
		String sqlCount="select count(*) ";
		String sqlfrom=" from (";
		
		
		if(IS_TOUDIQU.equals("1")){
			sqlfrom = sqlfrom + " select substr(a.DIST_CD,0,2) DIST_CD, ";
		}else if(IS_TOUDIQU.equals("2")){
			sqlfrom = sqlfrom + " select substr(a.DIST_CD,0,4) DIST_CD, ";
		}else if(IS_TOUDIQU.equals("3")){
			sqlfrom = sqlfrom + " select substr(a.DIST_CD,0,4) DIST_CD, ";
		}else if(IS_TOUDIQU.equals("4")){
			sqlfrom = sqlfrom + " select substr(a.DIST_CD,0,4) DIST_CD, ";
		}
		
		sqlfrom = sqlfrom + " max(a.PROVINCE_NAME) PROVINCE_NAME,max(a.CITY_NAME) CITY_NAME,'' ITEM_DATE,sum(a.SR_COUNT) SR_COUNT,sum(a.ADDR_SR_COUNT) ADDR_SR_COUNT,sum(a.DRWH_COUNT) DRWH_COUNT,sum(a.CRWH_COUNT) CRWH_COUNT,sum(a.ZRWH_COUNT) ZRWH_COUNT, max(b.num) num,sum(a.QBWH_COUNT) QBWH_COUNT,decode(REMEAKTJ,'0','没有处理','1','正确地址','2','虚假或错误地址','3','地址不完整或不规范','4','地址与寄达地不符',REMEAKTJ) REMEAKTJ  from STAT_ECI_OPE_dn_wh a  ";
		
		if(IS_TOUDIQU.equals("1")){
			sqlfrom = sqlfrom + "  ,(select dt_province_code dist_cd,count(*) num from cp_wh_district group by dt_province_code) b where substr(a.DIST_CD,0,2)=b.dist_cd(+) ";
		}else if(IS_TOUDIQU.equals("2")){
			sqlfrom = sqlfrom + "  ,sh_duotoudiqu_shi b where a.dist_cd = b.dist_cd ";
		}else if(IS_TOUDIQU.equals("3")){
			sqlfrom = sqlfrom + "  ,sh_duotoudiqu_pzf b  where a.dist_cd = b.dist_cd ";
		}else if(IS_TOUDIQU.equals("4")){
			sqlfrom = sqlfrom + "  ,sh_duotoudiqu_sy b  where a.dist_cd = b.dist_cd ";
		}
		
		if(!ITEM_DATESS.equals("null") && ITEM_DATESS.length()>0){
			sqlfrom = sqlfrom + " and a.item_date  >= '" + ITEM_DATESS + "' ";
		}
		
		if(!ITEM_DATESE.equals("null") && ITEM_DATESE.length()>0){
			sqlfrom = sqlfrom + " and a.item_date  <= '" + ITEM_DATESE + "' ";
		}
		
		if(!IS_CUSPEO.equals("null") && IS_CUSPEO.length()>0){
			if(IS_CUSPEO.equals("1")){
				sqlfrom = sqlfrom + " and a.source  = '1' ";
			}else if(IS_CUSPEO.equals("2")){
				sqlfrom = sqlfrom + " and a.source  = '2' ";
			}
		}
		
		if(!IS_DISTRI.equals("null") && IS_DISTRI.length()>0){
			if(IS_DISTRI.equals("1")){
				sqlfrom = sqlfrom + " and IS_DISTRI  = '1' ";
			}else if(IS_DISTRI.equals("2")){
				sqlfrom = sqlfrom + " and IS_DISTRI  = '0' ";
			}
		}
		
		if(getSessionUser().getUsCityOffice() != null && getSessionUser().getUsCityOffice().length() > 0){
			sqlfrom = sqlfrom + " and substr(a.DIST_CD,0,4) = '" + getSessionUser().getUsCityOffice().substring(0,4) + "'";
		}else if(getSessionUser().getUsProvinceOffice() != null && getSessionUser().getUsProvinceOffice().length() > 0){
			sqlfrom = sqlfrom + " and substr(a.DIST_CD,0,2) = '" + getSessionUser().getUsProvinceOffice().substring(0,2) + "'";
		}
				
		if(IS_TOUDIQU.equals("1")){
			sqlfrom = sqlfrom + " group by substr(a.DIST_CD,0,2) ";
		}else if(IS_TOUDIQU.equals("2")){
			sqlfrom = sqlfrom + " group by substr(a.DIST_CD,0,4) ";
		}else if(IS_TOUDIQU.equals("3")){
			sqlfrom = sqlfrom + " group by substr(a.DIST_CD,0,4) ";
		}else if(IS_TOUDIQU.equals("4")){
			sqlfrom = sqlfrom + " group by substr(a.DIST_CD,0,4) ";
		}
		
		sqlfrom = sqlfrom + " ,decode(REMEAKTJ,'0','没有处理','1','正确地址','2','虚假或错误地址','3','地址不完整或不规范','4','地址与寄达地不符',REMEAKTJ) ";
		
		sqlfrom = sqlfrom + " ) a ";
		String sqlwhere=" where 1=1 ";
		String sqlorder=" order by a.DIST_CD,a.PROVINCE_NAME ";
			

		sql=sql+sqlfrom+sqlwhere+sqlorder;
		sqlCount=sqlCount+sqlfrom+sqlwhere;
		
		page.setPageSize(1000);
		if(pageNo!=0){
			page.setPageNo(pageNo);
		}
		page=dispQueryDao.getBeanQuerytjpwhrbopdn(sql, sqlCount, page);
		return "json";
	}
	
	
	@SuppressWarnings("unchecked")
	public String querytjdt() throws Exception {
		
		if(userflag()==null){return "json";}
		
		String sql=" select DIST_CD,PROVINCE_NAME,DT_PK_CODE,DT_NAME,CCOUNT,NSRCOUNT,SRCOUNT,po,pt ";
		String sqlCount="select count(*) ";
		String sqlfrom= "  ";
		String sqlwhere=" where 1=1";
		String sqlorder=" order by trflag,DIST_CD,DT_PK_CODE,po,pt ";
		
		sqlfrom = sqlfrom + " from ( select 1 trflag,DIST_CD,max(PROVINCE_NAME) PROVINCE_NAME,a.DT_PK_CODE,max(a.DT_NAME) DT_NAME,sum(CCOUNT) CCOUNT,sum(NSRCOUNT) NSRCOUNT,sum(SRCOUNT) SRCOUNT,decode(substr(product,1,1),1,'文件','3','物品','其他') po,decode(substr(product,2,1),6,'经济时限',9,'标准时限','其他') pt from STAT_ECI_OPE_DT a,CP_WH_DISTRICT b where 1=1 and a.DT_PK_CODE = b.DT_PK_CODE and b.nj_flag = 1" ;
		
		if(!IS_DISTRI.equals("null") && IS_DISTRI.length()>0){
			if(IS_DISTRI.equals("1")){
				sqlfrom = sqlfrom + " and IS_DISTRI  = '1' ";
			}else if(IS_DISTRI.equals("2")){
				sqlfrom = sqlfrom + " and IS_DISTRI  = '0' ";
			}
		}
		
		if(!IS_CUSPEO.equals("null") && IS_CUSPEO.length()>0){
			if(IS_CUSPEO.equals("1")){
				sqlfrom = sqlfrom + " and substr(product,1,1)  = '1' ";
			}else if(IS_CUSPEO.equals("2")){
				sqlfrom = sqlfrom + " and substr(product,1,1)  = '3' ";
			}
		}
		
		if(!IS_TOUDIQU.equals("null") && IS_TOUDIQU.length()>0){
			if(IS_TOUDIQU.equals("1")){
				sqlfrom = sqlfrom + " and substr(product,2,1)  = '6' ";
			}else if(IS_TOUDIQU.equals("2")){
				sqlfrom = sqlfrom + " and substr(product,2,1)  = '9' ";
			}
		}
		
		if(!PROVINCE_CODE.equals("null") && PROVINCE_CODE.length()>0){
			sqlfrom = sqlfrom + " and DIST_CD  = '" + PROVINCE_CODE.substring(0,2) + "' ";
		}
		
		if(!ITEM_DATESS.equals("null") && ITEM_DATESS.length()>0){
			sqlfrom = sqlfrom + " and item_date  >= '" + ITEM_DATESS + "' ";
		}
		
		if(!ITEM_DATESE.equals("null") && ITEM_DATESE.length()>0){
			sqlfrom = sqlfrom + " and item_date  <= '" + ITEM_DATESE + "' ";
		}
		
		sqlfrom = sqlfrom + "group by DIST_CD,a.DT_PK_CODE,substr(product,1,1),substr(product,2,1) union all ";
		
		
		sqlfrom = sqlfrom + " select 2 trflag,'合计','合计' PROVINCE_NAME,'-' DT_PK_CODE,'-' DT_NAME,sum(CCOUNT) CCOUNT,sum(NSRCOUNT) NSRCOUNT,sum(SRCOUNT) SRCOUNT,'-' po,'-' pt from STAT_ECI_OPE_DT a,CP_WH_DISTRICT b where 1=1 and a.DT_PK_CODE = b.DT_PK_CODE and b.nj_flag = 1 " ;
		
		if(!IS_DISTRI.equals("null") && IS_DISTRI.length()>0){
			if(IS_DISTRI.equals("1")){
				sqlfrom = sqlfrom + " and IS_DISTRI  = '1' ";
			}else if(IS_DISTRI.equals("2")){
				sqlfrom = sqlfrom + " and IS_DISTRI  = '0' ";
			}
		}
		
		if(!IS_CUSPEO.equals("null") && IS_CUSPEO.length()>0){
			if(IS_CUSPEO.equals("1")){
				sqlfrom = sqlfrom + " and substr(product,2,1)  = '1' ";
			}else if(IS_CUSPEO.equals("2")){
				sqlfrom = sqlfrom + " and substr(product,2,1)  = '3' ";
			}
		}
		
		if(!IS_TOUDIQU.equals("null") && IS_TOUDIQU.length()>0){
			if(IS_TOUDIQU.equals("1")){
				sqlfrom = sqlfrom + " and substr(product,8,1)  = '6' ";
			}else if(IS_TOUDIQU.equals("2")){
				sqlfrom = sqlfrom + " and substr(product,8,1)  = '9' ";
			}
		}
		
		if(!PROVINCE_CODE.equals("null") && PROVINCE_CODE.length()>0){
			sqlfrom = sqlfrom + " and DIST_CD  = '" + PROVINCE_CODE.substring(0,2) + "' ";
		}
		
		if(!ITEM_DATESS.equals("null") && ITEM_DATESS.length()>0){
			sqlfrom = sqlfrom + " and item_date  >= '" + ITEM_DATESS + "' ";
		}
		
		if(!ITEM_DATESE.equals("null") && ITEM_DATESE.length()>0){
			sqlfrom = sqlfrom + " and item_date  <= '" + ITEM_DATESE + "' ";
		}
		
		sqlfrom = sqlfrom + " ) ";
		
		sql=sql+sqlfrom+sqlwhere+sqlorder;
		sqlCount=sqlCount+sqlfrom+sqlwhere;
		
		page.setPageSize(100);
		if(pageNo!=0){
			page.setPageNo(pageNo);
		}
		page=dispQueryDao.getBeanQueryTjdt(sql, sqlCount, page);
		return "json";
	}
	
	public String userflag(){
		if(getSessionUser()!=null && getSessionUser().getUsLoginId() != null && getSessionUser().getUsLoginId().length() > 0){
			return getSessionUser().getUsLoginId();
		}
		else{
			saveMessage = "用户信息丢失,请重新登陆!";
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public String queryexportmethod() throws Exception {

	
		String sql="";
		String sqlfrom="";
		String sqlwhere="";
		String sqlorder="";
		String sqlCount=" select count(*) ";
		
		String[] LINEKEYs = LINEKEY.split(",");
		
		String[] EXDATAs = EXDATA.split(",");
		
		//System.out.println(EXDATA);
		
		String ulid = userflag();
		
		if(ulid!=null){
		
			if(LINEKEYs[0].equals("viewtjdt")){
				sql=" select PROVINCE_NAME,DT_NAME,po,pt,CCOUNT,NSRCOUNT,SRCOUNT ";
				sqlfrom= "  ";
				sqlwhere=" where 1=1";
				sqlorder=" order by trflag,DIST_CD,DT_PK_CODE,po,pt ";
				
				sqlfrom = sqlfrom + " from ( select 1 trflag,DIST_CD,max(PROVINCE_NAME) PROVINCE_NAME,a.DT_PK_CODE,max(a.DT_NAME) DT_NAME,sum(CCOUNT) CCOUNT,sum(NSRCOUNT) NSRCOUNT,sum(SRCOUNT) SRCOUNT,decode(substr(product,1,1),1,'文件','3','物品','其他') po,decode(substr(product,2,1),6,'经济时限',9,'标准时限','其他') pt from STAT_ECI_OPE_DT a,CP_WH_DISTRICT b where 1=1 and a.DT_PK_CODE = b.DT_PK_CODE and b.nj_flag = 1 " ;
				
				if(!EXDATAs[0].trim().equals("null") && EXDATAs[0].trim().length()>0){
					if(EXDATAs[0].trim().equals("1")){
						sqlfrom = sqlfrom + " and IS_DISTRI  = '1' ";
					}else if(EXDATAs[0].trim().equals("2")){
						sqlfrom = sqlfrom + " and IS_DISTRI  = '0' ";
					}
				}
				
				if(!EXDATAs[1].trim().equals("null") && EXDATAs[1].trim().length()>0){
					if(EXDATAs[1].trim().equals("1")){
						sqlfrom = sqlfrom + " and substr(product,1,1)  = '1' ";
					}else if(EXDATAs[1].trim().equals("2")){
						sqlfrom = sqlfrom + " and substr(product,1,1)  = '3' ";
					}
				}
				
				if(!EXDATAs[2].trim().equals("null") && EXDATAs[2].trim().length()>0){
					if(EXDATAs[2].trim().equals("1")){
						sqlfrom = sqlfrom + " and substr(product,2,1)  = '6' ";
					}else if(EXDATAs[2].trim().equals("2")){
						sqlfrom = sqlfrom + " and substr(product,2,1)  = '9' ";
					}
				}
				
				if(!EXDATAs[3].trim().equals("null") && EXDATAs[3].trim().length()>0){
					sqlfrom = sqlfrom + " and DIST_CD  = '" + EXDATAs[3].trim().substring(0,2) + "' ";
				}
				
				if(!EXDATAs[4].trim().equals("null") && EXDATAs[4].trim().length()>0){
					sqlfrom = sqlfrom + " and item_date  >= '" + EXDATAs[4].trim() + "' ";
				}
				
				if(!EXDATAs[5].trim().equals("null") && EXDATAs[5].trim().length()>0){
					sqlfrom = sqlfrom + " and item_date  <= '" + EXDATAs[5].trim() + "' ";
				}
				
				sqlfrom = sqlfrom + "group by DIST_CD,a.DT_PK_CODE,substr(product,1,1),substr(product,2,1) union all ";
				
				
				sqlfrom = sqlfrom + " select 2 trflag,'合计','合计' PROVINCE_NAME,'-' DT_PK_CODE,'-' DT_NAME,sum(CCOUNT) CCOUNT,sum(NSRCOUNT) NSRCOUNT,sum(SRCOUNT) SRCOUNT,'-' po,'-' pt from STAT_ECI_OPE_DT a,CP_WH_DISTRICT b where 1=1 and a.DT_PK_CODE = b.DT_PK_CODE and b.nj_flag = 1" ;
				
				if(!EXDATAs[0].trim().equals("null") && EXDATAs[0].trim().length()>0){
					if(EXDATAs[0].trim().equals("1")){
						sqlfrom = sqlfrom + " and IS_DISTRI  = '1' ";
					}else if(EXDATAs[0].trim().equals("2")){
						sqlfrom = sqlfrom + " and IS_DISTRI  = '0' ";
					}
				}
				
				if(!EXDATAs[1].trim().equals("null") && EXDATAs[1].trim().length()>0){
					if(EXDATAs[1].trim().equals("1")){
						sqlfrom = sqlfrom + " and substr(product,1,1)  = '1' ";
					}else if(EXDATAs[1].trim().equals("2")){
						sqlfrom = sqlfrom + " and substr(product,1,1)  = '3' ";
					}
				}
				
				if(!EXDATAs[2].trim().equals("null") && EXDATAs[2].trim().length()>0){
					if(EXDATAs[2].trim().equals("1")){
						sqlfrom = sqlfrom + " and substr(product,2,1)  = '6' ";
					}else if(EXDATAs[2].trim().equals("2")){
						sqlfrom = sqlfrom + " and substr(product,2,1)  = '9' ";
					}
				}
				
				if(!EXDATAs[3].trim().equals("null") && EXDATAs[3].trim().length()>0){
					sqlfrom = sqlfrom + " and DIST_CD  = '" + EXDATAs[3].trim().substring(0,2) + "' ";
				}
				
				if(!EXDATAs[4].trim().equals("null") && EXDATAs[4].trim().length()>0){
					sqlfrom = sqlfrom + " and item_date  >= '" + EXDATAs[4].trim() + "' ";
				}
				
				if(!EXDATAs[5].trim().equals("null") && EXDATAs[5].trim().length()>0){
					sqlfrom = sqlfrom + " and item_date  <= '" + EXDATAs[5].trim() + "' ";
				}
				
				sqlfrom = sqlfrom + " ) ";
			}
			
			sql=sql+sqlfrom+sqlwhere+sqlorder;
			
			sqlCount=sqlCount+sqlfrom+sqlwhere;
			
			long tsqlcount = dispQueryDao.getQueryCount(sqlCount);
			
			pagecount = String.valueOf(tsqlcount);
	
			List<?> list = dispQueryDao.getqueryexportmethod(sql);
			
			Exportmathod exportmathod = new Exportmathod();
			
			EXPORTALLPATH = exportmathod.exportall(ulid,LINEMAIN,LINENAME,list);
		
		}
		
		return "json";
	}
	
	
	@SuppressWarnings("unchecked")
	public String queryitemnoxqcx() throws Exception {
		
		if(userflag()==null){return "json";}
		
		String sql="";
		String sqlCount="";
		String sqlfrom="";
		String sqlwhere="";
		String sqlorder="";
		
		sql=" select a.itemno ITEMNO," +
			" a.rec_prov||a.rec_city||a.rec_county REC_PROVCITY," +
			" a.collect_date COLLECT_DATE," +
			" a.collect_office COLLECT_OFFICE," +
			" a.inserttime INSERTTIME," +
			" decode(a.addr_flag,1,a.rec_alladdr,0,a.rec_street||a.rec_org,null) REC_ALLADDR," +
			" a.is_distri IS_DISTRI," +
			" a.postdist POSTDIST," +
			" decode(b.match_date||b.match_time,null,'没有匹配信息',b.match_date||b.match_time) MATCH_DATETIME," +
			" decode(b.Send_Flag,1,'已发送','未发送') SEND_FLAG," +
			" c.dt_name DT_NAME, " +
			" c.dt_alias_name DT_ALIAS_NAME ";
		
		sqlCount="select count(*) ";
		
		sqlfrom=" from exg_clt_item_ope a ,exg_esb_match b,cp_wh_district c ";
		
		sqlwhere=" where a.itemno = b.itemno  and to_number(a.postdist) = c.dt_pk_code  ";
		
		if(!ITEMNO.equals("null") && ITEMNO.length()>0){
			sqlwhere = sqlwhere + " and a.itemno in ('" + ITEMNO.replaceAll(",", "','") + "') ";
		}
		
		sqlorder=" order by a.itemno,a.collect_date,a.inserttime,MATCH_DATETIME ";
		 
		
		sql=sql+sqlfrom+sqlwhere+sqlorder;
		sqlCount=sqlCount+sqlfrom+sqlwhere;
		
		page.setPageSize(99999);
		if(pageNo!=0){
			page.setPageNo(pageNo);
		}
		page=dispQueryDao.getBeanQueryitemnoxqcx(sql, sqlCount, page);
		return "json";
	}
}
