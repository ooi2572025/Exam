package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Test;

public class TestDao extends Dao {


    public List<Test> filter(School school, int entYear, String classNum, String subjectCd, int no) throws Exception {

        List<Test> list = new ArrayList<>();

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
                Test t = new Test();
                t.setStudentNo(rs.getString("student_no"));
                t.setClassNum(rs.getString("class_num"));
                int point = rs.getInt("point");
                if (!rs.wasNull()) {
                    t.setPoint(point);
                }
                list.add(t);
            }
        }
        return list;
    }

    public void save(School school, String studentNo, String subjectCd, int no, Integer point, String classNum) throws Exception {

        String checkSql = "SELECT COUNT(*) FROM test WHERE student_no=? AND subject_cd=? AND school_cd=? AND no=?";

        try (Connection con = getConnection();
             PreparedStatement st = con.prepareStatement(checkSql)) {

            st.setString(1, studentNo);
            st.setString(2, subjectCd);
            st.setString(3, school.getSchoolCd());
            st.setInt(4, no);

            ResultSet rs = st.executeQuery();
            rs.next();
            int count = rs.getInt(1);

            String sql;
            if (count > 0) {
                sql = "UPDATE test SET point=? WHERE student_no=? AND subject_cd=? AND school_cd=? AND no=?";
            } else {
                sql = "INSERT INTO test(point, student_no, subject_cd, school_cd, no, class_num) VALUES(?,?,?,?,?,?)";
            }

            try (PreparedStatement st2 = con.prepareStatement(sql)) {
                st2.setObject(1, point);
                st2.setString(2, studentNo);
                st2.setString(3, subjectCd);
                st2.setString(4, school.getSchoolCd());
                st2.setInt(5, no);
                if (count == 0) {
                    st2.setString(6, classNum);
                }
                st2.executeUpdate();
            }
        }
    }
}