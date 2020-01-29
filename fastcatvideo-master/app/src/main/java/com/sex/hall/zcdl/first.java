package com.sex.hall.zcdl;

import android.*;
import android.app.*;
import android.content.*;
import android.content.pm.*;
import android.net.*;
import android.net.http.*;
import android.os.*;
import android.provider.*;
import android.support.annotation.*;
import android.support.v4.app.*;
import android.support.v4.content.*;
import android.support.v7.app.*;
import android.telephony.*;
import android.view.*;
import android.webkit.*;
import android.widget.*;
import com.sex.hall.*;
import java.io.*;
import java.util.*;

import android.support.v7.app.AlertDialog;
import com.sex.hall.R;
public class first extends AppCompatActivity
{
	public final static int PERMISSION_REQUESTCODE = 1;
	private String aabbs;


	private WebView mWebView;

	private String 域名;

	private int 次,邀请=0;

	private LinearLayout 布局;

	private ProgressDialog progressDialog;

	private EditText ed;

	//private ImageView ImageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first);
		//final MyUser myUser = BmobUser.getCurrentUser(MyUser.class);
		次=0;
		
		mWebView = (WebView) findViewById(R.id.firstwebview1);
        // 设置WebView的客户端
		布局 = (LinearLayout) findViewById(R.id.firstLinearLayout2);
		布局.setVisibility(View.GONE);
		//邀请();
		ed = (EditText) findViewById(R.id.firstEditText1);
		getWindow().setFlags(WindowManager.LayoutParams. FLAG_FULLSCREEN ,
							 WindowManager.LayoutParams. FLAG_FULLSCREEN);     
		permission();
	}
	private void permission() {
        List<String> permissionLists = new ArrayList<>();
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            permissionLists.add(Manifest.permission.READ_PHONE_STATE);
        }
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            permissionLists.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }

        if (!permissionLists.isEmpty()) {//说明肯定有拒绝的权限
            ActivityCompat.requestPermissions(this, permissionLists.toArray(new String[permissionLists.size()]), PERMISSION_REQUESTCODE);
			openOptionsMenu();
        } else {
			doBackup();
			
			//   Toast.makeText(this, "权限都授权了，可以搞事情了", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PERMISSION_REQUESTCODE:
                if (grantResults.length > 0) {
                    for (int grantResult : grantResults) {
                        if (grantResult != PackageManager.PERMISSION_GRANTED) {
							openAppDetails();
							//   Toast.makeText(this, "某一个权限被拒绝了", Toast.LENGTH_SHORT).show();
                            return;
                        }else{
							//Toast.makeText(this, "全部允许", Toast.LENGTH_SHORT).show();
							
							doBackup();
						}
                    }
                    //其他逻辑(这里当权限都同意的话就执行打电话、获取本地信息等逻辑)
                }
                break;
            default:
                break;
        }
    }
	private void doBackup() {
	登录了();
	}

	
	


	/**
	 * 打开 APP 的详情设置
	 */
	private void openAppDetails() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setCancelable(false);//点击屏幕和按返回键都不能取消加载框
		
		builder.setMessage("云间阁的使用需要访问 “内部储存” 和 “外部存储器”，请到 “应用信息 -> 权限” 中授予！");
		builder.setPositiveButton("去手动授权", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					Intent intent = new Intent();
					intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
					intent.addCategory(Intent.CATEGORY_DEFAULT);
					intent.setData(Uri.parse("package:" + getPackageName()));
					intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
					intent.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
					startActivity(intent);
					finish();
				}
			});
		builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					finish();
				}});
		builder.show();
	}

	

	private void 登录了()
	{  Toast.makeText(this, "开始加载域名.....", Toast.LENGTH_SHORT).show();
	mWebView.setWebViewClient(new WebViewClient(){
				@Override
				public boolean shouldOverrideUrlLoading(WebView view, String url) {
					return false;// 返回false
				}
			});
		
		WebSettings webSettings = mWebView.getSettings();
        // 让WebView能够执行javaScript
        webSettings.setJavaScriptEnabled(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
		final SharedPreferences sharedPreferences=getSharedPreferences("性餐厅", Context .MODE_PRIVATE);
		域名=sharedPreferences.getString("av链接","https://www.617uu.com/");
        mWebView.loadUrl(域名);
        ///4、设置响应超链接，在安卓5.0系统，不使用下面语句超链接也是正常的，但在MIUI中安卓4.4.4中需要使用下面这条语句，才能响应超链接
		mWebView.setWebViewClient(new WebViewClient() {
				@Override
				public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
					handler.proceed(); // 兼容https
				}
				@Override
				public void onPageFinished(WebView view, String url) {
					super.onPageFinished(view, url);
					次=次+1;
					if(次==1){
						final SharedPreferences sharedPreferences= getSharedPreferences("性餐厅", Context .MODE_PRIVATE);
						SharedPreferences.Editor editor = sharedPreferences.edit();
						editor.putString("av链接", view.getUrl().substring(0,view.getUrl() .indexOf("com/"))+"com");
						editor.commit();
						//final SharedPreferences sharedPreferences=getActivity(). getSharedPreferences("性餐厅", Context .MODE_PRIVATE);
						域名=sharedPreferences.getString("av链接", "");
						System.out.println("注册======="+域名);
						Intent intent=new Intent(first.this,MainActivity.class);
						startActivity(intent);
					}
				}
			});
	
		
		
	}


	
	
	
	/*private void 加载广告()
	{File filetx= new File(Environment.getExternalStorageDirectory().getPath()+"/.云间阁","广告.txt");
		if (!filetx.exists()){
			try
			{
				filetx.createNewFile();
				/*Glide
				 .with(this)
				 .load("https://ib11.go2yd.com/image.php?url=0Joba6pu1V")
				 .dontAnimate()
				 .diskCacheStrategy(DiskCacheStrategy.ALL)
				 //.transform(new yuanjiao(this))
				 .into(ImageView);*/
			/*}
			catch (IOException e)
			{System.out.println(e);

			}


		}else{
			filetx.delete();
			/*Glide
			 .with(this)
			 .load("https://ib11.go2yd.com/image.php?url=0JozRiY1rI")
			 .dontAnimate()
			 .diskCacheStrategy(DiskCacheStrategy.ALL)
			 .into(ImageView);*/
		/*}
	}
	public void 点击广告(View view){
		File filetx= new File(Environment.getExternalStorageDirectory().getPath()+"/.云间阁","广告.txt");
		if (!filetx.exists()){
			//Toasty.success(this,"点击广告○1",Toast.LENGTH_SHORT,true).show();

		}else{
			filetx.delete();
			//Toasty.success(this,"点击广告○2",Toast.LENGTH_SHORT,true).show();

		}

	}*/
	public void 加载() {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(this);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        }  
        progressDialog.setMessage("正在初始化....");	//设置内容
        progressDialog.setCancelable(false);//点击屏幕和按返回键都不能取消加载框
        progressDialog.show();
    }
	private void 肖框()
	{if(progressDialog!=null){
			progressDialog.dismiss();
		}
		// TODO: Implement this method
	}
	
	
}


