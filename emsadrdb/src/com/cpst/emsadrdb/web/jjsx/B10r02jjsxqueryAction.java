package com.cpst.emsadrdb.web.jjsx;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import com.cpst.core.orm.Page;
import com.cpst.core.web.struts2.BaseActionSupport;
import com.cpst.emsadrdb.dao.jjsx.JjsxQueryDao;
import com.cpst.emsadrdb.service.clfw.ClfwCommon;
import com.cpst.emsadrdb.service.clfw.ClfwExportall;
import com.cpst.emsadrdb.service.jjsx.JjsxCommon;
import com.cpst.emsadrdb.service.jjsx.JjsxExportall;

@ParentPackage("jsoncrud")
@Results( { @Result(type = "json", name = "json") })
public class B10r02jjsxqueryAction extends BaseActionSupport {

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
	private JjsxQueryDao jjsxQueryDao;
	
	private String  PCMC       ;
	private String  PCSX       ;


	public String getPCMC() {
		return PCMC;
	}

	public void setPCMC(String pcmc) {
		PCMC = JjsxCommon.commonsql_inj(pcmc);
	}

	public String getPCSX() {
		return PCSX;
	}

	public void setPCSX(String pcsx) {
		PCSX = JjsxCommon.commonsql_inj(pcsx);
	}
	
	public String LINEKEY;
	public String EXDATA;
	public String EXPORTALLPATH;
	public String LINEMAIN;
	public String LINENAME;
	
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

	public String getEXPORTALLPATH() {
		return EXPORTALLPATH;
	}

	public void setEXPORTALLPATH(String exportallpath) {
		EXPORTALLPATH = ClfwCommon.sql_inj(exportallpath);
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

	private String PROVINCE_ID;
	private String CITY_ID;
	private String CITY_NAME;
	private String COUNTY_ID;
	private String FLAGPZ;
	private String ORG_CODE;
	private String RANGE;
	private String CQUSXXUS;
	private String HXFLAG;
	
	
	public String getCITY_NAME() {
		return CITY_NAME;
	}

	public void setCITY_NAME(String city_name) {
		CITY_NAME = JjsxCommon.commonsql_inj(city_name);
	}

	public String getHXFLAG() {
		return HXFLAG;
	}

	public void setHXFLAG(String hxflag) {
		HXFLAG = JjsxCommon.commonsql_inj(hxflag);
	}

	public String getCQUSXXUS() {
		return CQUSXXUS;
	}

	public void setCQUSXXUS(String cqusxxus) {
		CQUSXXUS = JjsxCommon.commonsql_inj(cqusxxus);
	}

	

	public String getRANGE() {
		return RANGE;
	}

	public void setRANGE(String range) {
		RANGE = JjsxCommon.commonsql_inj(range);
	}

	public String getORG_CODE() {
		return ORG_CODE;
	}

	public void setORG_CODE(String org_code) {
		ORG_CODE = JjsxCommon.commonsql_inj(org_code);
	}

	public String getPROVINCE_ID() {
		return PROVINCE_ID;
	}

	public void setPROVINCE_ID(String province_id) {
		PROVINCE_ID = JjsxCommon.commonsql_inj(province_id);
	}

	public String getCITY_ID() {
		return CITY_ID;
	}

	public void setCITY_ID(String city_id) {
		CITY_ID = JjsxCommon.commonsql_inj(city_id);
	}

	public String getCOUNTY_ID() {
		return COUNTY_ID;
	}

	public void setCOUNTY_ID(String county_id) {
		COUNTY_ID = JjsxCommon.commonsql_inj(county_id);
	}

	public String getFLAGPZ() {
		return FLAGPZ;
	}

	public void setFLAGPZ(String flagpz) {
		FLAGPZ = JjsxCommon.commonsql_inj(flagpz);
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
	public String queryjjsxzbpc() throws Exception {
		
		if(!userflag()){return "json";}

		String sql = " select " +
		" a.SEQID,a.PCMC," +
		" a.CQUSXXUS,decode(a.CQUSXXUS,'1','城区','0','辖县','-') CQUSXXUSSTR," +
		" a.HXJZSJ,a.HXJZJG,a.FHXJZSJ,a.FHXJZJG," +
		" a.README," +
		" a.PCSX,decode(a.pcsx,'0','揽收','1','投递','-') PCSXSTR" ;
		String sqlCount = "select count(*) ";
		String sqlfrom = " from cjjsx_zb_pc a ";

		String sqlwhere = " where  1=1 ";
		String sqlorder = " order by pcmc";

		if (PCMC.length()>0) {
			sqlwhere = sqlwhere + " and a.PCMC like '%" + PCMC + "%'";
		}

		if (PCSX.length()>0) {
			sqlwhere = sqlwhere + " and a.PCSX = '" + PCSX + "'";
		}
		
		sql = sql + sqlfrom + sqlwhere + sqlorder;
		sqlCount = sqlCount + sqlfrom + sqlwhere;

		page.setPageSize(9999);
		if (pageNo != 0) {
			page.setPageNo(pageNo);
		}
		page = jjsxQueryDao.getBeansqueryjjsxzbpc(sql, sqlCount, page);
		return "json";
	}

	@SuppressWarnings("unchecked")
	public String queryjjsxsjspc() throws Exception {
		
		if(!userflag()){return "json";}
		
		String sql=" ";
		String sqlCount=" ";
		String sqlfrom=" ";
		String sqlwhere=" ";
		String sqlorder=" ";
		
		if(CITY_ID.length()>0){
			
			if(FLAGPZ.equals("1")){

				sql = " select " +
				" b.SEQID,b.PCMC," +
				" a.CQUSXXUS,decode(a.CQUSXXUS,'1','城区','0','辖县','-') CQUSXXUSSTR," +
				" a.HXJZSJ,a.HXJZJG,a.FHXJZSJ,a.FHXJZJG," +
				" b.README," +
				" b.pcmc||a.PCMC||decode(a.pcsx,'0','[揽收]','1','[投递]','[-]') ALLPCMC,city_name||county_name DISTNAME";
				
				sqlCount = "select count(*) ";
				
				sqlfrom = " from cjjsx_zb_pc a,CJJSX_SJ_SPC b,cp_base_org_district_yt c ";
				
				sqlwhere = " where   1=1 and a.seqid = b.zbpcseqid and b.distcd = c.district_code ";
				
				sqlorder = " order by ALLPCMC";
				
				
				sqlwhere = sqlwhere + " and b.distcd  = '" + CITY_ID + "'";
				

			}else if(FLAGPZ.equals("0")){

				sql = " select " +
				" a.SEQID,a.PCMC," +
				" a.CQUSXXUS,decode(a.CQUSXXUS,'1','城区','0','辖县','-') CQUSXXUSSTR," +
				" a.HXJZSJ,a.HXJZJG,a.FHXJZSJ,a.FHXJZJG," +
				" a.README," +
				" a.PCMC||decode(a.pcsx,'0','[揽收]','1','[投递]','[-]') ALLPCMC,'-' DISTNAME" +
				" " ;
				
				sqlCount = "select count(*) ";
				
				sqlfrom = " from cjjsx_zb_pc a ";

				sqlwhere = " where  1=1 ";
				
				sqlorder = " order by ALLPCMC";
	
			}
			
			if (PCSX.length()>0) {
				sqlwhere = sqlwhere + " and a.PCSX = '" + PCSX + "'";
			}
			
			if (CQUSXXUS.length()>0) {
				sqlwhere = sqlwhere + " and a.CQUSXXUS = '" + CQUSXXUS + "'";
			}
	
			sql=sql+sqlfrom+sqlwhere+sqlorder;
			
			sqlCount=sqlCount+sqlfrom+sqlwhere;
			
			page.setPageSize(9999);
			
			if(pageNo!=0){
				page.setPageNo(pageNo);
			}
			
			page=jjsxQueryDao.getBeansqueryjjsxsjspc(sql, sqlCount, page);
		
		}
		
		return "json";
	}
	
	
	@SuppressWarnings("unchecked")
	public String queryjjsxsqxzyzjpc() throws Exception {
		
		if(!userflag()){return "json";}
		
		String sql=" ";
		String sqlCount=" ";
		String sqlfrom=" ";
		String sqlwhere=" ";
		String sqlorder=" ";
		
		if (COUNTY_ID.length()>0) {
		
				if(FLAGPZ.equals("1")){
					
					sql = "  select "+
						 " e.SEQID SEQID, "+
						 " nvl(d.city_name,d.province_name)||d.county_name DISTNAME, "+
						 " b.pcmc || a.pcmc || "+
						 " decode(a.pcsx, '0', '[揽收]', '1', '[投递]', '[-]') || "+
						 " decode(a.CQUSXXUS,'1','[城区]','0','[辖县]','-') ||" +
						 " decode(e.hxflag, '1', '[核心]', '0', '[非核心]', '[-]')  ALLPCMC, "+
						 "  c.ORG_CNAME ORG_CNAME, "+
						 "  c.ORG_CODE ORG_CODE, "+
						 "  decode(e.hxflag,'1',a.hxjzsj,'0',a.fhxjzsj,'-') PCJZSJ, "+
						 "  decode(e.hxflag,'1',a.hxjzjg,'0',a.fhxjzjg,'-')  PCSJJG, "+
						 "  e.README README,e.hxflag " ;
					
					sqlCount = "select count(*) ";
					
					sqlfrom =  "  from "+
								 "  cjjsx_zb_pc a, "+
								 "  cjjsx_sj_spc b," +
								 " res_org c,cp_base_org_district_yt d ,CJJSX_SJ_ORGPC e ";
					
					
					
					sqlwhere = "  where   nvl(c.county_code,c.city_code) = d.district_code   and c.org_code = e.org_code  and b.seqid = e.sjspcseqid and a.seqid = b.zbpcseqid ";
					
					
					if (PCMC.length()>0) {
						sqlwhere = sqlwhere + " and b.pcmc || a.pcmc ||  decode(a.pcsx, '0', '揽收', '1', '投递', '-') || "+
						 " decode(a.CQUSXXUS,'1','城区','0','辖县','-') ||" +
						 " decode(e.hxflag, '1', '核心', '0', '非核心', '-') like '%" + PCMC + "%'";
					}
		
					if (PCSX.length()>0) {
						sqlwhere = sqlwhere + " and a.PCSX = '" + PCSX + "'";
					}
					
					if (CQUSXXUS.length()>0) {
						sqlwhere = sqlwhere + " and a.CQUSXXUS = '" + CQUSXXUS + "'";
					}
					
					if (HXFLAG.length()>0) {
						sqlwhere = sqlwhere + " and e.HXFLAG = '" + HXFLAG + "'";
					}
					
					sqlorder = " order by ALLPCMC,ORG_CODE";
		
		
				}else if(FLAGPZ.equals("0")){
		
		
					sql = " select " +
							" c.org_code SEQID, " +
							" d.city_name||d.county_name DISTNAME, " +
							" '-' ALLPCMC, " +
							" c.org_cname ORG_CNAME, " +
							" c.org_code ORG_CODE, " +
							" '-' PCJZSJ,'-' PCSJJG,'-' README,'-' HXFLAG";
					
					sqlCount = "select count(*) ";
					
					sqlfrom = " from res_org c,cp_base_org_district_yt d";
					
					sqlwhere = " where  nvl(c.county_code,c.city_code) = d.district_code  ";
					
					sqlorder = " order by c.ORG_CODE ";
		
				}
				
				if(RANGE.length()>0){
					
					if(RANGE.equals("1")){
						
						sqlwhere = sqlwhere + " and c.RANGE = '1'";
						
					}else if(RANGE.equals("0")){
						
						sqlwhere = sqlwhere + " and c.RANGE = '2'";
						
					}
					
					
				}
				
				if (COUNTY_ID.length()>0) {
					sqlwhere = sqlwhere + " and d.district_code = '" + COUNTY_ID + "'";
				}
				
				if(ORG_CODE.length()>0){
					
					sqlwhere = sqlwhere + " and c.ORG_CODE in ('" + ORG_CODE.replaceAll(",", "','") + "') ";
					
				}
		
				sql=sql+sqlfrom+sqlwhere+sqlorder;
				
				sqlCount=sqlCount+sqlfrom+sqlwhere;
				
				page.setPageSize(20);
				
				if(pageNo!=0){
					page.setPageNo(pageNo);
				}
				
				page=jjsxQueryDao.getBeansqueryjjsxqxzyzjpc(sql, sqlCount, page);
		}
		
		return "json";
	}
	
	@SuppressWarnings("unchecked")
	public String queryjjsxsqxzyzjpccx() throws Exception {
		
		if(!userflag()){return "json";}
		
		String sql=" ";
		String sqlCount=" ";
		String sqlfrom=" ";
		String sqlwhere=" ";
		String sqlorder=" ";
		
		sql = "  select "+
		 " e.SEQID SEQID, "+
		 "  nvl(d.city_name,d.province_name)||d.county_name DISTNAME, "+
		 " b.pcmc || a.pcmc || "+
		 " decode(a.pcsx, '0', '[揽收]', '1', '[投递]', '[-]') || "+
		 " decode(a.CQUSXXUS,'1','[城区]','0','[辖县]','-') ||" +
		 " decode(e.hxflag, '1', '[核心]', '0', '[非核心]', '[-]')  ALLPCMC, "+
		 "  c.ORG_CNAME ORG_CNAME, "+
		 "  c.ORG_CODE ORG_CODE, "+
		 "  decode(e.hxflag,'1',a.hxjzsj,'0',a.fhxjzsj,'-') PCJZSJ, "+
		 "  decode(e.hxflag,'1',a.hxjzjg,'0',a.fhxjzjg,'-')  PCSJJG, "+
		 "  e.README README,e.hxflag " ;
	
		sqlCount = "select count(*) ";
		
		sqlfrom =  "  from "+
					 "  cjjsx_zb_pc a, "+
					 "  cjjsx_sj_spc b," +
					 " res_org c,cp_base_org_district_yt d ,CJJSX_SJ_ORGPC e ";
		
		
		
		sqlwhere = "  where   nvl(c.county_code,c.city_code) = d.district_code   and c.org_code = e.org_code and  b.seqid = e.sjspcseqid and a.seqid = b.zbpcseqid ";
		
		if(RANGE.length()>0){
			
			if(RANGE.equals("1")){
				
				sqlwhere = sqlwhere + " and c.RANGE = '1'";
				
			}else if(RANGE.equals("0")){
				
				sqlwhere = sqlwhere + " and c.RANGE = '2'";
				
			}
			
		}
		
		if (COUNTY_ID.length()>0) {
			sqlwhere = sqlwhere + " and d.district_code = '" + COUNTY_ID + "'";
		}
		
		if (PCMC.length()>0) {
			sqlwhere = sqlwhere + " and b.pcmc || a.pcmc ||  decode(a.pcsx, '0', '揽收', '1', '投递', '-') || "+
			 " decode(a.CQUSXXUS,'1','城区','0','辖县','-') ||" +
			 " decode(e.hxflag, '1', '核心', '0', '非核心', '-') like '%" + PCMC + "%'";
		}
	
		if (PCSX.length()>0) {
			sqlwhere = sqlwhere + " and a.PCSX = '" + PCSX + "'";
		}
		
		if (CQUSXXUS.length()>0) {
			sqlwhere = sqlwhere + " and a.CQUSXXUS = '" + CQUSXXUS + "'";
		}
		
		if (HXFLAG.length()>0) {
			sqlwhere = sqlwhere + " and e.HXFLAG = '" + HXFLAG + "'";
		}
		
		sqlorder = " order by ALLPCMC,ORG_CODE";

		sql=sql+sqlfrom+sqlwhere+sqlorder;
		
		sqlCount=sqlCount+sqlfrom+sqlwhere;
		
		page.setPageSize(20);
		
		if(pageNo!=0){
			page.setPageNo(pageNo);
		}
		
		page=jjsxQueryDao.getBeansqueryjjsxqxzyzjpc(sql, sqlCount, page);
		
		return "json";
	}
	
	@SuppressWarnings("unchecked")
	public String queryjjsxqxzypctj() throws Exception {
		
		if(!userflag()){return "json";}
		
		String sql=" ";
		String sqlCount=" ";
		String tsqlfrom=" ";
		String tsqlfromres=" ";
		String sqlfrom=" ";
		String sqlwhere=" ";
		String sqlgroup=" ";
		String sqlorder=" ";

		if(CITY_ID.length()>0 && CITY_NAME.length()>0){
			
			tsqlfrom = "  ( " +
			"  select DISTRICT_CODE, " +
			"  decode(US_CITYFLAG,1,nvl(county_name,city_name),nvl(city_name, province_name)) DISTNAME, " +
			"  decode(degree || city_flag || US_CITYFLAG, " +
			"  '30', " +
			"  '0', " +
			"  '1') CITY_FLAG " +
			"  from cp_base_org_district_yt " +
			"  where (YT_COUNTYFLAG = '1' and nvl(us_cityflag, '0') = '0' and " +
			"  DEGREE = '3' and nvl(CITY_NAME, PROVINCE_NAME) = '" + CITY_NAME + "') " +
			"  or (YT_COUNTYFLAG = '1' and us_cityflag = '1' and " +
			"  DEGREE = '3' and " +
			"  DISTRICT_CODE = '" + CITY_ID + "') " +
			"  or (DEGREE = '2' and VA_COUNTYFLAG = '1' and DISTRICT_CODE = '" + CITY_ID + "') " +
			"  ) e ";
			
			
		}else{
			
			tsqlfrom = "  ( " +
			"  select DISTRICT_CODE, " +
			"  decode(US_CITYFLAG,1,nvl(county_name,city_name),nvl(city_name, province_name)) DISTNAME, " +
			"  decode(degree || city_flag || US_CITYFLAG, " +
			"  '30', " +
			"  '0', " +
			"  '1') CITY_FLAG " +
			"  from cp_base_org_district_yt " +
			"  where (YT_COUNTYFLAG = '1' and nvl(us_cityflag, '0') = '0' and " +
			"  DEGREE = '3') " +
			"  or (YT_COUNTYFLAG = '1' and us_cityflag = '1' and " +
			"  DEGREE = '3') " +
			"  or (DEGREE = '2' and VA_COUNTYFLAG = '1') " +
			"  ) e ";
			
			
		}
			
			
		tsqlfromres = " , res_org f  where nvl(f.county_code, f.city_code) = e.district_code ";
		
			if(RANGE.length()>0){
				
				if(RANGE.equals("1")){
					
					tsqlfromres = tsqlfromres + " and f.RANGE = '1'";
					
				}else if(RANGE.equals("0")){
					
					tsqlfromres = tsqlfromres + " and f.RANGE = '2'";
					
				}
				
			}

			
			sqlfrom = sqlfrom + "  select  " +
			"  max(DISTRICT_CODE) DISTRICT_CODE, " +
			"  DISTNAME, " +
			"  '总计'  ALLPCMC, " +
			"  count(*)  ZS, " +
			"  sum(case when (e.CITY_FLAG = 1) then 1 else 0 end) HXZS, " +
			"  sum(case when (e.CITY_FLAG = 0) then 1 else 0 end)  FHXZS " ;
			
			
			
			sqlfrom = sqlfrom + "  from  " + tsqlfrom + tsqlfromres + " group by DISTNAME " ;
			
			sqlfrom = sqlfrom + "  union all  ";
			
			
			sqlfrom = sqlfrom + "  select  " +
			"  max(DISTRICT_CODE) DISTRICT_CODE, " +
			"  DISTNAME, " +
			"  ALLPCMC, " +
			"  count(*)  ZS, " +
			"  sum(case when (d.hxflag = 1) then 1 else 0 end) HXZS,  " +
            "  sum(case when (d.hxflag = 0) then 1 else 0 end) FHXZS " ;
			
			
			
			sqlfrom = sqlfrom + "  from  " +
				"  ( " +
				"  select  " +
				"  b.pcmc || a.pcmc || " +
				"  decode(a.pcsx, '0', '[揽收]', '1', '[投递]', '[-]') ||  " +
				"  decode(a.CQUSXXUS,'1','[城区]','0','[辖县]','-')  ALLPCMC, " +
				"  c.hxflag HXFLAG, " +
				"  c.org_code ORG_CODE,  " +
				"  b.DISTCD DISTCD  " +
				"  from " +
				"  cjjsx_zb_pc a,cjjsx_sj_spc b,CJJSX_SJ_ORGPC c " +
				"  where b.seqid = c.sjspcseqid and a.seqid = b.zbpcseqid " +
				"  ) d, "  + tsqlfrom + tsqlfromres;
			
			
			
			sqlfrom = sqlfrom + " and f.org_code = d.org_code  ";

			sqlfrom = sqlfrom + " group by d.ALLPCMC,DISTNAME ";
			
			sqlfrom = sqlfrom + "  union all  ";
			
			
			sqlfrom = sqlfrom + "  select  " +
			"  max(DISTRICT_CODE) DISTRICT_CODE, " +
			"  DISTNAME, " +
			"  ALLPCMC, " +
			"  0  ZS, " +
			"  0 HXZS,  " +
            "  0 FHXZS " ;
			
			
			
			sqlfrom = sqlfrom + "  from  " +
				"  ( " +
				"  select  " +
				"  b.pcmc || a.pcmc || " +
				"  decode(a.pcsx, '0', '[揽收]', '1', '[投递]', '[-]') ||  " +
				"  decode(a.CQUSXXUS,'1','[城区]','0','[辖县]','-')  ALLPCMC, " +
				"  c.hxflag HXFLAG, " +
				"  c.org_code ORG_CODE,  " +
				"  b.DISTCD DISTCD  " +
				"  from " +
				"  cjjsx_zb_pc a,cjjsx_sj_spc b,CJJSX_SJ_ORGPC c " +
				"  where b.seqid = c.sjspcseqid(+) and  c.sjspcseqid is null and a.seqid = b.zbpcseqid " +
				"  ) d, "   + tsqlfrom + " where 1=1 ";
			
			
			
			sqlfrom = sqlfrom + " and d.DISTCD = e.district_code  ";

			sqlfrom = sqlfrom + " group by d.ALLPCMC,DISTNAME ";
			
			sql = " select DISTRICT_CODE,DISTNAME,ALLPCMC,ZS,HXZS,FHXZS from ( ";
			
			sqlorder = " ) order by DISTRICT_CODE,ALLPCMC ";
			
			
			sql=sql+sqlfrom+sqlwhere+sqlgroup+sqlorder;
			
			sqlCount = "select count(*) from ( " + sqlfrom+sqlwhere+sqlgroup + " ) ";
			
			page.setPageSize(50);
			
			if(pageNo!=0){
				page.setPageNo(pageNo);
			}
			
			page=jjsxQueryDao.getBeansqueryjjsxqxzypctj(sql, sqlCount, page);

		
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
		
		if(LINEKEYs[0].equals("expjjsxqxpctj")){
			
			String tsqlfrom=" ";
			
			String tsqlfromres=" ";
			
			if(EXDATAs[0].trim().length()>0 && EXDATAs[1].trim().length()>0){
				
				tsqlfrom = "  ( " +
				"  select DISTRICT_CODE, " +
				"  decode(US_CITYFLAG,1,nvl(county_name,city_name),nvl(city_name, province_name)) DISTNAME, " +
				"  decode(degree || city_flag || US_CITYFLAG, " +
				"  '30', " +
				"  '0', " +
				"  '1') CITY_FLAG " +
				"  from cp_base_org_district_yt " +
				"  where (YT_COUNTYFLAG = '1' and nvl(us_cityflag, '0') = '0' and " +
				"  DEGREE = '3' and nvl(CITY_NAME, PROVINCE_NAME) = '" + EXDATAs[1].trim() + "') " +
				"  or (YT_COUNTYFLAG = '1' and us_cityflag = '1' and " +
				"  DEGREE = '3' and " +
				"  DISTRICT_CODE = '" + EXDATAs[0].trim() + "') " +
				"  or (DEGREE = '2' and VA_COUNTYFLAG = '1' and DISTRICT_CODE = '" + EXDATAs[0].trim() + "') " +
				"  ) e ";
				
				
			}else{
				
				tsqlfrom = "  ( " +
				"  select DISTRICT_CODE, " +
				"  decode(US_CITYFLAG,1,nvl(county_name,city_name),nvl(city_name, province_name)) DISTNAME, " +
				"  decode(degree || city_flag || US_CITYFLAG, " +
				"  '30', " +
				"  '0', " +
				"  '1') CITY_FLAG " +
				"  from cp_base_org_district_yt " +
				"  where (YT_COUNTYFLAG = '1' and nvl(us_cityflag, '0') = '0' and " +
				"  DEGREE = '3') " +
				"  or (YT_COUNTYFLAG = '1' and us_cityflag = '1' and " +
				"  DEGREE = '3') " +
				"  or (DEGREE = '2' and VA_COUNTYFLAG = '1') " +
				"  ) e ";
				
				
			}
				
				
			tsqlfromres = " , res_org f  where nvl(f.county_code, f.city_code) = e.district_code ";
			
				if(EXDATAs[2].trim().length()>0){
					
					if(EXDATAs[2].trim().equals("1")){
						
						tsqlfromres = tsqlfromres + " and f.RANGE = '1'";
						
					}else if(EXDATAs[2].trim().equals("0")){
						
						tsqlfromres = tsqlfromres + " and f.RANGE = '2'";
						
					}
					
				}

				
				sqlfrom = sqlfrom + "  select  " +
				"  max(DISTRICT_CODE) DISTRICT_CODE, " +
				"  DISTNAME, " +
				"  '总计'  ALLPCMC, " +
				"  count(*)  ZS, " +
				"  sum(case when (e.CITY_FLAG = 1) then 1 else 0 end) HXZS, " +
				"  sum(case when (e.CITY_FLAG = 0) then 1 else 0 end)  FHXZS " ;
				
				
				
				sqlfrom = sqlfrom + "  from  " + tsqlfrom + tsqlfromres + " group by DISTNAME " ;
				
				sqlfrom = sqlfrom + "  union all  ";
				
				
				sqlfrom = sqlfrom + "  select  " +
				"  max(DISTRICT_CODE) DISTRICT_CODE, " +
				"  DISTNAME, " +
				"  ALLPCMC, " +
				"  count(*)  ZS, " +
				"  sum(case when (d.hxflag = 1) then 1 else 0 end) HXZS,  " +
	            "  sum(case when (d.hxflag = 0) then 1 else 0 end) FHXZS " ;
				
				
				
				sqlfrom = sqlfrom + "  from  " +
					"  ( " +
					"  select  " +
					"  b.pcmc || a.pcmc || " +
					"  decode(a.pcsx, '0', '[揽收]', '1', '[投递]', '[-]') ||  " +
					"  decode(a.CQUSXXUS,'1','[城区]','0','[辖县]','-')  ALLPCMC, " +
					"  c.hxflag HXFLAG, " +
					"  c.org_code ORG_CODE,  " +
					"  b.DISTCD DISTCD  " +
					"  from " +
					"  cjjsx_zb_pc a,cjjsx_sj_spc b,CJJSX_SJ_ORGPC c " +
					"  where b.seqid = c.sjspcseqid and a.seqid = b.zbpcseqid " +
					"  ) d, "  + tsqlfrom + tsqlfromres;
				
				
				
				sqlfrom = sqlfrom + " and f.org_code = d.org_code  ";

				sqlfrom = sqlfrom + " group by d.ALLPCMC,DISTNAME ";
				
				sqlfrom = sqlfrom + "  union all  ";
				
				
				sqlfrom = sqlfrom + "  select  " +
				"  max(DISTRICT_CODE) DISTRICT_CODE, " +
				"  DISTNAME, " +
				"  ALLPCMC, " +
				"  0  ZS, " +
				"  0 HXZS,  " +
	            "  0 FHXZS " ;
				
				
				
				sqlfrom = sqlfrom + "  from  " +
					"  ( " +
					"  select  " +
					"  b.pcmc || a.pcmc || " +
					"  decode(a.pcsx, '0', '[揽收]', '1', '[投递]', '[-]') ||  " +
					"  decode(a.CQUSXXUS,'1','[城区]','0','[辖县]','-')  ALLPCMC, " +
					"  c.hxflag HXFLAG, " +
					"  c.org_code ORG_CODE,  " +
					"  b.DISTCD DISTCD  " +
					"  from " +
					"  cjjsx_zb_pc a,cjjsx_sj_spc b,CJJSX_SJ_ORGPC c " +
					"  where b.seqid = c.sjspcseqid(+) and  c.sjspcseqid is null and a.seqid = b.zbpcseqid " +
					"  ) d, "   + tsqlfrom + " where 1=1 ";
				
				
				
				sqlfrom = sqlfrom + " and d.DISTCD = e.district_code  ";

				sqlfrom = sqlfrom + " group by d.ALLPCMC,DISTNAME ";
				
				sql = " select DISTNAME,ALLPCMC,ZS,HXZS,FHXZS from ( ";
				
				sqlorder = " ) order by DISTRICT_CODE,ALLPCMC ";

		}
		
		sql=sql+sqlfrom+sqlwhere+sqlorder;
		
		sqlCount=sqlCount+sqlfrom+sqlwhere;
		
		List<?> list = jjsxQueryDao.getqueryexportall(sql);
		
		ClfwExportall clfwexportall = new ClfwExportall();
		
		EXPORTALLPATH = clfwexportall.exportall("clfw",LINEMAIN,LINENAME,list);
		
		return "json";
	}
	
}
