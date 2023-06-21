package com.example.testspring;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainRestController {
    @GetMapping("/Hello")
    public String index() {
        return "Greetings from Spring Boot!";
    }
    @PostMapping("/add")
    public String AddTwoNumbers (int a, int b){//(@RequestParam("a") int a, @RequestParam("b") int b){
        return "Sum of numbers is: " + (a+b);
    }
}
