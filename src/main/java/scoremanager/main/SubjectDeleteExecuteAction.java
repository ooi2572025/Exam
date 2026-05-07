package scoremanager.main;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class SubjectDeleteExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        // パラメータ取得
        String cd   = req.getParameter("subject_cd");
        String name = req.getParameter("subject_name");

        // 科目を削除
        SubjectDao subjectDao = new SubjectDao();
        Subject subject = new Subject();
        subject.setCd(cd);
        subject.setName(name);
        subject.setSchoolCd(teacher.getSchool().getSchoolCd());

        subjectDao.delete(subject);

        // 削除完了画面へ
        req.getRequestDispatcher("subject_delete_done.jsp").forward(req, res);
    }
}