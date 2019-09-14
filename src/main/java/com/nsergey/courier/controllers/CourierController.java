package com.nsergey.courier.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CourierController {

    @GetMapping(value = "/courier")
    public ModelAndView index() {
        ModelAndView retVal = new ModelAndView();
        retVal.setViewName("courier");
        retVal.addObject("title", "Courier cabinet");
        return retVal;
    }

}