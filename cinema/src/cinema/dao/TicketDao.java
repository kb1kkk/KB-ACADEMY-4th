package cinema.dao;

import java.sql.SQLException;

import cinema.TicketDto;
import cinema.exception.DuplicatedIdException;

public interface TicketDao {
	public void add(TicketDto dto) throws SQLException, DuplicatedIdException;
}
