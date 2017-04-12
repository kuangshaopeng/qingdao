package com.example.qingdao.Tool;

import android.content.Context;
import android.content.SharedPreferences;
import android.provider.Contacts;

//封装shareparefence
public class Tool {
//像这种要引用的都加static
public static boolean getBoolen(Context context ,String string,boolean defaultValue) {
	 //userDefault// 第二个参数为默认值
	SharedPreferences share= context.getSharedPreferences("config", Context.MODE_PRIVATE);
	 boolean isFirstEnter =  share.getBoolean(string, defaultValue);
	
	return isFirstEnter;
	
}	
public static void setBoolen(Context context ,String string,boolean defaultValue) {
	 //userDefault// 第一个参数存储在哪   第二个参数为默认值
	
	SharedPreferences share= context.getSharedPreferences("config", Context.MODE_PRIVATE);
	//先打开编辑，编辑完成， 在提交 
	share.edit().putBoolean(string, defaultValue).commit();
	

	
}	
public static String getString(Context context ,String string,String defaultValue) {
	 //userDefault// 第二个参数为默认值
	
	SharedPreferences share= context.getSharedPreferences("config", Context.MODE_PRIVATE);
	String isString =  share.getString(string, defaultValue);
	
	return isString;

	
}	
public static void setString(Context context ,String string,String defaultValue) {
	 //userDefault// 第二个参数为默认值
	
	SharedPreferences share= context.getSharedPreferences("config", Context.MODE_PRIVATE);
 share.edit().putString(string, defaultValue).commit();
	
	
	
}	
}
