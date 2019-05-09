package cn.bsd.learn.refactor.sample;

import com.google.gson.Gson;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public abstract class HttpCallBack<Result> implements ICallBack {
    @Override
    public void onSuccess(String result) {
        //将网络访问框架得到的数据转换成Json对象
        Gson gson = new Gson();
        //分析HttpCallBcak后面的泛型
        Class<?> clazz = analysisClassInfo(this);
        Result objResult = (Result) gson.fromJson(result,clazz);
        onSuccess(objResult);
    }

    private Class<?> analysisClassInfo(Object obj) {
        //相当于可以得到参数化类型，类型变量，基本类型
        Type genType = obj.getClass().getGenericSuperclass();
        Type[] actualType = ((ParameterizedType)genType).getActualTypeArguments();
        return (Class<?>) actualType[0];
    }

    public abstract void onSuccess(Result objResult);

    @Override
    public void onFailure() {

    }
}
