package com.sex.hall.tentx5;

import android.app.Application;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.tencent.smtt.sdk.QbSdk;
import com.tencent.smtt.sdk.TbsListener;
//import cn.bmob.v3.*;

/**
 * Created by guoni on 2017/11/4.
 */

public class VIPlayer extends Application {
    public static Handler mTestHandler = null;
    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();
        //搜集本地tbs内核信息并上报服务器，服务器返回结果决定使用哪个内核。
		//Bmob.initialize(this, "2e7b198444d1345a9329e9b32b47f8af");
		
        QbSdk.PreInitCallback cb = new QbSdk.PreInitCallback() {

            @Override
            public void onViewInitFinished(boolean arg0) {
                // TODO Auto-generated method stub
                //x5內核初始化完成的回调，为true表示x5内核加载成功，否则表示x5内核加载失败，会自动切换到系统内核。
                Log.d("app", " onViewInitFinished is " + arg0);
                if(arg0){
                    if(VIPlayer.mTestHandler != null){
                        VIPlayer.mTestHandler.sendEmptyMessageDelayed(1, 10);
                    }
                }
            }

            @Override
            public void onCoreInitFinished() {
                // TODO Auto-generated method stub
            }
        };

        QbSdk.setTbsListener(new TbsListener() {
            private int status = 0;

            @Override
            public void onDownloadFinish(int i) {
                if(status == 0 && VIPlayer.mTestHandler != null) {
                    status = 1;
                    VIPlayer.mTestHandler.sendEmptyMessage(3);
                }
            }

            @Override
            public void onInstallFinish(int i) {
                if(status == 1 && VIPlayer.mTestHandler != null) {
                    status = 2;
                    VIPlayer.mTestHandler.sendEmptyMessage(4);
                }
            }

            @Override
            public void onDownloadProgress(int i) {
                if(status == 0 && VIPlayer.mTestHandler != null) {
                    final Message msg = new Message();
                    final Bundle b = new Bundle();
                    b.putInt("progress", i);
                    msg.setData(b);
                    msg.what = 2;
                    VIPlayer.mTestHandler.sendMessage(msg);
                    Log.d("app", "onDownloadProgress:" + i);
                }
            }
        });
        //x5内核初始化接口
        QbSdk.initX5Environment(getApplicationContext(),  cb);
    }
}
