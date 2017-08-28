package com.zy.mbk;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.orhanobut.logger.Logger;

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

                break;
            case R.id.button4:
                break;
//            case R.id.button5:
//                break;
             default:break;
        }
    }
}
