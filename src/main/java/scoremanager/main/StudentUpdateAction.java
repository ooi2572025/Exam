package scoremanager.main;

import java.util.List;

import bean.Student;
import bean.Teacher;
import dao.ClassNumDao;
import dao.StudentDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class StudentUpdateAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        // URLパラメータから学生番号を取得（student_list.jspのリンクより）
        String no = req.getParameter("no");

        // DBから学生情報を取得
        StudentDao studentDao = new StudentDao();
        Student student = studentDao.get(no);

        // クラスリストの取得
        ClassNumDao classNumDao = new ClassNumDao();
        List<String> classNumList = classNumDao.filter(teacher.getSchool());

        // リクエストに学生情報をセット
        req.setAttribute("ent_year", student.getEntYear());
        req.setAttribute("no", student.getStudentNo());
        req.setAttribute("name", student.getStudentName());
        req.setAttribute("class_num", student.getClassNum());
        req.setAttribute("is_attend", student.isAttend());
        req.setAttribute("classNumList", classNumList);

        req.getRequestDispatcher("student_update.jsp").forward(req, res);
    }
}