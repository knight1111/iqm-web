package com.thomsonreuters.modules.am.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.thomsonreuters.common.web.BaseController;

@Controller
public class LoginController extends BaseController {
	/**
	 * Get verifyCode
	 */
	// @RequestMapping("/getVerifyCodeImage")
	// public void getVerifyCodeImage(HttpServletRequest request,
	// HttpServletResponse response) throws IOException {
	// //设置页面不缓存
	// response.setHeader("Pragma", "no-cache");
	// response.setHeader("Cache-Control", "no-cache");
	// response.setDateHeader("Expires", 0);
	// String verifyCode =
	// VerifyCodeUtil.generateTextCode(VerifyCodeUtil.TYPE_NUM_ONLY, 4, null);
	// //将验证码放到HttpSession里面
	// request.getSession().setAttribute("verifyCode", verifyCode);
	// System.out.println("本次生成的验证码为[" + verifyCode + "],已存放到HttpSession中");
	// //设置输出的内容的类型为JPEG图像
	// response.setContentType("image/jpeg");
	// BufferedImage bufferedImage =
	// VerifyCodeUtil.generateImageCode(verifyCode, 90, 30, 3, true,
	// Color.WHITE, Color.BLACK, null);
	// //写给浏览器
	// ImageIO.write(bufferedImage, "JPEG", response.getOutputStream());
	// }

	@RequestMapping(value = "/login")
	public String login(HttpServletRequest req, Model model) {
		return "login";
	}
	
	@RequestMapping(value = "/index/dashboard")
	public String index(HttpServletRequest req, Model model) {
		return "index.dashboard";
	}

	/**
	 * 用户登录
	 */
	@RequestMapping(value = "/loginAction")
	public String loginAction(HttpServletRequest request, Model model) {
		String resultPageURL = InternalResourceViewResolver.REDIRECT_URL_PREFIX
				+ "/";
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		System.err.println(username);
		System.err.println(password);
		// 获取HttpSession中的验证码
		// String verifyCode = (String) request.getSession().getAttribute(
		// "verifyCode");
		// 获取用户请求表单中输入的验证码
		// String submitCode = WebUtils.getCleanParam(request, "verifyCode");
		// System.out.println("用户[" + username + "]登录时输入的验证码为[" + submitCode +
		// "],HttpSession中的验证码为[" + verifyCode + "]");
		// if (StringUtils.isEmpty(submitCode) ||
		// !StringUtils.equals(verifyCode, submitCode.toLowerCase())){
		// request.setAttribute("message_login", "验证码不正确");
		// return resultPageURL;
		// }
		UsernamePasswordToken token = new UsernamePasswordToken(username,
				password);
		token.setRememberMe(true);
		System.out.println("为了验证登录用户而封装的token为"
				+ ReflectionToStringBuilder.toString(token,
						ToStringStyle.MULTI_LINE_STYLE));
		// Get Subject
		Subject currentUser = SecurityUtils.getSubject();
		System.out.println("对用户[" + username + "]进行登录验证..验证开始");
		try {
			currentUser.login(token);
			System.out.println("Login successfully.");
			resultPageURL += "index/dashboard";

			// 验证是否登录成功
			if (currentUser.isAuthenticated()) {
				System.out.println("用户[" + username
						+ "]登录认证通过(这里可以进行一些认证通过后的一些系统参数初始化操作)");
			} else {
				token.clear();
			}
			return resultPageURL;

		} catch (UnknownAccountException uae) {
			System.out.println("对用户[" + username + "]进行登录验证..验证未通过,未知账户");
			model.addAttribute("error", "未知账户");
		} catch (IncorrectCredentialsException ice) {
			System.out.println("对用户[" + username + "]进行登录验证..验证未通过,错误的凭证");
			ice.printStackTrace();
			model.addAttribute("error", "密码不正确");
		} catch (LockedAccountException lae) {
			System.out.println("对用户[" + username + "]进行登录验证..验证未通过,账户已锁定");
			model.addAttribute("error", "账户已锁定");
		} catch (ExcessiveAttemptsException eae) {
			System.out.println("对用户[" + username + "]进行登录验证..验证未通过,错误次数过多");
			model.addAttribute("error", "用户名或密码错误次数过多");
		} catch (AuthenticationException ae) {
			// 通过处理Shiro的运行时AuthenticationException就可以控制用户登录失败或密码错误时的情景
			System.out.println("对用户[" + username + "]进行登录验证..验证未通过,堆栈轨迹如下");
			ae.printStackTrace();
			model.addAttribute("error", "用户名或密码不正确");
		}
		return "login";
	}

	/**
	 * 用户登出
	 */
	@RequestMapping("/logout")
	public String logout() {
		SecurityUtils.getSubject().logout();
		return InternalResourceViewResolver.REDIRECT_URL_PREFIX + "/";
	}
}