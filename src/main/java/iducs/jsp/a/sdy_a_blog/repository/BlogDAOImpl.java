package iducs.jsp.a.sdy_a_blog.repository;

import iducs.jsp.a.sdy_a_blog.model.Blog;
import iducs.jsp.a.sdy_a_blog.util.Pagination;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BlogDAOImpl extends DAOImplOracle implements BlogDAO {
    Connection conn = null;
    Statement stmt = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    // new BlogDAOImpl()로 생성한 객체는 연결 객체를 가지게 됨
    public BlogDAOImpl() { // 생성자
        this.conn = getConnection(); // DAOImplOracle의 getConnection()을 호출하여 반환
    }

    @Override
    public int create(Blog blog) { //create 문장은 영향받은 로그의 숫자
        String query = "insert into blog201812021 values(seq_blog201812021.nextval, ?, ?, ?, ?)"; //blog -> blog201812031 /seq_blog201812031
        int rows = 0; //질의 처리 결과 영향받은 행의 수
        try {
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, blog.getName());
            pstmt.setString(2, blog.getEmail());
            pstmt.setString(3, blog.getTitle());     //getTitle
            pstmt.setString(4, blog.getContent());      //getContent
            rows = pstmt.executeUpdate(); // 1이상이면 정상, 0이하면 오류
            // executeUpdate() : insert, update, delete 처리시 사용, 영향받은 row 숫
            // pstmt.executeQuery() <- select 문 처리시 사용 ResultSet 객체를 반환
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rows;
    }

    @Override
    public Blog read(Blog blog) {
        Blog retBlog = null;
        String sql = "select * from blog201812021 where id=?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, blog.getId());
            rs = pstmt.executeQuery();
            if(rs.next()) { // rs.next()는 반환된 객체에 속한 요소가 있는지를 반환하고, 다름 요소로 접근
                retBlog = new Blog();
                retBlog.setId(rs.getLong("id"));
                retBlog.setName(rs.getString("name"));
                retBlog.setEmail(rs.getString("email"));
                retBlog.setTitle(rs.getString("title"));
                retBlog.setContent(rs.getString("content"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return retBlog;
    }

    @Override
    public List<Blog> readList() {
        ArrayList<Blog> blogList = null;
        String sql = "select * from blog201812021";
        try {
            stmt = conn.createStatement();
            // execute(sql)는 모든 질의에 사용가능, executeQuery(sql)는 select에만, executeUpdate()는 insert, update, delete에 사용 가능
            if((rs = stmt.executeQuery(sql)) != null) { // 질의 결과가 ResultSet 형태로 반환
                blogList = new ArrayList<Blog>();
                while (rs.next()) {
                    Blog blog = new Blog();
                    blog.setId(rs.getLong("id"));   // id 값도 DTO에 저장
                    blog.setName(rs.getString("name"));
                    blog.setEmail(rs.getString("email"));
                    blog.setTitle(rs.getString("title"));
                    blog.setContent(rs.getString("content"));
                    blogList.add(blog);
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return blogList;
    }

    @Override
    public int update(Blog blog) {
        String sql = "update blog201812021 set name=?, email=?, title=?, content=? where id=?";
        int rows = 0; //질의 처리 결과 영향받은 행의 수
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, blog.getName());
            pstmt.setString(2, blog.getEmail());
            pstmt.setString(3, blog.getTitle());     //getTitle
            pstmt.setString(4, blog.getContent());      //getContent
            pstmt.setLong(5, blog.getId());
            rows = pstmt.executeUpdate(); // 1이상이면 정상, 0이하면 오류
            // executeUpdate() : insert, update, delete 처리시 사용, 영향받은 row 숫
            // pstmt.executeQuery() <- select 문 처리시 사용 ResultSet 객체를 반환
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rows;
    }

    @Override
    public int delete(Blog blog) {
        String sql = "delete from blog201812021 where id=?";
        int rows = 0; //질의 처리 결과 영향받은 행의 수
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, blog.getId());
            rows = pstmt.executeUpdate(); // 1이상이면 정상, 0이하면 오류
            // executeUpdate() : insert, update, delete 처리시 사용, 영향받은 row 숫
            // pstmt.executeQuery() <- select 문 처리시 사용 ResultSet 객체를 반환
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rows;
    }

    @Override
    public int readTotalRows() {
        int rows = 0;
        String sql = "select count(*) as totalRows from blog201812021";
        try {
            stmt = conn.createStatement();
            // execute(sql)는 모든 질의에 사용가능, executeQuery(sql)는 select에만, executeUpdate()는 insert, update, delete에 사용 가능
            rs = stmt.executeQuery(sql);
            if (rs.next()) {
                rows = rs.getInt("totalRows");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rows;
    }

    @Override
    public List<Blog> readListPagination(Pagination pagination) {
        ArrayList<Blog> blogList  = null;
        String sql = "select * from (" +
                "select A.*, rownum as rnum from (" +
                "select * from blog201812021 order by id desc) A) where rnum >= ? and rnum <= ?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, pagination.getFirstRow());
            pstmt.setInt(2, pagination.getEndRow());
            rs = pstmt.executeQuery();
            // execute(sql)는 모든 질의에 사용가능, executeQuery(sql)는 select에만, executeUpdate()는 insert, update, delete에 사용 가능
            blogList = new ArrayList<Blog>();
            while (rs.next()) {
                Blog blog = new Blog();
                blog.setId(rs.getLong("id")); // id 값도 dto에 저장
                blog.setName(rs.getString("name"));
                blog.setEmail(rs.getString("email"));
                blog.setTitle(rs.getString("title"));
                blog.setContent(rs.getString("content"));
                blogList.add(blog);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return blogList;
    }
}
