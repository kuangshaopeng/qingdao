package com.example.qingdao;

import com.example.qingdao.Tool.Tool;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.RelativeLayout;

public class StartActivity extends Activity {

	private RelativeLayout lay;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_staranimation);
		
	lay = (RelativeLayout) findViewById(R.id.rl_root);
	//������ת����
	RotateAnimation rote = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
		rote.setDuration(1000);
		rote.setFillAfter(true);
		//����scale����
		ScaleAnimation scale = new ScaleAnimation(0, 1, 0, 1, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
		scale.setDuration(1000);
		scale.setFillAfter(true);
		//���ý��䶯��
		AlphaAnimation Alpha =new AlphaAnimation(0, 1);
		Alpha.setDuration(2000);
		Alpha.setFillAfter(true);
		//��ʼ����
		AnimationSet Set =new AnimationSet(true);
		Set.addAnimation(rote);
		Set.addAnimation(scale);
		Set.addAnimation(Alpha);
		lay.startAnimation(Set);
		
		Set.setAnimationListener(new AnimationListener() {
			
			@Override
			public void onAnimationStart(Animation arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onAnimationRepeat(Animation arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onAnimationEnd(Animation animation) {
				// TODO Auto-generated method stub
				//����������ת����
				//�����һ�����������ڶ�����������
				 //userDefault// �ڶ�������ΪĬ��ֵ
//			SharedPreferences share=	getSharedPreferences("config", MODE_PRIVATE);
//			 boolean isFirstEnter =  share.getBoolean("is_first", true);
		
				
				boolean isFirstEnter = Tool.getBoolen(getApplicationContext(), "is_first", true);
			 Intent intent;
				if(isFirstEnter){
				 //��������ҳ��
				 intent = new Intent(getApplicationContext(),newGuide.class);
				 
			 }else{
				 //������
				 intent = new Intent(getApplicationContext(),mainActivity.class);
				 
				 
			 }
			 startActivity(intent);
				finish(); //������ǰҳ��
				
				
			}
		});
	}
  

	
	
}
