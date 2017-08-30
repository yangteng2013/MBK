package com.zy.view;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;

import java.util.Timer;
import java.util.TimerTask;

/**  
 * 自定义ProgressDialog
* @author 田裕杰 
*  
*/
public class IProgressDialog extends android.app.ProgressDialog {

    public static final String TAG = "ProgressDialog";
    private long mTimeOut = 0;// 默认timeOut为0即无限大
    private OnTimeOutListener mTimeOutListener = null;// timeOut后的处理器
    private Timer mTimer = null;// 定时器
    private Activity activity;
    private Handler mHandler = new Handler(){

        @Override
        public void handleMessage(Message msg) {
            if(mTimeOutListener != null){
                mTimeOutListener.onTimeOut(IProgressDialog.this);
            }
            dismiss();
        }
    };

    public IProgressDialog(Activity activity) {
        super(activity.getApplicationContext());
        this.activity = activity;
    }
    @Override
    public void dismiss() {
    	if (!activity.isFinishing()&&this.isShowing()) {
    		super.dismiss();
		}
    }
    /**
     * 设置timeOut长度，和处理器
     * 
     * @param t
     *            timeout时间长度
     * @param timeOutListener
     *            超时后的处理器
     */
    public void setTimeOut(long t, OnTimeOutListener timeOutListener) {
        mTimeOut = t;
        if (timeOutListener != null) {
            this.mTimeOutListener = timeOutListener;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mTimer != null) {
            mTimer.cancel();
            mTimer = null;
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        if (mTimeOut != 0) {
            mTimer = new Timer();
            TimerTask timerTast = new TimerTask() {
                @Override
                public void run() {
                //    dismiss();
                        Message msg = mHandler.obtainMessage();
                        mHandler.sendMessage(msg);
                }
            };
            mTimer.schedule(timerTast, mTimeOut);
        }

    }

    /**
     * 通过静态Create的方式创建一个实例对象
     *
     * @param time    
     *                 timeout时间长度
     * @param listener    
     *                 timeOutListener 超时后的处理器
     * @return MyProgressDialog 对象
     */
    public static IProgressDialog createProgressDialog(Activity activity,
                                                       long time, OnTimeOutListener listener) {
        IProgressDialog progressDialog = new IProgressDialog(activity);
        if (time != 0) {
            progressDialog.setTimeOut(time, listener);
        }
        return progressDialog;
    }

    /**
     * 
     * 处理超时的的接口
     *
     */
    public interface OnTimeOutListener {
        
        /**
         * 当progressDialog超时时调用此方法
         */
        abstract public void onTimeOut(IProgressDialog dialog);
    }
}