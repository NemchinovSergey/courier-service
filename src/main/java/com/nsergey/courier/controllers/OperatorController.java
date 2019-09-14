package com.nsergey.courier.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class OperatorController {

    @GetMapping(value = "/operator")
    public ModelAndView index() {
        ModelAndView retVal = new ModelAndView();
        retVal.setViewName("operator");
        retVal.addObject("title", "Operator cabinet");
        return retVal;
    }

}
