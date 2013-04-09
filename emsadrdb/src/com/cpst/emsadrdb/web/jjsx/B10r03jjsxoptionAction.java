package com.cpst.emsadrdb.web.jjsx;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import com.cpst.core.web.struts2.BaseActionSupport;
import com.cpst.emsadrdb.dao.jjsx.JjsxOptionDao;
import com.cpst.emsadrdb.entity.jjsx.DistrictBean;
import com.cpst.emsadrdb.entity.jjsx.JJSXBean;
import com.cpst.emsadrdb.service.jjsx.JjsxCommon;

@ParentPackage("jsoncrud")
@Results( { @Result(type = "json", name = "json") })
public class B10r03jjsxoptionAction extends BaseActionSupport {

	private static final long serialVersionUID = -6305069693022141671L;

	@Autowired
	private JjsxOptionDao jjsxOptionDao;

	private List<DistrictBean> districtBeans;
	
	private List<JJSXBean> spcBeans;

	private String PROVINCE_NAME;
	private String CITY_NAME;
	private String CITY_CODE;
	private String COUNTY_CODE;
	private String PCFLAG;


	
	public String getPCFLAG() {
		return PCFLAG;
	}

	public void setPCFLAG(String pcflag) {
		PCFLAG = JjsxCommon.commonsql_inj(pcflag);
	}

	public String getCOUNTY_CODE() {
		return COUNTY_CODE;
	}

	public void setCOUNTY_CODE(String county_code) {
		COUNTY_CODE = JjsxCommon.commonsql_inj(county_code);
	}

	public List<JJSXBean> getSpcBeans() {
		return spcBeans;
	}

	public void setSpcBeans(List<JJSXBean> spcBeans) {
		this.spcBeans = spcBeans;
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
		PROVINCE_NAME = JjsxCommon.commonsql_inj(province_name);
	}

	public String getCITY_NAME() {
		return CITY_NAME;
	}

	public void setCITY_NAME(String city_name) {
		CITY_NAME = JjsxCommon.commonsql_inj(city_name);
	}
	
	
	public String getCITY_CODE() {
		return CITY_CODE;
	}

	public void setCITY_CODE(String city_code) {
		CITY_CODE = JjsxCommon.commonsql_inj(city_code);
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

	public String provincesyt() throws Exception {
		
		if(!userflag()){return "json";}
		
		districtBeans = jjsxOptionDao.getProvincesyt(JjsxCommon
				.getRulUsProvinceOffice(getSessionUser()));
		return "json";
	}

	public String citysyt() throws Exception {
		
		if(!userflag()){return "json";}
		
		districtBeans = jjsxOptionDao.getCitysyt(PROVINCE_NAME, JjsxCommon
				.getRulUsCityOffice(getSessionUser()));
		return "json";
	}
	
	public String citysytuse() throws Exception {
		
		if(!userflag()){return "json";}
		
		districtBeans = jjsxOptionDao.getCitysyt(PROVINCE_NAME, JjsxCommon
				.getRulUsCityOffice(getSessionUser()));
		return "json";
	}


	public String countysyt() throws Exception {
		
		if(!userflag()){return "json";}
		
		districtBeans = jjsxOptionDao.getCountysyt(CITY_NAME,CITY_CODE);
		return "json";
	}

	public String spcs() throws Exception {
		
		if(!userflag()){return "json";}
		
		spcBeans = jjsxOptionDao.getSpcs(CITY_CODE,COUNTY_CODE,PCFLAG);
		
		return "json";
	}
	
	
}
