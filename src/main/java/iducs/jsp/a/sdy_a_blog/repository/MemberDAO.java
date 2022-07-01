package iducs.jsp.a.sdy_a_blog.repository;

import iducs.jsp.a.sdy_a_blog.model.Blog;
import iducs.jsp.a.sdy_a_blog.model.Member;

import java.util.List;

public interface MemberDAO {
    int create(Member member);
    Member read(Member member);
    Member read1(Member member);
    List<Member> readList();
    int update(Member member);
    int delete(Member member);
}
