package com.thomsonreuters.modules.am.web.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.thomsonreuters.common.utils.StringUtils;
import com.thomsonreuters.common.web.BaseController;
import com.thomsonreuters.common.web.utils.DataTablesUtils;
import com.thomsonreuters.modules.am.domain.User;
import com.thomsonreuters.modules.am.service.IUserService;
import com.thomsonreuters.modules.am.service.PasswordHelper;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

	@Resource
	private IUserService userService;
	
	@ModelAttribute
	public User get(@RequestParam(required=false) String id) {
		if (StringUtils.isNotBlank(id)){
			return userService.findUserById(Integer.valueOf(id));
		}else{
			return new User();
		}
	}

	@RequiresRoles("admin")
	@RequestMapping(value = "/list")
	public String index() { 
		return "am.userList";
	}

	@RequiresRoles("admin")
	@ResponseBody
	@RequestMapping(value = "/listUser", method = RequestMethod.POST)
	public Map<String, Object> listUser(@RequestParam String aoData)
			throws Exception {
		Map<String, Object> dataMap = new HashMap<String, Object>();

		Map<String, String> aoMap = DataTablesUtils.paramStringToMap(aoData);

		String sEcho = "0";
		String iCurrentPage = "1";
		String iDisplayLength = "10";
		String sSearch = null;
		int count = 0;
		int initEcho = 0;

		if (aoMap.size() > 0) {
			sEcho = aoMap.get("sEcho");
			iCurrentPage = aoMap.get("iCurrentPage");
			iDisplayLength = aoMap.get("iDisplayLength");
			sSearch = aoMap.get("sSearch");
		}

		PageHelper.startPage(Integer.valueOf(iCurrentPage),
				Integer.valueOf(iDisplayLength));

		PageInfo<User> page = new PageInfo<User>(userService.findUsers(sSearch));
		count = (int) page.getTotal();
		initEcho = Integer.parseInt(sEcho) + 1;

		dataMap.put("sEcho", initEcho);
		dataMap.put("iTotalRecords", count);
		dataMap.put("iTotalDisplayRecords", count);
		dataMap.put("aaData", page.getList());

		return dataMap;
	}
	
	@RequestMapping(value = "/find", method = RequestMethod.GET)
	public String currentUser(HttpServletRequest request, Model model) {
		Subject sub = SecurityUtils.getSubject();
		User user = userService.findByUsername((String)sub.getPrincipal());
		model.addAttribute("user", user);
		return "am.userPreference";
	}

	@RequiresRoles("admin")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String findUser(@PathVariable("id") int userId, Model model) {
		User user = userService.findUserById(userId);
		model.addAttribute("user", user);
		return "am.userPreference";
	}
	
	@RequiresRoles("admin")
	@RequestMapping(value = "/form")
	public String form(User user, Model model) {
		model.addAttribute("user", user);
		return "am.userForm";
	}
	
	@RequiresRoles("admin")
	@ResponseBody
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public int save(User user, Model model) {
		logger.info(user.toString());
		String newPwd = user.getNewPassword();
		if(StringUtils.isNotBlank(newPwd)){
			PasswordHelper ph = new PasswordHelper();
			user.setPassword(newPwd);
			ph.encryptPassword(user);
			logger.info(user.getPassword());
		}		
		return userService.save(user);
	}
	
	@RequiresRoles("admin")
	@ResponseBody
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public int delete(@PathVariable("id") int userId, Model model) {
		return userService.delete(userId);
	}

}
