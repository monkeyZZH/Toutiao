package test.com.toutiao;

import org.xutils.x;

/**
 * name:周振辉
 * 时间：2017/7/20 17:28
 * 类描述：
 */

public class App extends com.andy.share.App{
    @Override
    public void onCreate() {
        super.onCreate();


        x.Ext.init(this);
        //设置日志级别
        x.Ext.setDebug(BuildConfig.DEBUG);
    }
}
