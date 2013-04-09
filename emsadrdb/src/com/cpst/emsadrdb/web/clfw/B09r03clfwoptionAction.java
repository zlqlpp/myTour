package com.cpst.emsadrdb.web.clfw;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import com.cpst.core.web.struts2.BaseActionSupport;
import com.cpst.emsadrdb.dao.clfw.ClfwOptionDao;
import com.cpst.emsadrdb.entity.clfw.CLFWBean;
import com.cpst.emsadrdb.entity.clfw.DistrictBean;
import com.cpst.emsadrdb.service.clfw.ClfwCommon;

@ParentPackage("jsoncrud")
@Results( { @Result(type = "json", name = "json") })
public class B09r03clfwoptionAction extends BaseActionSupport {

	private static final long serialVersionUID = -6305069693022141671L;

	@Autowired
	private ClfwOptionDao clfwOptionDao;

	private List<DistrictBean> districtBeans;
	private List<CLFWBean> whclfwtdbBeans;
	private List<CLFWBean> dmBeans;
	private List<CLFWBean> pgBeans;
	private List<CLFWBean> clfwpcsBeans;
	private List<CLFWBean> clfwpcsshengBeans;
	private List<CLFWBean> clfwpcsshiBeans;
	private List<CLFWBean> clfwylxxbzBeans;

	private String PROVINCE_NAME;
	private String CITY_NAME;
	private String CLFWPC_NAMES;
	private String SEL_CLFWTDB_PZSX;
	private String DM_PK_CODE;
	private String CLFWPC_SX;

	
	
	
	public List<CLFWBean> getClfwylxxbzBeans() {
		return clfwylxxbzBeans;
	}

	public void setClfwylxxbzBeans(List<CLFWBean> clfwylxxbzBeans) {
		this.clfwylxxbzBeans = clfwylxxbzBeans;
	}

	public List<CLFWBean> getClfwpcsBeans() {
		return clfwpcsBeans;
	}

	public void setClfwpcsBeans(List<CLFWBean> clfwpcsBeans) {
		this.clfwpcsBeans = clfwpcsBeans;
	}

	public List<CLFWBean> getClfwpcsshiBeans() {
		return clfwpcsshiBeans;
	}

	public void setClfwpcsshiBeans(List<CLFWBean> clfwpcsshiBeans) {
		this.clfwpcsshiBeans = clfwpcsshiBeans;
	}

	public String getCLFWPC_SX() {
		return CLFWPC_SX;
	}

	public void setCLFWPC_SX(String clfwpc_sx) {
		CLFWPC_SX = clfwpc_sx;
	}

	public List<CLFWBean> getClfwpcsshengBeans() {
		return clfwpcsshengBeans;
	}

	public void setClfwpcsshengBeans(List<CLFWBean> clfwpcsshengBeans) {
		this.clfwpcsshengBeans = clfwpcsshengBeans;
	}

	public String getDM_PK_CODE() {
		return DM_PK_CODE;
	}

	public void setDM_PK_CODE(String dm_pk_code) {
		DM_PK_CODE = dm_pk_code;
	}

	public List<CLFWBean> getDmBeans() {
		return dmBeans;
	}

	public void setDmBeans(List<CLFWBean> dmBeans) {
		this.dmBeans = dmBeans;
	}

	public List<CLFWBean> getPgBeans() {
		return pgBeans;
	}

	public void setPgBeans(List<CLFWBean> pgBeans) {
		this.pgBeans = pgBeans;
	}

	public List<DistrictBean> getDistrictBeans() {
		return districtBeans;
	}

	public void setDistrictBeans(List<DistrictBean> districtBeans) {
		this.districtBeans = districtBeans;
	}

	public List<CLFWBean> getWhclfwtdbBeans() {
		return whclfwtdbBeans;
	}

	public void setWhclfwtdbBeans(List<CLFWBean> whclfwtdbBeans) {
		this.whclfwtdbBeans = whclfwtdbBeans;
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

	public String getCLFWPC_NAMES() {
		return CLFWPC_NAMES;
	}

	public void setCLFWPC_NAMES(String clfwpc_names) {
		CLFWPC_NAMES = clfwpc_names;
	}

	public String getSEL_CLFWTDB_PZSX() {
		return SEL_CLFWTDB_PZSX;
	}

	public void setSEL_CLFWTDB_PZSX(String sel_clfwtdb_pzsx) {
		SEL_CLFWTDB_PZSX = sel_clfwtdb_pzsx;
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

	public String provinces() throws Exception {
		
		if(!userflag()){return "json";}
		
		districtBeans = clfwOptionDao.getProvinces(ClfwCommon
				.getRulUsProvinceOffice(getSessionUser()));
		return "json";
	}

	public String citys() throws Exception {
		
		if(!userflag()){return "json";}
		
		districtBeans = clfwOptionDao.getCitys(PROVINCE_NAME, ClfwCommon
				.getRulUsCityOffice(getSessionUser()));
		return "json";
	}
	
	public String citysnull() throws Exception {
		
		if(!userflag()){return "json";}
		
		districtBeans = clfwOptionDao.getCitys(PROVINCE_NAME,null);
		return "json";
	}

	public String countys() throws Exception {
		
		if(!userflag()){return "json";}
		
		districtBeans = clfwOptionDao.getCountys(CITY_NAME, ClfwCommon
				.getRulUsCityOffice(getSessionUser()));
		return "json";
	}
	
	public String countysnull() throws Exception {
		
		if(!userflag()){return "json";}
		
		districtBeans = clfwOptionDao.getCountys(CITY_NAME,null);
		return "json";
	}
	
	public String clfwpcs() throws Exception {
		
		if(!userflag()){return "json";}
		
		clfwpcsBeans=clfwOptionDao.getClfwpcs(CITY_NAME,CLFWPC_SX);
		return "json";
	}
	
	public String clfwpcssheng() throws Exception {
		
		if(!userflag()){return "json";}
		
		clfwpcsshengBeans=clfwOptionDao.getClfwpcssheng(CITY_NAME,CLFWPC_SX);
		return "json";
	}
	
	public String clfwpcsshi() throws Exception {
		
		if(!userflag()){return "json";}
		
		clfwpcsshiBeans=clfwOptionDao.getClfwpcsshi(CITY_NAME,CLFWPC_SX);
		return "json";
	}
	
	public String dms() throws Exception {
		
		if(!userflag()){return "json";}
		
		dmBeans=clfwOptionDao.getDMs(CITY_NAME,ClfwCommon.getRulUsDepartmentOffice(getSessionUser()));
		return "json";
	}
	
	public String pgs() throws Exception {
		
		if(!userflag()){return "json";}
		
		pgBeans=clfwOptionDao.getPGs(DM_PK_CODE);
		return "json";
	}

	public String whclfwtdbl() throws Exception {
		
		if(!userflag()){return "json";}
		
		whclfwtdbBeans = clfwOptionDao.getWhclfwtdbs(CITY_NAME, CLFWPC_NAMES,ClfwCommon.getRulUsDepartmentOffice(getSessionUser()));
		return "json";
	}
	
	public String clfwylxxbzs() throws Exception {
		
		if(!userflag()){return "json";}
		
		clfwylxxbzBeans=clfwOptionDao.getClfwylxxbz(CITY_NAME);
		return "json";
	}
}
