package com.sex.hall.tentx5;
import com.tencent.smtt.sdk.*;
import android.net.*;

public class ReWebChomeClient extends WebChromeClient {  


    private OpenFileChooserCallBack mOpenFileChooserCallBack;  


    public ReWebChomeClient(OpenFileChooserCallBack openFileChooserCallBack) {  
        mOpenFileChooserCallBack = openFileChooserCallBack;  
    }  


    //For Android 3.0+  
    public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType) {  
        mOpenFileChooserCallBack.openFileChooserCallBack(uploadMsg, acceptType);  
    }  


    // For Android < 3.0  
    public void openFileChooser(ValueCallback<Uri> uploadMsg) {  
        openFileChooser(uploadMsg, "");  
    }  


    // For Android  > 4.1.1  
    public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType, String capture) {  
        openFileChooser(uploadMsg, acceptType);  
    }  


    // For Android 5.0+  
    @Override  
    public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback, FileChooserParams   


									 fileChooserParams) {  
        mOpenFileChooserCallBack.showFileChooserCallBack(filePathCallback);  
        return true;  
    }  


    public interface OpenFileChooserCallBack {  
        void openFileChooserCallBack(ValueCallback<Uri> uploadMsg, String acceptType);  


        void showFileChooserCallBack(ValueCallback<Uri[]> filePathCallback);  
    }  

}  
