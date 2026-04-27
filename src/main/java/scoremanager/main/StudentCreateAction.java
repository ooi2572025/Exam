package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import bean.Teacher;
import dao.ClassNumDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class StudentCreateAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        // 入学年度リスト（今年の10年前〜今年）
        LocalDate today = LocalDate.now();
        int year = today.getYear();
        List<Integer> entYearList = new ArrayList<>();
        for (int i = year - 10; i < year + 1; i++) {
            entYearList.add(i);
        }

        // クラスリスト
        ClassNumDao classNumDao = new ClassNumDao();
        List<String> classNumList = classNumDao.filter(teacher.getSchool());

        req.setAttribute("entYearList", entYearList);
        req.setAttribute("classNumList", classNumList);

        req.getRequestDispatcher("student_create.jsp").forward(req, res);
    }
}