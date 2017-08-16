package test.com.toutiao;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.List;

/**
 * name:周振辉
 * 时间：2017/7/19 20:39
 * 类描述：
 */

public class MyAdapter extends BaseAdapter{
    private Context mContext;
    private List<Data.ResultBean.DataBean> list;


    private ImageLoaderConfiguration  configuration;

    private final int TYPE1 = 0;
    private final int TYPE2 = 1;
    private final int TYPE3 = 2;
    private ImageLoader imageLoader;
    private DisplayImageOptions options;

    public MyAdapter(Context context, List<Data.ResultBean.DataBean> list) {
        mContext = context;
        this.list = list;

         //创建默认的ImageLoader配置参数
                 configuration = ImageLoaderConfiguration
                         .createDefault(context);
                 //将configuration配置到imageloader中
                 imageLoader=ImageLoader.getInstance();
                 imageLoader.init(configuration);

                 options=new DisplayImageOptions.Builder()
                         .cacheInMemory(true)
                         .cacheOnDisk(true)
                         .bitmapConfig(Bitmap.Config.ARGB_8888)
                         .showImageOnLoading(R.mipmap.ic_launcher)
                         .showImageForEmptyUri(R.mipmap.ic_launcher)
                         .showImageOnFail(R.mipmap.ic_launcher)
                         .build();
    }
    @Override
    public int getCount() {
        return list.size();
    }
    @Override
    public Object getItem(int position) {
        return list.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public int getItemViewType(int position) {
        if(list.get(position).getThumbnail_pic_s02()!=null && list.get(position).getThumbnail_pic_s03()!=null)
        {
            return TYPE3;
        }else if(list.get(position).getThumbnail_pic_s02()!=null && list.get(position).getThumbnail_pic_s03()==null)
        {
            return TYPE2;
        }else if(list.get(position).getThumbnail_pic_s02()==null && list.get(position).getThumbnail_pic_s()==null){
            return TYPE1;
        }
       return 0;
    }
    @Override
    public int getViewTypeCount() {
        return 3;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder=null;
        int type=getItemViewType(position);
        if(convertView==null){
                    holder=new ViewHolder();
            if(type==TYPE1){
                convertView=View.inflate(mContext,R.layout.item01,null);
                holder.name=(TextView)convertView.findViewById(R.id.item01_name);
                holder.title=(TextView)convertView.findViewById(R.id.item01_title);
                holder.date=(TextView)convertView.findViewById(R.id.item01_date);
                holder.image1=(ImageView)convertView.findViewById(R.id.item01_image);
            }else if(type==TYPE2){
                convertView=View.inflate(mContext,R.layout.item02,null);
                holder.name=(TextView)convertView.findViewById(R.id.item02_name);
                holder.title=(TextView)convertView.findViewById(R.id.item02_title);
                holder.date=(TextView)convertView.findViewById(R.id.item02_date);
                holder.image1=(ImageView)convertView.findViewById(R.id.item02_image1);
                holder.image2=(ImageView)convertView.findViewById(R.id.item02_image2);
            }else if(type==TYPE3){
                convertView=View.inflate(mContext,R.layout.item03,null);
                holder.name=(TextView)convertView.findViewById(R.id.item03_name);
                holder.title=(TextView)convertView.findViewById(R.id.item03_title);
                holder.date=(TextView)convertView.findViewById(R.id.item03_date);
                holder.image1=(ImageView)convertView.findViewById(R.id.item03_image1);
                holder.image2=(ImageView)convertView.findViewById(R.id.item03_image2);
                holder.image3=(ImageView)convertView.findViewById(R.id.item03_image3);
            }
            convertView.setTag(holder);
        }else{
            holder= (ViewHolder) convertView.getTag();
        }
        Data.ResultBean.DataBean bean = list.get(position);
        holder.title.setText(bean.getTitle());
        holder.name.setText(bean.getAuthor_name());
        holder.date.setText(bean.getDate());
        if(type==TYPE1){
            imageLoader.displayImage(bean.getThumbnail_pic_s(),holder.image1,options);
        }else if(type==TYPE2){
            imageLoader.displayImage(bean.getThumbnail_pic_s(),holder.image1,options);
            imageLoader.displayImage(bean.getThumbnail_pic_s02(),holder.image2,options);
        }else if(type == TYPE3)
        {
            imageLoader.displayImage(bean.getThumbnail_pic_s(),holder.image1,options);
            imageLoader.displayImage(bean.getThumbnail_pic_s02(),holder.image2,options);
            imageLoader.displayImage(bean.getThumbnail_pic_s03(),holder.image3,options);
        }
        return convertView;
    }
    class ViewHolder{
        TextView title,name,date;
        ImageView image1,image2,image3;
    }
}
