package test.com.toutiao;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * name:周振辉
 * 时间：2017/7/18 21:06
 * 类描述：
 */

public class WodeActivity extends Fragment{

    private ImageView image;
    private TextView name1;
    private TextView daynight;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.wode_xml,container,false);

        image = (ImageView)view.findViewById(R.id.qq_image);
        name1 = (TextView)view.findViewById(R.id.qq_name);
        daynight = (TextView)view.findViewById(R.id.daynight);

        daynight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyActivity activity = (MyActivity) getActivity();
                activity.dayNightChange();
            }
        });

        name1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyActivity activity = (MyActivity) getActivity();
                activity.login(image,name1);
            }
        });
        return view;
    }




}
