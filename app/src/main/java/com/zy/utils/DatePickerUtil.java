package com.zy.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zy.mbk.R;
import com.zy.view.datepicker.DatePickerView;

/**
 * 日期选择器工具类（调用日期控件）
 * @author 田裕杰
 */
public class DatePickerUtil {

	
	public static DatePickerUtil dpUtil = null;
	public static DatePickerUtil getInstance(){
		if (dpUtil==null) {
			dpUtil = new DatePickerUtil();
		}
		return dpUtil;
	}
	
	/**
	 * 显示日期选择器控件，选择日期结果以回调方式返回
	 * @param activity  
	 * @param date  日期选择器显示初始日期
	 * @param callBack  回调
	 */
	public void showDatePickerDialog(final Activity activity, String date, final DatePickerCallBack callBack){
		final DatePickerView pickerview = new DatePickerView(activity);
		pickerview.setBeginDate(date);
		final AlertDialog dlg = new AlertDialog.Builder(activity).create();
		dlg.setCancelable(false);
		dlg.show();
		Window window = dlg.getWindow();
		// *** 主要就是在这里实现这种效果的.
		// 设置窗口的内容页面,shrew_exit_dialog.xml文件中定义view内容
		window.setContentView(R.layout.dialog_datepicker_layout);
		// 为确认按钮添加事件,执行退出应用操作
		LinearLayout content_ll = (LinearLayout) window.findViewById(R.id.dialog_content_ll);
		content_ll.addView(pickerview);
		TextView title_tv = (TextView) window
				.findViewById(R.id.dialog_hint_title);
		Button submit = (Button) window.findViewById(R.id.dialog_hint_submit);
		Button cancel = (Button) window.findViewById(R.id.dialog_hint_cancel);
		submit.setBackgroundDrawable(ImageUtil.getInstance()
				.getStateListDrawable(submit.getBackground()));
		cancel.setBackgroundDrawable(ImageUtil.getInstance()
				.getStateListDrawable(cancel.getBackground()));
		cancel.setVisibility(View.GONE);
		title_tv.setText("选择日期");
		submit.setText("确定");
		submit.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				dlg.dismiss();
				callBack.onResult(pickerview.getDate());
			}
		});
		
		
	}
	public interface DatePickerCallBack{
		public void onResult(String date);
	}
}
