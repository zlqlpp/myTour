package com.cpst.emsadrdb.entity.address;

public class LogBean {
  private String LOG_ID;          
  private String DIST_CD;      
  private String DIST_NAME;
  private String STRT_ID;         
  private String STRT_NAME;         
  private String RSDNBLDG_ID;     
  private String RSDNBLDG_NAME;   
  private String ORG_ID;          
  private String ORG_NAME;        
  private String DATA_DATE;       
  private String DATA_FLAG;       
  private String DATA_USER;     
  private String DATA_USER_NAME;       
  private String DATA_FLAG_NAME;
  	
	public String getDATA_USER_NAME() {
	return DATA_USER_NAME;
}
public void setDATA_USER_NAME(String data_user_name) {
	DATA_USER_NAME = data_user_name;
}
	public String getDIST_NAME() {
		return DIST_NAME;
	}
	public void setDIST_NAME(String dist_name) {
		DIST_NAME = dist_name;
	}
	public String getDATA_FLAG_NAME() {
		if(DATA_FLAG_NAME!=null){
			if(DATA_FLAG_NAME.equals("2")){
				DATA_FLAG_NAME = "审核同意";
			}else if(DATA_FLAG_NAME.equals("6")){
				DATA_FLAG_NAME = "新增提交";
			}else if(DATA_FLAG_NAME.equals("7")){
				DATA_FLAG_NAME = "修改提交";
			}else if(DATA_FLAG_NAME.equals("8")){
				DATA_FLAG_NAME = "删除提交";
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
		if(LOG_ID==null){
			LOG_ID="";
		}
		return LOG_ID;
	}
	public void setLOG_ID(String log_id) {
		LOG_ID = log_id;
	}
	public String getDIST_CD() {
		if(DIST_CD==null){
			DIST_CD="";
		}
		return DIST_CD;
	}
	public void setDIST_CD(String dist_cd) {
		DIST_CD = dist_cd;
	}
	public String getSTRT_ID() {
		if(STRT_ID==null){
			STRT_ID="";
		}
		return STRT_ID;
	}
	public void setSTRT_ID(String strt_id) {
		STRT_ID = strt_id;
	}
	public String getSTRT_NAME() {
		if(STRT_NAME==null){
			STRT_NAME="";
		}
		return STRT_NAME;
	}
	public void setSTRT_NAME(String strt_name) {
		STRT_NAME = strt_name;
	}
	public String getRSDNBLDG_ID() {
		if(RSDNBLDG_ID==null){
			RSDNBLDG_ID="";
		}
		return RSDNBLDG_ID;
	}
	public void setRSDNBLDG_ID(String rsdnbldg_id) {
		RSDNBLDG_ID = rsdnbldg_id;
	}
	public String getRSDNBLDG_NAME() {
		if(RSDNBLDG_NAME==null){
			RSDNBLDG_NAME="";
		}
		return RSDNBLDG_NAME;
	}
	public void setRSDNBLDG_NAME(String rsdnbldg_name) {
		RSDNBLDG_NAME = rsdnbldg_name;
	}
	public String getORG_ID() {
		if(ORG_ID==null){
			ORG_ID="";
		}
		return ORG_ID;
	}
	public void setORG_ID(String org_id) {
		ORG_ID = org_id;
	}
	public String getORG_NAME() {
		if(ORG_NAME==null){
			ORG_NAME="";
		}
		return ORG_NAME;
	}
	public void setORG_NAME(String org_name) {
		ORG_NAME = org_name;
	}
	public String getDATA_DATE() {
		return DATA_DATE;
	}
	public void setDATA_DATE(String data_date) {
		DATA_DATE = data_date;
	}
	public String getDATA_FLAG() {
		if(DATA_FLAG==null){
			DATA_FLAG="";
		}
		return DATA_FLAG;
	}
	public void setDATA_FLAG(String data_flag) {
		DATA_FLAG = data_flag;
	}
	public String getDATA_USER() {
		if(DATA_USER==null){
			DATA_USER="";
		}
		return DATA_USER;
	}
	public void setDATA_USER(String data_user) {
		DATA_USER = data_user;
	}    
}
