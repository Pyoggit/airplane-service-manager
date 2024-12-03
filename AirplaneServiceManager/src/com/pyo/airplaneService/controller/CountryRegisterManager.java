package com.pyo.airplaneService.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import com.pyo.airplaneService.model.CountryVO;

public class CountryRegisterManager {
	public static Scanner sc = new Scanner(System.in);

	// 여행국가 리스트 출력
	public static void printCountryList(){
		CountryDAO countryDAO = new CountryDAO();
		ArrayList<CountryVO> countries = countryDAO.selectCountryDB();
		System.out.print(CountryVO.getHeader()); 
		if (countries != null && !countries.isEmpty()) {
			for (CountryVO country : countries) {
				System.out.println(country);
			}
		} else {
			System.out.println("등록된 여행 국가 정보가 없습니다.");
		}
	}

	// 여행국 정보 출력
	public void selectManager() throws SQLException {
		printCountryList();
	}

	// 여행국 정보 입력
	public void insertManager() throws SQLException {
		CountryDAO countryDAO = new CountryDAO();

		System.out.println("새로운 국가 이름을 입력해주세요 ");
		System.out.print(">>");
		String name = sc.nextLine();
		System.out.println("비행 거리를 입력해주세요 (km) ");
		System.out.print(">>");
		int distance = Integer.parseInt(sc.nextLine());

		CountryVO country = new CountryVO(name, distance);
		boolean successFlag = countryDAO.insertCountryDB(country);
		System.out.println((successFlag) ? "입력 성공" : "입력 실패");
	}

	// 여행국 정보 수정
	public void updateManager() throws SQLException {
		CountryDAO countryDAO = new CountryDAO();

		printCountryList();
		System.out.println("수정할 국가의 코드를 입력해주세요.");
		System.out.print(">>");
		CountryVO existingCountry = returnRightNo();
		if (existingCountry == null) {
			System.out.println("해당 국가 정보가 존재하지 않습니다.");
			return;
		}

		System.out.println("수정할 국가 이름을 입력해주세요. ");
		System.out.print(">>");
		String name = sc.nextLine();
		System.out.println("수정할 비행 거리를 입력해주세요 (km). ");
		System.out.print(">>");
		int distance = Integer.parseInt(sc.nextLine());

		existingCountry.setName(name);
		existingCountry.setDistance(distance);

		boolean successFlag = countryDAO.updateCountryDB(existingCountry);
		System.out.println((successFlag) ? "수정 성공" : "수정 실패");
	}

	// 여행국 정보 삭제
	public void deleteManager() throws SQLException {
		CountryDAO countryDAO = new CountryDAO();

		printCountryList();
		System.out.println("삭제할 국가의 코드를 입력하세요.");
		System.out.print(">>");
		CountryVO existingCountry = returnRightNo();
		if (existingCountry == null) {
			System.out.println("해당 국가 정보가 존재하지 않습니다.");
			return;
		}

		boolean successFlag = countryDAO.deleteCountryDB(existingCountry.getNo());
		System.out.println((successFlag) ? "삭제 성공" : "삭제 실패");
	}

	// 여행 국가 정보 찾기
	public void findManager() throws SQLException {
		CountryDAO countryDAO = new CountryDAO();

		System.out.println("찾으려는 국가 코드를 입력해주세요. ");
		System.out.print(">>");
		String no = sc.nextLine();
		CountryVO country = countryDAO.findCountryByNo(no);
		if (country != null) {
			CountryVO.getHeader();
			System.out.println(country);
		} else {
			System.out.println("해당 국가 정보가 존재하지 않습니다.");
		}
	}

	// 유효한 국가 코드를 입력받아 올바른 CountryVO를 반환해주는 함수
	public static CountryVO returnRightNo(){
		boolean exitFlag = false;
		CountryVO country = null;
		CountryDAO countryDAO = new CountryDAO();
		while (!exitFlag) {
			System.out.print(">> ");
			String no = sc.nextLine();
			country = countryDAO.findCountryByNo(no);
			if (country != null) {
				exitFlag = true;
			} else {
				System.out.println("존재하지 않는 국가 정보입니다. 다시 입력해주세요.");
				System.out.print("재입력>> ");
			}
		}
		return country;
	}


}