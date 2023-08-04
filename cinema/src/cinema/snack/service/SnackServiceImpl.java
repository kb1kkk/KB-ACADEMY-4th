package cinema.snack.service;

import java.sql.SQLException;
import java.util.List;

import cinema.dtos.SnackDto;
import cinema.exception.RecordNotFoundException;
import cinema.exception.SnackException;
import cinema.snack.dao.SnackDao;
import cinema.snack.dao.SnackDaoImpl;

public class SnackServiceImpl implements SnackService {

    private SnackDao snackDao = new SnackDaoImpl();

    @Override
    public List<SnackDto> list() throws SnackException {
        List<SnackDto> list = null;
        try {
            list = snackDao.list();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SnackException(e.getMessage());
        }
        return list;
    }

    @Override
    public int getPrice(int snum) throws SnackException {
        int price =0;
        try {
           price= snackDao.getPrice(snum);
        } catch (SQLException e) {
        	throw new SnackException(e.getMessage());
        } catch (RecordNotFoundException e) {
        	throw new SnackException(e.getMessage());
        }
        return price;
    }
}

