package cinema.theater.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import cinema.dtos.TheaterDto;
import cinema.exception.DuplicatedIdException;
import cinema.util.JdbcUtil;

public class TheaterDaoImpl implements TheaterDao {

	@Override
	public void add(TheaterDto dto) throws SQLException, DuplicatedIdException {
		//DBMS연결
				Connection con = null;
				PreparedStatement pstmt = null;
				try {
					con = JdbcUtil.connect();
					// 3. SQL 작성
					String sql = "INSERT INTO THEATER(thnum, totseat) ";
					sql += "VALUES(?, ?)";
					// 4. Statement 생성
					pstmt = con.prepareStatement(sql);
					// 5. 데이터 설정
					pstmt.setInt(1, dto.getThnum());
					pstmt.setInt(2, dto.getTotseat());
					// 6. SQL 전송, 결과수신
					int count = pstmt.executeUpdate();
				} catch (ClassNotFoundException e) {
					throw new SQLException(e);
				} finally {
					JdbcUtil.close(pstmt, con);
				}
			}

}
