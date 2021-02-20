package com.udemy.spring.practical.newmember.service;

import com.udemy.spring.practical.newmember.vo.NewMember;

import java.util.List;

public interface NewMemberSerivce {

    List<NewMember> getAllMembers();

    NewMember getMember(String id);

    void insertMember(NewMember member);

    void updateMember(NewMember member);

    void deleteMember(String id);
}
