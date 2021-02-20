package com.udemy.spring.practical.newmember.vo;

/**
 * mbgmember.vo.Member 클래스를 참조하여 새롭게 생성한 Member 클래스
 */
public class NewMember {

    private String id;
    private String pwd;
    private String name;
    private String tel;
    private String email;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
