package com.example.qingdao.Tool;

import android.content.Context;

public class DensityToll {
public static int dipToPx (float dip, Context ctx) {
	//√‹∂»÷µ
	float density = ctx.getResources().getDisplayMetrics().density;
	int px = (int) (dip*density+0.5f);
	
	return px;
}
	
	public static float pxTodip(int px, Context ctx) {
		float density = ctx.getResources().getDisplayMetrics().density;
		
		float dip = px/density;
		return dip;
		
	}
	
	
}
