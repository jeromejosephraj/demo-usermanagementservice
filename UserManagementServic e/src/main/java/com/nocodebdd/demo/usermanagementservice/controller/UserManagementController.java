package com.nocodebdd.demo.usermanagementservice.controller;

import com.nocodebdd.demo.usermanagementservice.service.UserManagementService;
import com.nocodebdd.demo.usermanagementservice.to.ResponseTO;
import com.nocodebdd.demo.usermanagementservice.to.UserTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@Slf4j
@CrossOrigin
@ComponentScan
@RestController
@RequestMapping(path="/userregistration")
@EnableAutoConfiguration
public class UserManagementController {

    @Autowired
    UserManagementService userManagementService;

    @RequestMapping(value="/add", method=RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseTO addUser(@RequestBody UserTO userTO) {
        log.info("inside UserManagementController {}", userTO);
        ResponseTO responseTO = new ResponseTO();
        try {
            userManagementService.add(userTO);
        }catch (Exception ex){
            log.error(ex.getMessage());
            responseTO.setResponse(ex.getMessage());
            return responseTO;
        }
        responseTO.setResponse("success");
        return responseTO;
    }

    @RequestMapping(value="/checkUser", method=RequestMethod.GET)
    public boolean checkUser(   String userName) {
        log.info("inside UserManagementController");
        UserTO userTO = new UserTO();
        userTO.setUserName(userName);
        return userManagementService.isUserExist(userTO);
    }

}
