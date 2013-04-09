package com.cpst.emsadrdb.web.address;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

import com.cpst.core.web.struts2.BaseActionSupport;
import com.cpst.emsadrdb.dao.address.AddrOptionDao;
import com.cpst.emsadrdb.dao.address.AddrQueryDao;
import com.cpst.emsadrdb.entity.address.BldgrsdnsBean;
import com.cpst.emsadrdb.entity.address.DistrictBean;
import com.cpst.emsadrdb.entity.address.OrganizationBean;
import com.cpst.emsadrdb.entity.address.StreetBean;
import com.cpst.emsadrdb.service.address.AddrCommon;
import com.cpst.emsadrdb.service.disp.DispCommon;

public class B04r01addrviewAction  extends BaseActionSupport{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6713451496948939455L;

	@Autowired
	private AddrOptionDao addrOptionDao;
	
	@Autowired
	private AddrQueryDao addrQueryDao;
	
	private List<DistrictBean> provinces;
	private List<DistrictBean> provincesjd;
	private List<DistrictBean> provincesg;
	private List<StreetBean> streets;
		
	private StreetBean streetBean;
	private BldgrsdnsBean bldgrsdnsBean;
	private OrganizationBean organizationBean;
	
	private String DIST_CD;
	private String STRT_ID;
	private String DORPLT_ID;
	private String RSDNBLDG_ID;
	private String ORG_ID;
	private String FLAG;
	private String TOTAL_DISTRICT_NAME;
	private String TOTAL_STREET_NAME;
	
	private String RSDNBLDG_NAME;
	private String ORG_NAME;
	private String ALL_DATA_FLAG;
	private String STREET_NAME;
	private String TOTAL_DIUMENPAI;
	
	
	
	public List<DistrictBean> getProvincesjd() {
		return provincesjd;
	}

	public void setProvincesjd(List<DistrictBean> provincesjd) {
		this.provincesjd = provincesjd;
	}

	public List<DistrictBean> getProvincesg() {
		return provincesg;
	}

	public void setProvincesg(List<DistrictBean> provincesg) {
		this.provincesg = provincesg;
	}

	public String getTOTAL_DIUMENPAI() {
		return TOTAL_DIUMENPAI;
	}

	public void setTOTAL_DIUMENPAI(String total_diumenpai) {
		TOTAL_DIUMENPAI = total_diumenpai;
	}

	public String getSTREET_NAME() {
		return STREET_NAME;
	}

	public void setSTREET_NAME(String street_name) {
		STREET_NAME = street_name;
	}

	public String getALL_DATA_FLAG() {
		return ALL_DATA_FLAG;
	}

	public void setALL_DATA_FLAG(String all_data_flag) {
		ALL_DATA_FLAG = all_data_flag;
	}

	public String getRSDNBLDG_NAME() {
		return RSDNBLDG_NAME;
	}

	public void setRSDNBLDG_NAME(String rsdnbldg_name) {
		RSDNBLDG_NAME = rsdnbldg_name;
	}

	public String getORG_NAME() {
		return ORG_NAME;
	}

	public void setORG_NAME(String org_name) {
		ORG_NAME = org_name;
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

	public String getFLAG() {
		return FLAG;
	}

	public void setFLAG(String flag) {
		FLAG = flag;
	}

	public String getRSDNBLDG_ID() {
		return RSDNBLDG_ID;
	}

	public void setRSDNBLDG_ID(String rsdnbldg_id) {
		RSDNBLDG_ID = rsdnbldg_id;
	}

	public String getORG_ID() {
		return ORG_ID;
	}

	public void setORG_ID(String org_id) {
		ORG_ID = org_id;
	}

	public String getDORPLT_ID() {
		return DORPLT_ID;
	}

	public void setDORPLT_ID(String dorplt_id) {
		DORPLT_ID = dorplt_id;
	}

	public String getDIST_CD() {
		return DIST_CD;
	}

	public void setDIST_CD(String dist_cd) {
		DIST_CD = dist_cd;
	}

	public String getSTRT_ID() {
		return STRT_ID;
	}

	public void setSTRT_ID(String strt_id) {
		STRT_ID = strt_id;
	}

	public List<DistrictBean> getProvinces() {
		return provinces;
	}

	public void setProvinces(List<DistrictBean> provinces) {
		this.provinces = provinces;
	}

	public List<StreetBean> getStreets() {
		return streets;
	}

	public void setStreets(List<StreetBean> streets) {
		this.streets = streets;
	}

	public StreetBean getStreetBean() {
		return streetBean;
	}

	public void setStreetBean(StreetBean streetBean) {
		this.streetBean = streetBean;
	}

	public BldgrsdnsBean getBldgrsdnsBean() {
		return bldgrsdnsBean;
	}

	public void setBldgrsdnsBean(BldgrsdnsBean bldgrsdnsBean) {
		this.bldgrsdnsBean = bldgrsdnsBean;
	}

	public OrganizationBean getOrganizationBean() {
		return organizationBean;
	}

	public void setOrganizationBean(OrganizationBean organizationBean) {
		this.organizationBean = organizationBean;
	}

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
	
	public String viewstreets() throws Exception {
		
		if(!userflag()){return null;}
		
		provinces=addrOptionDao.getProvinces(AddrCommon.getRulUsProvinceOffice(getSessionUser()));
		return "viewstreets";
	}
	
	public String viewtwstr() throws Exception {
		
		if(!userflag()){return null;}
		
		provinces=addrOptionDao.getProvinces(AddrCommon.getRulUsProvinceOffice(getSessionUser()));
		return "viewtwstr";
	}
	
	public String viewtwstrd() throws Exception {
		
		if(!userflag()){return null;}
		
		return "viewtwstrd";
	}
	
	public String viewlogs() throws Exception {
		
		if(!userflag()){return null;}
		
		provinces=addrOptionDao.getProvinces(AddrCommon.getRulUsProvinceOffice(getSessionUser()));
		return "viewlogs";
	}
	
	public String viewlogsbyid() throws Exception {
		
		if(!userflag()){return null;}
		
		return "viewlogsbyid";
	}
	
	public String viewbos() throws Exception {
		
		if(!userflag()){return null;}
		
		return "viewbos";
	}
	
	public String xxstreet() throws Exception {
		
		if(!userflag()){return null;}
		
		streetBean=addrQueryDao.getStreet(DIST_CD, STRT_ID);
		return "xxstreet";
	}
		
	public String viewbldgs() throws Exception {
		
		if(!userflag()){return null;}
		
		return "viewbldgs";
	}
	
	public String viewbldgrsdns() throws Exception {
		
		if(!userflag()){return null;}
		
		return "viewbldgrsdns";
	}
	
	public String xxbldgrsdns() throws Exception {
		
		if(!userflag()){return null;}
		
		bldgrsdnsBean=addrQueryDao.getBldgrsdns(DIST_CD, RSDNBLDG_ID);
		return "xxbldgrsdns";
	}
	
	public String vieworganizations() throws Exception {
		
		if(!userflag()){return null;}
		
		return "vieworganizations";
	}
	
	public String xxorganization() throws Exception {
		
		if(!userflag()){return null;}
		
		organizationBean=addrQueryDao.getOrganizationBean(DIST_CD, ORG_ID);
		return "xxorganization";
	}
	public String shdata() throws Exception {
		
		if(!userflag()){return null;}
		
		provinces=addrOptionDao.getProvinces(AddrCommon.getRulUsProvinceOffice(getSessionUser()));
		return "shdata";
	}
	public String viewjnadtpg() throws Exception {
		
		if(!userflag()){return null;}
		
		provinces=addrOptionDao.getProvinces(AddrCommon.getRulUsProvinceOffice(getSessionUser()));
		return "viewjnadtpg";
	}
	public String vieworgandius() throws Exception {
		
		if(!userflag()){return null;}
		
		provinces=addrOptionDao.getProvinces(AddrCommon.getRulUsProvinceOffice(getSessionUser()));
		return "vieworgandius";
	}
	public String viewbldgrsdnsdius() throws Exception {
		
		if(!userflag()){return null;}
		
		provinces=addrOptionDao.getProvinces(AddrCommon.getRulUsProvinceOffice(getSessionUser()));
		return "viewbldgrsdnsdius";
	}
	public String viewstreetsdium() throws Exception {
		
		if(!userflag()){return null;}
		
		provinces=addrOptionDao.getProvinces(AddrCommon.getRulUsProvinceOffice(getSessionUser()));
		return "viewstreetsdium";
	}
	public String viewstreetsluan() throws Exception {
		
		if(!userflag()){return null;}
		
		provinces=addrOptionDao.getProvinces(AddrCommon.getRulUsProvinceOffice(getSessionUser()));
		return "viewstreetsluan";
	}
	public String vieworganluan() throws Exception {
		
		if(!userflag()){return null;}
		
		provinces=addrOptionDao.getProvinces(AddrCommon.getRulUsProvinceOffice(getSessionUser()));
		return "vieworganluan";
	}
	public String viewbldgrsdnsluan() throws Exception {
		
		if(!userflag()){return null;}
		
		provinces=addrOptionDao.getProvinces(AddrCommon.getRulUsProvinceOffice(getSessionUser()));
		return "viewbldgrsdnsluan";
	}
	public String viewqhzuis() throws Exception {
		
		if(!userflag()){return null;}
		
		provinces=addrOptionDao.getProvinces(AddrCommon.getRulUsProvinceOffice(getSessionUser()));
		return "viewqhzuis";
	}
	public String viewstreetsqfhdb() throws Exception {
		
		if(!userflag()){return null;}
		
		provinces=addrOptionDao.getProvinces(AddrCommon.getRulUsProvinceOffice(getSessionUser()));
		return "viewstreetsqfhdb";
	}
	public String viewrgpqpd() throws Exception {
		
		if(!userflag()){return null;}
		
		provinces=addrOptionDao.getProvinces(AddrCommon.getRulUsProvinceOffice(getSessionUser()));
		if((getSessionUser().getRulLevel()==10 || getSessionUser().getRulLevel()==15 || getSessionUser().getRulLevel()==20
				) && DispCommon.isMunicipalitiesID(AddrCommon.getRulUsProvinceOffice(getSessionUser()).substring(0, 2))){
			provincesg=addrOptionDao.getProvincesno(AddrCommon.getRulUsProvinceOffice(getSessionUser()));
		}else{
			provincesg=addrOptionDao.getProvinces(null);
		}
		return "viewrgpqpd";
	}
	public String viewrgpqpdj() throws Exception {
		
		if(!userflag()){return null;}
		
		provinces=addrOptionDao.getProvinces(AddrCommon.getRulUsProvinceOffice(getSessionUser()));
		if((getSessionUser().getRulLevel()==10 || getSessionUser().getRulLevel()==15 || getSessionUser().getRulLevel()==20
				) && DispCommon.isMunicipalitiesID(AddrCommon.getRulUsProvinceOffice(getSessionUser()).substring(0, 2))){
			provincesg=addrOptionDao.getProvincesno(AddrCommon.getRulUsProvinceOffice(getSessionUser()));
		}else{
			provincesg=addrOptionDao.getProvinces(null);
		}
		return "viewrgpqpdj";
	}
	public String viewxxqr() throws Exception {
		
		if(!userflag()){return null;}
		
		provinces=addrOptionDao.getProvinces(AddrCommon.getRulUsProvinceOffice(getSessionUser()));
		return "viewxxqr";
	}
	
	public String viewcksr() throws Exception {
		
		if(!userflag()){return null;}
		
		provinces=addrOptionDao.getProvinces(AddrCommon.getRulUsProvinceOffice(getSessionUser()));
		provincesjd=addrOptionDao.getProvinces(null);
		return "viewcksr";
	}
	
	public String viewzshf() throws Exception {
		
		if(!userflag()){return null;}
		
		provinces=addrOptionDao.getProvinces(AddrCommon.getRulUsProvinceOffice(getSessionUser()));
		return "viewzshf";
	}
	
	public String viewgonggao() throws Exception {
		
		if(!userflag()){return null;}
		
		return "viewgonggao";
	}
	
	public String viewfkqr() throws Exception {
		
		if(!userflag()){return null;}
		
		provinces=addrOptionDao.getProvinces(AddrCommon.getRulUsProvinceOffice(getSessionUser()));
		return "viewfkqr";
	}
	
	public String viewrgpqpdn() throws Exception {
		
		if(!userflag()){return null;}
		
		provinces=addrOptionDao.getProvinces(AddrCommon.getRulUsProvinceOffice(getSessionUser()));
		return "viewrgpqpdn";
	}
	
	public String viewrgpqpdnls() throws Exception {
		
		if(!userflag()){return null;}
		
		provinces=addrOptionDao.getProvinces(AddrCommon.getRulUsProvinceOffice(getSessionUser()));
		return "viewrgpqpdnls";
	}
}
