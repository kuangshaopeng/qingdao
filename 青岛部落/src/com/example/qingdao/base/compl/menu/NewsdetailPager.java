package com.example.qingdao.base.compl.menu;
/*1因入库
 * 解决suport v4冲突
 * 从例子程序中拷贝文件
 * 从例子程序中拷贝相关代码 （指示器和viewpager绑定，重写getpageTitle返回标题）
 * 在文件清单中增加样式
 * 背景修改
 */
import java.util.ArrayList;

import com.example.qingdao.R;
import com.example.qingdao.mainActivity;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.viewpagerindicator.TabPageIndicator;

import android.app.Activity;
import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

public class NewsdetailPager extends basepagermenu implements OnPageChangeListener{
  
	private ViewPager viewPager;

	//页签数据
	private String[] contentArr;
	
	private ArrayList<tagMenuPager> pagerArr;

	private TabPageIndicator tabBar;
	
	public NewsdetailPager(Activity activity) {
		super(activity);
		// TODO Auto-generated constructor stub
	
	}

	@Override
	public View initView() {
		View view  =View.inflate(mActivity, R.layout.pager_news_manudetail	, null);
		    viewPager = (ViewPager) view.findViewById(R.id.vp_news_menudtail);
		    
		    tabBar = (TabPageIndicator) view.findViewById(R.id.tabbar);
		    //设置绑定,但是这个 一定要setadpter之后创建完之后才能绑定 所以不能写在这 写在setadpter的后面
		   // tabBar.setViewPager(viewPager);
		return view;
	}
//初始化页签要在nintData
	@Override
	public void initData() {
		// TODO Auto-generated method stub
		super.initData();
		contentArr = new String[]{"青岛","财政","大波美女","小波美女"};
		//new
		pagerArr = new ArrayList<tagMenuPager>();
		//这个地方本来要建立5个详情页面 现在省事全都搞成一个
		for (int i = 0; i < contentArr.length; i++) {
			//用构造方法传过去
		tagMenuPager pager = new tagMenuPager(mActivity,contentArr[i]);
			pagerArr.add(pager);
			}
		//设置adpter
		viewPager.setAdapter(new NewsMenuDetailAdpter());
		//设置在后面啊
		
		 tabBar.setViewPager(viewPager);
		 
		 //设置viewpager 监听 //因为你和indcater关联，所以用tabbar 监听
		 tabBar.setOnPageChangeListener(this);
		 
	}
	
	
	class NewsMenuDetailAdpter extends PagerAdapter{

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return pagerArr.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			// TODO Auto-generated method stub
			return arg0==arg1;
			
		
			
		}
	
	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		// TODO Auto-generated method stub
		//为了得到页面的对象
		
	tagMenuPager pager=	pagerArr.get(position);
	View  view  = pager.initView();
	container.addView(view);
	//初始化数据
	pager.initData();
		System.out.println("走到这里了没");
		return view;
	}	
	
	
	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		// TODO Auto-generated method stub
		container.removeView((View) object);
		
	}
	//重写它，指定指示器的标题
	@Override
	public CharSequence getPageTitle(int position) {
		// TODO Auto-generated method stub
		
	String  str =	contentArr[position];
		return str;
		
	}
	
		
		
	}


	@Override
	public void onPageScrollStateChanged(int arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPageSelected(int posion) {
		// TODO Auto-generated method stub
		if (posion==0) {
			//开启侧边栏
			setSlidingMenuEnable(true);
			
		}else {
			//关闭侧边栏
			setSlidingMenuEnable(false);
		}
		
		
	}
	
	protected void setSlidingMenuEnable(boolean b) {
		mainActivity actvity = (mainActivity) mActivity;
	SlidingMenu menu= actvity.getSlidingMenu();
	if (b==true) {
		menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
	}else{
		menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
		
		
	}
		
	}
	@OnClick(R.id.btn_next)
	public void nextPage(View v){
		//获取当前的item ,
		int currentItem =viewPager.getCurrentItem();
		currentItem++;
		
		viewPager.setCurrentItem(currentItem);
		
	}
	
}
