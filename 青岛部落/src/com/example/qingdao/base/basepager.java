package com.example.qingdao.base;

import com.example.qingdao.R;
import com.example.qingdao.mainActivity;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

public class basepager {
	
	 public Activity mactivity;
	public TextView tvTitle;
	 public ImageButton imaButton;
	 public FrameLayout flConten;
	 public  View    toolView;
	public ImageButton collecBtn;
	public Button btn;
	 //��ʼ��
	public  basepager(Activity activity) {
	mactivity =	activity ;
	toolView =initView(); //��ǰҳ��Ĳ��ֶ���
	
	}
	
	
//��ʼ������ 
	public View initView() {

			View  view	=View.inflate(mactivity, R.layout.base_pager, null);
	   tvTitle =	(TextView) view.findViewById(R.id.tv_title);
		imaButton = (ImageButton) view.findViewById(R.id.btn_menu);
		flConten = (FrameLayout) view.findViewById(R.id.fl_content);
		collecBtn = (ImageButton) view.findViewById(R.id.btn_collection);
		
	   imaButton.setOnClickListener(new OnClickListener() {
	
		@Override
		public void onClick(View arg0) {
			mainActivity ac = (mainActivity) mactivity;
			SlidingMenu sliding = ac.getSlidingMenu();
			sliding.toggle();//�����ǰ�ǿ������ú���ǹ� ��֮��Ȼ
			
		}
	});
		
		return view;
	}
//��ʼ������
	public void initData(){
		
		
		
	}
//
	
}
