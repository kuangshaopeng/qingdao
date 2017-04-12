package com.example.qingdao.Fragment;

import java.util.ArrayList;

import com.example.qingdao.R;
import com.example.qingdao.mainActivity;
import com.example.qingdao.newGuide;
import com.example.qingdao.base.basepager;
import com.example.qingdao.base.compl.HomePager;
import com.example.qingdao.base.compl.NewsPager;
import com.example.qingdao.base.compl.govPager;
import com.example.qingdao.base.compl.settingPager;
import com.example.qingdao.base.compl.smartPager;
import com.example.qingdao.view.NoScrollPager;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class contentfragment extends basefragment {

	private NoScrollPager mViewPager;
	private ArrayList<basepager> mPager;//五个标签页的集合
	private RadioGroup myRadio;

	@Override
	public View initView() {
		View view =View.inflate(mActivty, R.layout.contectlayout, null);
		mViewPager = (NoScrollPager) view.findViewById(R.id.mainViewPager);
		  myRadio = (RadioGroup) view.findViewById(R.id.radio);
		return view;
	}

	@Override
	public void initData() {
		mPager = new ArrayList<basepager>();
		//添加五个标签 添加之前一定要创建
		mPager.add(new HomePager(mActivty));
		mPager.add(new NewsPager(mActivty));
		mPager.add(new settingPager(mActivty));
		mPager.add(new govPager(mActivty));
		mPager.add(new smartPager(mActivty));
		 
	mViewPager.setAdapter(new ContentAdepter());
	//监听底部lan的切换
	myRadio.setOnCheckedChangeListener(new OnCheckedChangeListener() {
		
		@Override
		public void onCheckedChanged(RadioGroup group, int chectedId) {
			// TODO Auto-generated method stub
			switch (chectedId) {
			case R.id.topic:
				//首页
				mViewPager.setCurrentItem(0, false);
				break;
			case R.id.news:
				//首页
				mViewPager.setCurrentItem(1, false);
				break;
			case R.id.smart:
				//首页
				mViewPager.setCurrentItem(4, false);
				break;
			case R.id.gov:
				//首页
				mViewPager.setCurrentItem(3, false);
				break;
			case R.id.setting:
				//首页
				mViewPager.setCurrentItem(2, false);
				break;
			default:
				break;
			}
		}
	});
	//设置页面监听 
	mViewPager.setOnPageChangeListener(new OnPageChangeListener() {
		
		@Override
		public void onPageSelected(int position) {
			
		 basepager pager  = mPager.get(position);
		 //选中才加载这才是关键 可懂
		 pager.initData();
		 if (position==0 || position==2) {
			//首页设置侧边栏
			 setSlidingMenuEnable(false);
			 
		}else{
			//首页禁用侧边栏
			 setSlidingMenuEnable(true);
			  
		}
		 
			
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
	//手动加载第一页数据
	mPager.get(0).initData();
	//第一次要禁用
	setSlidingMenuEnable(false);
	
	}
	
	protected void setSlidingMenuEnable(boolean b) {
		mainActivity actvity = (mainActivity) mActivty;
	SlidingMenu menu= actvity.getSlidingMenu();
	if (b==true) {
		menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
	}else{
		menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
		
		
	}
		
	}
	
	
	class ContentAdepter extends  PagerAdapter{

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return mPager.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			// TODO Auto-generated method stub
			return arg0==arg1;
		}
		
		 @Override//scroll的容器 container
		public Object instantiateItem(ViewGroup container, int position) {
			// TODO Auto-generated method stub
			 
		   basepager pager = mPager.get(position);
		   //这个点是关键 pager 就是一个view //pager 一个就是自定义布局 创建什么butoon什么的  一个就是加载数据，他们继承与basepager 
		   //baspager 就是渣渣 自定义object 看清逻辑啊
		         View  view =  pager.toolView;//获取当前页面对象view
		         //顺便获取当前页面对象的布局  //这个就可以不要了 在item 不要搞数据
		         //pager.initData();
		         container.addView(view);
		         
			return view;
		}
		 @Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			// TODO Auto-generated method stub
		   container.removeView((View)object);
			
		}
		
	
		
	}
	
	//获取新闻中心的页面
	
	 public  NewsPager getNewsPager(){
     return		 (NewsPager) mPager.get(1);
		 
		 
		 
	 }
}
