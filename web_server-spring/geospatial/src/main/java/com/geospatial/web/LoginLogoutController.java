package com.geospatial.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.geospatial.dtos.ClientDTO;
import com.geospatial.exceptions.EmailExistsException;
import com.geospatial.exceptions.UsernameExistsException;
import com.geospatial.services.ClientService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginLogoutController {

    private final Logger logger = LoggerFactory.getLogger(LoginLogoutController.class);

    public LoginLogoutController() {
        logger.debug("LoginLogout controller initialized");
    }

    @Autowired
    private ClientService clientService;



    @RequestMapping(value={"/login", "/", ""}, method = RequestMethod.GET)
    public String login(Model model) {
        ClientDTO clientDto = new ClientDTO();
        model.addAttribute("client", clientDto);
        return "login";
    }

    @RequestMapping(value={"/success"}, method = RequestMethod.GET)
    public String success(Model model) {
        ClientDTO clientDto = new ClientDTO();
        model.addAttribute("client", clientDto);
        return "success";
    }

    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }        
        return "redirect:/login?logout";
    }

    @PostMapping("/")
    public String createClient(@Valid @ModelAttribute("client") ClientDTO clientDto, BindingResult result) throws UsernameExistsException, EmailExistsException{
        if(result.hasErrors()){
            return "login";
        }
        try{
            clientService.createClientDTO(clientDto);
        } catch (UsernameExistsException e){
            result.rejectValue("name", "client", "Username already exists...");
        } catch (EmailExistsException e2){
            result.rejectValue("email", "client", "Email already exists...");
        }
        if(!clientDto.getPassword().equals(clientDto.getMatchPassword())){
            result.rejectValue("password", "client", "passwords do not match...");
            result.rejectValue("matchPassword", "client", "passwords do not match...");
        }
        if(result.hasErrors()){
            return "login";
        }
        return "success";

    }




    

}