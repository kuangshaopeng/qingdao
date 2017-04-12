package com.example.qingdao.Tool;

import android.content.Context;

//网络缓存的工具类
public class CacheTools {
	//一url 为key 以jonson 为value 保存本地
public static void setCashe(String url, String json, Context context){
	//利用写过的工具类，储存在本地
	Tool.setString(context, url, json);
	
	
	
}

//获取缓存

public static String getCache(String url ,Context context){
	
  return	Tool.getString(context, url, null);
	
}

}