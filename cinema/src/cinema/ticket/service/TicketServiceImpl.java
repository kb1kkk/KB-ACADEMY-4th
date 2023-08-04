package cinema.ticket.service;

import java.sql.SQLException;

import cinema.dtos.TicketDto;
import cinema.exception.DuplicatedIdException;
import cinema.exception.RecordNotFoundException;
import cinema.exception.TicketException;
import cinema.ticket.dao.TicketDao;
import cinema.ticket.dao.TicketDaoImpl;

public class TicketServiceImpl implements TicketService {
	private TicketDao ticketDao = new TicketDaoImpl();
	
	@Override
	public boolean add(TicketDto dto) throws TicketException {
		try {
			ticketDao.add(dto);
		} catch (SQLException e) {
			throw new TicketException(e.getMessage());
		}
		      
		return true;
	}

	@Override
	public TicketDto getTicketByCnum(int cnum) throws TicketException {
		TicketDto dto;
		try {
			dto = ticketDao.getTicketByCnum(cnum);
		} catch (SQLException e) {
			throw new TicketException(e.getMessage());
		} catch (RecordNotFoundException e) {
			throw new TicketException(e.getMessage());
		}
		return dto;
	}

}
