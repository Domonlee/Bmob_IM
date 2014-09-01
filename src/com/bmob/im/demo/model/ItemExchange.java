package com.bmob.im.demo.model;

/**
 * show ItemSetting
 * 
 * @author Domon
 * 
 */
public class ItemExchange {
	private Integer picid;
	private String name;
	private String info;

	public ItemExchange() {
		super();
	}

	public ItemExchange(Integer id, String name, String info) {
		super();
		this.picid = id;
		this.name = name;
		this.info = info;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPicid() {
		return picid;
	}

	public void setPicid(Integer picid) {
		this.picid = picid;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String toString(Integer id, String name, String info) {
		return "ItemModel [id" + id + ",name=" + name + ",info=" + info + "]";
	}

}
