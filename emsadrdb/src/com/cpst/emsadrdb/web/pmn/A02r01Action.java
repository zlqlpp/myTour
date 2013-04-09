package com.cpst.emsadrdb.web.pmn;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.cpst.emsadrdb.dao.pmn.RoleDao;
import com.cpst.emsadrdb.entity.pmn.Role;
import com.opensymphony.xwork2.ActionSupport;
/**
 * 权限管理查询类
 * @author PengYuLei
 */
public class A02r01Action extends ActionSupport{

	private static final long serialVersionUID = 6416879795245168200L;
	@Autowired
	private RoleDao roleDao;
	private List<Role> roles;

	public List<Role> getRoles() {
		return roles;
	}

	public String enter() throws Exception {
		roles=roleDao.getAllOrderByPropertyName("reName");
		return "view";
	}

}
