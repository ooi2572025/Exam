package scoremanager.main;

import java.util.List;

import bean.School;
import bean.TestListSubject;
import dao.TestListSubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class TestListSubjectExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest request,
                        HttpServletResponse response)
            throws Exception {

        /*
         * セッションから学校情報を取得
         * （ログイン時にセットされている前提）
         */
        School school = (School) request.getSession().getAttribute("school");

        /*
         * 検索条件取得
         */
        String entYearStr = request.getParameter("entYear");
        String classNum   = request.getParameter("classNum");
        String subjectCd  = request.getParameter("subjectCd");

        /*
         * 科目検索条件がすべて入力されている場合のみ検索
         */
        if (entYearStr != null && !entYearStr.isEmpty()
         && classNum   != null && !classNum.isEmpty()
         && subjectCd  != null && !subjectCd.isEmpty()) {

            int entYear = Integer.parseInt(entYearStr);

            // DAO呼び出し
            TestListSubjectDao dao = new TestListSubjectDao();
            List<TestListSubject> list =
                    dao.filter(school, entYear, classNum, subjectCd);

            // JSPへ渡す
            request.setAttribute("subjectList", list);
            request.setAttribute("searchType", "subject");
        }

        /*
         * 成績参照画面へフォワード
         */
        request.getRequestDispatcher("test_list.jsp")
               .forward(request, response);
    }
}
