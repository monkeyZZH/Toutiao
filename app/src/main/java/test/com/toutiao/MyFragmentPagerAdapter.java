package test.com.toutiao;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * name:周振辉
 * 时间：2017/7/19 16:12
 * 类描述：
 */

public class MyFragmentPagerAdapter extends FragmentPagerAdapter {

    public int COUNT;
    private String[] titles;
    private Context context;
    public MyFragmentPagerAdapter(FragmentManager fm, Context context,List<String> list) {
        super(fm);
        this.context = context;
        COUNT = list.size();
        titles = new String[COUNT];
        for (int i = 0;i<list.size();i++)
        {
            titles[i] = list.get(i).toString();
        }
    }
    @Override
    public Fragment getItem(int position) {
        return PageFragment.newInstance(position + 1);
    }

    @Override
    public int getCount() {
        return COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
