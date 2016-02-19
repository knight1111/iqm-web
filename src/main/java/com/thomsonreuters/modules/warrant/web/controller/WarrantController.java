package com.thomsonreuters.modules.warrant.web.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.thomsonreuters.common.web.BaseController;
import com.thomsonreuters.modules.warrant.service.IWarrantService;

@Controller
@RequestMapping("/warrant")
public class WarrantController extends BaseController {
	
	@Resource
	private IWarrantService warrantService;
	
	@RequestMapping(value = "/gateway")
	public String gateway() {
		return "war.gateway";
	}
}
