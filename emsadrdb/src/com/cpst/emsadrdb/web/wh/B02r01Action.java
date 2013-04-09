package com.cpst.emsadrdb.web.wh;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.cpst.core.web.struts2.BaseActionSupport;
import com.cpst.emsadrdb.dao.base.BaseOrgDistrictDao;
import com.cpst.emsadrdb.dao.wh.DistrictDao;
import com.cpst.emsadrdb.entity.base.BaseOrgDistrict;
import com.cpst.emsadrdb.entity.pmn.User;
import com.cpst.emsadrdb.entity.wh.Department;
import com.cpst.emsadrdb.entity.wh.District;
import com.cpst.emsadrdb.service.base.BaseOrgDistrictManager;

public class B02r01Action extends BaseActionSupport{

	private static final long serialVersionUID = 7429825311038691926L;
	@Autowired
	private DistrictDao districtDao;
	private List<Department> departments;//部集合
	@Autowired
	private BaseOrgDistrictDao baseOrgDistrictDao;
	@Autowired
	private BaseOrgDistrictManager baseOrgDistrictManager;
	private List<BaseOrgDistrict> citys;//市集合
	private BaseOrgDistrict province;//省Id
	private BaseOrgDistrict city;//市
	private List<District> districts;//区集合
	private District district;//区
	private String message;
	
	public List<Department> getDepartments() {
		return departments; 
	}

	public List<BaseOrgDistrict> getCitys() {
		return citys;
	}

	public BaseOrgDistrict getProvince() {
		return province;
	}

	public BaseOrgDistrict getCity() {
		return city;
	}

	public List<District> getDistricts() {
		return districts;
	}

	public District getDistrict() {
		return district;
	}

	public String getMessage() {
		return message;
	}

	public String execute() throws Exception {
		User user=getSessionUser();//得到用户所在行政代码
		if(user.getRulLevel()==0 || user.getRulLevel()==2){//系统管理员或者是国家管理员
			province=baseOrgDistrictDao.get("110000");//默认是北京
			city=province;
			city.setCityName(city.getProvinceName());
			citys=new ArrayList<BaseOrgDistrict>();
			citys.add(city);
			districts=districtDao.getDistrictsByCityAndDepartment(city.getDistrictCode(),"1");
			if(districts!=null && !districts.isEmpty()){
				district=districts.get(0);
				departments=district.getDepartments();
			}
		}else if(user.getRulLevel()==5){//省级管理员
			province=baseOrgDistrictDao.get(user.getUsProvinceOffice());
			if(baseOrgDistrictManager.isMunicipalities(province.getProvinceName())){//如果是直辖市
				city=province;
				city.setCityName(city.getProvinceName());
				citys=new ArrayList<BaseOrgDistrict>();
				citys.add(city);
			}else{//如果是省
				citys=baseOrgDistrictDao.getCitysByProvince("2",province.getProvinceName());
				city=citys.get(0);
			}
			districts=districtDao.getDistrictsByCityAndDepartment(city.getDistrictCode(),"1");
			if(districts!=null && !districts.isEmpty()){
				district=districts.get(0);
				departments=district.getDepartments();
			}
		}else if(user.getRulLevel()==10){//地市级管理员
			city=baseOrgDistrictDao.get(user.getUsCityOffice());
			if(baseOrgDistrictManager.isMunicipalities(city.getProvinceName())){//如果是直辖市
				province=city;
				city.setCityName(city.getProvinceName());
				citys=new ArrayList<BaseOrgDistrict>();
				citys.add(city);
			}else{
				province=baseOrgDistrictDao.getProvinceByProvinceName(city.getProvinceName());
				citys=baseOrgDistrictDao.getCitysByProvince("2",province.getProvinceName());
			}
			districts=districtDao.getDistrictsByCityAndDepartment(city.getDistrictCode(),"1");
			if(districts!=null && !districts.isEmpty()){
				district=districts.get(0);
				departments=district.getDepartments();
			}

		}else if(user.getRulLevel()==15){//投递区级管理员
			district=districtDao.get(user.getUsDistrictOffice());
			city=baseOrgDistrictDao.get(district.getDtCityId());
			if(baseOrgDistrictManager.isMunicipalities(city.getProvinceName())){//如果是直辖市
				province=city;
				city.setCityName(city.getProvinceName());
				citys=new ArrayList<BaseOrgDistrict>();
				citys.add(city);
			}else{
				province=baseOrgDistrictDao.getProvinceByProvinceName(city.getProvinceName());
				citys=baseOrgDistrictDao.getCitysByProvince("2",province.getProvinceName());
			}
			districts=districtDao.getDistrictsByCityAndDepartment(city.getDistrictCode(),"1");
			if(districts!=null && !districts.isEmpty()){
				if(district.getDtHaveDepartment().equals("1")){
					departments=district.getDepartments();
				}else{
					district=districts.get(0);
					departments=district.getDepartments();
				}
			}
		}else{
			message="只有地市级以上用户可以查询维护";
			return "message";
		}
		return "view";
	}

}
