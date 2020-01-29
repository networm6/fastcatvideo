package com.sex.hall;


import android.app.*;
import android.content.*;
import android.os.*;
import android.support.v4.app.*;
import android.support.v7.app.*;
import android.telephony.*;
import android.view.*;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.content.pm.*;
import android.widget.*;
import android.view.View.*;
import android.net.*;



public class MainActivity extends AppCompatActivity {

    private static final String CURRENT_MODE = "current_mode";
    private int mCurrentMode = -1;

	private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
	
	//Bmob.initialize(this, "2e7b198444d1345a9329e9b32b47f8af");
		//更新用户();
			
        if (savedInstanceState != null) {
            onRestoreInstanceState(savedInstanceState);
        } else {
            // Add the default mode fragment first
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            Fragment fragment = new TabsParentFragment();
            Bundle bundle = new Bundle();
            bundle.putInt(TabsParentFragment.MODE, TabsParentFragment.ANIMATED_TABS);
            bundle.putString(TabsParentFragment.MODE_TITLE, getString(R.string.app_name));
			fragment.setArguments(bundle);
            transaction.add(R.id.container, fragment);
            transaction.commit();
        }
	

	    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);

        // restore the current selected mode
        if (mCurrentMode != -1) {
            onOptionsItemSelected(menu.findItem(mCurrentMode));
        }

        return true;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(CURRENT_MODE, mCurrentMode);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mCurrentMode = savedInstanceState.getInt(CURRENT_MODE);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long

        // as you specify a parent activity in AndroidManifest.xml.
		mCurrentMode = menuItem.getItemId();
        if (menuItem.getItemId() == R.id.mode7) {
			/*Intent intent=new Intent(MainActivity.this,myself.class);
			startActivity(intent);*/
		}else
		if (menuItem.getItemId() == R.id.mode6) {
			joinQQGroup("2u4G01Zo2fglLxKXcn0QnF8rcxH-oCdO");
		}else
		if (menuItem.getItemId() == R.id.我的) {
			/*Intent intent=new Intent(MainActivity.this,myself.class);
			startActivity(intent);*/
		}else

			return super.onOptionsItemSelected(menuItem);
		return false;
	}
public boolean joinQQGroup(String key) {
		Intent intent = new Intent();
		intent.setData(Uri.parse("mqqopensdkapi://bizAgent/qm/qr?url=http%3A%2F%2Fqm.qq.com%2Fcgi-bin%2Fqm%2Fqr%3Ffrom%3Dapp%26p%3Dandroid%26k%3D" + key));
		// 此Flag可根据具体产品需要自定义，如设置，则在加群界面按返回，返回手Q主界面，不设置，按返回会返回到呼起产品界面    //intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
		try {
			startActivity(intent);
			return true;
		} catch (Exception e) {
			// 未安装手Q或安装的版本不支持
			return false;
		}
	}
	
}
