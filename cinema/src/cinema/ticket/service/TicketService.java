package cinema.ticket.service;

import cinema.dtos.TicketDto;
import cinema.exception.TicketException;

public interface TicketService {
	
	public boolean add(TicketDto dto) throws TicketException;
}
