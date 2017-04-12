package com.example.qingdao.base.compl;

import java.util.ArrayList;

import com.example.qingdao.mainActivity;
import com.example.qingdao.Fragment.leftfragment;
import com.example.qingdao.Tool.CacheTools;
import com.example.qingdao.base.basepager;
import com.example.qingdao.base.compl.menu.InsteractdetailPager;
import com.example.qingdao.base.compl.menu.NewsdetailPager;
import com.example.qingdao.base.compl.menu.PhotosdetailPager;
import com.example.qingdao.base.compl.menu.TopicdetailPager;
import com.example.qingdao.base.compl.menu.basepagermenu;
import com.example.qingdao.domain.NewsMenue;
import com.example.qingdao.domain.NewsMenue.NewMenuData;
import com.example.qingdao.globle.GlobleConstence;
import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

import android.app.Activity;
import android.drm.ProcessedData;
import android.graphics.Color;
import android.opengl.Visibility;
import android.provider.Settings.Global;
import android.text.InputFilter.LengthFilter;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


//����homepager ����
public class NewsPager extends basepager {

	private NewsMenue data;
	private ArrayList<basepagermenu> mMenuDetaiPagers;

	public NewsPager(Activity activity) {
		super(activity);
		// TODO Auto-generated constructor stub
	}
	
	public void initData() {
		//��䲼�ֶ���
//		TextView textview = new TextView(mactivity);
//		textview.setText("����");
//		 textview.setTextColor(Color.RED);
//		 textview.setTextSize(22);
//		 textview.setGravity(Gravity.CENTER);
//		 flConten.addView(textview);
		 
		 //���ñ�����
		 tvTitle.setText("����");
		 //��ʾ�˵���ť
		 imaButton.setVisibility(View.VISIBLE);
		 
		 String cash = CacheTools.getCache(GlobleConstence.newsDetailURl, mactivity);
		 if (!TextUtils.isEmpty(cash)) {
			
			 //���ֻ�����
			// processData(cash);
		}
		 
		 //��Դ���
		 getDataForSever();
		
	}
	//����
	protected void processData(String json) {
		// TODO Auto-generated method stub
		//Gson ����json
		Gson gson = new  Gson();
		data = gson.fromJson(json, NewsMenue.class);
		
//		for (int i = 0; i < 4; i++) {
//		       NewMenuData item =  data.data.get(i);
//			System.out.println("sdsdsdsdsds"+item.title);
//			//System.out.println("sdsdsdsdsds"+data.data);
//			}
	
	//��ȡ���������
	//mainActivity activty  = (mainActivity) mactivity;
	//leftfragment  leftFram =   activty.getLeftMenuFragment();
		mainActivity ac = (mainActivity) mactivity;
	
	//��ȡ����֮�� ����Ҫ���������ȡ����
		ac.getLeftMenuFragment().setManuData(data.data);
		
	mMenuDetaiPagers = new ArrayList<basepagermenu>();
	mMenuDetaiPagers.add(new  NewsdetailPager(mactivity));
	mMenuDetaiPagers.add(new  TopicdetailPager(mactivity));
	mMenuDetaiPagers.add(new  PhotosdetailPager(mactivity,collecBtn));
	mMenuDetaiPagers.add(new  InsteractdetailPager(mactivity));
	
	//
	setCurrentDetaiPager(0);
	
	
	}

	private void getDataForSever() {
		// TODO Auto-generated method stub
		HttpUtils utls = new HttpUtils();
		//��������  // ��Ҫһ���ص� 
		utls.send(HttpMethod.GET,GlobleConstence.newsDetailURl, new RequestCallBack<String>() {

			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				// TODO Auto-generated method stub
				//����ɹ�
				String result = responseInfo.result;
				System.out.println("���������ؽ��"+result);
				System.out.println("�ɹ�������eeeeee");
				
				 processData(result);
				 //����ɹ�д����
				 CacheTools.setCashe( GlobleConstence.newsDetailURl	,result, mactivity);
			}

		

			@Override
			public void onFailure(HttpException error, String msg) {
				//ʧ��
				// TODO Auto-generated method stub
				 error.printStackTrace();
				 Toast.makeText(mactivity, msg,  Toast.LENGTH_SHORT).show();
					System.out.println("ʧ������1111");
			}
			
			
		});
		
		
		
	}
  //�����������ĵ� ����ҳ��
	public void setCurrentDetaiPager(int posion) {
		 //���¸�framLayout�������
		basepagermenu pager = mMenuDetaiPagers.get(posion);
		View view = pager.initView();
		//���֮ǰ�ɵĲ���
	flConten .removeAllViews();
		///��ʼ������
		flConten.addView(view);
		//��ʼ������,������û ������һ��Ҫע��
		pager.initData();
		//�ñ�����
	String  str =	data.data.get(posion).title;
		tvTitle.setText(str);
		//��ʾ�Ǹ�button
		if (pager instanceof PhotosdetailPager) {
			//��ʾ
			collecBtn.setVisibility(View.VISIBLE);
		}else{
			//����
		
			collecBtn.setVisibility(View.GONE);
		}
	
		
		
	}
	
}
