package com.example.cangqiong.constant;

public class JwtClaims {

    public static final String EMP_ID = "empId";
    public static final String USER_ID = "userId";
    public static final String PHONE = "phone";
    public static final String USERNAME = "username";
    public static final String NAME = "name";

    public static final String KOKENKEY = "token:";


    //微信小程序用户唯一id
    public static final String OPEN_ID = "openId";
    //店铺标识
    public static final String STORE_ADMIN = "storeadmin:";


    //    微信官网 Official_website
    public static final String WX_OFFICIAL_WEBSITE = "https://api.weixin.qq.com/sns/jscode2session?appid=";

    //appId
    public static final String APP_ID = "wx144ddabd6d78c09d";

    //appSecret
    public static final String APP_SECRET = "77a71452b265f56982b01e2d091a9fc1";


    //完整请求路径

    /**
     * String url = "https://api.weixin.qq.com/sns/jscode2session?appid=" + appId +
     * "&secret=" + secret +
     * "&js_code=" + code +
     * "&grant_type=authorization_code";
     */
    public static String getRequestUrl(String code) {
        return WX_OFFICIAL_WEBSITE + APP_ID + "&secret=" + APP_SECRET + "&js_code=" + code + "&grant_type=authorization_code";
    }

}
