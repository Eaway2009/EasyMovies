package com.movietime.easymovies;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.xunlei.downloadlib.XLTaskHelper;
import com.xunlei.downloadlib.parameter.XLTaskInfo;

import player.XLVideoPlayActivity;

/**
 * Created by xn058827 on 2018/9/7.
 */

public class MainActivity extends Activity {
    EditText inputUrl;
    Button btnDownload;
    Button btnPlay;
    TextView tvStatus;
    Handler handler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0) {
                long taskId = (long) msg.obj;
                XLTaskInfo taskInfo = XLTaskHelper.instance().getTaskInfo(taskId);
                tvStatus.setText(
                        "fileSize:" + taskInfo.mFileSize
                                + " downSize:" + taskInfo.mDownloadSize
                                + " speed:" + convertFileSize(taskInfo.mDownloadSpeed)
                                + "/s dcdnSpeed:" + convertFileSize(taskInfo.mAdditionalResDCDNSpeed)
                                + "/s filePath:" + "/sdcard/" + XLTaskHelper.instance().getFileName(inputUrl.getText().toString())
                );
                handler.sendMessageDelayed(handler.obtainMessage(0, taskId), 1000);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        XLTaskHelper.init(getApplicationContext());
        inputUrl = (EditText) findViewById(R.id.input_url);
        btnDownload = (Button) findViewById(R.id.btn_down);
        btnPlay = (Button) findViewById(R.id.btn_play);
        tvStatus = (TextView) findViewById(R.id.tv_status);
        btnDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(inputUrl.getText())) {
                    long taskId = 0;
                    try {
                        taskId = XLTaskHelper.instance().addThunderTask(inputUrl.getText().toString(), "/sdcard/", null);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    handler.sendMessage(handler.obtainMessage(0, taskId));
                }
            }
        });
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!TextUtils.isEmpty(inputUrl.getText())) {
                    XLVideoPlayActivity.intentTo(XLVideoPlayActivity.class, getApplicationContext(), inputUrl.getText().toString(), inputUrl.getText().toString(), 0);
                }
            }
        });
        getPermission();
    }

    public static String convertFileSize(long size) {
        long kb = 1024;
        long mb = kb * 1024;
        long gb = mb * 1024;

        if (size >= gb) {
            return String.format("%.1f GB", (float) size / gb);
        } else if (size >= mb) {
            float f = (float) size / mb;
            return String.format(f > 100 ? "%.0f M" : "%.1f M", f);
        } else if (size >= kb) {
            float f = (float) size / kb;
            return String.format(f > 100 ? "%.0f K" : "%.1f K", f);
        } else
            return String.format("%d B", size);
    }


    public void getPermission() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED//存储权限组
                    ) {
                ActivityCompat.requestPermissions(this, new String[]{
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                }, AllPer);
            }
        }
    }

    private static final int AllPer = 17;

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == AllPer) {
            boolean READ_EXTERNAL_STORAGECheck = grantResults[0] == PackageManager.PERMISSION_GRANTED;

            if (!READ_EXTERNAL_STORAGECheck) {
                mToast("没有申请到存储权限，无法下载播放");
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }


    public void mToast(String s) {
        Toast.makeText(this, s, Toast.LENGTH_LONG).show();
    }
}
