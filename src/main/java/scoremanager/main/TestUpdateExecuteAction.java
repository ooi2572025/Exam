package scoremanager.main;

import bean.School;
import bean.Teacher;
import dao.TestDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class TestUpdateExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest req,
                        HttpServletResponse res) throws Exception {

        /* ===============================
         * ログイン情報取得
         * =============================== */
        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");
        School school = teacher.getSchool();

        /* ===============================
         * パラメータ取得
         * =============================== */
        String studentNo = req.getParameter("studentNo");
        String subjectCd = req.getParameter("subjectCd");
        int testNo       = Integer.parseInt(req.getParameter("testNo"));
        int point        = Integer.parseInt(req.getParameter("point"));

        /* ===============================
         * 更新処理
         * =============================== */
        TestDao testDao = new TestDao();
        testDao.updatePoint(school, studentNo, subjectCd, testNo, point);

        /* ===============================
         * ★ 追加部分：検索条件の安全な引き継ぎ
         * =============================== */
        String f1 = req.getParameter("f1");
        String f2 = req.getParameter("f2");
        String f3 = req.getParameter("f3");
        String f4 = req.getParameter("f4");

        // null や空文字を防ぐ
        if (f1 == null || f1.isEmpty()) f1 = "";
        if (f2 == null || f2.isEmpty()) f2 = "";
        if (f3 == null || f3.isEmpty()) f3 = "";
        if (f4 == null || f4.isEmpty()) f4 = "";

        req.setAttribute("f1", f1);
        req.setAttribute("f2", f2);
        req.setAttribute("f3", f3);
        req.setAttribute("f4", f4);

        /* ===============================
         * 完了画面へ
         * =============================== */
        req.getRequestDispatcher("test_update_done.jsp")
           .forward(req, res);
    }
}