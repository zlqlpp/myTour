package com.cpst.emsadrdb.web.adr;

import java.util.List;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.cpst.core.web.struts2.BaseActionSupport;
import com.cpst.emsadrdb.dao.wh.DepartmentDao;
import com.cpst.emsadrdb.dao.wh.DistrictDao;
import com.cpst.emsadrdb.dao.wh.PostsegDao;
import com.cpst.emsadrdb.entity.wh.Department;
import com.cpst.emsadrdb.entity.wh.Postseg;
@ParentPackage("jsoncrud")
@Results( { @Result(type = "json", name = "json") })
public class C03r02Action extends BaseActionSupport{

	private static final long serialVersionUID = 4039053493968584807L;
	@Autowired
	private DepartmentDao departmentDao;
	@Autowired
	private PostsegDao postsegDao;
	private List<Department> departments;
	private List<Postseg> postsegs;
	private String districtId;
	private Long departmentId;
	
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

	public List<Department> getDepartments() {
		return departments;
	}

	public List<Postseg> getPostsegs() {
		return postsegs;
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
