package test.com.toutiao;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.andy.share.QQOauthUtils;

import org.xutils.x;


/**
 * name:周振辉
 * 时间：2017/7/18 21:38
 * 类描述：
 */

public class MyActivity extends FragmentActivity{
    private ShouyeActivity Shouye;
    private WeitouActivity Weitou;
    private XiguaActivity Xigua;
    private WodeActivity Wode;

    private RadioButton b1,b2,b3,b4;
   public QQOauthUtils mQQOauthUtils;
    private int theme = R.style.AppTheme;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        if(savedInstanceState != null){
            theme = savedInstanceState.getInt("theme");
            setTheme(theme);
        }



        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_xml);

        b1 = (RadioButton)findViewById(R.id.shouye);
        b2 = (RadioButton)findViewById(R.id.xigua);
        b3 = (RadioButton)findViewById(R.id.weitou);
        b4 = (RadioButton)findViewById(R.id.wode);

        Shouye = new ShouyeActivity();
        Xigua = new XiguaActivity();
        Weitou = new WeitouActivity();
        Wode = new WodeActivity();


        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frament,Shouye)
                .commit();


    }
    public void SY(View v){
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frament,Shouye)
                .commit();
        b1.setTextColor(getResources().getColor(R.color.cheng));
        b2.setTextColor(getResources().getColor(R.color.hei));
        b3.setTextColor(getResources().getColor(R.color.hei));
        b4.setTextColor(getResources().getColor(R.color.hei));

    }
    public void XG(View v){
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frament,Xigua)
                .commit();
        b1.setTextColor(getResources().getColor(R.color.hei));
        b2.setTextColor(getResources().getColor(R.color.cheng));
        b3.setTextColor(getResources().getColor(R.color.hei));
        b4.setTextColor(getResources().getColor(R.color.hei));

    }
    public void WT(View v){
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frament,Weitou)
                .commit();
        b1.setTextColor(getResources().getColor(R.color.hei));
        b2.setTextColor(getResources().getColor(R.color.hei));
        b3.setTextColor(getResources().getColor(R.color.cheng));
        b4.setTextColor(getResources().getColor(R.color.hei));

    }
    public void WD(View v){
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frament,Wode)
                .commit();
        b1.setTextColor(getResources().getColor(R.color.hei));
        b2.setTextColor(getResources().getColor(R.color.hei));
        b3.setTextColor(getResources().getColor(R.color.hei));
        b4.setTextColor(getResources().getColor(R.color.cheng));

    }

    public void login(final ImageView image, final TextView namel){
        mQQOauthUtils=new QQOauthUtils(MyActivity.this, new QQOauthUtils.IUserPhoto() {
            @Override
            public void userPhoto(String userPhoto,String name) {
                x.image().bind(image,userPhoto);
                namel.setText(name);
            }
        });
        mQQOauthUtils.qqLogin();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mQQOauthUtils.onActivityResult(requestCode,resultCode,data);
        Log.e("dara",data.toString());
    }

    //日夜间转换

    public void dayNightChange(){
        theme = (theme == R.style.AppTheme) ? R.style.NightAppTheme : R.style.AppTheme;
        MyActivity.this.recreate();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("theme", theme);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        theme = savedInstanceState.getInt("theme");
    }


}

