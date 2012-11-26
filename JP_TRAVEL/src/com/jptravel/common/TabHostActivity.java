package com.jptravel.common;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TabHost;

import com.jp_travel.android.R;
import com.jptravel.activity.myalbum.MyAlbumActivity;
import com.jptravel.activity.nearby.NearbyActivity;
import com.jptravel.activity.post.PostActivity;
import com.jptravel.activity.selectarea.SelectAreaActivity;
import com.jptravel.activity.timeline.TimelineActivity;

public class TabHostActivity extends TabActivity {
	protected TabHost tabHost;
	private Button selectAreaButton;
	private Button timelineButton;
	private Button postButton;
	private Button nearButton;
	private Button myAlbumButton;

	private static final int SELECT_AREA_TAB = 0;
	private static final int TIMELINE_TAB = 1;
	private static final int POST_TAB = 4;
	private static final int NEAR_HERE_TAB = 2;
	private static final int MY_ALBUM_TAB = 3;
	protected int CURRENT_TAB;
	public static LinearLayout llTab;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		try {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.common_tabhost);
			// Khởi tạo TabHost
			llTab = (LinearLayout) findViewById(R.id.tab_host);
			Intent mesh = new Intent();
			mesh.setClass(this, MyAlbumActivity.class);
			tabHost = getTabHost();
			tabHost.addTab(tabHost.newTabSpec("select_area").setIndicator("select_area")
					.setContent(new Intent(this, MyAlbumActivity.class)));
			tabHost.addTab(tabHost.newTabSpec("timeline").setIndicator("timeline")
					.setContent(new Intent(this, PostActivity.class)));

			tabHost.addTab(tabHost.newTabSpec("post").setIndicator("post")
					.setContent(new Intent(this, NearbyActivity.class)));

			tabHost.addTab(tabHost.newTabSpec("near_here").setIndicator("Snear_here")
					.setContent(new Intent(this, SelectAreaActivity.class)));
			
			tabHost.addTab(tabHost.newTabSpec("my_album").setIndicator("my_album")
					.setContent(new Intent(this, TimelineActivity.class)));
			
			selectAreaButton = (Button) findViewById(R.id.btn_select_area);
			timelineButton = (Button) findViewById(R.id.btn_timeline);
			postButton = (Button) findViewById(R.id.btn_post);
			nearButton = (Button) findViewById(R.id.btn_near_here);
			myAlbumButton = (Button) findViewById(R.id.btn_my_album);
			
			setBackgroundImage(SELECT_AREA_TAB);
			setButtonClickCallBack();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void showTabs() { 
		llTab.setVisibility(View.VISIBLE);
	}

	public static void hideTabs() {
		llTab.setVisibility(View.GONE);
	}

	public void setButtonClickCallBack() {
		selectAreaButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				tabHost.setCurrentTab(SELECT_AREA_TAB);
				setBackgroundImage(SELECT_AREA_TAB);
			}
		});
		timelineButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				tabHost.setCurrentTab(TIMELINE_TAB);
				setBackgroundImage(TIMELINE_TAB);
			}
		});

		postButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				tabHost.setCurrentTab(POST_TAB);
				setBackgroundImage(POST_TAB);
			}
		});

		nearButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				tabHost.setCurrentTab(NEAR_HERE_TAB);
				setBackgroundImage(NEAR_HERE_TAB);
			}
		});

		myAlbumButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				tabHost.setCurrentTab(MY_ALBUM_TAB);
				setBackgroundImage(MY_ALBUM_TAB);
			}
		});
	}

	public void switchTab(int TabId) {
		switch (TabId) {
		case SELECT_AREA_TAB:
			tabHost.setCurrentTab(SELECT_AREA_TAB);
			setBackgroundImage(SELECT_AREA_TAB);
			break;
		case TIMELINE_TAB:
			tabHost.setCurrentTab(TIMELINE_TAB);
			setBackgroundImage(TIMELINE_TAB);
			break;
		case NEAR_HERE_TAB:
			tabHost.setCurrentTab(NEAR_HERE_TAB);
			setBackgroundImage(NEAR_HERE_TAB);
			break;
		case MY_ALBUM_TAB:
			tabHost.setCurrentTab(MY_ALBUM_TAB);
			setBackgroundImage(MY_ALBUM_TAB);
			break;
		case POST_TAB:
			tabHost.setCurrentTab(POST_TAB);
			setBackgroundImage(POST_TAB);
			break;
		default:
			break;
		}

	}

	public void setBackgroundImage(int currentTab) {
		CURRENT_TAB = currentTab;
		// Reset all button background
		selectAreaButton.setBackgroundResource(R.drawable.common_tab_mesh);
		selectAreaButton.setTextColor(getResources().getColor(R.color.common_tab_unselect));

		timelineButton.setBackgroundResource(R.drawable.common_tab_trend);
		timelineButton.setTextColor(getResources().getColor(R.color.common_tab_unselect));

		postButton.setBackgroundResource(R.drawable.common_tab_shot);
		postButton.setTextColor(getResources().getColor(R.color.common_tab_unselect));

		nearButton.setBackgroundResource(R.drawable.common_tab_timeline);
		nearButton.setTextColor(getResources().getColor(R.color.common_tab_unselect));

		myAlbumButton.setBackgroundResource(R.drawable.common_tab_user);
		myAlbumButton.setTextColor(getResources().getColor(R.color.common_tab_unselect));

		// Set background for selected button
		switch (currentTab) {
		case SELECT_AREA_TAB:
			selectAreaButton.setBackgroundResource(R.drawable.common_tab_mesh_active);

			selectAreaButton.setTextColor(getResources().getColor(R.color.common_tab_selected));
			break;
		case TIMELINE_TAB:
			timelineButton.setBackgroundResource(R.drawable.common_tab_trend_active);
			timelineButton.setTextColor(getResources().getColor(R.color.common_tab_selected));
			break;
		case POST_TAB:
			postButton.setBackgroundResource(R.drawable.common_tab_shot_active);
			postButton.setTextColor(getResources().getColor(R.color.common_tab_selected));
			break;
		case NEAR_HERE_TAB:
			nearButton.setBackgroundResource(R.drawable.common_tab_timeline_active);
			nearButton.setTextColor(getResources().getColor(R.color.common_tab_selected));
			break;
		case MY_ALBUM_TAB:
			myAlbumButton.setBackgroundResource(R.drawable.common_tab_user_active);
			myAlbumButton.setTextColor(getResources().getColor(R.color.common_tab_selected));
			break;
		default:
			break;
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
	}

}