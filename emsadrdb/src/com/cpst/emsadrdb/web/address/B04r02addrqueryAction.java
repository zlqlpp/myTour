package com.cpst.emsadrdb.web.address;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.cpst.core.orm.Page;
import com.cpst.core.web.struts2.BaseActionSupport;
import com.cpst.emsadrdb.dao.address.AddrQueryDao;
import com.cpst.emsadrdb.dao.address.JnadtpgQueryDao;
import com.cpst.emsadrdb.entity.disp.TransBean;
import com.cpst.emsadrdb.service.address.AddrCommon;
import com.cpst.emsadrdb.service.disp.DispCommon;

@ParentPackage("jsoncrud")
@Results( {@Result(type="json", name = "json")})
public class B04r02addrqueryAction  extends BaseActionSupport{

	private static final long serialVersionUID = -6071823460042875876L;
	
	private String q;//ajax参数
	private int pageNo;
	@SuppressWarnings("unchecked")
	private Page page=new Page(10);
	
	@Autowired
	private AddrQueryDao addrQueryDao;
	@Autowired
	private JnadtpgQueryDao jnadtpgQueryDao;
	private String PROVINCE_ID;
	private String DIST_CD;
	private String STRT_ID;
	private String CITY_ID;
	private String COUNTY_ID;
	private String PROVINCE_IDJD;
	private String CITY_IDJD;
	private String COUNTY_IDJD;
	private String STREET_NAME;
	private String RSDNBLDG_NAME;
	private String ORG_NAME;
	private String BLDG_NAME;
	private String DORPLT_ID;
	private String RSDNBLDG_ID;
	private String ORG_ID;
	private String ALL_NAME;
	private String ALL_DATA_FLAG;
	private String TOTAL_DISTRICT_NAME;
	private String TOTAL_STREET_NAME;
	
	private String ALL_DATA_NUMBER;

	private String ADDR_NAME;
	private String ISSTREET_NAME;

	private String TYPE_FLAG;
	private String TYPE_DATA;
	
	private String PROVINCE_NAMES;
	private String CITY_NAMES;
	private String ALL_PATH;
	
	private String FIX;
	private String FIX_FLAGNAME;
	
	private String TOTAL_DIUMENPAI;
	
	private String ITEMNO;
	private String REC_ALLADDR;
	
	
	private String ISDT;
	private String ISPG;
	
	private String ISPOSTDIST;
	private String ISPOSTSEG;
	
	private String ISSR_FLAG;
	
	private String IS_DISTRI;
	
	private String IS_YOUDIZHI;
	private String IS_CHAXUNLX;
	private String PAIXU;
	private String ITEM_DATESS;
	private String ITEM_DATESE;
	private String SSLX;
	private String ISWH;
	private String ISPOSTDISTTYPE;
	private String ISSJN;
	private String SOURCE;
	private String COLLECT_DATE;
	private String COLLECT_DATEEND;
	private String EXPORTALLPATH;
	private String DT_PK_CODEZS;
	private String ISLX;
	
	
	public String getISLX() {
		return ISLX;
	}


	public void setISLX(String islx) {
		ISLX = AddrCommon.sql_inj(islx);
	}


	


	public String getDT_PK_CODEZS() {
		return DT_PK_CODEZS;
	}


	public void setDT_PK_CODEZS(String dt_pk_codezs) {
		DT_PK_CODEZS = AddrCommon.sql_inj(dt_pk_codezs);
	}


	public String getSOURCE() {
		return SOURCE;
	}


	public void setSOURCE(String source) {
		SOURCE = AddrCommon.sql_inj(source);
	}


	public String getCOLLECT_DATE() {
		return COLLECT_DATE;
	}


	public void setCOLLECT_DATE(String collect_date) {
		COLLECT_DATE = AddrCommon.sql_inj(collect_date);
	}


	public String getCOLLECT_DATEEND() {
		return COLLECT_DATEEND;
	}


	public void setCOLLECT_DATEEND(String collect_dateend) {
		COLLECT_DATEEND = AddrCommon.sql_inj(collect_dateend);
	}


	public String getEXPORTALLPATH() {
		return EXPORTALLPATH;
	}


	public void setEXPORTALLPATH(String exportallpath) {
		EXPORTALLPATH = AddrCommon.sql_inj(exportallpath);
	}


	public String getPROVINCE_IDJD() {
		return PROVINCE_IDJD;
	}


	public void setPROVINCE_IDJD(String province_idjd) {
		PROVINCE_IDJD = AddrCommon.sql_inj(province_idjd);
	}


	public String getCITY_IDJD() {
		return CITY_IDJD;
	}


	public void setCITY_IDJD(String city_idjd) {
		CITY_IDJD = AddrCommon.sql_inj(city_idjd);
	}


	public String getCOUNTY_IDJD() {
		return COUNTY_IDJD;
	}


	public void setCOUNTY_IDJD(String county_idjd) {
		COUNTY_IDJD = AddrCommon.sql_inj(county_idjd);
	}


	public String getISSJN() {
		return ISSJN;
	}


	public void setISSJN(String issjn) {
		ISSJN = AddrCommon.sql_inj(issjn);
	}


	public String getISPOSTDISTTYPE() {
		return ISPOSTDISTTYPE;
	}


	public void setISPOSTDISTTYPE(String ispostdisttype) {
		ISPOSTDISTTYPE = AddrCommon.sql_inj(ispostdisttype);
	}


	public String getISWH() {
		return ISWH;
	}


	public void setISWH(String iswh) {
		ISWH = AddrCommon.sql_inj(iswh);
	}


	public String getSSLX() {
		return SSLX;
	}


	public void setSSLX(String sslx) {
		SSLX = AddrCommon.sql_inj(sslx);
	}


	public String getITEM_DATESS() {
		return ITEM_DATESS;
	}


	public void setITEM_DATESS(String item_datess) {
		ITEM_DATESS = AddrCommon.sql_inj(item_datess);
	}


	public String getITEM_DATESE() {
		return ITEM_DATESE;
	}


	public void setITEM_DATESE(String item_datese) {
		ITEM_DATESE = AddrCommon.sql_inj(item_datese);
	}


	public String getPAIXU() {
		return PAIXU;
	}


	public void setPAIXU(String paixu) {
		PAIXU = AddrCommon.sql_inj(paixu);
	}


	public String getIS_CHAXUNLX() {
		return IS_CHAXUNLX;
	}


	public void setIS_CHAXUNLX(String is_chaxunlx) {
		IS_CHAXUNLX = AddrCommon.sql_inj(is_chaxunlx);
	}


	public String getIS_YOUDIZHI() {
		return IS_YOUDIZHI;
	}


	public void setIS_YOUDIZHI(String is_youdizhi) {
		IS_YOUDIZHI = AddrCommon.sql_inj(is_youdizhi);
	}


	public String getIS_DISTRI() {
		return IS_DISTRI;
	}


	public void setIS_DISTRI(String is_distri) {
		IS_DISTRI = AddrCommon.sql_inj(is_distri);
	}


	public String getISSR_FLAG() {
		return ISSR_FLAG;
	}


	public void setISSR_FLAG(String issr_flag) {
		ISSR_FLAG = AddrCommon.sql_inj(issr_flag);
	}


	public String getISPOSTDIST() {
		return ISPOSTDIST;
	}


	public void setISPOSTDIST(String ispostdist) {
		ISPOSTDIST = AddrCommon.sql_inj(ispostdist);
	}


	public String getISPOSTSEG() {
		return ISPOSTSEG;
	}


	public void setISPOSTSEG(String ispostseg) {
		ISPOSTSEG = AddrCommon.sql_inj(ispostseg);
	}


	public String getISDT() {
		return ISDT;
	}


	public void setISDT(String isdt) {
		ISDT = AddrCommon.sql_inj(isdt);
	}


	public String getISPG() {
		return ISPG;
	}


	public void setISPG(String ispg) {
		ISPG = AddrCommon.sql_inj(ispg);
	}


	public String getITEMNO() {
		return ITEMNO;
	}


	public void setITEMNO(String itemno) {
		ITEMNO = AddrCommon.sql_inj(itemno);
	}


	public String getREC_ALLADDR() {
		return REC_ALLADDR;
	}


	public void setREC_ALLADDR(String rec_alladdr) {
		REC_ALLADDR = AddrCommon.sql_inj(rec_alladdr);
	}


	public String getTOTAL_DIUMENPAI() {
		return TOTAL_DIUMENPAI;
	}


	public void setTOTAL_DIUMENPAI(String total_diumenpai) {
		TOTAL_DIUMENPAI = AddrCommon.sql_inj(total_diumenpai);
	}


	public String getFIX() {
		return FIX;
	}


	public void setFIX(String fix) {
		FIX = AddrCommon.sql_inj(fix);
	}


	public String getFIX_FLAGNAME() {
		return FIX_FLAGNAME;
	}


	public void setFIX_FLAGNAME(String fix_flagname) {
		FIX_FLAGNAME = AddrCommon.sql_inj(fix_flagname);
	}


	public String getISSTREET_NAME() {
		return ISSTREET_NAME;
	}


	public void setISSTREET_NAME(String isstreet_name) {
		ISSTREET_NAME = AddrCommon.sql_inj(isstreet_name);
	}


	public String getALL_PATH() {
		return ALL_PATH;
	}


	public void setALL_PATH(String all_path) {
		ALL_PATH = AddrCommon.sql_inj(all_path);
	}


	public String getPROVINCE_NAMES() {
		return PROVINCE_NAMES;
	}


	public void setPROVINCE_NAMES(String province_names) {
		PROVINCE_NAMES = AddrCommon.sql_inj(province_names);
	}


	public String getCITY_NAMES() {
		return CITY_NAMES;
	}


	public void setCITY_NAMES(String city_names) {
		CITY_NAMES = AddrCommon.sql_inj(city_names);
	}


	public String getADDR_NAME() {
		return ADDR_NAME;
	}


	public void setADDR_NAME(String addr_name) {
		ADDR_NAME = AddrCommon.sql_inj(addr_name);
	}


	public String getTYPE_FLAG() {
		return TYPE_FLAG;
	}


	public void setTYPE_FLAG(String type_flag) {
		TYPE_FLAG = AddrCommon.sql_inj(type_flag);
	}


	public String getTYPE_DATA() {
		return TYPE_DATA;
	}


	public void setTYPE_DATA(String type_data) {
		TYPE_DATA = type_data;
	}

	private String POST_CD;
	

	public String getPOST_CD() {
		return POST_CD;
	}


	public void setPOST_CD(String post_cd) {
		POST_CD = AddrCommon.sql_inj(post_cd);
	}

	public String getALL_DATA_NUMBER() {
		return ALL_DATA_NUMBER;
	}


	public void setALL_DATA_NUMBER(String all_data_number) {
		ALL_DATA_NUMBER = AddrCommon.sql_inj(all_data_number);
	}


	public String getALL_DATA_FLAG() {
		return ALL_DATA_FLAG;
	}


	public void setALL_DATA_FLAG(String all_data_flag) {
		ALL_DATA_FLAG = AddrCommon.sql_inj(all_data_flag);
	}


	public String getTOTAL_DISTRICT_NAME() {
		return TOTAL_DISTRICT_NAME;
	}


	public void setTOTAL_DISTRICT_NAME(String total_district_name) {
		TOTAL_DISTRICT_NAME = AddrCommon.sql_inj(total_district_name);
	}


	public String getTOTAL_STREET_NAME() {
		return TOTAL_STREET_NAME;
	}


	public void setTOTAL_STREET_NAME(String total_street_name) {
		TOTAL_STREET_NAME = AddrCommon.sql_inj(total_street_name);
	}


	public String getRSDNBLDG_ID() {
		return RSDNBLDG_ID;
	}


	public void setRSDNBLDG_ID(String rsdnbldg_id) {
		RSDNBLDG_ID = AddrCommon.sql_inj(rsdnbldg_id);
	}


	public String getORG_ID() {
		return ORG_ID;
	}


	public void setORG_ID(String org_id) {
		ORG_ID = AddrCommon.sql_inj(org_id);
	}


	public String getALL_NAME() {
		return ALL_NAME;
	}


	public void setALL_NAME(String all_name) {
		ALL_NAME = AddrCommon.sql_inj(all_name);
	}


	public String getDORPLT_ID() {
		return DORPLT_ID;
	}


	public void setDORPLT_ID(String dorplt_id) {
		DORPLT_ID = AddrCommon.sql_inj(dorplt_id);
	}


	public String getBLDG_NAME() {
		return BLDG_NAME;
	}


	public void setBLDG_NAME(String bldg_name) {
		BLDG_NAME = AddrCommon.sql_inj(bldg_name);
	}


	public String getDIST_CD() {
		return DIST_CD;
	}


	public void setDIST_CD(String dist_cd) {
		DIST_CD = AddrCommon.sql_inj(dist_cd);
	}


	public String getSTRT_ID() {
		return STRT_ID;
	}


	public void setSTRT_ID(String strt_id) {
		STRT_ID = AddrCommon.sql_inj(strt_id);
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


	public String getPROVINCE_ID() {
		return PROVINCE_ID;
	}


	public void setPROVINCE_ID(String province_id) {
		PROVINCE_ID = AddrCommon.sql_inj(province_id);
	}


	public String getCITY_ID() {
		return CITY_ID;
	}


	public void setCITY_ID(String city_id) {
		CITY_ID = AddrCommon.sql_inj(city_id);
	}


	public String getCOUNTY_ID() {
		return COUNTY_ID;
	}


	public void setCOUNTY_ID(String county_id) {
		COUNTY_ID = AddrCommon.sql_inj(county_id);
	}


	public String getSTREET_NAME() {
		return STREET_NAME;
	}


	public void setSTREET_NAME(String street_name) {
		STREET_NAME = AddrCommon.sql_inj(street_name);
	}


	public String getRSDNBLDG_NAME() {
		return RSDNBLDG_NAME;
	}


	public void setRSDNBLDG_NAME(String rsdnbldg_name) {
		RSDNBLDG_NAME = AddrCommon.sql_inj(rsdnbldg_name);
	}


	public String getORG_NAME() {
		return ORG_NAME;
	}


	public void setORG_NAME(String org_name) {
		ORG_NAME = AddrCommon.sql_inj(org_name);
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
	public String querystreets() throws Exception {		
		
		if(!userflag()){return "json";}
		
		String sql="select b.STRT_ID,b.DIST_CD,a.PROVINCE_NAME||a.CITY_NAME||a.COUNTY_NAME,b.STRT1_NAME||b.STRT2_NAME||b.STRT3_NAME||b.STRT4_NAME||b.STRT5_NAME,b.DATA_FLAG,b.POST_CD ";
		String sqlCount="select count(*) ";
		String sqlfrom=" from CP_BASE_ORG_DISTRICT a,CP_MK_ADR_STREET_" + PROVINCE_ID.substring(0,2) + " b ";
		
		if(!POST_CD.equals("null") && POST_CD.length()>0){
			sqlfrom = sqlfrom + " ," +
					" (select strt_id from CP_MK_ADR_BLDGRSDNS_" + PROVINCE_ID.substring(0,2) + "  where POST_CD = '" + POST_CD + "' " +
							"union select strt_id from CP_MK_ORG_ORGANIZATION_" + PROVINCE_ID.substring(0,2) + " where POST_CD = '" + POST_CD + "'" +
							"union select strt_id from CP_MK_ADR_STREET_" + PROVINCE_ID.substring(0,2) + " where POST_CD = '" + POST_CD + "'" +
									" ) c";
		}
		
		if(!RSDNBLDG_NAME.equals("null") && RSDNBLDG_NAME.length()>0){
			sqlfrom = sqlfrom + " ,(select distinct strt_id from CP_MK_ADR_BLDGRSDNS_" + PROVINCE_ID.substring(0,2) + "  where RSDNBLDG_NAME like '%" + RSDNBLDG_NAME + "%' ) d";
		}
		
		if(!ORG_NAME.equals("null") && ORG_NAME.length()>0){
			sqlfrom = sqlfrom + " , (select distinct strt_id from CP_MK_ORG_ORGANIZATION_" + PROVINCE_ID.substring(0,2) + " where ORG_NAME like '%" + ORG_NAME + "%' ) e";
		}
		
		if(getSessionUser().getRulLevel()==20){
			sqlfrom = sqlfrom + " ," +
			" (select STRT_ID from CP_WH_RL_PG_ST_" + PROVINCE_ID.substring(0,2) + "  where DM_PK_CODE = '" + getSessionUser().getUsDepartmentOffice() + "' " +
					"union select strt_id from CP_MK_ADR_STREET_" + PROVINCE_ID.substring(0,2) + " where  DATA_USER = '" + getSessionUser().getUsLoginId() + "'" +
							" ) f";
	  }
		
		
		String sqlwhere=" where a.district_code=b.dist_cd";
		String sqlorder=" order by decode(b.DATA_FLAG,'A',0,'A',0,'9',1,'8',2,'7',3,'6',4,5),b.DATA_DATE desc,b.DIST_CD,b.strt1_name";
		
		if(!ALL_DATA_FLAG.equals("null") && ALL_DATA_FLAG.length()>0){
			if(ALL_DATA_FLAG.equals("jdds")){
				sqlwhere = sqlwhere + " and b.DATA_FLAG in ('6','7','8') ";
			}else if(ALL_DATA_FLAG.equals("xqds")){
				sqlwhere = sqlwhere + " and b.STRT_ID in (select strt_id from CP_MK_ADR_BLDGRSDNS_" + PROVINCE_ID.substring(0,2) + " c where c.DATA_FLAG in ('6','7','8') )";
			}else if(ALL_DATA_FLAG.equals("jgds")){
				sqlwhere = sqlwhere + " and b.STRT_ID in (select strt_id from CP_MK_ORG_ORGANIZATION_" + PROVINCE_ID.substring(0,2) + " c where c.DATA_FLAG in ('6','7','8') )";
			}else if(ALL_DATA_FLAG.equals("jdjj")){
				sqlwhere = sqlwhere + " and b.DATA_FLAG in ('9','A') ";
			}else if(ALL_DATA_FLAG.equals("xqjj")){
				sqlwhere = sqlwhere + " and b.STRT_ID in (select strt_id from CP_MK_ADR_BLDGRSDNS_" + PROVINCE_ID.substring(0,2) + " c where c.DATA_FLAG in ('9','A') )";
			}else if(ALL_DATA_FLAG.equals("jgjj")){
				sqlwhere = sqlwhere + " and b.STRT_ID in (select strt_id from CP_MK_ORG_ORGANIZATION_" + PROVINCE_ID.substring(0,2) + " c where c.DATA_FLAG in ('9','A') )";
			}
		}
		
		if(!POST_CD.equals("null") && POST_CD.length()>0){
			sqlwhere = sqlwhere + " and b.strt_id=c.strt_id";
		}
		
		if(getSessionUser().getRulLevel()==20){
			sqlwhere = sqlwhere + " and b.strt_id=f.strt_id " ;
		}
		
		if(!RSDNBLDG_NAME.equals("null") && RSDNBLDG_NAME.length()>0){
			sqlwhere = sqlwhere + " and b.STRT_ID=d.strt_id";
		}
		
		if(!ORG_NAME.equals("null") && ORG_NAME.length()>0){
			sqlwhere = sqlwhere + " and b.STRT_ID=e.strt_id";
		}
		
		if(!STREET_NAME.equals("null") && STREET_NAME.length()>0){
			sqlwhere = sqlwhere + " and  b.strt1_name||b.strt2_name||b.strt3_name||b.strt4_name||b.strt5_name like '%" + STREET_NAME + "%' ";
		}
		
		if(!COUNTY_ID.equals("null") && COUNTY_ID.length()>0){
			sqlwhere = sqlwhere + " and a.district_code='" + COUNTY_ID + "'";
		}else if(!CITY_ID.equals("null") && CITY_ID.length()>0){
			if(CITY_ID.substring(4, 6).equals("00")){
				sqlwhere = sqlwhere + " and a.district_code like '" + CITY_ID.substring(0, 4) + "%'";
			}else{
				sqlwhere = sqlwhere + " and a.district_code like '" + CITY_ID + "%'";
			}
		}else if(!PROVINCE_ID.equals("null") && PROVINCE_ID.length()>0){
			sqlwhere = sqlwhere + " and a.district_code like '" + PROVINCE_ID.substring(0, 2) + "%'";
		}

		sql=sql+sqlfrom+sqlwhere+sqlorder;
		sqlCount=sqlCount+sqlfrom+sqlwhere;
		if(pageNo!=0){
			page.setPageNo(pageNo);
		}
		page=addrQueryDao.getBeanQueryStreets(sql, sqlCount, page);
		return "json";
	}
	
	@SuppressWarnings("unchecked")
	public String querystreetsdium() throws Exception {		
		
		if(!userflag()){return "json";}
		
		/*
		String sql="select a.strt_id,a.dist_cd,a.TOTAL_DISTRICT_NAME,a.TOTAL_STREET_NAME,a.TOTAL_DIUMENPAI ";
		String sqlCount="select count(*) ";
		String sqlfrom="from (select "+
		" max(g.PROVINCE_NAME||g.CITY_NAME||g.COUNTY_NAME) TOTAL_DISTRICT_NAME, "+
		" max(h.STRT1_NAME||h.STRT2_NAME||h.STRT3_NAME||h.STRT4_NAME||h.STRT5_NAME) TOTAL_STREET_NAME, "+
		" wmsys.wm_concat(data_flag_name) TOTAL_DIUMENPAI,h.dist_cd,f.strt_id from "+
		" ( "+
		" select distinct prefix||start_num||suffix data_flag_name,e.strt_id from  "+
		" ( "+
		" select c.dorplt_id  "+
		" from cp_mk_adr_bldg_" + PROVINCE_ID.substring(0,2) + " c, "+
		" ( "+
		" select a.dorplt_id from cp_mk_adr_bldg_" + PROVINCE_ID.substring(0,2) + " a,cp_wh_rl_pg_st_" + PROVINCE_ID.substring(0,2) + " b "+
		" where  "+
		" a.strt_id = b.strt_id "+
		" and a.emp_nbr >= b.start_num  "+
		" and a.emp_nbr <= b.end_num "+
		" and nvl(a.PREFIX,'null')=nvl(b.PREFIX,'null') "+
		" and nvl(a.SUFFIX,'null')=nvl(b.SUFFIX,'null') "+
		" and "+
		" ( "+
		" (b.flag = 4) "+
		" or "+
		" (b.flag = 5 and mod(a.emp_nbr,2)=1) "+
		" or "+
		" (b.flag = 6 and mod(a.emp_nbr,2)=0) "+
		" ) "+
		" ) a "+
		" where c.dorplt_id = a.dorplt_id(+) and a.dorplt_id is null "+
		" ) d,CP_MK_ORG_ORGANIZATION_" + PROVINCE_ID.substring(0,2) + " e "+
		" where d.dorplt_id = e.dorplt_id  and d.dorplt_id is not null "+
		" union "+
		" select distinct prefix||start_num||suffix data_flag_name,e.strt_id from  "+
		" ( "+
		" select c.dorplt_id  "+
		" from cp_mk_adr_bldg_" + PROVINCE_ID.substring(0,2) + " c, "+
		" ( "+
		" select a.dorplt_id from cp_mk_adr_bldg_" + PROVINCE_ID.substring(0,2) + " a,cp_wh_rl_pg_st_" + PROVINCE_ID.substring(0,2) + " b "+
		" where  "+
		" a.strt_id = b.strt_id "+
		" and a.emp_nbr >= b.start_num  "+
		" and a.emp_nbr <= b.end_num "+
		" and nvl(a.PREFIX,'null')=nvl(b.PREFIX,'null') "+
		" and nvl(a.SUFFIX,'null')=nvl(b.SUFFIX,'null') "+
		" and "+
		" ( "+
		" (b.flag = 4) "+
		" or "+
		" (b.flag = 5 and mod(a.emp_nbr,2)=1) "+
		" or "+
		" (b.flag = 6 and mod(a.emp_nbr,2)=0) "+
		" ) "+
		" ) a "+
		" where c.dorplt_id = a.dorplt_id(+) and a.dorplt_id is null "+
		" ) d,CP_MK_ADR_BLDGRSDNS_" + PROVINCE_ID.substring(0,2) + " e "+
		" where d.dorplt_id = e.dorplt_id  and d.dorplt_id is not null "+
		" ) f,CP_BASE_ORG_DISTRICT g,CP_MK_ADR_STREET_" + PROVINCE_ID.substring(0,2) + " h "+
		" where g.district_code=h.dist_cd and f.strt_id = h.strt_id "+
		" group by h.dist_cd,f.strt_id) a ";
		*/
		
		/*
		String sql="select max(a.strt_id),max(a.dist_cd),max(a.TOTAL_DISTRICT_NAME),max(a.TOTAL_STREET_NAME),max(a.TOTAL_DIUMENPAI) ";
		String sqlCount="select count(*)  ";
		String sqlfrom=" from (select "+
		" g.PROVINCE_NAME||g.CITY_NAME||g.COUNTY_NAME TOTAL_DISTRICT_NAME, "+
		" h.STRT1_NAME||h.STRT2_NAME||h.STRT3_NAME||h.STRT4_NAME||h.STRT5_NAME TOTAL_STREET_NAME, "+
		" wmsys.wm_concat(data_flag_name) over(PARTITION BY h.dist_cd, f.strt_id ORDER BY f.prefix,f.suffix,f.start_num) TOTAL_DIUMENPAI,h.dist_cd,f.strt_id from "+
		" ( "+
		" select distinct prefix||start_num||suffix data_flag_name,prefix,start_num,suffix,e.strt_id from  "+
		" ( "+
		" select c.dorplt_id  "+
		" from cp_mk_adr_bldg_" + PROVINCE_ID.substring(0,2) + " c, "+
		" ( "+
		" select a.dorplt_id from cp_mk_adr_bldg_" + PROVINCE_ID.substring(0,2) + " a,cp_wh_rl_pg_st_" + PROVINCE_ID.substring(0,2) + " b "+
		" where  "+
		" a.strt_id = b.strt_id "+
		" and a.emp_nbr >= b.start_num  "+
		" and a.emp_nbr <= b.end_num "+
		" and nvl(a.PREFIX,'null')=nvl(b.PREFIX,'null') "+
		" and nvl(a.SUFFIX,'null')=nvl(b.SUFFIX,'null') "+
		" and "+
		" ( "+
		" (b.flag = 4) "+
		" or "+
		" (b.flag = 5 and mod(a.emp_nbr,2)=1) "+
		" or "+
		" (b.flag = 6 and mod(a.emp_nbr,2)=0) "+
		" ) "+
		" ) a "+
		" where c.dorplt_id = a.dorplt_id(+) and a.dorplt_id is null "+
		" ) d,CP_MK_ORG_ORGANIZATION_" + PROVINCE_ID.substring(0,2) + " e "+
		" where d.dorplt_id = e.dorplt_id  and d.dorplt_id is not null "+
		" union "+
		" select distinct prefix||start_num||suffix data_flag_name,prefix,start_num,suffix,e.strt_id from  "+
		" ( "+
		" select c.dorplt_id  "+
		" from cp_mk_adr_bldg_" + PROVINCE_ID.substring(0,2) + " c, "+
		" ( "+
		" select a.dorplt_id from cp_mk_adr_bldg_" + PROVINCE_ID.substring(0,2) + " a,cp_wh_rl_pg_st_" + PROVINCE_ID.substring(0,2) + " b "+
		" where  "+
		" a.strt_id = b.strt_id "+
		" and a.emp_nbr >= b.start_num  "+
		" and a.emp_nbr <= b.end_num "+
		" and nvl(a.PREFIX,'null')=nvl(b.PREFIX,'null') "+
		" and nvl(a.SUFFIX,'null')=nvl(b.SUFFIX,'null') "+
		" and "+
		" ( "+
		" (b.flag = 4) "+
		" or "+
		" (b.flag = 5 and mod(a.emp_nbr,2)=1) "+
		" or "+
		" (b.flag = 6 and mod(a.emp_nbr,2)=0) "+
		" ) "+
		" ) a "+
		" where c.dorplt_id = a.dorplt_id(+) and a.dorplt_id is null "+
		" ) d,CP_MK_ADR_BLDGRSDNS_" + PROVINCE_ID.substring(0,2) + " e "+
		" where d.dorplt_id = e.dorplt_id  and d.dorplt_id is not null "+
		" ) f,CP_BASE_ORG_DISTRICT g,CP_MK_ADR_STREET_" + PROVINCE_ID.substring(0,2) + " h "+
		" where g.district_code=h.dist_cd and f.strt_id = h.strt_id "+
		" ) a ";
		*/
		
		String sql="select max(a.strt_id),max(a.dist_cd),max(a.TOTAL_DISTRICT_NAME),max(a.TOTAL_STREET_NAME),max(to_char(substr(a.TOTAL_DIUMENPAI,1,4000))) ";
		String sqlCount="select count(*)  ";
		String sqlfrom=" from (select "+
		" g.PROVINCE_NAME||g.CITY_NAME||g.COUNTY_NAME TOTAL_DISTRICT_NAME, "+
		" h.STRT1_NAME||h.STRT2_NAME||h.STRT3_NAME||h.STRT4_NAME||h.STRT5_NAME TOTAL_STREET_NAME, "+
		" wmsys.wm_concat(data_flag_name) over(PARTITION BY h.dist_cd, f.strt_id ORDER BY f.prefix,f.suffix,f.start_num) TOTAL_DIUMENPAI,h.dist_cd,f.strt_id from "+
		" ( "+
		" select distinct prefix||start_num||suffix data_flag_name,prefix,start_num,suffix,e.strt_id from  "+
		" ( "+
		" select c.dorplt_id  "+
		" from cp_mk_adr_bldg_" + PROVINCE_ID.substring(0,2) + " c, "+
		" ( "+
		" select a.dorplt_id from cp_mk_adr_bldg_" + PROVINCE_ID.substring(0,2) + " a,cp_wh_rl_pg_st_" + PROVINCE_ID.substring(0,2) + " b "+
		" where  "+
		" a.strt_id = b.strt_id "+
		" and a.emp_nbr >= b.start_num  "+
		" and a.emp_nbr <= b.end_num "+
		" and nvl(a.PREFIX,'null')=nvl(b.PREFIX,'null') "+
		" and nvl(a.SUFFIX,'null')=nvl(b.SUFFIX,'null') "+
		" and "+
		" ( "+
		" (b.flag = 4) "+
		" or "+
		" (b.flag = 5 and mod(a.emp_nbr,2)=1) "+
		" or "+
		" (b.flag = 6 and mod(a.emp_nbr,2)=0) "+
		" ) "+
		" ) a "+
		" where c.dorplt_id = a.dorplt_id(+) and a.dorplt_id is null and c.emp_nbr > 0 "+
		" ) d,CP_MK_ORG_ORGANIZATION_" + PROVINCE_ID.substring(0,2) + " e "+
		" where d.dorplt_id = e.dorplt_id  and d.dorplt_id is not null "+
		" union "+
		" select distinct prefix||start_num||suffix data_flag_name,prefix,start_num,suffix,e.strt_id from  "+
		" ( "+
		" select c.dorplt_id  "+
		" from cp_mk_adr_bldg_" + PROVINCE_ID.substring(0,2) + " c, "+
		" ( "+
		" select a.dorplt_id from cp_mk_adr_bldg_" + PROVINCE_ID.substring(0,2) + " a,cp_wh_rl_pg_st_" + PROVINCE_ID.substring(0,2) + " b "+
		" where  "+
		" a.strt_id = b.strt_id "+
		" and a.emp_nbr >= b.start_num  "+
		" and a.emp_nbr <= b.end_num "+
		" and nvl(a.PREFIX,'null')=nvl(b.PREFIX,'null') "+
		" and nvl(a.SUFFIX,'null')=nvl(b.SUFFIX,'null') "+
		" and "+
		" ( "+
		" (b.flag = 4) "+
		" or "+
		" (b.flag = 5 and mod(a.emp_nbr,2)=1) "+
		" or "+
		" (b.flag = 6 and mod(a.emp_nbr,2)=0) "+
		" ) "+
		" ) a "+
		" where c.dorplt_id = a.dorplt_id(+) and a.dorplt_id is null  and c.emp_nbr > 0 "+
		" ) d,CP_MK_ADR_BLDGRSDNS_" + PROVINCE_ID.substring(0,2) + " e "+
		" where d.dorplt_id = e.dorplt_id  and d.dorplt_id is not null "+
		" ) f,CP_BASE_ORG_DISTRICT g,CP_MK_ADR_STREET_" + PROVINCE_ID.substring(0,2) + " h "+
		" where g.district_code=h.dist_cd and f.strt_id = h.strt_id "+
		" ) a ";
		
		if(getSessionUser().getRulLevel()==20){
			sqlfrom = sqlfrom + " ," +
			" (select STRT_ID from CP_WH_RL_PG_ST_" + PROVINCE_ID.substring(0,2) + "  where DM_PK_CODE = '" + getSessionUser().getUsDepartmentOffice() + "' " +
					"union select strt_id from CP_MK_ADR_STREET_" + PROVINCE_ID.substring(0,2) + " where  DATA_USER = '" + getSessionUser().getUsLoginId() + "'" +
							" ) f";
	  }
		
		
		String sqlwhere=" where 1=1 ";
		String sqlorder=" group by a.dist_cd, a.strt_id ";
		
		
		if(getSessionUser().getRulLevel()==20){
			sqlwhere = sqlwhere + " and a.strt_id=f.strt_id " ;
		}

		if(!STREET_NAME.equals("null") && STREET_NAME.length()>0){
			sqlwhere = sqlwhere + " and  a.TOTAL_STREET_NAME like '%" + STREET_NAME + "%' ";
		}
		
		if(!COUNTY_ID.equals("null") && COUNTY_ID.length()>0){
			sqlwhere = sqlwhere + " and a.dist_cd='" + COUNTY_ID + "'";
		}else if(!CITY_ID.equals("null") && CITY_ID.length()>0){
			if(CITY_ID.substring(4, 6).equals("00")){
				sqlwhere = sqlwhere + " and a.dist_cd like '" + CITY_ID.substring(0, 4) + "%'";
			}else{
				sqlwhere = sqlwhere + " and a.dist_cd like '" + CITY_ID + "%'";
			}
		}else if(!PROVINCE_ID.equals("null") && PROVINCE_ID.length()>0){
			sqlwhere = sqlwhere + " and a.dist_cd like '" + PROVINCE_ID.substring(0, 2) + "%'";
		}

		sql=sql+sqlfrom+sqlwhere+sqlorder;
		sqlCount=sqlCount+ " from ( " + sql + " )";
		
		page.setPageSize(50);
		
		if(pageNo!=0){
			page.setPageNo(pageNo);
		}
		page=addrQueryDao.getBeanQueryStreetsdium(sql, sqlCount, page);
		return "json";
	}
	
	
	@SuppressWarnings("unchecked")
	public String querytwstr() throws Exception {		
		
		if(!userflag()){return "json";}
		
		String sql="select distinct b.STRT_ID,b.dist_cd,a.PROVINCE_NAME||a.CITY_NAME||a.COUNTY_NAME,b.strt1_name ";
		String sqlCount="select count(*) ";
		String sqlfrom=" from CP_BASE_ORG_DISTRICT a,CP_MK_ADR_STREET_" + PROVINCE_ID.substring(0,2) + " b, " +
				"(select dist_cd,strt1_name from CP_MK_ADR_STREET_" + PROVINCE_ID.substring(0,2) + " d ";
		
		
		String sqlwhere=" where strt2_name is not null";
		
		if(!STREET_NAME.equals("null") && STREET_NAME.length()>0){
			sqlwhere = sqlwhere + " and  d.strt1_name like '%" + STREET_NAME + "%' ";
		}
		
		if(!COUNTY_ID.equals("null") && COUNTY_ID.length()>0){
			sqlwhere = sqlwhere + " and d.dist_cd='" + COUNTY_ID + "'";
		}else if(!CITY_ID.equals("null") && CITY_ID.length()>0){
			if(CITY_ID.substring(4, 6).equals("00")){
				sqlwhere = sqlwhere + " and d.dist_cd like '" + CITY_ID.substring(0, 4) + "%'";
			}else{
				sqlwhere = sqlwhere + " and d.dist_cd like '" + CITY_ID + "%'";
			}
		}else if(!PROVINCE_ID.equals("null") && PROVINCE_ID.length()>0){
			sqlwhere = sqlwhere + " and d.dist_cd like '" + PROVINCE_ID.substring(0, 2) + "%'";
		}
		
		sqlwhere = sqlwhere + " group by dist_cd,strt1_name having count(*)>1 ) c ";
		
		sqlwhere= sqlwhere +  "  where a.district_code=b.dist_cd and b.strt2_name is null and b.strt1_name=c.strt1_name and b.dist_cd=c.dist_cd";
		
		String sqlorder=" order by b.strt1_name";

		sql=sql+sqlfrom+sqlwhere+sqlorder;
		sqlCount=sqlCount+sqlfrom+sqlwhere;
		if(pageNo!=0){
			page.setPageNo(pageNo);
		}
		page=addrQueryDao.getBeanQueryTwstr(sql, sqlCount, page);
		return "json";
	}
	
	@SuppressWarnings("unchecked")
	public String querytwstrd() throws Exception {		
		
		if(!userflag()){return "json";}
		
		
		String sql="select b.STRT_ID,b.DIST_CD,a.PROVINCE_NAME||a.CITY_NAME||a.COUNTY_NAME,b.STRT1_NAME||b.STRT2_NAME||b.STRT3_NAME||b.STRT4_NAME||b.STRT5_NAME,b.DATA_FLAG,b.POST_CD ";
		String sqlCount="select count(*) ";
		String sqlfrom=" from CP_BASE_ORG_DISTRICT a,CP_MK_ADR_STREET_" + DIST_CD.substring(0,2) + " b ";

		String sqlwhere=" where a.district_code=b.dist_cd";
		String sqlorder=" order by nvl(b.strt2_name,0)";
				
		if(!STREET_NAME.equals("null") && STREET_NAME.length()>0){
			sqlwhere = sqlwhere + " and  b.strt1_name= '" + STREET_NAME + "' ";
		}
		
		if(!DIST_CD.equals("null") && DIST_CD.length()>0){
			sqlwhere = sqlwhere + " and b.DIST_CD='" + DIST_CD + "'";
		}

		sql=sql+sqlfrom+sqlwhere+sqlorder;
		sqlCount=sqlCount+sqlfrom+sqlwhere;
		
		page.setPageSize(99999);
		if(pageNo!=0){
			page.setPageNo(pageNo);
		}
		page=addrQueryDao.getBeanQueryStreets(sql, sqlCount, page);
		return "json";
	}
	
	@SuppressWarnings("unchecked")
	public String querylogs() throws Exception {		
		
		if(!userflag()){return "json";}
		
		String sql="select b.STRT_ID,b.DIST_CD,a.PROVINCE_NAME||a.CITY_NAME||a.COUNTY_NAME,b.STRT_NAME,b.RSDNBLDG_NAME,b.ORG_NAME,b.DATA_FLAG,to_char(b.DATA_DATE,'yyyymmdd hh24:mm:ss')," +
		" CASE WHEN (length(b.DATA_USER) > 0 )  THEN (select MAX(c.US_NAME) from CP_PMN_USER c  where c.US_LOGIN_ID = b.DATA_USER) END AS DATA_USER_NAME ";
		String sqlCount="select count(*) ";
		String sqlfrom=" from CP_BASE_ORG_DISTRICT a,CP_MK_ADR_LOG b ";
		String sqlwhere=" where a.district_code=b.dist_cd ";
		String sqlorder=" order by b.DIST_CD,b.STRT_ID,b.RSDNBLDG_ID,b.ORG_ID,b.DATA_DATE desc";
		
		if(!RSDNBLDG_NAME.equals("null") && RSDNBLDG_NAME.length()>0){
			sqlwhere = sqlwhere + " and b.RSDNBLDG_NAME like '%" + RSDNBLDG_NAME + "%' ";
		}
		
		if(!ORG_NAME.equals("null") && ORG_NAME.length()>0){
			sqlwhere = sqlwhere + " and b.ORG_NAME like '%" + ORG_NAME + "%' ";
		}
		
		if(!STREET_NAME.equals("null") && STREET_NAME.length()>0){
			sqlwhere = sqlwhere + " and b.STRT_NAME like '%" + STREET_NAME + "%' ";
		}
		
		if(!COUNTY_ID.equals("null") && COUNTY_ID.length()>0){
			sqlwhere = sqlwhere + " and b.DIST_CD='" + COUNTY_ID + "'";
		}else if(!CITY_ID.equals("null") && CITY_ID.length()>0){
			if(CITY_ID.substring(4, 6).equals("00")){
				sqlwhere = sqlwhere + " and b.DIST_CD like '" + CITY_ID.substring(0, 4) + "%'";
			}else{
				sqlwhere = sqlwhere + " and b.DIST_CD like '" + CITY_ID + "%'";
			}
		}else if(!PROVINCE_ID.equals("null") && PROVINCE_ID.length()>0){
			sqlwhere = sqlwhere + " and b.DIST_CD like '" + PROVINCE_ID.substring(0, 2) + "%'";
		}

		sql=sql+sqlfrom+sqlwhere+sqlorder;
		sqlCount=sqlCount+sqlfrom+sqlwhere;
		if(pageNo!=0){
			page.setPageNo(pageNo);
		}
		page=addrQueryDao.getBeanQueryLogs(sql, sqlCount, page);
		return "json";
	}
	
	@SuppressWarnings("unchecked")
	public String querylogsbyid() throws Exception {		
		
		if(!userflag()){return "json";}
		
		String sql="select b.STRT_ID,b.DIST_CD,a.PROVINCE_NAME||a.CITY_NAME||a.COUNTY_NAME,b.STRT_NAME,b.RSDNBLDG_NAME,b.ORG_NAME,b.DATA_FLAG,to_char(b.DATA_DATE,'yyyymmdd hh24:mm:ss')," +
		" CASE WHEN (length(b.DATA_USER) > 0 )  THEN (select MAX(c.US_NAME) from CP_PMN_USER c  where c.US_LOGIN_ID = b.DATA_USER) END AS DATA_USER_NAME ";
		String sqlCount="select count(*) ";
		String sqlfrom=" from CP_BASE_ORG_DISTRICT a,CP_MK_ADR_LOG b ";
		String sqlwhere=" where a.district_code=b.dist_cd ";
		String sqlorder=" order by b.DIST_CD,b.STRT_ID,b.RSDNBLDG_ID,b.ORG_ID,b.DATA_DATE desc";
		
		
		
		if(!ALL_NAME.equals("null") && ALL_NAME.length()>0){
			sqlwhere = sqlwhere + " and (b.strt_name like '%" + ALL_NAME + "%' " +
					"or b.ORG_NAME like '%" + ALL_NAME + "%'  "+
					"or b.RSDNBLDG_NAME like '%" + ALL_NAME + "%'  )";
		}
		
		if(!DIST_CD.equals("null") && DIST_CD.length()>0){
			sqlwhere = sqlwhere + " and b.DIST_CD='" + DIST_CD + "'";
		}
		
		if(!STRT_ID.equals("null") && STRT_ID.length()>0){
			sqlwhere = sqlwhere + " and b.STRT_ID='" + STRT_ID + "'";
		}else if(!RSDNBLDG_ID.equals("null") && RSDNBLDG_ID.length()>0){
			sqlwhere = sqlwhere + " and b.RSDNBLDG_ID='" + RSDNBLDG_ID + "'";
		}else if(!ORG_ID.equals("null") && ORG_ID.length()>0){
			sqlwhere = sqlwhere + " and b.ORG_ID='" + ORG_ID + "'";
		}

		sql=sql+sqlfrom+sqlwhere+sqlorder;
		sqlCount=sqlCount+sqlfrom+sqlwhere;
		if(pageNo!=0){
			page.setPageNo(pageNo);
		}
		page=addrQueryDao.getBeanQueryLogsbyid(sql, sqlCount, page);
		return "json";
	}
	
	@SuppressWarnings("unchecked")
	public String querybos() throws Exception {		
		
		if(!userflag()){return "json";}
		
		String sqlCount="select count(*) ";
		String sqlorder=" order by decode(a.DATA_FLAG,'A',0,'9',1,'8',2,'7',3,'6',4,5),TOTAL_BLDG_NAME,RSDNBLDG_NAME,decode(ORG_NAME,null,'0',ORG_NAME),a.DATA_DATE desc";
		
		String sql="select * ";
		
		String sqlfrom=" from ( select 'X' as BO_FLAG,a.RSDNBLDG_ID,0,a.DIST_CD,a.STRT_ID,a.RSDNBLDG_NAME,'' as ORG_NAME, "
		+ " a.PREFIX || ' @@ ' || a.START_NUM || ' @@ ' || a.END_NUM || ' @@ ' ||  a.SUFFIX || ' @@ ' ||  a.NUM_FLAG AS TOTAL_BLDG_NAME ,"
		+ " a.data_flag ,"
		+ " decode(a.RSDNBLDG_FLAG,'0','小区','1','建筑物','其他') as RSDNBLDG_FLAG_NAME ,a.data_date from CP_MK_ADR_BLDGRSDNS_" + DIST_CD.substring(0,2) + " a ";
		String sqlwhere=" where 1=1 ";
		
		
		if(!STRT_ID.equals("null") && STRT_ID.length()>0){
			sqlwhere = sqlwhere + " and a.STRT_ID=" + STRT_ID ;
		}
		
		if(!RSDNBLDG_NAME.equals("null") && RSDNBLDG_NAME.length()>0){
			sqlwhere = sqlwhere + " and (a.RSDNBLDG_NAME like '%" + RSDNBLDG_NAME + "%' )";
		}
		
		if(!TOTAL_DIUMENPAI.equals("null") && TOTAL_DIUMENPAI.length()>0){
			sqlwhere = sqlwhere + " and (a.prefix||a.start_num||a.suffix = '" + TOTAL_DIUMENPAI + "' or INSTR('" + TOTAL_DIUMENPAI + "',a.prefix||a.start_num||a.suffix)>0 )";
		}
		
		if(!ALL_DATA_FLAG.equals("null") && ALL_DATA_FLAG.length()>0){
			if(ALL_DATA_FLAG.equals("xqds")){
				sqlwhere = sqlwhere + " and a.DATA_FLAG in ('6','7','8') ";
			}else if(ALL_DATA_FLAG.equals("jgds")){
				sqlwhere = sqlwhere + " and a.DATA_FLAG in ('6','7','8') ";
			}else if(ALL_DATA_FLAG.equals("xqjj")){
				sqlwhere = sqlwhere + " and a.DATA_FLAG in ('9','A') ";
			}else if(ALL_DATA_FLAG.equals("jgjj")){
				sqlwhere = sqlwhere + " and a.DATA_FLAG in ('9','A') ";
			}
		}
		
		String sqlfrom2=" union all select 'J' as BO_FLAG,0,a.ORG_ID,a.DIST_CD,a.STRT_ID,"
			+ " '' ,"
			//+ "CASE WHEN (length(a.RSDNBLDG_ID) > 0 )  THEN (select MAX(b.RSDNBLDG_NAME) from CP_MK_ADR_BLDGRSDNS_" + DIST_CD.substring(0,2) + " b  where b.RSDNBLDG_ID = a.RSDNBLDG_ID) END AS RSDNBLDG_NAME ,"
			+ " replace(replace(replace(a.ORG_NAME,'(','（'),')','）'),'\"','“'), "
			+ " a.PREFIX || ' @@ ' || a.START_NUM || ' @@ ' || a.END_NUM || ' @@ ' ||  a.SUFFIX || ' @@ ' ||  a.NUM_FLAG AS TOTAL_BLDG_NAME ,"
			+ " a.data_flag ,"
			+ " '机构',a.data_date from CP_MK_ORG_ORGANIZATION_" + DIST_CD.substring(0,2) + " a  ";
		String sqlwhere2=" where 1=1 ";
		
			
		if(!STRT_ID.equals("null") && STRT_ID.length()>0){
			sqlwhere2 = sqlwhere2 + " and a.STRT_ID=" + STRT_ID ;
		}
		
		if(!ORG_NAME.equals("null") && ORG_NAME.length()>0){
			sqlwhere2 = sqlwhere2 + " and (a.ORG_NAME like '%" + ORG_NAME + "%' )";
		}
		
		if(!TOTAL_DIUMENPAI.equals("null") && TOTAL_DIUMENPAI.length()>0){
			sqlwhere2 = sqlwhere2 + " and (a.prefix||a.start_num||a.suffix = '" + TOTAL_DIUMENPAI + "' or INSTR('" + TOTAL_DIUMENPAI + "',a.prefix||a.start_num||a.suffix)>0 )";
		}
		
		if(!ALL_DATA_FLAG.equals("null") && ALL_DATA_FLAG.length()>0){
			if(ALL_DATA_FLAG.equals("xqds")){
				sqlwhere2 = sqlwhere2 + " and a.DATA_FLAG in ('6','7','8') ";
			}else if(ALL_DATA_FLAG.equals("jgds")){
				sqlwhere2 = sqlwhere2 + " and a.DATA_FLAG in ('6','7','8') ";
			}else if(ALL_DATA_FLAG.equals("xqjj")){
				sqlwhere2 = sqlwhere2 + " and a.DATA_FLAG in ('9','A') ";
			}else if(ALL_DATA_FLAG.equals("jgjj")){
				sqlwhere2 = sqlwhere2 + " and a.DATA_FLAG in ('9','A') ";
			}
		}
		
		sqlwhere2 = sqlwhere2 + " ) a";
			
		
		sql=sql+sqlfrom+sqlwhere+sqlfrom2+sqlwhere2+sqlorder;
		sqlCount=sqlCount+sqlfrom+sqlwhere+sqlfrom2+sqlwhere2;
		if(pageNo!=0){
			page.setPageNo(pageNo);
		}
		page=addrQueryDao.getBeanQueryBos(sql, sqlCount, page);
		return "json";
	}
	
	@SuppressWarnings("unchecked")
	public String querybldgrsdns() throws Exception {		
		
		if(!userflag()){return "json";}
		
		String sql="select a.RSDNBLDG_ID,a.DIST_CD,a.STRT_ID,a.DORPLT_ID,a.RSDNBLDG_NAME,a.RSDNBLDG_ABBR, "
		+ " a.PREFIX || ' @@ ' || a.START_NUM || ' @@ ' || a.END_NUM || ' @@ ' ||  a.SUFFIX || ' @@ ' ||  a.NUM_FLAG AS TOTAL_BLDG_NAME ,"
		+ " a.data_flag ,"
		+ " decode(a.RSDNBLDG_FLAG,'0','小区','1','建筑物','其他') as RSDNBLDG_FLAG_NAME ";
		String sqlCount="select count(*) ";
		String sqlfrom=" from CP_MK_ADR_BLDGRSDNS_" + DIST_CD.substring(0,2) + " a ";
		String sqlwhere=" where 1=1 ";
		String sqlorder=" order by decode(a.DATA_FLAG,'A',0,'9',1,'8',2,'7',3,'6',4,5),a.DATA_DATE desc,a.RSDNBLDG_NAME ";
		
		if(!STRT_ID.equals("null") && STRT_ID.length()>0){
			sqlwhere = sqlwhere + " and a.STRT_ID=" + STRT_ID ;
		}
		
		if(!RSDNBLDG_NAME.equals("null") && RSDNBLDG_NAME.length()>0){
			sqlwhere = sqlwhere + " and (a.RSDNBLDG_NAME like '%" + RSDNBLDG_NAME + "%' )";
		}
		
		sql=sql+sqlfrom+sqlwhere+sqlorder;
		sqlCount=sqlCount+sqlfrom+sqlwhere;
		if(pageNo!=0){
			page.setPageNo(pageNo);
		}
		page=addrQueryDao.getBeanQueryBldgrsdns(sql, sqlCount, page);
		return "json";
	}
	
	@SuppressWarnings("unchecked")
	public String queryorganizations() throws Exception {		
		
		if(!userflag()){return "json";}
		
		String sql="select a.ORG_ID,a.DIST_CD,a.STRT_ID,replace(replace(replace(a.ORG_NAME,'(','（'),')','）'),'\"','“'),a.ORG_ABBR_NAME,a.ORG_ABBR, "
		+ " a.PREFIX || ' @@ ' || a.START_NUM || ' @@ ' || a.END_NUM || ' @@ ' ||  a.SUFFIX || ' @@ ' ||  a.NUM_FLAG AS TOTAL_BLDG_NAME ,"
		+ " a.data_flag ";
		//+ " CASE WHEN (length(a.RSDNBLDG_ID) > 0 )  THEN (select MAX(b.RSDNBLDG_NAME) from CP_MK_ADR_BLDGRSDNS_" + DIST_CD.substring(0,2) + " b  where b.RSDNBLDG_ID = a.RSDNBLDG_ID) END AS RSDNBLDG_NAME ";
		String sqlCount="select count(*) ";
		String sqlfrom=" from CP_MK_ORG_ORGANIZATION_" + DIST_CD.substring(0,2) + " a  ";
		String sqlwhere=" where 1=1 ";
		
		String sqlorder=" order by decode(a.DATA_FLAG,'A',0,'9',1,'8',2,'7',3,'6',4,5),a.DATA_DATE desc,a.ORG_NAME ";
		
		if(!STRT_ID.equals("null") && STRT_ID.length()>0){
			sqlwhere = sqlwhere + " and a.STRT_ID=" + STRT_ID ;
		}
		
		if(!ORG_NAME.equals("null") && ORG_NAME.length()>0){
			sqlwhere = sqlwhere + " and (a.ORG_NAME like '%" + ORG_NAME + "%' )";
		}
		
		sql=sql+sqlfrom+sqlwhere+sqlorder;
		sqlCount=sqlCount+sqlfrom+sqlwhere;
		if(pageNo!=0){
			page.setPageNo(pageNo);
		}
		page=addrQueryDao.getBeanQueryOrganizations(sql, sqlCount, page);
		return "json";
	}
	
	public String queryflagnum() throws Exception {		
		
		if(!userflag()){return "json";}
		
		ALL_DATA_NUMBER = "0";
		if(!SSLX.equals("null") && SSLX.length()>0){
			String sql=" ";
			String sqlfrom= "";
			String sqlwhere=" where 1=1 ";
			
			if(SSLX.equals("vs2sr")){
				sql = " select count(*)  ";
				sqlfrom = " from CP_MK_ADR_STREET_" + PROVINCE_ID.substring(0, 2) + " a";
				sqlwhere = sqlwhere + " and DATA_FLAG in ('6','7','8') ";
			}else if(SSLX.equals("vs3sr")){
				sql = " select count(*)  ";
				sqlfrom = " from CP_MK_ADR_STREET_" + PROVINCE_ID.substring(0, 2) + " a";
				sqlwhere = sqlwhere + " and DATA_FLAG in ('9') ";
			}else if(SSLX.equals("vs4sr")){
				sql = " select count(*)  ";
				sqlfrom = " from CP_MK_ADR_BLDGRSDNS_" + PROVINCE_ID.substring(0, 2) + " a";
				sqlwhere = sqlwhere + " and DATA_FLAG in ('6','7','8') ";
			}else if(SSLX.equals("vs5sr")){
				sql = " select count(*)  ";
				sqlfrom = " from CP_MK_ADR_BLDGRSDNS_" + PROVINCE_ID.substring(0, 2) + " a";
				sqlwhere = sqlwhere + " and DATA_FLAG in ('9') ";
			}else if(SSLX.equals("vs6sr")){
				sql = " select count(*)  ";
				sqlfrom = " from CP_MK_ORG_ORGANIZATION_" + PROVINCE_ID.substring(0, 2) + " a";
				sqlwhere = sqlwhere + " and DATA_FLAG in ('6','7','8') ";
			}else if(SSLX.equals("vs7sr")){
				sql = " select count(*)  ";
				sqlfrom = " from CP_MK_ORG_ORGANIZATION_" + PROVINCE_ID.substring(0, 2) + " a";
				sqlwhere = sqlwhere + " and DATA_FLAG in ('9') ";
			}else if(SSLX.equals("vsqfhdb2sr")){
				sql = " select count(*)  ";
				sqlfrom=" from " +
				" (select a.* from cp_mk_adr_street_" + PROVINCE_ID.substring(0,2) + " a, " +
				" (select strt1_name||strt2_name||strt3_name||strt4_name||strt5_name all_name from cp_mk_adr_street_"  + PROVINCE_ID.substring(0,2) +
				" group by strt1_name||strt2_name||strt3_name||strt4_name||strt5_name having count(*) > 1 )  b  " +
				" where a.strt1_name||a.strt2_name||a.strt3_name||a.strt4_name||a.strt5_name = b.all_name) a ";
				if(!DispCommon.isMunicipalitiesID(PROVINCE_ID.substring(0, 2))){
					sqlfrom=" from " +
					" (select a.* from cp_mk_adr_street_" + PROVINCE_ID.substring(0,2) + " a, " +
					" (select strt1_name||strt2_name||strt3_name||strt4_name||strt5_name all_name,substr(dist_cd,0,4) dist_cd from  cp_mk_adr_street_"  + PROVINCE_ID.substring(0,2) +
					" group by strt1_name||strt2_name||strt3_name||strt4_name||strt5_name,substr(dist_cd,0,4) having count(*) > 1 )  b " +
					" where a.strt1_name||a.strt2_name||a.strt3_name||a.strt4_name||a.strt5_name = b.all_name and substr(a.dist_cd,0,4)=b.dist_cd ) a ";
				}
				sqlwhere = sqlwhere + "  and a.IS_IGNORED is null ";
			}else if(SSLX.equals("vsqfhdb3sr")){
				sql = " select count(*)  ";
				sqlfrom=" from " +
				" (select a.* from cp_mk_adr_street_" + PROVINCE_ID.substring(0,2) + " a, " +
				" (select distinct t.dist_cd,t.strt1_name||t.strt2_name strt_name from cp_mk_adr_street_" + PROVINCE_ID.substring(0,2) + " t where t.strt2_name is not null) b" +
				" where a.strt1_name = b.strt_name and a.dist_cd = b.dist_cd" +
				" union" +
				" select c.* from cp_mk_adr_street_" + PROVINCE_ID.substring(0,2) + " c," +
				" (select b.strt_name from cp_mk_adr_street_" + PROVINCE_ID.substring(0,2) + " a," +
				" (select distinct t.dist_cd,t.strt1_name||t.strt2_name strt_name from cp_mk_adr_street_" + PROVINCE_ID.substring(0,2) + " t where t.strt2_name is not null) b" +
				" where a.strt1_name = b.strt_name and a.dist_cd = b.dist_cd) d" +
				" where c.strt1_name||c.strt2_name = d.strt_name) a ";
				sqlwhere = sqlwhere + "  and a.IS_IGNORED is null ";
			}else if(SSLX.equals("vsqfhdb4sr")){
				sql = " select count(*)  ";
				sqlfrom=" from " +
				" (select * from cp_mk_adr_street_" + PROVINCE_ID.substring(0,2) + " a " +
				" where 1=1 " +
				" and " +
				" (" +
				" strt1_name like '%镇%村%'  " +
				" or strt2_name like '%镇%村%' " +
				" or strt1_name like '%乡%村%' " +
				" or strt2_name like '%乡%村%' " +
				" or strt1_name like '%区%镇%' " +
				" or strt2_name like '%区%镇%' " +
				" or strt1_name like '%区%乡%' " +
				" or strt2_name like '%区%乡%' " +
				" or length(strt1_name) >= 6 " +
				" or length(strt2_name) >= 6 " +
				" or length(strt3_name) >= 6 " +
				" or length(strt4_name) >= 6 " +
				" or length(strt5_name) >= 6 " +
				" " +
				" ) " +
				" ) a ";
				sqlwhere = sqlwhere + "  and a.IS_IGNORED is null ";
			}else if(SSLX.equals("vsqfhdb5sr")){
				sql = " select count(*)  ";
				sqlfrom=" from " +
				" (select * from cp_mk_adr_street_" + PROVINCE_ID.substring(0,2) + " a " +
				" where 1=1 " +
				" and " +
				" (" +
				"  regexp_like(a.strt1_name||a.strt2_name||a.strt3_name||a.strt4_name||a.strt5_name,'[0-9][室]$') " +
				"  or regexp_like(a.strt1_name||a.strt2_name||a.strt3_name||a.strt4_name||a.strt5_name,'[0-9][层]$') " +
				"  or regexp_like(a.strt1_name||a.strt2_name||a.strt3_name||a.strt4_name||a.strt5_name,'[0-9][楼]$') " +
				"  or regexp_like(a.strt1_name||a.strt2_name||a.strt3_name||a.strt4_name||a.strt5_name,'[0-9]$') " +
				"  or regexp_like(a.strt1_name||a.strt2_name||a.strt3_name||a.strt4_name||a.strt5_name,'[a-z]$') " +
				"  or regexp_like(a.strt1_name||a.strt2_name||a.strt3_name||a.strt4_name||a.strt5_name,'[A-Z]$') " +
				"  or regexp_like(a.strt1_name||a.strt2_name||a.strt3_name||a.strt4_name||a.strt5_name,'^([a-z]+|[A-Z]+|[0-9]+)$') " +
				" ) " +
				" ) a ";
				sqlwhere = sqlwhere + "  and a.IS_IGNORED is null ";
			}else if(SSLX.equals("vol2sr")){
				sql = " select count(*)  ";
				sqlfrom=" from " +
				" CP_MK_ADR_STREET_" + PROVINCE_ID.substring(0,2) + " b," +
				" (select d.* from CP_MK_ORG_ORGANIZATION_" + PROVINCE_ID.substring(0,2) + " d, " +
				" (select org_name from  CP_MK_ORG_ORGANIZATION_"  + PROVINCE_ID.substring(0,2) +
				" group by org_name having count(*) > 1 )  e  " +
				" where d.org_name = e.org_name) a ";
				if(!DispCommon.isMunicipalitiesID(PROVINCE_ID.substring(0, 2))){
					sqlfrom=" from " +
					" CP_MK_ADR_STREET_" + PROVINCE_ID.substring(0,2) + " b," +
					" (select d.* from CP_MK_ORG_ORGANIZATION_" + PROVINCE_ID.substring(0,2) + " d, " +
					" (select org_name,substr(dist_cd,0,4) dist_cd from  CP_MK_ORG_ORGANIZATION_"  + PROVINCE_ID.substring(0,2) +
					" group by org_name,substr(dist_cd,0,4) having count(*) > 1 )  e  " +
					" where d.org_name = e.org_name and substr(d.dist_cd,0,4)=e.dist_cd ) a ";
				}
				sqlwhere = sqlwhere + "  and b.strt_id = a.strt_id  and a.IS_IGNORED is null ";
			}else if(SSLX.equals("vol3sr")){
				sql = " select count(*)  ";
				sqlfrom=" from " +
				" CP_MK_ADR_STREET_" + PROVINCE_ID.substring(0,2) + " b," +
				" (select * from CP_MK_ORG_ORGANIZATION_" + PROVINCE_ID.substring(0,2) + " d " +
				" where 1=1 " +
				" and " +
				" ( " +
				"  regexp_like(d.ORG_NAME,'[0-9][室]') " +
				"  or regexp_like(d.ORG_NAME,'[0-9][层]') " +
				"  or regexp_like(d.ORG_NAME,'[0-9][楼]') " +
				"  or regexp_like(d.ORG_NAME,'[0-9]$') " +
				"  or regexp_like(d.ORG_NAME,'[a-z]$') " +
				"  or regexp_like(d.ORG_NAME,'[A-Z]$') " +
				"  or regexp_like(d.ORG_NAME,'^([a-z]+|[A-Z]+|[0-9]+)$') "  +
				" ) " +
				" ) a ";
				sqlwhere = sqlwhere + " and b.strt_id = a.strt_id  and a.IS_IGNORED is null ";
			}else if(SSLX.equals("vbl2sr")){
				sql = " select count(*)  ";
				sqlfrom=" from " +
				" CP_MK_ADR_STREET_" + PROVINCE_ID.substring(0,2) + " b," +
				" (select d.* from CP_MK_ADR_BLDGRSDNS_" + PROVINCE_ID.substring(0,2) + " d, " +
				" (select RSDNBLDG_NAME from  CP_MK_ADR_BLDGRSDNS_"  + PROVINCE_ID.substring(0,2) +
				" group by RSDNBLDG_NAME having count(*) > 1 )  e  " +
				" where d.RSDNBLDG_NAME = e.RSDNBLDG_NAME) a ";
				if(!DispCommon.isMunicipalitiesID(PROVINCE_ID.substring(0, 2))){
					sqlfrom=" from " +
					" CP_MK_ADR_STREET_" + PROVINCE_ID.substring(0,2) + " b," +
					" (select d.* from CP_MK_ADR_BLDGRSDNS_" + PROVINCE_ID.substring(0,2) + " d, " +
					" (select RSDNBLDG_NAME,substr(dist_cd,0,4) dist_cd from  CP_MK_ADR_BLDGRSDNS_"  + PROVINCE_ID.substring(0,2) +
					" group by RSDNBLDG_NAME,substr(dist_cd,0,4) having count(*) > 1 )  e  " +
					" where d.RSDNBLDG_NAME = e.RSDNBLDG_NAME and substr(d.dist_cd,0,4)=e.dist_cd ) a ";
				}
				sqlwhere = sqlwhere + " and b.strt_id = a.strt_id  and a.IS_IGNORED is null ";
			}else if(SSLX.equals("vbl3sr")){
				sql = " select count(*)  ";
				sqlfrom=" from " +
				" CP_MK_ADR_STREET_" + PROVINCE_ID.substring(0,2) + " b," +
				" (select * from CP_MK_ADR_BLDGRSDNS_" + PROVINCE_ID.substring(0,2) + " d " +
				" where 1=1 " +
				" and " +
				" ( " +
				"  regexp_like(d.RSDNBLDG_NAME,'[0-9][室]') " +
				"  or regexp_like(d.RSDNBLDG_NAME,'[0-9][层]') " +
				"  or regexp_like(d.RSDNBLDG_NAME,'[0-9][楼]') " +
				"  or regexp_like(d.RSDNBLDG_NAME,'[0-9]$') " +
				"  or regexp_like(d.RSDNBLDG_NAME,'[a-z]$') " +
				"  or regexp_like(d.RSDNBLDG_NAME,'[A-Z]$') " +
				"  or regexp_like(d.RSDNBLDG_NAME,'^([a-z]+|[A-Z]+|[0-9]+)$') "  +
				" ) " +
				" ) a ";
				sqlwhere = sqlwhere + " and b.strt_id = a.strt_id  and a.IS_IGNORED is null ";
			}else if(SSLX.equals("vbd2sr")){
				sql = " select count(*)  ";
				sqlfrom=" from TB_MINUS_BLDGRSDNS_" + PROVINCE_ID.substring(0,2) + " a , CP_MK_ADR_STREET_"  + PROVINCE_ID.substring(0,2) + " b ";
				sqlwhere = sqlwhere + " and a.strt_id = b.strt_id ";
			}else if(SSLX.equals("vbd3nr")){
				sql = " select count(*)  ";
				sqlfrom=" from  TB_MINUS_BLDGRSDNS_" + PROVINCE_ID.substring(0,2) + " a , CP_MK_ADR_STREET_"  + PROVINCE_ID.substring(0,2) + " b ";
				sqlwhere = sqlwhere + " and a.strt_id = b.STRT_ID(+) and b.STRT_ID is null ";
			}else if(SSLX.equals("vod2sr")){
				sql = " select count(*)  ";
				sqlfrom=" from TB_MINUS_ORGANIZATION_" + PROVINCE_ID.substring(0,2) + " a , CP_MK_ADR_STREET_"  + PROVINCE_ID.substring(0,2) + " b ";
				sqlwhere = sqlwhere + " and a.strt_id = b.strt_id ";
			}else if(SSLX.equals("vod3nr")){
				sql = " select count(*)  ";
				sqlfrom=" from TB_MINUS_ORGANIZATION_" + PROVINCE_ID.substring(0,2) + " a , CP_MK_ADR_STREET_"  + PROVINCE_ID.substring(0,2) + " b ";
				sqlwhere = sqlwhere + " and a.strt_id = b.STRT_ID(+) and b.STRT_ID is null ";
			}
			
			if(getSessionUser().getRulLevel()==20 && SSLX.indexOf("sr") > -1){
				sqlfrom = sqlfrom + " ," +
				" (select STRT_ID from CP_WH_RL_PG_ST_" + PROVINCE_ID.substring(0,2) + "  where DM_PK_CODE = '" + getSessionUser().getUsDepartmentOffice() + "' " +
						"union select strt_id from CP_MK_ADR_STREET_" + PROVINCE_ID.substring(0,2) + " where  DATA_USER = '" + getSessionUser().getUsLoginId() + "'" +
								" ) f";
			}
			
			if(getSessionUser().getRulLevel()==20 && SSLX.indexOf("sr") > -1){
				sqlwhere = sqlwhere + " and a.STRT_ID = f.STRT_ID " ;
			}
			
			if(!COUNTY_ID.equals("null") && COUNTY_ID.length()>0){
				sqlwhere = sqlwhere + " and a.DIST_CD='" + COUNTY_ID + "'";
			}else if(!CITY_ID.equals("null") && CITY_ID.length()>0){
				if(CITY_ID.substring(4, 6).equals("00")){
					sqlwhere = sqlwhere + " and a.DIST_CD like '" + CITY_ID.substring(0, 4) + "%'";
				}else{
					sqlwhere = sqlwhere + " and a.DIST_CD like '" + CITY_ID + "%'";
				}				
			}else if(!PROVINCE_ID.equals("null") && PROVINCE_ID.length()>0){
				sqlwhere = sqlwhere + " and a.DIST_CD like '" + PROVINCE_ID.substring(0, 2) + "%'";
			}
			
			sql=sql+sqlfrom+sqlwhere;
			ALL_DATA_NUMBER=SSLX + "#" + SSLX + "#" + addrQueryDao.getALL_DATA_NUMBER(sql);
		}
		return "json";
	}
	
	@SuppressWarnings("unchecked")
	public String queryshdata() throws Exception {		
		
		if(!userflag()){return "json";}
		
		String sql="";
		String sqlCount="";
		String sqlfrom="";
		String sqlwhere="";
		String sqlorder="";
		if(TYPE_DATA.equals("strt")){
			sql="select a.DIST_CD,a.STRT_ID,a.DIST_CD||'@'||a.STRT_ID,replace(replace(replace(b.PROVINCE_NAME||b.CITY_NAME||b.COUNTY_NAME||a.STRT1_NAME||a.STRT2_NAME||a.STRT3_NAME||a.STRT4_NAME||a.STRT5_NAME,'(','（'),')','）'),'\"','“'),'0',"
				+ " a.data_flag  , a.note ";
			sqlCount="select count(*) ";
			sqlfrom=" from CP_MK_ADR_STREET_" + PROVINCE_ID.substring(0,2) + " a,CP_BASE_ORG_DISTRICT b";
			sqlwhere=" where 1=1 and a.DIST_CD = b.district_code ";
			sqlorder=" order by decode(a.DATA_FLAG,'A',0,'9',1,'8',2,'7',3,'6',4,5),a.DATA_DATE desc,a.strt1_name ";
		}else if(TYPE_DATA.equals("bldgrsdns")){
			sql="select a.DIST_CD,a.RSDNBLDG_ID,a.DIST_CD||'@'||a.RSDNBLDG_ID,replace(replace(replace(b.PROVINCE_NAME||b.CITY_NAME||b.COUNTY_NAME||c.STRT1_NAME||c.STRT2_NAME||c.STRT3_NAME||c.STRT4_NAME||c.STRT5_NAME||a.RSDNBLDG_NAME,'(','（'),')','）'),'\"','“'),'1',"
				+ " a.data_flag  , a.note ";
				sqlCount="select count(*) ";
			sqlfrom=" from CP_MK_ADR_BLDGRSDNS_" + PROVINCE_ID.substring(0,2) + " a,CP_BASE_ORG_DISTRICT b, CP_MK_ADR_STREET_" + PROVINCE_ID.substring(0,2) + " c ";
			sqlwhere=" where 1=1 and a.DIST_CD = b.district_code and a.strt_id=c.strt_id";
			
			sqlorder=" order by decode(a.DATA_FLAG,'A',0,'9',1,'8',2,'7',3,'6',4,5),a.DATA_DATE desc,a.RSDNBLDG_NAME ";
		}else if(TYPE_DATA.equals("organization")){
			sql="select a.DIST_CD,a.ORG_ID,a.DIST_CD||'@'||a.ORG_ID,replace(replace(replace(b.PROVINCE_NAME||b.CITY_NAME||b.COUNTY_NAME||c.STRT1_NAME||c.STRT2_NAME||c.STRT3_NAME||c.STRT4_NAME||c.STRT5_NAME||a.ORG_NAME,'(','（'),')','）'),'\"','“'),'2', "
				+ " a.data_flag , a.note ";
			sqlCount="select count(*) ";
			sqlfrom=" from CP_MK_ORG_ORGANIZATION_" + PROVINCE_ID.substring(0,2) + " a ,CP_BASE_ORG_DISTRICT b, CP_MK_ADR_STREET_" + PROVINCE_ID.substring(0,2) + " c ";
			sqlwhere=" where 1=1 and a.DIST_CD = b.district_code and a.strt_id=c.strt_id";
			
			sqlorder=" order by decode(a.DATA_FLAG,'A',0,'9',1,'8',2,'7',3,'6',4,5),a.DATA_DATE desc,a.ORG_NAME ";
		}
				
		if(!COUNTY_ID.equals("null") && COUNTY_ID.length()>0){
			sqlwhere = sqlwhere + " and a.DIST_CD='" + COUNTY_ID + "'";
		}else if(!CITY_ID.equals("null") && CITY_ID.length()>0){
			if(CITY_ID.substring(4, 6).equals("00")){
				sqlwhere = sqlwhere + " and a.DIST_CD like '" + CITY_ID.substring(0, 4) + "%'";
			}else{
				sqlwhere = sqlwhere + " and a.DIST_CD like '" + CITY_ID + "%'";
			}
		}else if(!PROVINCE_ID.equals("null") && PROVINCE_ID.length()>0){
			sqlwhere = sqlwhere + " and a.DIST_CD like '" + PROVINCE_ID.substring(0, 2) + "%'";
		}
		
		if(TYPE_FLAG.equals("0")){
			sqlwhere= sqlwhere + " and a.data_flag in ('6','7','8') ";
		}else if(TYPE_FLAG.equals("6")){
			sqlwhere=sqlwhere + " and a.data_flag = '6' ";
		}else if(TYPE_FLAG.equals("7")){
			sqlwhere=sqlwhere + " and a.data_flag = '7' ";
		}else if(TYPE_FLAG.equals("8")){
			sqlwhere=sqlwhere + " and a.data_flag = '8' ";
		}
				
		sql=sql+sqlfrom+sqlwhere+sqlorder;
		sqlCount=sqlCount+sqlfrom+sqlwhere;
		
		//System.out.println(sql);
		
		page.setPageSize(50);
		if(pageNo!=0){
			page.setPageNo(pageNo);
		}
		page=addrQueryDao.getBeanQueryShdata(sql, sqlCount, page);
		return "json";
	}
	

	@SuppressWarnings("unchecked")
	public String queryjnadtpg() throws Exception {		
		
		if(!userflag()){return "json";}
		
		//System.out.println(System.getProperty("user.dir"));
		//String[] tTOTAL_ADDR_NAME = ADDR_NAME.split(",");
		String[] tTOTAL_ADDR_NAME = ADDR_NAME.split("\n");
		for(int i=0;i<tTOTAL_ADDR_NAME.length;i++){
			//System.out.println(i);
			//System.out.println(tTOTAL_ADDR_NAME[i]);
			//tTOTAL_ADDR_NAME[i] = tTOTAL_ADDR_NAME[i].replace("\r","");
			//tTOTAL_ADDR_NAME[i] = tTOTAL_ADDR_NAME[i].replace("\n","");
			tTOTAL_ADDR_NAME[i] = PROVINCE_NAMES + "||" + CITY_NAMES + "||" + tTOTAL_ADDR_NAME[i];
		}	
		page.setPageSize(9999);
		if(pageNo!=0){
			page.setPageNo(pageNo);
		}
		if(ADDR_NAME.trim().length()>0){
			page=jnadtpgQueryDao.getBeanQueryJnadtpgdata(ALL_PATH,tTOTAL_ADDR_NAME, page);
		}
		return "json";
	}
	
	@SuppressWarnings("unchecked")
	public String queryorgandius() throws Exception {		
		
		if(!userflag()){return "json";}
		
		String sql="select b.ORG_ID,b.DIST_CD,a.PROVINCE_NAME||a.CITY_NAME||a.COUNTY_NAME TOTAL_DISTRICT_NAME,b.STRT_ID,c.strt1_name||c.strt2_name||c.strt3_name||c.strt4_name||c.strt5_name,b.ORG_NAME,b.ORG_ABBR ";
		String sqlCount="select count(*) ";
		String sqlfrom=" from CP_BASE_ORG_DISTRICT a,TB_MINUS_ORGANIZATION_" + PROVINCE_ID.substring(0,2) + " b , CP_MK_ADR_STREET_"  + PROVINCE_ID.substring(0,2) + " c ";
		String sqlwhere=" where a.district_code=b.dist_cd ";
		String sqlorder=" order by  b.dist_cd,b.org_name";
		
		if(getSessionUser().getRulLevel()==20 && ISSTREET_NAME.equals("1")){
			sqlfrom = sqlfrom + " ," +
			" (select STRT_ID from CP_WH_RL_PG_ST_" + PROVINCE_ID.substring(0,2) + "  where DM_PK_CODE = '" + getSessionUser().getUsDepartmentOffice() + "' " +
					"union select strt_id from CP_MK_ADR_STREET_" + PROVINCE_ID.substring(0,2) + " where  DATA_USER = '" + getSessionUser().getUsLoginId() + "'" +
							" ) f";
		}
		
		if(getSessionUser().getRulLevel()==20 && ISSTREET_NAME.equals("1")){
			sqlwhere = sqlwhere + " and b.strt_id=f.strt_id " ;
		}
		
		if(ISSTREET_NAME.equals("1")){
			sqlwhere = sqlwhere + "  and b.STRT_ID = c.STRT_ID " ;
		}
		
		if(ISSTREET_NAME.equals("0")){
			sqlwhere = sqlwhere + " and b.STRT_ID = c.STRT_ID(+) and c.STRT_ID is null " ;
		}
		
		if(!ORG_NAME.equals("null") && ORG_NAME.length()>0){
			sqlwhere = sqlwhere + " and b.org_name like '%" + ORG_NAME + "%'";
		}

		if(!STREET_NAME.equals("null") && STREET_NAME.length()>0){
			sqlwhere = sqlwhere + " and c.strt1_name||c.strt2_name||c.strt3_name||c.strt4_name||c.strt5_name  like '%" + STREET_NAME + "%'";
		}

		
		if(!COUNTY_ID.equals("null") && COUNTY_ID.length()>0){
			sqlwhere = sqlwhere + " and a.district_code='" + COUNTY_ID + "'";
		}else if(!CITY_ID.equals("null") && CITY_ID.length()>0){
			if(CITY_ID.substring(4, 6).equals("00")){
				sqlwhere = sqlwhere + " and a.district_code like '" + CITY_ID.substring(0, 4) + "%'";
			}else{
				sqlwhere = sqlwhere + " and a.district_code like '" + CITY_ID + "%'";
			}
		}else if(!PROVINCE_ID.equals("null") && PROVINCE_ID.length()>0){
			sqlwhere = sqlwhere + " and a.district_code like '" + PROVINCE_ID.substring(0, 2) + "%'";
		}

		sql=sql+sqlfrom+sqlwhere+sqlorder;
		sqlCount=sqlCount+sqlfrom+sqlwhere;
		
		page.setPageSize(50);
		
		if(pageNo!=0){
			page.setPageNo(pageNo);
		}
		page=addrQueryDao.getBeanQueryorgandius(sql, sqlCount, page);
		return "json";
	}
	
	
	@SuppressWarnings("unchecked")
	public String querybldgrsdnsdius() throws Exception {		
		
		if(!userflag()){return "json";}
		
		String sql="select b.RSDNBLDG_ID,b.DIST_CD,a.PROVINCE_NAME||a.CITY_NAME||a.COUNTY_NAME TOTAL_DISTRICT_NAME,b.STRT_ID,c.strt1_name||c.strt2_name||c.strt3_name||c.strt4_name||c.strt5_name,b.RSDNBLDG_NAME,b.RSDNBLDG_ABBR ";
		String sqlCount="select count(*) ";
		String sqlfrom=" from CP_BASE_ORG_DISTRICT a,TB_MINUS_BLDGRSDNS_" + PROVINCE_ID.substring(0,2) + " b , CP_MK_ADR_STREET_"  + PROVINCE_ID.substring(0,2) + " c ";
		String sqlwhere=" where a.district_code=b.dist_cd ";
		String sqlorder=" order by  b.dist_cd,b.RSDNBLDG_NAME";
		
		if(getSessionUser().getRulLevel()==20 && ISSTREET_NAME.equals("1")){
			sqlfrom = sqlfrom + " ," +
			" (select STRT_ID from CP_WH_RL_PG_ST_" + PROVINCE_ID.substring(0,2) + "  where DM_PK_CODE = '" + getSessionUser().getUsDepartmentOffice() + "' " +
					"union select strt_id from CP_MK_ADR_STREET_" + PROVINCE_ID.substring(0,2) + " where  DATA_USER = '" + getSessionUser().getUsLoginId() + "'" +
							" ) f";
		}
		
		if(getSessionUser().getRulLevel()==20 && ISSTREET_NAME.equals("1")){
			sqlwhere = sqlwhere + " and b.strt_id=f.strt_id " ;
		}
		
		if(ISSTREET_NAME.equals("1")){
			sqlwhere = sqlwhere + "  and b.STRT_ID = c.STRT_ID " ;
		}
		
		if(ISSTREET_NAME.equals("0")){
			sqlwhere = sqlwhere + " and b.STRT_ID = c.STRT_ID(+) and c.STRT_ID is null " ;
		}
		
		if(!RSDNBLDG_NAME.equals("null") && RSDNBLDG_NAME.length()>0){
			sqlwhere = sqlwhere + " and b.RSDNBLDG_NAME like '%" + RSDNBLDG_NAME + "%'";
		}

		if(!STREET_NAME.equals("null") && STREET_NAME.length()>0){
			sqlwhere = sqlwhere + " and c.strt1_name||c.strt2_name||c.strt3_name||c.strt4_name||c.strt5_name  like '%" + STREET_NAME + "%'";
		}

		
		if(!COUNTY_ID.equals("null") && COUNTY_ID.length()>0){
			sqlwhere = sqlwhere + " and a.district_code='" + COUNTY_ID + "'";
		}else if(!CITY_ID.equals("null") && CITY_ID.length()>0){
			if(CITY_ID.substring(4, 6).equals("00")){
				sqlwhere = sqlwhere + " and a.district_code like '" + CITY_ID.substring(0, 4) + "%'";
			}else{
				sqlwhere = sqlwhere + " and a.district_code like '" + CITY_ID + "%'";
			}
		}else if(!PROVINCE_ID.equals("null") && PROVINCE_ID.length()>0){
			sqlwhere = sqlwhere + " and a.district_code like '" + PROVINCE_ID.substring(0, 2) + "%'";
		}

		sql=sql+sqlfrom+sqlwhere+sqlorder;
		sqlCount=sqlCount+sqlfrom+sqlwhere;
		
		page.setPageSize(50);
		
		if(pageNo!=0){
			page.setPageNo(pageNo);
		}
		page=addrQueryDao.getBeanQuerybldgrsdnsdius(sql, sqlCount, page);
		return "json";
	}

	
	@SuppressWarnings("unchecked")
	public String querystreetsluan() throws Exception {		
		
		if(!userflag()){return "json";}
		
		String sql="select b.STRT_ID,b.DIST_CD,a.PROVINCE_NAME||a.CITY_NAME||a.COUNTY_NAME,b.STRT1_NAME||b.STRT2_NAME||b.STRT3_NAME||b.STRT4_NAME||b.STRT5_NAME,b.DATA_FLAG,b.POST_CD ";
		String sqlCount="select count(*) ";
		String sqlfrom=" from CP_BASE_ORG_DISTRICT a,CP_MK_ADR_STREET_" + PROVINCE_ID.substring(0,2) + " b ";
		
		if(getSessionUser().getRulLevel()==20){
			sqlfrom = sqlfrom + " ," +
			" (select STRT_ID from CP_WH_RL_PG_ST_" + PROVINCE_ID.substring(0,2) + "  where DM_PK_CODE = '" + getSessionUser().getUsDepartmentOffice() + "' " +
					"union select strt_id from CP_MK_ADR_STREET_" + PROVINCE_ID.substring(0,2) + " where  DATA_USER = '" + getSessionUser().getUsLoginId() + "'" +
							" ) f";
	  }
		
		
		String sqlwhere=" where a.district_code=b.dist_cd ";
		String sqlorder=" order by b.DIST_CD,b.strt1_name||b.strt2_name||b.strt3_name||b.strt4_name||b.strt5_name";

		
		if(getSessionUser().getRulLevel()==20){
			sqlwhere = sqlwhere + " and b.strt_id=f.strt_id " ;
		}
		
		if(!STREET_NAME.equals("null") && STREET_NAME.length()>0){
			sqlwhere = sqlwhere + " and  b.strt1_name||b.strt2_name||b.strt3_name||b.strt4_name||b.strt5_name like '%" + STREET_NAME + "%' ";
		}
		
		if(!COUNTY_ID.equals("null") && COUNTY_ID.length()>0){
			sqlwhere = sqlwhere + " and a.district_code='" + COUNTY_ID + "'";
		}else if(!CITY_ID.equals("null") && CITY_ID.length()>0){
			if(CITY_ID.substring(4, 6).equals("00")){
				sqlwhere = sqlwhere + " and a.district_code like '" + CITY_ID.substring(0, 4) + "%'";
			}else{
				sqlwhere = sqlwhere + " and a.district_code like '" + CITY_ID + "%'";
			}
		}else if(!PROVINCE_ID.equals("null") && PROVINCE_ID.length()>0){
			sqlwhere = sqlwhere + " and a.district_code like '" + PROVINCE_ID.substring(0, 2) + "%'";
		}
		
		sqlwhere = sqlwhere + " and ( " ;
		sqlwhere = sqlwhere + "  regexp_like(b.strt1_name||b.strt2_name||b.strt3_name||b.strt4_name||b.strt5_name,'[0-9][室]$') " ;
		sqlwhere = sqlwhere + "  or regexp_like(b.strt1_name||b.strt2_name||b.strt3_name||b.strt4_name||b.strt5_name,'[0-9][层]$') " ;
		sqlwhere = sqlwhere + "  or regexp_like(b.strt1_name||b.strt2_name||b.strt3_name||b.strt4_name||b.strt5_name,'[0-9][楼]$') " ;
		sqlwhere = sqlwhere + "  or regexp_like(b.strt1_name||b.strt2_name||b.strt3_name||b.strt4_name||b.strt5_name,'[0-9]$') " ;
		sqlwhere = sqlwhere + "  or regexp_like(b.strt1_name||b.strt2_name||b.strt3_name||b.strt4_name||b.strt5_name,'[a-z]$') " ;
		sqlwhere = sqlwhere + "  or regexp_like(b.strt1_name||b.strt2_name||b.strt3_name||b.strt4_name||b.strt5_name,'[A-Z]$') " ;
		sqlwhere = sqlwhere + "  or regexp_like(b.strt1_name||b.strt2_name||b.strt3_name||b.strt4_name||b.strt5_name,'^([a-z]+|[A-Z]+|[0-9]+)$') " ;
		sqlwhere = sqlwhere + " ) " ;

		sql=sql+sqlfrom+sqlwhere+sqlorder;
		sqlCount=sqlCount+sqlfrom+sqlwhere;
		
		page.setPageSize(50);
		
		if(pageNo!=0){
			page.setPageNo(pageNo);
		}
		page=addrQueryDao.getBeanQueryStreets(sql, sqlCount, page);
		return "json";
	}
	
	@SuppressWarnings("unchecked")
	public String queryorganluan() throws Exception {		
		
		if(!userflag()){return "json";}
		
		String sql="select c.ORG_ID,c.DIST_CD,b.STRT_ID,a.PROVINCE_NAME||a.CITY_NAME||a.COUNTY_NAME,b.STRT1_NAME||b.STRT2_NAME||b.STRT3_NAME||b.STRT4_NAME||b.STRT5_NAME,c.ORG_NAME,c.ORG_ABBR ";
		String sqlCount="select count(*) ";
		String sqlfrom=" ";
		
		
		if(IS_CHAXUNLX.equals("0")){
			sqlfrom=" from CP_BASE_ORG_DISTRICT a," +
			" CP_MK_ADR_STREET_" + PROVINCE_ID.substring(0,2) + " b," +
			" (select d.* from CP_MK_ORG_ORGANIZATION_" + PROVINCE_ID.substring(0,2) + " d, " +
			" (select org_name from  CP_MK_ORG_ORGANIZATION_"  + PROVINCE_ID.substring(0,2) +
			" group by org_name having count(*) > 1 )  e  " +
			" where d.org_name = e.org_name) c ";
			if(!DispCommon.isMunicipalitiesID(PROVINCE_ID.substring(0, 2))){
				sqlfrom=" from CP_BASE_ORG_DISTRICT a," +
				" CP_MK_ADR_STREET_" + PROVINCE_ID.substring(0,2) + " b," +
				" (select d.* from CP_MK_ORG_ORGANIZATION_" + PROVINCE_ID.substring(0,2) + " d, " +
				" (select org_name,substr(dist_cd,0,4) dist_cd from  CP_MK_ORG_ORGANIZATION_"  + PROVINCE_ID.substring(0,2) +
				" group by org_name,substr(dist_cd,0,4) having count(*) > 1 )  e  " +
				" where d.org_name = e.org_name and substr(d.dist_cd,0,4)=e.dist_cd ) c ";
			}
		}else if(IS_CHAXUNLX.equals("3")){
			sqlfrom=" from CP_BASE_ORG_DISTRICT a," +
			" CP_MK_ADR_STREET_" + PROVINCE_ID.substring(0,2) + " b," +
			" (select * from CP_MK_ORG_ORGANIZATION_" + PROVINCE_ID.substring(0,2) + " d " +
			" where 1=1 " +
			" and " +
			" ( " +
			"  regexp_like(d.ORG_NAME,'[0-9][室]') " +
			"  or regexp_like(d.ORG_NAME,'[0-9][层]') " +
			"  or regexp_like(d.ORG_NAME,'[0-9][楼]') " +
			"  or regexp_like(d.ORG_NAME,'[0-9]$') " +
			"  or regexp_like(d.ORG_NAME,'[a-z]$') " +
			"  or regexp_like(d.ORG_NAME,'[A-Z]$') " +
			"  or regexp_like(d.ORG_NAME,'^([a-z]+|[A-Z]+|[0-9]+)$') "  +
			" ) " +
			" ) c ";
		}
		
		if(getSessionUser().getRulLevel()==20){
			sqlfrom = sqlfrom + " ," +
			" (select STRT_ID from CP_WH_RL_PG_ST_" + PROVINCE_ID.substring(0,2) + "  where DM_PK_CODE = '" + getSessionUser().getUsDepartmentOffice() + "' " +
					"union select strt_id from CP_MK_ADR_STREET_" + PROVINCE_ID.substring(0,2) + " where  DATA_USER = '" + getSessionUser().getUsLoginId() + "'" +
							" ) f";
	  }
		
		
		String sqlwhere=" where a.district_code=c.dist_cd and b.strt_id = c.strt_id and c.IS_IGNORED is null ";
		String sqlorder=" order by c.DIST_CD,b.strt1_name,c.ORG_NAME";
		if(IS_CHAXUNLX.equals("0")){
			sqlorder=" order by c.ORG_NAME";
		}
		
		if(getSessionUser().getRulLevel()==20){
			sqlwhere = sqlwhere + " and b.strt_id=f.strt_id " ;
		}
		
		if(!STREET_NAME.equals("null") && STREET_NAME.length()>0){
			sqlwhere = sqlwhere + " and  b.strt1_name||b.strt2_name||b.strt3_name||b.strt4_name||b.strt5_name like '%" + STREET_NAME + "%' ";
		}
		
		if(!COUNTY_ID.equals("null") && COUNTY_ID.length()>0){
			sqlwhere = sqlwhere + " and a.district_code='" + COUNTY_ID + "'";
		}else if(!CITY_ID.equals("null") && CITY_ID.length()>0){
			if(CITY_ID.substring(4, 6).equals("00")){
				sqlwhere = sqlwhere + " and a.district_code like '" + CITY_ID.substring(0, 4) + "%'";
			}else{
				sqlwhere = sqlwhere + " and a.district_code like '" + CITY_ID + "%'";
			}
		}else if(!PROVINCE_ID.equals("null") && PROVINCE_ID.length()>0){
			sqlwhere = sqlwhere + " and a.district_code like '" + PROVINCE_ID.substring(0, 2) + "%'";
		}
		
		if(!ORG_NAME.equals("null") && ORG_NAME.length()>0){
			sqlwhere = sqlwhere + " and  c.ORG_NAME like '%" + ORG_NAME + "%' ";
		}
		

		sql=sql+sqlfrom+sqlwhere+sqlorder;
		sqlCount=sqlCount+sqlfrom+sqlwhere;
		
		page.setPageSize(50);
		
		if(pageNo!=0){
			page.setPageNo(pageNo);
		}
		page=addrQueryDao.getBeanQueryOrganluan(sql, sqlCount, page);
		return "json";
	}
	
	@SuppressWarnings("unchecked")
	public String querybldgrsdnsluan() throws Exception {		
		
		if(!userflag()){return "json";}
		
		String sql="select c.RSDNBLDG_ID,c.DIST_CD,b.STRT_ID,a.PROVINCE_NAME||a.CITY_NAME||a.COUNTY_NAME,b.STRT1_NAME||b.STRT2_NAME||b.STRT3_NAME||b.STRT4_NAME||b.STRT5_NAME,c.RSDNBLDG_NAME,c.RSDNBLDG_ABBR ";
		String sqlCount="select count(*) ";
		String sqlfrom=" ";
		
		
		if(IS_CHAXUNLX.equals("0")){
			sqlfrom=" from CP_BASE_ORG_DISTRICT a," +
			" CP_MK_ADR_STREET_" + PROVINCE_ID.substring(0,2) + " b," +
			" (select d.* from CP_MK_ADR_BLDGRSDNS_" + PROVINCE_ID.substring(0,2) + " d, " +
			" (select RSDNBLDG_NAME from  CP_MK_ADR_BLDGRSDNS_"  + PROVINCE_ID.substring(0,2) +
			" group by RSDNBLDG_NAME having count(*) > 1 )  e  " +
			" where d.RSDNBLDG_NAME = e.RSDNBLDG_NAME) c ";
			if(!DispCommon.isMunicipalitiesID(PROVINCE_ID.substring(0, 2))){
				sqlfrom=" from CP_BASE_ORG_DISTRICT a," +
				" CP_MK_ADR_STREET_" + PROVINCE_ID.substring(0,2) + " b," +
				" (select d.* from CP_MK_ADR_BLDGRSDNS_" + PROVINCE_ID.substring(0,2) + " d, " +
				" (select RSDNBLDG_NAME,substr(dist_cd,0,4) dist_cd from  CP_MK_ADR_BLDGRSDNS_"  + PROVINCE_ID.substring(0,2) +
				" group by RSDNBLDG_NAME,substr(dist_cd,0,4) having count(*) > 1 )  e  " +
				" where d.RSDNBLDG_NAME = e.RSDNBLDG_NAME and substr(d.dist_cd,0,4)=e.dist_cd ) c ";
			}
		}else if(IS_CHAXUNLX.equals("3")){
			sqlfrom=" from CP_BASE_ORG_DISTRICT a," +
			" CP_MK_ADR_STREET_" + PROVINCE_ID.substring(0,2) + " b," +
			" (select * from CP_MK_ADR_BLDGRSDNS_" + PROVINCE_ID.substring(0,2) + " d " +
			" where 1=1 " +
			" and " +
			" ( " +
			"  regexp_like(d.RSDNBLDG_NAME,'[0-9][室]') " +
			"  or regexp_like(d.RSDNBLDG_NAME,'[0-9][层]') " +
			"  or regexp_like(d.RSDNBLDG_NAME,'[0-9][楼]') " +
			"  or regexp_like(d.RSDNBLDG_NAME,'[0-9]$') " +
			"  or regexp_like(d.RSDNBLDG_NAME,'[a-z]$') " +
			"  or regexp_like(d.RSDNBLDG_NAME,'[A-Z]$') " +
			"  or regexp_like(d.RSDNBLDG_NAME,'^([a-z]+|[A-Z]+|[0-9]+)$') "  +
			" ) " +
			" ) c ";
		}
		
		if(getSessionUser().getRulLevel()==20){
			sqlfrom = sqlfrom + " ," +
			" (select STRT_ID from CP_WH_RL_PG_ST_" + PROVINCE_ID.substring(0,2) + "  where DM_PK_CODE = '" + getSessionUser().getUsDepartmentOffice() + "' " +
					"union select strt_id from CP_MK_ADR_STREET_" + PROVINCE_ID.substring(0,2) + " where  DATA_USER = '" + getSessionUser().getUsLoginId() + "'" +
							" ) f";
	  }
		
		
		String sqlwhere=" where a.district_code=c.dist_cd and b.strt_id = c.strt_id  and c.IS_IGNORED is null ";
		String sqlorder=" order by c.DIST_CD,b.strt1_name,c.RSDNBLDG_NAME";
		if(IS_CHAXUNLX.equals("0")){
			sqlorder=" order by c.RSDNBLDG_NAME";
		}
		
		if(getSessionUser().getRulLevel()==20){
			sqlwhere = sqlwhere + " and b.strt_id=f.strt_id " ;
		}
		
		if(!STREET_NAME.equals("null") && STREET_NAME.length()>0){
			sqlwhere = sqlwhere + " and  b.strt1_name||b.strt2_name||b.strt3_name||b.strt4_name||b.strt5_name like '%" + STREET_NAME + "%' ";
		}
		
		if(!COUNTY_ID.equals("null") && COUNTY_ID.length()>0){
			sqlwhere = sqlwhere + " and a.district_code='" + COUNTY_ID + "'";
		}else if(!CITY_ID.equals("null") && CITY_ID.length()>0){
			if(CITY_ID.substring(4, 6).equals("00")){
				sqlwhere = sqlwhere + " and a.district_code like '" + CITY_ID.substring(0, 4) + "%'";
			}else{
				sqlwhere = sqlwhere + " and a.district_code like '" + CITY_ID + "%'";
			}
		}else if(!PROVINCE_ID.equals("null") && PROVINCE_ID.length()>0){
			sqlwhere = sqlwhere + " and a.district_code like '" + PROVINCE_ID.substring(0, 2) + "%'";
		}
		
		if(!RSDNBLDG_NAME.equals("null") && RSDNBLDG_NAME.length()>0){
			sqlwhere = sqlwhere + " and  c.RSDNBLDG_NAME like '%" + RSDNBLDG_NAME + "%' ";
		}

		sql=sql+sqlfrom+sqlwhere+sqlorder;
		sqlCount=sqlCount+sqlfrom+sqlwhere;
		
		page.setPageSize(50);
		
		if(pageNo!=0){
			page.setPageNo(pageNo);
		}
		page=addrQueryDao.getBeanQueryBldgrsdnsluan(sql, sqlCount, page);
		return "json";
	}
	
	@SuppressWarnings("unchecked")
	public String queryqhzuis() throws Exception {		
		
		if(!userflag()){return "json";}
		
		String sql="select b.DIST_CD,b.QIANZUI_ID,a.PROVINCE_NAME||a.CITY_NAME||a.COUNTY_NAME,b.FIX,b.FIX_ABBR,b.FIX_XZ,b.FIX_SMP,b.FIX_FLAG";
		String sqlCount="select count(*) ";
		String sqlfrom=" from CP_BASE_ORG_DISTRICT a,CP_WH_QHZUI b ";
		
		String sqlwhere=" where a.district_code=b.dist_cd ";
		String sqlorder=" order by b.DIST_CD,b.FIX_FLAG,b.FIX";

		
		if(!FIX.equals("null") && FIX.length()>0){
			sqlwhere = sqlwhere + " and  b.FIX like '%" + FIX + "%' ";
		}
		
		if(!CITY_ID.equals("null") && CITY_ID.length()>0){
			if(CITY_ID.substring(4, 6).equals("00")){
				sqlwhere = sqlwhere + " and a.district_code like '" + CITY_ID.substring(0, 4) + "%'";
			}else{
				sqlwhere = sqlwhere + " and a.district_code like '" + CITY_ID + "%'";
			}	
		}else if(!PROVINCE_ID.equals("null") && PROVINCE_ID.length()>0){
			sqlwhere = sqlwhere + " and a.district_code like '" + PROVINCE_ID.substring(0, 2) + "%'";
		}
		
		if(!FIX_FLAGNAME.equals("null") && FIX_FLAGNAME.length()>0){
			sqlwhere = sqlwhere + " and  b.FIX_FLAG = '" + FIX_FLAGNAME + "' ";
		}

		sql=sql+sqlfrom+sqlwhere+sqlorder;
		sqlCount=sqlCount+sqlfrom+sqlwhere;
		
		page.setPageSize(50);
		
		if(pageNo!=0){
			page.setPageNo(pageNo);
		}
		page=addrQueryDao.getBeanQueryQueryqhzuis(sql, sqlCount, page);
		return "json";
	}
	
	@SuppressWarnings("unchecked")
	public String querytreetsqfhdb() throws Exception {		
		
		if(!userflag()){return "json";}
		
		String sql="select e.STRT_ID,e.DIST_CD,g.PROVINCE_NAME||g.CITY_NAME||g.COUNTY_NAME,e.STRT1_NAME||e.STRT2_NAME||e.STRT3_NAME||e.STRT4_NAME||e.STRT5_NAME,e.DATA_FLAG,e.POST_CD,e.STRT1_NAME,e.STRT2_NAME,e.STRT3_NAME,e.STRT4_NAME,e.STRT5_NAME ";
		String sqlCount="select count(*) ";
		
		String sqlfrom=" ";
		

		if(IS_CHAXUNLX.equals("0")){
			sqlfrom=" from CP_BASE_ORG_DISTRICT g," +
			" (select a.* from cp_mk_adr_street_" + PROVINCE_ID.substring(0,2) + " a, " +
			" (select strt1_name||strt2_name||strt3_name||strt4_name||strt5_name all_name from cp_mk_adr_street_"  + PROVINCE_ID.substring(0,2) +
			" group by strt1_name||strt2_name||strt3_name||strt4_name||strt5_name having count(*) > 1 )  b  " +
			" where a.strt1_name||a.strt2_name||a.strt3_name||a.strt4_name||a.strt5_name = b.all_name) e ";
			if(!DispCommon.isMunicipalitiesID(PROVINCE_ID.substring(0, 2))){
				sqlfrom=" from CP_BASE_ORG_DISTRICT g," +
				" (select a.* from cp_mk_adr_street_" + PROVINCE_ID.substring(0,2) + " a, " +
				" (select strt1_name||strt2_name||strt3_name||strt4_name||strt5_name all_name,substr(dist_cd,0,4) dist_cd from  cp_mk_adr_street_"  + PROVINCE_ID.substring(0,2) +
				" group by strt1_name||strt2_name||strt3_name||strt4_name||strt5_name,substr(dist_cd,0,4) having count(*) > 1 )  b " +
				" where a.strt1_name||a.strt2_name||a.strt3_name||a.strt4_name||a.strt5_name = b.all_name and substr(a.dist_cd,0,4)=b.dist_cd ) e ";
			}
		}else if(IS_CHAXUNLX.equals("1")){
			sqlfrom=" from CP_BASE_ORG_DISTRICT g," +
			" (select a.* from cp_mk_adr_street_" + PROVINCE_ID.substring(0,2) + " a, " +
			" (select distinct t.dist_cd,t.strt1_name||t.strt2_name strt_name from cp_mk_adr_street_" + PROVINCE_ID.substring(0,2) + " t where t.strt2_name is not null) b" +
			" where a.strt1_name = b.strt_name and a.dist_cd = b.dist_cd" +
			" union" +
			" select c.* from cp_mk_adr_street_" + PROVINCE_ID.substring(0,2) + " c," +
			" (select b.strt_name from cp_mk_adr_street_" + PROVINCE_ID.substring(0,2) + " a," +
			" (select distinct t.dist_cd,t.strt1_name||t.strt2_name strt_name from cp_mk_adr_street_" + PROVINCE_ID.substring(0,2) + " t where t.strt2_name is not null) b" +
			" where a.strt1_name = b.strt_name and a.dist_cd = b.dist_cd) d" +
			" where c.strt1_name||c.strt2_name = d.strt_name) e ";
		}else if(IS_CHAXUNLX.equals("2")){
			sqlfrom=" from CP_BASE_ORG_DISTRICT g," +
			" (select * from cp_mk_adr_street_" + PROVINCE_ID.substring(0,2) + " a " +
			" where 1=1 " +
			" and " +
			" (" +
			" strt1_name like '%镇%村%'  " +
			" or strt2_name like '%镇%村%' " +
			" or strt1_name like '%乡%村%' " +
			" or strt2_name like '%乡%村%' " +
			" or strt1_name like '%区%镇%' " +
			" or strt2_name like '%区%镇%' " +
			" or strt1_name like '%区%乡%' " +
			" or strt2_name like '%区%乡%' " +
			" or length(strt1_name) >= 6 " +
			" or length(strt2_name) >= 6 " +
			" or length(strt3_name) >= 6 " +
			" or length(strt4_name) >= 6 " +
			" or length(strt5_name) >= 6 " +
			" " +
			" ) " +
			" ) e ";
		}else if(IS_CHAXUNLX.equals("3")){
			sqlfrom=" from CP_BASE_ORG_DISTRICT g," +
			" (select * from cp_mk_adr_street_" + PROVINCE_ID.substring(0,2) + " a " +
			" where 1=1 " +
			" and " +
			" (" +
			"  regexp_like(a.strt1_name||a.strt2_name||a.strt3_name||a.strt4_name||a.strt5_name,'[0-9][室]$') " +
			"  or regexp_like(a.strt1_name||a.strt2_name||a.strt3_name||a.strt4_name||a.strt5_name,'[0-9][层]$') " +
			"  or regexp_like(a.strt1_name||a.strt2_name||a.strt3_name||a.strt4_name||a.strt5_name,'[0-9][楼]$') " +
			"  or regexp_like(a.strt1_name||a.strt2_name||a.strt3_name||a.strt4_name||a.strt5_name,'[0-9]$') " +
			"  or regexp_like(a.strt1_name||a.strt2_name||a.strt3_name||a.strt4_name||a.strt5_name,'[a-z]$') " +
			"  or regexp_like(a.strt1_name||a.strt2_name||a.strt3_name||a.strt4_name||a.strt5_name,'[A-Z]$') " +
			"  or regexp_like(a.strt1_name||a.strt2_name||a.strt3_name||a.strt4_name||a.strt5_name,'^([a-z]+|[A-Z]+|[0-9]+)$') " +
			" ) " +
			" ) e ";
		}
		
		
		if(getSessionUser().getRulLevel()==20){
			sqlfrom = sqlfrom + " ," +
			" (select STRT_ID from CP_WH_RL_PG_ST_" + PROVINCE_ID.substring(0,2) + "  where DM_PK_CODE = '" + getSessionUser().getUsDepartmentOffice() + "' " +
					"union select strt_id from CP_MK_ADR_STREET_" + PROVINCE_ID.substring(0,2) + " where  DATA_USER = '" + getSessionUser().getUsLoginId() + "'" +
							" ) f";
	  }
		
		
		String sqlwhere=" where g.district_code=e.dist_cd and e.IS_IGNORED is null";
		
		String sqlorder=" order by e.DIST_CD,e.strt1_name||e.strt2_name";
		if(IS_CHAXUNLX.equals("0")){
			sqlorder=" order by e.strt1_name||e.strt2_name||e.strt3_name||e.strt4_name||e.strt5_name";
		}
		
		if(getSessionUser().getRulLevel()==20){
			sqlwhere = sqlwhere + " and e.strt_id=f.strt_id " ;
		}
		
		if(!STREET_NAME.equals("null") && STREET_NAME.length()>0){
			sqlwhere = sqlwhere + " and  e.strt1_name||e.strt2_name||e.strt3_name||e.strt4_name||e.strt5_name like '%" + STREET_NAME + "%' ";
		}
		
		if(!COUNTY_ID.equals("null") && COUNTY_ID.length()>0){
			sqlwhere = sqlwhere + " and g.district_code='" + COUNTY_ID + "'";
		}else if(!CITY_ID.equals("null") && CITY_ID.length()>0){
			if(CITY_ID.substring(4, 6).equals("00")){
				sqlwhere = sqlwhere + " and g.district_code like '" + CITY_ID.substring(0, 4) + "%'";
			}else{
				sqlwhere = sqlwhere + " and g.district_code like '" + CITY_ID + "%'";
			}
		}else if(!PROVINCE_ID.equals("null") && PROVINCE_ID.length()>0){
			sqlwhere = sqlwhere + " and g.district_code like '" + PROVINCE_ID.substring(0, 2) + "%'";
		}

		sql=sql+sqlfrom+sqlwhere+sqlorder;
		sqlCount=sqlCount+sqlfrom+sqlwhere;
		
		page.setPageSize(50);
		
		if(pageNo!=0){
			page.setPageNo(pageNo);
		}
		page=addrQueryDao.getBeanQueryStreetsqfhdb(sql, sqlCount, page);
		return "json";
	}
	
	@SuppressWarnings("unchecked")
	public String queryrgpqpd() throws Exception {		
		
		if(!userflag()){return "json";}
		
		//String sql="select a.seqid,a.itemno,a.collect_date,a.collect_office,a.dist_cd,a.rec_post,a.rec_alladdr,a.rec_prov||a.rec_city,a.dt_pk_code,a.dm_pk_code,a.pg_pk_code,b.dt_name||b.dt_alias_name dt_name,c.dm_name,d.pg_name,a.PK_REMARK,a.POSTDIST,a.POSTSEG,e.dt_name||e.dt_alias_name POSTDIST_name,f.pg_name POSTSEG_NAME,a.PK_ALLADDR,a.REC_STREET,a.REC_ORG,a.ADDR_FLAG ";
		
		String sql="select a.seqid,a.itemno,a.collect_date,a.collect_office,a.dist_cd,a.rec_post,a.rec_alladdr,a.rec_prov||a.rec_city,a.dt_pk_code,a.dm_pk_code,a.pg_pk_code,b.dt_alias_name dt_name,c.dm_name,d.pg_name,a.PK_REMARK,a.POSTDIST,a.POSTSEG,e.dt_alias_name POSTDIST_name,f.pg_name POSTSEG_NAME,a.PK_ALLADDR,a.REC_STREET,a.REC_ORG,a.ADDR_FLAG,g.org_cname,'1' a,'2' b";
		
		String sqlCount="select count(*) ";
		
		//String sqlfrom=" from exg_clt_item_ope a,CP_WH_DISTRICT b,CP_WH_DEPARTMENT c, CP_WH_POSTSEG d , CP_WH_DISTRICT e, CP_WH_POSTSEG f" +
		
		//临时测试exg_clt_item_ope_rg
		String bstr = "exg_clt_item_ope_rg";
		if(!ISWH.equals("null") && ISWH.length()>0){
			if(ISWH.equals("2")){
				bstr = "exg_clt_item_ope_rg_his";
			}
		}
		
		String sqlfrom=" from " + bstr + " a,CP_WH_DISTRICT b,CP_WH_DEPARTMENT c, CP_WH_POSTSEG d , CP_WH_DISTRICT e, CP_WH_POSTSEG f" +
				
			",(select a.org_code,(b.province_name||a.org_cname) org_cname from res_org a,cp_base_org_district b where a.prov_code||'0000' = b.district_code(+)) g ";

		String sqlwhere=" where a.SR_FLAG is not null and a.dt_pk_code = b.dt_pk_code(+) and a.dm_pk_code = c.dm_pk_code(+) and a.pg_pk_code = d.pg_pk_code(+) and a.POSTDIST = to_char(e.dt_pk_code(+)) and a.POSTSEG = to_char(f.pg_pk_code(+)) and a.collect_office = g.org_code(+) ";
		String sqlorder=" order by a.IS_DISTRI desc,substr(a.collect_date,0,8) desc,a.rec_alladdr,a.POSTDISTTYPE desc";

		if(!PAIXU.equals("null") && PAIXU.length()>0){
			sqlorder = "order by " + PAIXU;
		}
		
		if(!ITEMNO.equals("null") && ITEMNO.length()>0){
			sqlwhere = sqlwhere + " and  a.ITEMNO like '%" + ITEMNO + "%' ";
		}
		
		if(!REC_ALLADDR.equals("null") && REC_ALLADDR.length()>0){
			sqlwhere = sqlwhere + " and  a.REC_ALLADDR||a.REC_STREET||a.REC_ORG like '%" + REC_ALLADDR + "%' ";
		}
		
		if(!COUNTY_ID.equals("null") && COUNTY_ID.length()>0){
			sqlwhere = sqlwhere + " and a.dist_cd='" + COUNTY_ID + "'";
		}else if(!CITY_ID.equals("null") && CITY_ID.length()>0){
			if(CITY_ID.substring(4, 6).equals("00")){
				sqlwhere = sqlwhere + " and a.dist_cd like '" + CITY_ID.substring(0, 4) + "%'";
			}else{
				sqlwhere = sqlwhere + " and a.dist_cd like '" + CITY_ID + "%'";
			}
		}else if(!PROVINCE_ID.equals("null") && PROVINCE_ID.length()>0){
			sqlwhere = sqlwhere + " and a.dist_cd like '" + PROVINCE_ID.substring(0, 2) + "%'";
		}
		
		if(!ISSR_FLAG.equals("null") && ISSR_FLAG.length()>0){
			if(ISSR_FLAG.equals("1")){
				//4月收容是1,6月是2
				sqlwhere = sqlwhere + " and  a.SR_FLAG = 2 ";
			}else if(ISSR_FLAG.equals("0")){
				sqlwhere = sqlwhere + " and  a.POSTSEG is  null ";
			}
		}
		
		if(!ISWH.equals("null") && ISWH.length()>0){
			if(ISWH.equals("1")){
				sqlwhere = sqlwhere + " and  a.PK_TIME is not null ";
			}else if(ISWH.equals("0")){
				sqlwhere = sqlwhere + " and a.PK_TIME is  null ";
			}
		}
		
		if(!ISDT.equals("null") && ISDT.length()>0){
			if(ISDT.equals("1")){
				sqlwhere = sqlwhere + " and  a.DT_PK_CODE is not null ";
			}else if(ISDT.equals("0")){
				sqlwhere = sqlwhere + " and a.DT_PK_CODE is  null ";
			}
		}
		
		if(!ISPG.equals("null") && ISPG.length()>0){
			if(ISPG.equals("1")){
				sqlwhere = sqlwhere + " and  a.PG_PK_CODE is not null ";
				//sqlwhere = sqlwhere + " and  a.PK_TIME is not null ";
			}else if(ISPG.equals("0")){
				sqlwhere = sqlwhere + " and a.PG_PK_CODE is  null ";
			}
		}
		
		if(!IS_DISTRI.equals("null") && IS_DISTRI.length()>0){
			if(IS_DISTRI.equals("1")){
				sqlwhere = sqlwhere + " and a.IS_DISTRI = 1 ";
			}else if(IS_DISTRI.equals("0")){
				sqlwhere = sqlwhere + " and a.IS_DISTRI = 0 ";
			}
		}
		
		if(!ISPOSTDISTTYPE.equals("null") && ISPOSTDISTTYPE.length()>0){
			if(ISPOSTDISTTYPE.equals("3")){
				sqlwhere = sqlwhere + " and a.POSTDISTTYPE = 3 ";
			}else if(ISPOSTDISTTYPE.equals("2")){
				sqlwhere = sqlwhere + " and a.POSTDISTTYPE = 2 ";
			}else if(ISPOSTDISTTYPE.equals("1")){
				sqlwhere = sqlwhere + " and a.POSTDISTTYPE = 1 ";
			}
		}
		
		if(!IS_YOUDIZHI.equals("null") && IS_YOUDIZHI.length()>0){
			if(IS_YOUDIZHI.equals("1")){
				sqlwhere = sqlwhere + " and ((a.ADDR_FLAG = 1 and a.rec_alladdr is not null) or (a.ADDR_FLAG = 0 and a.REC_STREET||a.REC_ORG is not null)) ";
			}else if(IS_YOUDIZHI.equals("0")){
				sqlwhere = sqlwhere + " and ((a.ADDR_FLAG = 1 and a.rec_alladdr is null) or (a.ADDR_FLAG = 0 and a.REC_STREET||a.REC_ORG is  null)) ";
			}
		}
		
		if(!ITEM_DATESS.equals("null") && ITEM_DATESS.length()>0){
			sqlwhere = sqlwhere + " and substr(a.collect_date,0,10) >= '" + ITEM_DATESS + "'";
		}
		
		if(!ITEM_DATESE.equals("null") && ITEM_DATESE.length()>0){
			sqlwhere = sqlwhere + " and substr(a.collect_date,0,10) <= '" + ITEM_DATESE + "'";
		}

		sql=sql+sqlfrom+sqlwhere+sqlorder;
		sqlCount=sqlCount+sqlfrom+sqlwhere;
		
		page.setPageSize(100);
		
		if(pageNo!=0){
			page.setPageNo(pageNo);
		}
		page=addrQueryDao.getBeanQueryrgpqpd(sql, sqlCount, page);
		return "json";
	}
	
	@SuppressWarnings("unchecked")
	public String querycksr() throws Exception {		
		
		if(!userflag()){return "json";}
		
		//String sql="select a.seqid,a.itemno,a.collect_date,a.collect_office,a.dist_cd,a.rec_post,a.rec_alladdr,a.rec_prov||a.rec_city,a.dt_pk_code,a.dm_pk_code,a.pg_pk_code,b.dt_name||b.dt_alias_name dt_name,c.dm_name,d.pg_name,a.PK_REMARK,a.POSTDIST,a.POSTSEG,e.dt_name||e.dt_alias_name POSTDIST_name,f.pg_name POSTSEG_NAME,a.PK_ALLADDR,a.REC_STREET,a.REC_ORG,a.ADDR_FLAG ";
		
		String sql="select a.seqid,a.itemno,a.collect_date,a.collect_office,a.dist_cd,a.rec_post,a.rec_alladdr,a.rec_prov||a.rec_city,a.PK_ALLADDR,a.REC_STREET,a.REC_ORG,a.ADDR_FLAG,g.ORG_CNAME,(select dt_name from cp_wh_district  where to_char(dt_pk_code) = postdist ) as postdist_name ";
		
		String sqlCount="select count(*) ";
		
		//String sqlfrom=" from exg_clt_item_ope a,CP_WH_DISTRICT b,CP_WH_DEPARTMENT c, CP_WH_POSTSEG d , CP_WH_DISTRICT e, CP_WH_POSTSEG f" +
		
		//临时测试exg_clt_item_ope_rg
		//String sqlfrom=" from exg_clt_item_ope a,RES_ORG g ";
		String sqlfrom="from exg_clt_item_ope a,";  
		//String sqlfrom="from exg_clt_item_ope a,";

		String sqlwhere=" where SR_FLAG = 2 and  a.collect_office = g.org_code ";
		String sqlorder=" order by g.ORG_CNAME,a.IS_DISTRI desc,substr(a.collect_date,0,8) desc,a.rec_alladdr";

		/*if(!COUNTY_ID.equals("null") && COUNTY_ID.length()>0){
			sqlwhere = sqlwhere + " and g.COUNTY_CODE='" + COUNTY_ID + "'";
		}else if(!CITY_ID.equals("null") && CITY_ID.length()>0){
			if(CITY_ID.substring(4, 6).equals("00")){
				sqlwhere = sqlwhere + " and substr(g.CITY_CODE,0,4) = '" + CITY_ID.substring(0, 4) + "' ";
			}else{
				sqlwhere = sqlwhere + " and substr(g.CITY_CODE,0,4) = '" + CITY_ID + "' ";
			}
		}else if(!PROVINCE_ID.equals("null") && PROVINCE_ID.length()>0){
			sqlwhere = sqlwhere + " and g.PROV_CODE = '" + PROVINCE_ID.substring(0, 2) + "' ";
		}*/
		if(!COUNTY_ID.equals("null") && COUNTY_ID.length()>0){
			//sqlwhere = sqlwhere + " and g.COUNTY_CODE='" + COUNTY_ID + "'";
			sqlfrom = sqlfrom +"(select org_code,org_cname from res_org where county_code ='"+COUNTY_ID+"') g";
		}else if(!CITY_ID.equals("null") && CITY_ID.length()>0){
			if(CITY_ID.substring(4, 6).equals("00")){
				//sqlwhere = sqlwhere + " and substr(g.CITY_CODE,0,4) = '" + CITY_ID.substring(0, 4) + "' ";
				sqlfrom = sqlfrom +"(select org_code,org_cname from res_org where substr(CITY_CODE,0,4) ='"+CITY_ID.substring(0, 4)+"') g";
			}else{
				//sqlwhere = sqlwhere + " and substr(g.CITY_CODE,0,4) = '" + CITY_ID + "' ";
				sqlfrom = sqlfrom +"(select org_code,org_cname from res_org where substr(CITY_CODE,0,4) ='"+CITY_ID+"') g";
				
			}
		}else if(!PROVINCE_ID.equals("null") && PROVINCE_ID.length()>0){
			//sqlwhere = sqlwhere + " and g.PROV_CODE = '" + PROVINCE_ID.substring(0, 2) + "' ";
			sqlfrom = sqlfrom +"(select org_code,org_cname from res_org where PROV_CODE ='"+PROVINCE_ID.substring(0, 2)+"') g";
		}
		
		
		if(!ISSJN.equals("null") && ISSJN.length()>0){
			if(ISSJN.equals("1")){
				sqlwhere = sqlwhere + " and  substr(a.dist_cd,0,2) = '" + PROVINCE_ID.substring(0, 2) + "' ";
			}else if(ISSJN.equals("2")){
				sqlwhere = sqlwhere + " and  substr(a.dist_cd,0,2) != '" + PROVINCE_ID.substring(0, 2) + "' ";
			}
		}
		
		if(!COUNTY_IDJD.equals("null") && COUNTY_IDJD.length()>0){
			sqlwhere = sqlwhere + " and a.dist_cd='" + COUNTY_IDJD + "' and a.dist_cd >= '"+COUNTY_IDJD.substring(0, 2)+"0000"+"' and a.dist_cd <= '"+COUNTY_IDJD.substring(0, 2)+"9999"+"' ";
		}else if(!CITY_IDJD.equals("null") && CITY_IDJD.length()>0){
			if(CITY_IDJD.substring(4, 6).equals("00")){
				sqlwhere = sqlwhere + " and substr(a.dist_cd,0,4) = '" + CITY_IDJD.substring(0, 4) + "' and a.dist_cd >= '"+CITY_IDJD.substring(0, 2)+"0000"+"' and a.dist_cd <= '"+CITY_IDJD.substring(0, 2)+"9999"+"' ";
			}else{
				sqlwhere = sqlwhere + " and substr(a.dist_cd,0,4) = '" + CITY_IDJD + "' and a.dist_cd >= '"+CITY_IDJD.substring(0, 2)+"0000"+"' and a.dist_cd <= '"+CITY_IDJD.substring(0, 2)+"9999"+"' ";
			}
		}else if(!PROVINCE_IDJD.equals("null") && PROVINCE_IDJD.length()>0){
			sqlwhere = sqlwhere + " and substr(a.dist_cd,0,2) = '" + PROVINCE_IDJD.substring(0, 2) + "' and a.dist_cd >= '"+PROVINCE_IDJD.substring(0, 2)+"0000"+"' and a.dist_cd <= '"+PROVINCE_IDJD.substring(0, 2)+"9999"+"' ";
		}
		
		if(!SOURCE.equals("null") && SOURCE.length()>0){
			sqlwhere = sqlwhere + " and  a.source = '"+SOURCE+"' ";
		}
		if(!IS_DISTRI.equals("null") && IS_DISTRI.length()>0){
			sqlwhere = sqlwhere + " and  a.is_distri = '"+IS_DISTRI+"' ";
		}
		if(!COLLECT_DATE.equals("null") && COLLECT_DATE.length()>0){
			sqlwhere = sqlwhere + " and  a.collect_date >= replace('"+COLLECT_DATE+"0000','-')";
		}
		if(!COLLECT_DATEEND.equals("null") && COLLECT_DATEEND.length()>0){
			sqlwhere = sqlwhere + " and  a.collect_date <= replace('"+COLLECT_DATEEND+"2359','-')";
		}
		
		
		if(!ORG_NAME.equals("null") && ORG_NAME.length()>0){
			sqlwhere = sqlwhere + " and  g.ORG_CNAME like '%" + ORG_NAME + "%' ";
		}
		sqlwhere = sqlwhere + " and a.rec_alladdr||a.REC_STREET||a.REC_ORG is not null ";
		sql=sql+sqlfrom+sqlwhere+sqlorder;
		sqlCount=sqlCount+sqlfrom+sqlwhere;
		
		page.setPageSize(50);
		
		if(pageNo!=0){
			page.setPageNo(pageNo);
		}
		page=addrQueryDao.getBeanQuerycksr(sql, sqlCount, page);
		return "json";
	}
	
	@SuppressWarnings("unchecked")
	public String exportcksr() throws Exception {		
		
		if(!userflag()){return "json";}
		
		
		String sql="select a.seqid,a.itemno,a.collect_date,a.collect_office,a.dist_cd,a.rec_post,a.rec_alladdr,a.rec_prov||a.rec_city,a.PK_ALLADDR,a.REC_STREET,a.REC_ORG,a.ADDR_FLAG,g.ORG_CNAME,(select dt_name from cp_wh_district  where dt_pk_code = postdist ) as postdist_name ";
		
		
		String sqlfrom="from exg_clt_item_ope a,";

		String sqlwhere=" where SR_FLAG != 1 and  a.collect_office = g.org_code ";
		String sqlorder=" order by g.ORG_CNAME,a.IS_DISTRI desc,substr(a.collect_date,0,8) desc,a.rec_alladdr";

		/*if(!COUNTY_ID.equals("null") && COUNTY_ID.length()>0){
			sqlwhere = sqlwhere + " and g.COUNTY_CODE='" + COUNTY_ID + "'";
		}else if(!CITY_ID.equals("null") && CITY_ID.length()>0){
			if(CITY_ID.substring(4, 6).equals("00")){
				sqlwhere = sqlwhere + " and substr(g.CITY_CODE,0,4) = '" + CITY_ID.substring(0, 4) + "' ";
			}else{
				sqlwhere = sqlwhere + " and substr(g.CITY_CODE,0,4) = '" + CITY_ID + "' ";
			}
		}else if(!PROVINCE_ID.equals("null") && PROVINCE_ID.length()>0){
			sqlwhere = sqlwhere + " and g.PROV_CODE = '" + PROVINCE_ID.substring(0, 2) + "' ";
		}*/
		if(!COUNTY_ID.equals("null") && COUNTY_ID.length()>0){
			//sqlwhere = sqlwhere + " and g.COUNTY_CODE='" + COUNTY_ID + "'";
			sqlfrom = sqlfrom +"(select org_code,org_cname from res_org where county_code ='"+COUNTY_ID+"') g";
		}else if(!CITY_ID.equals("null") && CITY_ID.length()>0){
			if(CITY_ID.substring(4, 6).equals("00")){
				//sqlwhere = sqlwhere + " and substr(g.CITY_CODE,0,4) = '" + CITY_ID.substring(0, 4) + "' ";
				sqlfrom = sqlfrom +"(select org_code,org_cname from res_org where substr(CITY_CODE,0,4) ='"+CITY_ID.substring(0, 4)+"') g";
			}else{
				//sqlwhere = sqlwhere + " and substr(g.CITY_CODE,0,4) = '" + CITY_ID + "' ";
				sqlfrom = sqlfrom +"(select org_code,org_cname from res_org where substr(CITY_CODE,0,4) ='"+CITY_ID+"') g";
				
			}
		}else if(!PROVINCE_ID.equals("null") && PROVINCE_ID.length()>0){
			//sqlwhere = sqlwhere + " and g.PROV_CODE = '" + PROVINCE_ID.substring(0, 2) + "' ";
			sqlfrom = sqlfrom +"(select org_code,org_cname from res_org where PROV_CODE ='"+PROVINCE_ID.substring(0, 2)+"') g";
		}
		
		
		if(!ISSJN.equals("null") && ISSJN.length()>0){
			if(ISSJN.equals("1")){
				sqlwhere = sqlwhere + " and  substr(a.dist_cd,0,2) = '" + PROVINCE_ID.substring(0, 2) + "' ";
			}else if(ISSJN.equals("2")){
				sqlwhere = sqlwhere + " and  substr(a.dist_cd,0,2) != '" + PROVINCE_ID.substring(0, 2) + "' ";
			}
		}
		
		if(!COUNTY_IDJD.equals("null") && COUNTY_IDJD.length()>0){
			sqlwhere = sqlwhere + " and a.dist_cd='" + COUNTY_IDJD + "' and a.dist_cd >= '"+COUNTY_IDJD.substring(0, 2)+"0000"+"' and a.dist_cd <= '"+COUNTY_IDJD.substring(0, 2)+"9999"+"' ";
		}else if(!CITY_IDJD.equals("null") && CITY_IDJD.length()>0){
			if(CITY_IDJD.substring(4, 6).equals("00")){
				sqlwhere = sqlwhere + " and substr(a.dist_cd,0,4) = '" + CITY_IDJD.substring(0, 4) + "' and a.dist_cd >= '"+CITY_IDJD.substring(0, 2)+"0000"+"' and a.dist_cd <= '"+CITY_IDJD.substring(0, 2)+"9999"+"' ";
			}else{
				sqlwhere = sqlwhere + " and substr(a.dist_cd,0,4) = '" + CITY_IDJD + "' and a.dist_cd >= '"+CITY_IDJD.substring(0, 2)+"0000"+"' and a.dist_cd <= '"+CITY_IDJD.substring(0, 2)+"9999"+"' ";
			}
		}else if(!PROVINCE_IDJD.equals("null") && PROVINCE_IDJD.length()>0){
			sqlwhere = sqlwhere + " and substr(a.dist_cd,0,2) = '" + PROVINCE_IDJD.substring(0, 2) + "' and a.dist_cd >= '"+PROVINCE_IDJD.substring(0, 2)+"0000"+"' and a.dist_cd <= '"+PROVINCE_IDJD.substring(0, 2)+"9999"+"' ";
		}
		
		if(!SOURCE.equals("null") && SOURCE.length()>0){
			sqlwhere = sqlwhere + " and  a.source = '"+SOURCE+"' ";
		}
		if(!IS_DISTRI.equals("null") && IS_DISTRI.length()>0){
			sqlwhere = sqlwhere + " and  a.is_distri = '"+IS_DISTRI+"' ";
		}
		if(!COLLECT_DATE.equals("null") && COLLECT_DATE.length()>0){
			sqlwhere = sqlwhere + " and  a.collect_date >= replace('"+COLLECT_DATE+"0000','-')";
		}
		if(!COLLECT_DATEEND.equals("null") && COLLECT_DATEEND.length()>0){
			sqlwhere = sqlwhere + " and  a.collect_date <= replace('"+COLLECT_DATEEND+"2359','-')";
		}
		
		
		if(!ORG_NAME.equals("null") && ORG_NAME.length()>0){
			sqlwhere = sqlwhere + " and  g.ORG_CNAME like '%" + ORG_NAME + "%' ";
		}
		sqlwhere = sqlwhere + " and a.rec_alladdr||a.REC_STREET||a.REC_ORG is not null ";
		
		sql=sql+sqlfrom+sqlwhere+sqlorder;	
		EXPORTALLPATH=addrQueryDao.exportcksr(sql);
		
		if(pageNo!=0){
			page.setPageNo(pageNo);
		}
		
		return "json";
	}
	
	@SuppressWarnings("unchecked")
	public String queryzshf() throws Exception {		
		
		if(!userflag()){return "json";}
		
		String sql="select a.RL_PG_ST_ID,a.DIST_CD,b.PROVINCE_NAME||b.CITY_NAME||b.COUNTY_NAME,a.STRT_ID," +
				"c.STRT1_NAME||c.STRT2_NAME||c.STRT3_NAME||c.STRT4_NAME||c.STRT5_NAME,a.POST_CODE," +
				"a.DT_PK_CODE,d.DT_NAME||d.DT_ALIAS_NAME, " +
				"a.DM_PK_CODE,e.DM_NAME, " +
				"a.PG_PK_CODE,f.PG_NAME, " +
				"a.PREFIX,a.START_NUM,a.END_NUM,a.PREFIX||a.START_NUM||'-'||a.END_NUM||a.SUFFIX,a.SUFFIX, " +
				"a.RSDNBLDG_ID,g.RSDNBLDG_NAME, " +
				"a.ORG_ID,h.ORG_NAME," +
				"a.FLAG,a.DATA_SOURCE,a.VERIFY_FLAG,a.MOD_DATE,a.OPERATOR "
				;
		String sqlCount="select count(*) ";
		String sqlfrom=" from CP_WH_RL_PG_ST_" + PROVINCE_ID.substring(0,2) + " a," +
				"CP_BASE_ORG_DISTRICT b," +
				"CP_MK_ADR_STREET_" + PROVINCE_ID.substring(0,2) + " c," +
				"CP_WH_DISTRICT d," +
				"CP_WH_DEPARTMENT e," +
				"CP_WH_POSTSEG f," +
				"CP_MK_ADR_BLDGRSDNS_" + PROVINCE_ID.substring(0,2) + " g," +
				"CP_MK_ORG_ORGANIZATION_" + PROVINCE_ID.substring(0,2) + " h";
		
		
		String sqlwhere=" where a.dist_cd=b.district_code(+) " +
				"and a.STRT_ID=c.STRT_ID(+) " + 
				"and a.DT_PK_CODE=d.DT_PK_CODE(+) " + 
				"and a.DM_PK_CODE=e.DM_PK_CODE(+) " + 
				"and a.PG_PK_CODE=f.PG_PK_CODE(+) " + 
				"and a.RSDNBLDG_ID=g.RSDNBLDG_ID(+) " + 
				"and a.ORG_ID=h.ORG_ID(+) ";

		/*
		if(getSessionUser().getUsDistrictOffice()!=null){
			sqlwhere=sqlwhere + "and a.DT_PK_CODE=" + getSessionUser().getUsDistrictOffice() + " ";
		}
		*/
		if(getSessionUser().getUsDepartmentOffice()!=null){
			sqlwhere=sqlwhere + "and a.DM_PK_CODE=" + getSessionUser().getUsDepartmentOffice() + " ";
		}
		String sqlorder=" order by a.DIST_CD,c.strt1_name||c.strt2_name||c.strt3_name||c.strt4_name||c.strt5_name,a.RSDNBLDG_ID desc,a.ORG_ID desc,a.START_NUM";
		
		
		if(!COUNTY_ID.equals("null") && COUNTY_ID.length()>0){
			sqlwhere = sqlwhere + " and a.DIST_CD='" + COUNTY_ID + "'";
		}else if(!CITY_ID.equals("null") && CITY_ID.length()>0){
			if(CITY_ID.substring(4, 6).equals("00")){
				sqlwhere = sqlwhere + " and a.DIST_CD like '" + CITY_ID.substring(0, 4) + "%'";
			}else{
				sqlwhere = sqlwhere + " and a.DIST_CD like '" + CITY_ID + "%'";
			}
		}else if(!PROVINCE_ID.equals("null") && PROVINCE_ID.length()>0){
			sqlwhere = sqlwhere + " and a.DIST_CD like '" + PROVINCE_ID.substring(0, 2) + "%'";
		}
		
		if(!DT_PK_CODEZS.equals("null") && DT_PK_CODEZS.length()>0){
			sqlwhere = sqlwhere + " and a.DT_PK_CODE ='" + DT_PK_CODEZS + "'";
		}
		
		if(!STREET_NAME.equals("null") && STREET_NAME.length()>0){
			sqlwhere = sqlwhere + " and c.STRT1_NAME||c.STRT2_NAME||c.STRT3_NAME||c.STRT4_NAME||c.STRT5_NAME like '%" + STREET_NAME + "%'";
		}
		
		if(!ISLX.equals("null") && ISLX.length()>0){
			if(ISLX.equals("1")){
				//4月收容是1,6月是2
				sqlwhere = sqlwhere + " and  a.RSDNBLDG_ID||a.ORG_ID is null ";
			}else if(ISLX.equals("2")){
				sqlwhere = sqlwhere + " and  a.RSDNBLDG_ID||a.ORG_ID is not null ";
			}else if(ISLX.equals("3")){
				sqlwhere = sqlwhere + " and  a.RSDNBLDG_ID is not null and a.ORG_ID is null ";
			}else if(ISLX.equals("4")){
				sqlwhere = sqlwhere + " and  a.ORG_ID is not null and a.RSDNBLDG_ID is null  ";
			}
		}
		

		sql=sql+sqlfrom+sqlwhere+sqlorder;
		sqlCount=sqlCount+sqlfrom+sqlwhere;
		
		page.setPageSize(50);
		
		if(pageNo!=0){
			page.setPageNo(pageNo);
		}
		page=addrQueryDao.getBeanQueryZshf(sql, sqlCount, page);
		return "json";
	}
	
	
	@SuppressWarnings("unchecked")
	public String querygonggao() throws Exception {		
		
		if(!userflag()){return "json";}
		

		String sql="select * ";
		String sqlCount="select count(*) ";
		String sqlfrom=" from ( " +
				" select cast('机构代码已变动,请修改' as varchar2(50)) gzcx_exzt,DM_NAME||'≠'||OFFICE_CODE||'('||PROVINCE_NAME||CITY_NAME||COUNTY_NAME||')' gzcx_excx,'0' gzcx_exps,3 cxor,'0' GZCX_EXRQ,0 GZCX_ID " +
				" from cp_wh_department,CP_BASE_ORG_DISTRICT where 1=1 " +
				" and city_code = DISTRICT_CODE " +
				" and office_code is not null " +
				" and office_code not in " +
				" ( select ems_county_code from CP_BASE_EMS_OFFICE_ASSIS )";
		
		if(getSessionUser().getRulLevel()==5 && getSessionUser().getUsProvinceOffice()!=null && getSessionUser().getUsProvinceOffice().length()>0){
			sqlfrom = sqlfrom + "  and city_code like '" + getSessionUser().getUsProvinceOffice().substring(0,2) + "%'";
		}
		
		if(getSessionUser().getRulLevel()>5 && getSessionUser().getUsCityOffice()!=null && getSessionUser().getUsCityOffice().length()>0){
			sqlfrom = sqlfrom + "  and city_code like '" + getSessionUser().getUsCityOffice() + "%'";
		}
				
		sqlfrom= sqlfrom + " ) a";
		String sqlwhere=" where 1=1 ";
		String sqlorder=" order by a.cxor,a.GZCX_EXRQ desc ";

		sql=sql+sqlfrom+sqlwhere+sqlorder;
		sqlCount=sqlCount+sqlfrom+sqlwhere;
		
		page.setPageSize(30);
		
		if(pageNo!=0){
			page.setPageNo(pageNo);
		}
		page=addrQueryDao.getBeanQuerygonggao(sql, sqlCount, page);
		
		return "json";
	}
	
	@SuppressWarnings("unchecked")
	public String queryfkqr() throws Exception {		
		
		if(!userflag()){return "json";}
		
		
		String sql="select a.SEQID,a.DIST_CD,b.PROVINCE_NAME||b.CITY_NAME||b.COUNTY_NAME," +
				"a.DT_PK_CODE,c.DT_NAME||c.DT_ALIAS_NAME," +
				"a.DM_PK_CODE,d.DM_NAME,a.PG_PK_CODE,e.PG_NAME," +
				"a.COLLECT_DATE,a.IS_PROCESS,a.ALLADDR,a.T_ALLADDR,a.PG_ALLADDR,a.PG_FLAG," +
				"a.SOURCE,a.SINTIME,a.SUSEFLAG,decode(a.SUSEFLAG,1,'确认','待确认')"
				;
		
		String sqlCount="select count(*) ";
		
		String sqlfrom=" from TB_SR_PROCESS a," +
				"cp_base_org_district b," +
				"cp_wh_district c," +
				"CP_WH_DEPARTMENT d, " +
				"cp_wh_postseg e ";
		
		
		String sqlwhere=" where a.SUSEFLAG = 0 " +
				"and a.dist_cd=b.district_code(+) " +
				"and a.DT_PK_CODE=c.DT_PK_CODE(+) " + 
				"and a.DM_PK_CODE=d.DM_PK_CODE(+) " + 
				"and a.PG_PK_CODE=e.PG_PK_CODE(+) ";

		/*
		if(getSessionUser().getUsDistrictOffice()!=null){
			sqlwhere=sqlwhere + "and a.DT_PK_CODE=" + getSessionUser().getUsDistrictOffice() + " ";
		}
		*/
		if(getSessionUser().getUsDepartmentOffice()!=null){
			sqlwhere=sqlwhere + "and a.DM_PK_CODE=" + getSessionUser().getUsDepartmentOffice() + " ";
		}
		String sqlorder=" order by a.SINTIME,a.DIST_CD,a.DT_PK_CODE,a.DM_PK_CODE,a.PG_PK_CODE,a.ALLADDR";
		
		
		if(!COUNTY_ID.equals("null") && COUNTY_ID.length()>0){
			sqlwhere = sqlwhere + " and a.DIST_CD='" + COUNTY_ID + "'";
		}else if(!CITY_ID.equals("null") && CITY_ID.length()>0){
			if(CITY_ID.substring(4, 6).equals("00")){
				sqlwhere = sqlwhere + " and a.DIST_CD like '" + CITY_ID.substring(0, 4) + "%'";
			}else{
				sqlwhere = sqlwhere + " and a.DIST_CD like '" + CITY_ID + "%'";
			}
		}else if(!PROVINCE_ID.equals("null") && PROVINCE_ID.length()>0){
			sqlwhere = sqlwhere + " and a.DIST_CD like '" + PROVINCE_ID.substring(0, 2) + "%'";
		}

		sql=sql+sqlfrom+sqlwhere+sqlorder;
		sqlCount=sqlCount+sqlfrom+sqlwhere;
		
		page.setPageSize(50);
		
		if(pageNo!=0){
			page.setPageNo(pageNo);
		}
		page=addrQueryDao.getBeanQueryfkqr(sql, sqlCount, page);
		return "json";
	}
	
	
	@SuppressWarnings("unchecked")
	public String queryrgpqpdn() throws Exception {		
		
		if(!userflag()){return "json";}
		
		String sql="select a.seqid,a.itemno,a.collect_date,a.collect_office,a.dist_cd,a.rec_post,a.rec_alladdr,a.rec_prov||a.rec_city||a.rec_county,a.dt_pk_code,a.dm_pk_code,a.pg_pk_code,b.dt_alias_name dt_name,c.dm_name,d.pg_name,a.PK_REMARK,a.POSTDIST,a.POSTSEG,e.dt_alias_name POSTDIST_name,f.pg_name POSTSEG_NAME,a.PK_ALLADDR,a.REC_STREET,a.REC_ORG,a.ADDR_FLAG,g.org_cname,to_char(a.pk_time,'yyyymmddhh24miss'),a.inserttime ";
		
		String sqlCount="select count(*) ";

		String bstr = "exg_clt_item_ope partition(p" + PROVINCE_ID.substring(0, 2) + ") ";
		
		String sqlfrom=" from " + bstr + " a,CP_WH_DISTRICT b,CP_WH_DEPARTMENT c, CP_WH_POSTSEG d , CP_WH_DISTRICT e, CP_WH_POSTSEG f" +
				
			",(select a.org_code,(b.province_name||a.org_cname) org_cname from res_org a,cp_base_org_district b where a.prov_code||'0000' = b.district_code(+)) g ";

		String sqlwhere=" where a.SR_FLAG is not null and a.dt_pk_code = b.dt_pk_code(+) and a.dm_pk_code = c.dm_pk_code(+) and a.pg_pk_code = d.pg_pk_code(+) and a.POSTDIST = to_char(e.dt_pk_code(+)) and a.POSTSEG = to_char(f.pg_pk_code(+)) and a.collect_office = g.org_code(+) ";
		String sqlorder=" order by a.IS_DISTRI desc,substr(a.collect_date,0,8) desc,a.rec_alladdr,a.POSTDISTTYPE desc";

		if(!ITEMNO.equals("null") && ITEMNO.length()>0){
			sqlwhere = sqlwhere + " and  a.ITEMNO like '%" + ITEMNO + "%' ";
		}
		
		if(!REC_ALLADDR.equals("null") && REC_ALLADDR.length()>0){
			sqlwhere = sqlwhere + " and  a.REC_ALLADDR||a.REC_STREET||a.REC_ORG like '%" + REC_ALLADDR + "%' ";
		}
		
		if(!COUNTY_ID.equals("null") && COUNTY_ID.length()>0){
			sqlwhere = sqlwhere + " and a.dist_cd='" + COUNTY_ID + "'";
		}else if(!CITY_ID.equals("null") && CITY_ID.length()>0){
			if(CITY_ID.substring(4, 6).equals("00")){
				sqlwhere = sqlwhere + " and a.dist_cd like '" + CITY_ID.substring(0, 4) + "%'";
				//sqlwhere = sqlwhere + " and a.dist_cd >= '" + CITY_ID.substring(0, 4) + "00' and a.dist_cd <= '" + CITY_ID.substring(0, 4) + "99'  ";
				
			}else{
				sqlwhere = sqlwhere + " and a.dist_cd like '" + CITY_ID + "%'";
				//sqlwhere = sqlwhere + " and a.dist_cd = '" + CITY_ID +  "'";
			}
		}else if(!PROVINCE_ID.equals("null") && PROVINCE_ID.length()>0){
			sqlwhere = sqlwhere + " and a.dist_cd like '" + PROVINCE_ID.substring(0, 2) + "%'";
			//sqlwhere = sqlwhere + " and a.dist_cd >= '" + PROVINCE_ID.substring(0, 2) + "0000' and a.dist_cd <= '" + PROVINCE_ID.substring(0, 2) + "9999'  ";
		}
		
		if(!ISSR_FLAG.equals("null") && ISSR_FLAG.length()>0){
			if(ISSR_FLAG.equals("1")){
				sqlwhere = sqlwhere + " and  a.SR_FLAG = 2 ";
			}else if(ISSR_FLAG.equals("0")){
				sqlwhere = sqlwhere + " and  a.POSTSEG is  null ";
			}
		}
		
		if(!ISWH.equals("null") && ISWH.length()>0){
			if(ISWH.equals("1")){
				sqlwhere = sqlwhere + " and  a.PK_TIME is not null ";
			}else if(ISWH.equals("0")){
				sqlwhere = sqlwhere + " and a.PK_TIME is  null ";
			}else if(ISWH.equals("2")){
				//已处理达标数据
				sqlwhere = sqlwhere + " and pk_time is not null " +
						" and (to_date(inserttime,'yyyymmddhh24miss')-to_date(substr(collect_date,0,8)||'000000','yyyymmddhh24miss') < 1)  " +
						" and (pk_time-to_date(substr(collect_date,0,8)||'000000','yyyymmddhh24miss')< 1.05) ";
			}else if(ISWH.equals("3")){
				//已处理未达标数据
				sqlwhere = sqlwhere + " and pk_time is not null " +
				" and (to_date(inserttime,'yyyymmddhh24miss')-to_date(substr(collect_date,0,8)||'000000','yyyymmddhh24miss') < 1)  " +
				" and (pk_time-to_date(substr(collect_date,0,8)||'000000','yyyymmddhh24miss')>= 1.05) ";
			}else if(ISWH.equals("4")){
				//已处理未达标数据
				sqlwhere = sqlwhere + " and pk_time is null " +
				" and (to_date(inserttime,'yyyymmddhh24miss')-to_date(substr(collect_date,0,8)||'000000','yyyymmddhh24miss') < 1)  " ;
			}
		}
		
		if(!ISDT.equals("null") && ISDT.length()>0){
			if(ISDT.equals("1")){
				sqlwhere = sqlwhere + " and  a.DT_PK_CODE is not null ";
			}else if(ISDT.equals("0")){
				sqlwhere = sqlwhere + " and a.DT_PK_CODE is  null ";
			}
		}
		
		if(!ISPG.equals("null") && ISPG.length()>0){
			if(ISPG.equals("1")){
				sqlwhere = sqlwhere + " and  a.PG_PK_CODE is not null ";
				//sqlwhere = sqlwhere + " and  a.PK_TIME is not null ";
			}else if(ISPG.equals("0")){
				sqlwhere = sqlwhere + " and a.PG_PK_CODE is  null ";
			}
		}
		
		if(!IS_DISTRI.equals("null") && IS_DISTRI.length()>0){
			if(IS_DISTRI.equals("1")){
				sqlwhere = sqlwhere + " and a.IS_DISTRI = 1 ";
			}else if(IS_DISTRI.equals("0")){
				sqlwhere = sqlwhere + " and a.IS_DISTRI = 0 ";
			}
		}
		
		if(!ISPOSTDISTTYPE.equals("null") && ISPOSTDISTTYPE.length()>0){
			if(ISPOSTDISTTYPE.equals("3")){
				sqlwhere = sqlwhere + " and a.POSTDISTTYPE = 3 ";
			}else if(ISPOSTDISTTYPE.equals("2")){
				sqlwhere = sqlwhere + " and a.POSTDISTTYPE = 2 ";
			}else if(ISPOSTDISTTYPE.equals("1")){
				sqlwhere = sqlwhere + " and a.POSTDISTTYPE = 1 ";
			}
		}
		
		if(!IS_YOUDIZHI.equals("null") && IS_YOUDIZHI.length()>0){
			if(IS_YOUDIZHI.equals("1")){
				sqlwhere = sqlwhere + " and ((a.ADDR_FLAG = 1 and a.rec_alladdr is not null) or (a.ADDR_FLAG = 0 and a.REC_STREET||a.REC_ORG is not null)) ";
			}else if(IS_YOUDIZHI.equals("0")){
				sqlwhere = sqlwhere + " and ((a.ADDR_FLAG = 1 and a.rec_alladdr is null) or (a.ADDR_FLAG = 0 and a.REC_STREET||a.REC_ORG is  null)) ";
			}
		}
		
		if(!ITEM_DATESS.equals("null") && ITEM_DATESS.length()>0){
			sqlwhere = sqlwhere + " and substr(a.collect_date,0,10) >= '" + ITEM_DATESS + "'";
		}
		
		if(!ITEM_DATESE.equals("null") && ITEM_DATESE.length()>0){
			sqlwhere = sqlwhere + " and substr(a.collect_date,0,10) <= '" + ITEM_DATESE + "'";
		}

		sql=sql+sqlfrom+sqlwhere+sqlorder;
		sqlCount=sqlCount+sqlfrom+sqlwhere;
		
		page.setPageSize(100);
		
		if(pageNo!=0){
			page.setPageNo(pageNo);
		}
		page=addrQueryDao.getBeanQueryrgpqpd(sql, sqlCount, page);
		return "json";
	}
}
