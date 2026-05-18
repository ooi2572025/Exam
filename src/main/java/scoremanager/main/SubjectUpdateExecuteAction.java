package scoremanager.main;

import java.util.HashMap;
import java.util.Map;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class SubjectUpdateExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest req,
                        HttpServletResponse res) throws Exception {

        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        String cd = req.getParameter("cd");
        String name = req.getParameter("name");

        Map<String, String> errors = new HashMap<>();

        /* ======================
         * サーバー側チェック（②）
         * ====================== */
        if (name == null || name.isEmpty()) {
            errors.put("name", "このフィールドを入力してください");
        }

        // 文字数チェック
        if (name != null && name.length() > 30) {
            errors.put("name", "30文字以内で入力してください");
        }

        /* ======================
         * エラーがあれば戻る
         * ====================== */
        if (!errors.isEmpty()) {

            req.setAttribute("errors", errors);
            req.setAttribute("cd", cd);
            req.setAttribute("name", name);

            req.getRequestDispatcher("subject_update.jsp")
               .forward(req, res);
            return;
        }

        /* ======================
         * 更新処理
         * ====================== */
        SubjectDao subjectDao = new SubjectDao();

        Subject subject = new Subject();
        subject.setCd(cd);
        subject.setName(name);
        subject.setSchoolCd(teacher.getSchool().getSchoolCd());

        subjectDao.update(subject);

        req.getRequestDispatcher("subject_update_done.jsp")
           .forward(req, res);
    }
}