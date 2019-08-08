package xyz.iotcode.generate.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;


/**
 * 代码生成配置
 * @author Zheng Jie
 * @date 2019-01-03
 */
@Data
@TableName(value = "gen_config")
public class GenConfig extends Model<GenConfig> {

    @TableId(type = IdType.AUTO)
    private Integer id;

    /** 包路径 **/
    private String pack;

    /** 模块名 **/
    @TableField(value = "module_name")
    private String moduleName;

    /** 前端文件路径 **/
    private String path;

    /** 前端文件路径 **/
    @TableField(value = "api_path")
    private String apiPath;

    /** 作者 **/
    private String author;

    /** 表前缀 **/
    private String prefix;

    /** 是否覆盖 **/
    private Boolean cover;
}
