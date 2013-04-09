package com.cpst.emsadrdb.entity.address;

public class BldgrsdnsBean {
	private String RSDNBLDG_IDTMP;
	private String RSDNBLDG_ID;
    private String RSDNBLDG_FLAG;
    private String RSDNBLDG_NAME;
    private String RSDNBLDG_ABBR;
    private String RSDNBLDG_LEV_CD;
    private String BLDG_CHAR_CD;
    private String BLDG_FORM_CD;
    private String BLDG_TYP_CD;
    private String BLDG_FLOORS;
    private String POST_CD;
    private String DIST_CD;
    private String DORPLT_ID;
    private String STRT_ID;
    private String HOUSEHOLD;
    private String POSTMAIL_BOXES;
    private String MAIL_BOXES;
    private String LETTERS_CANIN;
    private String NONAME_LETT_CANIN;
    private String NOADDRESS_LETT_CANIN;
    private String POST_OFFICE;
    private String ROUTE_ID;
    private String SUBWAY;
    private String ENVI_DESC;
    private String CAN_MAIL;
    private String RESIDE_DATE;
    private String PARENT_RSDN_ID;
    private String STAT_CD;
    private String EMP_NBR;
    private String UPD_DATE;
    private String DATA_DATE;
    private String DATA_FLAG;
    private String TOTAL_BLDG_NAME;
    private String TOTAL_DISTRICT_NAME;
    private String TOTAL_STREET_NAME;
    private String DT_PK_CODE;
    private String DM_PK_CODE;
    private String PG_PK_CODE;
    private String START_NUM;
    private String PREFIX;
    private String END_NUM;
    private String SUFFIX;
    private String NUM_FLAG;
    private String NUM_FLAG_NAME;
    private String DT_PK_NAME;
    private String DM_PK_NAME;
    private String PG_PK_NAME;
    private String LOG_ID;
    private String DATA_FLAG_NAME;
    private String DATA_USER;
    private String DATA_USER_NAME;
    private String RSDNBLDG_FLAG_NAME;
    
    private String NOTE;
    private String ADULTNOTE;
    
	public String getRSDNBLDG_IDTMP() {
		return RSDNBLDG_IDTMP;
	}
	public void setRSDNBLDG_IDTMP(String rsdnbldg_idtmp) {
		RSDNBLDG_IDTMP = rsdnbldg_idtmp;
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
    
	public String getRSDNBLDG_FLAG_NAME() {
		return RSDNBLDG_FLAG_NAME;
	}
	public void setRSDNBLDG_FLAG_NAME(String rsdnbldg_flag_name) {
		RSDNBLDG_FLAG_NAME = rsdnbldg_flag_name;
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
	public String getDT_PK_CODE() {
		return DT_PK_CODE;
	}
	public String getNUM_FLAG_NAME() {
		if(NUM_FLAG_NAME!=null){
			if(NUM_FLAG_NAME.equals("5")){
				NUM_FLAG_NAME = "单";
			}else if(NUM_FLAG_NAME.equals("6")){
				NUM_FLAG_NAME = "双";
			}else{
				NUM_FLAG_NAME = "";
			}
		}else{
			NUM_FLAG_NAME = "";
		}
		return NUM_FLAG_NAME;
	}
	public void setNUM_FLAG_NAME(String num_flag_name) {
		NUM_FLAG_NAME = num_flag_name;
	}
	public void setDT_PK_CODE(String dt_pk_code) {
		DT_PK_CODE = dt_pk_code;
	}
	public String getDM_PK_CODE() {
		return DM_PK_CODE;
	}
	public void setDM_PK_CODE(String dm_pk_code) {
		DM_PK_CODE = dm_pk_code;
	}
	public String getPG_PK_CODE() {
		return PG_PK_CODE;
	}
	public void setPG_PK_CODE(String pg_pk_code) {
		PG_PK_CODE = pg_pk_code;
	}
	public String getSTART_NUM() {
		return START_NUM;
	}
	public void setSTART_NUM(String start_num) {
		START_NUM = start_num;
	}
	public String getPREFIX() {
		return PREFIX;
	}
	public void setPREFIX(String prefix) {
		PREFIX = prefix;
	}
	public String getEND_NUM() {
		return END_NUM;
	}
	public void setEND_NUM(String end_num) {
		END_NUM = end_num;
	}
	public String getSUFFIX() {
		return SUFFIX;
	}
	public void setSUFFIX(String suffix) {
		SUFFIX = suffix;
	}
	public String getNUM_FLAG() {
		return NUM_FLAG;
	}
	public void setNUM_FLAG(String num_flag) {
		NUM_FLAG = num_flag;
	}
	public String getDT_PK_NAME() {
		return DT_PK_NAME;
	}
	public void setDT_PK_NAME(String dt_pk_name) {
		DT_PK_NAME = dt_pk_name;
	}
	public String getDM_PK_NAME() {
		return DM_PK_NAME;
	}
	public void setDM_PK_NAME(String dm_pk_name) {
		DM_PK_NAME = dm_pk_name;
	}
	public String getPG_PK_NAME() {
		return PG_PK_NAME;
	}
	public void setPG_PK_NAME(String pg_pk_name) {
		PG_PK_NAME = pg_pk_name;
	}
	public String getTOTAL_BLDG_NAME() {
		if(TOTAL_BLDG_NAME!=null){
			String str[]=TOTAL_BLDG_NAME.split("@@");
			TOTAL_BLDG_NAME = "";
			TOTAL_BLDG_NAME = TOTAL_BLDG_NAME + str[0].trim();
			TOTAL_BLDG_NAME = TOTAL_BLDG_NAME + str[1].trim();
			if(str[2].trim().length() >0 && !str[1].trim().equals(str[2].trim())){
				TOTAL_BLDG_NAME = TOTAL_BLDG_NAME + "至" + str[2].trim();
			}
			TOTAL_BLDG_NAME = TOTAL_BLDG_NAME + str[3].trim();
			if(str[4].trim().equals("5")){
				TOTAL_BLDG_NAME = TOTAL_BLDG_NAME + "(单)";
			}else if(str[4].trim().equals("6")){
				TOTAL_BLDG_NAME = TOTAL_BLDG_NAME + "(双)";
			}
		}else{
			TOTAL_BLDG_NAME = "无门牌";
		}
		return TOTAL_BLDG_NAME;
	}
	public void setTOTAL_BLDG_NAME(String total_bldg_name) {
		TOTAL_BLDG_NAME = total_bldg_name;
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
	public String getRSDNBLDG_ID() {
		return RSDNBLDG_ID;
	}
	public void setRSDNBLDG_ID(String rsdnbldg_id) {
		RSDNBLDG_ID = rsdnbldg_id;
	}
	public String getRSDNBLDG_FLAG() {
		return RSDNBLDG_FLAG;
	}
	public void setRSDNBLDG_FLAG(String rsdnbldg_flag) {
		RSDNBLDG_FLAG = rsdnbldg_flag;
	}
	public String getRSDNBLDG_NAME() {
		return RSDNBLDG_NAME;
	}
	public void setRSDNBLDG_NAME(String rsdnbldg_name) {
		RSDNBLDG_NAME = rsdnbldg_name;
	}
	public String getRSDNBLDG_ABBR() {
		return RSDNBLDG_ABBR;
	}
	public void setRSDNBLDG_ABBR(String rsdnbldg_abbr) {
		RSDNBLDG_ABBR = rsdnbldg_abbr;
	}
	public String getRSDNBLDG_LEV_CD() {
		return RSDNBLDG_LEV_CD;
	}
	public void setRSDNBLDG_LEV_CD(String rsdnbldg_lev_cd) {
		RSDNBLDG_LEV_CD = rsdnbldg_lev_cd;
	}
	public String getBLDG_CHAR_CD() {
		return BLDG_CHAR_CD;
	}
	public void setBLDG_CHAR_CD(String bldg_char_cd) {
		BLDG_CHAR_CD = bldg_char_cd;
	}
	public String getBLDG_FORM_CD() {
		return BLDG_FORM_CD;
	}
	public void setBLDG_FORM_CD(String bldg_form_cd) {
		BLDG_FORM_CD = bldg_form_cd;
	}
	public String getBLDG_TYP_CD() {
		return BLDG_TYP_CD;
	}
	public void setBLDG_TYP_CD(String bldg_typ_cd) {
		BLDG_TYP_CD = bldg_typ_cd;
	}
	public String getBLDG_FLOORS() {
		return BLDG_FLOORS;
	}
	public void setBLDG_FLOORS(String bldg_floors) {
		BLDG_FLOORS = bldg_floors;
	}
	public String getPOST_CD() {
		return POST_CD;
	}
	public void setPOST_CD(String post_cd) {
		POST_CD = post_cd;
	}
	public String getDIST_CD() {
		return DIST_CD;
	}
	public void setDIST_CD(String dist_cd) {
		DIST_CD = dist_cd;
	}
	public String getDORPLT_ID() {
		return DORPLT_ID;
	}
	public void setDORPLT_ID(String dorplt_id) {
		DORPLT_ID = dorplt_id;
	}
	public String getSTRT_ID() {
		return STRT_ID;
	}
	public void setSTRT_ID(String strt_id) {
		STRT_ID = strt_id;
	}
	public String getHOUSEHOLD() {
		return HOUSEHOLD;
	}
	public void setHOUSEHOLD(String household) {
		HOUSEHOLD = household;
	}
	public String getPOSTMAIL_BOXES() {
		return POSTMAIL_BOXES;
	}
	public void setPOSTMAIL_BOXES(String postmail_boxes) {
		POSTMAIL_BOXES = postmail_boxes;
	}
	public String getMAIL_BOXES() {
		return MAIL_BOXES;
	}
	public void setMAIL_BOXES(String mail_boxes) {
		MAIL_BOXES = mail_boxes;
	}
	public String getLETTERS_CANIN() {
		return LETTERS_CANIN;
	}
	public void setLETTERS_CANIN(String letters_canin) {
		LETTERS_CANIN = letters_canin;
	}
	public String getNONAME_LETT_CANIN() {
		return NONAME_LETT_CANIN;
	}
	public void setNONAME_LETT_CANIN(String noname_lett_canin) {
		NONAME_LETT_CANIN = noname_lett_canin;
	}
	public String getNOADDRESS_LETT_CANIN() {
		return NOADDRESS_LETT_CANIN;
	}
	public void setNOADDRESS_LETT_CANIN(String noaddress_lett_canin) {
		NOADDRESS_LETT_CANIN = noaddress_lett_canin;
	}
	public String getPOST_OFFICE() {
		return POST_OFFICE;
	}
	public void setPOST_OFFICE(String post_office) {
		POST_OFFICE = post_office;
	}
	public String getROUTE_ID() {
		return ROUTE_ID;
	}
	public void setROUTE_ID(String route_id) {
		ROUTE_ID = route_id;
	}
	public String getSUBWAY() {
		return SUBWAY;
	}
	public void setSUBWAY(String subway) {
		SUBWAY = subway;
	}
	public String getENVI_DESC() {
		return ENVI_DESC;
	}
	public void setENVI_DESC(String envi_desc) {
		ENVI_DESC = envi_desc;
	}
	public String getCAN_MAIL() {
		return CAN_MAIL;
	}
	public void setCAN_MAIL(String can_mail) {
		CAN_MAIL = can_mail;
	}
	public String getRESIDE_DATE() {
		return RESIDE_DATE;
	}
	public void setRESIDE_DATE(String reside_date) {
		RESIDE_DATE = reside_date;
	}
	public String getPARENT_RSDN_ID() {
		return PARENT_RSDN_ID;
	}
	public void setPARENT_RSDN_ID(String parent_rsdn_id) {
		PARENT_RSDN_ID = parent_rsdn_id;
	}
	public String getSTAT_CD() {
		return STAT_CD;
	}
	public void setSTAT_CD(String stat_cd) {
		STAT_CD = stat_cd;
	}
	public String getEMP_NBR() {
		return EMP_NBR;
	}
	public void setEMP_NBR(String emp_nbr) {
		EMP_NBR = emp_nbr;
	}
	public String getUPD_DATE() {
		return UPD_DATE;
	}
	public void setUPD_DATE(String upd_date) {
		UPD_DATE = upd_date;
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
    
    
}
