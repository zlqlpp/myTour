package com.cpst.emsadrdb.service.security;

import java.util.Collection;
import java.util.List;

import net.sf.ehcache.Cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.ConfigAttributeDefinition;
import org.springframework.security.ConfigAttributeEditor;
import org.springframework.security.intercept.web.FilterInvocation;
import org.springframework.security.intercept.web.FilterInvocationDefinitionSource;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import com.cpst.emsadrdb.dao.pmn.RoleDao;
import com.cpst.emsadrdb.entity.pmn.Resource;
import com.cpst.emsadrdb.entity.pmn.Role;

public class MySecureResourceFilter implements FilterInvocationDefinitionSource{
	@Autowired
	private Cache resourceCacheBean;
	private final PathMatcher pathMatcher = new AntPathMatcher();

	public ConfigAttributeDefinition getAttributes(Object filter)
			throws IllegalArgumentException {
		FilterInvocation filterInvocation = (FilterInvocation) filter;
		String url = filterInvocation.getRequestUrl();
		int firstQuestionMarkIndex = url.lastIndexOf("?");
		if (firstQuestionMarkIndex != -1) {
			url = url.substring(0, firstQuestionMarkIndex);
		}
		List<Long> resourceKeys = resourceCacheBean.getKeys();
		boolean matched = false;
		Resource r = null;
		for (Long str : resourceKeys) {
			r = (Resource) resourceCacheBean.get(str).getValue();
			String resStr = r.getRsStr();
			matched = pathMatcher.match(resStr, url);
			if (matched) {
				break;
			}
		}
		if (!matched) {
			return null;
		} else {
			ConfigAttributeEditor configAttrEditor = new ConfigAttributeEditor();
			List<Role> roles = r.getRoles();
			StringBuffer rolesList = new StringBuffer();
			for (Role role : roles) {
				rolesList.append(role.getReName());
				rolesList.append(",");
			}
			if (rolesList.length() > 0)
				rolesList.replace(rolesList.length() - 1,
						rolesList.length() + 1, "");
			configAttrEditor.setAsText(rolesList.toString());
			return (ConfigAttributeDefinition) configAttrEditor.getValue();
		}

	}

	public Collection getConfigAttributeDefinitions() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean supports(Class arg0) {
		// TODO Auto-generated method stub
		return true;
	}


}
