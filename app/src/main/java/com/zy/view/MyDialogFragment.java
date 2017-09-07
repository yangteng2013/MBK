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
    private AlertUtil.AlertCallBack callBack;

    static  MyDialogFragment myDialogFragment;
    public static  MyDialogFragment getInstance(@Nullable String title ,String message,@Nullable String submitStr,@Nullable String cancelStr,@Nullable AlertUtil.AlertCallBack callBack ){
        if(myDialogFragment==null){
            myDialogFragment= new MyDialogFragment();
        }
        Bundle bundle  = new Bundle();
        bundle.putString("title",title);
        bundle.putString("message",message);
        bundle.putString("submitStr",submitStr);
        bundle.putString("cancelStr",cancelStr);
        myDialogFragment.setArguments(bundle);
        return myDialogFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置无标题 无边框 应用自定义样式
        setStyle(DialogFragment.STYLE_NO_TITLE,R.style.dialog_style);
        if(savedInstanceState!=null){
            getFragmentManager();
        }
    }

    @Override
    public void onStart() {
        getDialog().setCancelable(false);

        //横向充满
//        getDialog().getWindow().getAttributes().width=getResources().getDisplayMetrics().widthPixels;
        //对齐方式
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

        if(getArguments().getString("title")!=null){
            title_tv.setText(getArguments().getString("title"));
        }
        if(getArguments().getString("submitStr")!=null){
            submit.setText(getArguments().getString("submitStr"));
        }
        if(getArguments().getString("cancelStr")!=null){
            cancel.setText(getArguments().getString("cancelStr"));
        }
        if(getArguments().getString("message")!=null){
            msg_tv.setText(getArguments().getString("message"));
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
    public void onViewStateRestored(Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
    }

}
