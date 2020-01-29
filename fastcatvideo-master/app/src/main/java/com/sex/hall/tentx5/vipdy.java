package com.sex.hall.tentx5;

import android.app.*;
import android.content.*;
import android.graphics.*;
import android.net.*;
import android.os.*;
import android.support.v7.app.*;
import android.support.v7.appcompat.*;
import android.util.*;
import android.view.*;
import android.widget.*;
import com.tencent.smtt.export.external.interfaces.*;
import com.tencent.smtt.sdk.*;
import com.tencent.smtt.utils.*;
import java.net.*;
import java.util.*;
import android.app.AlertDialog;
import android.support.v7.appcompat.R;
import com.tencent.smtt.sdk.DownloadListener;
import android.annotation.*;
import java.io.*;
import org.apache.http.util.*;
import com.sex.hall.tentx5.utils.*;

public class vipdy extends AppCompatActivity {
        private X5WebView mWebView = null;
    private ViewGroup mViewParent;
//    private ImageButton mPlay;
   
   
    private Button mGo;
    private ValueCallback<Uri> uploadMessage;
    private ValueCallback<Uri[]> uploadMessageAboveL;
    private final static int FILE_CHOOSER_RESULT_CODE = 10000;
	

   // private static final String mHomeUrl = "https://sorry.xuty.tk/wangjingze/";
	
	private String mHomeUrl = "https://www.633ii.com";
    private static final String TAG = "VIPlayer";
    private static final int MAX_LENGTH = 14;

    private final int disable = 120;
    private final int enable = 255;

    private ProgressBar mPageLoadingProgressBar = null;

    private ValueCallback<Uri> uploadFile;
	private String picUrl;
    private URL mIntentUrl;

    private String pageUrl = "";

	private Button 距离;

	private Button 距离1;

	private String 类型;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       /* getWindow().setFormat(PixelFormat.TRANSLUCENT);
        getSupportActionBar().hide();*/

        try {
            if (Integer.parseInt(android.os.Build.VERSION.SDK) >= 11) {
                getWindow()
                        .setFlags(
                                android.view.WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED,
                                android.view.WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED);
            }
        } catch (Exception e) {
        }

        setContentView(R.layout.vipdy);
	
			
			
		
	/*	if(类型.equals("二维码")){
			mHomeUrl="https://m.wwei.cn/template.html";
		}
		if(类型.equals("电影")){
			//mHomeUrl="https://m.wwei.cn/template.html";
		}if(类型.equals("撤回")){
			mHomeUrl="http://tool.xiaoming.ooo/qqqun/";
		}if(类型.equals("k歌")){
			mHomeUrl="http://tool.xiaoming.ooo/kg/";
		}if(类型.equals("ip")){
			mHomeUrl="http://tool.xiaoming.ooo/ip/";
		}if(类型.equals("表白")){
			mHomeUrl="http://www.51bbw.cn/show";
		}if(类型.equals("举牌")){
			mHomeUrl="http://3g.gljlw.com/diy/jupai/?page=2";
		}if(类型.equals("搜图")){
			mHomeUrl="http://shitu.baidu.com/";
		}
		if(类型.equals("克隆")){
			mHomeUrl="http://vip.qq.com/client/fr_index.html";
		}if(类型.equals("邮箱")){
			mHomeUrl="http://tool.uixsj.cn/s/spam-mail/";
		}
*/
		
		
		
        mViewParent = (ViewGroup) findViewById(R.id.webView1);
		
        mTestHandler.sendEmptyMessageDelayed(MSG_INIT_UI, 10);

        //去除QQ浏览器推广
        getWindow().getDecorView().addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                ArrayList<View> outView = new ArrayList<View>();
                getWindow().getDecorView().findViewsWithText(outView, "QQ浏览器", View.FIND_VIEWS_WITH_TEXT);
                int size = outView.size();
                if (outView != null && outView.size() > 0) {
                    outView.get(0).setVisibility(View.GONE);
                }
            }
        });
		
    }

    private void changGoForwardButton(WebView view) {
        
        
    }
	
	/**
	 * 判断是APP是否使用代理
	 * @param context
	 * @return
	 */
	/*public static boolean isWifiProxy(Context context) {
		
	//	Log.i("代理信息","proxyAddress :"+proxyAddress + "prot : "+proxyPort)
		return (!TextUtils.isEmpty(proxyAddress)) && (proxyPort != -1);
	}*/
    private void initProgressBar() {
        mPageLoadingProgressBar = (ProgressBar) findViewById(R.id.progressBar1);// new
        // ProgressBar(getApplicationContext(),
        // null,
        // android.R.attr.progressBarStyleHorizontal);
        mPageLoadingProgressBar.setMax(100);
    /*    mPageLoadingProgressBar.setProgressDrawable(this.getResources()
                .getDrawable(R.drawable.color_progressbar));*/
			
    }

    private void init() {
        

        VIPlayer.mTestHandler = null;
        

        if(mWebView != null) return;

        mWebView = new X5WebView(this, null);

        mViewParent.addView(mWebView, new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.FILL_PARENT,
                FrameLayout.LayoutParams.FILL_PARENT));
		WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        initProgressBar();

        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Intent intent;
                boolean handled = false;
                // 判断URL
                if (url.startsWith("http:") || url.startsWith("https:")) {
                    view.loadUrl(url);
                    handled = true;
                }

                // baidu
                if (url.startsWith("bdvideo:")) {
                    handled = true;
                }

                if (url.startsWith("intent:")) {
                    handled = true;
                }

                return handled;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
				/*//Toasty.warning(vipdy.this,view.getUrl(),Toast.LENGTH_SHORT,true).show();
				/*String javascript =  "javascript:function hideOther() {" +  
					"var headers = document.getElementsByClassName('dubalogo-inner');" +
					"var lastHeader = headers[headers.length-1];" +
					"lastHeader.remove();" +

					"}";

				//创建方法  

				view.loadUrl(javascript);

				//加载方法
				view.loadUrl("javascript:hideOther();");*/
				System.out.println(view.getUrl());
                if (Integer.parseInt(android.os.Build.VERSION.SDK) >= 16)
                    changGoForwardButton(view);
					
				/* mWebView.showLog("test Log"); */
            }
			
        });

        mWebView.setWebChromeClient(new WebChromeClient() {
// For Android >= 5.0
				@Override
				public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback, WebChromeClient.FileChooserParams fileChooserParams) {
					uploadMessageAboveL = filePathCallback;
					openImageChooserActivity();
					return true;
				}

	
            @Override
            public boolean onJsConfirm(WebView arg0, String arg1, String arg2,
                                       JsResult arg3) {
                return super.onJsConfirm(arg0, arg1, arg2, arg3);
            }

            @Override
            public boolean onJsAlert(WebView arg0, String arg1, String arg2,
                                     JsResult arg3) {
                /**
                 * 这里写入你自定义的window alert
                 */
                return super.onJsAlert(null, arg1, arg2, arg3);
            }

            @Override
            public void onReceivedTitle(WebView view, String title) {
                
            }

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                // TODO Auto-generated method stub
//                mPageLoadingProgressBar.setProgress(newProgress);

//                if (mPageLoadingProgressBar != null && newProgress != 100) {
//                    mPageLoadingProgressBar.setVisibility(View.VISIBLE);
                if (mPageLoadingProgressBar != null && newProgress != 100) {
                    mPageLoadingProgressBar.setProgress(newProgress);
mPageLoadingProgressBar.setVisibility(View.VISIBLE);
                } else {
                    mPageLoadingProgressBar.setProgress(0);
					mPageLoadingProgressBar.setVisibility(View.GONE);
                }

            }
        });
		mWebView.setOnLongClickListener(new View.OnLongClickListener() {
				@Override
				public boolean onLongClick(View view) {
					final WebView.HitTestResult hitTestResult = mWebView.getHitTestResult();
					// 如果是图片类型或者是带有图片链接的类型
					if (hitTestResult.getType() == WebView.HitTestResult.IMAGE_TYPE ||
                        hitTestResult.getType() == WebView.HitTestResult.SRC_IMAGE_ANCHOR_TYPE) {
							
						// 弹出保存图片的对话框
						AlertDialog.Builder builder = new AlertDialog.Builder(vipdy.this);
						
						if(类型.equals("头像")){
							builder.setTitle("出现错误错❌");
							builder.setMessage("由于此图片特殊，只能截屏后裁剪获得，请手动截屏保存，然后裁剪获得");
						}else{
							builder.setTitle("提示");
						builder.setMessage("保存图片到本地");
						}
						builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {

								
								@Override
								public void onClick(DialogInterface dialogInterface, int i) {
									picUrl = hitTestResult.getExtra();//获取图片链接
									//保存图片到相册
									new Thread(new Runnable() {
											@Override
											public void run() {
												url2bitmap(picUrl);
											}
										}).start();
								}
							});
						builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
								// 自动dismiss
								@Override
								public void onClick(DialogInterface dialogInterface, int i) {
								}
							});
						AlertDialog dialog = builder.create();
						dialog.show();
						return true;
					}
					return false;//保持长按可以复制文字
				}
			});
        mWebView.setDownloadListener(new DownloadListener() {

            @Override
            public void onDownloadStart(String arg0, String arg1, String arg2,
                                        String arg3, long arg4) {
                TbsLog.d(TAG, "url: " + arg0);
                new AlertDialog.Builder(vipdy.this)
                        .setTitle("请长按图片保存")
                        .setPositiveButton("yes",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog,
                                                        int which) {
															
//                             
                                    }
                                })
                        .setNegativeButton("no",
                                new DialogInterface.OnClickListener() {

                                    @Override
                                    public void onClick(DialogInterface dialog,
                                                        int which) {
                                        // TODO Auto-generated method stub
                                     /*   Toast.makeText(
                                                vipdy.this,
                                                "fake message: refuse download...",
                                                Toast.LENGTH_SHORT).show();
                                 */   }
                                })
                        .setOnCancelListener(
                                new DialogInterface.OnCancelListener() {

                                    @Override
                                    public void onCancel(DialogInterface dialog) {
                                        // TODO Auto-generated method stub
                                        Toast.makeText(
                                                vipdy.this,
                                                "fake message: refuse download...",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                }).show();
            }
        });

        long time = System.currentTimeMillis();
        if (mIntentUrl == null) {
            mWebView.loadUrl(mHomeUrl);
        } else {
            mWebView.loadUrl(mIntentUrl.toString());
        }
        Log.d("time-cost", "cost time: "
                + (System.currentTimeMillis() - time));
        CookieSyncManager.createInstance(this);
        CookieSyncManager.getInstance().sync();
    }

    public boolean goBackInWebView() {
        if (mWebView != null && mWebView.canGoBack()) {
            boolean found = false;
            if(pageUrl != ""){
                WebBackForwardList history = mWebView.copyBackForwardList();
                int index = -1;
                int curi = history.getCurrentIndex();

                while (mWebView.canGoBackOrForward(index)) {
                    if (history.getItemAtIndex(curi + index).getUrl().equals(pageUrl)) {
                        mWebView.goBackOrForward(index);
                        found = true;
                        break;
                    }
                    index--;
                }
                pageUrl = "";
            }

            if(!found) mWebView.goBack();

            if (Integer.parseInt(android.os.Build.VERSION.SDK) >= 16)
                changGoForwardButton(mWebView);
            return true;
        }
        return false;
    }

    boolean[] m_selected = new boolean[] { true, true, true, true, false,
            false, true };

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (goBackInWebView()) {
                return true;
            } else
                return super.onKeyDown(keyCode, event);
        }
        return super.onKeyDown(keyCode, event);
    }
	private void openImageChooserActivity() {
        Intent i = new Intent(Intent.ACTION_GET_CONTENT);
        i.addCategory(Intent.CATEGORY_OPENABLE);
        i.setType("image/*");
        startActivityForResult(Intent.createChooser(i, "Image Chooser"), FILE_CHOOSER_RESULT_CODE);
    }

	

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == FILE_CHOOSER_RESULT_CODE) {
            if (null == uploadMessage && null == uploadMessageAboveL) return;
            Uri result = data == null || resultCode != RESULT_OK ? null : data.getData();
            if (uploadMessageAboveL != null) {
                onActivityResultAboveL(requestCode, resultCode, data);
            } else if (uploadMessage != null) {
                uploadMessage.onReceiveValue(result);
                uploadMessage = null;
            }
        }
		
		
		if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case 0:
                    if (null != uploadFile) {
                        Uri result = data == null || resultCode != RESULT_OK ? null
                                : data.getData();
                        uploadFile.onReceiveValue(result);
                        uploadFile = null;
                    }
                    break;
                default:
                    break;
            }
        } else if (resultCode == RESULT_CANCELED) {
            if (null != uploadFile) {
                uploadFile.onReceiveValue(null);
                uploadFile = null;
            }

        }

    }

    @Override
    protected void onNewIntent(Intent intent) {
        if (intent == null || mWebView == null || intent.getData() == null)
            return;
        mWebView.loadUrl(intent.getData().toString());
    }

    @Override
    protected void onDestroy() {
        if (mTestHandler != null)
            mTestHandler.removeCallbacksAndMessages(null);
        if (mWebView != null)
            mWebView.destroy();
        super.onDestroy();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void onActivityResultAboveL(int requestCode, int resultCode, Intent intent) {
        if (requestCode != FILE_CHOOSER_RESULT_CODE || uploadMessageAboveL == null)
            return;
        Uri[] results = null;
        if (resultCode == Activity.RESULT_OK) {
            if (intent != null) {
                String dataString = intent.getDataString();
                ClipData clipData = intent.getClipData();
                if (clipData != null) {
                    results = new Uri[clipData.getItemCount()];
                    for (int i = 0; i < clipData.getItemCount(); i++) {
                        ClipData.Item item = clipData.getItemAt(i);
                        results[i] = item.getUri();
                    }
                }
                if (dataString != null)
                    results = new Uri[]{Uri.parse(dataString)};
            }
        }
        uploadMessageAboveL.onReceiveValue(results);
        uploadMessageAboveL = null;
    
	}
	

    public static final int MSG_OPEN_TEST_URL = 0;
    public static final int MSG_INIT_UI = 1;
    public static final int MSG_DOWNLOADING = 2;
    public static final int MSG_INSTALLING = 3;
    public static final int MSG_INITIALIZATION = 4;
    private final int mUrlStartNum = 0;
    private int mCurrentUrl = mUrlStartNum;
    public Handler mTestHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_INIT_UI:
                    init();
                    break;
          
    
            }
            super.handleMessage(msg);
        }
    };
	public void 结束(View view){
		finish();
	}  public void url2bitmap(String url) {
        Bitmap bm = null;
        try {
            URL iconUrl = new URL(url);
            URLConnection conn = iconUrl.openConnection();
            HttpURLConnection http = (HttpURLConnection) conn;
            int length = http.getContentLength();
            conn.connect();
            // 获得图像的字符流  
            InputStream is = conn.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(is, length);
            bm = BitmapFactory.decodeStream(bis);
            bis.close();
            is.close();
            if (bm != null) {
                save2Album(bm);
            }
        } catch (Exception e) {
            runOnUiThread(new Runnable() {
					@Override
					public void run() {
						Toast.makeText(vipdy.this, "保存失败", Toast.LENGTH_SHORT).show();
					}
				});
            e.printStackTrace();
        }
    }private void save2Album(Bitmap bitmap) {
        File appDir = new File(Environment.getExternalStorageDirectory(), "云间阁");
        if (!appDir.exists()) appDir.mkdir();
        String[] str = picUrl.split("/");
        String fileName = str[str.length - 1];
        File file = new File(appDir, fileName);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
            onSaveSuccess(file);
        } catch (final IOException e) {
            runOnUiThread(new Runnable() {
					@Override
					public void run() {
						//Toasty.error(vipdy.this, "保存失败:"+e, Toast.LENGTH_SHORT).show();
					}
				});
            e.printStackTrace();
        }
    }

    private void onSaveSuccess(final File file) {
        runOnUiThread(new Runnable() {
				@Override
				public void run() {
					sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(file)));
					//Toasty.success(vipdy.this, "保存成功--手机储存/云间阁", Toast.LENGTH_SHORT).show();
				}
			});
    }
}
