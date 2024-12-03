package com.pyo.airplaneService.util;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DBUtility {
	// 데이터베이스 연결 메서드
	public static Connection dbCon() {
		Connection con = null;

		// db.properties 파일 경로 설정
		String filePath = "D:\\airplane-service-manager\\AirplaneServiceManager\\src\\db.properties";
		Properties pt = new Properties();
		try {
			// 파일에서 데이터베이스 설정 읽기
			pt.load(new FileReader(filePath));
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		// 데이터베이스 연결에 필요한 정보 가져오기
		String id = pt.getProperty("id");
		String pw = pt.getProperty("pw");
		String url = pt.getProperty("url");

		try {
			// Oracle JDBC 드라이버 로드
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 데이터베이스 연결 생성
			con = DriverManager.getConnection(url, id, pw);
		} catch (ClassNotFoundException e) {
			// 드라이버 클래스를 찾을 수 없는 경우 예외 처리
			System.out.println(e.toString());
		} catch (SQLException e) {
			// 데이터베이스 연결 실패 시 예외 처리
			System.out.println(e.toString());
		}
		return con;
	}

	public static void dbClose(Connection con, ResultSet rs, Statement... stmts) {
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				System.out.println(e.toString());
			}
		}
		for (Statement data : stmts) {
			if (data != null) {
				try {
					data.close();
				} catch (SQLException e) {
					System.out.println(e.toString());
				}
			}
		}

		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				System.out.println(e.toString());
			}
		}
	}

	public static void dbClose(Connection con, Statement... stmts) {
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				System.out.println(e.toString());
			}
		}
		for (Statement data : stmts) {
			if (data != null) {
				try {
					data.close();
				} catch (SQLException e) {
					System.out.println(e.toString());
				}
			}
		}

	}

	public static void dbClose(Connection con, PreparedStatement pstmt, ResultSet rs) {
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				System.out.println(e.toString());
			}
		}
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				System.out.println(e.toString());
			}
		}

		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				System.out.println(e.toString());
			}
		}
	}

	public static void dbClose(Connection con, Statement stmt, ResultSet rs) {
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				System.out.println(e.toString());
			}
		}
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				System.out.println(e.toString());
			}
		}

		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				System.out.println(e.toString());
			}
		}

	}

}
