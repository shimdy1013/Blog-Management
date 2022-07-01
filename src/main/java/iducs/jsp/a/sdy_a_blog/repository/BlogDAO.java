package iducs.jsp.a.sdy_a_blog.repository;

import iducs.jsp.a.sdy_a_blog.model.Blog;
import iducs.jsp.a.sdy_a_blog.util.Pagination;

import java.util.List;

// crud : create read update delete
// http method : post, get, put, delete
public interface BlogDAO {
    int create(Blog blog);
    Blog read(Blog blog);
    List<Blog> readList();
    int update(Blog blog);
    int delete(Blog blog);
    int readTotalRows();
    List<Blog> readListPagination(Pagination pagination);
}
