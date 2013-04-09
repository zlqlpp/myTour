package com.cpst.emsadrdb.web.wh;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.cpst.core.web.struts2.BaseActionSupport;
import com.cpst.emsadrdb.dao.wh.RlDtPdDao;
import com.cpst.emsadrdb.entity.pmn.User;
import com.cpst.emsadrdb.service.wh.B05cud01Manager;

@ParentPackage("jsoncrud")
@Results( { @Result(type = "json", name = "json") })
public class B05cud01Action extends BaseActionSupport {
	
	private static final long serialVersionUID = -5904607032267595460L;
	private String dtPkCode;//投递区编码
	private String ptPkCodes;//邮编集合
	private int passstat;
	private String message;
	private String cityId;
	@Autowired
	private B05cud01Manager b05cud01Manager;
	@Autowired
	private RlDtPdDao rlDtPdDao;

	public String getDtPkCode() {
		return dtPkCode;
	}
	public void setDtPkCode(String dtPkCode) {
		this.dtPkCode = dtPkCode;
	}
	public String getPtPkCodes() {
		return ptPkCodes;
	}
	public void setPtPkCodes(String ptPkCodes) {
		this.ptPkCodes = ptPkCodes;
	}
	public String getCityId() {
		return cityId;
	}
	public void setCityId(String cityId) {
		this.cityId = cityId;
	}
	public int getPassstat() {
		return passstat;
	}
	public String getMessage() {
		return message;
	}

	public String save() throws Exception {
		User user=getSessionUser();
		passstat=b05cud01Manager.validateRole(user, cityId);
		message=b05cud01Manager.validateMessage(passstat);
		if(passstat==1){
			if(ptPkCodes!=null && !ptPkCodes.equals("")){
				rlDtPdDao.saveBatch(dtPkCode, ptPkCodes);
				message="保存成功";
			}else{
				message="没有选择邮编";
			}
		}
		return "json";
	}

}
