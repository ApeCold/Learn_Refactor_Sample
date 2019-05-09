package cn.bsd.learn.refactor.sample;

import java.util.Map;

public interface IHttpProcessor {
    //该类相当于房产公司 拥有卖房能力，对应网络请求，具有网络访问能力

    void post(String url, Map<String, Object> params, ICallBack callback);

    //房产公司的业务员
}
