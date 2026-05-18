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

public class SubjectCreateExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest req,
                        HttpServletResponse res) throws Exception {

        /* ===============================
         * セッション取得
         * =============================== */
        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        /* ===============================
         * 入力値取得
         * =============================== */
        String cd = req.getParameter("cd");
        String name = req.getParameter("name");

        /* ===============================
         * エラー用Map
         * =============================== */
        Map<String, String> errors = new HashMap<>();

        /* ===============================
         * ① 未入力チェック
         * =============================== */
        if (cd == null || cd.isEmpty()) {
            errors.put("cd", "このフィールドを入力してください");
        }

        if (name == null || name.isEmpty()) {
            errors.put("name", "このフィールドを入力してください");
        }

        /* ===============================
         * ② 文字数チェック
         * =============================== */
        if (cd != null && !cd.isEmpty() && cd.length() > 10) {
            errors.put("cd", "10文字以内で入力してください");
        }

        if (name != null && !name.isEmpty() && name.length() > 30) {
            errors.put("name", "30文字以内で入力してください");
        }

        /* ===============================
         * ③ 重複チェック
         * =============================== */
        SubjectDao dao = new SubjectDao();

        if (cd != null && !cd.isEmpty()
                && dao.get(cd, teacher.getSchool()) != null) {

            errors.put("cd", "科目コードが重複しています");
        }

        /* ===============================
         * エラーがあれば戻る
         * =============================== */
        if (!errors.isEmpty()) {

            req.setAttribute("errors", errors);

            // 入力値を保持
            req.setAttribute("cd", cd);
            req.setAttribute("name", name);

            req.getRequestDispatcher("subject_create.jsp")
               .forward(req, res);
            return;
        }

        /* ===============================
         * 登録処理
         * =============================== */
        Subject subject = new Subject();
        subject.setCd(cd);
        subject.setName(name);
        subject.setSchoolCd(teacher.getSchool().getSchoolCd());

        dao.insert(subject);

        /* ===============================
         * 完了画面へ
         * =============================== */
        req.getRequestDispatcher("subject_create_done.jsp")
           .forward(req, res);
    }
}