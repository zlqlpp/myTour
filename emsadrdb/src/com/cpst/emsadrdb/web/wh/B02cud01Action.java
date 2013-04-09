package com.cpst.emsadrdb.web.wh;

import java.util.List;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.cpst.core.web.struts2.BaseActionSupport;
import com.cpst.emsadrdb.dao.wh.DepartmentDao;
import com.cpst.emsadrdb.entity.base.BaseOrgDistrict;
import com.cpst.emsadrdb.entity.pmn.User;
import com.cpst.emsadrdb.entity.wh.Department;
import com.cpst.emsadrdb.service.wh.B02cud01Manager;
import com.opensymphony.xwork2.ModelDriven;

@ParentPackage("jsoncrud")
@Results( { @Result(type = "json", name = "json") })
public class B02cud01Action extends BaseActionSupport implements ModelDriven<Department>{

	private static final long serialVersionUID = 5027570688854765808L;
	@Autowired
	private B02cud01Manager b02cud01Manager;
	private List<BaseOrgDistrict> provinces;
	private List<BaseOrgDistrict> citys;
	private Department department=new Department();
	@Autowired
	private DepartmentDao departmentDao;
	private String cityId;
	private String districtId;
	private Long dmPkCode;
	private String message;
	private Integer passstat;//cud结果代码
	
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
	public Long getDmPkCode() {
		return dmPkCode;
	}
	public void setDmPkCode(Long dmPkCode) {
		this.dmPkCode = dmPkCode;
	}
	public List<BaseOrgDistrict> getProvinces() {
		return provinces;
	}
	public List<BaseOrgDistrict> getCitys() {
		return citys;
	}
	public String getMessage() {
		return message;
	}
	public Integer getPassstat() {
		return passstat;
	}

	public Department getModel() {
		return department;
	}
	public String edit() throws Exception {
		User user=getSessionUser();
		passstat=b02cud01Manager.validateRole(user, cityId,districtId);
		message=b02cud01Manager.validateMessage(passstat);
		if(passstat==1){
			if(dmPkCode!=null){
				department=departmentDao.get(dmPkCode);
			}
		}
		return "json";
	}
	public String save() throws Exception {
		passstat=departmentDao.saveDepartment(department, districtId);
		if(passstat==1){
			message="<span style=color:red id=saveMessage>保存成功</span>";
		}else{
			message="<span style=color:red id=saveMessage>失败，部所在的区已经删除</span>";
		}
		return "json";
	}
	public String delete() throws Exception {
		if(dmPkCode!=null && !dmPkCode.equals("")){
			User user=getSessionUser();
			department=departmentDao.get(dmPkCode);
			passstat=b02cud01Manager.validateRole(user,cityId,districtId);
			message=b02cud01Manager.validateMessage(passstat);
			if(passstat==1){
				departmentDao.deleteDepartment(department);
				message="<span style=color:red id=saveMessage >删除成功</span>";
			}
		}
		return "json";
	}

}
