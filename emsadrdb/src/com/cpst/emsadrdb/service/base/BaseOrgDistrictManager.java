package com.cpst.emsadrdb.service.base;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cpst.emsadrdb.dao.base.BaseOrgDistrictDao;
import com.cpst.emsadrdb.entity.base.BaseOrgDistrict;

@Repository
public class BaseOrgDistrictManager {
	@Autowired
	private BaseOrgDistrictDao baseOrgDistrictDao;

	public List<BaseOrgDistrict> getFilterProvince() {
		List<BaseOrgDistrict> orgDistricts = new ArrayList<BaseOrgDistrict>();
		for (BaseOrgDistrict o : baseOrgDistrictDao.getProvinces()) {
			if (!o.getDistrictCode().equals("810000")
					&& !o.getDistrictCode().equals("820000")) {
				orgDistricts.add(o);
			}
		}
		return orgDistricts;
	}
	
	public List<BaseOrgDistrict> getFilterCitysByProvince(String provinceCode,String provinceName) {
		List<BaseOrgDistrict> citys = new ArrayList<BaseOrgDistrict>();
		if(isMunicipalities(provinceName)){
			BaseOrgDistrict city=new BaseOrgDistrict();
			city.setDistrictCode(provinceCode);
			city.setCityName(provinceName);
			citys.add(city);
		}else{
			for (BaseOrgDistrict o : baseOrgDistrictDao.getCitysByProvince("2", provinceName)) {
				citys.add(o);
			}
		}
		return citys;
	}
	
	public boolean isMunicipalities(String name){
		name=name.trim();
		if(name.equals("北京市")||name.equals("上海市")||name.equals("天津市")||name.equals("重庆市")){
			return true;
		}else{
			return false;
		}
	}
	public boolean isMunicipalitiesByCode(String code){
		if(code.equals("110000")||code.equals("310000")||code.equals("120000")||code.equals("500000")){
			return true;
		}else{
			return false;
		}
	}
	

}
