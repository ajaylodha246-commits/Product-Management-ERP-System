package com.codeonet.product_managenent.service.impl;

import com.codeonet.product_managenent.entity.Platform;
import com.codeonet.product_managenent.repository.PlatformRepository;
import com.codeonet.product_managenent.service.PlatformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlatformServiceImpl implements PlatformService {
    @Autowired
    private PlatformRepository repo;

    public List<Platform> getAllPlatform(){
        return repo.findAll();
    }
    public Platform addPlatform(Platform platform){
        return repo.save(platform);
    }
    public List<Platform> searchByPlatformName(String platformName){
        return repo.getByPlatformName(platformName);
    }
}
