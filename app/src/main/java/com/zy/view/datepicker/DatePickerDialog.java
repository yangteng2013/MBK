package com.zy.view.datepicker;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.Display;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

import com.zy.mbk.R;

@SuppressLint("NewApi")
public class DatePickerDialog {
	private Context context;
	private Dialog dialog;
	private LinearLayout dialog_Group;
	private Button btn_pos;
	private Display display;
	public DatePickerDialog(Context context) {
		this.context = context;
		WindowManager windowManager = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		display = windowManager.getDefaultDisplay();
	}

	public DatePickerDialog builder() {
		// 获取Dialog布局
		LinearLayout view = new LinearLayout(context);
		view.setOrientation(LinearLayout.VERTICAL);
		GradientDrawable drawable = new GradientDrawable();
		drawable.setCornerRadius(20);
		drawable.setColor(0xffeeeeee);
		view.setBackground(drawable);
		view.setLayoutParams(new LayoutParams((int) (display
				.getWidth() * 0.85), LayoutParams.WRAP_CONTENT));
		dialog_Group = new LinearLayout(context);
		btn_pos = new Button(context);
		btn_pos.setBackgroundColor(0);
		btn_pos.setTextColor(Color.BLUE);

		view.addView(dialog_Group);
		view.addView(btn_pos);
		
		// 定义Dialog布局和参数
		dialog = new Dialog(context, R.style.dialog_style);
		dialog.setTitle("日期选择");
		dialog.setContentView(view);

		return this;
	}


	public DatePickerDialog setView(View view) {
			dialog_Group.addView(view,
					android.view.ViewGroup.LayoutParams.MATCH_PARENT,
					android.view.ViewGroup.LayoutParams.MATCH_PARENT);
		return this;
	}

	public DatePickerDialog setCancelable(boolean cancel) {
		dialog.setCancelable(cancel);
		return this;
	}

	public DatePickerDialog setPositiveButton(String text,
			final OnClickListener listener) {
		if ("".equals(text)) {
			btn_pos.setText("确定");
		} else {
			btn_pos.setText(text);
		}
		btn_pos.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				listener.onClick(v);
				dialog.dismiss();
			}
		});
		return this;
	}
	

	public void dismiss(){
		if (dialog.isShowing()) {
			dialog.dismiss();
		}
	}
	public void show() {
		dialog.show();
	}
}
