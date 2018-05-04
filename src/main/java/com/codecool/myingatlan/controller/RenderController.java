package com.codecool.myingatlan.controller;


import com.codecool.myingatlan.services.RealEstateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class RenderController {

    @Autowired
    private RealEstateService realEstateService;


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String renderIndexPage(Model model) {
        //TODO
//      model.addAttribute("isLogged", session.getAttribute("name")); // ha ez true, van session, ha nem, akkor nincs -
        model.addAttribute("list", true);
        model.addAttribute("realestatelist", realEstateService.getAll());
        return "index";
    }
    

    @RequestMapping(value = "/add-new", method = RequestMethod.POST)
    public String addNew(@RequestParam("description") String description,
                         @RequestParam("squaremeter") int squaremeter,
                         @RequestParam("district") int district,
                         @RequestParam("price") int price,
                         Model model) {
        realEstateService.addNewRealEstate(description, district, squaremeter, price);

            return "redirect:login";
        }
}

