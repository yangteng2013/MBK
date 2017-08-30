package com.zy.utils;

import android.os.CountDownTimer;
import android.widget.Button;

import com.zy.mbk.R;


/**
 * 定时器  控制短信验证码发送
 * */
public class SmsTimer extends CountDownTimer{

	private Button button;
	private long countDownInterval = 1000;
	/**
	 * @param millisInFuture 定时总时间
	 * @param countDownInterval 执行时间间隔
	 * @param button 获取验证码按钮
	 * */
	public SmsTimer(long millisInFuture, long countDownInterval,Button button) {
		super(millisInFuture-1, countDownInterval);
		this.button = button;
		this.countDownInterval = countDownInterval;
		
	}

	@Override
	public void onFinish() {
		button.setClickable(true);
		button.setText("发送");
		button.setBackgroundResource(R.drawable.regist_click_unpress);
	}
	
	@Override
	public void onTick(long millisUntilFinished) {
		button.setClickable(false);
		button.setText(millisUntilFinished/countDownInterval + "s");
	}
	public void stop(){
		this.cancel();
		this.onFinish();
	}
	
}
