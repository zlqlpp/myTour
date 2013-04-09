package com.cpst.emsadrdb.web.disp;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;

import com.cpst.core.web.struts2.BaseActionSupport;
import com.cpst.emsadrdb.dao.disp.DispOptionDao;
import com.cpst.emsadrdb.dao.disp.DispQueryDao;
import com.cpst.emsadrdb.entity.disp.DispBean;
import com.cpst.emsadrdb.entity.disp.DistrictBean;
import com.cpst.emsadrdb.entity.disp.TransBean;
import com.cpst.emsadrdb.service.disp.DispCommon;



public class B07cud01dispviewAction extends BaseActionSupport{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4984115778102208068L;
	
	private List<DistrictBean> provinces;
	private List<DispBean> dispp;

	private TransBean transBean;
	private DispBean dispBean;
	private String TRANS_CODE;
	private String DISP_OFFICE_CODE;
	private String DISP_OFFICE_NAME;
	private String DISP_OFFICE_ABBR;
	private String TRANS_NAME;
	
	
	
	public String getTRANS_NAME() {
		return TRANS_NAME;
	}

	public void setTRANS_NAME(String trans_name) {
		TRANS_NAME = trans_name;
	}

	public DispBean getDispBean() {
		return dispBean;
	}

	public void setDispBean(DispBean dispBean) {
		this.dispBean = dispBean;
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

	public TransBean getTransBean() {
		return transBean;
	}

	public void setTransBean(TransBean transBean) {
		this.transBean = transBean;
	}

	public String getTRANS_CODE() {
		return TRANS_CODE;
	}

	public void setTRANS_CODE(String trans_code) {
		TRANS_CODE = trans_code;
	}

	public List<DispBean> getDispp() {
		return dispp;
	}

	public void setDispp(List<DispBean> dispp) {
		this.dispp = dispp;
	}

	public List<DistrictBean> getProvinces() {
		return provinces;
	}

	public void setProvinces(List<DistrictBean> provinces) {
		this.provinces = provinces;
	}

	

	@Autowired
	private DispOptionDao dispOptionDao;
	
	@Autowired
	private DispQueryDao dispQueryDao;

	public String execute() throws Exception {
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
	
	public String adddisp() throws Exception {
		
		if(!userflag()){return null;}
		
		provinces=dispOptionDao.getProvinces(DispCommon.getRulUsProvinceOffice(getSessionUser()));
		return "adddisp";
	}
	
	public String updisp() throws Exception {
		
		if(!userflag()){return null;}
		
		provinces=dispOptionDao.getProvinces(null);
		dispBean=dispQueryDao.getBeanQueryDisp(DISP_OFFICE_CODE);
		return "adddisp";
	}
	
	public String zhdisp() throws Exception {
		
		if(!userflag()){return null;}
		
		dispp=dispOptionDao.getDispp(DispCommon.getRulUsProvinceOffice(getSessionUser()));
		return "zhdisp";
	}
		
	public String addtrans() throws Exception {
		
		if(!userflag()){return null;}
		
		provinces=dispOptionDao.getProvinces(DispCommon.getRulUsProvinceOffice(getSessionUser()));
		dispp=dispOptionDao.getDispp(DispCommon.getRulUsProvinceOffice(getSessionUser()));
		return "addtrans";
	}
	
	public String zhtrans() throws Exception {
		
		if(!userflag()){return null;}
		
		dispp=dispOptionDao.getDispp(DispCommon.getRulUsProvinceOffice(getSessionUser()));
		return "zhtrans";
	}
	
	public String zhtransdisp() throws Exception {
		
		if(!userflag()){return null;}
		
		return "zhtransdisp";
	}
	
	/*public String uptrans() throws Exception {
		provinces=dispOptionDao.getProvinces(null);
		dispp=dispOptionDao.getDispp(AddrCommon.getRulUsProvinceOffice(getSessionUser()));
		transBean=dispQueryDao.getBeanQueryTrans(TRANS_CODE);
		return "addtrans";
	}*/
	
}
