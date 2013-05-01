package com.johnathanmarksmith.savenames.controller;


import com.johnathanmarksmith.savenames.model.Message;
import com.johnathanmarksmith.savenames.service.MessageService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.apache.log4j.Logger.getLogger;

/**
 * Date:   5/1/13 / 8:00 AM
 * Author: Johnathan Mark Smith
 * Email:  john@johnathanmarksmith.com
 * <p/>
 * Comments:
 * <p/>
 * this controller is used to display the search screen and to do the search on the database and return the list.
 */


@Controller
@RequestMapping("/search")
public class SearchController
{

    @Autowired
    protected MessageService mService;

    private static final Logger LOGGER = getLogger(SearchController.class);

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView displayRequestPage()
    {
        Map<String, Object> model = new HashMap<String, Object>();

        model.put("message", new Message());
        return new ModelAndView("search", model);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView displayRequestPage(@ModelAttribute("message") Message message, BindingResult result)
    {
        Map<String, Object> model = new HashMap<String, Object>();

        List<Message> myList = mService.searchMessages(message.getMessage());
        model.put("names", myList);
        return new ModelAndView("search", model);
    }


}

