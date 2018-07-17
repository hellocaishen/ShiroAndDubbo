package com.wsbg.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wsbg.entity.User;
import com.wsbg.service.UserService;

@RequestMapping("/test/")
@RestController
public class TestController {
	   
	   @Resource
	   private UserService userService;
       
	   @RequestMapping("log.do")
	   public String getInfo() {
		      List<User> list = userService.findUserAll();
		      return list.toString();
	   }

}
