package com.sex.hall;


import android.content.*;
import android.util.*;
import android.view.*;
import android.widget.*;
import com.bumptech.glide.*;
import java.util.*;
/*import yun.jian.ge.*;
import yun.jian.ge.tool.jingpingtool.freemusic.*;
*/
public class Simpleadapter_jingping extends BaseAdapter
{  
    private List<Map<String,String>> Mdata=new ArrayList<>();  

	Context context;

	private long t;

	private long n;  

    public Simpleadapter_jingping(Context context,List<Map<String,String>> mData){  
        this.Mdata=mData;
        this.context = context;  
    }  

    @Override  
    public int getCount() {  
        return Mdata.size();
    }  

    @Override  
    public Object getItem(int position) {  
        return Mdata.get(position);  
    }  

    @Override  
    public long getItemId(int position) {  
        return position;  
    }  


    public class ViewHolder{  
        TextView textViewItem1;  
        ImageView imageView;

		public TextView textViewItem1plnr;

		public TextView textViewItem1plr;

		public TextView textViewItem1plsj;

		public TextView textViewItem1pltx;

		public TextView textViewItem1plgl;

		public TextView textViewItem1楼层;  
    }  

    @Override  
    public View getView(int position, View convertView, ViewGroup parent) {  
        ViewHolder viewHolder = null;  
		if(convertView==null){  
			convertView = LayoutInflater.from(context).inflate(  
				R.layout.jingping_item, null);  

            viewHolder = new ViewHolder();  
			viewHolder.textViewItem1plgl = (TextView)convertView.findViewById(R.id.jingpingitemTextView1);
			//viewHolder.textViewItem1pltx = (TextView)convertView.findViewById(R.id.歌手);
			/*viewHolder.textViewItem1plsj = (TextView)convertView.findViewById(R.id.plsj);
			 viewHolder.textViewItem1plr = (TextView)convertView.findViewById(R.id.plr); 
			 viewHolder.textViewItem1楼层 = (TextView)convertView.findViewById(R.id.楼层); 
			 viewHolder.textViewItem1plnr = (TextView)convertView.findViewById(R.id.plnrr);*/
            viewHolder.imageView = (ImageView)convertView.findViewById(R.id.jingpingitemImageView1);  
			convertView.setTag(viewHolder);  
        }else{  
            viewHolder = (ViewHolder)convertView.getTag();  
            Log.d("MyBaseAdapter", "旧的convertView,position="+position);  
        } 
		viewHolder.textViewItem1plgl.setText(Mdata.get(position).get("name"));
		//viewHolder.textViewItem1plsj.setText(Mdata.get(position).get("评论时间"));
		//viewHolder.textViewItem1plr.setText(Mdata.get(position).get("评论者"));
		//viewHolder.textViewItem1pltx.setText(Mdata.get(position).get("author"));
		// viewHolder.textViewItem1plnr.setText(Mdata.get(position).get("评论内容")); 
		//viewHolder.textViewItem1楼层.setText((position+1)+"楼"); 

		Glide
			.with(context)
			.load(  Mdata.get(position).get("pic"))
			.placeholder(R.drawable.ic_favorite_24dp)
			.error(R.drawable.ic_favorite_24dp)
			.into(viewHolder.imageView);
		
        return convertView;  
    }


}  


