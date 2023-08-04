package cinema.snack.dao;

import java.sql.SQLException;
import java.util.List;

import cinema.dtos.SnackDto;
import cinema.exception.RecordNotFoundException;

public interface SnackDao {
	  //목록
    public List<SnackDto> list() throws SQLException;

    public int getPrice(int snum) throws SQLException, RecordNotFoundException;
}
