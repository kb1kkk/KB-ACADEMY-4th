package cinema.payment.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cinema.dtos.PaymentDto;
import cinema.exception.RecordNotFoundException;
import cinema.util.JdbcUtil;

public class PaymentDaoImpl implements PaymentDao {

	@Override
	public void add(PaymentDto dto) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = JdbcUtil.connect();
			// 3. SQL 작성
			String sql = "INSERT INTO Payment(PNUM, CNUM, SONUM, TOTPRICE, PDAY)";
			sql += "VALUES(PAYMENT_SEQ.NEXTVAL,?,?,?,SYSDATE)";
			
			// 4. Statement 생성
			ps = con.prepareStatement(sql);
			
			// 5. 데이터 설정
			ps.setInt(1,dto.getCnum());
			ps.setInt(2,dto.getSonum());
			ps.setInt(3,dto.getTotprice());
			
			// 6. SQL 전송 및 결과 수신
			// 		DML전송 : executeUpdate() : int 반환
			//		SELECT 전송 : executeQuery() : ResultSet 반환	
			int count = ps.executeUpdate();
		} catch (ClassNotFoundException e) {
			throw new SQLException(e);
		} finally {
			//DBMS 해제
			JdbcUtil.close(ps,con);
		}
	}

	@Override
	public PaymentDto read(PaymentDto dto) throws SQLException, RecordNotFoundException {
		Connection con = null;
		PreparedStatement ps = null;
		PaymentDto check = null;
		try {
			con = JdbcUtil.connect();
			// 3. SQL 작성
			String sql = "SELECT * PAYMENT WHERE PNUM = ?";
			// 4. Statement 생성
			ps = con.prepareStatement(sql);
			
			// 5. 데이터 설정
			ps.setInt(1,dto.getPnum());
			
			// 6. SQL 전송 및 결과 수신
			// 		DML전송 : executeUpdate() : int 반환
			//		SELECT 전송 : executeQuery() : ResultSet 반환	
			ResultSet rs=ps.executeQuery();
			//id가 PK이면 0 개 또는 1 개 행을 반환
			if(rs.next()) { //조회결과가 있다.
				int pnum = rs.getInt(1);
				int cnum = rs.getInt(2);
				int sonum = rs.getInt(3);
				int totprice = rs.getInt(4);
				Date pday = rs.getDate(5);
				check = new PaymentDto(pnum,cnum,sonum,totprice,pday);
			}
		} catch (ClassNotFoundException e) {
			throw new SQLException(e);
		} finally {
			//DBMS 해제
			JdbcUtil.close(ps,con);
		}
		return check;
	}

	@Override
	public List<PaymentDto> payListByCnum(int cnum) throws SQLException, RecordNotFoundException {
		List<PaymentDto> result = new ArrayList<>();
		//DBMS 연결
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = JdbcUtil.connect();
			// 3. SQL 작성
			String sql = "SELECT * FROM PAYMENT WHERER CNUM = ?";
			
			// 4. Statement 생성
			ps = con.prepareStatement(sql);
			
			// 5. 데이터 설정
			
			
			// 6. SQL 전송 및 결과 수신
			// 		DML전송 : executeUpdate() : int 반환
			//		SELECT 전송 : executeQuery() : ResultSet 반환	
			ResultSet rs=ps.executeQuery();
			//id가 PK이면 0 개 또는 1 개 행을 반환
			while(rs.next()) { //조회결과가 있다.
				int pnum = rs.getInt(1);
				int sonum = rs.getInt(3);
				int totprice = rs.getInt(4);
				Date pday = rs.getDate(5);
				PaymentDto dto = new PaymentDto(pnum,cnum,sonum,totprice,pday);
				result.add(dto);
			}
		} catch (ClassNotFoundException e) {
			throw new SQLException(e);
		} finally {
			//DBMS 해제
			JdbcUtil.close(ps,con);
		}
		return result;
	}

	@Override
	public List<PaymentDto> payList() throws SQLException, RecordNotFoundException {
		List<PaymentDto> result = new ArrayList<>();
		//DBMS 연결
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = JdbcUtil.connect();
			// 3. SQL 작성
			String sql = "SELECT * FROM PAYMENT WHERER CNUM = ?";
			
			// 4. Statement 생성
			ps = con.prepareStatement(sql);
			
			// 5. 데이터 설정
			
			
			// 6. SQL 전송 및 결과 수신
			// 		DML전송 : executeUpdate() : int 반환
			//		SELECT 전송 : executeQuery() : ResultSet 반환	
			ResultSet rs=ps.executeQuery();
			//id가 PK이면 0 개 또는 1 개 행을 반환
			while(rs.next()) { //조회결과가 있다.
				int pnum = rs.getInt(1);
				int cnum = rs.getInt(2);
				int sonum = rs.getInt(3);
				int totprice = rs.getInt(4);
				Date pday = rs.getDate(5);
				PaymentDto dto = new PaymentDto(pnum,cnum,sonum,totprice,pday);
				result.add(dto);
			}
		} catch (ClassNotFoundException e) {
			throw new SQLException(e);
		} finally {
			//DBMS 해제
			JdbcUtil.close(ps,con);
		}
		return result;
	}

	@Override
	public int countTicketByCnum(int cnum) throws SQLException, RecordNotFoundException {
		Connection con = null;
		PreparedStatement ps = null;
		int num = -1;
		try {
			con = JdbcUtil.connect();
			// 3. SQL 작성
			String sql = "SELECT COUNT(*) FROM TICKET WHERE CNUM = ?";
			
			// 4. Statement 생성
			ps = con.prepareStatement(sql);
			
			// 5. 데이터 설정
			ps.setInt(1, cnum);
			// 6. SQL 전송 및 결과 수신
			// 		DML전송 : executeUpdate() : int 반환
			//		SELECT 전송 : executeQuery() : ResultSet 반환	
			ResultSet rs=ps.executeQuery();
			//id가 PK이면 0 개 또는 1 개 행을 반환
			if(rs.next()) { //조회결과가 있다.
				num = rs.getInt(1);
			}
		} catch (ClassNotFoundException e) {
			throw new SQLException(e);
		} finally {
			//DBMS 해제
			JdbcUtil.close(ps,con);
		}
		return num;
	}

	@Override
	public int getSnackPrice(int snum) throws SQLException, RecordNotFoundException {
		Connection con = null;
		PreparedStatement ps = null;
		int price = 0;
		try {
			con = JdbcUtil.connect();
			// 3. SQL 작성
			String sql = "SELECT STCNT SNACKORDER WHERE SNUM = ?";
			// 4. Statement 생성
			ps = con.prepareStatement(sql);
			
			// 5. 데이터 설정
			ps.setInt(1,snum);
			
			// 6. SQL 전송 및 결과 수신
			// 		DML전송 : executeUpdate() : int 반환
			//		SELECT 전송 : executeQuery() : ResultSet 반환	
			ResultSet rs=ps.executeQuery();
			//id가 PK이면 0 개 또는 1 개 행을 반환
			if(rs.next()) { //조회결과가 있다.
				price = rs.getInt("STCNT");
			}
		} catch (ClassNotFoundException e) {
			throw new SQLException(e);
		} finally {
			//DBMS 해제
			JdbcUtil.close(ps,con);
		}
		return price;
	}

	@Override
	public int getTicketPrice(int cnum) throws SQLException, RecordNotFoundException {
		int ticketNum = countTicketByCnum(cnum);
		int price = ticketNum*13000;
		
		return price;
	}

	@Override
	public void changeTicketStatus(int cnum) throws SQLException, RecordNotFoundException {
		Connection con = null;
		PreparedStatement ps = null;
		int price = 0;
		try {
			con = JdbcUtil.connect();
			// 3. SQL 작성
			String sql = "UPDATE TICKET SET PAYSTATUS = 1 WHERE CNUM = ?";
			// 4. Statement 생성
			ps = con.prepareStatement(sql);
			
			// 5. 데이터 설정
			ps.setInt(1,cnum);
			
			// 6. SQL 전송 및 결과 수신
			// 		DML전송 : executeUpdate() : int 반환
			//		SELECT 전송 : executeQuery() : ResultSet 반환	
			int count = ps.executeUpdate();
		} catch (ClassNotFoundException e) {
			throw new SQLException(e);
		} finally {
			//DBMS 해제
			JdbcUtil.close(ps,con);
		}
	}

}
