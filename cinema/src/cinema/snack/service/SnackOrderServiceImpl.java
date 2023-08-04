package cinema.snack.service;

import java.sql.SQLException;

import cinema.dtos.SnackOrderDto;
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
}
