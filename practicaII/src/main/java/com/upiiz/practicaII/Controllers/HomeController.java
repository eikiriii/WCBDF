package com.upiiz.practicaII.Controllers;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() { return "index"; }

    @GetMapping("/nuevo")
    public String nuevo() { return "nuevo"; }

    @GetMapping("/edita")
    public String edita() { return "edita"; }
}
