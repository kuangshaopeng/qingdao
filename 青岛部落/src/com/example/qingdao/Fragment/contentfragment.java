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
	private ArrayList<basepager> mPager;//�����ǩҳ�ļ���
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
		//��������ǩ ���֮ǰһ��Ҫ����
		mPager.add(new HomePager(mActivty));
		mPager.add(new NewsPager(mActivty));
		mPager.add(new settingPager(mActivty));
		mPager.add(new govPager(mActivty));
		mPager.add(new smartPager(mActivty));
		 
	mViewPager.setAdapter(new ContentAdepter());
	//�����ײ�lan���л�
	myRadio.setOnCheckedChangeListener(new OnCheckedChangeListener() {
		
		@Override
		public void onCheckedChanged(RadioGroup group, int chectedId) {
			// TODO Auto-generated method stub
			switch (chectedId) {
			case R.id.topic:
				//��ҳ
				mViewPager.setCurrentItem(0, false);
				break;
			case R.id.news:
				//��ҳ
				mViewPager.setCurrentItem(1, false);
				break;
			case R.id.smart:
				//��ҳ
				mViewPager.setCurrentItem(4, false);
				break;
			case R.id.gov:
				//��ҳ
				mViewPager.setCurrentItem(3, false);
				break;
			case R.id.setting:
				//��ҳ
				mViewPager.setCurrentItem(2, false);
				break;
			default:
				break;
			}
		}
	});
	//����ҳ����� 
	mViewPager.setOnPageChangeListener(new OnPageChangeListener() {
		
		@Override
		public void onPageSelected(int position) {
			
		 basepager pager  = mPager.get(position);
		 //ѡ�вż�������ǹؼ� �ɶ�
		 pager.initData();
		 if (position==0 || position==2) {
			//��ҳ���ò����
			 setSlidingMenuEnable(false);
			 
		}else{
			//��ҳ���ò����
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
	//�ֶ����ص�һҳ����
	mPager.get(0).initData();
	//��һ��Ҫ����
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
		
		 @Override//scroll������ container
		public Object instantiateItem(ViewGroup container, int position) {
			// TODO Auto-generated method stub
			 
		   basepager pager = mPager.get(position);
		   //������ǹؼ� pager ����һ��view //pager һ�������Զ��岼�� ����ʲôbutoonʲô��  һ�����Ǽ������ݣ����Ǽ̳���basepager 
		   //baspager �������� �Զ���object �����߼���
		         View  view =  pager.toolView;//��ȡ��ǰҳ�����view
		         //˳���ȡ��ǰҳ�����Ĳ���  //����Ϳ��Բ�Ҫ�� ��item ��Ҫ������
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
	
	//��ȡ�������ĵ�ҳ��
	
	 public  NewsPager getNewsPager(){
     return		 (NewsPager) mPager.get(1);
		 
		 
		 
	 }
}
