package com.pyo.airplaneService.view;

import java.sql.SQLException;

import com.pyo.airplaneService.model.BookingDAO;
import com.pyo.airplaneService.model.BookingVO;

public class BookingPrint {

	// 전체 고객 리스트를 출력요청
	public static void printAll() throws SQLException {
		BookingDAO bDAO = new BookingDAO();
		System.out.println(BookingVO.getHeader());
		for (BookingVO data : bDAO.selectDB()) {
			System.out.println(data.toString());
		}
	}

	// code를 받아서
	public static void printByCode(BookingVO bvo) {
		BookingDAO bDAO = new BookingDAO();
		System.out.println(BookingVO.getHeader());
		System.out.println(bDAO.selectByCodeDB(bvo).toString());

	}

	// customerNO가들어있는 bvo를 받아서 그 bvo를 출력
	public static void printByCustomer(BookingVO bvo) {
		BookingDAO bDAO = new BookingDAO();
		System.out.println(BookingVO.getHeader());
		for (BookingVO data : bDAO.selectByCustomerDB(bvo)) {
			System.out.println(data.toString());
		}

	}

}
