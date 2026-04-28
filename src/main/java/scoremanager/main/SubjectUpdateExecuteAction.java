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
        String subjectCode = req.getParameter("subject_code");
        String subjectName = req.getParameter("subject_name");

        // 科目名が未入力チェック
        if (subjectName == null || subjectName.isEmpty()) {

            // エラーメッセージと入力値を再セット
            req.setAttribute("errorMessage", "このフィールド入力してください");
            req.setAttribute("subject_code", subjectCode);
            req.setAttribute("subject_name", subjectName);

            // 科目変更画面へ戻す
            req.getRequestDispatcher("subject_update.jsp").forward(req, res);
            return;
        }

        // 科目情報を更新
        SubjectDao subjectDao = new SubjectDao();
        Subject subject = new Subject();
        subject.setSubjectCode(subjectCode);
        subject.setSubjectName(subjectName);
        subject.setSchool(teacher.getSchool());

        subjectDao.save(subject);

        // 変更完了画面へ転送
        req.getRequestDispatcher("subject_update_done.jsp").forward(req, res);
    }
}
``