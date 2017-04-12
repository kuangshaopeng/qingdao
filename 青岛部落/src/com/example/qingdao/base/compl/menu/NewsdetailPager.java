package com.example.qingdao.base.compl.menu;
/*1�����
 * ���suport v4��ͻ
 * �����ӳ����п����ļ�
 * �����ӳ����п�����ش��� ��ָʾ����viewpager�󶨣���дgetpageTitle���ر��⣩
 * ���ļ��嵥��������ʽ
 * �����޸�
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

	//ҳǩ����
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
		    //���ð�,������� һ��Ҫsetadpter֮�󴴽���֮����ܰ� ���Բ���д���� д��setadpter�ĺ���
		   // tabBar.setViewPager(viewPager);
		return view;
	}
//��ʼ��ҳǩҪ��nintData
	@Override
	public void initData() {
		// TODO Auto-generated method stub
		super.initData();
		contentArr = new String[]{"�ൺ","����","����Ů","С����Ů"};
		//new
		pagerArr = new ArrayList<tagMenuPager>();
		//����ط�����Ҫ����5������ҳ�� ����ʡ��ȫ�����һ��
		for (int i = 0; i < contentArr.length; i++) {
			//�ù��췽������ȥ
		tagMenuPager pager = new tagMenuPager(mActivity,contentArr[i]);
			pagerArr.add(pager);
			}
		//����adpter
		viewPager.setAdapter(new NewsMenuDetailAdpter());
		//�����ں��氡
		
		 tabBar.setViewPager(viewPager);
		 
		 //����viewpager ���� //��Ϊ���indcater������������tabbar ����
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
		//Ϊ�˵õ�ҳ��Ķ���
		
	tagMenuPager pager=	pagerArr.get(position);
	View  view  = pager.initView();
	container.addView(view);
	//��ʼ������
	pager.initData();
		System.out.println("�ߵ�������û");
		return view;
	}	
	
	
	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		// TODO Auto-generated method stub
		container.removeView((View) object);
		
	}
	//��д����ָ��ָʾ���ı���
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
			//���������
			setSlidingMenuEnable(true);
			
		}else {
			//�رղ����
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
		//��ȡ��ǰ��item ,
		int currentItem =viewPager.getCurrentItem();
		currentItem++;
		
		viewPager.setCurrentItem(currentItem);
		
	}
	
}
