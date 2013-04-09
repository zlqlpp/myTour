package com.cpst.emsadrdb.service.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cpst.emsadrdb.dao.base.BaseOrgDistrictDao;
import com.cpst.emsadrdb.dao.pmn.ResourceDao;
import com.cpst.emsadrdb.dao.wh.DepartmentDao;
import com.cpst.emsadrdb.dao.wh.DistrictDao;
import com.cpst.emsadrdb.dao.wh.PostsegDao;

@Repository
public class InitManager {
	@Autowired
	private BaseOrgDistrictDao baseOrgDistrictDao;
	@Autowired
	private DistrictDao districtDao;
	@Autowired
	private DepartmentDao departmentDao;
	@Autowired
	private PostsegDao postsegDao;
	@Autowired
	private ResourceDao resourceDao;
	
	public void initCache(){
		baseOrgDistrictDao.initCache();
		districtDao.initCache();
		departmentDao.initCache();
		postsegDao.initCache();
		resourceDao.initCache();
	}

}
