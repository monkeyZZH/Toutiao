package test.com.toutiao;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
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
 * 时间：2017/7/19 16:12
 * 类描述：
 */

public class PageFragment extends Fragment{
    public static final String ARGS_PAGE = "args_page";
    private int mPage;
    private String uriPath;

    public static PageFragment newInstance(int page) {
        Bundle args = new Bundle();

        args.putInt(ARGS_PAGE, page);
        PageFragment fragment = new PageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(ARGS_PAGE);
    }
private ListView listView;
    private MyAdapter adapter;
    private List<Data.ResultBean.DataBean> list;
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
        View view = inflater.inflate(R.layout.shouye_fragment_xml,container,false);
        x.view().inject(getActivity());
        listView = (ListView)view.findViewById(R.id.listview);
       panduan();
        Log.e("11111111111111111",uriPath);

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

    private void panduan() {
        if(mPage == 1)
        {
            uriPath = "http://v.juhe.cn/toutiao/index?type=top&key=2f092bd9ce76c0257052d6d3c93c11b4";
        }else if(mPage == 2)
        {
            uriPath = " http://v.juhe.cn/toutiao/index?type=shehui&key=2f092bd9ce76c0257052d6d3c93c11b4";
        }else if(mPage == 3)
        {
            uriPath = "http://v.juhe.cn/toutiao/index?type=guonei&key=2f092bd9ce76c0257052d6d3c93c11b4";
        }else if(mPage == 4)
        {
            uriPath = "http://v.juhe.cn/toutiao/index?type=guiji&key=2f092bd9ce76c0257052d6d3c93c11b4";
        }else if(mPage == 5)
        {
            uriPath = "http://v.juhe.cn/toutiao/index?type=yule&key=2f092bd9ce76c0257052d6d3c93c11b4";
        }else if(mPage == 6)
        {
            uriPath = "http://v.juhe.cn/toutiao/index?type=tiyu&key=2f092bd9ce76c0257052d6d3c93c11b4";
        }else if(mPage == 7)
        {
            uriPath = "http://v.juhe.cn/toutiao/index?type=junshi&key=2f092bd9ce76c0257052d6d3c93c11b4";
        }else if(mPage == 8)
        {
            uriPath = "http://v.juhe.cn/toutiao/index?type=keji&key=2f092bd9ce76c0257052d6d3c93c11b4";
        }else if(mPage == 9)
        {
            uriPath = "http://v.juhe.cn/toutiao/index?type=caijing&key=2f092bd9ce76c0257052d6d3c93c11b4";
        }else if(mPage == 10)
        {
            uriPath = "http://v.juhe.cn/toutiao/index?type=shishang&key=2f092bd9ce76c0257052d6d3c93c11b4";
        }
    }




}
