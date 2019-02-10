package news.dvlp.dvlpokhttp;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

import news.dvlp.http.Callback.Call2;
import news.dvlp.http.Callback.CallbackAnim;
import news.dvlp.http.Callback.HttpError;
import news.dvlp.http.ControllerView.ILoadingView;
import news.dvlp.http.RequsetManager.CallManager;
import news.dvlp.http.RetrofitManager;


public class MainActivity extends Activity implements ILoadingView {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }






    public void articleBc(View view) {

        //构建入餐
        Map<String, String> paramMap = new HashMap();
        paramMap.put("ORG_ID", "11111");


        RetrofitManager.create(ApiService.class)
                .getBicai(paramMap)
                .enqueue(hashCode(), new CallbackAnim<String>(this) {
                    @Override
                    public void onError(Call2<String> call2, HttpError error) {
                        Toast.makeText(MainActivity.this, error.msg, Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onSuccess(Call2<String> call2, String response) {
//                        Article article= JSON.parseObject(response,Article.class);
                        Toast.makeText(MainActivity.this, "请求成功", Toast.LENGTH_SHORT).show();

                    }
                });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //hashCode() 能保证唯一性，取消当前页面所发起的所有请求，只要
        // enqueue(tag, callback2) 传入的是对应的hashCode() 即可
        CallManager.getInstance().cancel(hashCode());
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
}