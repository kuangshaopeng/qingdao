package com.example.qingdao.base.compl;

import com.example.qingdao.base.basepager;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;


//����homepager ����
public class smartPager extends basepager {

	public smartPager(Activity activity) {
		super(activity);
		// TODO Auto-generated constructor stub
	}
	
	public void initData() {
		//��䲼�ֶ���
		TextView textview = new TextView(mactivity);
		textview.setText("�ǻ�");
		 textview.setTextColor(Color.RED);
		 textview.setTextSize(22);
		 textview.setGravity(Gravity.CENTER);
		 flConten.addView(textview);
		 
		 //���ñ�����
		 tvTitle.setText("�ǻ�");
		 //��ʾ�˵���ť
		 imaButton.setVisibility(View.VISIBLE);
	
	}

}
