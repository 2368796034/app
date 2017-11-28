package com.example.yuekao1123;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private ImageView iv;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//初始化组件
        iv = (ImageView) findViewById(R.id.iv);

        Timer timer=new Timer();

        TimerTask task=new TimerTask(){

            public void run(){

                Intent intent = new Intent(MainActivity.this, Main2Activity.class);

                startActivity(intent);

                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);

            }

        };
//5秒进行跳转
        timer.schedule(task, 5000);


    }
    //点击事件
        @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
        public void onClick(View v) {
            iv.setBackgroundColor(Color.TRANSPARENT);

            switch (v.getId()) {


                case R.id.animation_group2://先播放旋转动画，完成后播放位移动画
                    AnimatorSet animatorSetGroup2 = new AnimatorSet();
                    ObjectAnimator objectAnimatorTranslate2 = ObjectAnimator.ofFloat(iv, "translationY", 0f, 400f);
                    ObjectAnimator objectAnimatorRotateX2 = ObjectAnimator.ofFloat(iv, "rotationX", 0f, 360f);
                    ObjectAnimator objectAnimatorRotateY2 = ObjectAnimator.ofFloat(iv, "rotationY", 0f, 360f);
                    animatorSetGroup2.setDuration(1000);
                    animatorSetGroup2.play(objectAnimatorTranslate2).after(objectAnimatorRotateX2)
                            .after(objectAnimatorRotateY2);
                    animatorSetGroup2.start();
                    break;


            }

    }
}
