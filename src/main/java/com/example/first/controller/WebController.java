package com.example.first.controller;

import com.example.first.repository.CountryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class WebController {
    private final CountryRepository countryRepository;
    @GetMapping("/index")
    public String index(Model model){
        var countries = countryRepository.findAll();
        model.addAttribute("countries", countries);
        return "index";
    }
}
