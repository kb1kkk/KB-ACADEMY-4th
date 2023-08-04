package cinema.snack.service;

import cinema.dtos.SnackOrderDto;
import cinema.exception.SnackException;

public interface SnackOrderService {

    //간식주문등록
    public boolean add(SnackOrderDto dto) throws SnackException;
}
