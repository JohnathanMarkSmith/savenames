package com.johnathanmarksmith.savenames.controller;


import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

import static org.apache.log4j.Logger.getLogger;



@Controller
@RequestMapping("/list")
public class RequestController
{

    private static final Logger LOGGER = getLogger(RequestController.class);

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView displayRequestPage(HttpSession session)
    {
        Map<String, Object> model = new HashMap<String, Object>();

        return new ModelAndView("list", model);
    }
}

