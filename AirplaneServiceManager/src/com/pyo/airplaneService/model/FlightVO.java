package com.pyo.airplaneService.model;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class FlightVO {
	private String no;
	private String planeNo;
	private String departureCountryNo;
	private String arrivalCountryNo;
	private int price;
	private Timestamp departureHour;
	private Timestamp arrivalHour;
	private String arvCountryName;
	private String depCountryName;
	private int remain;

	public FlightVO(String no, String planeNo, String departureCountryNo, String arrivalCountryNo, int price,
			Timestamp departureHour, Timestamp arrivalHour) {
		super();
		this.no = no;
		this.planeNo = planeNo;
		this.departureCountryNo = departureCountryNo;
		this.arrivalCountryNo = arrivalCountryNo;
		this.price = price;
		this.departureHour = departureHour;
		this.arrivalHour = arrivalHour;
	}

	public FlightVO(String no, String planeNo, String departureCountryNo, String arrivalCountryNo, int price,
			Timestamp departureHour) {
		super();
		this.no = no;
		this.planeNo = planeNo;
		this.departureCountryNo = departureCountryNo;
		this.arrivalCountryNo = arrivalCountryNo;
		this.price = price;
		this.departureHour = departureHour;
	}

	public FlightVO(String no, String planeNo, int price, Timestamp departureHour, Timestamp arrivalHour,
			 String depCountryName,String arvCountryName,int remain) {
		super();
		this.no = no;
		this.planeNo = planeNo;
		this.price = price;
		this.departureHour = departureHour;
		this.arrivalHour = arrivalHour;
		this.arvCountryName = arvCountryName;
		this.depCountryName = depCountryName;
		this.remain = remain;
	}

	public FlightVO(String no, String planeNo, String arrivalCountryNo, Timestamp departureHour) {
		super();
		this.no = no;
		this.planeNo = planeNo;
		this.arrivalCountryNo = arrivalCountryNo;
		this.departureHour = departureHour;
	}

	public FlightVO() {
		super();
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

	public String getDepartureCountryNo() {
		return departureCountryNo;
	}

	public void setDepartureCountryNo(String departureCountryNo) {
		this.departureCountryNo = departureCountryNo;
	}

	public String getArrivalCountryNo() {
		return arrivalCountryNo;
	}

	public void setArrivalCountryNo(String arrivalCountryNo) {
		this.arrivalCountryNo = arrivalCountryNo;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public Timestamp getDepartureHour() {
		return departureHour;
	}

	public void setDepartureHour(Timestamp departureHour) {
		this.departureHour = departureHour;
	}

	public Timestamp getArrivalHour() {
		return arrivalHour;
	}

	public void setArrivalHour(Timestamp arrivalHour) {
		this.arrivalHour = arrivalHour;
	}

	// 헤더 메서드
	public static String getHeader() {
	    return String.format(
	        "%-10s | %-10s | %-10s | %-20s | %-20s | %-15s | %-15s | %-15s",
	        "No", "Plane No", "Price", "Dep. Hour", "Arv. Hour", "Dep. Country", "Arv. Country", "Remain Seats"
	    );
	}

	// toString 메서드
	@Override
	public String toString() {
	    return String.format(
	        "%-10s | %-10s | %-10d | %-20s | %-20s | %-15s | %-15s | %-15d",
	        no,                 // String or other type, assumed to be a string
	        planeNo,            // String
	        price,              // int or double
	        flightDateFormat(departureHour),  // String (formatted date)
	        flightDateFormat(arrivalHour),    // String (formatted date)
	        depCountryName,     // String
	        arvCountryName,     // String
	        remain              // int
	    );
	}

	 private String flightDateFormat(Timestamp tp) {
		    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		    	return sdf.format(tp);
		    }

}