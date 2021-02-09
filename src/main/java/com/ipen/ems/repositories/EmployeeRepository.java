package com.ipen.ems.repositories;

import java.awt.print.Pageable;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.ipen.ems.entities.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
