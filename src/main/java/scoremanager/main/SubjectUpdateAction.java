package scoremanager.main;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class SubjectUpdateAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        // URLパラメータから科目コードを取得
        String code = req.getParameter("code");

        // DBから科目情報を取得
        SubjectDao subjectDao = new SubjectDao();
        Subject subject = subjectDao.get(code, teacher.getSchool());

        // リクエストに科目情報をセット
     // リクエストに科目情報をセット
        req.setAttribute("cd", subject.getCd());
        req.setAttribute("name", subject.getName());
        // JSPへフォワード
        req.getRequestDispatcher("subject_update.jsp").forward(req, res);
    }
}