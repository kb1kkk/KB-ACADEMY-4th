package cinema.seat.service;

import java.sql.SQLException;
import java.util.List;

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
	public List<SeatDto> check() throws TheaterException {
		List<SeatDto> list = null;
		try {
			list = seatDao.list();
		} catch(SQLException e) {
			e.printStackTrace();
			throw new TheaterException(e.getMessage());
		}
		return list;
	}

}
