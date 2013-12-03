package com.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.WqInfoDao;
import com.model.Page;
import com.model.SearchPojo;
import com.model.WqInfo;
import com.service.WqInfoService;



@Service("wqinfoService")
public class WqInfoServiceImpl implements WqInfoService {

	@Autowired
	private WqInfoDao wqinfoDao; 
	@Override
	public void saveWqInfo(WqInfo wqinfo) {
		// TODO Auto-generated method stub
		wqinfoDao.save(wqinfo);
	}
	@Override
	public List<WqInfo> findAll() {
		// TODO Auto-generated method stub
		return wqinfoDao.findAll();
	}
	@Override
	public List<WqInfo> downLoadByDate(SearchPojo sp, Page page) {
		// TODO Auto-generated method stub
		return wqinfoDao.downLoadByDate(sp, page);
	}

}
