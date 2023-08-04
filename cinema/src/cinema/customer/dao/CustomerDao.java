package cinema.customer.dao;

import java.sql.SQLException;
import java.util.List;

import cinema.dtos.CustomerDto;
import cinema.exception.CustomerException;
import cinema.exception.DuplicatedIdException;

public interface CustomerDao {
	public void add(CustomerDto dto) throws CustomerException ,DuplicatedIdException, SQLException;

	public List<CustomerDto> list() throws CustomerException, SQLException;

	public void delete(int cnum) throws CustomerException, SQLException;
	
	public CustomerDto findById(String cid) throws SQLException;

}