package com.dao;

import java.util.List;

import com.model.Page;
import com.model.SearchPojo;
import com.model.WqInfo;

public interface WqInfoDao {

	public void save(WqInfo wqinfo);
	
	public List<WqInfo> findAll();
	
	public List<WqInfo> downLoadByDate(SearchPojo sp, Page page);
	public Page findByPage(Page page);
}
