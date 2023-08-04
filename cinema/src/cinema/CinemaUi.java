package cinema;

import java.util.List;
import java.util.Scanner;

import cinema.customer.service.CoustomerService;
import cinema.customer.service.CustomerServiceImpl;
import cinema.dtos.CustomerDto;
import cinema.dtos.MovieDto;
import cinema.dtos.ScheduleDto;
import cinema.dtos.SeatDto;
import cinema.exception.CustomerException;
import cinema.exception.MovieException;
import cinema.exception.RecordNotFoundException;
import cinema.exception.ScheduleException;
import cinema.dtos.TicketDto;
import cinema.exception.TicketException;
import cinema.movie.service.MovieService;
import cinema.movie.service.MovieServiceImpl;
import cinema.schedule.service.ScheduleService;
import cinema.schedule.service.ScheduleServiceImpl;
import cinema.seat.service.SeatService;
import cinema.seat.service.SeatServiceImpl;
import cinema.theater.service.TheaterService;
import cinema.theater.service.TheaterServiceImpl;
import cinema.ticket.service.TicketService;
import cinema.ticket.service.TicketServiceImpl;
import cinema.exception.TheaterException;

public class CinemaUi {

	private TicketService TicketSvc;
	private CoustomerService CustomerSvc;
	private SeatService seatSvc;
	private TheaterService theaterSvc;
	private MovieService MovieSvc;
	private ScheduleService ScheduleSvc;

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
		MovieSvc = new MovieServiceImpl();
		ScheduleSvc = new ScheduleServiceImpl();
		seatSvc = new SeatServiceImpl();
		theaterSvc = new TheaterServiceImpl();
	}

	private void mainMenu() {
		
		System.out.println("메인메뉴: (1)로그인 (2)회원가입 (3)종료");
		System.out.print("메뉴 선택: ");
		int menu = Integer.parseInt(sc.nextLine());
		CustomerDto user = null;

		if (menu == 1) {
			System.out.print("아이디를 입력하세요: ");
			String id = sc.nextLine();
			System.out.print("비밀번호를 입력하세요: ");
			String cpw = sc.nextLine();
			user = login(id);
			int isManager = 0;

			if (user == null) {
				System.out.println("아이디가 존재하지 않습니다");
				return;
			} else if (user.getCpw().equals(cpw) == true) {
				System.out.println("[로그인 성공]");
				isManager = user.getCnum();
			} else {
				System.out.println("아이디 또는 비밀번호가 일치하지 않습니다");
				return;
			}

			// cNum이 1000인 경우 관리자
			if (isManager == 1000) {
				manager();
			} else {
				member();
			}
		} else if (menu == 2) {
			signUpMenu();
		} else if (menu == 3) {
			user = null;
			System.exit(0);
		} else {
			System.out.println("비정상적인 접근입니다.");
		}
	}

	// 로그인 처리 함수
	private CustomerDto login(String id) {
		CustomerDto dto = null;
		try {
			dto = CustomerSvc.findById(id);
		} catch (CustomerException e) {
			System.out.println("로그인 오류");
			e.printStackTrace();
		}

		return dto;
	}

	// 회원 메뉴
	private void member() {
		System.out.println("회원메뉴: (1)예매 (2)로그아웃 (3)종료");
		System.out.print("메뉴 선택: ");
		int menu = Integer.parseInt(sc.nextLine());

		if (menu == 1) {
			System.out.println("예매메뉴: (1)영화 목록보기 (2)영화 예매 (3) 이전 메뉴");
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
		
		//// 빈 좌석 출력
		List<SeatDto> list = null;
		try {
			list = seatSvc.check(thnum);
			System.out.println("예약 가능 좌석은");
			System.out.println("상영관 | 좌석");
			for(SeatDto dto : list) {
				System.out.println(" "+
						dto.getThnum()+"       "+
						dto.getSeatnum()
						);
			}
		} catch (TheaterException e) {
			e.printStackTrace();
		}
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
		System.out.println("관리자메뉴: (1)영화 관리 (2)상영일정 관리 (3)로그아웃 (4)종료");
		System.out.print("메뉴 선택: ");
		
		int menu = Integer.parseInt(sc.nextLine());

		if (menu == 1) {
			System.out.println("영화메뉴: (1)영화 목록 (2)영화 등록 (3)영화 삭제 (4) 이전 메뉴");
			System.out.print("메뉴 선택: ");
			int movieMenu = Integer.parseInt(sc.nextLine());

			if (movieMenu == 1) {
				movieList();
				manager();
			} else if (movieMenu == 2) {
				addMovie();
				manager();
			} else if (movieMenu == 3) {
				deleteMovie();
				manager();
			} else if (movieMenu == 4) {
				manager();
			} else {
				System.out.println("비정상적인 접근입니다.");
			}
		} else if (menu == 2) {
			System.out.println("상영일정메뉴: (1)상영일정 목록 (2)상영일정 등록 (3)상영일정 수정 (4)상영일정 삭제 (5) 이전 메뉴");
			System.out.print("메뉴 선택: ");
			int scheduleMenu = Integer.parseInt(sc.nextLine());

			if (scheduleMenu == 1) {
				scheduleList();
				manager();
			} else if (scheduleMenu == 2) {
				addSchedule();
				manager();
			} else if (scheduleMenu == 3) {
				updateSchedule();
				manager();
			} else if (scheduleMenu == 4) {
				deleteSchedule();
				manager();
			} else if (scheduleMenu == 5) {
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

	// 회원탈퇴
	private void withdraw() {
		CustomerDto dto = null;

		System.out.println("** 탈퇴하기 **");
		System.out.println("사용자의 아이디를 입력하세요>> ");
		String cid = sc.nextLine();

		System.out.println("사용자의 비밀번호를 입력하세요>>");
		String cpw = sc.nextLine();

		try {
			dto = CustomerSvc.findById(cid);
			if (dto.getCpw().equals(cpw) == true) {
				System.out.println("탈퇴완료");
				CustomerSvc.delete(dto.getCnum());
			}
		} catch (CustomerException e) {
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

	// 영화 추가
	private void addMovie() {
		System.out.println("[영화 등록]");
		System.out.print("제목을 입력하세요 >> ");
		String title = sc.nextLine();
		System.out.print("러닝타임 입력하세요 >> ");
		int runtime = Integer.parseInt(sc.nextLine());
		System.out.print("상영시작일자를 입력하세요 >> ");
		String mstartdate = sc.nextLine();
		System.out.print("상영마감일자를 입력하세요 >> ");
		String mclosedate = sc.nextLine();

		MovieDto dto = new MovieDto(0, title, runtime, mstartdate, mclosedate);
		try {
			MovieSvc.add(dto);
		} catch (MovieException e) {
			e.printStackTrace();
		}
	}

	// 전체 영화목록 출력
	private void movieList() {
		System.out.println("[영화 목록]");
		System.out.println("-------------------------------------------------------------------------");
		System.out.printf("%-6s%-20s%-12s%-16s%-16s\n", "번호", "영화제목", "러닝타임", "상영시작일자", "상영마감일자");
		System.out.println("-------------------------------------------------------------------------");
		List<MovieDto> list;
		try {
			list = MovieSvc.list();
			for (MovieDto dto : list) {
				System.out.printf("%-6s%-20s%-12s%-16s%-16s\n", dto.getMnum(), dto.getTitle(), dto.getRuntime(),
						dto.getMstartdate(), dto.getMclosedate());
			}
		} catch (MovieException e) {
			System.out.println("*** 서버에 오류가 발생했습니다 ***");
		}
	}

	// 영화 삭제
	private void deleteMovie() {
		System.out.print("삭제할 영화 번호를 입력하세오 >> ");
		int no = Integer.parseInt(sc.nextLine());

		try {
			MovieSvc.delete(no);
		} catch (MovieException e) {
			System.out.println("서버 오류입니다");
		} catch (RecordNotFoundException e) {
			System.out.println("없는 영화입니다");
		}
	}

	// 전체 상영일정 출력
	private void scheduleList() {
		System.out.println("[상영 시간표]");
		System.out.println("-------------------------------------------------------------------------");
		System.out.printf("%-6s%-20s%-14s%-14s%-10s\n", "번호", "영화제목", "상영관번호", "상영일자", "시작시간");
		System.out.println("-------------------------------------------------------------------------");
		List<ScheduleDto> list;
		try {
			list = ScheduleSvc.list();
			for (ScheduleDto dto : list) {
				System.out.printf("%-6s%-20s%-14s%-14s%-10s\n", dto.getScnum(), dto.getMname(), dto.getThnum(),
						dto.getScdate(), dto.getSctime());
			}
		} catch (ScheduleException e) {
			System.out.println("*** 서버에 오류가 발생했습니다 ***");
		}
	}

	// 상영일정 등록
	private void addSchedule() {
		System.out.println("[상영 일정 등록]");

		try {
			System.out.print("영화 번호를 입력하세요 >> ");
			int mnum = Integer.parseInt(sc.nextLine());
			System.out.print("상영관 번호를 입력하세요 >> ");
			int thnum = Integer.parseInt(sc.nextLine());

			System.out.print("상영일자 입력하세요 >> ");
			String scdate = sc.nextLine();
			System.out.print("시작시간 입력하세요 >> ");
			String sctime = sc.nextLine();

			ScheduleDto dto = new ScheduleDto(0, scdate, sctime, mnum, thnum);
			ScheduleSvc.add(dto);
		} catch (ScheduleException e) {
			e.printStackTrace();
		} catch (RecordNotFoundException e) {
			System.out.println("없는 영화입니다");
		}
	}

	// 상영일정 수정
	private void updateSchedule() {
		System.out.print("수정할 상영일정 번호를 입력하세오 >> ");
		int no = Integer.parseInt(sc.nextLine());

		try {
			ScheduleDto dto = ScheduleSvc.read(no);
			System.out.println("[상영 정보]");
			System.out.print("상영일정 번호: " + dto.getScnum() + " ");
			System.out.print("영화번호: " + dto.getMnum() + " ");
			System.out.print("상영관번호: " + dto.getThnum() + " ");
			System.out.print("상영일자: " + dto.getScdate() + " ");
			System.out.println("시작시간: " + dto.getSctime() + " ");

			System.out.print("수정할 일자 입력 >> ");
			String scdate = sc.nextLine();
			dto.setScdate(scdate);

			System.out.print("수정할 시작시간 입력 >> ");
			String sctime = sc.nextLine();
			dto.setSctime(sctime);
			ScheduleSvc.update(dto);
		} catch (ScheduleException e) {
			System.out.println("서버 오류입니다");
		} catch (RecordNotFoundException e) {
			e.printStackTrace();
			System.out.println("없는 상영일정입니다");
		}
	}

	// 상영일정 삭제
	private void deleteSchedule() {
		System.out.print("삭제할 상영일정 번호를 입력하세오 >> ");
		int no = Integer.parseInt(sc.nextLine());

		try {
			ScheduleSvc.delete(no);
		} catch (ScheduleException e) {
			System.out.println("서버 오류입니다");
		} catch (RecordNotFoundException e) {
			System.out.println("없는 상영일정입니다");
		}
	}
}