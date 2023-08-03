package cinema.movie.service;

import java.util.List;

import cinema.dtos.MovieDto;
import cinema.exception.MovieException;
import cinema.exception.RecordNotFoundException;

/** 상영 영화 CRUD */
public interface MovieService {
	// 영화 등록
	public boolean add(MovieDto dto) throws MovieException;
	
	// 영화 목록
	public List<MovieDto> list() throws MovieException;
	
	// 영화 상세보기
	public MovieDto read(int id) throws MovieException, RecordNotFoundException;
	
	// 영화 수정
	public boolean update(MovieDto dto) throws MovieException, RecordNotFoundException;
	
	// 영화 삭제
	public boolean delete(int no) throws MovieException, RecordNotFoundException;
}
