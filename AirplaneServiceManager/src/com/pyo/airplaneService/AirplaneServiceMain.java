package com.pyo.airplaneService;

import java.sql.SQLException;
import java.util.Scanner;

import com.pyo.airplaneService.controller.BookingRegisterManager;
import com.pyo.airplaneService.controller.CountryRegisterManager;
import com.pyo.airplaneService.controller.CustomerRegisterManager;
import com.pyo.airplaneService.controller.FlightRegisterManager;
import com.pyo.airplaneService.controller.PlaneRegisterManager;
import com.pyo.airplaneService.controller.SeatsRegisterManager;
import com.pyo.airplaneService.model.BookingVO;
import com.pyo.airplaneService.model.CustomerVO;
import com.pyo.airplaneService.view.CUSTOMER_LOGIN_CHOICE;
import com.pyo.airplaneService.view.FLIGHT_SEARCH_MENU_CHOICE;
import com.pyo.airplaneService.view.MANAGER_LOGIN_CHOICE;
import com.pyo.airplaneService.view.MANAGE_CUSTOMER_MENU_CHOICE;
import com.pyo.airplaneService.view.MANAGE_SUB_MENU_CHOICE;
import com.pyo.airplaneService.view.MENU_CHOICE;
import com.pyo.airplaneService.view.MYBOOKING__MENU_CHOICE;
import com.pyo.airplaneService.view.MYPAGE_MENU_CHOICE;
import com.pyo.airplaneService.view.MenuViewer;



public class AirplaneServiceMain {
	public static Scanner sc = new Scanner(System.in);
	
	// 메인
	public static void main(String[] args) throws SQLException {
		int no = 0;
		CustomerRegisterManager crm = new CustomerRegisterManager();
		boolean exitFlag = false;
		while (!exitFlag) {
			MenuViewer.mainMenuView();
			try {
				no = Integer.parseInt(sc.nextLine());
			} catch (Exception e) {
				System.out.println("숫자를 입력해주세요.");
			}
			switch (no) {
			case MENU_CHOICE.LOGIN:
			exitFlag = loginMenu();
				break;
			case MENU_CHOICE.PRINTFLIGHT:
				flightSearchMenu(); // 운항정보 출력후, 해당 운항정보의남은 좌석 확인 또는 나가기
				break;
			case MENU_CHOICE.REGISTER:
				crm.insertRegistManager();
				break;
			case MENU_CHOICE.END:
				System.out.println("프로그램을 종료합니다.");
				exitFlag = true;
				break;
			}
		}

	}
	
	//관리자 로그인 메뉴
	private static boolean managerLoginMenu() {
		int no = 0;
		while (true) {
			MenuViewer.managerLoginMenuView();
			try {
				no = Integer.parseInt(sc.nextLine());
			} catch (Exception e) {
				System.out.println("숫자를 입력해주세요.");
			}
			switch (no) {
			case MANAGER_LOGIN_CHOICE.CUSTOMER:
				manageCustomerMenu();
				break;
			case MANAGER_LOGIN_CHOICE.PLANE:
				managePlaneMenu();
				break;
			case MANAGER_LOGIN_CHOICE.COUNTRY:
				manageCountryMenu();
				break;
			case MANAGER_LOGIN_CHOICE.FLIGHT:
				manageFlightMenu();
				break;
			case MANAGER_LOGIN_CHOICE.BOOKING:
				mangeBookingMenu();
				break;
			case MANAGER_LOGIN_CHOICE.LOGOUT:
				return false;
			case MANAGER_LOGIN_CHOICE.END:
				System.out.println("프로그램을 종료합니다.");
				return true;
			}
		}
		
	}

	//관리자모드 예약관리
	private static void mangeBookingMenu() {
		int no = 0;
		boolean exitFlag = false;
		BookingRegisterManager brm = new BookingRegisterManager();
		while (!exitFlag) {
			MenuViewer.manageBookingMenuView();
			try {
				no = Integer.parseInt(sc.nextLine());
			} catch (Exception e) {
				System.out.println("숫자를 입력해주세요.");
			}
			try {
				switch (no) {
				case MANAGE_SUB_MENU_CHOICE.SELECT:
					brm.selectManager();
					break;
				case MANAGE_SUB_MENU_CHOICE.INSERT:
					brm.insertManager();;
					break;
				case MANAGE_SUB_MENU_CHOICE.UPDATE:
					brm.updateManager();
					break;
				case MANAGE_SUB_MENU_CHOICE.DELETE:
					brm.deleteManager();
					break;
				case MANAGE_SUB_MENU_CHOICE.FIND:
					brm.findManager();
					break;
				case MANAGE_SUB_MENU_CHOICE.END:
					System.out.println("이전메뉴로 돌아갑니다.");
					exitFlag = true;
					break;
				default:

				}

			} catch (Exception e) {
			}
		}
		
	}
	
	//관리자모드 항공편 관리
	private static void manageFlightMenu() {
		int no = 0;
		boolean exitFlag = false;
		FlightRegisterManager frm = new FlightRegisterManager();
		while (!exitFlag) {
			MenuViewer.manageFlightMenuView();
			try {
				no = Integer.parseInt(sc.nextLine());
			} catch (Exception e) {
				System.out.println("숫자를 입력해주세요.");
			}
			try {
				switch (no) {
				case MANAGE_SUB_MENU_CHOICE.SELECT:
					frm.selectManager();
					break;
				case MANAGE_SUB_MENU_CHOICE.INSERT:
					frm.insertManager();;
					break;
				case MANAGE_SUB_MENU_CHOICE.UPDATE:
					frm.updateManager();
					break;
				case MANAGE_SUB_MENU_CHOICE.DELETE:
					frm.deleteManager();
					break;
				case MANAGE_SUB_MENU_CHOICE.FIND:
					frm.findManager();
					break;
				case MANAGE_SUB_MENU_CHOICE.END:
					System.out.println("이전메뉴로 돌아갑니다.");
					exitFlag = true;
					break;
				default:

				}

			} catch (Exception e) {
			}
		}
		
	}

	//관리자모드 나라 관리
	private static void manageCountryMenu() {
		int no = 0;
		boolean exitFlag = false;
		CountryRegisterManager ctrm = new CountryRegisterManager();
		while (!exitFlag) {
			MenuViewer.manageCountryMenuView();
			try {
				no = Integer.parseInt(sc.nextLine());
			} catch (Exception e) {
				System.out.println("숫자를 입력해주세요.");
			}
			try {
				switch (no) {
				case MANAGE_SUB_MENU_CHOICE.SELECT:
					ctrm.selectManager();
					break;
				case MANAGE_SUB_MENU_CHOICE.INSERT:
					ctrm.insertManager();;
					break;
				case MANAGE_SUB_MENU_CHOICE.UPDATE:
					ctrm.updateManager();
					break;
				case MANAGE_SUB_MENU_CHOICE.DELETE:
					ctrm.deleteManager();
					break;
				case MANAGE_SUB_MENU_CHOICE.FIND:
					ctrm.findManager();
					break;
				case MANAGE_SUB_MENU_CHOICE.END:
					System.out.println("이전메뉴로 돌아갑니다.");
					exitFlag = true;
					break;
				default:

				}

			} catch (Exception e) {
			}
		}
		
	}

	//관리자모드 비횅기 관리
	private static void managePlaneMenu() {
		int no = 0;
		boolean exitFlag = false;
		PlaneRegisterManager prm = new PlaneRegisterManager();
		while (!exitFlag) {
			MenuViewer.managePlaneMenuView();
			try {
				no = Integer.parseInt(sc.nextLine());
			} catch (Exception e) {
				System.out.println("숫자를 입력해주세요.");
			}
			try {
				switch (no) {
				case MANAGE_SUB_MENU_CHOICE.SELECT:
					prm.selectManager();
					break;
				case MANAGE_SUB_MENU_CHOICE.INSERT:
					prm.insertManager();;
					break;
				case MANAGE_SUB_MENU_CHOICE.UPDATE:
					prm.updateManager();
					break;
				case MANAGE_SUB_MENU_CHOICE.DELETE:
					prm.deleteManager();
					break;
				case MANAGE_SUB_MENU_CHOICE.FIND:
					prm.findManager();
					break;
				case MANAGE_SUB_MENU_CHOICE.END:
					System.out.println("이전메뉴로 돌아갑니다.");
					exitFlag = true;
					break;
				default:

				}

			} catch (Exception e) {
			}
		}
	}
	
	//관리자 모드 고객 관리
	private static void manageCustomerMenu() {
		int no = 0;
		boolean exitFlag = false;
		CustomerRegisterManager crm = new CustomerRegisterManager();
		while (!exitFlag) {
			MenuViewer.manageCustomerMenuView();
			try {
				no = Integer.parseInt(sc.nextLine());
			} catch (Exception e) {
				System.out.println("숫자를 입력해주세요.");
			}
			try {
				switch (no) {
				case MANAGE_CUSTOMER_MENU_CHOICE.SELECT:
					crm.selectManager();
					break;
				case MANAGE_CUSTOMER_MENU_CHOICE.INSERT:
					crm.insertManager();
					break;
				case MANAGE_CUSTOMER_MENU_CHOICE.UPDATE:
					crm.updateManager();
					break;
				case MANAGE_CUSTOMER_MENU_CHOICE.DELETE:
					crm.deleteManager();
					break;
				case MANAGE_CUSTOMER_MENU_CHOICE.FIND:
					crm.findManager();
					break;
				case MANAGE_CUSTOMER_MENU_CHOICE.RIGHT:
					crm.updaterRightManager();
					break;
				case MANAGE_CUSTOMER_MENU_CHOICE.END:
					System.out.println("이전메뉴로 돌아갑니다.");
					exitFlag = true;
					break;
				default:

				}

			} catch (Exception e) {
			}
		}
		
	}

	//고객 로그인 매뉴
	private static boolean CustomerLoginMenu(CustomerVO cvo) {
		int no = 0;
		BookingRegisterManager brm = new BookingRegisterManager();
		while (true) {
			MenuViewer.CustomerLoginMenuView(cvo);
			try {
				no = Integer.parseInt(sc.nextLine());
			} catch (Exception e) {
				System.out.println("숫자를 입력해주세요.");
			}
			switch (no) {
			case CUSTOMER_LOGIN_CHOICE.BOOKING:
				brm.bookingManager(cvo);
				break;
			case CUSTOMER_LOGIN_CHOICE.MYBOOKING:
				myBookingMenu(cvo);
				break;
			case CUSTOMER_LOGIN_CHOICE.MYPAGE:
				myPageMenu(cvo);
				break;
			case CUSTOMER_LOGIN_CHOICE.FLIGHT:
				flightSearchMenu();
				break;
			case CUSTOMER_LOGIN_CHOICE.LOGOUT:
				return false;
			case MANAGER_LOGIN_CHOICE.END:
				System.out.println("프로그램을 종료합니다.");
				return true;
			}
		}
		
	}
	
	//개인 예매 메뉴
	private static void myBookingMenu(CustomerVO cvo) {
		BookingRegisterManager brm = new BookingRegisterManager();
		int no = 0;
		boolean exitFlag = false;
		
		while (!exitFlag) {
			brm.bookingInquiryManager(cvo);
			MenuViewer.myBookingMenuView();
			try {
				no = Integer.parseInt(sc.nextLine());
			} catch (Exception e) {
				System.out.println("숫자를 입력해주세요.");
			}
			switch (no) {
			case MYBOOKING__MENU_CHOICE.CANCLE:
				brm.deleteMyPageManager(cvo);
				break;
			case MYBOOKING__MENU_CHOICE.UPDATE:
				brm.updateByCustomerManager(cvo);
				break;
			case MYBOOKING__MENU_CHOICE.END:
				System.out.println("메인화면으로 돌아갑니다.");
				exitFlag = true;
				break;
			}
		}
		
	}

	// 마이페이지 메뉴.
	private static void myPageMenu(CustomerVO cvo){
		CustomerRegisterManager crm = new CustomerRegisterManager();
		int no = 0;
		boolean exitFlag = false;
		while (!exitFlag) {
			crm.selectMypageManager(cvo);
			MenuViewer.myPageMenuView();
			try {
				no = Integer.parseInt(sc.nextLine());
			} catch (Exception e) {
				System.out.println("숫자를 입력해주세요.");
			}
			switch (no) {
			case MYPAGE_MENU_CHOICE.INFO_UPDATE :
				crm.updateMypageManager(cvo);
				break;
			case MYPAGE_MENU_CHOICE.INFO_DELETE :
				crm.deletemyPageManager(cvo);
				break;
			case MYPAGE_MENU_CHOICE.END :
				System.out.println("프로그램을 종료합니다.");
				exitFlag = true;
				break;
			}
		}
		
	}

	//항공검색 메뉴
	// 항공 검색 로그인 메뉴.
	private static void flightSearchMenu() {
		FlightRegisterManager frm = new FlightRegisterManager();

		int no = 0;
		boolean exitFlag = false;
		frm.selectManager();
		while (!exitFlag) {
			MenuViewer.flightSearchMenuView();
			try {
				no = Integer.parseInt(sc.nextLine());
			} catch (Exception e) {
				System.out.println("숫자를 입력해주세요.");
			}
			switch (no) {
			case FLIGHT_SEARCH_MENU_CHOICE.SEATS:
				flightSeatsMenu();	
				break;
			case FLIGHT_SEARCH_MENU_CHOICE.FIND:
				frm.findCountryManager();	
				break;
			case FLIGHT_SEARCH_MENU_CHOICE.END:
				System.out.println("프로그램을 종료합니다.");
				exitFlag = true;
				break;
			}
		}
		
	}

	//항공 좌석조회 메뉴
	// 항공 좌석확인 로그인 메뉴.
	private static void flightSeatsMenu() {
		SeatsRegisterManager srm = new SeatsRegisterManager();
		BookingVO bvo = new BookingVO();
		System.out.println("좌석을 조회할 비행편 번호를 입력해주세요.");
		bvo.setFlightNo(FlightRegisterManager.returnRightNo().getNo());
		srm.selectByFlightManager(bvo);
	}
	
	// 로그인 메뉴.
    public static boolean loginMenu() throws SQLException
    {
        CustomerRegisterManager crm = new CustomerRegisterManager();
        CustomerVO cvo = crm.loginManager();
       
        boolean returnFlag = false;
        
        if (cvo.getName() == null)
        {
            return false;
        } else
        {
            if (cvo.getRight() != 0)
            {	
                returnFlag = managerLoginMenu();
            } else
            {
                returnFlag = CustomerLoginMenu(cvo);
            }
        }
        return returnFlag;
    }

}