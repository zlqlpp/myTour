package com.dao.impl;

import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.dao.WqInfoDao;
import com.dao.base.BaseDao;
import com.model.Page;
import com.model.SearchPojo;
import com.model.WqInfo;
@Repository("wqinfoDao")
public class WqInfoDaoImpl extends BaseDao implements WqInfoDao {

	@Override
	public void save(WqInfo wqinfo) {
		// TODO Auto-generated method stub
		getEm().persist(wqinfo);
		
	}

	@Override
	public List<WqInfo> findAll() {
		// TODO Auto-generated method stub
		String sql="select w from WqInfo w";
		Query query = (Query)this.getEm().createQuery(sql);
        
        List<WqInfo> list = query.getResultList();
		return list;
	}

	@Override
	public List<WqInfo> downLoadByDate(SearchPojo sp, Page page) {
		// TODO Auto-generated method stub
		String sql="select w from WqInfo w where insertTime>='"+sp.getStartTime()+"' and insertTime<='"+sp.getEndTime()+"'";
		Query query = (Query)this.getEm().createQuery(sql);
        
        List<WqInfo> list = query.getResultList();
		return list;
	}

	@Override
	public Page findByPage(Page page) {
		// TODO Auto-generated method stub
		String sql="select w from WqInfo w";
		Query query = (Query)this.getEm().createQuery(sql);
        page.setTotal(query.getResultList().size());
        int xpage = Integer.parseInt(page.getCurrentpage());
        int xrows = Integer.parseInt(page.getRows());
        page.setList(query.setFirstResult((xpage-1)*xrows).setMaxResults(xpage*xrows).getResultList());
        
		return page;
	}

}
