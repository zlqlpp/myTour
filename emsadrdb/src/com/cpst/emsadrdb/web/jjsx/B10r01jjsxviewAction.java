package com.cpst.emsadrdb.web.jjsx;

import java.util.Calendar;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.cpst.core.web.struts2.BaseActionSupport;
import com.cpst.emsadrdb.dao.jjsx.JjsxOptionDao;
import com.cpst.emsadrdb.dao.jjsx.JjsxQueryDao;
import com.cpst.emsadrdb.entity.jjsx.DistrictBean;
import com.cpst.emsadrdb.entity.jjsx.JJSXBean;
import com.cpst.emsadrdb.service.jjsx.JjsxCommon;

public class B10r01jjsxviewAction  extends BaseActionSupport{

	private static final long serialVersionUID = 6713451496948939455L;
	
	@Autowired
	private JjsxOptionDao jjsxOptionDao;
	
	@Autowired
	private JjsxQueryDao jjsxQueryDao;
	
	private List<DistrictBean> provinces;
	
	private String FLAG;
	
	private String SEQID;
	
	private JJSXBean itemBean;

	public String getFLAG() {
		return FLAG;
	}

	public void setFLAG(String flag) {
		FLAG = JjsxCommon.commonsql_inj(flag);
	}
	
	public JJSXBean getItemBean() {
		return itemBean;
	}

	public void setItemBean(JJSXBean itemBean) {
		this.itemBean = itemBean;
	}
	
	public String getSEQID() {
		return SEQID;
	}

	public void setSEQID(String seqid) {
		SEQID = JjsxCommon.commonsql_inj(seqid);
	}

	public List<DistrictBean> getProvinces() {
		return provinces;
	}

	public void setProvinces(List<DistrictBean> provinces) {
		this.provinces = provinces;
	}

	public Boolean userflag(){
		if(getSessionUser()!=null && getSessionUser().getUsLoginId() != null && getSessionUser().getUsLoginId().length() > 0){
			return true;
		}
		else{
			//saveMessage = "用户信息丢失,请重新登陆!";
			return false;
		}
	}
	
	public String viewjjsxzbpc() throws Exception {
		
		if(!userflag()){return null;}
		
		return "viewjjsxzbpc";
	}
	
	public String addjjsxzbpc() throws Exception {
		
		if(!userflag()){return null;}
		
		return "addupjjsxzbpc";
	}
	
	public String upjjsxzbpc() throws Exception {
		
		if(!userflag()){return null;}
		
		itemBean=jjsxQueryDao.getBeanqueryjjsxzbpc(SEQID);
		
		return "addupjjsxzbpc";
	}
	
	public String viewjjsxsjspc() throws Exception {
		
		if(!userflag()){return null;}
		
		provinces=jjsxOptionDao.getProvincesyt(JjsxCommon.getRulUsProvinceOffice(getSessionUser()));
		
		return "viewjjsxsjspc";
	}
	
	public String upjjsxsjspc() throws Exception {
		
		if(!userflag()){return null;}
		
		itemBean=jjsxQueryDao.getBeanqueryjjsxsjspc(SEQID);
		
		return "addupjjsxsjspc";

	}	
	
	
public String viewjjsxqxzypc() throws Exception {
		
		if(!userflag()){return null;}
		
		provinces=jjsxOptionDao.getProvincesyt(JjsxCommon.getRulUsProvinceOffice(getSessionUser()));
		
		return "viewjjsxqxzypc";
	}
	
	public String upjjsxqxzypc() throws Exception {
		
		if(!userflag()){return null;}
		
		itemBean=jjsxQueryDao.getBeanqueryjjsxqxzyzjpc(SEQID);
		
		return "addupjjsxqxzypc";
	}
	
public String viewjjsxqxzjpc() throws Exception {
		
		if(!userflag()){return null;}
		
		provinces=jjsxOptionDao.getProvincesyt(JjsxCommon.getRulUsProvinceOffice(getSessionUser()));
		
		return "viewjjsxqxzjpc";
	}
	
	public String upjjsxqxzjpc() throws Exception {
		
		if(!userflag()){return null;}
		
		itemBean=jjsxQueryDao.getBeanqueryjjsxqxzyzjpc(SEQID);
		
		return "addupjjsxqxzjpc";
	}
	
public String viewjjsxqxzjpccx() throws Exception {
		
		if(!userflag()){return null;}
		
		provinces=jjsxOptionDao.getProvincesyt(null);
		
		return "viewjjsxqxzjpccx";
	}
	

public String viewjjsxqxzypccx() throws Exception {
		
		if(!userflag()){return null;}
		
		provinces=jjsxOptionDao.getProvincesyt(null);
		
		return "viewjjsxqxzypccx";
	}
	
public String viewjjsxqxzypctj() throws Exception {
	
	if(!userflag()){return null;}
	
	provinces=jjsxOptionDao.getProvincesyt(JjsxCommon.getRulUsProvinceOffice(getSessionUser()));
	
	return "viewjjsxqxzypctj";
}

public String viewjjsxqxzjpctj() throws Exception {
	
	if(!userflag()){return null;}
	
	provinces=jjsxOptionDao.getProvincesyt(JjsxCommon.getRulUsProvinceOffice(getSessionUser()));
	
	return "viewjjsxqxzjpctj";
}
	

}
