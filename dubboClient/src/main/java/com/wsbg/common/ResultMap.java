package com.wsbg.common;

public class ResultMap {
     
	private boolean status;
	private String  msg;
	public ResultMap(boolean status, String msg) {
		super();
		this.status = status;
		this.msg = msg;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}
