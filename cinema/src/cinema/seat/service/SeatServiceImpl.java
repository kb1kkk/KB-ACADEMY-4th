package cinema.seat.service;

import java.sql.SQLException;

import cinema.dtos.SeatDto;
import cinema.exception.DuplicatedIdException;
import cinema.exception.RecordNotFoundException;
import cinema.exception.TheaterException;
import cinema.seat.dao.SeatDao;
import cinema.seat.dao.SeatDaoImpl;

public class SeatServiceImpl implements SeatService {
	
	private SeatDao seatDao = new SeatDaoImpl();
	
	@Override
	public boolean add(SeatDto dto) throws TheaterException {
		try {
			seatDao.add(dto);
		} catch (SQLException e) {
			throw new TheaterException(e.getMessage());
		} catch (DuplicatedIdException e) {
		}
		return true;
	}

	@Override
	public boolean update(SeatDto dto) throws TheaterException, RecordNotFoundException {
		// TODO Auto-generated method stub
		return false;
	}

}
