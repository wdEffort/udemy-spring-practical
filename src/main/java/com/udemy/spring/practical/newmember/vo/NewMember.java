package com.udemy.spring.practical.newmember.vo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * mbgmember.vo.Member 클래스를 참조하여 새롭게 생성한 Member 클래스
 * + javax.persistence을 사용하여 Validaiton 적용
 */
@Entity
public class NewMember {

    @Id
    @Size(min = 6, max = 15, message = "아이디는 6 ~ 15자리 이내로 입력해 주세요.")
    private String id;
    @Size(min = 8, max = 16, message = "비밀번호는 8 ~ 16자리 이내로 입력해 주세요.")
    private String pwd;
    @Size(min = 2, max = 5, message = "이름은 2 ~ 5자리 이내로 입력해 주세요.")
    private String name;
    @Size(min = 12, max = 13, message = "연락처는 12 ~ 13자리 이내로 입력해 주세요.")
    private String tel;
    @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "이메일은 형식에 맞게 입력해 주세요.")
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
