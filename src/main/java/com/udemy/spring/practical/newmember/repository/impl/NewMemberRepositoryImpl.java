package com.udemy.spring.practical.newmember.repository.impl;

import com.udemy.spring.practical.newmember.repository.NewMemberRepository;
import com.udemy.spring.practical.newmember.vo.NewMember;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * NewMemberRepository 인터페이스 구현 클래스
 * 굳이 NewMemberRepository 인터페이스를 구현할 필요 없이 SqlSession 객체의 getMapper() 메소드만을 사용해도 되지만
 * 아래 코드처럼 SqlSession 객체가 가지고 있는 selectList(), selectOne(), insert(), update(), delete() 메소드를 사용한
 * 인터페이스 구현 클래스를 작성하여 사용할 수도 있다.
 */
@Repository
public class NewMemberRepositoryImpl implements NewMemberRepository {

    @Autowired
    private SqlSession sqlSession;

    @Override
    public List<NewMember> getAllMembers() {
        //return (List<Member>) sqlSession.getMapper(NewMemberRepository.class).getAllMembers();

        //위 방법과 다르게 SqlSession 객체가 가지고 있는 selectList() 메소드를 이용하는 방법도 있다.
        //selectList(String SQL Mapper의 id 값)
        return sqlSession.selectList("getAllMembers");
    }

    @Override
    public NewMember getMember(String id) {
        //return (Member) sqlSession.getMapper(NewMemberRepository.class).getMember(id);

        //위 방법과 다르게 SqlSession 객체가 가지고 있는 selectOne() 메소드를 이용하는 방법도 있다.
        //selectOne(String SQL Mapper의 id 값, Object 파라미터 객체)
        return sqlSession.selectOne("getMember", id);
    }

    @Override
    public void insertMember(NewMember member) {
        //sqlSession.getMapper(NewMemberRepository.class).insertMember(member);

        //위 방법과 다르게 SqlSession 객체가 가지고 있는 insert() 메소드를 이용하는 방법도 있다.
        //insert(String SQL Mapper의 id 값, Object 파라미터 객체)
        sqlSession.insert("insertMember", member);
    }

    @Override
    public void updateMember(NewMember member) {
        //sqlSession.getMapper(NewMemberRepository.class).updateMember(member);

        //위 방법과 다르게 SqlSession 객체가 가지고 있는 update() 메소드를 이용하는 방법도 있다.
        //update(String SQL Mapper의 id 값, Object 파라미터 객체)
        sqlSession.update("updateMember", member);
    }

    @Override
    public void deleteMember(String id) {
        //sqlSession.getMapper(NewMemberRepository.class).deleteMember(id);

        //위 방법과 다르게 SqlSession 객체가 가지고 있는 delete() 메소드를 이용하는 방법도 있다.
        //delete(String SQL Mapper의 id 값, Object 파라미터 객체)
        sqlSession.delete("deleteMember", id);
    }
}
