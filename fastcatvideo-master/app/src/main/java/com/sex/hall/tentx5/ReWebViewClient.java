package com.sex.hall.tentx5;
import com.tencent.smtt.sdk.*;
import android.graphics.*;

public class ReWebViewClient extends WebViewClient
{

	//private Bitmap favicon;  

    @Override  
    public void onPageStarted(WebView view, String url, Bitmap favicon) {  
        super.onPageStarted(view, url, favicon);  
    }  

    @Override  
    public void onPageFinished(WebView view, String url) {  
        super.onPageFinished(view, url);  
    }  
}  
