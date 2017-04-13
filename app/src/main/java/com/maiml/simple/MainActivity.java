package com.maiml.simple;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.maiml.previewphoto.PhotoPreviewIntent;
import com.maiml.previewphoto.PrePhotoInfo;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    public final static String URL = "http://192.168.0.232:8081/elk-tourist-develop/fileInfo/preview?path=";
    private List<PrePhotoInfo> previewList = new ArrayList<>();
    private MultiImageView mMultipleImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMultipleImage = (MultiImageView) findViewById(R.id.multiple_image);
        final List<PrePhotoInfo> prePhotoInfos = initData();

        mMultipleImage.setList(prePhotoInfos);

        mMultipleImage.setOnItemClickListener(new MultiImageView.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                previewImage(position,prePhotoInfos,view.getWidth(),view.getHeight());
            }
        });


    }



    private  List<PrePhotoInfo> initData() {

        List<PrePhotoInfo> list = new ArrayList<>();

        PrePhotoInfo p1 = new PrePhotoInfo();

        p1.setSmallHeight(376);
        p1.setSmallWidth(282);
        p1.setSmallPath(URL+"M00/00/6D/wKgA6FjkZOmAFOUpAACZPvdC7zU671.jpg");
        p1.setPath(URL+"M00/00/6D/wKgA6FjkZOmAA19dAAF-IUqtuic669.jpg");

        list.add(p1);

        PrePhotoInfo p2 = new PrePhotoInfo();

        p2.setSmallHeight(376);
        p2.setSmallWidth(282);
        p2.setSmallPath(URL+"M00/00/6D/wKgA6FjkZOqAHy82AACZPvdC7zU389.jpg");
        p2.setPath(URL+"M00/00/6D/wKgA6FjkZOqAIvH3AAF-IUqtuic313.jpg");

        list.add(p2);

        PrePhotoInfo p3 = new PrePhotoInfo();

        p3.setSmallHeight(376);
        p3.setSmallWidth(282);
        p3.setSmallPath(URL+"M00/00/6D/wKgA6FjkZOqAKsH_AADI8pmVttg263.jpg");
        p3.setPath(URL+"M00/00/6D/wKgA6FjkZOqAOlflAAIOvXzjfRM608.jpg");

        list.add(p3);


        PrePhotoInfo p4 = new PrePhotoInfo();

        p4.setSmallHeight(376);
        p4.setSmallWidth(282);
        p4.setSmallPath(URL+"M00/00/6D/wKgA6FjkZOqAZHYxAADI8pmVttg933.jpg");
        p4.setPath(URL+"M00/00/6D/wKgA6FjkZOqALJKDAAIOvXzjfRM762.jpg");

        list.add(p4);




        return list;



    }


    private void previewImage(int position,  List<PrePhotoInfo> photos,int width,int height) {

        PhotoPreviewIntent intent = new PhotoPreviewIntent(this);

        intent.setPhotoPaths(photos) //预览图片对象列表
                .setSmallWidth(width) //小图的宽
                .setSmallHeight(height)//小图的宽
                .setCurrentItem(position)//预览图片位置
                .setDefluatDrawble(R.drawable.ic_launcher) //加载错误时的图片
                .launch();

    }
}
