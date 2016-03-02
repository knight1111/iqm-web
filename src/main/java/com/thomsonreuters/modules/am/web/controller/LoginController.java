package com.thomsonreuters.modules.am.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.thomsonreuters.common.utils.StringUtils;
import com.thomsonreuters.common.web.BaseController;

@Controller
public class LoginController extends BaseController {

	/**
	 * login
	 */
	@RequestMapping(value = "/login")
	public String login() {
		return "login";
	}
	
	@RequestMapping(value = "/angular")
	public String angular() {
		return "angular/user";
	}

	/**
	 * loginAction
	 */
	@RequestMapping(value = "/loginAction")
	public String loginAction(HttpServletRequest request, Model model) {
		String resultPageURL = InternalResourceViewResolver.REDIRECT_URL_PREFIX + "/";

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		token.setRememberMe(false);
		// Get Subject
		Subject currentUser = SecurityUtils.getSubject();
		logger.info("Start login validation for user [" + username + "]...");
		String error = null;
		try {
			currentUser.login(token);

			// User isAuthenticated?
			if (currentUser.isAuthenticated()) {
				logger.info("User [" + username + "] authenticated sucessfully.");
			} else {
				token.clear();
			}

			resultPageURL += "index/dashboard";
			return resultPageURL;
		} catch (UnknownAccountException uae) {
			error = "Unknown account: " + username;		
		} catch (IncorrectCredentialsException ice) {
			ice.printStackTrace();
			error = "Password incorrect";
		} catch (LockedAccountException lae) {
			error = "Account has been locked: " + username;
		} catch (ExcessiveAttemptsException eae) {
			error = "Too many failed attemp";
		} catch (AuthenticationException ae) {
			ae.printStackTrace();
			error = "Username or password incorrect";
		}
		if(!StringUtils.isEmpty(error)){
			logger.debug("User [" + username + "] login validation failed..." + error);
		}
		model.addAttribute("error", error);
		return "login";
	}

	/**
	 * logout
	 */
	@RequestMapping("/logout")
	public String logout() {
		SecurityUtils.getSubject().logout();
		return InternalResourceViewResolver.REDIRECT_URL_PREFIX + "/";
	}

	/**
	 * dashboard
	 */
	@RequestMapping(value = "/index/dashboard")
	public String dashboard() {
		return "index.dashboard";
	}
}