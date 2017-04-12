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
//图片数组；
private int[] Images= new int[]{R.drawable.guide_1,R.drawable.guide_2,
		R.drawable.guide_3};
//imageview数组（集合）
private ArrayList<ImageView> mImageViewList;

private LinearLayout linLay;
//声明小红点view 
private ImageView redImagePoint;
//小红点的距离
private int pointDis;
private Button myButton;

@Override
protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	//去掉标题栏
	requestWindowFeature(Window.FEATURE_NO_TITLE);
	setContentView(R.layout.activity_guide);
	//创建imageview

	
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
			//scroll偏移的时候回调
			//在这里不能直接做运算。因为layout还没好 ,你无法做运算。等layout
			//画好了 你才能做运算，用树类
			//监听layout 方法结束事件 之后 在获取位置间间距
			//添加视图树的观察者
//			 redImagePoint.getViewTreeObserver().addOnGlobalLayoutListener(new
//					 OnGlobalLayoutListener() {
//				
//			
//
//				@Override
//				public void onGlobalLayout() {
//					   //this 删除哪个观察者 就是这个匿名内部类的观察者 
//					redImagePoint.getViewTreeObserver().removeGlobalOnLayoutListener(this); 
//					 int	pointDis = linLay.getChildAt(1).getLeft()-linLay.getChildAt(0).getLeft();
//					
//					//positionOffset移动的百分比
//					
//					int leftMargin = (int) (positionOffset * pointDis)+position*pointDis;
//					System.out.println("远点方法反反复复凤飞飞反复距离"+positionOffset );
//					RelativeLayout.LayoutParams parpas = (LayoutParams) redImagePoint.getLayoutParams();
//					
//					parpas.leftMargin =leftMargin;
//					//设置
//					redImagePoint.setLayoutParams(parpas);
//				}
//			});
			
			int leftMargin = (int) (positionOffset * pointDis)+position*pointDis;
			System.out.println("远点方法反反复复凤飞飞反复距离"+positionOffset );
			RelativeLayout.LayoutParams parpas = (LayoutParams) redImagePoint.getLayoutParams();
			
			parpas.leftMargin =leftMargin;
			//设置
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
		 //将图片放入imageview(这个是填充)，setimageResource不是填充
		 view.setBackgroundResource(Images[i]);
		 mImageViewList.add(view);
		//初始化小圆点
			ImageView oimg = new ImageView(this);
			//设置图片形状
			 oimg.setImageResource(R.drawable.shape_point);
			 linLay.addView(oimg);
			//设置params
			 LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
					 LinearLayout.LayoutParams.WRAP_CONTENT);
			 if ( i >0) {
				params.leftMargin =DensityToll.dipToPx(10, this); 
			}
			 //设置布局参数
			 oimg.setLayoutParams(params);


						}
	
	
	
	
	 
}
//跳转页面
public void button(View v) {
	//存储的bool 改变为no
	Tool.setBoolen(getApplicationContext(), "is_first", false);
	
	
	
	Intent intents = new Intent(getApplicationContext(),mainActivity.class);
	
	startActivity(intents);
	//消除页面
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
	
	//初始化item(重点)
       @Override
    public Object instantiateItem(ViewGroup container, int position) {
    	// TODO Auto-generated method stub
      	 //先创建imageview
      	 //得到view
      	 ImageView view  = mImageViewList.get(position);
      	 //将view 塞给这个容器,这就重用机制
      	 container.addView(view);
      	 //返回就是当前view 要用嘛
    	return  view;
    }
   
	//销毁item 
   
    public void destroyItem(ViewGroup container, int position, Object object) {
    	// TODO Auto-generated method stub
    	
    	container.removeView((View)object);
    }
	
	
}


}
