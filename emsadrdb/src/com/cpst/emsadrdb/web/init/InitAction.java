package com.cpst.emsadrdb.web.init;

import org.springframework.beans.factory.annotation.Autowired;

import com.cpst.emsadrdb.service.init.InitManager;

public class InitAction {
	@Autowired
	private InitManager initManager;

	public String execute() throws Exception {
		initManager.initCache();
		return "view";
	}

}
