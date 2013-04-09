package com.cpst.emsadrdb.web.adr;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.cpst.core.orm.Page;
import com.cpst.core.web.struts2.BaseActionSupport;
import com.cpst.emsadrdb.dao.adr.AdrStree11Dao;
import com.cpst.emsadrdb.dao.base.BaseOrgDistrictDao;
import com.cpst.emsadrdb.dao.wh.DepartmentDao;
import com.cpst.emsadrdb.dao.wh.DistrictDao;
import com.cpst.emsadrdb.dao.wh.PostsegDao;
import com.cpst.emsadrdb.entity.adr.AdrStreeBean;
import com.cpst.emsadrdb.entity.base.BaseOrgDistrict;
import com.cpst.emsadrdb.entity.pmn.User;
import com.cpst.emsadrdb.entity.wh.Department;
import com.cpst.emsadrdb.entity.wh.District;
import com.cpst.emsadrdb.entity.wh.Postseg;
import com.cpst.emsadrdb.service.base.BaseOrgDistrictManager;

public class C02r01Action extends BaseActionSupport{

	private static final long serialVersionUID = 1170836420855502794L;
	@Autowired
	private DistrictDao districtDao;
	@Autowired
	private PostsegDao postsegDao;
	@Autowired
	private DepartmentDao departmentDao;
	private List<Department> departments;//部集合
	private  Department department;//部
	@Autowired
	private BaseOrgDistrictDao baseOrgDistrictDao;
	@Autowired
	private BaseOrgDistrictManager baseOrgDistrictManager;
	private List<BaseOrgDistrict> citys;//市集合
	private BaseOrgDistrict province;//省
	private BaseOrgDistrict city;//市
	private List<District> districts;//区集合
	private District district;//区
	private List<Postseg> postsegs;//段集合
	private Postseg postseg;
	private String message;
	private String provinceId;
	private String cityId;
	private String districtId;
	private Long departmentId;
	private Long postsegId;
	private String adrName;
	private Page<AdrStreeBean> page=new Page<AdrStreeBean>(20);
	@Autowired
	private AdrStree11Dao adrStree11Dao;


	public String getAdrName() {
		return adrName;
	}

	public void setAdrName(String adrName) {
		this.adrName = adrName;
	}

	public Page<AdrStreeBean> getPage() {
		return page;
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

	

	public Long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}

	public Long getPostsegId() {
		return postsegId;
	}

	public void setPostsegId(Long postsegId) {
		this.postsegId = postsegId;
	}

	public Postseg getPostseg() {
		return postseg;
	}

	public List<Department> getDepartments() {
		return departments;
	}

	public Department getDepartment() {
		return department;
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

	public List<Postseg> getPostsegs() {
		return postsegs;
	}

	public String getMessage() {
		return message;
	}

	public String enter() throws Exception {
		User user=getSessionUser();//得到用户所在行政代码
		if(user.getRulLevel()==0 || user.getRulLevel()==2){//系统管理员或者是国家管理员
			province=baseOrgDistrictDao.get("110000");//默认是北京
			city=province;
			city.setCityName(city.getProvinceName());
			citys=new ArrayList<BaseOrgDistrict>();
			citys.add(city);
			districts=districtDao.getDistrictsByCity(city.getDistrictCode());
			if(districts==null || districts.isEmpty()){//没有投递部
				districts=new ArrayList<District>();
				departments=new ArrayList<Department>();
			}else{
				district=districts.get(0);
				departments=district.getDepartments();
				postsegs=district.getPostsegs();
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
			districts=districtDao.getDistrictsByCity(city.getDistrictCode());
			if(districts==null || districts.isEmpty()){//没有投递部
				districts=new ArrayList<District>();
			}else{
				district=districts.get(0);
				departments=district.getDepartments();
				postsegs=district.getPostsegs();
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
			districts=districtDao.getDistrictsByCity(city.getDistrictCode());
			if(districts==null || districts.isEmpty()){//没有投递部
				districts=new ArrayList<District>();
			}else{
				district=districts.get(0);
				departments=district.getDepartments();
				postsegs=district.getPostsegs();
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
			districts=districtDao.getDistrictsByCity(city.getDistrictCode());
			departments=district.getDepartments();
			postsegs=district.getPostsegs();
		}else if(user.getRulLevel()==20){//投递部级管理员
			department=departmentDao.get(user.getUsDepartmentOffice());
			district=districtDao.get(department.getDistrict().getDtPkCode());
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
			districts=districtDao.getDistrictsByCity(city.getDistrictCode());
			departments=district.getDepartments();
			postsegs=district.getPostsegs();
		}else if(user.getRulLevel()==25){//投递段级管理员
			postseg=postsegDao.get(user.getUsPostsegOffice());
			department=departmentDao.get(user.getUsDepartmentOffice());
			district=districtDao.get(postseg.getDistrict().getDtPkCode());
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
			districts=districtDao.getDistrictsByCity(city.getDistrictCode());
			departments=district.getDepartments();
			postsegs=district.getPostsegs();
		}else{
			message="只有地市级以上用户可以查询维护";
			return "message";
		}
		page=adrStree11Dao.getAdrStreeBeanByDistrictIdAndProvinceId(page, district.getDtPkCode(), province.getDistrictCode().substring(0, 2), adrName, departmentId, postsegId);
		return "view";
	}
	
	public String query() throws Exception {
		if(provinceId!=null && !provinceId.equals("")){//通过省代码查询
			province=baseOrgDistrictDao.get(provinceId);
			if(baseOrgDistrictManager.isMunicipalities(province.getProvinceName())){//如果是直辖市
				city=province;
				city.setCityName(city.getProvinceName());
				citys=new ArrayList<BaseOrgDistrict>();
				citys.add(city);
			}else{//如果是省
				citys=baseOrgDistrictDao.getCitysByProvince("2",province.getProvinceName());
				city=citys.get(0);
			}
			districts=districtDao.getDistrictsByCity(city.getDistrictCode());
			if(districts==null || districts.isEmpty()){//没有投递部
				districts=new ArrayList<District>();
			}else{
				district=districts.get(0);
				departments=district.getDepartments();
				postsegs=district.getPostsegs();
			}
		}
		if(cityId!=null && !cityId.equals("")){
			if(baseOrgDistrictManager.isMunicipalitiesByCode(cityId)){//如果是直辖市
				province=baseOrgDistrictDao.get(cityId);
				city=province;
				city.setCityName(city.getProvinceName());
				citys=new ArrayList<BaseOrgDistrict>();
				citys.add(city);
			}else{//如果是省下面的市
				city=baseOrgDistrictDao.get(cityId);
				province=baseOrgDistrictDao.getProvinceByProvinceName(city.getProvinceName());
				citys=baseOrgDistrictDao.getCitysByProvince("2",province.getProvinceName());
			}
			districts=districtDao.getDistrictsByCity(city.getDistrictCode());
			if(districts==null || districts.isEmpty()){//没有投递部
				districts=new ArrayList<District>();
			}else{
				district=districts.get(0);
				departments=district.getDepartments();
				postsegs=district.getPostsegs();
			}
		}
		if(districtId!=null && !districtId.equals("")){
			district=districtDao.get(districtId);
			city=baseOrgDistrictDao.get(district.getDtCityId());
			if(baseOrgDistrictManager.isMunicipalitiesByCode(city.getDistrictCode())){//如果是直辖市
				city.setCityName(city.getProvinceName());
				province=city;
				citys=new ArrayList<BaseOrgDistrict>();
				citys.add(city);
			}else{//如果是省下面的市
				province=baseOrgDistrictDao.getProvinceByProvinceName(city.getProvinceName());
				citys=baseOrgDistrictDao.getCitysByProvince("2",province.getProvinceName());
			}
			districts=districtDao.getDistrictsByCity(city.getDistrictCode());
			departments=district.getDepartments();
			postsegs=district.getPostsegs();
		}
		if(departmentId!=null){
			department=departmentDao.get(departmentId);
			district=districtDao.get(department.getDistrict().getDtPkCode());
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
			districts=districtDao.getDistrictsByCity(city.getDistrictCode());
			departments=district.getDepartments();
			postsegs=district.getPostsegs();
		}
		if(postsegId!=null){
			postseg=postsegDao.get(postsegId);
			if(postseg.getDmPkCode()!=null){
				department=departmentDao.get(postseg.getDmPkCode());
			}else{
				department=new Department();
			}
			district=districtDao.get(postseg.getDistrict().getDtPkCode());
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
			districts=districtDao.getDistrictsByCity(city.getDistrictCode());
			departments=district.getDepartments();
			postsegs=district.getPostsegs();
		}
		page=adrStree11Dao.getAdrStreeBeanByDistrictIdAndProvinceId(page, districtId, province.getDistrictCode().substring(0, 2), adrName, departmentId, postsegId);
		return "view";
	}
}
