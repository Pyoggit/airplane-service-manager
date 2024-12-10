package com.pyo.airplaneService.model;


import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import com.pyo.airplaneService.util.DBUtility;


public class CustomerDAO
{
	public static Scanner sc = new Scanner(System.in);
	public final String CUSTOMER_INSERT = "INSERT INTO CUSTOMER(NO, NAME, BIRTH, PHONE, REGIST, ID, PW) VALUES((SELECT TO_CHAR(NVL(MAX(NO),0)+1,'FM0000000') FROM CUSTOMER), ?, ?, ?, SYSDATE, ?, ?)";
		public final String CUSTOMER_CHECK_SELECT = "SELECT NO, NAME, BIRTH, PHONE, REGIST, ID, PW FROM CUSTOMER WHERE ID=? AND PHONE=?";
		public final String CUSTOMER_UPDATE = "UPDATE CUSTOMER SET NAME=?, BIRTH=?, PHONE=?, REGIST=SYSDATE WHERE ID=?";
		public final String CUSTOMER_RIGHTA_UPDATE = "UPDATE CUSTOMER SET RIGHT = '1' WHERE ID=?";
		public final String CUSTOMER_RIGHTN_UPDATE = "UPDATE CUSTOMER SET RIGHT = '0' WHERE ID=?";
	    public final String CUSTOMER_DELETE = "DELETE FROM CUSTOMER WHERE ID=?";
	    public final String CUSTOMER_OUT_DELETE = "DELETE FROM CUSTOMER WHERE ID=? AND PHONE=?";
	    public final String CUSTOMER_SELECT = "SELECT * FROM CUSTOMER ORDER BY TO_NUMBER(NO)";
	    private final String SELECT_COUNT_BY_ID_SQL = "SELECT * FROM CUSTOMER WHERE ID = ?";
	    
	    public boolean insertDB(CustomerVO abcvo) throws SQLException	// 3번 <회원가입>
		{
			boolean successFlag = false; 
			Connection con = null;
			PreparedStatement pstmt = null;

			con = DBUtility.dbCon();

			pstmt = con.prepareStatement(CUSTOMER_INSERT);
			pstmt.setString(1, abcvo.getName());
			pstmt.setDate(2, abcvo.getBirth());
			pstmt.setString(3, abcvo.getPhone());
			pstmt.setString(4, abcvo.getId());
			pstmt.setString(5, abcvo.getPw());
			
			int result = pstmt.executeUpdate();
			
			DBUtility.dbClose(con, pstmt);
			successFlag = (result != 0) ? true : false;
			return successFlag; 
		}
	    
	    public boolean updateDB(CustomerVO abcvo) throws SQLException	// 1번(로그인)->3번(마이페이지)->1번 <내정보 수정하기>
	    {
	    	boolean successFlag = false; 
	    	
	    	Connection con = null;
	    	PreparedStatement pstmt = null;
	    	
	    	con = DBUtility.dbCon();
	    	
	    	pstmt = con.prepareStatement(CUSTOMER_UPDATE);
	    	pstmt.setString(1, abcvo.getName());
	    	pstmt.setDate(2, abcvo.getBirth());
	    	pstmt.setString(3, abcvo.getPhone());
	    	pstmt.setString(4, abcvo.getId());	//
	    	
	    	int result = pstmt.executeUpdate();
	    	
	    	DBUtility.dbClose(con, pstmt);
	    	
	    	successFlag = (result != 0) ? true : false;
	    	
	    	return successFlag;
	    }
	    
	    public boolean updateRightADB(CustomerVO abcvo) 	// 1번(로그인)->3번(마이페이지)->1번 <내정보 수정하기>
	    {
	    	boolean successFlag = false; 
	    	
	    	Connection con = null;
	    	PreparedStatement pstmt = null;
	    	int result =0;
	    	con = DBUtility.dbCon();
	    	try {
	    		pstmt = con.prepareStatement(CUSTOMER_RIGHTA_UPDATE);
	    		pstmt.setString(1, abcvo.getId());
	    		result = pstmt.executeUpdate();
				
			} catch (Exception e) {
				// TODO: handle exception
			}
	    	
	    	DBUtility.dbClose(con, pstmt);
	    	
	    	successFlag = (result != 0) ? true : false;
	    	
	    	return successFlag;
	    }
	    
	    public boolean updateRightNDB(CustomerVO abcvo) 	// 1번(로그인)->3번(마이페이지)->1번 <내정보 수정하기>
	    {
	    	boolean successFlag = false; 
	    	
	    	Connection con = null;
	    	PreparedStatement pstmt = null;
	    	int result =0;
	    	
	    	con = DBUtility.dbCon();
	    	try {
	    		pstmt = con.prepareStatement(CUSTOMER_RIGHTN_UPDATE);
	    		pstmt.setString(1, abcvo.getId());
	    		result = pstmt.executeUpdate();
			} catch (Exception e) {
				// TODO: handle exception
			}
	    	DBUtility.dbClose(con, pstmt);
	    	
	    	successFlag = (result != 0) ? true : false;
	    	
	    	return successFlag;
	    }
	    
	    
	    public ArrayList <CustomerVO> selectCheckDB(String idValue, String phoneValue)	// 1번(로그인)->3번(마이페이지)->2번 <내정보 조회하기>
		{
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			ArrayList<CustomerVO> CustomerVOList = new ArrayList<CustomerVO>();

			try
			{
				con = DBUtility.dbCon();
				pstmt = con.prepareStatement(CUSTOMER_CHECK_SELECT);
				pstmt.setString(1, idValue);
				pstmt.setString(2, phoneValue);
				rs = pstmt.executeQuery();
				if (rs.next())
				{
					do
					{
						String no = rs.getString("NO");
						String name = rs.getString("NAME");
						Date birth = rs.getDate("BIRTH");
						Date regist = rs.getDate("REGIST");
						String pw = rs.getString("PW");

						CustomerVO abcvo = new CustomerVO();
						
						abcvo.setNo(no);
						abcvo.setName(name);
						abcvo.setBirth(birth);
						abcvo.setPhone(phoneValue);
						abcvo.setRegist(regist);
						abcvo.setId(idValue);
						abcvo.setPw(pw);

						CustomerVOList.add(abcvo);
					} while (rs.next());
				} else
				{
					CustomerVOList = null;
				}
			} catch (SQLException e)
			{
				System.out.println(e.toString());
			} finally
			{
				DBUtility.dbClose(con, pstmt, rs);
			}
			return CustomerVOList;
		}

		public boolean deleteDB(CustomerVO abcvo)	// 1번(로그인)->3번(마이페이지)->3번 <회월 탈퇴하기>
		{
			boolean successFlag =false; 
			Connection con = null;
			PreparedStatement pstmt = null;

			try
			{
				con = DBUtility.dbCon();
				pstmt = con.prepareStatement(CUSTOMER_DELETE);
				pstmt.setString(1, abcvo.getId());
				int result = pstmt.executeUpdate();
				successFlag = (result != 0) ? true : false ;
				
			} catch (SQLException e)
			{
				System.out.println(e.toString());
			} finally
			{
				DBUtility.dbClose(con, pstmt);
			}
			return successFlag; 
		}
		
		public boolean deleteOutDB(CustomerVO abcvo)	// 1번(로그인)->3번(마이페이지)->3번 <회월 탈퇴하기>
		{
			boolean successFlag =false; 
			Connection con = null;
			PreparedStatement pstmt = null;

			try
			{
				con = DBUtility.dbCon();
				pstmt = con.prepareStatement(CUSTOMER_DELETE);
				pstmt.setString(1, abcvo.getId());
				pstmt.setString(2, abcvo.getPhone());
				
				int result = pstmt.executeUpdate();
				successFlag = (result != 0) ? true : false ;
				
			} catch (SQLException e)
			{
				System.out.println(e.toString());
			} finally
			{
				DBUtility.dbClose(con, pstmt);
			}
			return successFlag; 
		}
		
		public ArrayList <CustomerVO> selectDB()
		{
			Connection con = null;
			Statement stmt = null;
			ResultSet rs = null;
			ArrayList <CustomerVO> AirBookingCustomerVOList = new ArrayList <CustomerVO>();

			con = DBUtility.dbCon();
					
			try
			{
				stmt = con.createStatement();
				rs = stmt.executeQuery(CUSTOMER_SELECT);

				if (rs.next())
				{
					do
					{
						String no = rs.getString("NO");
						String name = rs.getString("NAME");
						Date birth = rs.getDate("BIRTH");
						String phone = rs.getString("PHONE");
						Date regist = rs.getDate("REGIST");
						String id = rs.getString("ID");
						String pw = rs.getString("PW");

						CustomerVO abcvo = new CustomerVO(no, name, birth, phone, regist, id, pw);

						AirBookingCustomerVOList.add(abcvo);
					} while (rs.next());
				} else
				{
					AirBookingCustomerVOList = null;
				}
			} catch (SQLException e)
			{
				System.out.println(e.toString());
			} finally
			{
				DBUtility.dbClose(con, stmt, rs);
			}
			return AirBookingCustomerVOList;
		}

		 // ID가 들어있는 cvo를받아서 해당 db에서 일치하는 정보를 가져와서 반환
	    public CustomerVO selectByIdDB(CustomerVO cvo)
	    {
	        Connection con = null;
	        PreparedStatement pstmt = null;
	        ResultSet rs = null;
	      
	        con = DBUtility.dbCon();
	        try {
	        	pstmt = con.prepareStatement(SELECT_COUNT_BY_ID_SQL);
	        	pstmt.setString(1, cvo.getId());
	        	rs = pstmt.executeQuery();
	        	
	        	while (rs.next())
	        	{
	        		String no = rs.getString("NO");
	        		String name = rs.getString("NAME");
	        		String id = rs.getString("ID");
	        		String pw = rs.getString("PW");
	        		Date birth = rs.getDate("BIRTH");
	        		String phone = rs.getString("PHONE");
	        		int cCount = rs.getInt("C_COUNT");
	        		Date regist = rs.getDate("REGIST");
	        		int right = rs.getInt("RIGHT");
	        		cvo = new CustomerVO(no, name, birth, phone,  regist, right, id, pw,cCount);
	        	}
				
			} catch (Exception e) {
				// TODO: handle exception
			}
	        // 저장된 리턴값 추가.
			return cvo;
	    }
}