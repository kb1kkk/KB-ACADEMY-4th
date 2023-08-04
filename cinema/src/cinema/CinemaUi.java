package cinema;

import java.util.List;
import java.util.Scanner;

import cinema.customer.service.CoustomerService;
import cinema.customer.service.CustomerServiceImpl;
import cinema.dtos.CustomerDto;
import cinema.dtos.MovieDto;
import cinema.dtos.PaymentDto;
import cinema.dtos.ScheduleDto;
import cinema.dtos.SeatDto;
import cinema.dtos.SnackDto;
import cinema.dtos.SnackOrderDto;
import cinema.dtos.TicketDto;
import cinema.exception.CustomerException;
import cinema.exception.MovieException;
import cinema.exception.PaymentException;
import cinema.exception.RecordNotFoundException;
import cinema.exception.ScheduleException;
import cinema.exception.SnackException;
import cinema.exception.TheaterException;
import cinema.exception.TicketException;
import cinema.movie.service.MovieService;
import cinema.movie.service.MovieServiceImpl;
import cinema.payment.service.PaymentService;
import cinema.payment.service.PaymentServiceImpl;
import cinema.schedule.service.ScheduleService;
import cinema.schedule.service.ScheduleServiceImpl;
import cinema.seat.service.SeatService;
import cinema.seat.service.SeatServiceImpl;
import cinema.snack.service.SnackOrderService;
import cinema.snack.service.SnackOrderServiceImpl;
import cinema.snack.service.SnackService;
import cinema.snack.service.SnackServiceImpl;
import cinema.theater.service.TheaterService;
import cinema.theater.service.TheaterServiceImpl;
import cinema.ticket.service.TicketService;
import cinema.ticket.service.TicketServiceImpl;

public class CinemaUi {

	private TicketService TicketSvc;
	private CoustomerService CustomerSvc;
	private SeatService seatSvc;
	private TheaterService theaterSvc;
	private MovieService MovieSvc;
	private ScheduleService ScheduleSvc;
	private SnackService snackSvc;
	private SnackOrderService snackOrdSvc;
	private PaymentService paymentSvc;

	private static Scanner sc = new Scanner(System.in);
	private CustomerDto curUser = null;
	private SnackOrderDto soDto = null;
	

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
		snackSvc = new SnackServiceImpl();
		snackOrdSvc = new SnackOrderServiceImpl();
		paymentSvc = new PaymentServiceImpl();
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
				curUser = user;
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
			reset();
			System.exit(0);
		} else {
			System.out.println("비정상적인 접근입니다.");
		}
	}

	private void reset() {
		curUser = null;
		soDto = null;
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
				movieList();
				member();
			} else if (movieMenu == 2) {
				reservation();
				System.out.println("간식을 구매하시겠습니까? (1) 예 (2) 아니오");
				System.out.print("메뉴 선택: ");
				int snackStatus = Integer.parseInt(sc.nextLine());
				if (snackStatus == 1) {
					recomendation();
					snack();
					payment();
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
			reset();
			return;
		} else if (menu == 3) {
			reset();
			System.exit(0);
		} else {
			System.out.println("비정상적인 접근입니다.");
		}
	}

	private void recomendation() {
		int bestpop=0;
		int bestdrink=0;
		try {
			bestpop=snackOrdSvc.getBestPop();
			bestdrink=snackOrdSvc.getBestDrink();
			System.out.println(bestpop);
		} catch (SnackException e) {
			e.printStackTrace();
		}
		if(bestpop == 1) {
			System.out.println(" **** 인기가 가장 많은 팝콘은 '일반팝콘'입니다. **** ");
		}else if(bestpop == 2) {
			System.out.println(" **** 인기가 가장 많은 팝콘은 '캬라멜 팝콘'입니다. **** ");
		}else if(bestpop == 3) {
			System.out.println(" **** 인기가 가장 많은 팝콘은 '치즈맛 팝콘'입니다. **** ");
		}
		if(bestdrink == 4) {
			System.out.println(" **** 인기가 가장 많은 음료는 '콜라'입니다. **** ");
		}else if(bestdrink == 5) {
			System.out.println(" **** 인기가 가장 많은 음료는 '사이다'입니다. **** ");
		}else if(bestdrink == 6) {
			System.out.println(" **** 인기가 가장 많은 음료는 '오렌지주스'입니다. **** ");
		}
	}

	private void payment() {
		TicketDto ticket = null;
		ScheduleDto schedule = null;
		MovieDto movie = null;
		PaymentDto payResult = null;
		try {
			ticket = TicketSvc.getTicketByCnum(curUser.getCnum());
			schedule =ScheduleSvc.read(ticket.getScnum());
			movie = MovieSvc.read(schedule.getMnum());

		} catch (MovieException e) {
			System.out.println("영화 정보 불러오기 오류");
		} catch (ScheduleException e) {
			System.out.println("상영 일정 정보 불러오기 오류");
		} catch (RecordNotFoundException e) {
			System.out.println("상영 일정 정보 불러오기 오류");
		} catch (TicketException e) {
			System.out.println("티켓 정보 불러오기 오류");
		}
		String popcorn = null;
		String drink = null;
		if(soDto.getPopnum() == 1) {
			popcorn = "일반 팝콘";
		}else if (soDto.getPopnum() == 2) {
			popcorn = "캬라멜 팝콘";
		}else if (soDto.getPopnum() == 3) {
			popcorn = "치즈맛 팝콘";
		}
		if(soDto.getBnum() == 4) {
			drink = "콜라";
		}else if (soDto.getBnum() == 5) {
			drink = "사이다";
		}else if (soDto.getBnum() == 6) {
			drink = "오렌지 주스";
		}
		int totprice =(soDto.getStcnt()+ticket.getTprice());
		System.out.println(" *** 결제내역 **** ");
		System.out.println(" 회원 아이디 : "+curUser.getCid());
		System.out.println(" 영화 제목 : "+movie.getTitle());
		System.out.println(" 영화 상영관 : "+ticket.getThnum()+"관");
		System.out.println(" 좌석 번호 : "+ticket.getSeatnum()+"번 좌석");
		System.out.println(" 영화 상영일정 : "+schedule.getScdate().substring(0,4)+"년"
				+schedule.getScdate().substring(4,6)+"월"+schedule.getScdate().substring(6,8)+"일");
		System.out.println(" 영화 상영시작시간 : "+schedule.getSctime().substring(0,2)+":"+schedule.getSctime().substring(2,4));
		System.out.println(" 팝콘 종류(수량) : "+popcorn+"("+soDto.getPopcnt()+"개)");
		System.out.println(" 음료 종류(수량) : "+drink+"("+soDto.getBcnt()+"개)");
		System.out.println(" 총 구매 가격 : "+totprice+"원");
		System.out.println("-----------------------------------------------");
		System.out.println("결제하시겠습니까?  (1) 예 (2) 아니오");
		payResult = new PaymentDto(0,curUser.getCnum(),soDto.getSonum(),totprice,null);
		int payCheck = Integer.parseInt(sc.nextLine());
		if(payCheck == 1) {
			try {
				paymentSvc.getPay(payResult);
			} catch (PaymentException e) {
				System.out.println("결제오류");
			} catch (RecordNotFoundException e) {
				System.out.println("결제오류");
			}
			System.out.println("결제 완료!");
		}
		
	}

	private void reservation() {
		System.out.println("[영화 예매]");

		scheduleList();

		System.out.println("상영일정번호를 입력해주세요.>> ");
		int scnum = Integer.parseInt(sc.nextLine());
		System.out.println("상영관번호를 입력해주세요.>> ");
		int thnum = Integer.parseInt(sc.nextLine());

		// 빈 좌석 출력
		List<SeatDto> list = null;
		try {
			list = seatSvc.check(thnum);
			System.out.println("예약 가능 좌석은");
			System.out.println("상영관 | 좌석");
			for (SeatDto dto : list) {
				System.out.println(" " + dto.getThnum() + "       " + dto.getSeatnum());
			}
		} catch (TheaterException e) {
			e.printStackTrace();
		}
		// 좌석번호 출력

		System.out.println("좌석번호를 입력해주세요.>> ");
		int seatnumber = Integer.parseInt(sc.nextLine());

		// 좌석status가 0일떄
		int ticketPrice = 12000;
		// 현재 회원 정보 가져오기
		int cnum = curUser.getCnum();
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
			reset();
			return;
		} else if (menu == 4) {
			reset();
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

	// 팝콘메뉴출력
	private List<SnackDto> popMenu() {
		List<SnackDto> list = null;
		System.out.println("** 팝콘목록 **");
		System.out.printf("%-6s%-10s%-16s%-10s\n", "번호", "종류", "이름 ", "가격");
		System.out.println("-------------------------------------------");
		try {
			list = snackSvc.list();
			for (int i = 0; i < 3; i++) {
				System.out.printf("%-7s%-10s%-15s%-10s\n", list.get(i).getSnum(), list.get(i).getStype(),
						list.get(i).getSname(), list.get(i).getSprice());
			}
		} catch (SnackException e) {
			System.out.println("*** 서버에 오류가 발생했습니다 ***");
		}
		return list;
	}

	// 음료메뉴출력
	private void drinkMenu(List<SnackDto> list) {
		System.out.println("음료는 어떤걸 사시겠습니까?");
		System.out.printf("%-6s%-10s%-16s%-10s\n", "번호", "종류", "이름 ", "가격");
		System.out.println("-------------------------------------------");
		for (int i = 3; i < 6; i++) {
			System.out.printf("%-7s%-10s%-15s%-10s\n", i - 2, list.get(i).getStype(), list.get(i).getSname(),
					list.get(i).getSprice());

		}
	}
	
	// 간식메뉴
		private void snack() {
			List<SnackDto> list = popMenu();
			int popcorn = 0;
			int popcnt = 0;
			while (true) {
				System.out.println("팝콘은 어떤걸 사시겠습니까?");
				popcorn = Integer.parseInt(sc.nextLine());
				if(popcorn == 0){
					System.out.println("팝콘을 선택안하셨습니다");
					break;
				}else if (popcorn == 1) {
					System.out.println("일반 팝콘");
					break;
				} else if (popcorn == 2) {
					System.out.println("카라멜 팝콘");
					break;
				} else if (popcorn == 3) {
					System.out.println("치즈맛 팝콘");
					break;
				} else {
					System.out.println("팝콘을 선택해주세요.");
					continue;
				}
			}
			System.out.println("팝콘수량은 몇개가 필요한가요?");
			popcnt = Integer.parseInt(sc.nextLine());
			int drink =0;
			int drinkcnt = 0;
			while(true){
				drinkMenu(list);
				drink = Integer.parseInt(sc.nextLine());
				if(drink == 0){
					System.out.println("음료를 선택안하셨습니다");
					break;
				}else if (drink == 1) {
					drink = 4;
					System.out.println("콜라");
					break;
				} else if (drink == 2) {
					drink = 5;
					System.out.println("사이다");
					break;
				} else if (drink == 3) {
					drink = 6;
					System.out.println("오렌지주스");
					break;
				} else {
					System.out.println("음료를 선택해주세요.");
					continue;
				}
			}
			System.out.println("음료수량은 몇개가 필요한가요?");
			drinkcnt = Integer.parseInt(sc.nextLine());
			snackOrdSvc = new SnackOrderServiceImpl();

			int popprice =0;
			int drinkprice =0;
			try {
				popprice = snackSvc.getPrice(popcorn);
				drinkprice = snackSvc.getPrice(drink);
			} catch (SnackException e) {
			}
			int stcnt =(popcnt * popprice)+ (drinkprice * drinkcnt); // 간식 총금액
			soDto = new SnackOrderDto(0,popcorn,popcnt,drink,drinkcnt,stcnt,curUser.getCnum(),0);
			System.out.println("간식 총 금액은 " +stcnt+" 입니다");
			try {
				snackOrdSvc.add(soDto);
				soDto = snackOrdSvc.getSnackOrder(soDto);
			}  catch (SnackException e) {
				throw new RuntimeException(e);
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
		System.out.printf("%-8s%-14s\t%-12s%-16s%-16s\n", "영화번호", "영화제목", "러닝타임", "상영시작일자", "상영마감일자");
		System.out.println("-------------------------------------------------------------------------");
		List<MovieDto> list;
		try {
			list = MovieSvc.list();
			for (MovieDto dto : list) {
				System.out.printf("%-10s%-14s\t%-14s%-18s%-16s\n", dto.getMnum(), dto.getTitle(), dto.getRuntime(),
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
		System.out.printf("%-10s%-12s\t%-14s%-14s\t%-10s\n", "상영일정번호", "영화제목", "상영관번호", "상영일자", "시작시간");
		System.out.println("-------------------------------------------------------------------------");
		List<ScheduleDto> list;
		try {
			list = ScheduleSvc.list();
			for (ScheduleDto dto : list) {
				System.out.printf("%-12s%-14s\t%-14s%-14s\t%-10s\n", dto.getScnum(), dto.getMname(), dto.getThnum(),
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