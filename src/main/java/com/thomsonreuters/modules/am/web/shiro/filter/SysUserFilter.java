package com.thomsonreuters.modules.am.web.shiro.filter;

import com.thomsonreuters.modules.am.Constants;
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

		String username = (String) SecurityUtils.getSubject().getPrincipal();
		request.setAttribute(Constants.CURRENT_USER, userService.findByUsername(username));
		return true;
	}
}
