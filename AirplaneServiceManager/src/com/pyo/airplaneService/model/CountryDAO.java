package com.pyo.airplaneService.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.pyo.airplaneService.util.DBUtility;


public class CountryDAO {
	
	private static final String COUNTRY_SELECT = "SELECT * FROM COUNTRY";
	private static final String COUNTRY_INSERT = "INSERT INTO COUNTRY (NO,NAME, DISTANCE) VALUES ((SELECT TO_CHAR(NVL(MAX(NO),0)+1,'FM00000') FROM COUNTRY),?, ?)";
	private static final String COUNTRY_DELETE = "DELETE FROM COUNTRY WHERE NO = ?";
	private static final String COUNTRY_UPDATE = "UPDATE COUNTRY SET NAME = ?, DISTANCE = ? WHERE NO = ?";
	private static final String COUNTRY_NO_SELECT = "SELECT * FROM COUNTRY WHERE NO = TO_CHAR(?,'FM00000')";
	

    // 여행국 정보 출력
    public ArrayList<CountryVO> selectCountryDB() {
        ArrayList<CountryVO> countryList = new ArrayList<>();
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            con = DBUtility.dbCon();
            pstmt = con.prepareStatement(COUNTRY_SELECT);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                String no = rs.getString("NO");
                String name = rs.getString("NAME");
                int distance = rs.getInt("DISTANCE");
                double hour = rs.getDouble("HOUR");

                CountryVO country = new CountryVO(no, name, distance, hour);
                countryList.add(country);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtility.dbClose(con, pstmt, rs);
        }

        return countryList;
    }

    // 여행국 정보 입력
    public boolean insertCountryDB(CountryVO country) {
        Connection con = null;
        PreparedStatement pstmt = null;
        boolean successFlag = false;

        try {
            con = DBUtility.dbCon();
            pstmt = con.prepareStatement(COUNTRY_INSERT);
            pstmt.setString(1, country.getName());
            pstmt.setInt(2, country.getDistance());

            int result = pstmt.executeUpdate();
            successFlag = (result > 0);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtility.dbClose(con, pstmt);
        }

        return successFlag;
    }

    // 여행국 정보 수정
    public boolean updateCountryDB(CountryVO country) {
        Connection con = null;
        PreparedStatement pstmt = null;
        boolean successFlag = false;

        try {
            con = DBUtility.dbCon();
            pstmt = con.prepareStatement(COUNTRY_UPDATE);
            pstmt.setString(1, country.getName());
            pstmt.setInt(2, country.getDistance());
            pstmt.setString(3, country.getNo());

            int result = pstmt.executeUpdate();
            successFlag = (result > 0);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtility.dbClose(con, pstmt);
        }

        return successFlag;
    }

    // 여행국 정보 삭제
    public boolean deleteCountryDB(String countryNo) {
        Connection con = null;
        PreparedStatement pstmt = null;
        boolean successFlag = false;


        try {
            con = DBUtility.dbCon();
            pstmt = con.prepareStatement(COUNTRY_DELETE);
            pstmt.setString(1, countryNo);

            int result = pstmt.executeUpdate();
            successFlag = (result > 0);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtility.dbClose(con, pstmt);
        }

        return successFlag;
    }
    
    // 여행국 코드 검색
    public CountryVO findCountryByNo(String no) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        CountryVO country = null;

        try {
            con = DBUtility.dbCon();
            pstmt = con.prepareStatement(COUNTRY_NO_SELECT);
            pstmt.setString(1, no);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                String name = rs.getString("NAME");
                int distance = rs.getInt("DISTANCE");
                double hour = rs.getDouble("HOUR");

                country = new CountryVO(no, name, distance, hour);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtility.dbClose(con, pstmt, rs);
        }

        return country;
    }

}
