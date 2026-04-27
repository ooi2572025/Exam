package scoremanager.main;

import javax.security.auth.Subject;

import bean.School;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class SubjectCreateExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        // フォームから値を取得
        String id = req.getParameter("id");
        String name = req.getParameter("name");

        // ログイン中の先生の school を取得（あなたのプロジェクト仕様）
        School school = (School) req.getSession().getAttribute("school");

        // Subject を作成
        Subject subject = new Subject();
        subject.setId(id);
        subject.setName(name);
        subject.setSchool(school);

        // DAO を使って登録
        SubjectDao dao = new SubjectDao();
        dao.insert(subject);

        // 完了画面 or 一覧へ
        req.getRequestDispatcher("/subject_create_done.jsp").forward(req, res);
    }
}
