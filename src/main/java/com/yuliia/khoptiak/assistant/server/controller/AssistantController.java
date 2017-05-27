package com.yuliia.khoptiak.assistant.server.controller;

import com.yuliia.khoptiak.assistant.server.entity.Assistant;
import com.yuliia.khoptiak.assistant.server.service.AssistantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AssistantController {

    @Autowired
    private AssistantService service;

    @RequestMapping(value = "/assistant", method = RequestMethod.GET)
    @ResponseBody
    public List<Assistant> getAllAssistantData() {
        return service.getAll();
    }


    @RequestMapping(value = "/assistant/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Assistant getAssistantData(@PathVariable("id") long assistantID) {
        return service.getByID(assistantID);
    }

    @RequestMapping(value = "/assistant", method = RequestMethod.POST)
    @ResponseBody
    public Assistant setAssistantData(@RequestBody Assistant assistant) {
        return service.save(assistant);
    }

    @RequestMapping(value = "/assistant/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public void  delete(@PathVariable("id") long id) {
        service.remove(id);
    }
}