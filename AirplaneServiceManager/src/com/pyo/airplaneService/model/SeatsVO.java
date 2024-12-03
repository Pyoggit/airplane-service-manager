package com.pyo.airplaneService.model;

public class SeatsVO {
	private String no;
	private String planeNo ;
	private String rowx;
	private String coly;
	private String code;
	private int ynum;
	
	


	public SeatsVO(String rowx, String coly, String code, int ynum) {
		super();
		this.rowx = rowx;
		this.coly = coly;
		this.code = code;
		this.ynum = ynum;
	}


	public int getYnum() {
		return ynum;
	}


	public void setYnum(int ynum) {
		this.ynum = ynum;
	}


	public String getNo() {
		return no;
	}


	public void setNo(String no) {
		this.no = no;
	}


	public String getPlaneNo() {
		return planeNo;
	}


	public void setPlaneNo(String planeNo) {
		this.planeNo = planeNo;
	}


	public String getRowx() {
		return rowx;
	}


	public void setRowx(String rowx) {
		this.rowx = rowx;
	}


	public String getColy() {
		return coly;
	}


	public void setColy(String coly) {
		this.coly = coly;
	}


	public SeatsVO() {
		super();
	}


	public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
	}


	@Override
	public String toString() {
		String rt = null;
		if(code==null) {
			rt=rowx+coly;
		}else {
			rt="----";
		}
		return rt;
	}
	


}