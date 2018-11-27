package it.nextre.academy.restdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class GenericController {


    @GetMapping({"/","/index","/home"})
    public String getHomePage(Model model){
        List<String> listaNomi = new ArrayList<>(Arrays.asList("Valerio","Francesca","Simone"));
        model.addAttribute("vincitori",listaNomi);
        model.addAttribute("titolo","Movie List");
        return "index";

    }





}
