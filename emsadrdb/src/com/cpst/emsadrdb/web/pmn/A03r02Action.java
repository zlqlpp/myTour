package com.cpst.emsadrdb.web.pmn;

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
@Results( {@Result(type="json", name = "json",params={"excludeProperties", ".*hibernateLazyInitializer"})})
public class A03r02Action extends ActionSupport{

	private static final long serialVersionUID = -3355206459806136986L;
	private String usProvinceOffice;
	private String usCityOffice;
	private List<BaseOrgDistrict> citys;
	@Autowired
	private BaseOrgDistrictManager baseOrgDistrictManager;
	@Autowired
	private BaseOrgDistrictDao baseOrgDistrictDao;
	private String cityId;
	@Autowired
	private DistrictDao districtDao;
	private List<District> districts;
	private String districtId;
	private List<Department> departments;
	@Autowired
	private DepartmentDao departmentDao;
	private Long departmentId;
	private List<Postseg> postsegs;
	@Autowired
	private PostsegDao postsegDao;
	
	public Long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}

	public List<Postseg> getPostsegs() {
		return postsegs;
	}

	public String getDistrictId() {
		return districtId;
	}

	public void setDistrictId(String districtId) {
		this.districtId = districtId;
	}

	public List<Department> getDepartments() {
		return departments;
	}

	public String getCityId() {
		return cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

	public List<District> getDistricts() {
		return districts;
	}

	public String getUsProvinceOffice() {
		return usProvinceOffice;
	}

	public void setUsProvinceOffice(String usProvinceOffice) {
		this.usProvinceOffice = usProvinceOffice;
	}

	public String getUsCityOffice() {
		return usCityOffice;
	}

	public void setUsCityOffice(String usCityOffice) {
		this.usCityOffice = usCityOffice;
	}

	public List<BaseOrgDistrict> getCitys() {
		return citys;
	}

	public String citys() throws Exception {
		BaseOrgDistrict d=baseOrgDistrictDao.get(usProvinceOffice);
		if(d!=null){
			citys=baseOrgDistrictManager.getFilterCitysByProvince(usProvinceOffice, d.getProvinceName());
		}
		if(baseOrgDistrictManager.isMunicipalities(d.getProvinceName())){//如果是直辖市
			districts=districtDao.getDistrictsByCity(usProvinceOffice);
		}
		return "json";
	}
	public String districts() throws Exception {
		districts=districtDao.getDistrictsByCity(cityId);
		return "json";
	}
	public String departments() throws Exception {
		departments=departmentDao.getDepartments(districtId);
		postsegs=postsegDao.getPostsegsByDistrictId(districtId);
		return "json";
	}
	public String postsegs() throws Exception {
		postsegs=postsegDao.getPostsegsByDepartmentId(departmentId);
		return "json";
	}

}
