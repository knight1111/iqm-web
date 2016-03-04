package com.thomsonreuters.modules.warrant.web.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.thomsonreuters.common.web.BaseController;
import com.thomsonreuters.modules.warrant.service.IWarrantService;

@Controller
@RequestMapping("/warrant")
public class WarrantController extends BaseController {

	@Autowired
	private IWarrantService warrantService;

	@RequestMapping(value = "/gateway")
	public String gateway(Model model) {

		TagsModel tagsModel = new TagsModel();

		tagsModel.setUsername("aaa");
		tagsModel.setPassword("bbb");
		tagsModel.setTestBoolean(true);
		tagsModel.setSelectArray(new String[] { "arrayItem AAA" });
		tagsModel.setTestArray(new String[] { "arrayItem AAA", "arrayItem BBB",
				"arrayItem CCC" });
		tagsModel.setRadiobuttonId(1);
		tagsModel.setSelectId(2);
		tagsModel.setSelectIds(Arrays.asList(1, 2));
		Map<Integer, String> map = new HashMap<Integer, String>();
		map.put(1, "mapItem AAA");
		map.put(2, "mapItem BBB");
		map.put(3, "mapItem CCC");
		tagsModel.setTestMap(map);
		tagsModel.setRemark("Remark...");

		model.addAttribute("contentModel", tagsModel);

		return "war.gateway";
	}
}
