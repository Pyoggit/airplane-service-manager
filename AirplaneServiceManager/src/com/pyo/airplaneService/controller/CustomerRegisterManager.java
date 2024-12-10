package com.pyo.airplaneService.controller;

import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

import com.pyo.airplaneService.model.CustomerDAO;
import com.pyo.airplaneService.model.CustomerVO;

public class CustomerRegisterManager {
	public Scanner scan = new Scanner(System.in);

	public void insertManager() { // 관리자 회원정보 추가 메뉴
		CustomerDAO cDAO = new CustomerDAO();
		Date birth = null;
		String phone = null;
		String id = null;
		String pw = null;

		System.out.println("*계정을 추가합니다..");
		System.out.println("아이디를 입력해주세요. ");
		boolean exitFlag = false;
		CustomerVO cvo = new CustomerVO();
		cDAO = new CustomerDAO();
		while (!exitFlag) {
			System.out.print(">>");
			id = scan.nextLine();
			cvo.setId(id);
			cvo = cDAO.selectByIdDB(cvo);
			if (cvo.getName() == null) {
				System.out.println("사용가능한 아이디입니다.");
				System.out.print(">>");
				exitFlag = true;
			} else {
				System.out.println("중복된 아이디입니다.");
				System.out.print("재입력 >>");
			}
		}

		System.out.println("이름을 입력해주세요. ");
		System.out.print(">>");
		String name = scan.nextLine();

		System.out.println("생년월일을 입력해주세요. (YYYY/MM/DD) : ");
		boolean birthFlag = false;
		while (!birthFlag) {
			String stsp = scan.nextLine();
			try {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
				java.util.Date jdate = sdf.parse(stsp);
				birth = new Date(jdate.getTime());
				birthFlag = true;
			} catch (Exception e) {
				birthFlag = true;
			}
		}

		System.out.println("휴대폰번호를 입력해주세요.");
		System.out.print(">>");
		phone = scan.nextLine();

		System.out.println("비밀번호를 입력해주세요.");
		System.out.print(">>");
		pw = scan.nextLine();

		CustomerVO abcvo = new CustomerVO(name, birth, phone, id, pw);

		boolean successFlag;
		try {
			successFlag = cDAO.insertDB(abcvo);
			if (successFlag == true) {
				System.out.println(name + "님의 가입이 완료되었습니다.");
			} else {
				System.out.println(name + "님의 가입이 완료되지않았습니다.");
			}
		} catch (SQLException e) {
			System.out.println(e.toString());
		}
	}

	public void insertRegistManager() // 3번 <회원가입 메뉴>
	{
		CustomerDAO cDAO = new CustomerDAO();
		Date birth = null;
		String phone = null;
		String id = null;
		String pw = "0";
		String pw2 = "awdjwod";

		System.out.println("*가입정보를 입력해주세요.");
		System.out.println("아이디를 입력해주세요. ");
		boolean exitFlag = false;
		CustomerVO cvo = new CustomerVO();
		cDAO = new CustomerDAO();
		while (!exitFlag) {
			System.out.print(">>");
			id = scan.nextLine();
			cvo.setId(id);
			CustomerVO cvo2 = cDAO.selectByIdDB(cvo);
			if (cvo2.getName() == null) {
				System.out.println("사용가능한 아이디입니다.");
				System.out.print(">>");
				exitFlag = true;
			} else {
				System.out.println("중복된 아이디입니다.");
				System.out.print("재입력 >>");
			}
		}

		System.out.println("이름을 입력해주세요. ");
		System.out.print(">>");
		String name = scan.nextLine();

		System.out.println("생년월일을 입력해주세요. (YYYY/MM/DD) : ");
		boolean birthFlag = false;
		while (!birthFlag) {
			String stsp = scan.nextLine();
			try {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
				java.util.Date jdate = sdf.parse(stsp);
				birth = new Date(jdate.getTime());
				birthFlag = true;
			} catch (Exception e) {
				birthFlag = true;
			}
		}

		System.out.println("휴대폰번호를 입력해주세요.");
		System.out.print(">>");
		phone = scan.nextLine();

		while (!pw.equals(pw2)) {
			System.out.println("비밀번호를 입력해주세요.");
			System.out.print(">>");
			pw = scan.nextLine();
			System.out.println("입력된 비밀번호 확인.");
			System.out.print(">>");
			pw2 = scan.nextLine();
			System.out.println((!pw.equals(pw2)) ? ("비밀번호가 일치하지 않습니다.") : ("사용가능한 비밀번호입니다."));
		}

		CustomerVO abcvo = new CustomerVO(name, birth, phone, id, pw);

		boolean successFlag;
		try {
			successFlag = cDAO.insertDB(abcvo);
			if (successFlag == true) {
				System.out.println(name + "님의 가입이 완료되었습니다.");
			} else {
				System.out.println(name + "님의 가입이 완료되지않았습니다.");
			}
		} catch (SQLException e) {
			System.out.println(e.toString());
		}
	}

	public void updateManager() // 관리자 회원정보 수정 메뉴
	{
		CustomerDAO abcdao = new CustomerDAO();
		selectManager();
		System.out.println("삭제할 아이디를 입력해주세요. ");
		System.out.print(">>");
		String id = returnRightId().getId();

		System.out.println("수정할 이름을 입력해주세요.");
		System.out.print(">>");
		String name = scan.nextLine().trim();

		Date birth = null;
		System.out.println("수정할 생년월일을 입력해주세요.(YYYY/MM/DD)");
		System.out.print(">>");
		boolean birthFlag = false;
		while (!birthFlag) {
			String stsp = scan.nextLine();
			try {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
				java.util.Date jdate = sdf.parse(stsp);
				birth = new Date(jdate.getTime());
				birthFlag = true;
			} catch (Exception e) {
				birthFlag = true;
			}
		}

		System.out.println("수정할 휴대폰번호를 입력해주세요.");
		System.out.print(">>");

		String phone = scan.nextLine().trim();

		CustomerVO abcvo = new CustomerVO();

		boolean successFlag;

		abcvo = new CustomerVO(name, birth, phone, id);

		try {
			successFlag = abcdao.updateDB(abcvo);

			if (successFlag == true) {
				System.out.println(name + "님의 회원정보 수정이 완료되었습니다!");
			} else {
				System.out.println(name + "님의 회원정보 수정이 완료되지 않았습니다!");
			}
		} catch (SQLException e) {
			System.out.println(e.toString());
		}
	}

	public void updateMypageManager(CustomerVO cvo) // 1번(로그인)->3번(마이페이지)->1번 <내정보 수정하기>
	{
		CustomerDAO abcdao = new CustomerDAO();

		System.out.println("변경할 이름을 입력해주세요.(x 입력시 기존값 사용)");
		System.out.print(">>");
		String name = scan.nextLine().trim();
		if (name.equals("x")) {
			name = cvo.getName();
		}

		Date birth = null;
		System.out.println("변경할 생년월일을 입력해주세요.(YYYY/MM/DD) (x입력시 기존값 사용) ");
		System.out.print(">> ");
		boolean birthFlag = false;
		while (!birthFlag) {
			String stsp = scan.nextLine();

			try {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
				java.util.Date jdate = sdf.parse(stsp);
				birth = new Date(jdate.getTime());
				birthFlag = true;
			} catch (Exception e) {
				birth = cvo.getBirth();
				birthFlag = true;
			}
		}

		System.out.println("변경할 휴대폰번호를 입력해주세요. (x입력시 기존값 사용) ");
		System.out.print(">> ");
		String phone = scan.nextLine().trim();
		if (phone.equals("x")) {
			phone = cvo.getPhone();
		}
		CustomerVO abcvo = new CustomerVO();

		boolean successFlag;

		abcvo = new CustomerVO(name, birth, phone, cvo.getId());

		try {
			successFlag = abcdao.updateDB(abcvo);

			if (successFlag == true) {
				System.out.println(name + "님의 회원정보 수정이 완료되었습니다!");
			} else {
				System.out.println(name + "님의 회원정보 수정이 완료되지 않았습니다!");
			}
		} catch (SQLException e) {
			System.out.println(e.toString());
		}
	}

	public void updaterRightManager() // 관리자 회원정보 수정 메뉴
	{
		CustomerDAO abcdao = new CustomerDAO();
		selectManager();
		System.out.println("권한을 수정할 ID를입력해주세요. ");
		System.out.print(">>");
		String id = returnRightId().getId();
		System.out.println("관리자 계정으로 전환하시려면 Y, 일반계정은 N을 입력해주세요.");
		System.out.print(">>");
		String admin = scan.nextLine();
		CustomerVO cvo = new CustomerVO();
		cvo.setId(id);
		if (admin.equals("Y")) {
			abcdao.updateRightADB(cvo);
			System.out.println("관리자 계정으로 전환했습니다.");
		} else {
			abcdao.updateRightNDB(cvo);
			System.out.println("일반 계정으로 전환했습니다.");
		}

	}

	public void findManager() // 관리자모드 회원찾기
	{
		CustomerDAO abcdao = new CustomerDAO();
		ArrayList<CustomerVO> CustomerVOList = new ArrayList<CustomerVO>();

		System.out.println("조회할 아이디를 입력해주세요.");
		System.out.print(">>");
		String id = scan.nextLine().trim();

		System.out.println("가입 시 등록한 휴대폰번호를 입력해주세요.");
		System.out.print(">>");
		String phone = scan.nextLine().trim();

		CustomerVOList = abcdao.selectCheckDB(id, phone);

		if (CustomerVOList == null) {
			CustomerVO.getHeader();
			System.out.println("출력할 데이터가 존재하지 않습니다.");
			return;
		}
		printCustomerVOList(CustomerVOList);
	}

	public void deleteManager() // 관리자모드 회원 삭제
	{
		CustomerDAO abcdao = new CustomerDAO();

		CustomerVO abcvo = new CustomerVO();

		selectManager();

		System.out.println("삭제할 아이디를 입력해주세요.");
		System.out.print(">>");
		String id = returnRightId().getId();
		abcvo.setId(id);

		boolean successFlag = abcdao.deleteDB(abcvo);

		if (successFlag == true) {
			System.out.println("아이디 " + id + "님의 회원 탈퇴가 완료되었습니다.");
		} else {
			System.out.println("아이디 " + id + "님의 회원 탈퇴가 완료되지 않았습니다.");
		}
	}

	public void deletemyPageManager(CustomerVO cvo) // 1번(로그인)->3번(마이페이지)->3번 <회월 탈퇴하기>
	{
		CustomerRegisterManager crm = new CustomerRegisterManager();
		CustomerDAO cdao = new CustomerDAO();
		boolean successFlag = false;
		System.out.println("회원을 탈퇴하시려면 비밀번호를 입력해주세요.");
		String pw = scan.nextLine();
		String delFlag = null;
		cvo.setPw(pw);
		cvo = crm.returnLogin(cvo);

		if (cvo.getName() == null) {
			System.out.println("비밀번호가 일치하지 않습니다.");
		} else {

			System.out.println("정말 탈퇴하시려면 Y를 입력해주세요.");
			delFlag = scan.nextLine();
			if (delFlag != null && delFlag.equals("Y")) {
				successFlag = cdao.deleteDB(cvo);
			}
		}

		if (successFlag == true) {
			System.out.println("아이디 " + cvo.getId() + "님의 회원 탈퇴가 완료되었습니다.");
		} else {
			System.out.println("아이디 " + cvo.getId() + "님의 회원 탈퇴가 완료되지 않았습니다.");
		}
	}

	// <<CustomerRegisterManager>>

	public void selectManager() // <가입자 전체 정보 출력.>
	{
		CustomerDAO abcdao = new CustomerDAO();
		ArrayList<CustomerVO> airBookingCustomerVOList = new ArrayList<CustomerVO>();

		airBookingCustomerVOList = abcdao.selectDB();

		if (airBookingCustomerVOList == null) {
			System.out.println("출력할 데이터가 존재하지 않습니다.");
			return;
		}
		printCustomerVOList(airBookingCustomerVOList);
	}

	public void selectMypageManager(CustomerVO cvo) // <가입자 전체 정보 출력.>
	{
		CustomerDAO abcdao = new CustomerDAO();
		ArrayList<CustomerVO> CustomerVOList = new ArrayList<CustomerVO>();
		CustomerVOList = abcdao.selectCheckDB(cvo.getId(), cvo.getPhone());
		printCustomerVOList(CustomerVOList);
	}

	public CustomerVO loginManager() throws SQLException {
		CustomerVO cvo = new CustomerVO();
		CustomerRegisterManager crm = new CustomerRegisterManager();

		System.out.print("아이디를 입력해주세요 : ");
		String id = scan.nextLine();

		System.out.println();

		System.out.print("비밀번호를 입력해주세요 : ");
		String pw = scan.nextLine();

		cvo.setId(id);
		cvo.setPw(pw);
		cvo = crm.returnLogin(cvo);

		if (cvo.getName() == null) {
			System.out.println("\n로그인 실패");
			return cvo;
		} else {

			return cvo;
		}
	}

	private void printCustomerVOList(ArrayList<CustomerVO> CustomerVOList) // 출력메소드.
	{
		System.out.println(CustomerVO.getHeader());
		for (CustomerVO p : CustomerVOList) {
			System.out.println(p);
		}
	}

	// 로그인기능. ID와PWD가 들어있는 CVO를 받아서 일치하는 정보가 DB에 있으면 반환 없으면 NULL로 채워진CVO반환
	public CustomerVO returnLogin(CustomerVO cvo) {
		CustomerDAO cusDAO = new CustomerDAO();
		CustomerVO cvo2 = cusDAO.selectByIdDB(cvo);
		if (cvo2.getName() == null) {
			return cvo;
		} else {
			if (cvo2.getPw().equals(cvo.getPw())) {
				return cvo2;
			} else {
				return cvo;
			}
		}
	}

	// 실행하면 적합한 id 가 나올떄까지 반복해서 올바른 cVO를 반환해주는 함수
	public CustomerVO returnRightId() {
		boolean exitFlag = false;
		CustomerVO cvo = new CustomerVO();
		CustomerDAO cDAO = new CustomerDAO();
		while (!exitFlag) {
			System.out.print(">>");
			String id = scan.nextLine();
			cvo.setId(id);
			cvo = cDAO.selectByIdDB(cvo);
			if (cvo.getName() != null) {
				exitFlag = true;
			} else {
				System.out.println("존재하지 않는 예매정보입니다.");
				System.out.print("재입력 >>");
			}

		}
		return cvo;
	}

}