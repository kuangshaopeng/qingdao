package com.example.qingdao.Fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class basefragment extends Fragment {
	public Activity     mActivty;

	//����fragment
	 @Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	    mActivty = getActivity();//��ȡ��ǰfragement�����ݵ�activity
		
	}
	 //��ʼ��fragment�Ĳ���
	  @Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = initView();
		return view;
	}
	 
	 //��ʼ�����֣������������,������ͱ�ɳ�������
     	public  abstract View initView();
	    public abstract void initData();
	
	
     @Override
     //fragment ��������activity ��onCreat ����ִ�н���
    public void onActivityCreated(Bundle savedInstanceState) {
    	// TODO Auto-generated method stub
    	super.onActivityCreated(savedInstanceState);
    	//������������ʼ������
    	initData();
    }
     
}
