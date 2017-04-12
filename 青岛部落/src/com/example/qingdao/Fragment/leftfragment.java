package com.example.qingdao.Fragment;

import java.util.ArrayList;

import com.example.qingdao.R;
import com.example.qingdao.mainActivity;
import com.example.qingdao.base.compl.NewsPager;
import com.example.qingdao.domain.NewsMenue.NewMenuData;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class leftfragment extends basefragment {
	
  @ViewInject(R.id.fr_listView)
	
  private ListView list ;
  private int  mCurrenpos;
  //侧边栏网络数据对象
  ArrayList<NewMenuData> mMewsMenuDta;
private LeftMeneAdpter adpter;
	
	public View initView() {
	View  view =	View.inflate(mActivty, R.layout.fragment, null);
	//用viewutls
	ViewUtils.inject(this, view);//驻入view 和事件
	
		return view;
	}

	public void initData() {
		
		
	}
//给侧边烂设置数据
	public void setManuData(ArrayList<NewMenuData> data){
		//跟新数据，listview 需要一个adpter
		mMewsMenuDta = data;
		//当前点击的事件初始化为 0//当前选中的位置要清零
	  mCurrenpos=0;
	  adpter = new LeftMeneAdpter();
		list.setAdapter(adpter);
		//点击事件
		list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				
				mCurrenpos  = arg2;
				adpter.notifyDataSetChanged();
				//一点他就收起侧边栏
				
				toogle();
				//点击之后  要修改新闻中心的4个页面内容
				//设置当前的菜单详情页
				setCurrentPager(arg2);
				
			}


			private void toogle() {
				mainActivity ac = (mainActivity) mActivty;
				SlidingMenu sliding = ac.getSlidingMenu();
				sliding.toggle();//如果当前是开，调用后就是关 反之亦然
				
			}
		});
		
		
	}
	//设置当前菜单详情页
	protected void setCurrentPager(int arg2) {
		// TODO Auto-generated method stub
		//获取新闻中心的对象
		mainActivity ac =(mainActivity) mActivty;
		//在获取contentfragment
	contentfragment frag= ac.getContenMenuFragment();
		//获取新闻中心这个界面
	NewsPager newsPager = frag.getNewsPager();
	//修改新闻中心 framlayout布局
	newsPager.setCurrentDetaiPager(arg2);
	}

	class LeftMeneAdpter extends BaseAdapter{

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return mMewsMenuDta.size();
		}

		@Override
		public Object getItem(int arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int arg0) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int position, View arg1, ViewGroup arg2) {
		//不需要重用
			View  view  = View.inflate(mActivty, R.layout.list_detai_item, null);
			TextView textView =  (TextView) view.findViewById(R.id.tv_menu);
			 NewMenuData item =  mMewsMenuDta.get(position);
			//设置文字
			textView.setText(item.title);
			if (position == mCurrenpos) {
				//被选中
				textView.setEnabled(true);
				
			}else{
				//没有被选中
				textView.setEnabled(false);
				
				
				
			}
			
		
			return view;
		}
//		
		
		
		
	}
	
}
