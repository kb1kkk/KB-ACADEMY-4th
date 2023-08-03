package cinema.dao;

import java.sql.SQLException;

import cinema.TiketDto;

public interface TiketDao {
	public void add(TiketDto dto) throws SQLException, DuplicatedIdException;
}
