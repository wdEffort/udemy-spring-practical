package com.udemy.spring.practical.newmember.repository;

import com.udemy.spring.practical.newmember.vo.NewMember;

import java.util.List;

/**
 * DAO와 같은 역할
 */
public interface NewMemberRepository {

    List<NewMember> getAllMembers();

    NewMember getMember(String id);

    void insertMember(NewMember member);

    void updateMember(NewMember member);

    void deleteMember(String id);
}
