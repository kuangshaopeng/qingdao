package com.example.qingdao.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.text.method.Touch;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class NoScrollPager extends ViewPager {
//俩个父类的构造方法 。有机soure  constructer using....
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
		//重写方法 touch 什么也不做
		return true;
	}

   //事件拦截中断，不揽jie
@Override
	public boolean onInterceptTouchEvent(MotionEvent arg0) {
		// TODO Auto-generated method stub
		return false;
	}
   
   
}
