package com.zy.view;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.zy.mbk.R;
import com.zy.utils.ImageUtil;

/**
 * Created by Administrator on 2017/8/30.
 */

public class MyDialogFragment extends DialogFragment{
   private String title ,message,submitStr,cancelStr;
   private AlertUtil.AlertCallBack callBack;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setSubmitStr(String submitStr) {
        this.submitStr = submitStr;
    }

    public void setCancelStr(String cancelStr) {
        this.cancelStr = cancelStr;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置无标题 无边框 应用自定义样式
        setStyle(DialogFragment.STYLE_NO_TITLE|DialogFragment.STYLE_NO_FRAME,R.style.dialog_style);
    }

    @Override
    public void onStart() {
        //横向充满
        getDialog().getWindow().getAttributes().width=getResources().getDisplayMetrics().widthPixels;
        //对齐方式
        getDialog().getWindow().setGravity(Gravity.BOTTOM);
        super.onStart();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);
        //3 可在此处设置 无标题 对话框背景色
        //getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        // //对话框背景色
        //getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.RED));
        //getDialog().getWindow().setDimAmount(0.5f);//背景黑暗度

        //不能在此处设置style
        // setStyle(DialogFragment.STYLE_NORMAL,R.style.dialog_style);//在此处设置主题样式不起作用

        View view= inflater.inflate(R.layout.dialog_hint_layout,container);
        TextView msg_tv = (TextView) view.findViewById(R.id.dialog_hint_msg);
        TextView title_tv = (TextView) view.findViewById(R.id.dialog_hint_title);
        Button submit = (Button) view.findViewById(R.id.dialog_hint_submit);
        Button cancel = (Button) view.findViewById(R.id.dialog_hint_cancel);
        submit.setBackgroundDrawable(ImageUtil.getInstance()
                .getStateListDrawable(submit.getBackground()));
        cancel.setBackgroundDrawable(ImageUtil.getInstance()
                .getStateListDrawable(cancel.getBackground()));
        if(title!=null&&"".equals(title)){
            title_tv.setText("温馨提示");
        }else{
            title_tv.setText(title);
        }

        if(submitStr!=null&&"".equals(submitStr)){
            submit.setText("确定");
        }else{
            submit.setText(submitStr);
        }
        if(cancelStr!=null&&"".equals(cancelStr)){
            cancel.setText("取消");
        }else{
            cancel.setText(cancelStr);
        }
        msg_tv.setText(message);
        submit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(callBack!=null) {
                    callBack.onPositive();
                }
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(callBack!=null) {
                    callBack.onNegative();
                }
            }
        });
        return view;
    }

    public void setAlertCallBack(AlertUtil.AlertCallBack callBack){
        this.callBack =callBack;
    }


}
