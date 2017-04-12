 package com.example.qingdao.base.compl.menu;

import java.net.URI;
import java.net.URL;
import java.security.PublicKey;
import java.util.ArrayList;

import com.example.qingdao.R;
import com.example.qingdao.newsDetailActiviy;
import com.example.qingdao.Tool.CacheTools;
import com.example.qingdao.Tool.Tool;
import com.example.qingdao.domain.NewTabBean;
import com.example.qingdao.domain.NewTabBean.NewsList;
import com.example.qingdao.domain.NewTabBean.Newsheader;
import com.example.qingdao.globle.GlobleConstence;
import com.example.qingdao.view.DefaultViewPager;
import com.example.qingdao.view.PullListView;
import com.example.qingdao.view.PullListView.OnRefreshListener;
import com.google.gson.Gson;
import com.google.gson.internal.bind.ReflectiveTypeAdapterFactory.Adapter;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.viewpagerindicator.CirclePageIndicator;
import com.viewpagerindicator.PageIndicator;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
//页签页面的对象
public class tagMenuPager extends basepagermenu {

	private String  str;
	public String iD;
	private TextView textview;
	private DefaultViewPager pager;
	private String url;
	private ArrayList<Newsheader> headerArr;
	private TextView     text;
	private CirclePageIndicator pageIndicator;
	private PullListView list;
	private ArrayList<NewsList> tableNews;
	private NewsAdpter mNewsadpter;
	private View maheadrView;
	private Handler mhander;
	
	public tagMenuPager(Activity activity, String contentArr) {
		super(activity);
		
		// TODO Auto-generated constructor stub
		//不能这样写 此处空指针异常,先走super initView方法
		str =contentArr;
		url = GlobleConstence.newsUrl2;
	}

	@Override
	public View initView() {
     
	View view = View.inflate(mActivity, R.layout.pager_tab_detail, null);
	
	  list = (PullListView) view.findViewById(R.id.tableView);
	  maheadrView = View.inflate(mActivity, R.layout.list_header_view, null);
	  pager = (DefaultViewPager) maheadrView.findViewById(R.id.topNews);
      text = (TextView) maheadrView.findViewById(R.id.tv_title);
		pageIndicator = (CirclePageIndicator) maheadrView.findViewById(R.id.indictor);
	  
	  //添加头布局
	 list.addHeaderView(maheadrView);
	 //前端界面设置一个回调,在init里面就行
	 list.setOnRefreshListener(new OnRefreshListener() {
		
		@Override
		public void onRefresh() {
			// TODO Auto-generated method stub
			getDataFromServer();
		}
		@Override
		public void onLoadFoot() {
			// TODO Auto-generated method stub
			if (list.ksp!=null) {
				//有下一页
				getMoreDataFromSever();
			}else{
//没有下一页
		Toast.makeText(mActivity, "没有更多数据了", Toast.LENGTH_SHORT).show();		
		list.onRefreshComplete(true);
			}
		}
	});
	 
	 //
	 list.setOnItemClickListener(new OnItemClickListener() {


		//arg0 是当前的listView  view 是当前点击的contentview
		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			// TODO Auto-generated method stub
			int headViewCount =  list.getHeaderViewsCount();
			 arg2 = arg2-headViewCount;
			 //获取当前的tableNews
		NewsList news =	tableNews.get(arg2);
			//topic_id,先获取上个存储的id 然后不断的拼接
			String readIds = Tool.getString(mActivity, "topic_id", "");//现存一个空的字符串
			//一个字符串 怎么看包不包含另一个字符串,如果是int 直接追加一个“”就可以
			if (!readIds.contains(news.topic_id)) {
				readIds = readIds+ news.topic_id+",";
				Tool.setString(mActivity, "topic_id", readIds);
			}
		//要将被点击的颜色改为灰色, 实现局部刷新，很重要
			TextView  tvview = (TextView) arg1.findViewById(R.id.tv_title);
			tvview.setTextColor(Color.GRAY);
			iD = news.topic_id;
			
			//点击的时候就开始跳转
			Intent intent  = new Intent(mActivity,newsDetailActiviy.class);
			intent.putExtra("ID", news.url);
			//获取当前页面d的activity
			mActivity.startActivity(intent);
			
			
			
		}
	});
	  
		return view;
	}
//加载下一页数据
	protected void getMoreDataFromSever() {
		// TODO Auto-generated method stub
		
		HttpUtils utils = new HttpUtils();
//		RequestParams pararmas = new RequestParams();
//		pararmas.addBodyParamete
		utils.send(HttpMethod.GET, list.ksp, new RequestCallBack<String>() {
             
			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				
				// TODO Auto-generated method stub
				String result = responseInfo.result;
				//这一步很关键
				processData(result, true);
			//	CacheTools.setCashe(url, result, mActivity);
				System.out.println("kusngshaopeng"+list.ksp);
				
				//收起下拉刷新控件
				list.onRefreshComplete(true);
				
			}

			@Override
			public void onFailure(HttpException error, String msg) {
				error.printStackTrace();
				Toast.makeText(mActivity, msg, Toast.LENGTH_SHORT).show();
				System.out.println("解析失败");
				//收起下拉刷新控件
				list.onRefreshComplete(false);
			}
		});
		
		
	}

	class TopNewAdpter extends PagerAdapter{
   private BitmapUtils bitmapU;
		// bitmap new 一次就够了 //构造女  
		public  TopNewAdpter() {
			
			bitmapU = new BitmapUtils(mActivity);
			bitmapU.configDefaultLoadingImage(R.drawable.topnews_item_default);
			
		}
		
		
		
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return headerArr.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			// TODO Auto-generated method stub
			return arg0==arg1;
		}
		
		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			//初始化 imageview
			ImageView view  = new ImageView(mActivity);
			//view.setImageResource(R.drawable.topnews_item_default);
			view.setScaleType(ScaleType.FIT_XY);
			//下载图片，将图片设置，避免图片yichu 并且加入缓存 //宽高填充父控件
			bitmapU.display(view, headerArr.get(position).pic);
			System.out.println("sssssssdsds22"+headerArr.get(position).pic);
			container.addView(view);
			return view;
		}
		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			// TODO Auto-generated method stub
			container.removeView((View) object);
		}
		
		
	}
	
	@Override
	public void initData() {
		// TODO Auto-generated method stub
		super.initData();
		//textview.setText(str);
		//家缓存
		String cach = CacheTools.getCache(url, mActivity);
		if (!TextUtils.isEmpty(cach)) {
			processData(cach, false);
		}
		
		
		getDataFromServer();
		System.out.println("老司机");
		
		
	}

	private void getDataFromServer() {
		HttpUtils utils = new HttpUtils();
//		RequestParams pararmas = new RequestParams();
//		pararmas.addBodyParamete
		utils.send(HttpMethod.GET, GlobleConstence.newsUrl2, new RequestCallBack<String>() {

			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				// TODO Auto-generated method stub
				String result = responseInfo.result;
				processData(result, false);
				CacheTools.setCashe(url, result, mActivity);
				System.out.println("66666");
				
				//收起下拉刷新控件
				list.onRefreshComplete(true);
				
			}

			@Override
			public void onFailure(HttpException error, String msg) {
				error.printStackTrace();
				Toast.makeText(mActivity, msg, Toast.LENGTH_SHORT).show();
				System.out.println("解析失败");
				//收起下拉刷新控件
				list.onRefreshComplete(false);
			}
		});
		
	}

	protected void processData(String result,boolean ismore) {
		Gson gson = new Gson();
		//domani
		NewTabBean  newsTab =gson.fromJson(result, NewTabBean.class);
		
	    if(! ismore){
	    	
	      	headerArr = newsTab.header;
	      	if (headerArr!=null) {
	      		pager.setAdapter(new TopNewAdpter());
	      		//在设置page
				pageIndicator.setViewPager(pager);
				pageIndicator.setSnap(true); //快照方式展示
				
				
				pageIndicator.setOnPageChangeListener(new OnPageChangeListener() {
					
					private Newsheader sheader;

					@Override
					public void onPageSelected(int arg0) {
					sheader = headerArr.get(arg0);
					 text.setText(sheader.name);
					
					}
					
					@Override
					public void onPageScrolled(int arg0, float arg1, int arg2) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void onPageScrollStateChanged(int arg0) {
						// TODO Auto-generated method stub
						
					}
				});
				 text.setText(headerArr.get(0).name);
				//默认让第一个选中，解决页面销毁 indicater清零
				 pageIndicator.onPageSelected(0);
				 System.out.println("ssddewe3r455"+headerArr.get(0).name);
			
			}
	      	//设置新闻数组
	      	tableNews = newsTab.list;
	      	if (tableNews !=null) {
				mNewsadpter = new NewsAdpter();
				list.setAdapter(mNewsadpter);
				 System.out.println("sd555555"+tableNews.get(0).name);
			}
	      	
	  
	    	
	    	if (mhander== null) {
	    		//为什么在这里初始化 因为这里防止下拉刷新不断发消息
				mhander = new Handler(){
					
					// int curruntItem;

					public void handleMessage(android.os.Message msg) {
						
						
					int	curruntItem = pager.getCurrentItem();
						curruntItem++;
						
						if (curruntItem>headerArr.size()-1) {
							curruntItem= 0;
						}
						
						
						pager.setCurrentItem(curruntItem);
						mhander.sendEmptyMessageDelayed(0, 2000);
						
						
					};
				};
				
				//空消息  handleMessage会走这个方法
				mhander.sendEmptyMessageDelayed(0, 2000);
	    	
				pager.setOnTouchListener(new OnTouchListener() {
					
					@Override
					public boolean onTouch(View arg0, MotionEvent arg1) {
					switch (arg1.getAction()) {
					case  MotionEvent.ACTION_DOWN:
						//停止广告自动轮播
						//删除hander的所有消息
						mhander.removeCallbacksAndMessages(null);
						mhander.post(new Runnable() {
							
							@Override
							public void run() {
								// TODO Auto-generated method stub
								//在主线程运行   ，在主线程更新 ui 
								
								
							}
						});
						break;
            case  MotionEvent.ACTION_CANCEL:
						//启动广告
            	mhander.sendEmptyMessageDelayed(0, 2000);
						break;
            case  MotionEvent.ACTION_UP:
    			//启动广告
            	mhander.sendEmptyMessageDelayed(0, 2000);
				break;
					default:
						break;
					}
						
						
						return false;
					}
				});
				
				
				
			}
	      	
	      	
	      	
	    }
	    //当为true
	    else{
	      //加载更多数据
	    	ArrayList<NewsList> moreNews = newsTab.list;
	    	tableNews.addAll(moreNews);//将数据追加原来中
	    	//刷新listview
	    	mNewsadpter.notifyDataSetChanged();
    	
	    	
	    }
		
	}
	
class NewsAdpter extends BaseAdapter{
	private BitmapUtils bitmapu;

	//构造方法
	  public NewsAdpter() {
		bitmapu = new BitmapUtils(mActivity);
		bitmapu.configDefaultLoadFailedImage(R.drawable.news_pic_default);
	}
	

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return tableNews.size();
	}

	@Override
	public NewsList getItem(int arg0) {
		// TODO Auto-generated method stub
		return tableNews.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		// TODO Auto-generated method stub
		viewHodear hoder;
		if (arg1==null) {
			arg1 =View.inflate(mActivity, R.layout.list_item_news, null);
			hoder= new viewHodear();
			hoder.ivIcon = (ImageView) arg1.findViewById(R.id.iv_icon);
			hoder.title =  (TextView) arg1.findViewById(R.id.tv_title);
			hoder.date = (TextView) arg1.findViewById(R.id.tv_data);
		arg1.setTag(hoder);
		}else{
			//标配写法
			hoder = (viewHodear) arg1.getTag();
		
		}
		//数据要实时刷新
		//首先拿到对象
         NewsList news =   getItem(arg0);
     
     
    
     hoder.title.setText(news.name);
     hoder.date.setText(news.timestamp);
     
	bitmapu.display(hoder.ivIcon, news.pic)	;
	
	String readids =Tool.getString(mActivity, "topic_id", "");
	if (readids.contains(news.topic_id)) {
		hoder.title.setTextColor(Color.GRAY);
	}else{
		hoder.title.setTextColor(Color.BLACK);
		
		
	}
		return arg1;
	}
	
		
}

static class  viewHodear{

public ImageView ivIcon;
public TextView title;
public TextView date;

}



}
