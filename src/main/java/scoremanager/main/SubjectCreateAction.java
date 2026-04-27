package scoremanager.main;

import javax.security.auth.Subject;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class SubjectCreateAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        // 空の Subject を作成（フォーム初期表示用）
        Subject subject = new Subject();

        // リクエストにセット
        req.setAttribute("subject", subject);

        // 登録フォームへフォワード
        req.getRequestDispatcher("/subject_create.jsp").forward(req, res);
    }
}
