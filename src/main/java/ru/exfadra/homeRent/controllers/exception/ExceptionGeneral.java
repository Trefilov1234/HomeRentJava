package ru.exfadra.homeRent.controllers.exception;


import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionGeneral {

    @ExceptionHandler
    public String exception(Exception e, Model model){
        model.addAttribute("exception", e);
        System.out.println("Cause: " + e.getCause() +" " + e.getLocalizedMessage());
        return "/error";
    }
}
