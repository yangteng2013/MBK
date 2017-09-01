package com.zy.mbk;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.orhanobut.logger.Logger;
import com.zy.view.AlertUtil;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Logger.d("onCreate()");
        setContentView(R.layout.activity_main);
        TextView tv_ma_p1 = (TextView)this.findViewById(R.id.tv_ma_p1);
        tv_ma_p1.setText(NativeUtil.getSha1String());
//        Logger.d("native:"+NativeUtil.getSha1String());
//        Logger.d("getpakage:"+ PakageInfo.getCertificateSHA1Fingerprint(this));
//        Logger.d("getMetaData:"+PakageInfo.getMetaData(this,"UMENG_CHANNEL"));
    }

    @Override
    protected void onStart() {
        Logger.d("onStart()");
        super.onStart();
    }

    @Override
    protected void onRestart() {
        Logger.d("onRestart()");
        super.onRestart();
    }

    @Override
    protected void onResume() {
        Logger.d("onResume()");
        super.onResume();
    }

    @Override
    protected void onPause() {
        Logger.d("onPause()");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Logger.d("onStop()");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Logger.d("onDestroy()");
        super.onDestroy();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
       String string =newConfig.orientation==Configuration.ORIENTATION_LANDSCAPE?"横屏":"竖屏";
        Logger.d(string);
    }

    public void lpChange(View v){
        switch (v.getId()){
            case R.id.button:
                if (getResources().getConfiguration().orientation==Configuration.ORIENTATION_PORTRAIT) {
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                }else{
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                }
              break;
            case R.id.button2:
                startActivity(new Intent(this,ActivityliveTest.class));
                break;
            case R.id.button3:
                View popContent =View.inflate(this,R.layout.popwindow,null);
                final PopupWindow popupWindow  = new  PopupWindow(popContent, ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT,true);
                popupWindow.showAtLocation(v, Gravity.CENTER,0,0);
                popupWindow.setBackgroundDrawable(new ColorDrawable(Color.BLACK));
                Button btn_pop_cancer =(Button)popContent.findViewById(R.id.btn_pop_cancer);
                btn_pop_cancer.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        popupWindow.dismiss();
                    }
                });

                Button btn_pop_ensure =(Button)popContent.findViewById(R.id.btn_pop_ensure);
                btn_pop_ensure.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        popupWindow.dismiss();
                    }
                });

                break;
            case R.id.button4:
                AlertUtil.ShowAlertDialog(MainActivity.this, "sdad", new AlertUtil.AlertCallBack() {
                    @Override
                    public void onPositive() {

                    }
                    @Override
                    public void onNegative() {

                    }
                });
                break;
            case R.id.button5:
                AlertUtil.showDialog(MainActivity.this, "温馨提示", "cfcaasad", "确定", "取消", new AlertUtil.AlertCallBack() {
                    @Override
                    public void onPositive() {
                        Logger.d("madehaonan");
                    }

                    @Override
                    public void onNegative() {
                        Logger.d("madeyidiandoubunan");
                    }
                });
                break;
             default:break;
        }
    }
}
