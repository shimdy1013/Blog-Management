package iducs.jsp.a.sdy_a_blog.controller;

import iducs.jsp.a.sdy_a_blog.model.Blog;
import iducs.jsp.a.sdy_a_blog.model.Member;
import iducs.jsp.a.sdy_a_blog.repository.BlogDAOImpl;
import iducs.jsp.a.sdy_a_blog.repository.MemberDAOImpl;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "members", urlPatterns = {"/members/post-form.do", "/members/post.do", "/members/list.do", "/members/sort.do",
        "/members/detail.do", "/members/update-form.do", "/members/update.do", "/members/delete.do" , "/members/login-form.do",
        "/members/login.do", "/members/logout.do"})
public class MemberController extends HttpServlet {
    // DAO or Repository 객체 생성
    MemberDAOImpl dao = new MemberDAOImpl();

    protected void doService(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(); // session 객체 생성
        request.setCharacterEncoding("UTF-8");
        String uri = request.getRequestURI();
        String contextPath = request.getContextPath();
        String command = uri.substring(contextPath.length() + 1); // blogs/post.do,  blogs/get.do가 반환됨
        String action = command.substring(command.lastIndexOf("/") + 1);
        Member member = new Member();
        if(action.equals("post-form.do")) {
            request.getRequestDispatcher("member-post-form.jsp").forward(request, response);
        } else if(action.equals("post.do")) {
            member.setEmail(request.getParameter("email"));
            member.setPw(request.getParameter("pw"));
            member.setName(request.getParameter("name"));
            member.setPhone(request.getParameter("phone"));
            member.setAddress(request.getParameter("address"));

            if (dao.create(member) > 0) { // <- DB 처리하는 코드
                // 처리된 결과를 애트리뷰트로 설정한다.
                request.setAttribute("member", member);
                request.setAttribute("msg", "Success");
                // 처리 결과를 view에 전달한다.
                request.getRequestDispatcher("../status/message.jsp").forward(request, response);
            } else {
                request.setAttribute("msg", "Fail");
                request.getRequestDispatcher("../status/message.jsp").forward(request, response);
            }
        }

        if(action.equals("login-form.do")) {
            request.getRequestDispatcher("member-login-form.jsp").forward(request, response);
        } else if(action.equals("login.do")) {
            member.setEmail(request.getParameter("email"));
            member.setPw(request.getParameter("pw"));
            Member retMember = null;
            if ((retMember = dao.read(member)) != null) { // <- DB 처리하는 코드
                // 처리된 결과를 애트리뷰트로 설정한다.
                session.setAttribute("logined", retMember);
                request.getRequestDispatcher("../main/index.jsp").forward(request, response);
            } else {
                request.setAttribute("msg", "Fail");
                request.getRequestDispatcher("../status/message.jsp").forward(request, response);
            }
        } else if(action.equals("logout.do")) {
            session.invalidate();   // session 객체 무효화 -> logined 속성값이 메모리에서 삭제
            request.getRequestDispatcher("../main/index.jsp").forward(request, response);
        } else if(action.equals("list.do")) {
            ArrayList<Member> memberList = new ArrayList<Member>(); // 처리결과 한개 이상의 블로그를 저장하는 객체

            if((memberList = (ArrayList<Member>) dao.readList()) != null) { // 한 개 이상의 멤버가 반환.
                request.setAttribute("memberList", memberList);
                request.getRequestDispatcher("member-list-view.jsp").forward(request, response); // blogs/list.jsp로 포워딩
            } else {
                request.setAttribute("msg", "Fail");
                request.getRequestDispatcher("../status/message.jsp").forward(request, response);
            }
        } else if (action.equals("detail.do")) {
            // ?email=이메일 : queryString으로 요청한 경우, email 파라미터에 이메일이라는 문자열 값을 전달
            // System.out.println(request.getParameter("email")); // 요청에 포함된 파라미터 중 email 파라미터 값을 접근
            member.setId(Long.parseLong(request.getParameter("id")));
            Member retMember = null;
            if((retMember = dao.read1(member)) != null) {
                request.setAttribute("member", retMember);
                request.getRequestDispatcher("member-detail.jsp").forward(request, response);
            } else {
                request.setAttribute("errMsg", "Fail");
                request.getRequestDispatcher("../status/message.jsp").forward(request, response);; // 오류
            }
        } else if(action.equals("update-form.do")){  // update를 위한 정보 조회 후 view에게 전송
            // ?email=이메일 : queryString으로 요청한 경우, email 파라미터에 이메일이라는 문자열 값을 전달
            // System.out.println(request.getParameter("email")); // 요청에 포함된 파라미터 중 email 파라미터 값을 접근
            member.setId(Long.parseLong(request.getParameter("id")));
            Member retMember = null;
            if((retMember = dao.read1(member)) != null) {
                request.setAttribute("member", retMember);
                request.getRequestDispatcher("member-update-form.jsp").forward(request, response);
            } else {
                request.setAttribute("errMsg", "Fail");
                request.getRequestDispatcher("error.jsp").forward(request, response); // 오류
            }
        } else if(action.equals("update.do")){  // DAO에게 업데이트를 요청
            member.setId(Long.parseLong(request.getParameter("id")));
            member.setEmail(request.getParameter("email"));
            member.setPw(request.getParameter("pw"));
            member.setName(request.getParameter("name"));
            member.setPhone(request.getParameter("phone"));
            member.setAddress(request.getParameter("address"));

            if(dao.update(member) > 0) {
                request.setAttribute("member", member);

                request.setAttribute("msg", "Success");
                request.getRequestDispatcher("../status/message.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }
        } else if(action.equals("delete.do")){  // DAO에게 업데이트를 요청
            member.setId(Long.parseLong(request.getParameter("id")));

            if(dao.delete(member) > 0) {
                // request.setAttribute("blog", blog);
                request.setAttribute("msg", "Success");
                // 처리 결과를 view에 전달한다. post.jsp -> proccessok.jsp
                request.getRequestDispatcher("../status/message.jsp").forward(request, response);
            } else {
                request.setAttribute("msg", "Fail");
                request.getRequestDispatcher("../status/message.jsp").forward(request, response);
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doService(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doService(request, response);
    }
}
