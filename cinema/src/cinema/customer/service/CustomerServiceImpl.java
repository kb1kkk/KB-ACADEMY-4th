package cinema.customer.service;

import java.sql.SQLException;
import java.util.List;

import cinema.customer.dao.CustomerDao;
import cinema.dtos.CustomerDto;
import cinema.exception.CustomerException;
import cinema.exception.DuplicatedIdException;
import cinema.exception.RecordNotFoundException;

public class CustomerServiceImpl implements CoustomerService {
	private CustomerDao customerDao = new CustomerDaoImpl();
	
	@Override
	public boolean add(CustomerDto dto) throws CustomerException {
		try {
			customerDao.add(dto);
		} catch (SQLException e) {
			throw new CustomerException(e.getMessage());
		} catch (DuplicatedIdException e) {
		}
		   
		return true;
	}

	@Override
	public List<CustomerDto> list() throws CustomerException {
		List<CustomerDto> list = null;
		try {
			list = customerDao.list();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new CustomerException(e.getMessage());
		}
		return list;
	}

	@Override
	public boolean delete(int cnum) throws CustomerException {
		try {
			customerDao.delete(cnum);
		} catch (SQLException e) {
			throw new CustomerException(e.getMessage());
		} 
		return true;
	}

	@Override
	public CustomerDto findById(String cid) throws CustomerException {
		CustomerDto dto = null;
		
		try {
			dto = customerDao.findById(cid);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new CustomerException(e.getMessage());
		}
		
		return dto;
	}

}