package xyz.iotcode.generate.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.iotcode.generate.entity.GenConfig;
import xyz.iotcode.generate.mapper.GenConfigMapper;
import xyz.iotcode.generate.service.GenConfigService;

/**
 * @author Zheng Jie
 * @date 2019-01-14
 */
@Service
public class GenConfigServiceImpl implements GenConfigService {

    @Autowired
    private GenConfigMapper genConfigMapper;

    @Override
    public GenConfig find() {
        return genConfigMapper.selectById(1L);
    }

    @Override
    public GenConfig update(GenConfig genConfig) {
        genConfig.setId(1L);
        genConfigMapper.insert(genConfig);
        return genConfig;
    }
}
