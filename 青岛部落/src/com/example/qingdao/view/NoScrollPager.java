package com.example.qingdao.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.text.method.Touch;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class NoScrollPager extends ViewPager {
//��������Ĺ��췽�� ���л�soure  constructer using....
	public NoScrollPager(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public NoScrollPager(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
   @Override
	public boolean onTouchEvent(MotionEvent arg0) {
		//��д���� touch ʲôҲ����
		return true;
	}

   //�¼������жϣ�����jie
@Override
	public boolean onInterceptTouchEvent(MotionEvent arg0) {
		// TODO Auto-generated method stub
		return false;
	}
   
   
}
