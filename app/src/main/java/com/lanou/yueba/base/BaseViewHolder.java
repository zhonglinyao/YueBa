package com.lanou.yueba.base;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

/**
 * Created by dllo on 16/10/19.
 */
public class BaseViewHolder {
    private SparseArray<View> mViews;
    private int mPosition;
    private View mConvertView;
    private Context mContext;

    public BaseViewHolder(Context context, ViewGroup parent, int layoutId, int position) {
        mPosition = position;
        mViews = new SparseArray<>();
        mContext = context;
        mConvertView = LayoutInflater.from(context).inflate(layoutId, parent, false);
        mConvertView.setTag(this);
    }

    public static BaseViewHolder get(Context context, View convertView, ViewGroup parent, int layoutId, int position) {
        if (convertView == null) {
            return new BaseViewHolder(context, parent, layoutId, position);
        } else {
            BaseViewHolder holder = (BaseViewHolder) convertView.getTag();
            holder.mPosition = position;
            return holder;
        }
    }

    public <T extends View> T getView(int viewId) {
        View view = mViews.get(viewId);

        if (view == null) {
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }

        return (T) view;
    }

    public BaseViewHolder setText(int viewId, String string){
        TextView tv = getView(viewId);
        tv.setText(string);
        return this;
    }

    public BaseViewHolder setImage(int viewId, String imgUrl){
        ImageView iv = getView(viewId);
        Glide.with(mContext).load(imgUrl).into(iv);
        return this;
    }

    public View getConvertView() {
        return mConvertView;
    }
}
