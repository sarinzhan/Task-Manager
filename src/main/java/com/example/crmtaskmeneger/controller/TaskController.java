package com.example.crmtaskmeneger.controller;

import com.example.crmtaskmeneger.dto.response.EmployeeDtoResponse;
import com.example.crmtaskmeneger.dto.response.TaskDtoResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class TaskController {

    @GetMapping("/task_selection")
    public ModelAndView setEmployeeTask(
            ModelAndView model,
            @ModelAttribute(name = "user")EmployeeDtoResponse employeeDtoResponse,
            @ModelAttribute(name = "task")TaskDtoResponse taskDtoResponse
            ){

        System.out.println("========== Зашли в контроллер просмотра выбранной задачи ========================");
        System.out.println("Адрес: com.example.crmtaskmeneger.controller.TaskController.setEmployeeTask()==POST ");
        System.out.println("Пришла модель пользователя: " + employeeDtoResponse);
        System.out.println("Пришла модель задачи: " + taskDtoResponse);

        /*
        TODO если пользователь работник то нужно за ним закрепить задачу и провести изменения в базе данных

        Если пользователь директор то нужно его кинуть на страницу выбора работников
         */


        model.addObject("task", taskDtoResponse);
        model.addObject("user", employeeDtoResponse);
        if(employeeDtoResponse.getRole().equalsIgnoreCase("director")){
            // Сюда добавить всех свободных работников
            List<EmployeeDtoResponse> listFreeEmploy = new ArrayList<>();
            model.addObject("employee_list", listFreeEmploy);
            model.setViewName("fourth_floor/all_free_employee.html");
        }else {
            model.setViewName("thirt_floor/area_employee.html");
        }
        System.out.println("=================================================================");
        return model;
    }
}
