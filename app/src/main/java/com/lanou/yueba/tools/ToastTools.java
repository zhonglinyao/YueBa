package com.lanou.yueba.tools;

import android.content.Context;
import android.text.TextPaint;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.lanou.yueba.R;


public class ToastTools {
    private ToastTools()
    {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    public static boolean isShow = true;

    public static Toast sToast;



    /**
     * 显示自定Toast
     * @param context
     * @param msg
     */
    public static void showToastMsgShort(Context context, String msg) {

        if (sToast == null){
            sToast =Toast.makeText(context, msg, Toast.LENGTH_SHORT);
        }

//        Toast toast = new Toast(context);
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.toast_view, null);
        TextView tv = (TextView) view.findViewById(R.id.toast_msg);
        tv.setText(msg);
        TextPaint tp = tv.getPaint();
        tp.setFakeBoldText(true);
        sToast.setView(view);
        sToast.setGravity(Gravity.CENTER, 0, 0);
        sToast.setDuration(Toast.LENGTH_SHORT);
        sToast.show();
    }
    /**
     * 短时间显示Toast
     *
     * @param context
     * @param message
     */
    public static void showShort(Context context, CharSequence message)
    {

        if (sToast == null){
            sToast =Toast.makeText(context, message, Toast.LENGTH_SHORT);
        } else {
            sToast.setText(message);
            sToast.setDuration(Toast.LENGTH_SHORT);
        }
            sToast.show();

    }

    /**
     * 短时间显示Toast
     *
     * @param context
     * @param message
     */
    public static void showShort(Context context, int message)
    {

        if (sToast == null){
            sToast =Toast.makeText(context, message, Toast.LENGTH_SHORT);
        } else {
            sToast.setText(message);
            sToast.setDuration(Toast.LENGTH_SHORT);
        }
        sToast.show();

    }

    /**
     * 长时间显示Toast
     *
     * @param context
     * @param message
     */
    public static void showLong(Context context, CharSequence message)
    {


        if (sToast == null){
            sToast =Toast.makeText(context, message, Toast.LENGTH_SHORT);
        } else {
            sToast.setText(message);
            sToast.setDuration(Toast.LENGTH_SHORT);
        }
        sToast.show();



    }

    /**
     * 长时间显示Toast
     *
     * @param context
     * @param message
     */
    public static void showLong(Context context, int message)
    {


        if (sToast == null){
            sToast =Toast.makeText(context, message, Toast.LENGTH_LONG);
        } else {
            sToast.setText(message);
            sToast.setDuration(Toast.LENGTH_LONG);
        }
        sToast.show();

    }

    /**
     * 自定义显示Toast时间
     *
     * @param context
     * @param message
     * @param duration
     */
    public static void show(Context context, CharSequence message, int duration)
    {

        if (sToast == null){
            sToast =Toast.makeText(context, message,duration);
        } else {
            sToast.setText(message);
            sToast.setDuration(duration);
        }
        sToast.show();



    }

    /**
     * 自定义显示Toast时间
     *
     * @param context
     * @param message
     * @param duration
     */
    public static void show(Context context, int message, int duration)
    {

        if (sToast == null){
            sToast =Toast.makeText(context, message, duration);
        } else {
            sToast.setText(message);
            sToast.setDuration(duration);
        }
        sToast.show();

    }

}
