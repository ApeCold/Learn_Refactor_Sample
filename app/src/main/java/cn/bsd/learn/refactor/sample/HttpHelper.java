package cn.bsd.learn.refactor.sample;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

//房产公司的业务员
public class HttpHelper implements IHttpProcessor{
    private static HttpHelper instance;
    private HttpHelper(){}
    public static HttpHelper getInstance(){
        synchronized (HttpHelper.class){
            if(instance ==null){
                instance = new HttpHelper();
            }
        }
        return instance;
    }

    //需要持有一个有房的人，在本例中，相当于持有某个第三方网络框架
    private static IHttpProcessor mIHttpProcessor;
    public static void init(IHttpProcessor httpProcessor){
        mIHttpProcessor = httpProcessor;

        //init(买房人3)
    }

    @Override
    public void post(String url, Map<String, Object> params, ICallBack callback) {
        //post方法可以通用get方法
        String finalUrl = appendParams(url,params);
        mIHttpProcessor.post(finalUrl, params, callback);
    }

    private static String appendParams(String url, Map<String, Object> params) {
        if(params==null||params.isEmpty()){
            return url;
        }
        StringBuilder urlBuilder = new StringBuilder(url);
        if(url.indexOf("?")<=0){
            urlBuilder.append("?");
        }else{
            if(!urlBuilder.toString().endsWith("?")){
                urlBuilder.append("&");
            }
        }
        for(Map.Entry<String,Object> entry:params.entrySet()){
            urlBuilder.append("&"+entry.getKey()).append("=").append(encode(entry.getValue().toString()));
        }
        return urlBuilder.toString();
    }

    private static String encode(String str){
        try {
            return URLEncoder.encode(str,"utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
}
