package com.example.qingdao.base.compl.menu;

import java.util.ArrayList;

import com.example.qingdao.Tool.CacheTools;
import com.example.qingdao.base.compl.menu.PhotosdetailPager.PhotoAdpter;
import com.example.qingdao.base.compl.menu.PhotosdetailPager.ViewHoder;
import com.example.qingdao.domain.PhotoBen;
import com.example.qingdao.domain.PhotoBen.photoData;
import com.example.qingdao.globle.GlobleConstence;
import com.google.gson.Gson;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

import android.R;
import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Mytext extends Activity 
{
	private GridView  gvPhoto;
	private ArrayList<photoData> photoArr;
	private BitmapUtils mbitMap;
	private Button  qd;
	private Button  ph;
@Override
protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
 	setContentView(com.example.qingdao.R.layout.ksp);

	initView();
}
private void initView() {
	// TODO Auto-generated method stub
	View view  =  View.inflate(this, com.example.qingdao.R.layout.ksp, null);
	gvPhoto  = (GridView) view.findViewById(com.example.qingdao.R.id.gv_listksp);
	qd = (Button) view.findViewById(com.example.qingdao.R.id.btn_qd);
	ph = (Button) view.findViewById(com.example.qingdao.R.id.btn_ph);
	
}
private void initData(){
//	String  cash = CacheTools.getCache("图片", this);
//	if (!TextUtils.isEmpty(cache)) {
//		prossesData(cache);
//		
//	}
//	getDataFromServer()；
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
			CacheTools.setCashe(GlobleConstence.photoUrl, result, getApplicationContext());
		}

		@Override
		public void onFailure(HttpException error, String msg) {
			// TODO Auto-generated method stub
			Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
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
//lvPhoto.setAdapter(new PhotoAdpter());
//开始设置gvlist
gvPhoto.setAdapter(new PhotoAdpter());
}

class PhotoAdpter extends BaseAdapter{
	 //图片加载呀 只创建一次 所以写在构造方法里
	public PhotoAdpter(){
		
		mbitMap = new BitmapUtils(getApplicationContext());
		//mbitMap.configDefaultLoadingImage(R.drawable.pic_item_list_default);
		
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
//arg1 = View.inflate(this, R.layout.photo_detail, null);
//hoder = new ViewHoder();
//hoder.ivPic = (ImageView) arg1.findViewById(R.id.photoimg);
//hoder.text = (TextView) arg1.findViewById(R.id.titletext);
//arg1.setTag(hoder);

}else{

hoder = (ViewHoder) arg1.getTag();



}
//开始设置数据
photoData item = (photoData) getItem(arg0);

//hoder.text.setText(item.memo);
//System.out.println("sssssss"+item.img);
////图片
//		mbitMap.display(hoder.ivPic, item.img);
			return arg1;
	}
	
	
}
static class ViewHoder{
	public ImageView ivPic;
	public int id;
	public TextView  text;
	
}

}
