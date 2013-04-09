package com.cpst.emsadrdb.web.address;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.cpst.core.web.struts2.BaseActionSupport;
import com.cpst.emsadrdb.dao.address.AddrOptionDao;
import com.cpst.emsadrdb.entity.address.BldgrsdnsBean;
import com.cpst.emsadrdb.entity.address.CpBean;
import com.cpst.emsadrdb.entity.address.DistrictBean;
import com.cpst.emsadrdb.entity.address.DmBean;
import com.cpst.emsadrdb.entity.address.PgBean;
import com.cpst.emsadrdb.entity.address.QhzuiBean;
import com.cpst.emsadrdb.entity.address.ResorgBean;
import com.cpst.emsadrdb.entity.address.StreetBean;
import com.cpst.emsadrdb.entity.disp.CpwhrlpgstBean;
import com.cpst.emsadrdb.service.address.AddrCommon;

@ParentPackage("jsoncrud")
@Results( {@Result(type="json", name = "json")})
public class B04r03addroptionAction  extends BaseActionSupport{
	
	private static final long serialVersionUID = -6305069693022141671L;
	@Autowired
	private AddrOptionDao addrOptionDao;
	private List<CpBean> cpBeans;
	private List<DmBean> dmBeans;
	private List<PgBean> pgBeans;
	private List<QhzuiBean> qhzuiBeans;
	private List<DistrictBean> districtBeans;
	private List<StreetBean> streetBeans;
	private String PROVINCE_NAME;
	private String CITY_NAME;
	private String COUNTY_ID;
	private String STRT_ID;
	private String STREET_NAME;
	private String DORPLT_NAME;	
	private String CITY_CODE;
	private String DT_PK_CODE;
	private String DM_PK_CODE;
	
	private String NUM_FLAG;
	private String PREFIX;
	private String START_NUM;
	private String SUFFIX;
	private String DIST_CD;
	private CpwhrlpgstBean cpwhrlpgstBean;
	private List<CpwhrlpgstBean> cpwhrlpgstBeans;
	private List<ResorgBean> resorgBeans;
	
	
	public List<ResorgBean> getResorgBeans() {
		return resorgBeans;
	}

	public void setResorgBeans(List<ResorgBean> resorgBeans) {
		this.resorgBeans = resorgBeans;
	}

	public List<QhzuiBean> getQhzuiBeans() {
		return qhzuiBeans;
	}

	public void setQhzuiBeans(List<QhzuiBean> qhzuiBeans) {
		this.qhzuiBeans = qhzuiBeans;
	}

	public CpwhrlpgstBean getCpwhrlpgstBean() {
		return cpwhrlpgstBean;
	}

	public void setCpwhrlpgstBean(CpwhrlpgstBean cpwhrlpgstBean) {
		this.cpwhrlpgstBean = cpwhrlpgstBean;
	}

	public List<CpwhrlpgstBean> getCpwhrlpgstBeans() {
		return cpwhrlpgstBeans;
	}

	public void setCpwhrlpgstBeans(List<CpwhrlpgstBean> cpwhrlpgstBeans) {
		this.cpwhrlpgstBeans = cpwhrlpgstBeans;
	}

	public String getNUM_FLAG() {
		return NUM_FLAG;
	}

	public void setNUM_FLAG(String num_flag) {
		NUM_FLAG = num_flag;
	}

	public String getPREFIX() {
		return PREFIX;
	}

	public void setPREFIX(String prefix) {
		PREFIX = prefix;
	}

	public String getSTART_NUM() {
		return START_NUM;
	}

	public void setSTART_NUM(String start_num) {
		START_NUM = start_num;
	}

	public String getSUFFIX() {
		return SUFFIX;
	}

	public void setSUFFIX(String suffix) {
		SUFFIX = suffix;
	}

	public String getDIST_CD() {
		return DIST_CD;
	}

	public void setDIST_CD(String dist_cd) {
		DIST_CD = dist_cd;
	}

	public List<CpBean> getCpBeans() {
		return cpBeans;
	}

	public void setCpBeans(List<CpBean> cpBeans) {
		this.cpBeans = cpBeans;
	}

	public List<DmBean> getDmBeans() {
		return dmBeans;
	}

	public void setDmBeans(List<DmBean> dmBeans) {
		this.dmBeans = dmBeans;
	}

	public List<PgBean> getPgBeans() {
		return pgBeans;
	}

	public void setPgBeans(List<PgBean> pgBeans) {
		this.pgBeans = pgBeans;
	}

	public String getCITY_CODE() {
		return CITY_CODE;
	}

	public void setCITY_CODE(String city_code) {
		CITY_CODE = city_code;
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

	public String getSTRT_ID() {
		return STRT_ID;
	}

	public void setSTRT_ID(String strt_id) {
		STRT_ID = strt_id;
	}

	

	

	public String getDORPLT_NAME() {
		return DORPLT_NAME;
	}

	public void setDORPLT_NAME(String dorplt_name) {
		DORPLT_NAME = dorplt_name;
	}

	public List<StreetBean> getStreetBeans() {
		return streetBeans;
	}

	public void setStreetBeans(List<StreetBean> streetBeans) {
		this.streetBeans = streetBeans;
	}
	public String getCOUNTY_ID() {
		return COUNTY_ID;
	}

	public void setCOUNTY_ID(String county_id) {
		COUNTY_ID = county_id;
	}
	public String getSTREET_NAME() {
		return STREET_NAME;
	}

	public void setSTREET_NAME(String street_name) {
		STREET_NAME = street_name;
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
		
		districtBeans=addrOptionDao.getProvinces(AddrCommon.getRulUsProvinceOffice(getSessionUser()));
		return "json";
	}
	
	public String citys() throws Exception {		
		
		if(!userflag()){return "json";}
		
		districtBeans=addrOptionDao.getCitys(PROVINCE_NAME,AddrCommon.getRulUsCityOffice(getSessionUser()));
		return "json";
	}
	
	public String countys() throws Exception {		
		
		if(!userflag()){return "json";}
		
		districtBeans=addrOptionDao.getCountys(CITY_NAME,AddrCommon.getRulUsCityOffice(getSessionUser()));
		return "json";
	}
	
	public String resorgs() throws Exception {		
		
		if(!userflag()){return "json";}
		
		resorgBeans=addrOptionDao.getResorgs(COUNTY_ID,AddrCommon.getRulUsCityOffice(getSessionUser()));
		return "json";
	}
	
	public String resorgsnull() throws Exception {		
		
		if(!userflag()){return "json";}
		
		resorgBeans=addrOptionDao.getResorgs(COUNTY_ID,null);
		return "json";
	}
	
	public String citysnull() throws Exception {		
		
		if(!userflag()){return "json";}
		
		districtBeans=addrOptionDao.getCitys(PROVINCE_NAME,null);
		return "json";
	}
	
	public String citysno() throws Exception {		
		
		if(!userflag()){return "json";}
		
		districtBeans=addrOptionDao.getCitysno(PROVINCE_NAME,AddrCommon.getRulUsCityOffice(getSessionUser()));
		return "json";
	}
	
	public String countysnull() throws Exception {		
		
		if(!userflag()){return "json";}
		
		districtBeans=addrOptionDao.getCountys(CITY_NAME,null);
		return "json";
	}
	
	public String streets() throws Exception {		
		
		if(!userflag()){return "json";}
		
		streetBeans=addrOptionDao.getStreets(COUNTY_ID,STREET_NAME);
		return "json";
	}
	
	public String dts() throws Exception {		
		
		if(!userflag()){return "json";}
		
		//cpBeans=addrOptionDao.getDTs(CITY_CODE,AddrCommon.getRulUsPkId(getSessionUser()));
		cpBeans=addrOptionDao.getDTs(CITY_CODE,null,getSessionUser().getUsPkId().toString());
		return "json";
	}
	
	public String dtsfromdisp() throws Exception {		
		
		if(!userflag()){return "json";}
		
		//cpBeans=addrOptionDao.getDTsFromDisp(CITY_CODE,AddrCommon.getRulUsPkId(getSessionUser()));
		cpBeans=addrOptionDao.getDTsFromDisp(CITY_CODE,null,getSessionUser().getUsPkId().toString());
		return "json";
	}
	
	public String dtsfromwh() throws Exception {		
		
		if(!userflag()){return "json";}
		
		//cpBeans=addrOptionDao.getDTsFromDisp(CITY_CODE,AddrCommon.getRulUsPkId(getSessionUser()));
		cpBeans=addrOptionDao.getDTsFromWh(CITY_CODE);
		return "json";
	}
	
	public String dms() throws Exception {		
		
		if(!userflag()){return "json";}
		

			if(CITY_CODE!=null&&!CITY_CODE.equals("null") && CITY_CODE.length()>0){
				CITY_CODE = CITY_CODE;
			}else{
				CITY_CODE = AddrCommon.getRulUsCityOffice(getSessionUser());
			}
			
		dmBeans=addrOptionDao.getDMs(DT_PK_CODE,AddrCommon.getRulUsDepartmentOffice(getSessionUser()),CITY_CODE);
		return "json";
	}
	
	public String pgs() throws Exception {		
		
		if(!userflag()){return "json";}
		
		pgBeans=addrOptionDao.getPGs(DM_PK_CODE,AddrCommon.getUsPostsegOffice(getSessionUser()));
		return "json";
	}
	
	public void configCpwhrlpgstBean(){
		cpwhrlpgstBean = new CpwhrlpgstBean();
		if(STRT_ID!=null){
			cpwhrlpgstBean.setSTRT_ID(STRT_ID);
		}
		if(DIST_CD!=null){
			cpwhrlpgstBean.setDIST_CD(DIST_CD);
		}
		if(NUM_FLAG!=null){
			cpwhrlpgstBean.setNUM_FLAG(NUM_FLAG);
		}
		if(PREFIX!=null){
			cpwhrlpgstBean.setPREFIX(PREFIX);
		}
		if(START_NUM!=null){
			cpwhrlpgstBean.setSTART_NUM(START_NUM);
		}
		if(SUFFIX!=null){
			cpwhrlpgstBean.setSUFFIX(SUFFIX);
		}
	}
	
	public String dtdmpgs() throws Exception {		
		
		if(!userflag()){return "json";}
		
		configCpwhrlpgstBean();
		cpwhrlpgstBeans=addrOptionDao.getdtdmpgs(cpwhrlpgstBean);
		return "json";
	}
	
	public String dtdmpgsno() throws Exception {		
		
		if(!userflag()){return "json";}
		
		configCpwhrlpgstBean();
		cpwhrlpgstBeans=addrOptionDao.getdtdmpgsno(cpwhrlpgstBean);
		return "json";
	}
	
	public String qzuis() throws Exception {		
		
		if(!userflag()){return "json";}
		
		qhzuiBeans=addrOptionDao.getqzuis(DIST_CD);
		return "json";
	}
	public String hzuis() throws Exception {		
		
		if(!userflag()){return "json";}
		
		qhzuiBeans=addrOptionDao.gethzuis(DIST_CD);
		return "json";
	}
}
