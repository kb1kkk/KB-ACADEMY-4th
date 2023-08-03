package cinema.seat.service;

import cinema.dtos.SeatDto;
import cinema.exception.RecordNotFoundException;
import cinema.exception.TheaterException;

public interface SeatService {
	// 등록
	public boolean add(SeatDto dto) throws TheaterException;
	// 수정
	public boolean update(SeatDto dto) throws TheaterException, RecordNotFoundException;

}
