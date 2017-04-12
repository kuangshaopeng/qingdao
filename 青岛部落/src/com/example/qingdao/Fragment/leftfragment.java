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
  //������������ݶ���
  ArrayList<NewMenuData> mMewsMenuDta;
private LeftMeneAdpter adpter;
	
	public View initView() {
	View  view =	View.inflate(mActivty, R.layout.fragment, null);
	//��viewutls
	ViewUtils.inject(this, view);//פ��view ���¼�
	
		return view;
	}

	public void initData() {
		
		
	}
//���������������
	public void setManuData(ArrayList<NewMenuData> data){
		//�������ݣ�listview ��Ҫһ��adpter
		mMewsMenuDta = data;
		//��ǰ������¼���ʼ��Ϊ 0//��ǰѡ�е�λ��Ҫ����
	  mCurrenpos=0;
	  adpter = new LeftMeneAdpter();
		list.setAdapter(adpter);
		//����¼�
		list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				
				mCurrenpos  = arg2;
				adpter.notifyDataSetChanged();
				//һ��������������
				
				toogle();
				//���֮��  Ҫ�޸��������ĵ�4��ҳ������
				//���õ�ǰ�Ĳ˵�����ҳ
				setCurrentPager(arg2);
				
			}


			private void toogle() {
				mainActivity ac = (mainActivity) mActivty;
				SlidingMenu sliding = ac.getSlidingMenu();
				sliding.toggle();//�����ǰ�ǿ������ú���ǹ� ��֮��Ȼ
				
			}
		});
		
		
	}
	//���õ�ǰ�˵�����ҳ
	protected void setCurrentPager(int arg2) {
		// TODO Auto-generated method stub
		//��ȡ�������ĵĶ���
		mainActivity ac =(mainActivity) mActivty;
		//�ڻ�ȡcontentfragment
	contentfragment frag= ac.getContenMenuFragment();
		//��ȡ���������������
	NewsPager newsPager = frag.getNewsPager();
	//�޸��������� framlayout����
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
		//����Ҫ����
			View  view  = View.inflate(mActivty, R.layout.list_detai_item, null);
			TextView textView =  (TextView) view.findViewById(R.id.tv_menu);
			 NewMenuData item =  mMewsMenuDta.get(position);
			//��������
			textView.setText(item.title);
			if (position == mCurrenpos) {
				//��ѡ��
				textView.setEnabled(true);
				
			}else{
				//û�б�ѡ��
				textView.setEnabled(false);
				
				
				
			}
			
		
			return view;
		}
//		
		
		
		
	}
	
}
