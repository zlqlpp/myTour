package com.cpst.emsadrdb.web.pmn;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.cpst.emsadrdb.dao.pmn.ResourceDao;
import com.cpst.emsadrdb.dao.pmn.RoleDao;
import com.cpst.emsadrdb.dao.pmn.RoleLevelDao;
import com.cpst.emsadrdb.entity.pmn.Resource;
import com.cpst.emsadrdb.entity.pmn.Role;
import com.cpst.emsadrdb.entity.pmn.RoleLevel;
import com.cpst.emsadrdb.service.pmn.RoleLevelManager;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
/**
 * 权限管理事务类
 * @author PengYuLei
 */
public class A02cud01Action extends ActionSupport implements ModelDriven<Role>{
	
	private static final long serialVersionUID = 1431959441285776837L;
	private Role role=new Role();
	@Autowired
	private ResourceDao resourceDao;
	private List<Resource> allResources;
	private List<RoleLevel> roleLevels;
	@Autowired
	private RoleLevelDao roleLevelDao;
	@Autowired
	private RoleLevelManager roleLevelManager;
	@Autowired
	private RoleDao roleDao;
	private String message;
	private Long rePkId;
	private String haveCheckedRePkIds;

	
	public String getHaveCheckedRePkIds() {
		return haveCheckedRePkIds;
	}
	public Long getRePkId() {
		return rePkId;
	}
	public void setRePkId(Long rePkId) {
		this.rePkId = rePkId;
	}
	public String getMessage() {
		return message;
	}
	public List<RoleLevel> getRoleLevels() {
		return roleLevels;
	}
	
	public List<Resource> getAllResources() {
		return allResources;
	}

	public Role getModel() {
		return role;
	}
	public String edit() throws Exception {
		if(rePkId!=null){
			role=roleDao.get(rePkId);
			List<Resource> rs=role.getResources();
			haveCheckedRePkIds="";
			for(int i=0;i<rs.size();i++){
				haveCheckedRePkIds+=","+rs.get(i).getRsPkId();
			}
			if(haveCheckedRePkIds.length()>0){
				haveCheckedRePkIds=haveCheckedRePkIds.substring(1);
			}
			
		}
		allResources=resourceDao.getAllOrderByPropertyName("rsName");
		roleLevels=roleLevelDao.getAllOrderByPropertyName("rulLevel");
		boolean b=roleLevelManager.validatedRoleLevels(roleLevels);
		if(!b){
			message="权限级别表已经被破坏，请联系管理员";
			return "message";
		}
		return "edit";
	}
	public String save() throws Exception {
		roleDao.saveRole(role);
		message="保存成功";
		return "message";
	}
	public String delete() throws Exception {
		roleDao.deleteRoleById(rePkId);
		message="删除成功";
		return "message";
	}

}
