package com.jptravel.activity.myalbum;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.jptravel.common.BaseActivity;
import com.jptravel.common.Constant;
import com.jptravel.common.JsonPaser;
import com.jptravel.entity.Area;
import com.jptravel.networkmanager.RestClient;
import com.jptravel.networkmanager.RestClient.RequestMethod;
import com.jptravel.tech.multithread.RequestUI;

public class MyAlbumActivity extends BaseActivity {
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//TODO : Sample connect network and must be remove later
		
		// Phuong thuc GET
		RestClient restClient = new RestClient(Constant.BASE_URL +  "areas"); 
//		restClient.addParam("area_id", "1");
//		restClient.addParam("limit", "20");
//		restClient.addParam("offset", "1");
		
		
		// Phuong thuc POST
		RestClient restClientPost = new RestClient(Constant.BASE_URL +  "bookmarks");
		restClientPost.addParam("bookmarkable_type", "post");
		restClientPost.addParam("bookmarkable_id", "2");
		restClientPost.addParam("access_token", "abcdef12345");
		
		try {
			restClient.execute(RequestMethod.GET);
			Log.d("KET QUA GET    ", restClient.getResponse());
			ArrayList<Area> lstArea = new ArrayList<Area>();
			JsonPaser jsonPaser = new JsonPaser(this);
			lstArea = jsonPaser.getListArea(restClient.getResponse());
			restClientPost.execute(RequestMethod.POST);
			Log.d("KET QUA POST    ", restClientPost.getResponse());
		} catch (Exception e) { 
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Xử lí đa luồng
//		UserStatusRequest request = new UserStatusRequest("MyAlbum", MyAlbumActivity.this);
//		getGlobalState().getRequestQueue().addRequest(request);
		
	}
	
	class UserStatusRequest extends RequestUI {
		private Activity context;

		public UserStatusRequest(Object key, Activity activity) {
			super(key, activity);
			Log.d("TEST", "Vao day UserStatusRequest");
			System.out.println("Vao day UserStatusRequest");
		}

		@Override
		public void execute() {
			System.out.println("Vao day execute");
			Log.d("TEST", "Vao day execute");
		}

		@Override
		public void executeUI(Exception ex) {
			Log.d("TEST", "Vao day executeUI");
			System.out.println("Vao day executeUI");
		}

	}

}
