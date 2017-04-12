package com.example.qingdao.Fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class basefragment extends Fragment {
	public Activity     mActivty;

	//创建fragment
	 @Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	    mActivty = getActivity();//获取当前fragement所依据的activity
		
	}
	 //初始化fragment的布局
	  @Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = initView();
		return view;
	}
	 
	 //初始化布局，必须基于子类,整个类就变成抽象类了
     	public  abstract View initView();
	    public abstract void initData();
	
	
     @Override
     //fragment 所依赖的activity 的onCreat 方法执行结束
    public void onActivityCreated(Bundle savedInstanceState) {
    	// TODO Auto-generated method stub
    	super.onActivityCreated(savedInstanceState);
    	//在这个方法里初始化数据
    	initData();
    }
     
}
