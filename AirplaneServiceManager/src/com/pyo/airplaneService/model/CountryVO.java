package com.pyo.airplaneService.model;

public class CountryVO {
    private String no;        // 나라 코드 (PK)
    private String name;      // 나라 이름
    private int distance;     // 비행 거리
    private double hour;      // 비행 소요 시간


    public CountryVO() {}


    public CountryVO(String no, String name, int distance, double hour) {
        this.no = no;
        this.name = name;
        this.distance = distance;
        this.hour = hour;
    }
    

    public CountryVO(String name, int distance) {
		super();
		this.name = name;
		this.distance = distance;
	}


	public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public double getHour() {
        return hour;
    }

    public void setHour(double hour) {
        this.hour = hour;
    }

    public static String getHeader() {
        return String.format(
            "%-10s | %-20s | %-15s | %-10s\n",
            "No", "Name", "Distance", "Hour"
        );
    }
    
    @Override
    public String toString() {
        return String.format(
            "%-10s | %-20s | %-15s | %-10s",
            no,
            name,
            distance,
            hour
        );
    }

}
