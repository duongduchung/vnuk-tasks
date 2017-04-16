/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.edu.vnuk.tasks.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author michel
 */

@Controller
public class HelloWorldController {
    
    @RequestMapping("/helloworld")
    public String execute(){
        return "hello";
    }
    
    @RequestMapping("/hellospring")
    public String execute(ModelMap modelMap){
        modelMap.put("greeting", "Hello Spring");
        return "hello-spring";
    }
    
}
