package ru.exfadra.homeRent.controllers.exception;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

@Controller
@RequiredArgsConstructor
public class ErrorPageController implements ErrorController {

    private final ErrorAttributes errorAttributes;

    @RequestMapping("/error")
    public String error(Model model, WebRequest webRequest){
        Map<String, Object> errorAttributes = this.errorAttributes.getErrorAttributes(webRequest, ErrorAttributeOptions.defaults());
        model.addAttribute("timestamp", errorAttributes.get("timestamp"));
        model.addAttribute("message", errorAttributes.get("message"));
        model.addAttribute("error", errorAttributes.get("error"));
        model.addAttribute("path", errorAttributes.get("status"));
        return "/error";

    }

}
