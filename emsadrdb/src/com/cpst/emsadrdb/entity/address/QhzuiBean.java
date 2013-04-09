package com.cpst.emsadrdb.entity.address;

public class QhzuiBean {
	
	
    private String DIST_CD;
    private String QIANZUI_ID;
    private String FIX;
    private String FIX_ABBR;
    private String FIX_XZ;
    private String FIX_FLAG;
    private String FIX_SMP;
    private String FIX_FLAGNAME;
    private String TOTAL_DISTRICT_NAME;
	public String getDIST_CD() {
		return DIST_CD;
	}
	public void setDIST_CD(String dist_cd) {
		DIST_CD = dist_cd;
	}
	public String getQIANZUI_ID() {
		return QIANZUI_ID;
	}
	public void setQIANZUI_ID(String qianzui_id) {
		QIANZUI_ID = qianzui_id;
	}
	public String getFIX() {
		return FIX;
	}
	public void setFIX(String fix) {
		FIX = fix;
	}
	public String getFIX_ABBR() {
		return FIX_ABBR;
	}
	public void setFIX_ABBR(String fix_abbr) {
		FIX_ABBR = fix_abbr;
	}
	public String getFIX_XZ() {
		return FIX_XZ;
	}
	public void setFIX_XZ(String fix_xz) {
		FIX_XZ = fix_xz;
	}
	public String getFIX_FLAG() {
		return FIX_FLAG;
	}
	public void setFIX_FLAG(String fix_flag) {
		FIX_FLAG = fix_flag;
	}
	public String getFIX_SMP() {
		return FIX_SMP;
	}
	public void setFIX_SMP(String fix_smp) {
		FIX_SMP = fix_smp;
	}
	public String getFIX_FLAGNAME() {
		if(FIX_FLAGNAME!=null){
			if(FIX_FLAGNAME.equals("0")){
				FIX_FLAGNAME = "前缀";
			}else if(FIX_FLAGNAME.equals("1")){
				FIX_FLAGNAME = "后缀";
			}
		}else{
			FIX_FLAGNAME = "前缀";
		}
		return FIX_FLAGNAME;
	}
	public void setFIX_FLAGNAME(String fix_flagname) {
		FIX_FLAGNAME = fix_flagname;
	}
	public String getTOTAL_DISTRICT_NAME() {
		return TOTAL_DISTRICT_NAME;
	}
	public void setTOTAL_DISTRICT_NAME(String total_district_name) {
		TOTAL_DISTRICT_NAME = total_district_name;
	}

   
}
