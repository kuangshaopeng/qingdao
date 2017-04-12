package com.example.qingdao;

import com.example.qingdao.Fragment.contentfragment;
import com.example.qingdao.Fragment.leftfragment;
import com.example.qingdao.base.compl.menu.basepagermenu;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingActivity;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Window;
import android.view.WindowManager;

import java.util.ArrayList;

public class mainActivity extends SlidingFragmentActivity{
private static final String Tag_leftLatout = "TAG_LEFT_MENU";
private static final String Tag_Content = "TAG_CONTENT";

@Override
public void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);



	// 去掉标题, 必须在setContentView之前执行
	requestWindowFeature(Window.FEATURE_NO_TITLE);
	//??????????????????setContentView???°????????????
	setContentView(R.layout.activity_main);
	// 添加侧边栏
	setBehindContentView(R.layout.left_layout);
	SlidingMenu sildingMenu = getSlidingMenu();
	
	
	
	sildingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
	// 全屏触摸  // 屏幕预留200像素
	sildingMenu.setBehindOffset(200);
	
	WindowManager wm = getWindowManager();
int width =	wm.getDefaultDisplay().getWidth();
	wm.getDefaultDisplay().getHeight();
	sildingMenu.setBehindOffset(200*width/320);
	
	initfragment();
}

private void initfragment() {
	// Fragment管理器
FragmentManager fm =getSupportFragmentManager();
FragmentTransaction translation = fm.beginTransaction();//????????????????????????
translation.replace(R.id.leftlayout, new leftfragment(),Tag_leftLatout);//??????fragment ??????????????????
 translation.replace(R.id.mainActivty, new contentfragment(),Tag_Content);
  translation.commit();


}
// ???????????????????????// ?????????????±????????????static  ?????????±????????????
public  leftfragment getLeftMenuFragment(){
	
	FragmentManager manger = getSupportFragmentManager();
leftfragment fragment =	 (leftfragment) manger.findFragmentByTag(Tag_leftLatout);
return fragment;
	
}
//????????contentfragment
public  contentfragment getContenMenuFragment(){
	
	FragmentManager manger = getSupportFragmentManager();
contentfragment fragment =	 (contentfragment) manger.findFragmentByTag(Tag_Content);
return fragment;
	
}

}
