package com.cpst.emsadrdb.dao.address;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cpst.core.orm.Page;
import com.cpst.core.orm.PageUtils;
import com.cpst.core.orm.hibernate.HibernateDao;
import com.cpst.emsadrdb.entity.address.JnadtpgBean;
import com.sun.jna.Native;

@Repository
@Transactional
public class JnadtpgQueryDao extends HibernateDao<JnadtpgBean,String>{
	
	@Transactional(readOnly = true)
	public  long getQueryCount(String sql){
		List<?> listCount=getSession().createSQLQuery(sql).list();
		long totalCount =((BigDecimal)listCount.get(0)).longValue();
		return totalCount;
	}
		
	@Transactional(readOnly = true)
	public  Page<JnadtpgBean> getBeanQueryJnadtpgdata(String ALL_PATH,String[] TOTAL_ADDR_NAME,Page<JnadtpgBean> page){

		String jall ="";
		String jdt = "";
		String jdtchs = "";
		String jpg = "";
		String jpgchs = "";
		
		List<JnadtpgBean> beans=new ArrayList<JnadtpgBean>();
		
		try{
			Libaddrmatch libaddrmatchlib = (Libaddrmatch) Native.loadLibrary (ALL_PATH,Libaddrmatch.class);
			for(int i=0;i<TOTAL_ADDR_NAME.length;i++){
				jall ="投递区未得到||投递段未得到";
				//jall ="北京市投递01区||投递段未得到";
				jall = libaddrmatchlib.MatchById(TOTAL_ADDR_NAME[i]);
				if(jall != null){
					if(jall.indexOf("||")>-1){
						jdt = jall.substring(0, jall.indexOf("||"));
						jpg = jall.substring(jall.indexOf("||")+2,jall.length()-1);
					}else{
						jdt = jall;
						jpg = "投递段未得到";
					}
					
					String sql=" select decode(max(DT_ALIAS_NAME),null,' ',max(DT_ALIAS_NAME)),sysdate from CP_WH_DISTRICT where DT_NAME = '" +  jdt.replace("_收容", "")+ "'";
					List<?> list = getSession().createSQLQuery(sql).list();
					Iterator<?> it = list.iterator();
					if(it.hasNext()){
						Object[] obj = (Object[]) it.next();
						jdtchs = obj[0].toString();
					}
					
				}
				JnadtpgBean bean = new JnadtpgBean();
				bean.setJALL_ADDR_NAME(TOTAL_ADDR_NAME[i]);
				bean.setJNA_DT_NAME(jdt);
				bean.setJNA_DT_CHSNAME(jdtchs);
				bean.setJNA_PG_NAME(jpg);
				beans.add(bean);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		if (page.isAutoCount()) {
			page.setTotalCount(beans.size());
		}
		
		page.setResult(beans);
		page.setAjaxUrl(PageUtils.getPageUrl(page, "AJAx"));
		return page;
	}
	
}
