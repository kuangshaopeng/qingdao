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
	
	//隐藏
	requestWindowFeature(Window.FEATURE_NO_TITLE);
	//设置 接口
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
	
	//设置listener
	btnBack.setOnClickListener(this);
	btnShare.setOnClickListener(this);
	btnTextSize.setOnClickListener(this);
	
//	//topic_id=5803017
	//http://club.qingdaonews.com/showAnnounce_2_5803509_1_0.htm
	//http://club.qingdaonews.com/showAnnounce_2_5803520_1_0.htm
	Bundle  budle = getIntent().getExtras();
	String id =  budle.getString("ID");
//	String str = GlobleConstence.newsUrl4 +"topic_id=" +id;
	//System.out.println("你好p7"+ str);
	ve.loadUrl(id);
	//创建vebview 的setting 
	WebSettings settings =myWeb.getSettings();
	//显示缩放按钮
	settings.setBuiltInZoomControls(true);
	//双击
	settings.setUseWideViewPort(true);
	//js 打开js
	settings.setJavaScriptEnabled(true);
	//对web的拦截监听
	myWeb.setWebViewClient(new WebViewClient(){
		@Override
		public void onPageStarted(WebView view, String url, Bitmap favicon) {
			// TODO Auto-generated method stub
			super.onPageStarted(view, url, favicon);
			System.out.println("开始加载");
			bar.setVisibility(View.VISIBLE);
		} 
		@Override
		public void onPageFinished(WebView view, String url) {
			// TODO Auto-generated method stub
			super.onPageFinished(view, url);
			System.out.println("网页加载结束");
			bar.setVisibility(View.INVISIBLE);
		}
		//所有的链接的跳转都会走这页面
		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			view.loadUrl(url);//在跳转链接时强制在当前的webview中加载这个关键
			
			return true;
		}
		
	
		
		
	});
	
//	myWeb.goBack();//调到上一个页面
	//myWeb.goForward();//调到下个页面
	myWeb.setWebChromeClient(new WebChromeClient(){
		
		@Override
		public void onProgressChanged(WebView view, int newProgress) {
	    
			super.onProgressChanged(view, newProgress);
		//进度发生变化
			
		
		}
		@Override
		public void onReceivedTitle(WebView view, String title) {
			// TODO Auto-generated method stub
			super.onReceivedTitle(view, title);
			//网页标题
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
		//修改字体大小
		showChoseDialog();
	
	
		break;
	default:
		break;
	}
	
}

private  int  mWitch; 
//记住最终选择,当前字体大小
private int mCurrent =2;

private void showChoseDialog() {
	// TODO Auto-generated method stub
	AlertDialog.Builder builder = new AlertDialog.Builder(this);
	builder.setTitle("字体设置");
	String[] items = new String[]{"超大字体","大号字体","正常字体","小号字体","超小号字体"};
	builder.setSingleChoiceItems(items, mCurrent, new DialogInterface.OnClickListener() {

		@Override
		public void onClick(DialogInterface arg0, int arg1) {
			// TODO Auto-generated method stub
			mWitch =arg1;
		}
		

	
		

	});
	
	//添加点击确定和取消按钮
	builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
	
		
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
	
	builder.setNegativeButton("取消", null);
	builder.show();
	
} 
private void showShare() {
	 ShareSDK.initSDK(this);
	 OnekeyShare oks = new OnekeyShare();
	 //关闭sso授权
	 oks.disableSSOWhenAuthorize(); 
//oks.setTheme(OnekeyShareTheme.)
	// 分享时Notification的图标和文字  2.5.9以后的版本不调用此方法
	 //oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
	 // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
	 oks.setTitle(getString(R.string.ssdk_share_to_qq));
	 // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
	 oks.setTitleUrl("http://sharesdk.cn");
	 // text是分享文本，所有平台都需要这个字段
	 oks.setText("我是分享文本");
	 //分享网络图片，新浪微博分享网络图片需要通过审核后申请高级写入接口，否则请注释掉测试新浪微博
	 oks.setImageUrl("http://f1.sharesdk.cn/imgs/2014/02/26/owWpLZo_638x960.jpg");
	 // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
	 //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
	 // url仅在微信（包括好友和朋友圈）中使用
	 oks.setUrl("http://sharesdk.cn");
	 // comment是我对这条分享的评论，仅在人人网和QQ空间使用
	 oks.setComment("我是测试评论文本");
	 // site是分享此内容的网站名称，仅在QQ空间使用
	 oks.setSite(getString(R.string.app_name));
	 // siteUrl是分享此内容的网站地址，仅在QQ空间使用
	 oks.setSiteUrl("http://sharesdk.cn");

	// 启动分享GUI
	 oks.show(this);
	 }
}
