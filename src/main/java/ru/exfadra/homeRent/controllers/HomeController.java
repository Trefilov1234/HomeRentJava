package ru.exfadra.homeRent.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.exfadra.homeRent.model.Housing;
import ru.exfadra.homeRent.service.HousingService;
import ru.exfadra.homeRent.service.SearchService;
import ru.exfadra.homeRent.service.UserService;

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
    @GetMapping("search")
    public String navSearch(@RequestParam("query") String query,
                            @RequestParam("field") String field,Model model){
        System.out.println(query);
        System.out.println(field);
        if(query.equals(""))
        {
            model.addAttribute("housings", housingService.getAllHousings());
        }
        else {
            switch (field) {
                case "city":
                    model.addAttribute("housings", searchService.getSearchedHousingByCity(query));
                    break;
                case "country":
                    model.addAttribute("housings", searchService.getSearchedHousingByCountry(query));
                    break;
                case "rating":
                    model.addAttribute("housings", searchService.getSearchedHousingByRating(query));
                    break;
                case "rentDays":
                    model.addAttribute("housings", searchService.getSearchedHousingByRentDays(query));
                    break;
                case "price":
                    model.addAttribute("housings", searchService.getSearchedHousingByPrice(query));
                    break;
            }
        }
        model.addAttribute("field", field);
        model.addAttribute("query", query);
        return "home";
    }
}
