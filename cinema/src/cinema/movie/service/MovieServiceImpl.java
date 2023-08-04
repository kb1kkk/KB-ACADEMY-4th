package cinema.movie.service;

import java.sql.SQLException;
import java.util.List;

import cinema.dtos.MovieDto;
import cinema.exception.MovieException;
import cinema.exception.RecordNotFoundException;
import cinema.movie.dao.MovieDao;
import cinema.movie.dao.MovieDaoImpl;

public class MovieServiceImpl implements MovieService {
	private MovieDao movieDao = new MovieDaoImpl();

	@Override
	public boolean add(MovieDto dto) throws MovieException {
		try {
			movieDao.add(dto);
		} catch (SQLException e) {
			throw new MovieException(e.getMessage());
		}
		return true;
	}

	@Override
	public List<MovieDto> list() throws MovieException {
		List<MovieDto> list = null;
		try {
			list = movieDao.list();
		} catch (SQLException e) {
			throw new MovieException(e.getMessage());
		}
		return list;
	}

	@Override
	public MovieDto read(int id) throws MovieException, RecordNotFoundException {
		MovieDto dto = null;
		try {
			dto = movieDao.findById(id);
			if (dto == null) {
				throw new RecordNotFoundException();
			}
		} catch (SQLException e) {
			throw new MovieException(e.getMessage());
		}
		return dto;
	}

	@Override
	public boolean update(MovieDto dto) throws MovieException, RecordNotFoundException {
		try {
			movieDao.update(dto);
		} catch (SQLException e) {
			throw new MovieException(e.getMessage());
		}
		return true;
	}

	@Override
	public boolean delete(int no) throws MovieException, RecordNotFoundException {
		try {
			MovieDto dto = movieDao.findById(no);
			if (dto == null) {
				throw new RecordNotFoundException();
			}
			movieDao.delete(no);
		} catch (SQLException e) {
			throw new MovieException(e.getMessage());
		}
		return true;
	}

}
