package cinema.schedule.service;

import java.sql.SQLException;
import java.util.List;

import cinema.dtos.MovieDto;
import cinema.dtos.ScheduleDto;
import cinema.dtos.TheaterDto;
import cinema.exception.RecordNotFoundException;
import cinema.exception.ScheduleException;
import cinema.movie.dao.MovieDao;
import cinema.movie.dao.MovieDaoImpl;
import cinema.schedule.dao.ScheduleDao;
import cinema.schedule.dao.ScheduleDaoImpl;
import cinema.theater.dao.TheaterDao;
import cinema.theater.dao.TheaterDaoImpl;

public class ScheduleServiceImpl implements ScheduleService {
	private ScheduleDao scheduleDao = new ScheduleDaoImpl();
	private MovieDao movieDao = new MovieDaoImpl();
	 private TheaterDao theaterDao = new TheaterDaoImpl();

	@Override
	public boolean add(ScheduleDto dto) throws ScheduleException, RecordNotFoundException {
		try {
			MovieDto mdto = movieDao.findById(dto.getMnum());
			if(mdto == null) {
				throw new RecordNotFoundException();
			}
			TheaterDto thdto = theaterDao.check(dto.getThnum());
			if(thdto == null) {
				throw new RecordNotFoundException();
			}
			scheduleDao.add(dto);
		} catch (SQLException e) {
			throw new ScheduleException(e.getMessage());
		}
		return true;
	}

	@Override
	public List<ScheduleDto> list() throws ScheduleException {
		List<ScheduleDto> list = null;
		try {
			list = scheduleDao.list();
		} catch (SQLException e) {
			throw new ScheduleException(e.getMessage());
		}
		return list;
	}

	@Override
	public ScheduleDto read(int id) throws ScheduleException, RecordNotFoundException {
		ScheduleDto dto = null;
		try {
			dto = scheduleDao.findById(id);
			if (dto == null) {
				throw new RecordNotFoundException();
			}
		} catch (SQLException e) {
			throw new ScheduleException(e.getMessage());
		}
		return dto;
	}

	@Override
	public boolean update(ScheduleDto dto) throws ScheduleException, RecordNotFoundException {
		try {
			scheduleDao.update(dto);
		} catch (SQLException e) {
			throw new ScheduleException(e.getMessage());
		}
		return true;
	}

	@Override
	public boolean delete(int id) throws ScheduleException, RecordNotFoundException {
		try {
			ScheduleDto dto = scheduleDao.findById(id);
			if (dto == null) {
				throw new RecordNotFoundException();
			}
			scheduleDao.delete(id);
		} catch (SQLException e) {
			throw new ScheduleException(e.getMessage());
		}
		return true;
	}

}
