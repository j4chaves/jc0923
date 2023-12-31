package com.jc0923.toolrental.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.jc0923.toolrental.domain.Tool;
import com.jc0923.toolrental.domain.ToolType;
import com.jc0923.toolrental.domain.converters.ToolConverter;
import com.jc0923.toolrental.domain.converters.ToolTypeConverter;

public class Inventory {

	public static List<Tool> toolsList = new ArrayList<>();
	
	private Inventory() {}

	public static void loadInventory(String pathToResources) {

		// Load in tooltypes.json data
		JSONArray toolTypesJSONArray = JSONFileReader.readJsonFile(pathToResources + "ToolTypes.json");
		Map<String, ToolType> toolTypesMap = new HashMap<>();
		Iterator<JSONObject> toolTypeIterator = toolTypesJSONArray.iterator();
		while (toolTypeIterator.hasNext()) {
			JSONObject toolTypeJsonObject = (JSONObject) toolTypeIterator.next();
			ToolType toolType = ToolTypeConverter.fromFile(toolTypeJsonObject);
			toolTypesMap.put(toolType.getToolTypeName(), toolType);
		}

		// Load in tools.json data
		JSONArray toolsJSONArray = JSONFileReader.readJsonFile(pathToResources + "Tools.json");
		toolsList = new ArrayList<>();
		Iterator<JSONObject> toolIterator = toolsJSONArray.iterator();
		while (toolIterator.hasNext()) {
			JSONObject toolJsonObject = (JSONObject) toolIterator.next();

			String toolTypeString = (String) toolJsonObject.get("toolType");
			ToolType toolType = toolTypesMap.get(toolTypeString);

			Tool tool = new ToolConverter().fromFile(toolJsonObject);
			tool.setTooltype(toolType);
			toolsList.add(tool);
		}
	}
}
