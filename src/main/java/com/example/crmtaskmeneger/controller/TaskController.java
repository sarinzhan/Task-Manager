package com.example.crmtaskmeneger.controller;

import com.example.crmtaskmeneger.dto.TaskAuthorDto;
import com.example.crmtaskmeneger.dto.TaskDto;
import com.example.crmtaskmeneger.dto.TaskExecutorDto;
import com.example.crmtaskmeneger.dto.UserDto;
import com.example.crmtaskmeneger.dto.response.EmployeeDtoResponse;
import com.example.crmtaskmeneger.dto.response.TaskDtoResponse;
import com.example.crmtaskmeneger.entities.Employee;
import com.example.crmtaskmeneger.entities.Task;
import com.example.crmtaskmeneger.mapping.TaskMapping;
import com.example.crmtaskmeneger.service.EmployeeService;
import com.example.crmtaskmeneger.service.TaskService;
import com.example.crmtaskmeneger.utils.DataGenerator;
import com.sun.xml.bind.v2.TODO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class TaskController {
    private final EmployeeService employeeService;
    private final TaskService taskService;


    @Autowired
    public TaskController(EmployeeService employeeService, TaskService taskService) {
        this.employeeService = employeeService;
        this.taskService = taskService;
    }


    @GetMapping("/show_task_details")
    public ModelAndView setEmployeeTask(
            ModelAndView model,
            @ModelAttribute("user") UserDto userDto,
            @ModelAttribute(name = "taskAuthorDto") TaskAuthorDto taskAuthorDto,
            @ModelAttribute(name = "taskExecutorDto") TaskExecutorDto taskExecutorDto,
            @ModelAttribute("task") TaskDtoResponse taskDtoResponse
            ){
        // =========================  Востанновление данных =======================================
         taskDtoResponse.setAssignedTo(taskExecutorDto);
         taskDtoResponse.setCreatedBy(taskAuthorDto);
        // ========================================================================================

        System.out.println("========== Зашли в контроллер просмотра выбранной задачи ========================");
        System.out.println("Адрес: com.example.crmtaskmeneger.controller.TaskController.setEmployeeTask()==POST ");
        System.out.println("Пришла модель пользователя: " + userDto);
        System.out.println("Пришла модель задачи: " + taskDtoResponse);

        /*
        TODO если пользователь работник то нужно за ним закрепить задачу и провести изменения в базе данных

        Если пользователь директор то нужно его кинуть на страницу выбора работников
         */


        model.addObject("task", taskDtoResponse);
        model.addObject("user", userDto);
        model.setViewName("fourth_floor/show_task_details.html");

        System.out.println("=================================================================");
        return model;
    }


    @GetMapping("/all_tasks")
    public ModelAndView getAllTasks(ModelAndView model, @ModelAttribute(name = "user")UserDto userDto,
                                    @ModelAttribute(name="author") TaskAuthorDto taskAuthorDto,
                                    @ModelAttribute (name="executor") TaskExecutorDto taskExecutorDto
    ){
        System.out.println("=======================================================================================");
        System.out.println("Зашли в контролер созданной задачи POST:  com.example.crmtaskmeneger.controller.TaskController.getAllTask()");
        System.out.println("Пришел пользователь Активный: " + userDto );


        //List<TaskDtoResponse> tasksList = DataGenerator.generatorListToTaskResponse();
        List<TaskDto> ListTaskDto = TaskMapping.mapModelListEntityToDto( taskService.getAvailTask());
        // вывод все доступных задач выполнена
        //TODO передалать объект taskDtoResponse в TaskDto в thymeleaf

        model.addObject("task_list", ListTaskDto);

        System.out.println("=======================================================================================");

        model.addObject("user", userDto);
        model.setViewName("fourth_floor/all_tasks.html");
        return model;
    }

    @GetMapping(value = "/search_task_by_parameter")
    public ModelAndView searchTaskGet(ModelAndView model, @ModelAttribute("user") UserDto userDto){
        System.out.println("=======================================================================================");
        System.out.println("Зашли в контролер созданной задачи POST:  com.example.crmtaskmeneger.controller.EmployeeController.searchEmployee() : GET");
        System.out.println("Пришел пользователь Активный: " + userDto );
        model.addObject("user", userDto);
        model.setViewName("fourth_floor/search_task_by_parameter.html");
        System.out.println("=======================================================================================");
        return model;
    }

    @PostMapping(value = "/search_task_by_parameter")
    public ModelAndView searchTaskPost(
            ModelAndView model,
            @ModelAttribute("user") UserDto userDto,
            @RequestParam(name = "name",required = false)      String name,
            @RequestParam(name = "patrol" ,required = false)   String patrol,
            @RequestParam(name = "serName" ,required = false)  String serName,
            @RequestParam(name = "login" ,required = false)    String login,
            @RequestParam(name = "email" ,required = false)    String email,
            @RequestParam(name = "phone" ,required = false)    String phone,
            @RequestParam(name = "hireDate" ,required = false) String hireDate
    ){

        /*
        Параметры могут быть пустыми так что сделай проверку на всякий случай
         */
        System.out.println("=======================================================================================");
        System.out.println("Зашли в контролер созданной задачи POST:  com.example.crmtaskmeneger.controller.EmployeeController.searchEmployee() : POST");
        System.out.println("Пришел пользователь Активный: " + userDto );
        System.out.println("Параметр имя " +         name);
        System.out.println("Параметр отчество " +    patrol);
        System.out.println("Параметр фамилия " +     serName);
        System.out.println("Параметр логин " +       login);
        System.out.println("Параметр почта " +       email);
        System.out.println("Параметр телефон " +     phone);
        System.out.println("Параметр дата найма " +  hireDate);

        /*
        TODO Контроллер который должен найти задачи по пришедшим параметра и вернуть список задач которые будут найдены
            Если задачи не будут найдены вернуть пустой список СПИСОК ДОЛЖЕН БЫТЬ != NULL
            (Я на страничке сделаю логику ото брожения сообщения)
         */

        List<EmployeeDtoResponse> employeeDtoResponseList = new ArrayList<>(); // Найденные сотрудники по параметрам
        model.addObject("employees_list", employeeDtoResponseList);
        model.addObject("user", userDto);
        model.setViewName("fourth_floor/all_tasks.html"); // Будем возвращать их на страничку всех заданий
        System.out.println("=======================================================================================");
        return model;
    }
}
