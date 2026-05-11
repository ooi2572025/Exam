package scoremanager.main;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import bean.School;
import bean.Student;
import dao.ClassNumDao;
import dao.StudentDao;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class TestListAction extends Action {

    @Override
    public void execute(HttpServletRequest request,
                        HttpServletResponse response)
            throws Exception {

        School school =
            (School) request.getSession().getAttribute("school");

        /* ===== 入学年度 ===== */
        StudentDao studentDao = new StudentDao();

        // 在学中・卒業生すべて取得
        List<Student> students = studentDao.filter(school, false);

        // 重複なし・昇順にする
        Set<Integer> entYearSet = new TreeSet<>();
        for (Student s : students) {
            entYearSet.add(s.getEntYear());
        }

        /* ===== クラス ===== */
        ClassNumDao classNumDao = new ClassNumDao();
        List<String> classNumSet = classNumDao.filter(school);

        /* ===== 科目 ===== */
        SubjectDao subjectDao = new SubjectDao();
        List<?> subjectSet = subjectDao.filterBySchool(school);

        /* ===== JSPへ渡す ===== */
        request.setAttribute("ent_year_set", entYearSet);
        request.setAttribute("class_num_set", classNumSet);
        request.setAttribute("subject_set", subjectSet);

        request.getRequestDispatcher("test_list.jsp")
               .forward(request, response);
    }
}