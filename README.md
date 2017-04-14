# previewphoto

#简介

Android 图片预览工具

#效果图


![效果图02](https://github.com/maimingliang/previewphoto/blob/master/001.gif)


#使用

1. 在build.gradle 文件添加

```code

complime

```

2. 调用

```code

  PhotoPreviewIntent intent = new PhotoPreviewIntent(this);

        intent.setPhotoPaths(photos) ////预览图片对象列表
                .setSmallWidth(width) //小图的宽
                .setSmallHeight(height)//小图的宽
                .setCurrentItem(position)//预览图片位置
                .setDefluatDrawble(R.drawable.ic_launcher) //加载错误时的图片
                .launch();

```


##Thanks


[glide](https://github.com/bumptech/glide)

[PhotoView](https://github.com/chrisbanes/PhotoView)

[RxJava](https://github.com/ReactiveX/RxJava)


#关于我

[简书maimingliang](http://www.jianshu.com/users/141bda5f1c5c/latest_articles)


#最后

如果遇到问题或者好的建议，请反馈到我的邮箱，maimingliang8#163.com (# 换 @)

如果觉得对你有用的话，点一下Star赞一下吧!



