package cn.bsd.learn.refactor.sample;

import android.app.Application;

public class NeRefactorApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
//        HttpHelper.init(new VolleyProcessor(this));
        HttpHelper.init(new OkHttpProcessor());
    }
}
