package cinema;

import java.util.Scanner;

import cinema.customer.service.CoustomerService;
import cinema.customer.service.CustomerServiceImpl;
import cinema.dtos.CustomerDto;
import cinema.exception.CustomerException;
import cinema.dtos.TicketDto;
import cinema.exception.TicketException;
import cinema.ticket.service.TicketService;
import cinema.ticket.service.TicketServiceImpl;

public class CinemaUi {

	private TicketService TicketSvc;
	private CoustomerService CustomerSvc;
	private static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		new CinemaUi().go();
	}

	private void go() {
		init();
		while (true) {
			mainMenu(); // 메인메뉴 출력
		}
	}

	public void init() {
		TicketSvc = new TicketServiceImpl();
		CustomerSvc = new CustomerServiceImpl();
	}

	private void mainMenu() {
		System.out.println("메인메뉴: (1)로그인 (2)회원가입 (3)종료");
		System.out.print("메뉴 선택: ");
		int menu = Integer.parseInt(sc.nextLine());
		CustomerDto user = null;
		
		if (menu == 1) {
			System.out.print("아이디를 입력하세요: ");
			String id = sc.nextLine();
			user = login(id);
			int isManager = 0;
			
			if(user == null) {
				System.out.println("아이디가 존재하지 않습니다");
				return;
			}  else {
				isManager = user.getCnum();
			}
			// cNum이 1000인 경우 관리자
			if(isManager == 1000) {
				manager();
			} else {
				member();
			}
		} else if (menu == 2) {
			member();
		} else if (menu == 3) {
			signUpMenu();
		} else if (menu == 4) {
			signUp();
		} else if (menu == 3) {
			user = null;
			System.exit(0);
		} else {
			System.out.println("비정상적인 접근입니다.");
		}
	}

	private CustomerDto login(String id) {
		CustomerDto dto = null;
		//dto = CustomerSvc.findById(id);

		return dto;
	}

	// 회원 메뉴
	private void member() {
		System.out.println("회원메뉴: (1)예매 (2)이전메뉴 (3)종료");
		System.out.print("메뉴 선택: ");
		int menu = Integer.parseInt(sc.nextLine());

		if (menu == 1) {
			System.out.println("예매메뉴: (1)영화 목록보기 (2)영화 예매 (3)예매 취소 (4) 이전 메뉴");
			System.out.print("메뉴 선택: ");
			int movieMenu = Integer.parseInt(sc.nextLine());

			if (movieMenu == 1) {
				System.out.println("영화 목록출력");
				member();
			} else if (movieMenu == 2) {
				reservation();
				System.out.println("간식을 구매하시겠습니까? (1) 예 (2) 아니오");
				System.out.print("메뉴 선택: ");
				int snackStatus = Integer.parseInt(sc.nextLine());
				if (snackStatus == 1) {
					snack();
					
				} else if (snackStatus == 2) {
					System.out.println("결제하기로 넘어감");
				} 
				member();
				
			} else if (movieMenu == 3) {
				System.out.println("예매 취소");
				member();
			} else if (movieMenu == 4) {
				member();
			} else {
				System.out.println("비정상적인 접근입니다.");
			}
		} else if (menu == 2) {
			return;
		} else if (menu == 3) {
			System.exit(0);
		} else {
			System.out.println("비정상적인 접근입니다.");
		}
	}

	private void reservation() {
		System.out.println("[영화 예매]");
		
		// 상영일정 번호 출력
		
		System.out.println("상영일정번호를 입력해주세요.>> ");
		int scnum = Integer.parseInt(sc.nextLine());
		System.out.println("상영관번호를 입력해주세요.>> ");
		int thnum = Integer.parseInt(sc.nextLine());
		
		// 좌석번호 출력
		
		System.out.println("좌석번호를 입력해주세요.>> ");
		int seatnumber = Integer.parseInt(sc.nextLine());
		
		// 좌석status가 0일떄
		int ticketPrice = 120000;
		// 현재 회원 정보 가져오기
		int cnum = 1;
		TicketDto dto = new TicketDto(0, scnum, thnum, seatnumber, cnum, ticketPrice, 0);
		
		try {
			TicketSvc.add(dto);
		} catch (TicketException e) {
			System.out.println("티켓 등록 오류");
			e.printStackTrace();
		}
	}

	// 관리자 메뉴
	private void manager() {
		System.out.println("관리자메뉴: (1)영화 등록/삭제 (2)상영일정 등록/삭제 (3)이전메뉴 (4)종료");
		System.out.print("메뉴 선택: ");
		int menu = Integer.parseInt(sc.nextLine());

		if (menu == 1) {
			System.out.println("영화메뉴: (1)영화 목록 (2)영화 등록 (3)영화 삭제 (4) 이전 메뉴");
			System.out.print("메뉴 선택: ");
			int movieMenu = Integer.parseInt(sc.nextLine());

			if (movieMenu == 1) {
				System.out.println("영화 목록출력");
				manager();
			} else if (movieMenu == 2) {
				System.out.println("영화 등록");
				manager();
			} else if (movieMenu == 3) {
				System.out.println("영화 삭제");
				manager();
			} else if (movieMenu == 4) {
				manager();
			} else {
				System.out.println("비정상적인 접근입니다.");
			}
		} else if (menu == 2) {
			System.out.println("상영일정메뉴: (1)상영일정 목록 (2)상영일정 등록 (3)상영일정 삭제 (4) 이전 메뉴");
			System.out.print("메뉴 선택: ");
			int scheduleMenu = Integer.parseInt(sc.nextLine());

			if (scheduleMenu == 1) {
				System.out.println("상영일정 목록출력");
				manager();
			} else if (scheduleMenu == 2) {
				System.out.println("상영일정 등록");
				manager();
			} else if (scheduleMenu == 3) {
				System.out.println("상영일정 삭제");
				manager();
			} else if (scheduleMenu == 4) {
				manager();
			} else {
				System.out.println("비정상적인 접근입니다.");
			}
		} else if (menu == 3) {
			return;
		} else if (menu == 4) {
			System.exit(0);
		} else {
			System.out.println("비정상적인 접근입니다.");
		}
	}

	// 회원가입
	private void signUpMenu() {
		System.out.println("회원가입 메뉴: (1)회원가입 (2)회원 탈퇴");
		System.out.print("메뉴 선택: ");
		int scheduleMenu = Integer.parseInt(sc.nextLine());

		if (scheduleMenu == 1) {
			signUp();
		} else if (scheduleMenu == 2) {
			withdraw();
		} else {
			System.out.println("비정상적인 접근입니다.");
		}
	}
	// 회원가입
	private void signUp() {
		System.out.println("** 회원 가입 **");
		System.out.println("사용할 아이디를 입력하세요>> ");
		String cid = sc.nextLine();
		System.out.println("사용할 비밀번호를 입력하세요>>");
		String cpw = sc.nextLine();
		System.out.println("이름을 입력하세요>> ");
		String cname = sc.nextLine();
		System.out.println("연락처를 입력하세요>> ");
		String ctel = sc.nextLine();
		System.out.println("회원가입이 완료되었습니다>> ");

		CustomerDto dto = new CustomerDto(0, cid, cpw, cname, ctel);
		try {
			CustomerSvc.add(dto);
		} catch (CustomerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//회원탈퇴
	private void withdraw() {
		CustomerDto dto = null;
		
		System.out.println("** 탈퇴하기 **");
		System.out.println("사용자의 아이디를 입력하세요>> ");
		String cid = sc.nextLine();
		
		System.out.println("사용자의 비밀번호를 입력하세요>>");
		String cpw = sc.nextLine();
		
		try { 
			dto = CustomerSvc.findById(cid);
			System.out.println(dto.getCpw());
			if(dto.getCpw().equals(cpw) == true) {
				System.out.println("탈퇴완료");
				CustomerSvc.delete(dto.getCnum());
			}
		}catch(CustomerException e) {
			System.out.println("회원 정보를 찾을 수 없습니다");
		}
	}
	// 간식메뉴
	private void snack() {
		while (true) {
			System.out.println("간식 메뉴: (1)간식 목록 (2)간식 구매 (3) 간식구매 종료");
			System.out.print("메뉴 선택: ");
			int snackMenu = Integer.parseInt(sc.nextLine());
			
			if (snackMenu == 1) {
				System.out.println("간식 목록");
			} else if (snackMenu == 2) {
				System.out.println("간식 구매");
			} else if (snackMenu == 3) {
				System.out.println("간식 구매종료");
				System.out.println("결제하기로 넘어감");
				break;
			} else {
				System.out.println("비정상적인 접근입니다.");
			}
		}
	}

}