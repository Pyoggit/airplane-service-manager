package com.pyo.airplaneService.controller;

import java.util.ArrayList;
import java.util.Scanner;

import com.pyo.airplaneService.model.BookingVO;
import com.pyo.airplaneService.model.CustomerVO;
import com.pyo.airplaneService.model.FlightVO;
import com.pyo.airplaneService.view.BookingPrint;


public class BookingRegisterManager {
	public static Scanner sc = new Scanner(System.in);

	// 예매기능-- 이용시 CustomerVO를 넣어서 입력 >>
	public void bookingManager(CustomerVO cvo) {
		SeatsRegisterManager srm = new SeatsRegisterManager();
		ArrayList<BookingVO> bvoList = new ArrayList<BookingVO>();
		FlightRegisterManager frm = new FlightRegisterManager();
		BookingVO bvo = new BookingVO();
		BookingDAO bookDAO = new BookingDAO();
		frm.selectManager();// 현재 항공편 전체 출력
		System.out.println("항공편을 입력해주세요.");
		FlightVO fvo = FlightRegisterManager.returnRightNo();// 유효한 상영정보가 나올때까지 반복한뒤 해당 fvo를저장
		System.out.println(fvo);
		System.out.println("해당 항공편을 예매합니다.");
		bvo.setFlightNo(String.valueOf(fvo.getNo()));
		int remain = srm.selectByFlightManager(bvo);
		System.out.println("합계 인원을 작성해주세요.");
		System.out.print(">>");
		int amount = 0;
		try {
			amount = Integer.parseInt(sc.nextLine());

		} catch (Exception e) {
			System.out.println("숫자를 입력해주세요.");
		}
		if (remain < amount) {
			System.out.println("예매하려는 인원이 잔여 좌석보다 많습니다.");
			return;
		}
		bvo.setCustomerNo(cvo.getNo());
		bvo.setFlightNo(String.valueOf(fvo.getNo()));
		bvo.setAmount(amount);
		bvoList = selectSeatCheckManager(bvo);// bvo에 추가된 정보로 좌석을 고른다.
		boolean flag = bookDAO.insertDB(bvoList);// Bookingtable에 정보 추가
		bvo = bookDAO.selectLastDB();// 마지막에 추가한 bvo를 반환
		System.out.println((flag) ? "예매가 완료되었습니다. 예매 코드는" + bvo.getCode() + "입니다." : "예매에 실패하였습니다.");
	}

	// 유효한 좌석 선택후 bvoList에 담아서 반환.
	private ArrayList<BookingVO> selectSeatCheckManager(BookingVO bvo) {
		SeatsDAO sDAO = new SeatsDAO();
		ArrayList<BookingVO> bvoList = new ArrayList<BookingVO>();
		ArrayList<String> sList = new ArrayList<String>();
		int count = 0;
		while (count < bvo.getAmount()) {
			System.out.println("예매를 원하는 좌석을 선택해주세요.");
			System.out.print(">> ");
			String seat = sc.nextLine();
			BookingVO nbvo = new BookingVO(bvo.getCustomerNo(), bvo.getFlightNo(), seat);
			bvo.setSeat(seat);
			boolean flag = sDAO.selectRightSeatDB(nbvo);
			for(String data : sList) {
				if(data.equals(seat)) {
					flag = false;
				}
			}
			sList.add(seat);
			if (flag) {
				bvoList.add(nbvo); // 새로운 객체를 리스트에 추가
				count++;
				System.out.println(">> 예매가 가능한 좌석입니다.");
			} else {
				System.out.println(">> 예매가 불가능한 좌석입니다.");
			}
		}
		return bvoList;
	}

	// 고객 로그인 후 예매조회
	public void bookingInquiryManager(CustomerVO cvo) {
		BookingVO bvo = new BookingVO();
		System.out.println("고객님의 현재 예매 정보 입니다.");
		bvo.setCustomerNo(cvo.getNo());
		BookingPrint.printByCustomer(bvo);
	}

	// 출력
	public void selectManager() {
		BookingDAO bDAO = new BookingDAO();
		System.out.print(BookingVO.getHeader());
		for (BookingVO data : bDAO.selectDB()) {
			System.out.println(data.toString());
		}
	}

	// 삭제
	public void deleteManager() {
		System.out.println("삭제할 예매코드를 입력해주세요.");
		BookingDAO bookDAO = new BookingDAO();
		BookingVO bvo = returnRightCode();
		BookingPrint.printByCode(bvo);
		boolean flag = bookDAO.deleteDB(bvo);
		System.out.println((flag) ? "삭제성공" : "삭제실패");

	}

	// 삭제
	public void deleteMyPageManager(CustomerVO cvo) {// 마이페이지의 취소메뉴
		System.out.println("삭제할 예매코드를 입력해주세요.");
		BookingDAO bookDAO = new BookingDAO();
		BookingVO bvo = returnRightCodeMyPage(cvo);
		bvo.setCustomerNo(cvo.getNo());
		boolean flag = bookDAO.deleteDB(bvo);
		System.out.println((flag) ? "삭제성공" : "삭제실패");

	}

	// 예매취소
	public void deleteCancleManager() {
		System.out.println("취소할 예매코드를 입력해주세요.");
		BookingDAO bookDAO = new BookingDAO();
		BookingVO bvo = returnRightCode();
		System.out.println(
				"=======================================================================================================================================================================");
		BookingPrint.printByCode(bvo);
		System.out.println(
				"=======================================================================================================================================================================");
		boolean flag = bookDAO.deleteDB(bvo);
		System.out.println((flag) ? "취소되었습니다." : "취소실패");

	}

	// 예매 변경, 예매변경은 취소, 인서트의 순으로 이루어질 예정
	public void updateByCustomerManager(CustomerVO cvo) {// 마이페이지의 예매변경메뉴
		FlightRegisterManager frm = new FlightRegisterManager();
		System.out.println("변경할 예매코드를 입력해주세요.");
		BookingDAO bookDAO = new BookingDAO();
		BookingVO bvo = returnRightCodeMyPage(cvo);
		BookingPrint.printByCode(bvo);
		boolean flag = bookDAO.deleteDB(bvo);
		// --------------------------여기까지는 취소작업
		// --------------------------밑으로는 추가작업
		SeatsRegisterManager srm = new SeatsRegisterManager();
		ArrayList<BookingVO> bvoList = new ArrayList<BookingVO>();
		frm.selectManager();// 현재 항공편 전체 출력
		System.out.println("항공편을 입력해주세요.");
		FlightVO fvo = FlightRegisterManager.returnRightNo();// 유효한 상영정보가 나올때까지 반복한뒤 해당 fvo를저장
		System.out.println(fvo);
		System.out.println("해당 항공편을 예매합니다.");
		bvo.setFlightNo(String.valueOf(fvo.getNo()));
		int remain = srm.selectByFlightManager(bvo);
		System.out.println("합계 인원을 작성해주세요.");
		System.out.print(">>");
		int amount = Integer.parseInt(sc.nextLine());
		if (remain < amount) {
			System.out.println("예매하려는 인원이 잔여 좌석보다 많습니다.");
			return;
		}
		bvo.setCustomerNo(cvo.getNo());
		bvo.setFlightNo(String.valueOf(fvo.getNo()));
		bvo.setAmount(amount);
		bvoList = selectSeatCheckManager(bvo);// bvo에 추가된 정보로 좌석을 고른다.
		flag = bookDAO.insertDB(bvoList);// Bookingtable에 정보 추가
		bvo = bookDAO.selectLastDB();// 마지막에 추가한 bvo를 반환
		System.out.println((flag) ? "예매가 완료되었습니다. 예매 코드는" + bvo.getCode() + "입니다." : "예매에 실패하였습니다.");

	}

	// 예매 변경, 예매변경은 취소, 인서트의 순으로 이루어질 예정
	public void updateManager() {

		System.out.println("예매할 고객번호를 입력해주세요.");
		System.out.print(">>");
		String customerNo = sc.nextLine();
		FlightRegisterManager frm = new FlightRegisterManager();
		System.out.println("변경할 예매코드를 입력해주세요.");
		BookingDAO bookDAO = new BookingDAO();
		BookingVO bvo = returnRightCode();
		BookingPrint.printByCode(bvo);
		boolean flag = bookDAO.deleteDB(bvo);
		// --------------------------여기까지는 취소작업
		// --------------------------밑으로는 추가작업
		SeatsRegisterManager srm = new SeatsRegisterManager();
		ArrayList<BookingVO> bvoList = new ArrayList<BookingVO>();
		frm.selectManager();// 현재 항공편 전체 출력
		System.out.println("항공편을 입력해주세요.");
		FlightVO fvo = FlightRegisterManager.returnRightNo();// 유효한 상영정보가 나올때까지 반복한뒤 해당 fvo를저장
		System.out.println(fvo);
		System.out.println("해당 항공편을 예매합니다.");
		bvo.setFlightNo(String.valueOf(fvo.getNo()));
		int remain = srm.selectByFlightManager(bvo);
		System.out.println("합계 인원을 작성해주세요.");
		System.out.print(">>");
		int amount = Integer.parseInt(sc.nextLine());
		if (remain < amount) {
			System.out.println("예매하려는 인원이 잔여 좌석보다 많습니다.");
			return;
		}
		bvo.setCustomerNo(customerNo);
		bvo.setFlightNo(String.valueOf(fvo.getNo()));
		bvo.setAmount(amount);
		bvoList = selectSeatCheckManager(bvo);// bvo에 추가된 정보로 좌석을 고른다.
		flag = bookDAO.insertDB(bvoList);// Bookingtable에 정보 추가
		bvo = bookDAO.selectLastDB();// 마지막에 추가한 bvo를 반환
		System.out.println((flag) ? "예매가 완료되었습니다. 예매 코드는" + bvo.getCode() + "입니다." : "예매에 실패하였습니다.");

	}

	// 입력
	public void insertManager() {
		SeatsRegisterManager srm = new SeatsRegisterManager();
		CustomerRegisterManager crm = new CustomerRegisterManager();
		FlightRegisterManager frm = new FlightRegisterManager();
		BookingDAO bookDAO = new BookingDAO();
		BookingVO bvo = new BookingVO();
		ArrayList<BookingVO> bvoList = new ArrayList<BookingVO>();
		crm.selectManager();
		System.out.println("새로운 고객정보를 입력해주세요..");
		System.out.print(">>");
		String cusNo = sc.nextLine();
		frm.selectManager();
		System.out.println("새로운 항공편을 입력해주세요.");
		String flNo = FlightRegisterManager.returnRightNo().getNo();
		System.out.println("합계 인원을 작성해주세요.");
		System.out.print(">>");
		int amount = Integer.parseInt(sc.nextLine());
		bvo.setCustomerNo(cusNo);
		bvo.setFlightNo(flNo);
		bvo.setAmount(amount);
		srm.selectByFlightManager(bvo);// 항공편에 맞는 좌석정보 출력
		bvoList = selectSeatCheckManager(bvo);// bvo에 추가된 정보로 좌석을 고른다.
		boolean flag = bookDAO.insertDB(bvoList);// Bookingtable에 정보 추가
		bvo = bookDAO.selectLastDB();// 마지막에 추가한 bvo를 반환
		System.out.println((flag) ? "입력성공 : " + bvo.getCode() : "입력실패");
	}

	// 찾기
	public void findManager() {
		BookingDAO bDAO = new BookingDAO();
		BookingVO bvo = new BookingVO();
		System.out.println("찾으려는 예매 코드를 입력해주세요.");
		System.out.print(">>");
		String code = sc.nextLine();
		bvo.setCode(code);
		bvo = bDAO.selectByCodeDB(bvo);
		if (bvo.getNo() != null) {
			System.out.println(
					"=======================================================================================================================================================================");
			BookingPrint.printByCode(bvo);
			System.out.println(
					"=======================================================================================================================================================================");
		} else {
			System.out.println("존재하지 않는 상영정보입니다.");
		}

	}

	// 실행하면 적합한 code 가 나올떄까지 반복해서 올바른 BookingVO를 반환해주는 함수
	private BookingVO returnRightCode() {
		boolean exitFlag = false;
		BookingVO bvo = new BookingVO();
		BookingDAO bookDAO = new BookingDAO();
		while (!exitFlag) {
			System.out.print(">>");
			String code = sc.nextLine();
			bvo.setCode(code);
			bvo = bookDAO.selectByCodeDB(bvo);
			if (bvo.getBookingDate() != null) {
				exitFlag = true;

			} else {
				System.out.println("존재하지 않는 예매정보입니다.");
				System.out.print("재입력 >>");
			}

		}
		return bvo;
	}

	// 실행하면 적합한 code 가 나올떄까지 반복해서 올바른 BookingVO를 반환해주는 함수
	private BookingVO returnRightCodeMyPage(CustomerVO cvo) {
		boolean exitFlag = false;
		BookingVO bvo = new BookingVO();
		BookingDAO bookDAO = new BookingDAO();
		while (!exitFlag) {
			System.out.print(">>");
			String code = sc.nextLine();
			bvo.setCode(code);
			bvo.setCustomerNo(cvo.getNo());
			bvo = bookDAO.selectByCodeAndCusNoDB(bvo);
			if (bvo.getBookingDate() != null) {
				exitFlag = true;

			} else {
				System.out.println("존재하지 않는 예매정보입니다.");
				System.out.print("재입력 >>");
			}

		}
		return bvo;
	}

}