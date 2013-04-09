package com.cpst.emsadrdb.web.wh;

import java.util.List;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.cpst.core.web.struts2.BaseActionSupport;
import com.cpst.emsadrdb.dao.wh.PostsegDao;
import com.cpst.emsadrdb.entity.base.BaseOrgDistrict;
import com.cpst.emsadrdb.entity.pmn.User;
import com.cpst.emsadrdb.entity.wh.Postseg;
import com.cpst.emsadrdb.service.wh.B01cud01Manager;
import com.cpst.emsadrdb.service.wh.B02cud01Manager;
import com.opensymphony.xwork2.ModelDriven;

@ParentPackage("jsoncrud")
@Results( { @Result(type = "json", name = "json") })
public class B03cud01Action  extends BaseActionSupport implements ModelDriven<Postseg>{


	private static final long serialVersionUID = -7468828147803481504L;
	@Autowired
	private B02cud01Manager b02cud01Manager;
	@Autowired
	private B01cud01Manager b01cud01Manager;
	private List<BaseOrgDistrict> provinces;
	private List<BaseOrgDistrict> citys;
	private Postseg postseg=new Postseg();
	@Autowired
	private PostsegDao postsegDao;
	private String cityId;
	private String districtId;
	private Long departmentId;
	private Long pgPkCode;
	private String message;
	private Integer passstat;//cud结果代码
	private String ids;
	private String onchageDistrictId;
	private Long onchangDepartmentId;
	

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getOnchageDistrictId() {
		return onchageDistrictId;
	}

	public void setOnchageDistrictId(String onchageDistrictId) {
		this.onchageDistrictId = onchageDistrictId;
	}

	public Long getOnchangDepartmentId() {
		return onchangDepartmentId;
	}

	public void setOnchangDepartmentId(Long onchangDepartmentId) {
		this.onchangDepartmentId = onchangDepartmentId;
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

	

	public Long getPgPkCode() {
		return pgPkCode;
	}

	public void setPgPkCode(Long pgPkCode) {
		this.pgPkCode = pgPkCode;
	}

	public List<BaseOrgDistrict> getProvinces() {
		return provinces;
	}

	public List<BaseOrgDistrict> getCitys() {
		return citys;
	}

	public Postseg getPostseg() {
		return postseg;
	}

	public String getMessage() {
		return message;
	}

	public Integer getPassstat() {
		return passstat;
	}


	public Postseg getModel() {
		// TODO Auto-generated method stub
		return postseg;
	}
	
	public String edit() throws Exception {
		User user=getSessionUser();
		passstat=b02cud01Manager.validateRole(user, cityId,districtId);
		message=b02cud01Manager.validateMessage(passstat);
		if(passstat==1){
			if(pgPkCode!=null){
				postseg=postsegDao.get(pgPkCode);
			}
		}
		return "json";
	}
	public String save() throws Exception {
		passstat=postsegDao.savePostseg(postseg, districtId,departmentId);
		if(passstat==1){
			message="<span style=color:red id=saveMessage>保存成功</span>";
		}else{
			message="<span style=color:red id=saveMessage>失败，段所在的区或者部已经删除</span>";
		}
		return "json";
	}
	public String delete() throws Exception {
		if(pgPkCode!=null && !pgPkCode.equals("")){
			User user=getSessionUser();
			postseg=postsegDao.get(pgPkCode);
			passstat=b02cud01Manager.validateRole(user,cityId,districtId);
			message=b02cud01Manager.validateMessage(passstat);
			if(passstat==1){
				postsegDao.deletePostseg(postseg);
				message="<span style=color:red id=saveMessage >删除成功</span>";
			}
		}
		return "json";
	}
	public String alterDistrict() throws Exception {
		User user=getSessionUser();
		passstat=b01cud01Manager.validateRole(user, cityId);
		message=b01cud01Manager.validateMessage(passstat);
		if(passstat==1){
			if(ids!=null && !ids.equals("")){
				ids=ids.substring(1);
			}
			postsegDao.changDisrict(onchageDistrictId, ids);
		}
		return "json";
	}
	public String alterDepartment() throws Exception {
		User user=getSessionUser();
		passstat=b01cud01Manager.validateRole(user, cityId);
		message=b01cud01Manager.validateMessage(passstat);
		if(passstat==1){
			if(ids!=null && !ids.equals("")){
				ids=ids.substring(1);
			}
			postsegDao.changDepartment(onchangDepartmentId, ids);
			message="改部成功";
		}
		return "json";
	}

}
