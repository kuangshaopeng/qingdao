package com.example.qingdao.base.compl.menu;

import java.util.ArrayList;

import com.example.qingdao.R;
import com.example.qingdao.Tool.CacheTools;
import com.example.qingdao.domain.PhotoBen;
import com.example.qingdao.domain.PhotoBen.photoData;
import com.example.qingdao.globle.GlobleConstence;
import com.google.gson.Gson;
import com.google.gson.internal.bind.ReflectiveTypeAdapterFactory.Adapter;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.lidroid.xutils.view.annotation.ViewInject;

import android.app.Activity;
import android.drm.ProcessedData;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.provider.Settings.Global;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class PhotosdetailPager extends basepagermenu implements OnClickListener{
	
private ListView  lvPhoto;
private ImageButton collecBtn1;
private GridView  gvPhoto;
private ArrayList<photoData> photoArr;
private BitmapUtils mbitMap;
	
	
	public PhotosdetailPager(Activity activity, ImageButton collecBtn) {
		super(activity);
		// TODO Auto-generated constructor stub
	collecBtn1 =collecBtn;
	
	collecBtn1.setOnClickListener(this);
	
	}

	@Override
	public View initView() {
		
		
		
		View view = View.inflate(mActivity, R.layout.page_photos_menu_detail, null);
		//ViewUtils.inject(mActivity,view);
		lvPhoto=	(ListView) view.findViewById(R.id.lv_list);
		gvPhoto  = (GridView) view.findViewById(R.id.gv_list);
		System.out.println("1111111111");
		return view;
	}

	
	@Override
	public void initData() {
		//家缓存 ,去缓存
	String  cache =	CacheTools.getCache(GlobleConstence.photoUrl, mActivity);
	if (!TextUtils.isEmpty(cache)) {
		prossesData(cache);
		
	}
		
		// TODO Auto-generated method stub
		super.initData();
		
		getDataFromServer();
		
	}

	private void getDataFromServer() {
		HttpUtils utils = new HttpUtils();
		utils.send(HttpMethod.GET, GlobleConstence.photoUrl, new RequestCallBack<String>() {

			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				// TODO Auto-generated method stub
				String  result = responseInfo.result;
				prossesData(result);
				//设置缓存
				CacheTools.setCashe(GlobleConstence.photoUrl, result, mActivity);
			}

			@Override
			public void onFailure(HttpException error, String msg) {
				// TODO Auto-generated method stub
				Toast.makeText(mActivity, msg, Toast.LENGTH_SHORT).show();
			}
			
		});
		
	}

	protected void prossesData(String result) {
		// TODO Auto-generated method stub
		Gson gson = new Gson();
		//需要对象
	PhotoBen ben=	gson.fromJson(result, PhotoBen.class);
	
	System.out.println("1111111112");

	photoArr = ben.data;
	System.out.println("111111111ssss2"+ben.data.get(1).memo);
	//设置list
	lvPhoto.setAdapter(new PhotoAdpter());
	//开始设置gvlist
	gvPhoto.setAdapter(new PhotoAdpter());
	}
	

	
	
	//开始设置
	 class PhotoAdpter extends BaseAdapter{

		
		 //图片加载呀 只创建一次 所以写在构造方法里
		public PhotoAdpter(){
			
			mbitMap = new BitmapUtils(mActivity);
			mbitMap.configDefaultLoadingImage(R.drawable.pic_item_list_default);
			
		}
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return photoArr.size();
		}

		@Override
		public Object getItem(int arg0) {
			// TODO Auto-generated method stub
			return photoArr.get(arg0);
		}

		@Override
		public long getItemId(int arg0) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int arg0, View arg1, ViewGroup arg2) {
		//创建listviewitem吧
			ViewHoder hoder;
if (arg1==null) {
	arg1 = View.inflate(mActivity, R.layout.photo_detail, null);
   hoder = new ViewHoder();
   hoder.ivPic = (ImageView) arg1.findViewById(R.id.photoimg);
   hoder.text = (TextView) arg1.findViewById(R.id.titletext);
   arg1.setTag(hoder);

}else{
	
	hoder = (ViewHoder) arg1.getTag();
	
	
	
}
			
//开始设置数据
   photoData item = (photoData) getItem(arg0);
  
   hoder.text.setText(item.memo);
   System.out.println("sssssss"+item.img);
//图片
		mbitMap.display(hoder.ivPic, item.img);
			return arg1;
		}
		
		 
		 
	 }
	
	static class ViewHoder{
		public ImageView ivPic;
		public int id;
		public TextView  text;
		
	}

	private boolean isListView =true;

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		if (isListView) {
			lvPhoto.setVisibility(View.GONE);
			gvPhoto.setVisibility(View.VISIBLE);
			isListView =false; 
			collecBtn1.setImageResource(R.drawable.icon_pic_list_type);
		}else{
			lvPhoto.setVisibility(View.VISIBLE);
			gvPhoto.setVisibility(View.GONE);
			isListView =true;
			collecBtn1.setImageResource(R.drawable.icon_pic_grid_type);
			
			
		}
		
		
		
		
	}
}
