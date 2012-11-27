package com.jptravel.common;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;

import com.jptravel.entity.Area;

/**
 * JsonPaser class
 * 
 * @author DUYTX
 * 
 */
public class JsonPaser {

	public JsonPaser(Context context) {
	}

	

	/**
	 * No: 4 Name: Area List
	 * 
	 * @param json
	 * @return List Area Entity
	 */
	public ArrayList<Area> getListArea(String json) {
		ArrayList<Area> lstArea = new ArrayList<Area>();
		try {
			JSONObject jObject = new JSONObject(json);
			// Check status. If status is success ==> continue get data
			if (Constant.STATUS_SUCCESS.equals(jObject.getString((Constant.JSON_STATUS)))) {
				// Check data. If data <> null ==> Get area list
				if (jObject.opt(Constant.JSON_DATA) != null) {
					jObject = jObject.getJSONObject(Constant.JSON_DATA);
					JSONArray areaArray = jObject.getJSONArray("areas");
					JSONObject areaJson = null;
					Area area = null;
					// Create area list
					for (int i = 0; i < areaArray.length(); i++) {
						areaJson = areaArray.getJSONObject(i);
						area = new Area();
						if (areaJson.opt("id") != null) {
							area.setAreaId(areaJson.getInt("id"));
						}
						if (areaJson.opt("name") != null) {
							area.setAreaName(areaJson.getString("name"));
						}

						lstArea.add(area);
					}
				}
				
			}
		} catch (JSONException e) {
		}
		return lstArea;
	}
}
