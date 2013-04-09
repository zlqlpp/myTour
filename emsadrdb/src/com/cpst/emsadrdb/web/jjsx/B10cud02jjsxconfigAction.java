package com.cpst.emsadrdb.web.jjsx;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import com.cpst.core.web.struts2.BaseActionSupport;
import com.cpst.emsadrdb.dao.jjsx.JjsxConfigDao;
import com.cpst.emsadrdb.dao.jjsx.JjsxQueryDao;
import com.cpst.emsadrdb.entity.jjsx.JJSXBean;
import com.cpst.emsadrdb.service.jjsx.JjsxCommon;


@SuppressWarnings( { "serial" })
@ParentPackage("jsoncrud")
@Results( { @Result(type = "json", name = "json") })
public class B10cud02jjsxconfigAction extends BaseActionSupport {

	@Autowired
	private JjsxConfigDao jjsxConfigDao;

	@Autowired
	private JjsxQueryDao jjsxQueryDao;

	private String saveMessage;
	private JJSXBean itemBean;
	
	private String SEQID;
	private String PCMC;
	private String PCSX;
	
	private String SEQIDS;
	private String DISTCD;
	private String DISTNAME;
	private String README;
	private String SJSPCSEQID;
	
	private String CQUSXXUS;
	private String HXJZSJ;
	private String HXJZJG;
	private String FHXJZSJ;
	private String FHXJZJG;
	
	private String HXFLAG;
	
	
	
	public String getREADME() {
		return README;
	}

	public void setREADME(String readme) {
		README = readme;
	}

	public String getSaveMessage() {
		return saveMessage;
	}

	public void setSaveMessage(String saveMessage) {
		this.saveMessage = saveMessage;
	}

	public String getSEQID() {
		return SEQID;
	}

	public void setSEQID(String seqid) {
		SEQID = seqid;
	}

	public String getPCMC() {
		return PCMC;
	}

	public void setPCMC(String pcmc) {
		PCMC = pcmc;
	}

	public String getPCSX() {
		return PCSX;
	}

	public void setPCSX(String pcsx) {
		PCSX = pcsx;
	}

	public String getSEQIDS() {
		return SEQIDS;
	}

	public void setSEQIDS(String seqids) {
		SEQIDS = seqids;
	}

	public String getDISTCD() {
		return DISTCD;
	}

	public void setDISTCD(String distcd) {
		DISTCD = distcd;
	}

	public String getDISTNAME() {
		return DISTNAME;
	}

	public void setDISTNAME(String distname) {
		DISTNAME = distname;
	}

	public String getSJSPCSEQID() {
		return SJSPCSEQID;
	}

	public void setSJSPCSEQID(String sjspcseqid) {
		SJSPCSEQID = sjspcseqid;
	}

	public String getCQUSXXUS() {
		return CQUSXXUS;
	}

	public void setCQUSXXUS(String cqusxxus) {
		CQUSXXUS = cqusxxus;
	}

	public String getHXJZSJ() {
		return HXJZSJ;
	}

	public void setHXJZSJ(String hxjzsj) {
		HXJZSJ = hxjzsj;
	}

	public String getHXJZJG() {
		return HXJZJG;
	}

	public void setHXJZJG(String hxjzjg) {
		HXJZJG = hxjzjg;
	}

	public String getFHXJZSJ() {
		return FHXJZSJ;
	}

	public void setFHXJZSJ(String fhxjzsj) {
		FHXJZSJ = fhxjzsj;
	}

	public String getFHXJZJG() {
		return FHXJZJG;
	}

	public void setFHXJZJG(String fhxjzjg) {
		FHXJZJG = fhxjzjg;
	}

	public String getHXFLAG() {
		return HXFLAG;
	}

	public void setHXFLAG(String hxflag) {
		HXFLAG = hxflag;
	}

	public void configITEMBean() {
		itemBean = new JJSXBean();
		itemBean.setADUID(getSessionUser().getUsLoginId());
		itemBean.setADUNAME(getSessionUser().getUsName());
		itemBean.setOPEID(getSessionUser().getUsLoginId());
		itemBean.setOPENAME(getSessionUser().getUsName());
		itemBean.setSEQID(JjsxCommon.commonsql_inj(SEQID));
		itemBean.setPCMC(JjsxCommon.commonsql_inj(PCMC));
		itemBean.setREADME(JjsxCommon.commonsql_inj(README));
		itemBean.setPCSX(JjsxCommon.commonsql_inj(PCSX));

		itemBean.setSEQIDS(JjsxCommon.commonsql_inj(SEQIDS));
		itemBean.setDISTCD(JjsxCommon.commonsql_inj(DISTCD));
		itemBean.setDISTNAME(JjsxCommon.commonsql_inj(DISTNAME));
		itemBean.setSJSPCSEQID(JjsxCommon.commonsql_inj(SJSPCSEQID));
		
		itemBean.setCQUSXXUS(JjsxCommon.commonsql_inj(CQUSXXUS));
		itemBean.setHXJZSJ(JjsxCommon.commonsql_inj(HXJZSJ));
		itemBean.setHXJZJG(JjsxCommon.commonsql_inj(HXJZJG));
		itemBean.setFHXJZSJ(JjsxCommon.commonsql_inj(FHXJZSJ));
		itemBean.setFHXJZJG(JjsxCommon.commonsql_inj(FHXJZJG));

		itemBean.setHXFLAG(JjsxCommon.commonsql_inj(HXFLAG));
	}

	public Boolean userflag(){
		if(getSessionUser()!=null && getSessionUser().getUsLoginId() != null && getSessionUser().getUsLoginId().length() > 0){
			return true;
		}
		else{
			saveMessage = "用户信息丢失,请重新登陆!";
			return false;
		}
	}
	
	public String addjjsxzbpc() throws Exception {
		
		if(!userflag()){return "json";}
		
		configITEMBean();
		
		String sql = "select count(*) from CJJSX_ZB_PC where PCMC = '" + itemBean.getPCMC() + "' and PCSX = '" + itemBean.getPCSX() + "'";
		if (jjsxQueryDao.getQueryCount(sql) > 0) {
			saveMessage = "存在同样的配置名称,不能添加此数据!";
			return "json";
		}

		@SuppressWarnings("unused")
		boolean m = jjsxConfigDao.addjjsxzbpc(itemBean);

		saveMessage = "维护成功";
		return "json";
	}
	
	public String upjjsxzbpc() throws Exception {
		
		if(!userflag()){return "json";}
		
		configITEMBean();
		
		if(itemBean.getSEQID().length() > 0){
		
			String sql = "select count(*) from CJJSX_ZB_PC where PCMC = '" + itemBean.getPCMC() + "' and PCSX = '" + itemBean.getPCSX() + "' and  SEQID != '" + itemBean.getSEQID() + "'";
			if (jjsxQueryDao.getQueryCount(sql) > 0) {
				saveMessage = "存在同样的配置名称,不能添加此数据!";
				return "json";
			}

			@SuppressWarnings("unused")
			boolean m = jjsxConfigDao.upjjsxzbpc(itemBean);
	
			saveMessage = "维护成功";
		
		}else{
			
			saveMessage = "维护失败";
			
		}
		return "json";
	}
	
	public String deljjsxzbpc() throws Exception {
		
		if(!userflag()){return "json";}
		
		configITEMBean();
		
		if(itemBean.getSEQID().length() > 0 ){
		
			
			@SuppressWarnings("unused")
			boolean m = jjsxConfigDao.deljjsxzbpc(itemBean);

			saveMessage = "维护成功";
		}else{
			
			saveMessage = "维护失败";
			
		}
		
		
		return "json";
	}
	
	public String upjjsxsjspc() throws Exception {
		
		if(!userflag()){return "json";}
		
		configITEMBean();

		if(itemBean.getSEQID().length() > 0 ){
			
			@SuppressWarnings("unused")
			boolean m = jjsxConfigDao.upjjsxsjspc(itemBean);

			saveMessage = "维护成功";
			
		}else{
			
			saveMessage = "维护失败";
			
		}
		
		return "json";
	}
	
	public String addjjsxsjspc() throws Exception {
		
		if(!userflag()){return "json";}
		
		configITEMBean();
		@SuppressWarnings("unused")
		boolean m = jjsxConfigDao.addjjsxsjspc(itemBean);

		saveMessage = "维护成功";
		return "json";
	}
	
	public String deljjsxsjspc() throws Exception {
		
		if(!userflag()){return "json";}
		
		configITEMBean();
		
		if(itemBean.getSEQID().length() > 0 ){
			@SuppressWarnings("unused")
			boolean m = jjsxConfigDao.deljjsxsjspc(itemBean);
	
			saveMessage = "维护成功";
		}else{
			
			saveMessage = "维护失败";
			
		}
		return "json";
	}
	
	public String upjjsxqxzyzjpc() throws Exception {
		
		if(!userflag()){return "json";}
		
		configITEMBean();
		
		if(itemBean.getSEQID().length() > 0 ){
			@SuppressWarnings("unused")
			boolean m = jjsxConfigDao.upjjsxqxzyzjpc(itemBean);
	
			saveMessage = "维护成功";
		}else{
			
			saveMessage = "维护失败";
			
		}
		return "json";
	}
	
	public String addjjsxqxzyzjpc() throws Exception {
		
		if(!userflag()){return "json";}
		
		configITEMBean();
		@SuppressWarnings("unused")
		boolean m = jjsxConfigDao.addjjsxqxzyzjpc(itemBean);

		saveMessage = "维护成功";
		return "json";
	}
	
	public String deljjsxqxzyzjpc() throws Exception {
		
		if(!userflag()){return "json";}
		
		configITEMBean();
		@SuppressWarnings("unused")
		boolean m = jjsxConfigDao.deljjsxqxzyzjpc(itemBean);

		saveMessage = "维护成功";
		return "json";
	}
	
}
