package cinema.payment.dao;

import java.sql.SQLException;
import java.util.List;

import cinema.dtos.PaymentDto;
import cinema.exception.RecordNotFoundException;

public interface PaymentDao {
	
	//결제 등록
	public void add(PaymentDto dto) throws SQLException;
	
	//결제 상세보기
	public PaymentDto read(PaymentDto dto) throws SQLException,RecordNotFoundException;
	
	//회원번호로 결제안한 티켓수 가져오기
	public int countTicketByCnum(int cnum) throws SQLException,RecordNotFoundException;
	
	//간식가격구하기
	public int getSnackPrice(int snum) throws SQLException,RecordNotFoundException;
	
	//티켓가격구하기
	public int getTicketPrice(int cnum) throws SQLException,RecordNotFoundException;
	
	//티켓결제상태바꾸기
	public void changeTicketStatus(int cnum) throws SQLException,RecordNotFoundException;
	
	//결제리스트(회원)
	public List<PaymentDto> payListByCnum(int cnum) throws SQLException,RecordNotFoundException; 
	
	//총결제리스트(관리자)
	public List<PaymentDto> payList() throws SQLException,RecordNotFoundException;
	
}
