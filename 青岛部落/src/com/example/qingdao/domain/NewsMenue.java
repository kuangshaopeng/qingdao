package com.example.qingdao.domain;

import java.util.ArrayList;

//分类信息封装 
public class NewsMenue {
//第一层 有数字的话 就生成呗
// public int retcode;
//如果是数据里面是数字的话
	//public ArrayList<Integer>extend;
	//data 是数组类型  数组里是字典类型，字典就是个对象 所以在安卓里弄一个对象
	
	public ArrayList<NewMenuData>  data;
	//菜单的侧边栏对象
	public static class NewMenuData{
		
		public String title;

		@Override
		public String toString() {

			return "NewMenuData [title=" + title + "]";
		}
		
		
		//同类下的children
	//	public ArrayList children;
		
	}
//	//页签的对象
//	public class NewsTabData{
//		
//		public String title;
//		
//	}
	
	
}
