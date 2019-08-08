package xyz.iotcode.generate.service;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import xyz.iotcode.generate.entity.GenConfig;

/**
 * @author Zheng Jie
 * @date 2019-01-14
 */
@CacheConfig(cacheNames = "genConfig")
public interface GenConfigService {

    /**
     * find
     * @return
     */
    @Cacheable(key = "'1'")
    GenConfig find();

    /**
     * update
     * @param genConfig
     */
    @CacheEvict(allEntries = true)
    GenConfig update(GenConfig genConfig);
}
