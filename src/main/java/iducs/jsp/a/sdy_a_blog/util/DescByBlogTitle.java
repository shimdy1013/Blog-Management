package iducs.jsp.a.sdy_a_blog.util;

import iducs.jsp.a.sdy_a_blog.model.Blog;

import java.util.Comparator;

public class DescByBlogTitle implements Comparator<Blog> {

    @Override
    public int compare(Blog o1, Blog o2) {
        return o2.getContent().compareTo(o1.getTitle());
        // o2가 o1보다 작으면 음수, 크면 양수를, 같으면 0을 반환
        // 오름차순 : return o1.getContent().compareTo(o2.getTitle());
        // 이메일 기준 내림차순 : return o2.getEmail().compareTo(o1.getEmail());
    }
}
