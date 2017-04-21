package com.example.administrator.popwindow_ontouch;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

/**
 * 作者：李飞 on 2017/4/21 15:38
 * 类的用途：
 */

public class MyRelativeLayout extends RelativeLayout {
    public MyRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    //false  向上传递 上一级 onTouchEvent 处理  true  自己消费
    // 原生super.dispatchTouchEvent(ev) 向下分发
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.d("zzz", "RelativeLayout分发事件");
        return super.dispatchTouchEvent(ev);
    }
    //拦截事件  false:不拦截  true:表示拦截
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        // TODO Auto-generated method stub
        Log.d("zzz", "RelativeLayout拦截事件 " +" false");
        return false;
    }

    //true:自己消费此事件  false:自己不处理此事件，向上一层传递
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // TODO Auto-generated method stub
        Log.d("zzz", "RelativeLayout处理事件");
        return false;
    }
}
