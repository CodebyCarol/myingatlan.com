package com.codecool.myingatlan.controller;


import com.codecool.myingatlan.services.RealEstateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;


@Controller
public class RenderController {

    @Autowired
    private RealEstateService realEstateService;


    @RequestMapping(value = "/", method = GET)
    public String renderIndexPage(Model model) {
        //TODO
//      model.addAttribute("isLogged", session.getAttribute("name")); // ha ez true, van session, ha nem, akkor nincs -
        model.addAttribute("list", true);
        model.addAttribute("realestatelist", realEstateService.getAll());
        return "index";
    }


    @RequestMapping(value = "/addnew", method = POST)
    @ResponseBody
    public RedirectView addNew(@RequestParam("description") String description,
                               @RequestParam("squaremeter") int squaremeter,
                               @RequestParam("district") int district,
                               @RequestParam("price") int price) {
        realEstateService.addNewRealEstate(description, district, squaremeter, price);
            return new RedirectView("list");
        }

    @RequestMapping(value = "/addnew", method = GET)
    public String renderAddNewPage(Model model) {
        model.addAttribute("realestates", realEstateService.getAll());
        return "addnew";
    }

    @RequestMapping(value = "/list", method = GET)
    public String listAll(Model model) {
        model.addAttribute("listestates", realEstateService.getAll());
        return "list";
    }

}

