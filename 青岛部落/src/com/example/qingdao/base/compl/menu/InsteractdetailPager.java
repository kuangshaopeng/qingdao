package com.example.qingdao.base.compl.menu;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

public class InsteractdetailPager extends basepagermenu {

	public InsteractdetailPager(Activity activity) {
		super(activity);
		// TODO Auto-generated constructor stub
	}

	@Override
	public View initView() {
		// TODO Auto-generated method stub
		//��䲼�ֶ���
		TextView textview = new TextView(mActivity);
		textview.setText("����");
		 textview.setTextColor(Color.RED);
		 textview.setTextSize(22);
		 textview.setGravity(Gravity.CENTER);
		return textview;
	}

}
