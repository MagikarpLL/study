package cn.magikarpll.projectrelated.controller.sub;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sublog1")
@Slf4j
public class SubLogController1 {

    @GetMapping("/get")
    public String testLog(){
        log.info("hello this is {} controller", "sub log 1");
        return "success";
    }

}
