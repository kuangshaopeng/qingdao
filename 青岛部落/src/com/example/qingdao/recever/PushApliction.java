package com.example.qingdao.recever;

import cn.jpush.android.api.JPushInterface;
import android.app.Application;

public class PushApliction extends Application {   
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
	}
	
	
}
