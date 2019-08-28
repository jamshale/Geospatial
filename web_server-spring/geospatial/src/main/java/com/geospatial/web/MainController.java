package com.geospatial.web;

import com.geospatial.dtos.ClientDTO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {

    private final Logger logger = LoggerFactory.getLogger(MainController.class);



    public MainController() {
        logger.debug("Main Controller Initialized");
    }

    @RequestMapping(value="/geo-home", method = RequestMethod.GET)
    public String getHomeHTML(Model model) {
        ClientDTO clientDto = new ClientDTO();
        model.addAttribute("client", clientDto);
        return "index";
    }

    @RequestMapping(value="full")
    public String getFullHTML() {
        return "full";
    }

    @RequestMapping(value="view")
    public String getViewHTML() {
        return "view";
    }

    @RequestMapping(value="download")
    public String getDownloadHTML() {
        return "download";
    }


}