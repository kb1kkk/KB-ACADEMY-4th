package cinema.ticket.dao;

import java.sql.SQLException;

import cinema.dtos.TicketDto;
import cinema.exception.DuplicatedIdException;

public interface TicketDao {
	public void add(TicketDto dto) throws SQLException, DuplicatedIdException;
}
