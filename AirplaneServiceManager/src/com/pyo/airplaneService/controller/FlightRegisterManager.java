package com.pyo.airplaneService.controller;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

import com.pyo.airplaneService.model.FlightVO;

public class FlightRegisterManager {
	public static Scanner sc = new Scanner(System.in);

	public void selectManager() {
		FlightDAO fdao = new FlightDAO();
		ArrayList<FlightVO> flightList = new ArrayList<FlightVO>();
		flightList = fdao.selectDB();
		if (flightList == null) {
			System.out.println("데이터가 존재하지 않습니다.");
			return;
		}
		printFlightList(flightList);
	}
	
	public void findManager() {
		FlightDAO fdao = new FlightDAO();
		ArrayList<FlightVO> flightList = new ArrayList<FlightVO>();
		
		flightList = fdao.searchDB();
		if (flightList == null) {
			System.out.println("데이터가 존재하지 않습니다.");
			return;
		}
		printFlightList(flightList);
	}
	
	public void insertManager() {
		FlightDAO fdao = new FlightDAO();
		FlightVO fvo = new FlightVO();
		
		System.out.println("비행기 번호을 입력 해 주세요.");
		PlaneRegisterManager.printPlaneList();
		String planeNo = PlaneRegisterManager.returnRightNo().getNo();
		System.out.println("도착 국가의 번호를 입력 해 주세요.");
		CountryRegisterManager.printCountryList();
		String arrivalCountryNo = CountryRegisterManager.returnRightNo().getNo(); 
		System.out.print("출발 시간을 입력 해 주세요(yyyy/MM/dd HH:mm형식으로 입력.)\n>>");
		Timestamp departureHour = null;
		boolean timeFlag = false;
		while (!timeFlag) {
			String stsp = sc.nextLine();
			try {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
				java.util.Date jdate = sdf.parse(stsp);
				departureHour = new Timestamp(jdate.getTime());
				timeFlag = true;
			} catch (Exception e) {
				timeFlag = true;
			}
		}
		fvo = new FlightVO(null, planeNo, arrivalCountryNo, departureHour);
		boolean successFlag = fdao.insertDB(fvo);
		if (successFlag == true) {
			System.out.println("입력이 완료되었습니다.");
		} else {
			System.out.println("입력에 실패하였습니다. ");
		}
	}

	public void updateManager() {
		FlightDAO fdao = new FlightDAO();
		FlightVO fvo = new FlightVO();
		
		System.out.println("수정할 항공편의 번호를 입력 해 주세요\n>>");
		selectManager();
		String no = returnRightNo().getNo();
		System.out.println("비행기 번호을 입력 해 주세요.");
		PlaneRegisterManager.printPlaneList();
		String planeNo = PlaneRegisterManager.returnRightNo().getNo();
		System.out.println("도착 국가의 번호를 입력 해 주세요.");
		CountryRegisterManager.printCountryList();
		String arrivalCountryNo = CountryRegisterManager.returnRightNo().getNo(); 
		System.out.print("출발 시간을 입력 해 주세요(yyyy/MM/dd HH:mm형식으로 입력.)\n>>");
		Timestamp departureHour = null;
		boolean timeFlag = false;
		while (!timeFlag) {
			String stsp = sc.nextLine();
			try {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
				java.util.Date jdate = sdf.parse(stsp);
				departureHour = new Timestamp(jdate.getTime());
				timeFlag = true;
			} catch (Exception e) {
				timeFlag = true;
			}
		}
		fvo = new FlightVO(no, planeNo, arrivalCountryNo, departureHour);
		boolean successFlag = fdao.updateDB(fvo);

		if (successFlag == true) {
			System.out.println("수정이 완료되었습니다.");
		} else {
			System.out.println("수정에 실패하였습니다. ");
		}
	}

	public void deleteManager() {
		FlightDAO fdao = new FlightDAO();
		FlightVO fvo = new FlightVO();
		selectManager();
		System.out.println("삭제할 항공편 번호를 입력 해 주세요\n>>");
		fvo = new FlightVO();
		fvo.setNo(returnRightNo().getNo());
		
		boolean successFlag = fdao.deleteDB(fvo);

		if (successFlag == true) {
			System.out.println("삭제 완료되었습니다.");
		} else {
			System.out.println("삭제 실패했습니다. ");
		}
	}

	private void printFlightList(ArrayList<FlightVO> flightList) {
			System.out.println(FlightVO.getHeader());
		for (FlightVO fvo : flightList) {
			System.out.println(fvo.toString());
		}

	}
	
	// 실행하면 적합한 no 가 나올떄까지 반복해서 올바른 FlightVO를 반환해주는 함수
		public static FlightVO returnRightNo() {
			boolean exitFlag = false;
			FlightVO fvo = new FlightVO();
			FlightDAO fDAO = new FlightDAO();
			while (!exitFlag) {
				System.out.print(">>");
				String no =sc.nextLine();
				fvo.setNo(no);
				fvo = fDAO.selectByNoDB(fvo);
				if (fvo.getArrivalHour()!= null) {
					exitFlag = true;

				} else {
					System.out.println("존재하지 않는 항공편정입니다.");
					System.out.print("재입력 >>");
				}

			}
			return fvo;
		}
		
		public void findCountryManager() {
			FlightDAO fdao = new FlightDAO();
			ArrayList<FlightVO> flightList = new ArrayList<FlightVO>();
			CountryRegisterManager.printCountryList();
			System.out.print("항공편 출력을 원하는 나라의 번호를 입력해주세요.\n>>");
		 String arrivalCountryNo =CountryRegisterManager.returnRightNo().getNo();
			FlightVO fvo = new FlightVO();
			fvo.setArrivalCountryNo(arrivalCountryNo);
			flightList = fdao.selectCountryDB(fvo);
			if (flightList == null) {
				System.out.println("해당하는 항공편이 존재하지 않습니다.");
				return;
			}
			printFlightJoinList(flightList);
		}
		
		private void printFlightJoinList(ArrayList<FlightVO> flightList) {
				System.out.println(FlightVO.getHeader());
			for (FlightVO fjvo : flightList) {
	            System.out.println(fjvo.toString());
	        }
	    }
}
