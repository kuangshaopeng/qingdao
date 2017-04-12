package com.example.qingdao.recever;

import org.json.JSONException;
import org.json.JSONObject;

import com.example.qingdao.mainActivity;

import cn.jpush.android.api.JPushInterface;
import android.R.string;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class Recever extends BroadcastReceiver {

	private static final String TAG = "Recever";

	@Override
	public void onReceive(Context context, Intent intent) {
		 Bundle bundle = intent.getExtras();
	        Log.d(TAG, "onReceive - " + intent.getAction());

	        if (JPushInterface.ACTION_REGISTRATION_ID.equals(intent.getAction())) {
	        }else if (JPushInterface.ACTION_MESSAGE_RECEIVED.equals(intent.getAction())) {
	            System.out.println("�յ����Զ�����Ϣ����Ϣ�����ǣ�" + bundle.getString(JPushInterface.EXTRA_MESSAGE));
	            // �Զ�����Ϣ����չʾ��֪ͨ������ȫҪ������д����ȥ����
	        } else if (JPushInterface.ACTION_NOTIFICATION_RECEIVED.equals(intent.getAction())) {
	            System.out.println("�յ���֪ͨ");
	            
	            bundle = intent.getExtras();
	            String title = bundle.getString(JPushInterface.EXTRA_NOTIFICATION_TITLE);
	            
	            
	            // �����������Щͳ�ƣ�������Щ��������
	        } else if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent.getAction())) {
	            System.out.println("�û��������֪ͨ");
	            // ����������Լ�д����ȥ�����û���������Ϊ
	            Intent i = new Intent(context, mainActivity.class);  //�Զ���򿪵Ľ���
	            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	            context.startActivity(i);
	            //���Ǹ����ֶλ�ȡ
	             bundle = intent.getExtras();
	            String type = bundle.getString(JPushInterface.EXTRA_EXTRA);
	            try {
					JSONObject jo = new JSONObject(type);
					String url = jo.getString("url");
					
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        
	        } else {
	            Log.d(TAG, "Unhandled intent - " + intent.getAction());
	  }
	}

	
	
}
