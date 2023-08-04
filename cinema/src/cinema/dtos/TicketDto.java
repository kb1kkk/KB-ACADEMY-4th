package cinema.dtos;

public class TicketDto {
	private int tnum; // 티켓번호
	private int scnum; // 상영일정번호
	private int thnum; // 상영관번호
	private int seatnum; // 좌석번호
	private int cnum; //회원번호
	private int tprice; // 가격
	private int paystatus; //결제유무
	
	public TicketDto() {
	}

	public TicketDto(int tnum, int scnum, int thnum, int seatnumber, int cnum, int tprice, int paystatus) {
		super();
		this.tnum = tnum;
		this.scnum = scnum;
		this.thnum = thnum;
		this.seatnum = seatnumber;
		this.cnum = cnum;
		this.tprice = tprice;
		this.paystatus = paystatus;
	}

	public int getTnum() {
		return tnum;
	}

	public void setTnum(int tnum) {
		this.tnum = tnum;
	}

	public int getScnum() {
		return scnum;
	}

	public void setScnum(int scnum) {
		this.scnum = scnum;
	}

	public int getThnum() {
		return thnum;
	}

	public void setThnum(int thnum) {
		this.thnum = thnum;
	}

	public int getSeatnum() {
		return seatnum;
	}

	public void setSeatnum(int seatnumber) {
		this.seatnum = seatnumber;
	}

	public int getCnum() {
		return cnum;
	}

	public void setCnum(int cnum) {
		this.cnum = cnum;
	}

	public int getTprice() {
		return tprice;
	}

	public void setTprice(int tprice) {
		this.tprice = tprice;
	}

	public int getPaystatus() {
		return paystatus;
	}

	public void setPaystatus(int paystatus) {
		this.paystatus = paystatus;
	}

	@Override
	public String toString() {
		return "TiketDto [tnum=" + tnum + ", scnum=" + scnum + ", thnum=" + thnum + ", seatnumber=" + seatnum
				+ ", cnum=" + cnum + ", tprice=" + tprice + ", paystatus=" + paystatus + "]";
	}
	
}
