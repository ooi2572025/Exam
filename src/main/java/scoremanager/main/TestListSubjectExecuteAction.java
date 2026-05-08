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

        School school =
            (School) request.getSession().getAttribute("school");

        String entYearStr = request.getParameter("entYear");
        String classNum   = request.getParameter("classNum");
        String subjectCd  = request.getParameter("subjectCd");

        if (entYearStr != null && !entYearStr.isEmpty()
         && classNum   != null && !classNum.isEmpty()
         && subjectCd  != null && !subjectCd.isEmpty()) {

            int entYear = Integer.parseInt(entYearStr);

            TestListSubjectDao dao = new TestListSubjectDao();
            List<TestListSubject> list =
                dao.filter(school, entYear, classNum, subjectCd);

            request.setAttribute("subjectList", list);
            request.setAttribute("searchType", "subject");
        }

        request.getRequestDispatcher("test_list.jsp")
               .forward(request, response);
    }
}
