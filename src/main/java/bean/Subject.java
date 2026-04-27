package bean;

public class Subject {

    private String schoolCd;
    private String cd;        // テーブル物理名: CD
    private String name;      // テーブル物理名: NAME

    public String getSchoolCd() {
        return schoolCd;
    }
    public void setSchoolCd(String schoolCd) {
        this.schoolCd = schoolCd;
    }
    public String getCd() {
        return cd;
    }
    public void setCd(String cd) {
        this.cd = cd;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}