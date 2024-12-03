package com.pyo.airplaneService.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.pyo.airplaneService.model.PlaneVO;
import com.pyo.airplaneService.util.DBUtility;

public class PlaneDAO {
	
	private static final String PLANE_SELECT = "SELECT * FROM PLANE ORDER BY TO_NUMBER(NO)";
	private static final String PLANE_INSERT = "INSERT INTO PLANE (NO, NAME, ROWX, COLY) VALUES ((SELECT TO_CHAR(NVL(MAX(NO),0)+1,'FM00000') FROM PLANE), ?, ?, ?)";
	private static final String PLANE_UPDATE = "UPDATE PLANE SET NAME = ?, ROWX = ?, COLY = ? WHERE NO = ?";
	private static final String PLANE_DELETE = "DELETE FROM PLANE WHERE NO = ?";
	private static final String PLANE_NO_SELECT = "SELECT * FROM PLANE WHERE NO = TO_char(?,'FM00000')";
	
    // 여객기 정보 삽입 (INSERT)
    public boolean insertPlaneDB(PlaneVO plane) {
        Connection con = null;
        PreparedStatement pstmt = null;
        boolean successFlag = false;


        try {
            con = DBUtility.dbCon();
            pstmt = con.prepareStatement(PLANE_INSERT);
            pstmt.setString(1, plane.getName());
            pstmt.setInt(2, plane.getRowX());
            pstmt.setInt(3, plane.getColY());

            int result = pstmt.executeUpdate();
            successFlag = (result > 0);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtility.dbClose(con, pstmt);
        }

        return successFlag;
    }

    // 여객기 정보 조회 
    public ArrayList<PlaneVO> selectPlaneDB() {
        ArrayList<PlaneVO> planeList = new ArrayList<>();
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            con = DBUtility.dbCon();
            pstmt = con.prepareStatement(PLANE_SELECT);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                String no = rs.getString("NO");
                String name = rs.getString("NAME");
                int rowX = rs.getInt("ROWX");
                int colY = rs.getInt("COLY");
                int seats = rs.getInt("SEATS");

                PlaneVO plane = new PlaneVO(no, name, rowX, colY, seats);
                planeList.add(plane);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtility.dbClose(con, pstmt, rs);
        }

        return planeList;
    }

    // 여객기 정보 수정
    public boolean updatePlaneDB(PlaneVO plane) {
        Connection con = null;
        PreparedStatement pstmt = null;
        boolean successFlag = false;

        try {
            con = DBUtility.dbCon();
            pstmt = con.prepareStatement(PLANE_UPDATE);
            pstmt.setString(1, plane.getName());
            pstmt.setInt(2, plane.getRowX());
            pstmt.setInt(3, plane.getColY());
            pstmt.setString(4, plane.getNo());

            int result = pstmt.executeUpdate();
            successFlag = (result > 0);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtility.dbClose(con, pstmt);
        }

        return successFlag;
    }

    // 여객기 정보 삭제 
    public boolean deletePlaneDB(String no) {
        Connection con = null;
        PreparedStatement pstmt = null;
        boolean successFlag = false;

        try {
            con = DBUtility.dbCon();
            pstmt = con.prepareStatement(PLANE_DELETE);
            pstmt.setString(1, no);

            int result = pstmt.executeUpdate();
            successFlag = (result > 0);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtility.dbClose(con, pstmt);
        }

        return successFlag;
    }

    // 특정 여객기 정보 조회
    public PlaneVO findPlaneByNo(String no) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        PlaneVO plane = null;

        try {
            con = DBUtility.dbCon();
            pstmt = con.prepareStatement(PLANE_NO_SELECT);
            pstmt.setString(1, no);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                String name = rs.getString("NAME");
                int rowX = rs.getInt("ROWX");
                int colY = rs.getInt("COLY");
                int seats = rs.getInt("SEATS");

                plane = new PlaneVO(no, name, rowX, colY, seats);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtility.dbClose(con, pstmt, rs);
        }

        return plane;
    }
}
