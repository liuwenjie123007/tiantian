package com.msr.bean;

import java.io.Serializable;

public class Product implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String pid;
	private String pname;
	private double shop_price;
	private String pimage;
	private String pdate;
	private String pdesc;
	private String pflag;
	private String cid;

	public Product(String pid, String pname, double shop_price, String pimage, String pdate, String pdesc, String pflag,
			String cid) {
		super();
		this.pid = pid;
		this.pname = pname;
		this.shop_price = shop_price;
		this.pimage = pimage;
		this.pdate = pdate;
		this.pdesc = pdesc;
		this.pflag = pflag;
		this.cid = cid;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public double getShop_price() {
		return shop_price;
	}

	public void setShop_price(double shop_price) {
		this.shop_price = shop_price;
	}

	public String getPimage() {
		return pimage;
	}

	public void setPimage(String pimage) {
		this.pimage = pimage;
	}

	public String getPdate() {
		return pdate;
	}

	public void setPdate(String pdate) {
		this.pdate = pdate;
	}

	public String getPdesc() {
		return pdesc;
	}

	public void setPdesc(String pdesc) {
		this.pdesc = pdesc;
	}

	public String getPflag() {
		return pflag;
	}

	public void setPflag(String pflag) {
		this.pflag = pflag;
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

}
