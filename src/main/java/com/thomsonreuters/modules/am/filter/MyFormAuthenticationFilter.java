package com.thomsonreuters.modules.am.filter;

import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class MyFormAuthenticationFilter extends FormAuthenticationFilter {

	@Override
	protected boolean onAccessDenied(ServletRequest request,
			ServletResponse response, Object mappedValue) throws Exception {
		if (request.getAttribute(getFailureKeyAttribute()) != null) {
			return true;
		}
		return super.onAccessDenied(request, response, mappedValue);
	}
}
