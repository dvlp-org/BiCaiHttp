package news.dvlp.dvlpokhttp;


import java.util.Map;

import news.dvlp.http.Callback.Call2;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * 创建时间：2018/4/8
 * 编写人： karler
 * 功能描述：比财请求接口
 */
public interface ApiService {



    //比财测试
//    @FormUrlEncoded
    @POST(Api.GET_BICAI)
    Call2<String> getBicai(@Body Map<String, String> map);


}


