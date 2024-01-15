package com.employee.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.employee.dto.EmployeeDTO;

@Mapper
public interface EmployeeMapper {
	
	List<EmployeeDTO> selectAllEmployee();
	void deleteEmployee(String eno);
	EmployeeDTO selectEmployee(String eno);
	void updateEmployee(EmployeeDTO dto);
	void insertEmployee(EmployeeDTO dto);
	List<EmployeeDTO> employeeLowSalaryList();
	
}
