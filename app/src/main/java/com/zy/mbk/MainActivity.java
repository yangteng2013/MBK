package com.zy.mbk;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.orhanobut.logger.Logger;
import com.zy.utils.PakageInfo;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView tv_ma_p1 = (TextView)this.findViewById(R.id.tv_ma_p1);
        tv_ma_p1.setText(NativeUtil.getSha1String());
        Logger.d("native:"+NativeUtil.getSha1String());
        Logger.d("getpakage:"+ PakageInfo.getCertificateSHA1Fingerprint(this));
    }
}
