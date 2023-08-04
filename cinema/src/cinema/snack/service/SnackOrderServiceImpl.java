package cinema.snack.service;

import java.sql.SQLException;

import cinema.dtos.SnackOrderDto;
import cinema.exception.RecordNotFoundException;
import cinema.exception.SnackException;
import cinema.snack.dao.SnackOrderDao;
import cinema.snack.dao.SnackOrderDaoImpl;

public class SnackOrderServiceImpl implements SnackOrderService {
    SnackOrderDao sodao = new SnackOrderDaoImpl();

    @Override
    public boolean add(SnackOrderDto dto) throws SnackException {
        try {
            sodao.add(dto);
        } catch (SQLException e) {
            throw new SnackException(e.getMessage());
        }
        return true;
    }

	@Override
	public SnackOrderDto getSnackOrder(SnackOrderDto dto) throws SnackException {
		SnackOrderDto sdto = null;
		try {
			sdto=sodao.getSnackOrder(dto);
		} catch (RecordNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sdto;
	}

	@Override
	public int getBestPop() throws SnackException {
		int result =0;
		try {
			result=sodao.getPopSum();
		} catch (SnackException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int getBestDrink() throws SnackException {
		int result =0;
		try {
			result=sodao.getBSum();
		} catch (SnackException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
}
