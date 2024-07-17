package ru.exfadra.homeRent.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.exfadra.homeRent.model.Housing;
import ru.exfadra.homeRent.service.HousingService;
import ru.exfadra.homeRent.service.SearchService;
import ru.exfadra.homeRent.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
@RequestMapping("/")
public class HomeController {

    private final UserService userService;
    private final HousingService housingService;
    private final SearchService searchService;
    @GetMapping("")
    public String getHome(Model model){
        model.addAttribute("housings", housingService.getAllHousings());

        return "home";
    }

    @GetMapping("newHousing")
    public String newItem(Model model){
        model.addAttribute("housing", new Housing());
        return "admin/newItem";

    }

    @PostMapping(value = "newItem")
    public String saveNewItem(@ModelAttribute Housing housing, RedirectAttributes redirectAttributes){
        housingService.saveHousing(housing);
        redirectAttributes.addFlashAttribute("activation", "housing" + " added succesfully");
        return "redirect: /";
    }
}
