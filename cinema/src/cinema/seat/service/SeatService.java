package cinema.seat.service;

import java.util.List;

import cinema.dtos.SeatDto;
import cinema.exception.TheaterException;

public interface SeatService {
	// 등록
	public boolean add(SeatDto dto) throws TheaterException;
	// 빈자리 조회
	public List<SeatDto> check(int thnum) throws TheaterException;
}
