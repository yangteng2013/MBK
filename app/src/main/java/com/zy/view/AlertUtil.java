package com.zy.view;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.zy.mbk.R;
import com.zy.utils.ImageUtil;


/**  
 * 提示信息工具类
* @author 田裕杰 
*  
*/
@SuppressLint("ResourceAsColor")
public class AlertUtil {

	/**
	 *  Toast方式显示提示信息，显示时间很短
	 * @param context
	 *            上下文
	 * @param message
	 *            显示内容
	 * */
	public static void ToastMessageShort(Context context, String message) {
		// Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
		ToastMessage(context, message, Toast.LENGTH_SHORT);
	}

	/**
	 *  Toast方式显示提示信息，显示时间稍长
	 * @param context
	 *            上下文
	 * @param message
	 *            显示内容
	 * */
	public static void ToastMessageLong(Context context, String message) {
		// Toast.makeText(context, message, Toast.LENGTH_LONG).show();
		ToastMessage(context, message, Toast.LENGTH_LONG);
	}

	private static void ToastMessage(Context context, String message,
			int duration) {
		TextView text = new TextView(context);
		text.setText(message);
		text.setBackgroundResource(R.mipmap.toast_bg);
		text.setTextColor(Color.WHITE);
		Toast toast = new Toast(context);
		toast.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 80);
		toast.setDuration(duration);
		toast.setView(text);
		toast.show();
	}

	/**
	 *  progressdialog 数据加载等待dialog（加入了超时监听）
	 * */
	public static IProgressDialog createProgressDialog(Activity context,
                                                       long time, String title, String message, IProgressDialog.OnTimeOutListener
                                                               listener) {
		IProgressDialog progressDialog = IProgressDialog.createProgressDialog(
				context, time, listener);
		progressDialog.setTitle(title);
		progressDialog.setMessage(message);
		progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		progressDialog.setProgress(0);
		progressDialog.setCancelable(false);
		return progressDialog;
	}

	/**
	 * Dialog方式显示提示信息（包含确认和取消按钮）
	 * @param activity activity对象
	 * @param message 提示信息内容
	 * @param callback 点击事件回调方法
	 */
	public static void ShowAlertDialog(Activity activity,String message,AlertCallBack callback){
		ShowAlertDialog(activity, "确定", "取消", "温馨提示", message, callback);
	}
	/**
	 * Dialog方式显示提示信息（包含确认和取消按钮）
	 * @param activity activity对象
	 * @param submitStr 确认按钮按键名
	 * @param cancelStr 取消按钮按键名
	 * @param title 提示标题
	 * @param message 提示信息内容
	 * @param callback 点击事件回调方法
	 */
	public static void ShowAlertDialog(Activity activity, String submitStr,
			String cancelStr, String title, String message,
			final AlertCallBack callback) {
//		final Dialog dlg  = new Dialog(activity,R.style.dialog_style);
		final AlertDialog dlg = new AlertDialog.Builder(activity).create();
		dlg.setCancelable(false);
		dlg.show();
		Window window = dlg.getWindow();
		// *** 主要就是在这里实现这种效果的.
		// 设置窗口的内容页面,shrew_exit_dialog.xml文件中定义view内容
		window.setContentView(R.layout.dialog_hint_layout);
		// 为确认按钮添加事件,执行退出应用操作
		TextView msg_tv = (TextView) window.findViewById(R.id.dialog_hint_msg);
		TextView title_tv = (TextView) window.findViewById(R.id.dialog_hint_title);
		Button submit = (Button) window.findViewById(R.id.dialog_hint_submit);
		Button cancel = (Button) window.findViewById(R.id.dialog_hint_cancel);
		submit.setBackgroundDrawable(ImageUtil.getInstance()
				.getStateListDrawable(submit.getBackground()));
		cancel.setBackgroundDrawable(ImageUtil.getInstance()
				.getStateListDrawable(cancel.getBackground()));
		title_tv.setText(title);
		msg_tv.setText(message);
		submit.setText(submitStr);
		cancel.setText(cancelStr);
		submit.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				dlg.dismiss();
				callback.onPositive();
			}
		});
		cancel.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				dlg.dismiss();
				callback.onNegative();
			}
		});
	}
	
	
	public static void ShowAlertDialogForList(Activity activity, String title,final String[] str,
			final ListCallBack callback) {
//		final Dialog dlg  = new Dialog(activity,R.style.dialog_style);

		final AlertDialog dlg = new AlertDialog.Builder(activity).create();
		dlg.setCancelable(false);
		dlg.show();
		Window window = dlg.getWindow();
		// *** 主要就是在这里实现这种效果的.
		// 设置窗口的内容页面,shrew_exit_dialog.xml文件中定义view内容
		window.setContentView(R.layout.dialog_forlist_layout);
		// 为确认按钮添加事件,执行退出应用操作
//		TextView msg_tv = (TextView) window.findViewById(R.id.dialog_hint_msg);
		TextView title_tv = (TextView) window.findViewById(R.id.dialog_hint_title);
		
		title_tv.setText(title);
		
		ListView listv = (ListView) window.findViewById(R.id.dialog_forlist_msg);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(activity, android.R.layout.simple_list_item_1, str);

		listv.setAdapter(adapter);
		listv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				dlg.dismiss();
				callback.getSelect(str[arg2]);
			}
			
		});
		
		
		
	}

	/**
	 * Dialog方式显示提示信息（包含确认按钮）
	 * @param activity activity对象
	 * @param msg 提示信息
	 * @param callback 点击事件回调方法
	 */
	public static void ShowHintDialog(Activity activity,String msg, final AlertCallBack callback) {
		ShowHintDialog(activity, "确定","温馨提示", msg, callback);
	}
	/**
	 * Dialog方式显示提示信息（包含确认按钮）
	 * @param activity activity对象
	 * @param submitStr 确认按钮按键名
	 * @param title 标题
	 * @param msg 提示信息内容
	 * @param callback 点击事件回调方法
	 */
	public static void ShowHintDialog(Activity activity, String submitStr,
			String title, String msg, final AlertCallBack callback) {
//		final Dialog dlg  = new Dialog(activity,R.style.dialog_style);
		final AlertDialog dlg = new AlertDialog.Builder(activity).create();
		dlg.setCancelable(false);
		dlg.show();
		Window window = dlg.getWindow();
		// *** 主要就是在这里实现这种效果的.
		// 设置窗口的内容页面,shrew_exit_dialog.xml文件中定义view内容
		window.setContentView(R.layout.dialog_hint_layout);
		// 为确认按钮添加事件,执行退出应用操作
		TextView msg_tv = (TextView) window.findViewById(R.id.dialog_hint_msg);
		TextView title_tv = (TextView) window.findViewById(R.id.dialog_hint_title);
		Button submit = (Button) window.findViewById(R.id.dialog_hint_submit);
		Button cancel = (Button) window.findViewById(R.id.dialog_hint_cancel);
		submit.setBackgroundDrawable(ImageUtil.getInstance()
				.getStateListDrawable(submit.getBackground()));
		cancel.setBackgroundDrawable(ImageUtil.getInstance()
				.getStateListDrawable(cancel.getBackground()));
		cancel.setVisibility(View.GONE);
		title_tv.setText(title);
		msg_tv.setText(msg);
		submit.setText(submitStr);
		submit.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				dlg.dismiss();
				if (callback!=null) {
					callback.onPositive();
				}
			}
		});
	}
	
	
	public static void ShowHintDialogNotCallBack(Activity activity, String submitStr,
			String title, String msg) {
//		System.out.println("new AlertDialog.Builder");
		final Dialog dlg  = new Dialog(activity,R.style.dialog_style);
//		final AlertDialog dlg = new AlertDialog.Builder(activity).create();
		dlg.setCancelable(false);
		dlg.show();
		Window window = dlg.getWindow();
		// *** 主要就是在这里实现这种效果的.
		// 设置窗口的内容页面,shrew_exit_dialog.xml文件中定义view内容
		window.setContentView(R.layout.dialog_hint_layout);
		// 为确认按钮添加事件,执行退出应用操作
		TextView msg_tv = (TextView) window.findViewById(R.id.dialog_hint_msg);
		TextView title_tv = (TextView) window.findViewById(R.id.dialog_hint_title);
		Button submit = (Button) window.findViewById(R.id.dialog_hint_submit);
		Button cancel = (Button) window.findViewById(R.id.dialog_hint_cancel);
		submit.setBackgroundDrawable(ImageUtil.getInstance().getStateListDrawable(submit.getBackground()));
		cancel.setBackgroundDrawable(ImageUtil.getInstance().getStateListDrawable(cancel.getBackground()));
		cancel.setVisibility(View.GONE);
		title_tv.setText(title);
		msg_tv.setText(msg);
		submit.setText(submitStr);
		submit.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				dlg.dismiss();
			}
		});
	}
	
	/**
	 * final AlertDialog dlg = new AlertDialog.Builder(activity).create();
		dlg.setCancelable(false);
		dlg.show();
		Window window = dlg.getWindow();
		// *** 主要就是在这里实现这种效果的.
		// 设置窗口的内容页面,shrew_exit_dialog.xml文件中定义view内容
		window.setContentView(R.layout.dialog_hint_layout);
		// 为确认按钮添加事件,执行退出应用操作
		TextView msg_tv = (TextView) window.findViewById(R.id.dialog_hint_msg);
		TextView title_tv = (TextView) window
				.findViewById(R.id.dialog_hint_title);
		Button submit = (Button) window.findViewById(R.id.dialog_hint_submit);
		Button cancel = (Button) window.findViewById(R.id.dialog_hint_cancel);
		submit.setBackgroundDrawable(ImageUtil.getInstance()
				.getStateListDrawable(submit.getBackground()));
		cancel.setBackgroundDrawable(ImageUtil.getInstance()
				.getStateListDrawable(cancel.getBackground()));
		cancel.setVisibility(View.GONE);
		title_tv.setText(title);
		msg_tv.setText(msg);
		submit.setText(submitStr);
		submit.setOnClickListener(new android.view.View.OnClickListener() {

			@Override
			public void onClick(View v) {
				dlg.dismiss();
				if (callback!=null) {
					callback.onPositive();
				}
			}
		});
	 */
	
	
	
	/**
	 *  Dialog点击事件回调接口
	 * */
	public interface AlertCallBack {
		/**
		 *  确定按钮回调
		 * */
		public void onPositive();

		/**
		 *  取消按钮回调
		 * */
		public void onNegative();
	}
	
	/**
	 *  Dialog点击事件回调接口 for弹窗选择回调返值
	 * */
	public interface ListCallBack {
		public void getSelect(String s);
	}

	public void showDialog(){
//        new MyDialogFragment().show(getFragmentManager(),"dialog");
	}

}
