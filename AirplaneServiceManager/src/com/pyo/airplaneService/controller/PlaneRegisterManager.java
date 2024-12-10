package com.pyo.airplaneService.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import com.pyo.airplaneService.model.PlaneDAO;
import com.pyo.airplaneService.model.PlaneVO;


public class PlaneRegisterManager {
	public static Scanner sc = new Scanner(System.in);

	// 여객기 리스트 출력
	public static void printPlaneList() {
		PlaneDAO planeDAO = new PlaneDAO();
		ArrayList<PlaneVO> planes = planeDAO.selectPlaneDB();
		if (planes != null && !planes.isEmpty()) {
			System.out.println(PlaneVO.getHeader());
			for (PlaneVO plane : planes) {
				System.out.println(plane);
			}
		} else {
			System.out.println("등록된 여객기 정보가 없습니다.");
		}
	}

	// 여객기 데이터 출력 (SELECT)
	public void selectManager() throws SQLException {
		printPlaneList();
	}

	// 여객기 정보 생성
	public void insertManager() throws SQLException {
		PlaneDAO planeDAO = new PlaneDAO();

		System.out.println("새로운 여객기 이름을 입력해주세요. ");
		System.out.print(">>");
		String name = sc.nextLine();
		System.out.println("좌석의 행 개수를 입력해주세요. ");
		System.out.print(">>");
		int rowX = Integer.parseInt(sc.nextLine());
		
		int colY;
		do {
			System.out.print("좌석의 열 개수를 입력해주세요.(짝수만 가능) ");
			System.out.print(">>");
			colY = Integer.parseInt(sc.nextLine());
			if (colY % 2 != 0) {
				System.out.println("좌석의 열 개수는 짝수여야 합니다 다시입력하세요. ");
			}
			
		} while (colY % 2 != 0);
		
		PlaneVO plane = new PlaneVO(null, name, rowX, colY, 0);
		boolean successFlag = planeDAO.insertPlaneDB(plane);
		System.out.println((successFlag) ? "입력 성공" : "입력 실패");
	}

	// 여객기 정보 수정
	public void updateManager() throws SQLException {
		PlaneDAO planeDAO = new PlaneDAO();

		printPlaneList();
		System.out.println("수정할 여객기의 코드를 입력하세요.");
		System.out.print(">>");
		PlaneVO existingPlane = returnRightNo();
		if (existingPlane == null) {
			System.out.println("해당 여객기 정보가 존재하지 않습니다.");
			return;
		}

		System.out.println("수정할 여객기 이름을 입력해주세요. ");
		System.out.print(">>");
		String name = sc.nextLine();
		System.out.println("수정할 좌석의 행 개수를 입력해주세요 ");
		System.out.print(">>");
		int rowX = Integer.parseInt(sc.nextLine());
		int colY;
		do {
			System.out.print("수정할 좌석의 열 개수를 입력해주세요(짝수만 가능): ");
			colY = Integer.parseInt(sc.nextLine());
			if (colY % 2 != 0) {
				System.out.println("좌석의 열 개수는 짝수여야 합니다 다시입력하세요. ");
			}
			
		} while (colY % 2 != 0);

		existingPlane.setName(name);
		existingPlane.setRowX(rowX);
		existingPlane.setColY(colY);

		boolean successFlag = planeDAO.updatePlaneDB(existingPlane);
		System.out.println((successFlag) ? "수정 성공" : "수정 실패");
	}

	// 여객기 정보 삭제
	public void deleteManager() throws SQLException {
		PlaneDAO planeDAO = new PlaneDAO();

		printPlaneList();
		System.out.print("삭제할 여객기의 코드를 입력하세요:");
		PlaneVO existingPlane = returnRightNo();
		if (existingPlane == null) {
			System.out.println("해당 여객기 정보가 존재하지 않습니다.");
			return;
		}

		boolean successFlag = planeDAO.deletePlaneDB(existingPlane.getNo());
		System.out.println((successFlag) ? "삭제 성공" : "삭제 실패");
	}

	// 여객기 정보 찾기
	public void findManager() throws SQLException {
		PlaneDAO planeDAO = new PlaneDAO();

		System.out.println("찾으려는 여객기 코드를 입력해주세요 ");
		System.out.print(">>");
		String no = sc.nextLine();
		PlaneVO plane = planeDAO.findPlaneByNo(no);
		if (plane != null) {
			System.out.println(PlaneVO.getHeader());
			System.out.println(plane);
		} else {
			System.out.println("해당 여객기 정보가 존재하지 않습니다.");
		}
	}

	// 유효한 여객기 코드를 입력받아 올바른 PlaneVO를 반환해주는 함수
	public static PlaneVO returnRightNo() {
		boolean exitFlag = false;
		PlaneVO plane = null;
		PlaneDAO planeDAO = new PlaneDAO();
		while (!exitFlag) {
			System.out.print(">> ");
			String no = sc.nextLine();
			plane = planeDAO.findPlaneByNo(no);
			if (plane != null) {
				exitFlag = true;
			} else {
				System.out.println("존재하지 않는 여객기 정보입니다. 다시 입력해주세요.");
				System.out.print("재입력 >> ");
			}
		}
		return plane;
	}
}