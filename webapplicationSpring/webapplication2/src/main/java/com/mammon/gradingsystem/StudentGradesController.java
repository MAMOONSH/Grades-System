package com.mammon.gradingsystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@SessionAttributes("student")
public class StudentGradesController {
    @Autowired
    GradingSystemDAO database;

    @RequestMapping(value ="/studentGrades",method = RequestMethod.GET)
    public String returnToLogin(@ModelAttribute("student") Student student,
                                ModelMap model){
        model.put("grades",database.displayGrades(student));
        return "studentGrades";
    }
}
