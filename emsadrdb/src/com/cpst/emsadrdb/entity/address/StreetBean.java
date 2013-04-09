package com.cpst.emsadrdb.entity.address;

public class StreetBean {
	
	private String STRT_ID;
    private String DIST_CD;
    private String STRT1ID;
    private String STRT1_NAME;
    private String STRT2ID;
    private String STRT2_NAME;
    private String STRT3ID;
    private String STRT3_NAME;
    private String STRT4ID;
    private String STRT4_NAME;
    private String STRT5ID;
    private String STRT5_NAME;
    private String STRT_ABBR_NAME;
    private String STRT_ABBR;
    private String MIN_BGN_NBR;
    private String STAT_CD;
    private String SEG_NUM;
    private String UPD_DATE;
    private String EMP_NBR;
    private String STAT_FLAG;
    private String DATA_DATE;
    private String DATA_FLAG;
    private String TOTAL_DISTRICT_NAME;
    private String TOTAL_STREET_NAME;
    private String STRT1_ABBR_NAME;
    private String STRT2_ABBR_NAME;
    private String STRT3_ABBR_NAME;
    private String STRT4_ABBR_NAME;
    private String STRT5_ABBR_NAME;
    private String LOG_ID;
    private String DATA_FLAG_NAME;
    private String DATA_USER;
    private String DATA_USER_NAME;
    private String POST_CD;
    
    private String NOTE;
    private String ADULTNOTE;
    private String TOTAL_DIUMENPAI;
    
    private String STRT_IDY;
    private String STRT_IDG;
    
    
	public String getSTRT_IDY() {
		return STRT_IDY;
	}
	public void setSTRT_IDY(String strt_idy) {
		STRT_IDY = strt_idy;
	}
	public String getSTRT_IDG() {
		return STRT_IDG;
	}
	public void setSTRT_IDG(String strt_idg) {
		STRT_IDG = strt_idg;
	}
	public String getTOTAL_DIUMENPAI() {
		return TOTAL_DIUMENPAI;
	}
	public void setTOTAL_DIUMENPAI(String total_diumenpai) {
		TOTAL_DIUMENPAI = total_diumenpai;
	}
	public String getNOTE() {
		return NOTE;
	}
	public void setNOTE(String note) {
		NOTE = note;
	}
	public String getADULTNOTE() {
		return ADULTNOTE;
	}
	public void setADULTNOTE(String adultnote) {
		ADULTNOTE = adultnote;
	}
	
	public String getPOST_CD() {
		return POST_CD;
	}
	public void setPOST_CD(String post_cd) {
		POST_CD = post_cd;
	}
	public String getDATA_USER_NAME() {
		return DATA_USER_NAME;
	}
	public void setDATA_USER_NAME(String data_user_name) {
		DATA_USER_NAME = data_user_name;
	}
	public String getDATA_USER() {
		return DATA_USER;
	}
	public void setDATA_USER(String data_user) {
		DATA_USER = data_user;
	}
	public String getDATA_FLAG_NAME() {
		if(DATA_FLAG_NAME!=null){
			if(DATA_FLAG_NAME.equals("6")){
				DATA_FLAG_NAME = "新增未审";
			}else if(DATA_FLAG_NAME.equals("7")){
				DATA_FLAG_NAME = "修改未审";
			}else if(DATA_FLAG_NAME.equals("8")){
				DATA_FLAG_NAME = "删除未审";
			}else if(DATA_FLAG_NAME.equals("9")){
				DATA_FLAG_NAME = "审核拒绝";
			}else{
				DATA_FLAG_NAME = "正在使用";
			}
		}else{
			DATA_FLAG_NAME = "正在使用";
		}
		return DATA_FLAG_NAME;
	}
	public void setDATA_FLAG_NAME(String data_flag_name) {
		DATA_FLAG_NAME = data_flag_name;
	}
	public String getLOG_ID() {
		return LOG_ID;
	}
	public void setLOG_ID(String log_id) {
		LOG_ID = log_id;
	}
	public String getSTRT1_ABBR_NAME() {
		return STRT1_ABBR_NAME;
	}
	public void setSTRT1_ABBR_NAME(String strt1_abbr_name) {
		STRT1_ABBR_NAME = strt1_abbr_name;
	}
	public String getSTRT2_ABBR_NAME() {
		return STRT2_ABBR_NAME;
	}
	public void setSTRT2_ABBR_NAME(String strt2_abbr_name) {
		STRT2_ABBR_NAME = strt2_abbr_name;
	}
	public String getSTRT3_ABBR_NAME() {
		return STRT3_ABBR_NAME;
	}
	public void setSTRT3_ABBR_NAME(String strt3_abbr_name) {
		STRT3_ABBR_NAME = strt3_abbr_name;
	}
	public String getSTRT4_ABBR_NAME() {
		return STRT4_ABBR_NAME;
	}
	public void setSTRT4_ABBR_NAME(String strt4_abbr_name) {
		STRT4_ABBR_NAME = strt4_abbr_name;
	}
	public String getSTRT5_ABBR_NAME() {
		return STRT5_ABBR_NAME;
	}
	public void setSTRT5_ABBR_NAME(String strt5_abbr_name) {
		STRT5_ABBR_NAME = strt5_abbr_name;
	}
	public String getTOTAL_DISTRICT_NAME() {
		return TOTAL_DISTRICT_NAME;
	}
	public void setTOTAL_DISTRICT_NAME(String total_district_name) {
		TOTAL_DISTRICT_NAME = total_district_name;
	}
	public String getTOTAL_STREET_NAME() {
		return TOTAL_STREET_NAME;
	}
	public void setTOTAL_STREET_NAME(String total_street_name) {
		TOTAL_STREET_NAME = total_street_name;
	}
	public String getSTRT_ID() {
		return STRT_ID;
	}
	public void setSTRT_ID(String strt_id) {
		STRT_ID = strt_id;
	}
	public String getDIST_CD() {
		return DIST_CD;
	}
	public void setDIST_CD(String dist_cd) {
		DIST_CD = dist_cd;
	}
	public String getSTRT1ID() {
		return STRT1ID;
	}
	public void setSTRT1ID(String strt1id) {
		STRT1ID = strt1id;
	}
	public String getSTRT1_NAME() {
		return STRT1_NAME;
	}
	public void setSTRT1_NAME(String strt1_name) {
		STRT1_NAME = strt1_name;
	}
	public String getSTRT2ID() {
		return STRT2ID;
	}
	public void setSTRT2ID(String strt2id) {
		STRT2ID = strt2id;
	}
	public String getSTRT2_NAME() {
		return STRT2_NAME;
	}
	public void setSTRT2_NAME(String strt2_name) {
		STRT2_NAME = strt2_name;
	}
	public String getSTRT3ID() {
		return STRT3ID;
	}
	public void setSTRT3ID(String strt3id) {
		STRT3ID = strt3id;
	}
	public String getSTRT3_NAME() {
		return STRT3_NAME;
	}
	public void setSTRT3_NAME(String strt3_name) {
		STRT3_NAME = strt3_name;
	}
	public String getSTRT4ID() {
		return STRT4ID;
	}
	public void setSTRT4ID(String strt4id) {
		STRT4ID = strt4id;
	}
	public String getSTRT4_NAME() {
		return STRT4_NAME;
	}
	public void setSTRT4_NAME(String strt4_name) {
		STRT4_NAME = strt4_name;
	}
	public String getSTRT5ID() {
		return STRT5ID;
	}
	public void setSTRT5ID(String strt5id) {
		STRT5ID = strt5id;
	}
	public String getSTRT_ABBR_NAME() {
		return STRT_ABBR_NAME;
	}
	public void setSTRT_ABBR_NAME(String strt_abbr_name) {
		STRT_ABBR_NAME = strt_abbr_name;
	}
	public String getSTRT_ABBR() {
		return STRT_ABBR;
	}
	public void setSTRT_ABBR(String strt_abbr) {
		STRT_ABBR = strt_abbr;
	}
	public String getMIN_BGN_NBR() {
		return MIN_BGN_NBR;
	}
	public void setMIN_BGN_NBR(String min_bgn_nbr) {
		MIN_BGN_NBR = min_bgn_nbr;
	}
	public String getSTAT_CD() {
		return STAT_CD;
	}
	public void setSTAT_CD(String stat_cd) {
		STAT_CD = stat_cd;
	}
	public String getSEG_NUM() {
		return SEG_NUM;
	}
	public void setSEG_NUM(String seg_num) {
		SEG_NUM = seg_num;
	}
	public String getUPD_DATE() {
		return UPD_DATE;
	}
	public void setUPD_DATE(String upd_date) {
		UPD_DATE = upd_date;
	}
	public String getEMP_NBR() {
		return EMP_NBR;
	}
	public void setEMP_NBR(String emp_nbr) {
		EMP_NBR = emp_nbr;
	}
	public String getSTAT_FLAG() {
		return STAT_FLAG;
	}
	public void setSTAT_FLAG(String stat_flag) {
		STAT_FLAG = stat_flag;
	}
	public String getDATA_DATE() {
		return DATA_DATE;
	}
	public void setDATA_DATE(String data_date) {
		DATA_DATE = data_date;
	}
	public String getDATA_FLAG() {
		return DATA_FLAG;
	}
	public void setDATA_FLAG(String data_flag) {
		DATA_FLAG = data_flag;
	}
	public String getSTRT5_NAME() {
		return STRT5_NAME;
	}
	public void setSTRT5_NAME(String strt5_name) {
		STRT5_NAME = strt5_name;
	}
    
    
}
