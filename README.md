
![image](https://github.com/dvlp-org/DvlpTinker/blob/master/app/src/main/res/raw/banner.jpg?raw=true)

# DvlpOkHttp


retrofit2 极简封装，极简接入。

[ ![Download](https://api.bintray.com/packages/dvlp-org/MyMoven/dvlpokhttp/images/download.svg?version=1.0.2) ](https://bintray.com/dvlp-org/MyMoven/dvlpokhttp/1.0.2/link)
 [![](https://jitpack.io/v/dvlp-org/DvlpOkHttp.svg)](https://jitpack.io/#dvlp-org/DvlpOkHttp)


 范例请求URL
 
    http://47.94.110.156:9000/member/add
 
 对应json
 
    {
    "code":0,
    "msg":"SUCCESS",
    "data":{"ORG_ID":"11111"},
    "head":{"token":"token","appFlag":"BC"},
    "responseDate":"2019-02-10",
    "responseTime":"2019-02-10 21:45:10"
    }



 --------------------------------------------------------start------------------------------------------------------------------------
 
1.初始化配置

     加依赖项
 
      module 引用方式
     
    添加网路权限
 
    <uses-permission android:name="android.permission.INTERNET" />

    在Aplication进行初始化

     //替换比财 json解析格式
     RetrofitManager.initJSonTag("code","msg","data","0");
     //初始化
     RetrofitManager.init(Api.API_BASE);
     
     //。。。日志初始化请看源码
    
2.定义请求接口 ApiService
    
    //比财测试
    @POST(Api.GET_BICAI)
    Call2<String> getBicai(@Body Map<String, String> map);
    
 3.发起请求 
   
        public void articleBc(View view) {

        //构建参数
        Map<String, String> paramMap = new HashMap();
        paramMap.put("ORG_ID", "11111");


        RetrofitManager.create(ApiService.class)
                .getBicai(paramMap)
                .enqueue(hashCode(), new Callback2<String>() {
                    @Override
                    public void onError(Call2<String> call2, HttpError error) {
                        Toast.makeText(MainActivity.this, error.msg, Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onSuccess(Call2<String> call2, String response) {
                        Toast.makeText(MainActivity.this, "请求成功", Toast.LENGTH_SHORT).show();

                    }
                });
    }


      
------------------------------------------------end-------------------------------------------------------------------------------




**高度扩展说明*

1.三种初始化方式，以及使用条件

项目中默认顶级json key分别是：errorCode、errorMsg、data

   RetrofitManager.init(API_BASE); 

---
项目中默认顶级json 与默认不匹配：Code、Msg、dataS。“0”对应Code 成功状态

   RetrofitManager.initJSonTag("Code","Msg","dataS",0);
   RetrofitManager.init(API_BASE); 

---
库中封装Retrofit不满足需求使用，支持 Retrofit 自定义

   RetrofitManager.init(retrofit)



---
2.请求主体 CallbackAnim、Callback2

 CallbackAnim：支持获取请求Loding状态
 需要实现ILoadingView接口
 
    @Override
    public void showLoading() {
       // 显示loding布局
    }

    @Override
    public void hideLoading() {
       //隐藏loding布局
    }
    
 Callback2:不支持Loding获取状态           
 
3.data 支持类型 T

  对象数组
  
     Call2<List<WXArticle>> getArticle0();
  
  ---
  对象
  
     Call2<Article> getArticle0();
   
   ---
  文件
  
     Call2<File> getArticle0();
     
   ---  
  字符串
  
     Call2<String> getArticle0();

