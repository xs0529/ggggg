package xyz.iotcode.generate.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import xyz.iotcode.generate.entity.GenConfig;
import xyz.iotcode.generate.entity.vo.ColumnInfo;
import xyz.iotcode.generate.entity.vo.TableInfo;

import java.util.List;

/**
 * @author Zheng Jie
 * @date 2019-01-14
 */
public interface GenConfigMapper extends BaseMapper<GenConfig> {

    List<ColumnInfo> columnInfos(@Param("tableName") String tableName);

    List<TableInfo> tableInfos(@Param("tableName") String tableName);
}
