package cinema.seat.dao;

import java.sql.SQLException;
import java.util.List;

import cinema.dtos.SeatDto;
import cinema.exception.DuplicatedIdException;

public interface SeatDao {
	// 등록
	public void add(SeatDto dto) throws SQLException, DuplicatedIdException;
	// 남는 자리
	public List<SeatDto> list() throws SQLException;
	
}
