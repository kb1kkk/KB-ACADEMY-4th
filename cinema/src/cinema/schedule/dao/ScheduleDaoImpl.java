package cinema.schedule.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cinema.dtos.MovieDto;
import cinema.dtos.ScheduleDto;
import cinema.exception.RecordNotFoundException;
import cinema.util.JdbcUtil;

public class ScheduleDaoImpl implements ScheduleDao {

	@Override
	public void add(ScheduleDto dto) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = JdbcUtil.connect();

			String sql = "INSERT INTO SCHEDULE(scnum, scdate, sctime, mnum, thnum)";
			sql += "VALUES(SCHEDULE_SEQ.NEXTVAL, ?, ?, ?, ?)";

			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, dto.getScdate());
			pstmt.setString(2, dto.getSctime());
			pstmt.setInt(3, dto.getMnum());
			pstmt.setInt(4, dto.getThnum());

			int count = pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			throw new SQLException(e);
		} finally {
			JdbcUtil.close(pstmt, con);
		}
	}

	@Override
	public List<ScheduleDto> list() throws SQLException {
		List<ScheduleDto> result = new ArrayList<ScheduleDto>();
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = JdbcUtil.connect();

			String sql = "SELECT S.SCNUM, S.SCDATE, S.SCTIME, S.MNUM, S.THNUM, M.TITLE "
					+ "FROM SCHEDULE S join MOVIE M on(S.MNUM = M.MNUM) ORDER BY S.mnum, S.scdate, S.sctime";
			
			pstmt = con.prepareStatement(sql);

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int scnum = rs.getInt(1);
				String scdate = rs.getString("scdate");
				String sctime = rs.getString("sctime");
				int mnum = rs.getInt("mnum");
				int thnum = rs.getInt("thnum");
				String title = rs.getString("title");
				ScheduleDto dto = new ScheduleDto(scnum, scdate, sctime, mnum, thnum, title);
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
	public void update(ScheduleDto dto) throws SQLException, RecordNotFoundException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			// 등록여부 검사
			if (findById(dto.getScnum()) == null) {
				throw new RecordNotFoundException(dto.getScnum() + "는 없습니다");
			}

			con = JdbcUtil.connect();

			String sql = "UPDATE SCHEDULE SET scdate = ?, sctime = ? ";
			sql += "WHERE scnum = ?";

			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, dto.getScdate());
			pstmt.setString(2, dto.getSctime());
			pstmt.setInt(3, dto.getScnum());

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

			String sql = "DELETE SCHEDULE WHERE scnum = ?";

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
	public ScheduleDto findById(int id) throws SQLException {
		ScheduleDto dto = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = JdbcUtil.connect();

			String sql = "SELECT * FROM SCHEDULE WHERE scnum = ?";

			pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, id);

			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				int scnum = rs.getInt("scnum");
				String scdate = rs.getString("scdate");
				String sctime = rs.getString("sctime");
				int mnum = rs.getInt("mnum");
				int thnum = rs.getInt("thnum");
				dto = new ScheduleDto(scnum, scdate, sctime, mnum, thnum);
			}
		} catch (ClassNotFoundException e) {
			throw new SQLException(e);
		} finally {
			JdbcUtil.close(pstmt, con);
		}
		return dto;
	}

}
