package com.johnathanmarksmith.savenames.controller;


import com.johnathanmarksmith.savenames.model.Message;
import com.johnathanmarksmith.savenames.service.MessageService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.apache.log4j.Logger.getLogger;


@Controller
@RequestMapping("/add")
public class AddController
{

    @Autowired
    protected MessageService mService;

    private static final Logger LOGGER = getLogger(AddController.class);

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView displayRequestPage(HttpSession session)
    {
        Map<String, Object> model = new HashMap<String, Object>();

        List<Message> myList = mService.listMessages();
        model.put("names", myList );
        return new ModelAndView("list", model);
    }
}

