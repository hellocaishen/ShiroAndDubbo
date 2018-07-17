package com.wsbg.common;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @desc 杂牌工具类
 * ***/
public class AllTool {
	
	private static final String date = "yyyyMMdd";
    private static SimpleDateFormat sdf;
	/***
	 * 获取随机字符串长度
	 * */
	 public static String  randomNum(int num){
		 String string = "0123456789";
		 Random random =new Random();
		 StringBuilder sbff = new StringBuilder();
		 do{
			int in = random.nextInt(10);
			sbff.append(in+"");
			--num;
		  }while(num>0);
		 return sbff.toString();
	 }
	 
	 public static String getDateStr(Date dates){
		 sdf = new SimpleDateFormat(date);
		 return sdf.format(new Date());
	 }
	 
	 
	 public static Integer  randNum(){
		 Random random =new Random();
	     int in = random.nextInt(1000);
	     return in;
	 }
	 
	 public static void main(String[] args) {
		System.out.println(randNum());
		// System.out.println(getDateStr(new Date()));
	}
}
