package com.example.crmtaskmeneger.controller;

import com.example.crmtaskmeneger.dto.request.EmployeeDtoRequest;
import com.example.crmtaskmeneger.dto.response.EmployeeDtoResponse;
import com.example.crmtaskmeneger.dto.response.TaskDtoResponse;
import com.example.crmtaskmeneger.utils.DataGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.proxy.Mixin;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;


@Controller
@Scope("request")
public class EmployeeController {

    @GetMapping(value = "/new_task")
    public ModelAndView newTask(ModelAndView model, @ModelAttribute("user") EmployeeDtoResponse request){
        System.out.println("=======================================================================================");
        System.out.println("Зашли в контролер созданной задачи GET:  com.example.crmtaskmeneger.controller.EmployeeController.newTask()");
        System.out.println("Пришол пользователь Активный: " + request );
        model.addObject("user", request);
      model.addObject("task", new TaskDtoResponse());
        model.setViewName("fourth_floor/new_task.html");
        System.out.println("=======================================================================================");
        return model;
    }
    @PostMapping(value = "/new_task")
    public ModelAndView newTaskCreate(
            ModelAndView model,
            @ModelAttribute("user") EmployeeDtoResponse employeeDtoResponse,
            @ModelAttribute("task") TaskDtoResponse taskDtoResponse
            ){
        System.out.println("=======================================================================================");
        System.out.println("Зашли в контролер созданной задачи POST:  com.example.crmtaskmeneger.controller.EmployeeController.newTaskCreate()");
        System.out.println("Пришол пользователь Активный: " + employeeDtoResponse );
        System.out.println("Пришол задача : " + taskDtoResponse );
        /*
         TODO Добавить сервис для сохранения и обработки в сервис и базу данных

         Подумать над тем что делать с пользователем
         */
        model.addObject("user", employeeDtoResponse);
        if(employeeDtoResponse.getRole().equalsIgnoreCase("director")){
            model.setViewName("thirt_floor/area_director.html");
        }else {
            model.setViewName("thirt_floor/area_employee.html");
        }
        System.out.println("=======================================================================================");
        return model;
    }

    @GetMapping("/all_free_task")
    public ModelAndView getAllFreeTask(ModelAndView model, @ModelAttribute(name = "user") EmployeeDtoResponse employeeDtoResponse){

        List<TaskDtoResponse> taskList = DataGenerator.generatorListToTaskResponse();

        /*
        TODO тут должна быть бизнес логика фильтровки выдачи свободных заданий
        для директора все свободные задачи
        для работника фильтрация по его специальности или доступу (По роли и должны быть свободными)
         */
        model.addObject("task_list", taskList);
        model.addObject("user", employeeDtoResponse);
        model.setViewName("fourth_floor/all_tasks.html");
        return model;
    }
}
