package test.com.toutiao;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.Gson;

import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * name:周振辉
 * 时间：2017/7/18 21:06
 * 类描述：
 */

public class WeitouActivity extends Fragment{

    private ListView listView;
    private String uriPath = "http://v.juhe.cn/toutiao/index?type=shishang&key=2f092bd9ce76c0257052d6d3c93c11b4";
    private List<Data.ResultBean.DataBean> list;
    private MyAdapter adapter;
    private Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            Data bean = new Gson().fromJson(msg.obj.toString(),Data.class);
            list = new ArrayList<>();
            list.addAll(bean.getResult().getData());
            adapter = new MyAdapter(getActivity(),list);
            listView.setAdapter(adapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(getActivity(),Shouye_xiang.class);
                    intent.putExtra("uri",list.get(position).getUrl());
                    startActivity(intent);
                }
            });

        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.weitou_xml,container,false);
        x.view().inject(getActivity());
        listView = (ListView)view.findViewById(R.id.listview02);
        new Thread(){
            @Override
            public void run() {
                super.run();
                String aa = HttpUtils.getUrlConnect(uriPath);
                Message msg= Message.obtain();
                msg.what=1;
                msg.obj=aa.toString();
                mHandler.sendMessage(msg);
            }
        }.start();
        return view;
    }
}
