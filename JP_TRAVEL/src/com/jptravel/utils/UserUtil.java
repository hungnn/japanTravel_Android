package com.jptravel.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.jptravel.common.Constant;
import com.jptravel.common.JsonPaser;
import com.jptravel.entity.User;

public class UserUtil {

	/**
	 * Get user info save in Share Prefrences
	 * @param ctx
	 * @return User entity
	 */
	public static User getInfoUserLogin(Context ctx){
		User user = new User();
		SharedPreferences settings = ctx.getSharedPreferences(
				Constant.PREFS_USER_INFO, 0);
		String userInfo = settings.getString(Constant.USER_INFO, "");
		JsonPaser jsonPaser = new JsonPaser(ctx);
		if (userInfo != null) {
			//user = jsonPaser.getUserInfo(userInfo);
		}
		
		return user;
	}
	
	/**
	 * Get user access token
	 * @param ctx
	 * @return user access token
	 */
	public static String getAccessToken(Context ctx) {
		User user = getInfoUserLogin(ctx);
		String accessToken = null;
		if (user != null) {
			accessToken = user.getAccessToken();
		}
		return accessToken;
	}
	
	public static void logoutUser(Context ctx){
		SharedPreferences settings = ctx.getSharedPreferences(Constant.PREFS_USER_INFO, 0);
		SharedPreferences.Editor edit = settings.edit();
		edit.clear().commit();
	}
	
	
}
