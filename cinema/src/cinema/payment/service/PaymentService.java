package cinema.payment.service;

import java.util.List;

import cinema.dtos.PaymentDto;
import cinema.exception.PaymentException;
import cinema.exception.RecordNotFoundException;

public interface PaymentService {
	
	//결제(등록)
	public boolean getPay(PaymentDto pdto) throws PaymentException, RecordNotFoundException ;
	
	//결제결과출력
	public PaymentDto getPayResult(PaymentDto pdto) throws PaymentException;
	
	//가격구하기
	public int getPrice(PaymentDto pdto) throws PaymentException;
	
	//결제리스트(회원)
	public List<PaymentDto> getPayListByCnum(int cnum) throws PaymentException;
	
	//총결제리스트(관리자)
	public List<PaymentDto> getPayList() throws PaymentException;
}
