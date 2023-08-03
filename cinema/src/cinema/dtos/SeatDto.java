package cinema.dtos;

public class SeatDto {
	private int seatnum;	// 좌석번호 PK
	private int thnum;	// 상영관번호 FK
	private int seatstatus;	// 좌석배정유무 0 빈자리, 1 예약석
	
	public SeatDto() {
	}

	public SeatDto(int seatnum, int thnum, int seatstatus) {
		super();
		this.seatnum = seatnum;
		this.thnum = thnum;
		this.seatstatus = seatstatus;
	}

	public int getSeatnum() {
		return seatnum;
	}

	public void setSeatnum(int seatnum) {
		this.seatnum = seatnum;
	}


	public int getThnum() {
		return thnum;
	}

	public void setThnum(int thnum) {
		this.thnum = thnum;
	}

	public int getSeatstatus() {
		return seatstatus;
	}

	public void setSeatstatus(int seatstatus) {
		this.seatstatus = seatstatus;
	}

	@Override
	public String toString() {
		return "SeatDto [seatnum=" + seatnum + ", thnum=" + thnum + ", seatstatus=" + seatstatus + "]";
	}

	
	
	
	
	
	
}
