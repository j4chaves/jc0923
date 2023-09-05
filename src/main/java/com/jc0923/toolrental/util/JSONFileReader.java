package com.jc0923.toolrental.util;

import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSONFileReader {
	
	private static JSONParser jsonParser = new JSONParser();

	
	/**
	 * Parses a json file into a JSONArray Object.  If no json data is found, this method will return empty JSONArray object
	 * @param filename
	 * @return JSONArray object
	 */
	public static JSONArray readJsonFile(String filename) {
		JSONArray jsonArray = new JSONArray();
		
		if (filename != null && !filename.isEmpty()) {
			try (FileReader fileReader = new FileReader(filename)) {
				Object object = jsonParser.parse(fileReader);
				jsonArray = (JSONArray) object;
			} catch (IOException | ParseException e) {
				e.printStackTrace();
			}
		}
		
		return jsonArray;
	}
}
