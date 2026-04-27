package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.security.auth.Subject;

import bean.School;

public class SubjectDao extends Dao {

    // 1件取得
    public Subject get(String id, School school) throws Exception {
        Subject subject = null;

        String sql = "SELECT id, name, school_id FROM subject WHERE id=? AND school_id=?";

        try (Connection con = getConnection();
             PreparedStatement st = con.prepareStatement(sql)) {

            st.setString(1, id);
            st.setString(2, school.getId());

            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                subject = new Subject();
                subject.setId(rs.getString("id"));
                subject.setName(rs.getString("name"));
                subject.setSchool(school);
            }
        }
        return subject;
    }

    // 学校ごとの科目一覧
    public List<Subject> filterBySchool(School school) throws Exception {
        List<Subject> list = new ArrayList<>();

        String sql = "SELECT id, name FROM subject WHERE school_id=? ORDER BY id";

        try (Connection con = getConnection();
             PreparedStatement st = con.prepareStatement(sql)) {

            st.setString(1, school.getId());
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Subject s = new Subject();
                s.setId(rs.getString("id"));
                s.setName(rs.getString("name"));
                s.setSchool(school);
                list.add(s);
            }
        }
        return list;
    }

    // ★ 新規登録（insert）
    public void insert(Subject subject) throws Exception {
        String sql = "INSERT INTO subject(id, name, school_id) VALUES(?, ?, ?)";

        try (Connection con = getConnection();
             PreparedStatement st = con.prepareStatement(sql)) {

            st.setString(1, subject.getId());
            st.setString(2, subject.getName());
            st.setString(3, subject.getSchool().getId());

            st.executeUpdate();
        }
    }

    // ★ 更新（update）
    public void update(Subject subject) throws Exception {
        String sql = "UPDATE subject SET name=? WHERE id=? AND school_id=?";

        try (Connection con = getConnection();
             PreparedStatement st = con.prepareStatement(sql)) {

            st.setString(1, subject.getName());
            st.setString(2, subject.getId());
            st.setString(3, subject.getSchool().getId());

            st.executeUpdate();
        }
    }

    // 削除
    public boolean delete(Subject subject) throws Exception {
        String sql = "DELETE FROM subject WHERE id=? AND school_id=?";

        try (Connection con = getConnection();
             PreparedStatement st = con.prepareStatement(sql)) {

            st.setString(1, subject.getId());
            st.setString(2, subject.getSchool().getId());

            return st.executeUpdate() == 1;
        }
    }
}
