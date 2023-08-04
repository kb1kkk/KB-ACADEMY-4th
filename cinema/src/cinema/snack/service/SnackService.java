package cinema.snack.service;

import java.util.List;

import cinema.dtos.SnackDto;
import cinema.exception.SnackException;

public interface SnackService {
    public List<SnackDto> list() throws SnackException;

    public int getPrice(int snum) throws SnackException;
}

