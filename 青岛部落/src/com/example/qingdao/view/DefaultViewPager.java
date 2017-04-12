package com.example.qingdao.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
//头条新闻自己定义
public class DefaultViewPager extends ViewPager {

	private int startx;
	private int starty;
	private int endX;
	private int endY;

	public DefaultViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public DefaultViewPager(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
	    //先不要拦截
		getParent().requestDisallowInterceptTouchEvent(true);
		
		switch (ev.getAction()) {
		case MotionEvent.ACTION_DOWN:
			startx = (int) ev.getX();
			starty = (int) ev.getY();
			
			
			break;
case MotionEvent.ACTION_MOVE:
			endX = (int) ev.getX();	
			endY = (int) ev.getY();
	      int dx  = endX -startx;
	      int dy  = endY - starty;
	if (Math.abs(dy)<Math.abs(dx)) {
		//左右滑动
		int currentItem = getCurrentItem();
		if(dx>0){
			//向右hua，当等于	
			if(currentItem==0){
				getParent().requestDisallowInterceptTouchEvent(false);
			}
				
			
			
		}
		else{
			//向作画，当等于最后一个的时候 拦截
			 //拿到绑定的数据适配器
		if	(getAdapter().getCount()-1==currentItem){
			
			getParent().requestDisallowInterceptTouchEvent(false);
			
		}
		}
		
	}else{
		//上下滑动，拦截
		getParent().requestDisallowInterceptTouchEvent(false);
		
	}
	      
			break;

		default:
			break;
		}
		return super.dispatchTouchEvent(ev);
	}
}
