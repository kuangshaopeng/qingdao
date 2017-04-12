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
//ҳǩҳ��Ķ���
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
		//��������д �˴���ָ���쳣,����super initView����
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
	  
	  //���ͷ����
	 list.addHeaderView(maheadrView);
	 //ǰ�˽�������һ���ص�,��init�������
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
				//����һҳ
				getMoreDataFromSever();
			}else{
//û����һҳ
		Toast.makeText(mActivity, "û�и���������", Toast.LENGTH_SHORT).show();		
		list.onRefreshComplete(true);
			}
		}
	});
	 
	 //
	 list.setOnItemClickListener(new OnItemClickListener() {


		//arg0 �ǵ�ǰ��listView  view �ǵ�ǰ�����contentview
		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			// TODO Auto-generated method stub
			int headViewCount =  list.getHeaderViewsCount();
			 arg2 = arg2-headViewCount;
			 //��ȡ��ǰ��tableNews
		NewsList news =	tableNews.get(arg2);
			//topic_id,�Ȼ�ȡ�ϸ��洢��id Ȼ�󲻶ϵ�ƴ��
			String readIds = Tool.getString(mActivity, "topic_id", "");//�ִ�һ���յ��ַ���
			//һ���ַ��� ��ô������������һ���ַ���,�����int ֱ��׷��һ�������Ϳ���
			if (!readIds.contains(news.topic_id)) {
				readIds = readIds+ news.topic_id+",";
				Tool.setString(mActivity, "topic_id", readIds);
			}
		//Ҫ�����������ɫ��Ϊ��ɫ, ʵ�־ֲ�ˢ�£�����Ҫ
			TextView  tvview = (TextView) arg1.findViewById(R.id.tv_title);
			tvview.setTextColor(Color.GRAY);
			iD = news.topic_id;
			
			//�����ʱ��Ϳ�ʼ��ת
			Intent intent  = new Intent(mActivity,newsDetailActiviy.class);
			intent.putExtra("ID", news.url);
			//��ȡ��ǰҳ��d��activity
			mActivity.startActivity(intent);
			
			
			
		}
	});
	  
		return view;
	}
//������һҳ����
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
				//��һ���ܹؼ�
				processData(result, true);
			//	CacheTools.setCashe(url, result, mActivity);
				System.out.println("kusngshaopeng"+list.ksp);
				
				//��������ˢ�¿ؼ�
				list.onRefreshComplete(true);
				
			}

			@Override
			public void onFailure(HttpException error, String msg) {
				error.printStackTrace();
				Toast.makeText(mActivity, msg, Toast.LENGTH_SHORT).show();
				System.out.println("����ʧ��");
				//��������ˢ�¿ؼ�
				list.onRefreshComplete(false);
			}
		});
		
		
	}

	class TopNewAdpter extends PagerAdapter{
   private BitmapUtils bitmapU;
		// bitmap new һ�ξ͹��� //����Ů  
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
			//��ʼ�� imageview
			ImageView view  = new ImageView(mActivity);
			//view.setImageResource(R.drawable.topnews_item_default);
			view.setScaleType(ScaleType.FIT_XY);
			//����ͼƬ����ͼƬ���ã�����ͼƬyichu ���Ҽ��뻺�� //�����丸�ؼ�
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
		//�һ���
		String cach = CacheTools.getCache(url, mActivity);
		if (!TextUtils.isEmpty(cach)) {
			processData(cach, false);
		}
		
		
		getDataFromServer();
		System.out.println("��˾��");
		
		
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
				
				//��������ˢ�¿ؼ�
				list.onRefreshComplete(true);
				
			}

			@Override
			public void onFailure(HttpException error, String msg) {
				error.printStackTrace();
				Toast.makeText(mActivity, msg, Toast.LENGTH_SHORT).show();
				System.out.println("����ʧ��");
				//��������ˢ�¿ؼ�
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
	      		//������page
				pageIndicator.setViewPager(pager);
				pageIndicator.setSnap(true); //���շ�ʽչʾ
				
				
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
				//Ĭ���õ�һ��ѡ�У����ҳ������ indicater����
				 pageIndicator.onPageSelected(0);
				 System.out.println("ssddewe3r455"+headerArr.get(0).name);
			
			}
	      	//������������
	      	tableNews = newsTab.list;
	      	if (tableNews !=null) {
				mNewsadpter = new NewsAdpter();
				list.setAdapter(mNewsadpter);
				 System.out.println("sd555555"+tableNews.get(0).name);
			}
	      	
	  
	    	
	    	if (mhander== null) {
	    		//Ϊʲô�������ʼ�� ��Ϊ�����ֹ����ˢ�²��Ϸ���Ϣ
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
				
				//����Ϣ  handleMessage�����������
				mhander.sendEmptyMessageDelayed(0, 2000);
	    	
				pager.setOnTouchListener(new OnTouchListener() {
					
					@Override
					public boolean onTouch(View arg0, MotionEvent arg1) {
					switch (arg1.getAction()) {
					case  MotionEvent.ACTION_DOWN:
						//ֹͣ����Զ��ֲ�
						//ɾ��hander��������Ϣ
						mhander.removeCallbacksAndMessages(null);
						mhander.post(new Runnable() {
							
							@Override
							public void run() {
								// TODO Auto-generated method stub
								//�����߳�����   �������̸߳��� ui 
								
								
							}
						});
						break;
            case  MotionEvent.ACTION_CANCEL:
						//�������
            	mhander.sendEmptyMessageDelayed(0, 2000);
						break;
            case  MotionEvent.ACTION_UP:
    			//�������
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
	    //��Ϊtrue
	    else{
	      //���ظ�������
	    	ArrayList<NewsList> moreNews = newsTab.list;
	    	tableNews.addAll(moreNews);//������׷��ԭ����
	    	//ˢ��listview
	    	mNewsadpter.notifyDataSetChanged();
    	
	    	
	    }
		
	}
	
class NewsAdpter extends BaseAdapter{
	private BitmapUtils bitmapu;

	//���췽��
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
			//����д��
			hoder = (viewHodear) arg1.getTag();
		
		}
		//����Ҫʵʱˢ��
		//�����õ�����
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
