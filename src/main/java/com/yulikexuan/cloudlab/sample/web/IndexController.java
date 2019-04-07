//: com.yulikexuan.cloudlab.sample.web.IndexController.java


package com.yulikexuan.cloudlab.sample.web;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class IndexController {

    @GetMapping("/index")
    public String index() {
        return "jesuschrist.html";
    }

}///:~