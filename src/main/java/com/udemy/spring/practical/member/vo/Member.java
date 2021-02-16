package com.udemy.spring.practical.member.vo;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity // 데이터베이스 테이블에 대응되는 개념
public class Member {

    @Id // 테이블 Primary Key에 대응되는 개념
    @Column
    @Size(min = 4, max = 8, message = "아이디는 4 ~ 8자리 이내로 입력해 주세요.") // 입력 길이 제약사항 설정
    // @GeneratedValue(strategy = GenerationType.AUTO) // Oracle의 Sequence, MySQL의 auto_increment와 같은 개념
    private String id;
    @Column // 테이블 컬럼에 대응되는 개념
    @NotNull // Not Null 제약사항 설정
    //@Size(min = 2, max = 5, message = "이름은 2 ~ 5자리 이내로 입력해 주세요.") // 입력 길이 제약사항 설정
    @Pattern(regexp = "\\S{2,5}", message = "이름은 2 ~ 5자리 이내로 입력해 주세요.") // 정규 표현식을 이용한 제약사항 설정(java.util.regex)
    private String name;
    @Column
    @NotNull
    //@Size(min = 1, max = 3, message = "나이는 1 ~ 3자리 숫자로 입력해 주세요.") // 입력 길이 제약사항 설정
    @Pattern(regexp = "\\d{1,3}", message = "나이는 1 ~ 3자리 숫자로 입력해 주세요.")
    //private int age; // 하이버네이트의 Validator는 Integer형에 대한 검증을 지원하지 않음.
    private String age;
    @Column
    @NotNull
    @Size(min = 3, max = 50, message = "주소는 3 ~ 50자리 이내로 입력해 주세요.") // 입력 길이 제약사항 설정
    private String address;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
