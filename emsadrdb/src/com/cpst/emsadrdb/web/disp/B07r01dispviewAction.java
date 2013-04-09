package com.cpst.emsadrdb.web.disp;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.cpst.core.web.struts2.BaseActionSupport;
import com.cpst.emsadrdb.dao.disp.DispOptionDao;
import com.cpst.emsadrdb.entity.disp.DispBean;
import com.cpst.emsadrdb.entity.disp.DistrictBean;
import com.cpst.emsadrdb.service.address.AddrCommon;
import com.cpst.emsadrdb.service.disp.DispCommon;



public class B07r01dispviewAction  extends BaseActionSupport{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6713451496948939455L;

	@Autowired
	private DispOptionDao dispOptionDao;
	
	private List<DispBean> dispp;
			
	public List<DispBean> getDispp() {
		return dispp;
	}

	public void setDispp(List<DispBean> dispp) {
		this.dispp = dispp;
	}
	private List<DistrictBean> provinces;
	
	
	private String DISP_OFFICE_CODE;
	private String DISP_OFFICE_NAME;
	
	private String DISP_OFFICE_ABBR;
	private String TRANS_NAME;
	private String DISTRICT_CODE;
	private String CITY_NAME;
	
	
	public String getDISTRICT_CODE() {
		return DISTRICT_CODE;
	}

	public void setDISTRICT_CODE(String district_code) {
		DISTRICT_CODE = district_code;
	}

	public String getCITY_NAME() {
		return CITY_NAME;
	}

	public void setCITY_NAME(String city_name) {
		CITY_NAME = city_name;
	}

	public String getTRANS_NAME() {
		return TRANS_NAME;
	}

	public void setTRANS_NAME(String trans_name) {
		TRANS_NAME = trans_name;
	}

	public String getDISP_OFFICE_ABBR() {
		return DISP_OFFICE_ABBR;
	}

	public void setDISP_OFFICE_ABBR(String disp_office_abbr) {
		DISP_OFFICE_ABBR = disp_office_abbr;
	}
	public String getDISP_OFFICE_CODE() {
		return DISP_OFFICE_CODE;
	}

	public void setDISP_OFFICE_CODE(String disp_office_code) {
		DISP_OFFICE_CODE = disp_office_code;
	}

	public String getDISP_OFFICE_NAME() {
		return DISP_OFFICE_NAME;
	}

	public void setDISP_OFFICE_NAME(String disp_office_name) {
		DISP_OFFICE_NAME = disp_office_name;
	}

	public List<DistrictBean> getProvinces() {
		return provinces;
	}

	public void setProvinces(List<DistrictBean> provinces) {
		this.provinces = provinces;
	}

	public String execute() throws Exception {
		
		if(!userflag()){return null;}
		
		System.out.println("execute");
		return null;
	}
	
	public Boolean userflag(){
		if(getSessionUser()!=null && getSessionUser().getUsLoginId() != null && getSessionUser().getUsLoginId().length() > 0){
			return true;
		}
		else{
			//saveMessage = "用户信息丢失,请重新登陆!";
			return false;
		}
	}
	
	public String viewdisps() throws Exception {
		
		if(!userflag()){return null;}
		
		provinces=dispOptionDao.getProvinces(DispCommon.getRulUsProvinceOffice(getSessionUser()));
		return "viewdisps";
	}
	
	public String viewdistrict() throws Exception {
		
		if(!userflag()){return null;}
		
		provinces=dispOptionDao.getProvinces(DispCommon.getRulUsProvinceOffice(getSessionUser()));
		return "viewdistrict";
	}
	
	public String viewtddisp() throws Exception {
		
		if(!userflag()){return null;}
		
		return "viewtddisp";
	}
	
	public String viewtrans() throws Exception {
		
		if(!userflag()){return null;}
		
		return "viewtrans";
	}
	public String viewwhtrans() throws Exception {
		
		if(!userflag()){return null;}
		
		dispp=dispOptionDao.getDispp(DispCommon.getRulUsProvinceOffice(getSessionUser()));
		return "viewwhtrans";
	}
	
	public String viewtjpqb() throws Exception {
		
		if(!userflag()){return null;}
		
		return "viewtjpqb";
	}
	
	public String viewexporttjpqb() throws Exception {
		
		if(!userflag()){return null;}
		
		return "viewexporttjpqb";
	}
	
	public String viewtjcqb() throws Exception {
		
		if(!userflag()){return null;}
		
		provinces=dispOptionDao.getProvinces(DispCommon.getRulUsProvinceOffice(getSessionUser()));
		return "viewtjcqb";
	}

	public String viewtjtddisp() throws Exception {
		
		if(!userflag()){return null;}
		
		return "viewtjtddisp";
	}
	
	public String viewtjdtqb() throws Exception {
		
		if(!userflag()){return null;}
		
		provinces=dispOptionDao.getProvinces(DispCommon.getRulUsProvinceOffice(getSessionUser()));
		return "viewtjdtqb";
	}
	
	public String viewtjtdqwhtj() throws Exception {
		
		if(!userflag()){return null;}
		
		provinces=dispOptionDao.getProvinces(DispCommon.getRulUsProvinceOffice(getSessionUser()));
		return "viewtjtdqwhtj";
	}
	
	public String viewtjtdbwhtj() throws Exception {
		
		if(!userflag()){return null;}
		
		provinces=dispOptionDao.getProvinces(DispCommon.getRulUsProvinceOffice(getSessionUser()));
		return "viewtjtdbwhtj";
	}
	
	public String viewtjdtpkisp() throws Exception {
		
		if(!userflag()){return null;}
		
		return "viewtjdtpkisp";
	}
	
	public String viewtjtdqqb() throws Exception {
		
		if(!userflag()){return null;}
		
		provinces=dispOptionDao.getProvinces(DispCommon.getRulUsProvinceOffice(getSessionUser()));
		return "viewtjtdqqb";
	}
	
	public String viewtjprb() throws Exception {
		
		if(!userflag()){return null;}
		
		return "viewtjprb";
	}
	
	public String viewtjcrb() throws Exception {
		
		if(!userflag()){return null;}
		
		provinces=dispOptionDao.getProvinces(DispCommon.getRulUsProvinceOffice(getSessionUser()));
		return "viewtjcrb";
	}
	
	public String viewtjpwhrb() throws Exception {
		
		if(!userflag()){return null;}
		
		return "viewtjpwhrb";
	}
	
	public String viewtjpwhrbdn() throws Exception {
		
		if(!userflag()){return null;}
		
		return "viewtjpwhrbdn";
	}
	
	public String viewtjpwhrbopdn() throws Exception {
		
		if(!userflag()){return null;}
		
		return "viewtjpwhrbopdn";
	}
	
	public String viewtjdt() throws Exception {
		
		if(!userflag()){return null;}
		
		provinces=dispOptionDao.getProvinces(AddrCommon.getRulUsProvinceOffice(getSessionUser()));
		return "viewtjdt";
	}
	
	public String viewitemnoxqcx() throws Exception {
		
		if(!userflag()){return null;}
		
		return "viewitemnoxqcx";
	}
	
}
