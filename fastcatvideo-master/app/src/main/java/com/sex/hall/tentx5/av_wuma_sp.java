package com.sex.hall.tentx5;

import android.content.*;
import android.content.pm.*;
import android.content.res.*;
import android.graphics.*;
import android.net.*;
import android.os.*;
import android.support.v7.app.*;
import android.util.*;
import android.view.*;
import android.view.WindowManager.*;
import android.widget.*;
import java.io.*;
import java.util.*;
import java.util.regex.*;
import org.jsoup.*;
import org.jsoup.nodes.*;
import com.tencent.smtt.export.external.interfaces.*;
import com.tencent.smtt.sdk.*;
import com.tencent.smtt.utils.*;

import android.app.*;
import java.text.*;
import com.sex.hall.tentx5.utils.*;
import com.sex.hall.*;
import android.view.View.*;


/**
* 演示demo
*/
public class av_wuma_sp extends AppCompatActivity{
	private String 链接1,播放链接;
	private String 链接;
	private EditText edit;

	//private ZLoadingDialog dialog;
	//private FrameLayout mVideoContainer;

	private LinearLayout 标题;

	private TextView tex;

	//private RelativeLayout 高度;

	private ViewGroup mViewParent;

	private X5WebView mWebView;

	private String 次数1;

	private int 热门;

	private int 线路1;

	private TextView 线路;

	private int 视频监听=0;



@Override
protected void onCreate(Bundle savedInstanceState) {
super.onCreate(savedInstanceState);
setContentView(R.layout.av_sp_wuma);
	//mWebView= (WebView) findViewById(R.id.avweb);
	mViewParent = (ViewGroup) findViewById(R.id.webViewa);



	//mVideoContainer= (FrameLayout) findViewById(R.id.avspwumaF);
	标题= (LinearLayout) findViewById(R.id.avspwumaLinearLayout1);
	//高度= (RelativeLayout) findViewById(R.id.avspwumaRelativeLayout1);
	tex= (TextView) findViewById(R.id.avspwumaTextView1);
	线路= (TextView) findViewById(R.id.avspwumaTextView2);
	final SharedPreferences sharedPreferences=getSharedPreferences("性餐厅", Context .MODE_PRIVATE);
	线路1=sharedPreferences.getInt("线路",1);
Intent intt=getIntent();
	链接1=intt.getStringExtra("链接");
	String 类型=intt.getStringExtra("类型");
	edit = (EditText) findViewById(R.id.avspwumaEditText1);
	edit.setText(链接1);
	//init();
	if(类型==null){
		init();
		热门=1;
		线路.setVisibility(View.GONE);
	}else{
		if(线路1==1){
			线路.setText("线路-1");
		开始("m3u8_host");
		}if(线路1==2){
			线路.setText("线路-2");
			开始("m3u8_host1");
		}
	}
    
	
				/*
	SharedPreferences sharedPreferences= getSharedPreferences("bmob数据", Context.MODE_PRIVATE);
	int 路=sharedPreferences.getInt("av线路", 1);//
	if (路==1)
	{
		开始("m3u8_host");
					}
					if(路==2){
						开始("m3u8_host1");
					}if(路==3){
						开始("m3u8_host2");
					}
				
    }*/
	}
		private void 开始(final String 路)
			{
			new Thread(new Runnable() {

			private Document document;
			private String content;
			private String a1;
			private String a标签;

		private String b;

	private String a;

	 private String 标签;

	private String 标题;

			private String 图片;

						

						//private Element 代解析;

						
	@Override
	public void run() {
	System.out.println("开始");
	try {
		Document document = Jsoup.connect(链接1)
							.timeout(10000)
		.get();
		//String 代解析= document.select("script").get(0).toString().replace(" ","");	
	//	System.out.println("☆"+document.select("script").get(0).toString().replace("'","\"").replace("var","").replace(" ",""));
		String 代解析=document.select("script").get(0).toString().replace("'", "\"").replace("var", "").replace(" ", "");
		链接1 = 解析(代解析, 路) + 解析(代解析, "video");
		runOnUiThread(new Runnable() {

								@Override
								public void run() {
									//dialog.dismiss();
									
				//	initWebView();

				//	mWebView.loadUrl(链接);

					//mWebView.addJavascriptInterface(new JsObject(),"onClick");
					
					edit.setText(链接1);
					init();
					/*mTestHandler.sendEmptyMessageDelayed(MSG_INIT_UI, 10);
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
						});*/
								}
							});
					} catch (IOException e) {
					//	dialog.dismiss();
						e.printStackTrace();
						System.out.println("失败");
					}

				}
			}).start();
			}public void 结束(View view){
				finish();
			}
			@Override
			public boolean onKeyDown(int keyCode, KeyEvent event) {
				if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
					//++自定义点击事件
					finish();
					return true;//不执行父类的点击事件
				}
				return super.onKeyDown(keyCode, event);//继续执行父类的其他点击事件   
			}
	/*private String 解析(String 标签, String p1)
	{Pattern pattern = Pattern.compile(p1+"=(.*?)＇");
		Matcher matcher = pattern.matcher(标签);//sendString为网页源码 
//使用循环找出 html里所有的img标签
		if(matcher.find()){
					}
		return null;
	}*/private String 解析(String 标签, String p1)
	{Pattern pattern = Pattern.compile(p1+"=\"(.*?)\"");
		Matcher matcher = pattern.matcher(标签);//sendString为网页源码 
//使用循环找出 html里所有的img标签
		if(matcher.find()){
			return matcher.group().replace(p1+"=\"","").replace("\"","");
			

		}
		return null;
	}
			private void init() {


				VIPlayer.mTestHandler = null;


				if(mWebView != null) return;

				mWebView = new X5WebView(this, null);

				mViewParent.addView(mWebView, new FrameLayout.LayoutParams(
										FrameLayout.LayoutParams.FILL_PARENT,
										FrameLayout.LayoutParams.FILL_PARENT));

				//initProgressBar();

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

						
					});

				mWebView.setWebChromeClient(new WebChromeClient() {

						private File 数量;

						private int 数;

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
						/*	if (mPageLoadingProgressBar != null && newProgress != 100) {
								mPageLoadingProgressBar.setProgress(newProgress);

							} else {
								mPageLoadingProgressBar.setProgress(0);
							}*/
							if(newProgress==100){
								if(视频监听==1){
									视频监听=视频监听+1;
									数量 = new File(Environment.getExternalStorageDirectory().getPath()+"/Android/data/android.sex/number");
									//File f = new File("src\\com\\itheima_01\\RecurrenceDemo.java");
									String 数字=method1(数量).replace(".time","");
									数 =  Integer.parseInt(数字);
									File fom =new File(数量,method1(数量)) ;
									File t1o=new File(数量,Integer.toString(数+1)+".time") ;
									fom.renameTo(t1o) ; 
								}
							}

						}
					});

				
				mWebView.loadUrl("https://dynamicnewplayers.pe62.com/mdparse/m3u8.php?id="+链接1);
				视频监听=视频监听+1;
				CookieSyncManager.createInstance(this);
				CookieSyncManager.getInstance().sync();
			}

			

			boolean[] m_selected = new boolean[] { true, true, true, true, false,
				false, true };

			
			
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
			public void 线路 (View view){
				final View view1 = LayoutInflater.from(av_wuma_sp.this).inflate(R.layout.avstring, null);
				final Button bt1=(Button) view1.findViewById(R.id.avstringButton1);
				final Button bt2=(Button) view1.findViewById(R.id.avstringButton2);
				//final Button bt3=(Button) view1.findViewById(R.id.avstringButton3);
				final Button bt4=(Button) view1.findViewById(R.id.avstringButton4);
				final Dialog dialog1 = new Dialog(av_wuma_sp.this, R.style.Dialog);//,R.style.dialog
				Window dialogWindow = dialog1.getWindow();
				WindowManager.LayoutParams params = dialog1.getWindow().getAttributes(); // 获取对话框当前的参数值
				dialogWindow.setAttributes(params);
				dialog1.setContentView(view1);
				dialog1.setCanceledOnTouchOutside(false);
				dialog1.setCancelable(false);	
				bt4.setOnClickListener(new OnClickListener() {
						@Override
						public void onClick(View arg0) {
							dialog1.dismiss();
						}
					});
				bt1.setOnClickListener(new OnClickListener() {
						@Override
						public void onClick(View arg0) {
							SharedPreferences sharedPreferences= getSharedPreferences("性餐厅",Context.MODE_PRIVATE);
//步骤2： 实例化SharedPreferences.Editor对象
							SharedPreferences.Editor editor = sharedPreferences.edit();
							editor.putInt("线路",1);//int类型数
							finish();
//步骤4：提交
							editor.commit();
							dialog1.dismiss();
						}
					});
				bt2.setOnClickListener(new OnClickListener() {
						@Override
						public void onClick(View arg0) {
							SharedPreferences sharedPreferences= getSharedPreferences("性餐厅",Context.MODE_PRIVATE);
//步骤2： 实例化SharedPreferences.Editor对象
							SharedPreferences.Editor editor = sharedPreferences.edit();
							editor.putInt("线路",2);//int类型数
//步骤4：提交
							finish();
							editor.commit();
							dialog1.dismiss();
						}
					});
				
				dialog1.show();
				}
			public void yell (View view){
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date curDate = new Date(System.currentTimeMillis());
				String 目前 = formatter.format(curDate);
				
				DownloadManager.Request request = new DownloadManager.Request(Uri.parse(链接1));
//指定下载路径和下载文件名

				request.setDestinationInExternalPublicDir("/性餐厅/", 目前+".mp4");
//获取下载管理器
				DownloadManager downloadManager= (DownloadManager) av_wuma_sp.this.getSystemService(Context.DOWNLOAD_SERVICE);
//将下载任务加入下载队列，否则不会进行下载
				downloadManager.enqueue(request);
			//	Toasty.warning(av_wuma_sp.this,"加入下载列表",Toast.LENGTH_SHORT,true).show();
				
			}public static String method1(File file) {
				if(file.isDirectory()) {
					File[] files = file.listFiles();
					for (File f : files) {
						//判断是否是文件对象
						if(f.isFile()) {
							if(f.getName().endsWith(".time")) {
								System.out.println(f.getName());
								return f.getName();
							}
						}
					}
				}return null;
			}
}
