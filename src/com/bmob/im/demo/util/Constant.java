package com.bmob.im.demo.util;

import java.util.ArrayList;
import java.util.HashMap;

public class Constant {

	public static String API_KEY = "H1WWduPFjM1z6dqvTIyBrswb";

	public static String url = "http://api.androidhive.info/contacts/";
	public static final String TAG_CONTACTS = "contacts";
	public static final String TAG_ID = "id";
	public static final String TAG_NAME = "name";
	public static final String TAG_GENDER = "gender";
	public static final String TAG_EMAI = "emal";
	// 所有分类
	public static final String BASE_URL = "http://192.168.1.102:8080/hlf_spapp/check!androidchecks";
	public static final String BASE_ID = "id";
	public static final String BASE_NAME = "spfenleiname";
	// 限时购
	public static final String XIANSHIQIANGGOU = "http://sp.woaisp.com/hlf_spapp/check!androidqg?page=1&rows=10";
	public static final String XIANSHI_ID = "id";
	public static final String XIANSHI_BIAOTI = "tgbiaoti";
	public static final String XIANSHI_FBIAOTI = "tgfubiaoti";
	public static final String XIANSHI_STARTTIME = "tgkaishi";
	public static final String XIANSHI_STOPTIME = "tgend";
	public static final String XIANSHI_PRICE = "tgjiage";
	public static final String XIANSHI_PWD = "tgpwd";

	// 积分兑换
	public static final String JIFENDUIHUAN_URL = "http://sp.woaisp.com/hlf_spapp/check!androidscore?page=1&rows=10&order=asc";
	public static final String JIFEN_ID = "id";
	public static final String JIFEN_NAME = "spname";
	public static final String JIFEN_IMAGE = "spimg";
	public static final String JIFEN_NUMBER = "spnumber";
	public static final String JIFEN_SCORE = "spscore";
	// 团购
	public static final String TUANGOU_BASE_URL = "http://sp.woaisp.com/hlf_spapp/check!androidfood?";
	public static final String TUANGOU_URL = "http://sp.woaisp.com/hlf_spapp/check!androidfood?";
	public static final String TUANGOU_ID = "id";
	public static final String TUANGOU_EPT = "ept";
	public static final String TUANGOU_NMAE = "spname";
	public static final String TUANGOU_PKC = "sahngpinkucui";
	public static final String TUANGOU_IMG = "shangpinimg";
	public static final String TUANGOU_PTEXT = "shangpintext";
	public static final String TUANGOU_PJIGE = "shangpinjige";
	public static final String TUANGOU_XIAOLIANG = "shangpinxiaoliang";
	public static final String TUANGOU_CJTG = "cjtg";
	public static final String TUANGOU_CGQG = "cjqg";
	public static final String TUANGOU_TYPE = "spfenlei";

	public static final int TUANGOU_TYPE_FOOD = 1;
	public static final int TUANGOU_TYPE_MEIRONG = 2;
	public static final int TUANGOU_TYPE_YULE = 3;
	public static final int TUANGOU_TYPE_SHEYING = 4;
	public static final int TUANGOU_TYPE_JIUDIAN = 5;
	public static final int TUANGOU_TYPE_QITA = 7;
	public static ArrayList<HashMap<String, String>> foodlist = new ArrayList<HashMap<String, String>>();
	public static ArrayList<HashMap<String, String>> meilist = new ArrayList<HashMap<String, String>>();
	public static ArrayList<HashMap<String, String>> yulist = new ArrayList<HashMap<String, String>>();
	public static ArrayList<HashMap<String, String>> shelist = new ArrayList<HashMap<String, String>>();
	public static ArrayList<HashMap<String, String>> jiulist = new ArrayList<HashMap<String, String>>();
	public static ArrayList<HashMap<String, String>> qilist = new ArrayList<HashMap<String, String>>();

	// 我的订单
	public static final String DINGDAN_URL = "http://sp.woaisp.com/hlf_spapp/check!androidtg?page=1&rows=10";
	public static final String DINGDAN_ID = "id";
	public static final String DINGDAN_SP = "sp";
	public static final String DINGDAN_BIANTI = "tgbiaoti";
	public static final String DINGDAN_FBIAOTI = "tgfubiaoti";
	public static final String DINGDAN_COUNT = "tgjiage";
	public static final String DINGDAN_PWD = "tgpwd";
	public static final String DINGDAN_START = "tgkaishi";
	public static final String DINGDAN_END = "tgend";

	// 我的兑换
	public static final String MYDUIHUAN_URL = "http://192.168.0.166:8080/hlf_spapp/check!androiduserchange?";

	// 我的团购卷
	public static final String MYTUANGOUJUAN_URL = "http://192.168.0.166:8080/hlf_spapp/check!androidmytg?";
	public static final String MYTUANGOUJUAN_ID = "id";
	public static final String MYTUANGOUJUAN_STATUS = "status";
	public static final String MYTUANGOUJUAN_NAME = "spname";
	public static final String MYTUANGOUJUAN_BIAOTI = "tgbiaoti";
	public static final String MYTUANGOUJUAN_PWD = "tgpwd";
	public static final String MYTUANGOUJUAN_IMG = "spimg";
	public static final String MYTUANGOUJUAN_TG = "tg";
	public static final String MYTUANGOUJUAN_TIME = "valuetime";
	public static final String MYTUANGOUJUAN_MOVEUSER = "moveuser";
	public static final String MYTUANGOUJUAN_NUMBER = "number1";
	public static final String MYTUANGOUJUAN_SPPRICE = "spprice";
	public static final String MYTUANGOUJUAN_TGPRICE = "tgprice";
	public static final String MYTUANGOUJUAN_TEXT = "sptext";
	public static final String MYTUANGOUJUAN_DIANPU = "spdianpu";
	public static final String MYTUANGOUJUAN_FBIAOTI = "fubiaoti";

	public static int paixu_type = 0;
	// 经纬度

	public static double JINGDU = 0;
	public static double WEIDU = 0;
	// 范围
	public static final String FANWEI_BASEURL = "http://sp.woaisp.com/hlf_spapp/check!androidsearceh?";

}
