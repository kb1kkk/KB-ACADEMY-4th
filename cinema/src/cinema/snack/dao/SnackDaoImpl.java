package cinema.snack.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cinema.dtos.SnackDto;
import cinema.exception.RecordNotFoundException;
import cinema.util.JdbcUtil;

public class SnackDaoImpl implements SnackDao {

    @Override
    public List<SnackDto> list() throws SQLException {
        List<SnackDto> result = new ArrayList<SnackDto>();
        //DBMS연결
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = JdbcUtil.connect();
            // 3. SQL 작성
            String sql = "SELECT * FROM SNACK order by 1";
            // 4. Statement 생성
            pstmt = con.prepareStatement(sql);
            // 5. 데이터 설정
            // 6. SQL 전송, 결과수신
            //   DML전송: executeUpdate() : int
            //   SELECT전송: executeQuery() : ResultSet
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {//조회결과가 있다
                int snum = rs.getInt("snum");
                String stype = rs.getString("stype");
                String sname = rs.getString("sname");
                int sprice = rs.getInt("sprice");
                SnackDto dto = new SnackDto(snum, stype, sname, sprice);
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
    public int getPrice(int snum) throws SQLException, RecordNotFoundException {
        //DBMS연결
        int price=0;
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = JdbcUtil.connect();
            // 3. SQL 작성
            String sql = "SELECT SPRICE FROM SNACK WHERE SNUM = ?";
            // 4. Statement 생성
            pstmt = con.prepareStatement(sql);
            // 5. 데이터 설정
            pstmt.setInt(1,snum);
            // 6. SQL 전송, 결과수신
            //   DML전송: executeUpdate() : int
            //   SELECT전송: executeQuery() : ResultSet
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {//조회결과가 있다
                price = rs.getInt("SPRICE");
            }
        } catch (ClassNotFoundException e) {
            throw new SQLException(e);
        } finally {
            JdbcUtil.close(pstmt, con);
        }
        return price;
    }
}
