package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.TestListSubject;

public class TestListSubjectDao extends Dao {

    // 成績登録用（回数指定あり）
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

    // 成績参照用（1回・2回を横並び）
    public List<TestListSubject> filter(School school, int entYear, String classNum, String subjectCd) throws Exception {

        List<TestListSubject> list = new ArrayList<>();

        String sql = "SELECT s.student_no, s.student_name, s.ent_year, s.class_num, " +
                     "t1.point AS point1, t2.point AS point2 " +
                     "FROM student s " +
                     "LEFT JOIN test t1 " +
                     "ON s.student_no = t1.student_no " +
                     "AND t1.subject_cd = ? " +
                     "AND t1.school_cd = ? " +
                     "AND t1.no = 1 " +
                     "LEFT JOIN test t2 " +
                     "ON s.student_no = t2.student_no " +
                     "AND t2.subject_cd = ? " +
                     "AND t2.school_cd = ? " +
                     "AND t2.no = 2 " +
                     "WHERE s.school_cd = ? " +
                     "AND s.ent_year = ? " +
                     "AND s.class_num = ? " +
                     "ORDER BY s.student_no ASC";

        try (Connection con = getConnection();
             PreparedStatement st = con.prepareStatement(sql)) {

            st.setString(1, subjectCd);
            st.setString(2, school.getSchoolCd());
            st.setString(3, subjectCd);
            st.setString(4, school.getSchoolCd());
            st.setString(5, school.getSchoolCd());
            st.setInt(6, entYear);
            st.setString(7, classNum);

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                TestListSubject t = new TestListSubject();
                t.setStudentNo(rs.getString("student_no"));
                t.setStudentName(rs.getString("student_name"));
                t.setEntYear(rs.getInt("ent_year"));
                t.setClassNum(rs.getString("class_num"));

                int point1 = rs.getInt("point1");
                if (!rs.wasNull()) {
                    t.setPoints(point1);
                }

                int point2 = rs.getInt("point2");
                if (!rs.wasNull()) {
                    t.setPoints2(point2);
                }

                list.add(t);
            }
        }
        return list;
    }
}