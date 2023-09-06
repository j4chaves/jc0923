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

	public static List<Tool> toolsList = new ArrayList<Tool>();

	public static void loadInventory() {

		// Load in tooltypes.json data
		JSONArray toolTypesJSONArray = JSONFileReader.readJsonFile("src/main/resources/ToolTypes.json");
		Map<String, ToolType> toolTypesMap = new HashMap<String, ToolType>();
		Iterator<JSONObject> toolTypeIterator = toolTypesJSONArray.iterator();
		while (toolTypeIterator.hasNext()) {
			JSONObject toolTypeJsonObject = (JSONObject) toolTypeIterator.next();
			ToolType toolType = ToolTypeConverter.fromFile(toolTypeJsonObject);
			toolTypesMap.put(toolType.getToolTypeName(), toolType);
		}

		// Load in tools.json data
		JSONArray toolsJSONArray = JSONFileReader.readJsonFile("src/main/resources/Tools.json");
		toolsList = new ArrayList<Tool>();
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
