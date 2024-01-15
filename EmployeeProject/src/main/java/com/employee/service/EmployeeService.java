package com.employee.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.employee.dto.EmployeeDTO;
import com.employee.mapper.EmployeeMapper;

@Service
public class EmployeeService {
	private EmployeeMapper employeeMapper;

	public EmployeeService(EmployeeMapper employeeMapper) {
		this.employeeMapper = employeeMapper;
	}

	public List<EmployeeDTO> selectAllEmplyoee() {
		return employeeMapper.selectAllEmployee();
	}

	public void deleteEmployee(String eno) {
		employeeMapper.deleteEmployee(eno);
	}

	public EmployeeDTO selectEmployee(String eno) {
		return employeeMapper.selectEmployee(eno);
	}

	public void updateEmployee(EmployeeDTO dto) {
		employeeMapper.updateEmployee(dto);
	}

	public void insertEmployee(EmployeeDTO dto) {
		employeeMapper.insertEmployee(dto);
	}

	public List<EmployeeDTO> employeeLowSalaryList() {
		return employeeMapper.employeeLowSalaryList();
	}

	public List<EmployeeDTO> selectAllEmployee() {
		return employeeMapper.selectAllEmployee();
		
	}



}
