package com.support.monitor.server.controller;

import com.support.monitor.commons.binder.transfer.TransmitObject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/monitor")
public class IndexController {

    @PostMapping("/index")
    public String transfer(@RequestBody TransmitObject transmitObject) {
        System.out.println(transmitObject);
        return "ok";
    }

}
