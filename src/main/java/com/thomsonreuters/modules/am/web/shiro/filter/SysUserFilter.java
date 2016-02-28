package com.thomsonreuters.modules.am.web.shiro.filter;

import com.thomsonreuters.modules.am.Constants;
import com.thomsonreuters.modules.am.realm.UserRealm.Principal;
import com.thomsonreuters.modules.am.service.IUserService;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.filter.PathMatchingFilter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class SysUserFilter extends PathMatchingFilter {

	@Autowired
	private IUserService userService;

	@Override
	protected boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue)
			throws Exception {

		Principal p = (Principal) SecurityUtils.getSubject().getPrincipal();
		String username = p.getUsername();
		request.setAttribute(Constants.CURRENT_USER, userService.getByUsername(username));
		return true;
	}
}
