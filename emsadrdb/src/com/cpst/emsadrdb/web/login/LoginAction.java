package com.cpst.emsadrdb.web.login;

import java.util.Map;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.Authentication;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.security.userdetails.UserDetails;

import com.cpst.emsadrdb.dao.pmn.UserDao;
import com.cpst.emsadrdb.entity.pmn.User;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@Results( { @Result(name = "index", location = "/index.jsp"),
	@Result(name = "login", location = "/login/index.jsp") })
public class LoginAction extends ActionSupport{

	
	private static final long serialVersionUID = -7994837791304111997L;
	@Autowired
	private UserDao userDao;

	public String execute() throws Exception {
		ActionContext actionContext = ActionContext.getContext();
		Map session = actionContext.getSession();
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		User user = null;
		if (auth != null && auth.getPrincipal() != null
				&& auth.getPrincipal() instanceof UserDetails) {
			System.out.println("login");
			UserDetails details = (UserDetails) auth.getPrincipal();
			user = userDao.getUserByLoginId(details.getUsername());
		}
		System.out.println(user.getRulLevel());
		session.put("EMS_USER", user);
		return "login";
	}
}
