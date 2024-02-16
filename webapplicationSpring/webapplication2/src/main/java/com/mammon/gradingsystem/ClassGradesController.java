package com.mammon.gradingsystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@SessionAttributes("student")
public class ClassGradesController {
    @Autowired
    GradingSystemDAO database;

    @Autowired
    ClassStatistics classStatistics;

    @RequestMapping(value="/courseGrades",method = RequestMethod.GET)
    public String returnToLogin(@ModelAttribute("student") Student student, @RequestParam String courseID ,ModelMap model) {
        List<ClassGrade> classGrades=database.courseGrades(student,courseID+"");
        if(classGrades.isEmpty()){
            model.put("error","invalid class id or access denied");
            return "error";
        }
        classStatistics.analyze(classGrades);
        model.put("classGrades",classGrades);
        model.put("statistics",classStatistics);
        return "courseGrades";
    }
}
