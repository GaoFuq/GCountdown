package com.gfq.gcountdown;

import android.widget.TextView;

import androidx.lifecycle.Lifecycle;
import java.util.Locale;


/**
 * @created GaoFuq
 * @Date 2020/6/22 10:39
 * @Descaption 倒计时，可用于 获取验证码 等场景
 */
public class GCountdown {
    private TextView textView;
    private long totalTime = 60 * 1000;//默认总时间60秒
    private long intervalTime = 1000;//默认间隔时间1秒
    private String finishedText = "获取验证码";//默认使用场景为  获取验证码
    private String onTickTextPrefix = "";//默认使用场景为  获取验证码  前缀
    private String onTickTextSuffix = "s";//默认使用场景为  获取验证码  后缀
    private GTimer timer;

    public GCountdown(TextView textView) {
        this.textView = textView;
    }

    public GCountdown(TextView textView, long totalTime) {
        this(textView);
        this.totalTime = totalTime;
    }

    public GCountdown(TextView textView, long totalTime, long intervalTime) {
        this(textView, totalTime);
        this.intervalTime = intervalTime;
    }

    public GCountdown(TextView textView, long totalTime, long intervalTime, String finishedText) {
        this(textView, totalTime, intervalTime);
        this.finishedText = finishedText;
    }


    public GCountdown(TextView textView, long totalTime, long intervalTime, String finishedText, String onTickTextPrefix, String onTickTextSuffix) {
        this(textView, totalTime, intervalTime, finishedText);
        this.onTickTextPrefix = onTickTextPrefix;
        this.onTickTextSuffix = onTickTextSuffix;
    }

    private void startTimer(Lifecycle lifecycle) {
        if (timer == null) {
            timer = new GTimer(totalTime, intervalTime, new GTimer.CallBack() {
                @Override
                public void onTick(long second) {
                    textView.setText(String.format(Locale.CHINA, "%s%d%s", onTickTextPrefix, second, onTickTextSuffix));
                }

                @Override
                public void onFinish() {
                    textView.setText(finishedText);
                    textView.setEnabled(true);
                }
            });
            textView.setEnabled(false);
            lifecycle.addObserver(timer);
        }
        timer.start();
    }
}



