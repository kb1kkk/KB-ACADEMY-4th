package cinema.schedule.service;

import java.util.List;

import cinema.dtos.ScheduleDto;
import cinema.exception.RecordNotFoundException;
import cinema.exception.ScheduleException;

/** 상영 일정 CRUD */
public interface ScheduleService {
	// 상영일정 등록
	public boolean add(ScheduleDto dto) throws ScheduleException, RecordNotFoundException;

	// 상영일정 목록
	public List<ScheduleDto> list() throws ScheduleException;

	// 상영일정 상세보기
	public ScheduleDto read(int id) throws ScheduleException, RecordNotFoundException;

	// 상영일정 수정
	public boolean update(ScheduleDto dto) throws ScheduleException, RecordNotFoundException;

	// 상영일정 삭제
	public boolean delete(int no) throws ScheduleException, RecordNotFoundException;
}
