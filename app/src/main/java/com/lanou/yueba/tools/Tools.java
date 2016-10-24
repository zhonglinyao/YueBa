package com.lanou.yueba.tools;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;

import com.lanou.yueba.main.YueBaApp;

/**
 * Created by dllo on 16/10/22.
 */

public class Tools {

    public static int calculateInSampleSize(BitmapFactory.Options options, int width, int height) {
        int outWidth = options.outWidth;
        int outHeight = options.outHeight;
        int widthInSampleSize = outWidth / width;
        int heightInSampleSize = outHeight / height;
        return Math.max(widthInSampleSize, heightInSampleSize);
    }

    public static Bitmap changackgroundImage(Bitmap sentBitmap, float radius) {
        if (Build.VERSION.SDK_INT > 16 && sentBitmap != null) {
            Bitmap bitmap = sentBitmap.copy(sentBitmap.getConfig(), true);
            final RenderScript rs = RenderScript.create(YueBaApp.getContext());
            final Allocation input = Allocation.createFromBitmap(rs, sentBitmap, Allocation.MipmapControl.MIPMAP_NONE,
                    Allocation.USAGE_SCRIPT);
            final Allocation output = Allocation.createTyped(rs, input.getType());
            final ScriptIntrinsicBlur script = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs));
            script.setRadius(radius);
            script.setInput(input);
            script.forEach(output);
            output.copyTo(bitmap);
            return bitmap;
        }
        return null;
    }
}
