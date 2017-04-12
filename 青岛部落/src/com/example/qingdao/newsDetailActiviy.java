package com.example.qingdao;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;
import cn.sharesdk.onekeyshare.OnekeyShareTheme;

import com.example.qingdao.R.string;
import com.example.qingdao.base.compl.menu.tagMenuPager;
import com.example.qingdao.globle.GlobleConstence;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.Settings.Global;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebSettings.TextSize;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

public class newsDetailActiviy extends Activity implements OnClickListener{
//	@ViewInject(R.id.ll_container)
	private LinearLayout llControl;
//	@ViewInject(R.id.btn_back)
	private ImageButton btnBack;
//	@ViewInject(R.id.btn_toText)
	private ImageButton btnTextSize;
//	@ViewInject(R.id.btn_share)
	private ImageButton btnShare;
//	@ViewInject(R.id.btn_menu)
	private ImageButton btnMenu; 
//	@ViewInject(R.id.webview)
	private WebView myWeb;
	
	
 private WebView ve;
private ProgressBar bar;



@Override
protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	
	//����
	requestWindowFeature(Window.FEATURE_NO_TITLE);
	//���� �ӿ�
		setContentView(R.layout.news_detail_activity);
	ve = (WebView) findViewById(R.id.webview);
	llControl = (LinearLayout) findViewById(R.id.linyout);
	btnBack = (ImageButton) findViewById(R.id.btn_back);
	btnTextSize = (ImageButton) findViewById(R.id.btn_toText);
	btnShare =(ImageButton) findViewById(R.id.btn_share);
	btnMenu =(ImageButton) findViewById(R.id.btn_menu);
	myWeb = (WebView) findViewById(R.id.webview);
	bar = (ProgressBar) findViewById(R.id.pb_Loading);
		
	//ViewUtils.inject(this);
	llControl.setVisibility(View.VISIBLE);
	btnMenu.setVisibility(View.GONE);
	btnBack .setVisibility(View.VISIBLE);
	
	//����listener
	btnBack.setOnClickListener(this);
	btnShare.setOnClickListener(this);
	btnTextSize.setOnClickListener(this);
	
//	//topic_id=5803017
	//http://club.qingdaonews.com/showAnnounce_2_5803509_1_0.htm
	//http://club.qingdaonews.com/showAnnounce_2_5803520_1_0.htm
	Bundle  budle = getIntent().getExtras();
	String id =  budle.getString("ID");
//	String str = GlobleConstence.newsUrl4 +"topic_id=" +id;
	//System.out.println("���p7"+ str);
	ve.loadUrl(id);
	//����vebview ��setting 
	WebSettings settings =myWeb.getSettings();
	//��ʾ���Ű�ť
	settings.setBuiltInZoomControls(true);
	//˫��
	settings.setUseWideViewPort(true);
	//js ��js
	settings.setJavaScriptEnabled(true);
	//��web�����ؼ���
	myWeb.setWebViewClient(new WebViewClient(){
		@Override
		public void onPageStarted(WebView view, String url, Bitmap favicon) {
			// TODO Auto-generated method stub
			super.onPageStarted(view, url, favicon);
			System.out.println("��ʼ����");
			bar.setVisibility(View.VISIBLE);
		} 
		@Override
		public void onPageFinished(WebView view, String url) {
			// TODO Auto-generated method stub
			super.onPageFinished(view, url);
			System.out.println("��ҳ���ؽ���");
			bar.setVisibility(View.INVISIBLE);
		}
		//���е����ӵ���ת��������ҳ��
		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			view.loadUrl(url);//����ת����ʱǿ���ڵ�ǰ��webview�м�������ؼ�
			
			return true;
		}
		
	
		
		
	});
	
//	myWeb.goBack();//������һ��ҳ��
	//myWeb.goForward();//�����¸�ҳ��
	myWeb.setWebChromeClient(new WebChromeClient(){
		
		@Override
		public void onProgressChanged(WebView view, int newProgress) {
	    
			super.onProgressChanged(view, newProgress);
		//���ȷ����仯
			
		
		}
		@Override
		public void onReceivedTitle(WebView view, String title) {
			// TODO Auto-generated method stub
			super.onReceivedTitle(view, title);
			//��ҳ����
		}
		
	});
	
	
}

@Override
public void onClick(View arg0) {
	switch (arg0.getId()) {
	case R.id.btn_back:
		finish();
		break;
	case R.id.btn_share:
		showShare();
		break;
	case R.id.btn_toText:
		//�޸������С
		showChoseDialog();
	
	
		break;
	default:
		break;
	}
	
}

private  int  mWitch; 
//��ס����ѡ��,��ǰ�����С
private int mCurrent =2;

private void showChoseDialog() {
	// TODO Auto-generated method stub
	AlertDialog.Builder builder = new AlertDialog.Builder(this);
	builder.setTitle("��������");
	String[] items = new String[]{"��������","�������","��������","С������","��С������"};
	builder.setSingleChoiceItems(items, mCurrent, new DialogInterface.OnClickListener() {

		@Override
		public void onClick(DialogInterface arg0, int arg1) {
			// TODO Auto-generated method stub
			mWitch =arg1;
		}
		

	
		

	});
	
	//��ӵ��ȷ����ȡ����ť
	builder.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {
	
		
		@Override
		public void onClick(DialogInterface arg0, int arg1) {
			WebSettings setting  = myWeb.getSettings();
			switch (mWitch) {
			case 0:
			setting.setTextSize(TextSize.LARGEST);
				break;
	case 1:
		setting.setTextSize(TextSize.LARGER);
				break;
	case 2:
		setting.setTextSize(TextSize.NORMAL);
		break;
	case 3:
		setting.setTextSize(TextSize.SMALLER);
		break;
	case 4:
		setting.setTextSize(TextSize.SMALLEST);
		break;

			default:
				break;
			}
			
			mCurrent =mWitch;
					
			
		}
	});
	
	builder.setNegativeButton("ȡ��", null);
	builder.show();
	
} 
private void showShare() {
	 ShareSDK.initSDK(this);
	 OnekeyShare oks = new OnekeyShare();
	 //�ر�sso��Ȩ
	 oks.disableSSOWhenAuthorize(); 
//oks.setTheme(OnekeyShareTheme.)
	// ����ʱNotification��ͼ�������  2.5.9�Ժ�İ汾�����ô˷���
	 //oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
	 // title���⣬ӡ��ʼǡ����䡢��Ϣ��΢�š���������QQ�ռ�ʹ��
	 oks.setTitle(getString(R.string.ssdk_share_to_qq));
	 // titleUrl�Ǳ�����������ӣ�������������QQ�ռ�ʹ��
	 oks.setTitleUrl("http://sharesdk.cn");
	 // text�Ƿ����ı�������ƽ̨����Ҫ����ֶ�
	 oks.setText("���Ƿ����ı�");
	 //��������ͼƬ������΢����������ͼƬ��Ҫͨ����˺�����߼�д��ӿڣ�������ע�͵���������΢��
	 oks.setImageUrl("http://f1.sharesdk.cn/imgs/2014/02/26/owWpLZo_638x960.jpg");
	 // imagePath��ͼƬ�ı���·����Linked-In�����ƽ̨��֧�ִ˲���
	 //oks.setImagePath("/sdcard/test.jpg");//ȷ��SDcard������ڴ���ͼƬ
	 // url����΢�ţ��������Ѻ�����Ȧ����ʹ��
	 oks.setUrl("http://sharesdk.cn");
	 // comment���Ҷ�������������ۣ�������������QQ�ռ�ʹ��
	 oks.setComment("���ǲ��������ı�");
	 // site�Ƿ�������ݵ���վ���ƣ�����QQ�ռ�ʹ��
	 oks.setSite(getString(R.string.app_name));
	 // siteUrl�Ƿ�������ݵ���վ��ַ������QQ�ռ�ʹ��
	 oks.setSiteUrl("http://sharesdk.cn");

	// ��������GUI
	 oks.show(this);
	 }
}
