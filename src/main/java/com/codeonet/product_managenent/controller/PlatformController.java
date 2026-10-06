package com.codeonet.product_managenent.controller;

import com.codeonet.product_managenent.entity.Platform;
import com.codeonet.product_managenent.service.PlatformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
 @RequestMapping("/platform")
public class PlatformController {

    @Autowired
    private PlatformService service;

    @GetMapping("/getAll")
    public List<Platform> getPlatform(){
        return service.getAllPlatform();
    }

    @PostMapping("/add")
    public Platform addPlatform(@RequestBody Platform platform){
        return service.addPlatform(platform);
    }

    @GetMapping("/findByName/{platformName}")
    public List<Platform> findByOlatformName(@PathVariable String platformName){
        return service.searchByPlatformName(platformName);
    }

}
