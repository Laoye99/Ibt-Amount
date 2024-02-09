package com.xadmin.ibtamt.bean;

public class User {
	private int acsn;
	private String fsp;
	private float lower_bound;
	private float upper_bound;
	
	public User(int acsn, String fsp, float lower_bound, float upper_bound) {
		super();
		this.acsn = acsn;
		this.fsp = fsp;
		this.lower_bound = lower_bound;
		this.upper_bound = upper_bound;
	}
	public User( String fsp, float lower_bound, float upper_bound) {
		super();
		this.fsp = fsp;
		this.lower_bound = lower_bound;
		this.upper_bound = upper_bound;
	}
	
	public int getAcsn() {
		return acsn;
	}
	public void setAcsn(int acsn) {
		this.acsn = acsn;
	}
	public String getFsp() {
		return fsp;
	}
	public void setFsp(String fsp) {
		this.fsp = fsp;
	}
	public float getLower_bound() {
		return lower_bound;
	}
	public void setLower_bound(float lower_bound) {
		this.lower_bound = lower_bound;
	}
	public float getUpper_bound() {
		return upper_bound;
	}
	public void setUpper_bound(float upper_bound) {
		this.upper_bound = upper_bound;
	}
	
}
