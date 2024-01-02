package com.example.crmtaskmeneger.controller;

import com.example.crmtaskmeneger.dto.request.EmployeeDtoRequest;
import com.example.crmtaskmeneger.dto.response.EmployeeDtoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;


@Controller
@Scope("request")
public class EmployeeController {
    private EmployeeDtoResponse response;
    private EmployeeDtoRequest request;

    @Autowired
    public EmployeeController(EmployeeDtoResponse response, EmployeeDtoRequest request) {
        this.response = response;
        this.request = request;
    }

    @GetMapping(value = "/new_task")
    public ModelAndView newTask(ModelAndView model){

      model.addObject("message", "Зашли в контроллер");
        Map map = model.getModelMap();



        model.setViewName("fourth_floor/new_task.html");
        return model;
    }
}
