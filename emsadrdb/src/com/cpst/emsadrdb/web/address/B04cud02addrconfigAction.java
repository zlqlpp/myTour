package com.cpst.emsadrdb.web.address;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.cpst.core.web.struts2.BaseActionSupport;
import com.cpst.emsadrdb.dao.address.AddrConfigDao;
import com.cpst.emsadrdb.dao.address.AddrQueryDao;
import com.cpst.emsadrdb.entity.address.BldgrsdnsBean;
import com.cpst.emsadrdb.entity.address.CpwhrlpgstBean;
import com.cpst.emsadrdb.entity.address.ExgcltitemopeBean;
import com.cpst.emsadrdb.entity.address.OpexxsbbocBean;
import com.cpst.emsadrdb.entity.address.OrganizationBean;
import com.cpst.emsadrdb.entity.address.QhzuiBean;
import com.cpst.emsadrdb.entity.address.StreetBean;
import com.cpst.emsadrdb.entity.disp.TransBean;
import com.cpst.emsadrdb.service.address.AddrCommon;
import com.cpst.emsadrdb.service.disp.DispCommon;
import com.cpst.emsadrdb.entity.address.TBSRPROCESSBean;

@SuppressWarnings({ "serial" })
@ParentPackage("jsoncrud")
@Results( { @Result(type = "json", name = "json") })
public class B04cud02addrconfigAction extends BaseActionSupport{
	
	@Autowired
	private AddrConfigDao addrConfigDao;
	@Autowired
	private AddrQueryDao addrQueryDao;
	private String saveMessage;
	private StreetBean streetBean;
	private BldgrsdnsBean bldgrsdnsBean;
	private OrganizationBean organizationBean;
	private QhzuiBean qhzuiBean;
	private ExgcltitemopeBean exgcltitemopeBean;
	private TBSRPROCESSBean tbsrprocessBean;
	
	private CpwhrlpgstBean cpwhrlpgstBean;
	
	private String STRT_ID;
	private String DIST_CD;
	private String STRT1_NAME;
	private String STRT2_NAME;
	private String STRT3_NAME;
	private String STRT4_NAME;
	private String STRT5_NAME;
	private String STRT_ABBR;
	private String ORG_IDTMP;
    private String POST_CD;
    
    private String RSDNBLDG_ID;
    private String ORG_ID;
    private String RSDNBLDG_NAME;
    private String RSDNBLDG_ABBR;
    
    private String ORG_NAME;
    private String ORG_ABBR_NAME;
    private String ORG_ABBR;
    
    private String STRT1_ABBR_NAME;
    private String STRT2_ABBR_NAME;
    private String STRT3_ABBR_NAME;
    private String STRT4_ABBR_NAME;
    private String STRT5_ABBR_NAME;
    private String DATA_FLAG;
    private String TOTAL_STREET_NAME;
    
    private String NUM_FLAG;
    private String PREFIX;
    private String START_NUM;
    private String END_NUM;
    private String SUFFIX;
    private String DT_PK_CODE;
    private String DM_PK_CODE;
    private String PG_PK_CODE;
    
    private String TOTAL_ALL_VALUE;
    
    private String NOTE;
    private String ADULTNOTE;
    
    private String QIANZUI_ID;
    private String FIX;
    private String FIX_ABBR;
    private String FIX_XZ;
    private String FIX_FLAG;
    private String FIX_SMP;
    private String RSDNBLDG_IDTMP;

    private String PK_REMARK;
    
    private String STRT_IDY;
    private String STRT_IDG;
    
    private String SEQID;
	private String REC_ALLADDR;
	private String PK_ALLADDR;
	private String REC_ALLADDRSTREET;
	private String ADDR_FLAG;
	private OpexxsbbocBean opexxsbbocBean;
	
    private String ITEMNO;
    private String REC_ORG;
    private String  NEWCITYCODE;
    private String  ISWHQUERY;
	
    
	public String getISWHQUERY() {
		return ISWHQUERY;
	}

	public void setISWHQUERY(String iswhquery) {
		ISWHQUERY = AddrCommon.sql_inj(iswhquery);
	}

	public String getNEWCITYCODE() {
		return NEWCITYCODE;
	}

	public void setNEWCITYCODE(String newcitycode) {
		NEWCITYCODE = AddrCommon.sql_inj(newcitycode);
	}

	public String getITEMNO() {
		return ITEMNO;
	}

	public void setITEMNO(String itemno) {
		ITEMNO = AddrCommon.sql_inj(itemno);
	}

	public String getREC_ORG() {
		return REC_ORG;
	}

	public void setREC_ORG(String rec_org) {
		REC_ORG = AddrCommon.sql_inj(rec_org);
	}

	public String getREC_ALLADDRSTREET() {
		return REC_ALLADDRSTREET;
	}

	public void setREC_ALLADDRSTREET(String rec_alladdrstreet) {
		REC_ALLADDRSTREET = AddrCommon.sql_inj(rec_alladdrstreet);
	}

	public String getADDR_FLAG() {
		return ADDR_FLAG;
	}

	public void setADDR_FLAG(String addr_flag) {
		ADDR_FLAG = AddrCommon.sql_inj(addr_flag);
	}

	public String getSEQID() {
		return SEQID;
	}

	public void setSEQID(String seqid) {
		SEQID = AddrCommon.sql_inj(seqid);
	}

	public String getREC_ALLADDR() {
		return REC_ALLADDR;
	}

	public void setREC_ALLADDR(String rec_alladdr) {
		REC_ALLADDR = AddrCommon.sql_inj(rec_alladdr);
	}

	public String getPK_ALLADDR() {
		return PK_ALLADDR;
	}

	public void setPK_ALLADDR(String pk_alladdr) {
		PK_ALLADDR = AddrCommon.sql_inj(pk_alladdr);
	}

	public String getSTRT_IDY() {
		return STRT_IDY;
	}

	public void setSTRT_IDY(String strt_idy) {
		STRT_IDY = AddrCommon.sql_inj(strt_idy);
	}

	public String getSTRT_IDG() {
		return STRT_IDG;
	}

	public void setSTRT_IDG(String strt_idg) {
		STRT_IDG = AddrCommon.sql_inj(strt_idg);
	}

	public String getPK_REMARK() {
		return PK_REMARK;
	}

	public void setPK_REMARK(String pk_remark) {
		PK_REMARK = AddrCommon.sql_inj(pk_remark);
	}

	public String getRSDNBLDG_IDTMP() {
		return RSDNBLDG_IDTMP;
	}

	public void setRSDNBLDG_IDTMP(String rsdnbldg_idtmp) {
		RSDNBLDG_IDTMP = AddrCommon.sql_inj(rsdnbldg_idtmp);
	}

	public QhzuiBean getQhzuiBean() {
		return qhzuiBean;
	}

	public void setQhzuiBean(QhzuiBean qhzuiBean) {
		this.qhzuiBean = qhzuiBean;
	}

	public String getQIANZUI_ID() {
		return QIANZUI_ID;
	}

	public void setQIANZUI_ID(String qianzui_id) {
		QIANZUI_ID = AddrCommon.sql_inj(qianzui_id);
	}

	public String getFIX() {
		return FIX;
	}

	public void setFIX(String fix) {
		FIX = AddrCommon.sql_inj(fix);
	}

	public String getFIX_ABBR() {
		return FIX_ABBR;
	}

	public void setFIX_ABBR(String fix_abbr) {
		FIX_ABBR = AddrCommon.sql_inj(fix_abbr);
	}

	public String getFIX_XZ() {
		return FIX_XZ;
	}

	public void setFIX_XZ(String fix_xz) {
		FIX_XZ = AddrCommon.sql_inj(fix_xz);
	}

	public String getFIX_FLAG() {
		return FIX_FLAG;
	}

	public void setFIX_FLAG(String fix_flag) {
		FIX_FLAG = AddrCommon.sql_inj(fix_flag);
	}

	public String getFIX_SMP() {
		return FIX_SMP;
	}

	public void setFIX_SMP(String fix_smp) {
		FIX_SMP = AddrCommon.sql_inj(fix_smp);
	}

	public String getORG_IDTMP() {
		return ORG_IDTMP;
	}

	public void setORG_IDTMP(String org_idtmp) {
		ORG_IDTMP = AddrCommon.sql_inj(org_idtmp);
	}

	public String getNOTE() {
		return NOTE;
	}

	public void setNOTE(String note) {
		NOTE = AddrCommon.sql_inj(note);
	}

	public String getADULTNOTE() {
		return ADULTNOTE;
	}

	public void setADULTNOTE(String adultnote) {
		ADULTNOTE = AddrCommon.sql_inj(adultnote);
	}

	public String getTOTAL_ALL_VALUE() {
		return TOTAL_ALL_VALUE;
	}

	public void setTOTAL_ALL_VALUE(String total_all_value) {
		TOTAL_ALL_VALUE = AddrCommon.sql_inj(total_all_value);
	}

	public String getORG_ABBR() {
		return ORG_ABBR;
	}

	public void setORG_ABBR(String org_abbr) {
		ORG_ABBR = AddrCommon.sql_inj(org_abbr);
	}

	public String getNUM_FLAG() {
		return NUM_FLAG;
	}

	public void setNUM_FLAG(String num_flag) {
		NUM_FLAG = AddrCommon.sql_inj(num_flag);
	}

	public String getPREFIX() {
		return PREFIX;
	}

	public void setPREFIX(String prefix) {
		PREFIX = AddrCommon.sql_inj(prefix);
	}

	public String getSTART_NUM() {
		return START_NUM;
	}

	public void setSTART_NUM(String start_num) {
		START_NUM = AddrCommon.sql_inj(start_num);
	}

	public String getEND_NUM() {
		return END_NUM;
	}

	public void setEND_NUM(String end_num) {
		END_NUM = AddrCommon.sql_inj(end_num);
	}

	public String getSUFFIX() {
		return SUFFIX;
	}

	public void setSUFFIX(String suffix) {
		SUFFIX = AddrCommon.sql_inj(suffix);
	}

	public String getDT_PK_CODE() {
		return DT_PK_CODE;
	}

	public void setDT_PK_CODE(String dt_pk_code) {
		DT_PK_CODE = AddrCommon.sql_inj(dt_pk_code);
	}

	public String getDM_PK_CODE() {
		return DM_PK_CODE;
	}

	public void setDM_PK_CODE(String dm_pk_code) {
		DM_PK_CODE = AddrCommon.sql_inj(dm_pk_code);
	}

	public String getPG_PK_CODE() {
		return PG_PK_CODE;
	}

	public void setPG_PK_CODE(String pg_pk_code) {
		PG_PK_CODE = AddrCommon.sql_inj(pg_pk_code);
	}

	public String getTOTAL_STREET_NAME() {
		return TOTAL_STREET_NAME;
	}

	public void setTOTAL_STREET_NAME(String total_street_name) {
		TOTAL_STREET_NAME = AddrCommon.sql_inj(total_street_name);
	}

	public String getDATA_FLAG() {
		return DATA_FLAG;
	}

	public void setDATA_FLAG(String data_flag) {
		DATA_FLAG = AddrCommon.sql_inj(data_flag);
	}

	public String getSTRT1_ABBR_NAME() {
		return STRT1_ABBR_NAME;
	}

	public void setSTRT1_ABBR_NAME(String strt1_abbr_name) {
		STRT1_ABBR_NAME = AddrCommon.sql_inj(strt1_abbr_name);
	}

	public String getSTRT2_ABBR_NAME() {
		return STRT2_ABBR_NAME;
	}

	public void setSTRT2_ABBR_NAME(String strt2_abbr_name) {
		STRT2_ABBR_NAME = AddrCommon.sql_inj(strt2_abbr_name);
	}

	public String getSTRT3_ABBR_NAME() {
		return STRT3_ABBR_NAME;
	}

	public void setSTRT3_ABBR_NAME(String strt3_abbr_name) {
		STRT3_ABBR_NAME = AddrCommon.sql_inj(strt3_abbr_name);
	}

	public String getSTRT4_ABBR_NAME() {
		return STRT4_ABBR_NAME;
	}

	public void setSTRT4_ABBR_NAME(String strt4_abbr_name) {
		STRT4_ABBR_NAME = AddrCommon.sql_inj(strt4_abbr_name);
	}

	public String getSTRT5_ABBR_NAME() {
		return STRT5_ABBR_NAME;
	}

	public void setSTRT5_ABBR_NAME(String strt5_abbr_name) {
		STRT5_ABBR_NAME = AddrCommon.sql_inj(strt5_abbr_name);
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

	public String getRSDNBLDG_NAME() {
		return RSDNBLDG_NAME;
	}

	public void setRSDNBLDG_NAME(String rsdnbldg_name) {
		RSDNBLDG_NAME = AddrCommon.sql_inj(rsdnbldg_name);
	}

	public String getRSDNBLDG_ABBR() {
		return RSDNBLDG_ABBR;
	}

	public void setRSDNBLDG_ABBR(String rsdnbldg_abbr) {
		RSDNBLDG_ABBR = AddrCommon.sql_inj(rsdnbldg_abbr);
	}

	public String getORG_NAME() {
		return ORG_NAME;
	}

	public void setORG_NAME(String org_name) {
		ORG_NAME = AddrCommon.sql_inj(org_name);
	}

	public String getORG_ABBR_NAME() {
		return ORG_ABBR_NAME;
	}

	public void setORG_ABBR_NAME(String org_abbr_name) {
		ORG_ABBR_NAME = AddrCommon.sql_inj(org_abbr_name);
	}

	public String getPOST_CD() {
		return POST_CD;
	}

	public void setPOST_CD(String post_cd) {
		POST_CD = AddrCommon.sql_inj(post_cd);
	}

	public String getSTRT_ID() {
		return STRT_ID;
	}

	public void setSTRT_ID(String strt_id) {
		STRT_ID = AddrCommon.sql_inj(strt_id);
	}

	public String getSaveMessage() {
		return saveMessage;
	}

	public void setSaveMessage(String saveMessage) {
		this.saveMessage = saveMessage;
	}

	public String getDIST_CD() {
		return DIST_CD;
	}

	public void setDIST_CD(String dist_cd) {
		DIST_CD = AddrCommon.sql_inj(dist_cd);
		
	}

	public String getSTRT1_NAME() {
		return STRT1_NAME;
	}

	public void setSTRT1_NAME(String strt1_name) {
		STRT1_NAME = AddrCommon.sql_inj(strt1_name);
		
	}

	public String getSTRT2_NAME() {
		return STRT2_NAME;
	}

	public void setSTRT2_NAME(String strt2_name) {
		STRT2_NAME = AddrCommon.sql_inj(strt2_name);
		
	}

	public String getSTRT3_NAME() {
		return STRT3_NAME;
	}

	public void setSTRT3_NAME(String strt3_name) {
		STRT3_NAME = AddrCommon.sql_inj(strt3_name);
	}

	public String getSTRT4_NAME() {
		return STRT4_NAME;
	}

	public void setSTRT4_NAME(String strt4_name) {
		STRT4_NAME = AddrCommon.sql_inj(strt4_name);
		
	}

	public String getSTRT5_NAME() {
		return STRT5_NAME;
	}

	public void setSTRT5_NAME(String strt5_name) {
		STRT5_NAME = AddrCommon.sql_inj(strt5_name);
	}
	
	public String getSTRT_ABBR() {
		return STRT_ABBR;
	}

	public void setSTRT_ABBR(String strt_abbr) {
		STRT_ABBR = AddrCommon.sql_inj(strt_abbr);
	}
	
	public void configStreetBean(){
		streetBean=new StreetBean();
		if(DATA_FLAG!=null){
			streetBean.setDATA_FLAG(DATA_FLAG);
		}
		if(STRT_ID!=null){
			streetBean.setSTRT_ID(STRT_ID);
		}
		if(DIST_CD!=null){
			streetBean.setDIST_CD(DIST_CD);
		}
		if(STRT1_NAME!=null){
			streetBean.setSTRT1_NAME(STRT1_NAME.trim());
			streetBean.setSEG_NUM("1");
		}
		if(STRT2_NAME!=null){
			streetBean.setSTRT2_NAME(STRT2_NAME.trim());
			if(STRT2_NAME.length()>0){
				streetBean.setSEG_NUM("2");
			}
		}
		if(STRT3_NAME!=null){
			streetBean.setSTRT3_NAME(STRT3_NAME.trim());
			if(STRT3_NAME.length()>0){
				streetBean.setSEG_NUM("3");
			}
		}
		if(STRT4_NAME!=null){
			streetBean.setSTRT4_NAME(STRT4_NAME.trim());
			if(STRT4_NAME.length()>0){
				streetBean.setSEG_NUM("4");
			}
		}
		if(STRT5_NAME!=null){
			streetBean.setSTRT5_NAME(STRT5_NAME.trim());
			if(STRT5_NAME.length()>0){
				streetBean.setSEG_NUM("5");
			}
		}
		if(STRT_ABBR!=null){
			streetBean.setSTRT_ABBR(STRT_ABBR.trim());
		}
		if(STRT1_ABBR_NAME!=null){
			streetBean.setSTRT1_ABBR_NAME(STRT1_ABBR_NAME.trim());
		}
		if(STRT2_ABBR_NAME!=null){
			streetBean.setSTRT2_ABBR_NAME(STRT2_ABBR_NAME.trim());
		}
		if(STRT3_ABBR_NAME!=null){
			streetBean.setSTRT3_ABBR_NAME(STRT3_ABBR_NAME.trim());
		}
		if(STRT4_ABBR_NAME!=null){
			streetBean.setSTRT4_ABBR_NAME(STRT4_ABBR_NAME.trim());
		}
		if(STRT5_ABBR_NAME!=null){
			streetBean.setSTRT5_ABBR_NAME(STRT5_ABBR_NAME.trim());
		}
		if(TOTAL_STREET_NAME!=null){
			streetBean.setTOTAL_STREET_NAME(TOTAL_STREET_NAME);
		}
		if(POST_CD!=null){
			streetBean.setPOST_CD(POST_CD);
		}
		
		if(NOTE!=null){
			streetBean.setNOTE(NOTE);
		}
		if(ADULTNOTE!=null){
			streetBean.setADULTNOTE(ADULTNOTE);
		}
	}
	
	public Boolean userflag(){
		if(getSessionUser()!=null && getSessionUser().getUsLoginId() != null && getSessionUser().getUsLoginId().length() > 0){
			return true;
		}
		else{
			saveMessage = "用户信息丢失,请重新登陆!";
			return false;
		}
	}
	
	public String hbstreet() throws Exception {				
		
		if(!userflag()){return "json";}
		
		streetBean=new StreetBean();
		streetBean.setSTRT_IDY(STRT_IDY);
		streetBean.setSTRT_IDG(STRT_IDG);
		streetBean.setDIST_CD(DIST_CD);
		@SuppressWarnings("unused")
		boolean m=addrConfigDao.hbstreet(streetBean,getSessionUser());
		
		saveMessage="合并成功";
		return "json";
	}
	
	public String alertaddstreet() throws Exception {		
		
		if(!userflag()){return "json";}
		
		String sql="select count(*) from CP_MK_ADR_STREET_" + DIST_CD.substring(0,2) + " where DIST_CD != " +  DIST_CD + " and STRT1_NAME||STRT2_NAME||STRT3_NAME||STRT4_NAME||STRT5_NAME = '"  + STRT1_NAME + STRT2_NAME+ STRT3_NAME+ STRT4_NAME+ STRT5_NAME + "'";
		if(addrQueryDao.getQueryCount(sql)>0){
			saveMessage="本城市存在同样的街道名,是否添加此数据!";
			return "json";
		}
		sql="select count(*) from CP_MK_ADR_STREET_" + DIST_CD.substring(0,2) + " where ((STRT1_NAME = '"  + STRT1_NAME + STRT2_NAME + "' and STRT1_NAME != '"  + STRT1_NAME + "') or (STRT1_NAME || STRT2_NAME = '"  +  STRT1_NAME + "' and STRT1_NAME != '"  + STRT1_NAME  + "'))";
		if(addrQueryDao.getQueryCount(sql)>0){
			saveMessage="本城市存在切分不一致的街道名,是否添加此数据!";
			return "json";
		}
		saveMessage="true";
		return "json";
	}
	
	public String alertupdatestreet() throws Exception {		
		
		if(!userflag()){return "json";}
		
		String sql="select count(*) from CP_MK_ADR_STREET_" + DIST_CD.substring(0,2) + " where DIST_CD != " +  DIST_CD + " and  STRT_ID != " + STRT_ID + " and STRT1_NAME||STRT2_NAME||STRT3_NAME||STRT4_NAME||STRT5_NAME = '"  + STRT1_NAME + STRT2_NAME+ STRT3_NAME+ STRT4_NAME+ STRT5_NAME + "'";
		if(addrQueryDao.getQueryCount(sql)>0){
			saveMessage="本城市存在同样的街道名,是否修改此数据!";
			return "json";
		}
		sql="select count(*) from CP_MK_ADR_STREET_" + DIST_CD.substring(0,2) + " where  STRT_ID != " + STRT_ID +  " and ((STRT1_NAME = '"  + STRT1_NAME + STRT2_NAME + "' and STRT1_NAME != '"  + STRT1_NAME + "') or (STRT1_NAME || STRT2_NAME = '"  +  STRT1_NAME + "' and STRT1_NAME != '"  + STRT1_NAME + "'))";
		if(addrQueryDao.getQueryCount(sql)>0){
			saveMessage="本城市存在切分不一致的街道名,是否添加此数据!";
			return "json";
		}
		saveMessage="true";
		return "json";
	}
	
	public String shstreet() throws Exception {			
		
		if(!userflag()){return "json";}
			
		configStreetBean();
		@SuppressWarnings("unused")
		boolean m=addrConfigDao.shStreet(streetBean,getSessionUser());
		if(streetBean.getDATA_FLAG().equals("2")){
			saveMessage="审核同意成功";
		}else{
			saveMessage="审核拒绝成功";
		}
		
		return "json";
	}
	
	public String shstreets() throws Exception {			
		
		if(!userflag()){return "json";}
		
		String[] tTOTAL_ALL_VALUE = TOTAL_ALL_VALUE.split(",");
		for(int i=0;i<tTOTAL_ALL_VALUE.length;i++){
			if(tTOTAL_ALL_VALUE[i].indexOf("@")>1){
				String[] tTOTAL_ALL_VALUEN = tTOTAL_ALL_VALUE[i].split("@");
				streetBean = new StreetBean();
				streetBean.setDATA_FLAG(DATA_FLAG);
				streetBean.setDIST_CD(tTOTAL_ALL_VALUEN[0]);
				streetBean.setSTRT_ID(tTOTAL_ALL_VALUEN[1]);
				boolean m=addrConfigDao.shStreet(streetBean,getSessionUser());
			}
		}		
		if(DATA_FLAG.equals("2")){
			saveMessage="审核同意成功";
		}else{
			saveMessage="审核拒绝成功";
		}
		
		return "json";
	}
	
	public String addstreet() throws Exception {				
		
		if(!userflag()){return "json";}
		
		String sql="select count(*) from CP_MK_ADR_STREET_" + DIST_CD.substring(0,2) + " where DIST_CD = " +  DIST_CD + " and STRT1_NAME||STRT2_NAME||STRT3_NAME||STRT4_NAME||STRT5_NAME = '" + STRT1_NAME + STRT2_NAME+ STRT3_NAME+ STRT4_NAME+ STRT5_NAME  + "'";
		if(addrQueryDao.getQueryCount(sql)>0){
			saveMessage="本区域下存在同样的街道名,不能添加此数据!";
			return "json";
		}
		if(STRT1_ABBR_NAME!=null){
			sql="select count(*) from CP_MK_ADR_STREET_" + DIST_CD.substring(0,2) + " where DIST_CD = " +  DIST_CD + " and STRT1_NAME != '" + STRT1_NAME + "' and STRT1_ABBR_NAME = '" + STRT1_ABBR_NAME + "'";
			if(addrQueryDao.getQueryCount(sql)>0){
				saveMessage="本区域下存在同样的街道别名,不能添加此数据!";
				return "json";
			}
		}
		configStreetBean();
		@SuppressWarnings("unused")
		boolean m=addrConfigDao.addStreet(streetBean,getSessionUser());
		
		saveMessage="新增成功";
		return "json";
	}
	
	public String upstreet() throws Exception {		
		
		if(!userflag()){return "json";}
		
		String sql="select count(*) from CP_MK_ADR_STREET_" + DIST_CD.substring(0,2) + " where DIST_CD = " +  DIST_CD + " and  STRT_ID != " + STRT_ID + " and STRT1_NAME||STRT2_NAME||STRT3_NAME||STRT4_NAME||STRT5_NAME = '"  + STRT1_NAME + STRT2_NAME+ STRT3_NAME+ STRT4_NAME+ STRT5_NAME + "'";
		if(addrQueryDao.getQueryCount(sql)>0){
			saveMessage="本区域下存在同样的街道名,不能修改此数据!";
			return "json";
		}
		if(STRT1_ABBR_NAME!=null){
			sql="select count(*) from CP_MK_ADR_STREET_" + DIST_CD.substring(0,2) + " where DIST_CD = " +  DIST_CD + " and  STRT_ID != " + STRT_ID + " and STRT1_NAME != '" + STRT1_NAME + "' and STRT1_ABBR_NAME = '" + STRT1_ABBR_NAME + "'";
			if(addrQueryDao.getQueryCount(sql)>0){
				saveMessage="本区域下存在同样的街道别名,不能添加此数据!";
				return "json";
			}
		}
		configStreetBean();
		@SuppressWarnings("unused")
		boolean m=addrConfigDao.upStreet(streetBean,getSessionUser());

		saveMessage="修改成功";
		return "json";
	}
	
	public String delstreet() throws Exception {		
		
		if(!userflag()){return "json";}
		
		configStreetBean();
		@SuppressWarnings("unused")
		boolean m=addrConfigDao.delStreet(streetBean,getSessionUser());

		saveMessage="删除成功";
		return "json";
	}
	
	public String fnstreet() throws Exception {		
		
		if(!userflag()){return "json";}
		
		streetBean=new StreetBean();
		streetBean.setSTRT_ID(TOTAL_ALL_VALUE);
		if(DIST_CD!=null){
			streetBean.setDIST_CD(DIST_CD);
		}
		@SuppressWarnings("unused")
		boolean m=addrConfigDao.streetBean(streetBean,getSessionUser());
		
		saveMessage="操作成功";
		return "json";
	}
	
	public String delluanstreet() throws Exception {		
		
		if(!userflag()){return "json";}
		
		configStreetBean();
		@SuppressWarnings("unused")
		boolean m=addrConfigDao.delluanstreet(streetBean,getSessionUser());

		saveMessage="删除成功";
		return "json";
	}
	
	public String luanstreet() throws Exception {		
		
		if(!userflag()){return "json";}
		
		String sql="select count(*) from CP_MK_ADR_STREET_" + DIST_CD.substring(0,2) + " where DIST_CD = " +  DIST_CD + " and  STRT_ID != " + STRT_ID + " and STRT1_NAME||STRT2_NAME||STRT3_NAME||STRT4_NAME||STRT5_NAME = '"  + STRT1_NAME + STRT2_NAME+ STRT3_NAME+ STRT4_NAME+ STRT5_NAME + "'";
		if(addrQueryDao.getQueryCount(sql)>0){
			saveMessage="本区域下存在同样的街道名,不能修改此数据!";
			return "json";
		}
		if(STRT1_ABBR_NAME!=null){
			sql="select count(*) from CP_MK_ADR_STREET_" + DIST_CD.substring(0,2) + " where DIST_CD = " +  DIST_CD + " and  STRT_ID != " + STRT_ID + " and STRT1_NAME != '" + STRT1_NAME + "' and STRT1_ABBR_NAME = '" + STRT1_ABBR_NAME + "'";
			if(addrQueryDao.getQueryCount(sql)>0){
				saveMessage="本区域下存在同样的街道别名,不能添加此数据!";
				return "json";
			}
		}
		configStreetBean();
		@SuppressWarnings("unused")
		boolean m=addrConfigDao.luanStreet(streetBean,getSessionUser());

		saveMessage="修改成功";
		return "json";
	}
	
	public String qfhdbstreet() throws Exception {		
		
		if(!userflag()){return "json";}
		
		String sql="select count(*) from CP_MK_ADR_STREET_" + DIST_CD.substring(0,2) + " where DIST_CD = " +  DIST_CD + " and  STRT_ID != " + STRT_ID + " and STRT1_NAME||STRT2_NAME||STRT3_NAME||STRT4_NAME||STRT5_NAME = '"  + STRT1_NAME + STRT2_NAME+ STRT3_NAME+ STRT4_NAME+ STRT5_NAME + "'";
		if(addrQueryDao.getQueryCount(sql)>0){
			saveMessage="本区域下存在同样的街道名,不能修改此数据!";
			return "json";
		}
		if(STRT1_ABBR_NAME!=null){
			sql="select count(*) from CP_MK_ADR_STREET_" + DIST_CD.substring(0,2) + " where DIST_CD = " +  DIST_CD + " and  STRT_ID != " + STRT_ID + " and STRT1_NAME != '" + STRT1_NAME + "' and STRT1_ABBR_NAME = '" + STRT1_ABBR_NAME + "'";
			if(addrQueryDao.getQueryCount(sql)>0){
				saveMessage="本区域下存在同样的街道别名,不能添加此数据!";
				return "json";
			}
		}
		configStreetBean();
		@SuppressWarnings("unused")
		boolean m=addrConfigDao.qfhdbstreet(streetBean,getSessionUser());

		saveMessage="修改成功";
		return "json";
	}
			
	public void configBldgrsdnsBean(){
		bldgrsdnsBean=new BldgrsdnsBean();
		if(DATA_FLAG!=null){
			bldgrsdnsBean.setDATA_FLAG(DATA_FLAG);
		}
		if(RSDNBLDG_ID!=null){
			bldgrsdnsBean.setRSDNBLDG_ID(RSDNBLDG_ID);
		}
		if(RSDNBLDG_IDTMP!=null){
			bldgrsdnsBean.setRSDNBLDG_IDTMP(RSDNBLDG_IDTMP);
		}
		if(STRT_ID!=null){
			bldgrsdnsBean.setSTRT_ID(STRT_ID);
		}
		if(DIST_CD!=null){
			bldgrsdnsBean.setDIST_CD(DIST_CD);
		}
		if(RSDNBLDG_NAME!=null){
			bldgrsdnsBean.setRSDNBLDG_NAME(RSDNBLDG_NAME.trim());
			}
		if(RSDNBLDG_ABBR!=null){
			bldgrsdnsBean.setRSDNBLDG_ABBR(RSDNBLDG_ABBR.trim());
		}
		if(TOTAL_STREET_NAME!=null){
			bldgrsdnsBean.setTOTAL_STREET_NAME(TOTAL_STREET_NAME);
		}
		if(POST_CD!=null){
			bldgrsdnsBean.setPOST_CD(POST_CD);
		}
		if(NUM_FLAG!=null){
			bldgrsdnsBean.setNUM_FLAG(NUM_FLAG);
		}
		if(PREFIX!=null){
			bldgrsdnsBean.setPREFIX(PREFIX);
		}
		if(START_NUM!=null){
			bldgrsdnsBean.setSTART_NUM(START_NUM);
		}
		if(END_NUM!=null){
			bldgrsdnsBean.setEND_NUM(END_NUM);
		}
		if(SUFFIX!=null){
			bldgrsdnsBean.setSUFFIX(SUFFIX);
		}
		if(DT_PK_CODE!=null){
			bldgrsdnsBean.setDT_PK_CODE(DT_PK_CODE);
		}
		if(DM_PK_CODE!=null){
			bldgrsdnsBean.setDM_PK_CODE(DM_PK_CODE);
		}
		if(PG_PK_CODE!=null){
			bldgrsdnsBean.setPG_PK_CODE(PG_PK_CODE);
		}
		if(NOTE!=null){
			bldgrsdnsBean.setNOTE(NOTE);
		}
		if(ADULTNOTE!=null){
			bldgrsdnsBean.setADULTNOTE(ADULTNOTE);
		}
	}
	
	public String addbldgrsdns() throws Exception {		
		
		if(!userflag()){return "json";}
		
		String sql="select count(*) from CP_MK_ADR_BLDGRSDNS_" + DIST_CD.substring(0,2) + " where DIST_CD = " +  DIST_CD + " and  STRT_ID = " +  STRT_ID   +  " and  RSDNBLDG_NAME = '" + RSDNBLDG_NAME  + "'";
		if(addrQueryDao.getQueryCount(sql)>0){
			saveMessage="此街道下存在同样的小区名,不能添加此数据!";
			return "json";
		}
		configBldgrsdnsBean();
		@SuppressWarnings("unused")
		boolean m=addrConfigDao.addBldgrsdns(bldgrsdnsBean,getSessionUser());

		saveMessage="新增成功";
		return "json";
	}
	
	public String addbldgrsdnsdius() throws Exception {		
		
		if(!userflag()){return "json";}
		
		String sql="select count(*) from CP_MK_ADR_BLDGRSDNS_" + DIST_CD.substring(0,2) + " where DIST_CD = " +  DIST_CD + " and  STRT_ID = " +  STRT_ID   +  " and  RSDNBLDG_NAME = '" + RSDNBLDG_NAME  + "'";
		if(addrQueryDao.getQueryCount(sql)>0){
			saveMessage="此街道下存在同样的小区名,不能添加此数据!";
			return "json";
		}
		configBldgrsdnsBean();
		@SuppressWarnings("unused")
		boolean m=addrConfigDao.addBldgrsdnsdius(bldgrsdnsBean,getSessionUser());

		saveMessage="新增成功";
		return "json";
	}
	
	public String upbldgrsdns() throws Exception {		
		
		if(!userflag()){return "json";}
		
		String sql="select count(*) from CP_MK_ADR_BLDGRSDNS_" + DIST_CD.substring(0,2) + " where DIST_CD = " +  DIST_CD + " and  STRT_ID = " +  STRT_ID  + " and  RSDNBLDG_ID != " + RSDNBLDG_ID + " and  RSDNBLDG_NAME = '" + RSDNBLDG_NAME  + "'";
		if(addrQueryDao.getQueryCount(sql)>0){
			saveMessage="此街道下存在同样的小区名,不能修改此数据!";
			return "json";
		}
		configBldgrsdnsBean();
		@SuppressWarnings("unused")
		boolean m=addrConfigDao.upBldgrsdns(bldgrsdnsBean,getSessionUser());

		saveMessage="修改成功";
		return "json";
	}
	
	public String luanbldgrsdns() throws Exception {		
		
		if(!userflag()){return "json";}
		
		String sql="select count(*) from CP_MK_ADR_BLDGRSDNS_" + DIST_CD.substring(0,2) + " where DIST_CD = " +  DIST_CD + " and  STRT_ID = " +  STRT_ID  + " and  RSDNBLDG_ID != " + RSDNBLDG_ID + " and  RSDNBLDG_NAME = '" + RSDNBLDG_NAME  + "'";
		if(addrQueryDao.getQueryCount(sql)>0){
			saveMessage="此街道下存在同样的小区名,不能修改此数据!";
			return "json";
		}
		configBldgrsdnsBean();
		@SuppressWarnings("unused")
		boolean m=addrConfigDao.luanbldgrsdns(bldgrsdnsBean,getSessionUser());

		saveMessage="修改成功";
		return "json";
	}
	
	public String delbldgrsdns() throws Exception {		
		
		if(!userflag()){return "json";}
		
		configBldgrsdnsBean();
		@SuppressWarnings("unused")
		boolean m=addrConfigDao.delBldgrsdns(bldgrsdnsBean,getSessionUser());

		saveMessage="删除成功";
		return "json";
	}
	
	public String delbldgrsdnsdius() throws Exception {		
		
		if(!userflag()){return "json";}
		
		configBldgrsdnsBean();
		@SuppressWarnings("unused")
		boolean m=addrConfigDao.delBldgrsdnsdius(bldgrsdnsBean,getSessionUser());

		saveMessage="删除成功";
		return "json";
	}
	
	public String fnbldgrsdnsluan() throws Exception {		
		
		if(!userflag()){return "json";}
		
		bldgrsdnsBean=new BldgrsdnsBean();
		bldgrsdnsBean.setRSDNBLDG_ID(TOTAL_ALL_VALUE);
		if(DIST_CD!=null){
			bldgrsdnsBean.setDIST_CD(DIST_CD);
		}
		@SuppressWarnings("unused")
		boolean m=addrConfigDao.fnbldgrsdnsluan(bldgrsdnsBean,getSessionUser());
		
		saveMessage="操作成功";
		return "json";
	}
	
	public String delbldgdiustssq() throws Exception {		
		
		if(!userflag()){return "json";}
		
		bldgrsdnsBean=new BldgrsdnsBean();
		bldgrsdnsBean.setRSDNBLDG_ID(TOTAL_ALL_VALUE);
		if(DIST_CD!=null){
			bldgrsdnsBean.setDIST_CD(DIST_CD);
		}
		boolean m=addrConfigDao.delbldgdiustssq(bldgrsdnsBean,getSessionUser());
	
		saveMessage="提交成功";
		return "json";
	}
	
	public String delbldgrsdnsluan() throws Exception {		
		
		if(!userflag()){return "json";}
		
		configBldgrsdnsBean();
		@SuppressWarnings("unused")
		boolean m=addrConfigDao.delBldgrsdnsluan(bldgrsdnsBean,getSessionUser());

		saveMessage="删除成功";
		return "json";
	}
	
	public String shbldgrsdns() throws Exception {				
		
		if(!userflag()){return "json";}
		
		configBldgrsdnsBean();
		@SuppressWarnings("unused")
		boolean m=addrConfigDao.shBldgrsdns(bldgrsdnsBean,getSessionUser());
		if(bldgrsdnsBean.getDATA_FLAG().equals("2")){
			saveMessage="审核同意成功";
		}else{
			saveMessage="审核拒绝成功";
		}
		
		return "json";
	}
	
	public String shbldgrsdnss() throws Exception {				
		
		if(!userflag()){return "json";}
		
		String[] tTOTAL_ALL_VALUE = TOTAL_ALL_VALUE.split(",");
		for(int i=0;i<tTOTAL_ALL_VALUE.length;i++){
			if(tTOTAL_ALL_VALUE[i].indexOf("@")>1){
				String[] tTOTAL_ALL_VALUEN = tTOTAL_ALL_VALUE[i].split("@");
				bldgrsdnsBean = new BldgrsdnsBean();
				bldgrsdnsBean.setDATA_FLAG(DATA_FLAG);
				bldgrsdnsBean.setDIST_CD(tTOTAL_ALL_VALUEN[0]);
				bldgrsdnsBean.setRSDNBLDG_ID(tTOTAL_ALL_VALUEN[1]);
				bldgrsdnsBean.setTOTAL_STREET_NAME(" ");
				boolean m=addrConfigDao.shBldgrsdns(bldgrsdnsBean,getSessionUser());
			}
		}	
		if(DATA_FLAG.equals("2")){
			saveMessage="审核同意成功";
		}else{
			saveMessage="审核拒绝成功";
		}
		
		return "json";
	}
	
	public void configOrganizationBean(){
		organizationBean=new OrganizationBean();
		if(DATA_FLAG!=null){
			organizationBean.setDATA_FLAG(DATA_FLAG);
		}
		if(ORG_ID!=null){
			organizationBean.setORG_ID(ORG_ID);
		}
		if(ORG_IDTMP!=null){
			organizationBean.setORG_IDTMP(ORG_IDTMP);
		}
		if(STRT_ID!=null){
			organizationBean.setSTRT_ID(STRT_ID);
		}
		if(DIST_CD!=null){
			organizationBean.setDIST_CD(DIST_CD);
		}
		if(ORG_NAME!=null){
			organizationBean.setORG_NAME(ORG_NAME.trim());
			}
		if(ORG_ABBR_NAME!=null){
			organizationBean.setORG_ABBR_NAME(ORG_ABBR_NAME.trim());
		}
		if(ORG_ABBR!=null){
			organizationBean.setORG_ABBR(ORG_ABBR.trim());
		}
		if(TOTAL_STREET_NAME!=null){
			organizationBean.setTOTAL_STREET_NAME(TOTAL_STREET_NAME);
		}
		if(POST_CD!=null){
			organizationBean.setPOST_CD(POST_CD);
		}
		if(NUM_FLAG!=null){
			organizationBean.setNUM_FLAG(NUM_FLAG);
		}
		if(PREFIX!=null){
			organizationBean.setPREFIX(PREFIX);
		}
		if(START_NUM!=null){
			organizationBean.setSTART_NUM(START_NUM);
		}
		if(END_NUM!=null){
			organizationBean.setEND_NUM(END_NUM);
		}
		if(SUFFIX!=null){
			organizationBean.setSUFFIX(SUFFIX);
		}
		if(DT_PK_CODE!=null){
			organizationBean.setDT_PK_CODE(DT_PK_CODE);
		}
		if(DM_PK_CODE!=null){
			organizationBean.setDM_PK_CODE(DM_PK_CODE);
		}
		if(PG_PK_CODE!=null){
			organizationBean.setPG_PK_CODE(PG_PK_CODE);
		}
		if(NOTE!=null){
			organizationBean.setNOTE(NOTE);
		}
		if(ADULTNOTE!=null){
			organizationBean.setADULTNOTE(ADULTNOTE);
		}
		/*
		if(RSDNBLDG_NAME!=null){
			organizationBean.setRSDNBLDG_NAME(RSDNBLDG_NAME);
		}
		*/
	}
	
	public String addorganization() throws Exception {		
		
		if(!userflag()){return "json";}
		
		String sql="select count(*) from CP_MK_ORG_ORGANIZATION_" + DIST_CD.substring(0,2) +  " where ORG_NAME = '" + ORG_NAME  + "'";
		if(!DispCommon.isMunicipalitiesID(DIST_CD.substring(0, 2))){
		 sql="select count(*) from CP_MK_ORG_ORGANIZATION_" + DIST_CD.substring(0,2) +  " where DIST_CD like '" +  DIST_CD.substring(0,4) + "%' and  ORG_NAME = '" + ORG_NAME  + "'";
		}
		if(addrQueryDao.getQueryCount(sql)>0){
			saveMessage="此城市中存在同样的机构名,不能添加此数据!";
			return "json";
		}
		configOrganizationBean();
		@SuppressWarnings("unused")
		boolean m=addrConfigDao.addOrganization(organizationBean,getSessionUser());

		saveMessage="新增成功";
		return "json";
	}
	
	public String addorgandius() throws Exception {		
		
		if(!userflag()){return "json";}
		
		String sql="select count(*) from CP_MK_ORG_ORGANIZATION_" + DIST_CD.substring(0,2) +  " where ORG_NAME = '" + ORG_NAME  + "'";
		if(!DispCommon.isMunicipalitiesID(DIST_CD.substring(0, 2))){
		 sql="select count(*) from CP_MK_ORG_ORGANIZATION_" + DIST_CD.substring(0,2) +  " where DIST_CD like '" +  DIST_CD.substring(0,4) + "%' and  ORG_NAME = '" + ORG_NAME  + "'";
		}
		if(addrQueryDao.getQueryCount(sql)>0){
			saveMessage="此城市中存在同样的机构名,不能添加此数据!";
			return "json";
		}
		configOrganizationBean();
		@SuppressWarnings("unused")
		boolean m=addrConfigDao.addOrgandius(organizationBean,getSessionUser());

		saveMessage="新增成功";
		return "json";
	}
	
	public String uporganization() throws Exception {		
		
		if(!userflag()){return "json";}
		
		String sql="select count(*) from CP_MK_ORG_ORGANIZATION_" + DIST_CD.substring(0,2) +  " where ORG_ID != " + ORG_ID + " and  ORG_NAME = '" + ORG_NAME  + "'";
		if(!DispCommon.isMunicipalitiesID(DIST_CD.substring(0, 2))){
		  sql="select count(*) from CP_MK_ORG_ORGANIZATION_" + DIST_CD.substring(0,2) +  " where DIST_CD like '" +  DIST_CD.substring(0,4) + "%' and  ORG_ID != " + ORG_ID + " and  ORG_NAME = '" + ORG_NAME  + "'";
		}
		if(addrQueryDao.getQueryCount(sql)>0){
			saveMessage="此城市中存在同样的机构名,不能修改此数据!";
			return "json";
		}
		configOrganizationBean();
		@SuppressWarnings("unused")
		boolean m=addrConfigDao.upOrganization(organizationBean,getSessionUser());

		saveMessage="修改成功";
		return "json";
	}
	
	public String luanorganization() throws Exception {		
		
		if(!userflag()){return "json";}
		
		String sql="select count(*) from CP_MK_ORG_ORGANIZATION_" + DIST_CD.substring(0,2) +  " where ORG_ID != " + ORG_ID + " and  ORG_NAME = '" + ORG_NAME  + "'";
		if(!DispCommon.isMunicipalitiesID(DIST_CD.substring(0, 2))){
		  sql="select count(*) from CP_MK_ORG_ORGANIZATION_" + DIST_CD.substring(0,2) +  " where DIST_CD like '" +  DIST_CD.substring(0,4) + "%' and  ORG_ID != " + ORG_ID + " and  ORG_NAME = '" + ORG_NAME  + "'";
		}
		if(addrQueryDao.getQueryCount(sql)>0){
			saveMessage="此城市中存在同样的机构名,不能修改此数据!";
			return "json";
		}
		configOrganizationBean();
		@SuppressWarnings("unused")
		boolean m=addrConfigDao.luanorganization(organizationBean,getSessionUser());

		saveMessage="修改成功";
		return "json";
	}
	
	public String delorganization() throws Exception {		
		
		if(!userflag()){return "json";}
		
		configOrganizationBean();
		@SuppressWarnings("unused")
		boolean m=addrConfigDao.delOrganization(organizationBean,getSessionUser());

		saveMessage="删除成功";
		return "json";
	}
	
	public String delorganizationdius() throws Exception {		
		
		if(!userflag()){return "json";}
		
		configOrganizationBean();
		@SuppressWarnings("unused")
		boolean m=addrConfigDao.delOrganizationdius(organizationBean,getSessionUser());

		saveMessage="删除成功";
		return "json";
	}
	
	public String delorgdiustssq() throws Exception {		
		
		if(!userflag()){return "json";}
		
		organizationBean=new OrganizationBean();
		organizationBean.setORG_ID(TOTAL_ALL_VALUE);
		if(DIST_CD!=null){
			organizationBean.setDIST_CD(DIST_CD);
		}
		boolean m=addrConfigDao.delorgdiustssq(organizationBean,getSessionUser());
	
		saveMessage="提交成功";
		return "json";
	}
	
	public String delorganizationluan() throws Exception {		
		
		if(!userflag()){return "json";}
		
		configOrganizationBean();
		@SuppressWarnings("unused")
		boolean m=addrConfigDao.delOrganizationluan(organizationBean,getSessionUser());
		
		saveMessage="删除成功";
		return "json";
	}
	
	public String fnorganizationluan() throws Exception {		
		
		if(!userflag()){return "json";}
		
		organizationBean=new OrganizationBean();
		organizationBean.setORG_ID(TOTAL_ALL_VALUE);
		if(DIST_CD!=null){
			organizationBean.setDIST_CD(DIST_CD);
		}
		@SuppressWarnings("unused")
		boolean m=addrConfigDao.fnorganizationluan(organizationBean,getSessionUser());
		
		saveMessage="操作成功";
		return "json";
	}
	
	public String shorganization() throws Exception {				
		
		if(!userflag()){return "json";}
		
		configOrganizationBean();
		@SuppressWarnings("unused")
		boolean m=addrConfigDao.shOrganization(organizationBean,getSessionUser());
		if(organizationBean.getDATA_FLAG().equals("2")){
			saveMessage="审核同意成功";
		}else{
			saveMessage="审核拒绝成功";
		}
		
		return "json";
	}
	
	public String shorganizations() throws Exception {			
		
		if(!userflag()){return "json";}
		
		String[] tTOTAL_ALL_VALUE = TOTAL_ALL_VALUE.split(",");
		for(int i=0;i<tTOTAL_ALL_VALUE.length;i++){
			if(tTOTAL_ALL_VALUE[i].indexOf("@")>1){
				String[] tTOTAL_ALL_VALUEN = tTOTAL_ALL_VALUE[i].split("@");
				organizationBean = new OrganizationBean();
				organizationBean.setDATA_FLAG(DATA_FLAG);
				organizationBean.setDIST_CD(tTOTAL_ALL_VALUEN[0]);
				organizationBean.setORG_ID(tTOTAL_ALL_VALUEN[1]);
				organizationBean.setTOTAL_STREET_NAME(" ");
				//System.out.println(organizationBean.getDATA_FLAG());
				//System.out.println(organizationBean.getDIST_CD());
				//System.out.println(organizationBean.getORG_ID());
				boolean m=addrConfigDao.shOrganization(organizationBean,getSessionUser());
			}
		}		
		if(DATA_FLAG.equals("2")){
			saveMessage="审核同意成功";
		}else{
			saveMessage="审核拒绝成功";
		}
		
		return "json";
	}
	
	public void configQhzuiBean(){
		qhzuiBean=new QhzuiBean();
		if(QIANZUI_ID!=null){
			qhzuiBean.setQIANZUI_ID(QIANZUI_ID);
		}
		if(DIST_CD!=null){
			qhzuiBean.setDIST_CD(DIST_CD);
		}
		if(FIX!=null){
			qhzuiBean.setFIX(FIX);
		}
		if(FIX_ABBR!=null){
			qhzuiBean.setFIX_ABBR(FIX_ABBR);
		}
		if(FIX_XZ!=null){
			qhzuiBean.setFIX_XZ(FIX_XZ);
		}
		if(FIX_FLAG!=null){
			qhzuiBean.setFIX_FLAG(FIX_FLAG);
		}
		if(FIX_SMP!=null){
			qhzuiBean.setFIX_SMP(FIX_SMP);
		}
	}
	
	public String addqhzuis() throws Exception {		
		
		if(!userflag()){return "json";}
		
		String sql="select count(*) from CP_WH_QHZUI where  DIST_CD = '" + DIST_CD  + "' and FIX = '" + FIX  + "' and FIX_FLAG = '" + FIX_FLAG  + "'";
		if(addrQueryDao.getQueryCount(sql)>0){
			saveMessage="此城市中存在同样的前后缀,不能添加此数据!";
			return "json";
		}
		configQhzuiBean();
		@SuppressWarnings("unused")
		boolean m=addrConfigDao.addQhzuis(qhzuiBean,getSessionUser());

		saveMessage="新增成功";
		return "json";
	}
	
	public String upqhzuis() throws Exception {		
		
		if(!userflag()){return "json";}
		
		String sql="select count(*) from CP_WH_QHZUI where  DIST_CD = '" + DIST_CD  + "' and FIX = '" + FIX  + "' and FIX_FLAG = '" + FIX_FLAG  + "'";
		if(addrQueryDao.getQueryCount(sql)>0){
			saveMessage="此城市中存在同样的前后缀,不能添加此数据!";
			return "json";
		}
		configQhzuiBean();
		@SuppressWarnings("unused")
		boolean m=addrConfigDao.upQhzuis(qhzuiBean,getSessionUser());

		saveMessage="修改成功";
		return "json";
	}
	
	public String delqhzuis() throws Exception {		
		
		if(!userflag()){return "json";}
		
		configQhzuiBean();
		@SuppressWarnings("unused")
		boolean m=addrConfigDao.delQhzuis(qhzuiBean,getSessionUser());

		saveMessage="删除成功";
		return "json";
	}
	
	public void configExgcltitemopeBean(){
		exgcltitemopeBean=new ExgcltitemopeBean();
		if(SEQID!=null){
			exgcltitemopeBean.setSEQID(SEQID);
		}
		if(PK_REMARK!=null){
			exgcltitemopeBean.setPK_REMARK(PK_REMARK);
		}
		if(DT_PK_CODE!=null){
			exgcltitemopeBean.setDT_PK_CODE(DT_PK_CODE);
		}
		if(DM_PK_CODE!=null){
			exgcltitemopeBean.setDM_PK_CODE(DM_PK_CODE);
		}
		if(PG_PK_CODE!=null){
			exgcltitemopeBean.setPG_PK_CODE(PG_PK_CODE);
		}
		if(PK_ALLADDR!=null){
			exgcltitemopeBean.setPK_ALLADDR(PK_ALLADDR);
		}
		if(REC_ALLADDRSTREET!=null){
			exgcltitemopeBean.setREC_ALLADDRSTREET(REC_ALLADDRSTREET);
		}
		if(ADDR_FLAG!=null){
			exgcltitemopeBean.setADDR_FLAG(ADDR_FLAG);
		}
	}
	
	public String uprgpqpd() throws Exception {		
		
		if(!userflag()){return "json";}
		
		/*
		String[] tTOTAL_ALL_VALUE = TOTAL_ALL_VALUE.split(",");
		for(int i=0;i<tTOTAL_ALL_VALUE.length;i++){
			exgcltitemopeBean=new ExgcltitemopeBean();
			exgcltitemopeBean.setSEQID(tTOTAL_ALL_VALUE[i]);
			if(PK_REMARK!=null){
				exgcltitemopeBean.setPK_REMARK(PK_REMARK);
			}
			if(DT_PK_CODE!=null){
				exgcltitemopeBean.setDT_PK_CODE(DT_PK_CODE);
			}
			if(DM_PK_CODE!=null){
				exgcltitemopeBean.setDM_PK_CODE(DM_PK_CODE);
			}
			if(PG_PK_CODE!=null){
				exgcltitemopeBean.setPG_PK_CODE(PG_PK_CODE);
			}
			boolean m=addrConfigDao.uprgpqpd(exgcltitemopeBean,getSessionUser());
		}		
		*/
		exgcltitemopeBean=new ExgcltitemopeBean();
		exgcltitemopeBean.setSEQID(TOTAL_ALL_VALUE);
		if(PK_REMARK!=null){
			exgcltitemopeBean.setPK_REMARK(PK_REMARK);
		}
		if(DT_PK_CODE!=null){
			exgcltitemopeBean.setDT_PK_CODE(DT_PK_CODE);
		}
		if(DM_PK_CODE!=null){
			exgcltitemopeBean.setDM_PK_CODE(DM_PK_CODE);
		}
		if(PG_PK_CODE!=null){
			exgcltitemopeBean.setPG_PK_CODE(PG_PK_CODE);
		}
		if(ISWHQUERY!=null){
			exgcltitemopeBean.setISWHQUERY(ISWHQUERY);
		}
		boolean m=addrConfigDao.uprgpqpd(exgcltitemopeBean,getSessionUser());
	
		saveMessage="提交成功";
		return "json";
	}
	
	public String uprgpqpdn() throws Exception {		
		
		if(!userflag()){return "json";}
		
		
		exgcltitemopeBean=new ExgcltitemopeBean();
		exgcltitemopeBean.setSEQID(TOTAL_ALL_VALUE);
		if(PK_REMARK!=null){
			exgcltitemopeBean.setPK_REMARK(PK_REMARK);
		}
		if(DT_PK_CODE!=null){
			exgcltitemopeBean.setDT_PK_CODE(DT_PK_CODE);
		}
		if(DM_PK_CODE!=null){
			exgcltitemopeBean.setDM_PK_CODE(DM_PK_CODE);
		}
		if(PG_PK_CODE!=null){
			exgcltitemopeBean.setPG_PK_CODE(PG_PK_CODE);
		}
		if(ISWHQUERY!=null){
			exgcltitemopeBean.setISWHQUERY(ISWHQUERY);
		}
		boolean m=addrConfigDao.uprgpqpdn(exgcltitemopeBean,getSessionUser());
	
		saveMessage="提交成功";
		return "json";
	}
	
	public String tssq() throws Exception {		
		
		if(!userflag()){return "json";}
		
		exgcltitemopeBean=new ExgcltitemopeBean();
		exgcltitemopeBean.setSEQID(TOTAL_ALL_VALUE);
		if(DIST_CD!=null){
			exgcltitemopeBean.setDIST_CD(DIST_CD);
			exgcltitemopeBean.setNEWCITYCODE(NEWCITYCODE);
		}
		boolean m=addrConfigDao.tssq(exgcltitemopeBean,getSessionUser());
	
		saveMessage="提交成功";
		return "json";
	}

	public void configOpexxsbbocBeanBean(){
		opexxsbbocBean=new OpexxsbbocBean();
		if(SEQID!=null){
			opexxsbbocBean.setSEQID(SEQID);
		}
		if(ITEMNO!=null){
			opexxsbbocBean.setITEMNO(ITEMNO);
		}
		if(REC_ALLADDR!=null){
			opexxsbbocBean.setREC_ALLADDR(REC_ALLADDR);
		}
		if(REC_ORG!=null){
			opexxsbbocBean.setREC_ORG(REC_ORG);
		}
		if(DIST_CD!=null){
			opexxsbbocBean.setDIST_CD(DIST_CD);
		}
		if(STRT1_NAME!=null){
			opexxsbbocBean.setSTRT1_NAME(STRT1_NAME.trim());
			opexxsbbocBean.setSEG_NUM("1");
		}
		if(STRT2_NAME!=null){
			opexxsbbocBean.setSTRT2_NAME(STRT2_NAME.trim());
			if(STRT2_NAME.length()>0){
				opexxsbbocBean.setSEG_NUM("2");
			}
		}
		if(STRT3_NAME!=null){
			opexxsbbocBean.setSTRT3_NAME(STRT3_NAME.trim());
			if(STRT3_NAME.length()>0){
				opexxsbbocBean.setSEG_NUM("3");
			}
		}
		if(STRT4_NAME!=null){
			opexxsbbocBean.setSTRT4_NAME(STRT4_NAME.trim());
			if(STRT4_NAME.length()>0){
				opexxsbbocBean.setSEG_NUM("4");
			}
		}
		if(STRT5_NAME!=null){
			opexxsbbocBean.setSTRT5_NAME(STRT5_NAME.trim());
			if(STRT5_NAME.length()>0){
				opexxsbbocBean.setSEG_NUM("5");
			}
		}
		if(STRT_ABBR!=null){
			opexxsbbocBean.setSTRT_ABBR(STRT_ABBR.trim());
		}
		if(STRT1_ABBR_NAME!=null){
			opexxsbbocBean.setSTRT1_ABBR_NAME(STRT1_ABBR_NAME.trim());
		}
		if(STRT2_ABBR_NAME!=null){
			opexxsbbocBean.setSTRT2_ABBR_NAME(STRT2_ABBR_NAME.trim());
		}
		if(STRT3_ABBR_NAME!=null){
			opexxsbbocBean.setSTRT3_ABBR_NAME(STRT3_ABBR_NAME.trim());
		}
		if(STRT4_ABBR_NAME!=null){
			opexxsbbocBean.setSTRT4_ABBR_NAME(STRT4_ABBR_NAME.trim());
		}
		if(STRT5_ABBR_NAME!=null){
			opexxsbbocBean.setSTRT5_ABBR_NAME(STRT5_ABBR_NAME.trim());
		}
		if(POST_CD!=null){
			opexxsbbocBean.setPOST_CD(POST_CD);
		}
		if(NOTE!=null){
			opexxsbbocBean.setNOTE(NOTE);
		}
		if(RSDNBLDG_NAME!=null){
			opexxsbbocBean.setRSDNBLDG_NAME(RSDNBLDG_NAME);
		}
		if(RSDNBLDG_ABBR!=null){
			opexxsbbocBean.setRSDNBLDG_ABBR(RSDNBLDG_ABBR);
		}
		if(ORG_NAME!=null){
			opexxsbbocBean.setORG_NAME(ORG_NAME);
		}
		if(ORG_ABBR_NAME!=null){
			opexxsbbocBean.setORG_ABBR_NAME(ORG_ABBR_NAME);
		}
		if(ORG_ABBR!=null){
			opexxsbbocBean.setORG_ABBR(ORG_ABBR);
		}
		if(DT_PK_CODE!=null){
			opexxsbbocBean.setDT_PK_CODE(DT_PK_CODE);
		}
		if(DM_PK_CODE!=null){
			opexxsbbocBean.setDM_PK_CODE(DM_PK_CODE);
		}
		if(PG_PK_CODE!=null){
			opexxsbbocBean.setPG_PK_CODE(PG_PK_CODE);
		}
		if(START_NUM!=null){
			opexxsbbocBean.setSTART_NUM(START_NUM);
		}
		if(PREFIX!=null){
			opexxsbbocBean.setPREFIX(PREFIX);
		}
		if(END_NUM!=null){
			opexxsbbocBean.setEND_NUM(END_NUM);
		}
		if(SUFFIX!=null){
			opexxsbbocBean.setSUFFIX(SUFFIX);
		}
		if(NUM_FLAG!=null){
			opexxsbbocBean.setNUM_FLAG(NUM_FLAG);
		}
	}
	
	public String uprgming() throws Exception {		
		
		if(!userflag()){return "json";}
		
		
		configOpexxsbbocBeanBean();
		boolean m=addrConfigDao.uprgming(opexxsbbocBean,getSessionUser());		
	
		saveMessage="提交成功";
		return "json";
	}
	
	public Object getModel() {
		// TODO Auto-generated method stub
		return null;
	}
	

	public String addbldgrsdnsdiusorg() throws Exception {		
		
		if(!userflag()){return "json";}
		
		String sql="select count(*) from CP_MK_ADR_BLDGRSDNS_" + DIST_CD.substring(0,2) + " where DIST_CD = " +  DIST_CD + " and  STRT_ID = " +  STRT_ID   +  " and  RSDNBLDG_NAME = '" + RSDNBLDG_NAME  + "'";
		if(addrQueryDao.getQueryCount(sql)>0){
			saveMessage="此街道下存在同样的小区名,不能添加此数据!";
			return "json";
		}
		bldgrsdnsBean=new BldgrsdnsBean();
		if(DATA_FLAG!=null){
			bldgrsdnsBean.setDATA_FLAG(DATA_FLAG);
		}
		if(ORG_ID!=null){
			bldgrsdnsBean.setRSDNBLDG_ID(ORG_ID);
		}
		if(ORG_IDTMP!=null){
			bldgrsdnsBean.setRSDNBLDG_IDTMP(ORG_IDTMP);
		}
		if(STRT_ID!=null){
			bldgrsdnsBean.setSTRT_ID(STRT_ID);
		}
		if(DIST_CD!=null){
			bldgrsdnsBean.setDIST_CD(DIST_CD);
		}
		if(ORG_NAME!=null){
			bldgrsdnsBean.setRSDNBLDG_NAME(ORG_NAME.trim());
			}
		if(ORG_ABBR!=null){
			bldgrsdnsBean.setRSDNBLDG_ABBR(ORG_ABBR.trim());
		}
		if(TOTAL_STREET_NAME!=null){
			bldgrsdnsBean.setTOTAL_STREET_NAME(TOTAL_STREET_NAME);
		}
		if(POST_CD!=null){
			bldgrsdnsBean.setPOST_CD(POST_CD);
		}
		if(NUM_FLAG!=null){
			bldgrsdnsBean.setNUM_FLAG(NUM_FLAG);
		}
		if(PREFIX!=null){
			bldgrsdnsBean.setPREFIX(PREFIX);
		}
		if(START_NUM!=null){
			bldgrsdnsBean.setSTART_NUM(START_NUM);
		}
		if(END_NUM!=null){
			bldgrsdnsBean.setEND_NUM(END_NUM);
		}
		if(SUFFIX!=null){
			bldgrsdnsBean.setSUFFIX(SUFFIX);
		}
		if(DT_PK_CODE!=null){
			bldgrsdnsBean.setDT_PK_CODE(DT_PK_CODE);
		}
		if(DM_PK_CODE!=null){
			bldgrsdnsBean.setDM_PK_CODE(DM_PK_CODE);
		}
		if(PG_PK_CODE!=null){
			bldgrsdnsBean.setPG_PK_CODE(PG_PK_CODE);
		}
		if(NOTE!=null){
			bldgrsdnsBean.setNOTE(NOTE);
		}
		if(ADULTNOTE!=null){
			bldgrsdnsBean.setADULTNOTE(ADULTNOTE);
		}
		@SuppressWarnings("unused")
		boolean m=addrConfigDao.addBldgrsdnsdiusorg(bldgrsdnsBean,getSessionUser());

		saveMessage="新增成功";
		return "json";
	}
	
	public void configCpwhrlpgstBean(){
		cpwhrlpgstBean=new CpwhrlpgstBean();
		if(TOTAL_ALL_VALUE!=null){
			cpwhrlpgstBean.setRL_PG_ST_ID(TOTAL_ALL_VALUE);
		}
		if(DIST_CD!=null){
			cpwhrlpgstBean.setDIST_CD(DIST_CD);
		}
		if(DT_PK_CODE!=null){
			cpwhrlpgstBean.setDT_PK_CODE(DT_PK_CODE);
		}
		if(DM_PK_CODE!=null){
			cpwhrlpgstBean.setDM_PK_CODE(DM_PK_CODE);
		}
		if(PG_PK_CODE!=null){
			cpwhrlpgstBean.setPG_PK_CODE(PG_PK_CODE);
		}
	}
	
	public String delzshf() throws Exception {		
		
		if(!userflag()){return "json";}
		
		
		configCpwhrlpgstBean();
		
		@SuppressWarnings("unused")
		boolean m=addrConfigDao.delzshf(cpwhrlpgstBean,getSessionUser());
		
		saveMessage="操作成功";
		return "json";
	}
	
	public String delzsyq() throws Exception {		
		
		if(!userflag()){return "json";}
		
			
			configCpwhrlpgstBean();
			
			@SuppressWarnings("unused")
			boolean m=addrConfigDao.delzsyq(cpwhrlpgstBean,getSessionUser());
			
			saveMessage="操作成功";
			return "json";
		}
	
	public String delzsyb() throws Exception {		
		
		if(!userflag()){return "json";}
		
		
		configCpwhrlpgstBean();
		
		@SuppressWarnings("unused")
		boolean m=addrConfigDao.delzsyb(cpwhrlpgstBean,getSessionUser());
		
		saveMessage="操作成功";
		return "json";
	}
	
	public String delzsyd() throws Exception {		
		
		if(!userflag()){return "json";}
		
		
		configCpwhrlpgstBean();
		
		@SuppressWarnings("unused")
		boolean m=addrConfigDao.delzsyd(cpwhrlpgstBean,getSessionUser());
		
		saveMessage="操作成功";
		return "json";
	}
	
	public String upzsqbd() throws Exception {		
		
		if(!userflag()){return "json";}
		
		
		configCpwhrlpgstBean();
		
		@SuppressWarnings("unused")
		boolean m=addrConfigDao.upzsqbd(cpwhrlpgstBean,getSessionUser());
		
		saveMessage="操作成功";
		return "json";
	}
	
	public String upfkqr() throws Exception {		
		
		if(!userflag()){return "json";}
		
		tbsrprocessBean=new TBSRPROCESSBean();
		tbsrprocessBean.setSEQID(TOTAL_ALL_VALUE);
		boolean m=addrConfigDao.upfkqr(tbsrprocessBean,getSessionUser());
	
		saveMessage="提交成功";
		return "json";
	}
	
	public String delfkqr() throws Exception {		
		
		if(!userflag()){return "json";}
		
		tbsrprocessBean=new TBSRPROCESSBean();
		tbsrprocessBean.setSEQID(TOTAL_ALL_VALUE);
		boolean m=addrConfigDao.delfkqr(tbsrprocessBean,getSessionUser());
	
		saveMessage="提交成功";
		return "json";
	}
}
