package com.example.qingdao;

import java.security.PublicKey;
import java.util.ArrayList;

import com.example.qingdao.Tool.DensityToll;
import com.example.qingdao.Tool.Tool;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.style.ClickableSpan;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

public class newGuide extends Activity {
private ViewPager pager;
//ͼƬ���飻
private int[] Images= new int[]{R.drawable.guide_1,R.drawable.guide_2,
		R.drawable.guide_3};
//imageview���飨���ϣ�
private ArrayList<ImageView> mImageViewList;

private LinearLayout linLay;
//����С���view 
private ImageView redImagePoint;
//С���ľ���
private int pointDis;
private Button myButton;

@Override
protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	//ȥ��������
	requestWindowFeature(Window.FEATURE_NO_TITLE);
	setContentView(R.layout.activity_guide);
	//����imageview

	
	pager = (ViewPager) findViewById(R.id.vp_guide);
	
	
	
	 linLay = (LinearLayout) findViewById(R.id.ll_container);
	 myButton = (Button) findViewById(R.id.btn_start);
	 
	 redImagePoint = (ImageView) findViewById(R.id.iv_red_point);
	 initImageViews();
	pager.setAdapter(new myAdapter());
	
	pager.setOnPageChangeListener(new OnPageChangeListener() {
		
		@Override
		public void onPageSelected(int position) {
		   if(position ==mImageViewList.size()-1){
			   
			   myButton.setVisibility(View.VISIBLE);
			   
		   }else {
			   myButton.setVisibility(View.INVISIBLE);
		}
			
		}
		
		@Override
		public void onPageScrolled(final int position,  final float positionOffset,
				int positionOffsetPixels) {
		
			// TODO Auto-generated method stub
			//scrollƫ�Ƶ�ʱ��ص�
			//�����ﲻ��ֱ�������㡣��Ϊlayout��û�� ,���޷������㡣��layout
			//������ ����������㣬������
			//����layout ���������¼� ֮�� �ڻ�ȡλ�ü���
			//�����ͼ���Ĺ۲���
//			 redImagePoint.getViewTreeObserver().addOnGlobalLayoutListener(new
//					 OnGlobalLayoutListener() {
//				
//			
//
//				@Override
//				public void onGlobalLayout() {
//					   //this ɾ���ĸ��۲��� ������������ڲ���Ĺ۲��� 
//					redImagePoint.getViewTreeObserver().removeGlobalOnLayoutListener(this); 
//					 int	pointDis = linLay.getChildAt(1).getLeft()-linLay.getChildAt(0).getLeft();
//					
//					//positionOffset�ƶ��İٷֱ�
//					
//					int leftMargin = (int) (positionOffset * pointDis)+position*pointDis;
//					System.out.println("Զ�㷽������������ɷɷ�������"+positionOffset );
//					RelativeLayout.LayoutParams parpas = (LayoutParams) redImagePoint.getLayoutParams();
//					
//					parpas.leftMargin =leftMargin;
//					//����
//					redImagePoint.setLayoutParams(parpas);
//				}
//			});
			
			int leftMargin = (int) (positionOffset * pointDis)+position*pointDis;
			System.out.println("Զ�㷽������������ɷɷ�������"+positionOffset );
			RelativeLayout.LayoutParams parpas = (LayoutParams) redImagePoint.getLayoutParams();
			
			parpas.leftMargin =leftMargin;
			//����
			redImagePoint.setLayoutParams(parpas);
			
		}
		
		@Override
		public void onPageScrollStateChanged(int state) {
			// TODO Auto-generated method stub
			
		}
	});
	
	redImagePoint.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
		
		

		@Override
		public void onGlobalLayout() {
			// TODO Auto-generated method stub
			redImagePoint.getViewTreeObserver().removeGlobalOnLayoutListener(this);
			pointDis = linLay.getChildAt(1).getLeft()-linLay.getChildAt(0).getLeft();
		}
	});
	
    
	
	
}
private void initImageViews() {
	mImageViewList = new ArrayList<ImageView>();
	for (int i = 0; i < Images.length; i++) {
	
		 ImageView  view  = new ImageView(this);
		 //��ͼƬ����imageview(��������)��setimageResource�������
		 view.setBackgroundResource(Images[i]);
		 mImageViewList.add(view);
		//��ʼ��СԲ��
			ImageView oimg = new ImageView(this);
			//����ͼƬ��״
			 oimg.setImageResource(R.drawable.shape_point);
			 linLay.addView(oimg);
			//����params
			 LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
					 LinearLayout.LayoutParams.WRAP_CONTENT);
			 if ( i >0) {
				params.leftMargin =DensityToll.dipToPx(10, this); 
			}
			 //���ò��ֲ���
			 oimg.setLayoutParams(params);


						}
	
	
	
	
	 
}
//��תҳ��
public void button(View v) {
	//�洢��bool �ı�Ϊno
	Tool.setBoolen(getApplicationContext(), "is_first", false);
	
	
	
	Intent intents = new Intent(getApplicationContext(),mainActivity.class);
	
	startActivity(intents);
	//����ҳ��
	finish();
	
	
}

class myAdapter extends PagerAdapter{

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		
		return mImageViewList.size();
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		// TODO Auto-generated method stub
		return arg0 ==arg1;
	}
	
	//��ʼ��item(�ص�)
       @Override
    public Object instantiateItem(ViewGroup container, int position) {
    	// TODO Auto-generated method stub
      	 //�ȴ���imageview
      	 //�õ�view
      	 ImageView view  = mImageViewList.get(position);
      	 //��view �����������,������û���
      	 container.addView(view);
      	 //���ؾ��ǵ�ǰview Ҫ����
    	return  view;
    }
   
	//����item 
   
    public void destroyItem(ViewGroup container, int position, Object object) {
    	// TODO Auto-generated method stub
    	
    	container.removeView((View)object);
    }
	
	
}


}
