package com.cpst.emsadrdb.web.pmn;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.cpst.emsadrdb.dao.pmn.ResourceDao;
import com.cpst.emsadrdb.entity.pmn.Resource;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 资源管理事务类
 * @author PengYuLei
 */
@ParentPackage("jsoncrud")
@Results( { @Result(type = "json", name = "json") })
public class A01cud01Action extends ActionSupport implements ModelDriven<Resource>{
	private static final long serialVersionUID = 8403639670597908405L;
	private Long rsPkId;
	private String rsPkIds;
	private String saveMessage;
	private Resource resource=new Resource();
	@Autowired
	private ResourceDao resourceDao;
	
	public void setRsPkIds(String rsPkIds) {
		this.rsPkIds = rsPkIds;
	}

	public String getRsPkIds() {
		return rsPkIds;
	}

	public String getSaveMessage() {
		return saveMessage;
	}

	public Long getRsPkId() {
		return rsPkId;
	}

	public void setRsPkId(Long rsPkId) {
		this.rsPkId = rsPkId;
	}

	public Resource getModel() {
		return resource;
	}
	public String save() throws Exception {
		resource.setRsType("1");
		resourceDao.saveResource(resource);
		saveMessage="<span style=color:red id=saveMessage>保存成功</span>";
		return "json";
	}
	public String delete() throws Exception {
		if(rsPkIds!=null && !rsPkIds.equals("")){
			rsPkIds=rsPkIds.substring(1);
		}
		resourceDao.deleteResourcesByrsPkIds(rsPkIds);
		saveMessage="<span style=color:red id=tpostSegSaveMessage>删除成功</span>";
		return "json";
	}

}
