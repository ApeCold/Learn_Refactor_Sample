package cn.bsd.learn.refactor.sample;

import android.os.Handler;

import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OkHttpProcessor implements IHttpProcessor {
    private OkHttpClient mOkHttpClient;
    private Handler myHandler;
    public OkHttpProcessor(){
        mOkHttpClient = new OkHttpClient();
        myHandler = new Handler();
    }
    @Override
    public void post(String url, Map<String, Object> params, final ICallBack callback) {
        RequestBody requestBody = appendBody(params);
        Request request = new Request.Builder().url(url).post(requestBody).build();
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(response.isSuccessful()){
                    final String str = response.body().string();
                    myHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            callback.onSuccess(str);
                        }
                    });

                }
            }
        });
    }

    private RequestBody appendBody(Map<String,Object> params){
        FormBody.Builder body = new FormBody.Builder();
        if(params==null||params.isEmpty()){
            return body.build();
        }
        for(Map.Entry<String,Object> entry:params.entrySet()){
            body.add(entry.getKey(),entry.getValue().toString());
        }
        return body.build();
    }
}
