package com.example.pbl04.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping
    public String showHome()
    {
        return "b";
    }

    @GetMapping("/h")
    public String a()
    {
        return "c";
    }


}
