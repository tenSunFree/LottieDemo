package com.tensun.lottiedemo;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.model.LottieComposition;


public class ClickActivity extends AppCompatActivity {

    private TextView tv_seek;
    LottieAnimationView animation_view_click;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_click);

        animation_view_click = (LottieAnimationView) findViewById(R.id.animation_view_click);
        tv_seek=(TextView)findViewById(R.id.tv_seek);

        LottieComposition.fromAssetFileName(                                                          // 直接讀取assets裡面的資源, 並進行播放設置
                this,
                "hint_01.json",
                new LottieComposition.OnCompositionLoadedListener() {

            @Override
            public void onCompositionLoaded(LottieComposition composition) {
                animation_view_click.setComposition(composition);
                animation_view_click.playAnimation();                                               // 开启动画
            }
        });


        animation_view_click.addAnimatorUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // 對實現動畫的過程 進行監聽
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                tv_seek.setText(" 動畫進度: " +(int) (animation.getAnimatedFraction()*100) +" %");
            }
        });

        animation_view_click.setOnClickListener(new View.OnClickListener() {                        // 實現點擊監聽
            @Override
            public void onClick(View view) {
                startAnima();
            }
        });
    }

    /** 開始動畫 */
    private  void startAnima(){

        boolean inPlaying = animation_view_click.isAnimating();

        if (!inPlaying) {
            animation_view_click.playAnimation();
        }
    }

    /** 停止動畫 */
    private  void stopAnima(){

        boolean inPlaying = animation_view_click.isAnimating();

        if (inPlaying) {
            animation_view_click.cancelAnimation();
        }
    }

    @Override
    protected void onStop() {                                                                       // 結束ClickActivity時, 想做哪些事?
        super.onStop();
        stopAnima();
    }
}
