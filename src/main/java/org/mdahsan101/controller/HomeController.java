package org.mdahsan101.controller;

import org.mdahsan101.Entity.Employee;
import org.mdahsan101.Exceptions.EmployeeAlredyExists;
import org.mdahsan101.Exceptions.InvalidLogin;
import org.mdahsan101.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.NoSuchElementException;
import java.util.Optional;

@Controller
@RequestMapping("/emp")
public class HomeController
{
    @Autowired
    private EmployeeRepository employeeRepository;

    public HomeController(EmployeeRepository employeeRepository)
    {
        this.employeeRepository= employeeRepository;
    }

    @GetMapping("")
    public ModelAndView goToIndex()
    {
        ModelAndView mv= new ModelAndView();
        mv.setViewName("index");
        return mv;
    }

    @GetMapping("/gotosignup")
    public ModelAndView GoTOSignUp()
    {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("signup");
        return mv;
    }

    @GetMapping("/getemployeeslist")
    public ModelAndView getEmplyeeList()
    {
        ModelAndView mv = new ModelAndView();
        mv.addObject("allemps", employeeRepository.findAll());
        mv.setViewName("allemployees_details");
        return mv;
    }

    @PostMapping(value = "/showemployee" )
    public ModelAndView showEmployee(@ModelAttribute Employee empLog)
    {
        Long empId= employeeRepository.getEmployeeId(empLog.UName,empLog.Password);
        System.out.println(empId);
        if(empId==null)
            throw new InvalidLogin();

        ModelAndView mv = new ModelAndView();
        mv.addObject("myemp",employeeRepository.findById(empId));
        mv.setViewName("employee_details");
        return mv;
    }

    @PostMapping("/registeremployee")
    public ModelAndView registerEmployee(@ModelAttribute Employee new_emp)
    {
        long count= employeeRepository.getUnameCount(new_emp.UName);
        if(count>0)
            throw new EmployeeAlredyExists();

        employeeRepository.save(new_emp);
        ModelAndView mv= new ModelAndView();
        mv.addObject("allemps",employeeRepository.findAll());
        mv.setViewName("allemployees_details");
        return mv;
    }

//    @GetMapping("deleteemployee/{uname}/{pwd}")
//    public ModelAndView deleteEmployee(@PathVariable("uname") String uname, @PathVariable("pwd") String password)

    @GetMapping("/deleteemployee/{eid}")
    public ModelAndView deleteEmployee(@PathVariable("eid") long empId)
    {
//      long eid= employeeRepository.getEmployeeId(uname,password);
        Optional<Employee> emp= employeeRepository.findById(empId);

        if(!emp.isPresent())
            throw new NoSuchElementException();

        employeeRepository.deleteById(empId);
        ModelAndView mv = new ModelAndView();

        mv.addObject("allemps", employeeRepository.findAll());
        mv.setViewName("allemployees_details");
        return mv;
    }

    @GetMapping("/updateemployee/{eid}")
    public ModelAndView GoTOUpdate(@PathVariable("eid") long empId)
    {
        Optional<Employee> myemp = employeeRepository.findById(empId);
        if(!myemp.isPresent())
            throw new NoSuchElementException();

        ModelAndView mv = new ModelAndView();
        mv.setViewName("update");
        mv.addObject("emp",myemp);
        return mv;
    }

    @PostMapping("/saveemployeedata/{eid}")
    public ModelAndView saveEmployeeData(@ModelAttribute Employee myemp,@PathVariable("eid") long empId)
    {
        ModelAndView mv = new ModelAndView();
        employeeRepository.save(myemp);
        mv.addObject("allemps", employeeRepository.findAll());
        mv.setViewName("allemployees_details");
        return mv;
    }
}

