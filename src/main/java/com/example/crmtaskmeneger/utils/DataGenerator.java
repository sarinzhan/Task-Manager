package com.example.crmtaskmeneger.utils;

import com.example.crmtaskmeneger.dto.response.EmployeeDtoResponse;
import com.example.crmtaskmeneger.dto.response.TaskDtoResponse;
import com.example.crmtaskmeneger.entities.Role;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DataGenerator {
    private static Random random = new Random();
    private static String[] roles = new String[]{
      "разработчик",
      "Уборщик",
      "Директор",
      "водитель",
      "секретарь",
      "доставщик",
       "техник",
       "администратор",
       "охранник",
       "повар",
       "бухгалтер",
       "кассир",
       "машинист"
    };

    private static String[] names = new String[]{
      "Маша",
      "Саша",
      "Александр",
      "Саринджан",
      "Артур",
      "Фатима",
      "Аделя",
      "Амантур",
      "Руслан",
      "Лиза",
      "Вова",
      "Влад",
      "Владимир",
      "Вадим",
    };
    private static String[] patrols = new String[]{
            "Александрровичь",
            "Саринджаннович",
            "Артурович",
            "Амантурович",
            "Русланович",
            "Владимирович",
            "Вадимович",
    };
    private static String[] serNames = new String[]{
            "Бочкарев",
            "Петров",
            "Уллу Улу",
            "Голбоглазов",
            "Путин",
            "Криворучка",
            "Перелезбобра",
            "Перегрызипровод",
            "Недопил",
            "Перепил",
            "Недолил",
    };


    public static List<TaskDtoResponse> generatorListToTaskResponse(){
        List<TaskDtoResponse> genirateList = new ArrayList<>();
        for (int i = random.nextInt(1001) + 10; i > 0; i--) {
            genirateList.add(getneratorTaskResponse());
        }
        return genirateList;
    }

    public static TaskDtoResponse getneratorTaskResponse(){
        TaskDtoResponse response = new TaskDtoResponse();
        response.setDescription("Описание задачи " + random.nextInt());
        response.setCompletionDate(LocalDate.of(random.nextInt(2024 - 1970 + 1) + 1970,
                random.nextInt(12 - 1 + 1)  + 1 ,
                random.nextInt(28 - 1 + 1)  + 1 ).toString());
        response.setAssignedDate(LocalDate.of(random.nextInt(2024 - 1970 + 1) + 1970,
                random.nextInt(12 - 1 + 1)  + 1 ,
                random.nextInt(28 - 1 + 1)  + 1 ).toString());
        response.setCompletionDate(LocalDate.of(random.nextInt(2024 - 1970 + 1) + 1970,
                random.nextInt(12 - 1 + 1)  + 1 ,
                random.nextInt(28 - 1 + 1)  + 1 ).toString());
        response.setAssignedTo(generatorEmployeeDtoResponse().getId());
        response.setCreatedBy(generatorEmployeeDtoResponse().getId());
        response.setActivity("" + random.nextBoolean());


        return response;
    }

    public static EmployeeDtoResponse generatorEmployeeDtoResponse(){
        EmployeeDtoResponse response = new EmployeeDtoResponse();
        response.setId((long)random.nextInt(1000000) + 1);
        response.setFirstName(names[random.nextInt(names.length)]);
        response.setMiddleName(patrols[random.nextInt(patrols.length)]);
        response.setLastName(serNames[random.nextInt(serNames.length)]);
        response.setEmail("email" + random.nextInt() + "@mail.ru");
        response.setHireDate(LocalDate.of(random.nextInt(2024 - 1970 + 1) + 1970,
                                            random.nextInt(12 - 1 + 1)  + 1 ,
                                            random.nextInt(28 - 1 + 1)  + 1 ).toString());
        response.setPhoneNum("+" + random.nextLong(996999999999l - 996200000000l + 1) + 996200000000l);
        response.setRole(Role.values()[random.nextInt(Role.values().length)]);
        response.setLogin("login_" + random.nextInt());
        return response;
    }
}
