package com.mammon.gradingsystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@SessionAttributes("student")
public class WelcomeStudentController {

    @Autowired
    GradingSystemDAO database;
    @Autowired
    Student student;

    @RequestMapping(value ="/",method = RequestMethod.GET)
    public String showLoginPage(ModelMap model){
        setStudentData();
        model.put("student",student);
        model.put("grades",database.displayGrades(student));
        return "studentGrades";
    }
    private void setStudentData(){
        student.setId(getLoggedInUserName());
        student.setPassword(getLoggedInPassword());
        String studentName=database.getUserName(student);
        student.setName(studentName);
    }


    private String getLoggedInUserName() {
        Object principal = SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();

        if (principal instanceof UserDetails)
            return ((UserDetails) principal).getUsername();

        return principal.toString();
    }

    private String getLoggedInPassword() {
        Object principal = SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();

        if (principal instanceof UserDetails)
            return ((UserDetails) principal).getPassword();

        return principal.toString();
    }
}
