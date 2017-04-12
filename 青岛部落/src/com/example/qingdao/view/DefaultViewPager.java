package com.example.qingdao.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
//ͷ�������Լ�����
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
	    //�Ȳ�Ҫ����
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
		//���һ���
		int currentItem = getCurrentItem();
		if(dx>0){
			//����hua��������	
			if(currentItem==0){
				getParent().requestDisallowInterceptTouchEvent(false);
			}
				
			
			
		}
		else{
			//�����������������һ����ʱ�� ����
			 //�õ��󶨵�����������
		if	(getAdapter().getCount()-1==currentItem){
			
			getParent().requestDisallowInterceptTouchEvent(false);
			
		}
		}
		
	}else{
		//���»���������
		getParent().requestDisallowInterceptTouchEvent(false);
		
	}
	      
			break;

		default:
			break;
		}
		return super.dispatchTouchEvent(ev);
	}
}
