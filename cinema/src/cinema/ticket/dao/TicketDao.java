package cinema.ticket.dao;

import java.sql.SQLException;

import cinema.dtos.TicketDto;
import cinema.exception.RecordNotFoundException;

public interface TicketDao {
	public void add(TicketDto dto) throws SQLException;
	public TicketDto getTicketByCnum(int cnum) throws SQLException, RecordNotFoundException;
}
