package com.example.administrator.popwindow_ontouch;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.Toast;

import java.util.Calendar;

/**
 * 作者：李飞 on 2017/4/21 18:31
 * 类的用途：
 */

public class EasyTouchListener implements View.OnTouchListener {

    private static final long CLICK_SPACING_TIME = 300;

    private static final long LONG_PRESS_TIME = 500;
    private final Context context;


    public EasyTouchListener(Context context){
        this.context=context;

    }

    /**

     * 当前触摸点相对于屏幕的坐标

     */

    private int mCurrentInScreenX;

    private int mCurrentInScreenY;



    /**

     * 触摸点按下时的相对于屏幕的坐标

     */

    private int mDownInScreenX;

    private int mDownInScreenY;

    /**

     * 点击次数

     */

    private int mClickCount = 0;



    /**

     * 当前点击时间

     */

    private long mCurrentClickTime;



    private Handler mBaseHandler = new Handler();



    /**

     * 长按线程

     */

    private LongPressedThread mLongPressedThread;

    /**

     * 点击等待线程

     */

    private ClickPressedThread mPrevClickThread;



    @Override

    public boolean onTouch(View v, MotionEvent event) {

        //获取相对屏幕的坐标，即以屏幕左上角为原点

        mCurrentInScreenX = (int)event.getRawX();

        mCurrentInScreenY = (int)event.getRawY();

        switch (event.getAction()) {

            case MotionEvent.ACTION_DOWN:

                Toast.makeText(context,"按下  ACTION_DOWN",Toast.LENGTH_SHORT).show();
                //记录Down下时的坐标

                mDownInScreenX = (int)event.getRawX();

                mDownInScreenY = (int)event.getRawY();

                //记录当前点击的时间

                mCurrentClickTime = Calendar.getInstance().getTimeInMillis();

                //点击次数加1

                mClickCount++;

                //取消上一次点击的线程

                if(mPrevClickThread != null){

                    mBaseHandler.removeCallbacks(mPrevClickThread);

                }

                mLongPressedThread = new LongPressedThread();

                mBaseHandler.postDelayed(mLongPressedThread,LONG_PRESS_TIME);

                break;

            case MotionEvent.ACTION_MOVE: {


                mClickCount = 0; // 只要移动了 就没有点击事件了

                //取消注册的长按事件

                mBaseHandler.removeCallbacks(mLongPressedThread);

                break;

            }

            case MotionEvent.ACTION_UP: {

                Toast.makeText(context,"抬起  ",Toast.LENGTH_SHORT).show();

                if(!this.isMoved()){

                    //如果按住的时间超过了长按时间，那么其实长按事件已经出发生了,这个时候把数据清零

                    if(Calendar.getInstance().getTimeInMillis() - mCurrentClickTime <= LONG_PRESS_TIME){

                        //取消注册的长按事件

                        mBaseHandler.removeCallbacks(mLongPressedThread);

                        mPrevClickThread = new ClickPressedThread();

                        mBaseHandler.postDelayed(mPrevClickThread,CLICK_SPACING_TIME);

                    }

                }else{

                    //UP的时候Move过

                }

                break;

            }

            default : break;
        }
        return true;
    }

    /**

     * 判断是否移动

     * @return

     */

    private boolean isMoved(){

        //允许有5的偏差 在判断是否移动的时候

        if(Math.abs(mDownInScreenX - mCurrentInScreenX) <= 5 && Math.abs(mDownInScreenY - mCurrentInScreenY) <= 5 ){

            return false;

        }else{
            Toast.makeText(context,"移动  ",Toast.LENGTH_SHORT).show();
            return true;

        }

    }

    public class LongPressedThread implements Runnable{

        @Override

        public void run() {

            //这里处理长按事件
            Toast.makeText(context,"长按  ",Toast.LENGTH_SHORT).show();

            View view = View.inflate(context, R.layout.pop, null);

            PopupWindow popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            popupWindow.setTouchable(true);
            popupWindow.setFocusable(true);//默认是false

            //点击窗体外部和返回键 窗体消失----结合着下面两个方法去使用
            popupWindow.setOutsideTouchable(true);
            popupWindow.setBackgroundDrawable(new BitmapDrawable());

            View view1 = View.inflate(context, R.layout.activity_main, null);
            popupWindow.showAtLocation(view1 , Gravity.CENTER, 0, 0);

            mClickCount = 0;

        }

    }

    public class ClickPressedThread implements Runnable{

        @Override

        public void run() {

            //这里处理连续点击事件 mClickCount 为连续点击的次数

            if (mClickCount>=2){
                Toast.makeText(context,"双击  ",Toast.LENGTH_SHORT).show();
            }


            mClickCount = 0;

        }

    }
}
