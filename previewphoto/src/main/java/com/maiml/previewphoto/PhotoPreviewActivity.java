package com.maiml.previewphoto;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * 类       名:图片预览
 * 说       明:
 * date   2017/03/6
 * author   maimingliang
 */
public class PhotoPreviewActivity extends AppCompatActivity implements PhotoPagerAdapter.PhotoViewClickListener {

    public static final String EXTRA_PHOTOS = "extra_photos";
    public static final String EXTRA_SMALL_PHOTOS = "extra_small_photos";
    public static final String EXTRA_LOACL_PHOTOS = "extra_loacl_photos";
    public static final String EXTRA_CURRENT_ITEM = "extra_current_item";
    public static final String EXTRA_DF_DRAWBLE = "EXTRA_DF_DRAWBLE";
    public static final String EXTRA_SKIP_MEMORY = "extra_skip_memory";

    /**
     * 选择结果，返回为 ArrayList&lt;String&gt; 图片路径集合
     */
    public static final String EXTRA_RESULT = "preview_result";

    public static final String DELETE_RESULT = "delete_result";

    /**
     * 预览请求状态码
     */
    public static final int REQUEST_PREVIEW = 99;

     private ViewPagerFixed mViewPager;
    private PhotoPagerAdapter mPagerAdapter;
    private int currentItem = 0;
    private int resId = -1;
    private boolean isSkipMemory;
    private TextView mIndicator;
    private ArrayList<PrePhotoInfo> mPaths;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);//去掉信息栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);//隐藏虚拟按钮
        setContentView(R.layout.activity_image_preview);
        initViews();
        initCustomerAttr();
        initViewpager();
        updateActionBarTitle();
    }

    private void initViewpager() {

        mPagerAdapter = new PhotoPagerAdapter(this, mPaths, resId);

        mPagerAdapter.setPhotoViewClickListener(this);
        mViewPager.setAdapter(mPagerAdapter);
        mViewPager.setCurrentItem(currentItem);
        mViewPager.setOffscreenPageLimit(5);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                updateActionBarTitle();
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initCustomerAttr() {

        mPaths = getIntent().getParcelableArrayListExtra(EXTRA_PHOTOS);
        currentItem = getIntent().getIntExtra(EXTRA_CURRENT_ITEM, 0);
        resId = getIntent().getIntExtra(EXTRA_DF_DRAWBLE, -1);
        if (resId == -1) {
            resId = R.drawable.ic_launcher;
        }
        isSkipMemory = getIntent().getBooleanExtra(EXTRA_SKIP_MEMORY, false);

    }

    private void initViews() {
        mViewPager = (ViewPagerFixed) findViewById(R.id.vp_photos);
        mIndicator = (TextView) findViewById(R.id.indicator);
        Toolbar mToolbar = (Toolbar) findViewById(R.id.pickerToolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void OnPhotoTapListener(View view, float v, float v1) {
        onBackPressed();
    }

    public void updateActionBarTitle() {
        getSupportActionBar().setTitle(
                getString(R.string.viewpager_indicator, mViewPager.getCurrentItem() + 1, mPaths.size()));

        mIndicator.setText(getString(R.string.viewpager_indicator, mViewPager.getCurrentItem() + 1, mPaths.size()));

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra(EXTRA_RESULT, mPaths);
         setResult(RESULT_OK, intent);
        finish();
        super.onBackPressed();
    }


}
