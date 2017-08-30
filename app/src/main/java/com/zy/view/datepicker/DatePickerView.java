/**   
* @Title: DatePickerView.java 
* @Package cn.com.csii.widget.datepicker 
* @author A18ccms A18ccms_gmail_com
* @date 2015-2-4 下午2:41:57 
* @version V1.0   
*/
package com.zy.view.datepicker;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.widget.LinearLayout;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * @author 田裕杰
 * @ClassName: DatePickerView
 */
public class DatePickerView extends LinearLayout{

	private Context context;
	public static final int year = 0x9f019001;
	public static final int month = 0x9f019002;
	public static final int day = 0x9f019003;
	WheelDatePicker wheelMain;
	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	 /** 
	  * @param @param context
	  * @param @param attrs    设定文件 
	  * @return     返回类型 
	  * @throws 
	  */
	public DatePickerView(Context context) {
		super(context);
		this.context = context;
		init();
	}
	
	/** 
	 * @param @param context
	 * @param @param attrs    设定文件 
	 * @return     返回类型 
	 * @throws 
	 */
	public DatePickerView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
		init();
	}
	public void init(){
		this.setOrientation(LinearLayout.HORIZONTAL);
		this.addView(getWheelView(year));
		this.addView(getWheelView(month));
		this.addView(getWheelView(day));
		
		
		wheelMain = new WheelDatePicker(context, this);
		wheelMain.screenheight = getMetricsHeight(context);
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(System.currentTimeMillis());
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		wheelMain.initDateTimePicker(year, month, day);
	}
	public void setBeginDate(String date){
		Calendar calendar = Calendar.getInstance();
		if (date!=null&&!"".equals(date)&&JudgeDate.isDate(date, "yyyy-MM-dd")) {
			try {
				calendar.setTime(dateFormat.parse(date));
			} catch (ParseException e) {
			}
		}
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		wheelMain.initDateTimePicker(year, month, day);
	}
	public String getDate(){
		return wheelMain.getDate();
	}
	public WheelView getWheelView(int id){
		WheelView wheelView = new WheelView(context);
		LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
		params.weight = 1;
		wheelView.setLayoutParams(params);
		wheelView.setId(id);
		return wheelView;
	}
	public int getMetricsHeight(Context context) {
		// String str = "";
		DisplayMetrics dm = new DisplayMetrics();
		dm = context.getResources().getDisplayMetrics();
		int screenHeight = dm.heightPixels;// 屏幕高（像素，如：800px）
		return screenHeight;
	}
}
