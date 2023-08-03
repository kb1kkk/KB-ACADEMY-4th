package cinema.seat.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import cinema.dtos.SeatDto;
import cinema.exception.DuplicatedIdException;
import cinema.util.JdbcUtil;


public class SeatDaoImpl implements SeatDao {

	@Override
	public void add(SeatDto dto) throws SQLException, DuplicatedIdException {
		// DBMS연결
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = JdbcUtil.connect();
			// 3. SQL 작성
			String sql = "INSERT INTO SEAT(seatnum, tnumber, seatsstatus ) ";
			sql += "VALUES( xxx_SEQ.NEXTVAL , ? , ?  ) ";
			// 4. Statement 생성
			pstmt = con.prepareStatement(sql);
			// 5. 데이터 설정
			pstmt.setInt(1, dto.getTnumber());
			pstmt.setInt(2, dto.getSeatstatus());
			// 6. SQL 전송, 결과수신
			int count = pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			throw new SQLException(e);
		} finally {
			JdbcUtil.close(pstmt, con);
		}
	}

}
