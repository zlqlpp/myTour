package com.cpst.emsadrdb.web.disp;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.cpst.core.web.struts2.BaseActionSupport;
import com.cpst.emsadrdb.dao.disp.DispConfigDao;
import com.cpst.emsadrdb.dao.disp.DispQueryDao;
import com.cpst.emsadrdb.entity.disp.DispBean;
import com.cpst.emsadrdb.entity.disp.DistrictBean;
import com.cpst.emsadrdb.entity.disp.TransBean;
import com.cpst.emsadrdb.service.disp.DispCommon;

@SuppressWarnings({ "serial" })
@ParentPackage("jsoncrud")
@Results( { @Result(type = "json", name = "json") })
public class B07cud02dispconfigAction extends BaseActionSupport{
	private String saveMessage;
	
	public String getSaveMessage() {
		return saveMessage;
	}


	public void setSaveMessage(String saveMessage) {
		this.saveMessage = saveMessage;
	}

	@Autowired
	private DispConfigDao dispConfigDao;
	@Autowired
	private DispQueryDao dispQueryDao;
	
	private TransBean transBean;
	
	private DistrictBean districtBean;
	
	private DispBean dispBean;
	
	private String TOTAL_ALL_VALUE;
	private String DT_PK_CODE;
	
	private String PROVINCE_NAME;
	private String PROVINCE_CODE;
	private String DISP_OFFICE_CODE;
	private String DISP_OFFICE_NAME;
	private String TRANS_CODE;
	private String TRANS_NAME;
	private String DEGREE;
	private String FLAG;
	private String DISP_OFFICE_ABBR;
	private String DISP_OFFICE_CODET;
	private String WHDIS;
	private String COUNTY_NAME;
	private String CITY_FLAG;
	private String DISTRICT_CODE;
	
	
	
	public String getDISTRICT_CODE() {
		return DISTRICT_CODE;
	}


	public void setDISTRICT_CODE(String district_code) {
		DISTRICT_CODE = DispCommon.sql_inj(district_code);
	}


	public DistrictBean getDistrictBean() {
		return districtBean;
	}


	public void setDistrictBean(DistrictBean districtBean) {
		this.districtBean = districtBean;
	}


	public String getCITY_FLAG() {
		return CITY_FLAG;
	}


	public void setCITY_FLAG(String city_flag) {
		CITY_FLAG = DispCommon.sql_inj(city_flag);
	}


	public String getCOUNTY_NAME() {
		return COUNTY_NAME;
	}


	public void setCOUNTY_NAME(String county_name) {
		COUNTY_NAME = DispCommon.sql_inj(county_name);
	}


	public String getDT_PK_CODE() {
		return DT_PK_CODE;
	}


	public void setDT_PK_CODE(String dt_pk_code) {
		DT_PK_CODE = DispCommon.sql_inj(dt_pk_code);
	}


	public String getTOTAL_ALL_VALUE() {
		return TOTAL_ALL_VALUE;
	}


	public void setTOTAL_ALL_VALUE(String total_all_value) {
		TOTAL_ALL_VALUE = DispCommon.sql_inj(total_all_value);
	}


	public String getWHDIS() {
		return WHDIS;
	}


	public void setWHDIS(String whdis) {
		WHDIS = DispCommon.sql_inj(whdis);
	}


	public String getDISP_OFFICE_CODET() {
		return DISP_OFFICE_CODET;
	}


	public void setDISP_OFFICE_CODET(String disp_office_codet) {
		DISP_OFFICE_CODET = DispCommon.sql_inj(disp_office_codet);
	}


	public String getDISP_OFFICE_ABBR() {
		return DISP_OFFICE_ABBR;
	}


	public void setDISP_OFFICE_ABBR(String disp_office_abbr) {
		DISP_OFFICE_ABBR = DispCommon.sql_inj(disp_office_abbr);
	}


	public DispBean getDispBean() {
		return dispBean;
	}


	public void setDispBean(DispBean dispBean) {
		this.dispBean = dispBean;
	}


	public String getDISP_OFFICE_NAME() {
		return DISP_OFFICE_NAME;
	}


	public void setDISP_OFFICE_NAME(String disp_office_name) {
		DISP_OFFICE_NAME = DispCommon.sql_inj(disp_office_name);
	}


	public String getFLAG() {
		return FLAG;
	}


	public void setFLAG(String flag) {
		FLAG = DispCommon.sql_inj(flag);
	}


	public String getDEGREE() {
		return DEGREE;
	}


	public void setDEGREE(String degree) {
		DEGREE = DispCommon.sql_inj(degree);
	}


	public TransBean getTransBean() {
		return transBean;
	}


	public void setTransBean(TransBean transBean) {
		this.transBean = transBean;
	}


	public String getPROVINCE_NAME() {
		return PROVINCE_NAME;
	}


	public void setPROVINCE_NAME(String province_name) {
		PROVINCE_NAME = DispCommon.sql_inj(province_name);
	}


	public String getPROVINCE_CODE() {
		return PROVINCE_CODE;
	}


	public void setPROVINCE_CODE(String province_code) {
		PROVINCE_CODE = DispCommon.sql_inj(province_code);
	}


	public String getDISP_OFFICE_CODE() {
		return DISP_OFFICE_CODE;
	}


	public void setDISP_OFFICE_CODE(String disp_office_code) {
		DISP_OFFICE_CODE = DispCommon.sql_inj(disp_office_code);
	}


	public String getTRANS_CODE() {
		return TRANS_CODE;
	}


	public void setTRANS_CODE(String trans_code) {
		TRANS_CODE = DispCommon.sql_inj(trans_code);
	}


	public String getTRANS_NAME() {
		return TRANS_NAME;
	}


	public void setTRANS_NAME(String trans_name) {
		TRANS_NAME = DispCommon.sql_inj(trans_name);
	}
	
	public void configDispBean(){
		dispBean=new DispBean();
		if(PROVINCE_NAME!=null){
			dispBean.setPROVINCE_NAME(PROVINCE_NAME);
		}
		if(PROVINCE_CODE!=null){
			dispBean.setPROVINCE_CODE(PROVINCE_CODE);
		}
		if(DISP_OFFICE_CODE!=null){
			dispBean.setDISP_OFFICE_CODE(DISP_OFFICE_CODE);
		}
		if(DISP_OFFICE_CODET!=null){
			dispBean.setDISP_OFFICE_CODET(DISP_OFFICE_CODET);
		}
		if(DISP_OFFICE_NAME!=null){
			dispBean.setDISP_OFFICE_NAME(DISP_OFFICE_NAME);
		}
		if(DISP_OFFICE_ABBR!=null){
			dispBean.setDISP_OFFICE_ABBR(DISP_OFFICE_ABBR);
		}
		if(DEGREE!=null){
			dispBean.setDEGREE(DEGREE);
		}
		if(FLAG!=null){
			dispBean.setFLAG(FLAG);
		}
		if(WHDIS!=null){
			dispBean.setWHDIS(WHDIS);
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
	
	public String adddisp() throws Exception {		
		
		if(!userflag()){return "json";}
		
		String sql="select count(*) from CP_BASE_DISP_OFFICE where DISP_OFFICE_CODE = " +  DISP_OFFICE_CODE;
		if(dispQueryDao.getQueryCount(sql)>0){
			saveMessage="存在同样的经转局名,不能添加此数据!";
			return "json";
		}
		configDispBean();
		@SuppressWarnings("unused")
		boolean m=dispConfigDao.addDisp(dispBean,getSessionUser());
		
		saveMessage="新增成功";
		return "json";
	}
	
	public String alertdisp() throws Exception {		
		
		if(!userflag()){return "json";}
		
		String sql="select count(*) from CP_BASE_TRANS_OFFICE_ALIAS where TRANS_CODE = '" +  DISP_OFFICE_CODE + "'";
		if(dispConfigDao.getQueryCount(sql)>0){
			saveMessage="经转局名存在于经转范围中,是否添加此数据!";
			return "json";
		}
		saveMessage="true";
		return "json";
	}
		
	public String updisp() throws Exception {
		
		if(!userflag()){return "json";}
		
		
		configDispBean();
		@SuppressWarnings("unused")
		boolean m=dispConfigDao.upDisp(dispBean,getSessionUser());

		saveMessage="修改成功";
		return "json";
	}
	
	public String deldisp() throws Exception {
		
		if(!userflag()){return "json";}
		
		configDispBean();
		@SuppressWarnings("unused")
		boolean m=dispConfigDao.delDisp(dispBean,getSessionUser());

		saveMessage="删除成功";
		return "json";
	}
	
	public String zhdisp() throws Exception {
		
		if(!userflag()){return "json";}
		
		
		configDispBean();		
		@SuppressWarnings("unused")
		boolean m=dispConfigDao.zhDisp(dispBean,getSessionUser());

		saveMessage="调整成功";
		return "json";
	}


	public void configTransBean(){
		transBean=new TransBean();
		if(PROVINCE_NAME!=null){
			transBean.setPROVINCE_NAME(PROVINCE_NAME);
		}
		if(PROVINCE_CODE!=null){
			transBean.setPROVINCE_CODE(PROVINCE_CODE);
		}
		if(DISP_OFFICE_CODE!=null){
			transBean.setDISP_OFFICE_CODE(DISP_OFFICE_CODE);
		}
		if(TRANS_CODE!=null){
			transBean.setTRANS_CODE(TRANS_CODE);
		}
		if(TRANS_NAME!=null){
			transBean.setTRANS_NAME(TRANS_NAME);
		}
		if(DEGREE!=null){
			transBean.setDEGREE(DEGREE);
		}
		if(FLAG!=null){
			transBean.setFLAG(FLAG);
		}
		if(WHDIS!=null){
			dispBean.setWHDIS(WHDIS);
		}
	}
	
	
	public String addtrans() throws Exception {	
		
		if(!userflag()){return "json";}
			
		String sql="select count(*) from CP_BASE_DISP_OFFICE where DISP_OFFICE_CODE = " +  TRANS_CODE;
		if(dispQueryDao.getQueryCount(sql)>0){
			saveMessage="添加经转范围存在经转局中,不能添加此数据!";
			return "json";
		}
		
		sql="select count(*) from CP_BASE_TRANS_OFFICE_ALIAS where TRANS_CODE = " +  TRANS_CODE;
		if(dispQueryDao.getQueryCount(sql)>0){
			saveMessage="存在同样的经转范围,不能添加此数据!";
			return "json";
		}
		
		sql="select count(*) from CP_BASE_TRANS_OFFICE where TRANS_CODE = " +  TRANS_CODE;
		if(dispQueryDao.getQueryCount(sql)>0){
			saveMessage="存在同样的经转范围,不能添加此数据!";
			return "json";
		}
		configTransBean();
		@SuppressWarnings("unused")
		boolean m=dispConfigDao.addTrans(transBean,getSessionUser());
		
		saveMessage="新增成功";
		return "json";
	}
	
/*	public String uptrans() throws Exception {
		
		configTransBean();
		@SuppressWarnings("unused")
		boolean m=dispConfigDao.upTrans(transBean,getSessionUser());

		saveMessage="修改成功";
		return "json";
	}*/
	
	public String dttrans() throws Exception {
		
		if(!userflag()){return "json";}
		
	
		String[] tTOTAL_ALL_VALUE = TOTAL_ALL_VALUE.split(",");
		for(int i=0;i<tTOTAL_ALL_VALUE.length;i++){
			transBean = new TransBean();
			transBean.setTRANS_CODE(tTOTAL_ALL_VALUE[i]);
			transBean.setDT_PK_CODE(DT_PK_CODE);
			boolean m=dispConfigDao.dtTrans(transBean,getSessionUser());
		}		
	
		saveMessage="修改成功";
		return "json";
	}
	
	public void configDistrictBean(){
		districtBean=new DistrictBean();
		if(DISTRICT_CODE!=null){
			districtBean.setDISTRICT_CODE(DISTRICT_CODE);
		}
		if(CITY_FLAG!=null){
			districtBean.setCITY_FLAG(CITY_FLAG);
		}
	}
	
	public String updistrict() throws Exception {
		
		if(!userflag()){return "json";}
		
		
		String[] tCOUNTY_NAME = COUNTY_NAME.split(",");
		for(int i=0;i<tCOUNTY_NAME.length;i++){
			districtBean = new DistrictBean();
			districtBean.setDISTRICT_CODE(tCOUNTY_NAME[i]);
			districtBean.setCITY_FLAG(CITY_FLAG);
			boolean m=dispConfigDao.updistrict(districtBean,getSessionUser());
		}		
	
		saveMessage="修改成功";
		return "json";
	}
	
	public String deltrans() throws Exception {
		
		if(!userflag()){return "json";}
		
		configTransBean();
		@SuppressWarnings("unused")
		boolean m=dispConfigDao.delTrans(transBean,getSessionUser());

		saveMessage="删除成功";
		return "json";
	}
	
	public String zhtrans() throws Exception {
		
		if(!userflag()){return "json";}
		
		configTransBean();
		@SuppressWarnings("unused")
		boolean m=dispConfigDao.zhtrans(transBean,getSessionUser());

		saveMessage="调整成功";
		return "json";
	}
	
	public Object getModel() {
		// TODO Auto-generated method stub
		return null;
	}
	

}
