package scoremanager.main;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class SubjectCreateExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        // フォームから値を取得
        String cd = req.getParameter("cd");
        String name = req.getParameter("name");

        // 未入力チェック
        if (cd == null || cd.isEmpty() || name == null || name.isEmpty()) {
            req.setAttribute("errorMessage", "このフィールドを入力してください");
            req.setAttribute("cd", cd);
            req.setAttribute("name", name);
            req.getRequestDispatcher("subject_create.jsp").forward(req, res);
            return;
        }

        // Subjectを作成して登録
        Subject subject = new Subject();
        subject.setCd(cd);
        subject.setName(name);
        subject.setSchoolCd(teacher.getSchool().getSchoolCd());

        SubjectDao dao = new SubjectDao();
        dao.insert(subject);

        // 完了画面へ
        req.getRequestDispatcher("subject_create_done.jsp").forward(req, res);
    }
}