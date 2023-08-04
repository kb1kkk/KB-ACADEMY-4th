package cinema.snack.dao;

import java.sql.SQLException;

import cinema.dtos.SnackOrderDto;
import cinema.exception.SnackException;

public interface SnackOrderDao {
	
	 //등록
    public void add(SnackOrderDto dto) throws SnackException, SQLException;
}
