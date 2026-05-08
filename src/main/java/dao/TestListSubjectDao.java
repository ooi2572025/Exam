package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.TestListSubject;

public class TestListSubjectDao extends Dao {

    public List<TestListSubject> filter(School school, int entYear, String classNum, String subjectCd, int no) throws Exception {

        List<TestListSubject> list = new ArrayList<>();

        String sql = "SELECT s.student_no, s.student_name, s.ent_year, s.class_num, t.point " +
                     "FROM student s " +
                     "LEFT JOIN test t " +
                     "ON s.student_no = t.student_no " +
                     "AND t.subject_cd = ? " +
                     "AND t.school_cd = ? " +
                     "AND t.no = ? " +
                     "WHERE s.school_cd = ? " +
                     "AND s.ent_year = ? " +
                     "AND s.class_num = ? " +
                     "ORDER BY s.student_no ASC";

        try (Connection con = getConnection();
             PreparedStatement st = con.prepareStatement(sql)) {

            st.setString(1, subjectCd);
            st.setString(2, school.getSchoolCd());
            st.setInt(3, no);
            st.setString(4, school.getSchoolCd());
            st.setInt(5, entYear);
            st.setString(6, classNum);

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                TestListSubject t = new TestListSubject();
                t.setStudentNo(rs.getString("student_no"));
                t.setStudentName(rs.getString("student_name"));
                t.setEntYear(rs.getInt("ent_year"));
                t.setClassNum(rs.getString("class_num"));
                int point = rs.getInt("point");
                if (!rs.wasNull()) {
                    t.setPoints(point);
                }
                list.add(t);
            }
        }
        return list;
    }
}