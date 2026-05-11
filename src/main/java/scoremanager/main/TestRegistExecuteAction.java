package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bean.School;
import bean.Subject;
import bean.Teacher;
import bean.TestListSubject;
import dao.ClassNumDao;
import dao.SubjectDao;
import dao.TestDao;
import dao.TestListSubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class TestRegistExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");
        School school = teacher.getSchool();

        String subjectCd    = req.getParameter("subject");
        String noStr        = req.getParameter("count");
        String f1           = req.getParameter("f1");
        String f2           = req.getParameter("f2");
        String[] studentNos = req.getParameterValues("regist");

        // エラーマップ（学生番号→エラーメッセージ）
        Map<String, String> errors = new HashMap<>();

        // 入力値保持マップ
        Map<String, String> inputPoints = new HashMap<>();

        if (studentNos != null) {

            // 入力値を保持
            for (String studentNo : studentNos) {
                String pointStr = req.getParameter("point_" + studentNo);
                if (pointStr != null) {
                    inputPoints.put(studentNo, pointStr);
                }
            }

            // バリデーション
            for (String studentNo : studentNos) {
                String pointStr = req.getParameter("point_" + studentNo);
                if (pointStr == null || pointStr.isEmpty()) {
                    continue;
                }
                try {
                    int point = Integer.parseInt(pointStr);
                    if (point < 0 || point > 100) {
                        errors.put(studentNo, "0～100の範囲で入力してください");
                    }
                } catch (NumberFormatException e) {
                    errors.put(studentNo, "0～100の範囲で入力してください");
                }
            }

            // エラーがある場合は登録画面に戻す
            if (!errors.isEmpty()) {

                // 一覧を再取得
                TestListSubjectDao testDao = new TestListSubjectDao();
                List<TestListSubject> tests = testDao.filter(
                    school, Integer.parseInt(f1), f2, subjectCd, Integer.parseInt(noStr));

                // 各種リストを再セット
                LocalDate today = LocalDate.now();
                int year = today.getYear();
                List<Integer> entYearList = new ArrayList<>();
                for (int i = year - 10; i < year + 1; i++) {
                    entYearList.add(i);
                }

                ClassNumDao classNumDao = new ClassNumDao();
                List<String> classNumList = classNumDao.filter(school);

                SubjectDao subjectDao = new SubjectDao();
                List<Subject> subjectList = subjectDao.filterBySchool(school);

                List<Integer> noList = new ArrayList<>();
                for (int i = 1; i <= 10; i++) {
                    noList.add(i);
                }

                // 科目名をセット
                for (Subject s : subjectList) {
                    if (s.getCd().equals(subjectCd)) {
                        req.setAttribute("subjectName", s.getName());
                        break;
                    }
                }

                req.setAttribute("errors", errors);
                req.setAttribute("inputPoints", inputPoints);
                req.setAttribute("tests", tests);
                req.setAttribute("entYearList", entYearList);
                req.setAttribute("classNumList", classNumList);
                req.setAttribute("subjectList", subjectList);
                req.setAttribute("noList", noList);
                req.setAttribute("f1", f1);
                req.setAttribute("f2", f2);
                req.setAttribute("f3", subjectCd);
                req.setAttribute("f4", noStr);
                req.getRequestDispatcher("test_regist.jsp").forward(req, res);
                return;
            }

            // バリデーションOKの場合のみ保存
            TestDao testDao = new TestDao();
            for (String studentNo : studentNos) {
                String pointStr = req.getParameter("point_" + studentNo);
                if (pointStr == null || pointStr.isEmpty()) {
                    continue;
                }
                String classNum = req.getParameter("class_" + studentNo);
                testDao.save(school, studentNo, subjectCd,
                             Integer.parseInt(noStr), Integer.parseInt(pointStr), classNum);
            }
        }

        req.getRequestDispatcher("test_regist_done.jsp").forward(req, res);
    }
}