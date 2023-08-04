package cinema.snack.service;

import cinema.dtos.SnackOrderDto;
import cinema.exception.SnackException;

public interface SnackOrderService {

    //간식주문등록
    public boolean add(SnackOrderDto dto) throws SnackException;
    
  //검색
    public SnackOrderDto getSnackOrder(SnackOrderDto dto)throws SnackException;

	public int getBestPop() throws SnackException;

	public int getBestDrink() throws SnackException;
}
