package com.bmob.im.demo.util;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.bmob.im.demo.bean.MyDianDan;
import com.bmob.im.demo.bean.TuanGuangGao;

import android.util.Log;

public class JSONUtil {
	public static ArrayList<HashMap<String, String>> getData(String jsonString) {
		ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		JSONArray contacts = null;

		if (jsonString != null) {
			try {
				JSONObject jsonObject = new JSONObject(jsonString);
				contacts = jsonObject.getJSONArray(Constant.TAG_CONTACTS);

				for (int i = 0; i < contacts.length(); i++) {
					JSONObject c = contacts.getJSONObject(i);

					String id = c.getString(Constant.TAG_ID);
					String name = c.getString(Constant.TAG_NAME);
					String email = c.getString(Constant.TAG_GENDER);

					HashMap<String, String> contactHashMap = new HashMap<String, String>();

					contactHashMap.put(Constant.TAG_ID, id);
					contactHashMap.put(Constant.TAG_NAME, name);
					contactHashMap.put(Constant.TAG_GENDER, email);
					list.add(contactHashMap);
				}

			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	/**
	 * 获得所有分类
	 * 
	 * @param jsonString
	 * @return
	 */
	// public static ArrayList<HashMap<String, String>> getAllFenLei(
	// String jsonString) {
	// ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String,
	// String>>();
	// JSONArray contacts = null;
	//
	// if (jsonString != null) {
	// try {
	// JSONObject jsonObject = new JSONObject(jsonString);
	// contacts = jsonObject.getJSONArray("umassage");
	//
	// for (int i = 0; i < contacts.length(); i++) {
	// JSONObject c = contacts.getJSONObject(i);
	//
	// String id = c.getString(Constant.BASE_ID);
	// String biaoti = c.getString(Constant.BASE_NAME);
	//
	// HashMap<String, String> contactHashMap = new HashMap<String, String>();
	//
	// contactHashMap.put(Constant.XIANSHI_ID, id);
	// contactHashMap.put(Constant.XIANSHI_BIAOTI, biaoti);
	//
	// list.add(contactHashMap);
	// }
	//
	// } catch (JSONException e) {
	// e.printStackTrace();
	// }
	//
	// }
	// return list;
	// }

	/**
	 * 解析限时购
	 * 
	 * @param jsonString
	 * @return
	 */
	public static ArrayList<HashMap<String, String>> getXianShiGou(
			String jsonString) {
		ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		JSONArray contacts = null;

		if (jsonString != null) {
			try {
				JSONObject jsonObject = new JSONObject(jsonString);
				contacts = jsonObject.getJSONArray("umassage");

				for (int i = 0; i < contacts.length(); i++) {
					JSONObject c = contacts.getJSONObject(i);

					String id = c.getString(Constant.XIANSHI_ID);
					String biaoti = c.getString(Constant.XIANSHI_BIAOTI);
					String fbiaoti = c.getString(Constant.XIANSHI_FBIAOTI);
					String jiage = c.getString(Constant.XIANSHI_PRICE);
					String starttime = c.getString(Constant.XIANSHI_STARTTIME);
					String endtime = c.getString(Constant.XIANSHI_STOPTIME);
					String pwd = c.getString(Constant.XIANSHI_PWD);
					String img = c.getString(Constant.XIANSHI_IMG);

					HashMap<String, String> contactHashMap = new HashMap<String, String>();

					contactHashMap.put(Constant.XIANSHI_ID, id);
					contactHashMap.put(Constant.XIANSHI_BIAOTI, biaoti);
					contactHashMap.put(Constant.XIANSHI_FBIAOTI, fbiaoti);
					contactHashMap.put(Constant.XIANSHI_PRICE, jiage);
					contactHashMap.put(Constant.XIANSHI_STARTTIME, starttime);
					contactHashMap.put(Constant.XIANSHI_STOPTIME, endtime);
					contactHashMap.put(Constant.XIANSHI_PWD, pwd);
					contactHashMap.put(Constant.XIANSHI_IMG, img);

					list.add(contactHashMap);
				}

			} catch (JSONException e) {
				e.printStackTrace();
			}

		}
		return list;
	}

	/**
	 * 解析积分兑换
	 * 
	 * @param jsonString
	 * @return
	 */
	public static ArrayList<HashMap<String, String>> getJiFen(String jsonString) {
		ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		JSONArray contacts = null;
		Log.i("cheng", "jsonString" + jsonString);
		if (jsonString != null) {
			try {
				JSONObject jsonObject = new JSONObject(jsonString);
				contacts = jsonObject.getJSONArray("umassage1");

				for (int i = 0; i < contacts.length(); i++) {
					JSONObject c = contacts.getJSONObject(i);

					String id = c.getString(Constant.JIFEN_ID);
					String name = c.getString(Constant.JIFEN_NAME);
					String img = c.getString(Constant.JIFEN_IMAGE);
					String number = c.getString(Constant.JIFEN_NUMBER);
					String score = c.getString(Constant.JIFEN_SCORE);

					HashMap<String, String> contactHashMap = new HashMap<String, String>();

					contactHashMap.put(Constant.JIFEN_ID, id);
					contactHashMap.put(Constant.JIFEN_NAME, name);
					contactHashMap.put(Constant.JIFEN_IMAGE, img);
					contactHashMap.put(Constant.JIFEN_NUMBER, number);
					contactHashMap.put(Constant.JIFEN_SCORE, score);

					list.add(contactHashMap);
				}

			} catch (JSONException e) {
				e.printStackTrace();
			}

		}
		return list;
	}

	/**
	 * 解析积分兑换 广告
	 * 
	 * @param jsonString
	 * @return
	 */
	public static ArrayList<HashMap<String, String>> getJiFenGuanggao(
			String jsonString) {
		ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		JSONArray contacts = null;
		Log.i("cheng", "jsonString" + jsonString);
		if (jsonString != null) {
			try {
				JSONObject jsonObject = new JSONObject(jsonString);
				contacts = jsonObject.getJSONArray("tglist");

				for (int i = 0; i < contacts.length(); i++) {
					JSONObject c = contacts.getJSONObject(i);

					String id = c.getString(Constant.JIFEN_ID);
					String name = c.getString(Constant.JIFEN_NAME);
					String img = c.getString(Constant.JIFEN_IMAGE);
					String position = c.getString(Constant.JIFEN_POSITION);
					String score = c.getString(Constant.JIFEN_SCORE);
					String title = c.getString(Constant.JIFEN_TITLE);

					HashMap<String, String> contactHashMap = new HashMap<String, String>();

					contactHashMap.put(Constant.JIFEN_ID, id);
					contactHashMap.put(Constant.JIFEN_NAME, name);
					contactHashMap.put(Constant.JIFEN_IMAGE, img);
					contactHashMap.put(Constant.JIFEN_POSITION, position);
					contactHashMap.put(Constant.JIFEN_TITLE, title);
					contactHashMap.put(Constant.JIFEN_SCORE, score);

					list.add(contactHashMap);
				}

			} catch (JSONException e) {
				e.printStackTrace();
			}

		}
		return list;
	}

	/**
	 * 团购
	 * 
	 * @param jsonString
	 * @return
	 */
	public static ArrayList<HashMap<String, String>> getTuanGou(
			String jsonString) {
		ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		JSONArray contacts = null;
		if (jsonString != null) {
			try {
				JSONObject jsonObject = new JSONObject(jsonString);
				contacts = jsonObject.getJSONArray("umassage");

				for (int i = 0; i < contacts.length(); i++) {
					JSONObject c = contacts.getJSONObject(i);

					String id = c.getString(Constant.TUANGOU_ID);
					String name = c.getString(Constant.TUANGOU_NMAE);
					String ept = c.getString(Constant.TUANGOU_EPT);
					String pkc = c.getString(Constant.TUANGOU_PKC);
					String img = c.getString(Constant.TUANGOU_IMG);
					String text = c.getString(Constant.TUANGOU_PTEXT);
					String jige = c.getString(Constant.TUANGOU_PJIGE);
					String xiaoliang1 = c.getString(Constant.TUANGOU_XIAOLIANG);
					String cjtg = c.getString(Constant.TUANGOU_CJTG);
					String cgqg = c.getString(Constant.TUANGOU_CGQG);
					String type = c.getString(Constant.TUANGOU_TYPE);
					String kucun = c.getString(Constant.TUANGOU_KUNCUN);
					String xiaoliang = c.getString(Constant.TUANGOU_XIANGLIANG);

					HashMap<String, String> contactHashMap = new HashMap<String, String>();

					contactHashMap.put(Constant.TUANGOU_ID, id);
					contactHashMap.put(Constant.TUANGOU_NMAE, name);
					contactHashMap.put(Constant.TUANGOU_EPT, ept);
					contactHashMap.put(Constant.TUANGOU_PKC, pkc);
					contactHashMap.put(Constant.TUANGOU_IMG, img);
					contactHashMap.put(Constant.TUANGOU_PTEXT, text);
					contactHashMap.put(Constant.TUANGOU_PJIGE, jige);
					contactHashMap.put(Constant.TUANGOU_XIAOLIANG, xiaoliang1);
					contactHashMap.put(Constant.TUANGOU_CJTG, cjtg);
					contactHashMap.put(Constant.TUANGOU_CGQG, cgqg);
					contactHashMap.put(Constant.TUANGOU_TYPE, type);
					contactHashMap.put(Constant.TUANGOU_KUNCUN, kucun);
					contactHashMap.put(Constant.TUANGOU_XIANGLIANG, xiaoliang);

					list.add(contactHashMap);
				}

			} catch (JSONException e) {
				e.printStackTrace();
			}

		}
		return list;
	}

	/**
	 * 解析团购 广告
	 * 
	 * @param jsonString
	 * @return
	 */
	public static ArrayList<TuanGuangGao> getZhuTuanGouGuanggao(
			String jsonString) {
		ArrayList<TuanGuangGao> list = new ArrayList<TuanGuangGao>();
		JSONArray contacts = null;
		Log.i("cheng", "jsonString" + jsonString);
		if (jsonString != null) {
			try {
				JSONObject jsonObject = new JSONObject(jsonString);
				contacts = jsonObject.getJSONArray("umassage");

				for (int i = 0; i < contacts.length(); i++) {
					JSONObject c = contacts.getJSONObject(i);

					String id = c.getString(Constant.TUANGOU_ID);
					String eptname = c.getString(Constant.TUANGOU_EPTNAME);
					String img = c.getString(Constant.TUANGOU_TGIMG);
					String positionname = c
							.getString(Constant.TUANGOU_POSITIONNAME);
					String positionid = c
							.getString(Constant.TUANGOU_POSITIONID);
					String spname = c.getString(Constant.TUANGOU_NMAE);
					String biaoti = c.getString(Constant.TUANGOU_TGBIAOTI);
					String fbiaoti = c.getString(Constant.TUANGOU_TGFUBIAOTI);
					String yemian = c.getString(Constant.TUANGOU_YEMIAN);
					String tgid = c.getString(Constant.TUANGOU_TGID);
					String dianzhu = c.getString(Constant.TUANGOU_DIANZHU);
					TuanGuangGao g = new TuanGuangGao();

					g.setId(id);
					g.setEptname(eptname);
					g.setDiznzhu(dianzhu);
					g.setPosationname(positionname);
					g.setTgbiaoti(biaoti);
					g.setTgfubiaoti(fbiaoti);
					g.setTgid(tgid);
					g.setTgimg(img);
					g.setYemian(yemian);
					g.setSpname(spname);
					g.setPosationid(positionid);
					list.add(g);
				}

			} catch (JSONException e) {
				e.printStackTrace();
			}

		}
		return list;
	}

	/**
	 * 解析团购 广告
	 * 
	 * @param jsonString
	 * @return
	 */
	public static ArrayList<TuanGuangGao> getTuanGouGuanggao(String jsonString) {
		ArrayList<TuanGuangGao> list = new ArrayList<TuanGuangGao>();
		JSONArray contacts = null;
		Log.i("cheng", "jsonString" + jsonString);
		if (jsonString != null) {
			try {
				JSONObject jsonObject = new JSONObject(jsonString);
				contacts = jsonObject.getJSONArray("tglist");

				for (int i = 0; i < contacts.length(); i++) {
					JSONObject c = contacts.getJSONObject(i);

					String id = c.getString(Constant.TUANGOU_ID);
					String eptname = c.getString(Constant.TUANGOU_EPTNAME);
					String img = c.getString(Constant.TUANGOU_TGIMG);

					String positionname = c
							.getString(Constant.TUANGOU_POSITIONNAME);
					String positionid = c
							.getString(Constant.TUANGOU_POSITIONID);
					String spname = c.getString(Constant.TUANGOU_NMAE);
					String biaoti = c.getString(Constant.TUANGOU_TGBIAOTI);
					String fbiaoti = c.getString(Constant.TUANGOU_TGFUBIAOTI);
					String yemian = c.getString(Constant.TUANGOU_YEMIAN);
					String tgid = c.getString(Constant.TUANGOU_TGID);
					String dianzhu = c.getString(Constant.TUANGOU_DIANZHU);
					String fenlei = c.getString(Constant.TUANGOU_FEILEI);

					TuanGuangGao g = new TuanGuangGao();

					g.setId(id);
					g.setEptname(eptname);
					g.setDiznzhu(dianzhu);
					g.setPosationname(positionname);
					g.setTgbiaoti(biaoti);
					g.setTgfubiaoti(fbiaoti);
					g.setTgid(tgid);
					g.setTgimg(img);
					g.setYemian(yemian);
					g.setSpname(spname);
					g.setPosationid(positionid);
					g.setFenlei(fenlei);
					list.add(g);
				}

			} catch (JSONException e) {
				e.printStackTrace();
			}

		}
		return list;
	}

	/**
	 * 我的订单
	 * 
	 * @param jsonString
	 * @return
	 */
	public static ArrayList<MyDianDan> getMYDingDan(String jsonString) {
		ArrayList<MyDianDan> list = new ArrayList<MyDianDan>();
		JSONArray contacts = null;
		MyDianDan dan = null;
		Log.i("cheng", "jsonString" + jsonString);
		if (jsonString != null) {
			try {
				JSONObject jsonObject = new JSONObject(jsonString);
				contacts = jsonObject.getJSONArray("umassage");

				for (int i = 0; i < contacts.length(); i++) {
					JSONObject c = contacts.getJSONObject(i);
					dan = new MyDianDan();
					dan.setId(c.getString(Constant.DINGDAN_ID));
					dan.setSpname(c.getString(Constant.DINGDAN_SP));
					dan.setTgbiaoti(c.getString(Constant.DINGDAN_BIANTI));
					dan.setSpimg(c.getString(Constant.DINGDAN_IMG));
					dan.setValuetime(c.getString(Constant.DINGDAN_TIME));
					dan.setMoveuser(c.getString(Constant.DINGDAN_MOVERUSER));
					dan.setNumber2(c.getString(Constant.DINGDAN_NUMBER2));
					dan.setOrdermethod(c
							.getString(Constant.DINGDAN_ORDERMETHOD));
					dan.setOrdernum(c.getString(Constant.DINGDAN_ORDNUM));

					dan.setOrdertime(c.getString(Constant.DINGDAN_ORDTIME));
					dan.setRemark(c.getString(Constant.DINGDAN_REMARK));
					dan.setSpnumber(c.getString(Constant.DINGDAN_SPNAMBER));
					dan.setSpprice(c.getString(Constant.DINGDAN_SPPRICE));
					dan.setNumber3(c.getString(Constant.DINGDAN_NUMBER3));
					dan.setTgprice(c.getString(Constant.DINGDAN_TGPRICE));
					dan.setSptext(c.getString(Constant.DINGDAN_SPTEXT));
					dan.setSpdianpu(c.getString(Constant.DINGDAN_SPDIANPU));
					dan.setZongjia(c.getString(Constant.DINGDAN_ZONGJIA));
					dan.setOrderphone(c.getString(Constant.DINGDAN_ORDERPHONE));
					dan.setFubiaoti(c.getString(Constant.DINGDAN_FBIAOTI));

					list.add(dan);
				}

			} catch (JSONException e) {
				e.printStackTrace();
			}

		}
		return list;
	}

	/**
	 * 我的团购劵
	 * 
	 * @param jsonString
	 * @return
	 */
	public static ArrayList<HashMap<String, String>> getMYTuanGouJuan(
			String jsonString) {
		ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		JSONArray contacts = null;
		Log.i("cheng", "jsonString" + jsonString);
		if (jsonString != null) {
			try {
				JSONObject jsonObject = new JSONObject(jsonString);
				contacts = jsonObject.getJSONArray("umassage");

				for (int i = 0; i < contacts.length(); i++) {
					JSONObject c = contacts.getJSONObject(i);

					String id = c.getString(Constant.MYTUANGOUJUAN_ID);
					String name = c.getString(Constant.MYTUANGOUJUAN_NAME);
					String biaoti = c.getString(Constant.MYTUANGOUJUAN_BIAOTI);
					String fbiaoti = c
							.getString(Constant.MYTUANGOUJUAN_FBIAOTI);
					String pwd = c.getString(Constant.MYTUANGOUJUAN_PWD);
					String time = c.getString(Constant.MYTUANGOUJUAN_TIME);

					String img = c.getString(Constant.MYTUANGOUJUAN_IMG);
					String tg = c.getString(Constant.MYTUANGOUJUAN_TG);
					String user = c.getString(Constant.MYTUANGOUJUAN_MOVEUSER);
					String number = c.getString(Constant.MYTUANGOUJUAN_NUMBER);
					String yprice = c.getString(Constant.MYTUANGOUJUAN_SPPRICE);
					String price = c.getString(Constant.MYTUANGOUJUAN_TGPRICE);
					String text = c.getString(Constant.MYTUANGOUJUAN_TEXT);
					String dianpu = c.getString(Constant.MYTUANGOUJUAN_DIANPU);
					String status = c.getString(Constant.MYTUANGOUJUAN_STATUS);

					HashMap<String, String> contactHashMap = new HashMap<String, String>();

					contactHashMap.put(Constant.MYTUANGOUJUAN_ID, id);
					contactHashMap.put(Constant.MYTUANGOUJUAN_NAME, name);
					contactHashMap.put(Constant.MYTUANGOUJUAN_BIAOTI, biaoti);
					contactHashMap.put(Constant.MYTUANGOUJUAN_FBIAOTI, fbiaoti);
					contactHashMap.put(Constant.MYTUANGOUJUAN_PWD, pwd);
					contactHashMap.put(Constant.MYTUANGOUJUAN_TIME, time);
					contactHashMap.put(Constant.MYTUANGOUJUAN_IMG, img);
					contactHashMap.put(Constant.MYTUANGOUJUAN_TG, tg);
					contactHashMap.put(Constant.MYTUANGOUJUAN_MOVEUSER, user);
					contactHashMap.put(Constant.MYTUANGOUJUAN_NUMBER, number);
					contactHashMap.put(Constant.MYTUANGOUJUAN_SPPRICE, yprice);
					contactHashMap.put(Constant.MYTUANGOUJUAN_TGPRICE, price);
					contactHashMap.put(Constant.MYTUANGOUJUAN_TEXT, text);
					contactHashMap.put(Constant.MYTUANGOUJUAN_DIANPU, dianpu);
					contactHashMap.put(Constant.MYTUANGOUJUAN_STATUS, status);

					list.add(contactHashMap);
				}

			} catch (JSONException e) {
				e.printStackTrace();
			}

		}
		return list;
	}

	/**
	 * 我的答题
	 * 
	 * @param jsonString
	 * @return
	 */
	public static ArrayList<HashMap<String, String>> getDaTi(String jsonString) {
		ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		JSONArray contacts = null;
		if (jsonString != null) {
			try {
				JSONObject jsonObject = new JSONObject(jsonString);
				contacts = jsonObject.getJSONArray("umassage");

				for (int i = 0; i < contacts.length(); i++) {
					JSONObject c = contacts.getJSONObject(i);

					String id = c.getString(Constant.DATI_ID);
					String question = c.getString(Constant.DATI_QUESTION);
					String anwer1 = c.getString(Constant.DATI_ANSWER1);
					String anwer2 = c.getString(Constant.DATI_ANSWER2);
					String anwer3 = c.getString(Constant.DATI_ANSWER3);
					String anwer4 = c.getString(Constant.DATI_ANSWER4);

					HashMap<String, String> contactHashMap = new HashMap<String, String>();

					contactHashMap.put(Constant.DATI_ID, id);
					contactHashMap.put(Constant.DATI_QUESTION, question);
					contactHashMap.put(Constant.DATI_ANSWER1, anwer1);
					contactHashMap.put(Constant.DATI_ANSWER2, anwer2);
					contactHashMap.put(Constant.DATI_ANSWER3, anwer3);
					contactHashMap.put(Constant.DATI_ANSWER4, anwer4);

					list.add(contactHashMap);
				}

			} catch (JSONException e) {
				e.printStackTrace();
			}

		}
		return list;
	}

	/**
	 * 城市
	 * 
	 * @param jsonString
	 * @return
	 */
	public static ArrayList<HashMap<String, String>> getCity(String jsonString) {
		ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		JSONArray contacts = null;
		if (jsonString != null) {
			try {
				JSONObject jsonObject = new JSONObject(jsonString);
				contacts = jsonObject.getJSONArray("umassage");

				for (int i = 0; i < contacts.length(); i++) {
					JSONObject c = contacts.getJSONObject(i);

					String id = c.getString(Constant.CITY_ID);
					String qu_id = c.getString(Constant.CITY_DIQUID);
					String name = c.getString(Constant.CITY_QUYUNAME);
					String updata = c.getString(Constant.CITY_UPDATA);
					String create = c.getString(Constant.CITY_CREATED);

					HashMap<String, String> contactHashMap = new HashMap<String, String>();

					contactHashMap.put(Constant.CITY_ID, id);
					contactHashMap.put(Constant.CITY_DIQUID, qu_id);
					contactHashMap.put(Constant.CITY_QUYUNAME, name);
					contactHashMap.put(Constant.CITY_UPDATA, updata);
					contactHashMap.put(Constant.CITY_CREATED, create);

					list.add(contactHashMap);
				}

			} catch (JSONException e) {
				e.printStackTrace();
			}

		}
		return list;
	}

}
