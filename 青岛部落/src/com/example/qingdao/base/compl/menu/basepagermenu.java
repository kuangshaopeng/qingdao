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
	 //����view
	initView();
	}
//����,�ú�����ȥд
public abstract View initView();
	
 //��ʼ������
public void initData(){
	
	
}
 
 

}
