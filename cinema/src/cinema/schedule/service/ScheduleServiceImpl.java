package cinema.schedule.service;

import java.sql.SQLException;
import java.util.List;

import cinema.dtos.ScheduleDto;
import cinema.exception.RecordNotFoundException;
import cinema.exception.ScheduleException;
import cinema.schedule.dao.ScheduleDao;
import cinema.schedule.dao.ScheduleDaoImpl;

public class ScheduleServiceImpl implements ScheduleService {
	private ScheduleDao scheduleDao = new ScheduleDaoImpl();

	@Override
	public boolean add(ScheduleDto dto) throws ScheduleException {
		try {
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
		return null;
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
		} catch (SQLException e) {
			throw new ScheduleException(e.getMessage());
		}
		return true;
	}

}
