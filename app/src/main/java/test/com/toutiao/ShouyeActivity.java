package test.com.toutiao;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;


/**
 * name:周振辉
 * 时间：2017/7/18 21:06
 * 类描述：
 */

public class ShouyeActivity extends Fragment {


    private ViewPager viewPager;
    private String urlPath = "http://result.eolinker.com/gfGTLlHc049c6b450500b16971f52bd8e83f6b2fed305ab?uri=news";

    private MyFragmentPagerAdapter adapter;
    private TabLayout tabLayout;
    private List<String> list=new ArrayList<>();
    private List<Biaoti.ResultBean.DateBean> list_b=new ArrayList<>();



    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.shouye_xml,container,false);
        x.view().inject(getActivity());
        //Fragment+ViewPager+FragmentViewPager组合的使用
       viewPager = (ViewPager) view.findViewById(R.id.viewPager);

        //TabLayout
       tabLayout = (TabLayout) view.findViewById(R.id.tabLayout);

        RequestParams params = new RequestParams(urlPath);
        x.http().get(params, new Callback.CommonCallback<String>() {

            @Override
            public void onSuccess(String result) {

                Biaoti biaoti = new Gson().fromJson(result,Biaoti.class);

                list_b.addAll(biaoti.getResult().getDate());


                for (int i=0;i<list_b.size();i++)
                {
                    list.add(i,list_b.get(i).getTitle());
                }

                 adapter = new MyFragmentPagerAdapter((getActivity().getSupportFragmentManager()),
                        getActivity(),list);

                viewPager.setAdapter(adapter);
                tabLayout.setupWithViewPager(viewPager);
                adapter = new MyFragmentPagerAdapter((getActivity().getSupportFragmentManager()),
                        getActivity(),list);

                viewPager.setAdapter(adapter);
                tabLayout.setupWithViewPager(viewPager);


            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });











        return view;
    }




}
