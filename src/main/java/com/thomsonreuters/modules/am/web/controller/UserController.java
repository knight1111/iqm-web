package com.thomsonreuters.modules.am.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.thomsonreuters.common.config.GlobalConstants;
import com.thomsonreuters.common.utils.StringUtils;
import com.thomsonreuters.common.web.BaseController;
import com.thomsonreuters.common.web.bean.ResultBean;
import com.thomsonreuters.common.web.utils.DataTablesUtils;
import com.thomsonreuters.modules.am.domain.User;
import com.thomsonreuters.modules.am.realm.UserRealm.Principal;
import com.thomsonreuters.modules.am.service.IUserService;
import com.thomsonreuters.modules.am.service.PasswordHelper;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

	@Resource
	private IUserService userService;

	@Resource
	private PasswordHelper passwordHelper;

	@ModelAttribute
	public User get(@RequestParam(required = false) String id) {
		if (StringUtils.isNotBlank(id)) {
			return userService.get(Integer.valueOf(id));
		} else {
			return new User();
		}
	}

	@RequiresRoles("admin")
	@RequestMapping(value = "/list")
	public String index() {
		return "am.userList";
	}

	@ResponseBody
	@RequestMapping(value = "/listAllUser", method = RequestMethod.POST)
	public List<User> listAllUser() {
		return userService.findList(null);
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

		PageInfo<User> page = userService.findList(
				Integer.valueOf(iCurrentPage), Integer.valueOf(iDisplayLength),
				sSearch);
		count = (int) page.getTotal();
		initEcho = Integer.parseInt(sEcho) + 1;

		dataMap.put("sEcho", initEcho);
		dataMap.put("iTotalRecords", count);
		dataMap.put("iTotalDisplayRecords", count);
		dataMap.put("aaData", page.getList());

		return dataMap;
	}

	@RequestMapping(value = "/find", method = RequestMethod.GET)
	public String currentUser(Model model) {
		Principal p = (Principal) SecurityUtils.getSubject().getPrincipal();
		User user = userService.getByUsername(p.getUsername());
		model.addAttribute("user", user);
		return "am.userPreference";
	}

	@RequiresRoles("admin")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String findUser(@PathVariable("id") int userId, Model model) {
		User user = userService.get(userId);
		model.addAttribute("user", user);
		return "am.userPreference";
	}

	@RequiresRoles("admin")
	@RequestMapping(value = "/form")
	public String form(User user, Model model) {
		model.addAttribute("user", user);
		return "am.userForm";
	}

	// @RequiresRoles("admin")
	@ResponseBody
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ResultBean save(User user, Model model) {
		String newPwd = user.getNewPassword();
		if (StringUtils.isNotBlank(newPwd)) {
			user.setPassword(newPwd);
			passwordHelper.encryptPassword(user);
		}
		return userService.save(user);
	}

	@ResponseBody
	@RequestMapping(value = "/savePassword", method = RequestMethod.POST)
	public ResultBean savePassword(@RequestParam String oldPwd,
			@RequestParam String newPwd, Model model) {
		Principal p = (Principal) SecurityUtils.getSubject().getPrincipal();
		User user = userService.getByUsername(p.getUsername());

		String currPwd = user.getPassword();
		if (!currPwd.equals(passwordHelper.encryptPassword(oldPwd,
				user.getUsername() + user.getSalt()))) {
			return new ResultBean(GlobalConstants.FAILURE,
					"Old password is not correct!");
		}

		user.setPassword(newPwd);
		passwordHelper.encryptPassword(user);

		return userService.save(user);
	}

	@RequiresRoles("admin")
	@ResponseBody
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public ResultBean delete(@PathVariable("id") int userId, Model model) {
		return userService.delete(new User(userId));
	}

}
