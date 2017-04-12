package com.example.qingdao.view;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.example.qingdao.R;
import com.example.qingdao.globle.GlobleConstence;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;

public class PullListView extends ListView implements OnScrollListener{
private int starY=-1;
private int mHeaderViewHeight;
private View mHeaderView;
public String ksp;
private static  final int START_REFRESH= 1;//下拉刷新
private static  final int START_GO_REFRESH= 2;//松开刷新
private static  final int START_END_REFRESH= 3;//正在刷新

int CurrenState = START_REFRESH;
private TextView tvtitle;
private TextView tvTime;
private ImageView ivArrow;
private RotateAnimation animDown;
private RotateAnimation animation;
private ProgressBar bar;
	//下拉刷新的listview的构造方法们
	public PullListView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
		initListView();
		initFootView();
	}

	public PullListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		initListView();
		initFootView();
	}

	public PullListView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		initListView();
		initFootView();
	}

	//初始化头布局
	
	private void initListView() {
		mHeaderView = View.inflate(getContext(), R.layout.loadingview, null);
	addHeaderView(mHeaderView);
	tvtitle = (TextView) mHeaderView.findViewById(R.id.toptitle);
	tvTime = (TextView) mHeaderView.findViewById(R.id.date);
	ivArrow = (ImageView) mHeaderView.findViewById(R.id.iv_arrow);
	bar = (ProgressBar) mHeaderView.findViewById(R.id.pb_Loading);

	//初始化ini的时候就顺便出事话动画
	//隐藏头布局
	//先测量一下嘛
	mHeaderView.measure(0, 0);
	mHeaderViewHeight = mHeaderView.getMeasuredHeight();
	mHeaderView.setPadding(0, -mHeaderViewHeight, 0, 0);
	//初始化动画
	initAnimation();
	//初始化时间
	setCurrentTime();
	
	}
	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		switch (ev.getAction()) {
		case MotionEvent.ACTION_DOWN:
			starY = (int) ev.getY();
			
			break;
case MotionEvent.ACTION_MOVE:
	 if (starY==-1) {//当用户摁住头条进行下拉时 Actiondown 会被viewpager消费掉，导致starty没有赋值，此处应该获取一下
		 starY =(int) ev.getY();
		
	}
	 //如果是正在刷新 那么是不是该跳过啊
	if (CurrenState==START_END_REFRESH) {
		//如果是 正在刷新 就直接跳出
		break;
	}
	
			int endy =(int) ev.getY();
			int dy = endy -starY;
		int first=	getFirstVisiblePosition();
			//什么时候出现呢 1.必须下拉 2.必须并且当前显示的是第一个item
			if (dy>0 &&first ==0 ) {
				int pading =dy - mHeaderViewHeight;
				
			
				System.out.println("阿尔维斯"+ pading);
				mHeaderView.setPadding(0, pading, 0, 0);
				if (pading>0 &&  CurrenState!=START_GO_REFRESH) {
					CurrenState = START_GO_REFRESH;
					refreashState();
				}else if(pading<0 && CurrenState!= START_REFRESH){
					
					CurrenState = START_REFRESH;
					refreashState();
				}
				
				//消费掉
				return true;
			}
			
			break;
			
case MotionEvent.ACTION_UP:
	starY = -1;
	if (CurrenState==START_GO_REFRESH) {
		CurrenState =START_END_REFRESH;
		refreashState();
		//松开手回来吧
		mHeaderView.setPadding(0, 0, 0, 0);
		//松开手的那一刻  4.进行回调
		if (myListener!=null) {
			myListener.onRefresh();
		}
		
	}else if(CurrenState==START_REFRESH){
		//隐藏头布局
		mHeaderView.setPadding(0, -mHeaderViewHeight, 0, 0);
	}
	
	break;
	
		default:
			break;
		}
		
		
		return super.onTouchEvent(ev);
	}

	private void refreashState() {
		// TODO Auto-generated method stub
		switch (CurrenState) {
		case START_REFRESH:
			tvtitle.setText("下拉刷新");
			ivArrow.startAnimation(animDown);
			bar.setVisibility(View.INVISIBLE);
			ivArrow.setVisibility(View.VISIBLE);

			break;
case START_GO_REFRESH:
	tvtitle.setText("松开刷新");
	ivArrow.startAnimation(animation);
	bar.setVisibility(View.INVISIBLE);
	ivArrow.setVisibility(View.VISIBLE);

			break;
case START_END_REFRESH:
	tvtitle.setText("正在刷新");
	
	ivArrow.clearAnimation();//清除箭头动画否则无法隐藏
	bar.setVisibility(View.VISIBLE);
	ivArrow.setVisibility(View.INVISIBLE);
	

	break;
		default:
			break;
		}
		
	}
	
	private  void initAnimation(){
		animation = new RotateAnimation(0, -180, Animation.RELATIVE_TO_SELF	, 0.5f, Animation.RELATIVE_TO_SELF	, 0.5f);
		animation.setDuration(200);
		animation.setFillAfter(true);
		
		animDown = new RotateAnimation(-180, 0, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
	animDown.setDuration(200);
		animDown.setFillAfter(true);
		
	}
	//3.定义成员变量，接受监听对象
	private OnRefreshListener myListener;
	private View mFootView;
	
	//2.把接口暴露出来呀,设置监听
	public void setOnRefreshListener(OnRefreshListener listener){
		
		myListener =listener;
	}
	
	//1.回调接口
	  public interface OnRefreshListener{
		public void onRefresh();
		//下拉
		public void onLoadFoot();  
		  
	  }
	//写一个方法让刷新收起来
	  public void onRefreshComplete(boolean b){
		  if(!isLoading){
			  mHeaderView.setPadding(0, -mHeaderViewHeight, 0, 0);
			  //状态要改吧
			  CurrenState = START_REFRESH;
			  tvtitle.setText("下拉刷新");
			  bar.setVisibility(View.INVISIBLE);
			  ivArrow.setVisibility(View.VISIBLE);
			  
			  if (b) {
				setCurrentTime();
			}
			  
		  }//收起上拉加载
		  else{
			  mFootView.setPadding(0, -mFootViewHeight, 0, 0);
			  isLoading =false;
			  
		  }
		
	  }
	  
	  //设置刷新时间
	  private void setCurrentTime() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	String time  =format.format(new Date());
	tvTime.setText(time);
	  }
	  
	//添加 角布局
	  private void initFootView() {
		//
		 
	
		  
	  	mFootView = View.inflate(getContext(), R.layout.loadingview_foot	, null);
this.addFooterView(mFootView); 
//测量一下
mFootView.measure(0, 0);
mFootViewHeight = mFootView.getMeasuredHeight();
   //内部padding啊 大哥  不明白弄个数试试
		mFootView  .setPadding(0, -mFootViewHeight, 0, 0);
		//监听
		setOnScrollListener(this);
		  
	  }
//活动过程回调
	@Override
	public void onScroll(AbsListView arg0, int arg1, int arg2, int arg3) {
		
		
	}
	private boolean isLoading=false;
	private int page=1;
	private int mFootViewHeight;

//滑动状态的变化
	@Override
	public void onScrollStateChanged(AbsListView arg0, int arg1) {
	if (arg1 ==SCROLL_STATE_IDLE ) {//空闲状态
		int lastItem = getLastVisiblePosition();
		if (getCount()-1==lastItem&& isLoading==false) {
			System.out.println("加载更多");
			mFootView.setPadding(0, 0, 0, 0);
			setSelection(lastItem);//讲listview 显示在最后一个item上 
			isLoading =true;
	String		str =GlobleConstence.newsUrl3;
	//这是为什么呢
	++page;
	
	ksp = str +("page="+page);
	
	
	
	
			//加载更多
			if (myListener!=null) {
				myListener.onLoadFoot();
			}
			
		}
	}
		
	}

	  
}
