package com.example.qingdao.domain;

import java.util.ArrayList;

//������Ϣ��װ 
public class NewsMenue {
//��һ�� �����ֵĻ� ��������
// public int retcode;
//������������������ֵĻ�
	//public ArrayList<Integer>extend;
	//data ����������  ���������ֵ����ͣ��ֵ���Ǹ����� �����ڰ�׿��Ūһ������
	
	public ArrayList<NewMenuData>  data;
	//�˵��Ĳ��������
	public static class NewMenuData{
		
		public String title;

		@Override
		public String toString() {

			return "NewMenuData [title=" + title + "]";
		}
		
		
		//ͬ���µ�children
	//	public ArrayList children;
		
	}
//	//ҳǩ�Ķ���
//	public class NewsTabData{
//		
//		public String title;
//		
//	}
	
	
}
