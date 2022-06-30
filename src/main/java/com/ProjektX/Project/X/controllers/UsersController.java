package com.ProjektX.Project.X.controllers;

import com.ProjektX.Project.X.model.UsersModel;
import com.ProjektX.Project.X.repository.UsersRepository;
import com.ProjektX.Project.X.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UsersController {

    @Autowired
    private final UsersService usersService;

    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping("/register")
    public String getRegisterPage(Model model){
        model.addAttribute("registerRequest",new UsersModel());
        return "register";
    }

    @GetMapping("/login")
    public String getLoginPage(Model model){
        model.addAttribute("loginRequest",new UsersModel());
        return "login";
    }

    @GetMapping("/index")
    public String getMainPage(){
        return "index";
    }

    @GetMapping("/invoice")
    public String getInvoicePage(){
        return "invoice";
    }

    @GetMapping("/success")
    public String getSuccessPage(){
        return "success";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute UsersModel usersModel){
        System.out.println("register request " + usersModel);

        UsersModel registeredUser = usersService.registerUser(usersModel.getLogin(),
                usersModel.getPassword(),usersModel.getEmail());

        return registeredUser == null ? "error" : "redirect:/success";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute UsersModel usersModel, Model model){
        System.out.println("login request " + usersModel);

        UsersModel authenticated = usersService.authenticate(usersModel.getLogin(),
                usersModel.getPassword());

        if(authenticated != null){
            model.addAttribute("userLogin", authenticated.getLogin());
           return "invoice";
        }else{
            return "error";
        }
    }

}
