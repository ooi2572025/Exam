package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Teacher;
import bean.Test;
import dao.ClassNumDao;
import dao.SubjectDao;
import dao.TestDao;
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

        // ▼ パラメータ取得
        String f1 = req.getParameter("f1"); // 入学年度
        String f2 = req.getParameter("f2"); // クラス
        String f3 = req.getParameter("f3"); // 科目
        String f4 = req.getParameter("f4"); // 回数

        // ▼ 入学年度リスト
        LocalDate today = LocalDate.now();
        int year = today.getYear();
        List<Integer> entYearList = new ArrayList<>();
        for (int i = year - 10; i <= year; i++) {
            entYearList.add(i);
        }

        // ▼ クラスリスト
        ClassNumDao classNumDao = new ClassNumDao();
        List<String> classNumList = classNumDao.filter(school);

        // ▼ 科目リスト
        SubjectDao subjectDao = new SubjectDao();
        List<?> subjectList = subjectDao.filterBySchool(school);

        // ▼ JSP にセット（プルダウン維持）
        req.setAttribute("entYearList", entYearList);
        req.setAttribute("classNumList", classNumList);
        req.setAttribute("subjectList", subjectList);

        req.setAttribute("f1", f1);
        req.setAttribute("f2", f2);
        req.setAttribute("f3", f3);
        req.setAttribute("f4", f4);

        // ▼ 入力チェック
        if (f1 == null || f1.equals("0") ||
            f2 == null || f2.equals("0") ||
            f3 == null || f3.equals("0") ||
            f4 == null || f4.equals("0")) {

            req.setAttribute("errorMessage", "入学年度・クラス・科目・回数を選択してください");
            req.getRequestDispatcher("test_list_student.jsp").forward(req, res);
            return;
        }

        // ▼ 成績データ取得
        TestDao dao = new TestDao();
        List<Test> list = dao.filter(
                school,
                Integer.parseInt(f1),
                f2,
                f3,
                Integer.parseInt(f4)
        );

        // ▼ 結果チェック
        if (list.isEmpty()) {
            req.setAttribute("errorMessage", "該当する成績情報は存在しませんでした");
            req.getRequestDispatcher("test_list_student.jsp").forward(req, res);
            return;
        }

        // ▼ 成績リストを JSP に渡す
        req.setAttribute("tests", list);

        // ▼ 結果表示 JSP へ
        req.getRequestDispatcher("test_list_student.jsp").forward(req, res);
    }
}
