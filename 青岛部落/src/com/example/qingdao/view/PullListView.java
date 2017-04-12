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
private static  final int START_REFRESH= 1;//����ˢ��
private static  final int START_GO_REFRESH= 2;//�ɿ�ˢ��
private static  final int START_END_REFRESH= 3;//����ˢ��

int CurrenState = START_REFRESH;
private TextView tvtitle;
private TextView tvTime;
private ImageView ivArrow;
private RotateAnimation animDown;
private RotateAnimation animation;
private ProgressBar bar;
	//����ˢ�µ�listview�Ĺ��췽����
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

	//��ʼ��ͷ����
	
	private void initListView() {
		mHeaderView = View.inflate(getContext(), R.layout.loadingview, null);
	addHeaderView(mHeaderView);
	tvtitle = (TextView) mHeaderView.findViewById(R.id.toptitle);
	tvTime = (TextView) mHeaderView.findViewById(R.id.date);
	ivArrow = (ImageView) mHeaderView.findViewById(R.id.iv_arrow);
	bar = (ProgressBar) mHeaderView.findViewById(R.id.pb_Loading);

	//��ʼ��ini��ʱ���˳����»�����
	//����ͷ����
	//�Ȳ���һ����
	mHeaderView.measure(0, 0);
	mHeaderViewHeight = mHeaderView.getMeasuredHeight();
	mHeaderView.setPadding(0, -mHeaderViewHeight, 0, 0);
	//��ʼ������
	initAnimation();
	//��ʼ��ʱ��
	setCurrentTime();
	
	}
	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		switch (ev.getAction()) {
		case MotionEvent.ACTION_DOWN:
			starY = (int) ev.getY();
			
			break;
case MotionEvent.ACTION_MOVE:
	 if (starY==-1) {//���û���סͷ����������ʱ Actiondown �ᱻviewpager���ѵ�������startyû�и�ֵ���˴�Ӧ�û�ȡһ��
		 starY =(int) ev.getY();
		
	}
	 //���������ˢ�� ��ô�ǲ��Ǹ�������
	if (CurrenState==START_END_REFRESH) {
		//����� ����ˢ�� ��ֱ������
		break;
	}
	
			int endy =(int) ev.getY();
			int dy = endy -starY;
		int first=	getFirstVisiblePosition();
			//ʲôʱ������� 1.�������� 2.���벢�ҵ�ǰ��ʾ���ǵ�һ��item
			if (dy>0 &&first ==0 ) {
				int pading =dy - mHeaderViewHeight;
				
			
				System.out.println("����ά˹"+ pading);
				mHeaderView.setPadding(0, pading, 0, 0);
				if (pading>0 &&  CurrenState!=START_GO_REFRESH) {
					CurrenState = START_GO_REFRESH;
					refreashState();
				}else if(pading<0 && CurrenState!= START_REFRESH){
					
					CurrenState = START_REFRESH;
					refreashState();
				}
				
				//���ѵ�
				return true;
			}
			
			break;
			
case MotionEvent.ACTION_UP:
	starY = -1;
	if (CurrenState==START_GO_REFRESH) {
		CurrenState =START_END_REFRESH;
		refreashState();
		//�ɿ��ֻ�����
		mHeaderView.setPadding(0, 0, 0, 0);
		//�ɿ��ֵ���һ��  4.���лص�
		if (myListener!=null) {
			myListener.onRefresh();
		}
		
	}else if(CurrenState==START_REFRESH){
		//����ͷ����
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
			tvtitle.setText("����ˢ��");
			ivArrow.startAnimation(animDown);
			bar.setVisibility(View.INVISIBLE);
			ivArrow.setVisibility(View.VISIBLE);

			break;
case START_GO_REFRESH:
	tvtitle.setText("�ɿ�ˢ��");
	ivArrow.startAnimation(animation);
	bar.setVisibility(View.INVISIBLE);
	ivArrow.setVisibility(View.VISIBLE);

			break;
case START_END_REFRESH:
	tvtitle.setText("����ˢ��");
	
	ivArrow.clearAnimation();//�����ͷ���������޷�����
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
	//3.�����Ա���������ܼ�������
	private OnRefreshListener myListener;
	private View mFootView;
	
	//2.�ѽӿڱ�¶����ѽ,���ü���
	public void setOnRefreshListener(OnRefreshListener listener){
		
		myListener =listener;
	}
	
	//1.�ص��ӿ�
	  public interface OnRefreshListener{
		public void onRefresh();
		//����
		public void onLoadFoot();  
		  
	  }
	//дһ��������ˢ��������
	  public void onRefreshComplete(boolean b){
		  if(!isLoading){
			  mHeaderView.setPadding(0, -mHeaderViewHeight, 0, 0);
			  //״̬Ҫ�İ�
			  CurrenState = START_REFRESH;
			  tvtitle.setText("����ˢ��");
			  bar.setVisibility(View.INVISIBLE);
			  ivArrow.setVisibility(View.VISIBLE);
			  
			  if (b) {
				setCurrentTime();
			}
			  
		  }//������������
		  else{
			  mFootView.setPadding(0, -mFootViewHeight, 0, 0);
			  isLoading =false;
			  
		  }
		
	  }
	  
	  //����ˢ��ʱ��
	  private void setCurrentTime() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	String time  =format.format(new Date());
	tvTime.setText(time);
	  }
	  
	//��� �ǲ���
	  private void initFootView() {
		//
		 
	
		  
	  	mFootView = View.inflate(getContext(), R.layout.loadingview_foot	, null);
this.addFooterView(mFootView); 
//����һ��
mFootView.measure(0, 0);
mFootViewHeight = mFootView.getMeasuredHeight();
   //�ڲ�padding�� ���  ������Ū��������
		mFootView  .setPadding(0, -mFootViewHeight, 0, 0);
		//����
		setOnScrollListener(this);
		  
	  }
//����̻ص�
	@Override
	public void onScroll(AbsListView arg0, int arg1, int arg2, int arg3) {
		
		
	}
	private boolean isLoading=false;
	private int page=1;
	private int mFootViewHeight;

//����״̬�ı仯
	@Override
	public void onScrollStateChanged(AbsListView arg0, int arg1) {
	if (arg1 ==SCROLL_STATE_IDLE ) {//����״̬
		int lastItem = getLastVisiblePosition();
		if (getCount()-1==lastItem&& isLoading==false) {
			System.out.println("���ظ���");
			mFootView.setPadding(0, 0, 0, 0);
			setSelection(lastItem);//��listview ��ʾ�����һ��item�� 
			isLoading =true;
	String		str =GlobleConstence.newsUrl3;
	//����Ϊʲô��
	++page;
	
	ksp = str +("page="+page);
	
	
	
	
			//���ظ���
			if (myListener!=null) {
				myListener.onLoadFoot();
			}
			
		}
	}
		
	}

	  
}
