package com.example.qingdao.Tool;

import android.content.Context;

//���绺��Ĺ�����
public class CacheTools {
	//һurl Ϊkey ��jonson Ϊvalue ���汾��
public static void setCashe(String url, String json, Context context){
	//����д���Ĺ����࣬�����ڱ���
	Tool.setString(context, url, json);
	
	
	
}

//��ȡ����

public static String getCache(String url ,Context context){
	
  return	Tool.getString(context, url, null);
	
}

}