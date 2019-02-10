package news.dvlp.http.Interceptor;

import com.alibaba.fastjson.JSON;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import news.dvlp.http.ConfigHttp.ConfigHttps;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;

/**
 * Created by karler on 2019/1/29.
 */

/**
 * Retrofit 基本参数拦截器
 */
public class DvlpInterceptor implements Interceptor {


    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originalRequest = chain.request();

        ConfigHttps.headParam=getBaseParams();

        if(ConfigHttps.headParam.isEmpty()){
            return chain.proceed(originalRequest);
        }
        //组装参数
        String bodyStr="{" +
                "\"head\":" + JSON.toJSONString(ConfigHttps.headParam)+
                ",\"param\":" +bodyToString(originalRequest.body())+
                "}";

        ConfigHttps.bodyParam=bodyStr;
        Request.Builder requestBuilder  = originalRequest.newBuilder();
        originalRequest = requestBuilder.addHeader("Content-Type", "application/json;charset=UTF-8")
                .post(RequestBody.create(MediaType.parse("application/json;charset=UTF-8"),
                        bodyStr
                ))
                .build();
        return chain.proceed(originalRequest);
    }

    //设置requestBody的编码格式为json
    private static String bodyToString(final RequestBody request) {
        try {
            final RequestBody copy = request;
            final Buffer buffer = new Buffer();
            if (copy != null)
                copy.writeTo(buffer);
            else
                return "";
            return buffer.readUtf8();
        } catch (final IOException e) {
            return "did not work";
        }
    }


    //此方法替换比财 获取头部信息的方法
    private static Map<String, Object> getBaseParams() {

        // 拼接参数
        Map<String, Object> headMap = new HashMap();
        headMap.put("appFlag", "APP_FLAG");
        headMap.put("channel", "APP_FLAG");
        headMap.put("channelId", "10");
        headMap.put("clientId", "clientId");
        headMap.put("deviceId", "clientId");
        headMap.put("deviceName", "clientId");
        headMap.put("orgId", "orgId");
        String version = "clientId";
        version = version.replace("v", "");//和ios保持统一，去掉V
        headMap.put("version", version);
        headMap.put("systemType", "systemType");
        headMap.put("token", "token");

        return headMap;
    }

}
