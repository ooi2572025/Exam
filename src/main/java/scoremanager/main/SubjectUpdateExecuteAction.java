package scoremanager.main;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class SubjectUpdateExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        // リクエストパラメータの取得
        String cd = req.getParameter("cd");
        String name = req.getParameter("name");

        // 科目名が未入力チェック
        if (name == null || name.isEmpty()) {
            req.setAttribute("errorMessage", "このフィールドを入力してください");
            req.setAttribute("cd", cd);
            req.setAttribute("name", name);
            req.getRequestDispatcher("subject_update.jsp").forward(req, res);
            return;
        }

        // 科目情報を更新
        SubjectDao subjectDao = new SubjectDao();
        Subject subject = new Subject();
        subject.setCd(cd);
        subject.setName(name);
        subject.setSchoolCd(teacher.getSchool().getSchoolCd());

        subjectDao.update(subject);

        // 変更完了画面へ転送
        req.getRequestDispatcher("subject_update_done.jsp").forward(req, res);
    }
}