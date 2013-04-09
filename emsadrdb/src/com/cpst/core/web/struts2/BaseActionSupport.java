package com.cpst.core.web.struts2;

import java.util.Map;

import com.cpst.emsadrdb.entity.pmn.User;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class BaseActionSupport extends ActionSupport{
	
	private static final long serialVersionUID = 3532913289344668982L;
	private int basePageNo=1;
	public int getBasePageNo() {
		return basePageNo;
	}

	public void setBasePageNo(int basePageNo) {
		this.basePageNo = basePageNo;
	}
	
	@SuppressWarnings("unchecked")
	public User getSessionUser(){
		ActionContext actionContext = ActionContext.getContext();
		Map session = actionContext.getSession();
		User u=(User)session.get("EMS_USER");
		
		/*Short rulLevel=15;
		u.setRulLevel(rulLevel);
		u.setUsCountryOffice("000000");
		u.setUsProvinceOffice("130000");
		u.setUsCityOffice("130100");
		//u.setUsCityOffice("110000");
		u.setUsDistrictOffice("1301002");*/
		return u;
	}
}
