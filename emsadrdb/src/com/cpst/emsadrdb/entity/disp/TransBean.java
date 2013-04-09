package com.cpst.emsadrdb.entity.disp;

public class TransBean {
	
	private String TRANS_CODE;
    private String TRANS_NAME;
    private String PROVINCE_CODE;
    private String PROVINCE_NAME;
    private String DISP_OFFICE_CODE;
    private String DISP_OFFICE_NAME;
    private String DEGREE;
    private String FLAG;
    private String FLAG_NAME;
    private String DATA_DATE;
    private String DATA_FLAG;
    private String TOTAL_DISTRICT_NAME;
    private String DATA_USER;
    private String WHDIS;
    private String DT_PK_CODE;
    private String DT_NAME;
    
    
	public String getDT_PK_CODE() {
		return DT_PK_CODE;
	}
	public void setDT_PK_CODE(String dt_pk_code) {
		DT_PK_CODE = dt_pk_code;
	}
	public String getDT_NAME() {
		return DT_NAME;
	}
	public void setDT_NAME(String dt_name) {
		DT_NAME = dt_name;
	}
	public String getWHDIS() {
		return WHDIS;
	}
	public void setWHDIS(String whdis) {
		WHDIS = whdis;
	}
	public String getFLAG_NAME() {
		if(FLAG_NAME!=null){
			if(FLAG_NAME.equals("3")){
				FLAG_NAME = "外省";
			}else if(FLAG_NAME.equals("2")){
				FLAG_NAME = "外市";
			}else if(FLAG_NAME.equals("1")){
				FLAG_NAME = "本市";
			}
		}else{
			FLAG_NAME = "本市";
		}
		return FLAG_NAME;
	}
	public void setFLAG_NAME(String flag_name) {
		FLAG_NAME = flag_name;
	}
	public String getTOTAL_DISTRICT_NAME() {
		return TOTAL_DISTRICT_NAME;
	}
	public void setTOTAL_DISTRICT_NAME(String total_district_name) {
		TOTAL_DISTRICT_NAME = total_district_name;
	}
	public String getDISP_OFFICE_NAME() {
		return DISP_OFFICE_NAME;
	}
	public void setDISP_OFFICE_NAME(String disp_office_name) {
		DISP_OFFICE_NAME = disp_office_name;
	}
	public String getTRANS_CODE() {
		return TRANS_CODE;
	}
	public void setTRANS_CODE(String trans_code) {
		TRANS_CODE = trans_code;
	}
	public String getTRANS_NAME() {
		return TRANS_NAME;
	}
	public void setTRANS_NAME(String trans_name) {
		TRANS_NAME = trans_name;
	}
	public String getPROVINCE_CODE() {
		return PROVINCE_CODE;
	}
	public void setPROVINCE_CODE(String province_code) {
		PROVINCE_CODE = province_code;
	}
	public String getPROVINCE_NAME() {
		return PROVINCE_NAME;
	}
	public void setPROVINCE_NAME(String province_name) {
		PROVINCE_NAME = province_name;
	}
	public String getDISP_OFFICE_CODE() {
		return DISP_OFFICE_CODE;
	}
	public void setDISP_OFFICE_CODE(String disp_office_code) {
		DISP_OFFICE_CODE = disp_office_code;
	}
	public String getDEGREE() {
		return DEGREE;
	}
	public void setDEGREE(String degree) {
		DEGREE = degree;
	}
	public String getFLAG() {
		return FLAG;
	}
	public void setFLAG(String flag) {
		FLAG = flag;
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
	public String getDATA_USER() {
		return DATA_USER;
	}
	public void setDATA_USER(String data_user) {
		DATA_USER = data_user;
	}	
    
    
}
