/**
 * 
 */
package com.example.qingdao.base.compl.menu;

import android.app.Activity;
import android.view.View;

/**
 * @author kuang
 *
 */
public abstract class basepagermenu {

	public Activity mActivity;
	
 public basepagermenu(Activity activity) {
		// TODO Auto-generated constructor stub
	
	 mActivity =activity;
	 //创建view
	initView();
	}
//基类,让孩子们去写
public abstract View initView();
	
 //初始化数据
public void initData(){
	
	
}
 
 

}
