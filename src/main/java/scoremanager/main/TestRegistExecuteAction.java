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

        /* ===============================
         * セッション・共通パラメータ取得
         * =============================== */
        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");
        School school = teacher.getSchool();

        String subjectCd    = req.getParameter("subject");
        String noStr        = req.getParameter("count");
        String f1           = req.getParameter("f1");
        String f2           = req.getParameter("f2");
        String[] studentNos = req.getParameterValues("regist");

        /* ==================================================
         * 【追加】変更ボタン押下判定（最優先）
         * ================================================== */
        if (studentNos != null) {
            for (String studentNo : studentNos) {
                if (req.getParameter("update_" + studentNo) != null) {

                    // 変更画面へ渡す値
                    req.setAttribute("studentNo", studentNo);
                    req.setAttribute("subjectCd", subjectCd);
                    req.setAttribute("testNo", noStr);
                    req.setAttribute("point", req.getParameter("point_" + studentNo));

                    req.getRequestDispatcher("test_update.jsp")
                       .forward(req, res);
                    return;
                }
            }
        }

        /* ===============================
         * 削除処理
         * =============================== */
        if (studentNos != null) {
            for (String studentNo : studentNos) {
                if (req.getParameter("delete_" + studentNo) != null) {

                    TestDao testDao = new TestDao();
                    testDao.delete(school, studentNo, subjectCd, Integer.parseInt(noStr));

                    // 画面再表示
                    reloadPage(req, res, school, f1, f2, subjectCd, noStr);
                    return;
                }
            }
        }

        /* ===============================
         * 入力チェック用マップ
         * =============================== */
        Map<String, String> errors = new HashMap<>();
        Map<String, String> inputPoints = new HashMap<>();

        if (studentNos != null) {

            // 入力値保持
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

            /* ===============================
             * エラー時の再表示
             * =============================== */
            if (!errors.isEmpty()) {

                reloadPage(req, res, school, f1, f2, subjectCd, noStr);
                req.setAttribute("errors", errors);
                req.setAttribute("inputPoints", inputPoints);
                return;
            }

            /* ===============================
             * 保存処理
             * =============================== */
            TestDao testDao = new TestDao();
            for (String studentNo : studentNos) {
                String pointStr = req.getParameter("point_" + studentNo);
                if (pointStr == null || pointStr.isEmpty()) {
                    continue;
                }
                String classNum = req.getParameter("class_" + studentNo);
                testDao.save(
                    school,
                    studentNo,
                    subjectCd,
                    Integer.parseInt(noStr),
                    Integer.parseInt(pointStr),
                    classNum
                );
            }
        }

        req.getRequestDispatcher("test_regist_done.jsp").forward(req, res);
    }

    /* ==================================================
     * 【共通】登録画面再表示処理
     * ================================================== */
    private void reloadPage(HttpServletRequest req,
                            HttpServletResponse res,
                            School school,
                            String f1,
                            String f2,
                            String subjectCd,
                            String noStr) throws Exception {

        TestListSubjectDao testListDao = new TestListSubjectDao();
        List<TestListSubject> tests =
            testListDao.filter(
                school,
                Integer.parseInt(f1),
                f2,
                subjectCd,
                Integer.parseInt(noStr)
            );

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
    }
}
