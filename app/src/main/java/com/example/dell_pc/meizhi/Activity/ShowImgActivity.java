package com.example.dell_pc.meizhi.Activity;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;
import com.example.dell_pc.meizhi.R;
import com.example.dell_pc.meizhi.util.FileUtil;

import java.io.File;
import java.util.concurrent.ExecutionException;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by wbin on 2016/8/17.
 */
public class ShowImgActivity extends AppCompatActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.image_view)
    ImageView imageView;

    String imgUrl;
    String title;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_img_activity);
        ButterKnife.bind(this);
        parseIntent();

        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        Glide.with(this).load(imgUrl).into(imageView);
    }

    private void parseIntent() {
        imgUrl = getIntent().getStringExtra("url");
        title = getIntent().getStringExtra("title");
        Log.e(ShowImgActivity.class.toString(), "title: " + title);
    }

    //有问题。
    private void saveImg() {
        Observable.create(new Observable.OnSubscribe<File>() {

            @Override
            public void call(Subscriber<? super File> subscriber) {
                try {
                    File file = Glide.with(ShowImgActivity.this)
                            .load(imgUrl)
                            .downloadOnly(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                            .get();
                    subscriber.onNext(file);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        }).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<File>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(File file) {
                        File copyFile=new File(Environment.getExternalStorageDirectory(),title+".jpg");
                        Log.e(ShowImgActivity.class.toString(), copyFile.getPath());
                        FileUtil.fileChannelCopy(file,copyFile);
                        Log.e(ShowImgActivity.class.toString(), copyFile.getPath());
                    }
                });
      /*  Network.getImgApi()
                .getImg(imgUrl)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ResponseBody>() {
                    @Override
                    public void onCompleted() {
                        Toast.makeText(ShowImgActivity.this, "保存成功", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(ShowImgActivity.this, "下载失败", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        File file = new File(Environment.getExternalStorageDirectory(), title + ".jpg");
                        try {
                            FileOutputStream fos = new FileOutputStream(file);
                            fos.write(responseBody.bytes());
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_img, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.action_save:
                saveImg();
                break;
            case R.id.action_share:
                break;
            default:
                break;
        }
        return true;
    }
}
