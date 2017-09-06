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

import com.orhanobut.logger.Logger;
import com.zy.mbk.R;
import com.zy.utils.ImageUtil;

/**
 * Created by Administrator on 2017/8/30.
 */

public class MyDialogFragment extends DialogFragment{
    private String title ,message,submitStr,cancelStr;
    private AlertUtil.AlertCallBack callBack;

    public void initInfo(@Nullable String title ,String message,@Nullable String submitStr,@Nullable String cancelStr,@Nullable AlertUtil.AlertCallBack callBack){
        this.title = title;
        this.message = message;
        this.submitStr = submitStr;
        this.cancelStr = cancelStr;
        this.callBack =callBack;
    }


    static  MyDialogFragment myDialogFragment;
    public static  MyDialogFragment getInstance(){
        if(myDialogFragment==null){
            myDialogFragment= new MyDialogFragment();
        }
        return myDialogFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置无标题 无边框 应用自定义样式
        Logger.d("onCreate");
        setStyle(DialogFragment.STYLE_NO_TITLE,R.style.dialog_style);
    }

    @Override
    public void onStart() {
        getDialog().setCancelable(false);

        //横向充满
//        getDialog().getWindow().getAttributes().width=getResources().getDisplayMetrics().widthPixels;
        //对齐方式
        Logger.d("onStart");
        getDialog().getWindow().setGravity(Gravity.CENTER);
        super.onStart();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);
        //3 可在此处设置 无标题 对话框背景色
//        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        // //对话框背景色
        //getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.RED));
        //getDialog().getWindow().setDimAmount(0.5f);//背景黑暗度
        Logger.d("onCreateView");
        //不能在此处设置style
        // setStyle(DialogFragment.STYLE_NORMAL,R.style.dialog_style);//在此处设置主题样式不起作用

        View view= inflater.inflate(R.layout.dialog_hint_layout,container,false);
        TextView msg_tv = (TextView) view.findViewById(R.id.dialog_hint_msg);
        TextView title_tv = (TextView) view.findViewById(R.id.dialog_hint_title);
        Button submit = (Button) view.findViewById(R.id.dialog_hint_submit);
        Button cancel = (Button) view.findViewById(R.id.dialog_hint_cancel);
        submit.setBackgroundDrawable(ImageUtil.getInstance()
                .getStateListDrawable(submit.getBackground()));
        cancel.setBackgroundDrawable(ImageUtil.getInstance()
                .getStateListDrawable(cancel.getBackground()));
        if(title!=null&&!"".equals(title)){
            title_tv.setText(title);
        }else if(savedInstanceState!=null){
            String str =  savedInstanceState.getString("title");
            title_tv.setText(str == null?"" : str);
        }else{
            title_tv.setText("温馨提示");
        }

        if(submitStr!=null&&!"".equals(submitStr)){
            submit.setText(submitStr);
        }else if(savedInstanceState!=null){
            String str =  savedInstanceState.getString("submitStr");
            submit.setText(str == null?"" : str);
        }else{
            submit.setText("确定");
        }
        if(cancelStr!=null&&!"".equals(cancelStr)){
            cancel.setText(cancelStr);
        }else if(savedInstanceState!=null){
            String str =  savedInstanceState.getString("cancelStr");
            cancel.setText(str == null?"" : str);
        }else{
            cancel.setText("取消");
        }

        if(message!=null&&!"".equals(message)){
            msg_tv.setText(message);
        }else {
            if (savedInstanceState != null) {
                String str = savedInstanceState.getString("cancelStr");
                cancel.setText(str == null ? "" : str);
            }
        }

        submit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(callBack!=null) {
                    callBack.onPositive();
                }
                dismiss();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(callBack!=null) {
                    callBack.onNegative();
                }
                dismiss();
            }
        });
        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putString("title",title);
        outState.putString("message",message);
        outState.putString("submitStr",submitStr);
        outState.putString("cancelStr",cancelStr);

        super.onSaveInstanceState(outState);
    }
}
