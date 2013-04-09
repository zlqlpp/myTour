package com.cpst.emsadrdb.web.pmn;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.cpst.core.web.struts2.BaseActionSupport;
import com.cpst.emsadrdb.dao.base.BaseOrgDistrictDao;
import com.cpst.emsadrdb.dao.pmn.RoleDao;
import com.cpst.emsadrdb.dao.pmn.RoleLevelDao;
import com.cpst.emsadrdb.dao.pmn.UserDao;
import com.cpst.emsadrdb.dao.wh.DepartmentDao;
import com.cpst.emsadrdb.dao.wh.DistrictDao;
import com.cpst.emsadrdb.dao.wh.PostsegDao;
import com.cpst.emsadrdb.entity.base.BaseOrgDistrict;
import com.cpst.emsadrdb.entity.pmn.Role;
import com.cpst.emsadrdb.entity.pmn.RoleLevel;
import com.cpst.emsadrdb.entity.pmn.User;
import com.cpst.emsadrdb.entity.wh.Department;
import com.cpst.emsadrdb.entity.wh.District;
import com.cpst.emsadrdb.entity.wh.Postseg;
import com.cpst.emsadrdb.service.base.BaseOrgDistrictManager;
import com.cpst.emsadrdb.service.pmn.A03cud01Manager;
import com.opensymphony.xwork2.ModelDriven;

public class A03cud01Action extends BaseActionSupport implements ModelDriven<User>{

	private static final long serialVersionUID = -4033271013526027700L;
	private User user=new User();
	@Autowired
	private UserDao userDao;
	@Autowired
	private RoleDao roleDao;
	private Long usPkId;
	private String haveCheckedrePkIds;
	private List<Role> levelRoles=new ArrayList<Role>();
	private List<RoleLevel> roleLevels;
	@Autowired
	private RoleLevelDao roleLevelDao;
	private String message;
	private List<BaseOrgDistrict> provinces;//省列表
	private List<BaseOrgDistrict> citys=new ArrayList<BaseOrgDistrict>();
	private List<District> districts=new ArrayList<District>();
	private List<Department> departments=new ArrayList<Department>();
	private List<Postseg> postsegs=new ArrayList<Postseg>();
	@Autowired
	private BaseOrgDistrictManager baseOrgDistrictManager;
	@Autowired
	private BaseOrgDistrictDao baseOrgDistrictDao;
	@Autowired
	private DistrictDao districtDao;
	@Autowired
	private DepartmentDao departmentDao;
	@Autowired
	private PostsegDao postsegDao;
	private String rulLevelStr;
	@Autowired
	private A03cud01Manager a03cud01Manager;
	private int passstat;
	

	public String getHaveCheckedrePkIds() {
		return haveCheckedrePkIds;
	}
	public int getPassstat() {
		return passstat;
	}
	public String getRulLevelStr() {
		return rulLevelStr;
	}
	public String getMessage() {
		return message;
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
	public List<Department> getDepartments() {
		return departments;
	}
	public List<Postseg> getPostsegs() {
		return postsegs;
	}

	public List<RoleLevel> getRoleLevels() {
		return roleLevels;
	}
	public List<Role> getLevelRoles() {
		return levelRoles;
	}
	public Long getUsPkId() {
		return usPkId;
	}
	public void setUsPkId(Long usPkId) {
		this.usPkId = usPkId;
	}

	public User getModel() {
		return user;
	}
	public String edit() throws Exception {
		User curuser=getSessionUser();
		Short rulLevel=curuser.getRulLevel();
		provinces=baseOrgDistrictManager.getFilterProvince();
		levelRoles=roleDao.getRolesByRulLevel(rulLevel, ">");
		roleLevels=roleLevelDao.getRoleLevelByRulLevel(rulLevel, ">");
		if(usPkId!=null){//修改
			user=userDao.get(usPkId);
			List<Role> roles=user.getRoles();
			haveCheckedrePkIds="";
			for(int i=0;i<roles.size();i++){
				haveCheckedrePkIds+=","+roles.get(i).getRePkId();
			}
			if(haveCheckedrePkIds.length()>0){
				haveCheckedrePkIds=haveCheckedrePkIds.substring(1);
			}
			if(user.getUsCityOffice()!=null){
				BaseOrgDistrict d=baseOrgDistrictDao.get(user.getUsProvinceOffice());
				BaseOrgDistrict city=d;
				if(baseOrgDistrictManager.isMunicipalitiesByCode(user.getUsProvinceOffice())){//如果是直辖市
					city.setCityName(d.getProvinceName());
					citys.add(city);
					districts=districtDao.getDistrictsByCity(user.getUsProvinceOffice());
				}else{
					citys=baseOrgDistrictDao.getCitysByProvince("2", d.getProvinceName());
				}
			}
			if(user.getUsDistrictOffice()!=null){
				districts=districtDao.getDistrictsByCity(user.getUsCityOffice());
				departments=departmentDao.getDepartments(user.getUsDistrictOffice());
				postsegs=postsegDao.getPostsegsByDistrictId(user.getUsDistrictOffice());
			}
			if(user.getUsDepartmentOffice()!=null){
				departments=departmentDao.getDepartments(user.getUsDistrictOffice());
				postsegs=postsegDao.getPostsegsByDistrictId(user.getUsDistrictOffice());
			}
			if(user.getUsPostsegOffice()!=null){
				postsegs=postsegDao.getPostsegsByDistrictId(user.getUsDistrictOffice());
			}
			
		}else{//添加
			//Short managerLevel=a03r01Manager.managerRoleLevel(rulLevel,false);
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
					roleLevels.remove(0);
					levelRoles.remove(0);
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
			rulLevelStr=rulLevel.toString();
			user.setRulLevel(rulLevel);
			user.setUsCountryOffice("000000");
			user.setUsProvinceOffice(provinceId);
			user.setUsCityOffice(cityId);
			user.setUsDistrictOffice(districtId);
			user.setUsDepartmentOffice(departmentId);
			user.setRulLevel(rulLevel);
		}
		
		return "edit";
	}
	public String save() throws Exception {
		User curuser=getSessionUser();
		passstat=a03cud01Manager.validateRole(user,curuser);
		message=a03cud01Manager.validateMessage(passstat);
		a03cud01Manager.confirmUser(user);
		if(passstat==1){
			if(user.getUsPkId()==null){
				user.setUsPasswd("123456");
				user.setUsStatus("1");
				User u=userDao.getUserByLoginId(user.getUsLoginId());
				if(u!=null){
					passstat=2;
					message="添加失败！用户名已经存在，请选择其他用户名";
					return "message";
				}
				userDao.saveUser(user);
			}else{
				User user01=userDao.get(user.getUsPkId());
				user01.setUsName(user.getUsName());
				user01.setUsMobile(user.getUsMobile());
				user01.setUsPhone(user.getUsPhone());
				user01.setRoles(user.getRoles());
				user01.setRulLevel(user.getRulLevel());
				userDao.saveUser(user01);
			}
			
			message="添加或者修改成功";
		}
		return "message";
	}
	public String delete() throws Exception {
		User user=userDao.get(usPkId);
		User curuser=getSessionUser();
		passstat=a03cud01Manager.validateRole(user,curuser);
		message=a03cud01Manager.validateMessage(passstat);
		if(passstat==1){
			if(user!=null){
				userDao.deleteUser(user);
				message="删除成功";
			}else{
				message="用户不存在";
			}
		}
		return "message";
	}

}
