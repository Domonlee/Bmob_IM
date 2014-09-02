package com.bmob.im.demo.bean;

import cn.bmob.im.bean.BmobChatUser;
import cn.bmob.v3.datatype.BmobGeoPoint;

/** ����BmobChatUser����������������Ҫ���ӵ����Կ��ڴ����
  * @ClassName: TextUser
  * @Description: TODO
  * @author Domon
  * @date 2014-5-29 ����6:15:45
  */
public class User extends BmobChatUser {

	private static final long serialVersionUID = 1L;
	/**
	 * //��ʾ����ƴ��������ĸ
	 */
	private String sortLetters;
	
	/**
	 * //�Ա�-true-��
	 */
	private boolean sex;
	
	private String userAddr;
	private String userBir;
	private String userSendAddr;
	private String userMail;
	private String userTele;
	private String psw;
	
 
	public String getUserAddr() {
		return userAddr;
	}
	public void setUserAddr(String userAddr) {
		this.userAddr = userAddr;
	}
	public String getUserBir() {
		return userBir;
	}
	public void setUserBir(String userBir) {
		this.userBir = userBir;
	}
	public String getUserSendAddr() {
		return userSendAddr;
	}
	public void setUserSendAddr(String userSendAddr) {
		this.userSendAddr = userSendAddr;
	}
	public String getUserMail() {
		return userMail;
	}
	public void setUserMail(String userMail) {
		this.userMail = userMail;
	}
	public String getPsw() {
		return psw;
	}
	public void setPsw(String psw) {
		this.psw = psw;
	}
	/**
	 * ��������
	 */
	private BmobGeoPoint location;//
	
	
	public BmobGeoPoint getLocation() {
		return location;
	}
	public void setLocation(BmobGeoPoint location) {
		this.location = location;
	}
	public boolean getSex() {
		return sex;
	}
	public void setSex(boolean sex) {
		this.sex = sex;
	}
	public String getSortLetters() {
		return sortLetters;
	}
	public void setSortLetters(String sortLetters) {
		this.sortLetters = sortLetters;
	}
	public String getUserTele() {
		return userTele;
	}
	public void setUserTele(String userTele) {
		this.userTele = userTele;
	}
	
}
