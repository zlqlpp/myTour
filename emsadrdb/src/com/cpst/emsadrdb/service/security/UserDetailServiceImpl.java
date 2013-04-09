package com.cpst.emsadrdb.service.security;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.GrantedAuthority;
import org.springframework.security.GrantedAuthorityImpl;
import org.springframework.security.userdetails.UserDetails;
import org.springframework.security.userdetails.UserDetailsService;
import org.springframework.security.userdetails.UsernameNotFoundException;

import com.cpst.emsadrdb.dao.pmn.RoleDao;
import com.cpst.emsadrdb.dao.pmn.UserDao;
import com.cpst.emsadrdb.entity.pmn.Role;
import com.cpst.emsadrdb.entity.pmn.User;

public class UserDetailServiceImpl implements UserDetailsService{
	@Autowired
	private UserDao userDao;


	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException, DataAccessException {
		try {
			username=new String(username.getBytes("ISO8859-1"),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		User user = userDao.getUserFetchRolesByLoginId(username);
		if (user == null)
			throw new UsernameNotFoundException(username + " 不存在");
		List<GrantedAuthority> authsList = new ArrayList<GrantedAuthority>();

		for (Role role : user.getRoles()) {
			authsList.add(new GrantedAuthorityImpl(role.getReName()));
		}
		
		org.springframework.security.userdetails.User userdetail = new org.springframework.security.userdetails.User(
				user.getUsLoginId(), user.getUsPasswd(), true, true, true, true, authsList
						.toArray(new GrantedAuthority[authsList.size()]));
		return userdetail;
	}

}
