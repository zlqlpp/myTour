package com.cpst.emsadrdb.entity.address;

public class OrganizationBean {
	
	private String ORG_ID;
	private String ORG_IDTMP;
    private String EMP_NBR;
    private String ORG_NAME;
    private String ORG_ABBR_NAME;
    private String ORG_ABBR;
    private String DORPLT_ID;
    private String STRT_ID;
    private String INDTRY_TYP_CD;
    private String ECON_TYP_CD;
    private String BUSN_RANGE;
    private String TEL_AREA;
    private String TEL_NBR;
    private String FAX_AREA;
    private String FAX_NBR;
    private String EMAIL_ADDR;
    private String URL;
    private String SALE_INCOM;
    private String REG_FUND;
    private String DATA_SRC_CD;
    private String STAT_CD;
    private String REG_FUND_CRNCY_UNIT_CD;
    private String SALE_INCOM_CRNCY_UNIT_CD;
    private String ENG_NAME;
    private String EMP_QTY;
    private String ENTP_CLS_CD;
    private String ENTP_SCAL_CD;
    private String CNTRY_CD;
    private String DIST_CD;
    private String KEY_IND;
    private String SPEC_IND;
    private String SPEC_POST_CD;
    private String STAPLE_IND;
    private String CORP_NAME;
    private String PARENET_ORG_ID;
    private String UPD_DATE;
    private String TOWN_NAME;
    private String ADDR_OFFICE;
    private String ADDR_RSDN;
    private String ASSET;
    private String REG_TYPE;
    private String POST_CD;
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
    private String DT_PK_NAME;
    private String DM_PK_NAME;
    private String PG_PK_NAME;
    private String NUM_FLAG_NAME;
    private String LOG_ID;
    private String DATA_FLAG_NAME;
    private String DATA_USER;
    private String DATA_USER_NAME;
    private String RSDNBLDG_ID;
    private String RSDNBLDG_NAME;
    
    private String NOTE;
    private String ADULTNOTE;
    
	public String getORG_IDTMP() {
		return ORG_IDTMP;
	}
	public void setORG_IDTMP(String org_idtmp) {
		ORG_IDTMP = org_idtmp;
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
	
	public String getRSDNBLDG_ID() {
		return RSDNBLDG_ID;
	}
	public void setRSDNBLDG_ID(String rsdnbldg_id) {
		RSDNBLDG_ID = rsdnbldg_id;
	}
	public String getRSDNBLDG_NAME() {
		return RSDNBLDG_NAME;
	}
	public void setRSDNBLDG_NAME(String rsdnbldg_name) {
		RSDNBLDG_NAME = rsdnbldg_name;
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
	public String getDT_PK_CODE() {
		return DT_PK_CODE;
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
	public String getORG_ID() {
		return ORG_ID;
	}
	public void setORG_ID(String org_id) {
		ORG_ID = org_id;
	}
	public String getEMP_NBR() {
		return EMP_NBR;
	}
	public void setEMP_NBR(String emp_nbr) {
		EMP_NBR = emp_nbr;
	}
	public String getORG_NAME() {
		return ORG_NAME;
	}
	public void setORG_NAME(String org_name) {
		ORG_NAME = org_name;
	}
	public String getORG_ABBR_NAME() {
		return ORG_ABBR_NAME;
	}
	public void setORG_ABBR_NAME(String org_abbr_name) {
		ORG_ABBR_NAME = org_abbr_name;
	}
	public String getORG_ABBR() {
		return ORG_ABBR;
	}
	public void setORG_ABBR(String org_abbr) {
		ORG_ABBR = org_abbr;
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
	public String getINDTRY_TYP_CD() {
		return INDTRY_TYP_CD;
	}
	public void setINDTRY_TYP_CD(String indtry_typ_cd) {
		INDTRY_TYP_CD = indtry_typ_cd;
	}
	public String getECON_TYP_CD() {
		return ECON_TYP_CD;
	}
	public void setECON_TYP_CD(String econ_typ_cd) {
		ECON_TYP_CD = econ_typ_cd;
	}
	public String getBUSN_RANGE() {
		return BUSN_RANGE;
	}
	public void setBUSN_RANGE(String busn_range) {
		BUSN_RANGE = busn_range;
	}
	public String getTEL_AREA() {
		return TEL_AREA;
	}
	public void setTEL_AREA(String tel_area) {
		TEL_AREA = tel_area;
	}
	public String getTEL_NBR() {
		return TEL_NBR;
	}
	public void setTEL_NBR(String tel_nbr) {
		TEL_NBR = tel_nbr;
	}
	public String getFAX_AREA() {
		return FAX_AREA;
	}
	public void setFAX_AREA(String fax_area) {
		FAX_AREA = fax_area;
	}
	public String getFAX_NBR() {
		return FAX_NBR;
	}
	public void setFAX_NBR(String fax_nbr) {
		FAX_NBR = fax_nbr;
	}
	public String getEMAIL_ADDR() {
		return EMAIL_ADDR;
	}
	public void setEMAIL_ADDR(String email_addr) {
		EMAIL_ADDR = email_addr;
	}
	public String getURL() {
		return URL;
	}
	public void setURL(String url) {
		URL = url;
	}
	public String getSALE_INCOM() {
		return SALE_INCOM;
	}
	public void setSALE_INCOM(String sale_incom) {
		SALE_INCOM = sale_incom;
	}
	public String getREG_FUND() {
		return REG_FUND;
	}
	public void setREG_FUND(String reg_fund) {
		REG_FUND = reg_fund;
	}
	public String getDATA_SRC_CD() {
		return DATA_SRC_CD;
	}
	public void setDATA_SRC_CD(String data_src_cd) {
		DATA_SRC_CD = data_src_cd;
	}
	public String getSTAT_CD() {
		return STAT_CD;
	}
	public void setSTAT_CD(String stat_cd) {
		STAT_CD = stat_cd;
	}
	public String getREG_FUND_CRNCY_UNIT_CD() {
		return REG_FUND_CRNCY_UNIT_CD;
	}
	public void setREG_FUND_CRNCY_UNIT_CD(String reg_fund_crncy_unit_cd) {
		REG_FUND_CRNCY_UNIT_CD = reg_fund_crncy_unit_cd;
	}
	public String getSALE_INCOM_CRNCY_UNIT_CD() {
		return SALE_INCOM_CRNCY_UNIT_CD;
	}
	public void setSALE_INCOM_CRNCY_UNIT_CD(String sale_incom_crncy_unit_cd) {
		SALE_INCOM_CRNCY_UNIT_CD = sale_incom_crncy_unit_cd;
	}
	public String getENG_NAME() {
		return ENG_NAME;
	}
	public void setENG_NAME(String eng_name) {
		ENG_NAME = eng_name;
	}
	public String getEMP_QTY() {
		return EMP_QTY;
	}
	public void setEMP_QTY(String emp_qty) {
		EMP_QTY = emp_qty;
	}
	public String getENTP_CLS_CD() {
		return ENTP_CLS_CD;
	}
	public void setENTP_CLS_CD(String entp_cls_cd) {
		ENTP_CLS_CD = entp_cls_cd;
	}
	public String getENTP_SCAL_CD() {
		return ENTP_SCAL_CD;
	}
	public void setENTP_SCAL_CD(String entp_scal_cd) {
		ENTP_SCAL_CD = entp_scal_cd;
	}
	public String getCNTRY_CD() {
		return CNTRY_CD;
	}
	public void setCNTRY_CD(String cntry_cd) {
		CNTRY_CD = cntry_cd;
	}
	public String getDIST_CD() {
		return DIST_CD;
	}
	public void setDIST_CD(String dist_cd) {
		DIST_CD = dist_cd;
	}
	public String getKEY_IND() {
		return KEY_IND;
	}
	public void setKEY_IND(String key_ind) {
		KEY_IND = key_ind;
	}
	public String getSPEC_IND() {
		return SPEC_IND;
	}
	public void setSPEC_IND(String spec_ind) {
		SPEC_IND = spec_ind;
	}
	public String getSPEC_POST_CD() {
		return SPEC_POST_CD;
	}
	public void setSPEC_POST_CD(String spec_post_cd) {
		SPEC_POST_CD = spec_post_cd;
	}
	public String getSTAPLE_IND() {
		return STAPLE_IND;
	}
	public void setSTAPLE_IND(String staple_ind) {
		STAPLE_IND = staple_ind;
	}
	public String getCORP_NAME() {
		return CORP_NAME;
	}
	public void setCORP_NAME(String corp_name) {
		CORP_NAME = corp_name;
	}
	public String getPARENET_ORG_ID() {
		return PARENET_ORG_ID;
	}
	public void setPARENET_ORG_ID(String parenet_org_id) {
		PARENET_ORG_ID = parenet_org_id;
	}
	public String getUPD_DATE() {
		return UPD_DATE;
	}
	public void setUPD_DATE(String upd_date) {
		UPD_DATE = upd_date;
	}
	public String getTOWN_NAME() {
		return TOWN_NAME;
	}
	public void setTOWN_NAME(String town_name) {
		TOWN_NAME = town_name;
	}
	public String getADDR_OFFICE() {
		return ADDR_OFFICE;
	}
	public void setADDR_OFFICE(String addr_office) {
		ADDR_OFFICE = addr_office;
	}
	public String getADDR_RSDN() {
		return ADDR_RSDN;
	}
	public void setADDR_RSDN(String addr_rsdn) {
		ADDR_RSDN = addr_rsdn;
	}
	public String getASSET() {
		return ASSET;
	}
	public void setASSET(String asset) {
		ASSET = asset;
	}
	public String getREG_TYPE() {
		return REG_TYPE;
	}
	public void setREG_TYPE(String reg_type) {
		REG_TYPE = reg_type;
	}
	public String getPOST_CD() {
		return POST_CD;
	}
	public void setPOST_CD(String post_cd) {
		POST_CD = post_cd;
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
