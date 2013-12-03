package com.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class WqInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 176215806031754421L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column
	private int id;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Column
	private String wangwang;
	@Column
	private String createStoreTime;
	@Column
	private String stroeURL;
	@Column
	private String address;
	@Column
	private String storeLevel;
	@Column
	private String name;
	@Column
	private String imgURL;
	@Column
	private String sellCount;
	@Column
	private String price;
	@Column
	private Date insertTime;
	public Date getInsertTime() {
		return insertTime;
	}
	public void setInsertTime(Date insertTime) {
		this.insertTime = insertTime;
	}
	public String getWangwang() {
		return wangwang;
	}
	public void setWangwang(String wangwang) {
		this.wangwang = wangwang;
	}
	public String getCreateStoreTime() {
		return createStoreTime;
	}
	public void setCreateStoreTime(String createStoreTime) {
		this.createStoreTime = createStoreTime;
	}
	public String getStroeURL() {
		return stroeURL;
	}
	public void setStroeURL(String stroeURL) {
		this.stroeURL = stroeURL;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getStoreLevel() {
		return storeLevel;
	}
	public void setStoreLevel(String storeLevel) {
		this.storeLevel = storeLevel;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImgURL() {
		return imgURL;
	}
	public void setImgURL(String imgURL) {
		this.imgURL = imgURL;
	}
	public String getSellCount() {
		return sellCount;
	}
	public void setSellCount(String sellCount) {
		this.sellCount = sellCount;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
