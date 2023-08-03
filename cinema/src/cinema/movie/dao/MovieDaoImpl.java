package cinema.movie.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cinema.dtos.MovieDto;
import cinema.exception.RecordNotFoundException;
import cinema.util.JdbcUtil;

public class MovieDaoImpl implements MovieDao {

	@Override
	public void add(MovieDto dto) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = JdbcUtil.connect();

			String sql = "INSERT INTO MOVIE(mnum, title, runtime, mstartdate, mclosedate)";
			sql += "VALUES(MOVIE_SEQ.NEXTVAL, ?, ?, ?, ?)";

			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, dto.getTitle());
			pstmt.setInt(2, dto.getRuntime());
			pstmt.setString(3, dto.getMstartdate());
			pstmt.setString(4, dto.getMclosedate());

			int count = pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			throw new SQLException(e);
		} finally {
			JdbcUtil.close(pstmt, con);
		}
	}

	@Override
	public List<MovieDto> list() throws SQLException {
		List<MovieDto> result = new ArrayList<MovieDto>();
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = JdbcUtil.connect();

			String sql = "SELECT * FROM MOVIE ORDER BY mnum";

			pstmt = con.prepareStatement(sql);

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int mnum = rs.getInt("mnum");
				String title = rs.getString("title");
				int runtime = rs.getInt("runtime");
				String mstartdate = rs.getString("mstartdate");
				String mclosedate = rs.getString("mclosedate");
				MovieDto dto = new MovieDto(mnum, title, runtime, mstartdate, mclosedate);
				result.add(dto);
			}
		} catch (ClassNotFoundException e) {
			throw new SQLException(e);
		} finally {
			JdbcUtil.close(pstmt, con);
		}
		return result;
	}

	@Override
	public void update(MovieDto dto) throws SQLException, RecordNotFoundException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			// 등록여부 검사
			if (findById(dto.getMnum()) == null) {
				throw new RecordNotFoundException(dto.getMnum() + "는 없습니다");
			}

			con = JdbcUtil.connect();

			String sql = "UPDATE MOVIE SET title = ?, runtime = ?, mstartdate = ?, mclosedate = ? ";
			sql += "WHERE mnum = ?";

			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, dto.getTitle());
			pstmt.setInt(2, dto.getRuntime());
			pstmt.setString(3, dto.getMstartdate());
			pstmt.setString(4, dto.getMclosedate());
			pstmt.setInt(5, dto.getMnum());

			int count = pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			throw new SQLException(e);
		} finally {
			JdbcUtil.close(pstmt, con);
		}
	}

	@Override
	public void delete(int id) throws SQLException, RecordNotFoundException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			// 등록여부 검사
			if (findById(id) == null) {
				throw new RecordNotFoundException(id + "는 없습니다");
			}

			con = JdbcUtil.connect();

			String sql = "DELETE MOVIE WHERE mnum = ?";

			pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, id);

			int count = pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			throw new SQLException(e);
		} finally {
			JdbcUtil.close(pstmt, con);
		}
	}

	@Override
	public MovieDto findById(int id) throws SQLException {
		MovieDto dto = null;
		// DBMS 연결
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = JdbcUtil.connect();

			String sql = "SELECT * FROM MOVIE WHERE mnum = ?";

			pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, id);

			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				String title = rs.getString("title");
				int runtime = rs.getInt("runtime");
				String mstartdate = rs.getString("mstartdate");
				String mclosedate = rs.getString("mclosedate");
				dto = new MovieDto(id, title, runtime, mstartdate, mclosedate);
			}
		} catch (ClassNotFoundException e) {
			throw new SQLException(e);
		} finally {
			JdbcUtil.close(pstmt, con);
		}
		return dto;
	}

}
