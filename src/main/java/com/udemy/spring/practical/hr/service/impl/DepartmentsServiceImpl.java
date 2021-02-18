package com.udemy.spring.practical.hr.service.impl;

import com.udemy.spring.practical.hr.repository.DepartmentsRepository;
import com.udemy.spring.practical.hr.service.DepartmentsService;
import com.udemy.spring.practical.hr.vo.Department;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentsServiceImpl implements DepartmentsService {

    @Autowired
    private SqlSession sqlSession;

    @Override
    public List<Department> getDepartments() {
        return this.sqlSession.getMapper(DepartmentsRepository.class).getDepartments();
    }
}
