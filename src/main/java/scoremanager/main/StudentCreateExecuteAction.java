package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import bean.Student;
import bean.Teacher;
import dao.ClassNumDao;
import dao.StudentDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class StudentCreateExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        // セッションからログインユーザー（Teacher）を取得
        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        // リクエストパラメータの取得（設計書のname属性に合わせる）
        String entYear  = req.getParameter("ent_year");
        String no       = req.getParameter("no");
        String name     = req.getParameter("name");
        String classNum = req.getParameter("class_num");

        // 入学年度リスト（エラー時の画面再表示用）
        LocalDate today = LocalDate.now();
        int year = today.getYear();
        List<Integer> entYearList = new ArrayList<>();
        for (int i = year - 10; i < year + 1; i++) {
            entYearList.add(i);
        }

        // クラスリスト（エラー時の画面再表示用）
        ClassNumDao classNumDao = new ClassNumDao();
        List<String> classNumList = classNumDao.filter(teacher.getSchool());

        // 入力値のチェック（学生番号・氏名が未入力）
        if (no == null || no.isEmpty() ||
            name == null || name.isEmpty()) {
            req.setAttribute("errorMessage", "このフィールドを入力してください");
            req.setAttribute("ent_year", entYear);
            req.setAttribute("no", no);
            req.setAttribute("name", name);
            req.setAttribute("class_num", classNum);
            req.setAttribute("entYearList", entYearList);
            req.setAttribute("classNumList", classNumList);
            req.getRequestDispatcher("student_create.jsp").forward(req, res);
            return;
        }

        // 入学年度が未入力チェック
        if (entYear == null || entYear.isEmpty()) {
            req.setAttribute("errorMessage", "入学年度を選択してください");
            req.setAttribute("no", no);
            req.setAttribute("name", name);
            req.setAttribute("class_num", classNum);
            req.setAttribute("entYearList", entYearList);
            req.setAttribute("classNumList", classNumList);
            req.getRequestDispatcher("student_create.jsp").forward(req, res);
            return;
        }

        StudentDao studentDao = new StudentDao();

        // 学生番号の重複チェック
        Student existing = studentDao.get(no);
        if (existing != null) {
            req.setAttribute("errorMessage", "学生番号が重複しています");
            req.setAttribute("ent_year", entYear);
            req.setAttribute("no", no);
            req.setAttribute("name", name);
            req.setAttribute("class_num", classNum);
            req.setAttribute("entYearList", entYearList);
            req.setAttribute("classNumList", classNumList);
            req.getRequestDispatcher("student_create.jsp").forward(req, res);
            return;
        }

        // 学生をDBに登録
        Student student = new Student();
        student.setStudentNo(no);
        student.setStudentName(name);
        student.setEntYear(Integer.parseInt(entYear));
        student.setClassNum(classNum);
        student.setAttend(true);
        student.setSchool(teacher.getSchool());

        studentDao.save(student);

        // 登録完了画面へ転送
        req.getRequestDispatcher("student_create_done.jsp").forward(req, res);
    }
}