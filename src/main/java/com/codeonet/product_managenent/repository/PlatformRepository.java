package com.codeonet.product_managenent.repository;

import com.codeonet.product_managenent.entity.Platform;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PlatformRepository extends JpaRepository<Platform, Long> {

    List<Platform> getByPlatformName(String platformName);

    Optional<Platform> findByPlatformName(
            String platformName
    );
}
