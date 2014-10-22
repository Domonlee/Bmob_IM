package com.bmob.im.demo.bean;

import java.io.Serializable;

public class TuanGuangGao implements Serializable {
	private String id;
	private String eptname;
	private String spname;
	private String tgbiaoti;
	private String tgfubiaoti;
	private String yemian;
	private String tgid;
	private String posationid;
	private String tgimg;
	private String posationname;
	private String diznzhu;
	private String fenlei;
	

	public String getFenlei() {
		return fenlei;
	}

	public void setFenlei(String fenlei) {
		this.fenlei = fenlei;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEptname() {
		return eptname;
	}

	public void setEptname(String eptname) {
		this.eptname = eptname;
	}

	public String getSpname() {
		return spname;
	}

	public void setSpname(String spname) {
		this.spname = spname;
	}

	public String getTgbiaoti() {
		return tgbiaoti;
	}

	public void setTgbiaoti(String tgbiaoti) {
		this.tgbiaoti = tgbiaoti;
	}

	public String getTgfubiaoti() {
		return tgfubiaoti;
	}

	public void setTgfubiaoti(String tgfubiaoti) {
		this.tgfubiaoti = tgfubiaoti;
	}

	public String getYemian() {
		return yemian;
	}

	public void setYemian(String yemian) {
		this.yemian = yemian;
	}

	public String getTgid() {
		return tgid;
	}

	public void setTgid(String tgid) {
		this.tgid = tgid;
	}

	public String getPosationid() {
		return posationid;
	}

	public void setPosationid(String posationid) {
		this.posationid = posationid;
	}

	public String getTgimg() {
		return tgimg;
	}

	public void setTgimg(String tgimg) {
		this.tgimg = tgimg;
	}

	public String getPosationname() {
		return posationname;
	}

	public void setPosationname(String posationname) {
		this.posationname = posationname;
	}

	public String getDiznzhu() {
		return diznzhu;
	}

	public void setDiznzhu(String diznzhu) {
		this.diznzhu = diznzhu;
	}

	@Override
	public String toString() {
		return "TuanGuangGao [id=" + id + ", eptname=" + eptname + ", spname="
				+ spname + ", tgbiaoti=" + tgbiaoti + ", tgfubiaoti="
				+ tgfubiaoti + ", yemian=" + yemian + ", tgid=" + tgid
				+ ", posationid=" + posationid + ", tgimg=" + tgimg
				+ ", posationname=" + posationname + ", diznzhu=" + diznzhu
				+ "]";
	}

}
