package com.cpst.emsadrdb.web.wh;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.cpst.emsadrdb.dao.base.BaseOrgDistrictDao;
import com.cpst.emsadrdb.dao.wh.DistrictDao;
import com.cpst.emsadrdb.entity.base.BaseOrgDistrict;
import com.cpst.emsadrdb.entity.wh.District;
import com.cpst.emsadrdb.service.base.BaseOrgDistrictManager;
import com.cpst.emsadrdb.service.wh.B01r01Manager;
import com.opensymphony.xwork2.ActionSupport;

@ParentPackage("jsoncrud")
@Results( { @Result(type = "json", name = "json") })
public class B01r02Action extends ActionSupport{

	private static final long serialVersionUID = -1676397541251800098L;
	@Autowired
	private BaseOrgDistrictDao baseOrgDistrictDao;
	@Autowired
	private BaseOrgDistrictManager baseOrgDistrictManager;
	private String provinceId;
	private String cityId;
	private BaseOrgDistrict city;
	private List<BaseOrgDistrict> citys;
	@Autowired
	private DistrictDao districtDao;
	@Autowired
	private B01r01Manager b01r01Manager;
	private List<District> districts;//区集合
	
	
	public String getCityId() {
		return cityId; 
	}
	public void setCityId(String cityId) {
		this.cityId = cityId;
	}
	public List<District> getDistricts() {
		return districts;
	}
	public List<BaseOrgDistrict> getCitys() {
		return citys;
	}
	public BaseOrgDistrict getCity() {
		return city;
	}
	public String getProvinceId() {
		return provinceId;
	}
	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}
	/**
	 * 省_区查询
	 */
	public String province() throws Exception { 
		BaseOrgDistrict province=baseOrgDistrictDao.get(provinceId);//省
		if(baseOrgDistrictManager.isMunicipalities(province.getProvinceName())){//如果是直辖市
			city=province;
			city.setCityName(city.getProvinceName());
			citys=new ArrayList<BaseOrgDistrict>();
			citys.add(city);
		}else{//如果是省
			citys=baseOrgDistrictDao.getCitysByProvince("2", province.getProvinceName());
			city=citys.get(0);
		}
		districts=districtDao.getDistrictsAndShareDistrictsByCity(city.getDistrictCode());
		districts=b01r01Manager.getFilterDistrictsAndShareDistrict(districts);
		return "json";
	}
	/**
	 * 市_区查询
	 */
	public String city() throws Exception { 
		city=baseOrgDistrictDao.get(cityId);//市
		if(baseOrgDistrictManager.isMunicipalities(city.getProvinceName())){
			city.setCityName(city.getProvinceName());
		}
		districts=districtDao.getDistrictsAndShareDistrictsByCity(city.getDistrictCode());
		districts=b01r01Manager.getFilterDistrictsAndShareDistrict(districts);
		return "json";
	}

}
