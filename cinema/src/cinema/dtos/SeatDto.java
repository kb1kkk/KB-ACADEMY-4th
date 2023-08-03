package cinema.dtos;

public class SeatDto {
	private int seatnum;	// 좌석번호 PK
	private int tnumber;	// 상영관번호 FK
	private int seatstatus;	// 좌석배정유무 0 빈자리, 1 예약석
	
	public SeatDto() {
	}

	public SeatDto(int seatnum, int tnumber, int seatstatus) {
		super();
		this.seatnum = seatnum;
		this.tnumber = tnumber;
		this.seatstatus = seatstatus;
	}

	public int getSeatnum() {
		return seatnum;
	}

	public void setSeatnum(int seatnum) {
		this.seatnum = seatnum;
	}

	public int getTnumber() {
		return tnumber;
	}

	public void setTnumber(int tnumber) {
		this.tnumber = tnumber;
	}

	public int getSeatstatus() {
		return seatstatus;
	}

	public void setSeatstatus(int seatstatus) {
		this.seatstatus = seatstatus;
	}

	@Override
	public String toString() {
		return "seatnum=" + seatnum + ", tnumber=" + tnumber + ", seatstatus=" + seatstatus;
	}

	
	
	
	
	
}
