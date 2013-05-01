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

import javax.servlet.http.HttpSession;
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
 * This control is used to display the add screen and then add the record to the database and show a list.
 */


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

        model.put("names", myList);
        model.put("message", new Message());
        return new ModelAndView("add", model);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView displayAddRequestPage(@ModelAttribute("message") Message message)
    {
        Map<String, Object> model = new HashMap<String, Object>();

        mService.SaveMessage(message);

        List<Message> myList = mService.listMessages();
        model.put("names", myList);
        return new ModelAndView("add", model);
    }


}

