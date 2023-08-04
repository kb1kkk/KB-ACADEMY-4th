package cinema.snack.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import cinema.dtos.SnackOrderDto;
import cinema.exception.RecordNotFoundException;
import cinema.exception.SnackException;
import cinema.util.JdbcUtil;

public class SnackOrderDaoImpl implements SnackOrderDao {

    @Override
    public void add(SnackOrderDto dto) throws SnackException, SQLException{
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = JdbcUtil.connect();
            // 3. SQL 작성
            String sql = "INSERT INTO SNACKORDER ";
            sql += "VALUES(SNACKORDER_SEQ.NEXTVAL,?,?,?,?,?,?,0 )";
            // 4. Statement 생성
            pstmt = con.prepareStatement(sql);
            // 5. 데이터 설정

            pstmt.setInt(1, dto.getPopnum());
            pstmt.setInt(2, dto.getPopcnt());
            pstmt.setInt(3, dto.getBnum());
            pstmt.setInt(4, dto.getBcnt());
            pstmt.setInt(5, dto.getStcnt());
            pstmt.setInt(6, dto.getCnum());
            // 6. SQL 전송, 결과수신
            int count = pstmt.executeUpdate();
        } catch (ClassNotFoundException e) {
            throw new SQLException(e);
        } finally {
            JdbcUtil.close(pstmt, con);
        }
    }

	@Override
	public SnackOrderDto getSnackOrder(SnackOrderDto dto) throws RecordNotFoundException, SQLException {
		Connection con = null;
        PreparedStatement pstmt = null;
        SnackOrderDto soDto=null;
        try {
            con = JdbcUtil.connect();
            // 3. SQL 작성
            String sql = "SELECT * FROM SNACKORDER WHERE CNUM =? AND SOSTATUS = ? ";
            // 4. Statement 생성
            pstmt = con.prepareStatement(sql);
            // 5. 데이터 설정

            pstmt.setInt(1, dto.getCnum());
            pstmt.setInt(2, dto.getSostatus());
            // 6. SQL 전송, 결과수신
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()) {
            	int sonum=rs.getInt(1);
            	int popnum=rs.getInt(2);
            	int popcnt=rs.getInt(3);
            	int bnum=rs.getInt(4);
            	int bcnt=rs.getInt(5);
            	int stcnt=rs.getInt(6);
            	int cnum=rs.getInt(7);
            	int sostatus=rs.getInt(8);
            	soDto=new SnackOrderDto(sonum,popnum,popcnt,bnum,bcnt,stcnt,cnum,sostatus);
            }
        } catch (ClassNotFoundException e) {
            throw new SQLException(e);
        } finally {
            JdbcUtil.close(pstmt, con);
        }
		return soDto;
	}

	@Override
	public void changeSoStatus(int sonum) throws SnackException, SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		int price = 0;
		try {
			con = JdbcUtil.connect();
			// 3. SQL 작성
			String sql = "UPDATE SNACKORDER SET SOSTATUS = 1 WHERE SONUM = ?";
			// 4. Statement 생성
			ps = con.prepareStatement(sql);
			
			// 5. 데이터 설정
			ps.setInt(1,sonum);
			
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
    public int getPopSum() throws SnackException, SQLException{
        //DBMS연결
        Connection con = null;
        PreparedStatement pstmt = null;
        int sum = 0;
        try {
            con = JdbcUtil.connect();
            // 3. SQL 작성
            String sql = "SELECT POPNUM, SUM(POPCNT) FROM SNACKORDER GROUP BY POPNUM ORDER BY 2 DESC";
            // 4. Statement 생성
            pstmt = con.prepareStatement(sql);
            // 5. 데이터 설정
            // 6. SQL 전송, 결과수신
            //   DML전송: executeUpdate() : int
            //   SELECT전송: executeQuery() : ResultSet
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {//조회결과가 있다
                sum = rs.getInt("POPNUM");
            }
        } catch (ClassNotFoundException e) {
            throw new SQLException(e);
        } finally {
            JdbcUtil.close(pstmt, con);
        }
        return sum;
    }
	
	@Override
    public int getBSum() throws SnackException, SQLException{
        //DBMS연결
        Connection con = null;
        PreparedStatement pstmt = null;
        int sum = 0;
        try {
            con = JdbcUtil.connect();
            // 3. SQL 작성
            String sql = "SELECT BNUM, SUM(BCNT) FROM SNACKORDER GROUP BY BNUM ORDER BY 2 DESC";
            // 4. Statement 생성
            pstmt = con.prepareStatement(sql);
            // 5. 데이터 설정
            // 6. SQL 전송, 결과수신
            //   DML전송: executeUpdate() : int
            //   SELECT전송: executeQuery() : ResultSet
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {//조회결과가 있다
                sum = rs.getInt("BNUM");
            }
        } catch (ClassNotFoundException e) {
            throw new SQLException(e);
        } finally {
            JdbcUtil.close(pstmt, con);
        }
        return sum;
    }
	
}

