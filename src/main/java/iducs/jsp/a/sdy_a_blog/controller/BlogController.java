package iducs.jsp.a.sdy_a_blog.controller;

import iducs.jsp.a.sdy_a_blog.model.Blog;
import iducs.jsp.a.sdy_a_blog.repository.BlogDAOImpl;
import iducs.jsp.a.sdy_a_blog.util.DescByBlogTitle;
import iducs.jsp.a.sdy_a_blog.util.Pagination;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

// Controller
// 첫 슬래시는 웹 애플리케이션의 시작위치임
@WebServlet(name = "post", urlPatterns = {"/blogs/post-form.do", "/blogs/post.do",
        "/blogs/list.do", "/blogs/sort.do", "/blogs/index.do",
        "/blogs/detail.do", "/blogs/updateForm.do", "/blogs/update.do", "/blogs/delete.do"})
        // urlPatterns : 다수의 url을 기술할 수 있다.

public class BlogController extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    BlogDAOImpl dao = new BlogDAOImpl();

    public void doService(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");

        /* URI를 문자열에 저장하고, http://..... war/가 contextPath가 됨,
            전체 URI에서 contextPath를 제거한 경로명으로 명령을 구분함.
         */
        String uri = request.getRequestURI();
        String contextPath = request.getContextPath();
        String command = uri.substring(contextPath.length() + 1); // blogs/post.do,  blogs/get.do가 반환됨
        String action = command.substring(command.lastIndexOf("/") + 1);
        // System.out.println("command : " + action);


        if (action.equals("post.do")) {
            Blog blog = new Blog();
            blog.setName(request.getParameter("name"));
            blog.setEmail(request.getParameter("email"));
            blog.setTitle(request.getParameter("title"));
            blog.setContent(request.getParameter("content"));

            if (dao.create(blog) > 0) { // <- DB 처리하는 코드
                // 처리된 결과를 애트리뷰트로 설정한다.
                request.setAttribute("blog", blog);
                // 처리 결과를 view에 전달한다.
                request.getRequestDispatcher("post.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }
        } else if (action.equals("list.do")) {
            ArrayList<Blog> blogList = new ArrayList<Blog>(); // 처리결과 한개 이상의 블로그를 저장하는 객체
            String pageNo = request.getParameter("pn"); // 매개변수로 전달된 현재 페이지번호를 반환
            int curPageNo = (pageNo != null)? Integer.parseInt(pageNo):1;
            int perPage = 3;        // 한 페이지에 나타나는 행의 수
            int perPagination = 3;  // 한 화면에 나타나는 페이지 번호 수

            int totalRows = dao.readTotalRows();    // dao에서 총 행의 수를 질의

            Pagination pagination = new Pagination(curPageNo, perPage, perPagination, totalRows);
            if((blogList = (ArrayList<Blog>) dao.readListPagination(pagination)) != null) { // 한 개 이상의 블로그가 반환. JCF(Java Collection Framework)에 대한 이해
                request.setAttribute("blogList", blogList);
                request.setAttribute("pagination", pagination);
                request.getRequestDispatcher("list.jsp").forward(request, response); // blogs/list.jsp로 포워딩

            } else {
                request.setAttribute("errMsg", "블로그 목록 조회 실패");
                request.getRequestDispatcher("error.jsp").forward(request, response);; // 오류
            }
        } else if (action.equals("sort.do")) {
            ArrayList<Blog> blogList = new ArrayList<Blog>(); // 처리결과 한개 이상의 블로그를 저장하는 객체
            String properties = request.getParameter("by");
            if((blogList = (ArrayList<Blog>) dao.readList()) != null) { // 한 개 이상의 블로그가 반환. JCF(Java Collection Framework)에 대한 이해
                if(properties.equals("desc,title"))
                    Collections.sort(blogList, new DescByBlogTitle()); // 블로그 제목 기준 내림차순
                request.setAttribute("blogList", blogList);
                request.getRequestDispatcher("list.jsp").forward(request, response); // blogs/list.jsp로 포워딩
            } else {
                request.setAttribute("errMsg", "블로그 목록 조회 실패");
                request.getRequestDispatcher("error.jsp").forward(request, response);; // 오류
            }
        } else if (action.equals("detail.do")) {
            // ?email=이메일 : queryString으로 요청한 경우, email 파라미터에 이메일이라는 문자열 값을 전달
            // System.out.println(request.getParameter("email")); // 요청에 포함된 파라미터 중 email 파라미터 값을 접근
            Blog blog = new Blog();
            blog.setId(Long.parseLong(request.getParameter("id")));
            Blog retBlog = null;
            if((retBlog = dao.read(blog)) != null) {
                request.setAttribute("blog", retBlog); // key -> blog
                request.getRequestDispatcher("detail.jsp").forward(request, response);
            } else {
                request.setAttribute("errMsg", "블로그 조회 실패");
                request.getRequestDispatcher("error.jsp").forward(request, response);; // 오류
            }
        } else if(action.equals("updateForm.do")){  // update를 위한 정보 조회 후 view에게 전송
            // ?email=이메일 : queryString으로 요청한 경우, email 파라미터에 이메일이라는 문자열 값을 전달
            // System.out.println(request.getParameter("email")); // 요청에 포함된 파라미터 중 email 파라미터 값을 접근
            Blog blog = new Blog();
            blog.setId(Long.parseLong(request.getParameter("id")));
            Blog retBlog = null;
            if((retBlog = dao.read(blog)) != null) {
                request.setAttribute("blog", retBlog); // key -> blog
                request.getRequestDispatcher("updateForm.jsp").forward(request, response);
            } else {
                request.setAttribute("errMsg", "블로그 업데이트를 위한 조회 실패");
                request.getRequestDispatcher("error.jsp").forward(request, response);; // 오류
            }
        } else if(action.equals("update.do")){  // DAO에게 업데이트를 요청
            Blog blog = new Blog();
            blog.setId(Long.parseLong(request.getParameter("id")));
            blog.setName(request.getParameter("name"));
            blog.setEmail(request.getParameter("email"));
            blog.setTitle(request.getParameter("title"));
            blog.setContent(request.getParameter("content"));

            if(dao.update(blog) > 0) {
                request.setAttribute("blog", blog);
                // 처리 결과를 view에 전달한다. post.jsp -> proccessok.jsp
                request.getRequestDispatcher("list.do").forward(request, response);
            } else {
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }
        } else if(action.equals("delete.do")){  // DAO에게 업데이트를 요청
            Blog blog = new Blog();
            blog.setId(Long.parseLong(request.getParameter("id")));

            if(dao.delete(blog) > 0) {
                // request.setAttribute("blog", blog);
                request.setAttribute("msg", "Success : Delete posted blog.");
                // 처리 결과를 view에 전달한다. post.jsp -> proccessok.jsp
                request.getRequestDispatcher("../status/message.jsp").forward(request, response);
            } else {
                request.setAttribute("msg", "Fail : Delete posted blog.");
                request.getRequestDispatcher("../status/message.jsp").forward(request, response);
            }
        }
        /*
        String inputName = request.getParameter("name");
        String inputEmail = request.getParameter("email");
        String phone = request.getParameter("phone");

        Blog blog = new Blog();
        blog.setName(inputName);
        blog.setEmail(inputEmail);
        blog.setPhone(phone);
        blog.setMessage(request.getParameter("message"));

        request.setAttribute("list", blog);

        request.getRequestDispatcher("postView.jsp").forward(request, response);
        */
    }
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
       doService(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doService(request, response);
    }

    public void destroy() {
    }
}