package com.audgml.demo.web;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController implements ErrorController{

  @GetMapping({"/", "/error"})
  public String index() {
    return "index.html";
  }

  @Override
  public String getErrorPath() {
    return "/error"; 
  }
}