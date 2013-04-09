package com.cpst.emsadrdb.entity.address;

public class ShdataBean {
	private String DIST_CD;
	private String TYPE_ID;
    private String TYPE_FLAG;
    private String TYPE_DATA;
    private String TOTAL_VALUE;
    private String TYPE_FLAG_NAME;
    private String TYPE_DATA_NAME;
    private String TOTAL_NAME;
    
    private String NOTE;
    private String ADULTNOTE;
    
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
    
	public String getDIST_CD() {
		return DIST_CD;
	}
	public void setDIST_CD(String dist_cd) {
		DIST_CD = dist_cd;
	}
	public String getTYPE_ID() {
		return TYPE_ID;
	}
	public void setTYPE_ID(String type_id) {
		TYPE_ID = type_id;
	}
	public String getTYPE_FLAG() {
		return TYPE_FLAG;
	}
	public void setTYPE_FLAG(String type_flag) {
		TYPE_FLAG = type_flag;
	}
	public String getTYPE_DATA() {
		return TYPE_DATA;
	}
	public void setTYPE_DATA(String type_data) {
		TYPE_DATA = type_data;
	}
	public String getTOTAL_VALUE() {
		return TOTAL_VALUE;
	}
	public void setTOTAL_VALUE(String total_value) {
		TOTAL_VALUE = total_value;
	}
	public String getTYPE_FLAG_NAME() {
		if(TYPE_FLAG_NAME!=null){
			if(TYPE_FLAG_NAME.equals("6")){
				TYPE_FLAG_NAME = "新增未审";
			}else if(TYPE_FLAG_NAME.equals("7")){
				TYPE_FLAG_NAME = "修改未审";
			}else if(TYPE_FLAG_NAME.equals("8")){
				TYPE_FLAG_NAME = "删除未审";
			}else if(TYPE_FLAG_NAME.equals("9")){
				TYPE_FLAG_NAME = "审核拒绝";
			}else{
				TYPE_FLAG_NAME = "正在使用";
			}
		}else{
			TYPE_FLAG_NAME = "正在使用";
		}
		return TYPE_FLAG_NAME;
	}
	public void setTYPE_FLAG_NAME(String type_flag_name) {
		TYPE_FLAG_NAME = type_flag_name;
	}
	public String getTYPE_DATA_NAME() {
		if(TYPE_DATA_NAME!=null){
			if(TYPE_DATA_NAME.equals("0")){
				TYPE_DATA_NAME = "街道";
			}else if(TYPE_DATA_NAME.equals("1")){
				TYPE_DATA_NAME = "小区";
			}else if(TYPE_DATA_NAME.equals("2")){
				TYPE_DATA_NAME = "机构";
			}
		}else{
			TYPE_DATA_NAME = "正在使用";
		}
		return TYPE_DATA_NAME;
	}
	public void setTYPE_DATA_NAME(String type_data_name) {
		TYPE_DATA_NAME = type_data_name;
	}
	public String getTOTAL_NAME() {
		return TOTAL_NAME;
	}
	public void setTOTAL_NAME(String total_name) {
		TOTAL_NAME = total_name;
	}
   
    
}
