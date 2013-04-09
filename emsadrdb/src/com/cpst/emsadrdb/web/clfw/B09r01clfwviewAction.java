package com.cpst.emsadrdb.web.clfw;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.cpst.core.web.struts2.BaseActionSupport;
import com.cpst.emsadrdb.dao.clfw.ClfwOptionDao;
import com.cpst.emsadrdb.dao.clfw.ClfwQueryDao;
import com.cpst.emsadrdb.entity.clfw.CLFWBean;
import com.cpst.emsadrdb.entity.clfw.DistrictBean;
import com.cpst.emsadrdb.service.clfw.ClfwCommon;

public class B09r01clfwviewAction  extends BaseActionSupport{

	private static final long serialVersionUID = 6713451496948939455L;
	
	private List<DistrictBean> provinces;
	private List<DistrictBean> provincesnull;
	private List<DistrictBean> countys;
	private CLFWBean clfwBean;
	private List<CLFWBean> clfwpcs;
	
	private String SEL_CLFWYLXXB_CSRQ;
	
	
	public String getSEL_CLFWYLXXB_CSRQ() {
		return SEL_CLFWYLXXB_CSRQ;
	}

	public void setSEL_CLFWYLXXB_CSRQ(String sel_clfwylxxb_csrq) {
		SEL_CLFWYLXXB_CSRQ = sel_clfwylxxb_csrq;
	}

	@Autowired
	private ClfwOptionDao clfwOptionDao;
	
	@Autowired
	private ClfwQueryDao clfwQueryDao;
	
	private String FLAG;
	private String SEQID;

	
	public List<DistrictBean> getProvincesnull() {
		return provincesnull;
	}

	public void setProvincesnull(List<DistrictBean> provincesnull) {
		this.provincesnull = provincesnull;
	}

	public List<DistrictBean> getCountys() {
		return countys;
	}

	public void setCountys(List<DistrictBean> countys) {
		this.countys = countys;
	}

	public String getSEQID() {
		return SEQID;
	}

	public void setSEQID(String seqid) {
		SEQID = seqid;
	}

	public String getFLAG() {
		return FLAG;
	}

	public void setFLAG(String flag) {
		FLAG = flag;
	}

	public List<DistrictBean> getProvinces() {
		return provinces;
	}

	public void setProvinces(List<DistrictBean> provinces) {
		this.provinces = provinces;
	}

	public CLFWBean getClfwBean() {
		return clfwBean;
	}

	public void setClfwBean(CLFWBean clfwBean) {
		this.clfwBean = clfwBean;
	}
	
	public List<CLFWBean> getClfwpcs() {
		return clfwpcs;
	}

	public void setClfwpcs(List<CLFWBean> clfwpcs) {
		this.clfwpcs = clfwpcs;
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

	public String addclfwpc() throws Exception {
		
		if(!userflag()){return null;}
		
		return "addclfwpc";
	}
	
	public String upclfwpc() throws Exception {
		
		if(!userflag()){return null;}
		
		clfwBean=clfwQueryDao.getClfwpc(SEQID);
		return "addclfwpc";
	}
	
	public String viewclfwpc() throws Exception {
		
		if(!userflag()){return null;}
		
		return "viewclfwpc";
	}
	
	public String addclfwpcsheng() throws Exception {
		
		if(!userflag()){return null;}
		
		provinces=clfwOptionDao.getProvinces(ClfwCommon.getRulUsProvinceOffice(getSessionUser()));
		clfwpcs = clfwOptionDao.getClfwpcs(null,"1");
		return "addclfwpcsheng";
	}
	
	public String upclfwpcsheng() throws Exception {
		
		if(!userflag()){return null;}
		
		clfwBean=clfwQueryDao.getClfwpcsheng(SEQID);
		provinces=clfwOptionDao.getProvinces(ClfwCommon.getRulUsProvinceOffice(getSessionUser()));
		clfwpcs = clfwOptionDao.getClfwpcs(null,"1");
		return "addclfwpcsheng";
	}
	
	public String viewclfwpcsheng() throws Exception {
		
		if(!userflag()){return null;}
		
		provinces=clfwOptionDao.getProvinces(ClfwCommon.getRulUsProvinceOffice(getSessionUser()));
		return "viewclfwpcsheng";
	}
	
	public String addclfwpcshi() throws Exception {
		
		if(!userflag()){return null;}
		
		provincesnull=clfwOptionDao.getProvinces(null);
		provinces=clfwOptionDao.getProvinces(ClfwCommon.getRulUsProvinceOffice(getSessionUser()));
		return "addclfwpcshi";
	}
	
	public String upclfwpcshi() throws Exception {
		
		if(!userflag()){return null;}
		
		clfwBean=clfwQueryDao.getClfwpcshi(SEQID);
		provincesnull=clfwOptionDao.getProvinces(null);
		provinces=clfwOptionDao.getProvinces(ClfwCommon.getRulUsProvinceOffice(getSessionUser()));
		return "addclfwpcshi";
	}
	
	public String viewclfwpcshi() throws Exception {
		
		if(!userflag()){return null;}
		
		provinces=clfwOptionDao.getProvinces(ClfwCommon.getRulUsProvinceOffice(getSessionUser()));
		return "viewclfwpcshi";
	}
	
	public String addclfwtdb() throws Exception {
		
		if(!userflag()){return null;}
		
		provinces=clfwOptionDao.getProvinces(ClfwCommon.getRulUsProvinceOffice(getSessionUser()));
		return "addclfwtdb";
	}
	
	public String viewclfwtdbcq() throws Exception {
		
		if(!userflag()){return null;}
		
		provinces=clfwOptionDao.getProvinces(ClfwCommon.getRulUsProvinceOffice(getSessionUser()));
		return "viewclfwtdbcq";
	}
	
	public String addcommclfwtdbcq() throws Exception {
		
		if(!userflag()){return null;}
		
		clfwBean=clfwQueryDao.getCPWHDEPARTMENT(SEQID);
		countys=clfwOptionDao.getCountysqd(clfwBean.getCITY_CODE());
		return "addclfwtdbcq";
	}
	
	public String upcommclfwtdbcq() throws Exception {
		
		if(!userflag()){return null;}
		
		clfwBean=clfwQueryDao.getClfwtdbcq(SEQID);
		countys=clfwOptionDao.getCountysqd(clfwBean.getCITY_CODE());
		return "addclfwtdbcq";
	}
	
	public String viewclfwtdbsh() throws Exception {
		
		if(!userflag()){return null;}
		
		provinces=clfwOptionDao.getProvinces(ClfwCommon.getRulUsProvinceOffice(getSessionUser()));
		return "viewclfwtdbsh";
	}
	
	public String viewclfwtdb() throws Exception {
		
		if(!userflag()){return null;}
		
		provinces=clfwOptionDao.getProvinces(ClfwCommon.getRulUsProvinceOffice(getSessionUser()));
		return "viewclfwtdb";
	}
	
	public String upcommclfwtdb() throws Exception {
		
		if(!userflag()){return null;}
		
		clfwBean=clfwQueryDao.getClfwtdb(SEQID);
		return "upclfwtdb";
	}
	
	public String viewclfwtdd() throws Exception {
		
		if(!userflag()){return null;}
		
		clfwBean=clfwQueryDao.getClfwtdb(SEQID);
		return "viewclfwtdd";
	}
	
	public String viewclfwsjcx() throws Exception {
		
		if(!userflag()){return null;}
		
		provinces=clfwOptionDao.getProvinces(ClfwCommon.getRulUsProvinceOffice(getSessionUser()));
		return "viewclfwsjcx";
	}
	
	public String viewclfwtdbtj() throws Exception {
		
		if(!userflag()){return null;}
		
		provinces=clfwOptionDao.getProvinces(ClfwCommon.getRulUsProvinceOffice(getSessionUser()));
		return "viewclfwtdbtj";
	}
	
	public String viewclfwkffwpz() throws Exception {
		
		if(!userflag()){return null;}
		
		provinces=clfwOptionDao.getProvinces(ClfwCommon.getRulUsProvinceOffice(getSessionUser()));
		return "viewclfwkffwpz";
	}
	
	public String addclfwkffw() throws Exception {
		
		if(!userflag()){return null;}
		
		provinces=clfwOptionDao.getProvinces(ClfwCommon.getRulUsProvinceOffice(getSessionUser()));
		return "addclfwkffwpz";
	}
	
	public String upclfwkffw() throws Exception {
		
		if(!userflag()){return null;}
		
		clfwBean=clfwQueryDao.getClfwkffw(SEQID);
		provinces=clfwOptionDao.getProvinces(ClfwCommon.getRulUsProvinceOffice(getSessionUser()));
		return "addclfwkffwpz";
	}
	
	public String viewclfwkffwcx() throws Exception {
		
		if(!userflag()){return null;}
		
		provinces=clfwOptionDao.getProvinces(null);
		return "viewclfwkffwcx";
	}
	
	public String addclfwylxxb() throws Exception {
		
		if(!userflag()){return null;}
		
		provinces=clfwOptionDao.getProvinces(ClfwCommon.getRulUsProvinceOffice(getSessionUser()));
		return "addclfwylxxb";
	}
	
	public String upclfwylxxb() throws Exception {
		
		if(!userflag()){return null;}
		
		clfwBean=clfwQueryDao.getClfwylxxb(SEQID);
		provinces=clfwOptionDao.getProvinces(ClfwCommon.getRulUsProvinceOffice(getSessionUser()));
		return "addclfwylxxb";
	}
	
	public String viewclfwhkwh() throws Exception {
		
		if(!userflag()){return null;}
		
		provinces=clfwOptionDao.getProvinces(ClfwCommon.getRulUsProvinceOffice(getSessionUser()));
		return "viewclfwhkwh";
	}
	
	public String viewtjclfwhkwhal() throws Exception {
		
		if(!userflag()){return null;}
		
		provinces=clfwOptionDao.getProvinces(ClfwCommon.getRulUsProvinceOffice(getSessionUser()));
		return "viewtjclfwhkwhal";
	}
	
	public String addclfwhkwhcg() throws Exception {
		
		if(!userflag()){return null;}

		provincesnull=clfwOptionDao.getProvinces(null);
		provinces=clfwOptionDao.getProvinces(ClfwCommon.getRulUsProvinceOffice(getSessionUser()));
		
		return "addclfwhkwhcg";
	}
	
	public String upclfwhkwhcg() throws Exception {
		
		if(!userflag()){return null;}
		
		clfwBean=clfwQueryDao.getClfwhkwh(SEQID);
		provincesnull=clfwOptionDao.getProvinces(null);
		provinces=clfwOptionDao.getProvinces(ClfwCommon.getRulUsProvinceOffice(getSessionUser()));
		
		return "addclfwhkwhcg";
	}
	
	public String addclfwhkwhjg() throws Exception {
		
		if(!userflag()){return null;}

		provincesnull=clfwOptionDao.getProvinces(ClfwCommon.getRulUsProvinceOffice(getSessionUser()));
		provinces=clfwOptionDao.getProvinces(null);
		
		return "addclfwhkwhjg";
	}
	
	public String upclfwhkwhjg() throws Exception {
		
		if(!userflag()){return null;}
		
		clfwBean=clfwQueryDao.getClfwhkwh(SEQID);
		
		provincesnull=clfwOptionDao.getProvinces(ClfwCommon.getRulUsProvinceOffice(getSessionUser()));
		provinces=clfwOptionDao.getProvinces(null);
		
		return "addclfwhkwhjg";
	}
	
	public String viewclfwylxxbt() throws Exception {
		
		if(!userflag()){return null;}
		
		Calendar c=Calendar.getInstance();
		
        String tyear=String.valueOf(c.get(Calendar.YEAR));
        
        String tmonth=String.valueOf(c.get(Calendar.MONTH)+1);
        
        if((c.get(Calendar.MONTH)+1)<10){
        	tmonth='0' + tmonth;
		}
        
        String tday=String.valueOf(c.get(Calendar.DAY_OF_MONTH));

        if(c.get(Calendar.DAY_OF_MONTH)<10){
        	tday='0' + tday;
		}
		
        SEL_CLFWYLXXB_CSRQ = tyear + tmonth + tday;

		provinces=clfwOptionDao.getProvinces(ClfwCommon.getRulUsProvinceOffice(getSessionUser()));
		return "viewclfwylxxbt";
	}
	
	public String viewtjclfwylxxbt() throws Exception {
		
		if(!userflag()){return null;}

		provinces=clfwOptionDao.getProvinces(ClfwCommon.getRulUsProvinceOffice(getSessionUser()));
		
		return "viewtjclfwylxxbt";
	}
	
	public String viewtjclfwylxxbtalc() throws Exception {
		
		if(!userflag()){return null;}

		provinces=clfwOptionDao.getProvinces(ClfwCommon.getRulUsProvinceOffice(getSessionUser()));
		
		return "viewtjclfwylxxbtalc";
	}
	
	public String viewtjclfwhbwhalmx() throws Exception {
		
		if(!userflag()){return null;}

		provinces=clfwOptionDao.getProvinces(ClfwCommon.getRulUsProvinceOffice(getSessionUser()));
		
		return "viewtjclfwhbwhalmx";
	}
	
	public String viewclfwresorgpz() throws Exception {
		
		if(!userflag()){return null;}
		
		provinces=clfwOptionDao.getProvinces(ClfwCommon.getRulUsProvinceOffice(getSessionUser()));
		return "viewclfwresorgpz";
	}
	
	public String viewclfwjgdm() throws Exception {
		
		if(!userflag()){return null;}
		
		provinces=clfwOptionDao.getProvinces(ClfwCommon.getRulUsProvinceOffice(getSessionUser()));
		
		return "viewclfwjgdm";
	}
	
	public String viewclfwtabnjjs() throws Exception {
		
		if(!userflag()){return null;}
		
		provinces=clfwOptionDao.getProvinces(ClfwCommon.getRulUsProvinceOffice(getSessionUser()));
		return "viewclfwtabnjjs";
	}
	
	public String viewclfwhkldwh() throws Exception {
		
		if(!userflag()){return null;}
		
		provinces=clfwOptionDao.getProvinces(ClfwCommon.getRulUsProvinceOffice(getSessionUser()));
		return "viewclfwhkldwh";
	}
	
	public String viewclfwhkldwhmx() throws Exception {
		
		if(!userflag()){return null;}
		
		provinces=clfwOptionDao.getProvinces(ClfwCommon.getRulUsProvinceOffice(getSessionUser()));
		return "viewclfwhkldwhmx";
	}
	public String viewclfwhkldwhtj() throws Exception {
		
		if(!userflag()){return null;}
		
		provinces=clfwOptionDao.getProvinces(ClfwCommon.getRulUsProvinceOffice(getSessionUser()));
		return "viewclfwhkldwhtj";
	}

}
