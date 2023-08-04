package cinema.snack.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import cinema.dtos.SnackOrderDto;
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
            sql += "VALUES(SNACKORDER_SEQ.NEXTVAL,?,?,?,?,? )";
            // 4. Statement 생성
            pstmt = con.prepareStatement(sql);
            // 5. 데이터 설정

            pstmt.setInt(1, dto.getPopnum());
            pstmt.setInt(2, dto.getPopcnt());
            pstmt.setInt(3, dto.getBnum());
            pstmt.setInt(4, dto.getBcnt());
            pstmt.setInt(5, dto.getStcnt());
            // 6. SQL 전송, 결과수신
            int count = pstmt.executeUpdate();
        } catch (ClassNotFoundException e) {
            throw new SQLException(e);
        } finally {
            JdbcUtil.close(pstmt, con);
        }
    }
}

