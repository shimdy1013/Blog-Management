package iducs.jsp.a.sdy_a_blog.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

// Data Access Obejct : 연결 및 자원 관리 메소드 정의
public interface DAO {
    Connection getConnection();
    void closeResources(Connection conn, Statement stmt, PreparedStatement pstmt, ResultSet rs);
}
