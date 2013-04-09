package com.cpst.emsadrdb.web.wh;

import java.util.List;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.cpst.core.web.struts2.BaseActionSupport;
import com.cpst.emsadrdb.dao.base.BaseOrgDistrictDao;
import com.cpst.emsadrdb.dao.wh.DistrictDao;
import com.cpst.emsadrdb.entity.base.BaseOrgDistrict;
import com.cpst.emsadrdb.entity.pmn.User;
import com.cpst.emsadrdb.entity.wh.District;
import com.cpst.emsadrdb.entity.wh.ShareDistrict;
import com.cpst.emsadrdb.service.base.BaseOrgDistrictManager;
import com.cpst.emsadrdb.service.wh.B01cud01Manager;
import com.opensymphony.xwork2.ModelDriven;
@ParentPackage("jsoncrud")
@Results( {@Result(type="json", name = "json",params={"excludeProperties", ".*hibernateLazyInitializer"})})
public class B01cud01Action extends BaseActionSupport implements ModelDriven<District>{

	private static final long serialVersionUID = -6278068873837516131L;
	@Autowired
	private BaseOrgDistrictManager baseOrgDistrictManager;
	@Autowired
	private B01cud01Manager b01cud01Manager;
	@Autowired
	private BaseOrgDistrictDao baseOrgDistrictDao;
	@Autowired
	private DistrictDao districtDao;
	private List<BaseOrgDistrict> provinces;
	private List<BaseOrgDistrict> citys;
	private District district=new District();
	private List<ShareDistrict> shareDistricts;
	private String cityId;
	private String shareCityId;
	private String shareCityName;
	private String provinceId;
	private String dtPkCode;
	private String message;
	private Integer passstat;//cud结果代码
	

	public Integer getPassstat() {
		return passstat;
	}
	public List<ShareDistrict> getShareDistricts() {
		return shareDistricts;
	}
	public String getShareCityName() {
		return shareCityName;
	}
	public void setShareCityName(String shareCityName) {
		this.shareCityName = shareCityName;
	}
	public String getShareCityId() {
		return shareCityId;
	}
	public void setShareCityId(String shareCityId) {
		this.shareCityId = shareCityId;
	}
	public String getDtPkCode() {
		return dtPkCode;
	}
	public void setDtPkCode(String dtPkCode) {
		this.dtPkCode = dtPkCode;
	}
	public String getProvinceId() {
		return provinceId;
	}
	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}
	public List<BaseOrgDistrict> getCitys() {
		return citys;
	}
	public List<BaseOrgDistrict> getProvinces() {
		return provinces;
	}
	public String getCityId() {
		return cityId;
	}
	public void setCityId(String cityId) {
		this.cityId = cityId;
	}
	public String getMessage() {
		return message;
	}

	public District getModel() {
		return district;
	}
	public String edit() throws Exception {
		User user=getSessionUser();
		passstat=b01cud01Manager.validateRole(user, cityId);
		message=b01cud01Manager.validateMessage(passstat);
		if(passstat==1){
			if(dtPkCode!=null){
				district=districtDao.get(dtPkCode);
				if(district.getDtFlag().equals("1")){
					shareDistricts=b01cud01Manager.getFilterDistrict(district).getShareDistricts();
					provinces=baseOrgDistrictManager.getFilterProvince();
					citys=baseOrgDistrictManager.getFilterCitysByProvince(provinces.get(0).getDistrictCode(), provinces.get(0).getProvinceName());
				}
			}
		}
		return "json";
	}
	public String provinces() throws Exception {
		if(dtPkCode!=null){
			district=districtDao.get(dtPkCode);
			if(district.getDtFlag().equals("1")){
				shareDistricts=b01cud01Manager.getFilterDistrict(district).getShareDistricts();
			}
		}
		provinces=baseOrgDistrictManager.getFilterProvince();
		citys=baseOrgDistrictManager.getFilterCitysByProvince(provinces.get(0).getDistrictCode(), provinces.get(0).getProvinceName());
		return "json";
	}
	public String citys() throws Exception {
		BaseOrgDistrict d=baseOrgDistrictDao.get(provinceId);
		citys=baseOrgDistrictManager.getFilterCitysByProvince(provinceId, d.getProvinceName());
		return "json";
	}
	public String save() throws Exception {
		if(shareCityId.equals("")){
			district.setDtFlag("0");//不共享
		}else{
			district.setDtFlag("1");
			String[] sh=shareCityId.split(",");
			for(String s:sh){
				ShareDistrict sd=new ShareDistrict();
				sd.setDistrict(district);
				sd.setSdCityId(s);
				district.getShareDistricts().add(sd);
			}
		}
		districtDao.saveDistrict(district);
		message="<span style=color:red id=saveMessage>保存成功</span>";
		return "json";
	}
	
	public String delete() throws Exception {
		if(dtPkCode!=null && !dtPkCode.equals("")){
			User user=getSessionUser();
			district=districtDao.get(dtPkCode);
			passstat=b01cud01Manager.validateRole(user, district.getDtCityId());
			message=b01cud01Manager.validateMessage(passstat);
			if(passstat==1){
				districtDao.deleteDistrict(district);
				message="<span style=color:red id=saveMessage >删除成功</span>";
			}
		}
		return "json";
	}

}
