package com.example.crmtaskmeneger.util;

import java.util.Arrays;

public interface OutInfoPointExcecud {

     default void checkingTheOperationOfTheMethodAndThePassedParameters(String method, String description, Object... objectOuts ){
        System.out.println("========  " + this.getClass().getName() + "."+method+"()" + " ===========================");
        System.out.println(description);
         System.out.println("Переданные параметры:");
         for (int i = 0; i <objectOuts.length ; i++) {
             System.out.println(objectOuts[i]);
         }
        System.out.println("======================================================================================");

    }
}
