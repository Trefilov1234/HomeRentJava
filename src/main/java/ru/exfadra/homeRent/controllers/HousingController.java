package ru.exfadra.homeRent.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.exfadra.homeRent.model.Housing;
import ru.exfadra.homeRent.model.User;
import ru.exfadra.homeRent.service.HousingService;
import ru.exfadra.homeRent.service.UserService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

@Controller
@RequiredArgsConstructor
public class HousingController {
    private final HousingService housingService;
    private final UserService userService;

    @RequestMapping("/housing/addHousing")
    public String addHousingPage(Model model)
    {
        model.addAttribute("housing",new Housing());
        return "/housing/addHousing";
    }

    @RequestMapping("addHouse/{nickname}")
    public String addHouse(@ModelAttribute Housing housing,@PathVariable("nickname") String nickname,@RequestParam("image1") MultipartFile file,
                           RedirectAttributes redirectAttributes) throws IOException {
        User user=userService.getUser(nickname);
        if(file!=null)
            System.out.println("saff");
        String UPLOADED_FOLDER = System.getProperty("user.dir")+"/src/main/resources/static/images/housingImages";

        Path destPath = Paths.get(UPLOADED_FOLDER,file.getOriginalFilename());
        Files.write(destPath, file.getBytes());
        String originalPathStr = "images/housingImages/"+ destPath.getFileName().toString();
        byte[] encodedPath = Base64.getEncoder().encode(originalPathStr.getBytes());
        housing.setImage(encodedPath);
        housing.setLandLord(user);
        housing.setLandlordDone(false);
        housing.setTenantDone(false);
        housingService.saveHousing(housing);
        redirectAttributes.addFlashAttribute("housings", housingService.getAllHousings());
        return "redirect:/";
    }

    @RequestMapping("/housing/view/{id}")
    public String housingView(@PathVariable("id") Long id,
                              Model model)
    {

        Housing housing=housingService.getHousingById(id);
        model.addAttribute("housing",housingService.getHousingById(id));

        return "/housing/housingView";
    }

    @RequestMapping("rent")
    public String rentHousing(@RequestParam("housingId") String housingId,
                              @RequestParam("nickname") String nickname,
            RedirectAttributes redirectAttributes)
    {
        User user = userService.getUser(nickname);
        if(!user.isEnable()|| user.getTenantRating()<2||user.getLandLordRating()<2) {
            redirectAttributes.addAttribute("ban",true);
            return "/login";
        }
        System.out.println(housingId);
        System.out.println(nickname);
        Housing housing=housingService.getHousingById(Long.parseLong(housingId));
        if(user.getNickname().equals(housing.getLandLord().getNickname()))
        {
            redirectAttributes.addFlashAttribute("someWrong","It is impossible to rent from yourself");
        }
        else {
            housing.setTenant(user);
            housingService.saveHousing(housing);
            redirectAttributes.addFlashAttribute("someWrong","Successfully rented");
        }
        return "redirect:/housing/view/"+housingId;
    }
    @RequestMapping("markTenaten")
    public String markTenaten(@RequestParam("housingId") String housingId,
                              @RequestParam("ratingTenant") String tenantRating,
                              RedirectAttributes redirectAttributes)
    {
        System.out.println(housingId);
        System.out.println(tenantRating);

        Housing housing=housingService.getHousingById(Long.parseLong(housingId));
            if(!tenantRating.isEmpty()) {
                housing.getLandLord().setLandLordRating((housing.getLandLord().getLandLordRating() + Long.parseLong(tenantRating)) / 2);
                housing.setTenantDone(true);

            }
            housingService.saveHousing(housing);
            redirectAttributes.addFlashAttribute("marked","Marked");
        return "redirect:/housing/view/"+housingId;

    }
    @RequestMapping("markLandlord")
    public String markLandlord(@RequestParam("housingId") String housingId,
                             @RequestParam("ratingLandlord") String landlordRating,
                             RedirectAttributes redirectAttributes)
    {
        System.out.println(housingId);

        Housing housing=housingService.getHousingById(Long.parseLong(housingId));

        if(!landlordRating.equals("")) {
            housing.getTenant().setTenantRating((housing.getTenant().getTenantRating() + Long.parseLong(landlordRating)) / 2);
            housing.setLandlordDone(true);
        }
        housingService.saveHousing(housing);

        redirectAttributes.addFlashAttribute("marked","Marked");
        return "redirect:/housing/view/"+housingId;

    }
}
