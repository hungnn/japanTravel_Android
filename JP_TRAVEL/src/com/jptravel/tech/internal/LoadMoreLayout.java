package com.jptravel.tech.internal;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.jp_travel.android.R;

public class LoadMoreLayout extends LinearLayout{

	public LoadMoreLayout(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		LayoutInflater.from(context).inflate(R.layout.loadmore, this);
	}
	

}
