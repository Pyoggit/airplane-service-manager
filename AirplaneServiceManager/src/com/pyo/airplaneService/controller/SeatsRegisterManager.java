package com.pyo.airplaneService.controller;

import java.util.ArrayList;
import java.util.Scanner;

import com.pyo.airplaneService.model.BookingVO;
import com.pyo.airplaneService.model.SeatsDAO;
import com.pyo.airplaneService.model.SeatsVO;

public class SeatsRegisterManager {
	public static Scanner sc = new Scanner(System.in);

	// BookingVO를 받아서 해당 예약정보의 flight 일치하는 SeatsVO 전체를 ArrayList에 담아서 출력 요청, 잔여좌석은
	// int로 반환 .
	public int selectByFlightManager(BookingVO bvo) {
		SeatsDAO sDAO = new SeatsDAO();
		ArrayList<SeatsVO> seatsList = sDAO.selectByFlightNoDB(bvo);
		int count = 0;
		int remain = 0;
		System.out.println("-----------------좌석현황-----------------");
		for (SeatsVO data : seatsList) {
			System.out.print("|" + data + "|");
			count++;
			if (count % (data.getYnum() / 2) == 0) {
				System.out.print("  ");
			}
			if (count % data.getYnum() == 0) {
				System.out.println();
			}
			if (data.getCode() == null) {
				remain++;
			}
		}
		System.out.println("잔여 좌석은 : " + remain + "석 입니다.");
		return remain;
	}

}
