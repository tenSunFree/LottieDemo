package com.tensun.lottiedemo;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

/**
 * 只需要在 app/src/main/assets 中添加AE 生成的 json文件, 然後 activity_simple.xml中 lottie_fileName="material_wave_loading.json"
 */
public class SimpleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple);

        mHandler.sendEmptyMessageDelayed(GOTO_CLICK_ACTIVITY, 2720);                  // 2.72秒 跳轉
    }

    private static final int GOTO_CLICK_ACTIVITY = 0;

    private Handler mHandler = new Handler() {

        public void handleMessage(android.os.Message msg) {

            switch (msg.what) {

                case GOTO_CLICK_ACTIVITY:
                    Intent intent = new Intent();
                    intent.setClass(SimpleActivity.this, ClickActivity.class);
                    startActivity(intent);
                    finish();                                                                       // 如果按返回鍵, 將直接回到桌面
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);       // 在切換Activity時, 调用系统自带的淡入淡出的動畫效果
                    break;

                default:
                    break;
            }
        }
    };
}
