package com.employee.controller;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.employee.dto.EmployeeDTO;
import com.employee.dto.PositionDTO;
import com.employee.service.EmployeeService;
import com.employee.service.PositionService;

import batch.SelectEmployeeLowSalary;
import batch.TestCronTrigger;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;



public class MainController {

	private EmployeeService employeeService;
	private PositionService positionService;
	
	public MainController(EmployeeService employeeService, PositionService positionService) {
		this.employeeService = employeeService;
		this.positionService = positionService;
		TestCronTrigger trigger = new TestCronTrigger("0 0 0 1 1/1 ? *", SelectEmployeeLowSalary.class);
		trigger.triggerJob();
	}
	
	
	@RequestMapping("/employee/low-salary-list")
	public ResponseEntity<String> EmployeeLowSalaryList(){
		List<EmployeeDTO> list = employeeService.employeeLowSalaryList();
		return new ResponseEntity(list, HttpStatus.OK);
	}
	
	@RequestMapping("/")
	public ModelAndView index(ModelAndView view) {
		view.setViewName("index");
		return view;
	}
	
	@RequestMapping("/login")
	public String login(String id, String pw, HttpSession session, HttpServletResponse response) {
		if(id.equals("admin") && pw.equals("123456")) {
			session.setAttribute("login", true);
			return "redirect:/main";
		}
		return "redirect:/";
		
		EmployeeDTO employee = employeeService.login(id, pw);
		response.setContentType("text/html;charset=utf-8");
		PrintWriter pw = response.getWriter();
		if(employee == null) {
			pw.write("<script>alert('로그인 실패 하였습니다. \\n입력하신 정보를 확인하세요.');location.href='/';</script>");
			return null;
		}
		
		session.setAttribute("employee", employee);
		return "redirect:/main";
	}

	
	@RequestMapping("/main")
	public ModelAndView main(ModelAndView view) {

		List<EmployeeDTO> employeeList = employeeService.selectAllEmployee();
		List<PositionDTO> positionList = positionService.selectAllPosition();
		
		view.addObject("employeeList", employeeList);
		view.addObject("positionList", positionList);
		view.setViewName("main");
		
		return view;
	}
	
	@RequestMapping("/employee/delete")
	public String deleteEmployee(@PathVariable("eno") String eno) {
		employeeService.deleteEmployee(eno);
		return "redirect:/main";
	}
	
	@GetMapping("/employee/update/view")
	public ModelAndView updateStudentView(@PathVariable("eno") String eno, ModelAndView view) {
		EmployeeDTO dto = employeeService.selectEmployee(eno);
		List<PositionDTO> positionList = positionService.selectAllPosition();
		view.setViewName("employee_update");
		view.addObject("positionList", positionList);

		view.addObject("dto", dto);
		return view;
	}
	
	@PostMapping("/employee/update/")
	public String updateEmployee(EmployeeDTO dto) {
		employeeService.updateEmployee(dto);
		return "redirect:/main";
	}
	
	@GetMapping("/emplyoee/insert/view")
	public ModelAndView insertStudentView(ModelAndView view) {
		view.setViewName("employee_register");
		List<PositionDTO> positionList = positionService.selectAllPosition();
		view.addObject("positionList", positionList);
		return view;
	}
	
	@PostMapping("/employee/insert")
	public String insertEmployee(EmployeeDTO dto) {
		employeeService.insertEmployee(dto);
		return "redirect:/main";
	}
	
	@PostMapping("/position/insert")
	@ResponseBody
	public ResponseEntity insertPosition(PositionDTO dto) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		int result = positionService.insertPosition(dto);
		map.put("result", result);
		List<PositionDTO> positionList = positionService.selectAllPosition();
		map.put("list", positionList);
		if(result == 0) 
			map.put("msg", "데이터 등록에 실패하였습니다");
		
		return new ResponseEntity(map,HttpStatus.OK);
	}

	
	
	
	
	
	
	
}
	

