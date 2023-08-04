package cinema.theater.dao;

import java.sql.SQLException;

import cinema.dtos.TheaterDto;
import cinema.exception.DuplicatedIdException;

public interface TheaterDao {
	// 등록
	public void add(TheaterDto dto) throws SQLException, DuplicatedIdException;
	// 체크
	public TheaterDto check(int no) throws SQLException;
	
}
