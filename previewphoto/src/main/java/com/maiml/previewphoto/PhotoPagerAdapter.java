package com.maiml.previewphoto;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.view.PagerAdapter;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;

import java.util.ArrayList;
import java.util.List;

import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;


/**
 * 类       名:图片预览
 * 说       明:
 * date   2017/03/6
 * author   maimingliang
 */
public class PhotoPagerAdapter extends PagerAdapter {

    public interface PhotoViewClickListener {
        void OnPhotoTapListener(View view, float v, float v1);
    }

    public PhotoViewClickListener listener;

//    private List<String> paths = new ArrayList<>();
    private List<PrePhotoInfo> paths = new ArrayList<>();
    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private int resId = R.drawable.ic_launcher;
    private boolean isSkipMemory = false;

    public PhotoPagerAdapter(Context mContext, List<PrePhotoInfo> paths, int resId) {
        this.mContext = mContext;
        this.paths = paths;
        this.resId = resId;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    public void setPhotoViewClickListener(PhotoViewClickListener listener) {
        this.listener = listener;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {

        View itemView = mLayoutInflater.inflate(R.layout.item_preview, container, false);

        final PhotoView imageView = (PhotoView) itemView.findViewById(R.id.iv_pager);

        final ProgressBar loadingPb = (ProgressBar) itemView.findViewById(R.id.loading);

        final ImageView smallImageView = (ImageView) itemView.findViewById(R.id.small_img);

        final PrePhotoInfo path = paths.get(position);
//        String testUrl ="/data/user/0/com.elk.tourist/cache/image_manager_disk_cache/889639cde3de11f3355d783cfd5e99ad966cadf5543bd8ae0a1c592b7904b2b6.0" ;

         if (!TextUtils.isEmpty(path.getLocalPath())) {

            smallImageView.setVisibility(View.VISIBLE);

            Glide.with(mContext)
                    .load(path.getLocalPath())
                    .override(path.getSmallWidth(), path.getSmallHeight())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .error(resId)
                    .into(smallImageView);
        } else {
            smallImageView.setVisibility(View.GONE);
        }

        Glide.with(mContext)
                .load(path.getPath())
                .diskCacheStrategy(DiskCacheStrategy.ALL)//缓存多个尺寸
//                .thumbnail(0.1f)//先显示缩略图  缩略图为原图的1/10
                .error(resId)
                .into(new GlideDrawableImageViewTarget(imageView) {
                    @Override
                    public void onLoadStarted(Drawable placeholder) {
                        super.onLoadStarted(placeholder);
                        if(loadingPb != null){
                            loadingPb.setVisibility(View.VISIBLE);
                        }
                    }
                    @Override
                    public void onLoadFailed(Exception e, Drawable errorDrawable) {
                        if (smallImageView != null) {
                            smallImageView.setVisibility(View.GONE);
                        }
                        if(loadingPb != null){
                            loadingPb.setVisibility(View.GONE);
                        }
                    }

                    @Override
                    public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> animation) {
                        super.onResourceReady(resource, animation);
                        if(loadingPb != null){
                            loadingPb.setVisibility(View.GONE);
                        }
                        if (smallImageView != null) {
                            smallImageView.setVisibility(View.GONE);
                        }

                    }
                });

        imageView.setOnPhotoTapListener(new PhotoViewAttacher.OnPhotoTapListener() {
            @Override
            public void onPhotoTap(View view, float v, float v1) {
                if (listener != null) {
                    listener.OnPhotoTapListener(view, v, v1);
                }
            }

        });

        container.addView(itemView);

        return itemView;
    }


    @Override
    public int getCount() {
        return paths.size();
    }


    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }


}
