package com.zy.mbk;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.orhanobut.logger.Logger;
import com.zy.view.AlertUtil;
import com.zy.view.PopUtil;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

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
        Button button = (Button)this.findViewById(R.id.button);
        button.setOnClickListener(this);
        Button button2 = (Button)this.findViewById(R.id.button2);
        button2.setOnClickListener(this);
        Button button3 = (Button)this.findViewById(R.id.button3);
        button3.setOnClickListener(this);
        Button button4 = (Button)this.findViewById(R.id.button4);
        button4.setOnClickListener(this);
        Button button5 = (Button)this.findViewById(R.id.button5);
        button5.setOnClickListener(this);

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
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button:
                if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                } else {
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                }
                break;
            case R.id.button2:
                startActivity(new Intent(this, ActivityliveTest.class));
                break;
            case R.id.button3:
                PopUtil.showDiaogPop(this, view, "温馨提示", "来晚了和立法会拉赫", "", "", new PopUtil.PopCallBack() {
                    @Override
                    public void positiveCallBack() {
                        Logger.d("确定");
                    }

                    @Override
                    public void negtiveCallBack() {
                        Logger.d("取消");
                    }
                });

                break;
            case R.id.button4:
                AlertUtil.ShowAlertDialog(MainActivity.this, "asdadss",new AlertUtil.AlertCallBack() {
                    @Override
                    public void onPositive() {

                    }
                    @Override
                    public void onNegative() {

                    }
                });
                break;
            case R.id.button5:
                AlertUtil.showDialog(MainActivity.this, "温馨提示", "测da试ds内容", "确定", "取消", new AlertUtil.AlertCallBack() {
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
            default:
                break;
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
       String string =newConfig.orientation==Configuration.ORIENTATION_LANDSCAPE?"横屏":"竖屏";
        Logger.d(string);
    }
}
