package com.geospatial.web;

import java.util.List;

import com.geospatial.entities.Client;
import com.geospatial.repositories.ClientRepo;
import com.geospatial.services.ClientService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ClientController {

    private final Logger logger = LoggerFactory.getLogger(MainController.class);

    @Autowired
    private ClientService clientService;

    @Autowired
    private ClientRepo clientRepo;

    public ClientController() {
        logger.debug("Client Controller Initialized");
    }

    @RequestMapping(value="/clients", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public List<Client> getClients(Model model) {
        return this.clientService.getClients();
    }

    @RequestMapping("/deleteClients")
    @ResponseBody
    public String deleteClients(){
        clientRepo.deleteClients();
        return "success";
    }











}