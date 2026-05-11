package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Student;
import bean.Subject;
import bean.Teacher;
import bean.TestListStudent;
import dao.ClassNumDao;
import dao.StudentDao;
import dao.SubjectDao;
import dao.TestListStudentDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class TestListStudentExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");
        School school = teacher.getSchool();

        String f4 = req.getParameter("f4"); // 学生番号

        // 入学年度リスト
        LocalDate today = LocalDate.now();
        int year = today.getYear();
        List<Integer> entYearList = new ArrayList<>();
        for (int i = year - 10; i < year + 1; i++) {
            entYearList.add(i);
        }

        // クラスリスト
        ClassNumDao classNumDao = new ClassNumDao();
        List<String> classNumList = classNumDao.filter(school);

        // 科目リスト
        SubjectDao subjectDao = new SubjectDao();
        List<Subject> subjectList = subjectDao.filterBySchool(school);

        req.setAttribute("entYearList", entYearList);
        req.setAttribute("classNumList", classNumList);
        req.setAttribute("subjectList", subjectList);
        req.setAttribute("f4", f4);

        // 未入力チェック
        if (f4 == null || f4.isEmpty()) {
            req.setAttribute("errorMsg", "このフィールドを入力してください");
            req.getRequestDispatcher("test_list_student.jsp").forward(req, res);
            return;
        }

        // 学生情報取得
        StudentDao studentDao = new StudentDao();
        Student student = studentDao.get(f4);

        if (student == null) {
            req.setAttribute("errorMsg", "学生情報が存在しませんでした");
            req.getRequestDispatcher("test_list_student.jsp").forward(req, res);
            return;
        }

        // 成績一覧取得
        TestListStudentDao testDao = new TestListStudentDao();
        List<TestListStudent> scoreList = testDao.filter(school, f4);

        // 点数がnullのものを除外
        scoreList.removeIf(s -> s.getPoint() == null);

        req.setAttribute("searchDone", true);
        req.setAttribute("studentInfo", student);
        req.setAttribute("scoreList", scoreList.isEmpty() ? null : scoreList);

        req.getRequestDispatcher("test_list_student.jsp").forward(req, res);
    }
}