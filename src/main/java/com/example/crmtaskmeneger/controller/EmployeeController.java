package com.example.crmtaskmeneger.controller;

import com.example.crmtaskmeneger.dto.request.EmployeeDtoRequest;
import com.example.crmtaskmeneger.dto.response.EmployeeDtoResponse;
import com.example.crmtaskmeneger.dto.response.TaskDtoResponse;
import com.example.crmtaskmeneger.entities.Role;
import com.example.crmtaskmeneger.model.SelectingAnActionWhenCreatingATask;
import com.example.crmtaskmeneger.utils.DataGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.proxy.Mixin;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;
import java.util.Objects;


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
            @RequestParam(name = "action", required = false) SelectingAnActionWhenCreatingATask selectEmployee,
            @ModelAttribute("task") TaskDtoResponse taskDtoResponse
            ){
        System.out.println("=======================================================================================");
        System.out.println("Зашли в контролер созданной задачи POST:  com.example.crmtaskmeneger.controller.EmployeeController.newTaskCreate()");
        System.out.println("Пришел пользователь Активный: " + employeeDtoResponse );
        System.out.println("Пришел задача : " + taskDtoResponse );
        if(Objects.nonNull(selectEmployee)) {
            System.out.println("Пришел задача : " + selectEmployee.getDescription());
        }
        /*
         TODO Добавить сервис для сохранения и обработки в сервис и базу данных
         Подумать над тем что делать с пользователем
         */
        model.addObject("user", employeeDtoResponse);
        if(employeeDtoResponse.getRole().equals(Role.DIRECTOR) && selectEmployee.equals(SelectingAnActionWhenCreatingATask.SELECT_FREE_TASK)){
            model.setViewName("thirt_floor/area_director.html");
        }if(employeeDtoResponse.getRole().equals(Role.DIRECTOR) && selectEmployee.equals(SelectingAnActionWhenCreatingATask.SELECT_EMPLOYEE)){
            /*
            TODO Сюда добавить бизнес логику котора будет отдавать список свободных сотрудников
                Если директор решит выбрать сотрудника при создании задачи то для
                отображения на странице свободных сотрудников ему нужно их отобразить
             */
            List<EmployeeDtoResponse> freeEmployees =  DataGenerator.generatorListEmployees();// Имитация списака сотрудников Заменить на Бизнес логику
            model.addObject("employee_list", freeEmployees);
            model.addObject("task", taskDtoResponse);
            model.setViewName("thirt_floor/all_free_employee.html");
        }else {
            /*
            TODO добавить бизнес логику для обычного сотрудника и сохранения в базе данных все изменения связанные с данным сотрудником
                1) изменения статуса активности сотрудника на свободного или занятого
             */
            model.setViewName("thirt_floor/area_employee.html");
        }
        System.out.println("=======================================================================================");
        return model;
    }

    @GetMapping("/all_free_task")
    public ModelAndView getAllFreeTask(
            ModelAndView model,
            @ModelAttribute(name = "user") EmployeeDtoResponse employeeDtoResponse
    ){

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
