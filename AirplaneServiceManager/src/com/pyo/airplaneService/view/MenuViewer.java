package com.pyo.airplaneService.view;

import com.pyo.airplaneService.model.CustomerVO;

public class MenuViewer {

	// 메인 메뉴
	public static void mainMenuView() {
		System.out.println("\n환영합니다.");
		System.out.println("╔═══════════════════════════════════════════════════════════╗");
		System.out.println("║ 1. 로그인 │ 2. 이용가능한 항공편 확인 │ 3. 회원가입 │ 4. 프로그램 종료 ");
		System.out.println("╚═══════════════════════════════════════════════════════════╝");
		System.out.println("메뉴를 선택해주세요");
		System.out.print(">> ");
	}

	// 고객 로그인 이후 메뉴
	public static void CustomerLoginMenuView(CustomerVO cvo) {
		System.out.println("\n"+cvo.getName()+"님 환영합니다. 메뉴를 선택해주세요.");
		System.out.println("╔════════════════════════════════════════════════════════════════════════════════════╗");
		System.out.println("║ 1. 예매하기 │ 2. 나의 예매내역 │ 3. 마이페이지 │ 4. 이용가능한 항공편 확인 │ 5. 로그아웃 │ 6. 종료 ");
		System.out.println("╚════════════════════════════════════════════════════════════════════════════════════╝");
		System.out.println("메뉴를 선택해주세요");
		System.out.print(">> ");
	}

	// 관리자 모드 메뉴
	public static void managerLoginMenuView() {
		System.out.println("\n관리자 모드입니다.");
		System.out.println("╔═════════════════════════════════════════════════════════════════════════════════════════════════╗");
		System.out.println("║ 1. 고객정보 관리 │ 2. 여객기 관리 │ 3. 나라 목록 관리 │ 4. 항공편 관리 │ 5. 예매현황 관리 │ 6. 로그아웃 │ 7. 종료 ");
		System.out.println("╚═════════════════════════════════════════════════════════════════════════════════════════════════╝");
		System.out.println("메뉴를 선택해주세요");
		System.out.print(">> ");
	}

	// 관리자모드의 고객 데이터 확인 메뉴
	public static void manageCustomerMenuView() {
		System.out.println("\n고객 데이터 관리화면입니다.");
		System.out.println("╔════════════════════════════════════════════════════════════════════════════════════════════════╗");
		System.out.println("║ 1. 고객 데이터 출력 │ 2. 데이터 생성 │ 3. 데이터 수정 │ 4. 데이터 삭제 │ 5. 데이터 찾기 │ 6. 권한설정 │ 7. 뒤로가기 ");
		System.out.println("╚════════════════════════════════════════════════════════════════════════════════════════════════╝");
		System.out.println("메뉴를 선택해주세요");
		System.out.print(">> ");
	}

	// 관리자모드의 여객기 데이터 확인 메뉴
	public static void managePlaneMenuView() {
		System.out.println("\n여객기 데이터 관리화면입니다.");
		System.out.println("╔═══════════════════════════════════════════════════════════════════════════════════════╗");
		System.out.println("║ 1. 여객기 데이터 출력 │ 2. 데이터 생성 │ 3. 데이터 수정 │ 4. 데이터 삭제 │ 5. 데이터 찾기 │ 6. 뒤로가기 ");
		System.out.println("╚═══════════════════════════════════════════════════════════════════════════════════════╝");
		System.out.println("메뉴를 선택해주세요");
		System.out.print(">> ");
	}

	// 관리자모드의 여행국 데이터 확인 메뉴
	public static void manageCountryMenuView() {
		System.out.println("\n여행국 데이터 관리화면입니다.");
		System.out.println("╔═══════════════════════════════════════════════════════════════════════════════════════╗");
		System.out.println("║ 1. 여행국 데이터 출력 │ 2. 데이터 생성 │ 3. 데이터 수정 │ 4. 데이터 삭제 │ 5. 데이터 찾기 │ 6. 뒤로가기 ");
		System.out.println("╚═══════════════════════════════════════════════════════════════════════════════════════╝");
		System.out.println("메뉴를 선택해주세요");
		System.out.print(">> ");
	}

	// 관리자모드의 항공편 데이터 확인 메뉴
	public static void manageFlightMenuView() {
		System.out.println("\n항공편 데이터 관리화면입니다.");
		System.out.println("╔═══════════════════════════════════════════════════════════════════════════════════════╗");
		System.out.println("║ 1. 항공편 데이터 출력 │ 2. 데이터 생성 │ 3. 데이터 수정 │ 4. 데이터 삭제 │ 5. 데이터 찾기 │ 6. 뒤로가기 ");
		System.out.println("╚═══════════════════════════════════════════════════════════════════════════════════════╝");
		System.out.println("메뉴를 선택해주세요");
		System.out.print(">> ");
	}

	// 관리자모드의 고객 데이터 확인 메뉴
	public static void manageBookingMenuView() {
		System.out.println("\n예매 데이터 관리화면입니다.");
		System.out.println("╔═════════════════════════════════════════════════════════════════════════════════════════╗");
		System.out.println("║ 1. 예매 데이터 출력 │ 2. 예매 데이터 생성 │ 3. 데이터 수정 │ 4. 데이터 삭제 │ 5. 데이터 찾기 │ 6. 뒤로가기 ");
		System.out.println("╚═════════════════════════════════════════════════════════════════════════════════════════╝");
		System.out.println("메뉴를 선택해주세요");
		System.out.print(">> ");
	}
	
	// 항공편 출력후 메뉴
	public static void flightSearchMenuView() {
		System.out.println("╔═══════════════════════════════════════╗");
		System.out.println("║ 1. 잔여 좌석 확인 │ 2. 항공편 조회 │ 3. 나가기 ");
		System.out.println("╚═══════════════════════════════════════╝");
		System.out.print(">> ");
	}
	
	// 일반 고객 로그인후 마이페이지 메뉴
	public static void myPageMenuView() {
		System.out.println("╔════════════════════════════════════╗");
		System.out.println("║ 1. 내정보 수정하기 │2. 회원 탈퇴 | 3. 나가기 ");
		System.out.println("╚════════════════════════════════════╝");
		System.out.print(">> ");
	}

	// 일반 고객 로그인후 내 예매 확인 메뉴
	public static void myBookingMenuView() {
		System.out.println("╔═══════════════════════════════════════╗");
		System.out.println("║ 1. 예매 취소하기 │ 2. 예매 변경하기 │ 3. 나가기 ");
		System.out.println("╚═══════════════════════════════════════╝");
		System.out.print(">> ");
	}
}