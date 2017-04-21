package com.example.administrator.popwindow_ontouch;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

/**
 * 作者：李飞 on 2017/4/21 15:39
 * 类的用途：
 */

public class MyButton extends android.support.v7.widget.AppCompatButton {

    public MyButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Log.d("zzz", "Button分发事件");
        return super.dispatchTouchEvent(event);
    }



        @Override
    public boolean onTouchEvent(MotionEvent event) {
        // TODO Auto-generated method stub
        Log.d("zzz", "Button处理事件"+"true");
        switch (event.getAction()){

        }
        return true;
    }


}
