package ru.exfadra.homeRent.controllers;

import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.exfadra.homeRent.model.Housing;
import ru.exfadra.homeRent.model.User;
import ru.exfadra.homeRent.service.UserService;


@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    @GetMapping("user/profile/{nickname}")
    public String openProfile(@PathVariable("nickname") String nickname, Model model){
        User user=userService.getUser(nickname);
        if(user.getTenantRating()<2||user.getLandLordRating()<2)
        {
            user.setEnable(false);
        }
        model.addAttribute("user", user);
        model.addAttribute("newUser", new User());
        if(!user.isEnable()|| user.getTenantRating()<2||user.getLandLordRating()<2) {
            model.addAttribute("ban","user is blocked");
            return "/login";
        }
        return "user/profile";

    }
    @PostMapping("/user/profile/updateData/{nickname}")
    public String updateData(@PathVariable("nickname") String nickname,@ModelAttribute User user, RedirectAttributes redirectAttributes)
    {
        User currUser=userService.getUser(nickname);
        user.setRoles(currUser.getRoles());
        user.setId(currUser.getId());
        user.setEnable(currUser.isEnable());
        user.setTenantRating(currUser.getTenantRating());
        user.setLandLordRating(currUser.getLandLordRating());
        if(user.getBirthday()==null)
        {
            user.setBirthday(currUser.getBirthday());
        }
        if(user.getPassword()==null||user.getPassword().isEmpty()) {
            user.setPassword(currUser.getPassword());
        }
        else {
            PasswordEncoder encoder = new BCryptPasswordEncoder();
            String encodedPassword = encoder.encode(user.getPassword());
            user.setPassword(encodedPassword);
        }
        userService.saveUser(user);

        redirectAttributes.addFlashAttribute("user", user);
        redirectAttributes.addFlashAttribute("newUser", new User());
        return "redirect:../"+user.getNickname();
    }
}
