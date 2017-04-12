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


//这是homepager 子类
public class NewsPager extends basepager {

	private NewsMenue data;
	private ArrayList<basepagermenu> mMenuDetaiPagers;

	public NewsPager(Activity activity) {
		super(activity);
		// TODO Auto-generated constructor stub
	}
	
	public void initData() {
		//填充布局对象
//		TextView textview = new TextView(mactivity);
//		textview.setText("新闻");
//		 textview.setTextColor(Color.RED);
//		 textview.setTextSize(22);
//		 textview.setGravity(Gravity.CENTER);
//		 flConten.addView(textview);
		 
		 //设置标题了
		 tvTitle.setText("新闻");
		 //显示菜单按钮
		 imaButton.setVisibility(View.VISIBLE);
		 
		 String cash = CacheTools.getCache(GlobleConstence.newsDetailURl, mactivity);
		 if (!TextUtils.isEmpty(cash)) {
			
			 //发现缓存了
			// processData(cash);
		}
		 
		 //开源框架
		 getDataForSever();
		
	}
	//解析
	protected void processData(String json) {
		// TODO Auto-generated method stub
		//Gson 解析json
		Gson gson = new  Gson();
		data = gson.fromJson(json, NewsMenue.class);
		
//		for (int i = 0; i < 4; i++) {
//		       NewMenuData item =  data.data.get(i);
//			System.out.println("sdsdsdsdsds"+item.title);
//			//System.out.println("sdsdsdsdsds"+data.data);
//			}
	
	//获取侧边栏对象
	//mainActivity activty  = (mainActivity) mactivity;
	//leftfragment  leftFram =   activty.getLeftMenuFragment();
		mainActivity ac = (mainActivity) mactivity;
	
	//获取到了之后 就是要给侧边栏获取数据
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
		//发送请求  // 需要一个回调 
		utls.send(HttpMethod.GET,GlobleConstence.newsDetailURl, new RequestCallBack<String>() {

			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				// TODO Auto-generated method stub
				//请求成功
				String result = responseInfo.result;
				System.out.println("服务器返回结果"+result);
				System.out.println("成功了了亲eeeeee");
				
				 processData(result);
				 //请求成功写缓存
				 CacheTools.setCashe( GlobleConstence.newsDetailURl	,result, mactivity);
			}

		

			@Override
			public void onFailure(HttpException error, String msg) {
				//失败
				// TODO Auto-generated method stub
				 error.printStackTrace();
				 Toast.makeText(mactivity, msg,  Toast.LENGTH_SHORT).show();
					System.out.println("失败了亲1111");
			}
			
			
		});
		
		
		
	}
  //设置新闻中心的 详情页面
	public void setCurrentDetaiPager(int posion) {
		 //重新给framLayout添加内容
		basepagermenu pager = mMenuDetaiPagers.get(posion);
		View view = pager.initView();
		//清除之前旧的布局
	flConten .removeAllViews();
		///初始化布局
		flConten.addView(view);
		//初始化数据,看见了没 在这里一定要注意
		pager.initData();
		//该标题栏
	String  str =	data.data.get(posion).title;
		tvTitle.setText(str);
		//显示那个button
		if (pager instanceof PhotosdetailPager) {
			//显示
			collecBtn.setVisibility(View.VISIBLE);
		}else{
			//隐藏
		
			collecBtn.setVisibility(View.GONE);
		}
	
		
		
	}
	
}
