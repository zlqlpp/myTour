package com.cpst.emsadrdb.web.pmn;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.cpst.emsadrdb.dao.pmn.ResourceDao;
import com.cpst.emsadrdb.entity.pmn.Resource;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 资源管理查询类
 * @author PengYuLei
 */
public class A01r01Action extends ActionSupport{

	private static final long serialVersionUID = -6480916007127285474L;
	@Autowired
	private ResourceDao resourceDao;
	private List<Resource> resources;
	
	
	public List<Resource> getResources() {
		return resources;
	}


	public String enter() throws Exception {
		resources=resourceDao.getAllOrderByPropertyName("rsName");
		return "view";
	}
}
