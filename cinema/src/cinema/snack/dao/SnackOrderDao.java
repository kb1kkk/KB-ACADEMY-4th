package cinema.snack.dao;

import java.sql.SQLException;

import cinema.dtos.SnackOrderDto;
import cinema.exception.RecordNotFoundException;
import cinema.exception.SnackException;

public interface SnackOrderDao {
	
	 //등록
    public void add(SnackOrderDto dto) throws SnackException, SQLException;
    
    //검색
    public SnackOrderDto getSnackOrder(SnackOrderDto dto)throws RecordNotFoundException, SQLException;
    
    //상태변화

	public void changeSoStatus(int sonum) throws SnackException, SQLException;

	int getPopSum() throws SnackException, SQLException;

	int getBSum() throws SnackException, SQLException;
}
