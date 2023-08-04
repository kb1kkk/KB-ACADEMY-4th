package cinema.ticket.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import cinema.dtos.TicketDto;
import cinema.exception.RecordNotFoundException;
import cinema.util.JdbcUtil;

public class TicketDaoImpl implements TicketDao {

	@Override
	public void add(TicketDto dto) throws SQLException{
		// DBMS 연결
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = JdbcUtil.connect();
			// 3. SQL 작성
			String sql = "INSERT INTO TICKET(TNUM, SCNUM, THNUM, SEATNUM, CNUM, TPRICE, PAYSTATUS)";
			sql += "VALUES(TICKET_SEQ.NEXTVAL,?,?,?,?,?,?)";

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

	@Override
	public TicketDto getTicketByCnum(int cnum) throws SQLException, RecordNotFoundException{
		// DBMS 연결
		Connection con = null;
		PreparedStatement ps = null;
		TicketDto dto = null;
		try {
			con = JdbcUtil.connect();
			// 3. SQL 작성
			String sql = "SELECT * FROM TICKET WHERE CNUM = ? AND PAYSTATUS = 0";

			// 4. Statement 생성
			ps = con.prepareStatement(sql);

			// 5. 데이터 설정
			ps.setInt(1,cnum);

			// 6. SQL 전송 및 결과 수신
			// DML전송 : executeUpdate() : int 반환
			// SELECT 전송 : executeQuery() : ResultSet 반환
			ResultSet rs = ps.executeQuery(sql);
			if(rs.next()){
				int tnum = rs.getInt(1);
				int scnum = rs.getInt(2);
				int thnum = rs.getInt(3);
				int seatnum = rs.getInt(4);
				int tprice = rs.getInt(6);
				int paystatus = rs.getInt(7);
				dto=new TicketDto(tnum,scnum,thnum,seatnum,cnum,tprice,paystatus);
			}
		} catch (ClassNotFoundException e) {
			throw new SQLException(e);
		} finally {
			// DBMS 해제
			JdbcUtil.close(ps, con);
		}
		return dto;
	}

}
