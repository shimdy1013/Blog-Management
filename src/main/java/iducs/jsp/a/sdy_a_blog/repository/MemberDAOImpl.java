package iducs.jsp.a.sdy_a_blog.repository;

import iducs.jsp.a.sdy_a_blog.model.Blog;
import iducs.jsp.a.sdy_a_blog.model.Member;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MemberDAOImpl extends DAOImplOracle implements MemberDAO {
    Connection conn = null;
    Statement stmt = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    public MemberDAOImpl() {
        conn = getConnection();
    }
    @Override
    public int create(Member member) {
        String query = "insert into blogger201812021 values(seq_blogger201812021.nextval, ?, ?, ?, ?, ?)"; //blog -> blog201812031 /seq_blog201812031
        int rows = 0; //질의 처리 결과 영향받은 행의 수
        try {
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, member.getEmail());
            pstmt.setString(2, member.getPw());
            pstmt.setString(3, member.getName());
            pstmt.setString(4, member.getPhone());
            pstmt.setString(5, member.getAddress());

            rows = pstmt.executeUpdate(); // 1이상이면 정상, 0이하면 오류
            // executeUpdate() : insert, update, delete 처리시 사용, 영향받은 row 숫
            // pstmt.executeQuery() <- select 문 처리시 사용 ResultSet 객체를 반환
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rows;

    }

    @Override
    public Member read(Member member) {
        Member retMember = null;
        String sql = "select * from blogger201812021 where email=? and pw=?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, member.getEmail());
            pstmt.setString(2, member.getPw());
            rs = pstmt.executeQuery();

            if(rs.next()) { // rs.next()는 반환된 객체에 속한 요소가 있는지를 반환하고, 다름 요소로 접근
                retMember = new Member();
                retMember.setId(rs.getLong("id"));
                retMember.setEmail(rs.getString("email"));
                retMember.setPw(rs.getString("pw"));
                retMember.setName(rs.getString("name"));
                retMember.setPhone(rs.getString("phone"));
                retMember.setAddress(rs.getString("address"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return retMember;
    }

    @Override
    public Member read1(Member member) {
        Member retMember = null;
        String sql = "select * from blogger201812021 where id=?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, member.getId());
            rs = pstmt.executeQuery();

            if(rs.next()) { // rs.next()는 반환된 객체에 속한 요소가 있는지를 반환하고, 다름 요소로 접근
                retMember = new Member();
                retMember.setId(rs.getLong("id"));
                retMember.setEmail(rs.getString("email"));
                retMember.setPw(rs.getString("pw"));
                retMember.setName(rs.getString("name"));
                retMember.setPhone(rs.getString("phone"));
                retMember.setAddress(rs.getString("address"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return retMember;
    }

    @Override
    public List<Member> readList() {
        ArrayList<Member> memberList  = null;
        String sql = "select * from blogger201812021";
        try {
            stmt = conn.createStatement();
            // execute(sql)는 모든 질의에 사용가능, executeQuery(sql)는 select에만
            // executeUpdate()는 insert, update, delete에 사용 가능
            if((rs = stmt.executeQuery(sql)) != null) { // 질의 결과가 ResultSet 형태로 반환
                memberList = new ArrayList<Member>();
                while (rs.next()) {
                    Member member = new Member();
                    member = setMemberRs(rs);
                    memberList.add(member);
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return memberList;
    }
    private Member setMemberRs(ResultSet rs) throws SQLException {
        // rs : record 집합, re.getString(1) : 현재 레코드의 첫번째 필드 값
        Member retMember = new Member();
        retMember.setId(rs.getLong(1));
        retMember.setEmail(rs.getString(2));
        retMember.setPw(rs.getString(3));
        retMember.setName(rs.getString(4));
        retMember.setPhone(rs.getString(5));
        retMember.setAddress(rs.getString(6));
        return retMember;
    }

    @Override
    public int update(Member member) {
        String sql = "update blogger201812021 set email=?, pw=?, name=?, phone=?, address=? where id=?";
        int rows = 0; //질의 처리 결과 영향받은 행의 수
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, member.getEmail());
            pstmt.setString(2, member.getPw());
            pstmt.setString(3, member.getName());
            pstmt.setString(4, member.getPhone());
            pstmt.setString(5, member.getAddress());
            pstmt.setLong(6, member.getId());
            rows = pstmt.executeUpdate(); // 1이상이면 정상, 0이하면 오류
            // executeUpdate() : insert, update, delete 처리시 사용, 영향받은 row 숫
            // pstmt.executeQuery() <- select 문 처리시 사용 ResultSet 객체를 반환
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rows;
    }

    @Override
    public int delete(Member member) {
        String sql = "delete from blogger201812021 where id=?";
        int rows = 0; //질의 처리 결과 영향받은 행의 수
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, member.getId());
            rows = pstmt.executeUpdate(); // 1이상이면 정상, 0이하면 오류
            // executeUpdate() : insert, update, delete 처리시 사용, 영향받은 row 숫
            // pstmt.executeQuery() <- select 문 처리시 사용 ResultSet 객체를 반환
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rows;
    }
}
