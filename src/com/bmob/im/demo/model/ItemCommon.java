package com.bmob.im.demo.model;

/**
 * show ItemCommon
 * @author Domon
 *
 */

public class ItemCommon {

	private Integer id;
	private String name;

	public ItemCommon() {
		super();
	}

	public ItemCommon(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String toString(Integer id, String name) {
		return "ItemModel [id = " + id + ",name = " + name + "]";

	}

}
