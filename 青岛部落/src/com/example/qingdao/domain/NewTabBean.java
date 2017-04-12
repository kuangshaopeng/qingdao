package com.example.qingdao.domain;

import java.util.ArrayList;

import android.preference.PreferenceActivity.Header;

public class NewTabBean {
public  ArrayList<Newsheader> header;
public  ArrayList<NewsList> list;

	
public class  Newsheader{
	 

   // public  int  id;
	public  String url;
	public  String pic;
	public  String name;
	public  String board_name;
	
}
public class  NewsList{
	
	public  String url ;
	public  String pic ;
	public  String name;
	public  String author;
	public  String timestamp;
	public  String board_name;
	public  String topic_id;
}

}
