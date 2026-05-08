package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Subject;
import bean.Teacher;
import dao.ClassNumDao;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class TestListAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        HttpSession session = req.getSession();

        // ★ ログインチェック
        Teacher teacher = (Teacher) session.getAttribute("user");
        if (teacher == null || teacher.getSchool() == null) {
            res.sendRedirect("Login.action");
            return;
        }

        School school = teacher.getSchool();

        /* ===== 入学年度リスト（今年〜10年前） ===== */
        LocalDate today = LocalDate.now();
        int year = today.getYear();
        List<Integer> entYearList = new ArrayList<>();
        for (int i = year - 10; i <= year; i++) {
            entYearList.add(i);
        }

        /* ===== クラスリスト ===== */
        ClassNumDao classNumDao = new ClassNumDao();
        List<String> classNumList = classNumDao.filter(school);

        /* ===== 科目リスト ===== */
        SubjectDao subjectDao = new SubjectDao();
        List<Subject> subjectList = subjectDao.filterBySchool(school);

        /* ===== JSPへ渡す（JSP の名前に合わせる） ===== */
        req.setAttribute("ent_year_set", entYearList);
        req.setAttribute("class_num_set", classNumList);
        req.setAttribute("subject_set", subjectList);

        req.getRequestDispatcher("test_list.jsp").forward(req, res);
    }
}
