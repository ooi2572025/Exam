package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Subject;

public class SubjectDao extends Dao {

    // 1件取得
    public Subject get(String cd, School school) throws Exception {
        Subject subject = null;

        String sql = "SELECT subject_cd, subject_name, school_cd FROM subject WHERE subject_cd=? AND school_cd=?";

        try (Connection con = getConnection();
             PreparedStatement st = con.prepareStatement(sql)) {

            st.setString(1, cd);
            st.setString(2, school.getSchoolCd());

            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                subject = new Subject();
                subject.setCd(rs.getString("subject_cd"));
                subject.setName(rs.getString("subject_name"));
                subject.setSchoolCd(rs.getString("school_cd"));
            }
        }
        return subject;
    }

    // 学校ごとの科目一覧
    public List<Subject> filterBySchool(School school) throws Exception {
        List<Subject> list = new ArrayList<>();

        String sql = "SELECT subject_cd, subject_name FROM subject WHERE school_cd=? ORDER BY subject_cd";

        try (Connection con = getConnection();
             PreparedStatement st = con.prepareStatement(sql)) {

            st.setString(1, school.getSchoolCd());
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Subject s = new Subject();
                s.setCd(rs.getString("subject_cd"));
                s.setName(rs.getString("subject_name"));
                s.setSchoolCd(school.getSchoolCd());
                list.add(s);
            }
        }
        return list;
    }

    // 新規登録（insert）
    public void insert(Subject subject) throws Exception {
        String sql = "INSERT INTO subject(subject_cd, subject_name, school_cd) VALUES(?, ?, ?)";

        try (Connection con = getConnection();
             PreparedStatement st = con.prepareStatement(sql)) {

            st.setString(1, subject.getCd());
            st.setString(2, subject.getName());
            st.setString(3, subject.getSchoolCd());

            st.executeUpdate();
        }
    }

    // 更新（update）
    public void update(Subject subject) throws Exception {
        String sql = "UPDATE subject SET subject_name=? WHERE subject_cd=? AND school_cd=?";

        try (Connection con = getConnection();
             PreparedStatement st = con.prepareStatement(sql)) {

            st.setString(1, subject.getName());
            st.setString(2, subject.getCd());
            st.setString(3, subject.getSchoolCd());

            st.executeUpdate();
        }
    }

    // 削除（delete）
    public boolean delete(Subject subject) throws Exception {
        String sql = "DELETE FROM subject WHERE subject_cd=? AND school_cd=?";

        try (Connection con = getConnection();
             PreparedStatement st = con.prepareStatement(sql)) {

            st.setString(1, subject.getCd());
            st.setString(2, subject.getSchoolCd());

            return st.executeUpdate() == 1;
        }
    }
}