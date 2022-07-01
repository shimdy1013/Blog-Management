package iducs.jsp.a.sdy_a_blog.repository;

import java.sql.*;

public class DAOImplOracle implements DAO {
    private Connection conn = null;
    @Override
    public Connection getConnection() {
        String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:XE";
        String dbUser = "system";
        String dbPass = "cometrue";
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            conn = DriverManager.getConnection(jdbcUrl, dbUser, dbPass);
            System.out.println("getConnction");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return conn;
    }

    @Override
    public void closeResources(Connection conn, Statement stmt, PreparedStatement pstmt, ResultSet rs) {

    }
}
