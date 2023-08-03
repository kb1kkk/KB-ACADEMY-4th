package cinema;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import cinema.dao.DuplicatedIdException;
import cinema.dao.TiketDao;
import cinema.util.JdbcUtil;

public class TiketDaoImpl implements TiketDao {

	@Override
	public void add(TiketDto dto) throws SQLException, DuplicatedIdException {
		// DBMS 연결
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = JdbcUtil.connect();
			// 3. SQL 작성
			String sql = "INSERT INTO TIKET(TNUM, SCNUM, THNUM, SEATNUMBER, CNUM, TPRICE, PAYSTATUS)";
			sql += "VALUES(TIKET_SEQ.NEXTVAL,?,?,?,?,?,?)";

			// 4. Statement 생성
			ps = con.prepareStatement(sql);

			// 5. 데이터 설정
			ps.setInt(1, dto.getScnum());
			ps.setInt(2, dto.getThnum());
			ps.setInt(3, dto.getSeatnumber());
			ps.setInt(4, dto.getCnum());
			ps.setInt(5, dto.getTprice());
			ps.setInt(6, dto.getPaystatus());

			// 6. SQL 전송 및 결과 수신
			// DML전송 : executeUpdate() : int 반환
			// SELECT 전송 : executeQuery() : ResultSet 반환
			int count = ps.executeUpdate();
		} catch (ClassNotFoundException e) {
			throw new SQLException(e);
		} finally {
			// DBMS 해제
			JdbcUtil.close(ps, con);
		}

	}

}
