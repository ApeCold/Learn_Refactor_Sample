package cn.bsd.learn.refactor.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private String url= "http://v.juhe.cn/historyWeather/citys";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        new Request(url,parmas,new HttpCallBack(){
//            @Override
//            public void onSuccess(User user) {
//                super.onSuccess(result);
//            }
//        })
        Map<String,Object> params = new HashMap<>();
        params.put("province_id",1);
        HttpHelper.getInstance().post(url, params, new HttpCallBack<ResponseClass>() {
            @Override
            public void onSuccess(ResponseClass objResult) {
                Log.e("得到返回值：",objResult.toString());
            }
        });
    }
}
