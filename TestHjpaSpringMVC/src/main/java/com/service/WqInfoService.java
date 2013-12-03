package com.service;

import java.util.List;

import com.model.Page;
import com.model.SearchPojo;
import com.model.WqInfo;

public interface WqInfoService {

	public void saveWqInfo(WqInfo wqinfo);
	
	public List<WqInfo> findAll();
	
	public List<WqInfo> downLoadByDate(SearchPojo sp,Page page);
}
