package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.TestListStudent;

public class TestListStudentDao extends Dao {

    public List<TestListStudent> filter(School school, String studentNo) throws Exception {

        List<TestListStudent> list = new ArrayList<>();

        String sql = "SELECT sub.cd AS subject_cd, sub.name AS subject_name, t.no, t.point " +
                     "FROM subject sub " +
                     "LEFT JOIN test t " +
                     "ON sub.cd = t.subject_cd " +
                     "AND t.student_no = ? " +
                     "AND t.school_cd = ? " +
                     "WHERE sub.school_cd = ? " +
                     "ORDER BY sub.cd ASC, t.no ASC";

        try (Connection con = getConnection();
             PreparedStatement st = con.prepareStatement(sql)) {

            st.setString(1, studentNo);
            st.setString(2, school.getSchoolCd());
            st.setString(3, school.getSchoolCd());

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                TestListStudent t = new TestListStudent();
                t.setSubjectCd(rs.getString("subject_cd"));
                t.setSubjectName(rs.getString("subject_name"));

                int no = rs.getInt("no");
                if (!rs.wasNull()) {
                    t.setNo(no);
                }
                int point = rs.getInt("point");
                if (!rs.wasNull()) {
                    t.setPoint(point);
                }

                list.add(t);
            }
        }
        return list;
    }
}