package com.jiohh.poc.multipledb.repository.db1;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jiohh.poc.multipledb.model.db1.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{
}
