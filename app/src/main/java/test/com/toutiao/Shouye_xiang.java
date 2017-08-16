package test.com.toutiao;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

/**
 * name:周振辉
 * 时间：2017/7/20 10:01
 * 类描述：
 */

public class Shouye_xiang extends AppCompatActivity{

    private WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.shouye_xiangqiang_xml);
        webView = (WebView)findViewById(R.id.webview);
        Intent intent = getIntent();
        String aa = intent.getStringExtra("uri");
        webView.loadUrl(aa);





    }
}
