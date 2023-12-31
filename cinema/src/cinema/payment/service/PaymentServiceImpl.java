package cinema.payment.service;

import java.sql.SQLException;
import java.util.List;

import cinema.dtos.PaymentDto;
import cinema.exception.PaymentException;
import cinema.exception.RecordNotFoundException;
import cinema.exception.SnackException;
import cinema.payment.dao.PaymentDao;
import cinema.payment.dao.PaymentDaoImpl;
import cinema.seat.dao.SeatDao;
import cinema.seat.dao.SeatDaoImpl;
import cinema.snack.dao.SnackOrderDao;
import cinema.snack.dao.SnackOrderDaoImpl;
import cinema.ticket.dao.TicketDao;
import cinema.ticket.dao.TicketDaoImpl;

public class PaymentServiceImpl implements PaymentService {

	PaymentDao paymentDao = new PaymentDaoImpl();
	SnackOrderDao snackDao = new SnackOrderDaoImpl();
	
	@Override
	public boolean getPay(PaymentDto pdto) throws PaymentException, RecordNotFoundException {
		try {
			paymentDao.add(pdto);
			paymentDao.changeTicketStatus(pdto.getCnum());
			snackDao.changeSoStatus(pdto.getSonum());
		} catch (SQLException | SnackException e) {
			e.printStackTrace();
			throw new PaymentException(e.getMessage());
		}
		return true;
	}

	@Override
	public PaymentDto getPayResult(PaymentDto pdto) throws PaymentException {
		PaymentDto dto = null;
		try {
			dto = paymentDao.read(pdto);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PaymentException(e.getMessage());
		} catch (RecordNotFoundException e) {
			e.printStackTrace();
			throw new PaymentException(e.getMessage());
		}
		return dto;
	}

	@Override
	public List<PaymentDto> getPayListByCnum(int cnum) throws PaymentException {
		List<PaymentDto> list = null;
		try {
			list = paymentDao.payListByCnum(cnum);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PaymentException(e.getMessage());
		} catch (RecordNotFoundException e) {
			e.printStackTrace();
			throw new PaymentException(e.getMessage());
		}
		return list;
	}

	@Override
	public List<PaymentDto> getPayList() throws PaymentException {
		List<PaymentDto> list = null;
		try {
			list = paymentDao.payList();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PaymentException(e.getMessage());
		} catch (RecordNotFoundException e) {
			e.printStackTrace();
			throw new PaymentException(e.getMessage());
		}
		return list;
	}

	@Override
	public int getPrice(PaymentDto pdto) throws PaymentException {
		int price = -1;
		try {
			price = paymentDao.getSnackPrice(pdto.getSonum())+paymentDao.getTicketPrice(pdto.getCnum());
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PaymentException(e.getMessage());
		} catch (RecordNotFoundException e) {
			e.printStackTrace();
			throw new PaymentException(e.getMessage());
		}
		return price;
	}

}
