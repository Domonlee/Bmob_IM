package com.bmob.im.demo.util;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
	public static ArrayList<HashMap<String, String>> getAllFenLei(
			String jsonString) {
		ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		JSONArray contacts = null;

		if (jsonString != null) {
			try {
				JSONObject jsonObject = new JSONObject(jsonString);
				contacts = jsonObject.getJSONArray("umassage");

				for (int i = 0; i < contacts.length(); i++) {
					JSONObject c = contacts.getJSONObject(i);

					String id = c.getString(Constant.BASE_ID);
					String biaoti = c.getString(Constant.BASE_NAME);

					HashMap<String, String> contactHashMap = new HashMap<String, String>();

					contactHashMap.put(Constant.XIANSHI_ID, id);
					contactHashMap.put(Constant.XIANSHI_BIAOTI, biaoti);

					list.add(contactHashMap);
				}

			} catch (JSONException e) {
				e.printStackTrace();
			}

		}
		return list;
	}

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

					HashMap<String, String> contactHashMap = new HashMap<String, String>();

					contactHashMap.put(Constant.XIANSHI_ID, id);
					contactHashMap.put(Constant.XIANSHI_BIAOTI, biaoti);
					contactHashMap.put(Constant.XIANSHI_FBIAOTI, fbiaoti);
					contactHashMap.put(Constant.XIANSHI_PRICE, jiage);
					contactHashMap.put(Constant.XIANSHI_STARTTIME, starttime);
					contactHashMap.put(Constant.XIANSHI_STOPTIME, endtime);
					contactHashMap.put(Constant.XIANSHI_PWD, pwd);

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
				contacts = jsonObject.getJSONArray("umassage");

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
				Log.i("cheng", "jsonString" + jsonString);
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
					String xiaoliang = c.getString(Constant.TUANGOU_XIAOLIANG);
					String cjtg = c.getString(Constant.TUANGOU_CJTG);
					String cgqg = c.getString(Constant.TUANGOU_CGQG);
					String type = c.getString(Constant.TUANGOU_TYPE);

					HashMap<String, String> contactHashMap = new HashMap<String, String>();

					contactHashMap.put(Constant.TUANGOU_ID, id);
					contactHashMap.put(Constant.TUANGOU_NMAE, name);
					contactHashMap.put(Constant.TUANGOU_EPT, ept);
					contactHashMap.put(Constant.TUANGOU_PKC, pkc);
					contactHashMap.put(Constant.TUANGOU_IMG, img);
					contactHashMap.put(Constant.TUANGOU_PTEXT, text);
					contactHashMap.put(Constant.TUANGOU_PJIGE, jige);
					contactHashMap.put(Constant.TUANGOU_XIAOLIANG, xiaoliang);
					contactHashMap.put(Constant.TUANGOU_CJTG, cjtg);
					contactHashMap.put(Constant.TUANGOU_CGQG, cgqg);
					contactHashMap.put(Constant.TUANGOU_TYPE, type);

					list.add(contactHashMap);
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
	public static ArrayList<HashMap<String, String>> getMYDingDan(
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

					String id = c.getString(Constant.DINGDAN_ID);
					String name = c.getString(Constant.DINGDAN_SP);
					String biaoti = c.getString(Constant.DINGDAN_BIANTI);
					String fbiaoti = c.getString(Constant.DINGDAN_FBIAOTI);
					String count = c.getString(Constant.DINGDAN_COUNT);
					String pwd = c.getString(Constant.DINGDAN_PWD);
					String start = c.getString(Constant.DINGDAN_START);
					String end = c.getString(Constant.DINGDAN_END);

					HashMap<String, String> contactHashMap = new HashMap<String, String>();

					contactHashMap.put(Constant.TUANGOU_ID, id);
					contactHashMap.put(Constant.DINGDAN_SP, name);
					contactHashMap.put(Constant.DINGDAN_BIANTI, biaoti);
					contactHashMap.put(Constant.DINGDAN_FBIAOTI, fbiaoti);
					contactHashMap.put(Constant.DINGDAN_COUNT, count);
					contactHashMap.put(Constant.DINGDAN_PWD, pwd);
					contactHashMap.put(Constant.DINGDAN_START, start);
					contactHashMap.put(Constant.DINGDAN_END, end);

					list.add(contactHashMap);
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
}
