package com.example.qingdao.base.compl;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

import com.example.qingdao.R;
import com.example.qingdao.base.basepager;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;


//����homepager ����
public class HomePager extends basepager {

	private Button btn;

	public HomePager(Activity activity) {
		super(activity);
		// TODO Auto-generated constructor stub
	}
	
	public void initData() {
		//��䲼�ֶ���
		TextView textview = new TextView(mactivity);
		textview.setText("��ҳ");
		 textview.setTextColor(Color.RED);
		 textview.setTextSize(22);
		 textview.setGravity(Gravity.CENTER);
		 flConten.addView(textview);
		 //���ñ�����
		 tvTitle.setText("��ҳ");
		 
		 
		 
		 //���ز˵���ť
		 imaButton.setVisibility(View.GONE);
		 
		 btn = new Button(mactivity);	
		 btn.setText("dsss");
		//brn.setGrav
		 btn.setTextColor(Color.BLUE);
		 flConten.addView(btn);
		 btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				ShareSDK.initSDK(mactivity);
				 OnekeyShare oks = new OnekeyShare();
				 //�ر�sso��Ȩ
				 oks.disableSSOWhenAuthorize(); 
			//oks.setTheme(OnekeyShareTheme.)
				// ����ʱNotification��ͼ�������  2.5.9�Ժ�İ汾�����ô˷���
				 //oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
				 // title���⣬ӡ��ʼǡ����䡢��Ϣ��΢�š���������QQ�ռ�ʹ��
				 oks.setTitle("sdsdsdsd");
				 // titleUrl�Ǳ�����������ӣ�������������QQ�ռ�ʹ��
				 oks.setTitleUrl("http://sharesdk.cn");
				 // text�Ƿ����ı�������ƽ̨����Ҫ����ֶ�
				 oks.setText("���Ƿ����ı�");
				 //��������ͼƬ������΢����������ͼƬ��Ҫͨ����˺�����߼�д��ӿڣ�������ע�͵���������΢��
				// oks.setImageUrl("http://f1.sharesdk.cn/imgs/2014/02/26/owWpLZo_638x960.jpg");
				 // imagePath��ͼƬ�ı���·����Linked-In�����ƽ̨��֧�ִ˲���
				 //oks.setImagePath("/sdcard/test.jpg");//ȷ��SDcard������ڴ���ͼƬ
				 // url����΢�ţ��������Ѻ�����Ȧ����ʹ��
				 oks.setUrl("http://sharesdk.cn");
				 // comment���Ҷ�������������ۣ�������������QQ�ռ�ʹ��
				 oks.setComment("���ǲ��������ı�");
				 // site�Ƿ�������ݵ���վ���ƣ�����QQ�ռ�ʹ��
				 oks.setSite("ssss");
				 // siteUrl�Ƿ�������ݵ���վ��ַ������QQ�ռ�ʹ��
				 oks.setSiteUrl("http://sharesdk.cn");

				// ��������GUI
				 oks.show(mactivity);
				
			}
		});
		 
	}
	
 
	

}
