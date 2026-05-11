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

public class TestRegistAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");
        School school = teacher.getSchool();

        // パラメータ取得
        String f1 = req.getParameter("f1"); // 入学年度
        String f2 = req.getParameter("f2"); // クラス
        String f3 = req.getParameter("f3"); // 科目
        String f4 = req.getParameter("f4"); // 回数

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

        // 回数リスト（1〜10回）
        List<Integer> noList = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            noList.add(i);
        }

        req.setAttribute("entYearList", entYearList);
        req.setAttribute("classNumList", classNumList);
        req.setAttribute("subjectList", subjectList);
        req.setAttribute("noList", noList);
        req.setAttribute("f1", f1);
        req.setAttribute("f2", f2);
        req.setAttribute("f3", f3);
        req.setAttribute("f4", f4);

        // 検索条件が全て揃っている場合
        if (f1 != null && !f1.equals("0") &&
            f2 != null && !f2.equals("0") &&
            f3 != null && !f3.equals("0") &&
            f4 != null && !f4.equals("0")) {

            TestListSubjectDao testDao = new TestListSubjectDao();
            List<TestListSubject> tests = testDao.filter(
            	    school, Integer.parseInt(f1), f2, f3, Integer.parseInt(f4));

            if (tests.isEmpty()) {
                req.setAttribute("errorMessage", "学生情報が存在しませんでした");
            } else {
                req.setAttribute("tests", tests);

                // 科目名をセット
                for (Subject s : subjectList) {
                    if (s.getCd().equals(f3)) {
                        req.setAttribute("subjectName", s.getName());
                        break;
                    }
                }
            }

        } else if (f1 != null) {
            req.setAttribute("errorMessage", "入学年度とクラスと科目と回数を選択してください");
        }

        req.getRequestDispatcher("test_regist.jsp").forward(req, res);
    }
}