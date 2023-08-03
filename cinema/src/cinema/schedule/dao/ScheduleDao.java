package cinema.schedule.dao;

import java.sql.SQLException;
import java.util.List;

import cinema.dtos.ScheduleDto;
import cinema.exception.RecordNotFoundException;

public interface ScheduleDao {
	// 상영일정 등록
	public void add(ScheduleDto dto) throws SQLException;

	// 상영일정 목록
	public List<ScheduleDto> list() throws SQLException;

	// 상영일정 수정
	public void update(ScheduleDto dto) throws SQLException, RecordNotFoundException;

	// 상영일정 삭제
	public void delete(int id) throws SQLException, RecordNotFoundException;
	
	// 상영일정 id 검색
	public ScheduleDto findById(int id) throws SQLException;
}
