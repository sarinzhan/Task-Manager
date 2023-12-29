package com.example.crmtaskmeneger.controller;

import com.example.crmtaskmeneger.dto.TaskDto;
import com.example.crmtaskmeneger.dto.response.EmployeeDtoResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Arrays;
import java.util.Map;


@Controller
public class EmployeeController {


    @GetMapping(value = "/new_task")
    public ModelAndView newTask(ModelAndView model){
//        model.addObject("task",new TaskDto());
//        model.addObject("user",response);
//        System.out.println(response);
      model.addObject("message", "Зашли в контроллер");
        Map map = model.getModelMap();
        System.out.println(Arrays.toString(map.keySet().toArray()));


        model.setViewName("fourth_floor/new_task.html");
        return model;
    }
}
