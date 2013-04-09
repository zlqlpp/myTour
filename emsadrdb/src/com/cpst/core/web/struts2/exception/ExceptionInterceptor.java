package com.cpst.core.web.struts2.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class ExceptionInterceptor extends AbstractInterceptor {
	protected Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public String intercept(ActionInvocation actionInvocation) throws Exception {
		try {
			return actionInvocation.invoke();
		} catch (Exception e) {
			logger.error("错误信息:" + actionInvocation.getAction());
			e.printStackTrace();
			return null;
		}

	}
}
