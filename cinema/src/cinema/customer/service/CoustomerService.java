package cinema.customer.service;

import java.sql.SQLException;
import java.util.List;

import cinema.dtos.CustomerDto;
import cinema.exception.CustomerException;

public interface CoustomerService {
	
	public boolean add(CustomerDto dto) throws CustomerException;

	public List<CustomerDto> list() throws CustomerException;

	public boolean delete(int cnum) throws CustomerException;
	
	public CustomerDto findById(String cid) throws CustomerException;

}