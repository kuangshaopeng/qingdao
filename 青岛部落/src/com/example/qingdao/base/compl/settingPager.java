package com.example.qingdao.base.compl;

import com.example.qingdao.base.basepager;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;


//这是homepager 子类
public class settingPager extends basepager {

	public settingPager(Activity activity) {
		super(activity);
		// TODO Auto-generated constructor stub
	}
	
	public void initData() {
		//填充布局对象
		TextView textview = new TextView(mactivity);
		textview.setText("设置");
		 textview.setTextColor(Color.RED);
		 textview.setTextSize(22);
		 textview.setGravity(Gravity.CENTER);
		 flConten.addView(textview);
		 
		 //设置标题了
		 tvTitle.setText("设置");
		 //隐藏菜单按钮
		 imaButton.setVisibility(View.GONE);
	}

}
