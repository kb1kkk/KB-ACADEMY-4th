package cinema.theater.service;

import java.sql.SQLException;

import cinema.dtos.TheaterDto;
import cinema.exception.DuplicatedIdException;
import cinema.exception.RecordNotFoundException;
import cinema.exception.TheaterException;
import cinema.theater.dao.TheaterDao;
import cinema.theater.dao.TheaterDaoImpl;

public class TheaterServiceImpl implements TheaterService {
	
	private TheaterDao theaterDao = new TheaterDaoImpl();

	@Override
	public boolean add(TheaterDto dto) throws TheaterException {
		try {
			theaterDao.add(dto);
		} catch (SQLException e) {
			throw new TheaterException(e.getMessage());
		} catch (DuplicatedIdException e) {
		}
		return true;
	}

	@Override
	public boolean check() throws TheaterException, RecordNotFoundException {
		try {
			System.out.println("조회하고 싶은 관");
			TheaterDto check = theaterDao.check();
			if(check != null) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			return false;
		}
	}
}
