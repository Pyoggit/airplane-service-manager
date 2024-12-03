package com.pyo.airplaneService.model;

public class PlaneVO {
    private String no;           // 여객기 코드
    private String name;         // 기종
    private int rowX;            // 좌석의 행 개수
    private int colY;            // 좌석의 열 개수
    private int seats;           // 총 좌석 개수 (트리거로 생성됨)

    public PlaneVO() {}

 
    public PlaneVO(String no, String name, int rowX, int colY, int seats) {
        this.no = no;
        this.name = name;
        this.rowX = rowX;
        this.colY = colY;
        this.seats = seats;
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

    public int getRowX() {
        return rowX;
    }

    public void setRowX(int rowX) {
        this.rowX = rowX;
    }

    public int getColY() {
        return colY;
    }

    public void setColY(int colY) {
        this.colY = colY;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }


 // 헤더 메서드
    public static String getHeader() {
        return String.format(
            "%-10s | %-20s | %-10s | %-10s | %-10s",
            "No", "Name", "Row", "Col", "Seats"
        );
    }

    // toString 메서드
    @Override
    public String toString() {
        return String.format(
            "%-10s | %-20s | %-10s | %-10s | %-10s",
            no,
            name,
            rowX,
            colY,
            seats
        );
    }

}

