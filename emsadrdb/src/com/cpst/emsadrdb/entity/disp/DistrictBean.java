package com.cpst.emsadrdb.entity.disp;

public class DistrictBean {
	
	private String DISTRICT_CODE;
    private String PROVINCE_NAME;
    private String CITY_NAME;
    private String COUNTY_NAME;
    private String PROVINCE_SIM;
    private String CITY_SIM;
    private String COUNTY_SIM;
    private String PROVINCE_SIM_NAME;
    private String CITY_SIM_NAME;
    private String COUNTY_SIM_NAME;
    private String DEGREE;
    private String PROVINCE_SIMNAME;
    private String CITY_FLAG;
    private String CITY_FLAGNAME;
    
	public String getCITY_FLAG() {
		return CITY_FLAG;
	}
	public void setCITY_FLAG(String city_flag) {
		CITY_FLAG = city_flag;
	}
	public String getCITY_FLAGNAME() {
		if(CITY_FLAGNAME!=null){
			if(CITY_FLAGNAME.equals("1")){
				CITY_FLAGNAME = "城区";
			}else if(CITY_FLAGNAME.equals("0")){
				CITY_FLAGNAME = "郊县";
			}
		}else{
			CITY_FLAGNAME = "郊县";
		}
		return CITY_FLAGNAME;
	}
	public void setCITY_FLAGNAME(String city_flagname) {
		CITY_FLAGNAME = city_flagname;
	}
	public String getDISTRICT_CODE() {
		return DISTRICT_CODE;
	}
	public void setDISTRICT_CODE(String district_code) {
		DISTRICT_CODE = district_code;
	}
	public String getPROVINCE_NAME() {
		return PROVINCE_NAME;
	}
	public void setPROVINCE_NAME(String province_name) {
		PROVINCE_NAME = province_name;
	}
	public String getCITY_NAME() {
		return CITY_NAME;
	}
	public void setCITY_NAME(String city_name) {
		CITY_NAME = city_name;
	}
	public String getCOUNTY_NAME() {
		return COUNTY_NAME;
	}
	public void setCOUNTY_NAME(String county_name) {
		COUNTY_NAME = county_name;
	}
	public String getPROVINCE_SIM() {
		return PROVINCE_SIM;
	}
	public void setPROVINCE_SIM(String province_sim) {
		PROVINCE_SIM = province_sim;
	}
	public String getCITY_SIM() {
		return CITY_SIM;
	}
	public void setCITY_SIM(String city_sim) {
		CITY_SIM = city_sim;
	}
	public String getCOUNTY_SIM() {
		return COUNTY_SIM;
	}
	public void setCOUNTY_SIM(String county_sim) {
		COUNTY_SIM = county_sim;
	}
	public String getPROVINCE_SIM_NAME() {
		return PROVINCE_SIM_NAME;
	}
	public void setPROVINCE_SIM_NAME(String province_sim_name) {
		PROVINCE_SIM_NAME = province_sim_name;
	}
	public String getCITY_SIM_NAME() {
		return CITY_SIM_NAME;
	}
	public void setCITY_SIM_NAME(String city_sim_name) {
		CITY_SIM_NAME = city_sim_name;
	}
	public String getCOUNTY_SIM_NAME() {
		return COUNTY_SIM_NAME;
	}
	public void setCOUNTY_SIM_NAME(String county_sim_name) {
		COUNTY_SIM_NAME = county_sim_name;
	}
	public String getDEGREE() {
		return DEGREE;
	}
	public void setDEGREE(String degree) {
		DEGREE = degree;
	}
	public String getPROVINCE_SIMNAME() {
		return PROVINCE_SIMNAME;
	}
	public void setPROVINCE_SIMNAME(String province_simname) {
		PROVINCE_SIMNAME = province_simname;
	}
    
    
}
