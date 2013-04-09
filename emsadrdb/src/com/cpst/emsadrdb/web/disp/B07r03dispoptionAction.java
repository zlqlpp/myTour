package com.cpst.emsadrdb.web.disp;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.cpst.core.web.struts2.BaseActionSupport;
import com.cpst.emsadrdb.dao.disp.DispOptionDao;
import com.cpst.emsadrdb.entity.disp.DispBean;
import com.cpst.emsadrdb.entity.disp.DistrictBean;
import com.cpst.emsadrdb.entity.disp.WhdistrictBean;
import com.cpst.emsadrdb.service.address.AddrCommon;
import com.cpst.emsadrdb.service.disp.DispCommon;


@ParentPackage("jsoncrud")
@Results( {@Result(type="json", name = "json")})
public class B07r03dispoptionAction  extends BaseActionSupport{
	
	private List<DistrictBean> districtBeans;
	private List<DispBean> dispBeans;
	private List<WhdistrictBean> whdistrictBeans;
	
	private String PROVINCE_NAME;
	private String CITY_NAME;
	private String DISP_PROVINCE_NAME;
	
	private String CITY_CODE;
	private String PROVINCE_CODE;
	private String DISP_OFFICE_CODE;
	
	public String getDISP_OFFICE_CODE() {
		return DISP_OFFICE_CODE;
	}

	public void setDISP_OFFICE_CODE(String disp_office_code) {
		DISP_OFFICE_CODE = disp_office_code;
	}

	public List<WhdistrictBean> getWhdistrictBeans() {
		return whdistrictBeans;
	}

	public void setWhdistrictBeans(List<WhdistrictBean> whdistrictBeans) {
		this.whdistrictBeans = whdistrictBeans;
	}

	public String getCITY_CODE() {
		return CITY_CODE;
	}

	public void setCITY_CODE(String city_code) {
		CITY_CODE = city_code;
	}

	public String getPROVINCE_CODE() {
		return PROVINCE_CODE;
	}

	public void setPROVINCE_CODE(String province_code) {
		PROVINCE_CODE = province_code;
	}

	public List<DispBean> getDispBeans() {
		return dispBeans;
	}

	public void setDispBeans(List<DispBean> dispBeans) {
		this.dispBeans = dispBeans;
	}

	public String getDISP_PROVINCE_NAME() {
		return DISP_PROVINCE_NAME;
	}

	public void setDISP_PROVINCE_NAME(String disp_province_name) {
		DISP_PROVINCE_NAME = disp_province_name;
	}

	public List<DistrictBean> getDistrictBeans() {
		return districtBeans;
	}

	public void setDistrictBeans(List<DistrictBean> districtBeans) {
		this.districtBeans = districtBeans;
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

	private static final long serialVersionUID = -6305069693022141671L;
	@Autowired
	private DispOptionDao dispOptionDao;
	
	public Boolean userflag(){
		if(getSessionUser()!=null && getSessionUser().getUsLoginId() != null && getSessionUser().getUsLoginId().length() > 0){
			return true;
		}
		else{
			//saveMessage = "用户信息丢失,请重新登陆!";
			return false;
		}
	}
	
	public String provinces() throws Exception {
		
		if(!userflag()){return "json";}
		
		districtBeans=dispOptionDao.getProvinces(DispCommon.getRulUsProvinceOffice(getSessionUser()));
		return "json";
	}
	
	public String citys() throws Exception {
		
		if(!userflag()){return "json";}
		
		districtBeans=dispOptionDao.getCitys(PROVINCE_NAME,DispCommon.getRulUsCityOffice(getSessionUser()));
		return "json";
	}
	
	public String citysname() throws Exception {
		
		if(!userflag()){return "json";}
		
		districtBeans=dispOptionDao.getCitysname(PROVINCE_NAME,DispCommon.getRulUsCityOffice(getSessionUser()));
		return "json";
	}
	
	public String countys() throws Exception {
		
		if(!userflag()){return "json";}
		
		districtBeans=dispOptionDao.getCountys(CITY_NAME,AddrCommon.getRulUsCityOffice(getSessionUser()));
		return "json";
	}
	
	public String countysname() throws Exception {
		
		if(!userflag()){return "json";}
		
		districtBeans=dispOptionDao.getCountysname(CITY_NAME,AddrCommon.getRulUsCityOffice(getSessionUser()));
		return "json";
	}
	
	public String dispps() throws Exception {
		
		if(!userflag()){return "json";}
		
		dispBeans=dispOptionDao.getDispp(DispCommon.getRulUsProvinceOffice(getSessionUser()));
		return "json";
	}
	
	public String disppds() throws Exception {
		
		if(!userflag()){return "json";}
		
		dispBeans=dispOptionDao.getDisppd(PROVINCE_NAME,DispCommon.getRulUsCityOffice(getSessionUser()));
		return "json";
	}
	
	public String dispds() throws Exception {
		
		if(!userflag()){return "json";}
		
		dispBeans=dispOptionDao.getDispd(DISP_PROVINCE_NAME);
		return "json";
	}
	
	public String dispdwhs() throws Exception {
		
		if(!userflag()){return "json";}
		
		dispBeans=dispOptionDao.getDispdwh(DISP_OFFICE_CODE);
		return "json";
	}
	
	public String whdistrictdispl() throws Exception {
		
		if(!userflag()){return "json";}
		
		whdistrictBeans=dispOptionDao.getWhdistrictdispl(PROVINCE_CODE.substring(0, 2));
		return "json";
	}
	
	public String whdistrictdispr() throws Exception {
		
		if(!userflag()){return "json";}
		
		whdistrictBeans=dispOptionDao.getWhdistrictdispr(CITY_CODE);
		return "json";
	}

}
