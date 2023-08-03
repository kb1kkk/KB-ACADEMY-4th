package cinema;

import cinema.exception.TicketException;

public interface TicketService {
	
	public boolean add(TicketDto dto) throws TicketException;
}
