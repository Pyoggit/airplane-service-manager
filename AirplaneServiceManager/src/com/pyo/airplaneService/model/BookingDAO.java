package com.pyo.airplaneService.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

import com.pyo.airplaneService.model.BookingVO;
import com.pyo.airplaneService.util.DBUtility;


public class BookingDAO {
	private final String SELECT_SQL = "SELECT * FROM BOOKING_JOIN_ALL_VIEW ORDER BY TO_NUMBER(GROUP_NO),SUBSTR(SEAT,1,2),TO_NUMBER(SUBSTR(SEAT,3))";
	private final String SELECT_BY_CODE_SQL = "SELECT * FROM BOOKING_JOIN_ALL_VIEW WHERE trim(CODE) like TRIM(?)";
	private final String SELECT_BY_CODE_CUSNO_SQL = "SELECT * FROM BOOKING_JOIN_ALL_VIEW WHERE CODE = ? AND CUSTOMER_NO = TO_CHAR(?,'FM0000000')";
	private final String SELECT_BY_CUSTOMER_SQL = "SELECT * FROM BOOKING_JOIN_ALL_VIEW WHERE CUSTOMER_NO = TO_CHAR(?,'FM0000000') ORDER BY GROUP_NO, SUBSTR(SEAT,1,2),TO_NUMBER(SUBSTR(SEAT,3)) ";
	private final String SELECT_LAST_SQL = "SELECT * FROM (select * from BOOKING_JOIN_ALL_VIEW order by booking_date desc) where rownum=1";
	private final String SELECT_LAST_GROUP_NO_SQL = "SELECT * FROM BOOKING_JOIN_ALL_VIEW where TO_NUMBER(GROUP_NO)=(SELECT MAX(TO_NUMBER(GROUP_NO)) FROM BOOKING)";
	private final String INSERT_SQL = "INSERT INTO Booking(NO,GROUP_NO, CUSTOMER_NO, FLIGHT_NO, SEAT,BOOKING_DATE) "
			+ "VALUES(to_char((select nvl(max(no),0)+1 from Booking),'FM0000000'),to_char((select nvl(max(GROUP_NO),0)+1 from Booking),'FM000000'), TO_CHAR(?,'FM0000000'), TO_CHAR(?,'FM00000'),?,SYSDATE)";
	private final String INSERT_REMAIN_SQL = "INSERT INTO Booking( NO,GROUP_NO, CUSTOMER_NO, FLIGHT_NO, SEAT,BOOKING_DATE) "
			+ "VALUES(to_char((select nvl(max(no),0)+1 from Booking),'FM0000000'), TO_CHAR(?,'FM000000'), TO_CHAR(?,'FM0000000'), TO_CHAR(?,'FM00000'),?,SYSDATE)";
	private final String DELETE_SQL = "DELETE FROM Booking WHERE CODE = ?";

	// BookingVO를 받아서 db에 insert 후 성공여부 true false 반환
	public boolean insertDB(ArrayList<BookingVO> bvoList) {
		// Conection
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result1 = 0;
		String newGroupNo = null;
		// 1 Load, 2. connect
		con = DBUtility.dbCon();
		// 3.statement
		try {
			pstmt = con.prepareStatement(INSERT_SQL);
			pstmt.setString(1, bvoList.get(0).getCustomerNo());
			pstmt.setString(2, bvoList.get(0).getFlightNo());
			pstmt.setString(3, bvoList.get(0).getSeat());
			result1 = pstmt.executeUpdate();
			pstmt = con.prepareStatement(SELECT_LAST_SQL);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				newGroupNo = rs.getString("GROUP_NO");
			}
			for (int i = 1; i < bvoList.size(); i++) {
				pstmt = con.prepareStatement(INSERT_REMAIN_SQL);
				pstmt.setString(1, newGroupNo);
				pstmt.setString(2, bvoList.get(i).getCustomerNo());
				pstmt.setString(3, bvoList.get(i).getFlightNo());
				pstmt.setString(4, bvoList.get(i).getSeat());
				result1 = pstmt.executeUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		DBUtility.dbClose(con, rs, pstmt);
		return (result1 != 0) ? true : false;
	}

	// BookingVO를 받아서 db에 delete 후 성공여부 true false 반환
	public boolean deleteDB(BookingVO bvo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int result = 0;
		con = DBUtility.dbCon();
		try {
			pstmt = con.prepareStatement(DELETE_SQL);
			pstmt.setString(1, bvo.getCode());
			result = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		DBUtility.dbClose(con, pstmt);
		return (result != 0) ? true : false;
	}

	// 테이블 전체를 List에 저장 후 반환, code가 같은 예약정보는 좌석 정보만 취합해서 한개로 압축
	public ArrayList<BookingVO> selectDB() {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<BookingVO> bookingList = new ArrayList<>();
		con = DBUtility.dbCon();

		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(SELECT_SQL);

			BookingVO currentBvo = null; // 현재 처리 중인 BookingVO 객체
			ArrayList<String> seatList = new ArrayList<>();

			while (rs.next()) {
				String code = rs.getString("CODE");
				String name = rs.getString("NAME");
				String seat = rs.getString("SEAT");
				String planeName = rs.getString("PLANE_NAME");
				String depCountry = rs.getString("DEP_COUNTRY");
				String arvCountry = rs.getString("ARV_COUNTRY");
				int price = rs.getInt("PRICE");
				Timestamp departureHour = rs.getTimestamp("DEPARTURE_HOUR");
				Timestamp arrivalHour = rs.getTimestamp("ARRIVAL_HOUR");
				Timestamp bookingDate = rs.getTimestamp("BOOKING_DATE");

				// 새로운 그룹 (code가 변경되었을 때)
				if (currentBvo == null || !currentBvo.getCode().equals(code)) {
					// 이전 BookingVO를 리스트에 추가
					if (currentBvo != null) {
						bookingList.add(currentBvo);
					}
					// 새로운 BookingVO 생성 및 초기화
					seatList = new ArrayList<>(); // 새로운 좌석 리스트 생성
					seatList.add(seat);
					currentBvo = new BookingVO(code, bookingDate, name, depCountry, arvCountry, price, departureHour,
							arrivalHour, planeName, seatList);
				} else {
					// 같은 그룹이라면 seat만 추가
					seatList.add(seat);
				}
			}

			// 마지막 BookingVO 추가
			if (currentBvo != null) {
				bookingList.add(currentBvo);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtility.dbClose(con, rs, stmt);
		}

		return bookingList;
	}

	// code가 들어있는 BookingVO를 받아서 그에 해당하는 db의 BookingVO를 반환

	public BookingVO selectByCodeDB(BookingVO bvo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		con = DBUtility.dbCon();
		ArrayList<String> seatList = new ArrayList<String>();
		try {
			pstmt = con.prepareStatement(SELECT_BY_CODE_SQL);
			pstmt.setString(1, bvo.getCode());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				String code = rs.getString("CODE");
				String name = rs.getString("NAME");
				String seat = rs.getString("SEAT");
				String planeName = rs.getString("PLANE_NAME");
				String depCountry = rs.getString("DEP_COUNTRY");
				String arvCountry = rs.getString("ARV_COUNTRY");
				int price = rs.getInt("PRICE");
				Timestamp departureHour = rs.getTimestamp("DEPARTURE_HOUR");
				Timestamp arrivalHour = rs.getTimestamp("ARRIVAL_HOUR");
				Timestamp bookingDate = rs.getTimestamp("BOOKING_DATE");
				seatList.add(seat);
				bvo = new BookingVO(code, bookingDate, name, depCountry, arvCountry, price, departureHour, arrivalHour,
						planeName, seatList);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		DBUtility.dbClose(con, rs, pstmt);
		return bvo;
	}


	public BookingVO selectByCodeAndCusNoDB(BookingVO bvo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		con = DBUtility.dbCon();
		ArrayList<String> seatList = new ArrayList<String>();
		try {
			pstmt = con.prepareStatement(SELECT_BY_CODE_CUSNO_SQL);
			pstmt.setString(1, bvo.getCode());
			pstmt.setString(2, bvo.getCustomerNo());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				String code = rs.getString("CODE");
				String name = rs.getString("NAME");
				String seat = rs.getString("SEAT");
				String planeName = rs.getString("PLANE_NAME");
				String depCountry = rs.getString("DEP_COUNTRY");
				String arvCountry = rs.getString("ARV_COUNTRY");
				int price = rs.getInt("PRICE");
				Timestamp departureHour = rs.getTimestamp("DEPARTURE_HOUR");
				Timestamp arrivalHour = rs.getTimestamp("ARRIVAL_HOUR");
				Timestamp bookingDate = rs.getTimestamp("BOOKING_DATE");
				seatList.add(seat);
				bvo = new BookingVO(code, bookingDate, name, depCountry, arvCountry, price, departureHour, arrivalHour,
						planeName, seatList);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		DBUtility.dbClose(con, rs, pstmt);
		return bvo;
	}

	
	// Customer_no가 들어있는 BookingVO를 받아서 그에 해당하는 db의 BookingVO를 반환
	public ArrayList<BookingVO> selectByCustomerDB(BookingVO bvo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<BookingVO> bookingList = new ArrayList<BookingVO>();
		con = DBUtility.dbCon();
		try {
			pstmt = con.prepareStatement(SELECT_BY_CUSTOMER_SQL);
			pstmt.setString(1, bvo.getCustomerNo());
			rs = pstmt.executeQuery();
			BookingVO currentBvo = null; // 현재 처리 중인 BookingVO 객체
			ArrayList<String> seatList = new ArrayList<>();

			while (rs.next()) {
				String code = rs.getString("CODE");
				String name = rs.getString("NAME");
				String seat = rs.getString("SEAT");
				String planeName = rs.getString("PLANE_NAME");
				String depCountry = rs.getString("DEP_COUNTRY");
				String arvCountry = rs.getString("ARV_COUNTRY");
				int price = rs.getInt("PRICE");
				Timestamp departureHour = rs.getTimestamp("DEPARTURE_HOUR");
				Timestamp arrivalHour = rs.getTimestamp("ARRIVAL_HOUR");
				Timestamp bookingDate = rs.getTimestamp("BOOKING_DATE");

				// 새로운 그룹이 나올떄, code가 바뀔때 이전의 bvo를 리스트에 추가후, 새로 seat을 초기화하고 다시
				if (currentBvo == null || !currentBvo.getCode().equals(code)) {
					// 이전 BookingVO를 리스트에 추가
					if (currentBvo != null) {
						bookingList.add(currentBvo);
					}
					// 새로운 BookingVO 생성 및 초기화
					seatList = new ArrayList<>(); // 새로운 좌석 리스트 생성
					seatList.add(seat);
					currentBvo = new BookingVO(code, bookingDate, name, depCountry, arvCountry, price, departureHour,
							arrivalHour, planeName, seatList);
				} else {
					// 같은 그룹이라면 seat만 추가
					seatList.add(seat);
				}
			}

			// 마지막 BookingVO 추가
			if (currentBvo != null) {
				bookingList.add(currentBvo);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		DBUtility.dbClose(con, rs, pstmt);
		return bookingList;
	}

	// 가장 최근 입력된 bvo를 리턴
	public BookingVO selectLastDB() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BookingVO bvo = null;
		ArrayList<String> seatList = new ArrayList<String>();
		con = DBUtility.dbCon();
		try {
			pstmt = con.prepareStatement(SELECT_LAST_GROUP_NO_SQL);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				String code = rs.getString("CODE");
				String name = rs.getString("NAME");
				String seat = rs.getString("SEAT");
				String planeName = rs.getString("PLANE_NAME");
				String depCountry = rs.getString("DEP_COUNTRY");
				String arvCountry = rs.getString("ARV_COUNTRY");
				int price = rs.getInt("PRICE");
				Timestamp departureHour = rs.getTimestamp("DEPARTURE_HOUR");
				Timestamp arrivalHour = rs.getTimestamp("ARRIVAL_HOUR");
				Timestamp bookingDate = rs.getTimestamp("BOOKING_DATE");
				seatList.add(seat);
				bvo = new BookingVO(code, bookingDate, name, depCountry, arvCountry, price, departureHour, arrivalHour,
						planeName, seatList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		DBUtility.dbClose(con, rs, pstmt);
		return bvo;
	}
}