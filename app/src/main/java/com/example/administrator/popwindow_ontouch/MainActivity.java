package com.example.administrator.popwindow_ontouch;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;

public class MainActivity extends AppCompatActivity  {

    private MyButton button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        // TODO Auto-generated method stub
        Log.d("zzz", "Activity-分发事件");
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d("zzz", "Activity-处理事件");
        return super.onTouchEvent(event);
    }

    private void initView() {
        button = (MyButton) findViewById(R.id.button);

        button.setOnTouchListener(new EasyTouchListener(this));

    }

//    @Override
//    public boolean onTouch(View v, MotionEvent event) {
//
//        switch(event.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//                Log.d("zzz", "--按下--");
//                Toast.makeText(this,"按下  ACTION_DOWN",Toast.LENGTH_SHORT).show();
//                break;
//            case MotionEvent.ACTION_MOVE:
//				Log.d("zzz", "--滑动--");
//                Toast.makeText(this,"滑动  ACTION_MOVE",Toast.LENGTH_SHORT).show();
//                break;
//            case MotionEvent.ACTION_UP:
//                Log.d("zzz", "--抬起--");
//                Toast.makeText(this,"抬起  ACTION_UP",Toast.LENGTH_SHORT).show();
//                break;
//            case MotionEvent.ACTION_CANCEL:
//                Toast.makeText(this,"触摸动作取消  ACTION_CANCEL",Toast.LENGTH_SHORT).show();
//                 break;
//            case MotionEvent.ACTION_OUTSIDE:
//                Toast.makeText(this,";触摸动作超出边界  ACTION_OUTSIDE",Toast.LENGTH_SHORT).show();
//                break;
//            case MotionEvent.ACTION_POINTER_DOWN:
//                Toast.makeText(this,"有一个非主要的手指按下了  ACTION_POINTER_DOWN",Toast.LENGTH_SHORT).show();
//                break;
//            case MotionEvent.ACTION_POINTER_UP:
//                Toast.makeText(this,"一个非主要的手指抬起来了  ACTION_POINTER_UP",Toast.LENGTH_SHORT).show();
//                break;
//            case MotionEvent.ACTION_SCROLL:
//                Toast.makeText(this,"非触摸滚动  ACTION_SCROLL",Toast.LENGTH_SHORT).show();
//                break;
//        }
//        //false:代表自己不处理此事件，向上一层传递  true:自己消费此事件，不向上传递
//        return true;
//    }
}
