package com.cpst.emsadrdb.web.address;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

import com.cpst.core.web.struts2.BaseActionSupport;
import com.cpst.emsadrdb.dao.address.AddrOptionDao;
import com.cpst.emsadrdb.dao.address.AddrQueryDao;
import com.cpst.emsadrdb.entity.address.BldgrsdnsBean;
import com.cpst.emsadrdb.entity.address.DistrictBean;
import com.cpst.emsadrdb.entity.address.ExgcltitemopeBean;
import com.cpst.emsadrdb.entity.address.OrganizationBean;
import com.cpst.emsadrdb.entity.address.QhzuiBean;
import com.cpst.emsadrdb.entity.address.StreetBean;
import com.cpst.emsadrdb.service.address.AddrCommon;


public class B04cud01addrviewAction extends BaseActionSupport{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4984115778102208068L;

	@Autowired
	private AddrOptionDao addrOptionDao;
	
	@Autowired
	private AddrQueryDao addrQueryDao;
	
	private List<DistrictBean> provinces;
	private List<DistrictBean> provincesg;
	private List<StreetBean> streets;
		
	private StreetBean streetBean;
	private BldgrsdnsBean bldgrsdnsBean;
	private OrganizationBean organizationBean;
	
	private String DIST_CD;
	private String STRT_ID;
	private String RSDNBLDG_ID;
	private String ORG_ID;
	private String TOTAL_DISTRICT_NAME;
	private String TOTAL_STREET_NAME;
	private String FLAG;
	
	private String SEQID;
	private String REC_ALLADDR;
	private String REC_ALLADDRSTREET;
	private String ADDR_FLAG;
	
	private ExgcltitemopeBean exgcltitemopeBean;
	
	
	public List<DistrictBean> getProvincesg() {
		return provincesg;
	}

	public void setProvincesg(List<DistrictBean> provincesg) {
		this.provincesg = provincesg;
	}

	public ExgcltitemopeBean getExgcltitemopeBean() {
		return exgcltitemopeBean;
	}

	public void setExgcltitemopeBean(ExgcltitemopeBean exgcltitemopeBean) {
		this.exgcltitemopeBean = exgcltitemopeBean;
	}

	public String getADDR_FLAG() {
		return ADDR_FLAG;
	}

	public void setADDR_FLAG(String addr_flag) {
		ADDR_FLAG = addr_flag;
	}

	public String getREC_ALLADDRSTREET() {
		return REC_ALLADDRSTREET;
	}

	public void setREC_ALLADDRSTREET(String rec_alladdrstreet) {
		REC_ALLADDRSTREET = rec_alladdrstreet;
	}

	public String getSEQID() {
		return SEQID;
	}

	public void setSEQID(String seqid) {
		SEQID = seqid;
	}

	public String getREC_ALLADDR() {
		return REC_ALLADDR;
	}

	public void setREC_ALLADDR(String rec_alladdr) {
		REC_ALLADDR = rec_alladdr;
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
		
	public String addstreet() throws Exception {
		
		if(!userflag()){return null;}
		
		provinces=addrOptionDao.getProvinces(AddrCommon.getRulUsProvinceOffice(getSessionUser()));
		return "addstreet";
	}
	
	public String upstreet() throws Exception {
		
		if(!userflag()){return null;}
		
		provinces=addrOptionDao.getProvinces(AddrCommon.getRulUsProvinceOffice(getSessionUser()));
		streetBean=addrQueryDao.getStreet(DIST_CD, STRT_ID);
		return "addstreet";
	}
	
	public String shstreet() throws Exception {
		
		if(!userflag()){return null;}
		
		streetBean=addrQueryDao.getStreet(DIST_CD, STRT_ID);
		return "xxstreet";
	}
		
	public String addbldgrsdns() throws Exception {
		
		if(!userflag()){return null;}
		
		provinces=addrOptionDao.getProvinces(AddrCommon.getRulUsProvinceOffice(getSessionUser()));
		bldgrsdnsBean = new BldgrsdnsBean();
		if(DIST_CD!=null){
			bldgrsdnsBean.setDIST_CD(DIST_CD);
		}
		if(STRT_ID!=null){
			bldgrsdnsBean.setSTRT_ID(STRT_ID);
		}
		return "addbldgrsdns";
	}
	
	public String upbldgrsdns() throws Exception {
		
		if(!userflag()){return null;}
		
		provinces=addrOptionDao.getProvinces(AddrCommon.getRulUsProvinceOffice(getSessionUser()));
		bldgrsdnsBean=addrQueryDao.getBldgrsdns(DIST_CD, RSDNBLDG_ID);
		return "addbldgrsdns";
	}
	public String addbldgrsdnsdius() throws Exception {
		
		if(!userflag()){return null;}
		
		provinces=addrOptionDao.getProvinces(AddrCommon.getRulUsProvinceOffice(getSessionUser()));
		bldgrsdnsBean=addrQueryDao.getBldgrsdnsdiusBean(DIST_CD, RSDNBLDG_ID);
		return "addbldgrsdnsdius";
	}
	public String shbldgrsdns() throws Exception {
		
		if(!userflag()){return null;}
		
		bldgrsdnsBean=addrQueryDao.getBldgrsdnssh(DIST_CD, RSDNBLDG_ID);
		return "xxbldgrsdns";
	}
	
	public String addorganization() throws Exception {
		
		if(!userflag()){return null;}
		
		provinces=addrOptionDao.getProvinces(AddrCommon.getRulUsProvinceOffice(getSessionUser()));
		organizationBean = new OrganizationBean();
		if(DIST_CD!=null){
			organizationBean.setDIST_CD(DIST_CD);
		}
		if(STRT_ID!=null){
			organizationBean.setSTRT_ID(STRT_ID);
		}
		return "addorganization";
	}
	
	public String uporganization() throws Exception {
		
		if(!userflag()){return null;}
		
		provinces=addrOptionDao.getProvinces(AddrCommon.getRulUsProvinceOffice(getSessionUser()));
		organizationBean=addrQueryDao.getOrganizationBean(DIST_CD, ORG_ID);
		return "addorganization";
	}
	
	public String shorganization() throws Exception {
		
		if(!userflag()){return null;}
		
		organizationBean=addrQueryDao.getOrganizationshBean(DIST_CD, ORG_ID);
		return "xxorganization";
	}
	
	public String addorgandius() throws Exception {
		
		if(!userflag()){return null;}
		
		provinces=addrOptionDao.getProvinces(AddrCommon.getRulUsProvinceOffice(getSessionUser()));
		organizationBean=addrQueryDao.getOrgandiusBean(DIST_CD, ORG_ID);
		return "addorgandius";
	}
	
	public String luanstreet() throws Exception {
		
		if(!userflag()){return null;}
		
		provinces=addrOptionDao.getProvinces(AddrCommon.getRulUsProvinceOffice(getSessionUser()));
		streetBean=addrQueryDao.getStreet(DIST_CD, STRT_ID);
		return "luanstreet";
	}
	
	public String qfhdbstreet() throws Exception {
		
		if(!userflag()){return null;}
		
		provinces=addrOptionDao.getProvinces(AddrCommon.getRulUsProvinceOffice(getSessionUser()));
		streetBean=addrQueryDao.getStreet(DIST_CD, STRT_ID);
		return "qfhdbstreet";
	}
	
	public String luanorganization() throws Exception {
		
		if(!userflag()){return null;}
		
		provinces=addrOptionDao.getProvinces(AddrCommon.getRulUsProvinceOffice(getSessionUser()));
		organizationBean=addrQueryDao.getOrganizationBean(DIST_CD, ORG_ID);
		return "luanorganization";
	}
	
	public String luanbldgrsdns() throws Exception {
		
		if(!userflag()){return null;}
		
		provinces=addrOptionDao.getProvinces(AddrCommon.getRulUsProvinceOffice(getSessionUser()));
		bldgrsdnsBean=addrQueryDao.getBldgrsdns(DIST_CD, RSDNBLDG_ID);
		return "luanbldgrsdns";
	}
	
	public String addqhzuis() throws Exception {
		
		if(!userflag()){return null;}
		
		provinces=addrOptionDao.getProvinces(AddrCommon.getRulUsProvinceOffice(getSessionUser()));
		return "addqhzuis";
	}
	
	private QhzuiBean qhzuiBean;
	
	public QhzuiBean getQhzuiBean() {
		return qhzuiBean;
	}

	public void setQhzuiBean(QhzuiBean qhzuiBean) {
		this.qhzuiBean = qhzuiBean;
	}
	private String QIANZUI_ID;
	
	public String getQIANZUI_ID() {
		return QIANZUI_ID;
	}

	public void setQIANZUI_ID(String qianzui_id) {
		QIANZUI_ID = qianzui_id;
	}

	public String upqhzuis() throws Exception {
		
		if(!userflag()){return null;}
		
		provinces=addrOptionDao.getProvinces(AddrCommon.getRulUsProvinceOffice(getSessionUser()));
		qhzuiBean=addrQueryDao.getQhzuis(DIST_CD, QIANZUI_ID);
		return "addqhzuis";
	}
	
	public String hbstreet() throws Exception {
		
		if(!userflag()){return null;}
		
		provinces=addrOptionDao.getProvinces(AddrCommon.getRulUsProvinceOffice(getSessionUser()));
		return "hbstreet";
	}
	
	public String uprgming() throws Exception {
		
		if(!userflag()){return null;}
		
		provinces=addrOptionDao.getProvinces(AddrCommon.getRulUsProvinceOffice(getSessionUser()));
		exgcltitemopeBean=addrQueryDao.getRgming(SEQID);
		return "uprgming";
	}
	
	public String opentupian() throws Exception {
		
		if(!userflag()){return null;}

		return "opentupian";
	}
}
