package com.jptravel.activity.myalbum;

import android.os.Bundle;
import android.util.Log;

import com.jptravel.common.BaseActivity;
import com.jptravel.common.Constant;
import com.jptravel.networkmanager.RestClient;
import com.jptravel.networkmanager.RestClient.RequestMethod;

public class MyAlbumActivity extends BaseActivity {
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//TODO : Sample connect network and must be remove later
		RestClient restClient = new RestClient(Constant.BASE_URL +  "places");
		restClient.addParam("area_id", "1");
		restClient.addParam("limit", "20");
		restClient.addParam("offset", "1");
		
		RestClient restClientPost = new RestClient(Constant.BASE_URL +  "bookmarks");
		restClientPost.addParam("bookmarkable_type", "post");
		restClientPost.addParam("bookmarkable_id", "2");
		restClientPost.addParam("access_token", "abcdef12345");
		
		try {
			restClient.execute(RequestMethod.GET);
			Log.d("KET QUA GET", restClient.getResponse());
			restClientPost.execute(RequestMethod.POST);
			Log.d("KET QUA POST  ", restClientPost.getResponse());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
