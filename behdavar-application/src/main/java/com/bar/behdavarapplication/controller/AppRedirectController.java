package com.bar.behdavarapplication.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AppRedirectController {
    @RequestMapping(value = "/app", method = RequestMethod.GET)
    public String redirect() {
        return "forward:/index.html";
    }
}
