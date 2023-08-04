package cinema.customer.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cinema.dtos.CustomerDto;
import cinema.exception.DuplicatedIdException;
import cinema.util.JdbcUtil;

public class CustomerDaoImpl implements CustomerDao {

	@Override
	public void add(CustomerDto dto) throws DuplicatedIdException, SQLException {
		// DBMS 연결
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = JdbcUtil.connect();
			// 3. SQL 작성
			String sql = "INSERT INTO CUSTOMER(cnum, cid, cpw, cname, ctel)";
			sql += "VALUES(CUSTOMER_SEQ.NEXTVAL,?,?,?,?)";

			// 4. Statement 생성
			ps = con.prepareStatement(sql);

			// 5. 데이터 설정
			ps.setString(1, dto.getCid());
			ps.setString(2, dto.getCpw());
			ps.setString(3, dto.getCname());
			ps.setString(4, dto.getCtel());

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

	@Override
	public List<CustomerDto> list() throws SQLException {
		List<CustomerDto> result = new ArrayList<CustomerDto>();
		//DBMS연결
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = JdbcUtil.connect();
			// 3. SQL 작성
			String sql = "SELECT * FROM CUSTOMER order by no DESC";
			// 4. Statement 생성
			pstmt = con.prepareStatement(sql);
			// 5. 데이터 설정
			// 6. SQL 전송, 결과수신
			//   DML전송: executeUpdate() : int 
			//   SELECT전송: executeQuery() : ResultSet
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {//조회결과가 있다
				int cnum = rs.getInt("cnum");
				String cid = rs.getString("cid");
				String cpw = rs.getString("cpw"); 
				String cname = rs.getString("cname");
				String ctel = rs.getString("ctel");
				CustomerDto dto = new CustomerDto(cnum, cid, cpw, cname, ctel);
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
	public void delete(int cnum) throws SQLException {
		//DBMS연결
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = JdbcUtil.connect();
            // 3. SQL 작성
            String sql = "DELETE CUSTOMER ";
            sql += "WHERE cnum = ?";
            // 4. Statement 생성
            pstmt = con.prepareStatement(sql);
            // 5. 데이터 설정
            pstmt.setInt(1, cnum);
            // 6. SQL 전송, 결과수신
            int count = pstmt.executeUpdate();
        } catch (ClassNotFoundException e) {
            throw new SQLException(e);
        } finally {
            JdbcUtil.close(pstmt, con);
        }
	}
	@Override
	public CustomerDto findById(String cid) throws SQLException {
		CustomerDto dto = null;
		//DBMS연결
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = JdbcUtil.connect();
			// 3. SQL 작성
			String sql = "SELECT * FROM CUSTOMER where cid = ?";
			// 4. Statement 생성
			pstmt = con.prepareStatement(sql);
			// 5. 데이터 설정
			pstmt.setString(1, cid);
			// 6. SQL 전송, 결과수신
			//   DML전송: executeUpdate() : int 
			//   SELECT전송: executeQuery() : ResultSet
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {//조회결과가 있다
				int cnum = rs.getInt("cnum");
				String cpw = rs.getString("cpw");
				String cname = rs.getString("cname");
				String ctel = rs.getString("ctel");
				dto = new CustomerDto(cnum, cid, cpw, cname, ctel);
			}
		} catch (ClassNotFoundException e) {
			throw new SQLException(e);
		} finally {
			JdbcUtil.close(pstmt, con);
		}
		return dto;
	}

}