package com.johnathanmarksmith.savenames.controller;


import com.johnathanmarksmith.savenames.model.Message;
import com.johnathanmarksmith.savenames.service.MessageService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.apache.log4j.Logger.getLogger;

/**
 * Date:   5/1/13 / 7:59 AM
 * Author: Johnathan Mark Smith
 * Email:  john@johnathanmarksmith.com
 * <p/>
 * Comments:
 * <p/>
 * This controller is use to return a list of all the names on the database
 */


@Controller
@RequestMapping("/list")
public class ListController
{

    @Autowired
    protected MessageService mService;

    private static final Logger LOGGER = getLogger(ListController.class);

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView displayRequestPage()
    {
        Map<String, Object> model = new HashMap<String, Object>();

        List<Message> myList = mService.listMessages();
        model.put("names", myList);
        return new ModelAndView("list", model);
    }
}

