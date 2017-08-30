package com.zy.utils;

import android.content.Intent;
import android.os.Environment;

import com.zy.mbk.AppIns;
import com.zy.mbk.MainActivity;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.lang.Thread.UncaughtExceptionHandler;
import java.util.Calendar;

/**
 * 异常处理
 *
 */
public class UEHandler implements UncaughtExceptionHandler {
	private AppIns softApp;

	public UEHandler(AppIns app) {
		softApp = app;
	}
	
	// 获得异常信息
	public void uncaughtException(Thread thread, Throwable ex) {
		// 此处示例发生异常后，重新启动应用
		ex.printStackTrace();
		Intent intent = new Intent(softApp, MainActivity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		softApp.startActivity(intent);
//		Property.isinitCLIENT=false;
//		printException(ex);//错误日志文件
		android.os.Process.killProcess(android.os.Process.myPid());
	}
	/*
	 *保存Exception异常信息
	 */
 	public static void printException(Throwable ex) {
		File fir=Environment.getExternalStorageDirectory();
		try {File file = new File(fir+"/Android","MBKlog.txt");
		FileWriter fw = new FileWriter(file, true);
		PrintWriter pw = new PrintWriter(fw);
		ex.printStackTrace(pw);
//		String str=Property.UserMohile+"\n"+getDatatime()+"\n";
		String str="";
		pw.write(str);
		if(fw != null){
			fw.close();
		}
		if(pw != null){
			pw.close();
		}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
 	public static String getDatatime(){
		StringBuilder sb=new StringBuilder();
		int Year,Month,Day,HOUR,MINUTE,SECOND;
		final Calendar c = Calendar.getInstance();
		Year = c.get(Calendar.YEAR);
		Month = c.get(Calendar.MONTH);
		Day = c.get(Calendar.DAY_OF_MONTH);
		HOUR=c.get(Calendar.HOUR);
		MINUTE=c.get(Calendar.MINUTE);
		SECOND=c.get(Calendar.SECOND);
		sb.append(Year);
		sb.append("-");
		sb.append(Month+1);
		sb.append("-");
		sb.append(Day);
		sb.append(" ");
		sb.append(HOUR);
		sb.append(":");
		sb.append(MINUTE);
		sb.append(":");
		sb.append(SECOND);
		return sb.toString();
	}
}