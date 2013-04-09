package com.cpst.emsadrdb.service.pmn;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cpst.emsadrdb.dao.pmn.RoleDao;
import com.cpst.emsadrdb.entity.pmn.Role;
import com.cpst.emsadrdb.entity.pmn.User;
import com.cpst.emsadrdb.service.base.BaseOrgDistrictManager;
/**
 * 维护用户时，直辖市只有省级操作员，没有市级操作员
 * 直辖市用户所在的省id和市id相同
 * @author pengyulei
 *
 */
@Repository
public class A03cud01Manager {
	@Autowired
	private RoleDao roleDao;
	@Autowired
	private BaseOrgDistrictManager baseOrgDistrictManager;
	public int validateRole(User user,User curUser){
		int passstat=1;//验证通过
		if(curUser.getRulLevel()==0 || curUser.getRulLevel()==2){//系统管理员和国家管理员
			if(user.getRulLevel()==10){
				if(baseOrgDistrictManager.isMunicipalitiesByCode(user.getUsCityOffice())){
					passstat=7;//直辖市没有市级用户，只有省级用户
					return passstat;
				}	
			}
		}else if(curUser.getRulLevel()==5){//省管理员
			if(user.getUsCityOffice()==null){
				passstat=2;//错误代码：不能维护本省之外的用户
				return passstat;
			}
			if(!(user.getUsProvinceOffice()).equals(curUser.getUsProvinceOffice())){
				passstat=2;
				return passstat;
			}
		}else if(curUser.getRulLevel()==10){//地市级管理员
			if(user.getUsDistrictOffice()==null){
				passstat=3;//错误代码：不能维护本市之外的用户
				return passstat;
			}
			if(!user.getUsCityOffice().equals(curUser.getUsCityOffice())){
				passstat=3;//错误代码：不能维护本市之外的用户
				return passstat;
			}
		}else if(curUser.getRulLevel()==15){//区级管理员
			if(user.getUsDepartmentOffice()==null && user.getUsPostsegOffice()==null){
				passstat=4;//错误代码：不能维护本区之外的用户
				return passstat;
			}
			if(!user.getUsDistrictOffice().equals(curUser.getUsDistrictOffice())){
				passstat=4;//错误代码：不能维护本区之外的用户
				return passstat;
			}
		}else if(curUser.getRulLevel()==20){//部级管理员
			if(user.getUsPostsegOffice()==null){
				passstat=5;//错误代码：不能维护本部之外的用户
				return passstat;
			}
			if(user.getUsDepartmentOffice()!=curUser.getUsDepartmentOffice()){
				passstat=5;//错误代码：不能维护本部之外的用户
				return passstat;
			}
		}else {
			passstat=9;//错误代码：其他错误
			return passstat;
		}
		List<Role> roles=user.getRoles();
		String ids="";
		for(Role r:roles){
			ids+=","+r.getRePkId();
		}
		if(ids.equals("")){
			passstat=6;
			return passstat;
		}else{
			List<Role> rs=roleDao.getRolesIds(ids.substring(1));
			for(Role t: rs){
				if(t.getRulLevel()<user.getRulLevel()){
					passstat=6;
					break;
				}
			}
		}
		return passstat;
	}
	
	public String validateMessage(int passstat){
		String message="";
		if(passstat==1){
			message="通过验证";
		}else if(passstat==2){
			message="添加或者修改失败！不能维护本省之外的用户";
		}else if(passstat==3){
			message="添加或者修改失败！不能维护本市之外的用户";
		}else if(passstat==4){
			message="添加或者修改失败！不能维护本区之外的用户";
		}else if(passstat==5){
			message="添加或者修改失败！不能维护本部之外的用户";
		}else if(passstat==6){
			message="添加或者修改失败！不能给该用户高于他级别的权限";
		}else if(passstat==7){
			message="添加或者修改失败！直辖市没有市级用户，只有省级用户";
		}else{
			message="添加或者修改失败！其他错误";
		}
		return message;
	}
	
	public User confirmUser(User user){
		user.setUsLoginId(user.getUsLoginId().trim());
		if(user.getRulLevel()==2){//国家级管理员
			user.setUsProvinceOffice(null);
			user.setUsCityOffice(null);
			user.setUsDistrictOffice(null);
			user.setUsDepartmentOffice(null);
			user.setUsPostsegOffice(null);
		}else if(user.getRulLevel()==5){//省级管理员
			user.setUsCityOffice(null);
			if(user.getUsProvinceOffice()!=null){
				if(baseOrgDistrictManager.isMunicipalitiesByCode(user.getUsProvinceOffice())){//如果是直辖市
					user.setUsCityOffice(user.getUsProvinceOffice());
				}
			}
			user.setUsDistrictOffice(null);
			user.setUsDepartmentOffice(null);
			user.setUsPostsegOffice(null);
		}else if(user.getRulLevel()==10){//市级管理员
			user.setUsDistrictOffice(null);
			user.setUsDepartmentOffice(null);
			user.setUsPostsegOffice(null);
		}else if(user.getRulLevel()==15){//区级管理员
			user.setUsDepartmentOffice(null);
			user.setUsPostsegOffice(null);
		}else if(user.getRulLevel()==20){//部级管理员
			user.setUsPostsegOffice(null);
		}else{
			
		}
		return user;
	}

}
