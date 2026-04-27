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

public class StudentUpdateExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        // リクエストパラメータの取得
        String entYear   = req.getParameter("ent_year");
        String no        = req.getParameter("no");
        String name      = req.getParameter("name");
        String classNum  = req.getParameter("class_num");
        String isAttendStr = req.getParameter("is_attend");

        // 氏名が未入力チェック
        if (name == null || name.isEmpty()) {

            // クラスリスト再取得
            ClassNumDao classNumDao = new ClassNumDao();
            List<String> classNumList = classNumDao.filter(teacher.getSchool());

            req.setAttribute("errorMessage", "このフィールドを入力してください");
            req.setAttribute("ent_year", entYear);
            req.setAttribute("no", no);
            req.setAttribute("name", name);
            req.setAttribute("class_num", classNum);
            req.setAttribute("is_attend", isAttendStr != null);
            req.setAttribute("classNumList", classNumList);
            req.getRequestDispatcher("student_update.jsp").forward(req, res);
            return;
        }

        // 学生情報を更新（save()はgetで存在確認→UPDATEになる）
        StudentDao studentDao = new StudentDao();
        Student student = new Student();
        student.setStudentNo(no);
        student.setStudentName(name);
        student.setEntYear(Integer.parseInt(entYear));
        student.setClassNum(classNum);
        student.setAttend(isAttendStr != null);
        student.setSchool(teacher.getSchool());

        studentDao.save(student);

        // 変更完了画面へ転送
        req.getRequestDispatcher("student_update_done.jsp").forward(req, res);
    }
}