package com.souleater.init;

/** 
 * 系统常量类 包含固定常量与可变常量
 * 其中可变常量 需要系统启动之后获取
 * @author love720720@163.com
 * @date 2017年4月31日 上午9:56:30
 */
public class Constants {

	public static String WEB_PATH;
	
	public static final String FILE_PATH = "temp";

	public static final String EMPTY = "";

	public static final String CHARSET = "UTF-8";
	
	public static final String WEB_USER_KEY = "WEB_EATER_LOGIN";
	public static final String APP_USER_KEY = "APP_EATER_LOGIN";
	
	public static final int MSG_EXP_CODE = 100;// 这个异常直接是提示级异常，具体的提示内容按异常的内容为准
	public static final int SUCCESS_CODE = 200;// 请求成功
	public static final int REPEAT_EXP_CODE = 201;// 数据已重复。
	public static final int PARAM_EXP_CODE = 400; // 请求的参数有问题，解析失败。
	public static final int FORBIDDEN_EXP_CODE = 403; // 禁止访问，由于权限不足
	public static final int TIMEOUT_EXP_CODE = 408; // 请求超时
	public static final int DELED_EXP_CODE = 410;// 请求的资源已被删除
	public static final int SYS_EXP_CODE = 500;// 内部错误 —因为意外情况，服务器不能完成请求。
	public static final String SUCCESS_MSG = "请求成功";
	public static final String FAILURE_MSG = "请求失败 请稍后再试";
	public static final String IP_EXP_MSG = "请求的IP不合法";
	
	public static final String FORMAT_YMD ="yyyy-MM-dd";
	public static final String FORMATYMD ="yyyyMMdd";
	public static final String FORMAT_YMDHMSMS ="yyyy-MM-dd HH:mm:ss";
	public static final String FORMATYMDHMSMS ="yyyyMMddHHmmssSSS";
	
	//短网址相关
	public static final String SHORT_API_URL = "http://suo.im/api.php";
	//短网址相关
	
	//二维码相关
	public static final String QR_CODE_SUFFIX = "jpg";
	public static final int QR_CODE_WIDTH = 100;
	public static final int QR_CODE_HEIGHT = QR_CODE_WIDTH;
	public static final int QR_CODE_SIZE = QR_CODE_WIDTH * 3;
	public static final String QR_CODE_PATH = "QRCode";
	public static final String QR_CODE_LOGO = "signature.jpg";
	//二维码相关
	
	//excel相关
	public static final String EXCEL_PATH = "excel";
	//excel相关
	
	//微信相关
	public static String WX_API;// 微信api
	public static String WX_APPID;// 第三方用户唯一凭证
	public static String WX_APPSECRET;// 第三方用户唯一凭证密钥
	public static String WX_ACCESSTOKEN;// 微信accessToken
	public static String WX_PAGE_ACCESSTOKEN;// 微信网页授权accessToken
	public static String WX_TICKET;// 微信jsSdk
	public static String WX_OAUTHBASEURL;// 微信静默授权地址
	public static String WX_OAUTHUSERINFOURL;// 微信授权地址
	//微信相关
}