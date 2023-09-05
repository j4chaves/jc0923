package com.jc0923.toolrental.domain.converters;

import org.json.simple.JSONObject;

import com.jc0923.toolrental.domain.Tool;

public class ToolConverter {

	public Tool fromFile(JSONObject data) {
		Tool tool = new Tool();
		
		if (data != null && !data.isEmpty()) {
			String toolCode = (String) data.get("toolCode");
			String brand = (String) data.get("brand");
			
			tool.setToolCode(toolCode);
			tool.setBrand(brand);
		}
		
		return tool;
	}
}
