package com.maiml.previewphoto;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.FutureTarget;

import java.io.File;
import java.util.concurrent.ExecutionException;

/**
 * /**
 * 类       名:
 * 说       明:
 * date   2017/4/10
 * author   maimingliang
 */


public class Utils {

    public static String getImagePathFromCache(Context context, String url, int expectW, int expectH){

        FutureTarget<File> future  = Glide.with(context.getApplicationContext()).load(url).downloadOnly(expectW,expectH);

        try {
            File cacheFile = future.get();
            String path = cacheFile.getAbsolutePath();
            return path;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

}
