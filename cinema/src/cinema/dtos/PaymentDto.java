package cinema.dtos;

import java.util.Date;

public class PaymentDto {
	
	private int pnum; //결제번호
	private int cnum; //회원번호
	private int sonum; //간식주문번호
	private int totprice; //결제금액
	private Date pday; //결제일자
	
	public PaymentDto() {
	}

	public PaymentDto(int pnum, int cnum, int sonum, int totprice, Date pday) {
		super();
		this.pnum = pnum;
		this.cnum = cnum;
		this.sonum = sonum;
		this.totprice = totprice;
		this.pday = pday;
	}

	public int getPnum() {
		return pnum;
	}

	public void setPnum(int pnum) {
		this.pnum = pnum;
	}

	public int getCnum() {
		return cnum;
	}

	public void setCnum(int cnum) {
		this.cnum = cnum;
	}

	public int getSonum() {
		return sonum;
	}

	public void setSonum(int sonum) {
		this.sonum = sonum;
	}

	public int getTotprice() {
		return totprice;
	}

	public void setTotprice(int totprice) {
		this.totprice = totprice;
	}

	public Date getPday() {
		return pday;
	}

	public void setPday(Date pday) {
		this.pday = pday;
	}

	@Override
	public String toString() {
		return "pnum=" + pnum + ", cnum=" + cnum + ", sonum=" + sonum + ", totprice=" + totprice + ", pday="
				+ pday;
	}
	
	
	
}
