package com.pyo.airplaneService.model;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class BookingVO {
	private String no;// char(5), --PK
	private String groupNo;// char(3), --FK상영작NO
	private String customerNo;// char(5), --FK고객NO
	private String flightNo;// number(2) not null, --합계인원
	private String code;// char(15), --UQ예매 코드 생성 고객NO-상영작NO-예매NO로생성
	private String seat;// number(7), --합계가격
	private String seats_no;// date not null --예매날짜
	private Timestamp bookingDate;
	private String name;
	private String depCountry;
	private String arvCountry;
	private int price;
	public ArrayList<String> getSeatList() {
		return seatList;
	}
	public void setSeatList(ArrayList<String> seatList) {
		this.seatList = seatList;
	}

	private Timestamp departureHour;
	private Timestamp arrivalHour;
	private String planeName;
	private ArrayList<String> seatList;
	private int amount;
	
	


	public BookingVO(String code, Timestamp bookingDate, String name, String depCountry, String arvCountry, int price,
			Timestamp departureHour, Timestamp arrivalHour, String planeName, ArrayList<String> seatList) {
		super();
		this.code = code;
		this.bookingDate = bookingDate;
		this.name = name;
		this.depCountry = depCountry;
		this.arvCountry = arvCountry;
		this.price = price;
		this.departureHour = departureHour;
		this.arrivalHour = arrivalHour;
		this.planeName = planeName;
		this.seatList = seatList;
	}
	public BookingVO(String no, String groupNo, String customerNo, String flightNo, String code, String seat,
			String seats_no, Timestamp bookingDate) {
		super();
		this.no = no;
		this.groupNo = groupNo;
		this.customerNo = customerNo;
		this.flightNo = flightNo;
		this.code = code;
		this.seat = seat;
		this.seats_no = seats_no;
		this.bookingDate = bookingDate;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public BookingVO() {
		super();
	}
	public BookingVO(String groupNo, String customerNo, String flightNo, String seat , int amount) {
		super();
		this.groupNo = groupNo;
		this.customerNo = customerNo;
		this.flightNo = flightNo;
		this.seat = seat;
		this.amount = amount;
	}
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getGroupNo() {
		return groupNo;
	}
	public void setGroupNo(String groupNo) {
		this.groupNo = groupNo;
	}
	public String getCustomerNo() {
		return customerNo;
	}
	public void setCustomerNo(String customerNo) {
		this.customerNo = customerNo;
	}
	public String getFlightNo() {
		return flightNo;
	}
	public void setFlightNo(String flightNo) {
		this.flightNo = flightNo;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getSeat() {
		return seat;
	}
	public void setSeat(String seat) {
		this.seat = seat;
	}
	public String getSeats_no() {
		return seats_no;
	}
	public void setSeats_no(String seats_no) {
		this.seats_no = seats_no;
	}
	public Timestamp getBookingDate() {
		return bookingDate;
	}
	public void setBookingDate(Timestamp bookingDate) {
		this.bookingDate = bookingDate;
	}
	public BookingVO(String customerNo, String flightNo, String seat) {
		super();
		this.customerNo = customerNo;
		this.flightNo = flightNo;
		this.seat = seat;
	}
	
	public BookingVO(BookingVO bvo) {
		// TODO Auto-generated constructor stub
	}
	// 헤더 메서드
	public static String getHeader() {
	    return String.format(
	        "%-25s | %-10s | %-15s | %-15s | %-10s | %-20s | %-20s | %-20s | %-22s | %-20s\n",
	        "Code", "Name", "Dep. Country", "Arv. Country", "Price", 
	        "Dep. Hour", "Arv. Hour","Booking Date", "Plane Name", "Seats"
	    );
	}

	// toString 메서드
	@Override
	public String toString() {
	    return String.format(
	        "%-25s | %-10s | %-15s | %-15s | %-10d | %-20s | %-20s | %-19s  | %-22s | %s",
	        code, 
	        name, 
	        depCountry, 
	        arvCountry, 
	        price, 
	        bookingDateFormat(departureHour), 
	        bookingDateFormat(arrivalHour) , 
	        bookingDateFormat(bookingDate) , 
	        planeName, 
	        seatList != null ? String.join(", ", seatList) : "N/A"
	    );
	}
	public String seatsString() {
		String rt= "";
	for(String data :seatList) {
		rt=rt+data.toString()+" ";
	}
	return rt;
	}
	
	  private String bookingDateFormat(Timestamp tp) {
		    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		    	return sdf.format(tp);
		    }
	
	}