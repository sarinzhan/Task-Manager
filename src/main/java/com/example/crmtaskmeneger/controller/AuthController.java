package com.example.crmtaskmeneger.controller;

import com.example.crmtaskmeneger.dto.request.EmployeeDtoRequest;
import com.example.crmtaskmeneger.dto.response.EmployeeDtoResponse;
import com.example.crmtaskmeneger.mapping.EmployeeMapping;
import org.hibernate.boot.model.source.spi.EmbeddableMapping;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.Random;

@Controller
public class AuthController {

    @PostMapping("/register")
    public ModelAndView login(ModelAndView model, @ModelAttribute(name = "user")EmployeeDtoRequest dtoRequest ) {

        // TODO: дописать сюда бизнес логику регистрации и сохранения нового пользователя в системе

        EmployeeDtoResponse dtoResponse = new EmployeeDtoResponse();
        Random random = new Random();
        dtoResponse.setId((long)random.nextInt(101) + 1);
        dtoResponse.setRole(dtoRequest.getRole());
        dtoResponse.setPhoneNum(dtoRequest.getPhoneNum());
        dtoResponse.setLogin(dtoRequest.getLogin());
        dtoResponse.setMiddleName(dtoRequest.getMiddleName());
        dtoResponse.setLastName(dtoRequest.getLastName());
        dtoResponse.setHireDate(LocalDate.parse(dtoRequest.getHireDate()));
        dtoResponse.setFirstName(dtoRequest.getFirstName());
        dtoResponse.setEmail(dtoRequest.getEmail());

        model.addObject("user", dtoResponse);

        if(dtoResponse.getRole().equalsIgnoreCase("DIRECTOR")){
            model.setViewName("thirt_floor/area_director");
        }else {
            model.setViewName("thirt_floor/area_employee");
        }
        return model;
    }
    @PostMapping("/login")
    public ModelAndView register(
            @RequestParam String login,
            @RequestParam String password,
            ModelAndView model
    ) {

        // TODO: дописать сюда бизнес логику проверки и входа доступа пользователя к системе

        //  =====================  Имитация входа пользователя в систему =================================
        EmployeeDtoResponse dtoResponse = new EmployeeDtoResponse();
        dtoResponse.setId(1l);
        dtoResponse.setLogin(login);
        dtoResponse.setPassword(password);
        dtoResponse.setRole("DIRECTOR");
        //  =====================  Имитация входа пользователя в систему =================================



        model.addObject("user", dtoResponse);
        if(dtoResponse.getRole().equalsIgnoreCase("DIRECTOR")){
            model.setViewName("thirt_floor/area_director");
        }else {
            model.setViewName("thirt_floor/area_employee");
        }

        return model;
    }

}
