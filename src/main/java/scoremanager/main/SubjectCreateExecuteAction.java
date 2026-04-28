package scoremanager.main;

import bean.School;
import bean.Subject;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class SubjectCreateExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        // フォームから値を取得
        String cd = req.getParameter("cd");     // ← id ではなく cdｓ
        String name = req.getParameter("name");

        // ログイン中の先生の school を取得
        School school = (School) req.getSession().getAttribute("school");

        // Subject を作成（画像の JavaBean 仕様に合わせる）
        Subject subject = new Subject();
        subject.setCd(cd);
        subject.setName(name);
        subject.setSchoolCd(school.getSchoolCd());   // ← School オブジェクトではなく String

        // DAO を使って登録
        SubjectDao dao = new SubjectDao();
        dao.insert(subject);

        // 完了画面へ
        req.getRequestDispatcher("/subject_create_done.jsp").forward(req, res);
    }
}
