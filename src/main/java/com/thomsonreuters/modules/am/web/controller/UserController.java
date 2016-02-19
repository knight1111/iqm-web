package com.thomsonreuters.modules.am.web.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.thomsonreuters.common.web.BaseController;
import com.thomsonreuters.common.web.utils.DataTablesUtils;
import com.thomsonreuters.modules.am.domain.User;
import com.thomsonreuters.modules.am.service.IUserService;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

	@Resource
	private IUserService userService;

	@RequiresRoles("admin")
	@RequestMapping(value = "/list")
	public String index() { 
		return "um.listUser";
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

	@RequiresRoles("admin")
	@RequestMapping(value = "/{1}", method = RequestMethod.GET)
	public String showUser(@RequestParam String id, Model model) {
		int userId = Integer.parseInt(id);
		User user = this.userService.getUserById(userId);
		model.addAttribute("user", user);
		return "um.userPreference";
	}

}
