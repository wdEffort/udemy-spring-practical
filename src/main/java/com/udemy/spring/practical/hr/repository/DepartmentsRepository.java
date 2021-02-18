package com.udemy.spring.practical.hr.repository;

import com.udemy.spring.practical.hr.vo.Department;

import java.util.List;

public interface DepartmentsRepository {

    List<Department> getDepartments();
}
