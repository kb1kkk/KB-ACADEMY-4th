package cinema.theater.service;


import cinema.dtos.TheaterDto;
import cinema.exception.RecordNotFoundException;
import cinema.exception.TheaterException;

public interface TheaterService {
	// 등록
	public boolean add(TheaterDto dto) throws TheaterException;
	// 수정
	public boolean update(TheaterDto dto) throws TheaterException, RecordNotFoundException;
	
	
	
}
