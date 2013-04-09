package com.cpst.emsadrdb.web.pmn;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.cpst.core.orm.Page;
import com.cpst.core.web.struts2.BaseActionSupport;
import com.cpst.emsadrdb.dao.base.BaseOrgDistrictDao;
import com.cpst.emsadrdb.dao.pmn.RoleLevelDao;
import com.cpst.emsadrdb.dao.pmn.UserDao;
import com.cpst.emsadrdb.dao.wh.DepartmentDao;
import com.cpst.emsadrdb.dao.wh.DistrictDao;
import com.cpst.emsadrdb.dao.wh.PostsegDao;
import com.cpst.emsadrdb.entity.base.BaseOrgDistrict;
import com.cpst.emsadrdb.entity.pmn.RoleLevel;
import com.cpst.emsadrdb.entity.pmn.User;
import com.cpst.emsadrdb.entity.wh.Department;
import com.cpst.emsadrdb.entity.wh.District;
import com.cpst.emsadrdb.entity.wh.Postseg;
import com.cpst.emsadrdb.service.base.BaseOrgDistrictManager;
import com.cpst.emsadrdb.service.pmn.A03r01Manager;
import com.opensymphony.xwork2.ModelDriven;

public class A03r01Action extends BaseActionSupport implements ModelDriven<User>{

	private static final long serialVersionUID = 7248749076039247325L;
	private User user=new User();
	@Autowired
	private UserDao userDao;
	private Page<User> page=new Page<User>(20);
	@Autowired
	private RoleLevelDao roleLevelDao;
	@Autowired
	private BaseOrgDistrictManager baseOrgDistrictManager;
	@Autowired
	private A03r01Manager a03r01Manager;
	@Autowired
	private BaseOrgDistrictDao baseOrgDistrictDao;
	private List<BaseOrgDistrict> provinces;//省列表
	private List<BaseOrgDistrict> citys=new ArrayList<BaseOrgDistrict>();
	private List<District> districts=new ArrayList<District>();
	private List<Department> departments=new ArrayList<Department>();
	private List<Postseg> postsegs=new ArrayList<Postseg>();
	@Autowired
	private DistrictDao districtDao;
	@Autowired
	private DepartmentDao departmentDao;
	@Autowired
	private PostsegDao postsegDao;
	private List<RoleLevel> levelRoles;

	public List<Department> getDepartments() {
		return departments;
	}
	public List<Postseg> getPostsegs() {
		return postsegs;
	}

	public List<RoleLevel> getLevelRoles() {
		return levelRoles;
	}
	public Page<User> getPage() {
		return page;
	}
	
	public List<BaseOrgDistrict> getProvinces() {
		return provinces;
	}
	
	public List<BaseOrgDistrict> getCitys() {
		return citys;
	}
	
	public List<District> getDistricts() {
		return districts;
	}
	

	public User getModel() {
		return user;
	}
	public String enter() throws Exception {
		User curuser=getSessionUser();
		Short rulLevel=curuser.getRulLevel();
		provinces=baseOrgDistrictManager.getFilterProvince();
		Short rl=0;
		levelRoles=roleLevelDao.getRoleLevelByRulLevel(rl, ">");
		Short managerLevel=a03r01Manager.managerRoleLevel(rulLevel,false);
		String provinceId="";
		String cityId="";
		String districtId="";
		Long departmentId=null;
		if(rulLevel==0 || rulLevel==2){//系统或者国家级管理员
			
		}else if(rulLevel==5){//省级
			provinceId=curuser.getUsProvinceOffice();
			BaseOrgDistrict d=baseOrgDistrictDao.get(provinceId);
			if(baseOrgDistrictManager.isMunicipalities(d.getProvinceName())){//如果是直辖市
				BaseOrgDistrict city=d;
				city.setCityName(d.getProvinceName());
				citys.add(city);
				districts=districtDao.getDistrictsByCity(provinceId);
				managerLevel=a03r01Manager.managerRoleLevel(rulLevel,true);
			}else{
				citys=baseOrgDistrictDao.getCitysByProvince("2", d.getProvinceName());
			}
		}else if(rulLevel==10){//市级
			cityId=curuser.getUsCityOffice();
			provinceId=curuser.getUsProvinceOffice();
			BaseOrgDistrict d=baseOrgDistrictDao.get(provinceId);
			citys=baseOrgDistrictDao.getCitysByProvince("2", d.getProvinceName());
			districts=districtDao.getDistrictsByCity(cityId);
		}else if(rulLevel==15){//区级
			districtId=curuser.getUsDistrictOffice();
			cityId=curuser.getUsCityOffice();
			provinceId=curuser.getUsProvinceOffice();
			BaseOrgDistrict d=baseOrgDistrictDao.get(provinceId);
			if(baseOrgDistrictManager.isMunicipalities(d.getProvinceName())){//如果是直辖市
				BaseOrgDistrict city=d;
				city.setCityName(d.getProvinceName());
				citys.add(city);
			}else{
				citys=baseOrgDistrictDao.getCitysByProvince("2", d.getProvinceName());
			}
			districts=districtDao.getDistrictsByCity(cityId);
			departments=departmentDao.getDepartments(districtId);
			postsegs=postsegDao.getPostsegsByDistrictId(districtId);
		}else if(rulLevel==15){//部级
			departmentId=curuser.getUsDepartmentOffice();
			districtId=curuser.getUsDistrictOffice();
			cityId=curuser.getUsCityOffice();
			provinceId=curuser.getUsProvinceOffice();
			BaseOrgDistrict d=baseOrgDistrictDao.get(provinceId);
			if(baseOrgDistrictManager.isMunicipalities(d.getProvinceName())){//如果是直辖市
				BaseOrgDistrict city=d;
				city.setCityName(d.getProvinceName());
				citys.add(city);
			}else{
				citys=baseOrgDistrictDao.getCitysByProvince("2", d.getProvinceName());
			}
			districts=districtDao.getDistrictsByCity(cityId);
			departments=departmentDao.getDepartments(districtId);
			postsegs=postsegDao.getPostsegsByDepartmentId(departmentId);
		}else{
			
		}
		user.setRulLevel(rulLevel);
		user.setUsCountryOffice("000000");
		user.setUsProvinceOffice(provinceId);
		user.setUsCityOffice(cityId);
		user.setUsDistrictOffice(districtId);
		user.setUsDepartmentOffice(departmentId);
		user.setRulLevel(managerLevel);
		page=userDao.getPageUsers(user, page);
		a03r01Manager.transUserList(page.getResult());
		return "view";
	}
	public String query() throws Exception {
		//if(user.getUsProvinceOffice()!=null && !user.getUsProvinceOffice().equals("")){
			provinces=baseOrgDistrictManager.getFilterProvince();
		//}
		if(user.getUsCityOffice()!=null && !user.getUsCityOffice().equals("")){
			BaseOrgDistrict d=baseOrgDistrictDao.get(user.getUsCityOffice());
			if(baseOrgDistrictManager.isMunicipalitiesByCode(d.getDistrictCode())){//如果是直辖市
				BaseOrgDistrict city=d;
				city.setCityName(d.getProvinceName());
				citys.add(city);
			}else{
				citys=baseOrgDistrictDao.getCitysByProvince("2", d.getProvinceName());
			}
			districts=districtDao.getDistrictsByCity(d.getDistrictCode());
		}
		if(user.getUsDistrictOffice()!=null && !user.getUsDistrictOffice().equals("")){
			districts=districtDao.getDistrictsByCity(user.getUsCityOffice());
			departments=departmentDao.getDepartments(user.getUsDistrictOffice());
			postsegs=postsegDao.getPostsegsByDistrictId(user.getUsDistrictOffice());
		}
		if(user.getUsDepartmentOffice()!=null){
			departments=departmentDao.getDepartments(user.getUsDistrictOffice());
			postsegs=postsegDao.getPostsegsByDepartmentId(user.getUsDepartmentOffice());
		}
		if(user.getUsPostsegOffice()!=null){
			if(user.getUsDepartmentOffice()!=null){
				postsegs=postsegDao.getPostsegsByDepartmentId(user.getUsDepartmentOffice());
			}else{
				postsegs=postsegDao.getPostsegsByDistrictId(user.getUsDistrictOffice());
			}
		}
		Short rl=0;
		levelRoles=roleLevelDao.getRoleLevelByRulLevel(rl, ">");
		page=userDao.getPageUsers(user, page);
		a03r01Manager.transUserList(page.getResult());
		return "view";
	}

}
