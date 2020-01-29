package com.sex.hall;

import android.os.*;
import android.sax.*;
import android.support.v4.app.*;
import android.support.v7.widget.*;
import android.view.*;
import android.widget.*;
import java.io.*;
import java.util.*;
import org.jsoup.*;
import org.jsoup.nodes.Element;
import org.jsoup.select.*;
import org.jsoup.nodes.*;
import android.content.*;
import com.sex.hall.tentx5.*;
import android.view.View.*;


public class mao extends Fragment {
	private Simpleadapter_jingping adapter;
	private List<Map<String,String>> mdata=new ArrayList<>();
    public static final String TAB_NUMBER = "tab";
	private ListView list;
	private String 域名;
	private View view;
	private String 类型;
	private Handler handler=new Handler();
     int 页=1;
	private Button bt;
	private ProgressBar pro;

	private ProgressBar pro1;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		if (view == null) {
			view = inflater.inflate(R.layout.tab1, container, false);
			//String tab = getArguments().getString(TAB_NUMBER);
			//System.out.println(tab);
			bt=(Button)view.findViewById(R.id.tab1Button1);
			pro1=(ProgressBar)view.findViewById(R.id.tab1ProgressBar2);
			
			bt.setVisibility(view.GONE);
			//    View view = inflater.inflate(R.layout.tab1, container, false);
		 list=(ListView)view.findViewById(R.id.xiangqmeinListView1);
		 
		adapter = new Simpleadapter_jingping(getActivity(), mdata);
		list.setAdapter(adapter);
			pro=(ProgressBar)view.findViewById(R.id.tab1ProgressBar1);
			final SharedPreferences sharedPreferences=getActivity(). getSharedPreferences("性餐厅", Context .MODE_PRIVATE);
			域名=sharedPreferences.getString("av链接", "https://www.de325.com");
			//域名="https://www.633ii.com";
		开始();
		}ViewGroup parent = (ViewGroup) view.getParent();
        if (parent != null) {
            parent.removeView(view);
        }
		list.setOnItemClickListener(new AdapterView.OnItemClickListener(){

				private long n;

				private long t;

				private String time;

				private String 等级说明;
				@Override
				public void onItemClick(AdapterView<?> parent, View view, int position,long id) {
					Map<String, String> map = (Map<String, String>)adapter
						.getItem(position);
					Intent intent=new Intent();
					intent.setClass(getActivity(),av_wuma_sp.class);
					intent.putExtra("链接",map.get("id"));
					intent.putExtra("类型","点解点解");
					getActivity().startActivity(intent);
					/*
					wengjian 获取方法= new wengjian();
					try
					{
						if(获取方法.getdata()==1){
							Intent intent=new Intent();
							intent.setClass(getActivity(),av_wuma_sp.class);
							intent.putExtra("链接",map.get("id"));
							intent.putExtra("类型","点解点解");
							getActivity().startActivity(intent);
						}else{
							final MyUser myUser = BmobUser.getCurrentUser(MyUser.class);
							int a=myUser.getAge();
							//String 邀请码=myUser.getname();
							if(a<=3&& a>=0)
							{
								//特效.setText("sex0");
								//进度=a/4*100;
								等级说明="当前sex0,还差"+String.valueOf(4-a)+"点能量升级为sex1--每日免费看7次，已经达到上线，请提升等级后观看更多\n升级请看主页又上方我的资料";
							}if(a>= 4 && a < 12)
							{//特效.setText("sex1");
								等级说明="当前sex0,还差"+String.valueOf(12-a)+"点能量升级为sex2--每日免费看12次，已经达到上线，请提升等级后观看更多\n升级请看主页又上方我的资料";

								//进度=(a-4)/4*100;
							}if(a>= 12 && a <24)
							{等级说明="当前sex2,还差"+String.valueOf(24-a)+"点能量升级为sex3--每日免费看15次，已经达到上线，请提升等级后观看更多\n升级请看主页又上方我的资料";
								//特效.setText("sex2");
								//进度=(a-8)/6*100;
							}if(a>= 24)
							{//特效.setText("sex3");
								等级说明="当前sex3,已经达到当前版本最高特权";
								//进度=100;
							}
							dialog.create(getActivity(),"温馨提示",等级说明).show();
							
							//Toast.makeText(getActivity(), "进入已经达到播放上线", Toast.LENGTH_SHORT).show();
							
						}
						//获取方法.getdata();
					//	System.out.println(获取方法.getdata());
					}
					catch (Exception e)
					{System.out.println(e);
					}*/
				};
			});list.setOnScrollListener(new AbsListView.OnScrollListener() {

				private boolean scrollFlag;
				@Override
				public void onScroll(AbsListView p1, int p2, int p3, int p4)
				{
				}
				@Override
				public void onScrollStateChanged(AbsListView view, int scrollState) {
					switch (scrollState) {
						case AbsListView.OnScrollListener.SCROLL_STATE_IDLE:// 是当屏幕停止滚动时
							scrollFlag = false;
							// 判断滚动到底部 、position是从0开始算起的
							if (list.getLastVisiblePosition() == (list
                                .getCount() - 1)) {
									页=页+1;
								开始();pro1.setVisibility(view.VISIBLE);
							}
							break;
						case AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL:// 滚动时
							scrollFlag = true;
							break;
						case AbsListView.OnScrollListener.SCROLL_STATE_FLING: 
							// 当用户由于之前划动屏幕并抬起手指，屏幕产生惯性滑动时，即滚动时
							scrollFlag = true;
							break;
					}
				}
			});
		bt.setOnClickListener(new OnClickListener() {Intent intent=new Intent();
				@Override
				public void onClick(View v) {
					bt.setVisibility(view.GONE);
					pro.setVisibility(view.VISIBLE);
					开始();
				}
			});
        return view;
		
    }
	private void 开始()
	{System.out.println("mao，开始");
		new Thread(new Runnable() {
				private Elements li;
				@Override
				public void run() {
					//System.out.println(line+page+".html");
					try {
						System.out.println("mao"+域名+"/shipin/list-"+"国产精品-"+页+".html");
						Document document = Jsoup.connect(域名+"/shipin/list-"+"国产精品-"+页+".html")
							.timeout(10000)
							.get();
						if(document.toString().indexOf("成人在线，夜夜撸，免费A片，色影院，日日更新视频，草榴视频，乱伦，人体艺术，色吧图片，成人小说")!=-1){
							
							handler.post(new Runnable() {
									public void run() {
										dialog.create(getActivity(),"温馨提示","域名解析出错,请退出软件重新进入，如果还是不行请清除数据重新进入").show();
									}
								});
						}else{
							
						
							//System.out.println(document);
System.out.println("mao"+document);
						Elements noteList = document.select("ul.img-list-data");
						 li = noteList.select("li");		
						list.post(new Runnable() {
								public void run() {
									for (Element element : li) {
										String 标题=element.select("a").attr("title");
										String 图片=element.select("a").select("img").attr("data-original");
										String 链接 = element.select("a").attr("href");
										String 前缀=链接.substring(0, 链接.lastIndexOf("/"))+"/";
										链接=域名+"/shipin/play-"+链接.replace(前缀,"")+"?road=1";				System.out.println(element+"\n"+标题+链接+图片+"\n"+"————————————————————————");
										Map<String, String> item1=(Map<String, String>) new HashMap<String, Object>();
										item1.put("name", 标题);
										item1.put("pic", 图片);
										item1.put("id",链接);
										mdata.add(item1);

									}
									pro1.setVisibility(view.GONE);
									list.requestLayout();  
									list.setVisibility(View.GONE);
									adapter.notifyDataSetChanged();
									list.setVisibility(View.VISIBLE);
								}
							});
							}
		} catch (IOException e) {
						System.out.println(e);
			handler.post(new Runnable() {
					public void run() {
						bt.setVisibility(view.VISIBLE);
						pro1.setVisibility(view.GONE);
						pro.setVisibility(view.GONE);
					}
				});
					}

				}
			}).start();


}}
