package com.sex.hall;

import android.os.*;
import android.support.v4.app.*;
import android.view.*;
import android.widget.*;

import java.util.*;
import org.json.*;
import com.sex.hall.tentx5.*;
import android.content.*;
import com.sex.hall.zcdl.*;
import android.view.View.*;
import java.io.*;

public class tab extends Fragment {
	private Simpleadapter_jingping adapter;
	private List<Map<String,String>> mdata=new ArrayList<>();
	private ListView list;
	int 页,数;
	private View view = null;// 缓存Fragment view
private Handler handler=new Handler();
    public static final String TAB_NUMBER = "tab";
	private String b;
	private Button bt;

	private ProgressBar pro;

	private ProgressBar pro1;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
	
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		if (view == null) {
             view = inflater.inflate(R.layout.tab1, container, false);
			TextView a=(TextView)view.findViewById(R.id.tab1TextView1);
			a.setVisibility(View.GONE);
			bt=(Button)view.findViewById(R.id.tab1Button1);
			bt.setVisibility(view.GONE);
			pro=(ProgressBar)view.findViewById(R.id.tab1ProgressBar1);
			pro1=(ProgressBar)view.findViewById(R.id.tab1ProgressBar2);
			pro1.setVisibility(view.GONE);
			//开始(String.valueOf(1),"http://www.589cf.com"+"/meinv/list-3Agirl写真-","https://tu.ttt669.com/girl/Vgirl/");
			//开始1();
        }
        // 缓存的rootView需要判断是否已经被加过parent，
        // 如果有parent需要从parent删除，要不然会发生这个rootview已经有parent的错误。
        ViewGroup parent = (ViewGroup) view.getParent();
        if (parent != null) {
            parent.removeView(view);
        }
        bt.setOnClickListener(new OnClickListener() {Intent intent=new Intent();
				@Override
				public void onClick(View v) {
					bt.setVisibility(view.GONE);
					pro.setVisibility(view.VISIBLE);
					//开始1();
				}
			});
		list=(ListView)view.findViewById(R.id.xiangqmeinListView1);
		adapter=new Simpleadapter_jingping(getActivity(),mdata);
		list.setAdapter(adapter);
		list.setOnItemClickListener(new AdapterView.OnItemClickListener(){

				private long n;

				private long t;

				private String time;
				@Override
				public void onItemClick(AdapterView<?> parent, View view, int position,long id) {
					//int headerViewsCount = list.getHeaderViewsCount();//得到header的总数量

					Map<String, String> map = (Map<String, String>)adapter
						.getItem(position);
					
					
				};
				
			});

	
        return view;
		
    }
/*
	private void 开始1()
	{BmobQuery<get> query = new BmobQuery<get>();
		query.getObject("Im8WGGGU", new QueryListener<get>() {


				@Override
				public void done(get object, BmobException e) {
					if(e==null){
						//获得playerName的信息
						String json数据=object.getbutton();

						b = json数据.replace("\\", "");

						if (b != null && b.startsWith("\ufeff"))
						{
							b= b.substring(1);
						}
						parseJsonMulti(b);
						//System.out.println(b);
					}
					else
					{dialog.create(getActivity(),"温馨提示","更新用户失败"+e.getMessage()).show();
						
						//Toasty.warning(jingping.this,"请付费",Toast.LENGTH_SHORT,true).show();
						bt.setVisibility(view.VISIBLE);
						pro.setVisibility(view.GONE);
						//Log.i("bmob", "失败：" + e.getMessage() + "," + e.getErrorCode());
					}
				}

			});
		// TODO: Implement this method
	}*/
	
	private void parseJsonMulti(String strResult) { 
		try { 
			JSONArray jsonObjs = new JSONObject(strResult).getJSONArray("videos"); 
			for(int i = 0; i < jsonObjs.length() ; i++){ 

				//Map<String, Object> item = new HashMap<String, Object>();
				Map<String,String> item=(Map<String, String>) new HashMap<String, Object>();
				JSONObject jsonObj = (JSONObject)jsonObjs.get(i); 
				String 名字 = jsonObj.getString("video_name"); 
				String 链接 = "http://qiezsp889911.chuantouchi.com:8092"+jsonObj.getString("url");
				String pic = jsonObj.getString("preview_pics");
				item.put("id",链接);
				item.put("name",名字);
				item.put("pic","https://src.mnbvvbnm.com"+pic);
				mdata.add(item);
				adapter.notifyDataSetChanged();
			}
			//mSinger.setText(s); 
		} catch (JSONException e) { 
			//mSinger.setText("sb");
		
			//System.out.println("Jsons parse error !"); 
			e.printStackTrace(); 
			System.out.println(e);

		} 
	}
}
