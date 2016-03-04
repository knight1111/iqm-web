package com.thomsonreuters.modules.am.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.thomsonreuters.common.web.BaseController;
import com.thomsonreuters.modules.am.domain.Role;
import com.thomsonreuters.modules.am.service.IRoleService;

@Controller
@RequestMapping("/role")
public class RoleController extends BaseController {

	@Autowired
	private IRoleService roleService;
	
	@ResponseBody
	@RequestMapping(value = "/listAllRole", method = RequestMethod.POST)
	public List<Role> listAll() {
		return roleService.findList();
	}
}
