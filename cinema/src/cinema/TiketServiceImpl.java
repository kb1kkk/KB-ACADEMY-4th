package cinema;

import java.sql.SQLException;

import cinema.dao.DuplicatedIdException;
import cinema.dao.TiketDao;

public class TiketServiceImpl implements TiketService {
	private TiketDao tiketDao = new TiketDaoImpl();
	
	@Override
	public boolean add(TiketDto dto) throws TiketException {
		try {
			tiketDao.add(dto);
		} catch (SQLException e) {
			throw new TiketException(e.getMessage());
		} catch (DuplicatedIdException e) {
		}
		
		return true;
	}

}
