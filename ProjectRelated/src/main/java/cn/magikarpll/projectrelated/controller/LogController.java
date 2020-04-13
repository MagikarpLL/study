package cn.magikarpll.projectrelated.controller;

import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/log1")
@Slf4j
public class LogController {

    @GetMapping("/get")
    public String testLog(){
        log.info("hello this is {} controller", "parent log 1");
        return "success";
    }

}
