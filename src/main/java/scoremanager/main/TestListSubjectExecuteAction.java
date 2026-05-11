package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Subject;
import bean.Teacher;
import bean.TestListSubject;
import dao.ClassNumDao;
import dao.SubjectDao;
import dao.TestListSubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class TestListSubjectExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");
        School school = teacher.getSchool();

        String f1 = req.getParameter("f1"); // 入学年度
        String f2 = req.getParameter("f2"); // クラス
        String f3 = req.getParameter("f3"); // 科目

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
        req.setAttribute("f1", f1);
        req.setAttribute("f2", f2);
        req.setAttribute("f3", f3);

        // 入力チェック
        if (f1 == null || f1.equals("0") ||
            f2 == null || f2.equals("0") ||
            f3 == null || f3.equals("0")) {

            req.setAttribute("errorMessage", "入学年度とクラスと科目を選択してください");
            req.getRequestDispatcher("test_list_subject.jsp").forward(req, res);
            return;
        }

        // 成績データ取得
        TestListSubjectDao dao = new TestListSubjectDao();
        List<TestListSubject> tests = dao.filter(
            school, Integer.parseInt(f1), f2, f3);

        if (tests.isEmpty()) {
            req.setAttribute("errorMessage", "学生情報が存在しませんでした");
            req.getRequestDispatcher("test_list_subject.jsp").forward(req, res);
            return;
        }

        // 科目名をセット
        for (Subject s : subjectList) {
            if (s.getCd().equals(f3)) {
                req.setAttribute("subjectName", s.getName());
                break;
            }
        }

        req.setAttribute("tests", tests);
        req.getRequestDispatcher("test_list_subject.jsp").forward(req, res);
    }
}