package com.udemy.spring.practical.newmember.service.impl;

import com.udemy.spring.practical.newmember.repository.NewMemberRepository;
import com.udemy.spring.practical.newmember.service.NewMemberSerivce;
import com.udemy.spring.practical.newmember.vo.NewMember;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewMemberServiceImpl implements NewMemberSerivce {

    @Autowired
    private NewMemberRepository newMemberRepository;

    @Override
    public List<NewMember> getAllMembers() {
        return newMemberRepository.getAllMembers();
    }

    @Override
    public NewMember getMember(String id) {
        return newMemberRepository.getMember(id);
    }

    @Override
    public void insertMember(NewMember member) {
        newMemberRepository.insertMember(member);
    }

    @Override
    public void updateMember(NewMember member) {
        newMemberRepository.updateMember(member);
    }

    @Override
    public void deleteMember(String id) {
        newMemberRepository.deleteMember(id);
    }
}
