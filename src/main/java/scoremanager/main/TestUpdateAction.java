package scoremanager.main;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class TestUpdateAction extends Action {

    @Override
    public void execute(HttpServletRequest req,
                        HttpServletResponse res) throws Exception {

        // 一覧画面から渡された値をそのまま JSP へ
        req.setAttribute("studentNo", req.getParameter("studentNo"));
        req.setAttribute("subjectCd", req.getParameter("subjectCd"));
        req.setAttribute("testNo", req.getParameter("testNo"));
        req.setAttribute("entYear", req.getParameter("entYear"));
        req.setAttribute("classNum", req.getParameter("classNum"));

        req.getRequestDispatcher("test_update.jsp").forward(req, res);
    }
}
