package com.example.qingdao.Tool;

import android.content.Context;
import android.content.SharedPreferences;
import android.provider.Contacts;

//��װshareparefence
public class Tool {
//������Ҫ���õĶ���static
public static boolean getBoolen(Context context ,String string,boolean defaultValue) {
	 //userDefault// �ڶ�������ΪĬ��ֵ
	SharedPreferences share= context.getSharedPreferences("config", Context.MODE_PRIVATE);
	 boolean isFirstEnter =  share.getBoolean(string, defaultValue);
	
	return isFirstEnter;
	
}	
public static void setBoolen(Context context ,String string,boolean defaultValue) {
	 //userDefault// ��һ�������洢����   �ڶ�������ΪĬ��ֵ
	
	SharedPreferences share= context.getSharedPreferences("config", Context.MODE_PRIVATE);
	//�ȴ򿪱༭���༭��ɣ� ���ύ 
	share.edit().putBoolean(string, defaultValue).commit();
	

	
}	
public static String getString(Context context ,String string,String defaultValue) {
	 //userDefault// �ڶ�������ΪĬ��ֵ
	
	SharedPreferences share= context.getSharedPreferences("config", Context.MODE_PRIVATE);
	String isString =  share.getString(string, defaultValue);
	
	return isString;

	
}	
public static void setString(Context context ,String string,String defaultValue) {
	 //userDefault// �ڶ�������ΪĬ��ֵ
	
	SharedPreferences share= context.getSharedPreferences("config", Context.MODE_PRIVATE);
 share.edit().putString(string, defaultValue).commit();
	
	
	
}	
}
