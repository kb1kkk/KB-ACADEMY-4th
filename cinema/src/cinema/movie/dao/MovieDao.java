package cinema.movie.dao;

import java.sql.SQLException;
import java.util.List;

import cinema.dtos.MovieDto;
import cinema.exception.RecordNotFoundException;

public interface MovieDao {
	// 영화 등록
	public void add(MovieDto dto) throws SQLException;

	// 영화 목록
	public List<MovieDto> list() throws SQLException;

	// 영화 수정
	public void update(MovieDto dto) throws SQLException, RecordNotFoundException;

	// 영화 삭제
	public void delete(int id) throws SQLException, RecordNotFoundException;
	
	// 영화 id 검색
	public MovieDto findById(int id) throws SQLException;
}
