package com.example.qingdao.base.compl.menu;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

public class TopicdetailPager extends basepagermenu {

	public TopicdetailPager(Activity activity) {
		super(activity);
		// TODO Auto-generated constructor stub
	}

	@Override
	public View initView() {
		//��䲼�ֶ���
		TextView textview = new TextView(mActivity);
		textview.setText("����");
		 textview.setTextColor(Color.RED);
		 textview.setTextSize(22);
		 textview.setGravity(Gravity.CENTER);
		return textview;
	}

}
