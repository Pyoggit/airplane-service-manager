package com.pyo.airplaneService.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.pyo.airplaneService.util.DBUtility;

public class SeatsDAO {
	private final String SELECT_BY_FLIGHT_SQL = "SELECT S.ROWX,S.COLY,CUSTOMER_NO,CODE,GROUP_NO,P.COLY AS YNUM FROM SEATS S LEFT JOIN BOOKING B \r\n"
			+ "ON S.NO = B.SEATS_NO \r\n"
			+ "JOIN PLANE P \r\n"
			+ "ON P.NO = S.PLANE_NO\r\n"
			+ "WHERE S.PLANE_NO = (SELECT PLANE_NO FROM FLIGHT WHERE NO = TO_CHAR(?,'FM00000'))\r\n"
			+ "ORDER BY ROWX, TO_NUMBER(COLY)";

	// bvo를 받아서 해당 flightNo를 이용해 테이블 전체를 List 에 저장 후 반환
	public ArrayList<SeatsVO> selectByFlightNoDB(BookingVO bvo) {// bvo 의 flightNo 를 이용해서 저장
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null; 
		SeatsVO svo = new SeatsVO();
		ArrayList<SeatsVO> SeatsList = new ArrayList<SeatsVO>();
		con = DBUtility.dbCon();
		try {
			pstmt = con.prepareStatement(SELECT_BY_FLIGHT_SQL);
			pstmt.setString(1, bvo.getFlightNo());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				String rowx = rs.getString("ROWX");
				String coly = rs.getString("COLY");
				String code = rs.getString("CODE");
				int ynum = rs.getInt("YNUM");
				svo = new SeatsVO(rowx, coly, code, ynum);
				SeatsList.add(svo);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		DBUtility.dbClose(con, rs, pstmt);
		return SeatsList;
	}

	// bvo를 받아서 해당 예매정보의 flight_no 의 seat 이 예매할수 있는 좌석인지 확인. 확인해서 seat 의 이름도 같고 code
	// 가 null(예매한 고객 없음) 이면 true 반환
	public boolean selectRightSeatDB(BookingVO bvo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean checkFlag = false;
		con = DBUtility.dbCon();
		try {
			pstmt = con.prepareStatement(SELECT_BY_FLIGHT_SQL);
			pstmt.setString(1, bvo.getFlightNo());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				String rowx = rs.getString("ROWX");
				String coly = rs.getString("COLY");
				String code = rs.getString("CODE");
				if (code == null && (rowx+coly).equals(bvo.getSeat())) {
					checkFlag = true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		DBUtility.dbClose(con, rs, pstmt);
		return checkFlag;
	}

}