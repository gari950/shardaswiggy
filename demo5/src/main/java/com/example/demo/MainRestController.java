package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@RestController
public class MainRestController {
    @Autowired
    CredentialRepository credentialRepository;


    @Autowired
    private UserdetailRepository userdetailRepository;


    @Autowired
    UsertypelinkRepository usertypelinkRepository;

    @GetMapping("/landingpage")
    public String getLandingPage() {
        return "landingpage";
    }

    @PostMapping("/landingpage")
    public String signup(@RequestParam("username") String username, @RequestParam("password") String password, HttpSession session) {

        Credential credential = new Credential();
        credential.setUsername(username);
        credential.setPassword(password);
        session.setAttribute("username", username);
        credentialRepository.save(credential);
        return "Welcome "+ username;
    }

    // Handle GET and POST requests for /signup
    @RequestMapping(value = "/landingpage", method = {RequestMethod.GET, RequestMethod.POST})
    public String signUpForm() {
        // Add your logic here to display the sign-up form or perform other actions
        return "dashboard"; // Assuming "signup" is the name of your Thymeleaf signup.html template
    }

    @RequestMapping(value = "/usertype", method = {RequestMethod.GET, RequestMethod.POST})
    public String userTypeForm() {
        // Add your logic here to display the sign-up form or perform other actions
        return "usertype"; // Assuming "signup" is the name of your Thymeleaf signup.html template
    }


    @PostMapping("/dashboard")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password, HttpSession session, Model model) {

        Optional<Credential> credValue = credentialRepository.findById(username);

        if (credValue.isPresent()) {
            if (credValue.get().getPassword().equals(password)) {
                session.setAttribute("username", username);
                Optional<Userdetail> userdetail = userdetailRepository.findById(username);

                List<Usertypelink> usertypelinks = usertypelinkRepository.findAll();
                Optional<Usertypelink> usertypelink = usertypelinks.stream().filter(usertypelink1
                        -> usertypelink1.getUsername().equals(username)).findAny();

                if (userdetail.isPresent()) {

                    model.addAttribute("userdetail", userdetail.get());
                    if (usertypelink.isPresent()) {
                        if (usertypelink.get().getType().equals("BUYER")) {
                            return "buyerdashboard";
                        } else if (usertypelink.get().getType().equals("SELLER")) {
                            return "sellerdashboard";
                        } else {
                            return "dashboard";
                        }
                    } else {
                        return "dashboard";
                    }
                } else return "dashboard";
            } else return "landingpage";
        } else return "landingpage";
    }





}