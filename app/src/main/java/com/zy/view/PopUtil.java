package com.zy.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.zy.mbk.R;

/**
 * Created by zy on 2017/9/4.
 */

public class PopUtil {
    public static void  showDiaogPop(Context context,View Location ,String title,String msg,String ensure,String cancer,final PopCallBack popCallBack){
        View popContent = View.inflate(context, R.layout.popwindow, null);
        TextView tv_pop_title = (TextView)popContent.findViewById(R.id.tv_pop_title);
        if(title!=null&&!"".equals(title)){
            tv_pop_title.setText(title);
        }else{
            tv_pop_title.setText("温馨提示");
        }
        TextView tv_pop_msg = (TextView)popContent.findViewById(R.id.tv_pop_msg);
        tv_pop_msg.setText(msg);

        Button btn_pop_cancer = (Button) popContent.findViewById(R.id.btn_pop_cancer);
        if(cancer!=null&&!"".equals(cancer)){
            btn_pop_cancer.setText(cancer);
        }else{
            btn_pop_cancer.setText("取消");
        }
//        btn_pop_cancer.setBackgroundDrawable(ImageUtil.getInstance()
//                .getStateListDrawable(btn_pop_cancer.getBackground()));

        Button btn_pop_ensure = (Button) popContent.findViewById(R.id.btn_pop_ensure);
        if(ensure!=null&&!"".equals(ensure)){
            btn_pop_ensure.setText(ensure);
        }else{
            btn_pop_ensure.setText("确定");
        }
//        btn_pop_ensure.setBackgroundDrawable(ImageUtil.getInstance()
//                .getStateListDrawable(btn_pop_ensure.getBackground()));

        final PopupWindow popupWindow = new PopupWindow(popContent, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        popupWindow.showAtLocation(Location, Gravity.CENTER, 0, 0);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.BLACK));

        btn_pop_cancer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();
                popCallBack.negtiveCallBack();
            }
        });


        btn_pop_ensure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();
                popCallBack.positiveCallBack();
            }
        });
    }

    public interface PopCallBack{
        public void positiveCallBack();
        public void negtiveCallBack();
    }
}
