package scoremanager.main;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class SubjectDeleteAction extends Action {

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
        req.setAttribute("subject_cd", subject.getCd());
        req.setAttribute("subject_name", subject.getName());

        // 削除確認画面へフォワード
        req.getRequestDispatcher("subject_delete.jsp").forward(req, res);
    }
}