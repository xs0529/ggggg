package xyz.iotcode.generate.service.impl;

import cn.hutool.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.iotcode.generate.entity.GenConfig;
import xyz.iotcode.generate.entity.vo.ColumnInfo;
import xyz.iotcode.generate.entity.vo.TableInfo;
import xyz.iotcode.generate.mapper.GenConfigMapper;
import xyz.iotcode.generate.service.GeneratorService;
import xyz.iotcode.generate.utils.GenUtil;
import java.io.IOException;
import java.util.List;

/**
 * @author Zheng Jie
 * @date 2019-01-02
 */
@Service
public class GeneratorServiceImpl implements GeneratorService {

    @Autowired
    private GenConfigMapper genConfigMapper;

    @Override
    public Object getTables(String name, int[] startEnd) {
        List<TableInfo> tableInfos = genConfigMapper.tableInfos(name);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("content", tableInfos);
        jsonObject.put("totalElements", tableInfos.size());
        return jsonObject;
    }

    @Override
    public Object getColumns(String name) {
        List<ColumnInfo> columnInfos = genConfigMapper.columnInfos(name);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("content", columnInfos);
        jsonObject.put("totalElements", columnInfos.size());
        return jsonObject;
    }

    @Override
    public void generator(List<ColumnInfo> columnInfos, GenConfig genConfig, String tableName) {
        /*if(genConfig.getId() == null){
            throw new BadRequestException("请先配置生成器");
        }*/
        try {
            GenUtil.generatorCode(columnInfos,genConfig,tableName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
