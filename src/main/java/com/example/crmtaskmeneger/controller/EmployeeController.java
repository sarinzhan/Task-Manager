package com.example.crmtaskmeneger.controller;

import com.example.crmtaskmeneger.dto.TaskAuthorDto;
import com.example.crmtaskmeneger.dto.TaskExecutorDto;
import com.example.crmtaskmeneger.dto.UserDto;
import com.example.crmtaskmeneger.dto.request.EmployeeDtoRequest;
import com.example.crmtaskmeneger.dto.response.EmployeeDtoResponse;
import com.example.crmtaskmeneger.dto.response.TaskDtoResponse;
import com.example.crmtaskmeneger.entities.Employee;
import com.example.crmtaskmeneger.entities.Role;
import com.example.crmtaskmeneger.entities.Task;
import com.example.crmtaskmeneger.mapping.MappingUser;
import com.example.crmtaskmeneger.mapping.TaskMapping;
import com.example.crmtaskmeneger.model.SelectingAnActionWhenCreatingATask;
import com.example.crmtaskmeneger.service.EmployeeService;
import com.example.crmtaskmeneger.service.TaskService;
import com.example.crmtaskmeneger.utils.DataGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.proxy.Mixin;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;


@Controller
@Scope("request")
public class EmployeeController {
    private final EmployeeService employeeService;
    private final TaskService taskService;


    @Autowired
    public EmployeeController(EmployeeService employeeService, TaskService taskService) {
        this.employeeService = employeeService;
        this.taskService = taskService;
    }

    @GetMapping(value = "/new_task")
    public ModelAndView newTask(ModelAndView model, @ModelAttribute("user") UserDto userDto){
        System.out.println("=======================================================================================");
        System.out.println("Зашли в контролер созданной задачи GET:  com.example.crmtaskmeneger.controller.EmployeeController.newTask()");
        System.out.println("Пришол пользователь Активный: " + userDto );
        model.addObject("user", userDto);
        model.addObject("task", new TaskDtoResponse());
        model.setViewName("fourth_floor/new_task.html");
        System.out.println("=======================================================================================");
        return model;
    }
    @PostMapping(value = "/new_task")
    public ModelAndView newTaskCreate(
            ModelAndView model,
            @ModelAttribute("user") UserDto userDto,
            @RequestParam(name = "action", required = false) SelectingAnActionWhenCreatingATask selectEmployee,
            @ModelAttribute(name = "employee") EmployeeDtoResponse employeeDto,
            @ModelAttribute(name = "taskAuthorDto") TaskAuthorDto taskAuthorDto,
            @ModelAttribute(name = "taskExecutorDto") TaskExecutorDto taskExecutorDto,
            @ModelAttribute("task") TaskDtoResponse taskDtoResponse
            ){
        // ============================    присвоение данных из фронта ===============================================
        taskDtoResponse.setCreatedBy(taskAuthorDto); // Ничего умнее не придумал как отправлять автора отдельно и присваивать тут
        taskDtoResponse.setAssignedTo(taskExecutorDto);
        // ============================================================================================================================

        System.out.println("=======================================================================================");
        System.out.println("Зашли в контролер созданной задачи POST:  com.example.crmtaskmeneger.controller.EmployeeController.newTaskCreate()");
        System.out.println("Пришел пользователь Активный: " + userDto );
        System.out.println("Пришел Автор задания : " + taskAuthorDto );
        System.out.println("Пришел задача : " + taskDtoResponse );


        Employee executor = employeeService.getById(taskExecutorDto.getExecutorId());
        Employee author = employeeService.getById(taskAuthorDto.getAuthorId());
        Task task = TaskMapping.mapModelTaskDtoResponseWithAuthExecToEntity(taskDtoResponse,executor,author);
        taskService.createTask(task);


        if(userDto.getUserRole().equals(Role.DIRECTOR)){
            if( Objects.nonNull(selectEmployee) && selectEmployee.equals(SelectingAnActionWhenCreatingATask.SELECT_EMPLOYEE)) {
            /*
            TODO Сюда добавить бизнес логику котора будет отдавать список свободных сотрудников
                Если директор решит выбрать сотрудника при создании задачи то для
                отображения на странице свободных сотрудников ему нужно их отобразить
             */

//                List<EmployeeDtoResponse> freeEmployees = DataGenerator.generatorListEmployees();// Имитация списака сотрудников Заменить на Бизнес логику
                List<Employee> freeEmployees = employeeService.getFreeEmployee();
                model.addObject("employee_list", freeEmployees);
                model.setViewName("fourth_floor/all_free_employee.html");
//                System.out.println("=======================================================================================");
            }else  model.setViewName("thirt_floor/area_director.html");
//            System.out.println("=======================================================================================");
        }else {
            /*
            TODO добавить бизнес логику для обычного сотрудника и сохранения в базе данных все изменения связанные с данным сотрудником
                1) изменения статуса активности сотрудника на свободного или занятого
             */
            model.setViewName("thirt_floor/area_employee.html");
        }
//        System.out.println("=======================================================================================");
        model.addObject("task", taskDtoResponse);
        model.addObject("user", userDto);
        model.addObject("taskAuthorDto", taskAuthorDto);
        model.addObject("taskExecutorDto", taskExecutorDto);
        return model;
    }

    @GetMapping("/all_free_task")
    public ModelAndView getAllFreeTask(
            ModelAndView model,
            @ModelAttribute(name = "user") UserDto userDto
    ){

        List<TaskDtoResponse> taskList = DataGenerator.generatorListToTaskResponse();

        /*
        TODO тут должна быть бизнес логика фильтровки выдачи свободных заданий
        для директора все свободные задачи
        для работника фильтрация по его специальности или доступу (По роли и должны быть свободными)
         */
        model.addObject("task_list", taskList);
        model.addObject("user", userDto);
        model.setViewName("fourth_floor/all_tasks.html");
        return model;
    }

    @GetMapping("/all_employee")
    public ModelAndView getAllEmploy(ModelAndView model, @ModelAttribute(name = "user") UserDto userDto){

        /*
        TODO реализовать бизнес логику которая будет возврощать всех сотрудников из системы
         */
        List<EmployeeDtoResponse> employeeDtoResponseList = DataGenerator.generatorListEmployees();

        model.addObject("user", userDto);
        model.addObject("employees_list", employeeDtoResponseList);
        model.setViewName("fourth_floor/all_employees.html");
        return model;
    }

    @GetMapping(value = "/search_employee_by_parameter")
    public ModelAndView searchEmployeeGet(ModelAndView model, @ModelAttribute("user") UserDto userDto){
        System.out.println("=======================================================================================");
        System.out.println("Зашли в контролер созданной задачи POST:  com.example.crmtaskmeneger.controller.EmployeeController.searchEmployee() : GET");
        System.out.println("Пришел пользователь Активный: " + userDto );
        model.addObject("user", userDto);
        model.setViewName("fourth_floor/search_employee_by_parameter.html");
        System.out.println("=======================================================================================");
        return model;
    }

    @PostMapping(value = "/search_employee_by_parameter")
    public ModelAndView searchEmployeePost(
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
        TODO тут скорей всего нужно вернуть пользователей списком так как поиск сотрудника это относительная действие
                    и нужно вывести несколько возможных результатов а если сотрудник найдеться один все равно вывести его в виде колекции
                    если задачи не будут найдены вернуть пустой список сотрудников СПИСОК ДОЛЖЕН БЫТЬ != NULL
                    (Я на строничке сделаю логику отоброжения сообщения)
         */

        List<EmployeeDtoResponse> employeeDtoResponseList = new ArrayList<>(); // Найденные сотрудники по параметрам
        model.addObject("employees_list", employeeDtoResponseList);
        model.addObject("user", userDto);
        model.setViewName("fourth_floor/all_employees.html"); // Будем возвращать их на страничку всех сотрудников
        System.out.println("=======================================================================================");
        return model;
    }
}
