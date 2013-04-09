package com.cpst.emsadrdb.web.wh;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.cpst.emsadrdb.dao.base.BaseOrgDistrictDao;
import com.cpst.emsadrdb.dao.wh.DepartmentDao;
import com.cpst.emsadrdb.dao.wh.DistrictDao;
import com.cpst.emsadrdb.dao.wh.PostsegDao;
import com.cpst.emsadrdb.entity.base.BaseOrgDistrict;
import com.cpst.emsadrdb.entity.wh.Department;
import com.cpst.emsadrdb.entity.wh.District;
import com.cpst.emsadrdb.entity.wh.Postseg;
import com.cpst.emsadrdb.service.base.BaseOrgDistrictManager;
import com.opensymphony.xwork2.ActionSupport;

@ParentPackage("jsoncrud")
@Results( { @Result(type = "json", name = "json") })
public class B03r02Action  extends ActionSupport{

	private static final long serialVersionUID = -3736707186609564966L;
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
	private PostsegDao postsegDao;
	private List<District> districts;//区集合
	private List<Department> departments;//段集合
	private Long departmentId;
	private District district;//区
	private String districtId;//区ID
	private List<Postseg> postsegs;
	

	public Long getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}
	public List<Postseg> getPostsegs() {
		return postsegs;
	}
	public String getProvinceId() {
		return provinceId;
	}
	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}
	public String getCityId() {
		return cityId;
	}
	public void setCityId(String cityId) {
		this.cityId = cityId;
	}
	public String getDistrictId() {
		return districtId;
	}
	public void setDistrictId(String districtId) {
		this.districtId = districtId;
	}
	public BaseOrgDistrict getCity() {
		return city;
	}
	public List<BaseOrgDistrict> getCitys() {
		return citys;
	}
	public List<District> getDistricts() {
		return districts;
	}
	public List<Department> getDepartments() {
		return departments;
	}
	public District getDistrict() {
		return district;
	}
	/**
	 * 省_段查询
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
		districts=districtDao.getDistrictsByCity(city.getDistrictCode());
		if(districts==null || districts.isEmpty()){//没有投递区
			districts=new ArrayList<District>();
			departments=new ArrayList<Department>();
			district=new District();
			postsegs=new ArrayList<Postseg>();
		}else{
			district=districts.get(0);
			departments=district.getDepartments();
			postsegs=district.getPostsegs();
		}
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
		districts=districtDao.getDistrictsByCity(city.getDistrictCode());
		if(districts==null || districts.isEmpty()){//没有投递区
			districts=new ArrayList<District>();
			departments=new ArrayList<Department>();
			district=new District();
			postsegs=new ArrayList<Postseg>();
		}else{
			district=districts.get(0);
			departments=district.getDepartments();
			postsegs=district.getPostsegs();
		}
		return "json";
	}
	/**
	 * 区_段查询
	 */
	public String district() throws Exception { 
		district=districtDao.get(districtId);
		departments=district.getDepartments();
		postsegs=district.getPostsegs();
		return "json";
	}
	/**
	 * 部_段查询
	 */
	public String postseg() throws Exception { 
		postsegs=postsegDao.getPostsegsByDepartmentId(departmentId);
		return "json";
	}
	
	public String citys() throws Exception {
		district=districtDao.get(districtId);
		departments=district.getDepartments();
		return "json";
	}

}
