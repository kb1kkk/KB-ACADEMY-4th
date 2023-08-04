package cinema.theater.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import cinema.dtos.TheaterDto;
import cinema.exception.DuplicatedIdException;
import cinema.util.JdbcUtil;

public class TheaterDaoImpl implements TheaterDao {

	private Scanner sc = new Scanner(System.in);

	@Override
	public void add(TheaterDto dto) throws SQLException, DuplicatedIdException {
		// DBMS연결
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

	@Override
	public TheaterDto check(int no) throws SQLException {
		TheaterDto dto = null;
		// DBMS연결
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = JdbcUtil.connect();
			// 3. SQL 작성
			String sql = "SELECT * FROM THEATER where thnum = ? ";
			// 4. Statement 생성
			pstmt = con.prepareStatement(sql);
			// 5. 데이터 설정
			pstmt.setInt(1, no);
			// 6. SQL 전송, 결과수신
			// DML전송: executeUpdate() : int
			// SELECT전송: executeQuery() : ResultSet
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {// 조회결과가 있다
				int thnum = rs.getInt("thnum");
				int totseat = rs.getInt("totseat");
				dto = new TheaterDto(thnum, totseat);
			}
		} catch (ClassNotFoundException e) {
			dto = null;
		} finally {
			JdbcUtil.close(pstmt, con);
		}
		return dto;
	}

}
