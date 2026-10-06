package com.codeonet.product_managenent.service;

import com.codeonet.product_managenent.entity.Platform;

import java.util.List;

public interface PlatformService {

    public List<Platform> getAllPlatform();
    public Platform addPlatform(Platform platform);
    public List<Platform> searchByPlatformName(String platfornName);
}
